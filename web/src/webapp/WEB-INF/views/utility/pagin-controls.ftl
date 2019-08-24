<#macro addControls page pageNumber >
    <div class="container" style="margin-bottom: 15px">
        <div class="row">
            <div class="col-lg-12">
                <div class="col-lg-12">
                    <ul class="uui-pagination">
                        <li class="actions-wrapper">
                            <ul class="pagination-items">
                                <li>
                                    <a class="first-page" href="1">
                                        <span>First</span>
                                    </a>
                                </li>
                                <li <#if page?number < 2>class="disabled"</#if>>
                                    <a class="prev-page" href="${page?number-1}">
                                        <i class="fa fa-angle-left"></i>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="pages-wrapper">
                            <ul class="pagination-items">
                                <li class="active">
                                    <a>${page}</a>
                                </li>
                            </ul>
                        </li>
                        <li class="actions-wrapper">
                            <ul class="pagination-items">
                                <li <#if (pageNumber?number - page?number) <= 0>
                                    class="disabled"</#if>>
                                    <a class="next-page" href="${page?number+1}">
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                                <li>
                                    <a class="last-page" href="${pageNumber}">
                                        <span>Last</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</#macro>
