<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!DOCTYPE html>
<html>
<head>
    <#include "utility/resources.ftl"/>
    <title><@spring.message code="login.title"></@spring.message></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>
<#include  "utility/header.ftl"/>
<div class="container padding-top-65">
    <div class="row">
        <div class="col-lg-4">
            <div class="uui-info-panel-horizontal white panel-main">
                <div class="info-panel-body">
                    <h2>${user.login}</h2>
                    <h3><span class="text-muted">Number of your reviews:</span> ${reviews?size}</h3>
                    <h3><span class="text-muted">Number of your tours:</span> ${tours?size}</h3>
                    <button style="margin-top:15px; margin-bottom: 5px" type="button" class="uui-button"
                            data-toggle="modal"
                            data-target="#search-modal">
                        Change password
                    </button>
                    <@security.authorize access="hasRole('ROLE_ADMIN')">
                        <div>
                            <a class="uui-button" href="/tour/add">Add tour</a>
                        </div>
                    </@security.authorize>
                </div>
            </div>
            <div class="modal uui-modal fade" id="search-modal" tabindex="-1" role="dialog"
                 aria-labelledby="SearchModal"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content text-center">
                        <div class="modal-header">
                            <h4 class="modal-title">Changing password</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                <span></span><span></span></button>
                        </div>
                        <form id="search-form" class="form-signin" method="get" action="/tours">
                            <div class="uui-input-group">
                                <div class="modal-body">
                                    <input id="password-old" name="password-old" type="password"
                                           class="uui-form-element form-control"
                                           placeholder="Old password"/>
                                    <input id="password-new-1" name="password-new-1" type="password"
                                           class="uui-form-element form-control"
                                           placeholder="New password"/>
                                    <input id="password-new-2" name="password-new-2" type="password"
                                           class="uui-form-element form-control"
                                           placeholder="Confirm new password"/>
                                </div>
                                <div class="modal-footer">
                                    <button type="reset" data-dismiss="modal" class="uui-button">
                                        Cancel
                                    </button>
                                    <button class="uui-button" type="submit">
                                        Change
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="uui-info-panel-horizontal white panel-main" style="max-height: 350px; overflow-y: scroll">
                <div class="info-panel-body">
                    <h4>Your reviews</h4>
                    <#list reviews as review>
                        <div class="uui-info-panel-horizontal white panel-nest">
                            <div class="info-panel-footer">
                                <h5><span class="text-muted">Tour to</span> ${review.tour.country.name}</h5>
                                <h5><span class="text-muted">Tour date: </span>${review.tour.date}</h5>
                                <h5><span class="text-muted">Was posted: </span>${review.date}</h5>
                            </div>
                            <div class="info-panel-body">
                                <p>${review.text}</p>
                            </div>
                        </div>
                    <#else>
                        <h5>You do not have any reviews</h5>
                    </#list>
                </div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="uui-info-panel-horizontal white panel-main" style="margin-bottom: 50px;">
                <#list tours as tour>
                    <div class="uui-info-panel-horizontal white panel-nest">
                        <div class="info-panel-body">
                            <div class="container">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                    <img src="/img/test1.png" style="width: 100%" alt="">
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                                    <h4><span class="text-muted">
                                                Tour to
                                            </span>
                                        ${tour.country.name}
                                    </h4>
                                    <h4><span class="text-muted">
                                                Leaving day:
                                            </span>
                                        ${tour.date}
                                    </h4>
                                    <h4><span class="text-muted">
                                                Duration:
                                            </span>
                                        ${tour.duration} days
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </div>
                <#else>
                    <h5>You do not have any tours</h5>
                </#list>
            </div>

        </div>
    </div>
</div>
</body>
</html>