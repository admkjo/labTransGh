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
@Table(name = "tbl_lab_branch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabBranch.findAll", query = "SELECT l FROM LabBranch l")})
public class LabBranch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_branch_id")
    private String colBranchId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_branch_code")
    private String colBranchCode;
    @Size(max = 45)
    @Column(name = "col_labcode")
    private String colLabcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_branch_name")
    private String colBranchName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_contact")
    private String colContact;
    @Size(max = 45)
    @Column(name = "col_email")
    private String colEmail;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date colDateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String colDeleted;

    public LabBranch() {
    }

    public LabBranch(String colBranchId) {
        this.colBranchId = colBranchId;
    }

    public LabBranch(String colBranchId, String colBranchCode, String colBranchName, String colContact) {
        this.colBranchId = colBranchId;
        this.colBranchCode = colBranchCode;
        this.colBranchName = colBranchName;
        this.colContact = colContact;
    }

    public String getColBranchId() {
        return colBranchId;
    }

    public void setColBranchId(String colBranchId) {
        this.colBranchId = colBranchId;
    }

    public String getColBranchCode() {
        return colBranchCode;
    }

    public void setColBranchCode(String colBranchCode) {
        this.colBranchCode = colBranchCode;
    }

    public String getColLabcode() {
        return colLabcode;
    }

    public void setColLabcode(String colLabcode) {
        this.colLabcode = colLabcode;
    }

    public String getColBranchName() {
        return colBranchName;
    }

    public void setColBranchName(String colBranchName) {
        this.colBranchName = colBranchName;
    }

    public String getColContact() {
        return colContact;
    }

    public void setColContact(String colContact) {
        this.colContact = colContact;
    }

    public String getColEmail() {
        return colEmail;
    }

    public void setColEmail(String colEmail) {
        this.colEmail = colEmail;
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
        hash += (colBranchId != null ? colBranchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabBranch)) {
            return false;
        }
        LabBranch other = (LabBranch) object;
        if ((this.colBranchId == null && other.colBranchId != null) || (this.colBranchId != null && !this.colBranchId.equals(other.colBranchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabBranch[ colBranchId=" + colBranchId + " ]";
    }
    
}
