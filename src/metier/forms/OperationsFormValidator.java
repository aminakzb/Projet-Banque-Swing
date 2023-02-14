package metier.forms;

import java.util.Vector;

import metier.Verifiable;

public class OperationsFormValidator {

	private Vector<String> erreur = new Vector<>();

	public Vector<String> getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		getErreur().add(erreur);
	}

	public boolean verifierCompte(String numCompte) throws FormException {
		if (numCompte.trim().length() == 0 || numCompte.equals(null))
			throw new FormException("Numéro de compte obligatoire");
		return true;
	}

	public boolean verifierMontant(String montant) throws FormException {
		if (montant.trim().length() == 0 || montant.equals(null))
			throw new FormException("Montant obligatoire");
		else if (!Verifiable.isNumeric(montant))
			throw new FormException("Vérifier le format du montant !!!");
		else if (Double.parseDouble(montant) < 0)
			throw new FormException("Montant invalide !!");
		return true;
	}

	private void validerCompte(String numCompte) {
		try {
			verifierCompte(numCompte);
		} catch (FormException e) {
			setErreur(e.getMessage());
		}
	}

	private void validerMontant(String montant) {
		try {
			verifierMontant(montant);
		} catch (FormException e) {
			setErreur(e.getMessage());
		}
	}

	public void validerForm(String numCompte, String montant) {
		validerCompte(numCompte);
		validerMontant(montant);
	}

}
