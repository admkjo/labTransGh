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
@Table(name = "tbl_session_tests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SessionTests.findAll", query = "SELECT s FROM SessionTests s")})
public class SessionTests implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_session_test_id")
    private String colSessionTestId;
    @Size(max = 255)
    @Column(name = "col_lab_session")
    private String colLabSession;
    @Size(max = 255)
    @Column(name = "test_id")
    private String testId;

    public SessionTests() {
    }

    public SessionTests(String colSessionTestId) {
        this.colSessionTestId = colSessionTestId;
    }

    public String getColSessionTestId() {
        return colSessionTestId;
    }

    public void setColSessionTestId(String colSessionTestId) {
        this.colSessionTestId = colSessionTestId;
    }

    public String getColLabSession() {
        return colLabSession;
    }

    public void setColLabSession(String colLabSession) {
        this.colLabSession = colLabSession;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colSessionTestId != null ? colSessionTestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionTests)) {
            return false;
        }
        SessionTests other = (SessionTests) object;
        if ((this.colSessionTestId == null && other.colSessionTestId != null) || (this.colSessionTestId != null && !this.colSessionTestId.equals(other.colSessionTestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.SessionTests[ colSessionTestId=" + colSessionTestId + " ]";
    }
    
}
