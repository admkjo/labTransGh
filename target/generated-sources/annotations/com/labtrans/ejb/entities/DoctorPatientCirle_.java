package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-17T17:20:52")
@StaticMetamodel(DoctorPatientCirle.class)
public class DoctorPatientCirle_ { 

    public static volatile SingularAttribute<DoctorPatientCirle, Date> dateCreated;
    public static volatile SingularAttribute<DoctorPatientCirle, String> deleted;
    public static volatile SingularAttribute<DoctorPatientCirle, String> patientId;
    public static volatile SingularAttribute<DoctorPatientCirle, String> docId;
    public static volatile SingularAttribute<DoctorPatientCirle, String> circleId;
    public static volatile SingularAttribute<DoctorPatientCirle, String> circleCode;
    public static volatile SingularAttribute<DoctorPatientCirle, String> labSession;

}