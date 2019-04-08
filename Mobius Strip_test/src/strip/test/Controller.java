package strip.test;

import javafx.animation.Animation.Status;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import strip.test.model.Model;

public class Controller {

	private int pointX = 0;
	private int pointY = 0;
	private Model model;	//모델 객체
	private PathTransition pathTransition;	//Path객체를 따라 애니메이션을 동작할 트랜지션
	private boolean pause_control = true;	//일시정지와 실행을 컨트롤해줄 변수
	
	@FXML private Circle pointer;

	//뒤로가기 버튼 이벤트
	@FXML
	public void pause(ActionEvent event) {
		if(pause_control == true) {
			pathTransition.pause();
			pause_control = false;
			System.out.println(pathTransition.getStatus());
		}
			
		else if(pause_control == false) {
			pathTransition.play();
			pause_control = true;
		}
			
	}

	//마우스 클릭 이벤트 - 공 클릭시
	@FXML
	public void moving2(MouseEvent event) {

		pointX = (int)pointer.getLayoutX();
		pointY = (int)pointer.getLayoutY();
		System.out.println("공의 현재 좌표: ( "+pointX+", "+pointY+" )");

		//뫼비우스 띠 모양의 경로 만들기
		Path path = new Path();
		path.getElements().add(new MoveTo(0, 0));	//이동의 시작점
		path.getElements().add(new ArcTo(200, 250, 180, -500, 0, true, false));
		path.getElements().add(new ArcTo(200, 250, 180, 0, 0, true, false));
		path.getElements().add(new ArcTo(200, 250, 180, 500, 0, true, true));
		path.getElements().add(new ArcTo(200, 250, 180, 0, 0, true, true));
		
		// Set up a Path Transition
		pathTransition = new PathTransition(Duration.seconds(4), path, pointer);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		// Let the animation run times
		pathTransition.setCycleCount(2);	//공의 회전 횟수 컨트롤. FadeTransition.INDEFINITE를 입력하면 무한반복
	
		// Reverse direction on alternating cycles
		pathTransition.setAutoReverse(false);	//true : 한번의 회전이 끝날 때마다 방향을 바꾼다
	
		// Play the Animation
		pathTransition.play();
		if(pathTransition.getStatus() == Status.RUNNING)
			pause_control = true;
		//pathTransition.

	}
	//색깔 원을 띄워주는 역할
	@FXML
	private void colorPop() {

	}

}
