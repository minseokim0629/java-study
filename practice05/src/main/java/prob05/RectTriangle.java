package prob05;

public class RectTriangle extends Shape {

	public RectTriangle(double width, double height) {
		super(width, height);
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return width * height * 0.5;
	}

	@Override
	public double getPerimeter() {
		return width + height + Math.sqrt(width * width + height * height);
	}

}
