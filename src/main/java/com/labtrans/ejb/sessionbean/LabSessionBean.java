/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.LabSession;
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
public class LabSessionBean {

    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String labSessionCreate(LabSession labSession) {
        try {
            em.persist(labSession);
            em.flush();
            return labSession.getLabSessionId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean labSessionDelete(LabSession labSession, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(labSession));
            } else if (permanent == false) {
                em.merge(labSession);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean labSessionUpdate(LabSession labSession) {
        try {
            em.merge(labSession);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LabSession> labSessionFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<LabSession> listOfLabSession = null;
        String qryString = "SELECT l FROM LabSession l WHERE l." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            listOfLabSession = (List<LabSession>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfLabSession;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabSession> labSessionFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<LabSession> listOfLabSession = null;
        String qryString = "SELECT l FROM LabSession l WHERE l." + attribute1 + " =:attributeValue1 AND l." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            TypedQuery<LabSession> query = em.createQuery(qryString, LabSession.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfLabSession = query.getResultList();
            return listOfLabSession;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabSession> labSessionGetAll(boolean includeLogicallyDeleted) {
        List<LabSession> listOfLabSession = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabSession l ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabSession l WHERE l.deleted = 'NO' ";
            }
            listOfLabSession = (List<LabSession>) em.createQuery(qryString).getResultList();
            return listOfLabSession;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabSession>();
    }

    public LabSession labSessionFind(String labSessionId) {
        try {
            LabSession labSession = em.find(LabSession.class, labSessionId);
            if (labSession != null) {
                em.refresh(labSession);
            }
            return labSession;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LabSession> labSessionGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<LabSession> listOfLabSession = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabSession l";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabSession l WHERE l.deleted = 'NO'";
            }
            listOfLabSession = (List<LabSession>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfLabSession;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabSession>();
    }

    public LabSession labSessionLogin(String phonenumber, String pincode) {
        String qryString = "";
        LabSession labSession;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT l FROM LabSession l where l.phonenumber =:phonenumber and l.pincode=:pincode";
            TypedQuery<LabSession> query = em.createQuery(qryString, LabSession.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            labSession = query.getSingleResult();
            return labSession;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long labSessionCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
