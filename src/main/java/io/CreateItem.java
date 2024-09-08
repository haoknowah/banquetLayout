package io;

public class CreateItem {

	private double length;
	private double width;
	private double radius;
	public CreateItem(double lengthft, double lengthin, double widthft, double widthin)
	{
		this.length = lengthft + (lengthin / 12);
		this.width = widthft + (widthin / 12);
	}
	
	public CreateItem(double radiusft, double radiusin)
	{
		this.radius = radiusft+ (radiusin / 12);
	}
}
