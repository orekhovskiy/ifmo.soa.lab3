package ejb;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Remote
public interface EbayStatelessBeanRemote {
    String sayHello();
    String filterProducts(String key, String value) throws NoSuchAlgorithmException, KeyManagementException;
}
