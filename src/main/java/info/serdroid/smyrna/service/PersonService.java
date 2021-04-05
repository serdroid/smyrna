package info.serdroid.smyrna.service;

import info.serdroid.smyrna.data.HardCodedPersonStore;
import info.serdroid.smyrna.model.Person;

public class PersonService {

	public Person getPerson(String personId) {
		return HardCodedPersonStore.PEOPLE[0];
	}

}
