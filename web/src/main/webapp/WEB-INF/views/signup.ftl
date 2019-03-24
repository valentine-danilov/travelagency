<!DOCTYPE html>
<html lang="eu-US">
<head>
    <title>Sign Up</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "resources.ftl"/>
</head>
<body>
<#include "header.ftl"/>
<div class="container form-content text-center">
    <form id="userForm" class="form-signin" method="post" action="/process_signup" onchange="">
        <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>
        <#if errorMessage??>
            <div>${errorMessage}</div>
        </#if>
        <div class="uui-input-group">
            <input id="login"
                   type="text"
                   name="login"
                   class="uui-form-element form-control"
                   placeholder="Login"
                   required
                   <#--pattern="[a-zA-Z0-9_.]{3,45}"-->
                   autofocus
                   autocomplete="false">
            <input id="password"
                   type="password"
                   name="password"
                   class="uui-form-element form-control"
                   placeholder="Password"
                   autocomplete="false"
                   <#--pattern="[a-zA-Z0-9_.]{4,35}"-->
                   required>
            <input id="password-2"
                   type="password"
                   name="password-2"
                   class="uui-form-element form-control"
                   placeholder="Repeat password"
                   autocomplete="false"
                   <#--pattern="[a-zA-Z0-9_.]{4,35}"-->
                   required/>
            <button id="submit" class="uui-button uui-form-element" type="submit">Sign up</button>
        </div>
    </form>
</div>
</body>
<#include "footer.ftl"/>
</html>