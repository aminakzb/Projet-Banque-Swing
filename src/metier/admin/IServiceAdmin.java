package metier.admin;

import java.time.LocalDateTime;
import java.util.List;

import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Sexe;
import presentation.modele.TableauDeBord;

public interface IServiceAdmin {

	Client nouveauClient(String nom, String prenom, String login, String motDePasse, String cin, String email,
			String tel, Sexe sexe);

	Client nouveauCompteClientExistant(String numCompte, Double solde, Client propriétaire);

	Client chercherClientParId(Long id);

	List<Client> chercherClientParNom(String nom);

	List<Client> chercherClientParPrénom(String prenom);

	Client chercherClientParCin(String cin);

	Client chercherClientParEmail(String email);

	Compte chercherCompteParId(Long idCompte);

	List<Compte> chercherCompteParSolde(double solde);

	List<Compte> chercherCompteParDateCreation(LocalDateTime date);

	List<Compte> chercherCompteParPropriétaire(Client propriétaire);

	Client modifierClient(long id, String nom, String prenom, String login, String motDePasse, String cin, String email,
			String tel, Sexe sexe);

	List<Client> supprimerClient(Long id);

	TableauDeBord calculerEtAfficherStatistiques();

	List<Client> trierClientParNom();

	List<Client> trierClientParCin();

	List<Client> trierClientParEmail();

	List<Client> trierClientParSoldeCompte();

	List<Compte> trierComptesParSolde();

	List<Compte> trierComptesParDateDeCreation();

	List<Compte> trierComptesParNomPropriétaire();

}
