// Extend the Basic grid to allow for handling full rows.
public class TetrisGrid extends BasicTetrisGrid 
{
	public TetrisGrid()
	{
		super();
	}
	
	public int checkForFullRows()
	{
		int fullRowCounter, numberOfFullRows = 0;
		
		for(int y = 19; y >= 0; y--) // Start from last row
		{
			fullRowCounter=0;
			for(int x = 0; x < 10; x++)
				if(TetrisGrid[x][y] != GraphicsController.BLACK)
				{
					fullRowCounter++; // Count number of non-black squares in a row
				}
			if(fullRowCounter == 10) // Row is full
			{
				numberOfFullRows++;
				for(int j = y; j > 0; j--)
					for(int x = 0; x < 10; x++)
						TetrisGrid[x][j] = TetrisGrid[x][j-1]; // Move upper rows down
				for(int x = 0; x < 10; x++)
					TetrisGrid[x][0] = GraphicsController.BLACK; // Set upper row to black (no row above it to move down)
			}
				
		}
		return numberOfFullRows;	
				
	}
	
	public void setColour(int x, int y, int colour)
	{
		TetrisGrid[x][y] = colour;
	}
	
	public int getColour(int x, int y)
	{
		return TetrisGrid[x][y];
	}
	
	public boolean isFull()
	{
		for(int x = 0; x < 10; x++)
			if(TetrisGrid[x][0] != GraphicsController.BLACK)
				return true;
		return false;
	}
	
	public boolean canSpawnNewShape(TetrisDrawableObject shape) // Checks whether a new shape can be spawned. Used so that the last shape that spawns doesn't overlap an existing shape in the upper rows of the grid.
	{
		TetrisFileLoadingDrawableObject currentShape = shape.getCurrentShape();
		for(int x = 0; x < currentShape.getWidth(); x++)
			for(int y = 0; y < currentShape.getHeight(); y++)
				if(currentShape.getColour(x, y) != GraphicsController.BLACK) // If the non-black squares of the shape to be spawned overlap another shape in the grid, return false
					if(TetrisGrid[x + currentShape.getCoordX()][y + currentShape.getCoordY()] != GraphicsController.BLACK)
						return false;
		return true;
	}
}
