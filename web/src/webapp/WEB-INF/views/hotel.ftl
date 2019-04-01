<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Hotel - ${hotel.name}</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "utility/resources.ftl"/>
</head>
<body>
<#include "utility/header.ftl"/>
<div class="container padding-top-65">
    <div class="row">
        <div class="col-lg-4">
            <h2>Hotel information</h2>
            <div class="uui-info-panel-horizontal white panel-main">
                <p>${hotel.name}</p>
            </div>
        </div>
        <div class="col-lg-8">
            <h2>Hotel location</h2>
            <div id="hotelLocation" style="width:100%;height:400px; box-shadow: 0 1px 3px 0 rgba(51, 51, 51, .2);"></div>
            <script>
                function myMap() {
                    let location = {lat: ${hotel.latitude},lng: ${hotel.longitude}};
                    var mapProp = {
                        center: location,
                        zoom: 9
                    };
                    let map = new google.maps.Map(document.getElementById("hotelLocation"), mapProp);
                    let marker = new google.maps.Marker({
                        position: location,
                        map: map,
                        title: "Hotel '${hotel.name}'"
                    });
                }
            </script>
        </div>
    </div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?key=${API_KEY}&callback=myMap"></script>
</body>