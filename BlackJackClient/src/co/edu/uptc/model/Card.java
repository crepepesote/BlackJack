package co.edu.uptc.model;

import java.io.Serializable;

public class Card implements Serializable {
	
	private static final long serialVersionUID = 22L;
	
	private String name;
	private int value;
	private String figure;
	private String imageRute;
	
	public Card(String name,int value,String figure, String imageRute) {
		this.name = name;
		this.figure = figure;
		this.value = value;
		this.imageRute = imageRute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public int getValor() {
		return value;
	}

	public void setValor(int valor) {
		this.value = valor;
	}

	public String getImageRute() {
		return imageRute;
	}

	public void setImageRute(String imageRute) {
		this.imageRute = imageRute;
	}
}
