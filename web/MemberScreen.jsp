<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Welcome</title>
        <style>
            table.member-details{
                border-collapse: collapse;
            }
            table.member-details td, table.member-details th{
                padding: 6px;
                border: 1px solid #999;
            }
        </style>
    </head>
    <body>
        <h1>Club member Data</h1>
        <s:form action="MemberUpdate" method="post">
            <table class="member-details">
            <tr>
                <s:textfield name="member.memid"
                    label="Member ID"
                    value="%{#session['member'].memid}"/>
            </tr>
            <tr>
                <s:textfield name="member.lastname"
                    label="Last Nm"
                    value="%{#session['member'].lastname}"/>
            </tr>
            <tr>
                <s:textfield name="member.firstname"
                    label="First Nm"
                    value="%{#session['member'].firstname}"/>
            </tr>
            <tr>
                <s:textfield name="member.middlename"
                    label="Middle Nm"
                    value="%{#session['member'].middlename}"/>
            </tr>
            <tr>
                <s:textfield name="member.status"
                    label="Status"
                    value="%{#session['member'].status}"/>
            <tr>
            <tr>
                <s:textfield name="member.memdt"
                    label="Member Date"
                    value="%{#session['member'].memdt}"/>
            </tr>
            <tr>
                <s:textfield name="member.password"
                    label="Password"
                    value="%{#session['member'].password}"/>
            </tr>
            <tr>
                <s:submit value="Update Member data"/>
            </tr>
            </table>
        </s:form>
        <br>
        ${msg}
         <br>
         <br>View Transaction History From:<br>
            <s:form action="ShowPurchases" method="post" >
                <table>
                        <s:textfield name="month" label="Month"/>
                        <s:textfield name="day" label="Day"/>
                        <s:textfield name="year" label="year"/>
                </table>
                <br>
                <s:submit align="left" value="View Transactions"/>
            </s:form><br>
         <br><br>
         <a href="/ClubDBStruts">Back to the Login Screen</a>
       </body>
</html>