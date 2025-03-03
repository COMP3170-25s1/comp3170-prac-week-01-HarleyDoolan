package comp3170.week1;


import java.io.File;
import java.io.IOException;


import org.lwjgl.opengl.GLCapabilities;
import static org.lwjgl.opengl.GL41.*;


import comp3170.GLBuffers;
import comp3170.IWindowListener;
import comp3170.Shader;
import comp3170.ShaderLibrary;
import comp3170.Window;
import comp3170.OpenGLException;

public class Week1 implements  IWindowListener  {

	private Window window;
	private Shader shader;
	
	final private File DIRECTORY = new File("src/comp3170/week1"); 
	final private String VERTEX_SHADER = "vertex.glsl";
	final private String FRAGMENT_SHADER = "fragment.glsl";

	private float[] vertices;
	private int vertexBuffer;
	
	private int screenWidth = 800;
	private int screenHeight = 800;
	private Scene scene;


	public Week1() throws OpenGLException {
		
		// create window with title, size, and a listener (this)
		window = new Window("Week 1 prac", screenWidth, screenHeight, this);
		// make the window resizable
		window.setResizable(true);
		// start running the window
		window.run();
	}

	@Override
	/**
	 * Initialise the Window
	 */
	public void init() {
		
		ShaderLibrary shaderLibrary = new ShaderLibrary(DIRECTORY); // Creates a shader library which points to the directory shaders will be stored in.
		
		scene = new Scene(screenWidth,screenHeight); // Creates the scene. This is where we will be drawing things!
		//THIS IS WHAT WE PUT ALL THE OBJECT IN, CONTAINS CAMERA POSITIONS ROTATIONS ETC
		}

		
	@Override
	/**
	 * Called when the window is resized
	 */
	public void draw() {
		
        // clear the colour buffer
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // BLACK
		glClear(GL_COLOR_BUFFER_BIT);		
		
		// draw the scene
		scene.draw(screenWidth,screenHeight);	
	}

	@Override
	/**
	 * Called when the window is resized
	 */
	public void resize(int width, int height) {
		// record the new width and height
		this.screenWidth = width;
		this.screenHeight = height;
		glViewport(0,0,width,height);
	}
	
	@Override
	/**
	 * Called when we close the window
	 */
	public void close() {
		// nothing to do
	}

	public static void main(String[] args) throws IOException, OpenGLException {
		new Week1();
	}

}
