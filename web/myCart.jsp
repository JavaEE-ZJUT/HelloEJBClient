<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="javax.naming.*, java.util.Hashtable"%>
<%@ page import="cn.xyy.ejb.*"%>

<%

    final Hashtable<String, String> jndiProperties = new Hashtable<>();
    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    try {
        ProductCartRemote cart = null;
        cart = (ProductCartRemote) session.getAttribute("cart");
        if(cart == null) {
            final Context context = new InitialContext(jndiProperties);
            ProductCartRemote hello = (ProductCartRemote) context.lookup("ejb:/HELLOEJB_war_exploded/ProductCartBeanEJB!cn.xyy.ejb.ProductCartRemote?stateful");
            session.setAttribute("cart",hello);
        } else {
            String productName = request.getParameter("product");
            String sPrice = request.getParameter("price");
            int price = 0;
            if(sPrice != null) price = Integer.parseInt(sPrice);
            cart.addProduct(productName, price);
            List<String> myProducts = cart.listProducts();
            out.println("Total Price:" + cart.totalPrice() + "<br>");
            out.println("My Products:<br>" + myProducts);
        }
    } catch (NamingException e) {
        e.printStackTrace();
    }
%>

<table border=1>
    <tr><td><a href="myCart.jsp?product=fridge&price=3000">fridge
        buy</a></td></tr>
    <tr><td><a href="myCart.jsp?product=ledtv&price=5000">ledtv
        buy</a></td></tr>
    <tr><td><a
            href="myCart.jsp?product=waterheater&price=2800">waterheater
        buy</a></td></tr>
    <tr><td><a href="myCart.jsp?product=car&price=300000">car
        buy</a></td></tr>
</table>