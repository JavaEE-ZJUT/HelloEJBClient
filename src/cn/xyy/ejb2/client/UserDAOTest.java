package cn.xyy.ejb2.client;


import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import cn.xyy.ejb2.dao.UserDAORemote;
import cn.xyy.ejb2.User;

public class UserDAOTest {
    private static UserDAORemote lookupRemoteStatelessEjbBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        final String appName = "";
        final String moduleName = "HELLOEJB_war_exploded";
        final String distinctName = "";
        final String beanName = "UserDAOEJB";
        final String viewClassName = UserDAORemote.class.getName();
        final String namespace = "ejb:" + appName + "/" + moduleName
                + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful";
        System.out.println(namespace);
        return (UserDAORemote) context.lookup(namespace);
    }

    public static void main(String[] args) {
        try{
            UserDAORemote userdao = lookupRemoteStatelessEjbBean();
            System.out.println(userdao);
            List<User> userlist = userdao.select("select u from User u");
            System.out.println("userlist=" + userlist);
            User u = (User)userlist.get(0);
            System.out.println("username=" + u.getUsername());
            System.out.println("age=" + u.getAge());
        }catch(NamingException e){
            e.printStackTrace();
        }
    }
}
