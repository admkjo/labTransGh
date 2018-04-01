/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adm_Kjo
 */
@Entity
@Table(name = "tbl_lab_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabAccount.findAll", query = "SELECT l FROM LabAccount l")})
public class LabAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "col_labcode")
    private String labcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "col_name_of_orginazation")
    private String nameOfOrginazation;
    @Size(max = 45)
    @Column(name = "col_location")
    private String location;
    @Size(max = 50)
    @Column(name = "col_verify_code")
    private String verifyCode;
    @Size(max = 50)
    @Column(name = "col_email")
    private String email;
    @Size(max = 45)
    @Column(name = "col_region")
    private String region;
    @Size(max = 45)
    @Column(name = "col_contact")
    private String contact;
    @Size(max = 45)
    @Column(name = "col_username")
    private String username;
    @Size(max = 45)
    @Column(name = "col_password")
    private String password;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;
    @OneToMany(mappedBy = "labAccount")
    private Collection<LabBranch> labBranchCollection;

    public LabAccount() {
    }

    public LabAccount(Integer id) {
        this.id = id;
    }

    public LabAccount(Integer id, String labcode, String nameOfOrginazation) {
        this.id = id;
        this.labcode = labcode;
        this.nameOfOrginazation = nameOfOrginazation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabcode() {
        return labcode;
    }

    public void setLabcode(String labcode) {
        this.labcode = labcode;
    }

    public String getNameOfOrginazation() {
        return nameOfOrginazation;
    }

    public void setNameOfOrginazation(String nameOfOrginazation) {
        this.nameOfOrginazation = nameOfOrginazation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Collection<LabBranch> getLabBranchCollection() {
        return labBranchCollection;
    }

    public void setLabBranchCollection(Collection<LabBranch> labBranchCollection) {
        this.labBranchCollection = labBranchCollection;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof LabAccount)) {
            return false;
        }
        LabAccount other = (LabAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabAccount[ id=" + id + " ]";
    }

}
