package ejb;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;

@Remote
public interface EbayStatelessBeanRemote {
    String sayHello();
    String filterProducts(String key, String value);
}
