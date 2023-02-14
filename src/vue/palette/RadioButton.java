package vue.palette;

import java.awt.Font;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton {

	public RadioButton(String text1, String text2) {
		new JRadioButton(text1);
		setFont(new Font("Arial", Font.PLAIN, 15));
		setSelected(true);
		setSize(80, 20);
		setLocation(200, 200);

		new JRadioButton(text2);
		setFont(new Font("Arial", Font.PLAIN, 15));
		setSelected(false);
		setSize(80, 20);
		setLocation(275, 200);

	}

	public RadioButton(String text, int x, int y) {
		new JRadioButton(text);
		setFont(new Font("Arial", Font.PLAIN, 15));
		setSelected(true);
		setSize(80, 20);
		setLocation(x, y);

	}
}