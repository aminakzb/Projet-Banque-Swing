package metier.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;

import dao.daoFiles.ClientDao;
import dao.daoFiles.CompteDao;
import dao.daoFiles.LogDao;
import metier.forms.ClientFormValidator;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.Sexe;
import presentation.modele.TableauDeBord;
import presentation.modele.TypeLog;
import vue.admin.AdminAccueilFrame;
import vue.palette.TablePanel;

public class ServiceAdmin extends JComponent implements IServiceAdmin {
	private Client client;
	private ClientFormValidator validator;
	private TablePanel tablePanel;
	private Double maxSolde = 0.0;

	public ClientFormValidator getValidator() {
		return validator;
	}

	public void setValidator(ClientFormValidator validator) {
		this.validator = validator;
	}

	public ServiceAdmin() {
	}

	@Override
	public Client nouveauClient(String nom, String prenom, String login, String motDePasse, String cin, String email,
			String tel, Sexe sexe) {
		// TODO Auto-generated method stub
		Client newclient = null;
		validator = new ClientFormValidator();
		validator.Errors().clear();
		validator.validerForm(email, prenom, nom, tel, motDePasse, login, cin);

		if (validator.Errors().isEmpty()) {

			newclient = new Client(login, motDePasse, nom, prenom, cin, email, tel, sexe);
			new ClientDao().save(newclient);

			var list = new ClientDao().findAll();
			TablePanel.getTableModel().initClientsData(list);

		}
		return newclient;
	}

	@Override
	public Client nouveauCompteClientExistant(String numCompte, Double solde, Client clientProprietaire) {

		Compte newCompte = new Compte(numCompte, solde, clientProprietaire);

		Log log = new Log(numCompte, LocalDate.now(), TypeLog.CREATION, "Compte " + numCompte + " crée");

		new CompteDao().save(newCompte);
		new LogDao().save(log);

		var list = new CompteDao().findAll();
		TablePanel.getTableModel().initComptesData(list);

		return newCompte.getPropriétaire();
	}

	@Override
	public Client chercherClientParId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> chercherClientParNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> chercherClientParPrénom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client chercherClientParCin(String cin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client chercherClientParEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte chercherCompteParId(Long idCompte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> chercherCompteParSolde(double solde) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> chercherCompteParDateCreation(LocalDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> chercherCompteParPropriétaire(Client propriétaire) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client modifierClient(long id, String nom, String prenom, String login, String motDePasse, String cin,
			String email, String tel, Sexe sexe) {
		Client newclient = null;

		validator = new ClientFormValidator();
		validator.Errors().clear();
		validator.validerEditForm(email, prenom, nom, tel, motDePasse, login);
		if (validator.Errors().isEmpty()) {

			newclient = new Client(id, login, motDePasse, nom, prenom, cin, email, tel, sexe);

			// TablePanel.getTableModel().setValueAt();

			new ClientDao().update(newclient);
			var list = new ClientDao().findAll();
			TablePanel.getTableModel().initClientsData(list);
		}
		return newclient;
	}

	@Override
	public List<Client> supprimerClient(Long id) {

		List<Compte> comptesAssocies = new CompteDao().mesComptes(id);
		for (int i = 0; i < comptesAssocies.size(); i++) {
			String num = comptesAssocies.get(i).getNumeroCompte();
			Compte compte = new CompteDao().findByNum(num);
			new CompteDao().saveArchive(compte);
			new CompteDao().delete(compte);
		}

		new ClientDao().deleteById(id);
		var list = new ClientDao().findAll();

		return list;
	}

	@Override
	public TableauDeBord calculerEtAfficherStatistiques() {

		var compteList = new CompteDao().findAll();
		var clientList = new ClientDao().findAll();
		var minSolde = 0.0;
		String NomClientPlusRiche = "";

		// Solde max et client plus riche
		if (!compteList.isEmpty()) {
			maxSolde = new CompteDao().findAll().stream().map(compte -> compte.getSolde())
					.max((solde1, solde2) -> solde1.compareTo(solde2)).get();
			Compte CompteDuplusRiche = new CompteDao().findAll().stream()
					.filter(compte -> compte.getSolde().equals(maxSolde)).findFirst().orElse(null);
			NomClientPlusRiche = CompteDuplusRiche.getPropriétaire().getNomComplet();
		}

		// Solde min
		if (!compteList.isEmpty()) {
			minSolde = new CompteDao().findAll().stream().map(compte -> compte.getSolde())
					.min((solde1, solde2) -> solde1.compareTo(solde2)).get();
		}

		// Nombre total client
		long totalClient = 0;
		if (!clientList.isEmpty()) {
			totalClient = new ClientDao().findAll().stream().count();
		}

		// Nombre total compte
		long totalCompte = 0;
		if (!compteList.isEmpty()) {
			totalCompte = new CompteDao().findAll().stream().count();
		}

		// Nombre total client femme
		long totalClientFemme = 0;
		if (!clientList.isEmpty()) {
			totalClientFemme = new ClientDao().findAll().stream()
					.filter(clientF -> clientF.getSexe().equals(Sexe.FEMME)).count();
		}

		// Nombre total client homme
		long totalClientHomme = 0;
		if (!clientList.isEmpty()) {
			totalClientHomme = new ClientDao().findAll().stream()
					.filter(clientH -> clientH.getSexe().equals(Sexe.HOMME)).count();
		}

		TableauDeBord tableauDeBord = new TableauDeBord(maxSolde, minSolde, totalClient, totalCompte,
				NomClientPlusRiche, totalClientFemme, totalClientHomme);
		AdminAccueilFrame.setTableauDeBord(tableauDeBord);
		return tableauDeBord;
	}

	@Override
	public List<Client> trierClientParNom() {
		List<Client> clientsTrie = new ClientDao().findAll().stream().sorted((c1, c2) -> {
			return c1.getNom().compareTo(c2.getNom());
		}).collect(Collectors.toList());

		return clientsTrie;
	}

	@Override
	public List<Client> trierClientParCin() {
		List<Client> clientsTrie = new ClientDao().findAll().stream().sorted((c1, c2) -> {
			return c1.getCin().compareTo(c2.getCin());
		}).collect(Collectors.toList());

		return clientsTrie;
	}

	@Override
	public List<Client> trierClientParEmail() {
		List<Client> clientsTrie = new ClientDao().findAll().stream().sorted((c1, c2) -> {
			return c1.getEmail().compareTo(c2.getEmail());
		}).collect(Collectors.toList());

		return clientsTrie;
	}

	@Override
	public List<Client> trierClientParSoldeCompte() {
		return null;
	}

	@Override
	public List<Compte> trierComptesParSolde() {
		List<Compte> comptesTrie = new CompteDao().findAll().stream().sorted((c1, c2) -> {
			return c1.getSolde().compareTo(c2.getSolde());
		}).collect(Collectors.toList());

		return comptesTrie;
	}

	@Override
	public List<Compte> trierComptesParDateDeCreation() {
		List<Compte> comptesTrie = new CompteDao().findAll().stream().sorted((c1, c2) -> {
			return c1.getDateCreation().compareTo(c2.getDateCreation());
		}).collect(Collectors.toList());

		return comptesTrie;
	}

	@Override
	public List<Compte> trierComptesParNomPropriétaire() {
		List<Compte> comptesTrie = new CompteDao().findAll().stream().sorted((c1, c2) -> {
			return c1.getPropriétaire().getNomComplet().compareTo(c2.getPropriétaire().getNomComplet());
		}).collect(Collectors.toList());

		return comptesTrie;
	}

}
