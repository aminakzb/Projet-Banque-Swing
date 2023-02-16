package vue.admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.daoFiles.ClientDao;
import metier.admin.ServiceAdmin;
import presentation.modele.Client;
import presentation.modele.Sexe;
import vue.palette.HintTextField;
import vue.palette.Labels;
import vue.palette.TablePanelClient;
import vue.palette.TablePanelCompte;

public class EditClientFrame extends JFrame {

	private Container container;
	private JLabel nom, prenom, email, cin, tel, login, motDePass, sexelabel, idClient, idlabel;
	private JTextField textFieldNom, sexeLbl, textFieldPrenom, textFieldEmail, textFieldCin, textFieldTel,
			textFieldLogin, textFieldMotDePass;
	private JButton editButton;
	private TablePanelClient tablePanelClient;
	private JLabel errorLabelLogin, errorLabelMdp, errorLabelNom, errorLabelPrenom, errorLabelEmail, errorLabelTel,
			errorLabelCin;
	private static final String FIELD_LOGIN = "Login", FIELD_PASS = "Mot de passe", FIELD_EMAIL = "Email",
			FIELD_CIN = "Cin", FIELD_TEL = "Tel", FIELD_Nom = "Nom", FIELD_Prenom = "Prenom";
	Sexe sexe;
	Long id;

	public JTextField getTextFieldNom() {
		return textFieldNom;
	}

	public JTextField getTextFieldPrenom() {
		return textFieldPrenom;
	}

	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public JTextField getTextFieldCin() {
		return textFieldCin;
	}

	public JTextField getTextFieldTel() {
		return textFieldTel;
	}

	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}

	public JTextField getTextFieldMotDePass() {
		return textFieldMotDePass;
	}

	public JTextField getSexeLbl() {
		return sexeLbl;
	}

	public JLabel getIdlabel() {
		return idlabel;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public EditClientFrame(String title) {

		initContainer();
		setTitle(title);
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void initContainer() {
		container = getContentPane();
		container.setLayout(null);
		container.setBackground(new Color(255, 255, 204));

		initlabels();
		initTextFieldsEditClient();
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
		container.add(sexeLbl);

		container.add(errorLabelLogin);
		container.add(errorLabelMdp);
		container.add(errorLabelNom);
		container.add(errorLabelPrenom);
		container.add(errorLabelEmail);
		container.add(errorLabelTel);
		container.add(errorLabelCin);

		container.add(editButton);
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

	public void initTextFieldsEditClient() {
		String sexeString;
		ClientDao clientEdit = new ClientDao();
		tablePanelClient = new TablePanelClient();
		int row = TablePanelCompte.row;

		id = (Long) TablePanelClient.getIdClient();
		String nom = (String) clientEdit.findById(id).getNom();
		String prenom = (String) clientEdit.findById(id).getPrenom();
		String login = (String) clientEdit.findById(id).getLogin();
		String motDePasse = (String) clientEdit.findById(id).getMotDePasse();
		String cin = clientEdit.findById(id).getCin();
		String email = (String) clientEdit.findById(id).getEmail();
		String tel = (String) clientEdit.findById(id).getTel();
		sexe = (Sexe) clientEdit.findById(id).getSexe();
		if (sexe == Sexe.HOMME) {
			sexeString = "Homme";
		} else
			sexeString = "Femme";

		textFieldNom = new JTextField(nom);
		textFieldNom.setLocation(120, 100);
		textFieldNom.setSize(200, 35);

		textFieldPrenom = new JTextField(prenom);
		textFieldPrenom.setLocation(120, 200);
		textFieldPrenom.setSize(200, 35);

		textFieldEmail = new JTextField(email);
		textFieldEmail.setLocation(120, 300);
		textFieldEmail.setSize(200, 35);

		textFieldCin = new JTextField(cin);
		textFieldCin.setLocation(120, 400);
		textFieldCin.setSize(200, 35);

		textFieldTel = new JTextField(tel);
		textFieldTel.setLocation(430, 100);
		textFieldTel.setSize(200, 35);

		textFieldLogin = new JTextField(login);
		textFieldLogin.setLocation(430, 200);
		textFieldLogin.setSize(200, 35);

		textFieldMotDePass = new JTextField(motDePasse);
		textFieldMotDePass.setLocation(430, 300);
		textFieldMotDePass.setSize(200, 35);

		sexeLbl = new HintTextField(sexeString, 200, 450);
		sexeLbl.setEditable(false);
		sexeLbl.setLocation(430, 400);

	}

	public void initButtons() {

		editButton = new JButton("Modifier");
		editButton.setFont(new Font("Arial", Font.PLAIN, 15));
		editButton.setSize(150, 40);
		editButton.setForeground(Color.white);
		editButton.setBackground(new Color(0, 153, 153));
		editButton.setLocation(320, 500);
		editButton.setFocusable(false);
	}

	public void initActions() {
		editButton.addActionListener(e -> {

			ServiceAdmin service = new ServiceAdmin();
			Client newClient = service.modifierClient(id, textFieldNom.getText(), textFieldPrenom.getText(),
					textFieldLogin.getText(), textFieldMotDePass.getText(), textFieldCin.getText(),
					textFieldEmail.getText(), textFieldTel.getText(), sexe);
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
						"Le Client " + newClient.getNomComplet() + " a été modifié avec succès", "I N F O",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});

	}

}
