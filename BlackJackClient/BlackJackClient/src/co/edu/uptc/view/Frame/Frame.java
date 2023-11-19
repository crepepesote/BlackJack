package co.edu.uptc.view.Frame;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import co.edu.uptc.model.Card;
import co.edu.uptc.view.panels.Endgame;
import co.edu.uptc.view.panels.EnterPanel;
import co.edu.uptc.view.panels.PanelGame;

public class Frame extends JFrame {
	
	private PanelGame principalP;
	private EnterPanel enter;
	private Endgame results;
	

	public Frame(ActionListener listener)  {
		super("Black Jack");
//		this.setIconImage(new ImageIcon("images/logo.jpeg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((900)+16, (600)+120);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.initComponents(listener);
		this.requestFocus(true);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.repaint();
	}

	private void initComponents(ActionListener listener)  {
		enter = new EnterPanel(0, 0, this.getWidth()-16, this.getHeight()-39, listener);
		this.add(enter);	
		
		principalP = new PanelGame(0, 0, this.getWidth()-16, this.getHeight()-39,null ,listener);
		results= new Endgame(0, 0, this.getWidth()-16, this.getHeight()-39, listener);
	}
	
	public void startGame (ArrayList<String> cards) {
		principalP.updateCards(cards);
		this.remove(enter);
		this.add(principalP);
		this.revalidate();
		this.repaint();
	}
	
	public void waitPlayers (int num) {
		enter.esperar(num);
		this.revalidate();
		repaint();
	}
	
	public void updadeConectedPalyer(int num) {
		enter.updateplayers(num);
		this.revalidate();
		this.repaint();
	}
	
	public void updatecards(ArrayList<String> cards) {
		principalP.updateCards(cards);
		this.revalidate();
		this.repaint();
	}
	
	public void waitResult(String action) {
		principalP.waitingresult(action);
		this.revalidate();
		this.repaint();
	}
	
	public void  showresults() {
		this.remove(principalP);
		this.add(results);
		this.revalidate();
		this.repaint();
	}
	
	
	public String obtainServer() {
		return enter.getId();
	}
	
	public String obtainName() {
		return enter.getName();
	}

}
