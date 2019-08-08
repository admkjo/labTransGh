package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.Doctor;
import com.labtrans.ejb.entities.LabBranch;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T22:40:21")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, String> pincode;
    public static volatile SingularAttribute<Patient, String> deleted;
    public static volatile SingularAttribute<Patient, String> patientId;
    public static volatile ListAttribute<Patient, LabBranch> labBranches;
    public static volatile ListAttribute<Patient, Doctor> doctors;
    public static volatile SingularAttribute<Patient, String> phonenumber;
    public static volatile SingularAttribute<Patient, String> location;
    public static volatile SingularAttribute<Patient, String> fullname;
    public static volatile SingularAttribute<Patient, String> region;
    public static volatile SingularAttribute<Patient, String> email;

}