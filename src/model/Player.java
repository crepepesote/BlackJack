package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Player extends Thread  {


	private Socket socket;
	private String namePlayer;
	private ArrayList<Card> cards;
	private DataOutputStream outStream ;
	private DataInputStream inmStream;
	private Server server;
	private LogicGame logic;
	private boolean ingame;
	private boolean turn;
	private String status;
	private int points;

	public Player(Socket socket ,LogicGame logic,Server ser) {
		this.socket = socket;
		cards = new ArrayList<>();
		this.server= ser;
		this.logic= logic;
		points=0;
	}

	@Override
	public void run() {
		playing();
		super.run();
	}

	private void playing() {
		try {
			outStream = new DataOutputStream(this.socket.getOutputStream());
			inmStream =  new DataInputStream(this.socket.getInputStream());

			outStream.writeUTF("Conected");
			String name = inmStream.readUTF();
			this.namePlayer=name;
			System.out.printf("El cliente se llama %s \n ", name);

			synchronized (this) {
				wait();	
			}
			sleep(1000);
			outStream.writeUTF(server.getPlayers().size()+"");
		}catch (Exception e){
			e.printStackTrace();
		}
		this.game();
	}


	private void game() {
		try  {
			logic.startGame(this);
			for(Card car : cards) {
				outStream.writeUTF(car.getImageRute());	
			}
			int firstplay =logic.blackJack(cards);
			points=firstplay;
			if(firstplay == 21) {
				outStream.writeUTF("win");	
			}else {
				outStream.writeUTF("continue");	
				ingame=true;
				while(ingame) {

					if(this.status.equals("end")) {
						this.turn=true;
						this.status="end";
						server.turn(this);
						String gameStatus2=server.value(this);
						gameStatus2=server.value(this);
						while(server.isPlaying()) {
							synchronized (this) {
								wait();	
							}	
						}
						this.status= "end";
						server.sendResults(this);
					}
					else {
					String s = inmStream.readUTF();

					//si toma carta
					if(s.equals("take")) {
						logic.takeCard(this);
						this.turn= true;
						this.status ="waiting";

						//Espera a q los jugadores elijan
						if(!server.turn(this)) {
							synchronized (this) {
								wait();	
							}
							outStream.writeUTF(cards.get(cards.size()-1).getImageRute());
							continueTurns();
						}

						//si ya todos eligieron manda la carta
						else {
							outStream.writeUTF(cards.get(cards.size()-1).getImageRute());
							continueTurns();
						}


					}//Si pasa turno
					else {
						this.turn=true;
						this.status="end";
						server.turn(this);
						String gameStatus=server.value(this);
						while(server.isPlaying()) {
							synchronized (this) {
								wait();	
							}	
						}
						this.status= "end";
						server.sendResults(this);
					}}
				}
			}
		} catch (Exception e) {
		}
	}

	public void continueTurns() throws InterruptedException, IOException {
		this.turn= false;
		this.status=stopOrContinue();
		String gameStatus=server.value(this);
		if(gameStatus.equals("wait")) {
			synchronized (this) {
				wait();	}
		}
		gameStatus=server.value(this);
		outStream.writeUTF(gameStatus);

		if(gameStatus.equals("continue")) {

			outStream.writeUTF(this.status);

			if(this.status.equals("lost")) {
				this.turn=true;
				this.status="end";
				server.turn(this);
				String gameStatus2=server.value(this);
				gameStatus2=server.value(this);
				while(server.isPlaying()) {
					synchronized (this) {
						wait();	
					}	
				}
				this.status= "end";
				server.sendResults(this);
			}


		}else {
			this.status= "end";
			server.sendResults(this);
		}

	}

	public String stopOrContinue() {
		if(!logic.continueOrStopp(this)) {
			if(logic.winOrlost(this)) {
				return "win";
			}else {
				return "lost";
			}
		}else {
			return "continue";
		}

	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public DataOutputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(DataOutputStream outStream) {
		this.outStream = outStream;
	}

	public DataInputStream getInmStream() {
		return inmStream;
	}

	public void setInmStream(DataInputStream inmStream) {
		this.inmStream = inmStream;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}




}
