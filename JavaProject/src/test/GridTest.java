package test;

//import static org.junit.Assert.*;

import org.junit.Test;

import renderer.ImageWriter;

public class GridTest {

	@Test
	public void test() {
		ImageWriter w = new ImageWriter("image", 500,500, 50, 50);
		for(int i=0;i<500;i++)
		{
			for(int j=0;j<500;j++)
			{
				if(i%50==0||j%50==0)
					w.writePixel(i, j, 255, 255, 255);
			}
		}
		w.writeToimage();
	}
}

