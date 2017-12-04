

import processing.core.*;

public class Butterfly extends PApplet {
	PImage img;       // The source image

	public void settings() {
		  size(1024,764, P3D);
		}
	public void setup() {
 
	  img  = loadImage(""); // Load the image
	}

	public void draw() {
	  background(0);
	  loadPixels();
	  // Begin loop for columns
	  for ( int i = 0; i < img.width;i++) {
	    // Begin loop for rows
	    for ( int j = 0; j < img.height;j++) {
	      int x = width * i/img.width; // x position
	      int y = height * j/img.height; // y position
	      int loc = i + j*img.width;           // Pixel array location
	      int c = img.pixels[loc];       // Grab the color
	      // Calculate a z position as a function of mouseX and pixel brightness
	      float z = (float) ((mouseX/(float)width) * brightness(img.pixels[loc]) - 100.0);
	      // Translate to the location, set fill and stroke, and draw the rect
	      pushMatrix();
	      translate(x,y,z);
	      fill(c);
	      noStroke();
	      rectMode(CENTER);
	      rect(0,0,width/img.width,height/img.height);
	      popMatrix();
	    }
	  }
	}
	public static void main(String[] args) {
		PApplet.main(Butterfly.class.getName());

	}

}
