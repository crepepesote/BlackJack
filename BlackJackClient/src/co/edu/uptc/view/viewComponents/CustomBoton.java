package co.edu.uptc.view.viewComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CustomBoton extends JButton {

	private Color color1;
	private Color color2;

	public CustomBoton(String label,int x , int y, int w, int h) {
		color1 = new Color(35,70,38);
		color2 = Color.BLACK;
		this.setForeground(Color.WHITE);
		this.setBackground(color1);
		this.setText(label);
		this.setBounds(x, y, w, h);

		Font font = new Font("Arial", Font.BOLD, 24); 
		setFont(font);

		setBorder(new RoundedBorder(30)); 
		setContentAreaFilled(false);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(color2); 
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(color1);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		super.paintComponent(g);
	}

	private static class RoundedBorder extends javax.swing.border.AbstractBorder {
		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius/2, this.radius, this.radius/2, this.radius);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = insets.right = this.radius;
			insets.top = insets.bottom = this.radius/2;
			return insets;
		}
	}
}
