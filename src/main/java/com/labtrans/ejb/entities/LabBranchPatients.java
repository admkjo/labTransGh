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
@Table(name = "tbl_lab_branch_patients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabBranchPatients.findAll", query = "SELECT l FROM LabBranchPatients l")})
public class LabBranchPatients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "col_lab_branch_id")
    private String colLabBranchId;
    @Size(max = 45)
    @Column(name = "code_lab_branch_code")
    private String codeLabBranchCode;
    @Size(max = 45)
    @Column(name = "col_labcode")
    private String colLabcode;
    @Size(max = 45)
    @Column(name = "col_branch_code")
    private String colBranchCode;
    @Size(max = 45)
    @Column(name = "col_patient_id")
    private String colPatientId;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date colDateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted_no")
    private String colDeletedNo;

    public LabBranchPatients() {
    }

    public LabBranchPatients(String colLabBranchId) {
        this.colLabBranchId = colLabBranchId;
    }

    public String getColLabBranchId() {
        return colLabBranchId;
    }

    public void setColLabBranchId(String colLabBranchId) {
        this.colLabBranchId = colLabBranchId;
    }

    public String getCodeLabBranchCode() {
        return codeLabBranchCode;
    }

    public void setCodeLabBranchCode(String codeLabBranchCode) {
        this.codeLabBranchCode = codeLabBranchCode;
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

    public String getColPatientId() {
        return colPatientId;
    }

    public void setColPatientId(String colPatientId) {
        this.colPatientId = colPatientId;
    }

    public Date getColDateCreated() {
        return colDateCreated;
    }

    public void setColDateCreated(Date colDateCreated) {
        this.colDateCreated = colDateCreated;
    }

    public String getColDeletedNo() {
        return colDeletedNo;
    }

    public void setColDeletedNo(String colDeletedNo) {
        this.colDeletedNo = colDeletedNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colLabBranchId != null ? colLabBranchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabBranchPatients)) {
            return false;
        }
        LabBranchPatients other = (LabBranchPatients) object;
        if ((this.colLabBranchId == null && other.colLabBranchId != null) || (this.colLabBranchId != null && !this.colLabBranchId.equals(other.colLabBranchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabBranchPatients[ colLabBranchId=" + colLabBranchId + " ]";
    }
    
}
