package Helpers;

import java.util.Date;

public class DateRange {
    private final Date _start;
    private final Date _end;

    public DateRange(Date start, Date end) {
        this._start = start;
        this._end = end;
    }

    public Date getStart() {
        return _start;
    }

    public Date getEnd() {
        return _end;
    }
}
