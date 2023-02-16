package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import dao.daoFiles.CompteDao;
import presentation.modele.Sexe;
import vue.admin.AddAccountFrame;

public class TablePanelCompte extends JPanel {

	private JTable table;
	private static TableModel tableModel;
	private JScrollPane scrollPane;
	private SearchPanel searchPanel;
	private static long idClient;

	public static long getIdClient() {
		return idClient;
	}

	public static void setIdClient(long idClient) {
		TablePanelCompte.idClient = idClient;
	}

	Sexe sexe;
	public static int row;

	public JTable getTable() {
		return table;
	}

	private void initColDataCompte() {
		getTableModel().initColumns("Id", "Date création", "Solde", "Id Client");
		getTableModel().initComptesData(new CompteDao().findAll());
	}

	private void initTable() {

		tableModel = new TableModel();

		initColDataCompte();

		table = new JTable(getTableModel()) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				Color color1 = new Color(224, 224, 224);
				Color color2 = Color.WHITE;
				if (!c.getBackground().equals(getSelectionBackground())) {
					Color coleur = (row % 2 == 0 ? color1 : color2);
					c.setBackground(coleur);
					coleur = null;
				}
				return c;
			}
		};
		table.setFont(new Font("Arial", Font.PLAIN, 17));
		table.setForeground(Color.BLACK);
		table.setRowHeight(40);
		Dimension dim = new Dimension(0, 10);
		table.setIntercellSpacing(new Dimension(dim));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(false);
		table.setAutoCreateRowSorter(true);

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Optima", Font.BOLD, 20));
		header.setForeground(new Color(0, 51, 102));
		header.setBackground(new Color(255, 255, 204));

		// ((DefaultTableCellRenderer)
		// header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		header.setDefaultRenderer(new KeepSortIconHeaderRenderer(header.getDefaultRenderer()));

		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

		scrollPane = new JScrollPane(table);

		searchPanel = new SearchPanel(Color.white);

		initActions();
	}

	private void initActions() {

		searchPanel.getCrudPanel().addBtn().addActionListener(e -> {

			new AddAccountFrame("Add Account Form");

		});
		searchPanel.getCrudPanel().editBtn().setVisible(false);
		searchPanel.getCrudPanel().deleteBtn().setVisible(false);
	}

	public TablePanelCompte() {

		initTable();
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(searchPanel, BorderLayout.SOUTH);
	}

	public static TableModel getTableModel() {
		return tableModel;
	}

}
