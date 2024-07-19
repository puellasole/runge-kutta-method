import static java.lang.Math.exp;

import java.util.Arrays;

public class Main {
	private static final double h = 0.2;
	private static final double a = 1.0;
	private static final double b = 2.0;
	
	public static double getU(double t) {
		return t * exp(t);
	}
	
	public static double getU1() {
		return exp(1);
	}

	public static double getU2() {
		return 2 * exp(1);
	}
	
	public static double getF1(double t, double y1, double y2) {
		return y2;
	}
	
	public static double getF2(double t, double y1, double y2) {
		return (1 - 2.0 / t) * y2 + (3 * t + 2) / t / t * y1;
	}
	
	public static double[] solveRunge(double h) {
		double[][] K = new double[3][2];
		int N = (int) ((b-a)/h);
		double[] y1 = new double[N + 1];
		double[] y2 = new double[N + 1];
		y1[0] = getU1();
		y2[0] = getU2();
		for(int i=0; i<N; i++) {
			double t = a + i*h;
			K[0][0] = getF1(t, y1[i], y2[i]);
			K[0][1] = getF2(t, y1[i], y2[i]);
			K[1][0] = getF1(t + h/3, y1[i] + h/3 * K[0][0], y2[i] + h/3 * K[0][1]);
			K[1][1] = getF2(t + h/3, y1[i] + h/3 * K[0][0], y2[i] + h/3 * K[0][1]);
			K[2][0] = getF1(t + h, y1[i] - h * K[0][0] + 2 * h * K[1][0], y2[i] - h * K[0][1] + 2 * h * K[1][1]);
			K[2][1] = getF2(t + h, y1[i] - h * K[0][0] + 2 * h * K[1][0], y2[i] - h * K[0][1] + 2 * h * K[1][1]);
			y1[i + 1] = y1[i] + h/4 * (3 * K[1][0] + K[2][0]);
			y2[i + 1] = y2[i] + h/4 * (3 * K[1][1] + K[2][1]);
		}
		return y1;
	}
	
	public static void main(String[] args) {
		double[] result1 = solveRunge(h);
		System.out.println(Arrays.toString(result1));
	}

}
