package eye.record.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.main.controller.mainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ExamEndpopupcontroller implements Initializable{

	@FXML
	private AnchorPane ExamEndingPopup;

	@FXML
	private Button closeBtn;
	
    @FXML
    private Label score;
    
    

	@FXML
	void closeBtnAction(ActionEvent event) throws IOException{
		Stage stage = (Stage) ExamEndingPopup.getScene().getWindow();
		stage.close();
		AnchorPane an = FXMLLoader.load(getClass().getResource("/eye/record/view/recordMain.fxml"));
		Scene scene = new Scene(an);
		scene.getStylesheets().add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
		mainController.currentStage.setResizable(false);
		mainController.currentStage.setScene(scene);

	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		score.setText(Double.toString(eyeExamcontroller.Vis));
	}
}
