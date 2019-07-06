<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Adding new tour</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#include "utility/resources.ftl"/>
</head>

<body>
<#include "utility/header.ftl"/>
<div class="container form-content padding-top-65">
    <form name="tour-form" method="post" action="/hotel/process_adding">
        <div class="text-center">
            <h2>Hotel form</h2>
        </div>
        <div class="uui-input-group uu">
            <input type="text" name="name"
                   class="uui-form-element form-control"
                   placeholder="Hotel name"
                   required>

            <input type="number"
                   name="stars"
                   class="uui-form-element form-control"
                   min="1"
                   max="5"
                   placeholder="Hotel stars"
                   required>

            <input type="text" name="website"
                   class="uui-form-element form-control"
                   placeholder="Hotel website"
                   required>

            <input type="text" name="longitude"
                   class="uui-form-element form-control"
                   placeholder="Hotel longitude"
                   required>

            <input type="text" name="latitude"
                   class="uui-form-element form-control"
                   placeholder="Hotel latitude"
                   required>

            <button class="uui-button uui-form-element"
                    type="submit"
                    style="margin-top: 10px">
                Add hotel
            </button>
        </div>
    </form>
</div>
</body>