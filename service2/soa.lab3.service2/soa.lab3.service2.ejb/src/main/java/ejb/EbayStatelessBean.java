package ejb;

import ejb.EbayStatelessBeanRemote;
import models.ProductsList;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless(name = "EbayStatelessBean")
public class EbayStatelessBean implements EbayStatelessBeanRemote {
    private final Client client = ClientBuilder.newBuilder().build();
    private final String REST_URI = System.getenv("soa_service1_url");

    @Override
    public String sayHello() {
        return "Hello";
    }

     @Override
    public Response filterProducts(String key, String value) {
        Response response = client.target(REST_URI + "?" + key + "=" + value)
                .request(MediaType.APPLICATION_XML)
                .get();
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            return Response.status(response.getStatus())
                    .entity(response.readEntity(ProductsList.class))
                    .build();
        } else {
            return response;
        }
    }
}
