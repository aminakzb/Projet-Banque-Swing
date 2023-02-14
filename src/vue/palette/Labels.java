package vue.palette;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Labels extends JLabel {

	public Labels(String text) {
		setText(text);
		setFont(new Font("Arial", Font.PLAIN, 20));
		setSize(80, 20);
		setForeground(Color.DARK_GRAY);
	}

	public Labels(String text, int x, int y) {
		setText(text);
		setFont(new Font("Arial", Font.PLAIN, 18));
		setSize(200, 18);
		setForeground(Color.DARK_GRAY);
		setLocation(x, y);
	}

	public Labels(String text, Color color, Font font) {
		setText(text);
		setFont(font);
		setForeground(color);
	}

}
