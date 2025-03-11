package COMP3170.lectures.week2;

import org.joml.Vector3f;
import org.joml.Vector4f; //know what you are importing in

import static org.lwjgl.opengl.GL15.glDrawArrays;
import static org.lwjgl.opengl.GL15.glDrawElements;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.GL_TRIANGLES; //still using triangles
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_UNSIGNED_INT;
//the GL15 stands for the version

import comp3170.GLBuffers;
import comp3170.Shader;
import comp3170.ShaderLibrary;

public class House {
	//define shaders to be used
	final private String VERTEX_SHADER = "vertex.glsl";
	final private String FRAGMENT_SHADER = "fragment.glsl";
	
	private Vector4f[] vertices; //An array of points that make up the house
	private int vertexBuffer;
	private int[]indices;
	private int indexBuffer;
	
	private Vector3f[] colours;
	private int colourBuffer;
	
	private Shader shader;
	
	private Vector3f colour = new Vector3f(1.0f, 0.7f, 1.0f); //LILAC
	
	public House() {
		//compile the shader
		shader = ShaderLibrary.instance.compileShader(VERTEX_SHADER, FRAGMENT_SHADER);
		
		//vertices as (x,y) pairs
		
		// @formatter: off ???
		//stops auto formatting
		
		//easier to make it a whole array?
		vertices = new Vector4f[] {
				new Vector4f(-0.8f, -0.8f, 0.0f, 1.0f), //bottom left corner //0
				new Vector4f(0.8f, -0.8f, 0.0f, 1.0f), //bottom right corner //1
				
				new Vector4f(0.8f, 0.4f, 0.0f, 1.0f), //top right corner //2
				new Vector4f(-0.8f, 0.4f, 0.0f, 1.0f), //top left corner //3
				new Vector4f(0f, 0.8f, 0.0f, 1.0f), //peak //4
					
		};
		
		//use the indices to define the vertices in a row
		indices = new int[]{
				0,1,3, //Tri 1 //a triangle made from the points 0, 1, 2 which were drawn on a page
				1,2,3, //Tri 2
				2,3,4, //Tri3
		};
		//this is made from the points in order of the vertices vector array
		
		colours = new Vector3f[] {
				new Vector3f(1.0f, 0f, 0.1f), //bottom left corner //0
				new Vector3f(0.8f, 0.3f, 0.15f), //bottom right corner //1
				
				new Vector3f(1.0f, 0.8f, 0.5f), //top right corner //2
				new Vector3f(0.75f, 1.0f, 0.0f), //top left corner //3
				
				new Vector3f(1.0f, 0.5f, 0.0f), //peak //4
		};
		
		// @formatter:on 
		
		//I THINK THESE 2 TOGETHER IS HOW IT KNOWS THE ORDER OF VERTICES
		vertexBuffer = GLBuffers.createBuffer(vertices);
		indexBuffer = GLBuffers.createIndexBuffer(indices);
		colourBuffer = GLBuffers.createBuffer(colours);
		// NEED MORE UNDERSTANDING OF THESE
		
	}
	
	public void draw() {
		shader.enable();
		
		shader.setAttribute("a_position", vertexBuffer); //sets the attributes before the vertex.glsl?
		//same for colour
		shader.setAttribute("a_colour", colourBuffer);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
		
		//shader.setUniform("_colour", colour); using attributes
		
		//glLineWidth(10f); //changes line width
		//GL_TRIANGLES IS HOW ITS DRAWN, CAN REPLACE WITH GL_LINES 
		glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
		
	}

}
