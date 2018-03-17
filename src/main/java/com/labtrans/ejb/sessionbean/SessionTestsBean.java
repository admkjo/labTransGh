/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.SessionTests;
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
public class SessionTestsBean {

   
    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String sessionTestsCreate(SessionTests sessionTests) {
        try {
            em.persist(sessionTests);
            em.flush();
            return sessionTests.getSessionTestId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean sessionTestsDelete(SessionTests sessionTests, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(sessionTests));
            } else if (permanent == false) {
                em.merge(sessionTests);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean sessionTestsUpdate(SessionTests sessionTests) {
        try {
            em.merge(sessionTests);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SessionTests> sessionTestsFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<SessionTests> listOfSessionTests = null;
        String qryString = "SELECT s FROM SessionTests s WHERE s." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND s.deleted = 'NO'";
            }
            listOfSessionTests = (List<SessionTests>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfSessionTests;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SessionTests> sessionTestsFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<SessionTests> listOfSessionTests = null;
        String qryString = "SELECT s FROM SessionTests s WHERE s." + attribute1 + " =:attributeValue1 AND s." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND s.deleted = 'NO'";
            }
            TypedQuery<SessionTests> query = em.createQuery(qryString, SessionTests.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfSessionTests = query.getResultList();
            return listOfSessionTests;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SessionTests> sessionTestsGetAll(boolean includeLogicallyDeleted) {
        List<SessionTests> listOfSessionTests = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT s FROM SessionTests s ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT s FROM SessionTests s WHERE s.deleted = 'NO' ";
            }
            listOfSessionTests = (List<SessionTests>) em.createQuery(qryString).getResultList();
            return listOfSessionTests;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<SessionTests>();
    }

    public SessionTests sessionTestsFind(String sessionTestsId) {
        try {
            SessionTests sessionTests = em.find(SessionTests.class, sessionTestsId);
            if (sessionTests != null) {
                em.refresh(sessionTests);
            }
            return sessionTests;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SessionTests> sessionTestsGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<SessionTests> listOfSessionTests = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT s FROM SessionTests s";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT s FROM SessionTests s WHERE s.deleted = 'NO'";
            }
            listOfSessionTests = (List<SessionTests>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfSessionTests;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<SessionTests>();
    }

    public SessionTests sessionTestsLogin(String phonenumber, String pincode) {
        String qryString = "";
        SessionTests sessionTests;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT s FROM SessionTests s where s.phonenumber =:phonenumber and s.pincode=:pincode";
            TypedQuery<SessionTests> query = em.createQuery(qryString, SessionTests.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            sessionTests = query.getSingleResult();
            return sessionTests;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long sessionTestsCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
