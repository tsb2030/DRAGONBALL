package eye.set.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.game.catchMole.DodugeGameController;
import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	
    @FXML
    void doAlarmAction(ActionEvent event) throws IOException {
    	if(setController.restType == 1) {//short
    		setController.isPause = false;	//뒤에 pause상태를 풀어준다.
    		setController.totalRestCount = 3;
    		//현재 창을 닫는다.
    		Stage stage = (Stage)doAlarm.getScene().getWindow();
			stage.close();
			
			
			//주사위를 돌린다.
    		setController.playShorRest();
    		
    	}else if(setController.restType == 2) {
    		setController.isPause = false;
    		setController.totalRestCount = 5;
    		//현재 창을 닫는다.
    		Stage stage = (Stage)doAlarm.getScene().getWindow();
			stage.close();
			
			
    		setController.playLongRest();
    	}else {
    		setController.isPause = false;
    		
    		//현재 창을 닫는다.
    		Stage stage = (Stage)doAlarm.getScene().getWindow();
			stage.close();
			
			
    		setController.playRotateRest();
    	}
    }
    
    @FXML
    void doNotAlarmAction(ActionEvent event) {
		setController.isPause = false;
		
		//현재 창을 닫는다.
		Stage stage = (Stage)doAlarm.getScene().getWindow();
		stage.close();
		
    }
    
    @FXML
    void noMoreAlarmAction(ActionEvent event) {
    	setController.isPause = false;
    	setController.isStop = true;
    	//현재 창을 닫는다.
		Stage stage = (Stage)doAlarm.getScene().getWindow();
		stage.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
