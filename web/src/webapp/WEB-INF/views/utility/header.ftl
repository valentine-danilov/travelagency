<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<header>
    <div class="uui-header">
        <nav>
            <a href="#" class="brand-logo">
                <span class="logo">
                    <img src="/img/logo.svg" alt=""/>
                </span>
                Travel Agency
            </a>
            <ul class="uui-navigation nav navbar-nav">
                <li><a href="/tours">Tours</a></li>
                <li><a href="/hotels">Hotels</a></li>
            </ul>
            <ul class="uui-header-tools nav navbar-nav">
                <li class="dropdown uui-profile-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" onclick="return false;"
                       aria-expanded="false">
                        <div class="profile-photo">
                            <img src="/img/globe.svg" alt=""/>
                        </div>
                    </a>
                    <div id="localChanging" class="dropdown-menu" role="menu">
                        <span class="menu-arrow"></span>
                        <ul>
                            <li>
                                <a onclick="changeLocale('lang=en_US')">EN</a>
                            </li>
                            <li>
                                <a onclick="changeLocale('lang=ru_RU')">RU</a>
                            </li>
                        </ul>
                    </div>
                </li>

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
                            <@security.authorize access="isAuthenticated()">
                                <li>
                                    <a href="/profile"><i class="fas fa-user"></i><span>Profile</span></a>
                                </li>
                                <li>
                                    <a href="/cart"><i class="fas fa-shopping-cart"></i><span>Cart</span></a>
                                </li>
                            </@security.authorize>
                            <li class="login">
                                <@security.authorize access="isAuthenticated()">
                                    <a href="/process_logout"><i class="fas fa-sign-in-alt"></i><span>Log out</span></a>
                                </@security.authorize>
                                <@security.authorize access="!isAuthenticated()">
                                    <a href="/login"><i class="fas fa-sign-out-alt"></i><span>Log in</span></a>
                                </@security.authorize>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
</header>