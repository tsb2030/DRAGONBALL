package eye.game.eyeMovement2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EndPopUpcontroller implements Initializable{

	@FXML
	private Button closeBtn;

	@FXML
	private Button restartPopUpBtn;
	
    @FXML
    private TextField answer;
    
    

	@FXML
	void closePopUp(ActionEvent event) {

	}

	@FXML
	void numGameRestart(ActionEvent event) {

	}
	
	public void ans() {
		String getAnswer = answer.getText();
		int ganswer = Integer.parseInt(getAnswer);
		if(Playcontroller.cnt == ganswer) {
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ans();
	}

}
