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
import javax.persistence.ManyToOne;
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
@Table(name = "service_categorey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceCategorey.findAll", query = "SELECT s FROM ServiceCategorey s"),
    @NamedQuery(name = "ServiceCategorey.findById", query = "SELECT s FROM ServiceCategorey s WHERE s.id = :id"),
    @NamedQuery(name = "ServiceCategorey.findByName", query = "SELECT s FROM ServiceCategorey s WHERE s.name = :name"),
    @NamedQuery(name = "ServiceCategorey.findByActive", query = "SELECT s FROM ServiceCategorey s WHERE s.active = :active")})
public class ServiceCategorey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "parteners_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partener partenersId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoreyServiceid")
    private List<Service> serviceList;

    public ServiceCategorey() {
    }

    public ServiceCategorey(Integer id) {
        this.id = id;
    }

    public ServiceCategorey(Integer id, String name) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Partener getPartenersId() {
        return partenersId;
    }

    public void setPartenersId(Partener partenersId) {
        this.partenersId = partenersId;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
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
        if (!(object instanceof ServiceCategorey)) {
            return false;
        }
        ServiceCategorey other = (ServiceCategorey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ServiceCategorey[ id=" + id + " ]";
    }
    
}
