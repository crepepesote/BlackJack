package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static final int PORT = 9091;
	private boolean playing=false;
	private  ArrayList<Player> players;
	private LogicGame logic;
	private boolean  resul;


	public Server() throws IOException {
		players= new ArrayList<>();
		logic = new LogicGame(new JsonFileReader().getCards());

		init();
	}

	public void init() {
		try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println("Esperando Jugadores");
			while(true) {
				//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqui
				if(players.size()==2 || playing) {
					Socket socket = server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF("full");
					socket.close();
				}else {
					Player player = new Player( server.accept(),logic,this );
					players.add(player);
					player.start();
					Thread.sleep(100);

					//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqui
					if (players.size() == 2) {
						playing=true;
						for (Player j : players) {
							synchronized (j) {
								j.getOutStream().writeUTF(players.size()+"");
								j.notify();
							}
						}
					}else {
						for (Player j : players) {
							synchronized (j) {
								j.getOutStream().writeUTF(players.size()+"");
							}
						}
					}
				}

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean turn(Player player) {
		boolean bander=true;
		for(Player p: players) {
			if(!p.isTurn()) {
				bander=false;
				break;
			}
		}
		if(bander) {
			resul = true;
			for (Player j : players) {
				synchronized (j) {
					if(!j.equals(player)) {
						j.notify();
					}
				}
			}
			return true;
		}else {
			return false;}
	}

	//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqui
	public synchronized String value(Player player) {
		boolean bander=true;
		for (Player j : players) {
			if(j.getStatus().equals("waiting")) {
				bander=false;
				break;
			}
		}
		if(bander) {
			for (Player j : players) {
				synchronized (j) {
					if(!j.equals(player) && !j.getStatus().equals("end")) {
						j.notify();
					}
				}
			}

			int winner=0;
			int lossers=0;
			int ended=0;
			for (Player j : players) {
				if(j.getStatus().equals("win")) {
					winner++;
				}else if(j.getStatus().equals("lost")) {
					lossers++;
				}else if(j.getStatus().equals("end")) {
					ended++;
				}
			}
			if(winner>1 || lossers==2) {
				System.out.println("empate");
				this.playing = false;
				for (Player j : players) {
					synchronized (j) {
						if(!j.equals(player)) {
							j.notify();
						}
					}
				}

				return "Empate";
			}else if(winner==1) {
				String winnerPlayer="";
				for (Player j : players) {
					if(j.getStatus().equals("win")) {
						winnerPlayer = j.getNamePlayer();
						break;
					}
				}
				this.playing = false;
				for (Player j : players) {
					synchronized (j) {
						if(!j.equals(player) && !j.getStatus().equals("end")) {
							j.notify();
						}
					}
				}
				System.out.println("alguien gano");
				return winnerPlayer;
			}else if (ended==2){
				this.playing = false;
				for (Player j : players) {
					synchronized (j) {
						if(!j.equals(player)) {
							j.notify();
						}
					}
				}

				return "Empate";
			}else {
				System.out.println("el juego continua");
				return "continue";
			}
		}
		return "wait";
	}

	public synchronized void sendResults(Player player) throws IOException {
		boolean bander=true;
		playing=false;
		for (Player j : players) {
			synchronized (j) {
				if(!j.equals(player)) {
					j.notify();
				}
			}
		}
		for (Player j : players) {
			if(!j.getStatus().equals("end")) {
				bander=false;
				break;
			}
		}
		if(bander) {
			for (Player j : players) {
				for(Player j2 : players)
					j.getOutStream().writeUTF(j2.getNamePlayer()+"/"+j2.getPoints());
			}
		}


	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}



}
