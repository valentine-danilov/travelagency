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
    <h2>Hotel list</h2>
    <#list pagination.content as hotel>
        <#if hotel?counter%2==1 || hotel?counter==1>
            <div class="row">
        </#if>

        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="margin-bottom: 10px;">
            <div class="uui-info-panel-horizontal white panel-main">
                <p><span class="text-muted">Name: </span>
                    <a href="/hotel?id=${hotel.id}">
                        ${hotel.name}
                    </a>
                </p>
                <p>
                    <#list 1..hotel.stars as i>
                        <span><i class="fa fa-star"></i></span>
                    </#list>
                </p>
            </div>
        </div>
        <#if hotel?counter%2==0 || hotel?counter=pagination.content?size>
            </div>
        </#if>
    <#else>
        <div class="text-center">
            <h3>Sorry, there are no tours satisfying your search request</h3>
        </div>
    </#list>
</div>
<@paginControls.addControls page="${pagination.page}" pageNumber="${pagination.pageNumber}"/>
</body>