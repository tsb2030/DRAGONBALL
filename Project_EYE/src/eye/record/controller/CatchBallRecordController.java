package eye.record.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CatchBallRecordController implements Initializable {
	
	@FXML
	AnchorPane catchBallRecord;
	
	@FXML 	// top10 기록 라벨
	Label record1,record2,record3,record4,record5,record6,record7,record8,record9,record10;
	
	@FXML // top10 휴식 라벨
	Label date1,date2,date3,date4,date5,date6,date7,date8,date9,date10;
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// default step1 record
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 5 ; i>0 ; i--) {
			top10Record[5-i] = Integer.toString(i*10);
		}
		for(int i = 5 ; i>0 ; i--) {
			top10Date[5-i] = "6/1";
		}
	
		
		record1.setText(top10Record[0]);
		record2.setText(top10Record[1]);
		record3.setText(top10Record[2]);
		record4.setText(top10Record[3]);
		record5.setText(top10Record[4]);
		record6.setText(top10Record[5]);
		record7.setText(top10Record[6]);
		record8.setText(top10Record[7]);
		record9.setText(top10Record[8]);
		record10.setText(top10Record[9]);
		
		date1.setText(top10Date[0]);
		date2.setText(top10Date[1]);
		date3.setText(top10Date[2]);
		date4.setText(top10Date[3]);
		date5.setText(top10Date[4]);
		date6.setText(top10Date[5]);
		date7.setText(top10Date[6]);
		date8.setText(top10Date[7]);
		date9.setText(top10Date[8]);
		date10.setText(top10Date[9]);
		
	}
	
	
	//기록 창 닫기
	public void exitButton(ActionEvent e) {
		
		Stage currentStage = (Stage) catchBallRecord.getScene().getWindow();
		currentStage.close();
		
	}
	

	public void step1Button(ActionEvent e) {
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 5 ; i>0 ; i--) {
			top10Record[5-i] = Integer.toString(i*10);
		}
		for(int i = 5 ; i>0 ; i--) {
			top10Date[5-i] = "6/1";
		}
		
		
		record1.setText(top10Record[0]);
		record2.setText(top10Record[1]);
		record3.setText(top10Record[2]);
		record4.setText(top10Record[3]);
		record5.setText(top10Record[4]);
		record6.setText(top10Record[5]);
		record7.setText(top10Record[6]);
		record8.setText(top10Record[7]);
		record9.setText(top10Record[8]);
		record10.setText(top10Record[9]);
		
		date1.setText(top10Date[0]);
		date2.setText(top10Date[1]);
		date3.setText(top10Date[2]);
		date4.setText(top10Date[3]);
		date5.setText(top10Date[4]);
		date6.setText(top10Date[5]);
		date7.setText(top10Date[6]);
		date8.setText(top10Date[7]);
		date9.setText(top10Date[8]);
		date10.setText(top10Date[9]);
		
	}
	

	public void step2Button(ActionEvent e) {
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 7 ; i>0 ; i--) {
			top10Record[7-i] = Integer.toString(i*5);
		}
		for(int i = 7 ; i>0 ; i--) {
			top10Date[7-i] = "6/2";
		}
		
		
		record1.setText(top10Record[0]);
		record2.setText(top10Record[1]);
		record3.setText(top10Record[2]);
		record4.setText(top10Record[3]);
		record5.setText(top10Record[4]);
		record6.setText(top10Record[5]);
		record7.setText(top10Record[6]);
		record8.setText(top10Record[7]);
		record9.setText(top10Record[8]);
		record10.setText(top10Record[9]);
		
		date1.setText(top10Date[0]);
		date2.setText(top10Date[1]);
		date3.setText(top10Date[2]);
		date4.setText(top10Date[3]);
		date5.setText(top10Date[4]);
		date6.setText(top10Date[5]);
		date7.setText(top10Date[6]);
		date8.setText(top10Date[7]);
		date9.setText(top10Date[8]);
		date10.setText(top10Date[9]);
		
	}
	

	public void step3Button(ActionEvent e) {
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 7 ; i>0 ; i--) {
			top10Record[7-i] = Integer.toString(i*3);
		}
		for(int i = 7 ; i>0 ; i--) {
			top10Date[7-i] = "6/3";
		}
		
		
		record1.setText(top10Record[0]);
		record2.setText(top10Record[1]);
		record3.setText(top10Record[2]);
		record4.setText(top10Record[3]);
		record5.setText(top10Record[4]);
		record6.setText(top10Record[5]);
		record7.setText(top10Record[6]);
		record8.setText(top10Record[7]);
		record9.setText(top10Record[8]);
		record10.setText(top10Record[9]);
		
		date1.setText(top10Date[0]);
		date2.setText(top10Date[1]);
		date3.setText(top10Date[2]);
		date4.setText(top10Date[3]);
		date5.setText(top10Date[4]);
		date6.setText(top10Date[5]);
		date7.setText(top10Date[6]);
		date8.setText(top10Date[7]);
		date9.setText(top10Date[8]);
		date10.setText(top10Date[9]);
	}
	
	
	

}
