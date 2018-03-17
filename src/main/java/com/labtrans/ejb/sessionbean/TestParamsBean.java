/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.TestParams;
import com.labtrans.util.PasswordUtils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Adm_Kjo
 */
@Stateless
public class TestParamsBean {

    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String testParamsCreate(TestParams testParams) {
        try {
            em.persist(testParams);
            em.flush();
            return testParams.getTestParamId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean testParamsDelete(TestParams testParams, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(testParams));
            } else if (permanent == false) {
                em.merge(testParams);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean testParamsUpdate(TestParams testParams) {
        try {
            em.merge(testParams);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TestParams> testParamsFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<TestParams> listOfTestParams = null;
        String qryString = "SELECT t FROM TestParams t WHERE t." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND t.deleted = 'NO'";
            }
            listOfTestParams = (List<TestParams>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfTestParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TestParams> testParamsFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<TestParams> listOfTestParams = null;
        String qryString = "SELECT t FROM TestParams t WHERE t." + attribute1 + " =:attributeValue1 AND t." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND t.deleted = 'NO'";
            }
            TypedQuery<TestParams> query = em.createQuery(qryString, TestParams.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfTestParams = query.getResultList();
            return listOfTestParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TestParams> testParamsGetAll(boolean includeLogicallyDeleted) {
        List<TestParams> listOfTestParams = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT t FROM TestParams t ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT t FROM TestParams t WHERE t.deleted = 'NO' ";
            }
            listOfTestParams = (List<TestParams>) em.createQuery(qryString).getResultList();
            return listOfTestParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<TestParams>();
    }

    public TestParams testParamsFind(String testParamsId) {
        try {
            TestParams testParams = em.find(TestParams.class, testParamsId);
            if (testParams != null) {
                em.refresh(testParams);
            }
            return testParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TestParams> testParamsGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<TestParams> listOfTestParams = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT t FROM TestParams t";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT t FROM TestParams t WHERE t.deleted = 'NO'";
            }
            listOfTestParams = (List<TestParams>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfTestParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<TestParams>();
    }

    public TestParams testParamsLogin(String phonenumber, String pincode) {
        String qryString = "";
        TestParams testParams;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT t FROM TestParams t where t.phonenumber =:phonenumber and t.pincode=:pincode";
            TypedQuery<TestParams> query = em.createQuery(qryString, TestParams.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            testParams = query.getSingleResult();
            return testParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long testParamsCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
