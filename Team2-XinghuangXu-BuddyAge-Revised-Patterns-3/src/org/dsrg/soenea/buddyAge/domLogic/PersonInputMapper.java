package org.dsrg.soenea.buddyAge.domLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.dsrg.soenea.buddyAge.techSvc.PersonTDG;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.dsrg.soenea.uow.UoW;

public class PersonInputMapper {

	public static List<Person> findAll() throws MapperException {
		List<Person> people = new Vector<Person>();
		try {
			ResultSet rs = PersonTDG.findAll();
			while (rs.next()) {
				people.add(getOrMakePerson(rs));
			}
		} catch (SQLException e) {
			throw new MapperException(e);
		}
		return people;
	}

	public static Person find(long id, long version) throws MapperException {
		Person person = find(id);
		if (person.getVersion() != version) {
			String warning = "Person '" + person.getName()
					+ "' data is out-of-date: expected version " + version
					+ " but database record is at version "
					+ person.getVersion();
			throw new LostUpdateException(warning);
		}
		return person;
	}

	public static Person find(long id) throws MapperException {
		try {
			return IdentityMap.get(id, Person.class);
		} catch (DomainObjectNotFoundException ex) {
			try {
				ResultSet rs = PersonTDG.find(id);
				if (rs.next()) {
					Person p = getPerson(rs);
					// Register Person As Clean
					UoW.getCurrent().registerClean(p);
					return p;
				}
				throw new DomainObjectNotFoundException(
						"Cannot find a person with id " + id);
			} catch (SQLException e) {
				throw new MapperException(e);
			}
		} catch (ObjectRemovedException ex) {
			return null;
		}
	}

	private static Person getPerson(ResultSet rs) throws SQLException,
			MapperException {

		long buddyId = rs.getLong("buddy");
		IPerson buddy = buddyId > 0 ? new PersonProxy(buddyId) // PersonMapper.find(buddyId)
				: null;
		Person result = PersonFactory.createClean(rs.getLong("p.id"), rs.getInt("p.version"),
				rs.getString("p.name"), rs.getInt("p.age"), buddy);
		return result;
	}

	private static Person getOrMakePerson(ResultSet rs) throws SQLException 
			 {
		Long id = rs.getLong("p.id");
		try {
			return IdentityMap.get(id, Person.class);
		} catch (DomainObjectNotFoundException ex) {
			long buddyId = rs.getLong("buddy");
			IPerson buddy = buddyId > 0 ? new PersonProxy(buddyId) // PersonMapper.find(buddyId)
					: null;
			Person result = PersonFactory.createClean(rs.getLong("p.id"),
					rs.getInt("p.version"), rs.getString("p.name"),
					rs.getInt("p.age"), buddy);
			return result;
		} catch (ObjectRemovedException ex) {
			return null;
		}
	}

}
