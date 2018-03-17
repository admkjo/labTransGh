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
public class TestEndPoint {
}