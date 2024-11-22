package com.posco.paint.shape;

import com.posco.paint.i.Drawable;
import com.posco.paint.point.Point;

public abstract class Shape implements Drawable {
	private Point[] points;
	private String lineColor;
	private String fillColor;
	
	public abstract void draw();
}
