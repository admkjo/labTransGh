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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adm_Kjo
 */
@Entity
@Table(name = "tbl_lab_staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabStaff.findAll", query = "SELECT l FROM LabStaff l")})
public class LabStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_staff_id")
    private Integer colStaffId;
    @Size(max = 45)
    @Column(name = "col_user_code")
    private String colUserCode;
    @Size(max = 45)
    @Column(name = "col_labcode")
    private String colLabcode;
    @Size(max = 45)
    @Column(name = "col_branch_code")
    private String colBranchCode;
    @Size(max = 45)
    @Column(name = "col_fullname")
    private String colFullname;
    @Size(max = 45)
    @Column(name = "col_email")
    private String colEmail;
    @Size(max = 45)
    @Column(name = "col_username")
    private String colUsername;
    @Size(max = 45)
    @Column(name = "col_password")
    private String colPassword;
    @Size(max = 45)
    @Column(name = "col_phone")
    private String colPhone;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date colDateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String colDeleted;

    public LabStaff() {
    }

    public LabStaff(Integer colStaffId) {
        this.colStaffId = colStaffId;
    }

    public Integer getColStaffId() {
        return colStaffId;
    }

    public void setColStaffId(Integer colStaffId) {
        this.colStaffId = colStaffId;
    }

    public String getColUserCode() {
        return colUserCode;
    }

    public void setColUserCode(String colUserCode) {
        this.colUserCode = colUserCode;
    }

    public String getColLabcode() {
        return colLabcode;
    }

    public void setColLabcode(String colLabcode) {
        this.colLabcode = colLabcode;
    }

    public String getColBranchCode() {
        return colBranchCode;
    }

    public void setColBranchCode(String colBranchCode) {
        this.colBranchCode = colBranchCode;
    }

    public String getColFullname() {
        return colFullname;
    }

    public void setColFullname(String colFullname) {
        this.colFullname = colFullname;
    }

    public String getColEmail() {
        return colEmail;
    }

    public void setColEmail(String colEmail) {
        this.colEmail = colEmail;
    }

    public String getColUsername() {
        return colUsername;
    }

    public void setColUsername(String colUsername) {
        this.colUsername = colUsername;
    }

    public String getColPassword() {
        return colPassword;
    }

    public void setColPassword(String colPassword) {
        this.colPassword = colPassword;
    }

    public String getColPhone() {
        return colPhone;
    }

    public void setColPhone(String colPhone) {
        this.colPhone = colPhone;
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
        hash += (colStaffId != null ? colStaffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabStaff)) {
            return false;
        }
        LabStaff other = (LabStaff) object;
        if ((this.colStaffId == null && other.colStaffId != null) || (this.colStaffId != null && !this.colStaffId.equals(other.colStaffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabStaff[ colStaffId=" + colStaffId + " ]";
    }
    
}
