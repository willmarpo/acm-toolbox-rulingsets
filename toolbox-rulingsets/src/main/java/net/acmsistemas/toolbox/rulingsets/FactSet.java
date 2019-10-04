/**
 * AMCSistemas toolbox RulingSets
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.acmsistemas.toolbox.rulingsets;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A Fact Set is a repository for Facts wrapped in containing objects or collections. 
 * Each Fact set is identified by a {@link #name} and the facts are qualified by an object or collection
 * The set are to be used in a {@code ConditionalSet}
 * 
 * @author William Martínez Pomares
 */
public class FactSet {

	/**
	 * The Fact Set Name is used to qualify the contained fact set
	 */
	private String factSetName;
	
	private Map<String, Object> repository;

	public FactSet(String factSetName) {
		super();
		this.factSetName = factSetName;
	}
	
	/**
	 * Registers a fact source. The registered object will be stored as a fact source
	 * and its public getxxx methods are used as facts. The object's simple class name will be used
	 * as a qualifier. 
	 * @param source The fact source object
	 */
	public void addFactSource(Object source) {
		getRepository().put(source.getClass().getSimpleName(), source);
	}

	/**
	 * Registers a fact source. The registered object will be stored as a fact source
	 * and its public getxxx methods are used as facts. The name parameter is used to qualify 
	 * the source.
	 * @param name
	 * @param source
	 */
	public void addFactSource(String name,Object source) {
		getRepository().put(name, source);
	}
	
	/**
	 * Returns an Optional with the Qualified Fact value.
	 * @param source Fact Source (Object, Map)
	 * @param factName The required Fact Name. 
	 * @return Optional with and object value of the requested fact.
	 */
	public Optional<Object> getFact(String source, String factName ) {
		Map<String, Object> repo = getRepository();
		Object objSource = repo.get(source);

		if(objSource == null) return Optional.empty();
		
		//Check if collection
		if (objSource instanceof Map<?,?>) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>)objSource;
			return Optional.ofNullable(map.get(factName));
		}
		else {
			// Assume it is a source object
			try {
				Method method = objSource.getClass().getMethod("get".concat(capitalize(factName)));
				return Optional.ofNullable(method.invoke(objSource));
			}
			catch(Exception e) {
				return Optional.empty();
			}
		}
	}
	private Map<String, Object> getRepository(){
		if (this.repository==null) this.repository = new HashMap<String, Object>();
		return this.repository;
	}
	
	private String capitalize(String factName) {
		if (Character.isUpperCase(factName.codePointAt(0))) {
			return factName;
		}
		else {
			StringBuilder sb = new StringBuilder(factName); 
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString(); 		
		}
	}

	public String getFactSetName() {
		return factSetName;
	}

	public void setFactSetName(String factSetName) {
		this.factSetName = factSetName;
	}

	public void setRepository(Map<String, Object> repository) {
		this.repository = repository;
	}
	
}
