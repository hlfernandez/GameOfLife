package es.uvigo.ei.sing.hlfernandez.gameoflife;

import java.util.Arrays;
import java.util.List;

public class Cell {

	private int x;
	private int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public List<Cell> getNeighbourds() {
		return Arrays.asList(new Cell[] { 
				new Cell(x - 1, y - 1),	new Cell(x - 1, y), new Cell(x - 1, y + 1), 
				new Cell(x, y - 1), 						new Cell(x, y + 1), 
				new Cell(x + 1, y - 1),	new Cell(x + 1, y),	new Cell(x + 1, y + 1) });
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "x = " + this.getX() + ", y = " + this.getY();
	}
}
