/**
 * 
 */
package net.acmsistemas.toolbox.rulingsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

/**
 * @author wmartinez
 *
 */
public class CatalogSetTest {

	@Test
    public void setAndGetAliasTest() {
		CatalogSet catalog = new CatalogSet();
		catalog.addAlias("TheFirstClass", "TheFirstField", "ValueOne", 1);
		catalog.addAlias("TheFirstClass", "TheFirstField", "ValueTwo", 2);
		catalog.addAlias("TheFirstClass", "TheFirstField", "ValueThree", 3);
		Integer value = (Integer)catalog.getAliasValue("TheFirstClass", "TheFirstField", "ValueTwo");
		assertEquals(2, value.intValue());
	}
	
	@Test
    public void getAliasSetTest() {
		CatalogSet catalog = new CatalogSet();
		catalog.addAlias("TheFirstClass", "TheFirstField", "ValueOne", 1);
		catalog.addAlias("TheFirstClass", "TheFirstField", "ValueTwo", 2);
		catalog.addAlias("TheFirstClass", "TheFirstField", "ValueThree", 3);
		Set<String> aliases = catalog.getAliases("TheFirstClass", "TheFirstField");
		assertNotNull(aliases);
		assertEquals(3, aliases.size());
	}
}
