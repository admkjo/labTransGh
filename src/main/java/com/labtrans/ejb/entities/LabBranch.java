/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adm_Kjo
 */
@Entity
@Table(name = "tbl_lab_branch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabBranch.findAll", query = "SELECT l FROM LabBranch l")})
public class LabBranch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_branch_id")
    private String branchId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_branch_code")
    private String branchCode;
    @Size(max = 45)
    @Column(name = "col_labcode")
    private String labcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_branch_name")
    private String branchName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_contact")
    private String contact;
    @Size(max = 45)
    @Column(name = "col_email")
    private String email;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;
    @Size(max = 45)
    @Column(name = "col_location")
    private String location;
    @Size(max = 45)
    @Column(name = "col_region")
    private String region;
    @OneToMany(mappedBy = "labBranch")
    private Collection<LabSession> labSessionCollection;
    @OneToMany(mappedBy = "labBranch")
    private Collection<LabStaff> labStaffCollection;
    @ManyToOne
    @JoinColumn(name = "col_lab_account")
    private LabAccount labAccount;

    @ManyToMany
    @JoinTable(name = "tbl_lab_branch_patients",
            joinColumns = {
                @JoinColumn(name = "code_lab_branch_code")},
            inverseJoinColumns = {
                @JoinColumn(name = "col_patient_id")})
    private List<Patient> patients = new ArrayList<Patient>();

    public LabBranch() {
    }

    public LabBranch(String branchId) {
        this.branchId = branchId;
    }

    public LabBranch(String branchId, String branchCode, String branchName, String contact) {
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.contact = contact;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getLabcode() {
        return labcode;
    }

    public void setLabcode(String labcode) {
        this.labcode = labcode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LabAccount getLabAccount() {
        return labAccount;
    }

    public void setLabAccount(LabAccount labAccount) {
        this.labAccount = labAccount;
    }

    public Collection<LabSession> getLabSessionCollection() {
        return labSessionCollection;
    }

    public void setLabSessionCollection(Collection<LabSession> labSessionCollection) {
        this.labSessionCollection = labSessionCollection;
    }

    public Collection<LabStaff> getLabStaffCollection() {
        return labStaffCollection;
    }

    public void setLabStaffCollection(Collection<LabStaff> labStaffCollection) {
        this.labStaffCollection = labStaffCollection;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (branchId != null ? branchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabBranch)) {
            return false;
        }
        LabBranch other = (LabBranch) object;
        if ((this.branchId == null && other.branchId != null) || (this.branchId != null && !this.branchId.equals(other.branchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabBranch[ branchId=" + branchId + " ]";
    }

}
