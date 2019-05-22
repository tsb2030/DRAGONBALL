package eye.set.controller;
//설정 페이지 컨트롤러

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class setController implements Initializable{
	String timeInterval[] = {"30분", "1시간 30분", "2시간", "2시간 30분", "3시간", "3시간 30분", "4시간", "4시간 30분", "5시간"};

	@FXML ComboBox combobox1, combobox2, combobox3;

	ObservableList<Integer> timeList = FXCollections.observableArrayList();
	ObservableList<String> intervalList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i=0; i<24; i++) {
			timeList.add(i);
		}
		for(int i=0; i<timeInterval.length; i++) {
			intervalList.add(timeInterval[i]);
		}
		//방해금지 시간 콤보박스에  0~23시 추가
		combobox1.setItems(timeList);
		combobox2.setItems(timeList);
		//실행주기 간격 콤보박스에 timeInterval배열 값들 추가
		combobox3.setItems(intervalList);
	}
}
