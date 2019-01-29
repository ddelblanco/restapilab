package com.diegodelblanco.restapilab.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@RestResource
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "place", nullable = false)
    private String place;
    @Column(name = "date")
    private Date date;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "meetings_contacts",
            joinColumns = { @JoinColumn(name = "meeting_id",  referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "contact_id", referencedColumnName = "id") })
    @Column(name = "contacts")
    private List<Contact> contacts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Meeting() {
    }

    public Meeting(String place, Date date, List<Contact> contacts) {
        this.place = place;
        this.date = date;
        this.contacts = contacts;
    }

    public Meeting(Meeting meeting) {
        this.place = meeting.getPlace();
        this.date = meeting.getDate();
        this.contacts = meeting.getContacts();
    }

    public Meeting(Meeting meeting, long id) {
        this.id = id;
        this.place = meeting.getPlace();
        this.date = meeting.getDate();
        this.contacts = meeting.getContacts();
    }


}
