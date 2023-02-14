package vue.palette;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JCheckBox;

public class JCheckBoxIcon extends JCheckBox {

	public JCheckBoxIcon(Icon icon1, Icon icon2) {
		setBackground(Color.white);
		setSelected(false);
		// setIcon(new ImageIcon("src/images/icons/hide.png"));
		setIcon(icon1);
		// setSelectedIcon(new ImageIcon("src/images/icons/show.png"));
		setSelectedIcon(icon2);
	}
}
