package net.acmsistemas.toolbox.rulingsets;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import net.acmsistemas.toolbox.rulingsets.testmodels.PersonFactSource;

public class FactSetTest {
	
	static private final String NAME = "A Person"; 
	static private final int AGE = 35; 
	static private final float SALARY = (float) 100.5; 

	private PersonFactSource person;
	
	private PersonFactSource getPerson() {
		if (this.person == null) {
			this.person = new PersonFactSource(NAME,AGE,SALARY);
		}
		return this.person;
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void factStringTest() {
		PersonFactSource personSource= this.getPerson();
		FactSet factSet = new FactSet("factTest");
		factSet.addFactSource(personSource);
		Optional fact  = factSet.getFact("PersonFactSource", "name");
		assertTrue(fact.isPresent());
		assertEquals(NAME,fact.get().toString());
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void factNativeTest() {
		PersonFactSource person= getPerson();
		FactSet factSet = new FactSet("factTest");
		factSet.addFactSource(person);
		Optional fact  = factSet.getFact("PersonFactSource", "age");
		assertTrue(fact.isPresent());
		Integer age = (Integer)fact.get();
		assertEquals(AGE,age.intValue());
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void factMapTest() {
		PersonFactSource person= getPerson();
		FactSet factSet = new FactSet("factTest");
		factSet.addFactSource("TheMap",person.getAsMap());
		Optional fact  = factSet.getFact("TheMap", "age");
		assertTrue(fact.isPresent());
		Integer age = (Integer)fact.get();
		assertEquals(AGE,age.intValue());
	} 

}
