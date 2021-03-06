package es.uvigo.ei.sing.hlfernandez.gameoflife.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.swing.JFrame;

import es.uvigo.ei.sing.hlfernandez.gameoflife.Board;
import es.uvigo.ei.sing.hlfernandez.gameoflife.util.BoardUtils;

public class Launcher {
	public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
		Board board = new Board(BoardUtils.readCells("/boards/board-glider-spaceship.txt"));
		JFrame frame = new JFrame("Game of life");
		final BoardPanel boardPanel = new BoardPanel(board);
		frame.add(boardPanel);
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				boardPanel.finalize();
			}
		});
		frame.setVisible(true);
	}
}
