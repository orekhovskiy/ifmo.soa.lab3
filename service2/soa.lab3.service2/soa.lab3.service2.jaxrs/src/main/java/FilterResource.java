import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/filter")
public class FilterResource {

    @GET
    @Path("/manufacture-cost/{manufacture-cost}")
    @Produces("application/xml")
    public Response getFilteredByManufactureCost(@PathParam("manufacture-cost") String cost) {
        return null;
    }

    @GET
    @Path("/unit-of-measure/{unit-of-measure}")
    @Produces("application/xml")
    public Response getFilteredByUnitOfMeasure(@PathParam("unit-of-measure") String unitOfMeasure) {
        return null;
    }
}