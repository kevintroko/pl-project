package GraphicComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Colors;
import Main.Fonts;

public class CustomPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CustomText textField;
	private JLabel label;

	private String description;
	private int height;

	public CustomPanel(String description) {
		setBackground(Colors.blue);
		this.description = description;
		setLayout(null);
		loadFormField();
	}

	private void loadFormField() {
		removeAll();
		height = this.getHeight() - 4;
		textField = new CustomText();
		textField.setBounds(2, 2, getWidth() - 4, height);
		textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		textField.setFont(Fonts.helv_15);
		
		// Font color
		textField.setForeground(Colors.dark_gray);
		textField.setPlaceholder(description);
		add(textField);

		label = new JLabel();
		label.setBounds(getWidth() - height, 2, height, height);
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
	}

	@Override
	public void setFont(Font font) {
		if (textField != null)
			textField.setFont(font);
	}

	public String getText() {
		return textField.getText();
	}

	public void setText(String newText) {
		textField.setText(newText);
	}

	@Override
	public void setForeground(Color color) {
		if (textField != null)
			textField.setForeground(color);
	}

	@Override
	public void setBounds(Rectangle bounds) {
		super.setBounds(bounds);
		loadFormField();
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		loadFormField();
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		loadFormField();
	}

	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		loadFormField();
	}

}