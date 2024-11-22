package paint;

public class PaintApp {

	public static void main(String[] args) {
		Point point1 = new Point();
		point1.setX(10);
		point1.setY(20);
		// point1.show();
		// drawPoint(point1);
		draw(point1);
		Point point2 = new Point(100, 200);
		// point2.show(true);
		// drawPoint(point2);
		draw(point2);
		point2.show(false);

		// ColorPoint point3 = new ColorPoint();
		// 부모에 있는 메소드 사용 가능 (재사용)
		// point3.setX(50);
		// point3.setY(100);
		// point3.setColor("red");
		// drawColorPoint(point3);
		ColorPoint point3 = new ColorPoint(50, 100, "red");
		// ColorPoint의 show() 호출 - 다형성의 오버라이드
		// drawPoint(point3);
		draw(point3);

		// drawTriangle(new Triangle());
		// drawRectangle(new Rectangle());
//		drawShape(new Triangle());
//		drawShape(new Rectangle());
//		drawShape(new Circle());
		draw(new Triangle());
		draw(new Rectangle());
		draw(new Circle());
		draw(new GraphicText("안녕"));
	}

	public static void draw(Drawable drawable) {
		drawable.draw();
	}
	// static에서는 static만 부를 수 있다. main이 static이니까 main에서 부르는 함수도 static함수여야 하는 것
//	public static void drawPoint(Point point) {
//		point.show();
//	}
//
//	public static void drawShape(Shape shape) {
//		shape.draw();
//	}
//	public static void drawColorPoint(ColorPoint colorPoint) {
//		colorPoint.show();
//	}
//	public static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
//	
//	public static void drawRectangle(Rectangle rectangle) {
//		rectangle.draw();
//	}
}
