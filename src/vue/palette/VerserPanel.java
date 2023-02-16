package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.clients.ServiceClient;

public class VerserPanel extends JPanel {
	JButton verser_btn = new JButton();
	private JTextField textFieldMontant, compteBeneficiare;
	private JLabel beneficiareLabel, montantLabel;
	private JPanel errorPane, succesPane;
	private String erreur;
	List<String> messageError = new ArrayList<String>();

	public void initButtons() {
		verser_btn = new JButton("Verser");
		verser_btn.setFont(new Font("Arial", Font.PLAIN, 15));
		verser_btn.setSize(150, 40);
		verser_btn.setForeground(Color.white);
		verser_btn.setBackground(new Color(0, 153, 153));
		verser_btn.setLocation(250, 400);
		verser_btn.setFocusable(false);
		initActions();
	}

	public void initBox() {
		compteBeneficiare = new JTextField(50);
		compteBeneficiare.setLocation(300, 100);
		compteBeneficiare.setSize(200, 35);
		compteBeneficiare.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	public void initTextField() {

		textFieldMontant = new JTextField(50);
		textFieldMontant.setLocation(300, 200);
		textFieldMontant.setSize(200, 35);
		textFieldMontant.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	public void initLabels() {
		beneficiareLabel = new Labels("Compte Béneficiare:", 50, 100);
		montantLabel = new Labels("Montant:", 50, 200);
	}

	public void initMsgPanel() {
		initButtons();
		errorPane = new ColoredPanel(null, new Color(255, 204, 153));
		errorPane.setSize(520, 70);
		errorPane.setLocation(50, 300);
		errorPane.setVisible(false);

		succesPane = new ColoredPanel(null, new Color(204, 255, 153));
		JLabel label = new Labels("Versement effectué avec succès", Color.GREEN,
				new Font("Segoe UI Black", Font.ITALIC, 15));
		succesPane.add(label, BorderLayout.CENTER);
		succesPane.setSize(520, 70);
		succesPane.setLocation(50, 300);
		succesPane.setVisible(false);
	}

	private void initActions() {
		verser_btn.addActionListener(e -> {
			String numCompte = compteBeneficiare.getText();
			String montant = textFieldMontant.getText();
			ServiceClient service = new ServiceClient();
			boolean valide = false;
			if (service.versement(numCompte, montant))
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

	public VerserPanel() {
		initLabels();
		initTextField();
		initBox();
		initButtons();
		initMsgPanel();
		setBackground(Color.white);
		setLayout(null);
		add(verser_btn);
		add(beneficiareLabel);
		add(montantLabel);
		add(compteBeneficiare);
		add(textFieldMontant);
		add(errorPane);
		add(succesPane);

	}
}
