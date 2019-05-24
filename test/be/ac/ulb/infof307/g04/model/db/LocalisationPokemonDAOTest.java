package be.ac.ulb.infof307.g04.model.db;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Calendar;

import org.junit.Test;

import be.ac.ulb.infof307.g04.model.LocalisationPokemon;
import be.ac.ulb.infof307.g04.model.dao.LocalisationPokemonDAO;

public class LocalisationPokemonDAOTest {
	private LocalisationPokemon loc;	
	private LocalisationPokemon loc2;
	private LocalisationPokemonDAO locDAO;
	
	public LocalisationPokemonDAOTest() {
		loc = new LocalisationPokemon(25, 2, 
			Math.random()*50, 
			Math.random()*50, 
			Calendar.getInstance().getTime().toString()
		);
		locDAO = new LocalisationPokemonDAO();
	}	

	@Test
	public void testInsert() throws SQLException {
		int resultLoc = locDAO.insert(loc);
		assertNotNull(locDAO.findByUserId(loc.getUserId())); 
		locDAO.delete(resultLoc);
	}
	
	@Test
	public void mouradHasInsertedSomePokemons() throws SQLException {
		loc = new LocalisationPokemon(25, 2, Math.random()*50, Math.random()*50, Calendar.getInstance().getTime().toString());
		int resultLoc = locDAO.insert(loc);
		assertNotNull(locDAO.findByUserId(2)); // ID of the user mourad is 2.
		locDAO.delete(resultLoc);
	}
	
	@Test
	public void addPokemonWithConflict() throws SQLException {
		String time = Calendar.getInstance().getTime().toString();
		loc  = new LocalisationPokemon(25, 2, 4.3853258, 50.809174, time);
		int resultLoc = locDAO.insert(loc);
		System.out.println(resultLoc);
		
		loc2 = new LocalisationPokemon(25, 2, 4.3853258, 50.809174, time);
		int resultLoc2 =locDAO.insert(loc2);
		System.out.println(resultLoc2);
		assertEquals(resultLoc2, -1); //Conflict with the pokemon, the day and the position => Conflict return -1
		locDAO.delete(resultLoc);
		locDAO.delete(resultLoc2);
	}
	
	@Test
	public void addPokemonWithConflictJustOnPosition() throws SQLException {
		Calendar nextYear = Calendar.getInstance(); //Not the same day
		nextYear.add(Calendar.YEAR, 1);
		
		loc  = new LocalisationPokemon(25, 2, 4.3853258, 50.809174, nextYear.getTime().toString()); 
		int resultLoc = locDAO.insert(loc);
		
		loc2 = new LocalisationPokemon(25, 2, 4.3853258, 50.809174, Calendar.getInstance().getTime().toString());
		int resultLoc2 = locDAO.insert(loc2);
		
		assertNotEquals(resultLoc2, -1); //Conflict on position but not on date => No conflict!
		locDAO.delete(resultLoc);
		locDAO.delete(resultLoc2);
	}
	
	@Test
	public void addPokemonWithConflictJustOnDate() throws SQLException {
		loc  = new LocalisationPokemon(25, 2, 50.3853258, 50.809174, Calendar.getInstance().getTime().toString()); 
		int resultLoc = locDAO.insert(loc);
		
		loc2 = new LocalisationPokemon(25, 2, 4.3853258, 50.809174, Calendar.getInstance().getTime().toString());
		int resultLoc2 = locDAO.insert(loc2);
		
		assertNotEquals(resultLoc2, -1); //Conflict on date but not on position => No conflict!
		locDAO.delete(resultLoc);
		locDAO.delete(resultLoc2);
	}
	
	@Test
	public void addPokemonWithConflictOnDateAndPosition() throws SQLException {
		loc  = new LocalisationPokemon(25, 3, 4.3853258, 50.809174, Calendar.getInstance().getTime().toString()); 
		int resultLoc = locDAO.insert(loc);
		
		loc2 = new LocalisationPokemon(26, 3, 4.3853258, 50.809174, Calendar.getInstance().getTime().toString());
		int resultLoc2 = locDAO.insert(loc2);
		
		assertNotEquals(resultLoc2, -1); //Conflict on date and position but not on pokemon => No conflict!
		locDAO.delete(resultLoc);
		locDAO.delete(resultLoc2);
	}
	
	@Test
	public void deleteWithError() throws SQLException {
		boolean delete = locDAO.delete(-1);
		
		assertFalse(delete);
	}
	
	@Test
	public void deleteWithoutError() throws SQLException {
		loc  = new LocalisationPokemon(25, 3, 4.3853258, 50.809174, Calendar.getInstance().getTime().toString()); 
		int resultLoc = locDAO.insert(loc);
		boolean delete = locDAO.delete(resultLoc);
		System.out.println(delete);
		assertTrue(delete);
	}

}
