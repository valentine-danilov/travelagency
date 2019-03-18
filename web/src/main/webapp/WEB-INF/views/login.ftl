<#import "resources.ftl" as resources/>

<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Sign in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <@resources.import/>
</head>
</html>

<body>
<div class="container form-content text-center">
    <form class="form-signin" method="post" action="process_login">
        <h1 class="h3 mb-3 font-weight-normal">Log in</h1>
        <div class="uui-input-group uu">
            <input type="text" name="login" class="uui-form-element form-control" placeholder="Login" required autofocus>
            <input type="password" name="password" class="uui-form-element form-control" placeholder="Password" required>
            <button class="uui-button uui-form-element" type="submit">Log in</button>
        </div>
    </form>
</div>
</body>
