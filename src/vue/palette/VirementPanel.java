package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.clients.ServiceClient;
import presentation.modele.Compte;

public class VirementPanel extends JPanel {
	JButton virement_btn = new JButton();
	private JTextField textFieldBeneficiaire, textFieldMontant;
	private JLabel beneficiareLabel, emetteurLabel, montantLabel;
	private JComboBox compteEmetteur;
	Vector<String> comptes = new Vector<String>();
	private JPanel errorPane, succesPane;
	private String erreur;
	List<String> messageError = new ArrayList<String>();

	public void initButtons() {
		virement_btn = new JButton("Effectuer virement");
		virement_btn.setFont(new Font("Arial", Font.PLAIN, 15));
		virement_btn.setSize(250, 40);
		virement_btn.setForeground(Color.white);
		virement_btn.setBackground(new Color(0, 153, 153));
		virement_btn.setLocation(250, 430);
		initActions();
	}

	private void initContentsBox() {
		List<Compte> mesComptes = new ServiceClient().mesComptes();
		for (int i = 0; i < mesComptes.size(); i++) {
			comptes.add(mesComptes.get(i).getNumeroCompte());
		}
	}

	public void initBox() {
		initContentsBox();
		compteEmetteur = new JComboBox<String>(comptes);
		compteEmetteur.setLocation(300, 100);
		compteEmetteur.setSize(200, 35);
		compteEmetteur.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	public void initTextField() {
		textFieldBeneficiaire = new JTextField(50);
		textFieldBeneficiaire.setLocation(300, 200);
		textFieldBeneficiaire.setSize(200, 35);
		textFieldBeneficiaire.setFont(new Font("Arial", Font.PLAIN, 18));

		textFieldMontant = new JTextField(50);
		textFieldMontant.setLocation(300, 300);
		textFieldMontant.setSize(200, 35);
		textFieldMontant.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	public void initLabels() {
		emetteurLabel = new Labels("Compte emetteur:", 50, 100);
		beneficiareLabel = new Labels("Compte Béneficiare:", 50, 200);
		montantLabel = new Labels("Montant:", 50, 300);
	}

	public void initMsgPanel() {
		initButtons();
		errorPane = new ColoredPanel(null, new Color(255, 204, 153));
		errorPane.setSize(520, 70);
		errorPane.setLocation(50, 350);
		errorPane.setVisible(false);

		succesPane = new ColoredPanel(null, new Color(204, 255, 153));
		JLabel label = new Labels("Virement effectué avec succès", Color.GREEN,
				new Font("Segoe UI Black", Font.ITALIC, 15));
		succesPane.add(label, BorderLayout.CENTER);
		succesPane.setSize(520, 70);
		succesPane.setLocation(50, 350);
		succesPane.setVisible(false);
	}

	private void initActions() {
		virement_btn.addActionListener(e -> {
			String numCompteSrc = (String) compteEmetteur.getSelectedItem();
			String montant = textFieldMontant.getText();
			String numCompteDes = textFieldBeneficiaire.getText();
			ServiceClient service = new ServiceClient();
			boolean valide = false;
			if (service.virement(numCompteSrc, numCompteDes, montant))
				valide = true;
			if (!service.getValidator().getErreur().isEmpty()) {
				service.getValidator().getErreur().forEach(erreurMsg -> {
					messageError.add(erreurMsg);

					erreur = String.join(" ", messageError);
					errorPane.setVisible(true);
				});
				JLabel label = new Labels(erreur, Color.RED, new Font("Segoe UI Black", Font.ITALIC, 15));
				errorPane.add(label, BorderLayout.CENTER);
			} else if (valide) {
				errorPane.setVisible(false);
				succesPane.setVisible(true);
			}
		});

	}

	public VirementPanel() {
		initLabels();
		initTextField();
		initBox();
		initMsgPanel();
		setBackground(Color.white);
		setLayout(null);
		setBackground(Color.white);
		initButtons();
		add(virement_btn);
		add(beneficiareLabel);
		add(emetteurLabel);
		add(montantLabel);
		add(textFieldBeneficiaire);
		add(textFieldMontant);
		add(compteEmetteur);
		add(errorPane);
		add(succesPane);
	}
}
