package be.ac.ulb.infof307.g04.model.db;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import be.ac.ulb.infof307.g04.model.Pokemon;
import be.ac.ulb.infof307.g04.model.dao.PokemonDAO;

public class PokemonDAOTest {
		
	@Test
	public void testPluquetExists() {
		Pokemon pluquet = new PokemonDAO().findByName("Pluquet");
		assertEquals("Pluquet", pluquet.getName());
	}

	@Test
	public void testPikachuExistsAndTypeElektrik() {
		ArrayList<Pokemon> pokemonsFound = (ArrayList<Pokemon>) new PokemonDAO().findByType("Elektrik");
		Pokemon pikachu = new PokemonDAO().findByName("Pikachu");
		assertEquals(true, pokemonsFound.contains(pikachu));
	}
}
