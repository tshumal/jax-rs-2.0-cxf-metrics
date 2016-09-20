package com.example.rs;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.example.model.Person;

import reactor.core.publisher.Flux;

@Path("/people")
@Component
public class PeopleRestService {
	private final Random random = new Random();
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Person> getPeople() {
        return Flux
        	.just(
        		new Person("a@b.com", "John", "Smith"), 
        		new Person("c@b.com", "Bob", "Bobinec")
        	)
        	.delayMillis(random.nextInt(1000))
        	.toStream()
        	.collect(Collectors.toList());
    }
}
