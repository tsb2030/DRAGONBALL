package strip.test.model;

import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

//����콺��ó�� ������ �� �ְ� ���� ��ǥ�� ����Ѵ�. 
public class Model {

	private int direction = 0; //������ �ǹ�. 0�� ���� , 1�� ������.
	private int x, y;

	public Model(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() { return x;	}

	public void setX(int x) { this.x = x;	}

	public double getY() {	return y;	}

	public void setY(int y) {	this.y = y;	}
	
	public void calculate() {

		if(x >= 100 || x <= 700) {
			if(x >= 225){

				if(y <= 300) {
					--x;
					++y;
				}
				else {
					++x;
					--y;
				}
			}			
			else{
				if(y <= 300) {
					x--;
					y--;
				}
				else {
					x++;
					y++;
				}
			}
			if(x <= 575){

				if(y <= 300) {
					x++;
					y--;
				}
				else {
					x--;
					y--;
				}
			}			
			else{

				if(y <= 300) {
					x++;
					y++;
				}
				else {
					x--;
					y++;
				}
			}
		}
	}

	public void createPath() {
		Path path  = new Path();
		path.getElements().add(new MoveTo(0,0));
		
	}

}
