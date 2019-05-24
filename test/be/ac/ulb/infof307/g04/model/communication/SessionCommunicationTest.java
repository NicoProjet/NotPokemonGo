package be.ac.ulb.infof307.g04.model.communication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import be.ac.ulb.infof307.g04.model.ResponseCode;
import be.ac.ulb.infof307.g04.model.client.Session;
import be.ac.ulb.infof307.g04.model.client.UserCommunication;
import be.ac.ulb.infof307.g04.model.server.Server;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionCommunicationTest {
	
	private final String NEW_USER_NAME = "name";
	private final String NEW_USER_PASS = "pass";
	private final String NEW_USER_MAIL = "bruxagreb1993@hotmail.com";
	
	private final String EXISTING_USER_NAME = "issam";
	private final String EXISTING_USER_PASS = "issam1234";
	private final String EXISTING_USER_MAIL = "ihajji39579@gmail.com";

	private UserCommunication request;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.main(null);	
	}

	@Before
	public void setUp() throws Exception {
		request = new UserCommunication();
	}

	@Test
	public void test1SignUp() {
		int responseCode = request.signUp(NEW_USER_NAME, NEW_USER_PASS, NEW_USER_MAIL);
		assertEquals(responseCode, ResponseCode.OK.getValue());
		request.delete(NEW_USER_NAME);
	}

	@Test
	public void test2Login() {
		Session client = request.login(EXISTING_USER_NAME, EXISTING_USER_PASS);
		assertNotNull(client);
		request.logout(client.getTokenSession(),EXISTING_USER_NAME,EXISTING_USER_NAME);
	}

	@Test
	public void test3Update() {
		Session client = request.login(EXISTING_USER_NAME, EXISTING_USER_PASS);
		assertNotNull(client);
		int responseCode = request.update(EXISTING_USER_NAME, EXISTING_USER_PASS, EXISTING_USER_MAIL);
		assertEquals(responseCode, ResponseCode.OK.getValue());
		request.logout(client.getTokenSession(),EXISTING_USER_NAME,EXISTING_USER_NAME);
	}

	@Test
	public void test4Logout() {
		Session client = request.login(EXISTING_USER_NAME, EXISTING_USER_PASS);
		assertNotNull(client);
		int responseCode = request.logout(client.getTokenSession(),EXISTING_USER_NAME,EXISTING_USER_NAME);
		assertEquals(responseCode, ResponseCode.OK.getValue());
	}

	@Test
	public void test5Delete() {
		request.signUp(NEW_USER_NAME, NEW_USER_PASS, NEW_USER_MAIL);
		int responseCode = request.delete(NEW_USER_NAME);
		assertEquals(responseCode, ResponseCode.OK.getValue());
	}
}