package vue.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metier.admin.ServiceAdmin;
import presentation.modele.TableauDeBord;
import vue.client.ClientAccueilFrame;
import vue.palette.HeaderPanel;
import vue.palette.RoundedPanel;
import vue.palette.SideMenuPanel;

public class AdminAccueilFrame extends JFrame {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Container container;
	HeaderPanel header;
	SideMenuPanel menuPanel;
	private JPanel maxSolde, minSolde, totalCompte, totalClient, clientRiche, totalFemme, totalHomme, panel;
	private String maxSoldeValue, minSoldeValue, totalCompteValue, totalClientValue, clientRicheValue, totalFemmeValue,
			totalHommeValue;
	private GridLayout gridPanelsLayout;
	private static TableauDeBord tableauDeBord;
	static boolean tableClient;
	static boolean tableCompte;
	static boolean tableAgence;

	public TableauDeBord getTableauDeBord() {
		return tableauDeBord;
	}

	public static void setTableauDeBord(TableauDeBord tableauDeBord) {
		AdminAccueilFrame.tableauDeBord = tableauDeBord;
	}

	public static boolean isTableClient() {
		return tableClient;
	}

	public static void setTableClient(boolean tableClient) {
		AdminAccueilFrame.tableClient = tableClient;
	}

	public static boolean isTableCompte() {
		return tableCompte;
	}

	public static void setTableCompte(boolean tableCompte) {
		AdminAccueilFrame.tableCompte = tableCompte;
	}

	public static boolean isTableAgence() {
		return tableAgence;
	}

	public static void setTableAgence(boolean tableAgence) {
		AdminAccueilFrame.tableAgence = tableAgence;
	}

	public static void setOtherTablesFalse() {
		setTableCompte(false);
		setTableClient(false);
		setTableAgence(false);
		ClientAccueilFrame.setTableLog(false);
	}

	private void initPanels() {
		menuPanel = new SideMenuPanel("Accueil", "Client", "Compte", "Agence");

		Font logoFont = new Font("Optima", Font.BOLD, 15);

		header = new HeaderPanel(new Color(204, 229, 255), null, "MBank", Color.BLACK, logoFont,
				new ImageIcon("src/images/icons/menu.png"), "", Color.BLACK, logoFont);
		header.addText("Session Admin");
		header.addbtnLogout(this);
		initActions();
	}

	private void initMiniColoredPanel() {

		initStrings();
		maxSolde = new RoundedPanel(10, new Color(204, 153, 255), maxSoldeValue);
		maxSolde.setOpaque(false);

		minSolde = new RoundedPanel(10, new Color(204, 255, 204), minSoldeValue);
		minSolde.setOpaque(false);

		totalCompte = new RoundedPanel(10, new Color(255, 255, 204), totalCompteValue);
		totalCompte.setOpaque(false);

		totalClient = new RoundedPanel(10, new Color(153, 153, 255), totalClientValue);
		totalClient.setOpaque(false);

		clientRiche = new RoundedPanel(10, new Color(204, 255, 255), clientRicheValue);
		clientRiche.setOpaque(false);

		totalFemme = new RoundedPanel(10, new Color(229, 204, 255), totalFemmeValue + totalHommeValue);
		totalFemme.setOpaque(false);

	}

	private void initGridOfPanels() {
		initMiniColoredPanel();
		gridPanelsLayout = new GridLayout(2, 3, 40, 50);
		panel = new JPanel();
		panel.setLayout(gridPanelsLayout);
		panel.setBorder(new EmptyBorder(20, 20, 200, 20));
		panel.add(maxSolde);
		panel.add(minSolde);
		panel.add(totalCompte);
		panel.add(totalClient);
		panel.add(clientRiche);
		panel.add(totalFemme);
		// panel.add(totalHomme);

	}

	private void initStrings() {

		new ServiceAdmin().calculerEtAfficherStatistiques();
		maxSoldeValue = "Solde Max " + tableauDeBord.getMaxSolde().toString();
		minSoldeValue = "Solde Min " + tableauDeBord.getMinSolde().toString();
		totalCompteValue = "Total Comptes " + tableauDeBord.getNombreTotaleCompte().toString();
		totalClientValue = "Total Clients " + tableauDeBord.getNombreTotaleClient().toString();
		clientRicheValue = "Client puls riche " + tableauDeBord.getNomClientLePlusRiche();
		totalFemmeValue = "Total femme " + tableauDeBord.getTotalClientsFemme().toString();
		totalHommeValue = " ET  Total homme " + tableauDeBord.getTotaleClientsHomme().toString();
	}

	private void initContainer() {
		initPanels();
		initGridOfPanels();
		container = getContentPane();

		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);
		container.add(menuPanel, BorderLayout.WEST);
		container.add(header, BorderLayout.NORTH);

	}

	private void initActions() {

		var buttons = menuPanel.getButtons();

		buttons.get("Accueil").addActionListener(click -> {
			new AdminAccueilFrame("Accueil");
			setVisible(false);
		});

		buttons.get("Client").addActionListener(click -> {
			setOtherTablesFalse();
			setTableClient(true);
			new MyFrame("MyBank Manager Clients");
			setVisible(false);
		});

		buttons.get("Compte").addActionListener(click -> {
			setOtherTablesFalse();
			setTableCompte(true);
			new MyFrame("MyBank Manager Accounts");
			setVisible(false);
		});
		buttons.get("Agence").addActionListener(click -> {
			setOtherTablesFalse();
			setTableAgence(true);
			new MyFrame("MyBank Manager Agencies");
			setVisible(false);
		});

		buttons.get("Accueil").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttons.get("Accueil").setOpaque(true);
				buttons.get("Accueil").setBackground(new Color(0, 153, 153));
				buttons.get("Accueil").setFont(new Font("Baskerville Old Face", Font.BOLD, 24));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttons.get("Accueil").setForeground(Color.DARK_GRAY);
				buttons.get("Accueil").setOpaque(false);
				buttons.get("Accueil").setFont(new Font("Baskerville Old Face", Font.ITALIC, 24));

			}
		});
		buttons.get("Client").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttons.get("Client").setOpaque(true);
				buttons.get("Client").setBackground(new Color(0, 153, 153));
				buttons.get("Client").setFont(new Font("Baskerville Old Face", Font.BOLD, 24));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttons.get("Client").setForeground(Color.DARK_GRAY);
				buttons.get("Client").setOpaque(false);
				buttons.get("Client").setFont(new Font("Baskerville Old Face", Font.ITALIC, 24));

			}
		});

		buttons.get("Compte").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				buttons.get("Compte").setOpaque(true);
				buttons.get("Compte").setBackground(new Color(0, 153, 153));
				buttons.get("Compte").setFont(new Font("Baskerville Old Face", Font.BOLD, 24));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttons.get("Compte").setForeground(Color.DARK_GRAY);
				buttons.get("Compte").setOpaque(false);
				buttons.get("Compte").setFont(new Font("Baskerville Old Face", Font.ITALIC, 24));

			}
		});
		buttons.get("Agence").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				buttons.get("Agence").setOpaque(true);
				buttons.get("Agence").setBackground(new Color(0, 153, 153));
				buttons.get("Agence").setFont(new Font("Baskerville Old Face", Font.BOLD, 24));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttons.get("Agence").setForeground(Color.DARK_GRAY);
				buttons.get("Agence").setOpaque(false);
				buttons.get("Agence").setFont(new Font("Baskerville Old Face", Font.ITALIC, 24));

			}
		});
		header.getToggleMenu().addActionListener(e -> {

			if (menuPanel.isVisible())
				menuPanel.setVisible(false);
			else
				menuPanel.setVisible(true);
		});

	}

	public AdminAccueilFrame(String title) {

		initContainer();
		setTitle(title);
		setLocation(0, 0);
		setSize(screenSize.width, screenSize.height - 30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AdminAccueilFrame("test admin");
	}
}
