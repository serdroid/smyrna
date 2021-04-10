package info.serdroid.smyrna.service;

import javax.inject.Singleton;

import info.serdroid.smyrna.data.HardCodedPersonStore;
import info.serdroid.smyrna.model.JobHistory;
import info.serdroid.smyrna.model.Person;

@Singleton
public class PersonService {

	public Person getPerson(String personId) {
		return HardCodedPersonStore.PEOPLE[0];
	}

	public JobHistory getJobHistory(String personId) {
		return HardCodedPersonStore.ALL_HISTORIES[0];
	}
}
