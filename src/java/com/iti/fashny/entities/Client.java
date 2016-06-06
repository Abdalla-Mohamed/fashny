/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.entities;

import com.iti.fashny.interfaces.UserAccount;
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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),
    @NamedQuery(name = "Client.findByContactEmail", query = "SELECT c FROM Client c WHERE c.contactEmail = :contactEmail"),
    @NamedQuery(name = "Client.findByPassword", query = "SELECT c FROM Client c WHERE c.password = :password"),
    @NamedQuery(name = "Client.findByAddress", query = "SELECT c FROM Client c WHERE c.address = :address"),
    @NamedQuery(name = "Client.findByBirthDate", query = "SELECT c FROM Client c WHERE c.birthDate = :birthDate"),
    @NamedQuery(name = "Client.findByGender", query = "SELECT c FROM Client c WHERE c.gender = :gender"),
    @NamedQuery(name = "Client.findByMaritalStatus", query = "SELECT c FROM Client c WHERE c.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "Client.findByLastSeen", query = "SELECT c FROM Client c WHERE c.lastSeen = :lastSeen"),
    @NamedQuery(name = "Client.findByMobile1", query = "SELECT c FROM Client c WHERE c.mobile1 = :mobile1"),
    @NamedQuery(name = "Client.findByMobile2", query = "SELECT c FROM Client c WHERE c.mobile2 = :mobile2"),
    @NamedQuery(name = "Client.findByPhone", query = "SELECT c FROM Client c WHERE c.phone = :phone"),
    @NamedQuery(name = "Client.findByActive", query = "SELECT c FROM Client c WHERE c.active = :active")})
public class Client implements Serializable,UserAccount {

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
    @Column(name = "email")
    private String email;
    @Column(name = "contact_email")
    private String contactEmail;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "gender")
    private short gender;
    @Basic(optional = false)
    @Column(name = "marital_status")
    private short maritalStatus;
    @Basic(optional = false)
    @Column(name = "last_seen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSeen;
    @Basic(optional = false)
    @Column(name = "mobile1")
    private String mobile1;
    @Column(name = "mobile2")
    private String mobile2;
    @Column(name = "phone")
    private String phone;
    @Column(name = "active")
    private Boolean active;
    @JoinTable(name = "wish", joinColumns = {
        @JoinColumn(name = "clients_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "places_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Place> placeList;
    @JoinTable(name = "interset", joinColumns = {
        @JoinColumn(name = "client_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Tag> tagList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private List<ClientReviewPlace> clientReviewPlaceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<JoinTrip> joinTripList;
    @JoinColumn(name = "profile_Pic", referencedColumnName = "id")
    @ManyToOne
    private Resouce profilePic;

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Client(Integer id, String name, String email, String password, String address, Date birthDate, short gender, short maritalStatus, Date lastSeen, String mobile1) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.lastSeen = lastSeen;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public short getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(short maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
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
    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @XmlTransient
    public List<ClientReviewPlace> getClientReviewPlaceList() {
        return clientReviewPlaceList;
    }

    public void setClientReviewPlaceList(List<ClientReviewPlace> clientReviewPlaceList) {
        this.clientReviewPlaceList = clientReviewPlaceList;
    }

    @XmlTransient
    public List<JoinTrip> getJoinTripList() {
        return joinTripList;
    }

    public void setJoinTripList(List<JoinTrip> joinTripList) {
        this.joinTripList = joinTripList;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ id=" + id + " ]";
    }
    
}
