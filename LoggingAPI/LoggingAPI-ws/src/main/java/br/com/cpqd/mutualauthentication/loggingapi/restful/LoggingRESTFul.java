package br.com.cpqd.mutualauthentication.loggingapi.restful;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cpqd.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingGeneratorTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingTO;
import br.com.cpqd.mutualauthentication.loggingapi.facade.api.LoggingFacade;

@Named
@Path("/logging")
public class LoggingRESTFul {

    @EJB
    private LoggingFacade loggingFacade;

    @POST
    @Path("/filter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response filter(FiltersTO filters) {
        List<LoggingTO> out = loggingFacade.searchIndexedLogFilters(filters);
        return Response.status(200).entity(out).build();
    }
    
    @POST
    @Path("/logGenerator")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logGenerator(LoggingGeneratorTO log) {
        loggingFacade.logGenerator(log);
        return Response.status(200).entity(log).build();
    }
    
    

}
