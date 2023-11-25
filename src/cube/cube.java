package cube;

import java.awt.geom.Line2D;
import java.lang.Math;

public class cube {
	int screenHeight, screenWidth;
	int scale = 100;
	
	double angleX = 0;
	double angleY = 0;
	double angleZ = 0;
	
	int cubePoints[][] = {{-1,-1,-1},
				{-1,-1,1},
				{1,-1,1},
				{1,-1,-1},
				{-1,1,-1},
				{-1,1,1},
				{1,1,1},
				{1,1,-1},
			};
	
	int projectionMatrix[][] = {
			{1,0,0},
			{0,1,0}
		};
	
	int projected2d[][] = new int[8][2];
	
	Line2D[] lines = new Line2D[12];
	
	cube(int screenWidth,int screenHeight) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	void drawLines() {
		lines[0] = createLine(projected2d[0], projected2d[1]);
		lines[1] = createLine(projected2d[0], projected2d[3]);
		lines[2] = createLine(projected2d[0], projected2d[4]);
		lines[3] = createLine(projected2d[2], projected2d[1]);
		lines[4] = createLine(projected2d[2], projected2d[3]);
		lines[5] = createLine(projected2d[2], projected2d[6]);
		lines[6] = createLine(projected2d[5], projected2d[1]);
		lines[7] = createLine(projected2d[5], projected2d[4]);
		lines[8] = createLine(projected2d[5], projected2d[6]);
		lines[9] = createLine(projected2d[7], projected2d[3]);
		lines[10] = createLine(projected2d[7], projected2d[4]);
		lines[11] = createLine(projected2d[7], projected2d[6]);
		
	}
	
	Line2D createLine(int[] a, int[] b) {
		return new Line2D.Double(a[0],a[1],b[0],b[1]);
	}
	
	void projectPoints() {
		
		double XrotationMatix[][] = {
				{1,0,0},
				{0, Math.cos(angleX), Math.sin(angleX)*-1},
				{0, Math.sin(angleX), Math.cos(angleX)}
		};
		
		double YrotationMatix[][] = {
				{Math.cos(angleY), 0, Math.sin(angleY)},
				{0,1,0},
				{0, Math.sin(angleY)*-1, Math.cos(angleY)}
		};
		
		double ZrotationMatix[][] = {
				{Math.cos(angleZ), Math.sin(angleZ)*-1,0},
				{Math.sin(angleZ), Math.cos(angleZ),0},
				{0,0,1}
		};
		
		angleX += 0.1;
		angleY += 0.1;
		angleZ += 0;
		
		//prints rotation matix
		/*
		System.out.println(Arrays.toString(XrotationMatix[0]));
		System.out.println(Arrays.toString(XrotationMatix[1]));
		System.out.println(Arrays.toString(XrotationMatix[2]));
		System.out.println();
		*/
		
		for (int i = 0; i < 8; i++) {
			
			double rotatedPoints[] = matMul(XrotationMatix,cubePoints[i]);
			
			rotatedPoints = matMul(YrotationMatix, rotatedPoints);
			
			rotatedPoints = matMul(ZrotationMatix, rotatedPoints);
			
			for(int j=0; j<3; j++) {
				rotatedPoints[j] *= scale;
			}
			
			projected2d[i] = matMul(projectionMatrix, rotatedPoints);
			
			//print points coordinate
			//System.out.println(Arrays.toString(projected2d[i]));
			
			projected2d[i][0] = (projected2d[i][0] ) + (screenWidth / 2);
			projected2d[i][1] = (projected2d[i][1] ) + (screenHeight / 2);

		}
	}
	
	 public double[] matMul(double[][] m, int[] point) {
		 
		 
		 int row = m.length;
		 int col = m[0].length;
		 
		 double[] result = new double[row];
		 
		 for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				result[i] += m[i][j] * point[j];
			}
		}
		 
		 return result;
	 }
	 
	 public double[] matMul(double[][] m, double[] point) {
		 
		 
		 int row = m.length;
		 int col = m[0].length;
		 
		 double[] result = new double[row];
		 
		 for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				result[i] += m[i][j] * point[j];
			}
		}
		 
		 return result;
	 }
	 
	 public int[] matMul(int[][] m, double[] point) {
		 
		 
		 int row = m.length;
		 int col = m[0].length;
		 
		 int[] result = new int[row];
		 
		 for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				result[i] += Math.round(m[i][j] * point[j]);
			}
		}
		 
		 return result;
	 }
	 
}

