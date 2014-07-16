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
 * A PositionSequenceBuilder for <code>PointSequence</code>s with known size.
 *
 * @author Karel Maesen, Geovise BVBA, 2011
 */
class FixedSizePositionSequenceBuilder<P extends Position> extends AbstractPositionSequenceBuilder<P> {

    private int index = 0;
    private final double[] coordinates;

    FixedSizePositionSequenceBuilder(int capacity, PositionTypeDescriptor<P> descriptor) {
        super(descriptor);
        this.coordinates = new double[capacity * descriptor.getCoordinateDimension()];
    }

    FixedSizePositionSequenceBuilder(int capacity, Class<P> clazz) {
        this(capacity, Positions.getDescriptor(clazz));
    }

    protected void addCoordinate(double x) {
        this.coordinates[index++] = x;
    }

    @Override
    public PositionSequence<P> toPositionSequence() {
        if (index != coordinates.length) {
            throw new IllegalStateException("PointSequence not filled to capacity.");
        }
        return new PackedPositionSequence<P>(descriptor, this.coordinates);
    }

}
