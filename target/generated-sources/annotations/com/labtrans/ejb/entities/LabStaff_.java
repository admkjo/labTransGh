package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-17T17:20:52")
@StaticMetamodel(LabStaff.class)
public class LabStaff_ { 

    public static volatile SingularAttribute<LabStaff, String> labcode;
    public static volatile SingularAttribute<LabStaff, String> branchCode;
    public static volatile SingularAttribute<LabStaff, String> password;
    public static volatile SingularAttribute<LabStaff, Date> dateCreated;
    public static volatile SingularAttribute<LabStaff, String> deleted;
    public static volatile SingularAttribute<LabStaff, String> phone;
    public static volatile SingularAttribute<LabStaff, String> fullname;
    public static volatile SingularAttribute<LabStaff, Integer> staffId;
    public static volatile SingularAttribute<LabStaff, String> userCode;
    public static volatile SingularAttribute<LabStaff, String> email;
    public static volatile SingularAttribute<LabStaff, String> username;

}