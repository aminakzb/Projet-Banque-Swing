package metier.authentification;

import java.util.Map;

public interface IAuth {

	Map<String, String> seConnecter(String login, String pass);

	void SeDéconnecter();

}
