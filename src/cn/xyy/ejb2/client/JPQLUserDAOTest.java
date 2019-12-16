package cn.xyy.ejb2.client;


import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import cn.xyy.ejb2.dao.jpql.JPQLUserDAORemote;
import cn.xyy.ejb2.User;

public class JPQLUserDAOTest {
    private static JPQLUserDAORemote lookupRemoteStatefulEjbBean() throws NamingException {
        // /HELLOEJB_war_exploded/JPQLUserDAO!cn.xyy.ejb2.dao.jpql.JPQLUserDAORemote
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        final Context context = new InitialContext(jndiProperties);
        final String appName = "";

        final String moduleName = "HELLOEJB_war_exploded";

        final String distinctName = "";

        final String beanName = "JPQLUserDAO";

        final String viewClassName = JPQLUserDAORemote.class.getName();

        final String namespace = "ejb:" + appName + "/" + moduleName
                + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful";

        System.out.println(namespace);

        return (JPQLUserDAORemote) context.lookup(namespace);

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try{
            JPQLUserDAORemote userdao = lookupRemoteStatefulEjbBean();
            System.out.println(userdao);
            User user=new User();
            user.setAge(6);
            user.setUsername("new6");
            user.setUserpwd("new6");

            //插入Insert
            if(userdao.insert(user))
                System.out.println("new record has been inserted!");
            else
                System.out.println("insert failed!");
            //查询Select
            user=null;
            user=userdao.selectById(1);
            System.out.println("new username="+user.getUsername());
            //更新update
            user.setUsername("newname");
            userdao.update(user);
            //删除delete
            userdao.deleteById(1);

        }catch(NamingException e){
            e.printStackTrace();
        }
    }
}