package be.ac.ulb.infof307.g04.model.communication;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import be.ac.ulb.infof307.g04.model.client.ServerCommunication;

public class ServerCommunicationTest {
	
	private final String USER_MAIL = "bruxagreb1993@hotmail.com";
	private ServerCommunication serverCommunication;

	@Before
	public void setUp() throws Exception {
		serverCommunication = new ServerCommunication();
	}

	@Test
	public void testSendMail() {
		boolean mailSent = serverCommunication.sendMail(USER_MAIL, "Just for test : ", 0, "123"); //No mail sent?
		assertEquals(mailSent, true);
	}
}
