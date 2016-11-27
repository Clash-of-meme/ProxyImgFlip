package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.MemeReturn;
import io.swagger.model.MemePattern;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-27T10:15:06.921Z")
public abstract class MemeApiService {
    public abstract Response memeGet(SecurityContext securityContext) throws NotFoundException;
    public abstract Response memePost(MemePattern id,SecurityContext securityContext) throws NotFoundException;
}
