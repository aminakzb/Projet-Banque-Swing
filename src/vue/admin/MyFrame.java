package vue.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import vue.palette.HeaderPanel;
import vue.palette.SideMenuPanel;
import vue.palette.TablePanel;

public class MyFrame extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Container container;
	TablePanel tablePanel;
	HeaderPanel header;
	SideMenuPanel menuPanel;

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

	private void initPanels() {
		tablePanel = new TablePanel();
		tablePanel.setBorder(new EmptyBorder(15, 15, 0, 15));
		menuPanel = new SideMenuPanel("Accueil", "Client", "Compte", "Agence");

		Font logoFont = new Font("Optima", Font.BOLD, 15);

		header = new HeaderPanel(new Color(204, 229, 255), null, "MBank", Color.BLACK, logoFont,
				new ImageIcon("src/images/icons/menu.png"), "", Color.BLACK, logoFont);
		header.addText("Session Admin");
		header.addbtnLogout(this);
		initActions();
	}

	private void initContainer() {
		initPanels();
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(tablePanel, BorderLayout.CENTER);
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
			AdminAccueilFrame.setOtherTablesFalse();
			AdminAccueilFrame.setTableClient(true);
			new MyFrame("MyBank Manager Clients");
			setVisible(false);
		});

		buttons.get("Compte").addActionListener(click -> {
			AdminAccueilFrame.setOtherTablesFalse();
			AdminAccueilFrame.setTableCompte(true);
			new MyFrame("MyBank Manager Accounts");
			setVisible(false);
		});
		buttons.get("Agence").addActionListener(click -> {
			AdminAccueilFrame.setOtherTablesFalse();
			AdminAccueilFrame.setTableAgence(true);
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

	public MyFrame(String title) {

		initContainer();
		setTitle(title);
		setLocation(0, 0);
		setSize(screenSize.width, screenSize.height - 30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
