package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JsonFileReader {
	
	private JsonReader reader;
	private ArrayList<Card>cards;

	public JsonFileReader() throws IOException {
		cards= new ArrayList <Card>();
		readFiles();
	}

	public void readFiles()throws  IOException {
		reader= new Gson().newJsonReader(new FileReader("data/files/archivo_generado.json"));
		Card[] cd = new Gson().fromJson(reader, Card[].class);
		for (int i = 0; i < cd.length; i++) {
		cards.add(cd[i]);}	
		reader.close();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
}