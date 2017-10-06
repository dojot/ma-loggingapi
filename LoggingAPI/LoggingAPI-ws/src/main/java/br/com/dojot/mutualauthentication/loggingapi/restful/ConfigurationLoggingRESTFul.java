package br.com.dojot.mutualauthentication.loggingapi.restful;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dojot.mutualauthentication.loggingapi.beans.to.ConfigurationLoggingTO;
import br.com.dojot.mutualauthentication.loggingapi.facade.api.LoggingFacade;

@Named
@Path("/configuration")
public class ConfigurationLoggingRESTFul {

    @EJB
    private LoggingFacade loggingFacade;

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchTO() {
        List<ConfigurationLoggingTO> out = loggingFacade.searchConfigurationLoggingTO();
        return Response.status(200).entity(out).build();
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ConfigurationLoggingTO to) {
        ConfigurationLoggingTO out = loggingFacade.updateConfigurationLoggingTO(to);
        return Response.status(200).entity(out).build();        
    }
}
