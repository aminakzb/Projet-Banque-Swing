package presentation.modele;

public class Client extends Utilisateur {
	protected String prenom, nom;
	private String email, cin, tel;
	private Sexe sexe;

	public Client(String login, String pass, String n, String p, String cin, String mail, String tel, Sexe sexe) {

		setLogin(login);
		setMotDePasse(pass);
		setNom(n);
		setPrenom(p);
		setCin(cin);
		setEmail(mail);
		setTel(tel);
		setSexe(sexe);

	}

	public Client(long id, String login, String pass, String n, String p, String cin, String mail, String tel,
			Sexe sexe) {

		setId(id);
		setLogin(login);
		setMotDePasse(pass);
		setNom(n);
		setPrenom(p);
		setCin(cin);
		setEmail(mail);
		setTel(tel);
		setSexe(sexe);

	}

	public Client(Client client) {

		setId(client.getId());
		setLogin(client.getLogin());
		setMotDePasse(client.getMotDePasse());
		setNom(client.getNom());
		setPrenom(client.getPrenom());
		setCin(client.getCin());
		setEmail(client.getEmail());
		setTel(client.getTel());
		setSexe(client.getSexe());

	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		String clientStr = "├ Identifiant               : " + this.id + "\n";
		clientStr += "├ Nom Complet               : " + this.getNomComplet() + "\n";
		clientStr += "├ Numéro de CIN             : " + this.cin + "\n";
		clientStr += "├ Adresse email             : " + this.email + "\n";
		clientStr += "├ Numéro téléphone          : " + this.tel + "\n";
		clientStr += "├ Sexe                      : " + this.sexe + "\n";
		clientStr += "├┼┼┼┤► Informations de connexion \n";
		clientStr += "├ Nom d'utilisateur         : " + this.getLogin() + "\n";
		clientStr += "├ Mot de pass               : " + this.getMotDePasse() + "\n";

		return clientStr;
	}

	public String getNomComplet() {
		return prenom + " " + nom;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Client client)
			return id.equals(client.id);
		return false;
	}

}
