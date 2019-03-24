$(document).ready(function () {
    let url = getURLWithParams();
    if (url.indexOf("page")<=-1) {
        if (url.indexOf("?")>-1) {
            url += "&page=";
        } else url += "?page=";
    } else {
        url = url.substring(0, url.lastIndexOf("=")+1);
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


function getURLWithParams() {
    return location.pathname + location.search;
}

$(document).ready(function () {
    $("#search-form").submit(function () {
        $("input").each(function (index, obj) {
            if ($(obj).val() === "") {
                $(obj).remove();
            }
        })
    })
});


