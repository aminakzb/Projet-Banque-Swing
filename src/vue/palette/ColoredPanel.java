package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ColoredPanel extends JPanel {
	private JLabel textLabel, titleLabel;

	private void initLabel(String text) {
		textLabel = new Labels(text, Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
	}

	public void setTextLabel(String text) {
		textLabel.setText(text);
	}

	public ColoredPanel(String text, Color color) {
		setBackground(color);
		// setSize(200, 100);
		textLabel = new Labels(null, Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
		setTextLabel(text);
		textLabel.setSize(500, 60);
		// textLabel.setLocation(50, 300);
		add(textLabel, BorderLayout.CENTER);
	}

	public ColoredPanel(String title, String text, Color color) {
		titleLabel = new Labels(title, Color.DARK_GRAY, new Font("Segoe UI Black", Font.BOLD, 20));
		setBackground(color);
		initLabel(text);

		Font font = titleLabel.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		titleLabel.setFont(font.deriveFont(attributes));

		add(titleLabel, BorderLayout.NORTH);
		add(textLabel, BorderLayout.CENTER);
	}

	public ColoredPanel(String title, JList list, Color color) {
		titleLabel = new Labels(title, Color.DARK_GRAY, new Font("Segoe UI Black", Font.BOLD, 20));

		// underligned label
		Font font = titleLabel.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		titleLabel.setFont(font.deriveFont(attributes));

		setBackground(color);
		setLayout(new BorderLayout());
		add(titleLabel, BorderLayout.NORTH);
		add(list, BorderLayout.CENTER);

	}

	public ColoredPanel() {
	}

}
