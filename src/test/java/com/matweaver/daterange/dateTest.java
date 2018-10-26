package com.matweaver.daterange;
import java.util.Date;
import java.util.Calendar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class dateTest {
    protected Date date1, date2, date3, date4;
    protected dateRange dateSet1, dateSet2;
    protected getOverlap theOverlap = new getOverlap();
    protected dateRange result;

    private void setTheDates(int days1, int days2, int days3, int days4) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt);
        if (days1 != 0) {
            c.add(Calendar.DATE, days1);
        }
        date1 = c.getTime();

        c.setTime(dt);
        c.add(Calendar.DATE, days2);
        date2 = c.getTime();

        c.setTime(dt);
        if (days3 != 0) {
            c.add(Calendar.DATE, days3);
        }
        date3 = c.getTime();

        c.setTime(dt);
        c.add(Calendar.DATE, days4);
        date4 = c.getTime();
        
        dateSet1 = new dateRange(date1, date2);
        dateSet2 = new dateRange(date3, date4);

    }
    @Test
        public void secondDatesInRangeOfFisrt() {
            // 1-4    2-3
            setTheDates(1, 4, 2, 3);
            result = theOverlap.calcOverlap(dateSet1, dateSet2);
            assertEquals(dateSet2.getBegin(), result.getBegin());
            assertEquals(dateSet2.getEnd(), result.getEnd());
        }
        @Test
        public void firstDatesInRangeOfSecond() {
            // 2-3    1-4
            setTheDates(2,3,1,4);
            result = theOverlap.calcOverlap(dateSet1, dateSet2);
            assertEquals(dateSet1.getBegin(), result.getBegin());
            assertEquals(dateSet1.getEnd(), result.getEnd());
        }

        @Test
        public void sameDateRange() {
            // 1-4    1-4
            setTheDates(1,4,1,4);
            result = theOverlap.calcOverlap(dateSet1, dateSet2);
            assertEquals(dateSet1.getBegin(), result.getBegin());
            assertEquals(dateSet1.getEnd(), result.getEnd());
        }

        @Test
        public void staggerdDateRange() {
            // 1-3    2-5
            setTheDates(1,3,2,5);
            result = theOverlap.calcOverlap(dateSet1, dateSet2);
            assertEquals(dateSet2.getBegin(), result.getBegin());
            assertEquals(dateSet1.getEnd(), result.getEnd());
        }
        @Test
        public void staggeredDateRangeInverted() {
            // 2-5    1-3
            setTheDates(2,5,1,3);
            result = theOverlap.calcOverlap(dateSet1, dateSet2);
            assertEquals(dateSet1.getBegin(), result.getBegin());
            assertEquals(dateSet2.getEnd(), result.getEnd());
        }

        @Test
        public void secondDateNotInRange() {
            // 1-2    3-4            
            setTheDates(1,2,3,4);
            assertThrows(IllegalArgumentException.class, () -> {
                theOverlap.calcOverlap(dateSet1, dateSet2);
            });
        }

        @Test
        public void firstDateNotInRange() {
            // 2-4    1-2                   
            setTheDates(1,2,3,4);
            assertThrows(IllegalArgumentException.class, () -> {
                theOverlap.calcOverlap(dateSet1, dateSet2);
            });
        }            
}