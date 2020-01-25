
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class XYMap extends JComponent
{
	boolean[][][] space = new boolean[201][201][201];
	double[] vector = new double[3];
	public boolean done = false;
	
	public void paintComponent(Graphics g)
	{
		g.fillRect(1000, 500, 200, 200);
		g.setColor(Color.BLACK);
		g.drawRect(this.getX() + 100, this.getY(), 1, 200);
		g.drawRect(this.getX(), this.getY() + 100, 200, 1);
		g.drawString("x = " + vector[3], this.getX() + 10, this.getY() + 170);
		g.drawString("y = " + vector[4], this.getX() + 10, this.getY() + 180);
		g.drawString("z = " + vector[5], this.getX() + 10, this.getY() + 190);
		g.drawString("x2 = " + vector[0], this.getX() + 110, this.getY() + 170);
		g.drawString("y2 = " + vector[1], this.getX() + 110, this.getY() + 180);
		g.drawString("z2 = " + vector[2], this.getX() + 110, this.getY() + 190);
		for(int x = 0; x < 200; x++)
			for(int y = 0; y < 200; y++)
				for(int z = 0; z < 200; z++)
				{
					if(space[x][y][z])
					{
						if (z > 100)
							g.setColor(new Color(
									.2f, .2f, ((float) z/200)));
						else g.setColor(new Color(
								((1 - (float) z/200)), .2f, .2f));
							g.drawRect(x, 200-y, 1, 1);
					}
				}
		//long time = System.currentTimeMillis();
		//while (System.currentTimeMillis() < time + 400) {}
		g.clearRect(1000, 500, 100, 100);
	}
	
	public void passInSpace(boolean[][][] space, double[] vector)
	{
		this.space = space;
		this.vector = vector;
	}
}
