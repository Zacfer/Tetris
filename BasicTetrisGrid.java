// Interface of tetris grid. Holds the information of the grid, adds shapes to the grid and checks for validity of potential movements in the grid.
public class BasicTetrisGrid extends DrawableObject 
{
	protected int TetrisGrid[][];
	
	public BasicTetrisGrid()
	{
		TetrisGrid  = new int[10][20]; // Create the grid and paint it black
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 20; j++)
				TetrisGrid[i][j] = GraphicsController.BLACK;
	}

	public void draw(GraphicsController arg0) 
	{
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 20; j++)
				arg0.setGridColour(i, j, TetrisGrid[i][j]);
	}
	
	public void add(TetrisDrawableObject shape) //Adds a stationary shape to the grid
	{
		TetrisFileLoadingDrawableObject currentShape = shape.getCurrentShape();
		
		for(int i = 0; i < currentShape.getWidth(); i++)
			for(int j = 0; j < currentShape.getHeight(); j++)
				if(currentShape.getColour(i, j) != GraphicsController.BLACK) // Adds the non-black squares of a shape to the grid
					TetrisGrid[i + currentShape.getCoordX()][j + currentShape.getCoordY()] = currentShape.getColour(i, j);
	}
	
	public boolean canMoveDown(TetrisDrawableObject shape)
	{
		TetrisFileLoadingDrawableObject currentShape = shape.getCurrentShape();
		
		for(int i = 0; i < currentShape.getWidth(); i++)
			for(int j = 0; j < currentShape.getHeight(); j++)
				if((currentShape.getColour(i, j) != GraphicsController.BLACK)) // Checks whether a shape's non-black squares can move down
					if(j + currentShape.getCoordY() + 1 < 20) // Used to check that movement is within bounds and that an ArrayIndexOutofBoundsException is not thrown in the next check
					{
						if(TetrisGrid[i + currentShape.getCoordX()][j + currentShape.getCoordY() + 1] != GraphicsController.BLACK)  //Checks whether the squares ahead are occupied
							return false;
					}else
						return false;
		return true;
	}
	
	public boolean canMoveLeft(TetrisDrawableObject shape)
	{
		TetrisFileLoadingDrawableObject currentShape = shape.getCurrentShape();
		
		for(int i = 0; i < currentShape.getWidth(); i++)
			for(int j = 0; j < currentShape.getHeight(); j++)
				if((currentShape.getColour(i, j) != GraphicsController.BLACK) ) // Same as above, for left
					if(i + currentShape.getCoordX() - 1 >= 0)
					{
						if(TetrisGrid[i + currentShape.getCoordX() - 1][j + currentShape.getCoordY()] != GraphicsController.BLACK)
							return false;
					}
					else
						return false;
		return true;
	}
	
	public boolean canMoveRight(TetrisDrawableObject shape)
	{
		TetrisFileLoadingDrawableObject currentShape = shape.getCurrentShape();
		
		for(int i = 0; i < currentShape.getWidth(); i++)
			for(int j = 0; j < currentShape.getHeight(); j++)
				if((currentShape.getColour(i, j) != GraphicsController.BLACK))
					if(i + currentShape.getCoordX() + 1 < 10) // Same as above for right
					{
						if(TetrisGrid[i + currentShape.getCoordX() + 1][j + currentShape.getCoordY()] != GraphicsController.BLACK)
							return false;
					}else
						return false;
		return true;
	}	
	
	public boolean canRotate(TetrisDrawableObject shape)
	{
		TetrisFileLoadingDrawableObject nextRotation = shape.getNextRotation();
		
		for(int i = 0; i < nextRotation.getWidth(); i++)
			for(int j = 0; j < nextRotation.getHeight(); j++)
				if(shape.isSafeToRotate() && (nextRotation.getColour(i, j) != GraphicsController.BLACK)) // Same as above for rotation
					if(TetrisGrid[i + nextRotation.getCoordX()][j + nextRotation.getCoordY()] != GraphicsController.BLACK)
						return false;
		return true;
	}	

}
