package ejb;

import ejb.EbayStatelessBeanRemote;
import models.ProductsList;
import javax.ejb.Stateless;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

@Stateless(name = "EbayStatelessBean")
public class EbayStatelessBean implements EbayStatelessBeanRemote {
    private Client client ;
    private final String REST_URI = System.getenv("soa_lab3_service1_url");
    private final Logger logger = Logger.getLogger(getClass().getName());

    public void init()
            throws KeyManagementException, NoSuchAlgorithmException {
        TrustManager[] noopTrustManager = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
        SSLContext sc = SSLContext.getInstance("ssl");
        sc.init(null, noopTrustManager, null);
        this.client = ClientBuilder.newBuilder().sslContext(sc).build();
    }

    @Override
    public String sayHello() {
        return "Hello";
    }

     @Override
    public String filterProducts(String key, String value)
             throws NoSuchAlgorithmException, KeyManagementException {
        this.init();
        Response response = client.target(REST_URI + "?" + key + "=" + value)
                .request(MediaType.APPLICATION_XML)
                .get();
        String responsePayload = response.readEntity(String.class);
        int status = response.getStatus();
        return  String.join(" ", Integer.toString(status), responsePayload);
    }
}
