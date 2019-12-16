package cn.xyy.ejb2.client;


import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import cn.xyy.ejb2.User;
import cn.xyy.ejb2.Department;
import cn.xyy.ejb2.dao.OneToManyDAORemote;

public class OneToManyTest {

    private static OneToManyDAORemote lookupRemoteStatelessEjbBean() throws NamingException {
        // /HELLOEJB_war_exploded/OneToManyDAO!cn.xyy.ejb2.dao.OneToManyDAORemote
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        final Context context = new InitialContext(jndiProperties);
        final String appName = "";

        final String moduleName = "HELLOEJB_war_exploded";

        final String distinctName = "";

        final String beanName = "OneToManyDAO";

        final String viewClassName = OneToManyDAORemote.class.getName();

        final String namespace = "ejb:" + appName + "/" + moduleName
                + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful";

        System.out.println(namespace);

        return (OneToManyDAORemote) context.lookup(namespace);

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try{
            OneToManyDAORemote o2mdao = lookupRemoteStatelessEjbBean();
            Department department=new Department();
            department.setDepartmentname("行政部");
            User u1=new User();
            u1.setUsername("张小平");
            User u2=new User();
            u2.setUsername("张小青");
            department.addNewUser(u1);
            department.addNewUser(u2);
            o2mdao.inserDepartment(department);
        }catch(NamingException e){
            e.printStackTrace();
        }
    }
}