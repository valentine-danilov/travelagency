<ul class="uui-pagination">
    <li class="actions-wrapper">
        <ul class="pagination-items">
            <li class="first-page disable">
                <a href="#">
                    <span>First</span>
                </a>
            </li>
            <li class="back-pages disable">
                <a href="#">
                    <i class="fa fa-angle-double-left"></i>
                </a>
            </li>
            <li class="prev-page disable">
                <a href="/tours?page=1">
                    <i class="fa fa-angle-left"></i>
                </a>
            </li>
        </ul>
    </li>
    <li class="pages-wrapper">
        <ul class="pagination-items">
            <li class="active">
                <a href="/tours?page=${pagination.page}">${page}</a>
            </li>
            <#if (pagination - page) gt 0>
                <li>
                    <a href="/tours?page=${page+1}">${page+1}</a>
                </li>
            </#if>
            <#if (pageNumber - page) gt 1>
                <li>
                    <a href="/tours?page=${page+2}">${page+2}</a>
                </li>
            </#if>
        </ul>
    </li>
    <li class="actions-wrapper">
        <ul class="pagination-items">
            <li class="next-page">
                <a href="#">
                    <i class="fa fa-angle-right"></i>
                </a>
            </li>
            <li class="forward-pages">
                <a href="#">
                    <i class="fa fa-angle-double-right"></i>
                </a>
            </li>
            <li class="last-page">
                <a href="/tours?page=${pageNumber}">
                    <span>Last</span>
                </a>
            </li>
        </ul>
    </li>
</ul>