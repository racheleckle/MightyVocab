package mainapp;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.zeromq.ZMQ;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server_comm.Server;

public class MightyVocabApplication extends Application {

	private static final String SERVER_PATH = "../../../server/main.py";
	private static final String LOGIN_PAGE_FXML_PATH = "../view/LoginPage.fxml";

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_PAGE_FXML_PATH));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MightyVocab");
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		Server server = new Server(SERVER_PATH);
//		Client client = new Client(null, null);
//		server.start();
//		client.start();
		System.out.println(ZMQ.CHARSET);
	}

	public static void main(String[] args) {
		Path serverPath = Paths.get("").toAbsolutePath().normalize();
		String path = serverPath.resolve(SERVER_PATH).toString();
		Server server = new Server(path);
		server.start();
		launch(args);
		server.exit();
	}
}
