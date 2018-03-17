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
@Table(name = "tbl_doctor_patient_cirle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorPatientCirle.findAll", query = "SELECT d FROM DoctorPatientCirle d")})
public class DoctorPatientCirle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_circle_id")
    private String circleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_circle_code")
    private String circleCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_doc_id")
    private String docId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col_patient_id")
    private String patientId;
    @Size(max = 45)
    @Column(name = "col_lab_session")
    private String labSession;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;

    public DoctorPatientCirle() {
    }

    public DoctorPatientCirle(String circleId) {
        this.circleId = circleId;
    }

    public DoctorPatientCirle(String circleId, String circleCode, String docId, String patientId) {
        this.circleId = circleId;
        this.circleCode = circleCode;
        this.docId = docId;
        this.patientId = patientId;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getCircleCode() {
        return circleCode;
    }

    public void setCircleCode(String circleCode) {
        this.circleCode = circleCode;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getLabSession() {
        return labSession;
    }

    public void setLabSession(String labSession) {
        this.labSession = labSession;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (circleId != null ? circleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorPatientCirle)) {
            return false;
        }
        DoctorPatientCirle other = (DoctorPatientCirle) object;
        if ((this.circleId == null && other.circleId != null) || (this.circleId != null && !this.circleId.equals(other.circleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.DoctorPatientCirle[ colCircleId=" + circleId + " ]";
    }

}
