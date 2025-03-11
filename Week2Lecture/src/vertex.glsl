//this was made as an example of how we will create and organise glsl files, eclipse just thinks this is
// a file, without knowing its GLSL

//First tell the GPU what version we are using.
#version 410

//first in the vertex shader, put an IN (Attribute)
//attributes are passing a value per vertex
//thats why we pass in attribute cause its different for every vertex 

in vec4 a_position; //vertex position - (X,Y,Z,W) //NOTE DOWN the axis the attributes are in 
in vec3 a_colour; //vertex colour (R,G,B) //we got rid of this
out vec3 v_colour; //RGB to the fragment shader

void main(){
	gl_Position = a_position;
	//gl position is a built in variable, in open gl, means "position of this vertice"
	// for every vertex we are setting the gl position through the a position
	
	v_colour = a_colour; //putting a colour into v colour
}