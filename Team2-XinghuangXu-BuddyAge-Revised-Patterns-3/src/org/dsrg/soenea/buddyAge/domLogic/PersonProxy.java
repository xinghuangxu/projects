package org.dsrg.soenea.buddyAge.domLogic;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;

public class PersonProxy extends DomainObjectProxy<Long, Person>
	implements IPerson 
{
	
	public int getAge() {
		return getInnerObject().getAge();
	}

	
	public void setAge(int age) {
		getInnerObject().setAge(age);
	}

	
	public String getName() {
		return getInnerObject().getName();
	}

	
	public void setName(String name) {
		getInnerObject().setName(name);
	}

	
	public IPerson getBuddy() {
		return getInnerObject().getBuddy();
	}

	
	public void setBuddy(IPerson buddy) {
		getInnerObject().setBuddy(buddy);
	}

	protected PersonProxy(Long id) {
		super(id);
	}

	
	protected Person getFromMapper(Long id) throws MapperException {
		return PersonInputMapper.find(id);
	}

}
