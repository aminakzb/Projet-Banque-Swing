package metier.authentification;

import java.util.HashMap;
import java.util.Map;

import dao.daoFiles.ClientDao;
import metier.forms.LoginFormValidator;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Utilisateur;

public class ServiceAuth implements IAuth {
	Banque banque = new Banque();
	private Client client;
	private Map<String, String> errors = new HashMap<>();
	private static Utilisateur session = null;

	public static Utilisateur getSession() {
		return session;
	}

	public static void setSession(Utilisateur session) {
		ServiceAuth.session = session;
	}

	public ServiceAuth(Banque banque) {
		super();
		this.banque = banque;
	}

	public ServiceAuth() {
	}

	public Map<String, String> Errors() {
		return errors;
	}

	public void setError(String fieldName, String errorMsg) {
		Errors().put(fieldName, errorMsg);
	}

	@Override
	public Map<String, String> seConnecter(String login, String pass) {

		errors.clear();
		LoginFormValidator validator = new LoginFormValidator();
		if (validator.validerLogin(login) && validator.validerPass(pass)) {
			if (Admin.getInstance().getLogin().equals(login) && Admin.getInstance().getMotDePasse().equals(pass)) {
				session = Admin.getInstance();
			} else if (new ClientDao().findByLoginAndPass(login, pass) != null) {
				session = new ClientDao().findByLoginAndPass(login, pass);
			}
		} else if (!validator.validerLogin(login)) {
			setError("*Login", "obligatoire");
		} else if (!validator.validerPass(pass)) {
			setError("*Mot de passe", "obligatoire");
		}
		return errors;

	}

	@Override
	public void SeDéconnecter() {
		// TODO Auto-generated method stub

	}

}
