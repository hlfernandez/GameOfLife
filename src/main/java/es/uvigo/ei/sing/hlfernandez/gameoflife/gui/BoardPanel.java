package es.uvigo.ei.sing.hlfernandez.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import es.uvigo.ei.sing.hlfernandez.gameoflife.Board;

public class BoardPanel extends JPanel{

	private final static ImageIcon ICON_NEXT = new ImageIcon(
			BoardPanel.class.getResource("/icons/next.png"));
	private final static ImageIcon ICON_PLAY = new ImageIcon(
			BoardPanel.class.getResource("/icons/play.png"));
	private final static ImageIcon ICON_PAUSE = new ImageIcon(
			BoardPanel.class.getResource("/icons/pause.png"));
	
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 40;
	private static final int COLS = 40;
	private static final int INTERVAL = 500;
	
	private Board board;
	private JTextArea boardTA;
	private JButton nextButton;
	private JToggleButton autoButton;
	private BoardTable table;
	private boolean auto = false;
	private Thread animatorThread;

	public BoardPanel(Board board) {
		super();
		this.board = board;
		initComponent();
	}

	private void initComponent() {
		this.setLayout(new BorderLayout());
		this.add(getToolbar(), BorderLayout.NORTH);
		this.add(getMainPanel(), BorderLayout.CENTER);
	}

	private Component getToolbar() {
		JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		toolbar.setFloatable(false);
		nextButton = new JButton(ICON_NEXT);
		nextButton.setToolTipText("Moves onto the next generation");
		nextButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nextButtonAction();
			}
		});
		toolbar.add(nextButton);
		autoButton = new JToggleButton(ICON_PLAY);
		autoButton.setSelectedIcon(ICON_PAUSE);
		autoButton.setToolTipText("Play/pause animation");
		autoButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				nextButton.setEnabled(!autoButton.isSelected());
				auto = autoButton.isSelected();
				if(autoButton.isSelected()){
					animatorThread = new Thread(new BoardAnimator());
					animatorThread.start();
				} else{
					animatorThread.interrupt();
					animatorThread = null;
				}
			}
		});
		toolbar.add(autoButton);
		return toolbar;
	}

	private Component getMainPanel() {
		JPanel mainPanel = new JPanel();
		table = new BoardTable(this.board, ROWS, COLS);

		boardTA = new JTextArea(board.toString());
		boardTA.setColumns(50);
		mainPanel.add(table);
		return mainPanel;
	}

	private void nextButtonAction() {
		board.nextGeneration();
		table.updateTable();
	}

	class BoardAnimator implements Runnable {
		private boolean interrupted = false;

		@Override
		public void run() {
			while (!interrupted) {
				if (auto) {
					nextButtonAction();
				}
				try {
					Thread.currentThread();
					Thread.sleep(INTERVAL);
				} catch (InterruptedException e) {
					interrupted = true;
				}
			}
		}
	}

	@Override
	public void finalize() {
		if (this.animatorThread != null) {
			this.animatorThread.interrupt();
		}
	}

}
