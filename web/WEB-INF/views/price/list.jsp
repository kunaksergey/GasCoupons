<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gas list</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="/resources/js/price.js"></script>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div id="priceList"></div>
<div id="open-popup">
<a href="#" class="open-popup" name="open">Открыть</a>
</div>
<div id="close-popup">
<div style="position: absolute; z-index: 1000; top: 183px; left: 135px;">
    <div class="login-popup" name="signin-signup">
        <div class="popup">
            <a href="#" class="close-popup" name="close" title="Закрыть">Закрыть</a>
            <div class="container-form">
                <div class="form-item log-in">
                    <div class="wrap-white-block">
                        <ul>
                            <li><a href="#">
                                <span name="signin-block">Вход</span>

                            </a></li>
                            <li><a href="#" class="btn xhr" name="dosignup">Регистрация</a></li>
                        </ul>

                        <div name="signin-block">
                            <h3>Вход</h3>
                            <form method="post" action="#" name="signin-form">
                                <div class="item">
                                    <label for="login">Email</label>
                                    <div class="inp"><input type="text" name="login" id="login" required="required"
                                                            pattern="^\+?[0-9\-() ]{1,19}$"></div>
                                </div>
                                <div class="item">
                                    <label for="password">Пароль</label>
                                    <div class="inp"><input type="password" id="password" name="password"
                                                            _required="required" tabindex="2"></div>
                                </div>

                                <div class="item bt">
                                    <button class="btn-style4" name="signin-continue" type="submit">Войти</button>
                                </div>

                            </form>
                        </div>


                    </div>
                </div>
                <div class="form-item sign-up">
                    <div class="wrap-white-block">
                        <ul class="tab-login mobile480 clearfix">
                            <li><a href="#" class="btn xhr" name="dosignin">
                                <span name="signin-block">Вход с паролем</span>
                            </a></li>
                            <li><a href="#" class="active xhr" name="dosignup">Регистрация</a></li>
                        </ul>
                        <h3>Регистрация</h3>
                        <form method="post" action="#" name="signup-form">
                            <div name="msg"></div>

                            <div class="item">
                                <label for="password-field2">Пароль</label>
                                <div class="inp"><input type="password" id="password-field2" name="password"
                                                        _required="required" tabindex="3" _pattern="^.{6,}$"></div>
                                <div class="info-form">Не менее 6 символов</div>
                            </div>
                            <div class="item">
                                <label for="email-field">Эл. почта</label>
                                <div class="inp"><input id="email-field" type="text" name="email"
                                                        _required="required"
                                                        _pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-zA-Z0-9]{2,4})$"
                                                        tabindex="4"></div>
                            </div>

                            <div class="item bt">
                                <button class="btn-style4" type="submit">регистрация</button>
                            </div>


                            <input type="hidden" name="request_token" value="rB4AW1n+O9cI0wr1EDr0Ag=="></form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<input type="button" id="but"/>




</body>
</html>
