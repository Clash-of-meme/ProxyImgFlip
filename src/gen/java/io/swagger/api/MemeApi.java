package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.MemeApiService;
import io.swagger.api.factories.MemeApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.MemeReturn;
import io.swagger.model.MemePattern;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/meme")
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
@io.swagger.annotations.Api(description = "the meme API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-27T10:15:06.921Z")
public class MemeApi  {
   private final MemeApiService delegate = MemeApiServiceFactory.getMemeApi();

    @GET
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère sur le site ImgFlip l'URL des 100 meme les plus populaire durant les 30 derniers jours.  ", response = MemeReturn.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = MemeReturn.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = MemeReturn.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = MemeReturn.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "ImgFlip n'a pas répondu.", response = MemeReturn.class, responseContainer = "List") })
    public Response memeGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.memeGet(securityContext);
    }
    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On crée un meme sur ImgFlip ", response = MemeReturn.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response", response = MemeReturn.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = MemeReturn.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = MemeReturn.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "L'id du meme est introuvable.", response = MemeReturn.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "ImgFlip n'a pas répondu", response = MemeReturn.class) })
    public Response memePost(@ApiParam(value = "Meme que l'on veut créer" ,required=true) MemePattern id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.memePost(id,securityContext);
    }
}
