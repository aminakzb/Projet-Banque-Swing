package vue.palette;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TableCrudPanel extends JPanel {

	private ClassLoader cl = getClass().getClassLoader();
	private JButton btn_add, btn_edit, btn_remove;

	public JButton deleteBtn() {
		return btn_remove;
	}

	public JButton editBtn() {
		return btn_edit;
	}

	public JButton addBtn() {
		return btn_add;
	}

	private void initButtons() {

		btn_add = new JButton(new ImageIcon("src/images/crud/add.png"));
		btn_add.setOpaque(false);
		btn_add.setContentAreaFilled(false);
		btn_add.setBorderPainted(false);
		btn_add.setFocusable(false);

		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_add.setIcon(new ImageIcon("src/images/crud/addH.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_add.setIcon(new ImageIcon("src/images/crud/add.png"));

			}
		});

		btn_edit = new JButton(new ImageIcon("src/images/crud/edit.png"));
		btn_edit.setOpaque(false);
		btn_edit.setContentAreaFilled(false);
		btn_edit.setBorderPainted(false);
		btn_edit.setFocusable(false);
		btn_edit.setFocusable(false);

		btn_edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_edit.setIcon(new ImageIcon("src/images/crud/editHover.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_edit.setIcon(new ImageIcon("src/images/crud/edit.png"));

			}
		});

		btn_remove = new JButton(new ImageIcon("src/images/crud/delete.png"));
		btn_remove.setOpaque(false);
		btn_remove.setContentAreaFilled(false);
		btn_remove.setBorderPainted(false);
		btn_remove.setFocusable(false);

		btn_remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_remove.setIcon(new ImageIcon("src/images/crud/deleteHover.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_remove.setIcon(new ImageIcon("src/images/crud/delete.png"));

			}
		});
	}

	public TableCrudPanel() {

		initButtons();
		setLayout(new FlowLayout());
		setBackground(Color.white);
		add(btn_add);
		add(btn_edit);
		add(btn_remove);
	}

}
