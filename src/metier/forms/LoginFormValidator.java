package metier.forms;

import java.util.HashMap;
import java.util.Map;

import metier.Colors;

public class LoginFormValidator implements Colors {

	private static final String FIELD_LOGIN = "Login", FIELD_PASS = "Mot de passe";
	private Map<String, String> errors = new HashMap<>();
	private String resultMsg;

	public LoginFormValidator() {
	}

	public Map<String, String> Errors() {
		return errors;
	}

	public void setError(String fieldName, String errorMsg) {
		Errors().put(fieldName, errorMsg);
	}

	public String ResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	// fonctions de verification
	public boolean verifierLogin(String login) throws FormException {
		if (login != null && login.trim().length() != 0 && !login.equals("Login ")) {
			if (login.trim().length() < 4)
				throw new FormException("Login doit avoir plus de 4 chars !!!");
		} else {
			throw new FormException("*Login obligatoire");
		}
		return true;
	}

	public void verifierPass(String pass) throws FormException {
		if (pass != null && pass.trim().length() != 0) {
			if (pass.trim().length() < 4)
				throw new FormException("Mot de passe doit avoir plus de 4 chars !!!");
		} else {
			throw new FormException("*Mot de passe obligatoire!");
		}
	}

	// fonctions de validation
	public boolean validerLogin(String login) {

		try {
			verifierLogin(login);
		} catch (FormException e) {
			setError(FIELD_LOGIN, e.getMessage());
			return false;
		}
		return true;
	}

	public boolean validerPass(String pass) {

		try {
			verifierPass(pass);
		} catch (FormException e) {
			setError(FIELD_PASS, e.getMessage());
			return false;
		}
		return true;
	}

}