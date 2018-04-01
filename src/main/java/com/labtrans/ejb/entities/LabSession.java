/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;
    @Size(max = 45)
    @Column(name = "col_completion_status")
    private String completionStatus;
    @Size(max = 45)
    @JoinColumn(name = "col_branch_code", referencedColumnName = "col_branch_code")
    @ManyToOne
    private LabBranch labBranch;
    @Size(max = 45)
    @OneToOne
    @JoinColumn(name = "col_patient_id")
    private Patient patientId;
    @Size(max = 45)
    @JoinColumn(name = "col_technician")
    @ManyToOne
    private LabStaff labStaff;
    @OneToMany(mappedBy = "LabSession")
    private Collection<SessionTests> sessionTestsCollection;
    @OneToMany(mappedBy = "labSession")
    private Collection<LabResults> labResultsCollection;

    public LabSession() {
    }

    public String getLabSessionId() {
        return labSessionId;
    }

    public void setLabSessionId(String labSessionId) {
        this.labSessionId = labSessionId;
    }

    public LabBranch getLabBranch() {
        return labBranch;
    }

    public void setLabBranch(LabBranch labBranch) {
        this.labBranch = labBranch;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public LabStaff getLabStaff() {
        return labStaff;
    }

    public void setLabStaff(LabStaff labStaff) {
        this.labStaff = labStaff;
    }

    public String getNotifyPatient() {
        return notifyPatient;
    }

    public void setNotifyPatient(String notifyPatient) {
        this.notifyPatient = notifyPatient;
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

    @XmlTransient
    public Collection<SessionTests> getSessionTestsCollection() {
        return sessionTestsCollection;
    }

    public void setSessionTestsCollection(Collection<SessionTests> sessionTestsCollection) {
        this.sessionTestsCollection = sessionTestsCollection;
    }

    @XmlTransient
    public Collection<LabResults> getLabResultsCollection() {
        return labResultsCollection;
    }

    public void setlabResultsCollectionCollection(Collection<LabResults> labResultsCollection) {
        this.labResultsCollection = labResultsCollection;
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
