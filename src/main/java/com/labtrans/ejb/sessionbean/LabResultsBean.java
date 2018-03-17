/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.LabResults;
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
public class LabResultsBean {

    
    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String labResultsCreate(LabResults labResults) {
        try {
            em.persist(labResults);
            em.flush();
            return labResults.getLabResultsId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean labResultsDelete(LabResults labResults, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(labResults));
            } else if (permanent == false) {
                em.merge(labResults);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean labResultsUpdate(LabResults labResults) {
        try {
            em.merge(labResults);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LabResults> labResultsFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<LabResults> listOfLabResults = null;
        String qryString = "SELECT l FROM LabResults l WHERE l." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            listOfLabResults = (List<LabResults>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfLabResults;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabResults> labResultsFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<LabResults> listOfLabResults = null;
        String qryString = "SELECT l FROM LabResults l WHERE l." + attribute1 + " =:attributeValue1 AND l." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            TypedQuery<LabResults> query = em.createQuery(qryString, LabResults.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfLabResults = query.getResultList();
            return listOfLabResults;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabResults> labResultsGetAll(boolean includeLogicallyDeleted) {
        List<LabResults> listOfLabResults = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabResults l ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabResults l WHERE l.deleted = 'NO' ";
            }
            listOfLabResults = (List<LabResults>) em.createQuery(qryString).getResultList();
            return listOfLabResults;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabResults>();
    }

    public LabResults labResultsFind(String labResultsId) {
        try {
            LabResults labResults = em.find(LabResults.class, labResultsId);
            if (labResults != null) {
                em.refresh(labResults);
            }
            return labResults;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LabResults> labResultsGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<LabResults> listOfLabResults = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabResults l";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabResults l WHERE l.deleted = 'NO'";
            }
            listOfLabResults = (List<LabResults>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfLabResults;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabResults>();
    }

    public LabResults labResultsLogin(String phonenumber, String pincode) {
        String qryString = "";
        LabResults labResults;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT l FROM LabResults l where l.phonenumber =:phonenumber and l.pincode=:pincode";
            TypedQuery<LabResults> query = em.createQuery(qryString, LabResults.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            labResults = query.getSingleResult();
            return labResults;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long labResultsCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
