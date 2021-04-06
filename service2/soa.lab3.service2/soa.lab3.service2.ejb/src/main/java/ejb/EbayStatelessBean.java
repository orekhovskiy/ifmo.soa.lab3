package ejb;

import ejb.EbayStatelessBeanRemote;
import models.ProductsList;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Stateless(name = "EbayStatelessBean")
public class EbayStatelessBean implements EbayStatelessBeanRemote {
    private final Client client = ClientBuilder.newBuilder().build();
    private final String REST_URI = System.getenv("soa_service1_url");
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public String sayHello() {
        return "Hello";
    }

     @Override
    public String filterProducts(String key, String value) {
        Response response = client.target(REST_URI + "?" + key + "=" + value)
                .request(MediaType.APPLICATION_XML)
                .get();
        String responsePayload = response.readEntity(String.class);
        int status = response.getStatus();
        return  String.join(" ", Integer.toString(status), responsePayload);

        /*if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {

            logger.info("--OK--");
            logger.info(responsePayload);
            return responsePayload;
        } else {
            String responsePayload = response.readEntity(String.class);
            logger.info("--ERROR--");
            logger.info(responsePayload);
            return responsePayload;
        }*/
    }
}
