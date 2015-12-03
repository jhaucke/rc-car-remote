package com.github.jhaucke.carremotefx;

import org.junit.Test;

import com.github.jhaucke.carremotefx.network.Transmitter;

/**
 * Unit test for the transmitter.
 */
public class TransmitterTest {

	/**
	 * This test is for transmitting test values to the car.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void transmitValues() throws InterruptedException {

		Transmitter.setSteeringValue(90);
		Transmitter.setCamXAxisValue(180);
		Transmitter.setCamYAxisValue(83);

		Thread thread = new Thread(new Transmitter());
		thread.start();
		Thread.sleep(1000);
		thread.stop();
	}
}
