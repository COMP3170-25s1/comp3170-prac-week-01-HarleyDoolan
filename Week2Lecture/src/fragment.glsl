#version 410

uniform vec3 u_colour; //colour as a 3d vector(r,g,b)
//no longer uniform, became
in vec3 v_colour; //colour from vertex shader as a 3d vector (r,g,b,a)

layout(location = 0) out vec4 o_colour; //(r,g,b,a) //IN THROUGH VERTEX OUT THROUGH FRAGMENT

void main(){
	o_colour = vec4(v_colour, 1); //1 makes it completely opaque, 0 is transparent
}

