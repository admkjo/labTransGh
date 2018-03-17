/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.LabBranchPatients;
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
public class LabBranchPatientsBean {

   
    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String labBranchPatientsCreate(LabBranchPatients labBranchPatients) {
        try {
            em.persist(labBranchPatients);
            em.flush();
            return labBranchPatients.getLabBranchId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean labBranchPatientsDelete(LabBranchPatients labBranchPatients, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(labBranchPatients));
            } else if (permanent == false) {
                em.merge(labBranchPatients);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean labBranchPatientsUpdate(LabBranchPatients labBranchPatients) {
        try {
            em.merge(labBranchPatients);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LabBranchPatients> labBranchPatientsFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<LabBranchPatients> listOfLabBranchPatients = null;
        String qryString = "SELECT l FROM LabBranchPatients l WHERE l." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            listOfLabBranchPatients = (List<LabBranchPatients>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfLabBranchPatients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabBranchPatients> labBranchPatientsFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<LabBranchPatients> listOfLabBranchPatients = null;
        String qryString = "SELECT l FROM LabBranchPatients l WHERE l." + attribute1 + " =:attributeValue1 AND l." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            TypedQuery<LabBranchPatients> query = em.createQuery(qryString, LabBranchPatients.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfLabBranchPatients = query.getResultList();
            return listOfLabBranchPatients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabBranchPatients> labBranchPatientsGetAll(boolean includeLogicallyDeleted) {
        List<LabBranchPatients> listOfLabBranchPatients = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabBranchPatients l ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabBranchPatients l WHERE l.deleted = 'NO' ";
            }
            listOfLabBranchPatients = (List<LabBranchPatients>) em.createQuery(qryString).getResultList();
            return listOfLabBranchPatients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabBranchPatients>();
    }

    public LabBranchPatients labBranchPatientsFind(String labBranchPatientsId) {
        try {
            LabBranchPatients labBranchPatients = em.find(LabBranchPatients.class, labBranchPatientsId);
            if (labBranchPatients != null) {
                em.refresh(labBranchPatients);
            }
            return labBranchPatients;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LabBranchPatients> labBranchPatientsGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<LabBranchPatients> listOfLabBranchPatients = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabBranchPatients l";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabBranchPatients l WHERE l.deleted = 'NO'";
            }
            listOfLabBranchPatients = (List<LabBranchPatients>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfLabBranchPatients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabBranchPatients>();
    }

    public LabBranchPatients labBranchPatientsLogin(String phonenumber, String pincode) {
        String qryString = "";
        LabBranchPatients labBranchPatients;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT l FROM LabBranchPatients l where l.phonenumber =:phonenumber and l.pincode=:pincode";
            TypedQuery<LabBranchPatients> query = em.createQuery(qryString, LabBranchPatients.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            labBranchPatients = query.getSingleResult();
            return labBranchPatients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long labBranchPatientsCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
