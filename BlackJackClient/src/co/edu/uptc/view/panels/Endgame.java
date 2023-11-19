package co.edu.uptc.view.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.view.viewComponents.CustomBoton;
import co.edu.uptc.view.viewComponents.JtextPlaceHolder;

public class Endgame extends JPanel {

	private JLabel gameResult;
	private JLabel firstPosition;
	private JLabel secondPosition;
	private JLabel thirdPosition;
	
	private CustomBoton enter;

	public Endgame(int x, int y, int width, int height, ActionListener listener)  {
		this.setLayout(null);
		this.setBackground(new Color(120, 193, 101));
		this.setBounds(x, y, width, height);
		this.initComponents(listener);
	}

	private void initComponents( ActionListener listener)  {
		
		gameResult = new JLabel("");
		gameResult.setBounds((int) (this.getWidth()*0.38), (int) (this.getHeight()*0.13), (int) (this.getWidth()*0.4), (int) (this.getHeight()*0.15));
		gameResult.setFont(new Font("Arial", Font.BOLD, 30));
		this.add(gameResult);

		firstPosition = new JLabel("Ingrese un nombre: ");
		firstPosition.setBounds((int) (this.getWidth()*0.22), (int) (this.getHeight()*0.25), (int) (this.getWidth()*0.4), (int) (this.getHeight()*0.15));
		firstPosition.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(firstPosition);
		
		secondPosition = new JLabel("Ingrese id del juego: ");
		secondPosition.setBounds((int) (this.getWidth()*0.22), (int) (this.getHeight()*0.35), (int) (this.getWidth()*0.4), (int) (this.getHeight()*0.15));
		secondPosition.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(secondPosition);
		
		thirdPosition = new JLabel("Ingrese id del juego: ");
		thirdPosition.setBounds((int) (this.getWidth()*0.22), (int) (this.getHeight()*0.35), (int) (this.getWidth()*0.4), (int) (this.getHeight()*0.15));
		thirdPosition.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(thirdPosition);
		
		
		enter = new CustomBoton("Enter Game",(int) (this.getWidth()*0.35), (int) (this.getHeight()*0.68), (int) (this.getWidth()*0.3), (int) (this.getHeight()*0.1));
		enter.setActionCommand("none");
		enter.addActionListener(listener);
		this.add(enter);
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 =(Graphics2D)g;
		super.paintComponent(g2);
		
		g2.setStroke (new BasicStroke (4));
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRoundRect((int) (this.getWidth()*0.34), (int) (this.getHeight()*0.15), (int) (this.getWidth()*0.3), (int) (this.getHeight()*0.2), 30, 30);
		g2.setColor(Color.black);
		g2.drawRoundRect((int) (this.getWidth()*0.34), (int) (this.getHeight()*0.15), (int) (this.getWidth()*0.3), (int) (this.getHeight()*0.2), 30, 30);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRoundRect((int) (this.getWidth()*0.2), (int) (this.getHeight()*0.25), (int) (this.getWidth()*0.6), (int) (this.getHeight()*0.5), 30, 30);
		g2.setColor(Color.black);
		g2.drawRoundRect((int) (this.getWidth()*0.2), (int) (this.getHeight()*0.25), (int) (this.getWidth()*0.6), (int) (this.getHeight()*0.5), 30, 30);
		
	}

	

}
