package es.uvigo.ei.sing.hlfernandez.gameoflife.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import es.uvigo.ei.sing.hlfernandez.gameoflife.Cell;

public class BoardUtils {

	public static final Cell[] GLIDER_SPACESHIP = new Cell[] { 
		new Cell(0, 1),
		new Cell(1, 2), 
		new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) 
	};

	public static final Cell[] readCells(String resource) {
		return readCells(BoardUtils.class.getResourceAsStream(resource));
	}

	public static final Cell[] readCells(File f) throws FileNotFoundException{
		return readCells(new FileInputStream(f));
	}
	
	private static final Cell[] readCells(InputStream is){
		Set<Cell> cells = new HashSet<Cell>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		List<String> lines = (List<String>) reader.lines().collect(
				Collectors.toList());
		for (String line : lines) {
			for (int column = 0; column < line.length(); column++) {
				if (line.charAt(column) == '1') {
					cells.add(new Cell(lines.indexOf(line), column));
				}
			}
		}
		return cells.toArray(new Cell[cells.size()]);
	}
	
	public static final Cell[] randomCells(int rows, int cols){
		Set<Cell> cells = new HashSet<Cell>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (Math.random() > 0.7) {
					cells.add(new Cell(i, j));
				}
			}
		}
		return cells.toArray(new Cell[cells.size()]);
	}
	
}
