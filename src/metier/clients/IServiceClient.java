package metier.clients;

import javax.swing.DefaultListModel;

import presentation.modele.Client;
import presentation.modele.Compte;

public interface IServiceClient {

	boolean versement(String numCompte, String montant);

	boolean retrait(int choixRetrait);

	boolean modifierProfile(int choixModification);

	Double afficherSolde();

	Compte choisirCompte();

	void afficherTicket();

	boolean retrait(String numCompte, String montant);

	boolean virement(String numCompteSrc, String numCompteDes, String montant);

	DefaultListModel<String> dernièresOpérations(Client user, String choix);

}
