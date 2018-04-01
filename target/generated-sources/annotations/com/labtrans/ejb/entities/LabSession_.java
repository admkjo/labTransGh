package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.LabBranch;
import com.labtrans.ejb.entities.LabResults;
import com.labtrans.ejb.entities.LabStaff;
import com.labtrans.ejb.entities.Patient;
import com.labtrans.ejb.entities.SessionTests;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-01T00:03:26")
@StaticMetamodel(LabSession.class)
public class LabSession_ { 

    public static volatile SingularAttribute<LabSession, Patient> patientId;
    public static volatile SingularAttribute<LabSession, String> labSessionId;
    public static volatile SingularAttribute<LabSession, LabBranch> labBranch;
    public static volatile SingularAttribute<LabSession, LabStaff> labStaff;
    public static volatile SingularAttribute<LabSession, String> notifyPatient;
    public static volatile SingularAttribute<LabSession, Date> dateCreated;
    public static volatile SingularAttribute<LabSession, String> deleted;
    public static volatile SingularAttribute<LabSession, String> sendToPatientStatus;
    public static volatile SingularAttribute<LabSession, String> sendToDocStatus;
    public static volatile CollectionAttribute<LabSession, SessionTests> sessionTestsCollection;
    public static volatile SingularAttribute<LabSession, String> completionStatus;
    public static volatile CollectionAttribute<LabSession, LabResults> labResultsCollection;
    public static volatile SingularAttribute<LabSession, String> paymentStatus;

}