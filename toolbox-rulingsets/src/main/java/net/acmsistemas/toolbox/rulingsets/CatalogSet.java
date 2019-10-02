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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A Catalog Set is a repository for values and their aliases. When defining a {@code ConditionalSet}, 
 * the value constants being evaluated need to be user readable. This set allows the creation of those aliases
 * to be used in the {@code RuleEngine}
 * 
 * @author William Martínez Pomares
 */
public class CatalogSet {

	/**
	 * The catalogs structure is a multilevel map holding the aliases for 
	 * Class.Attribute sets
	 */
	private Map<String, Map<String, Map<String, Object>>> catalogs;
	
	public void addAlias(String className, String attribute, String alias, Object value) {
		if (this.catalogs == null) this.catalogs = new HashMap<String, Map<String, Map<String, Object>>>();

		Map<String, Map<String, Object>> objectSet = this.catalogs.get(className);
		if (objectSet == null) objectSet = new HashMap<String, Map<String, Object>>();

		Map<String, Object> attributeSet = objectSet.get(attribute);
		if (attributeSet == null) attributeSet = new HashMap<String, Object>();
		
		attributeSet.put(alias, value);
		objectSet.put(attribute, attributeSet);
		this.catalogs.put(className, objectSet);
		
	}

	public Map<String, Object> getAttributeSet(String className, String attribute){
		
		if (this.catalogs == null) return Collections.emptyMap();
		
		Map<String, Map<String, Object>> objectSet = this.catalogs.get(className);
		if (objectSet == null) return Collections.emptyMap();
		
		Map<String, Object> attributeSet = objectSet.get(attribute);
		if (attributeSet == null) {
			return Collections.emptyMap();
		}
		else {
			return attributeSet;
		}
	}
	
	public Set<String> getAliases(String className, String attribute){
		return this.getAttributeSet(className, attribute).keySet();
	}
	
	public Object getAliasValue(String className, String attribute, String alias){
		return this.getAttributeSet(className, attribute).get(alias);
	}
}
