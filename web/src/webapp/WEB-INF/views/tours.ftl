<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#import "utility/pagin-controls.ftl" as paginControls/>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Tours</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "utility/resources.ftl"/>
</head>
<body>
<#include "utility/header.ftl"/>
<div class="container padding-top-65">

    <button id="filterButton" type="button" class="uui-button" data-toggle="modal"
            data-target="#search-modal">
        <@spring.message code="tours.filter"/>
    </button>
    <#if successMessage??>
        <div class="modal uui-modal fade" id="success-modal" tabindex="-1" role="dialog"
             aria-labelledby="errorModel"
             aria-hidden="false">
            <div class="modal-dialog">
                <div class="modal-content text-center">
                    <div class="modal-header">
                        <h4 class="modal-title">Success!</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            <span></span><span></span></button>
                    </div>
                    <div class="modal-body">
                        ${successMessage!"There should've been an error message, but something has gone wrong:("}
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#success-modal").modal("show");
            });
        </script>
    </#if>
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
    <#list pagination.content as tour>
        <#if tour?counter%2==1 || tour?counter==1>
            <div class="row">
        </#if>
        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="margin-bottom: 10px;">
            <section class="uui-image-text" style="height: 270px">
                <a href="/tour?id=${tour.id}">
                    <div class="image-section" style="background-image: url('/img/${tour.photo}')"></div>
                </a>
                <@security.authorize access="hasRole('ROLE_ADMIN')">
                    <div class="corner left">
                        <a href="/tour/process_deleting?id=${tour.id}"><span class="far fa-trash-alt"></span></a>
                    </div>
                </@security.authorize>
                <#if errorMessage??>
                    <div class="modal uui-modal fade" id="error-modal" tabindex="-1" role="dialog"
                         aria-labelledby="errorModel"
                         aria-hidden="false">
                        <div class="modal-dialog">
                            <div class="modal-content text-center">
                                <div class="modal-header">
                                    <h4 class="modal-title">Some problems occurred!</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span></span><span></span></button>
                                </div>
                                <div class="modal-body">
                                    ${errorMessage!"There should've been an error message, but something has gone wrong:("}
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>
                        $(document).ready(function () {
                            $("#error-modal").modal("show");
                        });
                    </script>
                </#if>
                <div class="image-text-section">
                    <h3>${tour.country.name}</h3>
                    <p><span class="text-muted">Duration: </span>${tour.duration}</p>
                    <p><span class="text-muted">Cost: </span>$${tour.cost}</p>
                    <p><a href="/tour/process_adding_to_cart?id=${tour.id}">Add to cart</a></p>

                </div>
            </section>
        </div>
        <#if tour?counter%2==0 || tour?counter=pagination.content?size>
            </div>
        </#if>
    <#else>
        <div class="text-center">
            <h3>Sorry, there are no tours satisfying your search request</h3>
        </div>
    </#list>
    <@paginControls.addControls page="${pagination.page}" pageNumber="${pagination.pageNumber}"/>
</div>
</body>
</html>
