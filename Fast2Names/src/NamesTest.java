import static org.junit.Assert.*;

import java.util.*;
import java.util.Map.*;

import org.junit.Test;

public class NamesTest {

	@Test
	public void testNoFilter() {
		
		// Get all names
		Names allNames = new Names("", "");
		assertEquals(111, allNames.getFirstNamesCount());
		assertEquals(112, allNames.getSurNamesCount());
	}
	
	@Test
	public void testFilter() {
		
		/*
		 * Get all names where first name or surname consists of "Eri":
		 * Erika Dahlberg
		 * Emelie Eriksson
		 * Erik Vikström
		 * Erik Cedersved
		 * Carin Eriksson
		 * Erika Öhman
		 * 
		 * Unika förnamn 4 st: Erika, Emelie, Erik och Carin
		 * Unika efternamn 5 st: Dahlberg, Eriksson, Vikström, Cedersved och Öhman
		 * 
		 */
		Names filterNames = new Names("Eri", "");
		assertEquals(4, filterNames.getFirstNamesCount());
		assertEquals(5, filterNames.getSurNamesCount());
	}
	
	@Test
	public void testEven() {
		/*
		 * Get all names with the count 2, 4, 6 etc...
		 */
		Names evenNames = new Names("", "Even");
		assertEquals(15, evenNames.getFirstNamesCount());
		assertEquals(12, evenNames.getSurNamesCount());
	}
	
	public void testOdd() {
		/*
		 * Get all names with the count 1, 3, 5 etc...
		 */
		Names oddNames = new Names("", "Odd");
		assertEquals(96, oddNames.getFirstNamesCount());
		assertEquals(100, oddNames.getSurNamesCount());
	}

	@Test
	public void testFilterEven() {
		/*
		 * Get all names with the count 2, 4, 6 etc...
		 * First names: Erik (2), Erika (2)
		 * Surnames: Eriksson (2)
		 */
		Names filterEvenNames = new Names("Eri", "Even");
		assertEquals(2, filterEvenNames.getFirstNamesCount());
		assertEquals(1, filterEvenNames.getSurNamesCount());
	}
	
	@Test
	public void testGetListOfTopFirstNames()
	{
		// Get all names
		// Make sure Kjell is most common and Johanna least common in the result
		Names allNames = new Names("", "");
		List<Entry<String, Integer>>top5FirstNames = allNames.getListOfTopFirstNames(5);
		assertEquals(5, top5FirstNames.size());
		assertEquals("Kjell", top5FirstNames.get(0).getKey());
		assertEquals("Johanna", top5FirstNames.get(4).getKey());
	}

	@Test
	public void testGetListOfTopSurNames()
	{
		// Get all names
		// Make sure Andersson is most common and Jonsson least common in the result
		Names allNames = new Names("", "");
		List<Entry<String, Integer>>top5SurNames = allNames.getListOfTopSurNames(5);
		assertEquals(5, top5SurNames.size());
		assertEquals("Andersson", top5SurNames.get(0).getKey());
		assertEquals("Jonsson", top5SurNames.get(4).getKey());
	}

	@Test
	public void testGetListOfFirstNamesSorted()
	{
		// Get all names
		// Make sure Agneta is the top first name and William is the bottom first name
		// Get the names descending and make sure Agneta is the bottom first name and William is the top first name
		Names allNames = new Names("", "");
		List<String> sortedNames = allNames.getListOfFirstNamesSorted(true);
		assertEquals("Agneta", sortedNames.get(0));
		assertEquals("William", sortedNames.get(110));
		
		sortedNames = allNames.getListOfFirstNamesSorted(false);
		assertEquals("William", sortedNames.get(0));
		assertEquals("Agneta", sortedNames.get(110));
	}

	@Test
	public void testGetListOfSurNamesSorted()
	{
		// Get all names
		// Make sure Abrahamsson is the top surname and Öhman is the bottom surname
		// Get the names descending and make sure Abrahamsson is the bottom surname and Öhman is the top surname
		Names allNames = new Names("", "");
		List<String> sortedNames = allNames.getListOfSurNamesSorted(true);
		assertEquals("Abrahamsson", sortedNames.get(0));
		assertEquals("Öhman", sortedNames.get(111));
		
		sortedNames = allNames.getListOfSurNamesSorted(false);
		assertEquals("Öhman", sortedNames.get(0));
		assertEquals("Abrahamsson", sortedNames.get(111));
	}

}
