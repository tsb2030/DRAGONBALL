 package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			 Font.loadFont(getClass().getResourceAsStream("a소나무B.ttf"), 10);
	         Font.loadFont(getClass().getResourceAsStream("a소나무L.ttf"), 10);
	         Font.loadFont(getClass().getResourceAsStream("a소나무M.ttf"), 10);
	         
			Parent root=FXMLLoader.load(getClass().getResource("StartPage.fxml"));
			Scene scene=new Scene(root);			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("눈 운동"); 
			primaryStage.setResizable(false);
			primaryStage.show();
			
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
