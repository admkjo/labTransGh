/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.LabBranch ;
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
public class LabBranchBean {

 
    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String labBranchCreate(LabBranch labBranch) {
        try {
            em.persist(labBranch);
            em.flush();
            return labBranch.getBranchId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean labBranchDelete(LabBranch labBranch, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(labBranch));
            } else if (permanent == false) {
                em.merge(labBranch);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean labBranchUpdate(LabBranch labBranch) {
        try {
            em.merge(labBranch);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LabBranch> labBranchFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<LabBranch> listOfLabBranch = null;
        String qryString = "SELECT l FROM LabBranch l WHERE l." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            listOfLabBranch = (List<LabBranch>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfLabBranch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabBranch> labBranchFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<LabBranch> listOfLabBranch = null;
        String qryString = "SELECT l FROM LabBranch l WHERE l." + attribute1 + " =:attributeValue1 AND l." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            TypedQuery<LabBranch> query = em.createQuery(qryString, LabBranch.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfLabBranch = query.getResultList();
            return listOfLabBranch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabBranch> labBranchGetAll(boolean includeLogicallyDeleted) {
        List<LabBranch> listOfLabBranch = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabBranch l ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabBranch l WHERE l.deleted = 'NO' ";
            }
            listOfLabBranch = (List<LabBranch>) em.createQuery(qryString).getResultList();
            return listOfLabBranch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabBranch>();
    }

    public LabBranch labBranchFind(String labBranchId) {
        try {
            LabBranch labBranch = em.find(LabBranch.class, labBranchId);
            if (labBranch != null) {
                em.refresh(labBranch);
            }
            return labBranch;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LabBranch> labBranchGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<LabBranch> listOfLabBranch = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabBranch l";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabBranch l WHERE l.deleted = 'NO'";
            }
            listOfLabBranch = (List<LabBranch>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfLabBranch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabBranch>();
    }

    public LabBranch labBranchLogin(String phonenumber, String pincode) {
        String qryString = "";
        LabBranch labBranch;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT l FROM LabBranch l where l.phonenumber =:phonenumber and l.pincode=:pincode";
            TypedQuery<LabBranch> query = em.createQuery(qryString, LabBranch.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            labBranch = query.getSingleResult();
            return labBranch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long labBranchCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
