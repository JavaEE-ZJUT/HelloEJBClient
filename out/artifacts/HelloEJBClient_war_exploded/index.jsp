<%--
  Created by IntelliJ IDEA.
  User: xyy
  Date: 2019/12/14
  Time: 上午10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="javax.naming.*, java.util.Hashtable"%>
<%@ page import="cn.xyy.ejb.*"%>

<%

    final Hashtable<String, String> jndiProperties = new Hashtable<>();
    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    try {
      final Context context = new InitialContext(jndiProperties);
      UserServiceRemote hello = (UserServiceRemote) context.lookup("ejb:/HELLOEJB_war_exploded/UserServiceEJB!cn.xyy.ejb.UserServiceRemote");
      if (hello.login("xyy", "xyy")) {
        out.print("login ok!");
      } else
        out.print("login failed");
    } catch (NamingException e) {
      e.printStackTrace();
    }
%>