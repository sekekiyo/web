<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Heets Corporation</title>
<link rel="stylesheet" href="common/css/style.css">
</head>
<body>
    <div class="header">
        <span class="big_title">H</span>eets <span class="big_title">C</span>orporation
    </div>

    <div class= "menu">
        <div class= "main_frame">
            <p>TOPページ</p>
        </div>
    </div>
    <div class="i_main_wrapper">
        <div class="employee_button">
            <input type="button" class="regist_button" onclick="location.href='Login.jsp'" value="管理者用メニュー">
            <input type="button" onclick="location.href='AttendanceLogin.jsp'" value="従業員用メニュー">
        </div>
    </div>

    <div class="footer_top">
        <table class="table_format" >
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