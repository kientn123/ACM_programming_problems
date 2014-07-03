
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FoldyButGoody {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		// Use HashMap to store the correspondent angle with 2^i
		Scanner in = new Scanner(System.in);
		int numOfCases = in.nextInt();
		in.nextLine();
		int numCase = 1;
		while (numCase <= numOfCases) 
		{
			String line = in.nextLine();
			Scanner lineScan = new Scanner(line);
			String arrayUpAndLow = lineScan.next();
			int anglePosition = lineScan.nextInt();
			char[] angleArray = new char[arrayUpAndLow.length()];
			for (int i=0; i<arrayUpAndLow.length(); i++) 
			{
				angleArray[i] = arrayUpAndLow.charAt(arrayUpAndLow.length()-1-i);
			}
		
			HashMap<Integer, Character> hm = new HashMap<Integer, Character>();
			for (int i=0; i<angleArray.length; i++)
			{
				int key = (int) Math.pow(2, i);
				hm.put(key, angleArray[i]);
			}
			//char toReturn = (char) hm.get((int) Math.pow(2, 1));
			//System.out.println(toReturn);
			//int numOfPoints = (int) Math.pow(2, angleArray.length) + 1;
			//System.out.println(numOfPoints);
			//@SuppressWarnings("rawtypes")
			HashMap<Integer, Point> points = new HashMap<Integer, Point>();
//			points[0] = new Point(0, 0);
//			points[1] = new Point(1, 0);
			points.put(0, new Point(0, 0));
			points.put(1, new Point(1, 0));
			for (int i=1; i<=angleArray.length; i++)
			{
				int nextIndex = (int) Math.pow(2, i);
				int currentIndex = (int) Math.pow(2, i-1);
				char angleOfCurrent = (char) hm.get(currentIndex);
				points.put(nextIndex, coordinateOfTurnedPoint(points.get(currentIndex), points.get(0), angleOfCurrent));
			}
			
			
			
			ArrayList<Integer> arrList = new ArrayList<Integer>();
			int i = anglePosition;
			while (i>0)
			{
				if (isPowerOf2(i)) 
				{
					arrList.add(i);
					break;
				}
				arrList.add(i);
				i = nearest(i) - (i - nearest(i));
			}
			
			if (arrList.size() == 0) 
			{
				System.out.println("(0,0)");
			}
			else if (arrList.size() == 1) 
			{
				System.out.println("(" + ((Point) points.get(arrList.get(0))).x + "," +((Point) points.get(arrList.get(0))).y + ")");
			}
			else 
			{
				for (int j=arrList.size()-2; j>=0; j--) 
				{
					int num = nearest(arrList.get(j));
					char angle = (char) hm.get(num);
					points.put(arrList.get(j), coordinateOfTurnedPoint((Point) points.get(num), (Point) points.get(arrList.get(j+1)), angle)); 
				}
				System.out.println("(" + ((Point) points.get(arrList.get(0))).x + "," +((Point) points.get(arrList.get(0))).y + ")");
			}
			lineScan.close();
			numCase++;
		}	
		in.close();
	}
//	private static class Point
//	{
//		public int x;
//		public int y;
//		
//		public Point(int x, int y) 
//		{
//			this.x = x;
//			this.y = y;
//		}
//	}
//	
	private static Point coordinateOfTurnedPoint(Point pointToTurn, Point pointToMakeCopy, char direction) 
	{
		int x = 0;
		int y = 0;
		Point toReturn = null;
		if (direction == 'U')
		{
			x = pointToMakeCopy.y - pointToTurn.y + pointToTurn.x;
			y = pointToTurn.x - pointToMakeCopy.x + pointToTurn.y;	
			toReturn = new Point(x, y);
		}
		else 
		{
			x = pointToTurn.y - pointToMakeCopy.y + pointToTurn.x;
			y = pointToMakeCopy.x - pointToTurn.x + pointToTurn.y;
			toReturn = new Point(x, y);
		}
		return toReturn;
	}
	
	public static boolean isPowerOf2(int i)
	{
		int num = (int) (Math.log(i)/Math.log(2));
		int check = (int) Math.pow(2, num);
		if (check == i)
		{
			return true;
		}
		return false;
	}
	
	public static int nearest(int i) 
	{
		if (isPowerOf2(i)) 
		{
			return i;
		}
		else 
		{
			int num = (int) (Math.log(i)/Math.log(2));
			return (int) Math.pow(2, num);
		}
	}
	
}
