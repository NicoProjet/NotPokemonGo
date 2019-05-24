package be.ac.ulb.infof307.g04.model.communication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.ac.ulb.infof307.g04.model.Token;
import be.ac.ulb.infof307.g04.model.TokenManager;

public class TokenManagerTest {
	
	private TokenManager tokenManager;
	private Token token;
	private String access;
	private String refresh;
	
	private final String USER_NAME = "name";

	@Before
	public void setUp() throws Exception {
		tokenManager = new TokenManager();
		token = null;
		refresh = null;
		access = null;
	}

	@Test
	public void testAddToken() {
		token = tokenManager.addToken(USER_NAME);
		assertNotNull(token);
	}

	@Test
	public void testGenerateAccessToken() {
		String tokenStr = tokenManager.generateAccessToken(USER_NAME);
		assertNotNull(tokenStr);
		assertNotEquals(tokenStr, "");
	}

	@Test
	public void testGenerateRefreshToken() {
		String tokenStr = tokenManager.generateRefreshToken(USER_NAME);
		assertNotNull(tokenStr);
		assertNotEquals(tokenStr, "");
	}

	@Test
	public void testUserExists() {
		tokenManager.addToken(USER_NAME);
		boolean exist = tokenManager.userExists(USER_NAME);
		assertTrue(exist);
	}

	@Test
	public void testHasExpired() {
		tokenManager.addToken(USER_NAME);
		boolean expired = tokenManager.hasExpired(USER_NAME);
		assertFalse(expired);
	}

	@Test
	public void testCheckToken() {
		token = tokenManager.addToken(USER_NAME);
		access = token.getAccess();
		boolean check = tokenManager.checkToken(USER_NAME, access);
		assertTrue(check);
	}

	@Test
	public void testDeleteToken() {
		tokenManager.addToken(USER_NAME);
		boolean delete = tokenManager.deleteToken(USER_NAME);
		assertTrue(delete);
	}
}
