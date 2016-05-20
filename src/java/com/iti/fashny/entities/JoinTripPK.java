/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Hosam
 */
@Embeddable
public class JoinTripPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "client_id")
    private int clientId;
    @Basic(optional = false)
    @Column(name = "Trip_id")
    private int tripid;

    public JoinTripPK() {
    }

    public JoinTripPK(int clientId, int tripid) {
        this.clientId = clientId;
        this.tripid = tripid;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getTripid() {
        return tripid;
    }

    public void setTripid(int tripid) {
        this.tripid = tripid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clientId;
        hash += (int) tripid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JoinTripPK)) {
            return false;
        }
        JoinTripPK other = (JoinTripPK) object;
        if (this.clientId != other.clientId) {
            return false;
        }
        if (this.tripid != other.tripid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.JoinTripPK[ clientId=" + clientId + ", tripid=" + tripid + " ]";
    }
    
}
