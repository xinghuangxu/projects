package org.dsrg.soenea.buddyAge.domLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.dsrg.soenea.buddyAge.techSvc.PersonTDG;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

public class PersonMapper {
	public static List<Person> findAll() throws MapperException {
		List<Person> people = new Vector<Person>();
		try {
			ResultSet rs = PersonTDG.findAll();
			while (rs.next()) {
				people.add(getPerson(rs));
			}
		} catch (SQLException e) {
			throw new MapperException(e);
		}
		return people;
	}

	public static Person find(long id, long version) throws MapperException {
		Person person = PersonMapper.find(id);
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
			ResultSet rs = PersonTDG.find(id);
			if (rs.next()) {
				return getPerson(rs);
			}
			throw new DomainObjectNotFoundException("Cannot find a person with id "
					+ id);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	private static Person getPerson(ResultSet rs) throws SQLException,
			MapperException {
		long buddyId = rs.getLong("buddy");
		IPerson buddy = buddyId > 0 
				? new PersonProxy(buddyId) // PersonMapper.find(buddyId) 
				: null;
		Person result = new Person(rs.getLong("p.id"), rs.getInt("p.version"),
				rs.getString("p.name"), rs.getInt("p.age"), buddy);
		return result;
	}

	public static void insert(Person p) throws MapperException  {
		Long buddyId = p.getBuddy() == null ? null : p.getBuddy().getId();
		try {
			PersonTDG.insert(p.getId(), p.getVersion(), p.getName(), p.getAge(),
					buddyId);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	public static void update(Person p) throws MapperException {
		Long buddyId = p.getBuddy() == null ? null : p.getBuddy().getId();
		int count;
		try {
			count = PersonTDG.update(p.getId(), p.getVersion(), p.getName(),
					p.getAge(), buddyId);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
		if (count == 0) {
			throw new LostUpdateException(
					"It appears that someone else may already have changed this person.");
		}
		p.setVersion(p.getVersion() + 1);
	}

	public static void delete(Person p) throws MapperException {
		int count;
		try {
			count = PersonTDG.delete(p.getId(), p.getVersion());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
		if (count == 0) {
			throw new LostUpdateException(
					"It appears that someone else may already have changed this person.");
		}
		setToNullBuddyOfPeopleWithBuddy(p.getId());
	}

	private static void setToNullBuddyOfPeopleWithBuddy(long id)
			throws MapperException {
		List<Person> people = findAll();
		for (Person person : people) {
			IPerson buddy = person.getBuddy();
			if(buddy == null || buddy.getId() != id)
				continue;
			person.setBuddy(null);
			update(person);
		}
	}
}
