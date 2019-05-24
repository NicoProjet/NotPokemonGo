package be.ac.ulb.infof307.g04.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import be.ac.ulb.infof307.g04.model.ResponseCode;
import be.ac.ulb.infof307.g04.model.client.UserCommunication;
import be.ac.ulb.infof307.g04.view.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * The controller which manages the sign up view.
 */
public class SignUpController implements Initializable {
	@FXML private Label titleSignUp;
	@FXML private Label labelCaptcha;

	@FXML private TextField usernameLabel;
	@FXML private TextField passwordLabel;
	@FXML private TextField confirmPasswordLabel;
	@FXML private TextField emailLabel;
	@FXML private TextField resultCaptcha;
	@FXML private HomeController parent;
	// A regex that matches the pattern of a simple email address.
	public static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	// The two operands of the captcha in the sign up view.
	// They are generated randomly in the initialize() method.
	private int operand1;
	private int operand2;

	/**
	 * This method is called by the FXMLLoader when initialization is complete.
	 * @param fxmlFileLocation URL.
	 * @param resources
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Simulate text-shadow property
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		titleSignUp.setEffect(ds);
		// Generate a captcha
		operand1 = (int) (Math.random() * 10);
		operand2 = (int) (Math.random() * 10);
		labelCaptcha.setText("Question : Combien font " + operand1 + " + " + operand2 + " ? ");
	}

	/**
	 * Set the instance of the parent controller on the child controller
	 * @param parent Controller instance of the parent pane
	 */
	public void setParentController(HomeController homeController) {
		this.parent = homeController;
	}

	/**
	 * The event handler of the sign up button.
	 * this method signs up the client in the application.
	 * @param event The event.
	 * @throws IOException If a communication error occurred.
	 */
	@FXML
	private void signUp(ActionEvent event) throws IOException {
		try {
			if(isFormValid()) {
				UserCommunication request = new UserCommunication();
				int responseCode = request.signUp(usernameLabel.getText(), passwordLabel.getText(), emailLabel.getText());
				Message.show("", "response : " + responseCode, AlertType.ERROR);
				if(responseCode == ResponseCode.OK.getValue()) {
					// login success
					Message.show("Inscription effectu�e avec succ�s !",
								  "Un lien de confirmation a �t� envoy� � l'adresse que vous "
								+ "avez sp�cifi� afin d'activer votre compte.", AlertType.INFORMATION);
					parent.goToLogin();
				} else { 
					// login failed
					Message.show("response", "Inscription foir�e", AlertType.INFORMATION);
				}
				// After one of the previous message, we reset the informations 
				// and we go to the login page.
				usernameLabel.clear();
				passwordLabel.clear();
				confirmPasswordLabel.clear();
				emailLabel.clear();
				resultCaptcha.clear();

			} else {
				Message.show("Erreur au niveau du formulaire",
						"Veuillez remplir le formulaire convenablement.", AlertType.WARNING);
			}
		} catch(NumberFormatException ex) {
			Message.show("Erreur au niveau du formulaire",
					"Veuillez remplir le formulaire convenablement.", AlertType.WARNING);
		}
	}
	

	/**
	 * Checks if the sign up form is valid of not.
	 * It is valid if all fields are filled and the answer to the captcha is correct.
	 * @return True if it's valid, false otherwise.
	 */
	private boolean isFormValid() {
		return (operand1 + operand2) == Integer.parseInt(resultCaptcha.getText())
				&& !usernameLabel.getText().equals("")
				&& !passwordLabel.getText().equals("")
				&& !confirmPasswordLabel.getText().equals("")
				&& passwordLabel.getText().equals(confirmPasswordLabel.getText())
				&& EMAIL_REGEX.matcher(emailLabel.getText()).find();
	}
	
	/**
	 * Shows the term of use.
	 * I am aware that it's hardcoded but... well...
	 * (In a professional use case, I would surely have placed that huge text in text file or somewhere else).
	 * @param e The event.
	 */
	@FXML
	private void showConditions(MouseEvent e) {
		Message.showScrollableMessage("Conditions d'utilisations",
				"Qu'est-ce que le Lorem Ipsum ?\n\n"
				+ "Le Lorem Ipsum est simplement du faux texte employ� "
				+ "dans la composition et la mise en page avant impression. "
				+ "Le Lorem Ipsum est le faux texte standard de l'imprimerie "
				+ "depuis les ann�es 1500, quand un peintre anonyme assembla "
				+ "ensemble des morceaux de texte pour r�aliser un livre sp�cimen "
				+ "de polices de texte. Il n'a pas fait que survivre cinq si�cles, "
				+ "mais s'est aussi adapt� � la bureautique informatique, sans que "
				+ "son contenu n'en soit modifi�. Il a �t� popularis� dans les ann�es "
				+ "1960 gr�ce � la vente de feuilles Letraset contenant des passages du "
				+ "Lorem Ipsum, et, plus r�cemment, par son inclusion dans des applications "
				+ "de mise en page de texte, comme Aldus PageMaker."

				+ "Pourquoi l'utiliser ?\n\n"
				+ "On sait depuis longtemps que travailler avec du texte lisible "
				+ "et contenant du sens est source de distractions, et emp�che de "
				+ "se concentrer sur la mise en page elle-m�me. L'avantage du Lorem "
				+ "Ipsum sur un texte g�n�rique comme 'Du texte. Du texte. Du texte.' "
				+ "est qu'il poss�de une distribution de lettres plus ou moins normale,"
				+ " et en tout cas comparable avec celle du fran�ais standard. "
				+ "De nombreuses suites logicielles de mise en page ou �diteurs de sites "
				+ "Web ont fait du Lorem Ipsum leur faux texte par d�faut, et une "
				+ "recherche pour 'Lorem Ipsum' vous conduira vers de nombreux sites qui "
				+ "n'en sont encore qu'� leur phase de construction. Plusieurs versions "
				+ "sont apparues avec le temps, parfois par accident, souvent intentionnellement "
				+ "(histoire d'y rajouter de petits clins d'oeil, voire des phrases embarassantes)."

				+ "D'o� vient-il ?\n\n"
				+ "Contrairement � une opinion r�pandue, le Lorem Ipsum n'est pas simplement "
				+ "du texte al�atoire. Il trouve ses racines dans une oeuvre de la litt�rature "
				+ "latine classique datant de 45 av. J.-C., le rendant vieux de 2000 ans. "
				+ "Un professeur du Hampden-Sydney College, en Virginie, s'est int�ress� � un des "
				+ "mots latins les plus obscurs, consectetur, extrait d'un passage du Lorem Ipsum, "
				+ "et en �tudiant tous les usages de ce mot dans la litt�rature classique, d�couvrit "
				+ "la source incontestable du Lorem Ipsum. Il provient en fait des sections 1.10.32 "
				+ "et 1.10.33 du \"De Finibus Bonorum et Malorum\" (Des Supr�mes Biens et des Supr�mes Maux) "
				+ "de Cic�ron. Cet ouvrage, tr�s populaire pendant la Renaissance, est un trait� "
				+ "sur la th�orie de l'�thique. Les premi�res lignes du Lorem Ipsum, \"Lorem ipsum "
				+ "dolor sit amet...\", proviennent de la section 1.10.32."
				+ "L'extrait standard de Lorem Ipsum utilis� depuis le XVI� si�cle "
				+ "est reproduit ci-dessous pour les curieux. Les sections 1.10.32 et 1.10.33 du "
				+ "\"De Finibus Bonorum et Malorum\" de Cic�ron sont aussi reproduites dans leur "
				+ "version originale, accompagn�e de la traduction anglaise de H. Rackham (1914)."

				+ "O� puis-je m'en procurer ?\n\n"
				+ "Plusieurs variations de Lorem Ipsum peuvent �tre trouv�es ici ou l�, mais "
				+ "la majeure partie d'entre elles a �t� alt�r�e par l'addition d'humour ou de "
				+ "mots al�atoires qui ne ressemblent pas une seconde � du texte standard. Si "
				+ "vous voulez utiliser un passage du Lorem Ipsum, vous devez �tre s�r qu'il n'y "
				+ "a rien d'embarrassant cach� dans le texte. Tous les g�n�rateurs de Lorem Ipsum "
				+ "sur Internet tendent � reproduire le m�me extrait sans fin, ce qui fait de "
				+ "lipsum.com le seul vrai g�n�rateur de Lorem Ipsum. Iil utilise un dictionnaire"
				+ " de plus de 200 mots latins, en combinaison de plusieurs structures de phrases, "
				+ "pour g�n�rer un Lorem Ipsum irr�prochable. Le Lorem Ipsum ainsi obtenu ne "
				+ "contient aucune r�p�tition, ni ne contient des mots farfelus, "
				+ "ou des touches d'humour.",
				AlertType.INFORMATION);
	}
}
