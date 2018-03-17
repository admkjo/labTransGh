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
    private String labBranchId;
    @Size(max = 45)
    @Column(name = "code_lab_branch_code")
    private String labBranchCode;
    @Size(max = 45)
    @Column(name = "col_labcode")
    private String labcode;
    @Size(max = 45)
    @Column(name = "col_branch_code")
    private String branchCode;
    @Size(max = 45)
    @Column(name = "col_patient_id")
    private String patientId;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted_no")
    private String deletedNo;

    public LabBranchPatients() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labBranchId != null ? labBranchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabBranchPatients)) {
            return false;
        }
        LabBranchPatients other = (LabBranchPatients) object;
        if ((this.labBranchId == null && other.labBranchId != null) || (this.labBranchId != null && !this.labBranchId.equals(other.labBranchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabBranchPatients[ labBranchId=" + labBranchId + " ]";
    }
    
}
