/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adm_Kjo
 */
@Entity
@Table(name = "tbl_lab_results")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabResults.findAll", query = "SELECT l FROM LabResults l")})
public class LabResults implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_lab_results_id")
    private String labResultsId;
    @Size(min = 1, max = 255)
    @ManyToOne
    @JoinColumn(name = "col_lab_session")
    private LabSession labSession;
    @Size(max = 255)
    @Column(name = "col_test_param_name")
    private String testParamName;
    @Lob
    @Size(max = 65535)
    @Column(name = "col_test_param_value")
    private String testParamValue;

    public LabResults() {
    }

    public LabResults(String labResultsId) {
        this.labResultsId = labResultsId;
    }

    public LabResults(String labResultsId, LabSession labSession) {
        this.labResultsId = labResultsId;
        this.labSession = labSession;
    }

    public String getLabResultsId() {
        return labResultsId;
    }

    public void setLabResultsId(String labResultsId) {
        this.labResultsId = labResultsId;
    }

    public LabSession getLabSession() {
        return labSession;
    }

    public void setLabSession(LabSession labSession) {
        this.labSession = labSession;
    }

    public String getTestParamName() {
        return testParamName;
    }

    public void setTestParamName(String testParamName) {
        this.testParamName = testParamName;
    }

    public String getTestParamValue() {
        return testParamValue;
    }

    public void setTestParamValue(String testParamValue) {
        this.testParamValue = testParamValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labResultsId != null ? labResultsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabResults)) {
            return false;
        }
        LabResults other = (LabResults) object;
        if ((this.labResultsId == null && other.labResultsId != null) || (this.labResultsId != null && !this.labResultsId.equals(other.labResultsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabResults[ labResultsId=" + labResultsId + " ]";
    }

}
