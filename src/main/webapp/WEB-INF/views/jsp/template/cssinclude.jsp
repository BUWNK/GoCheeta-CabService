<%-- 
    Document   : cssinclude
    Created on : Oct 16, 2015, 9:24:49 AM
    Author     : Isuru
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">

<title> Commercial Credit </title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Basic Styles -->
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/font-awesome.min.css" var="font" />
<link href="${font}" rel="stylesheet" />

<spring:url value="/resources/core/css/smartadmin-production.min.css" var="production" />
<link href="${production}" rel="stylesheet" />

<spring:url value="/resources/core/css/bootstrap-datetimepicker.css" var="datetimepicker" />
<link href="${datetimepicker}" rel="stylesheet" />

<spring:url value="/resources/core/css/bootstrap-timepicker.min.css" var="timepic" />
<link href="${timepic}" rel="stylesheet" />

<spring:url value="/resources/core/css/style.css" var="style" />
<link href="${style}" rel="stylesheet" />

<spring:url value="/resources/core/css/your_style.css" var="yourStyle" />
<link href="${yourStyle}" rel="stylesheet" />

<spring:url value="/resources/img/favicon.ico" var="icon" />
<link href="${icon}" rel="image/x-icon" />

<link rel="icon" href="${icon}" type="image/x-icon"/>
<link rel="shortcut icon" href="${icon}" type="image/x-icon"/>

<spring:url value="/resources/core/css/chartist.min.css" var="chartistcss" />
<link href="${chartistcss}" rel="stylesheet" />

<spring:url value="/resources/core/css/jquery-ui.css" var="jqueryui" />
<link href="${jqueryui}" rel="stylesheet" />

<spring:url value="/resources/core/css/fullcalendar.css" var="calendercss" />
<link href="${calendercss}" rel="stylesheet" />

<spring:url value="/resources/core/css/fullcalendar.print.css" var="calenderprint" />
<link href="${calenderprint}" rel="stylesheet" media='print' />

<!-- GOOGLE FONT -->
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
