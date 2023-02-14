package vue.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import dao.daoFiles.LogDao;
import metier.authentification.ServiceAuth;
import metier.clients.ServiceClient;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Log;
import vue.LoginFrame;
import vue.admin.AdminAccueilFrame;
import vue.palette.ColoredPanel;
import vue.palette.HeaderPanel;
import vue.palette.RetirerPanel;
import vue.palette.SideMenuPanel;
import vue.palette.TablePanel;
import vue.palette.VerserPanel;
import vue.palette.VirementPanel;

public class ClientAccueilFrame extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Container container;
	TablePanel tablePanel;
	HeaderPanel header;
	SideMenuPanel menuPanel;
	JPanel panelCompte, paneloperations, mainPanel, traitement;
	static boolean tableLog;
	JList<String> detailsComptes, operations;
	JTabbedPane onglets;
	DefaultListModel<String> model = new DefaultListModel<>();
	DefaultListModel<String> modelLogs = new DefaultListModel<>();
	JPanel leftPanel = new JPanel(new BorderLayout(0, 15));
	JPanel rightPanel = new JPanel(new BorderLayout());
	VerserPanel verserPanel;
	JPanel retirerPanel, virementPanel;
	JComboBox choiceLastOperations;
	Vector<String> dateOperations = new Vector<String>();
	JScrollPane scroll;
	JMenuBar menubar;

	public static boolean isTableLog() {
		return tableLog;
	}

	public static void setTableLog(boolean tableLog) {
		ClientAccueilFrame.tableLog = tableLog;
	}

	private void initListCompte() {
		List<Compte> mesComptes = new ServiceClient().mesComptes();
		for (int i = 0; i < mesComptes.size(); i++) {
			model.addElement(mesComptes.get(i).toString());
		}
	}

	private void initListLogsWeek() {
		List<Log> logs = new LogDao().findLogsOfLastWeek((Client) ServiceAuth.getSession());
		modelLogs.clear();
		for (int i = 0; i < logs.size(); i++) {
			modelLogs.addElement(logs.get(i).toString());
		}

	}

	private void initListLogsMonth() {
		List<Log> logs = new LogDao().findLogsOfLastMonth((Client) ServiceAuth.getSession());
		modelLogs.clear();
		for (int i = 0; i < logs.size(); i++) {
			modelLogs.addElement(logs.get(i).toString());
		}

	}

	private void initDetailsMesComptes() {
		initListCompte();
		detailsComptes = new JList<>(model);
		detailsComptes.setFixedCellHeight(40);
		detailsComptes.setFont(new Font("Optima", Font.ITALIC, 15));
		detailsComptes.setSelectionForeground(Color.DARK_GRAY);
		detailsComptes.setSelectionBackground(new Color(204, 229, 255));
	}

	private void initTabbedPanes() {
		verserPanel = new VerserPanel();
		retirerPanel = new RetirerPanel();
		virementPanel = new VirementPanel();
		onglets = new JTabbedPane();
		onglets.add("Versement", verserPanel);
		onglets.add("Retrait", retirerPanel);
		onglets.add("Virement", virementPanel);
		onglets.setBorder(new EmptyBorder(20, 50, 20, 20));
		onglets.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		onglets.setBackground(Color.white);
	}

	private void initLeftPanel() {
		panelCompte = new ColoredPanel("Mes Comptes", detailsComptes, Color.white);
		panelCompte.setBorder(new EmptyBorder(20, 20, 40, 70));

		initOperationsPanel();
		leftPanel.add(panelCompte, BorderLayout.NORTH);
		leftPanel.add(paneloperations, BorderLayout.CENTER);
		leftPanel.setBorder(new EmptyBorder(20, 20, 20, 0));
	}

	private void initOperationsPanel() {

		initListLogsWeek();
		operations = new JList<>(modelLogs);
		operations.setFixedCellHeight(40);
		operations.setFont(new Font("Optima", Font.ITALIC, 15));
		operations.setSelectionForeground(Color.DARK_GRAY);
		operations.setSelectionBackground(new Color(204, 229, 255));

		paneloperations = new ColoredPanel("Dernières opérations", operations, Color.white);
		paneloperations.setBorder(new EmptyBorder(20, 20, 20, 70));

		dateOperations.add("Dernière semaine");
		dateOperations.add("Dernier mois");
		choiceLastOperations = new JComboBox<String>(dateOperations);
		choiceLastOperations.setLocation(100, 100);
		choiceLastOperations.setSize(250, 20);
		choiceLastOperations.setFont(new Font("Arial", Font.ITALIC, 15));

		choiceLastOperations.addActionListener(e -> {
			String choix = (String) choiceLastOperations.getSelectedItem();
			switch (choix) {
			case "Dernière semaine": {
				initListLogsWeek();
				break;
			}
			case "Dernier mois": {
				initListLogsMonth();
				break;
			}
			}
		});
		scroll = new JScrollPane(operations);
		paneloperations.add(choiceLastOperations, BorderLayout.SOUTH);
		paneloperations.add(scroll);
	}

	private void initMenubar() {
		menubar = new JMenuBar();
		JMenu menu = new JMenu();
		menu.setIcon(new ImageIcon("src/images/icons/avatar.png"));
		JMenuItem firstelement = new JMenuItem("Se déconnecter");
		firstelement.addActionListener(e -> {
			setVisible(false);
			new LoginFrame("MyBank Manager Login");
		});
		menu.add(firstelement);
		menubar.add(menu);
		menubar.setSize(30, 30);
	}

	private void initPanels() {
		menuPanel = new SideMenuPanel("Accueil", "Historique");

		Font logoFont = new Font("Optima", Font.BOLD, 15);

		header = new HeaderPanel(new Color(204, 229, 255), null, null, Color.BLACK, logoFont,
				new ImageIcon("src/images/icons/menu.png"), "", Color.BLACK, logoFont);
		header.addClientInfo((Client) ServiceAuth.getSession());

		initMenubar();
		header.add(menubar, BorderLayout.LINE_END);

		initDetailsMesComptes();
		mainPanel = new JPanel(new BorderLayout());
		initTabbedPanes();
		initLeftPanel();
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(onglets, BorderLayout.CENTER);
		initActions();
	}

	private void initContainer() {
		initPanels();
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(menuPanel, BorderLayout.WEST);
		container.add(header, BorderLayout.NORTH);
		container.add(mainPanel, BorderLayout.CENTER);
	}

	private void initActions() {

		var buttons = menuPanel.getButtons();

		buttons.get("Accueil").addActionListener(click -> {
			new ClientAccueilFrame("Accueil");
			setVisible(false);
		});
		buttons.get("Historique").addActionListener(click -> {
			AdminAccueilFrame.setOtherTablesFalse();
			ClientAccueilFrame.setTableLog(true);
			new ClientFrame("Historique");
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
		buttons.get("Historique").addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttons.get("Historique").setOpaque(true);
				buttons.get("Historique").setBackground(new Color(0, 153, 153));
				buttons.get("Historique").setFont(new Font("Baskerville Old Face", Font.BOLD, 24));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttons.get("Historique").setForeground(Color.DARK_GRAY);
				buttons.get("Historique").setOpaque(false);
				buttons.get("Historique").setFont(new Font("Baskerville Old Face", Font.ITALIC, 24));

			}
		});
		header.getToggleMenu().addActionListener(e -> {

			if (menuPanel.isVisible())
				menuPanel.setVisible(false);
			else
				menuPanel.setVisible(true);
		});

	}

	public ClientAccueilFrame(String title) {

		initContainer();
		setTitle(title);
		setLocation(0, 0);
		setSize(screenSize.width, screenSize.height - 30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
