
import processing.core.*;
import processing.core.PApplet; 
import processing.core.PImage;
import processing.core.PVector;

/**
 * @author eitan
 *
 */
public class KinectRenderDemo extends PApplet {

	KinectBodyDataProvider kinectReader;
	public static float PROJECTOR_RATIO = 1080f/1920.0f;
	PImage butterfly_img; 

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
	
	// use lower numbers to zoom out (show more of the world)
	// zoom of 1 means that the window is 2 meters wide and appox 1 meter tall.
	public void setScale(float zoom) {
		scale(zoom* width/2.0f, zoom * -width/2.0f);
		translate(1f/zoom , -PROJECTOR_RATIO/zoom );		
	}

	public void settings() {
		createWindow(true, true, .5f);
	}

	public void setup(){

		/*
		 * use this code to run your PApplet from data recorded by UPDRecorder 
		 */
		/*
		try {
			kinectReader = new KinectBodyDataProvider("test.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		 */
		butterfly_img  = loadImage("butterfly.gif");
		
		kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();
		
		

	}
	public void draw(){
		setScale(.5f);

		KinectBodyData bodyData = kinectReader.getMostRecentData();

		Body person = bodyData.getPerson(0);



		background(0);
		loadPixels();
		// butterfly begins here // 
		 for ( int i = 0; i < butterfly_img.width;i++) {
			    // Begin loop for rows
			    for ( int j = 0; j < butterfly_img.height;j++) {
			      float x = width * i/butterfly_img.width; // x position
			      float y = height * j/butterfly_img.height; // y position
			      int loc = i + j*butterfly_img.width;           // Pixel array location
			      int c = butterfly_img.pixels[loc];       // Grab the color
			      // Calculate a z position as a function of mouseX and pixel brightness
			      float z = (float) ((mouseX/(float)width) * brightness(butterfly_img.pixels[loc]) - 100.0);
			      // Translate to the location, set fill and stroke, and draw the rect
			      pushMatrix();
			    
			      translate(x,y,z + mouseY);
			      fill(c);
			      noStroke();
			      rectMode(CENTER);
			      rect(0,0,width/butterfly_img.width,height/butterfly_img.height);
			      popMatrix();
			    }
			  }
	

		
		
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
		}

//			fill(255,255,255);
//			noStroke();
//			drawIfValid(head);
//			drawIfValid(spine);
//			drawIfValid(spineBase);
//			drawIfValid(shoulderLeft);
//			drawIfValid(shoulderRight);
//			drawIfValid(footLeft);
//			drawIfValid(footRight);
//			drawIfValid(handLeft);
//			drawIfValid(handRight);

			
			
			

		
	}

	/**
	 * Draws an ellipse in the x,y position of the vector (it ignores z).
	 * Will do nothing is vec is null.  This is handy because get joint 
	 * will return null if the joint isn't tracked. 
	 * @param vec
	 */
	public void drawIfValid(PVector vec) {
		if(vec != null) {
			ellipse(vec.x, vec.y, .1f,.1f);
		}

	}


	public static void main(String[] args) {
		
		PApplet.main(KinectRenderDemo.class.getName());
	}

}
