package vue.palette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SideMenuPanel extends JPanel {

	ClassLoader cl = getClass().getClassLoader();
	private LinkedHashMap<String, JButton> buttons;

	public LinkedHashMap<String, JButton> getButtons() {
		return buttons;
	}

	// ("Ajouter","Modifier","Supprimer","Chercher")
	private void initButtons(String... buttonsNames) {

		buttons = new LinkedHashMap<>();

		List<String> btnNames = new ArrayList<>(Arrays.asList(buttonsNames));

		btnNames.forEach(nameOfButton -> {

			JButton btn = new JButton(nameOfButton);
			btn.setFont(new Font("Baskerville Old Face", Font.ITALIC, 20));
			btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.setBorderPainted(false);
			btn.setHorizontalAlignment(JButton.CENTER);
			btn.setPreferredSize(new Dimension(250, 50));
			btn.setMaximumSize(new Dimension(250, 50));
			btn.setMargin(new Insets(5, 5, 5, 5));
			btn.setFocusable(false);
			// btn.setIcon(icon);
			// btn.setHorizontalTextPosition(JButton.CENTER);
			// btn.setVerticalTextPosition(JButton.BOTTOM);

			buttons.put(nameOfButton, btn);
		});

	}

	public SideMenuPanel(String... buttonsNames) {
		initButtons(buttonsNames);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(new EmptyBorder(15, 0, 0, 0));

		setBackground(new Color(204, 229, 255));

		buttons.forEach((names, btn) -> {

			add(btn);
		});

		setVisible(false);
	}
}
