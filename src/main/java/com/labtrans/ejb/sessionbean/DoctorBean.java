/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.Doctor;
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
public class DoctorBean {

    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String doctorCreate(Doctor doctor) {
        try {
            em.persist(doctor);
            em.flush();
            return doctor.getDocId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean doctorDelete(Doctor doctor, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(doctor));
            } else if (permanent == false) {
                em.merge(doctor);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean doctorUpdate(Doctor doctor) {
        try {
            em.merge(doctor);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Doctor> doctorFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<Doctor> listOfDoctor = null;
        String qryString = "SELECT d FROM Doctor d WHERE d." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND d.deleted = 'NO'";
            }
            listOfDoctor = (List<Doctor>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfDoctor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Doctor> doctorFindByAttributes(String attribute1, Object attributeValue1, String attribute2, Object attributeValue2, boolean includeLogicallyDeleted) {
        List<Doctor> listOfDoctor = null;
        String qryString = "SELECT d FROM Doctor d WHERE d." + attribute1 + " =:attributeValue1 AND d." + attribute2 + " =:attributeValue2";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND d.deleted = 'NO'";
            }
            TypedQuery<Doctor> query = em.createQuery(qryString, Doctor.class);
            query.setParameter("attributeValue1", attributeValue1);
            query.setParameter("attributeValue2", attributeValue2);
            listOfDoctor = query.getResultList();
            return listOfDoctor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Doctor> doctorGetAll(boolean includeLogicallyDeleted) {
        List<Doctor> listOfDoctor = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT d FROM Doctor d ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT d FROM Doctor d WHERE d.deleted = 'NO' ";
            }
            listOfDoctor = (List<Doctor>) em.createQuery(qryString).getResultList();
            return listOfDoctor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Doctor>();
    }

    public Doctor doctorFind(String doctorId) {
        try {
            Doctor doctor = em.find(Doctor.class, doctorId);
            if (doctor != null) {
                em.refresh(doctor);
            }
            return doctor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Doctor> doctorGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<Doctor> listOfDoctor = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT d FROM Doctor d";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT d FROM Doctor d WHERE d.deleted = 'NO'";
            }
            listOfDoctor = (List<Doctor>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfDoctor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Doctor>();
    }

    public Doctor doctorLogin(String phonenumber, String pincode) {
        String qryString = "";
        Doctor doctor;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT d FROM Doctor d where d.phonenumber =:phonenumber and d.pincode=:pincode";
            TypedQuery<Doctor> query = em.createQuery(qryString, Doctor.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            doctor = query.getSingleResult();
            return doctor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long doctorCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
