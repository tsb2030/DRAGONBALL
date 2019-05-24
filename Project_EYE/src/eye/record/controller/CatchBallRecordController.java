package eye.record.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CatchBallRecordController {
	
	@FXML
	AnchorPane catchBallRecord;
	
	//기록 창 닫기
	public void exitButton(ActionEvent e) {
		
		Stage currentStage = (Stage) catchBallRecord.getScene().getWindow();
		currentStage.close();
		
	}
	
	
	

}
