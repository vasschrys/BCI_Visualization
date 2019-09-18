
/**
 * This is the Box class. This class creates the Box object. Each 
 * object holds its own frequency and phase at which it moves at. 
 *@author Vassilia Chrysanthopoulos
 */

import java.awt.Color;
import acm.graphics.*;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Box extends GraphicsProgram implements Runnable {

	private double frequency;
	private double phase;
	public GRect rect;
	private int size;
	private double time;
	private double thistime; 

	//default constructor for the Box class
	public Box() {

	}

	//@Param: boxSize
	//Constructor for the box class
	public Box(int size) {
		setRectSize(size);
	}

	// @Param: frequency, phase
	// constructor for the Box class
	public Box(double frequency, double phase) {
		this.frequency = frequency;
		this.phase = phase;

		timeDifference();
		getTimeDuration(); 
	}

	// creates Rectangles of the same size
	public GRect createRect(Color c, int s) {
		// set rectangle size and color
		rect = new GRect(.05 * s, .05 * s);
		rect.setColor(c);
		rect.setFilled(true);
		return rect;
	}

	// run method for rectangles
	public void run() {
		flash();
	}

	// how the rectangles should flash
	public void flash() {
		while (true) {
			// set the object invisible
			this.rect.setVisible(false);
			try {

				// rest for the frequency accounted with phase duration
				//I DONT THINK THIS IS RIGHT
				Thread.sleep((long) (1 / this.frequency * 1000) + timeDifference());
				//System.out.println( " Bloop " + (long) (1 / this.frequency * 1000) + timeDifference());
				//System.out.println((long) time * 1000);

				//Thread.sleep((long) (1 / timeDifference()) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// set object visible
			this.rect.setVisible(true);

			// rest for frequency accounted with phase duration
			try {
				Thread.sleep((long) (1 / this.frequency * 1000) + timeDifference());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// set rectangle color
	public void setColor(GRect r, Color userColor) {
		r.setColor(userColor);
	}

	// get the user entered phase and calculate the time delay
	public long getPhase() {
		return (long) phase;
	}

	// get the given frequency in milliseconds
	public long getFrequency() {
		return (long) frequency;
	}

	// get the time DIfference between each flash
	public long timeDifference() {
		time = this.phase / (360 * this.frequency);
		// this is in milliseconds
		//i added time difference because its the frequency + the time (which includes phase shift)
		double time2 = time + this.frequency;
		time = time2;
		return (long) time;

	}

	// setter method for rectangle size
	public void setRectSize(int s) {
		size = (int) .05 * s;
	}

	// getter method for rectangle size
	public int getRectSize() {
		return size;
	}
	
	//Visual stimulation presentation formula --> still need to test
	//THIS IS PRINTING 0.0 SO SOMETHING DOESNT WORK 
	public long getTimeDuration() {
		thistime = 1/2 * (1 + Math.sin(2*Math.PI * this.frequency * (1/60) + this.phase));
		return (long)thistime; 
	}

}
