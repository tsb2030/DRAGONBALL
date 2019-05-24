package eye.record.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CatchMoleRecordController {
	
	@FXML
	AnchorPane catchMoleRecord;
	
	//기록 창 닫기
	public void exitButton(ActionEvent e) {
		
		Stage currentStage = (Stage) catchMoleRecord.getScene().getWindow();
		currentStage.close();
		
	}
	

}
