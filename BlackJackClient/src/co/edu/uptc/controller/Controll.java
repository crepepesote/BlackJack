package co.edu.uptc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import co.edu.uptc.model.Card;
import co.edu.uptc.view.Frame.Frame;

public class Controll implements ActionListener{

	private static final int MAXCONECT = 2;
	private Frame frame;
	private Socket socket;
	private DataOutputStream outStream;
	private DataInputStream inStream;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private ArrayList<String> cards;

	public Controll() {
		frame = new Frame(this);
	}

	//Controlador de el listener
	public void actionPerformed(ActionEvent event) {
		String source = event.getActionCommand();
		try {
			switch (source) {
			case "EnterGame": {	
				generateSocket(frame.obtainServer(), frame.obtainName());
				break;
			}
			case "take": {	
				generateSocket(frame.obtainServer(), frame.obtainName());
				break;
			}
			case "pass": {	
				generateSocket(frame.obtainServer(), frame.obtainName());
				break;
			}
			}
		}catch (Exception e) {
		}	
	}

	//Generar socket cuando se permite nuevo jugador en el server
	private void generateSocket (String id, String name) throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1",9091);
		try {

			outStream = new DataOutputStream(this.socket.getOutputStream());
			objOut = new ObjectOutputStream(this.socket.getOutputStream());
			inStream = new DataInputStream(this.socket.getInputStream());
			objIn = new ObjectInputStream(this.socket.getInputStream());

			new Thread(() -> {
				try {
					handleServerResponse(name);
				} catch (IOException e) {
					System.out.println("Error de E/S: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.println("Clase no encontrada: " + e.getMessage());
				}
			}).start();
		} catch (UnknownHostException e) {
			System.out.println("Host desconocido: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
		}
	}

	private void handleServerResponse(String name) throws IOException, ClassNotFoundException {

		if (inStream.readUTF().equals("full")) {
			System.out.println("servidor lleno");
		} else {
			outStream.writeUTF(name);

			int numplayers =Integer.parseInt(inStream.readUTF());
			frame.waitPlayers(numplayers);
			while(numplayers!= MAXCONECT) {
				numplayers =Integer.parseInt(inStream.readUTF());
				frame.updadeConectedPalyer(numplayers);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int num = Integer.parseInt(inStream.readUTF());
			cards = new ArrayList<>();
			for(int i=0 ; i < num ; i++ ) {
				cards.add(inStream.readUTF());
			}
		
			
			frame.startGame(cards);
			
			if(inStream.readUTF().equals("win")) {
				//gano hacer algo
				
			}
		}
	}

	private void takeCard() throws IOException {
		outStream.writeUTF("take");
		cards.add(inStream.readUTF());
		frame.updatecards(cards);
	}
	
	private void pass() throws IOException {
		outStream.writeUTF("pass");
	}

	//main
	public static void main(String[] args) {
		new Controll();
	}

}
