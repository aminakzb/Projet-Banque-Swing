package metier.forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FormException extends Exception {

	private String consigne;
	private JLabel label;

	public FormException(JLabel label) {
		this.label = label;
	}

	public String getConsigne() {
		return consigne;
	}

	public FormException() {
		super("Erreur dans le formulaire !!!!");
	}

	public FormException(String errorMsg) {
		super(errorMsg);
	}

	public FormException(String errorMsg, String consigne) {
		super(errorMsg);
		this.consigne = consigne;
	}

	public FormException(String errorMsg, JFrame frame) {

		JOptionPane.showMessageDialog(frame, errorMsg, "A L E R T", JOptionPane.ERROR_MESSAGE);
	}

	public FormException(String errorMsg, JLabel errorLabel) {
		errorLabel.setText(errorMsg);
	}

}
