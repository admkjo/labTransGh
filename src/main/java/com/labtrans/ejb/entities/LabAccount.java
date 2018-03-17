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
@Table(name = "tbl_lab_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabAccount.findAll", query = "SELECT l FROM LabAccount l")})
public class LabAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "col_id")
    private String colId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "col_labcode")
    private String colLabcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "col_name_of_orginazation")
    private String colNameOfOrginazation;
    @Size(max = 45)
    @Column(name = "col_location")
    private String colLocation;
    @Size(max = 45)
    @Column(name = "col_region")
    private String colRegion;
    @Size(max = 45)
    @Column(name = "col_contact")
    private String colContact;
    @Size(max = 45)
    @Column(name = "col_username")
    private String colUsername;
    @Size(max = 45)
    @Column(name = "col_password")
    private String colPassword;
    @Size(max = 45)
    @Column(name = "col_date_created")
    private String colDateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String colDeleted;

    public LabAccount() {
    }

    public LabAccount(String colId) {
        this.colId = colId;
    }

    public LabAccount(String colId, String colLabcode, String colNameOfOrginazation) {
        this.colId = colId;
        this.colLabcode = colLabcode;
        this.colNameOfOrginazation = colNameOfOrginazation;
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getColLabcode() {
        return colLabcode;
    }

    public void setColLabcode(String colLabcode) {
        this.colLabcode = colLabcode;
    }

    public String getColNameOfOrginazation() {
        return colNameOfOrginazation;
    }

    public void setColNameOfOrginazation(String colNameOfOrginazation) {
        this.colNameOfOrginazation = colNameOfOrginazation;
    }

    public String getColLocation() {
        return colLocation;
    }

    public void setColLocation(String colLocation) {
        this.colLocation = colLocation;
    }

    public String getColRegion() {
        return colRegion;
    }

    public void setColRegion(String colRegion) {
        this.colRegion = colRegion;
    }

    public String getColContact() {
        return colContact;
    }

    public void setColContact(String colContact) {
        this.colContact = colContact;
    }

    public String getColUsername() {
        return colUsername;
    }

    public void setColUsername(String colUsername) {
        this.colUsername = colUsername;
    }

    public String getColPassword() {
        return colPassword;
    }

    public void setColPassword(String colPassword) {
        this.colPassword = colPassword;
    }

    public String getColDateCreated() {
        return colDateCreated;
    }

    public void setColDateCreated(String colDateCreated) {
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
        hash += (colId != null ? colId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabAccount)) {
            return false;
        }
        LabAccount other = (LabAccount) object;
        if ((this.colId == null && other.colId != null) || (this.colId != null && !this.colId.equals(other.colId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabAccount[ colId=" + colId + " ]";
    }
    
}
