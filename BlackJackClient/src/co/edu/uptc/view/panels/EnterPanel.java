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

public class EnterPanel extends JPanel {

	private JLabel balckJackTittle;
	private JLabel nametitle;
	private JLabel idtitle;
	private JLabel waitPlayers;
	private JtextPlaceHolder name;
	private JtextPlaceHolder id;
	
	private CustomBoton enter;

	public EnterPanel(int x, int y, int width, int height, ActionListener listener)  {
		this.setLayout(null);
		this.setBackground(new Color(120, 193, 101));
		this.setBounds(x, y, width, height);
		this.initComponents(listener);
	}

	private void initComponents( ActionListener listener)  {
		
		balckJackTittle = new JLabel("BLACK JACK");
		balckJackTittle.setBounds((int) (this.getWidth()*0.38), (int) (this.getHeight()*0.13), (int) (this.getWidth()*0.4), (int) (this.getHeight()*0.15));
		balckJackTittle.setFont(new Font("Arial", Font.BOLD, 30));
		this.add(balckJackTittle);

		nametitle = new JLabel("Ingrese un nombre: ");
		nametitle.setBounds((int) (this.getWidth()*0.22), (int) (this.getHeight()*0.3), (int) (this.getWidth()*0.4), (int) (this.getHeight()*0.15));
		nametitle.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(nametitle);
		
		name = new JtextPlaceHolder("Aqui su nombre", (int) (this.getWidth()*0.42), (int) (this.getHeight()*0.345), (int) (this.getWidth()*0.35), (int) (this.getHeight()*0.06), Color.white, Color.gray);
		this.add(name);
		
		idtitle = new JLabel("Ingrese id del juego: ");
		idtitle.setBounds((int) (this.getWidth()*0.22), (int) (this.getHeight()*0.45), (int) (this.getWidth()*0.4), (int) (this.getHeight()*0.15));
		idtitle.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(idtitle);
		
		id = new JtextPlaceHolder("Id. Ej. 125.0.76.343", (int) (this.getWidth()*0.42), (int) (this.getHeight()*0.495), (int) (this.getWidth()*0.35), (int) (this.getHeight()*0.06), Color.white, Color.gray);
		this.add(id);
		
		enter = new CustomBoton("Enter Game",(int) (this.getWidth()*0.35), (int) (this.getHeight()*0.68), (int) (this.getWidth()*0.3), (int) (this.getHeight()*0.1));
		enter.setActionCommand("EnterGame");
		enter.addActionListener(listener);
		this.add(enter);
	}

	
	public void esperar(int num) {
		this.remove(nametitle);
		this.remove(name);
		this.remove(id);
		this.remove(idtitle);
		this.remove(enter);
		
		waitPlayers = new JLabel("Esperando jugadores "+num+" / 2" );
		waitPlayers.setBounds((int) (this.getWidth()*0.22), (int) (this.getHeight()*0.4), (int) (this.getWidth()*0.5), (int) (this.getHeight()*0.1));
		waitPlayers.setFont(new Font("Arial", Font.BOLD, 30));
		this.add(waitPlayers);
	}
	
	public void updateplayers(int num) {
		waitPlayers.setText("Esperando jugadores "+num+" / 2" );
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

	public String getName() {
		return name.getText();
	}

	public void setName(JtextPlaceHolder name) {
		this.name = name;
	}

	public String getId() {
		return id.getText();
	}

	public void setId(JtextPlaceHolder id) {
		this.id = id;
	}
	
	

}
