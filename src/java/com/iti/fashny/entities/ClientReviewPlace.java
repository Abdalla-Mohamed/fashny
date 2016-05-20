/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "client_review_place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientReviewPlace.findAll", query = "SELECT c FROM ClientReviewPlace c"),
    @NamedQuery(name = "ClientReviewPlace.findById", query = "SELECT c FROM ClientReviewPlace c WHERE c.id = :id"),
    @NamedQuery(name = "ClientReviewPlace.findByComment", query = "SELECT c FROM ClientReviewPlace c WHERE c.comment = :comment")})
public class ClientReviewPlace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Client clientId;
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Place placeId;

    public ClientReviewPlace() {
    }

    public ClientReviewPlace(Integer id) {
        this.id = id;
    }

    public ClientReviewPlace(Integer id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Place getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Place placeId) {
        this.placeId = placeId;
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
        if (!(object instanceof ClientReviewPlace)) {
            return false;
        }
        ClientReviewPlace other = (ClientReviewPlace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ClientReviewPlace[ id=" + id + " ]";
    }
    
}
