package com.posco.paint.point;

public class ColorPoint extends Point {
	private String color;

	public ColorPoint(int x, int y, String color) {
		// setX(x);
		// setY(y);
		// 부모 생성자는 자식생정자보다 먼저 호출되어야 한다
		super(x, y);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		// 부모의 기능을 사용하면 부분재구현, 안쓰고 완전 새로 구현시 완전 재구현. 보통 완전 재구현이 많다
		// super.show();

		System.out.println("Point[x=" + getX() + ", y=" + getY() + ", color=" + color + "]를 그렸습니다.");
	}

	@Override
	public void draw() {
		show();
	}
}
