package org.dsrg.soenea.buddyAge.domLogic;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IPerson extends IDomainObject<Long> {

	public abstract void setBuddy(IPerson buddy);

	public abstract IPerson getBuddy();

	public abstract void setName(String name);

	public abstract String getName();

	public abstract void setAge(int age);

	public abstract int getAge();

}
