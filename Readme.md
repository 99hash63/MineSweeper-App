## How to Run
### Run Using Docker
Build docker image: `docker build -t mine-sweeper-app .` or `sudo docker build -t mine-sweeper-app .`\
Run docker container: `docker run -it mine-sweeper-app`
### Run Using Maven
Build the project: `mvn clean package`\
Run the generated jar in target: `java -jar MineSweeper-App-1.0-SNAPSHOT.jar`

## Design
**Main Application (App Class):**\
The App class contains the main method which initializes and starts the MineSweeperGame.

**GameUtil Class:**\
Contains utility methods for MineSweeperGame Class

**Grid Class:**\
Represents the game board.\
Responsible for initializing the grid, placing mines, calculating the number of adjacent mines for each square, revealing squares and printing the grid.\
Contains a 2D array of Square objects.

**InputUtil Class:**\
Extract user inputs and validate

**MineSweeperGame Class:**\
Handles the main game loop and game state.\
Responsible for starting the game, running the game while prompting messages, and checking win/lose conditions.

**Square Class:**\
Represents each square on the grid.\
Contains properties to indicate whether it is a mine, whether it has been revealed, and how many adjacent mines it has.

## Testing

Unit testing using junit jupiter\
\
**GridTest:**\
Tests if grid generates with correct size and mine count.\
Tests adjacent mine calculation of the whole grid.\
Tests if reveal square functionality works.\
Tests print grid functionality for both small and large grids.

**InputUtilTest:**\
Tests all input valid/invalid scenarios

**MineSweeperGameTest:**\
Tests maximum mine count for different grid sizes (35%)\
Tests game win functionality
