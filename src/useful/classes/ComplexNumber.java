package useful.classes;

public class ComplexNumber {
	
	private double a, b;
	
	public ComplexNumber(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	public ComplexNumber square(){
		return new ComplexNumber(a*a - b*b, 2*a*b);
	}
	
	public ComplexNumber add(ComplexNumber c){
		return new ComplexNumber(c.a + this.a, c.b + this.b);
	}
	
	public double magnitude(){
		return a*a+b*b;
	}
}