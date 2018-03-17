package com.labtrans.api.restfulresources;

import com.labtrans.ejb.entities.LabBranch;
import com.labtrans.ejb.entities.LabSession;
import com.labtrans.ejb.entities.Patient;
import com.labtrans.ejb.sessionbean.LabBranchBean;
import com.labtrans.ejb.sessionbean.LabSessionBean;
import com.labtrans.ejb.sessionbean.PatientBean;
import com.labtrans.jwtfilter.JWTTokenNeeded;
import com.labtrans.util.JWTokenUtility;
import com.labtrans.util.PasswordUtils;
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
@Path("/labbranch")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@Stateless
public class LabBranchEndPoint {

    @EJB
    private LabBranchBean labBranchBean;
    private static final Logger LOGGER = Logger.getLogger(LabBranchEndPoint.class.getName());

    @POST
    @Path("/createbranch")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response create(
            @FormParam("branchName") String branchName,
            @FormParam("contact") String contact,
            @FormParam("email") String email,
            @FormParam("labcode") String labcode
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(branchName) && StringUtils.isNotBlank(contact)
                    && StringUtils.isNotBlank(email) && StringUtils.isNotBlank(labcode)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            boolean dp = checkForDuplicateRegistration(contact);
            if (dp == true) {
                return Response.ok("This Lab is already registered in the system").build();
            }
            String branchCode = "LAB-" + branchName + new Date().toString();
            LabBranch labBranch = new LabBranch();
            labBranch.setBranchCode(branchCode);
            labBranch.setLabcode(labcode);
            labBranch.setBranchName(branchName);
            labBranch.setContact(contact);
            labBranch.setEmail(email);
            labBranch.setDeleted("NO");
            labBranch.setDateCreated(new Date());

            String brId = labBranchBean.labBranchCreate(labBranch);
            if (brId == null) {
                return Response.ok("UNABLE TO CREATE ACCOUNT").build();
            }
            return Response.ok(brId).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkForDuplicateRegistration(String contact) {
        List<LabBranch> lb = labBranchBean.labBranchFindByAttribute(contact, "contact", false);
        if (!(lb.isEmpty())) {
            return true;
        } else {
            return false;
        }

    }
}
