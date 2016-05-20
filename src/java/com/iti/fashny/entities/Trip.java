/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hosam
 */
@Entity
@Table(name = "trip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
    @NamedQuery(name = "Trip.findById", query = "SELECT t FROM Trip t WHERE t.id = :id"),
    @NamedQuery(name = "Trip.findByName", query = "SELECT t FROM Trip t WHERE t.name = :name"),
    @NamedQuery(name = "Trip.findByCost", query = "SELECT t FROM Trip t WHERE t.cost = :cost"),
    @NamedQuery(name = "Trip.findByCountBooking", query = "SELECT t FROM Trip t WHERE t.countBooking = :countBooking"),
    @NamedQuery(name = "Trip.findByDate", query = "SELECT t FROM Trip t WHERE t.date = :date"),
    @NamedQuery(name = "Trip.findByJoinDeadline", query = "SELECT t FROM Trip t WHERE t.joinDeadline = :joinDeadline"),
    @NamedQuery(name = "Trip.findByMaxbooking", query = "SELECT t FROM Trip t WHERE t.maxbooking = :maxbooking"),
    @NamedQuery(name = "Trip.findByValidated", query = "SELECT t FROM Trip t WHERE t.validated = :validated"),
    @NamedQuery(name = "Trip.findByOffer", query = "SELECT t FROM Trip t WHERE t.offer = :offer")})
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "cost")
    private int cost;
    @Basic(optional = false)
    @Column(name = "count_booking")
    private int countBooking;
    @Basic(optional = false)
    @Lob
    @Column(name = "program")
    private String program;
    @Lob
    @Column(name = "need_tools")
    private String needTools;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "join_deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDeadline;
    @Basic(optional = false)
    @Column(name = "Max_booking")
    private int maxbooking;
    @Lob
    @Column(name = "hint")
    private String hint;
    @Basic(optional = false)
    @Column(name = "validated")
    private boolean validated;
    @Column(name = "offer")
    private Boolean offer;
    @JoinTable(name = "trip_has_place", joinColumns = {
        @JoinColumn(name = "Trip_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "place_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Place> placeList;
    @ManyToMany(mappedBy = "tripList")
    private List<Resouce> resouceList;
    @JoinTable(name = "trip_has_tag", joinColumns = {
        @JoinColumn(name = "trip_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Tag> tagList;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Company companyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private List<JoinTrip> joinTripList;

    public Trip() {
    }

    public Trip(Integer id) {
        this.id = id;
    }

    public Trip(Integer id, String name, int cost, int countBooking, String program, Date date, Date joinDeadline, int maxbooking, boolean validated) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.countBooking = countBooking;
        this.program = program;
        this.date = date;
        this.joinDeadline = joinDeadline;
        this.maxbooking = maxbooking;
        this.validated = validated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCountBooking() {
        return countBooking;
    }

    public void setCountBooking(int countBooking) {
        this.countBooking = countBooking;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getNeedTools() {
        return needTools;
    }

    public void setNeedTools(String needTools) {
        this.needTools = needTools;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getJoinDeadline() {
        return joinDeadline;
    }

    public void setJoinDeadline(Date joinDeadline) {
        this.joinDeadline = joinDeadline;
    }

    public int getMaxbooking() {
        return maxbooking;
    }

    public void setMaxbooking(int maxbooking) {
        this.maxbooking = maxbooking;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Boolean getOffer() {
        return offer;
    }

    public void setOffer(Boolean offer) {
        this.offer = offer;
    }

    @XmlTransient
    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    @XmlTransient
    public List<Resouce> getResouceList() {
        return resouceList;
    }

    public void setResouceList(List<Resouce> resouceList) {
        this.resouceList = resouceList;
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    @XmlTransient
    public List<JoinTrip> getJoinTripList() {
        return joinTripList;
    }

    public void setJoinTripList(List<JoinTrip> joinTripList) {
        this.joinTripList = joinTripList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trip[ id=" + id + " ]";
    }
    
}
