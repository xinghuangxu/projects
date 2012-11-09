package org.dsrg.soenea.buddyAge.domLogic;

import org.dsrg.soenea.buddyAge.techSvc.PersonTDG;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;

public class PersonFactory {
	
	
//	public static Person createNew(String name, int age, IPerson buddy){
//		return createNew(PersonTDG.maxId(),1,name,age,buddy);
//	}
	
	
	public static Person createNew(long id, long version, String name, int age, IPerson buddy) throws MissingMappingException, MapperException{
		Person p=new Person(id,version,name,age,buddy);
		UoW.getCurrent().registerNew(p);
		return p;
	}
	
	
	public static Person createClean(long id, long version, String name, int age, IPerson buddy){
		Person p=new Person(id,version,name,age,buddy);
		UoW.getCurrent().registerClean(p);
		return p;
	}

}
