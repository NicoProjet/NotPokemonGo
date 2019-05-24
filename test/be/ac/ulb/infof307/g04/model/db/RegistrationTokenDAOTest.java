package be.ac.ulb.infof307.g04.model.db;

import org.junit.Test;

import be.ac.ulb.infof307.g04.model.RegistrationToken;
import be.ac.ulb.infof307.g04.model.dao.RegistrationTokenDAO;

import static org.junit.Assert.*;

import java.util.UUID;


public class RegistrationTokenDAOTest {
	private RegistrationToken rt;
	private RegistrationTokenDAO registrationTokenDAO;
	
	public RegistrationTokenDAOTest() {
		rt = new RegistrationToken(22,  UUID.nameUUIDFromBytes("22".getBytes()).toString().replace("-", ""));
		registrationTokenDAO = new RegistrationTokenDAO();
	}
	
	@Test
	public void testInsertionDelete() {
		assertNull(registrationTokenDAO.findById(22));
		registrationTokenDAO.insert(rt);
		assertNotNull(registrationTokenDAO.findById(22));
		registrationTokenDAO.delete(rt);
		assertNull(registrationTokenDAO.findById(22));
	}
}
