package co.edu.uptc.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.model.Card;
import co.edu.uptc.view.viewComponents.CustomBoton;

public class PanelGame extends JPanel {

	private ArrayList<int []> cardPosition;
	private ArrayList<String>cards;
	private ArrayList<JLabel> labels;
	private JLabel wating;
	private CustomBoton take;
	private CustomBoton pass;

	public PanelGame(int x, int y, int width, int height,ArrayList<String> cards ,ActionListener listener)  {
		this.setLayout(null);
		this.cards=cards;
		this.setBackground(new Color(120, 193, 101));
		this.setBounds(x, y, width, height);
		labels = new ArrayList<>();
		this.initComponents(listener);
	}

	private void initComponents( ActionListener listener)  {

		cardPosition = new ArrayList<>();

		for(int i=0; i < 4; i++) {
			cardPosition.add(new int[] {(int) (((1+i)*(this.getWidth()*0.08))+(this.getWidth()*0.15*(i))),(int) (this.getHeight()*0.063)});
		}
		for(int i=0; i < 4; i++) {
			cardPosition.add(new int[] {(int) (((1+i)*(this.getWidth()*0.08))+(this.getWidth()*0.15*(i))),(int) (this.getHeight()*0.423)});
		}

		take = new CustomBoton("Take Card",(int) (this.getWidth()*0.25), (int) (this.getHeight()*0.85), (int) (this.getWidth()*0.2), (int) (this.getHeight()*0.1));
		take.setActionCommand("take");
		take.addActionListener(listener);
		this.add(take);

		pass = new CustomBoton("Pass Turn",(int) (this.getWidth()*0.55), (int) (this.getHeight()*0.85), (int) (this.getWidth()*0.2), (int) (this.getHeight()*0.1));
		pass.setActionCommand("pass");
		pass.addActionListener(listener);
		this.add(pass);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 =(Graphics2D)g;
		super.paintComponent(g2);

		g2.drawRect(0, (int) (this.getHeight()*0.8), (int) (this.getWidth()), (int) (this.getHeight()*0.2));
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, (int) (this.getHeight()*0.8), (int) (this.getWidth()), (int) (this.getHeight()*0.2));
		g2.setColor(Color.black);
		g2.drawRect(0, (int) (this.getHeight()*0.8), (int) (this.getWidth()-1), (int) (this.getHeight()*0.2));

		g2.setColor(new Color (113, 160, 100));
		
		
			for(int i=0; i < cardPosition.size(); i++) {
				g2.fillRect(cardPosition.get(i)[0], cardPosition.get(i)[1], (int) (this.getWidth()*0.15), (int) (this.getHeight()*0.3));
			}
			if(cards!=null) {
			for(JLabel label: labels) {
				this.remove(label);
			}
			labels.clear();

			for(int i=0; i < cards.size(); i++){
				JLabel label = new JLabel();
				label.setBounds(cardPosition.get(i)[0], cardPosition.get(i)[1],  (int) (this.getWidth()*0.15), (int) (this.getHeight()*0.3));
				setImageLabel(label, cards.get(i));
				labels.add(label);
				this.add(label);
			}
		}
		
	}

	private void setImageLabel(JLabel labelName, String root) {
		ImageIcon image = new ImageIcon(root);
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
		labelName.setIcon(icon);
	}
	
	public void updateCards(ArrayList<String> car) {
		this.cards = car;
		this.revalidate();
		this.repaint();
	}
	
	public void waitingresult (String status) {
		this.remove(pass);
		this.remove(take);
		wating = new JLabel("Has "+status+", esperando resultados" );
		wating .setBounds((int) (this.getWidth()*0.22), (int) (this.getHeight()*0.8), (int) (this.getWidth()*0.5), (int) (this.getHeight()*0.2));
		wating .setFont(new Font("Arial", Font.BOLD, 30));
		this.add(wating );
		this.repaint();
		this.revalidate();
	}

	public ArrayList<String> getCards() {
		return cards;
	}

	public void setCards(ArrayList<String> cards) {
		this.cards = cards;
	}
	
	

}
