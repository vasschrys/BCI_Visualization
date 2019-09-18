
/**
 * this is the InputFile class, the user will write on a textFile, and in this class the 
 * program reads in what parameters the user wants. 
 * @author Vassilia Chrysanthopoulos
 */

import java.io.*;
import java.util.Scanner;

public class InputFile {
	String configFile;
	String[] freqList;
	String[] phaseList;
	String[] colorList;
	int numBoxes;

	// constructor for the InputFile class
	public InputFile(String configFile) {
		this.configFile = configFile;
		scanFile(configFile);
	}

	// scans in the file reading all the user inputs
	public void scanFile(String f) {
		try {

			FileReader file = new FileReader(f);
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();

				// scans in all the frequencies
				if (line.substring(0, 3).equals("Fre")) {
					String fre = line.substring(11, line.length());
					freqList = fre.split(", ");

					// scans in all the phases
				} else if (line.substring(0, 3).equals("Pha")) {
					String ph = line.substring(7, line.length());
					phaseList = ph.split(", ");

					// scans in the number of boxes
				} else if (line.substring(0, 3).equals("Num")) {
					String boxes = line.substring(14, line.length());
					numBoxes = Integer.parseInt(boxes);
				}
				// scans in the color of the boxes
				else if (line.substring(0, 3).equals("Col")) {
					String col = line.substring(7, line.length());
					colorList = col.split(", ");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
