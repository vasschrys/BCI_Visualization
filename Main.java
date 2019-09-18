
/**
 *  This is the Main class. This is where the program runs. The user 
 * inputs the number of boxes they want, the list of frequencies, and 
 * the list of phases. 
 *@author Vassilia Chrysanthopoulos
 */
import acm.graphics.*;
import java.util.*;

public class Main {
	public static InputFile f;

	public static void main(String[] args) {

		f = new InputFile("ConfigFile");
		// take in number of boxes created
		int nbRect = f.numBoxes;

		ArrayList<Double> arrayFreq = new ArrayList<Double>();
		int i = 0;

		// number of frequencies user input
		while (i + 1 <= nbRect) {
			arrayFreq.add(Double.parseDouble(f.freqList[i]));
			i++;
		}

		// the number of phases user input
		ArrayList<Double> arrayPhase = new ArrayList<Double>();
		i = 0;
		while (i + 1 <= nbRect) {
			arrayPhase.add(Double.parseDouble(f.phaseList[i]));
			i++;
		}
		// create arrayList of colors
		ArrayList<String> arrayColor = new ArrayList<String>();
		i = 0;
		while (i + 1 <= nbRect) {
			arrayColor.add(f.colorList[i]);
			i++;
		}

		// Program runs
		new Draw(nbRect, arrayFreq, arrayPhase, arrayColor).start();
	}

}