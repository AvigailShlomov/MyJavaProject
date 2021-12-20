package test;

//import static org.junit.Assert.*;

import org.junit.Test;
import renderer.*;

public class ImageWriterTest 
{

	@Test
	public void test() 
	{
		ImageWriter img = new ImageWriter("test4", 500, 500, 500, 500);
		for(int i=0; i<500;i++)
		{
			for(int j=0; j<500;j++)
			{
				if(i%50==0||j%50==0)
					img.writePixel(j, i, 255, 255, 255);
			}
		}
		img.writeToimage();
	}

}
