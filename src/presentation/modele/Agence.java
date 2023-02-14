package presentation.modele;

public class Agence {
	private static long compteur = 1;
	private long id;
	private String nomAgence;
	private String emailAgence;
	private String telAgence;
	private String adresseAgence;

	public long getId() {
		return id;
	}

	public void setId() {
		this.id = compteur++;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomAgence() {
		return nomAgence;
	}

	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}

	public String getEmailAgence() {
		return emailAgence;
	}

	public void setEmailAgence(String emailAgence) {
		this.emailAgence = emailAgence;
	}

	public String getTelAgence() {
		return telAgence;
	}

	public void setTelAgence(String telAgence) {
		this.telAgence = telAgence;
	}

	public String getAdresseAgence() {
		return adresseAgence;
	}

	public void setAdresseAgence(String adresseAgence) {
		this.adresseAgence = adresseAgence;
	}

	public Agence( String nomAgence, String emailAgence, String telAgence, String adresseAgence) {
		setNomAgence(nomAgence);
		setEmailAgence(emailAgence);
		setTelAgence(telAgence);
		setAdresseAgence(adresseAgence);
	}

}
