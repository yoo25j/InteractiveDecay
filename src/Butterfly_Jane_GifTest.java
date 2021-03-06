import processing.core.PApplet; 
import processing.core.PImage;
import processing.core.PVector;
import java.io.IOException;
import java.util.ArrayList;
import processing.core.*;
import gifAnimation.*;


public class Butterfly_Jane_GifTest extends PApplet {
	
	//*******************GIF THINGS*******************
	final static int NUM_FRAMES = 27; // number of frames
	int frame = 0; // current frame index 
	boolean last_frame = false; 
	PImage [] butterfly_frames; //holds each frame of gif
	//PImage butterfly_img;       // The source image
	
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
		createWindow(true, true, .5f);
	}
	
	public void setup() {
		//butterfly_img  = loadImage("butterfly.gif"); // Load the image
		//loadImage("butterfly.gif"); // Load the image
		//butterfly_frames = Gif.getPImages(this, "butterfly.gif"); //array of frames
		
  for(int i= 1; i <= 27; i++){
	  butterfly_frames= new PImage [28];
	   butterfly_frames[i] =loadImage("butterfly" +i +".gif");
			
			
		}
		
		kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();
	}

	public void draw() {
		KinectBodyData bodyData = kinectReader.getMostRecentData();
		Body person = bodyData.getPerson(0);	
		background(0);
		
		for(int l= 1; l <= butterfly_frames.length; l++){
			
			loadPixels();
			updatePixels();
		
		
		
		
	    if(person != null){
			PVector handLeft = person.getJoint(Body.HAND_LEFT);
			PVector handRight = person.getJoint(Body.HAND_RIGHT);
			if(handRight !=null && handLeft!= null){
				rep_X = handRight.x * 1000; 
				rep_Y = handLeft.y * 750; 
			}
			
	    }
	     
	    for ( int i = 0; i < butterfly_frames[l].width;i++) { // Begin loop for columns
		    for ( int j = 0; j < butterfly_frames[l].height;j++) { // Begin loop for rows
		    		float x = width * i/butterfly_frames[l].width; 	// x position
		    		float y = height * j/butterfly_frames[l].height; 	// y position
			    int loc = i + j*butterfly_frames[l].width;       	// Pixel array location
			    int c = butterfly_frames[l].pixels[loc];       	// Grab the color
			    float z = (float) ((rep_X/(float)width) * brightness(butterfly_frames[l].pixels[loc]) - 100.0);	// Calculate a z position as a function of mouseX and pixel brightness
			    // Translate to the location, set fill and stroke, and draw the rect
			    pushMatrix();
			    translate(x,y,z + rep_Y  );
			    fill(c);
			    noStroke();
			    rectMode(CENTER);
			    rect(0,0,width/butterfly_frames[l].width,height/butterfly_frames[l].height);
			    popMatrix();
		    }
	    }
	     }
	     
		}
//	    if (frame == NUM_FRAMES) {
//	    		last_frame= true;
//	    }
//	    else if (frame > 0 && last_frame) {
//	    		if (frame == 0) {
//	    			last_frame = false;
//	    		}
//	    }
	

	public static void main(String[] args) {
		PApplet.main(Butterfly_Jane_GifTest.class.getName());
	}

}
