package vue.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metier.authentification.ServiceAuth;
import presentation.modele.Client;
import vue.palette.HeaderPanel;
import vue.palette.SideMenuPanel;
import vue.palette.TablePanelLogs;

public class ClientFrame extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Container container;
	HeaderPanel header;
	SideMenuPanel menuPanel;
	TablePanelLogs tablePanelLogs;
	ClientAccueilPanel clientAccueil;
	JPanel cardsPanel;
	CardLayout layout;
	JMenuBar menubar;

	public HeaderPanel getHeader() {
		return header;
	}

	public void setHeader(HeaderPanel header) {
		this.header = header;
	}

	public SideMenuPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(SideMenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	private void initMenubar() {
		menubar = new JMenuBar();
		JMenu menu = new JMenu();
		menu.setIcon(new ImageIcon("src/images/icons/avatar.png"));
		JMenuItem firstelement = new JMenuItem("Se déconnecter");
		firstelement.addActionListener(e -> {
			setVisible(false);
			new ServiceAuth().SeDéconnecter();
		});
		menu.add(firstelement);
		menubar.add(menu);
		menubar.setSize(30, 30);
	}

	private void initPanels() {
		tablePanelLogs = new TablePanelLogs();
		tablePanelLogs.setBorder(new EmptyBorder(15, 15, 0, 15));

		menuPanel = new SideMenuPanel("Accueil", "Historique");

		Font logoFont = new Font("Optima", Font.BOLD, 15);

		header = new HeaderPanel(new Color(204, 229, 255), null, "MBank", Color.BLACK, logoFont,
				new ImageIcon("src/images/icons/menu.png"), "", Color.BLACK, logoFont);

		header.addClientInfo((Client) ServiceAuth.getSession());
		initMenubar();
		header.add(menubar, BorderLayout.LINE_END);

		clientAccueil = new ClientAccueilPanel();
		cardsPanel = new JPanel();
		layout = new CardLayout();
		initActions();
	}

	private void initContainer() {
		initPanels();
		container = getContentPane();
		container.setLayout(new BorderLayout());
		cardsPanel.setLayout(layout);
		cardsPanel.add(clientAccueil);
		container.add(cardsPanel, BorderLayout.CENTER);
		container.add(menuPanel, BorderLayout.WEST);
		container.add(header, BorderLayout.NORTH);
	}

	private void initActions() {

		var buttons = menuPanel.getButtons();
		buttons.get("Accueil").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.first(cardsPanel);
			}
		});
		buttons.get("Historique").addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardsPanel.add(tablePanelLogs);
				layout.last(cardsPanel);

			}
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

	public ClientFrame(String title) {

		initContainer();
		setTitle(title);
		setLocation(0, 0);
		setSize(screenSize.width, screenSize.height - 30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
