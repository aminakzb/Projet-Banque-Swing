package vue.palette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	private Color backgroundColor;
	private int cornerRadius = 15;
	private JLabel textLabel;

	public RoundedPanel(LayoutManager layout, int radius) {
		super(layout);
		cornerRadius = radius;
	}

	public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
		super(layout);
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	public RoundedPanel(int radius) {
		super();
		cornerRadius = radius;
	}

	public RoundedPanel(int radius, Color bgColor) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	public RoundedPanel(int radius, Color bgColor, String text) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
		initLabel(text);
		add(textLabel, BorderLayout.CENTER);
	}

	public RoundedPanel(int radius, Color bgColor, Icon icon, JLabel Label1, String text) {
		super();

		JLabel iconlabel = new JLabel(icon);
		add(iconlabel);
		JPanel p = new JPanel();
		p.setBackground(bgColor);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(Label1);
		initLabel(text);
		p.add(textLabel, BorderLayout.EAST);
		add(p);
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	private void initLabel(String text) {
		textLabel = new Labels(text, Color.DARK_GRAY, new Font("Segoe UI Black", Font.ITALIC, 20));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (backgroundColor != null) {
			graphics.setColor(backgroundColor);
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
		graphics.setColor(getBackground());
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
	}
}
