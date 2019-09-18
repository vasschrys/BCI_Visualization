
/**
 *This is the Draw class. Here the Boxes are drawn at the specified
 *frequencies and phases with corresponding colors. 
 *The boxes are drawn in order from left  to right, starting with the
 *top left corner, and moving to the bottom right corner. 
 *@author Vassilia Chrysanthopoulos
 */


import acm.graphics.*;
import java.util.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Draw extends GraphicsProgram {
	private Box box;
	private ArrayList<Double> arrayFreq;
	private ArrayList<Double> arrayPhase;
	private ArrayList<Box> boxList;
	private ArrayList<String> colorList;
	private int nbRect;
	private int WIDTH;
	private int HEIGHT;
	private int count = 0;
	private GPoint last;
	private int size;

	// constructor for the Draw class
	public Draw(int nbRect, ArrayList<Double> arrayFreq, ArrayList<Double> arrayPhase, ArrayList<String> colorList) {
		this.nbRect = nbRect;
		this.arrayFreq = arrayFreq;
		this.arrayPhase = arrayPhase;
		this.colorList = colorList;
		boxList = new ArrayList<Box>();

	}

	// setup for the canvas
	public void init() {
		// GCanvas canvas = new GCanvas();
		// System.out.println(canvas.getWidth());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		WIDTH = getWidth();
		HEIGHT = getHeight();
		centerCross();
	}

	// this is where multi-threading happens
	// run method for the rectangles
	public void run() {

		// creates box object and adds them to an arrayList
		for (int i = 0; i < nbRect; i++) {
			Box box = new Box(arrayFreq.get(i), arrayPhase.get(i));
			boxList.add(box);
		}

		// places box on screen and creates threads for each
		for (int i = 0; i < nbRect; i++) {
			placeBox(boxList.get(i).rect);
			new Thread(boxList.get(i)).start();
		}
	}

	// This method places the box on the screen with its corresponding color
	// the boxes are drawn diagonal from each other left to right.
	public void placeBox(GRect rect) {
		box = new Box();
		size = (int) (WIDTH * .05);
		while (count != nbRect) {
			if (count == 0) {
				GRect rect1 = boxList.get(0).createRect(getColor(colorList.get(count)), WIDTH);
				rect1.addMouseListener(this);
				rect1.addMouseMotionListener(this);
				add(rect1, .05 * WIDTH, .05 * HEIGHT);
				count++;
			} else if (count == 1) {
				GRect rect1 = boxList.get(1).createRect(getColor(colorList.get(count)), WIDTH);
				rect1.addMouseListener(this);
				rect1.addMouseMotionListener(this);
				add(rect1, WIDTH - size * 2, HEIGHT - size * 3);
				count++;
			} else if (count == 2) {
				GRect rect1 = boxList.get(2).createRect(getColor(colorList.get(count)), WIDTH);
				rect1.addMouseListener(this);
				rect1.addMouseMotionListener(this);
				add(rect1, .05 * WIDTH, HEIGHT - size * 3);
				count++;
			} else if (count == 3) {
				GRect rect1 = boxList.get(3).createRect(getColor(colorList.get(count)), WIDTH);
				rect1.addMouseListener(this);
				rect1.addMouseMotionListener(this);
				add(rect1, WIDTH - size * 2, .05 * HEIGHT);
				count++;
			} else {
				if (count > 3) {
					GRect rect1 = boxList.get(count).createRect(getColor(colorList.get(count)), WIDTH);
					rect1.addMouseListener(this);
					rect1.addMouseMotionListener(this);
					add(rect1, 0, 0);
					count++;
				}
			}

		}

	}

	// creates the center cross on the screen
	public void centerCross() {
		int WcenterR = WIDTH / 2;
		int HcenterR = HEIGHT / 2;
		int WRect = getHeight() / 200;
		int HRect = getHeight() / 20;
		GRect rect = new GRect(WRect, HRect);
		GRect rect2 = new GRect(HRect, WRect);
		rect.setColor(Color.BLACK);
		rect2.setColor(Color.BLACK);
		rect.setFilled(true);
		rect2.setFilled(true);

		add(rect, getWidth() / 2, getHeight() / 2 - HRect / 2);
		add(rect2, getWidth() / 2 - HRect / 2, getHeight() / 2);
	}

	// switch statement for colors
	public Color getColor(String s) {
		switch (s) {
		case "red":
			return Color.RED;
		case "green":
			return Color.GREEN;
		case "blue":
			return Color.BLUE;
		case "black":
			return Color.BLACK;
		case "gray":
			return Color.GRAY;
		case "magenta":
			return Color.MAGENTA;
		case "pink":
			return Color.PINK;
		default:
			return Color.BLACK;

		}
	}

	// called on mouse pressed to record the coordinates of the click
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
	}

	// called on mouse drag to reposition the object by dragging the mouse
	public void mouseDragged(MouseEvent e) {
		GObject gobj = (GObject) e.getSource();
		gobj.move(e.getX() - last.getX(), e.getY() - last.getY());
		last = new GPoint(e.getPoint());

		if (gobj.getX() < 0) {
			gobj.setLocation(0, 0);
		} else if (gobj.getX() + size > WIDTH) {
			gobj.setLocation(0, 0);

		}
		if (gobj.getY() < 0) {
			gobj.setLocation(0, 0);
		} else if (gobj.getY() + 70 + size > HEIGHT) {

			gobj.setLocation(0, 0);
		}
	}

	// called on mouse clicked to move the object to the front
	public void mouseCicked(MouseEvent e) {
		GObject gobj = (GObject) e.getSource();
		gobj.sendToFront();
	}

}
