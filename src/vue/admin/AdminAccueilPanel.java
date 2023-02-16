package vue.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metier.admin.ServiceAdmin;
import presentation.modele.TableauDeBord;
import vue.palette.HeaderPanel;
import vue.palette.Labels;
import vue.palette.RoundedPanel;
import vue.palette.SideMenuPanel;

public class AdminAccueilPanel extends JPanel {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	HeaderPanel header;
	SideMenuPanel menuPanel;
	private JPanel maxSolde, minSolde, totalCompte, totalClient, clientRiche, totalFemme, totalHomme, panel;
	private String maxSoldeValue, minSoldeValue, totalCompteValue, totalClientValue, clientRicheValue, totalFemmeValue,
			totalHommeValue;
	private JLabel maxSoldelbl, minSoldelbl, totalComptelbl, totalClientlbl, clientRichelbl, totalFemmelbl,
			totalHommelbl;
	private GridLayout gridPanelsLayout;
	private static TableauDeBord tableauDeBord;

	public TableauDeBord getTableauDeBord() {
		return tableauDeBord;
	}

	public static void setTableauDeBord(TableauDeBord tableauDeBord) {
		AdminAccueilPanel.tableauDeBord = tableauDeBord;
	}

	private void initMiniColoredPanel() {

		initLabels();
		maxSolde = new RoundedPanel(20, new Color(204, 153, 255), new ImageIcon("src/images/icons/maxicon.png"),
				maxSoldelbl, maxSoldeValue);
		maxSolde.setOpaque(false);

		minSolde = new RoundedPanel(20, new Color(204, 255, 204), new ImageIcon("src/images/icons/minicon.png"),
				minSoldelbl, minSoldeValue);
		minSolde.setOpaque(false);

		totalCompte = new RoundedPanel(20, new Color(255, 255, 204), new ImageIcon("src/images/icons/iconCompte.png"),
				totalComptelbl, totalCompteValue);
		totalCompte.setOpaque(false);

		totalClient = new RoundedPanel(20, new Color(153, 153, 255), new ImageIcon("src/images/icons/client.png"),
				totalClientlbl, totalClientValue);
		totalClient.setOpaque(false);

		clientRiche = new RoundedPanel(20, new Color(204, 255, 255), new ImageIcon("src/images/icons/riche.png"),
				clientRichelbl, clientRicheValue);
		clientRiche.setOpaque(false);

		totalFemme = new RoundedPanel(20, new Color(229, 204, 255), new ImageIcon("src/images/icons/femme.png"),
				totalFemmelbl, totalFemmeValue);
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

	private void initLabels() {

		new ServiceAdmin().calculerEtAfficherStatistiques();
		maxSoldelbl = new Labels(Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		maxSoldelbl.setText("Solde Max ");
		maxSoldeValue = tableauDeBord.getMaxSolde().toString();

		minSoldelbl = new Labels(Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		minSoldelbl.setText("Solde Min ");
		minSoldeValue = tableauDeBord.getMinSolde().toString();

		totalComptelbl = new Labels(Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		totalComptelbl.setText("Total Comptes ");
		totalCompteValue = tableauDeBord.getNombreTotaleCompte().toString();

		totalClientlbl = new Labels(Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		totalClientlbl.setText("Total Clients ");
		totalClientValue = tableauDeBord.getNombreTotaleClient().toString();

		clientRichelbl = new Labels(Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		clientRichelbl.setText("Client puls riche ");
		clientRicheValue = tableauDeBord.getNomClientLePlusRiche();

		totalFemmelbl = new Labels(Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		totalFemmelbl.setText("Total femme ");
		totalFemmeValue = tableauDeBord.getTotalClientsFemme().toString();

		totalHommelbl = new Labels(Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		totalHommelbl.setText("Total homme ");
		totalHommeValue = tableauDeBord.getTotaleClientsHomme().toString();
	}

	private void initContainer() {

		initGridOfPanels();

		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);

	}

	public AdminAccueilPanel() {

		initContainer();
		setLocation(0, 0);
		setSize(screenSize.width, screenSize.height - 30);
		setVisible(true);
	}

}
