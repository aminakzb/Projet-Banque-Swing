package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import dao.daoFiles.AgenceDao;
import dao.daoFiles.ClientDao;
import presentation.modele.Sexe;

public class TablePanelAgence extends JPanel {

	private JTable table;
	private static TableModel tableModel;
	private JScrollPane scrollPane;
	private SearchPanel searchPanel;
	private static long idClient;

	public static long getIdClient() {
		return idClient;
	}

	public static void setIdClient(long idClient) {
		TablePanelAgence.idClient = idClient;
	}

	Sexe sexe;
	public static int row;

	public JTable getTable() {
		return table;
	}

	private void initColDataAgence() {
		getTableModel().initColumns("ID", "Nom agence ", "Email agence", "Tel agence", "Adresse agence");
		getTableModel().initAgenciesData(new AgenceDao().findAll());
	}

	private void initTable() {

		tableModel = new TableModel();

		initColDataAgence();

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

		searchPanel.getCrudPanel().addBtn().setVisible(false);
		searchPanel.getCrudPanel().editBtn().setVisible(false);
		searchPanel.getCrudPanel().deleteBtn().setVisible(false);

		searchPanel.getTxt_search().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchPanel.getBtn_search().doClick();
				}
			}
		});
		searchPanel.getBtn_search().addActionListener(e -> {
			String keyword = searchPanel.getTxt_search().getText();

			var clients = new ClientDao().findByKeywordLike(keyword);

			getTableModel().initClientsData(clients);

		});

	}

	public TablePanelAgence() {

		initTable();
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(searchPanel, BorderLayout.SOUTH);
	}

	public static TableModel getTableModel() {
		return tableModel;
	}

}
