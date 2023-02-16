package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import metier.authentification.ServiceAuth;
import presentation.modele.Admin;
import presentation.modele.Client;
import vue.admin.AdminFrame;
import vue.client.ClientFrame;
import vue.palette.HintTextField;
import vue.palette.JCheckBoxIcon;
import vue.palette.Labels;

public class LoginFrame extends JFrame {

	private JButton LoginBtn;
	private JLabel login, motDePass;
	private HintTextField textFieldLogin;
	private JPasswordField textFieldMotDePass;
	private JLabel background, errorLabelLogin, errorLabelMdp, errorLogin;
	JCheckBox hideshowpass;
	static JFrame frame;

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		LoginFrame.frame = frame;
	}

	public HintTextField getTextFieldLogin() {
		return textFieldLogin;
	}

	public JPasswordField getTextFieldMotDePass() {
		return textFieldMotDePass;
	}

	public JButton getLoginBtn() {
		return LoginBtn;
	}

	public JLabel getErrorLabelLogin() {
		return errorLabelLogin;
	}

	public void setErrorLabelLogin(JLabel errorLabelLogin) {
		this.errorLabelLogin = errorLabelLogin;
	}

	public JLabel getErrorLabelMdp() {
		return errorLabelMdp;
	}

	public void setErrorLabelMdp(JLabel errorLabelMdp) {
		this.errorLabelMdp = errorLabelMdp;
	}

	public JLabel getErrorLogin() {
		return errorLogin;
	}

	public void setErrorLogin(JLabel errorLogin) {
		this.errorLogin = errorLogin;
	}

	public LoginFrame(String title) {
		initContainer();
		setTitle(title);
		setSize(700, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}

	private void initContainer() {
		setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("src/images/login1.png"));
		add(background);
		background.setLayout(null);
		initlabels();
		initTextFieldsLogin();
		initButtons();
		background.add(login);
		background.add(motDePass);

		background.add(errorLabelLogin);
		background.add(errorLabelMdp);
		background.add(errorLogin);

		background.add(textFieldLogin);
		background.add(textFieldMotDePass);

		background.add(LoginBtn);
		background.add(hideshowpass);

		initActions();
	}

	public void initlabels() {
		login = new Labels("Login:", Color.DARK_GRAY, new Font("Baskerville Old Face", Font.PLAIN, 20));
		login.setLocation(400, 100);
		login.setSize(150, 20);

		motDePass = new Labels("Mot de passe:", Color.DARK_GRAY, new Font("Baskerville Old Face", Font.PLAIN, 20));
		motDePass.setLocation(400, 225);
		motDePass.setSize(150, 20);

		errorLabelLogin = new Labels("*Login obligatoire", Color.red, new Font("Arial", Font.ITALIC, 17));
		errorLabelLogin.setSize(150, 20);
		errorLabelLogin.setLocation(400, 200);
		errorLabelLogin.setVisible(false);

		errorLabelMdp = new Labels("*Mot de passe obligatoire", Color.red, new Font("Arial", Font.ITALIC, 17));
		errorLabelMdp.setSize(300, 20);
		errorLabelMdp.setLocation(400, 325);
		errorLabelMdp.setVisible(false);

		errorLogin = new Labels("Login ou mot de passe incorrect!!!", Color.red, new Font("Arial", Font.ITALIC, 17));
		errorLogin.setSize(300, 20);
		errorLogin.setLocation(400, 360);
		errorLogin.setVisible(false);
	}

	public void initTextFieldsLogin() {

		textFieldLogin = new HintTextField("Login ");
		textFieldLogin.setLocation(400, 150);
		textFieldLogin.setSize(225, 35);

		textFieldMotDePass = new JPasswordField();
		textFieldMotDePass.setLocation(400, 275);
		textFieldMotDePass.setSize(225, 35);
		textFieldMotDePass.setEchoChar('*');
		textFieldMotDePass.setFont(textFieldLogin.getFont());

		hideshowpass = new JCheckBoxIcon(new ImageIcon("src/images/icons/hide.png"),
				new ImageIcon("src/images/icons/show.png"));
		hideshowpass.setLocation(627, 275);
		hideshowpass.setSize(40, 29);

	}

	public void initButtons() {
		LoginBtn = new JButton("Se connecter");
		LoginBtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		LoginBtn.setBackground(new Color(0, 51, 102));
		LoginBtn.setForeground(Color.white.brighter());
		LoginBtn.setSize(225, 35);
		LoginBtn.setLocation(400, 400);
		LoginBtn.setFocusable(false);
	}

	public void initActions() {

		LoginBtn.addActionListener(e -> {
			String login = textFieldLogin.getText();
			String pass = textFieldMotDePass.getText();

			ServiceAuth service = new ServiceAuth();
			service.seConnecter(login, pass);
			if (service.seConnecter(login, pass).isEmpty()) {
				if (ServiceAuth.getSession() instanceof Admin) {
					frame = new AdminFrame("Bank Manager Admin");
					setVisible(false);
				} else if (ServiceAuth.getSession() instanceof Client) {
					frame = new ClientFrame("Bank Manager Client");
					setVisible(false);
				} else
					errorLogin.setVisible(true);

			}
			if (login.equals("Login "))
				errorLabelLogin.setVisible(true);
			if (pass.equals(""))
				errorLabelMdp.setVisible(true);

		});
		textFieldMotDePass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					LoginBtn.doClick();
				}
			}
		});
		hideshowpass.addActionListener(e -> {
			if (hideshowpass.isSelected()) {
				textFieldMotDePass.setEchoChar((char) 0);
			} else
				textFieldMotDePass.setEchoChar('*');
		});
	}

}
