package be.ac.ulb.infof307.g04.model.communication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import be.ac.ulb.infof307.g04.model.LocalisationPokemon;
import be.ac.ulb.infof307.g04.model.ResponseCode;
import be.ac.ulb.infof307.g04.model.client.PokemonCommunication;
import be.ac.ulb.infof307.g04.model.client.Session;
import be.ac.ulb.infof307.g04.model.client.UserCommunication;
import be.ac.ulb.infof307.g04.model.server.Server;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PokemonCommunicationTest {
	PokemonCommunication pokemon;
	private UserCommunication request;
	
	private final int POKEMON_ID = 10;
	private final int USER_ID    = 33;
	private final int LATITUDE   = 100;
	private final int LONGITUDE  = 150;
	private final String DATE  = "20/03/2015";
	private static int locId = 0;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.main(null);	
		
	}
	
	@Before
	public void setUp() throws Exception {
		pokemon = new PokemonCommunication();
		request = new UserCommunication();
	}

	@Test
	public void test1InsertPokemonTest() {
		LocalisationPokemon localisationPokemon = new LocalisationPokemon(POKEMON_ID, USER_ID , LATITUDE , LONGITUDE, DATE);
		int response = Integer.parseInt(pokemon.insert("FAKE","USER",localisationPokemon));
		locId = response;
		assertEquals(-2,response);
	}
	@Test
	public void test2ConnectAndPlace(){
		Session client = request.login("rr","rr");
		assertNotNull(client);
		LocalisationPokemon localisationPokemon = new LocalisationPokemon(POKEMON_ID, USER_ID , LATITUDE , LONGITUDE, DATE);
		int response = Integer.parseInt(pokemon.insert(client.getTokenSession(),"rr",localisationPokemon));
		locId = response;
		assertTrue(response > 0);
		request.logout(client.getTokenSession(),"rr","rr");
	}
	
	@Test
	public void test3UpdateMarker(){
		Session client = request.login("rr","rr");
		assertNotNull(client);
		LocalisationPokemon localisationPokemon = new LocalisationPokemon(POKEMON_ID ,USER_ID , LATITUDE , 
				LONGITUDE, DATE,30,60,99);
		int responseStatus = pokemon.update(client.getTokenSession(),"rr",localisationPokemon);
		assertEquals(ResponseCode.OK.getValue(),responseStatus);
		request.logout(client.getTokenSession(),"rr","rr");
	}
	@Test
	public void test4Delete(){
		Session client = request.login("rr","rr");
		assertNotNull(client);
		int response = pokemon.delete(client.getTokenSession(),"rr",locId);
		assertEquals(ResponseCode.OK.getValue(),response);
		request.logout(client.getTokenSession(),"rr","rr");
	}
	@Test
	public void test5ConnectDisconnectAndPlace(){
		Session client = request.login("rr","rr");
		assertNotNull(client);
		LocalisationPokemon localisationPokemon = new LocalisationPokemon(POKEMON_ID, USER_ID , LATITUDE , LONGITUDE, DATE);
		request.logout(client.getTokenSession(),"rr","rr");
		int response = Integer.parseInt(pokemon.insert(client.getTokenSession(),"rr",localisationPokemon));
		assertEquals(-2 ,response);
	}
	
}
