package eye.record.controller;

import java.net.URL;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExamExplaincontroller implements Initializable{
	@FXML
    private AnchorPane ExplainPage;

    @FXML
    private ImageView backBtn;

    @FXML
    private Text title;

    @FXML
    private ImageView nextBtn;

    @FXML
    private Text nextBtn2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Main.setMusic("mainMusic", true, 1);
					Parent root = FXMLLoader.load(getClass().getResource("/eye/record/view/recordMain.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) ExplainPage.getScene().getWindow();
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					scene.getStylesheets()
					.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
				}

			}

		});
		
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/ExamPage.fxml"));
					Scene scene = new Scene(recordPage);
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					Stage primaryStage = (Stage) nextBtn.getScene().getWindow();
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		//다음 화면 전환 버튼
		nextBtn2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/ExamPage.fxml"));
					Scene scene = new Scene(recordPage);
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					Stage primaryStage = (Stage) nextBtn2.getScene().getWindow();
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});		
	}
    
    
}
