<%@ page import="entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String employeeCode = (String) session.getAttribute("employeeCode");
    if (employeeCode == null) {
        response.sendRedirect("AttendanceMenu.jsp");
    } else {
%>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>エラー画面</title>
<link rel="stylesheet" href="common/css/style.css">
</head>
<body>
    <div class="header">
        <span class="big_title">H</span>eets <span class="big_title">C</span>orporation
    </div>

    <div class="menu">
        <div class="main_frame">
            <p>エラーが発生しました</p>
        </div>
    </div>

    <div class="main_wrapper">
        <div class="main_admin">
            <p>エラーが発生しました<br> 正常に処理を実行できませんでした</p>
        </div>
    </div>

    <div class="a_logout_button">
        <a href="AttendanceLogin.jsp">
            <button class="display_button">メニューに戻る</button>
        </a>
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