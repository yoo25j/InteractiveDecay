
import processing.core.PApplet; 
import processing.core.PImage;
import processing.core.PVector;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.*;

public class Butterfly_Jane extends PApplet {
	
	final static int NUM_FRAMES = 27; // number of frames
	int frame = 0; // current frame index 
	PImage butterfly_img;       // The source image
	boolean last_frame = false;
	
	KinectBodyDataProvider kinectReader;

	public void settings() {
<<<<<<< HEAD


=======
>>>>>>> a76130b9f2b979cdfb9795efd72f3005e9bf4a8c
		//size(1024,764, P3D);
		size(1000,1000, P3D);
	}

<<<<<<< HEAD

	
=======
>>>>>>> a76130b9f2b979cdfb9795efd72f3005e9bf4a8c
	public void setup() {
		butterfly_img  = loadImage("butterfly.gif"); // Load the image
	    //loadImage("butterfly.gif"); // Load the image
		kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();
	}

	public void draw() {
		KinectBodyData bodyData = kinectReader.getMostRecentData();
		Body person = bodyData.getPerson(0);	
		
<<<<<<< HEAD
		background(0);
		loadPixels();
	
	  // Begin loop for columns 
	  for ( int i = 0; i < butterfly_img.width;i++) {
	    // Begin loop for rows
	    for ( int j = 0; j < butterfly_img.height;j++) {
	      float x = width * i/butterfly_img.width; // x position
	      float y = height * j/butterfly_img.height; // y position
	      int loc = i + j*butterfly_img.width;           // Pixel array location
	      int c = butterfly_img.pixels[loc];       // Grab the color
	      
//	      if(person != null){
//				PVector handLeft = person.getJoint(Body.HAND_LEFT);
//				PVector handRight = person.getJoint(Body.HAND_RIGHT);
	      
	      // Calculate a z position as a function of mouseX and pixel brightness
	      float z = (float) ((mouseX/(float)width) * brightness(butterfly_img.pixels[loc]) - 100.0);
	      // Translate to the location, set fill and stroke, and draw the rect
	      pushMatrix();
	      translate(x,y,z + mouseY );
	      fill(c);
	      noStroke();
	      rectMode(CENTER);
	      rect(0,0,width/butterfly_img.width,height/butterfly_img.height);
	      popMatrix();
	    }
	  }
		}
//	}
=======
		if(person != null){
			PVector head = person.getJoint(Body.HEAD);
			PVector spine = person.getJoint(Body.SPINE_SHOULDER);
			PVector spineBase = person.getJoint(Body.SPINE_BASE);
			PVector shoulderLeft = person.getJoint(Body.SHOULDER_LEFT);
			PVector shoulderRight = person.getJoint(Body.SHOULDER_RIGHT);
			PVector footLeft = person.getJoint(Body.FOOT_LEFT);
			PVector footRight = person.getJoint(Body.FOOT_RIGHT);
			PVector handLeft = person.getJoint(Body.HAND_LEFT);
			PVector handRight = person.getJoint(Body.HAND_RIGHT);
		
		
		  background(0);
		  loadPixels();
		  // Begin loop for columns 
		  for ( int i = 0; i < butterfly_img.width;i++) {
		    // Begin loop for rows
		    for ( int j = 0; j < butterfly_img.height;j++) {
			      float x = width * i/butterfly_img.width; // x position
			      float y = height * j/butterfly_img.height; // y position
			      int loc = i + j*butterfly_img.width;           // Pixel array location
			      int c = butterfly_img.pixels[loc];       // Grab the color
			      // Calculate a z position as a function of mouseX and pixel brightness
			      float z = (float) ((handRight.x/(float)width) * brightness(butterfly_img.pixels[loc]) - 100.0);
			      // Translate to the location, set fill and stroke, and draw the rect
			      pushMatrix();
			      translate(x,y,z + handLeft.y );
			      fill(c);
			      noStroke();
			      rectMode(CENTER);
			      rect(0,0,width/butterfly_img.width,height/butterfly_img.height);
			      popMatrix();
		    }
		  }
		}
	}
	
>>>>>>> a76130b9f2b979cdfb9795efd72f3005e9bf4a8c
	public static void main(String[] args) {
		PApplet.main(Butterfly_Jane.class.getName());
	}

}
