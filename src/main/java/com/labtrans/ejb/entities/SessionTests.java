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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_session_test_id")
    private String sessionTestId;
    @Size(max = 255)
    @Column(name = "col_lab_session")
    private String labSession;
    @Size(max = 255)
    @Column(name = "test_id")
    private String testId;

    public SessionTests() {
    }

    public SessionTests(String sessionTestId) {
        this.sessionTestId = sessionTestId;
    }

    public String getSessionTestId() {
        return sessionTestId;
    }

    public void setSessionTestId(String sessionTestId) {
        this.sessionTestId = sessionTestId;
    }

    public String getLabSession() {
        return labSession;
    }

    public void setLabSession(String labSession) {
        this.labSession = labSession;
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
        hash += (sessionTestId != null ? sessionTestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionTests)) {
            return false;
        }
        SessionTests other = (SessionTests) object;
        if ((this.sessionTestId == null && other.sessionTestId != null) || (this.sessionTestId != null && !this.sessionTestId.equals(other.sessionTestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.SessionTests[ sessionTestId=" + sessionTestId + " ]";
    }
    
}
