<!DOCTYPE html>
<html>
<head>
    <#include "utility/resources.ftl"/>
    <title><@spring.message code="login.title"></@spring.message></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>
<#include "utility/header.ftl"/>
<div class="container form-content text-center">
    <form class="form-signin" method="post" action="process_login">
        <h1 class="h3 mb-3 font-weight-normal"><@spring.message code="login.title"></@spring.message></h1>
        <div class="uui-input-group uu">
            <input type="text" name="login" class="uui-form-element form-control" placeholder="Login" required
                   autofocus>
            <input type="password" name="password" class="uui-form-element form-control" placeholder="Password"
                   required>
            <button class="uui-button uui-form-element"
                    type="submit"><@spring.message code="login.submit"/>
            </button>
        </div>
        <p>New on our site?<a href="/signup"> Register</a></p>
    </form>
</div>
</body>
</html>


