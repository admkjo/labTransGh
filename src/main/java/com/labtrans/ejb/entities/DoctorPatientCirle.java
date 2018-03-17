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
@Table(name = "tbl_doctor_patient_cirle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorPatientCirle.findAll", query = "SELECT d FROM DoctorPatientCirle d")})
public class DoctorPatientCirle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_circle_id")
    private String colCircleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_circle_code")
    private String colCircleCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_doc_id")
    private String colDocId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_patient_id")
    private String colPatientId;
    @Size(max = 45)
    @Column(name = "col_lab_session")
    private String colLabSession;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date colDateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String colDeleted;

    public DoctorPatientCirle() {
    }

    public DoctorPatientCirle(String colCircleId) {
        this.colCircleId = colCircleId;
    }

    public DoctorPatientCirle(String colCircleId, String colCircleCode, String colDocId, String colPatientId) {
        this.colCircleId = colCircleId;
        this.colCircleCode = colCircleCode;
        this.colDocId = colDocId;
        this.colPatientId = colPatientId;
    }

    public String getColCircleId() {
        return colCircleId;
    }

    public void setColCircleId(String colCircleId) {
        this.colCircleId = colCircleId;
    }

    public String getColCircleCode() {
        return colCircleCode;
    }

    public void setColCircleCode(String colCircleCode) {
        this.colCircleCode = colCircleCode;
    }

    public String getColDocId() {
        return colDocId;
    }

    public void setColDocId(String colDocId) {
        this.colDocId = colDocId;
    }

    public String getColPatientId() {
        return colPatientId;
    }

    public void setColPatientId(String colPatientId) {
        this.colPatientId = colPatientId;
    }

    public String getColLabSession() {
        return colLabSession;
    }

    public void setColLabSession(String colLabSession) {
        this.colLabSession = colLabSession;
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
        hash += (colCircleId != null ? colCircleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorPatientCirle)) {
            return false;
        }
        DoctorPatientCirle other = (DoctorPatientCirle) object;
        if ((this.colCircleId == null && other.colCircleId != null) || (this.colCircleId != null && !this.colCircleId.equals(other.colCircleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.DoctorPatientCirle[ colCircleId=" + colCircleId + " ]";
    }
    
}
