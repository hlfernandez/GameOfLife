package hlfernandez.gameoflife;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import es.uvigo.ei.sing.hlfernandez.gameoflife.Board;
import es.uvigo.ei.sing.hlfernandez.gameoflife.Cell;

public class BoardTest {

	@Test
	public void testCellWithoutNeighbourds() {
		Board board = new Board(new Cell[]{
				new Cell(1,1)
		});
		board.nextGeneration();
		assertTrue(board.getCells().isEmpty());
	}
	
	@Test
	public void testCellWithOneNeighbourd() {
		Board board = new Board(new Cell[]{
				new Cell(1,1), new Cell(0,0)
		});
		board.nextGeneration();
		assertTrue(board.getCells().isEmpty());
	}
	
	@Test
	public void testCellsWithTwoNeighbourds() {
		Board board = new Board(new Cell[]{
				new Cell(1,0), new Cell(1,1), new Cell(1,2)
		});
		board.nextGeneration();
		Set<Cell> nextGenerationCells = board.getCells();
		assertTrue(nextGenerationCells.size() == 3);
		assertFalse(nextGenerationCells.contains(new Cell(1,0)));
		assertTrue(nextGenerationCells.contains(new Cell(1,1)));
		assertFalse(nextGenerationCells.contains(new Cell(1,2)));
	}
	
	@Test
	public void testDeadCellWithThreeNeighbourds() {
		Board board = new Board(new Cell[]{
				new Cell(1,1), new Cell(0,0), new Cell(0,1)
		});
		board.nextGeneration();
		Set<Cell> nextGenerationCells = board.getCells();
		assertTrue(nextGenerationCells.size() == 4);
		assertTrue(nextGenerationCells.contains(new Cell(0,0)));
		assertTrue(nextGenerationCells.contains(new Cell(1,0)));
		assertTrue(nextGenerationCells.contains(new Cell(1,1)));
		assertTrue(nextGenerationCells.contains(new Cell(0,1)));
	}

	@Test
	public void testDeadCellWithMoreThanThreeNeighbourds() {
		Board board = new Board(new Cell[]{
				new Cell(1,1), new Cell(0,0), new Cell(0,1), new Cell(2,0)
		});
		board.nextGeneration();
		Set<Cell> nextGenerationCells = board.getCells();
		assertTrue(nextGenerationCells.size() == 3);
		assertTrue(nextGenerationCells.contains(new Cell(1,1)));
		assertTrue(nextGenerationCells.contains(new Cell(0,0)));
		assertTrue(nextGenerationCells.contains(new Cell(0,1)));
		assertFalse(nextGenerationCells.contains(new Cell(1,0)));
	}

	@Test
	public void testLiveCellWithMoreThanThreeNeighbourds() {
		Board board = new Board(new Cell[]{
				new Cell(0,0), new Cell(0,1), new Cell(0,2),
				new Cell(1,0), new Cell(1,1), new Cell(1,2),
				new Cell(2,0), new Cell(2,1), new Cell(2,2)
		});
		board.nextGeneration();
		Set<Cell> nextGenerationCells = board.getCells();
		assertTrue(nextGenerationCells.size() == 8);
		assertTrue(nextGenerationCells.contains(new Cell(0,0)));
		assertFalse(nextGenerationCells.contains(new Cell(0,1)));
		assertTrue(nextGenerationCells.contains(new Cell(0,2)));
		assertFalse(nextGenerationCells.contains(new Cell(1,0)));
		assertFalse(nextGenerationCells.contains(new Cell(1,1)));
		assertFalse(nextGenerationCells.contains(new Cell(1,2)));
		assertTrue(nextGenerationCells.contains(new Cell(2,0)));
		assertFalse(nextGenerationCells.contains(new Cell(2,1)));
		assertTrue(nextGenerationCells.contains(new Cell(2,2)));
		
		/**
		 * New cells
		 */
		assertTrue(nextGenerationCells.contains(new Cell(-1,1)));
		assertTrue(nextGenerationCells.contains(new Cell(1,-1)));
		assertTrue(nextGenerationCells.contains(new Cell(3,1)));
		assertTrue(nextGenerationCells.contains(new Cell(1,3)));
	}
}
