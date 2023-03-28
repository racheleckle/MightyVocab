package mainapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server_comm.Client;
import server_comm.Server;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

public class MightyVocabApplication extends Application {
//vm arguments: --module-path "${eclipse_home}/javafx-sdk-18.0.2/lib" --add-modules javafx.controls,javafx.fxml

	private static final String SERVER_PATH = "../../../server/main.py";

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/LoginPage.fxml"));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MightyVocab");
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//Server server = new Server();
		//Client client = new Client();
		
		//server.start();
		//client.start();
		

		Context context = ZMQ.context(1);
		
		System.out.println(ZMQ.CHARSET);
	}

	/**
	 * Entry point
	 * 
	 * @pre none
	 * @post none
	 * @param args Not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}