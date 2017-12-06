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
	float rep_X; 
	float rep_Y;
	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		if (useP2D) {
			if(isFullscreen) {
				fullScreen(P3D);  			
			} else {
				size((int)(1920 * windowsScale), (int)(1080 * windowsScale), P3D);
			}
		} else {
			if(isFullscreen) {
				fullScreen();  			
			} else {
				size((int)(1920 * windowsScale), (int)(1080 * windowsScale));
			}
		}		
	}
	public void settings() {
		//size(1024,764, P3D);
<<<<<<< HEAD
		size(1000,1000, P3D);
=======
		createWindow(true, true, .5f);
		
>>>>>>> 044321e3a56ab2e771b2f5353369d01bfa9beb32
	}


	
	public void setup() {
		butterfly_img  = loadImage("butterfly.gif"); // Load the image
	  //loadImage("butterfly.gif"); // Load the image
		
		kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();
	}

	public void draw() {
		KinectBodyData bodyData = kinectReader.getMostRecentData();

		Body person = bodyData.getPerson(0);	
		
		background(0);
		loadPixels();
	      if(person != null){
	    	  
				PVector handLeft = person.getJoint(Body.HAND_LEFT);
				PVector handRight = person.getJoint(Body.HAND_RIGHT);
			if(handRight !=null && handLeft!= null){
				rep_X = handRight.x * 1000; 
				rep_Y = handLeft.y * 750; 
	     
	}
	      }
	  // Begin loop for columns 
	  for ( int i = 0; i < butterfly_img.width;i++) {
	    // Begin loop for rows
	    for ( int j = 0; j < butterfly_img.height;j++) {
	      float x = width * i/butterfly_img.width; // x position
	      float y = height * j/butterfly_img.height; // y position
	      int loc = i + j*butterfly_img.width;           // Pixel array location
	      int c = butterfly_img.pixels[loc];       // Grab the color
	      

	      
	      // Calculate a z position as a function of mouseX and pixel brightness
	      float z = (float) ((rep_X/(float)width) * brightness(butterfly_img.pixels[loc]) - 100.0);
	      // Translate to the location, set fill and stroke, and draw the rect
	      pushMatrix();
	      translate(x,y,z + rep_Y  );
	      fill(c);
	      noStroke();
	      rectMode(CENTER);
	      rect(0,0,width/butterfly_img.width,height/butterfly_img.height);
	      popMatrix();
	    }
	  }
	      }
	      

	public static void main(String[] args) {
		PApplet.main(Butterfly_Jane.class.getName());

	}

}
