package com.matweaver.daterange;
import java.util.Date;

class dateRange {
    private Date begin;
    private Date end;

    public dateRange(Date startDate, Date EndDate) {
        begin = startDate;
        end = EndDate;
    }

    public Date getBegin() {
        return begin;
    }
    public Date getEnd() {
        return end;
    }
}