package com.labtrans.api.restfulresources;

import com.labtrans.ejb.entities.LabAccount;
import com.labtrans.ejb.entities.LabBranch;
import com.labtrans.ejb.entities.LabStaff;
import com.labtrans.ejb.sessionbean.LabAccountBean;
import com.labtrans.ejb.sessionbean.LabBranchBean;
import com.labtrans.ejb.sessionbean.LabStaffBean;
import com.labtrans.jwtfilter.JWTTokenNeeded;
import com.labtrans.util.JWTokenUtility;
import com.labtrans.util.PasswordUtils;
import com.labtrans.util.RandomStringGenerator;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
@Path("/labaccount")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@Stateless
public class LabAccountEndPoint {

    @EJB
    private LabAccountBean labAccountBean;
    @EJB
    private LabBranchBean labBranchBean;
    @EJB
    private LabStaffBean labStaffBean;

    private static final Logger LOGGER = Logger.getLogger(LabAccountEndPoint.class.getName());

    @POST
    @Path("/create_lab_account")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response createLabAccount(
            @FormParam("nameOfOrginazation") String nameOfOrginazation,
            @FormParam("location") String location,
            @FormParam("region") String region,
            @FormParam("contact") String contact,
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("email") String email
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(nameOfOrginazation)
                    && StringUtils.isNotBlank(location) && StringUtils.isNotBlank(region)
                    && StringUtils.isNotBlank(contact) && StringUtils.isNotBlank(username)
                    && StringUtils.isNotBlank(email)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            String labcode = "LAB-" + UUID.randomUUID().toString();
            password = PasswordUtils.digestPassword(password);
            LabAccount labAccount = new LabAccount();
            labAccount.setPassword(password);
            labAccount.setNameOfOrginazation(nameOfOrginazation);
            labAccount.setLocation(location);
            labAccount.setRegion(region);
            labAccount.setContact(contact);
            labAccount.setUsername(username);
            labAccount.setDateCreated(new Date());
            labAccount.setLabcode(labcode);
            labAccount.setEmail(email);
            labAccount.setDeleted("NO");

            Integer LabId = labAccountBean.labAccountCreate(labAccount);
            if (LabId == null) {
                return Response.ok("UNABLE TO CREATE ACCOUNT").build();
            }
            return Response.ok(LabId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/create_branch_staff")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response createBranchStaff(
            @FormParam("fullname") String fullname,
            @FormParam("password") String password,
            @FormParam("phone") String phone,
            @FormParam("branchId") String branchId,
            @FormParam("username") String username,
            @FormParam("usercode") String usercode,
            @FormParam("status") String status,
            @FormParam("email") String email
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(fullname)
                    && StringUtils.isNotBlank(branchId) && StringUtils.isNotBlank(password)
                    && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(username)
                    && StringUtils.isNotBlank(usercode) && StringUtils.isNotBlank(status)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }

            //get lab branch
            List<LabBranch> labBranchList = labBranchBean.labBranchFindByAttribute("branchId", branchId, false);
            LabBranch labBranch = labBranchList.get(0);

            LabStaff labStaff = new LabStaff();
            labStaff.setDateCreated(new Date());
            labStaff.setDeleted("NO");
            labStaff.setEmail(email);
            labStaff.setFullname(fullname);
            labStaff.setLabBranch(labBranch);
            labStaff.setPassword(password);
            labStaff.setPhone(phone);
            labStaff.setUserCode(usercode);
            labStaff.setUsername(username);
            labStaff.setStatus(status);

            Integer StaffId = labStaffBean.labStaffCreate(labStaff);
            if (StaffId == null) {
                return Response.ok("UNABLE TO CREATE STAFF").build();
            }
            return Response.ok(branchId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(
            @FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            LOGGER.info("#### login/password : " + username + "/" + password);
            // Authenticate the user using the credentials provided
            LabAccount labAccount = labAccountBean.labAccountLogin(username, password);
            String labcode = labAccount.getLabcode();
            if (labAccount == null) {
                return Response.status(NOT_FOUND).entity("unregistered user").build();
            }
            // Issue a token for the user
            String token = JWTokenUtility.buildJWT(labcode);

            // Return the token on the response
            return Response.ok("login successful").header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).entity("login failed").build();
        }
    }

    @POST
    @Path("/create_lab_branch")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response createLabBranch(
            @FormParam("location") String location,
            @FormParam("region") String region,
            @FormParam("contact") String contact,
            @FormParam("branchname") String branchname,
            @FormParam("email") String email,
            @Context SecurityContext securityContext
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(email)
                    && StringUtils.isNotBlank(location) && StringUtils.isNotBlank(region)
                    && StringUtils.isNotBlank(contact) && StringUtils.isNotBlank(branchname)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            Principal principal = securityContext.getUserPrincipal();
            String labcode = principal.getName();
            List<LabAccount> labAccountList = labAccountBean.labAccountFindByAttribute("labcode", labcode, false);
            LabAccount labAccount = labAccountList.get(0);
            String branchcode = "LAB-" + labAccount.getLabcode() + "-" + RandomStringGenerator.randomString(5);

            LabBranch labBranch = new LabBranch();
            labBranch.setBranchCode(branchcode);
            labBranch.setBranchName(branchname);
            labBranch.setContact(contact);
            labBranch.setDateCreated(new Date());
            labBranch.setDeleted("NO");
            labBranch.setEmail(email);
            labBranch.setLabAccount(labAccount);
            labBranch.setLocation(location);
            labBranch.setRegion(region);

            String branchId = labBranchBean.labBranchCreate(labBranch);
            if (branchId == null) {
                return Response.ok("UNABLE TO CREATE LAB BRANCH").build();
            }
            return Response.ok(branchId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            List<LabAccount> labAccountList = labAccountBean.labAccountFindByAttribute("username", username, false);
            LabAccount labAccount = labAccountList.get(0);
            if (labAccount.getVerifyCode().equals(code)) {
                return Response.ok("correct code").build();
            } else {
                return Response.status(NOT_FOUND).entity("incorrect code").build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(UNAUTHORIZED).entity("validation failed").build();
        }
    }

    @POST
    @Path("/generatecode")
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
            List<LabAccount> labAccountList = labAccountBean.labAccountFindByAttribute("username", username, false);
            LabAccount labAccount = labAccountList.get(0);
            labAccount.setVerifyCode(code);

            boolean LabId = labAccountBean.labAccountUpdate(labAccount);
            if (LabId == false) {
                return Response.ok("UNABLE TO GENERATE CODE").build();
            }
            return Response.ok(LabId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/updatepassword")
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
            List<LabAccount> labAccountList = labAccountBean.labAccountFindByAttribute("username", username, false);
            password = PasswordUtils.digestPassword(password);
            LabAccount labAccount = labAccountList.get(0);
            labAccount.setPassword(password);

            boolean LabId = labAccountBean.labAccountUpdate(labAccount);
            if (LabId == false) {
                return Response.ok("UNABLE TO UPDATE PASSWORD").build();
            }
            return Response.ok(LabId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Path("/all_staff")
    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response allStaff(@Context SecurityContext securityContext) {
        try {
            List<LabStaff> labStaffList = labStaffBean.labStaffGetAll(false);
            GenericEntity<List<LabStaff>> allLabStaff = new GenericEntity<List<LabStaff>>(labStaffList) {
            };
            if (labStaffList == null) {
                LOGGER.info("STAFF..........EMPTY LIST");
                return Response.ok("EMPTY LIST").build();
            } else {
                return Response.ok(allLabStaff).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Path("/all_branches")
    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response allBranches(@Context SecurityContext securityContext) {
        try {
            List<LabBranch> labBranchList = labBranchBean.labBranchGetAll(false);
            GenericEntity<List<LabBranch>> allLabBranch = new GenericEntity<List<LabBranch>>(labBranchList) {
            };
            if (labBranchList == null) {
                LOGGER.info("BRANCHES..........EMPTY LIST");
                return Response.ok("EMPTY LIST").build();
            } else {
                return Response.ok(allLabBranch).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/update_lab_branch")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response updateLabBranch(
            @FormParam("location") String location,
            @FormParam("region") String region,
            @FormParam("contact") String contact,
            @FormParam("branchname") String branchname,
            @FormParam("branchId") String branchId,
            @FormParam("email") String email) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(email)
                    && StringUtils.isNotBlank(location) && StringUtils.isNotBlank(region)
                    && StringUtils.isNotBlank(contact) && StringUtils.isNotBlank(branchname)
                    && StringUtils.isNotBlank(branchId)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            List<LabBranch> labBranchList = labBranchBean.labBranchFindByAttribute("branchId", branchId, false);
            LabBranch labBranch = labBranchList.get(0);

            labBranch.setBranchName(branchname);
            labBranch.setContact(contact);
            labBranch.setEmail(email);
            labBranch.setLocation(location);
            labBranch.setRegion(region);

            boolean LabId = labBranchBean.labBranchUpdate(labBranch);
            if (LabId == false) {
                return Response.ok("UNABLE TO UPDATE LAB BRANCH").build();
            }
            return Response.ok(LabId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/update_branch_staff")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response updateBranchStaff(
            @FormParam("fullname") String fullname,
            @FormParam("password") String password,
            @FormParam("phone") String phone,
            @FormParam("username") String username,
            @FormParam("usercode") String usercode,
            @FormParam("status") String status,
            @FormParam("staffId") String staffId,
            @FormParam("email") String email) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(fullname)
                    && StringUtils.isNotBlank(staffId) && StringUtils.isNotBlank(password)
                    && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(username)
                    && StringUtils.isNotBlank(usercode) && StringUtils.isNotBlank(status)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            List<LabStaff> labStaffList = labStaffBean.labStaffFindByAttribute("staffId", staffId, false);
            LabStaff labStaff = labStaffList.get(0);

            labStaff.setEmail(email);
            labStaff.setFullname(fullname);
            labStaff.setPassword(password);
            labStaff.setPhone(phone);
            labStaff.setUserCode(usercode);
            labStaff.setUsername(username);
            labStaff.setStatus(status);

            boolean LabId = labStaffBean.labStaffUpdate(labStaff);
            if (LabId == false) {
                return Response.ok("UNABLE TO UPDATE STAFF").build();
            }
            return Response.ok(LabId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
