package com.absolutezero.gpslocator.map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//@RunWith(MockitoJunitRunner.class)
public class MapLocationTest {
    @Rule
    public ExpectedException mThrown = ExpectedException.none();

    private MapLocation mMapLocation;

    @Test
    public void constructor_WithEmptyDate_Return() {
        // Expected
        mThrown.expect(IllegalArgumentException.class);

        // When MapLocation is initialized with invalid date
        mMapLocation = new MapLocation(10, 10, "3122");
    }
}