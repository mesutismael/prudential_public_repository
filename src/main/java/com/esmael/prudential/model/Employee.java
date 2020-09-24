package com.esmael.prudential.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "employeeId")
    private Double employeeId;
    @Column(name = "prefix")
    private String prefix;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "eMail")
    private String eMail;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    @Column(name = "dateOfJoining")
    private Date dateOfJoining;
    @Column(name = "quarterOfJoining")
    private String quarterOfJoining;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "phoneNo")
    private String phoneNo;
    @Column(name = "postedRegion")
    private String postedRegion;

    public Double getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Double employeeId) {
        this.employeeId = employeeId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getQuarterOfJoining() {
        return quarterOfJoining;
    }

    public void setQuarterOfJoining(String quarterOfJoining) {
        this.quarterOfJoining = quarterOfJoining;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPostedRegion() {
        return postedRegion;
    }

    public void setPostedRegion(String postedRegion) {
        this.postedRegion = postedRegion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

}
