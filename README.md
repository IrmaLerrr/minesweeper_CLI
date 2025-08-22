# Minesweeper CLI

A classic Minesweeper game built with Java, designed to run right in your terminal. This project was completed as part of the [Hyperskill](https://hyperskill.org/projects/77) educational project.

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)
![Architecture](https://img.shields.io/badge/Architecture-OOP-blue)

## How to Run It

**Prerequisites:** Make sure you have Java 17 or later installed on your machine.

1.  **Clone & Navigate:**
    ```bash
    git clone https://github.com/IrmaLerrr/minesweeper_CLI.git
    cd minesweeper_CLI
    cd src
    ```

2.  **Compile & Play:**
    ```bash
    # Compile the Java source files
    javac *.java

    # Run the game!
    java Main
    ```

## How to Play

1.  **Start the game:** Run the program, and you'll be asked to define your playing field.
    > *Example: Enter `5 5` for a 5x5 grid, then `5` for 5 mines.*

2.  **Make your moves:**
    *   To **explore** a cell, type its coordinates, type `free` after the coordinates (e.g., `2 3 free`).
    *   To **place (or remove) a flag** on a suspected mine, type `mine` after the coordinates (e.g., `2 3 mine`).

3.  **Win the game:** Win by successfully flagging all mines or revealing every safe cell without triggering an explosion!

## Project Architecture

This project follows an Object-Oriented Programming (OOP) design:

- **Main.java**                 
Entry point. Bootstraps the game. 
- **GameEngine.java**           
The brain of the game. Manages the game loop, input, and state. 
- **MineField.java**            
Represents the game board grid and contains the core logic for generating mines, calculating hints, and revealing cells. 
- **MineFieldCell.java**        
A class representing a single cell on the board (its value, state). 
- **MineFieldCellState.java**   
An enum defining the possible states of a cell (e.g., OPENED, CLOSED, MARKED).
- **InputHandler.java**   
An class that handles user input.
