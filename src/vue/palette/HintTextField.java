package vue.palette;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class HintTextField extends JTextField {

	Font gainFont = new Font("Candara", Font.ITALIC, 20);
	Font lostFont = new Font("Candara", Font.ITALIC, 18);

	public HintTextField(final String hint) {

		setText(hint);
		setFont(lostFont);
		setForeground(Color.GRAY);
		setSize(190, 30);
		setHorizontalAlignment(JTextField.LEFT);

		this.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(gainFont);
				} else {
					setText(getText());
					setFont(gainFont);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint) || getText().length() == 0) {
					setText(hint);
					setFont(lostFont);
					setForeground(Color.GRAY);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(Color.DARK_GRAY);
				}
			}
		});

	}

	public HintTextField(final String hint, Color gainColor, Color lostColor) {

		setText(hint);
		setFont(lostFont);
		setForeground(lostColor);
		setHorizontalAlignment(JTextField.CENTER);

		this.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(gainFont);
					setForeground(gainColor);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(gainColor);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint) || getText().length() == 0) {
					setText(hint);
					setFont(lostFont);
					setForeground(lostColor);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(gainColor);
				}
			}
		});

	}

	public HintTextField(final String hint, Font f, Color textColor, Color backColor, boolean opaque) {

		setText(hint);
		setFont(f);
		setForeground(textColor);
		setBackground(backColor);
		setOpaque(opaque);

		this.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(f);
				} else {
					setText(getText());
					setFont(f);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint) || getText().length() == 0) {
					setText(hint);
					setFont(lostFont);
					// setForeground(Color.GRAY);
					setForeground(textColor);
				} else {
					setText(getText());
					setFont(f);
					setForeground(Color.blue);
				}
			}
		});

	}

	public HintTextField(String hint, int x, int y) {

		setText(hint);
		setFont(lostFont);
		setLocation(x, y);
		setForeground(Color.GRAY);
		setSize(190, 30);
		setHorizontalAlignment(JTextField.CENTER);

	}

}
