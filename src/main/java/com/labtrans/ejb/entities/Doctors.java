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
@Table(name = "tbl_doctors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctors.findAll", query = "SELECT d FROM Doctors d")})
public class Doctors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_doc_id")
    private String colDocId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_doc_code")
    private String colDocCode;
    @Size(max = 45)
    @Column(name = "col_fullname")
    private String colFullname;
    @Size(max = 45)
    @Column(name = "col_contact")
    private String colContact;
    @Size(max = 45)
    @Column(name = "col_residence")
    private String colResidence;
    @Size(max = 45)
    @Column(name = "col_pincode")
    private String colPincode;
    @Size(max = 45)
    @Column(name = "col_region")
    private String colRegion;
    @Size(max = 45)
    @Column(name = "col_hospital_name")
    private String colHospitalName;
    @Size(max = 45)
    @Column(name = "col_loc_of_hospital")
    private String colLocOfHospital;
    @Size(max = 45)
    @Column(name = "col_number_of_years_worked")
    private String colNumberOfYearsWorked;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date colDateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String colDeleted;

    public Doctors() {
    }

    public Doctors(String colDocId) {
        this.colDocId = colDocId;
    }

    public Doctors(String colDocId, String colDocCode) {
        this.colDocId = colDocId;
        this.colDocCode = colDocCode;
    }

    public String getColDocId() {
        return colDocId;
    }

    public void setColDocId(String colDocId) {
        this.colDocId = colDocId;
    }

    public String getColDocCode() {
        return colDocCode;
    }

    public void setColDocCode(String colDocCode) {
        this.colDocCode = colDocCode;
    }

    public String getColFullname() {
        return colFullname;
    }

    public void setColFullname(String colFullname) {
        this.colFullname = colFullname;
    }

    public String getColContact() {
        return colContact;
    }

    public void setColContact(String colContact) {
        this.colContact = colContact;
    }

    public String getColResidence() {
        return colResidence;
    }

    public void setColResidence(String colResidence) {
        this.colResidence = colResidence;
    }

    public String getColPincode() {
        return colPincode;
    }

    public void setColPincode(String colPincode) {
        this.colPincode = colPincode;
    }

    public String getColRegion() {
        return colRegion;
    }

    public void setColRegion(String colRegion) {
        this.colRegion = colRegion;
    }

    public String getColHospitalName() {
        return colHospitalName;
    }

    public void setColHospitalName(String colHospitalName) {
        this.colHospitalName = colHospitalName;
    }

    public String getColLocOfHospital() {
        return colLocOfHospital;
    }

    public void setColLocOfHospital(String colLocOfHospital) {
        this.colLocOfHospital = colLocOfHospital;
    }

    public String getColNumberOfYearsWorked() {
        return colNumberOfYearsWorked;
    }

    public void setColNumberOfYearsWorked(String colNumberOfYearsWorked) {
        this.colNumberOfYearsWorked = colNumberOfYearsWorked;
    }

    public Date getColDateCreated() {
        return colDateCreated;
    }

    public void setColDateCreated(Date colDateCreated) {
        this.colDateCreated = colDateCreated;
    }

    public String getColDeleted() {
        return colDeleted;
    }

    public void setColDeleted(String colDeleted) {
        this.colDeleted = colDeleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colDocId != null ? colDocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctors)) {
            return false;
        }
        Doctors other = (Doctors) object;
        if ((this.colDocId == null && other.colDocId != null) || (this.colDocId != null && !this.colDocId.equals(other.colDocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.Doctors[ colDocId=" + colDocId + " ]";
    }
    
}
