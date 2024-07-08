## How to Run
### Run Using Docker
Build docker image: `docker build -t mine-sweeper-app .` or `sudo docker build -t mine-sweeper-app .`\
Run docker container: `docker run -it mine-sweeper-app`
### Run Using Maven
Build the project: `mvn clean package`\
Run the generated jar in target: `java -jar MineSweeper-App-1.0-SNAPSHOT.jar`

## Design

**Maven based Dockerized Java Application**\
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

**Junit Unit Testing**\
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

## Assumptions
The grid size is between 2x2 and 10x10.\
The number of mines is between 1 and 35% of the total squares in the grid.\
The user inputs for grid size, mine count, and square selection are valid integer and string values.\
Invalid inputs are handled by prompting the user again until a valid input is provided.\
The game continues until the user either detonates a mine or successfully reveals all non-mine squares.\
After the game ends, the user can choose to play again or exit by entering -1.


