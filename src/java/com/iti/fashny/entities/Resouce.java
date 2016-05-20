/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "resouce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resouce.findAll", query = "SELECT r FROM Resouce r"),
    @NamedQuery(name = "Resouce.findById", query = "SELECT r FROM Resouce r WHERE r.id = :id"),
    @NamedQuery(name = "Resouce.findByPath", query = "SELECT r FROM Resouce r WHERE r.path = :path"),
    @NamedQuery(name = "Resouce.findByDescription", query = "SELECT r FROM Resouce r WHERE r.description = :description"),
    @NamedQuery(name = "Resouce.findByType", query = "SELECT r FROM Resouce r WHERE r.type = :type")})
public class Resouce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "path")
    private String path;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private Integer type;
    @JoinTable(name = "service_has_resouce", joinColumns = {
        @JoinColumn(name = "resouce_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "service_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Service> serviceList;
    @JoinTable(name = "place_has_resouce", joinColumns = {
        @JoinColumn(name = "resouce_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "place_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Place> placeList;
    @JoinTable(name = "trip_has_resouce", joinColumns = {
        @JoinColumn(name = "resouce_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "trip_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Trip> tripList;
    @OneToMany(mappedBy = "profilePic")
    private List<Admin> adminList;
    @OneToMany(mappedBy = "profilePic")
    private List<Partener> partenerList;
    @OneToMany(mappedBy = "profilePic")
    private List<Client> clientList;
    @OneToMany(mappedBy = "profilePic")
    private List<Company> companyList;

    public Resouce() {
    }

    public Resouce(Integer id) {
        this.id = id;
    }

    public Resouce(Integer id, String path) {
        this.id = id;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @XmlTransient
    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    @XmlTransient
    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    @XmlTransient
    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    @XmlTransient
    public List<Partener> getPartenerList() {
        return partenerList;
    }

    public void setPartenerList(List<Partener> partenerList) {
        this.partenerList = partenerList;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
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
        if (!(object instanceof Resouce)) {
            return false;
        }
        Resouce other = (Resouce) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Resouce[ id=" + id + " ]";
    }
    
}
