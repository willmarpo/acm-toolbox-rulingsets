package net.acmsistemas.toolbox.rulingsets.testmodels;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic Person object used for testing facts
 * @author William Martínez Pomares
 */
public class PersonFactSource {

	private String name;
	private int age;
	private float salary;
	
	public PersonFactSource(String name, int age, float salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public Map<String,Object> getAsMap(){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("name", this.name);
		map.put("age", this.age);
		map.put("salary", this.salary);
		return map;
	}
}
