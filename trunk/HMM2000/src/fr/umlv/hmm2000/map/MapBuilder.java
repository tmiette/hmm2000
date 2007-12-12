package fr.umlv.hmm2000.map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class MapBuilder {

	public static Map createMap(String mapFilePath)
			throws FileNotFoundException, IOException {

		LineNumberReader lnr = new LineNumberReader(new FileReader(mapFilePath));
		String s;

		s = lnr.readLine();
		int nbRows = Integer.parseInt(s);
		s = lnr.readLine();
		int nbColumns = Integer.parseInt(s);

		MapBackgroundEnum[][] background = new MapBackgroundEnum[nbRows][nbColumns];

		while ((s = lnr.readLine()) != null) {
			decodeLine(s, background[lnr.getLineNumber() - 3]);
		}
		
		return new Map(background);
	}

	private static void decodeLine(String s, MapBackgroundEnum[] line) {
		for (int i = 0; i < s.length(); i++) {
			line[i] = getMapBackgroundEnum(s.charAt(i));
		}
	}

	private static MapBackgroundEnum getMapBackgroundEnum(char c) {
		switch (c) {
		case 'M':
			return MapBackgroundEnum.MOUNTAIN;
		case 'P':
			return MapBackgroundEnum.PATH;
		case 'L':
			return MapBackgroundEnum.PLAIN;
		case 'T':
			return MapBackgroundEnum.TREE;
		case 'W':
			return MapBackgroundEnum.WATER;
		default:
			return null;
		}
	}

}
