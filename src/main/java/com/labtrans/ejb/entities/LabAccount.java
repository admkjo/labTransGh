/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_id")
    private String id;
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
    @Size(max = 45)
    @Column(name = "col_date_created")
    private String dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;

    public LabAccount() {
    }

    public LabAccount(String id) {
        this.id = id;
    }

    public LabAccount(String id, String labcode, String nameOfOrginazation) {
        this.id = id;
        this.labcode = labcode;
        this.nameOfOrginazation = nameOfOrginazation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
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
