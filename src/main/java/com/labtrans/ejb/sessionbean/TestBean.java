/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.Test;
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
public class TestBean {

    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String testCreate(Test test) {
        try {
            em.persist(test);
            em.flush();
            return test.getTestId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean testDelete(Test test, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(test));
            } else if (permanent == false) {
                em.merge(test);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean testUpdate(Test test) {
        try {
            em.merge(test);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Test> testFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<Test> listOfTest = null;
        String qryString = "SELECT t FROM Test t WHERE t." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND t.deleted = 'NO'";
            }
            listOfTest = (List<Test>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfTest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Test> testFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<Test> listOfTest = null;
        String qryString = "SELECT t FROM Test t WHERE t." + attribute1 + " =:attributeValue1 AND t." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND t.deleted = 'NO'";
            }
            TypedQuery<Test> query = em.createQuery(qryString, Test.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfTest = query.getResultList();
            return listOfTest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Test> testGetAll(boolean includeLogicallyDeleted) {
        List<Test> listOfTest = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT t FROM Test t ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT t FROM Test t WHERE t.deleted = 'NO' ";
            }
            listOfTest = (List<Test>) em.createQuery(qryString).getResultList();
            return listOfTest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Test>();
    }

    public Test testFind(String testId) {
        try {
            Test test = em.find(Test.class, testId);
            if (test != null) {
                em.refresh(test);
            }
            return test;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Test> testGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<Test> listOfTest = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT t FROM Test t";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT t FROM Test t WHERE t.deleted = 'NO'";
            }
            listOfTest = (List<Test>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfTest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Test>();
    }

    public Test testLogin(String phonenumber, String pincode) {
        String qryString = "";
        Test test;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT t FROM Test t where t.phonenumber =:phonenumber and t.pincode=:pincode";
            TypedQuery<Test> query = em.createQuery(qryString, Test.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            test = query.getSingleResult();
            return test;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long testCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
