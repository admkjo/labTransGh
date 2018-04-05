package com.labtrans.ejb.entities;

import com.labtrans.ejb.entities.LabSession;
import com.labtrans.ejb.entities.Test;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T22:10:13")
@StaticMetamodel(SessionTests.class)
public class SessionTests_ { 

    public static volatile SingularAttribute<SessionTests, String> sessionTestId;
    public static volatile SingularAttribute<SessionTests, Test> testId;
    public static volatile SingularAttribute<SessionTests, LabSession> labSession;

}