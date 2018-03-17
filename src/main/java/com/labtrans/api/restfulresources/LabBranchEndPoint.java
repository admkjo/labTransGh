package com.labtrans.api.restfulresources;

import com.labtrans.ejb.entities.LabSession;
import com.labtrans.ejb.entities.Patient;
import com.labtrans.ejb.sessionbean.LabSessionBean;
import com.labtrans.ejb.sessionbean.PatientBean;
import com.labtrans.jwtfilter.JWTTokenNeeded;
import com.labtrans.util.JWTokenUtility;
import com.labtrans.util.PasswordUtils;
import java.security.Principal;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.GenericEntity;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Adm_Kjo
 */
@Path("/patient")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@Stateless
public class LabBranchEndPoint {

    @EJB
    private PatientBean patientBean;
    @EJB
    private LabSessionBean labSessionBean;
    private static final Logger LOGGER = Logger.getLogger(PatientBean.class.getName());

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(
            @FormParam("phonenumber") String phonenumber,
            @FormParam("pincode") String pincode) {
        try {
            LOGGER.info("#### login/password : " + phonenumber + "/" + pincode);
            // Authenticate the user using the credentials provided
            Patient patient = patientBean.patientLogin(phonenumber, pincode);
            if (patient == null) {
                return Response.status(NOT_FOUND).entity("unregistered user").build();
            }
            // Issue a token for the user
            String token = JWTokenUtility.buildJWT(phonenumber);

            // Return the token on the response
            return Response.ok("login successful").header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).entity("login failed").build();
        }
    }

    @POST
    @Path("/signup")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response create(
            @FormParam("fullname") String fullname,
            @FormParam("email") String email,
            @FormParam("location") String location,
            @FormParam("region") String region,
            @FormParam("phonenumber") String phonenumber,
            @FormParam("pincode") String pincode
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(fullname) && StringUtils.isNotBlank(email)
                    && StringUtils.isNotBlank(location) && StringUtils.isNotBlank(region)
                    && StringUtils.isNotBlank(phonenumber) && StringUtils.isNotBlank(pincode)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            boolean dp = checkForDuplicateRegistration(phonenumber);
            if (dp == true) {
                return Response.ok("This number is already registered in the system").build();
            }
            pincode = PasswordUtils.digestPassword(pincode);
            Patient patient = new Patient();
            patient.setFullname(fullname);
            patient.setEmail(email);
            patient.setLocation(location);
            patient.setRegion(region);
            patient.setPhonenumber(phonenumber);
            patient.setPincode(pincode);
            patient.setDeleted("NO");

            String pId = patientBean.patientCreate(patient);
            if (pId == null) {
                return Response.ok("UNABLE TO CREATE USER").build();
            }
            return Response.ok(pId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Path("/pending")
    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response pendingResults(@Context SecurityContext securityContext) {
        try {
            Principal principal = securityContext.getUserPrincipal();
            String phonenumber = principal.getName();
            List<LabSession> pendingSessionList = labSessionBean.labSessionGetAll(false);
            GenericEntity<List<LabSession>> pendingSession = new GenericEntity<List<LabSession>>(pendingSessionList) {
            };
            if (pendingSessionList == null) {
                System.out.println("nothkin heeeee");
                return Response.ok("empty results").build();
            } else {
                System.out.println("sometinn heerrr");
                return Response.ok(pendingSession).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkForDuplicateRegistration(String phonenumber) {
        List<Patient> ps = patientBean.patientFindByAttribute("phonenumber", phonenumber, false);
        if (!(ps.isEmpty())) {
            return true;
        } else {
            return false;
        }

    }
}
