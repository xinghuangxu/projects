package org.dsrg.soenea.buddyAge.domLogic.command;



public class DomainCommandFactory {

	public static DomainCommand getInstance(String key) throws Exception {
		try {
			return (DomainCommand) Class.forName(key.toString()).newInstance();
		} catch (ClassNotFoundException e) {
			throw new Exception(
					"There was a problem getting a DomainCommand for key '"
							+ key + "'. " + e.getMessage(), e);
		}
	}

}
