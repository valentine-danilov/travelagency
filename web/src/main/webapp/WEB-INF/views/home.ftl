<!DOCTYPE html>
<html lang="en-US" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "resources.ftl"/>
</head>
<body>
<#include "header.ftl"/>
<div class="container" style="padding-top: 50px">

    <button style="margin-top:15px; margin-bottom: 5px" type="button" class="uui-button" data-toggle="modal"
            data-target="#search-modal">
        Filter
    </button>
    <div class="modal uui-modal fade" id="search-modal" tabindex="-1" role="dialog" aria-labelledby="SearchModal"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content text-center">
                <div class="modal-header">
                    <h4 class="modal-title">Filter form</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span></span><span></span></button>
                </div>

                <form id="search-form" class="form-signin" method="get" action="/tours">
                    <div class="uui-input-group">
                        <div class="modal-body">
                            <input id="date" name="date" type="date"
                                   class="uui-form-element form-control"
                                   placeholder="Date"/>
                            <input id="duration" name="duration" type="number"
                                   class="uui-form-element form-control"
                                   placeholder="Tour duration (in days)"/>
                            <input id="cost" name="cost" type="text"
                                   class="uui-form-element form-control"
                                   placeholder="Tour cost"/>
                            <input id="stars" name="stars" type="number" max="5"
                                   class="uui-form-element form-control"
                                   placeholder="Hotel stars"/>
                            <input id="country" name="country" type="text"
                                   class="uui-form-element form-control"
                                   placeholder="Country"/>
                        </div>
                        <div class="modal-footer">
                            <button type="reset" data-dismiss="modal" class="uui-button">
                                Cancel
                            </button>
                            <button class="uui-button" type="submit">
                                <i class="fa fa-search"></i>
                                Apply
                            </button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<div class="container">
    <#list pagination.content as tour>

        <#if tour?counter%2==1 || tour?counter==1>
            <div class="row">
        </#if>
        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="margin-bottom: 10px;">
            <section class="uui-image-text" style="height: 200px">
                <div class="image-section" style="background-image: url('/img/test1.png')"></div>
                <div class="image-text-section">
                    Tour to ${tour.country.name}
                </div>
            </section>
        </div>
        <#if tour?counter%2==0 || tour?counter=pagination.content?size>
            </div>
        </#if>

    </#list>
</div>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="col-lg-12">
                <ul class="uui-pagination">
                    <li class="actions-wrapper">
                        <ul class="pagination-items">
                            <li>
                                <a class="first-page" href="1">
                                    <span>First</span>
                                </a>
                            </li>
                            <li <#if pagination.page < 2>class="disabled"</#if>>
                                <a class="prev-page" href="${pagination.page-1}">
                                    <i class="fa fa-angle-left"></i>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="pages-wrapper">
                        <ul class="pagination-items">
                            <li class="active">
                                <a>${pagination.page}</a>
                            </li>
                        </ul>
                    </li>
                    <li class="actions-wrapper">
                        <ul class="pagination-items">
                            <li <#if (pagination.pageNumber - pagination.page) gt 0>
                                class="disabled"</#if>>
                                <a class="next-page" href="${pagination.page+1}">
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                            <li>
                                <a class="last-page" href="${pagination.pageNumber}">
                                    <span>Last</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
