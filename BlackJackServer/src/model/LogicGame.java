package model;

import java.util.ArrayList;
import java.util.Random;

public class LogicGame {

	private ArrayList<Card> baraja;
	private int randomIndex;

	public LogicGame(ArrayList<Card> baraja) {
		this.baraja = baraja;
	}

	public synchronized void startGame(Player player) {
		for(int i=0; i < 2 ;i++) {
			randomIndex=new Random().nextInt(baraja.size());
			player.getCards().add(baraja.remove(randomIndex));
		}
	}

	public int blackJack(ArrayList<Card> cr) {
		if(cr.get(0).getName().equals("A")||cr.get(1).getName().equals("A")) {
			if(cr.get(0).getName().equals("10")||cr.get(1).getName().equals("10")||
					cr.get(0).getName().equals("K")||cr.get(1).getName().equals("K")||
					cr.get(0).getName().equals("J")||cr.get(1).getName().equals("J")||
					cr.get(0).getName().equals("Q")||cr.get(1).getName().equals("Q")) {
				
				return 21;
			}else {
				return cr.get(0).getValor()+cr.get(1).getValor();
			}	
		}	else {
			return cr.get(0).getValor()+cr.get(1).getValor();
		}
	}

	public synchronized void takeCard (Player player) {
		randomIndex=new Random().nextInt(baraja.size());
		player.getCards().add(baraja.remove(randomIndex));
	}

	public boolean continueOrStopp(Player player) {
		int points=0;
		for(Card card:player.getCards()) {
			points+=card.getValor();
		}
		player.setPoints(points);
		if(points >=21) {
			return false;
		}else {
			return true;
		}
	}

	public boolean winOrlost(Player player) {
		int points=0;
		for(Card card:player.getCards()) {
			points+=card.getValor();	
		}
		if(points ==21) {
			return true;
		}else {
			return false;
		}

	}

}
