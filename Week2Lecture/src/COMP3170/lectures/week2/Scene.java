package COMP3170.lectures.week2;

public class Scene {

	//we want to have an object be spawned by the scene
	
	private House house;
	
	public Scene() {
		house = new House();
	}
	
	public void draw() {
		house.draw();
	}

}
