package eye.record.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AchieveController implements Initializable{

	@FXML
	private AnchorPane achieveMainPage;
	
	@FXML
	private LineChart recordChart;
	
	@FXML
	private ImageView backBtn;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Main.setMusic("introMusic", true);
					Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/recordMain.fxml"));
					Scene scene = new Scene(recordPage);
					scene.getStylesheets()
							.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
					Stage primaryStage = (Stage) backBtn.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




}
