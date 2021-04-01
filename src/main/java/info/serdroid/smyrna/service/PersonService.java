package info.serdroid.smyrna.service;

import info.serdroid.smyrna.model.Person;

public class PersonService {

	public Person getPerson(String personId) {
		Person expected = new Person();
		expected.setId(personId);
		expected.setName("ali");
		expected.setSurname("al");
		expected.setEmail("ali.al@example.com");
		return expected;
	}

}
