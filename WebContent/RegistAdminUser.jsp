<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>総務部登録</title>
<link rel="stylesheet" href="common/css/style.css">
<script type="text/javascript" src="common/JS/func.js"></script>

</head>
<body>
    <div class="header">
        <span class="big_title">H</span>eets <span class="big_title">C</span>orporation
    </div>
    <div class="menu">
        <div class="main_frame">
            <p>総務部登録</p>
        </div>
    </div>

    <div class="main_wrapper">
        <div class="main_admin">
            総務部として登録<br> ユーザーID(4文字以上)、パスワード(8文字以上)を半角英数で入力
        </div>

        <form action="RegistAdminUser" method="POST"
            onsubmit="return chkUser()">
            <div class="regist_table">
                <table>
                    <tr>
                        <td>ユーザーID</td>
                        <td>：</td>
                        <td><input type="text" name="userId" id="user_id"></td>
                        <td>
                            <div id="comment_user_id">半角英数4文字以上24文字以下で入力</div>
                        </td>
                    </tr>
                    <tr>
                        <td>パスワード</td>
                        <td>：</td>
                        <td><input type="password" name="password" id="password"></td>
                        <td><div id="comment_password">半角英数8文字以上32文字以下で入力</div></td>
                    </tr>
                    <tr>
                        <td>もう一度</td>
                        <td>：</td>
                        <td><input type="password" name="confirmation"
                            id="confirmation"></td>
                        <td>
                            <div id="comment_confirmation">パスワードが違います</div>
                        </td>
                    </tr>
                </table>
                <div class="tomenu_button">
                    <div class="admin_user_button">
                        <input type="submit" class="admin_user_submit" value="登録する">

                        <a href="Menu.jsp"> <input type="button" class="clear_button"
                            value="キャンセル">
                        </a>
                    </div>
                </div>
            </div>
        </form>
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