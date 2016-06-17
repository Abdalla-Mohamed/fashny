/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import com.iti.fashny.interfaces.UserAccount;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findByPassword", query = "SELECT c FROM Company c WHERE c.password = :password"),
    @NamedQuery(name = "Company.findByWebsite", query = "SELECT c FROM Company c WHERE c.website = :website"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address"),
    @NamedQuery(name = "Company.findByValidated", query = "SELECT c FROM Company c WHERE c.validated = :validated"),
    @NamedQuery(name = "Company.findByLastSeen", query = "SELECT c FROM Company c WHERE c.lastSeen = :lastSeen"),
    @NamedQuery(name = "Company.findByWorkHoures", query = "SELECT c FROM Company c WHERE c.workHoures = :workHoures"),
    @NamedQuery(name = "Company.findByEmail", query = "SELECT c FROM Company c WHERE c.email = :email"),
    @NamedQuery(name = "Company.findByContactEmail", query = "SELECT c FROM Company c WHERE c.contactEmail = :contactEmail"),
    @NamedQuery(name = "Company.findByMobile1", query = "SELECT c FROM Company c WHERE c.mobile1 = :mobile1"),
    @NamedQuery(name = "Company.findByMobile2", query = "SELECT c FROM Company c WHERE c.mobile2 = :mobile2"),
    @NamedQuery(name = "Company.findByPhone", query = "SELECT c FROM Company c WHERE c.phone = :phone"),
    @NamedQuery(name = "Company.findByActive", query = "SELECT c FROM Company c WHERE c.active = :active")})
public class Company implements Serializable,UserAccount {

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
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "validated")
    private boolean validated;
    @Basic(optional = false)
    @Column(name = "last_seen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSeen;
    @Column(name = "work_houres")
    private String workHoures;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Trip> tripList;
    @JoinColumn(name = "profile_Pic", referencedColumnName = "id")
    @ManyToOne
    private Resouce profilePic;
    @JoinTable(name = "company_has_tag", joinColumns = {
        @JoinColumn(name = "company_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tagList;

    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Company(Integer id, String name, String password, String address, boolean validated, Date lastSeen, String email, String mobile1) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.validated = validated;
        this.lastSeen = lastSeen;
        this.email = email;
        this.mobile1 = mobile1;
    }

    public Company(Integer id, String name, String password, String website, String address, String description, boolean validated, Date lastSeen, String workHoures, String email, String contactEmail, String mobile1, String mobile2, String phone, Boolean active, List<Trip> tripList, Resouce profilePic, List<Tag> tagList) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.website = website;
        this.address = address;
        this.description = description;
        this.validated = validated;
        this.lastSeen = lastSeen;
        this.workHoures = workHoures;
        this.email = email;
        this.contactEmail = contactEmail;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.phone = phone;
        this.active = active;
        this.tripList = tripList;
        this.profilePic = profilePic;
        this.tagList = tagList;
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

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getWorkHoures() {
        return workHoures;
    }

    public void setWorkHoures(String workHoures) {
        this.workHoures = workHoures;
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
    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public Resouce getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Resouce profilePic) {
        this.profilePic = profilePic;
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Company[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

}
