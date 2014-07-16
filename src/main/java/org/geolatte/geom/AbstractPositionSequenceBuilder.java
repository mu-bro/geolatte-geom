/*
 * This file is part of the GeoLatte project.
 *
 *     GeoLatte is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     GeoLatte is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with GeoLatte.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010 - 2011 and Ownership of code is shared by:
 * Qmino bvba - Romeinsestraat 18 - 3001 Heverlee  (http://www.qmino.com)
 * Geovise bvba - Generaal Eisenhowerlei 9 - 2140 Antwerpen (http://www.geovise.com)
 */

package org.geolatte.geom;

/**
 * @author Karel Maesen, Geovise BVBA
 *         creation-date: 4/25/11
 */
abstract class AbstractPositionSequenceBuilder<P extends Position> implements PositionSequenceBuilder<P> {

    protected final PositionTypeDescriptor<P> descriptor;
    protected final double[] coords;

    public AbstractPositionSequenceBuilder(PositionTypeDescriptor<P> descriptor) {
        if (descriptor == null) throw new IllegalArgumentException("Require a non-null Coordinate reference system.");
        this.descriptor = descriptor;
        this.coords = new double[descriptor.getCoordinateDimension()];
    }

    @Override
    public PositionSequenceBuilder<P> add(double... coordinates) {
        if (coordinates.length != this.descriptor.getCoordinateDimension())
            throw new IllegalArgumentException(String.format("Parameter must be array of length %d", descriptor.getCoordinateDimension()));
        for (int i = 0; i < descriptor.getCoordinateDimension(); i++) {
            addCoordinate(coordinates[i]);
        }
        return this;
    }

    @Override
    public PositionSequenceBuilder<P> add(P pos) {
        pos.toArray(coords);
        for (double coord : coords) {
            addCoordinate(coord);
        }
        return this;
    }

    protected abstract void addCoordinate(double val);
}
