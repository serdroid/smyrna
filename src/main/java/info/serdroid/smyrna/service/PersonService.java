package info.serdroid.smyrna.service;

import javax.inject.Singleton;

import info.serdroid.smyrna.data.HardCodedPersonStore;
import info.serdroid.smyrna.model.Person;

@Singleton
public class PersonService {

	public Person getPerson(String personId) {
		return HardCodedPersonStore.PEOPLE[0];
	}

}
