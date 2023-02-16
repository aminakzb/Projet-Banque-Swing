package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metier.authentification.ServiceAuth;
import presentation.modele.Client;
import presentation.modele.Sexe;

public class HeaderPanel extends JPanel {

	private JLabel lbl_logo;
	private JButton btn_toggleMenu;

	public JButton getToggleMenu() {
		return btn_toggleMenu;
	}

	private void initLabel(Icon icon, String text, Color color, Font font) {

		lbl_logo = new JLabel(text);
		lbl_logo.setIcon(icon);
		lbl_logo.setForeground(color);
		lbl_logo.setFont(font);
		lbl_logo.setHorizontalTextPosition(JLabel.CENTER);
		lbl_logo.setVerticalTextPosition(JLabel.BOTTOM);

	}

	private void initButton(Icon icon, String text, Color color, Font font) {

		btn_toggleMenu = new JButton(text);
		btn_toggleMenu.setIcon(icon);
		btn_toggleMenu.setForeground(color);
		btn_toggleMenu.setFont(font);
		btn_toggleMenu.setHorizontalTextPosition(JLabel.CENTER);
		btn_toggleMenu.setVerticalTextPosition(JLabel.BOTTOM);
		btn_toggleMenu.setOpaque(false);
		btn_toggleMenu.setContentAreaFilled(false);
		btn_toggleMenu.setBorderPainted(false);
		btn_toggleMenu.setFocusable(false);
	}

	public HeaderPanel(Color bgColor, Icon iconlbl, String textlbl, Color colorlbl, Font fontlbl, Icon iconbtn,
			String textbtn, Color colorbtn, Font fontbtn) {

		initLabel(iconlbl, textlbl, colorlbl, fontlbl);
		initButton(iconbtn, textbtn, colorbtn, fontbtn);
		setLayout(new BorderLayout());
		setBackground(bgColor);
		setBorder(new EmptyBorder(5, 20, 5, 20));

		add(btn_toggleMenu, BorderLayout.WEST);
		add(lbl_logo, BorderLayout.EAST);
	}

	public HeaderPanel(Color bgColor) {

		setLayout(new BorderLayout());
		setBackground(bgColor);
		setBorder(new EmptyBorder(5, 20, 5, 20));

		add(btn_toggleMenu, BorderLayout.WEST);
		add(lbl_logo, BorderLayout.EAST);
	}

	public void addClientInfo(Client user) {
		String civilité;
		if (user.getSexe() == Sexe.FEMME)
			civilité = "Mme ";
		else
			civilité = "M ";
		JLabel nom = new JLabel("Bonjour, " + civilité + user.getNomComplet());
		nom.setForeground(Color.DARK_GRAY);
		nom.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		add(nom, BorderLayout.CENTER);
	}

	public void addText(String text) {

		JLabel label = new JLabel(text);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		add(label, BorderLayout.CENTER);
	}

	public void addbtnLogout(JFrame frame) {

		JButton btn = new JButton("Se déconnecter");
		btn.setForeground(Color.DARK_GRAY);
		btn.setFont(new Font("Baskerville Old Face", Font.ITALIC, 18));
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusable(false);
		btn.addActionListener(e -> {
			frame.setVisible(false);
			new ServiceAuth().SeDéconnecter();
		});
		add(btn, BorderLayout.EAST);
	}

}
