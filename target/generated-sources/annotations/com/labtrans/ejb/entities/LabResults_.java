package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.LabSession;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T22:10:13")
@StaticMetamodel(LabResults.class)
public class LabResults_ { 

    public static volatile SingularAttribute<LabResults, String> labResultsId;
    public static volatile SingularAttribute<LabResults, String> testParamName;
    public static volatile SingularAttribute<LabResults, String> testParamValue;
    public static volatile SingularAttribute<LabResults, LabSession> labSession;

}