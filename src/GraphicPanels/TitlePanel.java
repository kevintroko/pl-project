package GraphicPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


/* @Author Kevin O. */

public class TitlePanel extends JPanel{
	private static final long serialVersionUID = 1L;

	//Provides a general purpose component for implementing divider lines 
	JSeparator separator;
	
	// Colors definition
	Color blue  = new Color(0,191,255);	 
	Color white = new Color(255,255,255);
	
	// Create the panel for the title component
	public TitlePanel(Rectangle bounds){
		setLayout(null);
		setBounds(bounds);
		setBackground(this.blue);
		loadComponents();
	}
	
	// Constructor
	public TitlePanel(){
		setLayout(null);
		loadComponents();
	}
	
	public void loadComponents() {
		// Set the layout for the Separator
		separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBounds(0,99,getBounds().width,1);
		add(separator);
		
		// Title top
		JLabel jlTitle = new JLabel("                                                         Programming Language Project");
		jlTitle.setBounds(110,-15, 650, 100);
		jlTitle.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 20));
		jlTitle.setForeground(this.white);
		add(jlTitle);
		
		// Title names
		JLabel jlBottom = new JLabel("Kevin O. Cabrera A01227157  Valentin Ochoa A01227157");
		jlBottom.setBounds(400, 20, 650, 100);
		jlBottom.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 14));
		jlBottom.setForeground(this.white);
		add(jlBottom);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		separator.setSize(width, separator.getBounds().height);
	}
}
