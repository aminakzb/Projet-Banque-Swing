package vue.admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.daoFiles.ClientDao;
import dao.daoFiles.CompteDao;
import dao.daoFiles.FileBasePaths;
import metier.admin.ServiceAdmin;
import presentation.modele.Client;
import presentation.modele.Compte;
import vue.palette.HintTextField;
import vue.palette.Labels;

public class AddAccountFrame extends JFrame {

	private Container container;
	private JLabel numCompte, Solde, nCompteInit, soldeInit, propriétaire;
	private HintTextField textFieldPropriétaire;
	private JButton addButton;
	private JLabel erreurLabel;

	public HintTextField getTextFieldPropriétaire() {
		return textFieldPropriétaire;
	}

	public JLabel getnCompteInit() {
		return nCompteInit;
	}

	public JLabel getSoldeInit() {
		return soldeInit;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public AddAccountFrame(String title) {

		initContainer();
		setTitle(title);
		setSize(650, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private void initContainer() {
		container = getContentPane();
		container.setLayout(null);
		container.setBackground(new Color(255, 255, 204));

		initlabels(100);
		initTextFieldsAddAccount();
		initButtons();
		container.add(numCompte);
		container.add(Solde);
		container.add(propriétaire);
		container.add(nCompteInit);
		container.add(soldeInit);
		container.add(textFieldPropriétaire);
		container.add(erreurLabel);
		container.add(addButton);
		initActions();
	}

	public void initlabels(int x) {
		numCompte = new Labels("Num compte:", x, 100);
		Solde = new Labels("Solde initial:", x, 200);
		propriétaire = new Labels("Cin du Propriétaire:", x, 300);
		long idCompte = new CompteDao().getIncrementedIdByIndexFile(FileBasePaths.INDEX_ACCOUNT);
		nCompteInit = new Labels("b-co00" + idCompte, 300, 100);
		soldeInit = new Labels(new Compte().getSolde().toString(), 300, 200);
		erreurLabel = new Labels("Erreur,réessayez avec un autre CIN", Color.red, new Font("Arial", Font.ITALIC, 17));
		erreurLabel.setSize(300, 20);
		erreurLabel.setLocation(280, 340);
		erreurLabel.setVisible(false);
	}

	public void initTextFieldsAddAccount() {

		textFieldPropriétaire = new HintTextField("Propriétaire ");
		textFieldPropriétaire.setLocation(300, 300);
	}

	public void initButtons() {
		addButton = new JButton("Ajouter");
		addButton.setFont(new Font("Arial", Font.PLAIN, 15));
		addButton.setSize(150, 40);
		addButton.setForeground(Color.white);
		addButton.setBackground(new Color(0, 153, 153));
		addButton.setLocation(320, 500);
		addButton.setFocusable(false);
	}

	public void initActions() {
		addButton.addActionListener(e -> {
			String proprietaireCin = (String) textFieldPropriétaire.getText();
			Client clientProprietaire = new ClientDao().findByCin(proprietaireCin);
			Double solde = Double.parseDouble(soldeInit.getText());
			String numCompte = (String) nCompteInit.getText();
			ServiceAdmin service = new ServiceAdmin();

			if (clientProprietaire != null) {
				if (service.nouveauCompteClientExistant(numCompte, solde, clientProprietaire) != null)

					JOptionPane.showMessageDialog(this, "Le Compte " + numCompte + " a été ajouté avec succès",
							"I N F O", JOptionPane.INFORMATION_MESSAGE);
			} else
				erreurLabel.setVisible(true);
		});
		textFieldPropriétaire.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addButton.doClick();
				}
			}
		});
	}
}
