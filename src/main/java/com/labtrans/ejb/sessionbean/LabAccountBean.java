/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.LabAccount ;
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
public class LabAccountBean {

    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public Integer labAccountCreate(LabAccount labAccount) {
        try {
            em.persist(labAccount);
            em.flush();
            return labAccount.getId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean labAccountDelete(LabAccount labAccount, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(labAccount));
            } else if (permanent == false) {
                em.merge(labAccount);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean labAccountUpdate(LabAccount labAccount) {
        try {
            em.merge(labAccount);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LabAccount> labAccountFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<LabAccount> listOfLabAccount = null;
        String qryString = "SELECT l FROM LabAccount l WHERE l." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            listOfLabAccount = (List<LabAccount>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfLabAccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabAccount> labAccountFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<LabAccount> listOfLabAccount = null;
        String qryString = "SELECT l FROM LabAccount l WHERE l." + attribute1 + " =:attributeValue1 AND l." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND l.deleted = 'NO'";
            }
            TypedQuery<LabAccount> query = em.createQuery(qryString, LabAccount.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfLabAccount = query.getResultList();
            return listOfLabAccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LabAccount> labAccountGetAll(boolean includeLogicallyDeleted) {
        List<LabAccount> listOfLabAccount = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabAccount l ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabAccount l WHERE l.deleted = 'NO' ";
            }
            listOfLabAccount = (List<LabAccount>) em.createQuery(qryString).getResultList();
            return listOfLabAccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabAccount>();
    }

    public LabAccount labAccountFind(String labAccountId) {
        try {
            LabAccount labAccount = em.find(LabAccount.class, labAccountId);
            if (labAccount != null) {
                em.refresh(labAccount);
            }
            return labAccount;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LabAccount> labAccountGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<LabAccount> listOfLabAccount = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT l FROM LabAccount l";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT l FROM LabAccount l WHERE l.deleted = 'NO'";
            }
            listOfLabAccount = (List<LabAccount>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfLabAccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LabAccount>();
    }

    public LabAccount labAccountLogin(String username, String password) {
        String qryString = "";
        LabAccount labAccount;
        password = PasswordUtils.digestPassword(password);
        try {
            qryString = " SELECT l FROM LabAccount l where l.username =:username and l.password=:password";
            TypedQuery<LabAccount> query = em.createQuery(qryString, LabAccount.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            labAccount = query.getSingleResult();
            return labAccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long labAccountCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
