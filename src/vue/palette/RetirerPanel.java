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

public class RetirerPanel extends JPanel {
	JButton retirer_btn = new JButton();
	private JTextField textFieldMontant;
	private JLabel emetteurLabel, montantLabel;
	private JComboBox compteEmetteur;
	Vector<String> comptes = new Vector<String>();
	private JPanel errorPane, succesPane;
	private String erreur;
	List<String> messageError = new ArrayList<String>();

	public void initButtons() {
		retirer_btn = new JButton("Retirer");
		retirer_btn.setFont(new Font("Arial", Font.PLAIN, 15));
		retirer_btn.setSize(150, 40);
		retirer_btn.setForeground(Color.white);
		retirer_btn.setBackground(new Color(0, 153, 153));
		retirer_btn.setLocation(250, 400);
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

		textFieldMontant = new JTextField(50);
		textFieldMontant.setLocation(300, 200);
		textFieldMontant.setSize(200, 35);
		textFieldMontant.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	public void initLabels() {
		emetteurLabel = new Labels("Compte emetteur:", 50, 100);
		montantLabel = new Labels("Montant:", 50, 200);
	}

	public void initMsgPanel() {
		initButtons();
		errorPane = new ColoredPanel(null, new Color(255, 204, 153));
		errorPane.setSize(520, 70);
		errorPane.setLocation(50, 300);
		errorPane.setVisible(false);

		succesPane = new ColoredPanel(null, new Color(204, 255, 153));
		JLabel label = new Labels("Retrait effectué avec succès", Color.GREEN,
				new Font("Segoe UI Black", Font.ITALIC, 15));
		succesPane.add(label, BorderLayout.CENTER);
		succesPane.setSize(520, 70);
		succesPane.setLocation(50, 300);
		succesPane.setVisible(false);
	}

	private void initActions() {
		retirer_btn.addActionListener(e -> {
			String numCompte = (String) compteEmetteur.getSelectedItem();
			String montant = textFieldMontant.getText();
			ServiceClient service = new ServiceClient();
			boolean valide = false;
			if (service.retrait(numCompte, montant))
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

	public RetirerPanel() {
		initLabels();
		initTextField();
		initBox();
		initMsgPanel();
		setBackground(Color.white);
		setLayout(null);
		add(retirer_btn);
		add(emetteurLabel);
		add(montantLabel);
		add(textFieldMontant);
		add(compteEmetteur);
		add(errorPane);
		add(succesPane);
	}
}
