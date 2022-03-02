

<%@page import="entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String employeeCode = (String) session.getAttribute("employeeCode");
    if (employeeCode == null) {
        response.sendRedirect("AttendanceLogin.jsp");
    } else {
%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>メニュー画面</title>
<link rel="stylesheet" href="common/css/style.css">
</head>
<body>
    <div class="header">
        <span class="big_title">H</span>eets <span class="big_title">C</span>orporation
    </div>
    <div class="menu">
        <div class="main_frame">
            <p>メニュー画面</p>
        </div>
    </div>
    <div class="main_wrapper">
        <div class="employee_button">
            <a href="AttendanceViewTimecard" class="regist_button">
                <button class="a_display_button">打刻</button>
            </a> <a href="attendance_select_timesheet.jsp" class="regist_button">
                <button class="a_display_button">月次報告</button>
            </a>
        </div>

        <div class="logout_button">
            <form action="AttendanceLogoutChk" method="post">
                <input type="submit" class="a_display_button" value="ログアウト">
            </form>
        </div>
        <div class="display_top">
            <input type="button" class="display_button"
                onclick="location.href='SendIndex'" value="Topページ">
        </div>
    </div>
    <div class="footer_top">
        <table class="table_format">
            <tr>
                <th>管理者情報</th>
            </tr>
            <tr>
                <td class="cel">会社名</td>
                <td>&nbsp;</td>
                <td>HEETS 株式会社</td>
            </tr>
            <tr>
                <td class="cel">Tell</td>
                <td>&nbsp;</td>
                <td>03-0000-0000</td>
            <tr>
                <td class="cel">Email</td>
                <td>&nbsp;</td>
                <td>heets.tbc@gmail.com</td>
            </tr>
        </table>
    </div>

    <div class="footer_design">
        <footer>
            <small>© 2022 HEETS corporation</small>
        </footer>
    </div>
</body>
</html>
<%
    }
%>