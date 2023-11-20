package co.edu.uptc.view.viewComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JtextPlaceHolder extends JTextField implements FocusListener {

	private String placeholder;
	private boolean showingPlaceholder;
	private Color colorFount;
	private Color backgroColor;

	public JtextPlaceHolder(String placeholder, int x, int y ,int widht, int height, Color colorFount, Color background) {
		super();
		this.backgroColor=background;
		this.setOpaque(false);
		this.setBorder(new EmptyBorder(0,15,0,15));
		this.colorFount=colorFount;
		this.setBounds(x,y,widht,height);
		this.placeholder = placeholder;
		this.showingPlaceholder = true;
		this.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.showingPlaceholder) {
			this.setText(this.getText());
			this.setForeground(colorFount);
			this.showingPlaceholder = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().equals("")) {
			this.setForeground(Color.DARK_GRAY);
			this.showingPlaceholder = true;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(backgroColor);	
		g2.fillRoundRect((int) (this.getWidth()*0.01), (int) (this.getHeight()*0.1), (int) (this.getWidth()*0.98), (int) (this.getHeight()*0.88),20,20);
		g2.setColor(Color.BLACK);	
		g2.drawRoundRect((int) (this.getWidth()*0.01), (int) (this.getHeight()*0.1), (int) (this.getWidth()*0.98), (int) (this.getHeight()*0.88),20,20);

		if (this.showingPlaceholder && this.getText().equals("")) {
			g2.setColor(Color.DARK_GRAY);
			g2.drawString(placeholder, (int) (this.getWidth()*0.05),(int) (this.getHeight()*0.7));
		}   
		else {
		}
		super.paintComponent(g2);
	}
}

