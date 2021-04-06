package soa;

import ejb.EbayStatelessBeanRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Locale;
import java.util.Properties;

@Path("/filter")
public class FilterResource {
    private static final String SERVICE2_EJB_JNDI = "java:global/SOA3/EbayStatelessBean!ejb.EbayStatelessBeanRemote";

    private static EbayStatelessBeanRemote getStatelessBean() throws NamingException {
        Properties environment = new Properties();
        InitialContext ejbRemoteContext = new InitialContext(environment);
        String ejb_jndi = System.getenv("SOA_EJB_JNDI");
        if (ejb_jndi == null || ejb_jndi.isEmpty()) ejb_jndi = SERVICE2_EJB_JNDI;
        return (EbayStatelessBeanRemote) ejbRemoteContext.lookup(ejb_jndi);
    }

    @GET
    @Path("hello")
    public Response getHello() throws NamingException {
        return Response.status(Response.Status.OK).entity(getStatelessBean().sayHello()).build();
    }

    @GET
    @Path("/manufacture-cost/{manufacture-cost}")
    @Produces("application/xml")
    public Response getFilteredByManufactureCost(@PathParam("manufacture-cost") String cost)
            throws NamingException {
       return generateResponse(getStatelessBean().filterProducts("manufacture-cost", cost));

    }

    @GET
    @Path("/unit-of-measure/{unit-of-measure}")
    @Produces("application/xml")
    public Response getFilteredByUnitOfMeasure(@PathParam("unit-of-measure") String unitOfMeasure)
            throws NamingException {
        return generateResponse(getStatelessBean().filterProducts("unit-of-measure", unitOfMeasure));
    }

    private Response generateResponse(String rmiResponse) {
        int status = Integer.parseInt(rmiResponse.substring(0, 3));
        String body = rmiResponse.substring(4);
        return Response.status(status).entity(body).build();
    }
}