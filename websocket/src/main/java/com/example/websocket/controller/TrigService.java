package com.example.websocket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.websocket.controller.TrigResponse.SinCos;

@Service
public class TrigService {

	private static final double DEGREES_PER_PI = 180 / Math.PI;

	public TrigResponse getNextValues(TrigRequest request) {
		List<SinCos> nextValuesList = IntStream
			.range(request.getDegrees(), request.getDegrees() + request.getSize())
			.asDoubleStream()
			.map(d -> d / DEGREES_PER_PI)
			.boxed()
			.map(r -> new SinCos(Math.sin(r), Math.cos(r)))
			.collect(ArrayList<SinCos>::new, (list, sc) -> list.add(sc), (l1, l2) -> l1.addAll(l2));

		SinCos[] nextValues = nextValuesList.toArray(new SinCos[nextValuesList.size()]);

		request.setDegrees(request.getDegrees() + request.getSize());

		return new TrigResponse(nextValues);
	}
}
