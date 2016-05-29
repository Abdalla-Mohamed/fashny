/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hosam
 */
@Entity
@Table(name = "place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p"),
    @NamedQuery(name = "Place.findById", query = "SELECT p FROM Place p WHERE p.id = :id"),
    @NamedQuery(name = "Place.findByName", query = "SELECT p FROM Place p WHERE p.name = :name"),
    @NamedQuery(name = "Place.findByAddress", query = "SELECT p FROM Place p WHERE p.address = :address"),
    @NamedQuery(name = "Place.findByWorkHours", query = "SELECT p FROM Place p WHERE p.workHours = :workHours"),
    @NamedQuery(name = "Place.findByLang", query = "SELECT p FROM Place p WHERE p.lang = :lang"),
    @NamedQuery(name = "Place.findByAttd", query = "SELECT p FROM Place p WHERE p.attd = :attd"),
    @NamedQuery(name = "Place.findByValidated", query = "SELECT p FROM Place p WHERE p.validated = :validated"),
    @NamedQuery(name = "Place.findByActive", query = "SELECT p FROM Place p WHERE p.active = :active")})
public class Place implements Serializable {

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
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "work_hours")
    private String workHours;
    @Basic(optional = false)
    @Column(name = "lang")
    private Double lang;
    @Basic(optional = false)
    @Column(name = "attd")
    private Double attd;
    @Basic(optional = false)
    @Column(name = "validated")
    private Boolean validated;
    @Column(name = "active")
    private Boolean active;
    @ManyToMany(mappedBy = "placeList")
    private List<Trip> tripList;
    @JoinTable(name = "place_has_tag", joinColumns = {
        @JoinColumn(name = "place_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Tag> tagList;
    @ManyToMany(mappedBy = "placeList")
    private List<Client> clientList;
    @ManyToMany(mappedBy = "placeList")
    private List<Resouce> resouceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placeId")
    private List<ClientReviewPlace> clientReviewPlaceList;

    public Place() {
    }

    public Place(Integer id) {
        this.id = id;
    }

    public Place(Integer id, String name, String address, String workHours, Double lang, Double attd, Boolean validated) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.workHours = workHours;
        this.lang = lang;
        this.attd = attd;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public Double getLang() {
        return lang;
    }

    public void setLang(Double lang) {
        this.lang = lang;
    }

    public Double getAttd() {
        return attd;
    }

    public void setAttd(Double attd) {
        this.attd = attd;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    public List<Resouce> getResouceList() {
        return resouceList;
    }

    public void setResouceList(List<Resouce> resouceList) {
        this.resouceList = resouceList;
    }

    @XmlTransient
    public List<ClientReviewPlace> getClientReviewPlaceList() {
        return clientReviewPlaceList;
    }

    public void setClientReviewPlaceList(List<ClientReviewPlace> clientReviewPlaceList) {
        this.clientReviewPlaceList = clientReviewPlaceList;
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
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Place[ id=" + id + " ]";
    }
    
}
