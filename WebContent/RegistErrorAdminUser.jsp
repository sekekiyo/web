<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="entity.Employee"%>
<%
    if (session.getAttribute("loginUserId") == null) {
        response.sendRedirect("Login.jsp");
    } else {
%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>管理者権限登録エラー</title>
<link rel="stylesheet" href="common/css/style.css">
</head>
<body>
    <div class="header">
        <span class="big_title">H</span>eets <span class="big_title">C</span>orporation
    </div>
    <div class="menu">
        <div class="main_frame">
            <p>⚠すでに同じ登録内容が存在しています。</p>
        </div>
    </div>
    <div class="main_wrapper">

        <div class="logout_button">
            <a href="regist_adminuser.jsp">
                <button class="display_button">もう一度管理者権限を付与をする</button>
            </a>
        </div>

        <div class="logout_button">
            <a href="menu.jsp">
                <button class="display_button">メニューへ戻る</button>
            </a>
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
<%}%>