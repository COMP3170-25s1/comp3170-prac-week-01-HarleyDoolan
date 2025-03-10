#version 410
//what does uniform mean? 
//	In GLSL (OpenGL Shading Language), a uniform is a global 
//	variable that is set by the application (CPU) and remains constant 
//	for all shader invocations during a draw call. 
//	It does not change per vertex or per fragment, unlike attributes or varyings.

//uniform vec3 u_colour;			// colour as a 3D vector (r,g,b)

uniform vec2 u_screenSize;  	// screen dimensions in pixels

layout(location = 0) out vec4 o_colour;	// output to colour buffer //WHAT DOES THIS DO?

void main() {
   //vec2 p = gl_FragCoord.xy / u_screenSize;   // scale p into range (0,0) to (1,1)
   
   float minScreenDimension = min(u_screenSize.x, u_screenSize.y);
   vec2 minScreenSizeRatio = vec2(minScreenDimension, minScreenDimension);
   vec2 newP = gl_FragCoord.xy / minScreenSizeRatio;
      
   // FRAGCOORD IS EVERY PIXEL
   
   //DRAWING CIRCLE
   //float d = distance(p, vec2(0.2, 0.6));     // calculate distance to midpoint
   
   //DRAWING SQUARE
   //vec2 v = abs(p - vec2(0.5, 0.5)); //vector from middle point to p
   //float d = max(v.x, v.y); //the distance is set to whatever is the largest of the distance from the midpoint out of x and y
   //this draws a cube, because the values returned are a distance in terms of x or y, so the x is constrained and the y is constrained, 
   //so the maximum of both would be within the range.
   //if x is within range and y is not within range it doesnt change colour. ONLY when both are in range would it change colour.
   //SO the x and the y both have a range that they must both be between for it to change colour, 2 ranges, 1 horizontal, 1 vertical gives the shape of a sqaure.
   
   //when max is changed to min it draws a cross because the smaller of the two distances is chosen so when y is far away if x is in range it will be a different colour.
   //as well, if x was far away but y was within range then it would change colour.
   
   //removing the abs value stops the range from stopping at -y and -x. 
   //To the right of the square, x is positive and is chosen, if its bigger than the range it is not drawn.
   //To the left of the square though, -x and y, if y is within range, it will be bigger always than x since x will be in the negatives.
   // to the right p = (0.7, 0.5), v = (0.2, 0), then d = 0.2, point not chosen it is out of the bounds of below.
   // to the left p = (0.4, 0.5), v = (-0.1, 0), then d = 0, point is chosen, 0 is within range.
   // So as long as one axis is within range the negatives will be not chosen and it will think its within range.
   // for the bottom left, the value for d is below 0, so as it is a less then check the negatives will still be chosen.
      
   //DRAWING DIAMOND
   vec2 v = abs(newP - vec2(0.5, 0.5));
   float d = abs(v.x) + abs(v.y);
   
   if (d < 0.5 && d>0) {
      o_colour = vec4(1,0,0.9f,1); //pink
      // IN OPEN GL COLOUR VECTOR XYZ IS COMPLETELY INTERCHANGABLE WITH RGB, (CAN EVEN BE SWITCHED WITH BGR ETC, not the order that matters)
      
	      //DEMO, MAKING GRADIENTS
	      // vec2 offset = vec2(0.5f, 0.5f) //middle
	      
	      // float redChannel = p.y;
	      // float blueChannel = 1.0f - p.y; //we want it to dissipate going up so at the top its at 0 blue, bottom 1 blue, bottom of screen is (0,0)
	      // float greenChannel = distance(offset,p) //distance from middle to edges, 0.5 is max here, we can times 2 to get a blue from value 0 to 1
	      
	      // vec3 finalColour = vec3(redChannel,blueChannel,greenChannel);
	      // o_colour = vec4(u_colour.rgb + finalColour.rgb, 1.0f) 
   }
   else if( d<0 ){
   		o_colour = vec4(0,0,0.0,1); //black
   }
   else {
      o_colour = vec4(1,1,0,1); //yellow
   }
}