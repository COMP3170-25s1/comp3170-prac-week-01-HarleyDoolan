package COMP3170.lectures.week2;
//PACKAGE 

//THESE IMPORTS WERE TAKEN FROM ANOTHER WEEK
//THESE WILL HAVE A YELLOW UNDERLINE IF THEY ARENT USED
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glViewport;

import java.io.File;
import java.io.IOException;

import org.joml.Vector4f;

import comp3170.IWindowListener;
import comp3170.OpenGLException;
import comp3170.ShaderLibrary; //creates shaders
import comp3170.Window;

public class Week2Lecture implements IWindowListener{
	
	private int screenWidth=500;
	private int screenHeight=500;
	private Vector4f clearColour = new Vector4f(0.0f, 0.0f, 0.5f, 1.0f);
	//vec 4 is downloaded from a java package?
	
	//Directory for shader library
	final private File DIRECTORY = new File("src/");
	//this BUILDS THE SHADER LIBRARY HERE AT SRC
	
	//WE DONT WANT TO BUILD OBJECTS IN THIS JAVA WE WANT TO CREATE A SCENE TO BUILD OBJECTS
	private Scene scene;
	
	public Week2Lecture() throws OpenGLException {
		//create window with a title, a size, and a listener(this)
		Window window = new Window("House With A Gradient On Its Walls", screenWidth, screenHeight, this);
		
		//start running the window
		window.run();
	}
	
	public static void main(String[] args) throws OpenGLException{
		new Week2Lecture();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//MAKE SURE TO GET RID OF TODO's
		
		new ShaderLibrary(DIRECTORY);
		//imports shader library thats premade
		//its a singelton so its easy to construct
		
		//set background colour to white
		//glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glClearColor(clearColour.x, clearColour.y, clearColour.z, clearColour.w);
		//WE WERE RUNNING THE COLOUR EVERY DRAW CALL, NOT GOOD, SO WE MOVED TO INIT
		
		scene = new Scene();
	}

	@Override
	public void draw() {
		//CLEAR THE SCREEN, CANT HAVE BLANK BUT CAN HAVE BLACK
		glClear(GL_COLOR_BUFFER_BIT);
		scene.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
