package com.labtrans.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-17T17:20:52")
@StaticMetamodel(LabAccount.class)
public class LabAccount_ { 

    public static volatile SingularAttribute<LabAccount, String> labcode;
    public static volatile SingularAttribute<LabAccount, String> password;
    public static volatile SingularAttribute<LabAccount, Date> dateCreated;
    public static volatile SingularAttribute<LabAccount, String> deleted;
    public static volatile SingularAttribute<LabAccount, String> nameOfOrginazation;
    public static volatile SingularAttribute<LabAccount, String> contact;
    public static volatile SingularAttribute<LabAccount, String> location;
    public static volatile SingularAttribute<LabAccount, Integer> id;
    public static volatile SingularAttribute<LabAccount, String> region;
    public static volatile SingularAttribute<LabAccount, String> username;

}