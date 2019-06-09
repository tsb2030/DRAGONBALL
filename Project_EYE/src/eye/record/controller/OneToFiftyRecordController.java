package eye.record.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OneToFiftyRecordController implements Initializable {
	
	@FXML
	AnchorPane oneToFiftyRecord;
	
	@FXML
	Label record1,record2,record3,record4,record5,record6,record7,record8,record9,record10;
	
	@FXML
	Label date1,date2,date3,date4,date5,date6,date7,date8,date9,date10;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// default kor
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0 ; i<5 ; i++) {
			top10Record[i] = Integer.toString(i*10);
		}
		for(int i = 0 ; i<5 ; i++) {
			top10Date[i] = "6/1";
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
		
		Stage currentStage = (Stage) oneToFiftyRecord.getScene().getWindow();
		currentStage.close();
		
	}
	
	//ㄱtoㅎ record
	public void korButton(ActionEvent e) {
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0 ; i<5 ; i++) {
			top10Record[i] = Integer.toString(i*10);
		}
		for(int i = 0 ; i<5 ; i++) {
			top10Date[i] = "6/1";
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

	//AtoZ record
	public void engButton(ActionEvent e) {
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0 ; i<9 ; i++) {
			top10Record[i] = Integer.toString(i*5);
		}
		for(int i = 0 ; i<9 ; i++) {
			top10Date[i] = "6/2";
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

	// 1to50 record
	public void numButton(ActionEvent e) {
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0 ; i<3 ; i++) {
			top10Record[i] = Integer.toString(i*2);
		}
		for(int i = 0 ; i<3 ; i++) {
			top10Date[i] = "6/3";
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
