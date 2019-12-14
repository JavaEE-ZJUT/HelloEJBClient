package test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class TestEJBClient {

//    private static Hello lookupRemoteStatelessEjbBean()
//            throws NamingException {
//        final Hashtable jndiProperties = new Hashtable();
//        jndiProperties.put(Context.URL_PKG_PREFIXES,
//                "org.jboss.ejb.client.naming");
//        final Context context = new InitialContext(jndiProperties);
//        final String appName = "";
//        final String moduleName = "HELLOEJB_war_exploded";
//        final String distinctName = "";
//        final String beanName = "TestSessionEJB";
//        final String viewClassName = Hello.class.getName();
//        final String namespace = "ejb:" + appName + "/" + moduleName
//                + "/" + distinctName + "/" + beanName + "!" + viewClassName;
//        return (Hello) context.lookup(namespace);
//    }
//
//    public static void main(String[] args) {
//        try {
//            Hello hello = lookupRemoteStatelessEjbBean();
//            System.out.println(hello);
//            String b1 = hello.say();
//            System.out.println(b1);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            final Context context = new InitialContext(jndiProperties);
            Hello hello = (Hello) context.lookup("ejb:/HELLOEJB_war_exploded/TestRemoteEJB!test.Hello");
            System.out.println(hello.say());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}