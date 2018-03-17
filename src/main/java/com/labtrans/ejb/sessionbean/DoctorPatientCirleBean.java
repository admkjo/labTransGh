/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.DoctorPatientCirle ;
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
public class DoctorPatientCirleBean {

    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String doctorPatientCirleCreate(DoctorPatientCirle doctorPatientCirle) {
        try {
            em.persist(doctorPatientCirle);
            em.flush();
            return doctorPatientCirle.getCircleId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean doctorPatientCirleDelete(DoctorPatientCirle doctorPatientCirle, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(doctorPatientCirle));
            } else if (permanent == false) {
                em.merge(doctorPatientCirle);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean doctorPatientCirleUpdate(DoctorPatientCirle doctorPatientCirle) {
        try {
            em.merge(doctorPatientCirle);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DoctorPatientCirle> doctorPatientCirleFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<DoctorPatientCirle> listOfDoctorPatientCirle = null;
        String qryString = "SELECT c FROM DoctorPatientCirle c WHERE c." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND c.deleted = 'NO'";
            }
            listOfDoctorPatientCirle = (List<DoctorPatientCirle>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfDoctorPatientCirle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DoctorPatientCirle> doctorPatientCirleFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<DoctorPatientCirle> listOfDoctorPatientCirle = null;
        String qryString = "SELECT c FROM DoctorPatientCirle c WHERE c." + attribute1 + " =:attributeValue1 AND c." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND c.deleted = 'NO'";
            }
            TypedQuery<DoctorPatientCirle> query = em.createQuery(qryString, DoctorPatientCirle.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfDoctorPatientCirle = query.getResultList();
            return listOfDoctorPatientCirle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DoctorPatientCirle> doctorPatientCirleGetAll(boolean includeLogicallyDeleted) {
        List<DoctorPatientCirle> listOfDoctorPatientCirle = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT c FROM DoctorPatientCirle c ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT c FROM DoctorPatientCirle c WHERE c.deleted = 'NO' ";
            }
            listOfDoctorPatientCirle = (List<DoctorPatientCirle>) em.createQuery(qryString).getResultList();
            return listOfDoctorPatientCirle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DoctorPatientCirle>();
    }

    public DoctorPatientCirle doctorPatientCirleFind(String doctorPatientCirleId) {
        try {
            DoctorPatientCirle doctorPatientCirle = em.find(DoctorPatientCirle.class, doctorPatientCirleId);
            if (doctorPatientCirle != null) {
                em.refresh(doctorPatientCirle);
            }
            return doctorPatientCirle;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DoctorPatientCirle> doctorPatientCirleGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<DoctorPatientCirle> listOfDoctorPatientCirle = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT c FROM DoctorPatientCirle c";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT c FROM DoctorPatientCirle c WHERE c.deleted = 'NO'";
            }
            listOfDoctorPatientCirle = (List<DoctorPatientCirle>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfDoctorPatientCirle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DoctorPatientCirle>();
    }

    public DoctorPatientCirle doctorPatientCirleLogin(String phonenumber, String pincode) {
        String qryString = "";
        DoctorPatientCirle doctorPatientCirle;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT c FROM DoctorPatientCirle c where c.phonenumber =:phonenumber and c.pincode=:pincode";
            TypedQuery<DoctorPatientCirle> query = em.createQuery(qryString, DoctorPatientCirle.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            doctorPatientCirle = query.getSingleResult();
            return doctorPatientCirle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long doctorPatientCirleCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
