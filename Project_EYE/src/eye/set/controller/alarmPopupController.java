package eye.set.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class alarmPopupController implements Initializable{
	
	@FXML
    private AnchorPane alarmPopupPage;

    @FXML
    private Button doAlarm;

    @FXML
    private Button doNotAlarm;
    Parent parent;
	Scene scene;
	
	//알람으로 인한 휴식 진행
    @FXML
    void doAlarmAction(ActionEvent event) throws IOException {
    	if(setController.restType == 1) {//short
    		setController.isPause = false;	//뒤에 pause상태를 풀어준다.
    		setController.totalRestCount = 3;
    		//현재 창을 닫는다.
    		Stage stage = (Stage)doAlarm.getScene().getWindow();
			stage.close();
			
			
			//짧은 휴식 알람을 진행한다.
    		setController.playShorRest();
    		
    	}else if(setController.restType == 2) {
    		setController.isPause = false;
    		setController.totalRestCount = 5;
    		//현재 창을 닫는다.
    		Stage stage = (Stage)doAlarm.getScene().getWindow();
			stage.close();
			
			//긴 휴식을 한다.
    		setController.playLongRest();
    	}else {
    		setController.isPause = false;
    		
    		//현재 창을 닫는다.
    		Stage stage = (Stage)doAlarm.getScene().getWindow();
			stage.close();
			
			// 랜덤으로 휴식을 한다.
    		setController.playRotateRest();
    	}
    }
    
    // 지금 한 번은 휴식을 하지 않는다.
    @FXML
    void doNotAlarmAction(ActionEvent event) {
		setController.isPause = false;
		
		//현재 창을 닫는다.
		Stage stage = (Stage)doAlarm.getScene().getWindow();
		stage.close();
		
    }
    
    //더이상 알람 창을 뜨지 않게 한다.
    @FXML
    void noMoreAlarmAction(ActionEvent event) {
    	setController.isPause = false;
    	setController.isStop = true;
    	//현재 창을 닫는다.
		Stage stage = (Stage)doAlarm.getScene().getWindow();
		stage.close();
    }
    
    //초기화 할때 추가할 사항
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
