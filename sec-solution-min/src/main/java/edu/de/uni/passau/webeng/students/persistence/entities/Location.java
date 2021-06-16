package edu.de.uni.passau.webeng.students.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Location {
    @Id
    private final long lat;
    //@Id
    private final long lng;
    //@Id
    private final Date dateFrom;

    private Date dateTo;
    private String details;

    public Location(long lat, long lng, Date dateFrom, Date dateTo, String details) {
        this.lat = lat;
        this.lng = lng;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.details = details;
    }

    public long getLat() {
        return lat;
    }

    public long getLng() {
        return lng;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void addDetails(String details) {
        this.details = this.details + "\n" + details;
    }
}
