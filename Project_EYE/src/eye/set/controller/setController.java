package eye.set.controller;
//설정 페이지 컨트롤러

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;

import eye.main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class setController implements Initializable {
	@FXML
	private AnchorPane ExplainPage;
	
	String timeInterval[] = { "30분", "1시간", "1시간 30분", "2시간", "2시간 30분", "3시간", "3시간 30분", "4시간", "4시간 30분", "5시간" };
	
	Clock clock;
	public static LocalTime currentTime = LocalTime.now();
	public static LocalDate currentDate = LocalDate.now();
	int nextDay = currentDate.getDayOfMonth();
	int today = currentDate.getDayOfMonth();
	

	private ArrayList<Integer> restList = new ArrayList<Integer>();
	int restCycle = 0;
	int startDisturbTime;
	int endDisturbTime;
	private int varStartDisturbTime;
	private int varEndDisturbTime;
	
	
	int restType;
	boolean rotateFlag = false;
	boolean BGMFlag = false;
	boolean effectFlag = false;
	boolean flag = false;// 게임이 진행 중인지 받아올 변수
	boolean tomarrowFlag = false;
	// 만약 운동중에 휴식 알람을 울려야 한다면
	public static boolean maintainRestEvent = false;

	@FXML
	private JFXComboBox<Integer> combobox1;

	@FXML
	private JFXComboBox<Integer> combobox2;

	@FXML
	private JFXComboBox<String> combobox3;

//	@FXML
//	ComboBox<Integer> combobox1, combobox2;
//	@FXML
//	ComboBox<String> combobox3;

	@FXML
	private JFXToggleButton BGMToggle;

	@FXML
	private JFXToggleButton effectToggle;

	ObservableList<Integer> timeList = FXCollections.observableArrayList();
	ObservableList<String> intervalList = FXCollections.observableArrayList();

	@FXML
	void submit(MouseEvent event) {
		System.out.println("submit");

		if(varEndDisturbTime == 0)					//기본 알람 방지 시작 시간 = 9(저녁 9시)
			varEndDisturbTime = 21;
		if(varStartDisturbTime == 0)				//기본 알람 방지 종료 시간 = 8(다음날)
			varStartDisturbTime = 8;
		if(restCycle == 0)							//기본 알람 주기 = 3시간
			restCycle = 6;
		
		if (varStartDisturbTime > varEndDisturbTime) {	//다음 날짜로 알람이 설정 되었는지?
			tomarrowFlag = true;
			today = currentDate.getDayOfMonth();
			nextDay = currentDate.getDayOfMonth() + 1;
		}
		startDisturbTime = varStartDisturbTime;
		endDisturbTime = varEndDisturbTime;
		
		if (clock != null) {
			clock.animation.stop();
			clock = null;
		}

		clock = new Clock();
		
		/*
		 * 
		 * 창을 이전 페이지(main)페이지로 가게 함
		 */
	}
	
	@FXML
	void backButtonAction(MouseEvent event) throws IOException {
		Main.setMusic("introMusic", true);
		Parent mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
		ExplainPage.getChildren().setAll(mainPage);
	}

	// shorRestplay
	public void playShorRest() {
		double ran = Math.random();
		int intValue = (int) (ran * 5) + 1;

		// 이미 배열에 차있으면 다른 변수를 가져오도록 한다.
		while (restList.contains(intValue))
			intValue = (int) (ran * 5) + 1;

		switch (intValue) {
		case 1:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(1);
			break;
		case 2:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(2);
			break;
		case 3:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(3);
			break;
		case 4:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(4);
			break;
		case 5:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(5);
			break;

		default:
			isSizeFull();
			break;
		}
	}

	// 만약 배열의 크기가 다 찼을 경우( == 5개의 휴식을 모두 취했을 경우) 배열을 비워두도록 한다.
	public void isSizeFull() {
		if (restList.size() == 5)
			restList.clear();
	}

	// LongRestEvent!!
	public void playLongRest() {
		// fadeOutRest.fxml로 이동!
	}

	// RotateRestEvent!!
	public void playRotateRest() {
		if (rotateFlag == false) {
			playShorRest();
			rotateFlag = true;
		} else {
			playLongRest();
			rotateFlag = false;
		}
	}

	@FXML
	void restTypeIsShort(MouseEvent event) {
		restType = 1;
		System.out.println("resType = short");
	}

	@FXML
	void restTypeIsLong(MouseEvent event) {
		restType = 2;
		System.out.println("resType = long");
	}

	@FXML
	void restTypeIsRotate(MouseEvent event) {
		restType = 3;
		System.out.println("resType = rotate");
	}

	// 휴식 이벤트 시작
	public void playRest(int restType) {
		switch (restType) {

		// ShortEvent
		case 1:
			playShorRest();
			System.out.println("play short Rest");
			break;

		// LongEvent
		case 2:
			playLongRest();
			System.out.println("play long Rest");
			break;

		// RotateEvent!
		case 3:
			playRotateRest();
			System.out.println("play rotate Rest");
			break;

		default:
			break;
		}
	}

	// 일단 오류때문에 사용금지
	public void setToggleColors() {
		System.out.println("change Color");
		Color firstBGMLUnToggleColor = (Color) BGMToggle.getUnToggleColor();
		Color firstBGMLUnToggleLineColor = (Color) BGMToggle.getUnToggleLineColor();
		Color firstBGMLToggleColor = (Color) BGMToggle.getToggleColor();
		Color firstBGMLToggleLineColor = (Color) BGMToggle.getToggleLineColor();

		Color firstEffectLUnToggleColor = (Color) effectToggle.getUnToggleColor();
		Color firstEffectLUnToggleLineColor = (Color) effectToggle.getUnToggleLineColor();
		Color firstEffectLToggleColor = (Color) effectToggle.getToggleColor();
		Color firstEffectLToggleLineColor = (Color) effectToggle.getToggleLineColor();

		BGMToggle.setUnToggleColor(firstBGMLToggleColor);
		BGMToggle.setUnToggleLineColor(firstBGMLToggleLineColor);
		BGMToggle.setToggleColor(firstBGMLUnToggleColor);
		BGMToggle.setToggleLineColor(firstBGMLUnToggleLineColor);

		effectToggle.setUnToggleColor(firstEffectLToggleColor);
		effectToggle.setUnToggleLineColor(firstEffectLToggleLineColor);
		effectToggle.setToggleColor(firstEffectLUnToggleColor);
		effectToggle.setToggleLineColor(firstEffectLUnToggleLineColor);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 처음에 켜진 상태이기 때문에 바꿈..
		setToggleColors();

		for (int i = 0; i < 24; i++) {
			timeList.add(i);
		}
		for (int i = 0; i < timeInterval.length; i++) {
			intervalList.add(timeInterval[i]);
		}
		// 방해금지 시간 콤보박스에 0~23시 추가
		combobox1.setItems(timeList);
		combobox2.setItems(timeList);
		// 실행주기 간격 콤보박스에 timeInterval배열 값들 추가
		combobox3.setItems(intervalList);
	}

	/**
	 * 
	 * t --> BGMToggleFlag
	 */
	@FXML
	void BGMToggleAction(ActionEvent event)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (BGMFlag == false) {
			System.out.println("BGM music close");
			Main.closeMusic();
			BGMFlag = true;
		} else {
			System.out.println("BGM music restart");
			Main.reStartMusic(true);
			BGMFlag = false;
		}
	}

	/**
	 * 
	 * 위랑 같이 변경
	 */
	@FXML
	void effectToggleAction(ActionEvent event) {
//		if (effectFlag == false) {
//			System.out.println("effect music close");
//			Main.closeMusic();
//			effectFlag = true;
//		} else {
//			System.out.println("effect music restart");
//			Main.reStartMusic(true);
//			effectFlag = false;
//		}
	}

	@FXML
	void cancel(MouseEvent event) {
		System.out.println("cancel");
	}

	@FXML
	void cancelText(MouseEvent event) {

		System.out.println("cancel");
	}

	

	// 방해금지 종료시간 설정
	@FXML
	void setEndDisturbTime(ActionEvent event) {
		varEndDisturbTime = (int) combobox2.getValue();
	}

	// 방해금지 시작시간 설정
	@FXML
	void setStartDisturbTime(ActionEvent event) {
		varStartDisturbTime = (int) combobox1.getValue();
	}

	// 실행 주기 설정
	@FXML
	void setRestCycle(ActionEvent event) {
		String s = (String) combobox3.getValue();
		System.out.println(s);
		findOne(s);
	}

	/**
	 * 
	 * 입력된 값에 따라 integer값을 restCycle에 저장해 둔다.
	 */
	public void findOne(String s) {
		switch (s) {
		case "30":
			restCycle = 1;
			break;
		case "1시간":
			restCycle = 2;
			break;
		case "1시간 30분":
			restCycle = 3;
			break;
		case "2시간":
			restCycle = 4;
			break;
		case "2시간 30분":
			restCycle = 5;
			break;
		case "3시간":
			restCycle = 6;
			break;
		case "3시간 30분":
			restCycle = 7;
			break;
		case "4시간":
			restCycle = 8;
			break;
		case "4시간 30분":
			restCycle = 9;
			break;
		case "5시간":
			restCycle = 10;
			break;

		default:
			break;
		}
	}

	public class Clock extends Pane {

		int currentDateDay = currentDate.getDayOfMonth();
		int currnetTimeHour = currentTime.getHour();
		private Timeline animation;
		private int timeTmp = 1;

		public Clock() {
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

				// 시간이 째깍 흘러갑니다.
				timeLabel();

				// 방해금지 시간이 아니라면!
				System.out.println("현재 날짜: " + currentDateDay);
				System.out.println("현재 시각 : " + currnetTimeHour);
				System.out.println("방해 시작 시각: " + startDisturbTime);
				System.out.println("방해 종료 시각: " + endDisturbTime);
				System.out.println("현재 초 : " + currentTime.getSecond());
				if (maintainRestEvent) {
					if(flag == false) {
						playRest(restType);
						maintainRestEvent = false;
					}
				}
				// 알람이 내일까지로 설정되어있는지?
				if (tomarrowFlag) {
					if (!((currnetTimeHour > startDisturbTime)
							|| ((nextDay == currentDate.getDayOfMonth()) && (currnetTimeHour < endDisturbTime))))
						if (restCycle != 0) {
							// 현재 휴식 프로그램을 실행 중인가? 설정 안했으면 false
							if (!flag) {
								if (timeTmp % (restCycle * 1800) == 0) {
									playRest(restType);
								}
							} else {
								maintainRestEvent = true;
							}
						}
				} else {
					if (!((currnetTimeHour > startDisturbTime) && (currnetTimeHour < endDisturbTime))) {
						// 알람 설정 주기를 설정하였는가?
						if (restCycle != 0) {
							// 현재 휴식 프로그램을 실행 중인가? 설정 안했으면 false
							System.out.println("flag가 false면알람 주기 시작" + flag);
							if (!flag) {
								if (timeTmp % (restCycle * 1800) == 0) {
									playRest(restType);
								}
							} else {
								maintainRestEvent = true;

								// 게임 끝나고 실행 할 수 있게 만들기
								// 게임이 종료될 때마다 if문을 추가해서 여기서 if문을 실행 했다면 해당 변수를 true값을 바꿔저서 그 게임잉 종료된후 여기에 휴식
								// 프로그램을 실행 할 수 있도록 구현한다.
							}
						}
					}
				}
			}));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabel() {
			timeTmp++;
		}

		public int getTime() {
			return timeTmp;
		}
	}
}
