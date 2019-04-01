$(document).ready(function () {
    let url = getURLWithParams();
    if (url.indexOf("page") <= -1) {
        if (url.indexOf("?") > -1) {
            url += "&page=";
        } else url += "?page=";
    } else {
        url = url.substring(0, url.lastIndexOf("=") + 1);
    }
    let pageNext = $(".next-page").attr("href");
    let pagePrev = $(".prev-page").attr("href");
    let pageLast = $(".last-page").attr("href");
    let pageFirst = $(".first-page").attr("href");
    $(".prev-page").attr("href", url + pagePrev);
    $(".last-page").attr("href", url + pageLast);
    $(".first-page").attr("href", url + pageFirst);
    $(".next-page").attr("href", url + pageNext);
});

function changeLocale(lang) {
    let url = getURLWithParams();
    let xhr = new XMLHttpRequest();
    let localeUrl = url;
    if (url.indexOf("?") <= -1) {
        localeUrl += ("?" + lang);
    } else {
        localeUrl += ("&" + lang);
    }
    xhr.open('GET', localeUrl);
    xhr.send();
    location.reload();
}

function getURLWithParams() {
    return location.pathname + location.search;
}

$(document).ready(function () {
    $("#search-form").submit(function () {
        $("input").each(function (index, obj) {
            if ($(obj).val() === "") {
                $(obj).attr("disabled", "disabled");
            }
        })
    })
});


function editTour() {
    $("#tour-edit-form input").each(function (index, obj) {
        $(obj).removeAttr("disabled");
    });
    $("#tour-edit-form select").each(function (index, obj) {
        $(obj).removeAttr("disabled");
    });
    $("#tour-edit").attr("disabled", "disabled");
    $("#tour-save").removeAttr("disabled");
    $("#edit-cancel").removeAttr("disabled");
}

function saveTour() {
    $("#tour-edit-form").submit();
    $("#tour-edit-form input").each(function (index, obj) {
        $(obj).attr("disabled", "disabled");
    });

    $("#tour-edit-form select").each(function (index, obj) {
        $(obj).attr("disabled", "disabled");
    });
}

function cancelEditing() {
    $("#tour-edit-form").trigger("reset");
    $("#tour-save").attr("disabled", "disabled");
    $("#tour-edit").removeAttr("disabled");
}


