package be.ac.ulb.infof307.g04.model.communication;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import be.ac.ulb.infof307.g04.model.server.MailHandler;

public class MailHandlerTest {
	
	private final String USER_MAIL = "bruxagreb1993@hotmail.com";
	private MailHandler mail;

	@Before
	public void setUp() throws Exception {
		mail = new MailHandler();
	}

	@Test
	public void testSendMail() {
		boolean mailSent = mail.sendMail(USER_MAIL, "Just for test : ", 0, "123");
		assertEquals(mailSent, true);
	}
}
