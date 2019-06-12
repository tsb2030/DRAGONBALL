package eye.record.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import eye.db.*;
import eye.record.model.recordModel;
public class CatchMoleRecordController implements Initializable {
	
	dbconn db = new dbconn();
	
	@FXML
	AnchorPane catchMoleRecord;
	
	@FXML
	Label record1,record2,record3,record4,record5,record6,record7,record8,record9,record10;
	
	@FXML
	Label date1,date2,date3,date4,date5,date6,date7,date8,date9,date10;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<recordModel> recordm =  new ArrayList<recordModel>();
		try {
			recordm = db.getTopRecordDESC("catchMole");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// record
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
		
		Stage currentStage = (Stage) catchMoleRecord.getScene().getWindow();
		currentStage.close();
		
	}
	

}
