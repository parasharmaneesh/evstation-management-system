package com.dzm.evsms.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;

public interface GISUtils {

	public static Point createPoint(double latitude, double longitude) {
		return new GeometryFactory().createPoint(new Coordinate(latitude, longitude));
	}

	public static Geometry createCircle(Point center, double radius) {
		GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
		shapeFactory.setNumPoints(32);
		shapeFactory.setCentre(center.getCoordinate());
		shapeFactory.setSize(radius * 2);
		return shapeFactory.createCircle();
	}

	public static double convertKMToDegree(double distanceInKM) {
		return distanceInKM / 111;
	}

	public static double convertDegreeToKM(double distanceInDegree) {
		return distanceInDegree * 111;
	}


}
