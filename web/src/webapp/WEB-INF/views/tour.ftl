<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Tour - ${tour.country}</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "utility/resources.ftl"/>
</head>

<body>
<#include "utility/header.ftl"/>
<div class="container mt-3" style="margin-top: 75px">
    <div class="row">
        <div class="col-lg-12">
            <h2>Tour information</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8">
            <img src="/img/test1.png" alt="" class="img-rounded mr-3 mt-3" style="width:100%;">
        </div>

        <div class="col-lg-4">
            <table class="table table-borderless">
                <tbody>
                <tr>
                    <td>
                        <h2>${tour.country.name}</h2>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Leaving</strong> - ${tour.date}
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Duration</strong> - ${tour.duration}
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Price</strong> - $${tour.cost}
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Hotel</strong> - ${tour.hotel.name}
                        <#list 1..tour.hotel.stars as i>
                            <span><i class="fa fa-star"></i></span>
                        </#list>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12" style="margin-top: 25px">
            <h2>Tour description</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <section class="uui-info-panel-horizontal white">
                <div class="info-panel-body">
                    <div class="info-panel-section">
                        ${tour.description}
                    </div>
                </div>
            </section>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12" style="margin-top: 25px">
            <h2>Tour reviews</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <form id="review-form" method="post" action="/review/add" style="margin-bottom: 5px">
                <input type="hidden" name="tourId" value="${tour.id}">
                <button type="submit" class="uui-button">Add review</button>
            </form>
            <textarea name="text"
                      form="review-form"
                      class="uui-form-element"
                      rows="6"
                      style="width: 60%"
                      placeholder="Your's review text">
            </textarea>
        </div>
    </div>
    <#list reviews as review>
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">
                        <p><strong>${review.user.login}</strong></p>
                        <p class="text-muted">${review.date}</p>
                    </div>
                    <div class="card-body">
                        <p>${review.text}</p>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
<div class="container">
    <div class="row">

    </div>
</div>
</body>
</html>