/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hosam
 */
@Entity
@Table(name = "join_trip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JoinTrip.findAll", query = "SELECT j FROM JoinTrip j"),
    @NamedQuery(name = "JoinTrip.findByClientId", query = "SELECT j FROM JoinTrip j WHERE j.joinTripPK.clientId = :clientId"),
    @NamedQuery(name = "JoinTrip.findByTripid", query = "SELECT j FROM JoinTrip j WHERE j.joinTripPK.tripid = :tripid"),
    @NamedQuery(name = "JoinTrip.findByCount", query = "SELECT j FROM JoinTrip j WHERE j.count = :count"),
    @NamedQuery(name = "JoinTrip.findByRate", query = "SELECT j FROM JoinTrip j WHERE j.rate = :rate")})
public class JoinTrip implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JoinTripPK joinTripPK;
    @Basic(optional = false)
    @Column(name = "count")
    private int count;
    @Basic(optional = false)
    @Column(name = "rate")
    private int rate;
    @JoinColumn(name = "Trip_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Trip trip;
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Client client;

    public JoinTrip() {
    }

    public JoinTrip(JoinTripPK joinTripPK) {
        this.joinTripPK = joinTripPK;
    }

    public JoinTrip(JoinTripPK joinTripPK, int count, int rate) {
        this.joinTripPK = joinTripPK;
        this.count = count;
        this.rate = rate;
    }

    public JoinTrip(int clientId, int tripid) {
        this.joinTripPK = new JoinTripPK(clientId, tripid);
    }

    public JoinTripPK getJoinTripPK() {
        return joinTripPK;
    }

    public void setJoinTripPK(JoinTripPK joinTripPK) {
        this.joinTripPK = joinTripPK;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (joinTripPK != null ? joinTripPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JoinTrip)) {
            return false;
        }
        JoinTrip other = (JoinTrip) object;
        if ((this.joinTripPK == null && other.joinTripPK != null) || (this.joinTripPK != null && !this.joinTripPK.equals(other.joinTripPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.JoinTrip[ joinTripPK=" + joinTripPK + " ]";
    }
    
}
