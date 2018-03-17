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
@Table(name = "tbl_test_params")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestParams.findAll", query = "SELECT t FROM TestParams t")})
public class TestParams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_test_param_id")
    private String colTestParamId;
    @Size(max = 255)
    @Column(name = "col_test_id")
    private String colTestId;
    @Size(max = 255)
    @Column(name = "col_parameter")
    private String colParameter;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String colDeleted;

    public TestParams() {
    }

    public TestParams(String colTestParamId) {
        this.colTestParamId = colTestParamId;
    }

    public String getColTestParamId() {
        return colTestParamId;
    }

    public void setColTestParamId(String colTestParamId) {
        this.colTestParamId = colTestParamId;
    }

    public String getColTestId() {
        return colTestId;
    }

    public void setColTestId(String colTestId) {
        this.colTestId = colTestId;
    }

    public String getColParameter() {
        return colParameter;
    }

    public void setColParameter(String colParameter) {
        this.colParameter = colParameter;
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
        hash += (colTestParamId != null ? colTestParamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestParams)) {
            return false;
        }
        TestParams other = (TestParams) object;
        if ((this.colTestParamId == null && other.colTestParamId != null) || (this.colTestParamId != null && !this.colTestParamId.equals(other.colTestParamId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.TestParams[ colTestParamId=" + colTestParamId + " ]";
    }
    
}
