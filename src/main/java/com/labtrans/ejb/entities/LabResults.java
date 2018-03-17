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
import javax.persistence.Id;
import javax.persistence.Lob;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_lab_results_id")
    private String colLabResultsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_lab_session")
    private String colLabSession;
    @Size(max = 255)
    @Column(name = "col_test_param_name")
    private String colTestParamName;
    @Lob
    @Size(max = 65535)
    @Column(name = "col_test_param_value")
    private String colTestParamValue;

    public LabResults() {
    }

    public LabResults(String colLabResultsId) {
        this.colLabResultsId = colLabResultsId;
    }

    public LabResults(String colLabResultsId, String colLabSession) {
        this.colLabResultsId = colLabResultsId;
        this.colLabSession = colLabSession;
    }

    public String getColLabResultsId() {
        return colLabResultsId;
    }

    public void setColLabResultsId(String colLabResultsId) {
        this.colLabResultsId = colLabResultsId;
    }

    public String getColLabSession() {
        return colLabSession;
    }

    public void setColLabSession(String colLabSession) {
        this.colLabSession = colLabSession;
    }

    public String getColTestParamName() {
        return colTestParamName;
    }

    public void setColTestParamName(String colTestParamName) {
        this.colTestParamName = colTestParamName;
    }

    public String getColTestParamValue() {
        return colTestParamValue;
    }

    public void setColTestParamValue(String colTestParamValue) {
        this.colTestParamValue = colTestParamValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colLabResultsId != null ? colLabResultsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabResults)) {
            return false;
        }
        LabResults other = (LabResults) object;
        if ((this.colLabResultsId == null && other.colLabResultsId != null) || (this.colLabResultsId != null && !this.colLabResultsId.equals(other.colLabResultsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabResults[ colLabResultsId=" + colLabResultsId + " ]";
    }
    
}
