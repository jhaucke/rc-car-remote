package com.github.jhaucke.carremotefx.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transmitter implements Runnable {

	private final Logger logger;

	private static int steeringValue;
	private static int gasValue;
	private static int camYAxisValue;
	private static int camXAxisValue;

	private InetAddress inetAddr;
	private DatagramSocket socket;
	private byte[] buffer;
	private DatagramPacket packet;

	private byte[] ipBytes = new byte[] { (byte) 192, (byte) 168, (byte) 240, (byte) 1 };

	public Transmitter() {
		super();
		logger = LoggerFactory.getLogger(Transmitter.class);
	}

	@Override
	public void run() {

		try {
			inetAddr = InetAddress.getByAddress(ipBytes);
			socket = new DatagramSocket();

			while (true) {
				buffer = (steeringValue + ":" + gasValue + ":" + camYAxisValue + ":" + camXAxisValue).getBytes();
				packet = new DatagramPacket(buffer, buffer.length, inetAddr, 5005);

				socket.send(packet);

				Thread.sleep(10);
			}
		} catch (IOException | InterruptedException e) {
			String newLine = System.getProperty("line.separator");
			logger.error("Message: " + e.getMessage() + newLine + "Cause: " + e.getCause() + newLine + "StackTrace: "
					+ e.getStackTrace());
		}
	}

	public static void setSteeringValue(int steeringValue) {
		Transmitter.steeringValue = steeringValue;
	}

	public static void setGasValue(int gasValue) {
		Transmitter.gasValue = gasValue;
	}

	public static void setCamYAxisValue(int camYAxisValue) {
		Transmitter.camYAxisValue = camYAxisValue;
	}

	public static void setCamXAxisValue(int camXAxisValue) {
		Transmitter.camXAxisValue = camXAxisValue;
	}
}
