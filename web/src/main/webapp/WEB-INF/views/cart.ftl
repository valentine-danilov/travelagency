<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#import "utility/pagin-controls.ftl" as paginControls/>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Your cart</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "utility/resources.ftl"/>
</head>
<body>
<#include "utility/header.ftl"/>
<div class="container padding-top-65">
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
    <#if user.cart??>
        <div class="container">
            <div class="row ">
                <h2>Your cart Total money amount: ${totalMoneyAmount}
                    <span>
                        <button id="buyButton" type="button" class="uui-button" data-toggle="modal"
                                data-target="#buy-modal">
                            Buy
                        </button>
                    </span>
                </h2>
                <div class="modal uui-modal fade" id="buy-modal" tabindex="-1" role="dialog"
                     aria-labelledby="SearchModal"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content text-center">
                            <div class="modal-header">
                                <h4 class="modal-title">Filter form</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    <span></span><span></span></button>
                            </div>

                            <form id="buy-form" class="form-signin" method="get" action="/cart/process_purchase">
                                <div class="uui-input-group">
                                    <div class="modal-body">
                                        <input id="totalMoneyAmount"
                                               name="totalMoneyAmount"
                                               value="${totalMoneyAmount}"
                                               type="hidden"
                                               class="uui-form-element form-control"/>
                                        <input type="text" pattern="[0-9]{16}" placeholder="Debit card">
                                        <input type="text" pattern="[0-9]{3}" placeholder="3-digit security code">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="reset" data-dismiss="modal" class="uui-button">
                                            Cancel
                                        </button>
                                        <button class="uui-button" type="submit">
                                            <i class="fa fa-search"></i>
                                            Buy
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>

            <div class="row">
                <h3>Tours you have added to cart:</h3>
                <#list user.cart as tour>
                    <#if tour?counter%2==1 || tour?counter==1>
                        <div class="row">
                    </#if>
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="margin-bottom: 10px;">
                        <section class="uui-image-text" style="height: 270px">
                            <a href="/tour?id=${tour.id}">
                                <div class="image-section" style="background-image: url('/img/test1.png')"></div>
                            </a>
                            <div class="image-text-section">
                                <h3>${tour.country.name}</h3>
                                <p><span class="text-muted">Duration: </span>${tour.duration}</p>
                                <p><span class="text-muted">Cost: </span>$${tour.cost}</p>
                                <p><a href="/tour/process_removing_from_cart?id=${tour.id}">Remove from cart</a></p>

                            </div>
                        </section>
                    </div>
                    <#if tour?counter%2==0 || tour?counter=user.cart?size>
                        </div>
                    </#if>
                </#list>
            </div>
        </div>
    <#else >
        <h1 class="padding-top-65 text-center">Your cart is empty!</h1>
    </#if>
</div>
</body>
</html>
