package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T21:33:56")
@StaticMetamodel(LabSession.class)
public class LabSession_ { 

    public static volatile SingularAttribute<LabSession, String> colBranchCode;
    public static volatile SingularAttribute<LabSession, String> colDoctor;
    public static volatile SingularAttribute<LabSession, String> colNotifyPatient;
    public static volatile SingularAttribute<LabSession, Date> colDateCreated;
    public static volatile SingularAttribute<LabSession, String> colTechnician;
    public static volatile SingularAttribute<LabSession, String> colLabSessionId;
    public static volatile SingularAttribute<LabSession, String> colDeleted;
    public static volatile SingularAttribute<LabSession, String> colPatientId;
    public static volatile SingularAttribute<LabSession, String> colStatus;

}