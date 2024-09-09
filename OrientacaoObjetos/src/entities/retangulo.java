package entities;

public class retangulo {
	
	//=> variáveis do Objeto
	public double width,
				  height;
	
	//=> Métodos do Objeto
	public double Area() {
		return width * height;
	}
	
	public double Perimeter() {
		return (width * 2) + (height * 2);
	}
	
	public double Diagonal() {
		return Math.sqrt((Math.pow(width, 2.00)) + (Math.pow(height, 2.00)));
	}
	
}
