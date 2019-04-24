package GraphicPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class TitlePanel extends JPanel{
	private static final long serialVersionUID = 1L;

	JSeparator separator;
	
	public TitlePanel(Rectangle bounds){
		setLayout(null);
		setBounds(bounds);
		setBackground(new Color(220, 220, 220));
		
		loadComponents();
	}
	
	public TitlePanel(){
		setLayout(null);
		setBackground(new Color(220, 220, 220));
		
		loadComponents();
	}
	
	public void loadComponents(){
		separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBounds(0,99,getBounds().width,1);
		separator.setBackground(Color.LIGHT_GRAY);
		add(separator);
		
		JLabel jlTitle = new JLabel("                                                         Programming Language Project");
		jlTitle.setBounds(110,-10,650,100);
		jlTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
		jlTitle.setForeground(new Color(115,115,115));
		add(jlTitle);
		
		JLabel jlBottom = new JLabel("<html>Kevin O. Cabrera <b>A01227157</b>"+ " Valentin Ochoa <b>A01227157</b><html>");
		jlBottom.setBounds(400,20,650,100);
		jlBottom.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 14));
		jlBottom.setForeground(new Color(115,115,115));
		add(jlBottom);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		
		separator.setSize(width, separator.getBounds().height);

	}
}
