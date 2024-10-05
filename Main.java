import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Main {


    // Recursive function to traverse the maze
    public static boolean traverseMaze(char[][] maze, int x, int y, int exitRow, int exitCol, List<int[]> path, int[] startPos) {
        // Check boundaries
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            // System.out.println("Out of bounds: (" + x + ", " + y + ")");
            return false;
        }


        // If it's a wall or a dead end, return False
        if (maze[x][y] == 'X' || maze[x][y] == '.') {
            //System.out.println("Blocked or dead end: (" + x + ", " + y + ") -> " + maze[x][y]);
            return false;
        }


        // If it's already visited and it's not the start position, return False
        if (maze[x][y] == '+' && (x != startPos[0] || y != startPos[1])) {
            //System.out.println("Already visited: (" + x + ", " + y + ")");
            return false;
        }


        // If we reached the exit, return True
        if (x == exitRow && y == exitCol) {
            path.add(new int[]{x, y});
            //System.out.println("Exit found at: (" + x + ", " + y + ")");
            return true;
        }


        // Mark the current cell as part of the path, but don't mark the start position again
        if (x != startPos[0] || y != startPos[1]) {
            //System.out.println("Marking path: (" + x + ", " + y + ")");
            maze[x][y] = '+'; // Mark the path
        }
        path.add(new int[]{x, y}); // Add to path


        // Explore in all four directions: down, right, up, left
        if (traverseMaze(maze, x + 1, y, exitRow, exitCol, path, startPos) ||  // Move down
                traverseMaze(maze, x, y + 1, exitRow, exitCol, path, startPos) ||  // Move right
                traverseMaze(maze, x - 1, y, exitRow, exitCol, path, startPos) ||  // Move up
                traverseMaze(maze, x, y - 1, exitRow, exitCol, path, startPos)) {   // Move left
            return true;
        }


        // Mark the cell as part of a dead end and backtrack
        if (x != startPos[0] || y != startPos[1]) {
            //System.out.println("Dead end at: (" + x + ", " + y + ") - backtracking");
            maze[x][y] = '.'; // Mark as dead end
        }
        path.remove(path.size() - 1); // Remove from path
        return false;
    }


    // Function to load the maze from a file
    public static char[][] loadMazeFromFile(String filePath, int[] startPos, int[] endPos) throws Exception {
        List<char[]> mazeList = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String inputLine;


            // Read the first line containing the maze dimensions
            String[] dimensions = reader.readLine().trim().split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);


            int row = 0;
            while ((inputLine = reader.readLine()) != null) {
                char[] mazeRow = inputLine.trim().toCharArray();


                // Ensure the maze row matches expected column size
                if (mazeRow.length != cols) {
                    throw new IllegalArgumentException("Row " + row + " does not match expected column count of " + cols);
                }


                // Find start ('+') and end ('-') positions
                for (int col = 0; col < mazeRow.length; col++) {
                    if (mazeRow[col] == '+') {
                        startPos[0] = row;
                        startPos[1] = col;
                    } else if (mazeRow[col] == '-') {
                        endPos[0] = row;
                        endPos[1] = col;
                    }
                }
                mazeList.add(mazeRow);
                row++;
            }
        }


        // Convert List to 2D char array
        char[][] maze = new char[mazeList.size()][];
        for (int i = 0; i < mazeList.size(); i++) {
            maze[i] = mazeList.get(i);
        }


        return maze;
    }


    public static void main(String[] args) {
        try {
            int[] startPos = new int[2];  // Holds start position
            int[] endPos = new int[2];    // Holds exit position


            // Load the maze from a file
            String mazeFilePath = "C:\\CS113\\m\\src\\maze"; // Updated variable name
            char[][] maze = loadMazeFromFile(mazeFilePath, startPos, endPos);


            // Print start and exit positions
            System.out.println("Start: (" + startPos[0] + ", " + startPos[1] + ")");
            System.out.println("Exit: (" + endPos[0] + ", " + endPos[1] + ")");


            // List to store the path
            List<int[]> path = new ArrayList<>();


            // Traverse the maze
            if (traverseMaze(maze, startPos[0], startPos[1], endPos[0], endPos[1], path, startPos)) {
                System.out.println("Path found!");
            } else {
                System.out.println("No path exists.");
            }


            // Print the maze with the path and dead ends marked
            for (char[] row : maze) {
                System.out.println(new String(row));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

