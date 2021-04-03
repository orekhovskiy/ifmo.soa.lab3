package ejb;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;

@Remote
public interface EbayStatelessBeanRemote {
    String sayHello();
    Response filterProducts(String key, String value);
}
