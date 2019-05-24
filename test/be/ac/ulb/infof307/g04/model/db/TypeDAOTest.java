package be.ac.ulb.infof307.g04.model.db;

import static org.junit.Assert.*;

import org.junit.Test;

import be.ac.ulb.infof307.g04.model.dao.PokemonDAO;
import be.ac.ulb.infof307.g04.model.dao.TypeDAO;

public class TypeDAOTest {

	@Test
	public void typeFireExists() {
		String type = new TypeDAO().findById(7).getDescription();
		assertEquals("Feu", type);
	}

	@Test
	public void aPokemonWithNoSecondTypeHasZeroForThatTypeValue() {
		// Pikachu (id 25) has no second type, so his second type should be 0.
		int type2 = new PokemonDAO().findById(25).getType2();
		assertEquals(0, type2);
	}

	@Test
	public void typeWaterExists() {
		assertEquals(4, new TypeDAO().findByName("Eau").getId());
	}
}
