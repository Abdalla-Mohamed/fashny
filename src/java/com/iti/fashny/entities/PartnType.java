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
import javax.persistence.Id;
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
@Table(name = "partn_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartnType.findAll", query = "SELECT p FROM PartnType p"),
    @NamedQuery(name = "PartnType.findById", query = "SELECT p FROM PartnType p WHERE p.id = :id"),
    @NamedQuery(name = "PartnType.findByName", query = "SELECT p FROM PartnType p WHERE p.name = :name")})
public class PartnType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Partener> partenerList;

    public PartnType() {
    }

    public PartnType(Integer id) {
        this.id = id;
    }

    public PartnType(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public List<Partener> getPartenerList() {
        return partenerList;
    }

    public void setPartenerList(List<Partener> partenerList) {
        this.partenerList = partenerList;
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
        if (!(object instanceof PartnType)) {
            return false;
        }
        PartnType other = (PartnType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PartnType[ id=" + id + " ]";
    }
    
}
