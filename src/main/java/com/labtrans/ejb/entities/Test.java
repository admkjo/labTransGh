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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "tbl_test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_test_id")
    private String colTestId;
    @Size(max = 255)
    @Column(name = "col_test_code")
    private String colTestCode;
    @Size(max = 255)
    @Column(name = "col_test_name")
    private String colTestName;
    @Lob
    @Size(max = 65535)
    @Column(name = "col_test_description")
    private String colTestDescription;
    @Size(max = 255)
    @Column(name = "col_created_by")
    private String colCreatedBy;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date colDateCreated;
    @Size(max = 10)
    @Column(name = "col_deleted")
    private String colDeleted;

    public Test() {
    }

    public Test(String colTestId) {
        this.colTestId = colTestId;
    }

    public String getColTestId() {
        return colTestId;
    }

    public void setColTestId(String colTestId) {
        this.colTestId = colTestId;
    }

    public String getColTestCode() {
        return colTestCode;
    }

    public void setColTestCode(String colTestCode) {
        this.colTestCode = colTestCode;
    }

    public String getColTestName() {
        return colTestName;
    }

    public void setColTestName(String colTestName) {
        this.colTestName = colTestName;
    }

    public String getColTestDescription() {
        return colTestDescription;
    }

    public void setColTestDescription(String colTestDescription) {
        this.colTestDescription = colTestDescription;
    }

    public String getColCreatedBy() {
        return colCreatedBy;
    }

    public void setColCreatedBy(String colCreatedBy) {
        this.colCreatedBy = colCreatedBy;
    }

    public Date getColDateCreated() {
        return colDateCreated;
    }

    public void setColDateCreated(Date colDateCreated) {
        this.colDateCreated = colDateCreated;
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
        hash += (colTestId != null ? colTestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.colTestId == null && other.colTestId != null) || (this.colTestId != null && !this.colTestId.equals(other.colTestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.Test[ colTestId=" + colTestId + " ]";
    }
    
}
