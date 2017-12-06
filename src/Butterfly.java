

import processing.core.*;

public class Butterfly extends PApplet {
	
	final static int NUM_FRAMES = 27; // number of frames
	int frame = 0; // current frame index 
	PImage butterfly_img;       // The source image
	boolean last_frame = false;
	

	public void settings() {
		  size(1024,764, P3D);
		}
	
	public void setup() {
<<<<<<< HEAD

	  butterfly_img  = loadImage("butterfly.gif"); // Load the image

=======
 	  butterfly_img  = loadImage("butterfly.gif"); // Load the image
>>>>>>> e5b8cef51a517d693a418b020ea3e59e426d6e90
	}

	public void draw() {
	  background(0);
	  loadPixels();
	  // Begin loop for columns
	  for ( int i = 0; i < butterfly_img.width;i++) {
	    // Begin loop for rows
	    for ( int j = 0; j < butterfly_img.height;j++) {
	      int x = width * i/butterfly_img.width; // x position
	      int y = height * j/butterfly_img.height; // y position
	      int loc = i + j*butterfly_img.width;           // Pixel array location
	      int c = butterfly_img.pixels[loc];       // Grab the color
	      // Calculate a z position as a function of mouseX and pixel brightness
	      float z = (float) ((mouseX/(float)width) * brightness(butterfly_img.pixels[loc]) - 100.0);
	      // Translate to the location, set fill and stroke, and draw the rect
	      pushMatrix();
	      translate(x,y,z);
	      fill(c);
	      noStroke();
	      rectMode(CENTER);
	      rect(0,0,width/butterfly_img.width,height/butterfly_img.height);
	      popMatrix();
	    }
	  }
	}
	public static void main(String[] args) {
		PApplet.main(Butterfly.class.getName());

	}

}
