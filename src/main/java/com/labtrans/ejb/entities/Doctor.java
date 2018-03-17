/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tbl_doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d")})
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_doc_id")
    private String docId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_doc_code")
    private String docCode;
    @Size(max = 45)
    @Column(name = "col_fullname")
    private String fullname;
    @Size(max = 45)
    @Column(name = "col_contact")
    private String contact;
    @Size(max = 45)
    @Column(name = "col_residence")
    private String residence;
    @Size(max = 45)
    @Column(name = "col_pincode")
    private String pincode;
    @Size(max = 45)
    @Column(name = "col_region")
    private String region;
    @Size(max = 45)
    @Column(name = "col_hospital_name")
    private String hospitalName;
    @Size(max = 45)
    @Column(name = "col_loc_of_hospital")
    private String locOfHospital;
    @Size(max = 45)
    @Column(name = "col_number_of_years_worked")
    private String numberOfYearsWorked;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLocOfHospital() {
        return locOfHospital;
    }

    public void setLocOfHospital(String locOfHospital) {
        this.locOfHospital = locOfHospital;
    }

    public String getNumberOfYearsWorked() {
        return numberOfYearsWorked;
    }

    public void setNumberOfYearsWorked(String numberOfYearsWorked) {
        this.numberOfYearsWorked = numberOfYearsWorked;
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

    public Doctor() {
    }

    public Doctor(String colDocId) {
        this.docId = docId;
    }

    public Doctor(String docId, String docCode) {
        this.docId = docId;
        this.docCode = docCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.Doctor[ docId=" + docId + " ]";
    }

}
