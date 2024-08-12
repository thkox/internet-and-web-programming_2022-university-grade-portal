<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv = "refresh" content = "1; url = SessionServlet" />
<title>Please wait</title>
</head>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
<body>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.
    char ch;

    if((session.getAttribute("username")) == null)
    {
        response.sendRedirect("../index.jsp");
    }
    else
    {
        String registNumber = (session.getAttribute("username")).toString();    //temp variable to keep username untouchable
        ch = registNumber.charAt(0);    //Keep the first char which is the role

        System.out.println("Im here");
    }


%>
<h1>Please Wait...</h1>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</body>

</html>