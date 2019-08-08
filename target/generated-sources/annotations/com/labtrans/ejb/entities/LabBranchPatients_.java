package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T22:40:21")
@StaticMetamodel(LabBranchPatients.class)
public class LabBranchPatients_ { 

    public static volatile SingularAttribute<LabBranchPatients, String> labcode;
    public static volatile SingularAttribute<LabBranchPatients, String> branchCode;
    public static volatile SingularAttribute<LabBranchPatients, String> labBranchId;
    public static volatile SingularAttribute<LabBranchPatients, Date> dateCreated;
    public static volatile SingularAttribute<LabBranchPatients, String> deletedNo;
    public static volatile SingularAttribute<LabBranchPatients, String> patientId;
    public static volatile SingularAttribute<LabBranchPatients, String> labBranchCode;

}