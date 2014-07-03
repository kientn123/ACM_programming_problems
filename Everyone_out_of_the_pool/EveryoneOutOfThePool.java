package acmPractices;

import java.util.Scanner;

public class EveryoneOutOfThePool {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNum = 1;
		while (true) {
			int x = in.nextInt();
			int y = in.nextInt();
			//System.out.println("" + x + " " + y);
			if (x == 0 && y == 0) {
				break;
			}
			
			int _x = (int) Math.sqrt(x);
			boolean include = false;
			int _y = (int) Math.sqrt(y);
			if (_y*_y == y) {
				include = true;
			}
			int count = 0;
			if (include) {
				for (int i=_x+1; i<_y; i++) {
					if (isTriangle(i*i-1)) {
						count++;
					}
				}
			}
			else {
				for (int i=_x+1; i<=_y; i++) {
					if (isTriangle(i*i-1)) {
						count++;
					}
				}
			}
			System.out.println("Case " + caseNum++ + ": " + count);
		}
	}
	
	public static boolean isTriangle(int n) {
		int m = (int) Math.sqrt(2*n);
		if (2*n == m*(m+1)) {
			return true;
		}
		return false;
	}
	
//	public static boolean isSquare(int n) {
//		int num = (int) Math.sqrt(n);
//		if (n == num*num) {
//			return true;
//		}
//		return false;
//	}
}