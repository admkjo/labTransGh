package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.LabBranch;
import com.labtrans.ejb.entities.LabSession;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T22:40:21")
@StaticMetamodel(LabStaff.class)
public class LabStaff_ { 

    public static volatile SingularAttribute<LabStaff, String> verifyCode;
    public static volatile SingularAttribute<LabStaff, LabBranch> labBranch;
    public static volatile SingularAttribute<LabStaff, String> userCode;
    public static volatile SingularAttribute<LabStaff, String> password;
    public static volatile SingularAttribute<LabStaff, Date> dateCreated;
    public static volatile SingularAttribute<LabStaff, String> deleted;
    public static volatile SingularAttribute<LabStaff, String> phone;
    public static volatile CollectionAttribute<LabStaff, LabSession> labSessionCollection;
    public static volatile SingularAttribute<LabStaff, String> fullname;
    public static volatile SingularAttribute<LabStaff, Integer> staffId;
    public static volatile SingularAttribute<LabStaff, String> email;
    public static volatile SingularAttribute<LabStaff, String> status;
    public static volatile SingularAttribute<LabStaff, String> username;

}