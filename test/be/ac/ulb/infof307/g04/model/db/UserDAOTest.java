package be.ac.ulb.infof307.g04.model.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import be.ac.ulb.infof307.g04.model.User;
import be.ac.ulb.infof307.g04.model.dao.UserDAO;

public class UserDAOTest {
	private User userTest;
	private UserDAO userDAO;
	
	
	public UserDAOTest() {
		this.userDAO  = new UserDAO();
		this.userTest = new User("newGuy", "tinyPwd", "im_a_king_xxx___king@google.fr");
	}
	
	@Test
	public void checkIfMouradExists() {
		User user = userDAO.findById(2);
		assertEquals("mourad", user.getUsername());
	}

	@Test
	public void testInsertion() {
		userDAO.insert(userTest);
		User u = userDAO.credentialsVerification("newGuy", "tinyPwd");
		assertNotNull(u);
		userDAO.delete(userTest);
	}
	
	@Test
	public void testDelete() {
		userDAO.insert(userTest);
		User u = userDAO.credentialsVerification("newGuy", "tinyPwd");
		assertNotNull(u);
		userDAO.delete(userTest);
		u = userDAO.credentialsVerification("newGuy", "tinyPwd");
		assertNull(u);
	}
}
