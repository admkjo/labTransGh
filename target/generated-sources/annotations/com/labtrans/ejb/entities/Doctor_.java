package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.Patient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T22:40:21")
@StaticMetamodel(Doctor.class)
public class Doctor_ { 

    public static volatile SingularAttribute<Doctor, String> pincode;
    public static volatile SingularAttribute<Doctor, String> docId;
    public static volatile ListAttribute<Doctor, Patient> patients;
    public static volatile SingularAttribute<Doctor, String> docCode;
    public static volatile SingularAttribute<Doctor, String> hospitalName;
    public static volatile SingularAttribute<Doctor, Date> dateCreated;
    public static volatile SingularAttribute<Doctor, String> deleted;
    public static volatile SingularAttribute<Doctor, String> contact;
    public static volatile SingularAttribute<Doctor, String> numberOfYearsWorked;
    public static volatile SingularAttribute<Doctor, String> fullname;
    public static volatile SingularAttribute<Doctor, String> residence;
    public static volatile SingularAttribute<Doctor, String> region;
    public static volatile SingularAttribute<Doctor, String> locOfHospital;

}