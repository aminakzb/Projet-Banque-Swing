package presentation.modele;

import java.time.LocalDateTime;

public class Compte {
	private static long compteur = 1;
	private String numeroCompte;
	private Double solde;
	private LocalDateTime dateCreation;
	private Client propriétaire;

	public static long getCompteur() {
		return compteur;
	}

	public void setDateCreation() {
		this.dateCreation = LocalDateTime.now();
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setPropriétaire(Client propriétaire) {
		this.propriétaire = propriétaire;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Client getPropriétaire() {
		return propriétaire;
	}

	public Double getSolde() {
		return solde;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte() {
		this.numeroCompte = "b-co00" + compteur++;
	}

	public void setNumeroCompte(long id) {
		this.numeroCompte = "b-co00" + id;
	}

	public void setNumeroCompte(String num) {
		this.numeroCompte = num;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public Compte() {
		setNumeroCompte();
		setDateCreation();
		setSolde(0.0);
		setPropriétaire(null);
	}

	public Compte(String num, LocalDateTime date, Double solde, Client client) {
		setNumeroCompte(num);
		setDateCreation(date);
		setSolde(solde);
		setPropriétaire(client);
	}

	public Compte(long id, Double solde, Client client) {
		setNumeroCompte(id);
		setDateCreation();
		setSolde(solde);
		setPropriétaire(client);
	}

	public Compte(String num, Double solde, Client client) {
		setNumeroCompte(num);
		setDateCreation();
		setSolde(solde);
		setPropriétaire(client);
	}

	@Override
	public String toString() {

		String compteStr = "| Compte N°  : " + getNumeroCompte() + "\n";
		compteStr += "         " + getSolde() + " MAD\n";

		return compteStr;
	}

}
