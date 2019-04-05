package application;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
//	@FXML
//	private Button myMessage;
	@FXML
	private Button btn00;
	@FXML
	private Button btn01;
	@FXML
	private Button btn02;
	@FXML
	private Button btn03;
	@FXML
	private Button btn04;
	@FXML
	private Button btn05;
	@FXML
	private Button btn06;
	@FXML
	private Button btn10;
	@FXML
	private Button btn11;
	@FXML
	private Button btn12;
	@FXML
	private Button btn13;
	@FXML
	private Button btn14;
	@FXML
	private Button btn15;
	@FXML
	private Button btn16;
	@FXML
	private Button btn20;
	@FXML
	private Button btn21;
	@FXML
	private Button btn22;
	@FXML
	private Button btn23;
	@FXML
	private Button btn24;
	@FXML
	private Button btn25;
	@FXML
	private Button btn26;
	@FXML
	private Button btn31;
	@FXML
	private Button btn32;
	@FXML
	private Button btn33;
	@FXML
	private Button btn34;
	@FXML
	private Button btn35;
	
	
	// 1~50 사이의 숫자를 선택하는 랜덤 메소드를 만듬.
	// 이 메소드는 액션 이벤트가 발생할 때 작동함.
	// Scene Builder에서 컨트롤러와 이벤트를 모두 걸어줘야 함.
//	public void generateRandom(ActionEvent event) {
//		Random rand = new Random();
//		int myrand = rand.nextInt(50) + 1;
//		myMessage.setText(Integer.toString(myrand));
//	}

	public void generateRandom(ActionEvent event) {
		Random rand = new Random();
		String[] alphabetList = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		int randomIndex;
		String alphaValue = null;
		int count = 0; // 몇 개가 나왔는지 횟수 카운트
		String usedAlphabet[] = new String[26];
		int reCount = 0;
		
		while (true) {
			randomIndex = rand.nextInt(26); // 0~25
			if (alphabetList[randomIndex] != null) {
				alphaValue = alphabetList[randomIndex];
				alphabetList[randomIndex] = null;
				usedAlphabet[count] = alphaValue;
				count++;
			}
			if (count == 26) {
				break;
			}
		}

		// 각 버튼에 값 하나씩 넣어주기
		btn00.setText(usedAlphabet[reCount++]);
		btn01.setText(usedAlphabet[reCount++]);
		btn02.setText(usedAlphabet[reCount++]);
		btn03.setText(usedAlphabet[reCount++]);
		btn04.setText(usedAlphabet[reCount++]);
		btn05.setText(usedAlphabet[reCount++]);
		btn06.setText(usedAlphabet[reCount++]);
		btn10.setText(usedAlphabet[reCount++]);
		btn11.setText(usedAlphabet[reCount++]);
		btn12.setText(usedAlphabet[reCount++]);
		btn13.setText(usedAlphabet[reCount++]);
		btn14.setText(usedAlphabet[reCount++]);
		btn15.setText(usedAlphabet[reCount++]);
		btn16.setText(usedAlphabet[reCount++]);
		btn20.setText(usedAlphabet[reCount++]);
		btn21.setText(usedAlphabet[reCount++]);
		btn22.setText(usedAlphabet[reCount++]);
		btn23.setText(usedAlphabet[reCount++]);
		btn24.setText(usedAlphabet[reCount++]);
		btn25.setText(usedAlphabet[reCount++]);
		btn26.setText(usedAlphabet[reCount++]);
		btn31.setText(usedAlphabet[reCount++]);
		btn32.setText(usedAlphabet[reCount++]);
		btn33.setText(usedAlphabet[reCount++]);
		btn34.setText(usedAlphabet[reCount++]);
		btn35.setText(usedAlphabet[reCount++]);
	}

	// 누르면 문자가 사라지게 하기
	public void invisibleAlphabet00(ActionEvent event) {
		btn00.setText("");
	}

	public void invisibleAlphabet01(ActionEvent event) {
		btn01.setText("");
	}

	public void invisibleAlphabet02(ActionEvent event) {
		btn02.setText("");
	}

	public void invisibleAlphabet03(ActionEvent event) {
		btn03.setText("");
	}

	public void invisibleAlphabet04(ActionEvent event) {
		btn04.setText("");
	}

	public void invisibleAlphabet05(ActionEvent event) {
		btn05.setText("");
	}

	public void invisibleAlphabet06(ActionEvent event) {
		btn06.setText("");
	}

	public void invisibleAlphabet10(ActionEvent event) {
		btn10.setText("");
	}

	public void invisibleAlphabet11(ActionEvent event) {
		btn11.setText("");
	}

	public void invisibleAlphabet12(ActionEvent event) {
		btn12.setText("");
	}

	public void invisibleAlphabet13(ActionEvent event) {
		btn13.setText("");
	}

	public void invisibleAlphabet14(ActionEvent event) {
		btn14.setText("");
	}

	public void invisibleAlphabet15(ActionEvent event) {
		btn15.setText("");
	}

	public void invisibleAlphabet16(ActionEvent event) {
		btn16.setText("");
	}

	public void invisibleAlphabet20(ActionEvent event) {
		btn20.setText("");
	}

	public void invisibleAlphabet21(ActionEvent event) {
		btn21.setText("");
	}

	public void invisibleAlphabet22(ActionEvent event) {
		btn22.setText("");
	}

	public void invisibleAlphabet23(ActionEvent event) {
		btn23.setText("");
	}

	public void invisibleAlphabet24(ActionEvent event) {
		btn24.setText("");
	}

	public void invisibleAlphabet25(ActionEvent event) {
		btn25.setText("");
	}

	public void invisibleAlphabet26(ActionEvent event) {
		btn26.setText("");
	}

	public void invisibleAlphabet31(ActionEvent event) {
		btn31.setText("");
	}

	public void invisibleAlphabet32(ActionEvent event) {
		btn32.setText("");
	}

	public void invisibleAlphabet33(ActionEvent event) {
		btn33.setText("");
	}

	public void invisibleAlphabet34(ActionEvent event) {
		btn34.setText("");
	}

	public void invisibleAlphabet35(ActionEvent event) {
		btn35.setText("");
	}
}
