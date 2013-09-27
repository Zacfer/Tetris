import java.util.Random;

// Tetris Game. Handles all the necessary information of the game and runs it
public class TetrisGame extends WindowController 
{
	private TetrisDrawableObject[] shapes; // Used an array, as it was more convenient to use a random index to get the random shape.
	private TetrisDrawableObject shapeInPlay;
	private TetrisGrid grid;
	private int score, delay, updateDelay, shapesAdded;
	private boolean gameOver, moveLeft, moveRight, leftPressed, rightPressed, speedUp;
	
	public TetrisGame()
	{
		super("Tetris", 1, 10, 20, 25, true, 70);
		shapes = new TetrisDrawableObject[7];
		shapes[0] = new TetrisDrawableObject("shape1a.txt", "shape1b.txt", "shape1c.txt", "shape1d.txt");
		shapes[1] = new TetrisDrawableObject("shape2a.txt", "shape2b.txt", "shape2c.txt", "shape2d.txt");
		shapes[2] = new TetrisDrawableObject("shape3a.txt", "shape3b.txt", "shape3c.txt", "shape3d.txt");
		shapes[3] = new TetrisDrawableObject("shape4a.txt", "shape4b.txt", "shape4c.txt", "shape4d.txt");
		shapes[4] = new TetrisDrawableObject("shape5a.txt", "shape5b.txt", "shape5c.txt", "shape5d.txt");
		shapes[5] = new TetrisDrawableObject("shape6a.txt", "shape6b.txt", "shape6c.txt", "shape6d.txt");
		shapes[6] = new TetrisDrawableObject("shape7a.txt", "shape7b.txt", "shape7c.txt", "shape7d.txt");
		grid = new TetrisGrid();
		shapeInPlay = new TetrisDrawableObject(getRandomShape());
		score = 0;
		updateDelay = 10;
		delay = 0;
		shapesAdded = 0;
		gameOver = false;
		moveLeft = false;
		moveRight = false;
		leftPressed = false;
		rightPressed = false;
		speedUp = false;
		this.start();
	}

	protected void buttonPressed(int arg0) 
	{

	}

	protected void keyPressed(Key arg0) 
	{
		if(arg0.isLeft())
		{
			if(grid.canMoveLeft(shapeInPlay))
			{
				moveLeft = true;
				leftPressed = true;
			}
		}
		else if(arg0.isRight())
		{
			if(grid.canMoveRight(shapeInPlay))
			{
				moveRight = true;
				rightPressed = true;
			}
		}
		else if(arg0.isDown())
			speedUp = true;
		else if(arg0.isUp())
		{
			if(grid.canRotate(shapeInPlay))
				shapeInPlay.rotate();
		}

	}

	protected void keyReleased(Key arg0) 
	{
		if(arg0.isLeft())
			moveLeft = false;
		if(arg0.isRight())
			moveRight = false;
		if(arg0.isDown())
			speedUp = false;
	}

	protected void update() 
	{
		if(gameOver == false)
		{
			score += grid.checkForFullRows();
			setLabelText("The current score is: " + score);
			if((score%10 == 0) && (updateDelay >= 2))
				updateDelay--;
			if(grid.isFull() || !grid.canSpawnNewShape(shapeInPlay)) // Game is over if the new shape spawns overlapping another stationary shape as well.
			{
				gameOver = true;
				setLabelText("Game Over! Final Score: " + score);
			}
			if((++delay > updateDelay) || (speedUp == true))
			{
				if(grid.canMoveDown(shapeInPlay))
					shapeInPlay.moveDown();
				delay = 0;
			}
			if((moveLeft == true) || (leftPressed == true))
			{
				if(grid.canMoveLeft(shapeInPlay))
					shapeInPlay.moveLeft();
				leftPressed = false; // Set lefPressed to false after move has been carried out, otherwise shape keeps moving left
			}
			if((moveRight == true) || (rightPressed == true))
			{
				if(grid.canMoveRight(shapeInPlay))
					shapeInPlay.moveRight();
				rightPressed = false; // Set rightPressed to false after move has been carried out, otherwise shape keeps moving right
			}
			if(!grid.canMoveDown(shapeInPlay)) 
			{
				if(!grid.isFull() && grid.canSpawnNewShape(shapeInPlay)) 
				{
					grid.add(shapeInPlay);
					shapesAdded++; // For Extension 1
					shapeInPlay = new TetrisDrawableObject(getRandomShape());
				}
			}
			if(shapesAdded == 10) // Extension 1
			{
				Random randomGenerator = new Random();
				int blocksToBeAdded = randomGenerator.nextInt(9); // Choose a random number of blocks to add, lower than 10
				
				for(int y = 0; y < 19; y++)
					for(int x = 0; x < 10; x++)
						if(grid.getColour(x, y+1) != GraphicsController.BLACK) // Starts from top and sets each non-black square to the colour of the square one position down.
							grid.setColour(x, y, grid.getColour(x, y+1));
				for(int x = 0; x < 10; x++) // Bottom row
				{
					if(blocksToBeAdded >= 0)
					{
						if(randomGenerator.nextInt(2) == 1) // Randomly choose whether the block is going to be added
							grid.setColour(x, 19, randomGenerator.nextInt(8)+1); // Choose a non-black colour to add.
					}
					blocksToBeAdded--;
				}
				shapesAdded = 0;
					
			}
			draw(grid);
			if(grid.canSpawnNewShape(shapeInPlay)) // So that a new shape is not drawn when game is over.
				draw(shapeInPlay);
		}
	}
	
	public TetrisDrawableObject getRandomShape()
	{
		Random randomShapeIndex = new Random();
		
		return shapes[randomShapeIndex.nextInt(7)];
	}
	
	public static void main(String[] args)
	{
		new TetrisGame();
	}
}
