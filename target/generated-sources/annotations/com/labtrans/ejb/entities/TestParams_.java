package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.Test;
import com.labtrans.ejb.entities.TestParamDefaultValues;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T22:10:13")
@StaticMetamodel(TestParams.class)
public class TestParams_ { 

    public static volatile CollectionAttribute<TestParams, TestParamDefaultValues> TestcoluParamDefaultValuesCollection;
    public static volatile SingularAttribute<TestParams, String> deleted;
    public static volatile SingularAttribute<TestParams, String> parameter;
    public static volatile SingularAttribute<TestParams, Test> testId;
    public static volatile SingularAttribute<TestParams, String> testParamId;

}