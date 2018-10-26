package com.matweaver.daterange;
import java.util.Date;

public class getOverlap {

    public getOverlap() {}
    public dateRange calcOverlap(dateRange dateRange1, dateRange dateRange2) {

        long d1Start = dateRange1.getBegin().getTime();
        long d2Start = dateRange2.getBegin().getTime();
        long d1End = dateRange1.getEnd().getTime();
        long d2End = dateRange2.getEnd().getTime();

        if ( d2Start >= d1Start && d2Start <= d1End) {
            // date2 has a part in date1
            return new dateRange(new Date(d2Start), new Date(calcSmallOverlap(d1End, d2End)));
        } else if (d1Start >= d2Start && d1Start <= d2End) {
            // date1 has a part in date2.
            return new dateRange(new Date(d1Start), new Date(calcSmallOverlap(d2End, d1End)));
        } else {
            // is not in range.
            throw new IllegalArgumentException("It was not in range.");
        }

    }



    private long calcSmallOverlap(long d1End,long d2End) {
        // d2Start is in range d2Start is between d1Start and d1End
        if (d2End <= d1End) {
            return d2End;
        } else {
            // this means it's greater and staggered so pick the smaller.
            return d1End;
        }
    }
}