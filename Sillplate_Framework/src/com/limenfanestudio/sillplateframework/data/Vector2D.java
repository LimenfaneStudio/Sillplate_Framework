/*
 * File:		Vector2D.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.27
 * Purpose:		Defines a 2D floating-point vector class and related
 * 				mathematical operations
 */

package com.limenfanestudio.sillplateframework.data;

// 2D floating-point vector with related mathematical operations
public class Vector2D {
	
	// The horizontal component of this vector
	public double x = 0.0d;
	// The vertical component of this vector
	public double y = 0.0d;
	
	/*
	 * Constructs a 2D zero-vector
	 */
	public Vector2D() {
		x = 0.0d;
		y = 0.0d;
	}
	/*
	 * Constructs a 2D vector
	 * Parameter: double x - The horizontal component for this vector
	 * Parameter: double y - The vertical component for this vector
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/*
	 * Compute the sum of this vector with another
	 * Parameter: Vector2D v - The vector to add to this one
	 * Returns: Vector2D - The sum of this vector and v
	 */
	public Vector2D add(Vector2D v) {
		return new Vector2D(x + v.x, y + v.y);
	}
	/*
	 * Compute the difference of this vector and another
	 * Parameter: Vector2D v - The vector to subtract from this one
	 * Returns: Vector2D - The difference between this vector and v
	 */
	public Vector2D subtract(Vector2D v) {
		return new Vector2D(x - v.x, y - v.y);
	}
	/*
	 * Compute the scalar product of this vector and a value
	 * Parameter: double a - The value to scale this vector by
	 * Returns: Vector2D - This vector scaled by a
	 */
	public Vector2D scale(double a) {
		return new Vector2D(x * a, y * a);
	}
	/*
	 * Multiply the elements of this vector and another
	 * Parameter: Vector2D v - The vector to multiply with this vector's
	 * elements
	 * Returns: Vector2D - The element-wise product of this vector and v
	 */
	public Vector2D multiply(Vector2D v) {
		return new Vector2D(x * v.x, y * v.y);
	}
	/*
	 * Compute the sum of this vector's components
	 * Returns: double - The sum of this vector's components
	 */
	public double sum() {
		return x + y;
	}
	/*
	 * Compute the magnitude of this vector
	 * Returns: double - The magnitude of this vector
	 */
	public double magnitude() {
		return Math.sqrt((x * x) + (y * y));
	}
	/*
	 * Compute the dot product of this vector and another
	 * Parameter: Vector2D v - The vector to multiply with this one
	 * Returns: double - The dot product of this vector and v
	 */
	public double dot(Vector2D v) {
		return multiply(v).sum();
	}
	/*
	 * Compute a vector with length 1 and the same direction as this one
	 * Returns: Vector2D - The normalized vector
	 */
	public Vector2D normalize() {
		return scale(1.0d / magnitude());
	}
	/*
	 * Compute the angle/direction of this vector
	 * Returns: double - The angle of this vector off the horizontal in radians
	 */
	public double angle() {
		if (x == 0.0d) {
			return 0.0d;
		}
		return Math.atan(y / x);
	}

}
