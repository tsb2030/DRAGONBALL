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
	private Model model;	//�� ��ü
	private PathTransition pathTransition;	//Path��ü�� ���� �ִϸ��̼��� ������ Ʈ������
	private boolean pause_control = true;	//�Ͻ������� ������ ��Ʈ������ ����
	
	@FXML private Circle pointer;

	//�ڷΰ��� ��ư �̺�Ʈ
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

	//���콺 Ŭ�� �̺�Ʈ - �� Ŭ����
	@FXML
	public void moving2(MouseEvent event) {

		pointX = (int)pointer.getLayoutX();
		pointY = (int)pointer.getLayoutY();
		System.out.println("���� ���� ��ǥ: ( "+pointX+", "+pointY+" )");

		//����콺 �� ����� ��� �����
		Path path = new Path();
		path.getElements().add(new MoveTo(0, 0));	//�̵��� ������
		path.getElements().add(new ArcTo(200, 250, 180, -500, 0, true, false));
		path.getElements().add(new ArcTo(200, 250, 180, 0, 0, true, false));
		path.getElements().add(new ArcTo(200, 250, 180, 500, 0, true, true));
		path.getElements().add(new ArcTo(200, 250, 180, 0, 0, true, true));
		
		// Set up a Path Transition
		pathTransition = new PathTransition(Duration.seconds(4), path, pointer);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		// Let the animation run times
		pathTransition.setCycleCount(2);	//���� ȸ�� Ƚ�� ��Ʈ��. FadeTransition.INDEFINITE�� �Է��ϸ� ���ѹݺ�
	
		// Reverse direction on alternating cycles
		pathTransition.setAutoReverse(false);	//true : �ѹ��� ȸ���� ���� ������ ������ �ٲ۴�
	
		// Play the Animation
		pathTransition.play();
		if(pathTransition.getStatus() == Status.RUNNING)
			pause_control = true;
		//pathTransition.

	}
	//���� ���� ����ִ� ����
	@FXML
	private void colorPop() {

	}

}
