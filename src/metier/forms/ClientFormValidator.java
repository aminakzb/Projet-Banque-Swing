package metier.forms;

import java.util.HashMap;
import java.util.Map;

import dao.daoFiles.ClientDao;
import metier.Verifiable;

public class ClientFormValidator {

	private static final String FIELD_LOGIN = "Login", FIELD_PASS = "Mot de passe", FIELD_EMAIL = "Email",
			FIELD_CIN = "Cin", FIELD_TEL = "Tel", FIELD_Nom = "Nom", FIELD_Prenom = "Prenom";
	private Map<String, String> errors = new HashMap<>();
	private String erreur;

	public ClientFormValidator() {
	}

	public Map<String, String> Errors() {
		return errors;
	}

	public void setError(String fieldName, String errorMsg) {
		Errors().put(fieldName, errorMsg);
	}

	private void verifierNom(String nom) throws FormException {
		if (nom.equals("Nom ") || nom == null || nom.trim().length() == 0) {
			throw new FormException("*Nom  obligatoire !!!");
		}
	}

	private void verifierPrenom(String prenom) throws FormException {
		if (prenom.equals("Prenom ") || prenom == null || prenom.trim().length() == 0) {
			throw new FormException("*Prenom  obligatoire !!!");
		}
	}

	private void verifierLogin(String login) throws FormException {
		if (!login.equals("Login ") && login != null && login.trim().length() != 0) {
			if (login.trim().length() < 4)
				throw new FormException("Login doit avoir moins de 4 chars !!!");
		} else {
			throw new FormException("*Login obligatoire !!!");
		}
	}

	private void verifierPass(String pass) throws FormException {
		if (!pass.equals("Mot de passe ") && pass != null && pass.trim().length() != 0) {
			if (pass.trim().length() < 4)
				throw new FormException("Mot de passe doit avoir plus que 4 chars !!!");
		} else {
			throw new FormException("*Mot de passe obligatoire !!!");
		}
	}

	private void verifierCin(String cin) throws FormException {
		if (!cin.equals("Cin ") && cin != null && cin.trim().length() != 0) {
			if (cin.trim().length() < 4)
				throw new FormException("Cin doit avoir plus que 4 chars !!!");

			if (new ClientDao().findByCin(cin) != null)
				throw new FormException("Client ayant meme Cin existe déjà !!!");

		} else {
			throw new FormException("*Cin obligatoire !!!");
		}
	}

	private void verifierEmail(String email) throws FormException {
		if (!email.equals("Email ") && email != null && email.trim().length() != 0) {
			if (!email.contains("@"))
				throw new FormException("Adresse saisi ne verifie pas le format email \"@\" !!!");
			else if (email.trim().length() < 6)
				throw new FormException("Adresse email invalide !!!");
		} else {
			throw new FormException("*Email obligatoire !!!");
		}
	}

	private void verifierTel(String tel) throws FormException {
		if (!tel.equals("Tel ") && tel != null && tel.trim().length() != 0) {
			if (tel.trim().length() < 4)
				throw new FormException("Tel doit avoir plus que 4 chars !!!");
			if (!Verifiable.isNumeric(tel))
				throw new FormException("Tel ne doit pas contenir des chars !!!");
		} else {
			throw new FormException("*Tel obligatoire !!!");
		}
	}

	private void verifierLoginPassUnique(String pass, String login) throws FormException {
		if (new ClientDao().findByLoginAndPass(pass, login) != null)
			throw new FormException("Informations de connexion existent déjà !!!");
	}

	public void validerForm(String email, String prenom, String nom, String tel, String pass, String login,
			String cin) {
		validerLoginPassUnique(pass, login);
		validerNom(nom);
		validerPrenom(prenom);
		validerEmail(email);
		validerLogin(login);
		validerMotDePasse(pass);
		validerCin(cin);
		validerTel(tel);
	}

	public void validerEditForm(String email, String prenom, String nom, String tel, String pass, String login) {
		validerNom(nom);
		validerPrenom(prenom);
		validerEmail(email);
		validerLogin(login);
		validerMotDePasse(pass);
		validerTel(tel);
	}

	private void validerLoginPassUnique(String pass, String login) {
		try {
			verifierLoginPassUnique(pass, login);
		} catch (FormException e) {
			setError(FIELD_LOGIN, e.getMessage());

		}

	}

	private void validerNom(String nom) {
		try {
			verifierNom(nom);
		} catch (FormException e) {
			setError(FIELD_Nom, e.getMessage());

		}

	}

	private void validerPrenom(String prenom) {
		try {
			verifierPrenom(prenom);
		} catch (FormException e) {
			setError(FIELD_Prenom, e.getMessage());

		}

	}

	private void validerEmail(String email) {
		try {
			verifierEmail(email);
		} catch (FormException e) {
			setError(FIELD_EMAIL, e.getMessage());

		}

	}

	private void validerLogin(String login) {
		try {
			verifierLogin(login);
		} catch (FormException e) {
			setError(FIELD_LOGIN, e.getMessage());

		}

	}

	private void validerMotDePasse(String mdp) {
		try {
			verifierPass(mdp);
		} catch (FormException e) {
			setError(FIELD_PASS, e.getMessage());

		}

	}

	private void validerCin(String cin) {
		try {
			verifierCin(cin);
		} catch (FormException e) {
			setError(FIELD_CIN, e.getMessage());

		}

	}

	private void validerTel(String tel) {
		try {
			verifierTel(tel);
		} catch (FormException e) {
			setError(FIELD_TEL, e.getMessage());

		}

	}

}