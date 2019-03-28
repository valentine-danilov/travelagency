<#import "utility/pagin-controls.ftl" as paginControls/>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Hotels - All</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "utility/resources.ftl"/>
</head>
<body>
<#include "utility/header.ftl"/>
<div class="container padding-top-65">
    <#list pagination.content as hotel>
        <div class="row">
            <h2>${hotel.name}</h2>
            <h3>${hotel.stars}</h3>
        </div>
    </#list>
</div>
<@paginControls.addControls page="${pagination.page}" pageNumber="${pagination.pageNumber}"/>
</body>