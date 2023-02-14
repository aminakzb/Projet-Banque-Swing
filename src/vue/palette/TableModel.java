package vue.palette;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import presentation.modele.Agence;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Log;

public class TableModel extends AbstractTableModel {

	private String[] columnsNames;
	private Object[][] data;

	public void initColumns(String... colNames) {
		columnsNames = new String[colNames.length];

		for (int i = 0; i < colNames.length; i++)
			columnsNames[i] = colNames[i];
	}

	public void initClientsData(List<Client> clients) {

		data = new Object[clients.size()][columnsNames.length];

		int i = 0;
		for (Client client : clients) {

			data[i][0] = client.getId();
			data[i][1] = client.getNom();
			data[i][2] = client.getPrenom();
			data[i][3] = client.getLogin();
			data[i][4] = client.getMotDePasse();
			data[i][5] = client.getCin();
			data[i][6] = client.getEmail();
			data[i][7] = client.getTel();
			data[i][8] = client.getSexe();

			i++;
		}

		this.fireTableDataChanged();
	}

	public void initComptesData(List<Compte> comptes) {

		data = new Object[comptes.size()][columnsNames.length];

		int i = 0;
		for (Compte compte : comptes) {

			data[i][0] = compte.getNumeroCompte();
			data[i][1] = compte.getDateCreation();
			data[i][2] = compte.getSolde();
			if (compte.getPropriétaire() != null)
				data[i][3] = compte.getPropriétaire().getId();
			else
				data[i][3] = "Archivé";
			i++;
		}

		this.fireTableDataChanged();
	}

	public void initLogsData(List<Log> logs) {

		data = new Object[logs.size()][columnsNames.length];

		int i = 0;
		for (Log log : logs) {

			data[i][0] = log.getNumCompteLog();
			data[i][1] = log.getDate();
			data[i][2] = log.getType();
			data[i][3] = log.getMessage();

			i++;
		}

		this.fireTableDataChanged();
	}

	public void initAgenciesData(List<Agence> agences) {

		data = new Object[agences.size()][columnsNames.length];

		int i = 0;
		for (Agence agence : agences) {

			data[i][0] = agence.getId();
			data[i][1] = agence.getNomAgence();
			data[i][2] = agence.getEmailAgence();
			data[i][3] = agence.getTelAgence();
			data[i][4] = agence.getAdresseAgence();
			i++;
		}

		this.fireTableDataChanged();
	}

	@Override
	public String getColumnName(int col) {
		return columnsNames[col];
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnsNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		data[rowIndex][columnIndex] = aValue;
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}

}
