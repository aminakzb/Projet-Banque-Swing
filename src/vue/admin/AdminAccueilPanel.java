package vue.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metier.admin.ServiceAdmin;
import presentation.modele.TableauDeBord;
import vue.palette.HeaderPanel;
import vue.palette.RoundedPanel;
import vue.palette.SideMenuPanel;

public class AdminAccueilPanel extends JPanel {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	HeaderPanel header;
	SideMenuPanel menuPanel;
	private JPanel maxSolde, minSolde, totalCompte, totalClient, clientRiche, totalFemme, totalHomme, panel;
	private String maxSoldeValue, minSoldeValue, totalCompteValue, totalClientValue, clientRicheValue, totalFemmeValue,
			totalHommeValue;
	private GridLayout gridPanelsLayout;
	private static TableauDeBord tableauDeBord;

	public TableauDeBord getTableauDeBord() {
		return tableauDeBord;
	}

	public static void setTableauDeBord(TableauDeBord tableauDeBord) {
		AdminAccueilPanel.tableauDeBord = tableauDeBord;
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
