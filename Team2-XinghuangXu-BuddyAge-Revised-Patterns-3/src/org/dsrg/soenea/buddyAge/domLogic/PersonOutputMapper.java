package org.dsrg.soenea.buddyAge.domLogic;


import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.buddyAge.techSvc.PersonTDG;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

public class PersonOutputMapper extends GenericOutputMapper<Long, Person> {



	@Override
	public void update(Person p) throws MapperException {
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

	@Override
	public void delete(Person p) throws MapperException {
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
	}

	@Override
	public  void insert(Person p) throws MapperException {
		Long buddyId = p.getBuddy() == null ? null : p.getBuddy().getId();
		try {
			PersonTDG.insert(p.getId(), p.getVersion(), p.getName(), p.getAge(),
					buddyId);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
		
	}
	
	@Override
	public  void cascadeDelete(Person p)
			throws MapperException {
		List<Person> people = PersonInputMapper.findAll();
		for (Person person : people) {
			IPerson buddy = person.getBuddy();
			if(buddy == null || buddy.getId() != p.getId())
				continue;
			delete(person);
		}
	}
	

}
