package es.uvigo.ei.sing.hlfernandez.gameoflife.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import es.uvigo.ei.sing.hlfernandez.gameoflife.Board;
import es.uvigo.ei.sing.hlfernandez.gameoflife.Cell;

public class BoardTable extends JTable {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SIZE = 15;
	private int cols;
	private int rows;
	private Board board;

	public BoardTable(Board board, int rows, int cols) {
		super();
		this.board = board;
		this.rows = rows;
		this.cols = cols;
		this.initComponent();
	}

	public synchronized void updateTable() {
		TableModel newModel = new DefaultTableModel(getData(), getColNames());
		setModel(newModel);
	}

	private void initComponent() {
		this.setTableProperties();
		this.updateTable();
	}

	private void setTableProperties() {
		this.setTableHeader(null);
		this.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		this.setShowHorizontalLines(false);
		this.setShowVerticalLines(false);
		this.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		this.setRowHeight(DEFAULT_SIZE);
		this.setFillsViewportHeight(false);
		this.setRowMargin(0);
		this.setIntercellSpacing(new Dimension(1, 1));
		this.setCellSelectionEnabled(false);
		this.setAutoCreateRowSorter(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.setDoubleBuffered(true);

	}

	private String[] getColNames() {
		String[] toret = new String[this.cols];
		for (int i = 0; i < this.cols; i++) {
			toret[i] = String.valueOf(i);
		}
		return toret;
	}

	private String[][] getData() {
		String[][] toret = new String[this.rows][this.cols];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				String cell = "";
				if (this.board.getCells().contains(new Cell(i, j))) {
					cell = "X";
				}
				toret[i][j] = cell;
			}
		}

		return toret;
	}

	private final class CustomTableCellRenderer extends JLabel implements
			TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CustomTableCellRenderer() {
			super();
			this.setOpaque(true);
			final Dimension size = new Dimension(DEFAULT_SIZE, DEFAULT_SIZE);
			this.setSize(size);
			this.setMinimumSize(size);
			this.setMaximumSize(size);
			this.setPreferredSize(size);
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if(value instanceof String ){
				if (value.equals("X")){
					this.setBackground(Color.BLACK);
				} else {
					this.setBackground(Color.WHITE);
				}
			}
			return this;
		}
		
	}
	
	@Override
	public void createDefaultColumnsFromModel() {
		super.createDefaultColumnsFromModel();

		final Enumeration<TableColumn> columns = this.getColumnModel()
				.getColumns();
		while (columns.hasMoreElements()) {
			final TableColumn column = columns.nextElement();
			if (this.getTableHeader() != null)
				this.getTableHeader().setResizingColumn(column);
			column.setPreferredWidth(DEFAULT_SIZE);
			column.setMaxWidth(DEFAULT_SIZE);
			column.setMinWidth(DEFAULT_SIZE);
			column.setWidth(DEFAULT_SIZE);
		}
	}
}
