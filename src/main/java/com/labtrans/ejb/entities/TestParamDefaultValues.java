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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adm_Kjo
 */
@Entity
@Table(name = "tbl_test_param_default_values")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestParamDefaultValues.findAll", query = "SELECT t FROM TestParamDefaultValues t")})
public class TestParamDefaultValues implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_test_param_default_values_id")
    private Integer testParamDefaultValuesId;
    @ManyToOne
    @JoinColumn(name = "col_test_param")
    private TestParams testParam;
    @Size(max = 255)
    @Column(name = "col_value")
    private String value;
    @Size(max = 10)
    @Column(name = "col_deleted")
    private String deleted;

    public TestParamDefaultValues() {
    }

    public TestParamDefaultValues(Integer testParamDefaultValuesId) {
        this.testParamDefaultValuesId = testParamDefaultValuesId;
    }

    public TestParamDefaultValues(Integer testParamDefaultValuesId, int testId) {
        this.testParamDefaultValuesId = testParamDefaultValuesId;
    }

    public Integer getTestParamDefaultValuesId() {
        return testParamDefaultValuesId;
    }

    public void setTestParamDefaultValuesId(Integer testParamDefaultValuesId) {
        this.testParamDefaultValuesId = testParamDefaultValuesId;
    }

    public TestParams getTestParam() {
        return testParam;
    }

    public void setTestParam(TestParams testParam) {
        this.testParam = testParam;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        hash += (testParamDefaultValuesId != null ? testParamDefaultValuesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestParamDefaultValues)) {
            return false;
        }
        TestParamDefaultValues other = (TestParamDefaultValues) object;
        if ((this.testParamDefaultValuesId == null && other.testParamDefaultValuesId != null) || (this.testParamDefaultValuesId != null && !this.testParamDefaultValuesId.equals(other.testParamDefaultValuesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.TestParamDefaultValues[ colTestParamDefaultValuesId=" + testParamDefaultValuesId + " ]";
    }

}
