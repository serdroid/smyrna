package info.serdroid.smyrna.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import info.serdroid.smyrna.model.JobHistory;
import info.serdroid.smyrna.model.Person;
import info.serdroid.smyrna.service.PersonService;

@ApplicationScoped
@Path("person")
public class PersonEndpoint {

	@Inject
	private PersonService service;
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("id") String personId) {
		Person person = service.getPerson(personId);
		return Response.ok(person).build();
	}

	@GET
	@Path("history/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJobHistory(@PathParam("id") String personId) {
		JobHistory jobHistory = service.getJobHistory(personId);
		return Response.ok(jobHistory).build();
	}

}
