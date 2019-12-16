package cn.xyy.ejb3.client;

import cn.xyy.ejb2.Department;
import cn.xyy.ejb2.User;
import cn.xyy.ejb2.dao.OneToManyDAORemote;
import cn.xyy.ejb3.Address;
import cn.xyy.ejb3.Employee;
import cn.xyy.ejb3.dao.OneToOneDAORemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class OneToOneDAOTest {
    public static void main(String[] args) {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            final Context context = new InitialContext(jndiProperties);
            OneToOneDAORemote o2odao = (OneToOneDAORemote) context.lookup("ejb:/HELLOEJB_war_exploded/OneToOneDAO!cn.xyy.ejb3.dao.OneToOneDAORemote?stateful");
            // 构造address对象
            Address address = new Address();
            address.setAddrLocation("new");
            // 构造employee对象
            Employee employee = new Employee();
            employee.setEmployeeName("new");
            // 将employee作为属性赋值给address对象
            address.setEmployee(employee);
            // 执行数据库操作
            o2odao.insertAddress(address);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

