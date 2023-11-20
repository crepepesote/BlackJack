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
				takeCard();
				break;
			}
			case "pass": {	
				pass();
				break;
			}
			}
		}catch (Exception e) {
		}	
	}

	//Generar socket cuando se permite nuevo jugador en el server
	private void generateSocket (String id, String name) throws UnknownHostException, IOException {
		socket = new Socket(id,9091);
		outStream = new DataOutputStream(this.socket.getOutputStream());
		inStream = new DataInputStream(this.socket.getInputStream());
		new Thread(() -> {
			try {
				handleServerResponse(name);
			} catch (IOException e) {
				System.out.println("Error de E/S: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("Clase no encontrada: " + e.getMessage());
			}
		}).start();
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
			String s = inStream.readUTF();
			System.out.println(s);
			if(s.equals("win")) {
				//gano hacer algo

			}
		}
	}

	private void takeCard() throws IOException {
		outStream.writeUTF("take");
		
		cards.add(inStream.readUTF());	
		
		frame.updatecards(cards);
		String statusgame=inStream.readUTF();
		if(statusgame.equals("continue")) {
			String status = inStream.readUTF();
			if(status.equals("lost")) {
				lost();
			}
		}else if(statusgame.equals("Empate")) {
			inStream.readUTF();
			inStream.readUTF();
//			inStream.readUTF();
			frame.showresults();
		}else {
			inStream.readUTF();
			inStream.readUTF();
//			inStream.readUTF();
			frame.showresults();
		}

	}

	private void pass() throws IOException {
		new Thread(() -> {
			frame.waitResult("pasado");
			try {
				outStream.writeUTF("pass");
				inStream.readUTF();
				inStream.readUTF();
//				inStream.readUTF();
				frame.showresults();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	private void lost() throws IOException {
		new Thread(() -> {
			frame.waitResult("perdido");
			try {
				outStream.writeUTF("lost");
				inStream.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	//main
	public static void main(String[] args) {
		new Controll();
	}

}
