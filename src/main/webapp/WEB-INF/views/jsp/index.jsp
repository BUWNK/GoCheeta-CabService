
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page">
    <head>
        <meta charset="utf-8">
        <title> Commercial Credit</title>
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
        <link href="${bootstrapCss}" rel="stylesheet" />

        <spring:url value="/resources/core/css/demo.min.css" var="demo" />
        <link href="${demo}" rel="stylesheet" />

        <spring:url value="/resources/core/css/font-awesome.min.css" var="font" />
        <link href="${font}" rel="stylesheet" />

        <spring:url value="/resources/core/css/invoice.min.css" var="invoice" />
        <link href="${invoice}" rel="stylesheet" />

        <spring:url value="/resources/core/css/lockscreen.min.css" var="lockscreen" />
        <link href="${lockscreen}" rel="stylesheet" />

        <spring:url value="/resources/core/css/smartadmin-production.min.css" var="production" />
        <link href="${production}" rel="stylesheet" />

        <spring:url value="/resources/core/css/smartadmin-rtl.min.css" var="rtl" />
        <link href="${rtl}" rel="stylesheet" />

        <spring:url value="/resources/core/css/smartadmin-skins.min.css" var="skins" />
        <link href="${skins}" rel="stylesheet" />

        <spring:url value="/resources/core/css/your_style.css" var="your_style" />
        <link href="${your_style}" rel="stylesheet" />

        <spring:url value="/resources/core/css/style.css" var="style" />
        <link href="${style}" rel="stylesheet" />

        <spring:url value="resources/img/favicon.ico" var="icon" />
        <link href="${icon}" rel="stylesheet" />

        <link rel="icon" href="${icon}" type="image/x-icon"/>
        <link rel="shortcut icon" href="${icon}" type="image/x-icon"/>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">

    </head>

    <body class="animated fadeInDown">
        <header id="header">
            <div id="logo-group"></div>
            <!--<span id="extr-page-header-space"> <span class="hidden-mobile">Need an account?</span> <a href="register.html" class="btn btn-danger">Create account</a> </span>-->
        </header>

        <div id="main" role="main">
            <!-- MAIN CONTENT -->
            <div id="content" class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
                        <div class="hero row">
                            <div class="pull-left login-desc-box-l">
                                <div class="login-app-icons">
                                    <span id=""> <img class="login-logo" src="${pageContext.servletContext.contextPath}/resources/img/logo.png" alt=""> </span>
                                </div>
                            </div>
                        </div>
                        <!--                        <div class="row">
                                                    <h1 class="txt-color-red login-header-big login-headertxt">Customer Relationship Management System</h1>
                                                </div>-->

                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
                        <div class="well no-padding">
                            <form id="login-form" name="loginForm" novalidate="novalidate" action="${pageContext.servletContext.contextPath}/login" method='post' class="smart-form client-form">                              
                                <header>
                                    Sign In
                                </header>
                                <fieldset>
                                    <div class="row">
                                        <div class="col-xs-1"></div>
                                        <div class="col-xs-10">
                                            <c:if test="${not empty msg}">
                                                <div id="message_out" class="alert alert-success">
                                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                                    <strong>Success!</strong> ${msg}
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty error}">                                           
                                                <div id="message_out" class="alert alert-danger">
                                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                                    <strong>Error!</strong> ${error}
                                                </div>
                                                <br/>
                                            </c:if>
                                            <c:if test="${not empty info}">                                           
                                                <div id="message_out" class="alert alert-info">
                                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                                    <strong>Error!</strong> ${info}
                                                </div>
                                                <br/>
                                            </c:if> 
                                        </div>
                                        <div class="col-xs-1"></div>
                                    </div>
                                    <section>
                                        <label class="label">Username</label>
                                        <label class="input"> 
                                            <i class="icon-append fa fa-user"></i>
                                            <input name="username" class="myWish" placeholder="username"/>
                                            <b class="tooltip tooltip-top-right">
                                                <i class="fa fa-user txt-color-teal"></i> Please enter username
                                            </b>
                                        </label>
                                    </section>
                                    <section>
                                        <label class="label">Password</label>
                                        <label class="input"> 
                                            <i class="icon-append fa fa-lock"></i>
                                            <input type="password" name="password" class="myWish" placeholder="password"/>
                                            <b class="tooltip tooltip-top-right">
                                                <i class="fa fa-lock txt-color-teal"></i> Enter your password
                                            </b> 
                                        </label>
                                    </section>
                                </fieldset>
                                <footer>                                   
                                    <button type="submit" class="btn btn-primary">Sign in</button>
                                </footer>
                            </form>
                        </div>
                        <p class="pull-right">${version}</p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
                        <!--                        <div class="hero row">
                                                    <div class="pull-left login-desc-box-l">
                                                        <div class="login-app-icons">
                                                            <span id=""> <img class="login-logo" src="${pageContext.servletContext.contextPath}/resources/img/LFT_LOGO.png" alt=""> </span>
                                                        </div>
                                                    </div>
                                                </div>-->
                        <!--                        <div class="row">
                                                    <h1 class="txt-color-red login-header-big login-headertxt">Customer Relationship Management System</h1>
                                                </div>-->
                        <div class="row ">
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <!--<h5 class="about-heading">About Commercial Credit - Are you up to date?</h5>-->
                                <p>
                                    Affiniti integrated sales and services solution allows you to connect with your customers in a manner that helps you make the most of every lead.
                                </p>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <!--<h5 class="about-heading">Benefits with Commercial Credit CRM</h5>-->
                                <p>
                                    Our revolutionary CRM solution makes it easier for you to get to know your customers better, become truly customer-oriented and ultimately deliver an outstanding service.
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">

                    </div>
                </div>
            </div>

        </div>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <spring:url value="/resources/core/js/jquery-2.0.2.min.js" var="jquery2" />
        <script> if (!window.jQuery) {
                document.write('<script src="${jquery2}"><\/script>');
            }
        </script>

        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
        <spring:url value="/resources/core/js/jquery-ui-1.10.3.min.js" var="jqueryui" />
        <script> if (!window.jQuery.ui) {
                document.write('<script src="${jqueryui}"><\/script>');
            }
        </script>

        <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrap" />
        <script src="${bootstrap}"></script>

        <spring:url value="/resources/core/js/jquery.validate.min.js" var="validate" />
        <script src="${validate}"></script>

        <spring:url value="/resources/core/js/jquery.maskedinput.min.js" var="masked" />
        <script src="${masked}"></script>

        <spring:url value="/resources/core/js/app.min.js" var="app" />
        <script src="${app}"></script>
        <script type="text/javascript">
            setTimeout(function () {
                $("#message_out").fadeTo(2000, 500).slideUp(500, function () {
                    $("#message_out").alert('close');
                });
            }, 20000);

            $(".myWish").focus(function () {
                $("#message_out").fadeTo(2000, 500).slideUp(500, function () {
                    $("#message_out").alert('close');
                });
            });
        </script>
    </body>
</html>
