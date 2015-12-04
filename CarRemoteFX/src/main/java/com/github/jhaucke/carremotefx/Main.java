package com.github.jhaucke.carremotefx;

import com.github.jhaucke.carremotefx.network.Transmitter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The entry point for the next gen multi touch car control panel.
 */
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);

		Thread transmitterThread = new Thread(new Transmitter());
		transmitterThread.start();

		// TODO values should come from ui
		Transmitter.setSteeringValue(80);
		Transmitter.setCamXAxisValue(150);
		Transmitter.setCamYAxisValue(70);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final Button btnClose = new Button("Push 2 close");
		btnClose.setOnAction(event -> System.exit(0));

		final StackPane parent = new StackPane(btnClose);

		final Scene scene = new Scene(parent, 800, 480);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
