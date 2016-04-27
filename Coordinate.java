package pictionary;

public class Coordinate {
	private double X, Y;
	
	public Coordinate()
	{
		X = 0.0;
		Y = 0.0;
	}
	
	public Coordinate(double x, double y)
	{
		X = x;
		Y = y;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return X;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		X = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return Y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		Y = y;
	}
}
