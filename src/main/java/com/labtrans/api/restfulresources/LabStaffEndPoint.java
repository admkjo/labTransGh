package com.labtrans.api.restfulresources;

import com.labtrans.ejb.entities.LabAccount;
import com.labtrans.ejb.entities.LabBranch;
import com.labtrans.ejb.entities.LabSession;
import com.labtrans.ejb.entities.LabStaff;
import com.labtrans.ejb.entities.Patient;
import com.labtrans.ejb.sessionbean.LabBranchBean;
import com.labtrans.ejb.sessionbean.LabSessionBean;
import com.labtrans.ejb.sessionbean.LabStaffBean;
import com.labtrans.ejb.sessionbean.PatientBean;
import com.labtrans.jwtfilter.JWTTokenNeeded;
import com.labtrans.util.JWTokenUtility;
import com.labtrans.util.PasswordUtils;
import com.labtrans.util.RandomStringGenerator;
import java.security.Principal;
import java.util.Date;
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
@Path("/labstaff")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@Stateless
public class LabStaffEndPoint {

    @EJB
    private LabStaffBean labStaffBean;
    @EJB
    private LabSessionBean labSessionBean;
     @EJB
    private LabBranchBean labBranchBean;
      @EJB
    private PatientBean patientBean;

    private static final Logger LOGGER = Logger.getLogger(LabStaffEndPoint.class.getName());

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(
            @FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            LOGGER.info("#### login/password : " + username + "/" + password);
            // Authenticate the user using the credentials provided
            LabStaff labStaff = labStaffBean.labStaffLogin(username, password);
            if (labStaff == null) {
                return Response.status(NOT_FOUND).entity("WRONG LOGIN DETAILS").build();
            }
            String staffCode = labStaff.getUsername();
            // Issue a token for the user
            String token = JWTokenUtility.buildJWT(staffCode);

            // Return the token on the response
            return Response.ok("LOGIN SUCCESSFUL").header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).entity("LOGIN FAILED").build();
        }
    }

    @POST
    @Path("/confirm_code")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response confirmCode(
            @FormParam("username") String username,
            @FormParam("code") String code) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(code)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            LOGGER.info("#### username/code : " + username + "/" + code);
            List<LabStaff> LabStaffList = labStaffBean.labStaffFindByAttribute("username", username, false);
            LabStaff labStaff = LabStaffList.get(0);
            if (labStaff.getVerifyCode().equals(code)) {
                return Response.ok("CORRECT CODE").build();
            } else {
                return Response.status(NOT_FOUND).entity("INCORRECT CODE").build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(UNAUTHORIZED).entity("VALIDATION FAILED").build();
        }
    }

    @POST
    @Path("/generate_code")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response generateCode(
            @FormParam("username") String username) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(username)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            String code = RandomStringGenerator.randomString(5);
            List<LabStaff> LabStaffList = labStaffBean.labStaffFindByAttribute("username", username, false);
            LabStaff labStaff = LabStaffList.get(0);
            labStaff.setVerifyCode(code);

            boolean staffId = labStaffBean.labStaffUpdate(labStaff);
            if (staffId == false) {
                return Response.ok("UNABLE TO GENERATE CODE").build();
            }
            return Response.ok("CODE GENERATED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/update_password")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response updatePassword(
            @FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            List<LabStaff> LabStaffList = labStaffBean.labStaffFindByAttribute("username", username, false);
            LabStaff labStaff = LabStaffList.get(0);
            password = PasswordUtils.digestPassword(password);
            labStaff.setPassword(password);

            boolean staffId = labStaffBean.labStaffUpdate(labStaff);
            if (staffId == false) {
                return Response.ok("UNABLE TO UPDATE PASSWORD").build();
            }
            return Response.ok("PASSWORD UPDATED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/create_lab_session")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response createLabSession(
            @FormParam("completionStatus") String completionStatus,
            @FormParam("labBranch") String labBranch,
            @FormParam("labStaff") String labStaff,
            @FormParam("notifyPatient") String notifyPatient,
            @FormParam("patient") String patient,
            @FormParam("paymentStatus") String paymentStatus,
            @FormParam("sendToPatientStatus") String sendToPatientStatus
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(completionStatus) && StringUtils.isNotBlank(labBranch)
                    && StringUtils.isNotBlank(labStaff) && StringUtils.isNotBlank(notifyPatient)
                    && StringUtils.isNotBlank(patient) && StringUtils.isNotBlank(paymentStatus)
                    && StringUtils.isNotBlank(sendToPatientStatus)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }

            String labSessionId = "SESS-" + RandomStringGenerator.randomString(5);

            //get lab branch
            List<LabBranch> labBranchList = labBranchBean.labBranchFindByAttribute("branchCode", labBranch, false);
            if (labBranchList.isEmpty()) {
                return Response.ok("UNKNOWN BRANCH").build();
            }
            LabBranch selectedLabBranch = labBranchList.get(0);
            //get branch staff
            List<LabStaff> labStaffList = labStaffBean.labStaffFindByAttribute("staffId", labStaff, false);
            if (labStaffList.isEmpty()) {
                return Response.ok("UNKNOWN STAFF").build();
            }
            LabStaff selectedLabStaff = labStaffList.get(0);
            
            //get patient
            List<Patient> patientList = patientBean.patientFindByAttribute("patientId", patient, true);
            if (patientList.isEmpty()) {
                return Response.ok("UNKNOWN PATIENT").build();
            }
            Patient selectedPatient = patientList.get(0);
          
            LabSession labSession = new LabSession();
            labSession.setCompletionStatus("PENDING");
            labSession.setDateCreated(new Date());
            labSession.setLabBranch(selectedLabBranch);
            labSession.setLabSessionId(labSessionId);
            labSession.setLabStaff(selectedLabStaff);
            labSession.setNotifyPatient("NO");
            labSession.setPatientId(selectedPatient);
            labSession.setPaymentStatus(paymentStatus);
            labSession.setSendToPatientStatus(sendToPatientStatus);

            String sessionId = labSessionBean.labSessionCreate(labSession);
            if (sessionId == null) {
                return Response.ok("UNABLE TO CREATE LAB SESSION").build();
            }
            return Response.ok("LAB SESSION CREATED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
