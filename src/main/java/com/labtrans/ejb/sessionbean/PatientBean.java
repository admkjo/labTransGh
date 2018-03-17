/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labtrans.ejb.sessionbean;

import com.labtrans.ejb.entities.Patient;
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
public class PatientBean {

    @PersistenceContext(unitName = "LabTransGh-PU")
    private EntityManager em;

    public String patientCreate(Patient patient) {
        try {
            em.persist(patient);
            em.flush();
            return patient.getPatientId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean patientDelete(Patient patient, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(patient));
            } else if (permanent == false) {
                em.merge(patient);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean patientUpdate(Patient patient) {
        try {
            em.merge(patient);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Patient> patientFindByAttribute(String attribute, Object attributeValue, boolean includeLogicallyDeleted) {
        List<Patient> listOfPatient = null;
        String qryString = "SELECT p FROM Patient p WHERE p." + attribute + " =:attributeValue ";
        try {
            if (includeLogicallyDeleted == true) {

            } else if (includeLogicallyDeleted == false) {
                qryString += " AND p.deleted = 'NO'";
            }
            listOfPatient = (List<Patient>) em.createQuery(qryString).setParameter("attributeValue", attributeValue).getResultList();
            return listOfPatient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> patientGetAll(boolean includeLogicallyDeleted) {
        List<Patient> listOfPatient = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT p FROM Patient p ";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT p FROM Patient p WHERE p.deleted = 'NO' ";
            }
            listOfPatient = (List<Patient>) em.createQuery(qryString).getResultList();
            return listOfPatient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Patient>();
    }

    public Patient patientFind(String patientId) {
        try {
            Patient patient = em.find(Patient.class, patientId);
            if (patient != null) {
                em.refresh(patient);
            }
            return patient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Patient> patientGetRange(Integer firstResultIndex, Integer numberToRetrieve, boolean includeLogicallyDeleted) {
        List<Patient> listOfPatient = null;
        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT p FROM Patient p";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT p FROM Patient p WHERE p.deleted = 'NO'";
            }
            listOfPatient = (List<Patient>) em.createQuery(qryString).setFirstResult(firstResultIndex).setMaxResults(numberToRetrieve).getResultList();
            return listOfPatient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Patient>();
    }

    public Patient patientLogin(String phonenumber, String pincode) {
        String qryString = "";
        Patient patient;
        pincode = PasswordUtils.digestPassword(pincode);
        try {
            qryString = " SELECT p FROM Patient p where p.phonenumber =:phonenumber and p.pincode=:pincode";
            TypedQuery<Patient> query = em.createQuery(qryString, Patient.class);
            query.setParameter("phonenumber", phonenumber);
            query.setParameter("pincode", pincode);
            patient = query.getSingleResult();
            return patient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long patientCount(boolean includeLogicallyDeleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
