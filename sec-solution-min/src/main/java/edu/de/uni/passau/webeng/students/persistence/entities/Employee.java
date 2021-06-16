package edu.de.uni.passau.webeng.students.persistence.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Employee {
    @Id
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long employeeId;

    @OneToOne
    private Location currentLocation;

    @OneToMany
    private final List<Location> locationHistory;

    private final String name;
    private Date validityDate;
    private String password;

    public Employee(String name, Date validityDate, String password,
                    Location currentLocation) {
        this.name = name;
        this.validityDate = validityDate;
        this.password = password;
        this.currentLocation = currentLocation;
        this.locationHistory = new LinkedList<>();
        this.locationHistory.add(currentLocation);
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        locationHistory.add(currentLocation);
    }

    public List<Location> getLocationHistory() {
        return locationHistory;
    }

}
