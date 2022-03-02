<%@page import="entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String employeeCode = (String) session.getAttribute("employeeCode");
    if (employeeCode != null) {
        response.sendRedirect("attendance_menu.jsp");
    } else {
%>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>従業員用ログイン画面</title>
<link rel="stylesheet" href="common/css/style.css">
</head>
<body>
    <div class="header">
        <span class="big_title">H</span>eets <span class="big_title">C</span>orporation
    </div>
    <div class="menu">
        <div class="main_frame">
            <p>従業員用ログイン画面</p>
        </div>
    </div>

    <div class="main_wrapper">
        <form action="AttendanceLoginChk" method="post">
            <div class="regist_table">
                <table>
                    <tr>
                        <td>従業員コード</td>
                        <td>：</td>
                        <td><input type="text" name="employeeCode"></td>
                    </tr>
                    <tr>
                        <td>パスワード</td>
                        <td>：</td>
                        <td><input type="password" name="password"></td>
                    </tr>
                </table>
            </div>
            <div class="admin_user_button">
                <input type="submit" class="login_button" value="ログイン"> <input
                    type="reset" class="clear_button">
            </div>

        </form>
        <div class="admin_user_button">
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
                <td>Heets 株式会社</td>
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