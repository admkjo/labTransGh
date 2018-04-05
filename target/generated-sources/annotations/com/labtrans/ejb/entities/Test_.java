package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.SessionTests;
import com.labtrans.ejb.entities.TestParams;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T22:10:13")
@StaticMetamodel(Test.class)
public class Test_ { 

    public static volatile CollectionAttribute<Test, TestParams> testParamsCollection;
    public static volatile SingularAttribute<Test, String> testCode;
    public static volatile SingularAttribute<Test, Date> dateCreated;
    public static volatile SingularAttribute<Test, String> deleted;
    public static volatile SingularAttribute<Test, String> createdBy;
    public static volatile CollectionAttribute<Test, SessionTests> sessionTestsCollection;
    public static volatile SingularAttribute<Test, String> testId;
    public static volatile SingularAttribute<Test, String> testDescription;
    public static volatile SingularAttribute<Test, String> testName;

}