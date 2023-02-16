package vue.palette;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JCheckBox;

public class JCheckBoxIcon extends JCheckBox {

	public JCheckBoxIcon(Icon icon1, Icon icon2) {
		setBackground(Color.white);
		setSelected(false);
		setIcon(icon1);
		setSelectedIcon(icon2);
	}
}
