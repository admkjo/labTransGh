/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_test_param_id")
    private String testParamId;
    @Size(max = 255)
    @ManyToOne
    @JoinColumn(name = "col_test_id")
    private Test testId;
    @Size(max = 255)
    @Column(name = "col_parameter")
    private String parameter;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;
    @OneToMany(mappedBy = "testParam")
    private Collection<TestParamDefaultValues> TestcoluParamDefaultValuesCollection;

    public TestParams() {
    }

    public TestParams(String testParamId) {
        this.testParamId = testParamId;
    }

    public String getTestParamId() {
        return testParamId;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Collection<TestParamDefaultValues> getTestcoluParamDefaultValuesCollection() {
        return TestcoluParamDefaultValuesCollection;
    }

    public void setTestcoluParamDefaultValuesCollection(Collection<TestParamDefaultValues> TestcoluParamDefaultValuesCollection) {
        this.TestcoluParamDefaultValuesCollection = TestcoluParamDefaultValuesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testParamId != null ? testParamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestParams)) {
            return false;
        }
        TestParams other = (TestParams) object;
        if ((this.testParamId == null && other.testParamId != null) || (this.testParamId != null && !this.testParamId.equals(other.testParamId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.TestParams[ testParamId=" + testParamId + " ]";
    }

}
