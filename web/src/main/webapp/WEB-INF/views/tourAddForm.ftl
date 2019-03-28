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
    <form name="tour-form" method="post" action="/tour/process_adding">
        <div class="text-center">
            <h2>Tour form</h2>
        </div>
        <div class="uui-input-group uu">
            <input type="date" name="date"
                   class="uui-form-element form-control"
                   placeholder="Tour date"
                   required>

            <input type="number"
                   name="duration"
                   class="uui-form-element form-control"
                   placeholder="Tour duration"
                   required>

            <input type="text" name="description"
                   class="uui-form-element form-control"
                   placeholder="Tour description"
                   required>

            <input type="text" name="cost"
                   class="uui-form-element form-control"
                   placeholder="Tour cost"
                   required>

            <label for="hotel">Choose hotel</label>
            <select id="hotel" name="hotel" class="uui-form-element form-control">
                <#list hotels as hotel>
                    <option value="${hotel.id}">${hotel.name}</option>
                </#list>
            </select>

            <label for="country">Choose country</label>
            <select id="country" name="country" class="uui-form-element form-control">
                <#list countries as country>
                    <option value="${country.id}">${country.name}</option>
                </#list>
            </select>

            <label for="tourType">Choose tour type</label>
            <select id="tourType" name="tourType" class="uui-form-element form-control">
                <#list tourTypes as type>
                    <option value="${type}">${type}</option>
                </#list>
            </select>

            <button class="uui-button uui-form-element"
                    type="submit"
                    style="margin-top: 10px">
                Add tour
            </button>
        </div>
    </form>
</div>
</body>