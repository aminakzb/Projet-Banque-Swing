package vue.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
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
import vue.palette.ColoredPanel;
import vue.palette.HeaderPanel;
import vue.palette.RetirerPanel;
import vue.palette.SideMenuPanel;
import vue.palette.TablePanelCompte;
import vue.palette.VerserPanel;
import vue.palette.VirementPanel;

public class ClientAccueilPanel extends JPanel {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Container container;
	TablePanelCompte tablePanelCompte;
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

	public static boolean isTableLog() {
		return tableLog;
	}

	public static void setTableLog(boolean tableLog) {
		ClientAccueilPanel.tableLog = tableLog;
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

	private void initPanels() {

		initDetailsMesComptes();
		mainPanel = new JPanel(new BorderLayout());
		initTabbedPanes();
		initLeftPanel();
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(onglets, BorderLayout.CENTER);

	}

	private void initContainer() {
		initPanels();
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}

	public ClientAccueilPanel() {

		initContainer();
		setLocation(0, 0);
		setSize(screenSize.width, screenSize.height - 30);
		setVisible(true);
	}

}
