package kwang.experimentals.jersey.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoField.MILLI_OF_SECOND;

/**
 * @author kevin.wang.cy@gmail.com
 */
public final class Clock {

    private final LocalDate localDate;

    private final LocalTime localTime;

    private final LocalDateTime localDateTime;

    private final ZonedDateTime zonedDateTime;
    private final ZonedDateTime zonedDateTimeTruncated ;

    private final Date utilDate;
    private final java.sql.Date sqlDate;
    private final java.sql.Timestamp sqlTimestamp;
    private final LocalDateTime localDateTime2;


    public Clock() {
        this.localDate = LocalDate.of(2016, 1, 1);
        this.localTime = LocalTime.of(10, 24);
        this.localDateTime = LocalDateTime.of(localDate, localTime);

        this.zonedDateTime = ZonedDateTime.now();
        zonedDateTimeTruncated = this.zonedDateTime.truncatedTo(ChronoUnit.MILLIS);

        this.utilDate = new Date(this.zonedDateTime.toInstant().toEpochMilli());
        this.sqlDate = new java.sql.Date(/*milliseconds*/utilDate.getTime());
        this.sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
        this.localDateTime2 = this.zonedDateTimeTruncated.toLocalDateTime();
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return this.zonedDateTime;
    }

    public ZonedDateTime getZonedDateTimeTruncated() {
        return this.zonedDateTime;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public Date getUtilDate() {
        return utilDate;
    }

    public LocalDateTime getLocalDateTime2() {
        return localDateTime2;
    }

    public Timestamp getSqlTimestamp() {
        return sqlTimestamp;
    }
}