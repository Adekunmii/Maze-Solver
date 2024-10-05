Maze Solver in Java
This project implements a maze-solving algorithm using recursive backtracking. The program reads a maze from a text file and finds a path from the start to the exit, marking dead ends and the solution path along the way.

Features
Recursive backtracking algorithm to find the path.
File-based maze input: Load mazes of any size from a text file.
Path and dead end marking: The solution path is marked with +, and dead ends with ..
Customizable maze: Easily adaptable to different maze layouts and dimensions.
How to Run
Clone this repository:
bash
Copy code
git clone https://github.com/yourusername/MazeSolver.git
Navigate to the project directory:
bash
Copy code
cd MazeSolver
Run the Java program:
Make sure you have the correct path to your maze file.
In the Main.java, update the path of the maze file in this line:
java
Copy code
String mazeFilePath = "path_to_your_maze_file";
Then compile and run the program:
bash
Copy code
javac Main.java
java Main
Example Maze File
Here’s a sample format for a maze file:

bash
Copy code
7 9  # Dimensions: 7 rows, 9 columns
XXXXXXXXX
X+    X X
X X X   X
X X XXX X
X X   X X
X XXX X X
X     X-X
XXXXXXXXX
X: Walls
+: Start point
-: Exit point
Output
After running the program, the maze will be printed with the path marked with + and dead ends marked with ..
