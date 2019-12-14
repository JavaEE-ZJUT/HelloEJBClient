package cn.xyy.ejb;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class LoginClient {
    public static void main(String[] args) {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            final Context context = new InitialContext(jndiProperties);
            UserServiceRemote hello = (UserServiceRemote) context.lookup("ejb:/HELLOEJB_war_exploded/UserServiceEJB!cn.xyy.ejb.UserServiceRemote");
            System.out.println(hello.login("xyy", "xyy"));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}