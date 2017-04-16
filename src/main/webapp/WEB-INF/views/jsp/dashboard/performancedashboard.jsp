<%-- 
    Document   : performancedashboard
    Created on : Jun 21, 2016, 12:05:16 PM
    Author     : Roshen Dilshan
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html lang="en-us">
    <head>
        <jsp:include page="../template/cssinclude.jsp"/>
    </head>
    <body class="">
        <!-- HEADER -->
        <header id="header">			
            <jsp:include page="../template/header.jsp"/>
        </header>
        <!-- END HEADER -->

        <aside id="left-panel">
            <jsp:include page="../template/menu.jsp"/>
        </aside>
        <!-- END NAVIGATION -->

        <!-- MAIN PANEL -->
        <div id="main" role="main">
            <!-- RIBBON -->
            <div id="ribbon">
                <div class="row">
                    <div class="col-xs-6">
                        <ol class="breadcrumb">
                            <li>Home</li><li>PERFORMANCE DASHBOARD</li>
                        </ol>
                    </div>
                    <div class="col-xs-6">
                        <p style="color: #ffffff; padding-top: 11px" class="text-right"><c:out value="${sessionScope.lastlogin}" /></p>
                    </div>
                </div>
            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row smart-form" style="padding-bottom: 30px;">
                    <div class="col-xs-12">
                        <header><h4>Quarterly Statistics</h4></header>
                    </div>
                </div>
                <!-- row -->
                <div class="row">
                    <!--NEW WIDGET START--> 
                    <c:if test="${LeadRatioGraph.leadratio}">
                        <article class="${LeadRationCSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${LeadRationID}" data-widget-editbutton="false">
                                <!-- widget options:
                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
        
                                data-widget-colorbutton="false"
                                data-widget-editbutton="false"
                                data-widget-togglebutton="false"
                                data-widget-deletebutton="false"
                                data-widget-fullscreenbutton="false"
                                data-widget-custombutton="false"
                                data-widget-collapsed="true"
                                data-widget-sortable="false"
        
                                -->
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>Overall User Lead Success Ratio</h2>

                                </header>

                                <!-- widget div-->
                                <div>

                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->

                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <div class="widget-body no-padding" style="height: 245px;">

                                        <div id="leadsucessrationgraph" class="chart"></div>

                                    </div>
                                    <!-- end widget content -->

                                </div>
                                <!-- end widget div -->

                            </div>
                            <!-- end widget -->

                        </article>
                    </c:if>
                    <!--WIDGET END-->
                    <!--NEW WIDGET START--> 
                    <c:if test="${LeadContagGraph.contacklead}">
                        <article class="${LeadContagGraphCSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${LeadContagGraphID}" data-widget-editbutton="false">
                                <!-- widget options:
                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
        
                                data-widget-colorbutton="false"
                                data-widget-editbutton="false"
                                data-widget-togglebutton="false"
                                data-widget-deletebutton="false"
                                data-widget-fullscreenbutton="false"
                                data-widget-custombutton="false"
                                data-widget-collapsed="true"
                                data-widget-sortable="false"
        
                                -->
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>Total User Contacts vs. Leads vs. Accounts</h2>

                                </header>

                                <!-- widget div-->
                                <div>

                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->

                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <div class="widget-body">

                                        <!--<div id="contactvsleadvsaccount" class="chart"></div>-->
                                        <div class="show-stats">
                                            <div class="row">
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                    <div class="row">
                                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                                            <div class="text" style="margin-top: 10px;"> Accounts </div>
                                                        </div>
                                                        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                                            <span id="account_count" class="pull-right" style="font-size: 20px;"></span>
                                                        </div>
                                                    </div>
                                                    <div class="progress">
                                                        <div id="account_count_style" class="progress-bar bg-color-greenLight" style=""></div>
                                                    </div> 
                                                </div>
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                    <div class="row">
                                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                                            <div class="text" style="margin-top: 10px;">Leads</div>
                                                        </div>
                                                        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                                            <span id="lead_count" class="pull-right" style="font-size: 20px;"></span>
                                                        </div>
                                                    </div>
                                                    <div class="progress">
                                                        <div id="lead_count_style" class="progress-bar bg-color-yellow" style=""></div>
                                                    </div> 
                                                </div>
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                    <div class="row">
                                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                                            <div class="text" style="margin-top: 10px;"> Contacts</div>
                                                        </div>
                                                        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                                            <span id="contact_count" class="pull-right" style="font-size: 20px;"></span>
                                                        </div>
                                                    </div>
                                                    <div class="progress">
                                                        <div id="contact_count_style" class="progress-bar bg-color-blue" style=""></div>
                                                    </div> 
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <!-- end widget content -->

                                </div>
                                <!-- end widget div -->

                            </div>
                            <!-- end widget -->

                        </article>
                    </c:if>
                    <!--WIDGET END-->
                </div>
                <!-- row -->
                <div class="row">
                    <!--NEW WIDGET START--> 
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                        <!--Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget" id="wid-id-10" data-widget-editbutton="false">

                            <header>
                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                <h2>Next 6 Months Forecast</h2>
                            </header>

                            <!--widget div-->
                            <div>

                                <!--widget edit box--> 
                                <div class="jarviswidget-editbox">
                                    <!--This area used as dropdown edit box--> 

                                </div>
                                <!--end widget edit box--> 

                                <!--widget content--> 
                                <div class="widget-body no-padding">

                                    <!--<div id="talegendcontainer"></div>-->
                                    <div id="next6monthsforecast" class="chart"></div>

                                </div>
                                <!--end widget content--> 

                            </div>
                            <!--end widget div--> 

                        </div>
                        <!--end widget--> 

                    </article>
                    <!--WIDGET END-->
                </div>
                <!-- end row -->
                <div class="row smart-form" style="padding-bottom: 30px;">
                    <div class="col-xs-12">
                        <header><h4>Dynamic Graphs</h4></header>
                    </div>
                </div>
                <!-- end row-->
                <form:form class="smart-form" novalidate="novalidate" commandName="dashboardform">
                    <div class="row" style="padding-top: 20px;">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-2">
                            <section>
                                <label class="label">From Date <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input id="fromDate" path="fromDate" class="form-control" />
                                    <i class="icon-append fa fa-calendar"></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-2">
                            <section>
                                <label class="label">To Date <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input id="toDate" path="toDate" class="form-control" />
                                    <i class="icon-append fa fa-calendar"></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-2">
                            <section>
                                <label class="label">Target Period <samp style="color: red">*</samp></label> 
                                <label class="select">
                                    <form:select id="targetPeriod" path="targetPeriod">
                                        <form:option value="" label="-- Select --"/>
                                        <form:options itemValue="periodid" itemLabel="description" items="${targetPeriodList}"/>
                                    </form:select><i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row" style="">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-4">
                            <section>
                                <label class="label">Revenue Target</label>
                                <label class="select" style="padding-bottom: 20px;">
                                    <form:select id="target" path="target">
                                        <form:option value="" label="-- Select --"/>
                                    </form:select><i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-4">
                            <div class="row">
                                <div class="col-xs-1"></div>
                                <div class="col-xs-5">
                                    <section>
                                        <label class="label">From Date</label>
                                        <label class="input" style="padding-bottom: 20px;">
                                            <input id="targetFromDate" class="form-control" disabled="disabled"/>
                                            <i class="icon-append fa fa-calendar"></i>
                                        </label>
                                    </section>
                                </div>
                                <div class="col-xs-1"></div>
                                <div class="col-xs-5">
                                    <section>
                                        <label class="label">To Date</label>
                                        <label class="input" style="padding-bottom: 20px;">
                                            <input id="targetToDate" class="form-control" disabled="disabled"/>
                                            <i class="icon-append fa fa-calendar"></i>
                                        </label>
                                    </section>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                </form:form>
                <!-- row -->
                <div class="row">
                    <!--NEW WIDGET START--> 
                    <c:if test="${LeadRatioGraph.leadratio}">
                        <article class="${LeadRationCSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${LeadRationID}" data-widget-editbutton="false">
                                <!-- widget options:
                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
        
                                data-widget-colorbutton="false"
                                data-widget-editbutton="false"
                                data-widget-togglebutton="false"
                                data-widget-deletebutton="false"
                                data-widget-fullscreenbutton="false"
                                data-widget-custombutton="false"
                                data-widget-collapsed="true"
                                data-widget-sortable="false"
        
                                -->
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>Revenue Performance</h2>

                                </header>

                                <!-- widget div-->
                                <div>

                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->

                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <div class="widget-body" style="height: 178px;">
                                        <div class="show-stats">
                                            <div class="row">
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="margin-top: 20px">
                                                    <div id="organizationratio" class="easy-pie-chart txt-color-orangeDark" data-pie-size="120">
                                                        <span class="percent percent-sign"></span>
                                                    </div>
                                                    <span class="easy-pie-title"> Organization
                                                        <!--<i class="fa fa-caret-up icon-color-bad"></i>--> 
                                                    </span>
                                                </div>
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="margin-top: 20px">
                                                    <div id="userratio" class="easy-pie-chart txt-color-blue" data-pie-size="120">
                                                        <span class="percent percent-sign"></span>
                                                    </div>
                                                    <span class="easy-pie-title"> User
                                                        <!--<i class="fa fa-caret-up icon-color-bad"></i>--> 
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- end widget content -->

                                </div>
                                <!-- end widget div -->

                            </div>
                            <!-- end widget -->

                        </article>
                    </c:if>
                    <!--WIDGET END-->
                    <!--NEW WIDGET START--> 
                    <c:if test="${TargetGraph2.tragetgraph}">
                        <article class="${TargetGraph2CSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${TargetGraph2ID}" data-widget-editbutton="false">
                                <!-- widget options:
                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
        
                                data-widget-colorbutton="false"
                                data-widget-editbutton="false"
                                data-widget-togglebutton="false"
                                data-widget-deletebutton="false"
                                data-widget-fullscreenbutton="false"
                                data-widget-custombutton="false"
                                data-widget-collapsed="true"
                                data-widget-sortable="false"
        
                                -->
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>Organization Target vs Forecast vs Achieved Amount</h2>

                                </header>

                                <!-- widget div-->
                                <div>
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body">
                                        <div class="show-stats">
                                            <div class="row">
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                    <div class="row">
                                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                                            <div class="text" style="margin-top: 10px;"> Target </div>
                                                        </div>
                                                        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                                            <span id="target_amount" class="pull-right" style="font-size: 20px;"></span>
                                                        </div>
                                                    </div>
                                                    <div class="progress">
                                                        <div id="target_amount_style" class="progress-bar bg-color-red" style=""></div>
                                                    </div> 
                                                </div>
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                    <div class="row">
                                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                                            <div class="text" style="margin-top: 10px;"> Forecast </div>
                                                        </div>
                                                        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                                            <span id="forecast_amount" class="pull-right" style="font-size: 20px;"></span>
                                                        </div>
                                                    </div>
                                                    <div class="progress">
                                                        <div id="forecast_amount_style" class="progress-bar bg-color-yellow" style=""></div>
                                                    </div> 
                                                </div>
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                    <div class="row">
                                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                                            <div class="text" style="margin-top: 10px;"> Achieved </div>
                                                        </div>
                                                        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                                            <span id="achieved_amount" class="pull-right" style="font-size: 20px;"></span>
                                                        </div>
                                                    </div>
                                                    <div class="progress">
                                                        <div id="achieved_amount_style" class="progress-bar bg-color-greenLight" style=""></div>
                                                    </div> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->
                        </article>
                    </c:if>
                    <!--WIDGET END-->
                </div>
                <!-- end row -->

                <!-- row -->
                <div class="row">
                    <!--NEW WIDGET START--> 
                    <c:if test="${TargetGraph1.barchart}">
                        <article class="${TargetGraph1CSS}">

                            <!--Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${TargetGraph1ID}" data-widget-editbutton="false">

                                <header>
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>Organization Target vs Achievement</h2>
                                </header>

                                <!--widget div-->
                                <div>

                                    <!--widget edit box--> 
                                    <div class="jarviswidget-editbox">
                                        <!--This area used as dropdown edit box--> 

                                    </div>
                                    <!--end widget edit box--> 

                                    <!--widget content--> 
                                    <div class="widget-body no-padding">

                                        <div id="talegendcontainer"></div>
                                        <div id="site-stats" class="chart"></div>

                                    </div>
                                    <!--end widget content--> 

                                </div>
                                <!--end widget div--> 

                            </div>
                            <!--end widget--> 

                        </article>
                    </c:if>
                    <!--WIDGET END-->
                </div>
                <!-- end row -->

                <!-- row -->
                <div class="row">
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                        <!--Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget" id="1" data-widget-editbutton="false">

                            <header>
                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                <h2>Organization Activity Performance</h2>
                            </header>

                            <!--widget div-->
                            <div class="no-padding">

                                <!--widget edit box--> 
                                <div class="jarviswidget-editbox">
                                    <!--This area used as dropdown edit box--> 

                                </div>
                                <!--end widget edit box--> 

                                <!--widget content--> 
                                <div class="widget-body">

                                    <!--<div class="" style="padding-top: 30px;">-->
                                    <div class="row">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div class="col-xs-4">
                                                <div class="row" style="padding-top: 20px;">
                                                    <div class="col-xs-12" style="text-align: center;"><span style="font-size: 20px;">Calls</span></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12" style="text-align: center;"><span id="calls" style="color: threeddarkshadow; font-size: 60px;">0</span></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div id="callactivitytagrtlegend"></div>
                                                        <div id="callactivitytargetgraph" class="chart"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div class="row" style="padding-top: 20px;">
                                                    <div class="col-xs-12" style="text-align: center;"><span style="font-size: 20px;">Proposals</span></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12" style="text-align: center;"><span id="proposals" style="color: threeddarkshadow; font-size: 60px;">0</span></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div id="proposalsactivitytagrtlegend"></div>
                                                        <div id="proposalsactivitytargetgraph" class="chart"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div class="row" style="padding-top: 20px;">
                                                    <div class="col-xs-12" style="text-align: center;"><span style="font-size: 20px;">Visits / Meetings</span></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12" style="text-align: center;"><span id="visits_meetings" style="color: threeddarkshadow; font-size: 60px;">0</span></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div id="visitmeetingactivitytagrtlegend"></div>
                                                        <div id="visitmeetingactivitytargetgraph" class="chart"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>  
                                    </div>
                                    <!--</div>-->

                                </div>
                                <!--end widget content--> 

                            </div>
                            <!--end widget div--> 

                        </div>
                        <!--end widget--> 

                    </article>
                </div>

                <!-- row -->
                <div class="row">
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <!--Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget" id="1" data-widget-editbutton="false">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                <h2>Region Revenue Performance</h2>
                            </header>
                            <!--widget div-->
                            <div>
                                <!--widget edit box--> 
                                <div class="jarviswidget-editbox">
                                    <!--This area used as dropdown edit box--> 
                                </div>
                                <!--end widget edit box--> 
                                <!--widget content--> 
                                <div class="widget-body">
                                    <!--                                    <div class="row">
                                                                            <div class="col-sm-offset-1 col-sm-10">-->
                                    <div id="regionperformancecontent" class="show-stats" style="height: 250px; max-height:250px; overflow-y:scroll;">
                                        <div class="row">

                                        </div>
                                    </div>
                                    <!--                                        </div>
                                                                        </div>-->
                                </div>
                                <!--end widget content--> 

                            </div>
                            <!--end widget div--> 

                        </div>
                        <!--end widget--> 

                    </article>
                </div>

                <!-- row -->
                <div class="row">
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <!--Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget" id="1" data-widget-editbutton="false">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                <h2>Branch Revenue Performance</h2>
                            </header>
                            <!--widget div-->
                            <div>
                                <!--widget edit box--> 
                                <div class="jarviswidget-editbox">
                                    <!--This area used as dropdown edit box--> 
                                </div>
                                <!--end widget edit box--> 
                                <!--widget content--> 
                                <div class="widget-body">
                                    <div id="branchperformancecontent" class="show-stats" style="height: 250px; max-height:250px; overflow-y:scroll;">
                                        <div class="row">
                                            <div class="col-sm-offset-1 col-sm-10" style="padding-top: 20%;">
                                                No records to show
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--end widget content--> 

                            </div>
                            <!--end widget div--> 

                        </div>
                        <!--end widget--> 

                    </article>
                </div>

                <div class="row smart-form" style="padding-bottom: 30px;">
                    <div class="col-xs-12">
                        <header><h4>Organizational Performance</h4></header>
                    </div>
                </div>
                <input id="regionid" type="hidden"/>
                <input id="region_productcategory" type="hidden"/>
                <input id="branchid" type="hidden"/>
                <input id="branch_productcategory" type="hidden"/>
                <input id="employeeid" type="hidden"/>
                <input id="employee_productcategory" type="hidden"/>
                <div class="row">
                    <div class="col-xs-offset-1 col-xs-10">
                        <div class="well">
                            <div class="input-group">
                                <input id="perFromDate" path="perFromDate" class="form-control" placeholder="From Date"/>
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input id="perToDate" path="perToDate" class="form-control" placeholder="To Date"/>
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <div class="input-group-btn">
                                    <button id="searchperformance" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <!-- Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                <h2>Regional Performance</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->

                                <!-- widget content -->
                                <!--<div class="widget-body no-padding">-->
                                <div>
                                    <table id="regional_performance" class="table table-striped table-bordered table-hover" width="100%">
                                        <thead>
                                            <tr>
                                                <th data-class="phone, expand">ID</th>
                                                <th data-class="phone, expand">Product Category ID</th>
                                                <th data-class="phone, expand">Region</th>
                                                <th data-class="phone, expand">Product Category</th>
                                                <th data-hide="phone, expand">RRO</th>
                                                <th data-class="phone, expand">Forecast Lead Amount</th>
                                                <th data-class="phone, tablet">Lead Confirm Amount</th>
                                                <th data-class="phone, tablet"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody></tbody> 
                                    </table>
                                    <!--<div id="bar-graph" class="chart no-padding"></div>-->
                                </div>
                            </div>
                            <!-- end widget content -->
                        </div>
                        <!-- end widget div -->
                    </article>
                    <!-- end widget content -->
                </div>

                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <!-- Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                <h2>Branch Performance</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->

                                <!-- widget content -->
                                <!--<div class="widget-body no-padding">-->
                                <div>
                                    <table id="branch_performance" class="table table-striped table-bordered table-hover" width="100%">
                                        <thead>
                                            <tr>
                                                <th data-class="phone, expand">ID</th>
                                                <th data-class="phone, expand">Product Category ID</th>
                                                <th data-class="phone, expand">Branch</th>
                                                <th data-class="phone, expand">Product Category</th>
                                                <th data-hide="phone, expand">Branch Manager</th>
                                                <th data-class="phone, expand">Forecast Lead Amount</th>
                                                <th data-class="phone, tablet">Lead Confirm Amount</th>
                                                <th data-class="phone, tablet"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody></tbody> 
                                    </table>
                                    <!--<div id="bar-graph" class="chart no-padding"></div>-->
                                </div>
                            </div>
                            <!-- end widget content -->
                        </div>
                        <!-- end widget div -->
                    </article>
                    <!-- end widget content -->
                </div>

                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <!-- Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                <h2>Individual Performance</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->

                                <!-- widget content -->
                                <!--<div class="widget-body no-padding">-->
                                <div>
                                    <table id="individual_performance" class="table table-striped table-bordered table-hover" width="100%">
                                        <thead>
                                            <tr>
                                                <th data-class="phone, expand">ID</th>
                                                <th data-class="phone, expand">Product Category ID</th>
                                                <th data-class="phone, expand">Branch</th>
                                                <th data-class="phone, expand">Product Category</th>
                                                <th data-hide="phone, expand">Marketing Executive</th>
                                                <th data-class="phone, expand">Forecast Lead Amount</th>
                                                <th data-class="phone, tablet">Lead Confirm Amount</th>
                                            </tr>
                                        </thead>
                                        <tbody></tbody> 
                                    </table>
                                    <!--<div id="bar-graph" class="chart no-padding"></div>-->
                                </div>
                            </div>
                            <!-- end widget content -->
                        </div>
                        <!-- end widget div -->
                    </article>
                    <!-- end widget content -->
                </div>
            </div>
            <!-- END MAIN CONTENT -->
        </div>
        <!-- end widget -->

        <!-- PAGE FOOTER -->
        <div class="page-footer">
            <jsp:include page="../template/footer.jsp"/>
        </div>

        <!-- END PAGE FOOTER -->

        <jsp:include page="../template/jsinclide.jsp"/>

    </body>

    <script type="text/javascript">
        // DO NOT REMOVE : GLOBAL FUNCTIONS!
        $(document).ready(function () {
            pageSetUp();

            $('#fromDate').datetimepicker({
                format: 'yyyy-mm-dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1,
                minView: 2
            });

            $('#toDate').datetimepicker({
                format: 'yyyy-mm-dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1,
                minView: 2
            });

            $('#perFromDate').datetimepicker({
                format: 'yyyy-mm-dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1,
                minView: 2
            });

            $('#perToDate').datetimepicker({
                format: 'yyyy-mm-dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1,
                minView: 2
            });

            $('#targetFromDate').datetimepicker({
                format: 'yyyy-mm-dd'
            });

            $('#targetToDate').datetimepicker({
                format: 'yyyy-mm-dd'
            });

            $('#fromDate').change(function () {
                loadTargets();
            });

            $('#toDate').change(function () {
                loadTargets();
            });

            $('#targetPeriod').change(function () {
                loadTargets();
            });

//            var d1 = [[1, 300], [2, 600], [3, 550], [4, 400], [5, 300]];
//            $.plot($("#chartist_chart01"), [d1]);
//            $.plot($("#chartist_chart02"), [d1]);
//            $.plot($("#chartist_chart03"), [d1]);

            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/dashboard/contactleadaccount",
                cache: false,
//                global: false,
                dataType: 'json',
                success: function (response) {
                    var totalCount = 0;
                    var account_count = 0;
                    var lead_count = 0;
                    var contact_count = 0;
                    $.each(response.data, function (key, object) {
                        if (key === 0) {
                            contact_count = object.data[0][0];
                            totalCount += object.data[0][0];
                        } else if (key === 1) {
                            lead_count = object.data[0][0];
                            totalCount += object.data[0][0];
                        } else if (key === 2) {
                            account_count = object.data[0][0];
                            totalCount += object.data[0][0];
                        }
                    });
                    var account_pre = (account_count * 100) / totalCount;
                    var lead_pre = (lead_count * 100) / totalCount;
                    var contact_pre = (contact_count * 100) / totalCount;
                    $('#account_count_style').css('width', account_pre + '%');
                    $('#account_count').html('<b>' + account_count + '</b>');
                    $('#lead_count_style').css('width', lead_pre + '%');
                    $('#lead_count').html('<b>' + lead_count + '</b');
                    $('#contact_count_style').css('width', contact_pre + '%');
                    $('#contact_count').html('<b>' + contact_count + '</b>');
                }, error: function () {
                    console.log("Contact vs Lead vs Accounts.");
                }
            });

            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/dashboard/leadratio",
                cache: false,
//                global: false,
                dataType: 'json',
                success: function (response) {
                    if ($('#leadsucessrationgraph').length) {
                        var plot = $.plot($("#leadsucessrationgraph"), response.data, {
                            series: {
                                pie: {
                                    show: true,
                                    innerRadius: 0.5,
                                    radius: 1,
                                    label: {
                                        show: true,
                                        radius: 2 / 3,
                                        formatter: function (label, series) {
                                            return '<div style="font-size:13px;text-align:center;font-weight: bold; padding:4px;color:black;">' + label + '<br/>' + series.data[0][1] + '%</div>';
                                        },
                                        threshold: 0.1
                                    }
                                }
                            }, legend: {
                                show: true,
                                noColumns: 1, // number of colums in legend table
                                labelFormatter: null, // fn: string -> string
                                labelBoxBorderColor: "#000", // border color for the little label boxes
                                container: null, // container (as jQuery object) to put legend in, null means default on top of graph
                                position: "ne", // position of default legend container within plot
                                margin: [5, 10], // distance from grid edge to default legend container within plot
                                backgroundColor: "#efefef", // null means auto-detect
                                backgroundOpacity: 1 // set to 0 to avoid background
                            }, grid: {
                                hoverable: true,
                                clickable: true
                            }
                        });
                        plot.draw();
                    }
                }, error: function () {
                    console.log("Lead Ratio Graph.");
                }
            });

            $('#target').change(function () {
                var dataObject = new Object();
                dataObject.target = $('#target').val();
                if (dataObject.target) {
                    $.post("${pageContext.servletContext.contextPath}/dashboard/gettarget", dataObject, "json")
                            .done(function (data) {
                                $('#targetFromDate').val($.format.date(new Date(data.targetstartdate), "yyyy-MM-dd"));
                                $('#targetToDate').val($.format.date(new Date(data.targetenddate), "yyyy-MM-dd"));
                            });
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/dashboard/targetGraph",
                        cache: false,
//                        global: false,
                        dataType: 'json',
                        data: dataObject,
                        success: function (response) {
                            if ($("#site-stats").length) {
                                $.plot($("#site-stats"), response, {
                                    legend: {
                                        container: $("#talegendcontainer"),
                                        noColumns: 0
                                    }, series: {
                                        lines: {
                                            show: true,
                                            lineWidth: 2
                                        },
                                        points: {
                                            show: true
                                        },
                                        shadowSize: 0
                                    }, colors: ["#ba6b93", "#4d749b"],
                                    xaxis: {
                                        mode: "time",
                                        timeformat: "%0m-%0d",
                                        ticks: 12,
                                        tickDecimals: 0
                                    }, yaxis: {
                                        ticks: 5,
                                        tickLength: 5,
                                        tickFormatter: function (x) {
                                            return $.number(x);
                                        }
                                    }, yaxes: [{
                                            min: 0,
                                            tickLength: 5
                                        }], grid: {
                                        hoverable: true,
                                        clickable: true,
                                        borderWidth: 0
                                    }, tooltip: true,
                                    tooltipOpts: {
                                        content: "%s for <b>%x </b> is %y",
                                        dateFormat: "%y-%0m-%0d",
                                        defaultTheme: false
                                    }
                                });
                            }
                        },
                        error: function () {
                            console.log("Target graph 01.");
                        }
                    });


                    $('#target_amount_style').css('width', '0%');
                    $('#target_amount').html($.number(0));
                    $('#forecast_amount_style').css('width', '0%');
                    $('#forecast_amount').html($.number(0));
                    $('#achieved_amount_style').css('width', '0%');
                    $('#achieved_amount').html($.number(0));
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/dashboard/targetBarGraph",
                        cache: false,
//                        global: false,
                        dataType: 'json',
                        data: dataObject,
                        success: function (response) {
                            var target = response.target[0][1];
                            var forecast = response.forecast[0][1];
                            var achieved = response.achieved[0][1];
                            var highest = 0;
                            if (target > forecast) {
                                highest = target;
                            } else {
                                highest = forecast;
                            }
                            if (achieved > target) {
                                highest = achieved;
                            }
                            var target_pre = (target * 100) / highest;
                            var forecast_pre = (forecast * 100) / highest;
                            var achieved_pre = (achieved * 100) / highest;
                            $('#target_amount_style').css('width', target_pre + '%');
                            $('#target_amount').html($.number(target));
                            $('#forecast_amount_style').css('width', forecast_pre + '%');
                            $('#forecast_amount').html($.number(forecast));
                            $('#achieved_amount_style').css('width', achieved_pre + '%');
                            $('#achieved_amount').html($.number(achieved));
                        }, error: function () {
                            console.log("Target graph 02.");
                        }
                    });

                    $('#organizationratio').data('easyPieChart').update(0);
                    $('span', $('#organizationratio')).text(0);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/dashboard/organizationratio",
                        cache: false,
//                        global: false,
                        dataType: 'json',
                        data: dataObject,
                        success: function (response) {
                            $('#organizationratio').data('easyPieChart').update(response.data);
                            $('span', $('#organizationratio')).text(response.data);
                        }, error: function () {
                            console.log("Organization Ratio Graph.");
                        }
                    });


                    $('#userratio').data('easyPieChart').update(0);
                    $('span', $('#userratio')).text(0);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/dashboard/userratio",
                        cache: false,
//                        global: false,
                        dataType: 'json',
                        data: dataObject,
                        success: function (response) {
                            $('#userratio').data('easyPieChart').update(response.data);
                            $('span', $('#userratio')).text(response.data);
                        }, error: function () {
                            console.log("User Ratio Graph.");
                        }
                    });

//                    $.ajax({
//                        type: "post",
//                        url: "${pageContext.servletContext.contextPath}/dashboard/activitygraph",
//                        cache: false,
//                        global: false,
//                        dataType: 'json',
//                        data: dataObject,
//                        success: function (response) {
//                            console.log(response);
//                        }, error: function () {
//                            console.log("User Ratio Graph.");
//                        }
//                    });

                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/dashboard/activitiesgraph",
                        cache: false,
//                        global: false,
                        dataType: 'json',
                        data: dataObject,
                        success: function (response) {
                            $('#calls').empty();
                            $('#proposals').empty();
                            $('#visits_meetings').empty();
                            $('#calls').html(response.calls);
                            $('#proposals').html(response.proposals);
                            $('#visits_meetings').html(response.visits_meetings);
                            if ($("#callactivitytargetgraph").length) {
                                $.plot($("#callactivitytargetgraph"), response.callactivitygraph, {
                                    legend: {
                                        container: $("#callactivitytagrtlegend"),
                                        noColumns: 0
                                    }, series: {
                                        lines: {
                                            show: true,
                                            lineWidth: 2
                                        },
                                        points: {
                                            show: true
                                        },
                                        shadowSize: 0
                                    }, colors: ["#ba6b93", "#4d749b"],
                                    xaxis: {
                                        mode: "time",
                                        timeformat: "%0m-%0d",
                                        ticks: 12,
                                        tickDecimals: 0
                                    }, yaxis: {
                                        ticks: 5,
                                        tickLength: 5,
                                        tickFormatter: function (x) {
                                            return $.number(x);
                                        }
                                    }, yaxes: [{
                                            min: 0,
                                            tickLength: 5
                                        }], grid: {
                                        hoverable: true,
                                        clickable: true,
                                        borderWidth: 0
                                    }, tooltip: true,
                                    tooltipOpts: {
                                        content: "%s for <b>%x </b> is %y",
                                        dateFormat: "%y-%0m-%0d",
                                        defaultTheme: false
                                    }
                                });
                            }

                            if ($("#proposalsactivitytargetgraph").length) {
                                $.plot($("#proposalsactivitytargetgraph"), response.proposalactivitygraph, {
                                    legend: {
                                        container: $("#proposalsactivitytagrtlegend"),
                                        noColumns: 0
                                    }, series: {
                                        lines: {
                                            show: true,
                                            lineWidth: 2
                                        },
                                        points: {
                                            show: true
                                        },
                                        shadowSize: 0
                                    }, colors: ["#ba6b93", "#4d749b"],
                                    xaxis: {
                                        mode: "time",
                                        timeformat: "%0m-%0d",
                                        ticks: 12,
                                        tickDecimals: 0
                                    }, yaxis: {
                                        ticks: 5,
                                        tickLength: 5,
                                        tickFormatter: function (x) {
                                            return $.number(x);
                                        }
                                    }, yaxes: [{
                                            min: 0,
                                            tickLength: 5
                                        }], grid: {
                                        hoverable: true,
                                        clickable: true,
                                        borderWidth: 0
                                    }, tooltip: true,
                                    tooltipOpts: {
                                        content: "%s for <b>%x </b> is %y",
                                        dateFormat: "%y-%0m-%0d",
                                        defaultTheme: false
                                    }
                                });
                            }

                            if ($("#visitmeetingactivitytargetgraph").length) {
                                $.plot($("#visitmeetingactivitytargetgraph"), response.visitmeetingactivitygraph, {
                                    legend: {
                                        container: $("#visitmeetingactivitytagrtlegend"),
                                        noColumns: 0
                                    }, series: {
                                        lines: {
                                            show: true,
                                            lineWidth: 2
                                        },
                                        points: {
                                            show: true
                                        },
                                        shadowSize: 0
                                    }, colors: ["#ba6b93", "#4d749b"],
                                    xaxis: {
                                        mode: "time",
                                        timeformat: "%0m-%0d",
                                        ticks: 12,
                                        tickDecimals: 0
                                    }, yaxis: {
                                        ticks: 5,
                                        tickLength: 5,
                                        tickFormatter: function (x) {
                                            return $.number(x);
                                        }
                                    }, yaxes: [{
                                            min: 0,
                                            tickLength: 5
                                        }], grid: {
                                        hoverable: true,
                                        clickable: true,
                                        borderWidth: 0
                                    }, tooltip: true,
                                    tooltipOpts: {
                                        content: "%s for <b>%x </b> is %y",
                                        dateFormat: "%y-%0m-%0d",
                                        defaultTheme: false
                                    }
                                });
                            }
                        }, error: function () {
                            console.log("User Ratio Graph.");
                        }
                    });

                    $.post("${pageContext.servletContext.contextPath}/dashboard/getbranchperformance", dataObject, "json")
                            .done(function (data) {
                                $('#branchperformancecontent').empty();
                                var content = "";
                                $.each(data, function (i, val) {
                                    var highest = 0;
                                    if (val.branchtarget > val.fourecastamount) {
                                        highest = val.branchtarget;
                                    } else {
                                        highest = val.fourecastamount;
                                    }
                                    if (val.achievedamount > val.branchtarget) {
                                        highest = val.achievedamount;
                                    }
                                    var target_pre = (val.branchtarget * 100) / highest;
                                    var forecast_pre = (val.fourecastamount * 100) / highest;
                                    var achieved_pre = (val.achievedamount * 100) / highest;
                                    content += '<div class="row" style="padding-right: 20px; padding-left: 20px;">'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<h5><strong>' + val.branch + '</strong></h5>'
                                            + '</div>'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<div class="row">'
                                            + '<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">'
                                            + '<div class="text" style="margin-top: 10px;"> Target </div>'
                                            + '</div>'
                                            + '<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">'
                                            + '<span class="pull-right" style="font-size: 20px;">' + $.number(val.branchtarget) + '</span>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="progress">'
                                            + '<div class="progress-bar bg-color-red" style="width: ' + target_pre + '%;"></div>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<div class="row">'
                                            + '<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">'
                                            + '<div class="text" style="margin-top: 10px;"> Forecast </div>'
                                            + '</div>'
                                            + '<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">'
                                            + '<span class="pull-right" style="font-size: 20px;">' + $.number(val.fourecastamount) + '<span style="padding-left: 5px;"><strong>(' + Math.round((val.fourecastamount * 100) / val.branchtarget) + '%)</strong></span></span>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="progress">'
                                            + '<div class="progress-bar bg-color-yellow" style="width: ' + forecast_pre + '%;"></div>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<div class="row">'
                                            + '<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">'
                                            + '<div class="text" style="margin-top: 10px;"> Achieved </div>'
                                            + '</div>'
                                            + '<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">'
                                            + '<span class="pull-right" style="font-size: 20px;">' + $.number(val.achievedamount) + '<span style="padding-left: 5px;"><strong>(' + Math.round((val.achievedamount * 100) / val.branchtarget) + '%)</strong></span></span>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="progress">'
                                            + '<div class="progress-bar bg-color-greenLight" style="width: ' + achieved_pre + '%;"></div>'
                                            + '</div>'
                                            + '</div>'
                                            + '</div>';
                                });
                                $('#branchperformancecontent').html(content);
                                if ($('#branchperformancecontent').is(':empty')) {
                                    $('#branchperformancecontent').html('<div class="row">'
                                            + '<div class="col-sm-offset-1 col-sm-10" style="padding-top: 6%; font-size: 50px; color: rgba(0,0,0,.2); text-align: center;"><span style="display: inline-block; vertical-align: middle; line-height: normal;">No records to show</span></div>'
                                            + '</div>');
                                }
                            });

                    $.post("${pageContext.servletContext.contextPath}/dashboard/getregionperformance", dataObject, "json")
                            .done(function (data) {
                                $('#regionperformancecontent').empty();
                                var content = "";
                                $.each(data, function (i, val) {
                                    var highest = 0;
                                    if (val.regiontarget > val.fourecastamount) {
                                        highest = val.regiontarget;
                                    } else {
                                        highest = val.fourecastamount;
                                    }
                                    if (val.achievedamount > val.regiontarget) {
                                        highest = val.achievedamount;
                                    }
                                    var target_pre = (val.regiontarget * 100) / highest;
                                    var forecast_pre = (val.fourecastamount * 100) / highest;
                                    var achieved_pre = (val.achievedamount * 100) / highest;
                                    content += '<div class="row" style="padding-right: 20px; padding-left: 20px;">'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<h5><strong>' + val.region + '</strong></h5>'
                                            + '</div>'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<div class="row">'
                                            + '<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">'
                                            + '<div class="text" style="margin-top: 10px;"> Target </div>'
                                            + '</div>'
                                            + '<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">'
                                            + '<span class="pull-right" style="font-size: 20px;">' + $.number(val.regiontarget) + '</span>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="progress">'
                                            + '<div class="progress-bar bg-color-red" style="width: ' + target_pre + '%;"></div>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<div class="row">'
                                            + '<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">'
                                            + '<div class="text" style="margin-top: 10px;"> Forecast </div>'
                                            + '</div>'
                                            + '<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">'
                                            + '<span class="pull-right" style="font-size: 20px;">' + $.number(val.fourecastamount) + '<span style="padding-left: 5px;"><strong>(' + Math.round((val.fourecastamount * 100) / val.regiontarget) + '%)</strong></span></span>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="progress">'
                                            + '<div class="progress-bar bg-color-yellow" style="width: ' + forecast_pre + '%;"></div>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">'
                                            + '<div class="row">'
                                            + '<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">'
                                            + '<div class="text" style="margin-top: 10px;"> Achieved </div>'
                                            + '</div>'
                                            + '<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">'
                                            + '<span class="pull-right" style="font-size: 20px;">' + $.number(val.achievedamount) + '<span style="padding-left: 5px;"><strong>(' + Math.round((val.achievedamount * 100) / val.regiontarget) + '%)</strong></span></span>'
                                            + '</div>'
                                            + '</div>'
                                            + '<div class="progress">'
                                            + '<div class="progress-bar bg-color-greenLight" style="width: ' + achieved_pre + '%;"></div>'
                                            + '</div>'
                                            + '</div>'
                                            + '</div>';
                                });
                                $('#regionperformancecontent').html(content);
                                if ($('#regionperformancecontent').is(':empty')) {
                                    $('#regionperformancecontent').html('<div class="row">'
                                            + '<div class="col-sm-offset-1 col-sm-10" style="padding-top: 6%; font-size: 50px; color: rgba(0,0,0,.2); text-align: center;"><span style="display: inline-block; vertical-align: middle; line-height: normal;">No records to show</span></div>'
                                            + '</div>');
                                }
                            });
                } else {
                    initGraphs();
                }
            });

            initGraphs();

            var breakpointDefinition = {
                tablet: 1024,
                phone: 480
            };

            var regional_performance = undefined;
            var regional_tb = $('#regional_performance').dataTable({
                "bProcessing": true,
                "bServerSide": true,
                "bFilter": false,
                "sPaginationType": "full_numbers",
                "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                        "t" +
                        "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!regional_performance) {
                        regional_performance = new ResponsiveDatatablesHelper($('#regional_performance'), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    regional_performance.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    regional_performance.respond();
                },
                "sAjaxSource": "${pageContext.servletContext.contextPath}/regionalperformance",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    aoData.push({"name": "perFromDate", "value": $('#perFromDate').val()});
                    aoData.push({"name": "perToDate", "value": $('#perToDate').val()});
                    $.ajax({
                        "dataType": 'json',
                        "type": "GET",
                        "url": sSource,
                        "data": aoData,
                        "global": false,
                        "success": fnCallback
                    });
                },
                "order": [[2, "asc"]],
                "aoColumns": [
                    {"mDataProp": "id", "bSortable": false, "sClass": "hidetablecolumn"},
                    {"mDataProp": "productcategoryid", "bSortable": false, "sClass": "hidetablecolumn"},
                    {"mDataProp": "territory", "bSortable": true},
                    {"mDataProp": "productcategory", "bSortable": true},
                    {"mDataProp": "employeename", "bSortable": true},
                    {"mDataProp": "leadforecast", "bSortable": true, "sClass": "numericCol"},
                    {"mDataProp": "leadconfirmed", "bSortable": true, "sClass": "numericCol"},
                    {"mDataProp": "action", "bSortable": false, "sClass": "actiontablecolumn"}
                ],
                "aoColumnDefs": [
                    {"aTargets": [5, 6],
                        "mRender": function (data, type, full) {
                            return $.number(data, 2);
                        }
                    }
                ]
            });

            var branch_performance = undefined;
            var branch_tb = $('#branch_performance').dataTable({
                "bProcessing": true,
                "bServerSide": true,
                "bFilter": false,
                "sPaginationType": "full_numbers",
                "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                        "t" +
                        "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!branch_performance) {
                        branch_performance = new ResponsiveDatatablesHelper($('#branch_performance'), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    branch_performance.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    branch_performance.respond();
                },
                "sAjaxSource": "${pageContext.servletContext.contextPath}/branchperformance",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    aoData.push({"name": "regionid", "value": $("#regionid").val()});
                    aoData.push({"name": "region_productcategory", "value": $("#region_productcategory").val()});
                    aoData.push({"name": "perFromDate", "value": $('#perFromDate').val()});
                    aoData.push({"name": "perToDate", "value": $('#perToDate').val()});
                    $.ajax({
                        "dataType": 'json',
                        "type": "GET",
                        "url": sSource,
                        "data": aoData,
                        "global": false,
                        "success": fnCallback
                    });
                },
                "order": [[2, "asc"]],
                "aoColumns": [
                    {"mDataProp": "id", "bSortable": false, "sClass": "hidetablecolumn"},
                    {"mDataProp": "productcategoryid", "bSortable": false, "sClass": "hidetablecolumn"},
                    {"mDataProp": "territory", "bSortable": true},
                    {"mDataProp": "productcategory", "bSortable": true},
                    {"mDataProp": "employeename", "bSortable": true},
                    {"mDataProp": "leadforecast", "bSortable": true, "sClass": "numericCol"},
                    {"mDataProp": "leadconfirmed", "bSortable": true, "sClass": "numericCol"},
                    {"mDataProp": "action", "bSortable": false, "sClass": "actiontablecolumn"}
                ],
                "aoColumnDefs": [
                    {"aTargets": [5, 6],
                        "mRender": function (data, type, full) {
                            return $.number(data, 2);
                        }
                    }
                ]
            });

            var individual_performance = undefined;
            var individual_tb = $('#individual_performance').dataTable({
                "bProcessing": true,
                "bServerSide": true,
                "bFilter": false,
                "sPaginationType": "full_numbers",
                "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                        "t" +
                        "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!individual_performance) {
                        individual_performance = new ResponsiveDatatablesHelper($('#individual_performance'), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    individual_performance.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    individual_performance.respond();
                },
                "sAjaxSource": "${pageContext.servletContext.contextPath}/individualperformance",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    aoData.push({"name": "branchid", "value": $("#branchid").val()});
                    aoData.push({"name": "branch_productcategory", "value": $("#branch_productcategory").val()});
                    aoData.push({"name": "perFromDate", "value": $('#perFromDate').val()});
                    aoData.push({"name": "perToDate", "value": $('#perToDate').val()});
                    $.ajax({
                        "dataType": 'json',
                        "type": "GET",
                        "url": sSource,
                        "data": aoData,
                        "global": false,
                        "success": fnCallback
                    });
                },
                "order": [[2, "asc"]],
                "aoColumns": [
                    {"mDataProp": "id", "bSortable": false, "sClass": "hidetablecolumn"},
                    {"mDataProp": "productcategoryid", "bSortable": false, "sClass": "hidetablecolumn"},
                    {"mDataProp": "territory", "bSortable": true},
                    {"mDataProp": "productcategory", "bSortable": true},
                    {"mDataProp": "employeename", "bSortable": true},
                    {"mDataProp": "leadforecast", "bSortable": true, "sClass": "numericCol"},
                    {"mDataProp": "leadconfirmed", "bSortable": true, "sClass": "numericCol"}
                ],
                "aoColumnDefs": [
                    {"aTargets": [5, 6],
                        "mRender": function (data, type, full) {
                            return $.number(data, 2);
                        }
                    }
                ]
            });

            $('#searchperformance').click(function () {
                $('#regionid').val('');
                $('#region_productcategory').val('');
                $('#branchid').val('');
                $('#branch_productcategory').val('');
                $('#employeeid').val('');
                $('#employee_productcategory').val('');
                regional_tb.fnDraw();
                branch_tb.fnDraw();
                individual_tb.fnDraw();
            });

            $(document).on('click', '#regional_performance tbody tr .row_both', function () {
                console.log('.row_both');
                $('#regionid').val($(this).closest('tr').find('td:eq(0)').text());
                $('#region_productcategory').val('');
                branch_tb.fnDraw();
            });

            $(document).on('click', '#regional_performance tbody tr .row_crdtorintr', function () {
                console.log('.row_credit');
                $('#regionid').val($(this).closest('tr').find('td:eq(0)').text());
                $('#region_productcategory').val($(this).closest('tr').find('td:eq(1)').text());
                branch_tb.fnDraw();
            });

            $(document).on('click', '#branch_performance tbody tr .row_both', function () {
                $('#branchid').val($(this).closest('tr').find('td:eq(0)').text());
                $('#branch_productcategory').val('');
                individual_tb.fnDraw();
            });

            $(document).on('click', '#branch_performance tbody tr .row_crdtorintr', function () {
                $('#branchid').val($(this).closest('tr').find('td:eq(0)').text());
                $('#branch_productcategory').val($(this).closest('tr').find('td:eq(1)').text());
                individual_tb.fnDraw();
            });

            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/dashboard/next6thmonthsforecast",
                cache: false,
//                        global: false,
                dataType: 'json',
                success: function (response) {
                    Morris.Area({
                        element: 'next6monthsforecast',
                        data: response.data,
                        xkey: response.xkey,
                        ykeys: response.ykeys,
                        labels: response.labels,
                        pointSize: 2,
                        hideHover: 'auto'
                    });
                }, error: function () {
                    console.log("User Ratio Graph.");
                }
            });

        });

        function initGraphs() {
            if ($("#site-stats").length) {
                $.plot($("#site-stats"), [[]], {
                    legend: {
                        container: $("#talegendcontainer"),
                        noColumns: 0
                    }, series: {
                        lines: {
                            show: true,
                            lineWidth: 2
                        },
                        points: {
                            show: true
                        },
                        shadowSize: 0
                    }, colors: ["#ba6b93", "#4d749b"],
                    xaxis: {
                        mode: "time",
                        timeformat: "%0m-%0d",
                        ticks: 12,
                        tickDecimals: 0
                    }, yaxis: {
                        ticks: 1,
                        tickLength: 5,
                        tickFormatter: function (x) {
                            return $.number(x);
                        }
                    }, yaxes: [{
                            min: 0,
                            tickLength: 1
                        }], grid: {
                        hoverable: true,
                        clickable: true,
                        borderWidth: 0
                    }, tooltip: true,
                    tooltipOpts: {
                        content: "%s for <b>%x </b> is %y",
                        dateFormat: "%y-%0m-%0d",
                        defaultTheme: false
                    }
                });
            }

            $('#target_amount_style').css('width', 0 + '%');
            $('#target_amount').html('');
            $('#forecast_amount_style').css('width', 0 + '%');
            $('#forecast_amount').html('');
            $('#achieved_amount_style').css('width', 0 + '%');
            $('#achieved_amount').html('');

            $('#organizationratio').data('easyPieChart').update(0);
            $('span', $('#organizationratio')).text(0);

            $('#userratio').data('easyPieChart').update(0);
            $('span', $('#userratio')).text(0);

            $('#calls').empty();
            $('#proposals').empty();
            $('#visits_meetings').empty();
            $('#calls').html(0);
            $('#proposals').html(0);
            $('#visits_meetings').html(0);
            if ($("#callactivitytargetgraph").length) {
                $.plot($("#callactivitytargetgraph"), [[]], {
                    legend: {
                        container: $("#callactivitytagrtlegend"),
                        noColumns: 0
                    }, series: {
                        lines: {
                            show: true,
                            lineWidth: 2
                        },
                        points: {
                            show: true
                        },
                        shadowSize: 0
                    }, colors: ["#ba6b93", "#4d749b"],
                    xaxis: {
                        mode: "time",
                        timeformat: "%0m-%0d",
                        ticks: 12,
                        tickDecimals: 0
                    }, yaxis: {
                        ticks: 1,
                        tickLength: 5,
                        tickFormatter: function (x) {
                            return $.number(x);
                        }
                    }, yaxes: [{
                            min: 0,
                            tickLength: 5
                        }], grid: {
                        hoverable: true,
                        clickable: true,
                        borderWidth: 0
                    }, tooltip: true,
                    tooltipOpts: {
                        content: "%s for <b>%x </b> is %y",
                        dateFormat: "%y-%0m-%0d",
                        defaultTheme: false
                    }
                });
            }

            if ($("#proposalsactivitytargetgraph").length) {
                $.plot($("#proposalsactivitytargetgraph"), [[]], {
                    legend: {
                        container: $("#proposalsactivitytagrtlegend"),
                        noColumns: 0
                    }, series: {
                        lines: {
                            show: true,
                            lineWidth: 2
                        },
                        points: {
                            show: true
                        },
                        shadowSize: 0
                    }, colors: ["#ba6b93", "#4d749b"],
                    xaxis: {
                        mode: "time",
                        timeformat: "%0m-%0d",
                        ticks: 12,
                        tickDecimals: 0
                    }, yaxis: {
                        ticks: 1,
                        tickLength: 5,
                        tickFormatter: function (x) {
                            return $.number(x);
                        }
                    }, yaxes: [{
                            min: 0,
                            tickLength: 5
                        }], grid: {
                        hoverable: true,
                        clickable: true,
                        borderWidth: 0
                    }, tooltip: true,
                    tooltipOpts: {
                        content: "%s for <b>%x </b> is %y",
                        dateFormat: "%y-%0m-%0d",
                        defaultTheme: false
                    }
                });
            }

            if ($("#visitmeetingactivitytargetgraph").length) {
                $.plot($("#visitmeetingactivitytargetgraph"), [[]], {
                    legend: {
                        container: $("#visitmeetingactivitytagrtlegend"),
                        noColumns: 0
                    }, series: {
                        lines: {
                            show: true,
                            lineWidth: 2
                        },
                        points: {
                            show: true
                        },
                        shadowSize: 0
                    }, colors: ["#ba6b93", "#4d749b"],
                    xaxis: {
                        mode: "time",
                        timeformat: "%0m-%0d",
                        ticks: 12,
                        tickDecimals: 0
                    }, yaxis: {
                        ticks: 1,
                        tickLength: 5,
                        tickFormatter: function (x) {
                            return $.number(x);
                        }
                    }, yaxes: [{
                            min: 0,
                            tickLength: 5
                        }], grid: {
                        hoverable: true,
                        clickable: true,
                        borderWidth: 0
                    }, tooltip: true,
                    tooltipOpts: {
                        content: "%s for <b>%x </b> is %y",
                        dateFormat: "%y-%0m-%0d",
                        defaultTheme: false
                    }
                });
            }
            $('#regionperformancecontent').empty();
            if ($('#regionperformancecontent').is(':empty')) {
                $('#regionperformancecontent').html('<div class="row">'
                        + '<div class="col-sm-offset-1 col-sm-10" style="padding-top: 6%; font-size: 50px; color: rgba(0,0,0,.2); text-align: center;"><span style="display: inline-block; vertical-align: middle; line-height: normal;">No records to show</span></div>'
                        + '</div>');
            }
            $('#branchperformancecontent').empty();
            if ($('#branchperformancecontent').is(':empty')) {
                $('#branchperformancecontent').html('<div class="row">'
                        + '<div class="col-sm-offset-1 col-sm-10" style="padding-top: 6%; font-size: 50px; color: rgba(0,0,0,.2); text-align: center;"><span style="display: inline-block; vertical-align: middle; line-height: normal;">No records to show</span></div>'
                        + '</div>');
            }
        }

        function loadTargets() {
            var fromDate = $('#fromDate').val();
            var toDate = $('#toDate').val();
            var targetPeriod = $('#targetPeriod').val();
            if (fromDate && toDate && targetPeriod) {
                $('#target').empty();
                $('#targetFromDate').val('');
                $('#targetToDate').val('');
                $('#regionperformancecontent').empty();
                $('#branchperformancecontent').empty();
                $.post("${pageContext.servletContext.contextPath}/dashboard/targetdropdown", {fromDate: fromDate, toDate: toDate, targetPeriod: targetPeriod}, "json")
                        .done(function (data) {
                            $('#target').append($("<option></option>")
                                    .attr("value", '')
                                    .text('-- Select --'));
                            $.each(data, function (key, value) {
                                $('#target').append($("<option></option>")
                                        .attr("value", key)
                                        .text(value));
                                $('#target').trigger('change');
                            });
                        });
            }
        }
    </script>
</html>
