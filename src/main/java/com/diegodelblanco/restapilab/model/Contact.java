package com.diegodelblanco.restapilab.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;


@Entity
@RestResource
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToMany(mappedBy = "contacts")
    private List<Meeting> meetings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Contact() {
    }

    public Contact(Contact contact) {
        this.name = contact.getName();
        this.surname = contact.getSurname();
        this.email = contact.getEmail();
        this.phone = contact.getPhone();
        this.birthDate = contact.getBirthDate();
        this.meetings = contact.getMeetings();
    }

    public Contact(Contact contact, long id) {
        this.id = id;
        this.name = contact.getName();
        this.surname = contact.getSurname();
        this.email = contact.getEmail();
        this.phone = contact.getPhone();
        this.birthDate = contact.getBirthDate();
        this.meetings = contact.getMeetings();
    }

    public Contact(String name, String surname, String email, String phone, Date birthDate, List<Meeting> meetings) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.meetings = meetings;
    }

    public Contact(long id, String name, String surname, String email, String phone, Date birthDate, List<Meeting> meetings) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.meetings = meetings;
    }
}
