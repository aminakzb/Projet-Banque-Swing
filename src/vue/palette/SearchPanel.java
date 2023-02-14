package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SearchPanel extends JPanel {
	ClassLoader cl = getClass().getClassLoader();
	private JButton btn_search;
	private HintTextField txt_search;
	private Color bgColor;

	private TableCrudPanel crudButtonsPanel;

	public TableCrudPanel getCrudPanel() {
		return crudButtonsPanel;
	}

	public JButton getBtn_search() {
		return btn_search;
	}

	public HintTextField getTxt_search() {
		return txt_search;
	}

	private void initButton() {
		btn_search = new JButton(new ImageIcon("src/images/crud/search.png"));
		btn_search.setBorderPainted(false);
		btn_search.setOpaque(false);
		btn_search.setContentAreaFilled(false);

		btn_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_search.setIcon(new ImageIcon("src/images/crud/searchHover.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_search.setIcon(new ImageIcon("src/images/crud/search.png"));

			}
		});
	}

	private void initTextField() {

		txt_search = new HintTextField("mot-clé", new Color(70, 130, 180), Color.DARK_GRAY);
		txt_search.setHorizontalAlignment(SwingConstants.CENTER);
		txt_search.setPreferredSize(new Dimension(200, 25));
		txt_search.setMaximumSize(new Dimension(200, 45));
		txt_search.setBackground(new Color(204, 229, 255));
	}

	private void initComponents() {
		initTextField();
		initButton();
		crudButtonsPanel = new TableCrudPanel();
	}

	public SearchPanel(Color bgColor) {

		this.bgColor = bgColor;
		initComponents();
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		pane.add(btn_search);
		pane.add(txt_search);
		pane.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		pane.setBackground(Color.white);

		// setLayout(new FlowLayout(FlowLayout.RIGHT));
		setLayout(new BorderLayout());
		setBackground(bgColor);
		setBorder(new EmptyBorder(5, 5, 5, 25));
		// setBorder(new RoundedBorder(true, Color.blue, 15));

		add(pane, BorderLayout.EAST);
		add(crudButtonsPanel, BorderLayout.WEST);
	}

}
