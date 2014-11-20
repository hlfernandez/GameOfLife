package es.uvigo.ei.sing.hlfernandez.gameoflife.util;

import es.uvigo.ei.sing.hlfernandez.gameoflife.Cell;

public class BoardUtils {

	public static final Cell[] GLIDER_SPACESHIP = new Cell[] { 
		new Cell(0, 1),
		new Cell(1, 2), 
		new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) 
	};
}
