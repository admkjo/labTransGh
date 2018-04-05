package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.LabAccount;
import com.labtrans.ejb.entities.LabSession;
import com.labtrans.ejb.entities.LabStaff;
import com.labtrans.ejb.entities.Patient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T22:10:13")
@StaticMetamodel(LabBranch.class)
public class LabBranch_ { 

    public static volatile SingularAttribute<LabBranch, String> labcode;
    public static volatile SingularAttribute<LabBranch, String> branchId;
    public static volatile CollectionAttribute<LabBranch, LabStaff> labStaffCollection;
    public static volatile ListAttribute<LabBranch, Patient> patients;
    public static volatile SingularAttribute<LabBranch, String> branchName;
    public static volatile SingularAttribute<LabBranch, LabAccount> labAccount;
    public static volatile SingularAttribute<LabBranch, String> branchCode;
    public static volatile SingularAttribute<LabBranch, Date> dateCreated;
    public static volatile SingularAttribute<LabBranch, String> deleted;
    public static volatile SingularAttribute<LabBranch, String> contact;
    public static volatile CollectionAttribute<LabBranch, LabSession> labSessionCollection;
    public static volatile SingularAttribute<LabBranch, String> location;
    public static volatile SingularAttribute<LabBranch, String> region;
    public static volatile SingularAttribute<LabBranch, String> email;

}