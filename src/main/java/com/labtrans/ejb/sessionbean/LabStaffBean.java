/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.LabStaff;
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
public class LabStaffBean {
   
    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public Integer labStaffCreate(LabStaff labStaff) {
        try {
            em.persist(labStaff);
            em.flush();
            return labStaff.getStaffId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean labStaffDelete(LabStaff labStaff, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(labStaff));
            } else if (permanent == false) {
                em.merge(labStaff);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean labStaffUpdate(LabStaff labStaff) {
        try {
            em.merge(labStaff);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LabStaff> labStaffFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<LabStaff> listOfLabStaff = null;
        String qryString = "SELECT l FROM LabStaff l WHERE l." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            listOfLabStaff = (List<LabStaff>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfLabStaff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabStaff> labStaffFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<LabStaff> listOfLabStaff = null;
        String qryString = "SELECT l FROM LabStaff l WHERE l." + attribute1 + " =:attributeValue1 AND l." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            TypedQuery<LabStaff> query = em.createQuery(qryString, LabStaff.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfLabStaff = query.getResultList();
            return listOfLabStaff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabStaff> labStaffGetAll(boolean includeLogicallyDeleted) {
        List<LabStaff> listOfLabStaff = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabStaff l ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabStaff l WHERE l.deleted = 'NO' ";
            }
            listOfLabStaff = (List<LabStaff>) em.createQuery(qryString).getResultList();
            return listOfLabStaff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabStaff>();
    }

    public LabStaff labStaffFind(String labStaffId) {
        try {
            LabStaff labStaff = em.find(LabStaff.class, labStaffId);
            if (labStaff != null) {
                em.refresh(labStaff);
            }
            return labStaff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LabStaff> labStaffGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<LabStaff> listOfLabStaff = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabStaff l";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabStaff l WHERE l.deleted = 'NO'";
            }
            listOfLabStaff = (List<LabStaff>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfLabStaff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabStaff>();
    }

    public LabStaff labStaffLogin(String phonenumber, String pincode) {
        String qryString = "";
        LabStaff labStaff;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT l FROM LabStaff l where l.phonenumber =:phonenumber and l.pincode=:pincode";
            TypedQuery<LabStaff> query = em.createQuery(qryString, LabStaff.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            labStaff = query.getSingleResult();
            return labStaff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long labStaffCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
