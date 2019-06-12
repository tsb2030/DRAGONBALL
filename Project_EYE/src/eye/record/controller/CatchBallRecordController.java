package eye.record.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import eye.record.model.recordModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import eye.db.*;
public class CatchBallRecordController implements Initializable {
	
	dbconn db = new dbconn();
	
	@FXML
	AnchorPane catchBallRecord;
	
	@FXML 	// top10 기록 라벨
	Label record1,record2,record3,record4,record5,record6,record7,record8,record9,record10;
	
	@FXML // top10 휴식 라벨
	Label date1,date2,date3,date4,date5,date6,date7,date8,date9,date10;
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		//디비연결 데이터가져오기
		List<recordModel> recordm =  new ArrayList<recordModel>();
		try {
			recordm = db.getTopRecordDESC("catchball1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// default step1 record
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0;i<recordm.size();i++) {
			recordModel rm = recordm.get(i);
			top10Record[i] = Integer.toString(rm.getRecord());
			top10Date[i] = rm.getDate();
		}
		for(int i = recordm.size();i<10;i++) {
			top10Record[i] = "";
			top10Date[i] = "";
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
		List<recordModel> recordm =  new ArrayList<recordModel>();
		try {
			recordm = db.getTopRecordDESC("catchball1");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0;i<recordm.size();i++) {
			recordModel rm = recordm.get(i);
			top10Record[i] = Integer.toString(rm.getRecord());
			top10Date[i] = rm.getDate();
		}
		for(int i = recordm.size();i<10;i++) {
			top10Record[i] = "";
			top10Date[i] = "";
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
		List<recordModel> recordm =  new ArrayList<recordModel>();
		try {
			recordm = db.getTopRecordDESC("catchball2");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0;i<recordm.size();i++) {
			recordModel rm = recordm.get(i);
			top10Record[i] = Integer.toString(rm.getRecord());
			top10Date[i] = rm.getDate();
		}
		for(int i = recordm.size();i<10;i++) {
			top10Record[i] = "";
			top10Date[i] = "";
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
		List<recordModel> recordm =  new ArrayList<recordModel>();
		try {
			recordm = db.getTopRecordDESC("catchball3");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String top10Record[] = new String[10];
		String top10Date[] = new String[10];
		// db에서 값 가져와야 합니다. 임의의 값으로 일단 함
		for(int i = 0;i<recordm.size();i++) {
			recordModel rm = recordm.get(i);
			top10Record[i] = Integer.toString(rm.getRecord());
			top10Date[i] = rm.getDate();
		}
		for(int i = recordm.size();i<10;i++) {
			top10Record[i] = "";
			top10Date[i] = "";
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
