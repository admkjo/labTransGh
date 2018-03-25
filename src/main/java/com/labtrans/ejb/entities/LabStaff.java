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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adm_Kjo
 */
@Entity
@Table(name = "tbl_lab_staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabStaff.findAll", query = "SELECT l FROM LabStaff l")})
public class LabStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_staff_id")
    private Integer staffId;
    @Size(max = 45)
    @Column(name = "col_user_code")
    private String userCode;
    @Size(max = 45)
    @Column(name = "col_labcode")
    private String labcode;
    @Size(max = 45)
    @Column(name = "col_fullname")
    private String fullname;
    @Size(max = 45)
    @Column(name = "col_email")
    private String email;
    @Size(max = 45)
    @Column(name = "col_username")
    private String username;
    @Size(max = 45)
    @Column(name = "col_password")
    private String password;
    @Size(max = 45)
    @Column(name = "col_phone")
    private String phone;
    @Column(name = "col_date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 45)
    @Column(name = "col_deleted")
    private String deleted;

    @OneToOne(mappedBy = "labStaff")
    private LabSession labSession;

    @ManyToOne
    @JoinColumn(name = "col_branch_code")
    private LabBranch labBranch;

    public LabStaff() {
    }

    public LabStaff(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLabcode() {
        return labcode;
    }

    public void setLabcode(String labcode) {
        this.labcode = labcode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public LabSession getLabSession() {
        return labSession;
    }

    public void setLabSession(LabSession labSession) {
        this.labSession = labSession;
    }

    public LabBranch getLabBranch() {
        return labBranch;
    }

    public void setLabBranch(LabBranch labBranch) {
        this.labBranch = labBranch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffId != null ? staffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabStaff)) {
            return false;
        }
        LabStaff other = (LabStaff) object;
        if ((this.staffId == null && other.staffId != null) || (this.staffId != null && !this.staffId.equals(other.staffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.labtrans.ejb.entities.LabStaff[ staffId=" + staffId + " ]";
    }

}
