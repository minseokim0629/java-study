package prob05;

public class Rectangle extends Shape implements Resizable {

	public Rectangle(double width, double height) {
		super(width, height);
	}

	@Override
	public void resize(double rate) {
		width *= rate;
		height *= rate;
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public double getPerimeter() {
		return (width + height) * 2;
	}
}
