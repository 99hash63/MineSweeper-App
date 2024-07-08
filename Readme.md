## How to Run

### Run Using Docker
Build docker image: `docker build -t mine-sweeper-app .` or `sudo docker build -t mine-sweeper-app .`\
Run docker container: `docker run -it mine-sweeper-app`


### Run Using Maven
Build the project: `mvn clean package`\
Run the generated jar in target: `java -jar MineSweeper-App-1.0-SNAPSHOT.jar`

## Design and Assumptions of the Minesweeper Solution

**Main Application (App Class):**
The App class contains the main method which initializes and starts the MineSweeperGame.

**GameUtil Class:**
Contains utility methods for MineSweeperGame Class

**Grid Class:**
Represents the game board.
Responsible for initializing the grid, placing mines, and calculating the number of adjacent mines for each square, revealing squares and printing the grid
Contains a 2D array of Square objects.

**InputUtil Class:**
Extract user inputs and validate

**MineSweeperGame Class:**
Handles the main game loop and game state.
Responsible for starting the game, running the game while prompting messages, and checking win/lose conditions.

**Square Class:**
Represents each square on the grid.
Contains properties to indicate whether it is a mine, whether it has been revealed, and how many adjacent mines it has.