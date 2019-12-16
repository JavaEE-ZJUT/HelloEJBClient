package cn.xyy.ejb4.client;

import cn.xyy.ejb3.Address;
import cn.xyy.ejb3.Employee;
import cn.xyy.ejb3.dao.OneToOneDAORemote;
import cn.xyy.ejb4.Role;
import cn.xyy.ejb4.UserUser;
import cn.xyy.ejb4.dao.ManyToManyDAORemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class ManyToManyDAOTest {
    public static void main(String[] args) {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            final Context context = new InitialContext(jndiProperties);
            ManyToManyDAORemote m2mdao = (ManyToManyDAORemote) context.lookup("ejb:/HELLOEJB_war_exploded/ManyToManyDAO!cn.xyy.ejb4.dao.ManyToManyDAORemote?stateful");
            // 构造UserUser对象
            UserUser userUser = new UserUser();
            userUser.setUseruserNAme("newUser");
            // 构造role对象
            Role role = new Role();
            role.setRoleName("newRole");
            // 将User作为属性赋值给role对象
            userUser.addNewRole(role);
            // 执行数据库操作
            m2mdao.insertUser(userUser);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
