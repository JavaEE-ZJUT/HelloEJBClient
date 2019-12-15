package cn.xyy.ejb.lifecycle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.security.Security;
import java.util.Hashtable;

import cn.xyy.ejb.UserServiceRemote;
import cn.xyy.ejb.lifecycle.*;

public class LifeCycleClient {
    public static void main(String[] args) {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            final Context context = new InitialContext(jndiProperties);
            LifeCycleRemote hello = (LifeCycleRemote) context.lookup("ejb:/HELLOEJB_war_exploded/LifeCycleEJB!cn.xyy.ejb.lifecycle.LifeCycleRemote?stateful");
            System.out.println(hello.hello("zjut"));
            hello.removeSession();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}