<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<header>
    <div class="uui-header">
        <nav>
            <div class="uui-responsive-header">
                <div class="responsive-header">
                    <a href="#" class="responsive-brand-logo">
                        <span class="arrow fa fa-angle-left"></span>
                        <span class="logo">
                            <img src="/img/logo.svg" alt=""/>
                        </span>
                        <span class="title">Travel Agency</span>
                    </a>
                </div>
                <div class="responsive-menu">
                    <div class="menu-wrapper">
                        <div class="menu-scroll">
                            <ul class="nav navbar-nav">
                                <li class="sub-menu uui-profile-menu">
                                    <ul class="sub">
                                        <li class="login">
                                            <@security.authorize access="isAuthenticated()">
                                                <a href="/process_logout"><span>Log out</span></a>
                                            </@security.authorize>
                                            <@security.authorize access="!isAuthenticated()">
                                                <a href="/login"><span>Log in</span></a>
                                            </@security.authorize>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!---->
            <a href="#" class="brand-logo">
                <span class="logo">
                    <img src="/img/logo.svg" alt=""/>
                </span>
                Travel Agency
            </a>
            <ul class="uui-header-tools nav navbar-nav">
                <li class="dropdown uui-profile-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" onclick="return false;"
                       aria-expanded="false">
                        <div class="profile-photo">
                            <img src="/img/no_photo.png" alt=""/>
                        </div>
                    </a>
                    <div class="dropdown-menu" role="menu">
                        <span class="menu-arrow"></span>
                        <ul class="profile-links">
                            <li class="login">
                                <@security.authorize access="isAuthenticated()">
                                    <a href="/process_logout"><span>Log out</span></a>
                                </@security.authorize>
                                <@security.authorize access="!isAuthenticated()">
                                    <a href="/login"><span>Log in</span></a>
                                </@security.authorize>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
</header>