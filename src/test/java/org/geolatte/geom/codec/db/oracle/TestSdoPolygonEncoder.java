package org.geolatte.geom.codec.db.oracle;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.crs.CrsRegistry;
import org.geolatte.geom.crs.GeographicCoordinateReferenceSystem;
import org.junit.Test;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.polygon;
import static org.geolatte.geom.builder.DSL.ring;
import static org.junit.Assert.assertEquals;

/**
 * Created by Karel Maesen, Geovise BVBA on 01/04/15.
 */
public class TestSdoPolygonEncoder {

    GeographicCoordinateReferenceSystem<G2D> wgs84 = CrsRegistry.getGeographicCoordinateReferenceSystemForEPSG(4326);

    @Test
    public void test2DPolygonWithHole() {
        SDOGeometry expected = SDOGeometryHelper.sdoGeometry(2003, wgs84.getCrsId().getCode(), null, new int[]{1,
                1003, 1, 19, 2003, 1}, new Double[]{2d, 4d, 4d, 3d, 10d, 3d, 13d, 5d, 13d, 9d, 11d, 13d, 5d, 13d, 2d,
                11d, 2d, 4d, 7d, 5d, 7d, 10d, 10d, 10d, 10d, 5d, 7d, 5d});
        Polygon<G2D> poly = polygon(wgs84, ring(g(2, 4), g(4, 3), g(10, 3), g(13, 5), g(13, 9), g(11, 13), g(5, 13),
                g(2, 11), g(2, 4)), ring(g(7, 5), g(7, 10), g(10, 10), g(10, 5), g(7, 5)));

        assertEquals(expected, Encoders.encode(poly));
    }

    @Test
    public void test2DRectangle() {

        SDOGeometry expected = SDOGeometryHelper.sdoGeometry(2003, wgs84.getCrsId().getCode(), null, new int[]{1,
                1003, 1}, new Double[]{1.0, 1.0, 5.0, 1.0, 5.0, 7.0, 1.0, 7.0, 1.0, 1.0});
        Polygon<G2D> poly = polygon(wgs84, ring(g(1, 1), g(5, 1), g(5, 7), g(1, 7), g(1, 1)));

        assertEquals(expected, Encoders.encode(poly));
    }


    @Test
    public void test2DPolygonWithReversedOrientationRings() {
        SDOGeometry expected = SDOGeometryHelper.sdoGeometry(2003, wgs84.getCrsId().getCode(), null, new int[]{1,
                1003, 1, 19, 2003, 1}, new Double[]{2d, 4d, 4d, 3d, 10d, 3d, 13d, 5d, 13d, 9d, 11d, 13d, 5d, 13d, 2d,
                11d, 2d, 4d, 7d, 5d, 7d, 10d, 10d, 10d, 10d, 5d, 7d, 5d});
        Polygon<G2D> poly = polygon(wgs84, ring(g(2, 4), g(2, 11), g(5, 13), g(11, 13), g(13, 9), g(13, 5), g(10,
                3), g(4, 3), g(2, 4)), ring(g(7, 5),  g(10, 5), g(10, 10), g(7, 10),g(7, 5)));

        assertEquals(expected, Encoders.encode(poly));
    }


}
