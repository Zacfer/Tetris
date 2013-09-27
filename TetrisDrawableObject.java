
// Class which represents a Tetris shape, and all 4 of its possible rotations. The information for the rotation is held in seperate TetrisFileLoadingDrawableObjects
public class TetrisDrawableObject extends DrawableObject 
{
	private TetrisFileLoadingDrawableObject shape1, shape2, shape3, shape4, currentShape;
	private int coordX = 0, coordY = 0, index;

	public TetrisDrawableObject(String rotationA, String rotationB, String rotationC, String rotationD)
	{
		shape1 = new TetrisFileLoadingDrawableObject(rotationA);
		shape2 = new TetrisFileLoadingDrawableObject(rotationB);
		shape3 = new TetrisFileLoadingDrawableObject(rotationC);
		shape4 = new TetrisFileLoadingDrawableObject(rotationD);
		currentShape = shape1; // Use a TetrisFileLoadingDrawableObject object to keep the current shape
		index = 1; // Keep the index of the current rotation (1 corresponds to a, 2 to b, etc)
	}
	
	public TetrisDrawableObject(TetrisDrawableObject cpy) // Simply copy by calling the copy constructor of each rotation. Set all other details to default. 
	{
		shape1 = new TetrisFileLoadingDrawableObject(cpy.shape1);
		shape2 = new TetrisFileLoadingDrawableObject(cpy.shape2);
		shape3 = new TetrisFileLoadingDrawableObject(cpy.shape3);
		shape4 = new TetrisFileLoadingDrawableObject(cpy.shape4);
		coordX = 0;
		coordY = 0;
		index = 1;
		currentShape = shape1;
	}
	
	public void draw(GraphicsController arg0) 
	{
		currentShape.draw(arg0); // Draw the current rotation only.
	}
	
	public TetrisFileLoadingDrawableObject getCurrentShape()
	{
		return currentShape;
	}
	
	public TetrisFileLoadingDrawableObject getNextRotation()
	{
		switch(index)
		{
		case 1: return shape2;
		case 2: return shape3;
		case 3: return shape4;
		case 4: return shape1;
		default: return null;
		}
	}
	
	public int getNextRotationIndex()
	{
		if(index != 4)
			return index+1;
		else
			return 1;
	}
	
	public boolean isSafeToRotate()
	{
		TetrisFileLoadingDrawableObject nextRotation = getNextRotation();
		
		for(int i = 0; i < nextRotation.getWidth(); i++)
			for(int j = 0; j < nextRotation.getHeight(); j++)
				if(nextRotation.getColour(i, j) != GraphicsController.BLACK) // Checks whether the non-black squares of a potential next rotation are within the bounds of the grid
					if(((nextRotation.getCoordX() + i) >= 10) || ((nextRotation.getCoordX() + i) < 0) || ((nextRotation.getCoordY() + j) < 0) || ((nextRotation.getCoordY() + j) >= 20))
						return false;
		return true;
	}
	
	public void rotate()
	{
		if(isSafeToRotate())
		{
			currentShape = getNextRotation();
			index = getNextRotationIndex();
		} else
			try {
				throw new InvalidMovementException();
			} catch (InvalidMovementException e) {
			}
	}
	
	public void moveDown()
	{
		if(coordY + (currentShape.getHeight()-1) + 1 < 20) // Ensure movement is within bounds of y axis
		{
			updateCoordinates(0, 1);
			currentShape.setCoordY(coordY);
		} else
			try {
				throw new InvalidMovementException();
			} catch (InvalidMovementException e) {
			}
	}
	
	public void moveLeft()
	{
		if(currentShape.left2ColumnsAreBlack()) // Ensure movement is within bounds of the x axis for a left movement, according to how many of the left columns of a shape are black
		{
			if(coordX - 1 >= -2)
			{
				updateCoordinates(-1, 0);
				currentShape.setCoordX(coordX);
			} else
				try {
					throw new InvalidMovementException();
				} catch (InvalidMovementException e) {
				}
		}
		else if(currentShape.leftColumnIsBlack())
		{
			if(coordX - 1 >= -1)
			{
				updateCoordinates(-1, 0);
				currentShape.setCoordX(coordX);
			} else
				try {
					throw new InvalidMovementException();
				} catch (InvalidMovementException e) {
				}
		}
		else
		{
			if(coordX - 1 >= 0)
			{
				updateCoordinates(-1, 0);
				currentShape.setCoordX(coordX);
			} else
				try {
					throw new InvalidMovementException();
				} catch (InvalidMovementException e) {
				}
		}
	}
	
	public void moveRight()
	{

		if(currentShape.right2ColumnsAreBlack()) // Ensure movement is within bounds of the x axis for a left movement, according to how many of the left columns of a shape are black
		{
			if(coordX + (currentShape.getWidth()-1) + 1 < 12)
			{
				updateCoordinates(1, 0);
				currentShape.setCoordX(coordX);
			} else
				try {
					throw new InvalidMovementException();
				} catch (InvalidMovementException e) {
				}
		}
		else if(currentShape.rightColumnIsBlack())
		{
			if(coordX + (currentShape.getWidth()-1) + 1 < 11)
			{
				updateCoordinates(1, 0);
				currentShape.setCoordX(coordX);
			} else
				try {
					throw new InvalidMovementException();
				} catch (InvalidMovementException e) {
				}
		}
		else
		{
			if(coordX + (currentShape.getWidth()-1) + 1 < 10)
			{
				updateCoordinates(1, 0);
				currentShape.setCoordX(coordX);
			} else
				try {
					throw new InvalidMovementException();
				} catch (InvalidMovementException e) {
				}
		}
	}
	
	public void updateCoordinates(int coordXoffset, int coordYoffset) //Update coordinates for each of the rotation according to the offsets
	{
		coordX += coordXoffset;
		coordY += coordYoffset;
		shape1.setCoordX(coordX);
		shape1.setCoordY(coordY);
		shape2.setCoordX(coordX);
		shape2.setCoordY(coordY);
		shape3.setCoordX(coordX);
		shape3.setCoordY(coordY);
		shape4.setCoordX(coordX);
		shape4.setCoordY(coordY);
	}
}
