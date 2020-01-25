import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

//William Sease
//2018
@SuppressWarnings("serial")
public class VectorProject extends JComponent {
	public boolean space[][][] = new boolean[201][201][201];
	public double[] vector = new double[6];
	public double[] hold = new double[3];
	public JFrame frame;
	public XYMap content;

	public static void main(String[] args) {
		VectorProject main = new VectorProject();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter an X for your vector:");
		main.vector[0] = Double.parseDouble(in.nextLine());
		main.vector[3] = main.vector[0];
		System.out.println("Please enter an Y for your vector:");
		main.vector[1] = Double.parseDouble(in.nextLine());
		main.vector[4] = main.vector[1];
		System.out.println("Please enter an Z for your vector:");
		main.vector[2] = Double.parseDouble(in.nextLine());
		main.vector[5] = main.vector[2];
		main.mapVector();
		main.ezMap();
		for (int i = 0; i < 3; i++)
			main.hold[i] = main.vector[i];
		while (true) {
			System.out.println("'X' to map another over X axis (Horizontal line)");
			System.out.println("'Y' to map another over Y axis (Vertical line)");
			System.out.println("'Z' to map another over Z axis (\"counter-clockwise\")");
			System.out.println("'S' to make it spin like a top.");
			System.out.println("Q to quit.");
			char a = in.nextLine().charAt(0);
			double b;
			if (a == 'Q')
				break;
			for (int i = 0; i < 3; i++)
				main.vector[i] = main.hold[i];
			switch (a) {
			case 'X':
			case 'x':
				System.out.println("By how much? (Any double is valid)");
				b = Double.parseDouble(in.nextLine());
				main.matrixMultiply(main.getStandardX(b / 57.2958));
				break;
			case 'Y':
			case 'y':
				System.out.println("By how much? (Any double is valid)");
				b = Double.parseDouble(in.nextLine());
				main.matrixMultiply(main.getStandardY(b / 57.2958));
				break;
			case 'Z':
			case 'z':
				System.out.println("By how much? (Any double is valid)");
				b = Double.parseDouble(in.nextLine());
				main.matrixMultiply(main.getStandardZ(b / 57.2958));
				break;
			case 's':
			case 'S':
				b = 1;
				while (b < 720) {
					long t1 = System.currentTimeMillis() + 100;
					while (System.currentTimeMillis() < t1);
					for (int i = 0; i < 3; i++)
						main.vector[i] = main.hold[i];
					main.matrixMultiply(main.getStandardY(b / 57.2958));
					main.mapVector();
					main.update();
					b+=5;
				}
				break;
			}
			main.mapVector();
			main.update();			
		}
		in.close();
	}

	public void mapVector() {
		for (int i = 0; i < 200; i++)
			for (int j = 0; j < 200; j++)
				for (int k = 0; k < 200; k++)
					space[i][j][k] = false;

		for (int i = 0; i < 200; i++) {
			if (((Math.round(vector[0] / 200 * i) + 100) > 199) || ((Math.round(vector[1] / 200 * i) + 100) > 199)
					|| ((Math.round(vector[2] / 200 * i) + 100) > 199) || ((Math.round(vector[0] / 200 * i) + 100) < 0)
					|| ((Math.round(vector[1] / 200 * i) + 100) < 0) || ((Math.round(vector[2] / 200 * i) + 100) < 0))
				continue;
			space[(int) (Math.round(vector[0] / 200 * i) + 100)][(int) (Math.round(vector[1] / 200 * i)
					+ 100)][(int) (Math.round(vector[2] / 200 * i) + 100)] = true;
		}
	}

	public void ezMap() {
		frame = new JFrame();
		frame.setTitle("Display");
		frame.setSize(216, 239);
		frame.setLocation(1000, 500);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new XYMap();
		content.passInSpace(space, vector);
		frame.add(content);
	}

	public void update() {
		content.passInSpace(space, vector);
		content.repaint();
	}

	public void matrixMultiply(double[][] matrix) {
		double[] temp = new double[3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				temp[i] += vector[i] * matrix[j][i];
			}
		for (int i = 0; i < 3; i++) {
			vector[i] = temp[i];
		}

	}

	public double[][] getStandardX(double t) {
		return new double[][] { { 1, 0, 0 }, { 0, Math.cos(t), 0 - Math.sin(t) }, { 0, Math.sin(t), Math.cos(t) } };
	}

	public double[][] getStandardY(double t) {
		return new double[][] { { Math.cos(t), 0, Math.sin(t) }, { 0, 1, 0 }, { 0 - Math.sin(t), 0, Math.cos(t) } };
	}

	public double[][] getStandardZ(double t) {
		return new double[][] { { Math.cos(t), 0 - Math.sin(t), 0 }, { Math.sin(t), Math.cos(t), 0 }, { 0, 0, 1 } };
	}
}
