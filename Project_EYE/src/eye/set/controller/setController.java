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
//	int time[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
	@FXML ComboBox combobox1, combobox2, combobox3;
	ObservableList<Integer> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i=0; i<24; i++) {
			list.add(i);
		}
		combobox1.setItems(list);
		combobox2.setItems(list);
	}
}
