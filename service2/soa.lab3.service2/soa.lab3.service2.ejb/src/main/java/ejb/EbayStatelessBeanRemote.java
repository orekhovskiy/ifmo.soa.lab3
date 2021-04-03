package ejb;

import models.ProductsList;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;

@Remote
public interface EbayStatelessBeanRemote {
    public String sayHello();
    public Response filterProducts(String key, String value);
}
