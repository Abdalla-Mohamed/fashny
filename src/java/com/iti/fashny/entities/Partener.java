/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import com.iti.fashny.interfaces.UserAccount;
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
@Table(name = "partener")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partener.findAll", query = "SELECT p FROM Partener p"),
    @NamedQuery(name = "Partener.findById", query = "SELECT p FROM Partener p WHERE p.id = :id"),
    @NamedQuery(name = "Partener.findByName", query = "SELECT p FROM Partener p WHERE p.name = :name"),
    @NamedQuery(name = "Partener.findByPassword", query = "SELECT p FROM Partener p WHERE p.password = :password"),
    @NamedQuery(name = "Partener.findByWebsite", query = "SELECT p FROM Partener p WHERE p.website = :website"),
    @NamedQuery(name = "Partener.findByAddress", query = "SELECT p FROM Partener p WHERE p.address = :address"),
    @NamedQuery(name = "Partener.findByWorkHours", query = "SELECT p FROM Partener p WHERE p.workHours = :workHours"),
    @NamedQuery(name = "Partener.findByValidated", query = "SELECT p FROM Partener p WHERE p.validated = :validated"),
    @NamedQuery(name = "Partener.findByEmail", query = "SELECT p FROM Partener p WHERE p.email = :email"),
    @NamedQuery(name = "Partener.findByContactEmail", query = "SELECT p FROM Partener p WHERE p.contactEmail = :contactEmail"),
    @NamedQuery(name = "Partener.findByMobile1", query = "SELECT p FROM Partener p WHERE p.mobile1 = :mobile1"),
    @NamedQuery(name = "Partener.findByMobile2", query = "SELECT p FROM Partener p WHERE p.mobile2 = :mobile2"),
    @NamedQuery(name = "Partener.findByPhone", query = "SELECT p FROM Partener p WHERE p.phone = :phone"),
    @NamedQuery(name = "Partener.findByActive", query = "SELECT p FROM Partener p WHERE p.active = :active")})
public class Partener implements Serializable,UserAccount {

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
    @Column(name = "password")
    private String password;
    @Column(name = "website")
    private String website;
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "work_hours")
    private String workHours;
    @Column(name = "validated")
    private Boolean validated;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "contact_email")
    private String contactEmail;
    @Basic(optional = false)
    @Column(name = "mobile1")
    private String mobile1;
    @Column(name = "mobile2")
    private String mobile2;
    @Column(name = "phone")
    private String phone;
    @Column(name = "active")
    private Boolean active;
    @JoinTable(name = "partener_has_tag", joinColumns = {
        @JoinColumn(name = "partener_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Tag> tagList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partenersId")
    private List<ServiceCategorey> serviceCategoreyList;
    @JoinColumn(name = "profile_Pic", referencedColumnName = "id")
    @ManyToOne
    private Resouce profilePic;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PartnType type;

    public Partener() {
    }

    public Partener(Integer id) {
        this.id = id;
    }

    public Partener(Integer id, String name, String password, String email, String mobile1) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.mobile1 = mobile1;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @XmlTransient
    public List<ServiceCategorey> getServiceCategoreyList() {
        return serviceCategoreyList;
    }

    public void setServiceCategoreyList(List<ServiceCategorey> serviceCategoreyList) {
        this.serviceCategoreyList = serviceCategoreyList;
    }

    public Resouce getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Resouce profilePic) {
        this.profilePic = profilePic;
    }

    public PartnType getType() {
        return type;
    }

    public void setType(PartnType type) {
        this.type = type;
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
        if (!(object instanceof Partener)) {
            return false;
        }
        Partener other = (Partener) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Partener[ id=" + id + " ]";
    }
    
}
