package entities;

public class Triangle{
	public double a;
	public double b;
	public double c;
	
	public double Area() {
		double p = (a + b + c)/2;
		double Area =  Math.sqrt(p * (p - a) * (p - b) * (p - c));
		
		return Area;
	}
}
