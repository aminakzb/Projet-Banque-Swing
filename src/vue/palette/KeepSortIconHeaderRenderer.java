package vue.palette;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class KeepSortIconHeaderRenderer implements TableCellRenderer {

	private TableCellRenderer defaultRenderer;
	private Icon ascIcon = UIManager.getIcon("Table.ascendingSortIcon");
	private Icon desIcon = UIManager.getIcon("Table.descendingSortIcon");

	public KeepSortIconHeaderRenderer(TableCellRenderer defaultRenderer) {
		this.defaultRenderer = defaultRenderer;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component comp = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (comp instanceof JLabel) {
			JLabel label = (JLabel) comp;
			label.setBorder(BorderFactory.createEtchedBorder());
			// label.setBorder(null);
			JButton btn = new JButton(new ImageIcon("src/images/icons/sorticon.png"));
			btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.setBorderPainted(false);

			btn.setSize(15, 15);
			label.setLayout(null);
			label.add(btn);
			label.setHorizontalAlignment(SwingConstants.CENTER);

			label.setIcon(null);

		}

		return comp;
	}

}
