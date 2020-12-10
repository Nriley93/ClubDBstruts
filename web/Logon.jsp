<%-- 
    Author     : n.riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Club Logon</title>
    </head>
    <body>
        <h1>Welcome to the Club - Please Login</h1>
<!--        <form action="ClubLogon" method="post">
            <table>
                <tr>
                    <td>User ID:</td>
                    <td><input type="text" name="userid" id="userid"
                            value="">
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" id="password">
                    </td>
                </tr>
            </table>
            <input type="submit" value="Login">
        </form>-->
        <s:form action="ClubLogon" method="post">
            <s:textfield name="userid" label="User Id" required="true"/>
            <s:password name="pattempt" label="Password" required="true"/>
            <s:submit/>
        </s:form>
        <br>
        ${msg}
    </body>
</html>
