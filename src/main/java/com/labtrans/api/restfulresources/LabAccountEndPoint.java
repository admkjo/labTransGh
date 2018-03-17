package com.labtrans.api.restfulresources;

import com.labtrans.ejb.entities.LabAccount;
import com.labtrans.ejb.sessionbean.LabAccountBean;
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
@Path("/labaccount")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@Stateless
public class LabAccountEndPoint {

    @EJB
    private LabAccountBean labAccountBean;
    private static final Logger LOGGER = Logger.getLogger(LabAccountEndPoint.class.getName());

    @POST
    @Path("/createaccount")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response create(
            @FormParam("nameOfOrginazation") String nameOfOrginazation,
            @FormParam("location") String location,
            @FormParam("region") String region,
            @FormParam("contact") String contact,
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        try {
            //check for empty inputs
            if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(nameOfOrginazation)
                    && StringUtils.isNotBlank(location) && StringUtils.isNotBlank(region)
                    && StringUtils.isNotBlank(contact) && StringUtils.isNotBlank(username)) {
            } else {
                return Response.ok("Please Complete All Fields").build();
            }
            String labcode = "LAB-" + nameOfOrginazation +new Date().toString();
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
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(
            @FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            LOGGER.info("#### login/password : " + username + "/" + password);
            // Authenticate the user using the credentials provided
            LabAccount labAccount = labAccountBean.labAccountLogin(username, password);
            if (labAccount == null) {
                return Response.status(NOT_FOUND).entity("unregistered user").build();
            }
            // Issue a token for the user
            String token = JWTokenUtility.buildJWT(username);

            // Return the token on the response
            return Response.ok("login successful").header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).entity("login failed").build();
        }
    }

 }
