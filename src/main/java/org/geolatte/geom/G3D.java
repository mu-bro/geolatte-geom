package org.geolatte.geom;

/**
 * @author Karel Maesen, Geovise BVBA
 *         creation-date: 2/19/14
 */
public class G3D extends G2D implements Vertical {

    public final static PositionTypeDescriptor<G3D> descriptor = new PositionTypeDescriptor<>(G3D.class,
            3, 2, -1);

    public G3D() {
        super();
    }

    public G3D(double lon, double lat, double alt) {
        super(lon, lat, alt);
    }

    protected G3D(double... coords) {
        super(coords);
    }

    @Override
    public double getAltitude() {
        return getCoordinate(2);
    }

    @Override
    public PositionTypeDescriptor<? extends G3D> getDescriptor() {
        return descriptor;
    }
}
