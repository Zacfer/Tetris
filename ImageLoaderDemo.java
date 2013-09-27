
//Test the FileLoadingDrawableObject class with a simple creation of an object and drawing it.
public class ImageLoaderDemo extends WindowController
{
	private FileLoadingDrawableObject test;
	
	public ImageLoaderDemo(String filename)
	{
		super("ImageLoader", 1, 20, 20, 10, true);
		test = new FileLoadingDrawableObject(filename);
		this.start();
		
	}

	protected void buttonPressed(int arg0) 
	{
		
	}

	protected void keyPressed(Key arg0) 
	{	
		
	}

	protected void keyReleased(Key arg0) 
	{

	}

	protected void update() 
	{
		draw(test);
	}
	
	public static void main(String[] args)
	{
		new ImageLoaderDemo("demo1.txt");
	}
}
