package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T21:33:56")
@StaticMetamodel(DoctorPatientCirle.class)
public class DoctorPatientCirle_ { 

    public static volatile SingularAttribute<DoctorPatientCirle, Date> colDateCreated;
    public static volatile SingularAttribute<DoctorPatientCirle, String> colDocId;
    public static volatile SingularAttribute<DoctorPatientCirle, String> colCircleId;
    public static volatile SingularAttribute<DoctorPatientCirle, String> colCircleCode;
    public static volatile SingularAttribute<DoctorPatientCirle, String> colLabSession;
    public static volatile SingularAttribute<DoctorPatientCirle, String> colDeleted;
    public static volatile SingularAttribute<DoctorPatientCirle, String> colPatientId;

}