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
@Table(name = "tbl_lab_session")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabSession.findAll", query = "SELECT l FROM LabSession l")})
public class LabSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_lab_session_id")
    private String labSessionId;
    @Size(max = 45)
    @Column(name = "col_branch_code")
    private String branchCode;
    @Size(max = 45)
    @Column(name = "col_patient_id")
    private String patientId;
    @Size(max = 45)
    @Column(name = "col_technician")
    private String technician;
    @Size(max = 10)
    @Column(name = "col_notify_patient")
    private String notifyPatient;
    @Size(max = 10)
    @Column(name = "col_send_to_patient_status")
    private String sendToPatientStatus;
    @Size(max = 10)
    @Column(name = "col_send_to_doc_status")
    private String sendToDocStatus;
    @Size(max = 10)
    @Column(name = "col_payment_status")
    private String paymentStatus;
    @Size(max = 255)
    @Column(name = "col_doctor")
    private String doctor;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;
    @Size(max = 45)
    @Column(name = "col_completion_status")
    private String completionStatus;

    public LabSession() {
    }

    public String getLabSessionId() {
        return labSessionId;
    }

    public void setLabSessionId(String labSessionId) {
        this.labSessionId = labSessionId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getNotifyPatient() {
        return notifyPatient;
    }

    public void setNotifyPatient(String notifyPatient) {
        this.notifyPatient = notifyPatient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
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

    public String getSendToPatientStatus() {
        return sendToPatientStatus;
    }

    public void setSendToPatientStatus(String sendToPatientStatus) {
        this.sendToPatientStatus = sendToPatientStatus;
    }

    public String getSendToDocStatus() {
        return sendToDocStatus;
    }

    public void setSendToDocStatus(String sendToDocStatus) {
        this.sendToDocStatus = sendToDocStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labSessionId != null ? labSessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabSession)) {
            return false;
        }
        LabSession other = (LabSession) object;
        if ((this.labSessionId == null && other.labSessionId != null) || (this.labSessionId != null && !this.labSessionId.equals(other.labSessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabSession[ labSessionId=" + labSessionId + " ]";
    }

}
