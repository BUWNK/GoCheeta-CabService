<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="logo-group">
    <span id="logo"> <img src="${pageContext.servletContext.contextPath}/resources/img/LFT_LOGO.png" alt="logo"> </span>
</div>


<!--<div id="logo-group ">

    
</div>-->

<!-- pulled right: nav area -->
<div class="pull-right">
    
    <span id="activity" class="activity-dropdown "><i class="fa fa-bell-o fa-th-large" style="padding-top: 14px; font-size: 20px"></i> <b class="badge" id="badge">0</b></span>
    <div class="ajax-dropdown">
        <div class="btn-group btn-group-justified" data-toggle="buttons" id="buttonView">
            <span style="margin-bottom:-15px"> 
                <button type="button" id="minus"  class="btn btn-xs btn-default" style="margin-bottom:10px"><i class="fa fa-angle-up" aria-hidden="true"></i></button>
            </span> 
            <div class="ajax-notifications custom-scroll">

                <ul class="notification-body" id="notification-body">
                </ul>

            </div>
            <span> 
                <button type="button" id="plus"  class="btn btn-xs btn-default" style="margin-top:7px;"><i class="fa fa-angle-down" aria-hidden="true"></i></button>
            </span>

        </div>
    </div>

    <!-- collapse menu button -->
    <div id="hide-menu" class="btn-header pull-right">
        <span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
    </div>
    <!-- end collapse menu -->

    <!-- #MOBILE -->
    <!-- Top menu profile link : this shows only when top menu is active -->
    <ul id="mobile-profile-img" class="header-dropdown-list hidden-xs padding-5">
        <li class="">
            <!--<a href="#" class="dropdown-toggle no-margin userdropdown" data-toggle="dropdown">--> 
                <!--<img src="${pageContext.servletContext.contextPath}/resources/img/3.png" alt="John Doe" class="online" />-->  
            <!--</a>-->
            <ul class="dropdown-menu pull-right">
                <li>
                    <a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0"><i class="fa fa-cog"></i> Setting</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="profile.html" class="padding-10 padding-top-0 padding-bottom-0"> <i class="fa fa-user"></i> <u>P</u>rofile</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0" data-action="toggleShortcut"><i class="fa fa-arrow-down"></i> <u>S</u>hortcut</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0" data-action="launchFullscreen"><i class="fa fa-arrows-alt"></i> Full <u>S</u>creen</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="login.html" class="padding-10 padding-top-5 padding-bottom-5" data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i> <strong><u>L</u>ogout</strong></a>
                </li>
            </ul>
        </li>
    </ul>

    <!-- logout button -->

    <div id="logout" class="btn-header transparent pull-right">
        <span> <a href="javascript:void(0);"  onclick="logout('${pageContext.servletContext.contextPath}')" id="smart-mod-eg4"> <i class="fa fa-sign-out"></i></a> </span>
    </div>
    <!--href="<c:url value='/j_spring_security_logout' />"-->
    <!-- end logout button -->

    <!-- search mobile button (this is hidden till mobile view port) -->
    <div id="search-mobile" class="btn-header transparent pull-right">
        <span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
    </div>

    <!-- end search mobile button -->

    <!-- input: search field -->
    <!--        <form action="search.html" class="header-search pull-right">
                <input id="search-fld"  type="text" name="param" placeholder="Find reports and more" data-autocomplete='[
                       "ActionScript",
                       "AppleScript",
                       "Asp",
                       "BASIC",
                       "C",
                       "C++",
                       "Clojure",
                       "COBOL",
                       "ColdFusion",
                       "Erlang",
                       "Fortran",
                       "Groovy",
                       "Haskell",
                       "Java",
                       "JavaScript",
                       "Lisp",
                       "Perl",
                       "PHP",
                       "Python",
                       "Ruby",
                       "Scala",
                       "Scheme"]'>
                <button type="submit">
                    <i class="fa fa-search"></i>
                </button>
                <a href="javascript:void(0);" id="cancel-search-js" title="Cancel Search"><i class="fa fa-times"></i></a>
    
            </form>-->

    <!-- end input: search field -->
    <!--<spring:url value="/resources/core/js/SmartNotification.min.js" var="smartnotification" />-->
                        <!--<script src="${smartnotification}"></script>-->
</div>
<!-- end pulled right: nav area -->