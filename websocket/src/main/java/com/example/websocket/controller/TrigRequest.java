package com.example.websocket.controller;

public class TrigRequest {

	private int degrees;
	private int size;

	public TrigRequest() {
	}

	public TrigRequest(int degrees, int size) {
		this.degrees = degrees;
		this.size = size;
	}

	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "TrigRequest [degrees=" + degrees + ", size=" + size + "]";
	}
}
