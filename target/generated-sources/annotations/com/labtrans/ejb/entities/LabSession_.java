package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-17T03:07:21")
@StaticMetamodel(LabSession.class)
public class LabSession_ { 

    public static volatile SingularAttribute<LabSession, String> branchCode;
    public static volatile SingularAttribute<LabSession, String> doctor;
    public static volatile SingularAttribute<LabSession, Date> dateCreated;
    public static volatile SingularAttribute<LabSession, String> deleted;
    public static volatile SingularAttribute<LabSession, String> patientId;
    public static volatile SingularAttribute<LabSession, String> sendToPatientStatus;
    public static volatile SingularAttribute<LabSession, String> labSessionId;
    public static volatile SingularAttribute<LabSession, String> sendToDocStatus;
    public static volatile SingularAttribute<LabSession, String> technician;
    public static volatile SingularAttribute<LabSession, String> completionStatus;
    public static volatile SingularAttribute<LabSession, String> notifyPatient;
    public static volatile SingularAttribute<LabSession, String> paymentStatus;

}