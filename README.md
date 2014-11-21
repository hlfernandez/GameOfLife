GameOfLife
========================

A Java implementation of the John Conway's Game of Life with a GUI.

Requirements
------------
To run this implementation of the game of life you need:
  - Java 1.7+
  - Maven 2+
  - Git

Installation
------------
### 1. Download
You can download the GameOfLife project directly from Github using the following command:
`git clone https://github.com/hlfernandez/GameOfLife.git`

### 2. Build 

First, go to the GameOfLife project base directory and run the following command:
`mvn package`

### 3. Launch
After building you can find the distribution file `gol-1.0.war` file in the `target` directory. Type `java -jar gol-1.0.jar` to run the application.

Usage
------------
The GUI has four simple options:

- Random: generates a random board.
- Load board: loads a board from a plain txt file (see next section for details).
- Next: moves onto the next generation.
- Play/pause: automatically generates the next generations. 

Board file format
------------
The format of a plain txt boar file is very simple: 

- Each line of the represents a row of the board.
- Each character in a line represents a cell: `1` means that the cell is alive and other character (`0` by convention) means that the cell is dead.

For example, the following file represents a board with three live cells at positions (0,0), (1,1) and (2,2):
>100

>010

>001