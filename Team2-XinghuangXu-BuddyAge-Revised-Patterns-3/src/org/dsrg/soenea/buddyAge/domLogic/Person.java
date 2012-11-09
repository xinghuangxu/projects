package org.dsrg.soenea.buddyAge.domLogic;

import org.dsrg.soenea.domain.DomainObject;


public class Person extends DomainObject<Long> implements IPerson {
	private String name;
	private int age;
	private IPerson buddy;
	
	public Person(long id, long version, String name, int age, IPerson buddy) {
		super(id, version);
		this.name = name;
		this.age = age;
		this.setBuddy(buddy);
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	public IPerson getBuddy() {
		return buddy;
	}

	
	public void setBuddy(IPerson buddy) {
		this.buddy = buddy;
	}

	
}
