package metier.clients;

import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;

import dao.daoFiles.CompteDao;
import dao.daoFiles.LogDao;
import metier.authentification.ServiceAuth;
import metier.forms.OperationsFormValidator;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

public class ServiceClient implements IServiceClient {
	private OperationsFormValidator validator;

	public OperationsFormValidator getValidator() {
		return validator;
	}

	public List<Compte> mesComptes() {
		List<Compte> comptes = new CompteDao().findAll().stream()
				.filter(compte -> compte.getPropriétaire().getId().equals(ServiceAuth.getSession().getId())).toList();
		return comptes;
	}

	@Override
	public boolean versement(String numCompte, String montant) {
		validator = new OperationsFormValidator();
		validator.getErreur().clear();
		validator.validerForm(numCompte, montant);
		String emetteur = "";
		if (validator.getErreur().isEmpty()) {
			Compte compte = new CompteDao().findByNum(numCompte);

			if (compte != null) {
				for (Compte c : mesComptes()) {
					if (c.equals(compte)) {
						emetteur = "vous meme";
					} else
						emetteur = compte.getPropriétaire().getNomComplet();
				}
				compte.setSolde(compte.getSolde() + Double.parseDouble(montant));
				new CompteDao().update(compte);
				Log log = new Log(numCompte, LocalDate.now(), TypeLog.VERSEMENT,
						montant + " versé à votre profit par " + emetteur);
				new LogDao().save(log);
			} else {
				validator.setErreur("Compte non trouvé, vérifier le num saisi!");
				return false;
			}
		} else
			return false;

		return true;
	}

	@Override
	public boolean retrait(String numCompte, String montant) {
		validator = new OperationsFormValidator();
		validator.getErreur().clear();
		validator.validerForm(numCompte, montant);
		if (validator.getErreur().isEmpty()) {
			Compte compte = new CompteDao().findByNum(numCompte);
			if (compte != null) {
				compte.setSolde(compte.getSolde() - Double.parseDouble(montant));
				new CompteDao().update(compte);
				Log log = new Log(numCompte, LocalDate.now(), TypeLog.RETRAIT, montant + " Retiré de votre compte ");
				new LogDao().save(log);
			}
		} else
			return false;

		return true;
	}

	@Override
	public boolean retrait(int choixRetrait) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean virement(String numCompteSrc, String numCompteDes, String montant) {
		validator = new OperationsFormValidator();
		validator.getErreur().clear();
		validator.validerForm(numCompteDes, montant);
		if (validator.getErreur().isEmpty()) {
			Compte compteDes = new CompteDao().findByNum(numCompteDes);
			Compte compteSrc = new CompteDao().findByNum(numCompteSrc);
			if (compteDes != null) {
				compteDes.setSolde(compteDes.getSolde() + Double.parseDouble(montant));
				compteSrc.setSolde(compteSrc.getSolde() - Double.parseDouble(montant));
				new CompteDao().update(compteDes);
				new CompteDao().update(compteSrc);
				Log logSrc = new Log(numCompteSrc, LocalDate.now(), TypeLog.VIREMENT,
						"Vous avez effectué un virement de " + montant + "Dhs au profit de "
								+ compteDes.getPropriétaire().getNomComplet() + " compte N° " + numCompteDes);
				Log logDes = new Log(numCompteDes, LocalDate.now(), TypeLog.VIREMENT, "Vous avez reçu un virement de"
						+ montant + " Dhs de la part de " + compteSrc.getPropriétaire().getNomComplet());
				new LogDao().save(logSrc);
				new LogDao().save(logDes);
			} else {
				validator.setErreur("Compte destinataire non trouvé, vérifier le num saisi!");
				return false;
			}
		} else
			return false;

		return true;
	}

	@Override
	public boolean modifierProfile(int choixModification) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DefaultListModel<String> dernièresOpérations(Client user, String choix) {
		DefaultListModel<String> modelLogs = new DefaultListModel<>();
		List<Log> logs = null;
		switch (choix) {
		case "Dernière semaine": {
			logs = new LogDao().findLogsOfLastWeek(user);
			modelLogs.clear();
			for (int i = 0; i < logs.size(); i++) {
				modelLogs.addElement(logs.get(i).toString());
			}
			break;
		}
		case "Dernier mois": {
			logs = new LogDao().findLogsOfLastMonth(user);
			modelLogs.clear();
			for (int i = 0; i < logs.size(); i++) {
				modelLogs.addElement(logs.get(i).toString());
			}
			break;
		}
		}
		return modelLogs;
	}

	@Override
	public Double afficherSolde() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte choisirCompte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afficherTicket() {
		// TODO Auto-generated method stub

	}

}
