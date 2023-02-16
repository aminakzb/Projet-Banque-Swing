package vue.admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import metier.admin.ServiceAdmin;
import presentation.modele.Client;
import presentation.modele.Sexe;
import vue.palette.HintTextField;
import vue.palette.Labels;

public class AddClientFrame extends JFrame {

	private Container container;
	private JLabel nom, prenom, email, cin, tel, login, motDePass, sexelabel;
	private HintTextField textFieldNom, textFieldPrenom, textFieldEmail, textFieldCin, textFieldTel, textFieldLogin,
			textFieldMotDePass;
	private JRadioButton female, male;
	private JButton addButton;
	private static final String FIELD_LOGIN = "Login", FIELD_PASS = "Mot de passe", FIELD_EMAIL = "Email",
			FIELD_CIN = "Cin", FIELD_TEL = "Tel", FIELD_Nom = "Nom", FIELD_Prenom = "Prenom";
	private JLabel errorLabelLogin, errorLabelMdp, errorLabelNom, errorLabelPrenom, errorLabelEmail, errorLabelTel,
			errorLabelCin;

	public HintTextField getTextFieldNom() {
		return textFieldNom;
	}

	public HintTextField getTextFieldPrenom() {
		return textFieldPrenom;
	}

	public HintTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public HintTextField getTextFieldCin() {
		return textFieldCin;
	}

	public HintTextField getTextFieldTel() {
		return textFieldTel;
	}

	public HintTextField getTextFieldLogin() {
		return textFieldLogin;
	}

	public HintTextField getTextFieldMotDePass() {
		return textFieldMotDePass;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JRadioButton getMale() {
		return male;
	}

	public AddClientFrame(String title) {

		initContainer();
		setTitle(title);
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	private void initContainer() {
		container = getContentPane();
		container.setLayout(null);
		container.setBackground(new Color(255, 255, 204));

		initlabels();
		initTextFieldsAddClient();
		initButtons();

		container.add(nom);
		container.add(prenom);
		container.add(email);
		container.add(cin);
		container.add(tel);
		container.add(login);
		container.add(motDePass);
		container.add(sexelabel);

		container.add(textFieldNom);
		container.add(textFieldPrenom);
		container.add(textFieldEmail);
		container.add(textFieldCin);
		container.add(textFieldTel);
		container.add(textFieldLogin);
		container.add(textFieldMotDePass);

		container.add(errorLabelLogin);
		container.add(errorLabelMdp);
		container.add(errorLabelNom);
		container.add(errorLabelPrenom);
		container.add(errorLabelEmail);
		container.add(errorLabelTel);
		container.add(errorLabelCin);

		container.add(male);
		container.add(female);
		container.add(addButton);

		initActions();
	}

	public void initlabels() {
		nom = new Labels("Nom:", 50, 100);
		prenom = new Labels("Prenom:", 50, 200);
		email = new Labels("Email:", 50, 300);
		cin = new Labels("CIN:", 50, 400);
		tel = new Labels("Tel:", 350, 100);
		login = new Labels("Login:", 350, 200);
		motDePass = new Labels("Pass:", 350, 300);
		sexelabel = new Labels("Sexe:", 350, 400);

		errorLabelLogin = new Labels(null, Color.red, new Font("Arial", Font.ITALIC, 13));
		errorLabelLogin.setSize(250, 10);
		errorLabelLogin.setLocation(350, 240);

		errorLabelMdp = new Labels(null, Color.red, new Font("Arial", Font.ITALIC, 13));
		errorLabelMdp.setSize(250, 10);
		errorLabelMdp.setLocation(350, 340);

		errorLabelNom = new Labels(null, Color.red, new Font("Arial", Font.ITALIC, 13));
		errorLabelNom.setSize(250, 10);
		errorLabelNom.setLocation(50, 140);

		errorLabelPrenom = new Labels(null, Color.red, new Font("Arial", Font.ITALIC, 13));
		errorLabelPrenom.setSize(250, 10);
		errorLabelPrenom.setLocation(50, 240);

		errorLabelEmail = new Labels(null, Color.red, new Font("Arial", Font.ITALIC, 13));
		errorLabelEmail.setSize(250, 10);
		errorLabelEmail.setLocation(50, 340);

		errorLabelTel = new Labels(null, Color.red, new Font("Arial", Font.ITALIC, 13));
		errorLabelTel.setSize(250, 10);
		errorLabelTel.setLocation(350, 140);

		errorLabelCin = new Labels(null, Color.red, new Font("Arial", Font.ITALIC, 13));
		errorLabelCin.setSize(250, 10);
		errorLabelCin.setLocation(50, 440);

	}

	public void initTextFieldsAddClient() {

		textFieldNom = new HintTextField("Nom ");
		textFieldNom.setLocation(120, 100);

		textFieldPrenom = new HintTextField("Prenom ");
		textFieldPrenom.setLocation(120, 200);

		textFieldEmail = new HintTextField("Email ");
		textFieldEmail.setLocation(120, 300);

		textFieldCin = new HintTextField("Cin ");
		textFieldCin.setLocation(120, 400);

		textFieldTel = new HintTextField("Tel ");
		textFieldTel.setLocation(430, 100);

		textFieldLogin = new HintTextField("Login ");
		textFieldLogin.setLocation(430, 200);

		textFieldMotDePass = new HintTextField("Mot de passe ");
		textFieldMotDePass.setLocation(430, 300);

	}

	public void initButtons() {

		male = new JRadioButton("Homme");
		male.setFont(new Font("Arial", Font.PLAIN, 15));
		male.setSelected(true);
		male.setSize(80, 20);
		male.setLocation(430, 400);
		male.setBackground(null);

		female = new JRadioButton("Femme");
		female.setFont(new Font("Arial", Font.PLAIN, 15));
		female.setSelected(false);
		female.setSize(80, 20);
		female.setLocation(510, 400);
		female.setBackground(null);

		ButtonGroup gender = new ButtonGroup();
		gender.add(getMale());
		gender.add(female);

		addButton = new JButton("Ajouter");
		addButton.setFont(new Font("Arial", Font.PLAIN, 18));
		addButton.setSize(150, 40);
		addButton.setForeground(Color.white);
		addButton.setBackground(new Color(0, 153, 153));
		addButton.setLocation(320, 500);
		addButton.setFocusable(false);

	}

	public void initActions() {
		addButton.addActionListener(e -> {

			Sexe sexe;
			String nom = textFieldNom.getText();
			String prenom = textFieldPrenom.getText();
			String login = textFieldLogin.getText();
			String motDePasse = textFieldMotDePass.getText();
			String cin = textFieldCin.getText();
			String email = textFieldEmail.getText();
			String tel = textFieldTel.getText();
			if (male.isSelected()) {
				sexe = Sexe.HOMME;
			} else {
				sexe = Sexe.FEMME;
			}
			ServiceAdmin service = new ServiceAdmin();
			Client newClient = service.nouveauClient(nom, prenom, login, motDePasse, cin, email, tel, sexe);
			if (!service.getValidator().Errors().isEmpty()) {
				service.getValidator().Errors().forEach((field, errMsg) -> {
					if (field.equals(FIELD_LOGIN)) {
						errorLabelLogin.setText(errMsg);
					}
					if (field.equals(FIELD_PASS)) {
						errorLabelMdp.setText(errMsg);
					}
					if (field.equals(FIELD_EMAIL)) {
						errorLabelEmail.setText(errMsg);
					}
					if (field.equals(FIELD_CIN)) {
						errorLabelCin.setText(errMsg);
					}
					if (field.equals(FIELD_TEL)) {
						errorLabelTel.setText(errMsg);
					}
					if (field.equals(FIELD_Nom)) {
						errorLabelNom.setText(errMsg);
					}
					if (field.equals(FIELD_Prenom)) {
						errorLabelPrenom.setText(errMsg);
					}
				});

			}
			if (newClient != null) {
				JOptionPane.showMessageDialog(this,
						"Le Client " + newClient.getNomComplet() + " a été ajouté avec succès", "I N F O",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

}
