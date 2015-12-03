package com.github.jhaucke.carremotefx;

import com.github.jhaucke.carremotefx.network.Transmitter;

/**
 * The entry point for the next gen multi touch car control panel.
 */
public class CarRemoteFX {
	public static void main(String[] args) {
		
		Thread transmitterThread = new Thread(new Transmitter());
		transmitterThread.start();
		
		// TODO values should come from ui
		Transmitter.setSteeringValue(80);
		Transmitter.setCamXAxisValue(150);
		Transmitter.setCamYAxisValue(70);
	}
}
