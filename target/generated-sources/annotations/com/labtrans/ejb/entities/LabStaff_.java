package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T21:33:56")
@StaticMetamodel(LabStaff.class)
public class LabStaff_ { 

    public static volatile SingularAttribute<LabStaff, String> colUserCode;
    public static volatile SingularAttribute<LabStaff, String> colBranchCode;
    public static volatile SingularAttribute<LabStaff, Date> colDateCreated;
    public static volatile SingularAttribute<LabStaff, Integer> colStaffId;
    public static volatile SingularAttribute<LabStaff, String> colPhone;
    public static volatile SingularAttribute<LabStaff, String> colPassword;
    public static volatile SingularAttribute<LabStaff, String> colUsername;
    public static volatile SingularAttribute<LabStaff, String> colDeleted;
    public static volatile SingularAttribute<LabStaff, String> colFullname;
    public static volatile SingularAttribute<LabStaff, String> colEmail;
    public static volatile SingularAttribute<LabStaff, String> colLabcode;

}