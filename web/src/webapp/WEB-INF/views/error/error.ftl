<!DOCTYPE html>

<html lang="en-US">
<head>
    <title>Error</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "../utility/resources.ftl"/>
</head>
<body>
<#include "../utility/header.ftl"/>
<div class="container" style="margin-top: 65px">
    <div class="jumbotron jumbotron-fluid">
        <div class="container text-center">
            <h1>Error occurred</h1>
        </div>
    </div>
</div>
<div class="container">
    <div class="text-center">
        <h3>${errorMessage}</h3>
        <#if description??>
            <h3>${description}</h3>
        </#if>
    </div>
</div>
</body>
</html>