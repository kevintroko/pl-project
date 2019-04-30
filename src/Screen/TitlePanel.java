package Screen;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Main.Colors;
import Main.Fonts;

/** @Author Kevin Cabrera */

public class TitlePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	// Provides a general purpose component for implementing divider lines
	JSeparator separator;

	// Create the panel for the title component
	public TitlePanel(Rectangle bounds) {
		setLayout(null);
		setBounds(bounds);
		setBackground(Colors.blue);
		loadComponents();
	}

	// Constructor
	public TitlePanel() {
		setLayout(null);
		loadComponents();
	}

	public void loadComponents() {
		int width = 650;
		int height = 100;
		int x = this.getWidth() / 2;

		// Title top
		JLabel jlTitle = new JLabel("Final Programming Language Project");
		jlTitle.setBounds(x + 70, -15, width, height);
		jlTitle.setFont(Fonts.helv_20);
		jlTitle.setForeground(Colors.white);
		add(jlTitle);

		// Title names
		JLabel jlBottom = new JLabel("Kevin O. Cabrera A01227157  Valentin Ochoa A01227157");
		jlBottom.setBounds(x + 50, 20, width, height);
		jlBottom.setFont(Fonts.helv_15);
		jlBottom.setForeground(Colors.white);
		add(jlBottom);
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}
}
