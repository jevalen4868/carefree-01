package org.sp.j01.carefree.api;

import org.sp.j01.carefree.api.domain.Family;

import java.time.LocalDate;

/**
 * This is mapped to the initial state setup by test-data.sql.
 */
public class TestData {
    public static Family[] FAMILIES = {
            new Family(1L, "Valenzuela", null, "http://sampleurl.com/thumbnail1",
                    "http://sampleurl.com/standard1", "Triple Homicide1", 1, LocalDate.of(1989, 1, 1)),
            new Family(2L, "Garcia", null, "http://sampleurl.com/thumbnail2",
                    "http://sampleurl.com/standard2", "Triple Homicide2", 2, LocalDate.of(1989, 1, 2)),
            new Family(3L, "Tupac", null, "http://sampleurl.com/thumbnail3",
                    "http://sampleurl.com/standard3", "Triple Homicide3", 3, LocalDate.of(1989, 1, 3)),
            new Family(4L, "Biggie", null, "http://sampleurl.com/thumbnail4",
                    "http://sampleurl.com/standard4", "Triple Homicide4", 4, LocalDate.of(1989, 1, 4)),
            new Family(5L, "newbee", null, "http://sampleurl.com/thumbnail5",
                    "http://sampleurl.com/standard5", "Triple Homicide5", 5, LocalDate.of(1989, 1, 5)),
            new Family(6L, "arthur", null, "http://sampleurl.com/thumbnail6",
                    "http://sampleurl.com/standard6", "Triple Homicide6", 6, LocalDate.of(1989, 1, 6))
    };
}
