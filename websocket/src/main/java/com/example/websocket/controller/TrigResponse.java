package com.example.websocket.controller;

import java.util.Arrays;

public class TrigResponse {

	private SinCos[] nextValues;

	public TrigResponse() {
	}

	public TrigResponse(SinCos[] nextValues) {
		this.nextValues = nextValues;
	}

	public SinCos[] getNextValues() {
		return nextValues;
	}

	public void setNextValues(SinCos[] nextValues) {
		this.nextValues = nextValues;
	}

	@Override
	public String toString() {
		return "TrigResponse [nextValues=" + Arrays.toString(nextValues) + "]";
	}

	public static class SinCos {

		private final double sin;
		private final double cos;

		public SinCos(double sin, double cos) {
			this.sin = sin;
			this.cos = cos;
		}

		public double getSin() {
			return sin;
		}

		public double getCos() {
			return cos;
		}

		@Override
		public String toString() {
			return "SinCos [sin=" + sin + ", cos=" + cos + "]";
		}
	}
}
