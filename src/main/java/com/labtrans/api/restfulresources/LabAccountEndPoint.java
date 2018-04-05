package com.labtrans.api.restfulresources;

import com.labtrans.dto.LabBranchDTO;
import com.labtrans.dto.LabStaffDTO;
import com.labtrans.ejb.entities.LabAccount;
import com.labtrans.ejb.entities.LabBranch;
import com.labtrans.ejb.entities.LabStaff;
import com.labtrans.ejb.entities.Test;
import com.labtrans.ejb.sessionbean.LabAccountBean;
import com.labtrans.ejb.sessionbean.LabBranchBean;
import com.labtrans.ejb.sessionbean.LabStaffBean;
import com.labtrans.ejb.sessionbean.TestBean;
import com.labtrans.jwtfilter.JWTTokenNeeded;
import com.labtrans.util.JWTokenUtility;
import com.labtrans.util.PasswordUtils;
import com.labtrans.util.RandomStringGenerator;
import java.security.Principal;
import java.util.ArrayList;
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
    @EJB
    private TestBean testBean;

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

            //make username unique
            List<LabAccount> labAccountchkList = labAccountBean.labAccountFindByAttribute("username", username, false);
            if (!(labAccountchkList.isEmpty())) {
                return Response.ok("CHANGE USERNAME").build();
            }
            String labcode = "LAB-" + RandomStringGenerator.randomString(5);
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
            return Response.ok("ACCOUNT CREATED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/create_test")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response createTest(
            @FormParam("testName") String testName,
            @FormParam("testDescription") String testDescription,
            @FormParam("branchCode") String branchCode) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(testName) && StringUtils.isNotBlank(testDescription) && StringUtils.isNotBlank(branchCode)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }

            //get lab branch
            List<LabBranch> labBranchList = labBranchBean.labBranchFindByAttribute("branchCode", branchCode, false);
            if (labBranchList.isEmpty()) {
                return Response.ok("UNKNOWN BRANCH").build();
            }
            LabBranch labBranch = labBranchList.get(0);
            //make test name unique
            List<Test> tests = testBean.testFindByAttribute("testName", testName, true);
            if (!(tests.isEmpty())) {
                return Response.ok("CHANGE TEST NAME").build();
            }

            Test test = new Test();
            test.setCreatedBy(testName);
            test.setDateCreated(new Date());
            test.setDeleted(testName);
            test.setTestCode(testName);
            test.setTestDescription(testDescription);
            test.setTestId(testName);
            test.setTestName(testName);

            String testId = testBean.testCreate(test);
            if (testId == null) {
                return Response.ok("UNABLE TO CREATE STAFF").build();
            }
            return Response.ok("STAFF CREATED SUCCESSFULLY").build();
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
            @FormParam("branchCode") String branchCode,
            @FormParam("username") String username,
            @FormParam("usercode") String usercode,
            @FormParam("status") String status,
            @FormParam("email") String email
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(fullname)
                    && StringUtils.isNotBlank(branchCode) && StringUtils.isNotBlank(password)
                    && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(username)
                    && StringUtils.isNotBlank(usercode) && StringUtils.isNotBlank(status)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }

            //get lab branch
            List<LabBranch> labBranchList = labBranchBean.labBranchFindByAttribute("branchCode", branchCode, false);
            if (labBranchList.isEmpty()) {
                return Response.ok("UNKNOWN BRANCH").build();
            }
            LabBranch labBranch = labBranchList.get(0);
            //make username unique
            List<LabStaff> labStaffs = labStaffBean.labStaffFindByAttribute("username", username, false);
            if (!(labStaffs.isEmpty())) {
                return Response.ok("CHANGE USERNAME").build();
            }

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
            return Response.ok("STAFF CREATED SUCCESSFULLY").build();
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

            //get lab account
            Principal principal = securityContext.getUserPrincipal();
            String labcode = principal.getName();
            List<LabAccount> labAccountList = labAccountBean.labAccountFindByAttribute("labcode", labcode, false);
            LabAccount labAccount = labAccountList.get(0);
            String branchcode = "LAB-" + labAccount.getLabcode() + "-" + RandomStringGenerator.randomString(5);
            LOGGER.info("labAccount ........" + labAccount.toString());

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
            return Response.ok("LAB BRANCH CREATED SUCCESSFULLY").build();
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
            return Response.ok("CODE GENERATED SUCCESSFULLY").build();
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
            return Response.ok("PASSWORD UPDATED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Path("/all_staff")
    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response allStaff() {
        try {
            List<LabStaff> labStaffList = labStaffBean.labStaffGetAll(false);
            List<LabStaffDTO> labStaffDTOList = new ArrayList<>();
            for (LabStaff labStaff : labStaffList) {
                LabStaffDTO dto = new LabStaffDTO();
                dto.setDateCreated(labStaff.getDateCreated());
                dto.setEmail(labStaff.getEmail());
                dto.setFullname(labStaff.getFullname());
                dto.setPhone(labStaff.getPhone());
                dto.setStatus(labStaff.getStatus());
                dto.setUserCode(labStaff.getUserCode());
                dto.setUsername(labStaff.getUsername());
                labStaffDTOList.add(dto);
            }

            GenericEntity<List<LabStaffDTO>> allLabStaffDTO = new GenericEntity<List<LabStaffDTO>>(labStaffDTOList) {
            };
            if (labStaffList == null) {
                LOGGER.info("STAFF..........EMPTY LIST");
                return Response.ok("EMPTY LIST").build();
            } else {
                LOGGER.info("..........allLabStaff........" + allLabStaffDTO);
                return Response.ok(allLabStaffDTO).build();
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
    public Response allBranches() {
        try {
            List<LabBranch> labBranchList = labBranchBean.labBranchGetAll(false);
            List<LabBranchDTO> labBranchDTOList = new ArrayList<>();
            for (LabBranch labBranch : labBranchList) {
                LabBranchDTO dto = new LabBranchDTO();
                dto.setBranchCode(labBranch.getBranchCode());
                dto.setBranchId(labBranch.getBranchId());
                dto.setBranchName(labBranch.getBranchName());
                dto.setContact(labBranch.getContact());
                dto.setDateCreated(labBranch.getDateCreated());
                dto.setDeleted(labBranch.getDeleted());
                dto.setEmail(labBranch.getEmail());
                dto.setLabcode(labBranch.getLabcode());
                dto.setLabcode(labBranch.getLabcode());
                dto.setLocation(labBranch.getLocation());
                dto.setRegion(labBranch.getRegion());
                labBranchDTOList.add(dto);
            }

            GenericEntity<List<LabBranchDTO>> allLabBranchDTO = new GenericEntity<List<LabBranchDTO>>(labBranchDTOList) {
            };
            if (labBranchList == null) {
                return Response.ok("EMPTY LIST").build();
            } else {
                return Response.ok(allLabBranchDTO).build();
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
            return Response.ok("LAB BRANCH UPDATED SUCCESSFULLY").build();
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
            return Response.ok("STAFF UPDATED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/delete_lab_branch")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response deleteLabBranch(
            @FormParam("branchId") String branchId) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(branchId)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            List<LabBranch> labBranchList = labBranchBean.labBranchFindByAttribute("branchId", branchId, false);
            LabBranch labBranch = labBranchList.get(0);

            labBranch.setDeleted("NO");
            boolean LabId = labBranchBean.labBranchDelete(labBranch, false);
            if (LabId == false) {
                return Response.ok("UNABLE TO DELETE LAB BRANCH").build();
            }
            return Response.ok("BRANCH DELETED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/delete_branch_staff")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @JWTTokenNeeded
    public Response deleteBranchStaff(
            @FormParam("staffId") String staffId) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(staffId)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            List<LabStaff> labStaffList = labStaffBean.labStaffFindByAttribute("staffId", staffId, false);
            LabStaff labStaff = labStaffList.get(0);

            labStaff.setDeleted("NO");
            boolean LabId = labStaffBean.labStaffDelete(labStaff, false);
            if (LabId == false) {
                return Response.ok("UNABLE TO DELETE STAFF").build();
            }
            return Response.ok("STAFF DELETED SUCCESSFULLY").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
