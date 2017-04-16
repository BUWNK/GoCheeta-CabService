<%-- 
    Document   : revenuetarget
    Created on : Jul 1, 2016, 3:08:03 PM
    Author     : ISURU
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-us">
    <head>
        <jsp:include page="../template/cssinclude.jsp"/>
        <style>
            .columnhide{
                display: none
            }
        </style>
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
                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li> <a href="${pageContext.servletContext.contextPath}/">Target Management</a></li><li>Update Target</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-12">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i>
                            Target Management
                            <span>
                                Update Target
                            </span>
                        </h1>
                    </div>
                </div>

                <article class="col-sm-12 col-md-12 col-lg-12">

                    <!-- Widget ID (each widget will need unique ID)-->
                    <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" data-widget-deletebutton="false">
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
                            <span class="widget-icon"> <i class="fa fa-check"></i> </span>
                            <h2>Target Setting </h2>

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

                                <div class="row">

                                    <div id="bootstrap-wizard-1" class="col-sm-12">

                                        <div class="form-bootstrapWizard">
                                            <ul class="bootstrapWizard form-wizard">
                                                <li class="active" data-target="#step1">
                                                    <a href="#tab1" data-toggle="tab"> <span class="step"  id="step01">1</span> <span class="title">Organization</span> </a>
                                                </li>
                                                <li data-target="#step2">
                                                    <a href="#tab2" data-toggle="tab"> <span class="step"  id="step02">2</span> <span class="title">Regional</span> </a>
                                                </li>
                                                <li data-target="#step3">
                                                    <a href="#tab3" data-toggle="tab"> <span class="step"  id="step03">3</span> <span class="title">Branch</span> </a>
                                                </li>
                                                <li data-target="#step4" >
                                                    <a href="#tab4" data-toggle="tab"> <span class="step"  id="step04">4</span> <span class="title">Team Revenue</span> </a>
                                                </li>
                                                <li data-target="#step5" >
                                                    <a href="#tab5" data-toggle="tab"> <span class="step"  id="step05">5</span> <span class="title">Team Activity</span> </a>
                                                </li>
                                                <li data-target="#step6" >
                                                    <a href="#tab6" data-toggle="tab"> <span class="step"  id="step06">6</span> <span class="title">Finish</span> </a>
                                                </li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                        <form:form class="smart-form" id="wizard-1" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userolesection" method="post" >
                                            <br><br>
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <div id="msg_dev" class="col-xs-10">
                                                    <c:if test="${not empty successMsg}">
                                                        <div class="alert alert-success">
                                                            <strong>Success!</strong> ${successMsg}
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${not empty errorMsg}">
                                                        <div class="alert alert-warning">
                                                            <strong>Warning!</strong> ${errorMsg}
                                                        </div>
                                                        <br/>
                                                    </c:if>
                                                </div>
                                                <div class="col-xs-1"></div>
                                            </div>
                                            <form:input  type="hidden" id="multiregionalarray" path="multiregionalarray"/>
                                            <form:input  type="hidden" id="targetnotassignregion" path="targetnotassignregion"/>
                                            <form:input  type="hidden" id="multibrancharray" path="multibrancharray"/>
                                            <form:input  type="hidden" id="targetnotassignbranch" path="targetnotassignbranch"/>
                                            <form:input  type="hidden" id="regionaltargetlist" path="regionaltargetlist"/>
                                            <form:input  type="hidden" id="brachtargetlist" path="brachtargetlist"/>
                                            <form:input  type="hidden" id="activitytargettbl" path="activitytargettbl"/>
                                            <form:input  type="hidden" id="datatabledata" path="datatabledata"/>
                                            <form:input  type="hidden" id="regionactivity" path="regionactivity"/>
                                            <form:input  type="hidden" id="gentargetid" path="targetid"/>
                                            <form:input type="hidden"  id="tabfivetotalbranchgroprevene" path="tab5totalrevenue"  readonly="true"/>
                                            <form:input type="hidden"  id="totalbranchgroprevene" path="tab5totalrevenue" readonly="true"/>

                                            <%--<form:input  type="hidden" id="totalamount" path="totalvalue"/>--%>
                                            <form:input  type="hidden" id="currentamout" path="tab2totalrevenue"/>
                                            <form:input  type="hidden" id="currentamouttabtwo" path="tab2totalrevenue"/>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab1">
                                                    <br><br>
                                                    <h3><strong>Step 1 </strong> - Organization</h3>
                                                    <label id="tab1"  ></label>
                                                    <!--<div class="row">-->
                                                    <%-- ====================================================================================================
                                                                                          START  Organization Target
                                                      ====================================================================================================--%>
                                                    <header>Organization Revenue</header>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>

                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Product<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="productid" path="productid" items="${productList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target Period<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select  id="targetperiodid" path="targetperiodid" items="${sectionList}" onchange="activedatime();"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="form-group">
                                                                <label class="label">Target Start Date:<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input disabled="true" 
                                                                                path="targetstartdate" 
                                                                                id="datetimepickerfromdate" 
                                                                                class="form-control"  
                                                                                data-date-format="yyyy-mm-dd" 
                                                                                placeholder="From Date"/>
                                                                    <i class="icon-append fa fa-calendar"></i>
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="form-group">
                                                                <label class="label">Target End Date:<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input path="targetenddate" 
                                                                                disabled="true" 
                                                                                id="datetimepickertodate" 
                                                                                class="form-control"  
                                                                                data-date-format="yyyy-mm-dd" 
                                                                                placeholder="End Date"/>
                                                                    <i class="icon-append fa fa-calendar"></i>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <label class="label">Description<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input path="tragetdes" type="text" name="tragetdes" value="${fullname}"  maxlength="64"/><i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target Amount<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input path="revenue" type="text" name="revenue"    /><i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <!--</div>-->
                                                    <%--====================================================================================================
                                                                                          END  Organization Target
                                                    ====================================================================================================--%>
                                                    <%--====================================================================================================
                                                                                          START Activity Target
                                                      ====================================================================================================--%>
                                                    <header>Organization Activity</header>
                                                    <div class="row" style="margin-top:70px;">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-md-8 ">
                                                            <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th data-hide="phone" >Activity ID</th>
                                                                        <th data-hide="phone">Activity Type</th>
                                                                        <th data-class="phone"><p class="text-right">Count</p></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                            </table> 
                                                        </div>
                                                    </div>
                                                    <%--====================================================================================================
                                                                                          START Activity Target
                                                      ====================================================================================================--%>
                                                </div>
                                                <%--====================================================================================================
                                                                                    =====  end of the first step  ======
                                                  ====================================================================================================--%>
                                                <!--</div>-->
                                                <div class="tab-pane" id="tab2">
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev2" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br> <br> 
                                                    <%--====================================================================================================
                                                                                                                                              =====  START Organization Target  ======
                                                                                                            ====================================================================================================--%>
                                                    <h3><strong>Step 2</strong> - Region <label id="organizattionWStarget"></label></h3>
                                                    <div class="row">
                                                        <br>
                                                        <header>Region Revenue</header>
                                                        <br>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target description<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="targetid" path="targetid" items="${taget}"  onchange="loadregionlist();"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target Amount<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input id="totalrevenue" path="tab2revenue" readonly="true" value="0" />
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>

                                                    <%--====================================================================================================
                                                                                                                                           =====  END Organization Target  ======
                                                                                                         ====================================================================================================--%>
                                                    <%--====================================================================================================
                                                                                        =====  START Region Activity  ======
                                                      ====================================================================================================--%>
                                                    <!--<div class="row" style="margin-left:220px ; margin-top:50px">-->
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-md-8">
                                                            <table id="dt_basic2" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th data-hide="phone" style="display: none">Region ID</th>
                                                                        <th data-hide="phone">Region Name</th>
                                                                        <th data-hide="phone" style="width:145px" class="text-right">Amount</th>
                                                                    </tr>
                                                                </thead>
                                                                <tfoot>
                                                                    <tr>
                                                                        <th style="display: none"></th>
                                                                        <th  style="text-align:right">Total:</th>
                                                                        <th id="toatal"></th>
                                                                    </tr>
                                                                </tfoot>
                                                                <tbody>
                                                                </tbody>
                                                            </table> 
                                                        </div>
                                                        <br>

                                                    </div>

                                                    <%--====================================================================================================
                                                                                        =====  END Region Activity  ======
                                                      ====================================================================================================--%>
                                                    <%--====================================================================================================
                                                                                        =====  START Region Activity  ======
                                                      ====================================================================================================--%>
                                                    <header>Region Activity</header>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev3" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br> <br>
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Region<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <%--<form:select id="regionalid" path="regionalid" onchange=""/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Activity Type<samp style="color: red">*</samp></label>
                                                <label class="select">
                                                    <%--<form:select id="targetactivityid" path="targetactivityid" onchange="targetactivitycount()"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Total Count<samp style="color: red">*</samp></label>
                                                                                                                    <label class="input">
                                                    <%--<form:input id="tab2totalcount" readonly="true" path="tab2totalcount" />--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Count<samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <%--<form:input id="tab2count" path="tab2count" value="0" />--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->

                                                    <!--<input type="button" id="addRow"  class="btn pull-right btn btn-primary btn btn-sm" value="Save"/>-->
                                                    <input id="ccount" type="hidden"  value="0" />
                                                    <input id="pcount" type="hidden"  value="0"/>
                                                    <input id="vpcount" type="hidden"  value="0" />
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <table id="dt_basic3" class="table table-striped table-bordered table-hover" width="100%">

                                                                <!--                                                                <thead>			                
                                                                                                                                    <tr>-->
                                                                <!--                                                                        <th data-hide="phone" style="display: none">Region Id</th>
                                                                                                                                        <th data-hide="phone">Region Name</th>
                                                                                                                                        <th data-hide="phone" style="display: none">Activity Id</th>
                                                                                                                                        <th data-hide="phone">Activity Name</th>
                                                                                                                                        <th data-hide="phone" style="width:201px" class="hasinput">Count</th>
                                                                                                                                        <th data-hide="phone" style="width:201px" class="hasinput">Action</th>-->
                                                                <!--                                                                    </tr>
                                                                                                                                </thead>-->

                                                                <!--<tbody></tbody>-->

                                                            </table> 
                                                        </div>

                                                    </div>
                                                    <%--====================================================================================================
                                                                                            =====  END Region Activity  ======
                                                    ====================================================================================================--%>
                                                </div>
                                                <%--====================================================================================================
                                                                                   =====  END tab two  ======
                                                 ====================================================================================================--%>
                                                <div class="tab-pane" id="tab3">
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev4" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <%--====================================================================================================
                                                                                                                                          =====  START Branch Target  ======
                                                     ====================================================================================================--%>
                                                    <br> <br>
                                                    <h3><strong>Step 3</strong> - Branch <label id="branchtarget"></label></h3>
                                                    <br>
                                                    <div class="row">
                                                        <header>Branch Revenue</header>
                                                        <br>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target Description<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="tabthreetargetid" path="targetid" items="${taget}" onchange="loadRegionDropdownList();"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>



                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Region<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="regionaltargetid" path="regionaltargetid"  onchange="loadBrnachlist()" />
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>


                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Total Revenue<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input id="tab3totalrevenue" readonly="true" path="tab3totalrevenue" />
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>

                                                    <div class="row" >
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <table id="dt_basic4" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th data-hide="phone" style="display: none">Branch ID</th>
                                                                        <th data-hide="phone">Branch Name</th>
                                                                        <th data-hide="phone" style="width:201px" class="hasinput">Amount</th>
                                                                    </tr>
                                                                </thead>
                                                                <tfoot>
                                                                    <tr>
                                                                        <th style="display: none"></th>
                                                                        <th style="text-align:right">Total:</th>
                                                                        <th id="brnachtoatal"></th>
                                                                    </tr>
                                                                </tfoot>
                                                                <tbody>
                                                                </tbody>
                                                            </table> 
                                                        </div>
                                                        <br>

                                                    </div>
                                                    <%--====================================================================================================
                                                                                       =====  END Branch Target  ======
                                                     ====================================================================================================--%>
                                                    <%--====================================================================================================
                                                                                       =====  START Branch Activity ======
                                                     ====================================================================================================--%>

                                                    <header>Branch Activity</header>
                                                    <br><div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev5" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div> <br> <br>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Region<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="tabthreeregionalid" path="regionalid" onchange="tabthreeloadActivityList()"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <!--                                                        <div class="col-xs-2"></div>
                                                                                                                <div class="col-xs-3">
                                                                                                                    <section>
                                                                                                                        <label class="label">Activity Type<samp style="color: red">*</samp></label>
                                                                                                                        <label class="select">
                                                        <%--<form:select id="barnchtargetactivitiy" path="targetactivityid" onchange="loadBranchActivitycount()"/>--%>
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>-->


                                                    </div>
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Branch<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <%--<form:select id="tabthreebranchid" path="branchid" onchange="brachlistclick();"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>     
                                    </div>-->
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Total Count<samp style="color: red">*</samp></label>
                                                                                                                    <label class="input">
                                                    <%--<form:input id="barnchtotalcount" path="tab3totalcount" readonly="true"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Count<samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <%--<form:input id="BAcount" path="tab3count" value="0" />--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->
                                                    <!--<button type="button" id="addBranchActivityRow" class="btn pull-right btn btn-primary btn btn-sm">Save</button>-->
                                                    <input id="bccount" type="hidden" value="0" />
                                                    <input id="bpcount" type="hidden"  value="0"/>
                                                    <input id="bvpcount" type="hidden"  value="0" />
                                                    <div class="row">
                                                        <div class="col-md-2 "></div>
                                                        <div class="col-md-8 ">
                                                            <table id="dt_basic5" class="table table-striped table-bordered table-hover" width="100%">
                                                                <!--                                                                <thead>			                
                                                                                                                                    <tr>
                                                                                                                                        <th data-hide="phone" style="display: none">Region Activity Id</th>
                                                                                                                                        <th data-hide="phone">Region Activity Name</th>  
                                                                                                                                        <th data-hide="phone" style="display: none">Branch Id</th>     
                                                                                                                                        <th data-hide="phone">Branch Name</th>     
                                                                                                                                        <th data-hide="phone" >Count</th>
                                                                                                                                        <th data-hide="phone" >Action</th>
                                                                                                                                    </tr>
                                                                                                                                </thead>
                                                                                                                                <tbody>
                                                                                                                                </tbody>-->
                                                            </table> 
                                                        </div>

                                                    </div>
                                                    <%--====================================================================================================
                                                                                       =====  END Branch Activity ======
                                                     ====================================================================================================--%>
                                                </div>
                                                <%--====================================================================================================
                                                                                   =====  end tan three ======
                                                 ====================================================================================================--%>

                                                <div class="tab-pane" id="tab4">
                                                    <br><br>
                                                    <h3><strong>Step 4</strong> - Team Revenue <label id="branchtarget"></label></h3>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br><br>
                                                    <%--====================================================================================================
                                                                                       =====  Team Revenue Target ======
                                                     ====================================================================================================--%>
                                                    <div class="row">
                                                        <header>Team Revenue</header>
                                                        <br>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target Description<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="tabfourtargetid" path="targetid" items="${taget}" onchange="loadtabforRegionDropdownList();"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Region<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="tabfourregionalid" path="regionalid" onchange="loadbranchregionlist()"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>

                                                    </div>
                                                    <div class="row"> 
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Branch<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="brnachtargetid" path="brnachtargetid"  onchange="getBranchTarget()"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <!--                                                        <div class="col-xs-3">
                                                                                                                    <section>
                                                                                                                        <label class="label">Group<samp style="color: red">*</samp></label>
                                                                                                                        <label class="select">
                                                        <%--<form:select id="branchgroupid" path="branchgroupid" items="${branchgroup}" onchange="branchgorupchange();" />--%>
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>-->



                                                    </div>
                                                    <div class="row">

                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Total Amount<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input id="barnchamount" path="tab4totalrevenue" readonly="true" />
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <!--<label class="label">Amount<samp style="color: red">*</samp></label>-->
                                                                <label class="input">
                                                                    <form:hidden id="branchgroupamount" path="tab4revenue" />
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>

                                                    <!--<button type="button" id="addBrnachGroupTarget" class="btn pull-right btn btn-primary btn btn-sm">Save</button>-->

                                                    <div class="row">
                                                        <div class="col-md-2 "></div>
                                                        <div class="col-md-8 ">
                                                            <table id="dt_basic6" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th data-hide="phone" style="display: none">Region Brach Id</th>
                                                                        <th data-hide="phone" style="display: none">Region Brach Id</th>
                                                                        <th data-hide="phone">Region Brach Name</th>
                                                                        <th data-hide="phone" style="display: none">Group Id</th>
                                                                        <th data-hide="phone" style="width:201px" class="hasinput">Group Name</th>
                                                                        <th data-hide="phone" style="width:201px" class="hasinput">Amount</th>
                                                                        <th data-hide="phone" style="width:201px" class="hasinput">Action</th>

                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                                <tfoot>
                                                                    <tr>
                                                                        <th style="display: none"></th>
                                                                        <th style="display: none"></th>
                                                                        <th></th>
                                                                        <th style="display: none"></th>
                                                                        <th >Total</th>
                                                                        <th id="branchtoatal"></th>
                                                                        <th></th>
                                                                    </tr>
                                                                </tfoot>
                                                            </table> 
                                                        </div>
                                                        <br>

                                                    </div>
                                                    <%--====================================================================================================
                                                                                       =====  Team Revenue Target ======
                                                     ====================================================================================================--%>

                                                    <header>Team Member  Revenue</header>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev_tmr" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br> <br> <br>
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Region<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <%--<form:select id="tabfourregionalidbmr" path="regionalid" onchange="loadBrancgGroupMemberBranch()"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Branch<samp style="color: red">*</samp></label>
                                                <label class="select">
                                                    <%--<form:select id="brnachtargetidbmr" path="brnachtargetid"  onchange="loadRegionBranchGroupDropdownList()"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->
                                                    <!--                                                    <div class="row">
                                                    
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Branch Group<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <%--<form:select id="branchgrouprevenuetargetid" path="branchgrouprevenuetargetid"  onchange="getBranchGroupRevenueTarget()"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Branch Group Member Id<samp style="color: red">*</samp></label>
                                                <label class="select">
                                                    <%--<form:select id="branchgroupmembertargetid" path="branchgroupmembertargetid" onchange="membertargetonchange();" />--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div> 
                                    </div>-->
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Total Amount<samp style="color: red">*</samp></label>
                                                                                                                    <label class="input">
                                                    <%--<form:input id="totalbranchgroprevene" path="tab5totalrevenue" readonly="true"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Amount<samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <%--<form:input id="memberrevenue" path="tab5revenue" value="0"  />--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>


                                    </div>-->
                                                    <%--====================================================================================================
                                                           =====  START Branch Member Revenue  ======
                                                    ====================================================================================================--%>
                                                    <!--                                                    <div class="row" >
                                                                                                            <div class="col-md-2 ">  </div>
                                                                                                            <div class="col-md-8 ">
                                                                                                                <button type="button" id="addbranchmemberrevenue" class="btn pull-right btn btn-primary btn btn-sm">Save</button>
                                                                                                            </div>
                                                                                                        </div>-->


                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">

                                                            <table id="dt_basic7" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th data-hide="phone" style="display: none">BGMTID</th>
                                                                        <th data-hide="phone" style="display: none">RBG Id</th>
                                                                        <th data-hide="phone">RBG Name</th>
                                                                        <th data-hide="phone" style="display: none">Branch Group Member Id</th>
                                                                        <th data-hide="phone">Branch Group Member</th>
                                                                        <th data-hide="phone">Amount</th>
                                                                        <!--                                                                        <th data-hide="phone">Action</th>-->
                                                                    </tr>
                                                                </thead>
                                                                <tfoot>
                                                                    <tr>
                                                                        <!--<th style="display: none"></th>--> 
                                                                        <th style="display: none"></th> 
                                                                        <th style="display: none"></th> 
                                                                        <th></th>
                                                                        <th style="display: none"></th>
                                                                        <th >Total</th>
                                                                        <th id="branchmembertoatal"> $0 ($0 total)</th>

                                                                    </tr>
                                                                </tfoot>
                                                                <tbody></tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <%--====================================================================================================
                                                                                        =====  END Branch Member Revenue  ======
                                                      ====================================================================================================--%>
                                                    <br>
                                                    <br>
                                                </div>
                                                <%--====================================================================================================
                                                                                =====  END TAB FOUR ======
                                                ====================================================================================================--%>
                                                <div class="tab-pane" id="tab5"><br><br>
                                                    <h3><strong>Step 5</strong> - Team Activity <label id="branchtarget"></label></h3>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br><br>
                                                    <%--====================================================================================================
                                                                                        ===== START Team Activity Target ======
                                                      ====================================================================================================--%>
                                                    <div class="row">
                                                        <header>Team Activity</header>
                                                        <br>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target Description<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="tabfivetargetid" path="targetid" items="${taget}" onchange="getRegionBranchActivity();"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Region<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="tabfiveregionid" path="regionalid" onchange="getRegionActivityList();"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>

                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label"> Activity<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="regionalactivitytargetid" path="regionalactivitytargetid"  onchange=" getRegionActivityBranchList();"/>
                                                                    <%--<form:select id="branchactivitytargetid" path="branchactivitytargetid"  onchange=";"/>--%>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Branch<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="branchactivitytargetid" path="branchactivitytargetid"  onchange="getBranchActivityCount();"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Group<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <form:select id="tabfivebranchgrouptargetid" path="branchgroupid"  items="${branchgroup}" onchange="branchgroupclick();" />
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->
                                                    <div class="row">  
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Total Count<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input id="branchactivitycount" path="tab5totalcount" readonly="true" />
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <!--                                                        <div class="col-xs-3">
                                                                                                                    <section>
                                                                                                                        <label class="label">count<samp style="color: red">*</samp></label>
                                                                                                                        <label class="input">
                                                        <form:input id="branchgrouptivitycount" path="tab5count" value="0" />
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>
                                            <div class="col-xs-1"></div>-->
                                                    </div>

                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-md-2"> </div>
                                                                                                            <div class="col-md-8">
                                                                                                                <button type="button" id="addBrnachGroupActivityTarget" class="btn pull-right btn btn-primary btn btn-sm">Save</button>
                                                                                                            </div>
                                                                                                        </div>-->

                                                    <div class="row">
                                                        <div class="col-md-2"> </div>
                                                        <div class="col-md-8">
                                                            <table id="dt_basic8" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th data-hide="phone" style="display: none">RBA Id</th>
                                                                        <th data-hide="phone" style=" width: 363px;">Regional Branch Activity Name</th>
                                                                        <th data-hide="phone"  style="display: none">Group Id</th>
                                                                        <th data-hide="phone" style="width:201px">Group Name</th>
                                                                        <th data-hide="phone" style="width:201px" >Amount</th>
                                                                        <th data-hide="phone" style="width:201px">Action</th>
                                                                    </tr>
                                                                </thead>

                                                                <tbody>
                                                                </tbody>
                                                                <tfoot>
                                                                    <tr>
                                                                        <!--<th style="display: none"></th>--> 
                                                                        <th style="display: none"></th> 
                                                                        <th></th> 
                                                                        <th style="display: none"></th>
                                                                        <th>Total</th>
                                                                        <th id="bactivity"> </th>
                                                                        <th></th>

                                                                    </tr>
                                                                </tfoot>
                                                            </table> 
                                                        </div>
                                                        <br>
                                                    </div>
                                                    <%--====================================================================================================
                                                                              ===== END Team Activity Target ======
                                                    ====================================================================================================--%>

                                                    <%--====================================================================================================
                                                                                        ===== START Branch Member Activity ======
                                                      ====================================================================================================--%>
                                                    <header>Team Member Activity</header>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div id="msg_dev_tma" class="col-xs-10">
                                                            <c:if test="${not empty successMsg}">
                                                                <div class="alert alert-success">
                                                                    <strong>Success!</strong> ${successMsg}
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${not empty errorMsg}">
                                                                <div class="alert alert-warning">
                                                                    <strong>Warning!</strong> ${errorMsg}
                                                                </div>
                                                                <br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br> <br>
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Region<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <%--<form:select id="tabfivememberregionid" path="regionid" onchange="getMemberRegionActivityList();"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label"> Activity<samp style="color: red">*</samp></label>
                                                <label class="select">
                                                    <%--<form:select id="memberregionalactivitytargetid" path="memberregionalactivitytargetid"  onchange="getMemberBramchList();"/>--%>

                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Branch<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <%--<form:select id="memberbranchactivitytargetid" path="memberbranchactivitytargetid"  onchange="getMemberBramchGroupList();"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Branch Group<samp style="color: red">*</samp></label>
                                                <label class="select">
                                                    <%--<form:select id="branchgroupactivitytargeid" path="branchgroupactivitytargeid"  onchange="getBranchGroupMember2()"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Employee<samp style="color: red">*</samp></label>
                                                                                                                    <label class="select">
                                                    <%--<form:select id="branchgroupmemberid" path="branchgroupmembertargetid" onchange="empnameclick()"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>-->
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-2"></div>
                                                                                                            <div class="col-xs-3">
                                                                                                                <section>
                                                                                                                    <label class="label">Total Count<samp style="color: red">*</samp></label>
                                                                                                                    <label class="input">
                                                    <%--<form:input id="tabfivetotalbranchgroprevene" path="tab5totalrevenue"  readonly="true"/>--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Count<samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <%--<form:input id="branchgroupmembercount" path="tab5revenue" value="0" />--%>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2"></div>
                                        <div class="col-md-8">
                                            <button type="button" id="addbranchgroupmemberactivity" class="btn pull-right btn btn-primary btn btn-sm">Save</button>
                                        </div>
                                    </div>-->

                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <table id="dt_basic9" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th data-hide="phone" style="display: none">Id</th>
                                                                        <th data-hide="phone" align="right" >Activity Name</th>  
                                                                        <th data-hide="phone" style="display: none">Branch Id</th>     
                                                                        <th data-hide="phone">Branch Name</th>     
                                                                        <th data-hide="phone" style="width:201px" class="hasinput">Count</th>

                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                                <tfoot>
                                                                    <tr>
                                                                        <!--<th style="display: none"></th>--> 
                                                                        <th style="display: none"></th> 
                                                                        <th></th> 
                                                                        <th style="display: none"></th>
                                                                        <th>Total</th>
                                                                        <th id="bmactivity"> </th>


                                                                    </tr>
                                                                </tfoot>
                                                            </table> 
                                                        </div>
                                                    </div>
                                                    <%--====================================================================================================
                                                                                        ===== END Branch Member Activity ======
                                                      ====================================================================================================--%>
                                                    <br>
                                                    <br>
                                                </div>
                                                <div class="tab-pane" id="tab6">
                                                    <br>
                                                    <h3><strong>Step 6</strong> - Save Form</h3>
                                                    <br>
                                                    <h1 class="text-center text-success"><strong><i class="fa fa-check fa-lg"></i> Complete</strong></h1>
                                                    <!--<h4 class="text-center">Click next to finish</h4>-->
                                                    <br>
                                                    <br>
                                                </div>
                                                <!--<form id="wizard-1" novalidate="novalidate">-->

                                                <div class="form-actions">
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <ul class="wizard" style="padding-bottom:50px; list-style: none;">
                                                                <li class="previous pull-left"  style="margin-left:20px;">
                                                                    <a href="javascript:void(0);" class="btn btn-sm btn-default disabled " id="previous" > Previous </a>
                                                                </li>
                                                                <li class="next pull-right" style="margin-right:20px;">
                                                                    <a href="javascript:void(0);" class="btn btn-sm btn-default" id="finish"> Save and Move Next</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form:form>
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
            </div>
            <!-- END MAIN CONTENT -->
        </div>
        <!-- END MAIN PANEL -->
        <!-- PAGE FOOTER -->
        <div class="page-footer">
            <jsp:include page="../template/footer.jsp"/>
        </div>
        <!-- END PAGE FOOTER -->

        <jsp:include page="../template/jsinclide.jsp"/>
        <script>

            //tab navigetion------------------
            $('#regionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#targetactivityid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#regionaltargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#regionalactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#tabthreebranchid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#brnachtargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#branchgrouprevenuetargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#branchgroupmembertargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#branchactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#branchgroupmemberid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#branchgroupactivitytargeid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#tabthreeregionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#barnchtargetactivitiy').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#tabfourregionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#branchgroupid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#tabfivebranchgrouptargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#tabfourregionalidbmr').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#brnachtargetidbmr').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#tabfiveregionid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#tabfivememberregionid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#memberregionalactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#memberbranchgroupactivitytargeid').append($("<option></option>").attr("value", '').text('-- Select --'));
            $('#memberbranchactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));

            $(document).ready(function () {
                ////console.log("ready!");
                var dataObject = new Object();
                dataObject.targetid = $("#targetid").val();
                dataObject.targetactivityid = $("#targetactivityid").val();
                var content = JSON.stringify(dataObject);
                //                var table3 = null;
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/datatbelthreecolums",
                    cache: false,
                    async: false,
                    dataType: 'json',
                    data: {tab2_info: content},
                    success: function (response) {
                        var tableHeaders = "";
                        var tableFooter = "";
                        var tableHeadersTwo = "";
                        $.each(response, function (i, val) {
                            tableHeaders += "<th class='firstrow'>" + val.title + "</th>";
                            tableHeadersTwo += "<th class='secondrow'></th>";
                            tableFooter += "<th ></th>";
                        });
                        var tableContent = "<thead><tr id='rowtwo'>" + tableHeadersTwo + "</tr><tr id='rowone'>" + tableHeaders + "</tr></thead><tbody></tbody><tfoot><tr>" + tableFooter + "</tr></tfoot>";
                        ////console.log(tableContent);
//                        $('#dt_basic3').empty();
                        $('#dt_basic3').html(tableContent);
//                        $('#dt_basic3').DataTable({
//                            "bProcessing": false,
//                            "bServerSide": true,
//                            "bFilter": false,
//                            "cache": false,
//                            "sPaginationType": "full_numbers",
//                            "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
//                                    "t" +
//                                    "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
//                            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/regionactivity",
//                            "fnServerData": function (sSource, aoData, fnCallback) {
//                                aoData.push({"name": "targetid", "value": $('#targetid').val()});
//                                //                        ////console.log(aoData);
//                                $.ajax({
//                                    "dataType": 'json',
//                                    async: false,
//                                    "type": "POST",
//                                    "url": "${pageContext.servletContext.contextPath}/load/regionactivity",
//                                    "data": aoData,
//                                    "success": fnCallback
//
//                                });
//                            },
//                            "aoColumns": [
//                                //                        {"mDataProp": "regionid", "bSortable": false},
//                                {"mDataProp": "regiondes", "bSortable": false},
//                                {"mRender": function (data, type, full) {
//                                        return '<input type="text" id="' + full["callactivityid"] + '"  name="' + full["callactivityid"] + '"  value="' + $.number(full["callcount"]) + '" onfocus="numberformation()" /> <div id="errorContainer"></div>';
//                                    }},
//                                {"mRender": function (data, type, full) {
//                                        return '<input type="text" id="' + full["proposalsactivityid"] + '" name="' + full["proposalsactivityid"] + '"  value="' + $.number(full["proposalscount"]) + '" onfocus="numberformation()" /> <div id="errorContainer"></div>';
//                                    }},
//                                {"mRender": function (data, type, full) {
//                                        return '<input type="text" id="' + full["vpresentationsactivityid"] + '" name="' + full["vpresentationsactivityid"] + '"  value="' + $.number(full["visitspresentationscount"]) + '"  onfocus="numberformation()"/> <div id="errorContainer"></div>';
//                                    }},
//                                {"mRender": function (data, type, full) {
//                                        return '<button type="button" id="" class="btn btn-primary btn btn-sm">Save</button>';
//                                    }}
//                            ],
//                            "headerCallback": function (thead, data, start, end, display) {
//                                var call = 0;
//                                var proposal = 0;
//                                var vp = 0;
//                                var dataObject = new Object();
//                                dataObject.targetid = $("#targetid").val();
//                                var content = JSON.stringify(dataObject);
//                                $.ajax({
//                                    type: "post",
//                                    url: "${pageContext.servletContext.contextPath}/gettotalactivitycount",
//                                    cache: false,
//                                    async: false,
//                                    dataType: 'json',
//                                    data: {tab2_info: content},
//                                    success: function (response) {
//
//                                        ////console.log(response);
//                                        call = response[0].callcount;
//                                        proposal = response[2].proposal;
//                                        vp = response[1].vp;
//                                        $("#ccount").val(response[0].Tcallcount);
//                                        $("#pcount").val(response[2].Tproposal);
//                                        $("#vpcount").val(response[1].Tvp);
//                                        $('#rowtwo').find('th').eq(1).html("Remaning activity count " + $.number(call));
//                                        $('#rowtwo').find('th').eq(2).html("Remaning activity count " + $.number(vp));
//                                        $('#rowtwo').find('th').eq(3).html("Remaning activity count " + $.number(proposal));
//
//                                    },
//                                    error: function () {
//                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                        window.scrollTo(0, 0);
//                                    }
//                                });
//
//
//                            },
//                            "footerCallback": function (tfoot, data, start, end, display) {
//                                var call = 0;
//                                var proposal = 0;
//                                var vp = 0;
//                                data.forEach(function (x) {
//                                    call += parseInt((x['callcount']));
//                                    proposal += parseInt((x['proposalscount']));
//                                    vp += parseInt((x['visitspresentationscount']));
//                                });
////                                $(tfoot).find('th').eq(1).html("Toatal activity count " + $.number(call));
////                                $(tfoot).find('th').eq(2).html("Toatal activity count " + $.number(vp) );
////                                $(tfoot).find('th').eq(3).html("Toatal activity count " + $.number(proposal));
//                            }
////                        }
//                        });
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });


                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/datatbelfivecolums",
                    cache: false,
                    async: true,
                    dataType: 'json',
                    data: {tab2_info: content},
                    success: function (response) {
                        var tableHeaders = "";
                        var tableFooter = "";
                        var tableHeadersTwo = "";
                        $.each(response, function (i, val) {
                            tableHeaders += "<th class='firstrow'>" + val.title + "</th>";
                            tableHeadersTwo += "<th class='tfivesecondrow'></th>";
                            tableFooter += "<th ></th>";
                        });
                        var tableContent = "<thead><tr id='tfiverowtwo'>" + tableHeadersTwo + "</tr><tr id='tfiverowone'>" + tableHeaders + "</tr></thead><tbody></tbody><tfoot><tr>" + tableFooter + "</tr></tfoot>";
                        ////console.log(tableContent);

                        $('#dt_basic5').empty();
                        $('#dt_basic5').html(tableContent);
                        $('#dt_basic5').DataTable({
                            "bProcessing": false,
                            "bServerSide": true,
                            "bFilter": false,
                            "cache": false,
                            "sPaginationType": "full_numbers",
                            "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                                    "t" +
                                    "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/regionactivity",
                            "fnServerData": function (sSource, aoData, fnCallback) {
                                aoData.push({"name": "regionid", "value": $('#regionalid').val()});
                                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                                //                        ////console.log(aoData);
                                //                                $.ajax({
                                //                                    "dataType": 'json',
                                //                                    async: true,
                                //                                    "type": "POST",
                                //                                    "url": "${pageContext.servletContext.contextPath}/load/branchactivity",
                                //                                    "data": aoData,
                                //                                    "success": fnCallback
                                //
                                //                                });
                            }
                        });
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });

                loadregionlist();
                loadRegionDropdownList();
                getRegionBranchActivity();
                loadtabforRegionDropdownList();

//                getRegionActivityDetails();
//                loadbranchregionlist();
//                getRegionBranchActivity();
////                getRegionActivityDetails();
//                loadBrnachlist();
//                getBranchActivityDetails();
//                getBranchGroupRevenueDetails();
//                getBranchGroupActivityDetails();
//                getBranchGroupActivityMemberDetails();
//                loadtabforRegionDropdownList();



                var table6 = $('#dt_basic6').DataTable({
                    "bFilter": false,
                    "async": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                "autoWidth": true,
                });
                var table7 = $('#dt_basic7').DataTable({
                    "bFilter": false,
                    "async": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                "autoWidth": true,
                });
                var table8 = $('#dt_basic8').DataTable({
                    "bFilter": false,
                    "async": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                "autoWidth": true,
                });
                var table9 = $('#dt_basic9').DataTable({
                    "bFilter": false,
                    "async": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                "autoWidth": true,
                });
            });


            $('#bootstrap-wizard-1').bootstrapWizard({'tabClass': 'form-wizard',
                'onNext': function (tab, navigation, index) {
                    var $valid = $("#wizard-1").valid();
                    if (!$valid) {
                        return false;
                    } else {
                        var state = $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).attr("class");
                        var $valid = $("#wizard-1").valid();
                        if (!$valid) {
                            return false;
                        }
                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 0) {

                            $("#msg_dev").empty();
                            var $valid = $("#wizard-1").valid();
                            if (!$valid) {
                                return false;
                            }
                            checkAmountChange();

                        }
                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 1) {
                            $("#previous").removeClass('disabled');
                            $("#msg_dev").empty();
                            loadRegionDropdownList();
                            //                            $("#tab2count").rules("remove");
                            if ($("#totalrevenue").val() !== $("#currentamouttabtwo").val()) {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning! Allocated target amounts to Regions are not equal to the total target amount</strong> !</div>  ');
                                window.scrollTo(0, 0);
                                return false;
                            }
                            var $valid = $("#wizard-1").valid();
                            if (!$valid) {
                                return false;
                            }
                        }
                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 2) {
                            $("#previous").removeClass('disabled');
                            $("#msg_dev").empty();
                            loadtabforRegionDropdownList();
                            var returnValue = tabthreevalidation();
                            if (returnValue) {
                            } else {
                                ////console.log("tabe 3 reteurne" + returnValue);
                                return false;
                            }
                            var $valid = $("#wizard-1").valid();
                            if (!$valid) {
                                return false;
                            }
                        }

                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 3) {
                            $("#previous").removeClass('disabled');
                            $("#msg_dev").empty();
                            getRegionBranchActivity();
                            $("#tabthreetargetid").trigger("change");
                            var returnValue = tabfourvalidation();
                            ////console.log("returnValuepage" + returnValue);
                            if (returnValue) {
                                var returnValue2 = tabfourvalidationMemberTarget();
                                if (returnValue2) {
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                            var $valid = $("#wizard-1").valid();
                            ////console.log($valid);
                            if (!$valid) {
                                return false;
                            }
                        }
                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 4) {
                            $("#finish").addClass('disabled');
                        } else {
                            $("#finish").removeClass('disabled');
                        }
                        $("#msg_dev").empty();
                        //                    }
                        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).addClass(
                                'complete');
                        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).find('.step')
                                .html('<i class="fa fa-check"></i>');
                        $("#previous").removeClass('disabled');
                    }
                    window.scrollTo(0, 0);
                },
                'onPrevious': function (tab, navigation, index) {
                    if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') - 1 === 0) {

                        $("#msg_dev").empty();
                        $("#previous").addClass('disabled');
                    } else {
                        $("#previous").removeClass('disabled');
                        $("#finish").removeClass('disabled');
                    }
                    if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') - 1 === 4) {
                        $("#msg_dev").empty();
                        $("#finish").removeClass('disabled');
                    }
                    window.scrollTo(0, 0);
                }
            });
            $('#step01').click(function () {
                $("#previous").addClass('disabled');
                $("#finish").removeClass('disabled');
                //console.log('clicked');
            });
            $('#step02').click(function () {
                $("#previous").removeClass('disabled');
                $("#finish").removeClass('disabled');
                //console.log('clicked');
            });
            $('#step03').click(function () {
                $("#previous").removeClass('disabled');
                $("#finish").removeClass('disabled');
                loadRegionDropdownList();
                //console.log('clicked3');
            });
            $('#step04').click(function () {
                $("#previous").removeClass('disabled');
                $("#finish").removeClass('disabled');
                loadtabforRegionDropdownList();
                //                loadBrancgGroupMemberRegion();
                //console.log('clicked5');
            });
            $('#step05').click(function () {
                $("#previous").removeClass('disabled');
                $("#finish").removeClass('disabled');
                getRegionBranchActivity();
                ////console.log('clicked5');
            });
            $('#step06').click(function () {
                $("#previous").removeClass('disabled');
                $("#finish").addClass('disabled');
                //console.log('clicked6');
            });
            //-------------tabnavigation-------------------------------
            var count = 0;
            var table = $('#dt_basic').DataTable({
                data: ${newactivitytype},
                'columnDefs': [
                    {
                        'targets': 2,
                        'width': '1%',
                        'className': 'dt-body-center',
                        'render': function (data, type, full, meta) {
                            count++;
                            return '<input type="text" id="' + full[0] + '"  name="name' + count + '" value="' + $.number(data) + '" onchange="validate()" > <div id="errorContainer"></div>';
                        }
                    },
                    {
                        "targets": [0],
                        "visible": false

                    }
                ],
                'searchable': false,
                'orderable': false,
                "bPaginate": false,
                "bFilter": false,
                "bInfo": false

            });
            function validate() {
                //            datatbel text box validation
                $('#dt_basic').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).rules('add', {
                            required: true,
                            number: true
                        });
                        $(this).number(true, ',', ' ');
                    });
                });
            }

            function activedatime() {
                $('#datetimepickerfromdate').removeAttr('disabled');
            }

            var myTable = $('#dt_basic').DataTable();
            $('#myTable').on('click', 'tbody td', function () {
                myTable.cell(this).edit();
            });
            var counter = 0;
            var date = new Date(), y = date.getFullYear(), m = date.getMonth();
            d = date.getDate();
            var stardate = m + 1;
            $('#datetimepickerfromdate').datetimepicker({
                weekStart: 1,
                todayHighlight: false,
                //                todayBtn: 2,
                autoclose: 1,
                startView: 3,
                minView: 3,
                forceParse: 0,
                startDate: new Date(y, stardate, 1),
                format: "yyyy-mm-dd"
            });
            $("#datetimepickerfromdate").datetimepicker('setDate', new Date(y, stardate, 1));
            $('#datetimepickerfromdate').datetimepicker().on('changeDate', function (ev) {
                loadToDate();
            });
            $('#targetperiodid').change(function () {
                loadToDate();
            });
            function loadToDate() {
                var date = new Date($('#datetimepickerfromdate').val());
                var yy = date.getFullYear();
                var mm = date.getMonth();
                $("#datetimepickertodate").val("");
                if ($('#targetperiodid').val() == 1) {
                    mm = mm + 1;
                }
                if ($('#targetperiodid').val() == 2) {
                    mm = mm + 3;
                }
                if ($('#targetperiodid').val() == 3) {
                    mm = mm + 12;
                }
                $('#datetimepickertodate').datetimepicker('setDate', new Date(yy, mm, 1));
            }

            function loadTargetDroupDown() {
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/target/targetdropdown",
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        for (var i = 0; i < response.targetdropdown.length; i++) {
                            $("#targetid").empty();
                            $("#tabthreetargetid").empty();
                            $("#tabfourtargetid").empty();
                            $("#tabfivetargetid").empty();
                            $('#targetid').append($("<option></option>").attr("value", response.targetdropdown[i].targetid).text(response.targetdropdown[i].tragetdes));
                            $('#tabthreetargetid').append($("<option></option>").attr("value", response.targetdropdown[i].targetid).text(response.targetdropdown[i].tragetdes));
                            $('#tabfourtargetid').append($("<option></option>").attr("value", response.targetdropdown[i].targetid).text(response.targetdropdown[i].tragetdes));
                            $('#tabfivetargetid').append($("<option></option>").attr("value", response.targetdropdown[i].targetid).text(response.targetdropdown[i].tragetdes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }


            function savetab1() {
                var array = [];
                $('#dt_basic').find('tr').each(function () {
                    var idx = table
                            .row(this)
                            .index();
                    if (idx >= 0) {
                        var ID = table.cell(this, 0).data();
                        var COUNT = 0;
                        $(this).find("input,text").each(function (i) {
                            COUNT = $(this).val().replace(/,/g, "");
                        });
                        array.push({
                            ID: ID,
                            COUNT: COUNT
                        });
                    }

                });
                var jsonString = JSON.stringify(array);
                $("#activitytargettbl").val(jsonString);
                $('#msg_dev').empty();
                var dataObject = new Object();
                dataObject.targetid = $('#targetid').val();
                dataObject.productid = $('#productid').val();
                dataObject.targetperiodid = $('#targetperiodid').val();
                dataObject.targetstartdate = $('#datetimepickerfromdate').val();
                dataObject.targetenddate = $('#datetimepickertodate').val();
                dataObject.tragetdes = $('#tragetdes').val();
                dataObject.revenue = $('#revenue').val().replace(/,/g, "");
                dataObject.activitytargettbl = $('#activitytargettbl').val();
                //                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                //                ////console.log(content);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/target/savetabone",
                    cache: false,
                    async: false,
                    data: {tab1_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div>  ');
                            window.scrollTo(0, 0);
                        } else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }
                        $("#gentargetid").val(response.TARGETID);
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                $("#targetid").empty();
                $("#tabthreetargetid").empty();
                $("#tabfourtargetid").empty();
                $("#tabfivetargetid").empty();
                $.ajax({
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/targetdropdown",
                    cache: false,
                    async: false,
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.target.length; i++) {
                            $('#targetid').append($("<option></option>").attr("value", response.target[i].targetid).text(response.target[i].tragetdes));
                            $('#tabthreetargetid').append($("<option></option>").attr("value", response.target[i].targetid).text(response.target[i].tragetdes));
                            $('#tabfourtargetid').append($("<option></option>").attr("value", response.target[i].targetid).text(response.target[i].tragetdes));
                            $('#tabfivetargetid').append($("<option></option>").attr("value", response.target[i].targetid).text(response.target[i].tragetdes));
                        }
                        $('#tabfivetargetid').val($("#gentargetid").val()).trigger('change');
                        $('#tabfourtargetid').val($("#gentargetid").val()).trigger('change');
                        $('#tabthreetargetid').val($("#gentargetid").val()).trigger('change');
                        $('#targetid').val($("#gentargetid").val()).trigger('change');
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }
            //-------------------------------------search ----------------------------------

            $('#dt_basic2').dataTable().fnDestroy();
            var table2 = $('#dt_basic2').DataTable({
                "bFilter": false,
                "sPaginationType": "full_numbers",
                "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                        "t" +
                        "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                //                "autoWidth": true,

            });
            var responsiveHelper_dt_basic = undefined;
            var breakpointDefinition = {
                tablet: 1024,
                phone: 480
            };
            ;
            /* END BASIC */
            var search_table;
            var TOTAL = 0;
            function loadregionlist() {
                var totalval = 0;
                $("#toatal").empty();
                countnew = 0;
                search = true;
                $('#dt_basic2').dataTable().fnDestroy();
                search_table = $('#dt_basic2').dataTable({
                    "bProcessing": false,
                    "bServerSide": true,
                    "bFilter": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/target/regionlist",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "targetid", "value": $('#targetid').val()});
                        //                        ////console.log(aoData);
                        $.ajax({
                            "dataType": 'json',
                            async: false,
                            "type": "POST",
                            "url": "${pageContext.servletContext.contextPath}/target/regionlist",
                            "data": aoData,
                            "success": fnCallback

                        });
                    },
                    "aoColumns": [
                        {"mDataProp": "branchid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "branchdes", "bSortable": false},
                        {"mRender": function (data, type, full) {
                                countnew++;
                                return '<input type="text" id="' + full["branchid"] + '"  name="feild' + countnew + '" value="' + full["revenue"] + '" onfocusout="calcuation();"> <div id="errorContainer"></div>';
                            }}


                    ],
                    "footerCallback": function (tfoot, data, start, end, display) {


                        data.forEach(function (x) {
                            totalval += parseInt((x['revenue']));
                        });


                    }

                });
                var dataObject = new Object();
                dataObject.targetid = $('#targetid').val();
                //                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                //                ////console.log(content);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/target/sum",
                    cache: false,
                    async: false,
                    data: {tab1_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        TOTAL = response.sum;
                        TOTALAMOUNT = response.totalamount;
                        $("#totalrevenue").val(TOTALAMOUNT);
//                        $("#currentamout").val(TOTALAMOUNT);
                        calcuation();
//                        $("#toatal").append('$' + $.number(totalval) + ' ( $' + $.number(TOTALAMOUNT) + ' total)');
                        getRegionActivityDetails();
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                //                $("#dt_basic3").dataTable().fnClearTable();

            }



            function clearTable() {
                search = false;
                $('#dt_basic2').dataTable().fnClearTable();
            }

            //-------------------------------------search ----------------------------------
            //-----------------------------calculation and save onchaneg------------------------------
            jQuery.validator.addMethod("lessthan", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
//                alert(i)
//                alert($("#totalrevenue").val().replace(/,/g, ""))
                if ($.isNumeric(i)) {
                    if (i <= $("#totalrevenue").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Pleas enter less than target amount");
            jQuery.validator.addMethod("totalamout", function (value, element) {
                var COUNT = 0;
                $('#dt_basic2').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                        }
                    });
                });
                if (COUNT <= $("#totalrevenue").val().replace(/,/g, "")) {
                    return true;
                } else {
                    return false;
                }
            }, "Target amount exceeded");
            function calcuation() {
                $('#msg_dev').empty();
                $('#msg_dev2').empty();
                $("#totalrevenue").rules("remove");
                var $valid = $("#wizard-1").valid();
                $('#dt_basic2').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).rules('add', {
                            //                            required: true,
                            number: true,
                            lessthan: true,
                            totalamout: true
                        });
                        $(this).number(true, ',', ' ');
                    });
                });
                var array = [];
                var COUNT = 0;
                $('#dt_basic2').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric(($(this).val().replace(/,/g, "")))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                            array.push({
                                ID: $(this).attr("id"),
                                COUNT: $(this).val()
                            });
                        }
                    });
                });
                if ($valid) {
//                    $.SmartMessageBox({
//                        title: "Confirmation!",
//                        content: "Are you sure you want to change the Target Amount? This will clear  any already created target amounts in lower levels of the hierarchy.",
//                        buttons: '[No][Yes]'
//                    }, function (ButtonPressed) {
//                        if (ButtonPressed === "Yes") {
                    $("#toatal").empty();
                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.targetid = $("#targetid").val();
                    dataObject.regionaltargetid = $("#regionaltargetid").val();
                    dataObject.datatabledata = $('#datatabledata').val();
                    //                    ////console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/datatable/save",
                        cache: false,
                        async: false,
                        data: {datatable_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            console.log(response.remaning !== "0");
                            if (response.CODE === "SUCCESS") {
                                if (response.remaning !== 0) {
                                    $('#msg_dev2').html('<div class="alert alert-info"><strong>Info!</strong> Remaining amount to be shared among the Region of current Organization is ' + $.number(response.remaning) + '</div>  ');
                                    window.scrollTo(0, 0);
                                } else {

                                }

                                loadregionlistByTargetid();
                            } else {

                                window.scrollTo(0, 0);
                            }
                            $("#toatal").append('$' + $.number(COUNT) + ' ( $' + $.number(response.total) + ' total)');
                            $("#currentamouttabtwo").val(response.total);
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            window.scrollTo(0, 0);
                        }
                    });
                    loadregionlistByTargetid();
                    loadActivityList();
                    getRegionActivityDetails();
//                            var dataObject = new Object();
//                            dataObject.targetid = $("#gentargetid").val();
//                            var content = JSON.stringify(dataObject);
//                            $.ajax({type: "post",
//                                url: "${pageContext.servletContext.contextPath}/deletealltargetaftersecondtab",
//                                cache: false,
//                                async: false,
//                                data: {tab1_info: content},
//                                success: function (response) {
//                                    response = JSON.parse(response);
//                                    if (response.status === true) {
//                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');
//
//                                    } else {
//                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
//                                        window.scrollTo(0, 0);
//                                    }
//
//                                },
//                                error: function () {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                    window.scrollTo(0, 0);
//                                }
//                            });
//                            loadBrnachlist();
//                            getBranchGroupRevenueDetails();
//                            getBranchGroupMemberDetails();
//                        }
//                        if (ButtonPressed === "No") {
//                            loadregionlist();
//                            getRegionActivityDetails();
//                        }

//                    });
                }
            }


            //-----------------------------calculation and save onchaneg------------------------------

            //-----------------------------loadRegionlist and activity list to tabe 1------------------------------

            function loadregionlistByTargetid() {
                $("#regionalid").empty();
                $('#regionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#targetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/load/regionlist",
                    data: {tab2_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        for (var i = 0; i < response.regionlist.length; i++) {
                            $('#regionalid').append($("<option></option>").attr("value", response.regionlist[i].regionid).text(response.regionlist[i].regiondes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }


            function loadActivityList() {
                $("#targetactivityid").empty();
                $('#targetactivityid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#targetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/load/activitylist",
                    data: {tab2_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        for (var i = 0; i < response.activitylist.length; i++) {
                            $('#targetactivityid').append($("<option></option>").attr("value", response.activitylist[i].targetactivityid).text(response.activitylist[i].targetactivitydes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }

            function tabthreeloadActivityList() {
                getBranchActivityDetails();
                //                $("#barnchtotalcount").val("");
                //                $("#BAcount").val("");
                //                $("#barnchtargetactivitiy").empty();
                //                $('#barnchtargetactivitiy').append($("<option></option>").attr("value", '').text('-- Select --'));
                //                var dataObject = new Object();
                //                dataObject.targetid = $("#tabthreetargetid").val();
                //                dataObject.regionid = $("#tabthreeregionalid").val();
                //                var content = JSON.stringify(dataObject);
                //                $.ajax({
                //                    type: "POST",
                //                    async: false,
                //                    url: "${pageContext.servletContext.contextPath}/load/regionactivitylist",
                //                    data: {tab3_info: content},
                //                    cache: false, success: function (response) {
                //                        response = JSON.parse(response);
                ////                        ////console.log(response);
                //                        for (var i = 0; i < response.activitylist.length; i++) {
                //                            $('#barnchtargetactivitiy').append($("<option></option>").attr("value", response.activitylist[i].targetactivityid).text(response.activitylist[i].targetactivitydes));
                //                        }
                //
                //                    },
                //                    error: function () {
                //                        ////console.log('Error while request..');
                //                    }
                //                });
                //                BranchList();
                //                $("#dt_basic5").dataTable().fnClearTable();
            }


            //-----------------------------loadRegionlist and activity list to tabe 1------------------------------
            //---------------------------------load region activity count-------------------------------------------
            function targetactivitycount() {
                var dataObject = new Object();
                dataObject.targetactivityid = $("#targetactivityid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/gettargetactivitycount",
                    cache: false,
                    async: true,
                    data: {tab2_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log("count" + response.count);
                        $("#tab2totalcount").val(response.count);
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                var selectedRegion = $('#regionalid option:selected');
                var selectedActivity = $('#targetactivityid option:selected');
                var region = selectedRegion.text();
                var regionid = selectedRegion.val();
                var activity = selectedActivity.text();
                var activityid = selectedActivity.val();
                var table3 = $('#dt_basic3').DataTable();
                $('#dt_basic3').find('tr').each(function () {
                    var table3 = $('#dt_basic3').DataTable();
                    var idx = table3.row(this).index();
                    if (idx >= 0) {
                        var tregionid = table3.cell(this, 0).data();
                        var tactivityid = table3.cell(this, 2).data();
                        if (regionid === tregionid && activityid === tactivityid) {
                            $("#tab2count").val(table3.cell(this, 4).data());
                        }
                    }
                });
            }
            //--------------------------------------------------------------------------------------------------
            //-----------------------------load activity count and check allready data in datatabel ------------------------------
            function loadActivitycount() {
                var dataObject = new Object();
                dataObject.targetid = $("#targetid").val();
                dataObject.targetactivityid = $("#targetactivityid").val();
                var content = JSON.stringify(dataObject);
                //                ////console.log(content);
                $.ajax({
                    type: "POST",
                    async: true,
                    url: "${pageContext.servletContext.contextPath}/load/count",
                    data: {tab2_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        $("#totalcount").val(response.count);
                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }

            //            var table3 = $('#dt_basic3').DataTable({
            //                "bFilter": false,
            //                "sPaginationType": "full_numbers",
            //                "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
            //                        "t" +
            //                        "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
            ////                "autoWidth": true,
            //                "columns": [
            //                    {"title": "engine"},
            //                    {"title": "browser"},
            //                    {"title": "platform"},
            //                    {"title": "version"},
            //                    {"title": "grade"},
            //                    {"title": "grade2"}
            //                ],
            //            });


            function validation() {
                var sum = 0;
                var selectedItem = $('#targetactivityid option:selected');
                var selectedItem2 = $("#regionalid option:selected");
                var tactivityid = selectedItem.val();
                var regionid = selectedItem2.val();
                $('#dt_basic3').find('tr').each(function () {
                    var table3 = $('#dt_basic3').DataTable();
                    var idx = table3.row(this).index();
                    if (idx >= 0) {
                        var tgroupid = table3.cell(this, 2).data();
                        var tregionpid = table3.cell(this, 0).data();
                        if (regionid === tregionpid && tactivityid === tgroupid) {
                            ////console.log("true");
                        } else {
                            if (tactivityid === tgroupid) {
                                sum += parseInt(table3.cell(this, 4).data());
                            }

                        }
                    }
                });
                jQuery.validator.addMethod("sum", function () {
                    ////console.log(sum);
                    ////console.log(parseInt($("#tab2count").val()) + parseInt(sum));
                    ////console.log($("#tab2totalcount").val());
                    if ($.isNumeric($("#tab2count").val()) && $.isNumeric(parseInt(sum))) {
                        if (parseInt($("#tab2count").val()) + parseInt(sum) <= $("#tab2totalcount").val()) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                }, jQuery.validator.format("Invalid amount."));
            }

            $('#addRow').on('click', function () {
                $("#regionalid").rules("add", {
                    required: true
                });
                $("#targetactivityid").rules("add", {
                    required: true
                });
                $("#tab2count").rules("add", {
                    required: true
                });
                $('#msg_dev').empty();
                validation();
                $("#tab2count").rules('add', {
                    sum: true,
                    number: true
                });
                var $valid = $("#wizard-1").valid();
                if ($valid) {
                    var count = $("#tab2count").val();
                    var selectedItem = $('#regionalid option:selected');
                    var selectedItem2 = $('#targetactivityid option:selected');
                    var regionid = selectedItem.val();
                    var activityid = selectedItem2.val();
                    var status = false;
                    $('#dt_basic3').find('tr').each(function () {
                        var table3 = $('#dt_basic3').DataTable();
                        var idx = table3.row(this).index();
                        if (idx >= 0) {
                            var tregionid = table3.cell(this, 0).data();
                            var tactivityid = table3.cell(this, 2).data();
                            if (regionid === tregionid && activityid === tactivityid) {
                                status = true;
                            }
                        }
                    });
                    if (status) {
                        $('#dt_basic3').find('tr').each(function () {
                            var table3 = $('#dt_basic3').DataTable();
                            var idx = table3.row(this).index();
                            if (idx >= 0) {
                                var tregionid = table3.cell(this, 0).data();
                                var tactivityid = table3.cell(this, 2).data();
                                if (regionid === tregionid) {
                                    if (activityid === tactivityid) {
                                        var dataObject = new Object();
                                        dataObject.targetid = $("#targetid").val();
                                        dataObject.regionid = $("#regionalid").val();
                                        dataObject.targetactivityid = $("#targetactivityid").val();
                                        dataObject.count = $("#tab2count").val();
                                        var content = JSON.stringify(dataObject);
                                        $.ajax({
                                            type: "post",
                                            url: "${pageContext.servletContext.contextPath}/updatedatatablethereerow",
                                            cache: false,
                                            async: false,
                                            data: {tab2_info: content},
                                            success: function (response) {
                                                response = JSON.parse(response);
                                                //                                                $('#dt_basic3').draw();
                                                getRegionActivityDetails();
                                            },
                                            error: function () {
                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                                window.scrollTo(0, 0);
                                            }
                                        });
                                        getRegionActivityDetails();
                                    }

                                }
                            }
                        });
                    } else {
                        var dataObject = new Object();
                        dataObject.targetid = $("#targetid").val();
                        dataObject.regionalid = $("#regionalid").val();
                        dataObject.targetactivityid = $("#targetactivityid").val();
                        dataObject.count = $("#tab2count").val();
                        var content = JSON.stringify(dataObject);
                        $.ajax({
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/savedatatbeltwo",
                            cache: false,
                            async: false,
                            data: {tab2_info: content},
                            success: function (response) {
                                response = JSON.parse(response);
                                getRegionActivityDetails();
                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                window.scrollTo(0, 0);
                            }
                        });
                    }
                }
                $("tab2count").rules("remove");
            });
            //-----------------------------load  adta tabel33333 activity count------------------------------

            function getRegionActivityDetails() {
                //                targetactivitycount();
                //                $('#dt_basic3').dataTable().fnClearTable();
                $('#dt_basic3').dataTable().fnDestroy();
                $('#dt_basic3').dataTable({
                    "bProcessing": false,
                    "bServerSide": true,
                    "bFilter": false,
                    cache: false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                    "autoWidth": true,
                    "preDrawCallback": function () {
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/load/regionactivity",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "targetid", "value": $('#targetid').val()});
                        //                        ////console.log(aoData);
                        $.ajax({
                            "dataType": 'json',
                            async: false,
                            "type": "POST",
                            "url": "${pageContext.servletContext.contextPath}/load/regionactivity",
                            "data": aoData,
                            "success": fnCallback

                        });
                    },
                    "aoColumns": [
                        //                        {"mDataProp": "regionid", "bSortable": false},
                        {"mDataProp": "regiondes", "bSortable": false},
                        {"mRender": function (data, type, full) {
                                return '<input type="text" id="' + full["callactivityid"] + '"  name="' + full["callactivityid"] + '"  value="' + $.number(full["callcount"]) + '" onfocus="numberformation()" /> <div id="errorContainer"></div>';
                            }},
                        {"mRender": function (data, type, full) {
                                return '<input type="text" id="' + full["vpresentationsactivityid"] + '" name="' + full["vpresentationsactivityid"] + '"  value="' + $.number(full["visitspresentationscount"]) + '"  onfocus="numberformation()"/> <div id="errorContainer"></div>';
                            }},
                        {"mRender": function (data, type, full) {
                                return '<input type="text" id="' + full["proposalsactivityid"] + '" name="' + full["proposalsactivityid"] + '"  value="' + $.number(full["proposalscount"]) + '" onfocus="numberformation()" /> <div id="errorContainer"></div>';
                            }},
                        {"mRender": function (data, type, full) {
                                return '<button type="button" id="" class="btn btn-primary btn btn-sm">Save</button>';
                            }}
                    ],
                    "headerCallback": function (thead, data, start, end, display) {
                        var call = 0;
                        var proposal = 0;
                        var vp = 0;
                        var dataObject = new Object();
                        dataObject.targetid = $("#targetid").val();
                        var content = JSON.stringify(dataObject);
                        $.ajax({
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/gettotalactivitycount",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {tab2_info: content},
                            success: function (response) {

                                ////console.log(response);
                                call = response[0].callcount;
                                proposal = response[2].proposal;
                                vp = response[1].vp;
                                $("#ccount").val(response[0].Tcallcount);
                                $("#pcount").val(response[2].Tproposal);
                                $("#vpcount").val(response[1].Tvp);
                                $('#rowtwo').find('th').eq(1).html("Remaning activity count " + $.number(call));
                                $('#rowtwo').find('th').eq(2).html("Remaning activity count " + $.number(vp));
                                $('#rowtwo').find('th').eq(3).html("Remaning activity count " + $.number(proposal));

                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                window.scrollTo(0, 0);
                            }
                        });


                    },
                    "footerCallback": function (tfoot, data, start, end, display) {
                        var fcall = 0;
                        var fproposal = 0;
                        var fvp = 0;
                        data.forEach(function (x) {
                            fcall += parseInt((x['callcount']));
                            fproposal += parseInt((x['proposalscount']));
                            fvp += parseInt((x['visitspresentationscount']));
                        });
                        $(tfoot).find('th').eq(1).html("Toatal activity count " + $.number(fcall));
                        $(tfoot).find('th').eq(2).html("Toatal activity count " + $.number(fvp));
                        $(tfoot).find('th').eq(3).html("Toatal activity count " + $.number(fproposal));

                    }
                });
                //                $("#regionalid").rules("remove");
                //                $("#targetactivityid").rules("remove");
                //                $("#tab2count").rules("remove");
                //                $("#tab2totalcount").val("");
                //                $("#tab2count").val("");
                //                $("#targetactivityid").prop('selectedIndex', 0);
                //                $("#regionalid").prop('selectedIndex', 0);

            }
            //----------------------------table row remove-------------------------------------------
            function numberformation() {
                $('#dt_basic3').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).number(true, ',', ' ');
                    });
                });
                $('#revenue').focus(function () {
                });
            }
            $('#dt_basic3').on('click', 'tr button', function (e) {
                $('#msg_dev').empty();
                $('#msg_dev3').empty();
                var $row = $(this).closest("tr");
                $row.find("input,text").eq(0).rules("add", {
                    required: true,
                    callcount: true
                });
                $row.find("input,text").eq(1).rules("add", {
                    required: true,
                    vpcount: true
                });
                $row.find("input,text").eq(2).rules("add", {
                    required: true,
                    proposalcount: true
                });
                var array = [];
                var $valid = $("#wizard-1").valid();
                $row.find("input,text").each(function (i) {
                    array.push({
                        ID: $(this).attr("id"),
                        VALUE: $(this).val().replace(/,/g, "")
                    });
                });
                ////console.log(array);
                if ($valid) {
//                    $.SmartMessageBox({
//                        title: "Confirmation!",
//                        content: "Are you sure you want to change the Activity count? This will clear any already allocated activity counts in lower levels of the hierarchy.",
//                        buttons: '[No][Yes]'
//                    }, function (ButtonPressed) {
//                        if (ButtonPressed === "Yes") {

                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.datatabledata = $("#datatabledata").val();
                    dataObject.targetid = $("#targetid").val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/update/regionalactivity",
                        cache: false,
                        async: false,
                        data: {tab2_info: content},
                        success: function (response) {
                            ////console.log(response);
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev3').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div>  ');
//                                window.scrollTo(0, 0);
                            } else {
                                $('#msg_dev3').html('<div class="alert alert-warning"><strong>Warning!</strong> Invalid Amount added, Please check your amount</div>');
//                                        window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            window.scrollTo(0, 0);
                        }
                    });
                    getRegionActivityDetails();
//                            var dataObject = new Object();
//                            dataObject.targetid = $("#gentargetid").val();
//                            var content = JSON.stringify(dataObject);
//                            $.ajax({type: "post",
//                                url: "${pageContext.servletContext.contextPath}/deleteallactivitycountaftersecondtab",
//                                cache: false,
//                                async: false,
//                                data: {tab1_info: content},
//                                success: function (response) {
//                                    response = JSON.parse(response);
//                                    if (response.status === true) {
//                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');
//
//                                    } else {
//                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
//                                        window.scrollTo(0, 0);
//                                    }
//
//                                },
//                                error: function () {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                    window.scrollTo(0, 0);
//                                }
//                            });



//                        }
//                        if (ButtonPressed === "No") {
//                            getRegionActivityDetails();
//                        }

//                    });
                }

            });



            jQuery.validator.addMethod("callcount", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                //                alert(i)
                if ($.isNumeric(i)) {
                    if (i <= $("#ccount").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Please enter less than total activity count");

            jQuery.validator.addMethod("proposalcount", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                if ($.isNumeric(i)) {
                    if (i <= $("#pcount").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Please enter less than total activity count");

            jQuery.validator.addMethod("vpcount", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                if ($.isNumeric(i)) {
                    if (i <= $("#vpcount").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Please enter less than total activity count");

            $('#dt_basic3').on('click', 'tr a', function (e) {
                e.preventDefault();
                var id = $(this).attr('id');
                var id2 = $(this).attr('style');
                $.SmartMessageBox({
                    title: "Confirmation!",
                    content: "Do you really want to remove this Activity?",
                    buttons: '[No][Yes]'
                }, function (ButtonPressed) {
                    if (ButtonPressed === "Yes") {

                        $.smallBox({
                            title: "Callback function",
                            content: "<i class='fa fa-clock-o'></i> <i>Deleting...</i>",
                            color: "#659265",
                            iconSmall: "fa fa-check fa-2x fadeInRight animated",
                            timeout: 4000
                        });
                        deletetable3row(id, id2);
                    }
                    if (ButtonPressed === "No") {
                        $.smallBox({
                            title: "Callback function",
                            content: "<i class='fa fa-clock-o'></i> <i>You pressed No...</i>",
                            color: "#C46A69",
                            iconSmall: "fa fa-times fa-2x fadeInRight animated",
                            timeout: 4000
                        });
                    }

                });
                e.preventDefault();
            });
            function  deletetable3row(id1, id2) {
                var dataObject = new Object();
                dataObject.targetid = $("#targetid").val();
                dataObject.regionid = id1;
                dataObject.targetactivityid = id2;
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/deletetablethreerow",
                    cache: false,
                    async: false,
                    data: {tab2_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response.MESSAGE);
                        if (response.CODE === "SUCCESS") {
                            $("#msg_dev").show();
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div>  ');
                            window.scrollTo(0, 0);
                        } else {
                            $("#msg_dev").show();
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                $('#dt_basic3').dataTable().fnDestroy();
                getRegionActivityDetails();
            }

            //-----------------------------------------------------------------------------------------
            //-----------------------------tab tow savae------------------------------

            function  savetabtwo() {
                $('#regionactivity').val(JSON.stringify($('#dt_basic3').tableToJSON()));
                var dataObject = new Object();
                dataObject.targetid = $("#targetid").val();
                dataObject.regionactivity = $("#regionactivity").val();
                //                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/target/savetabtwo",
                    cache: false,
                    data: {tabtwo_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div>  ');
                            window.scrollTo(0, 0);
                        } else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            //-----------------------------tab tow savae------------------------------  
            //-----------------------------tab there load Regions------------------------------  

            function tabethreeRegionDropdownList() {
                $('#tabthreeregionalid').empty();
                $('#tabthreeregionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabthreetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/load/regionlist",
                    data: {tab2_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        for (var i = 0; i < response.regionlist.length; i++) {
                            $('#tabthreeregionalid').append($("<option></option>").attr("value", response.regionlist[i].regionid).text(response.regionlist[i].regiondes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
                //                getBranchActivityDetails()
            }

            function loadRegionDropdownList() {
                ////console.log("tabe3");
                ////console.log($("#tabthreetargetid").val())
                $('#regionaltargetid').empty();
                $('#regionaltargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                //                $('#tabthreeregionalid').empty();
                //                $('#tabthreeregionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabthreetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/load/regiondropdown",
                    data: {tab3_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        for (var i = 0; i < response.regionlisttabthree.length; i++) {
                            $('#regionaltargetid').append($("<option></option>").attr("value", response.regionlisttabthree[i].regionaltargetid).text(response.regionlisttabthree[i].regiondes));
                            //                            $('#tabthreeregionalid').append($("<option></option>").attr("value", response.regionlisttabthree[i].regionaltargetid).text(response.regionlisttabthree[i].regiondes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
                //                tabthreeloadActivityList();
                tabethreeRegionDropdownList();
                $("#dt_basic4").dataTable().fnClearTable();
                //                $("#dt_basic5").dataTable().fnClearTable();
            }

            function loadtabforRegionDropdownList() {
//                $("#dt_basic6").dataTable().fnClearTable();
                $("#tabfourregionalid").prop('selectedIndex', 0);
                $("#brnachtargetid").prop('selectedIndex', 0);
                $("#barnchamount").val("");
                $('#tabfourregionalid').empty();
                $('#tabfourregionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabfourtargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/load/regiondropdown",
                    data: {tab3_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        for (var i = 0; i < response.regionlisttabthree.length; i++) {
                            $('#tabfourregionalid').append($("<option></option>").attr("value", response.regionlisttabthree[i].regionaltargetid).text(response.regionlisttabthree[i].regiondes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
                //                loadgroupList();
                //                loadRegionBranchGroupDropdownList();
                //                loadBrancgGroupMemberRegion();
            }

            //-----------------------------tab there load Regions-------------------------------------------  
            //-------------------------------load brnalist----------------------------------------------------------

            function loadRegionAmout() {
                var dataObject = new Object();
                dataObject.targetid = $("#tabthreetargetid").val();
                dataObject.regionaltargetid = $("#regionaltargetid").val();
                var content = JSON.stringify(dataObject);
                //                ////console.log(content);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/loadregiontargetamount",
                    data: {tab3_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        //                        ////console.log(response);
                        $("#tab3totalrevenue").val(response.amount);
                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }

            var table4 = $('#dt_basic4').DataTable({
                "bFilter": false,
                "sPaginationType": "full_numbers",
                "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                        "t" +
                        "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                //                "autoWidth": true,
            });
            var responsiveHelper_dt_basic = undefined;
            var breakpointDefinition = {
                tablet: 1024,
                phone: 480
            };
            ;
            /* END BASIC */
            var search_table2;
            var TOTAL = 0;
            function loadBrnachlist() {
                var totalval = 0;
                $("#brnachtoatal").empty();
                loadRegionAmout();
                $('#dt_basic4').dataTable().fnClearTable();
                $('#dt_basic4').dataTable().fnDestroy();
                countnew = 0
                search = true;
                search_table2 = $('#dt_basic4').dataTable({
                    "bProcessing": false,
                    "bServerSide": true,
                    "bFilter": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/load/branchlist",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "targetid", "value": $('#tabthreetargetid').val()});
                        aoData.push({"name": "regionaltagetid", "value": $('#regionaltargetid').val()});
                        ////console.log(aoData);
                        $.ajax({
                            "dataType": 'json',
                            async: false,
                            "type": "POST",
                            "url": "${pageContext.servletContext.contextPath}/load/branchlist",
                            "data": aoData,
                            "success": fnCallback

                        });
                    },
                    "aoColumns": [
                        {"mDataProp": "branchid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "branchdes", "bSortable": false},
                        {"mRender": function (data, type, full) {
                                countnew++;
                                return '<input type="text" id="' + full["branchid"] + '"  name="feild' + countnew + '" value="' + full["revenue"] + '" onfocusout="calcuationBrnachtarget()"  > <div id="errorContainer"></div>';
                            }}


                    ],
                    "footerCallback": function (tfoot, data, start, end, display) {

                        data.forEach(function (x) {
                            totalval += parseInt((x['revenue']));

                        });
                    }
                });
                var dataObject = new Object();
                dataObject.regionaltargetid = $('#regionaltargetid').val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/branchtarget/sum",
                    cache: false,
                    async: false,
                    data: {tab3_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        TOTAL = response.sum;
                        calcuationBrnachtarget();
//                        $("#brnachtoatal").append('$' + $.number(totalval) + ' ( $' + $.number(TOTAL) + ' total)');
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });


            }

            function clearTable() {
                search = false;
                $('#dt_basic2').dataTable().fnClearTable();
            }
            //--------------------------------------------------------------------------------------------------------
            //-------------------------------------------------------Brancgt target list calculation-----------------


            jQuery.validator.addMethod("branchamountlessthan", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                if ($.isNumeric(i)) {
                    if (i <= $("#tab3totalrevenue").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Pleas enter less than target amount");
            jQuery.validator.addMethod("branchtotalamout", function (value, element) {
                var COUNT = 0;
                $('#dt_basic4').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                        }
                    });
                });
                if (COUNT <= $("#tab3totalrevenue").val().replace(/,/g, "")) {
                    return true;
                }
            }, "Target amount exceeded");
            function calcuationBrnachtarget() {
                $('#msg_dev').empty();
                $('#msg_dev4').empty();
                $('#dt_basic4').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).rules('add', {
                            //                            required: true,
                            number: true,
                            branchamountlessthan: true,
                            branchtotalamout: true
                        });
                        $(this).number(true, ',', ' ');
                    });
                });
                $("#tab3totalrevenue").rules("remove");
                var $valid = $("#wizard-1").valid();
                var array = [];
                var COUNT = 0;
                if ($valid) {
                    $('#dt_basic4').find('tr').each(function () {
                        $(this).find("input,text").each(function (i) {
                            if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                                COUNT += parseInt($(this).val().replace(/,/g, ""));
                                array.push({
                                    ID: $(this).attr("id"),
                                    COUNT: $(this).val()
                                });
                            }
                        });
                    });
//                    $.SmartMessageBox({
//                        title: "Confirmation!",
//                        content: "Are you sure you want to change the Target Amount? This will clear  any already created target amounts in lower levels of the hierarchy.",
//                        buttons: '[No][Yes]'
//                    }, function (ButtonPressed) {
//                        if (ButtonPressed === "Yes") {
                    $("#brnachtoatal").empty();
                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.targetid = $("#tabthreetargetid").val();
                    dataObject.datatabledata = $('#datatabledata').val();
                    dataObject.regionaltargetid = $("#regionaltargetid").val();
                    dataObject.datatabledata = $('#datatabledata').val();
                    //                    ////console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/branchdatatable/save",
                        cache: false,
                        data: {datatable_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                if (response.remaning !== 0) {
                                    $('#msg_dev4').html('<div class="alert alert-info"><strong>Info!</strong> Remaining amount to be shared among the Branches of current Region is ' + $.number(response.remaning) + '</div>  ');
                                    window.scrollTo(0, 0);
                                } else {

                                }
                                $("#brnachtoatal").append('$' + $.number(COUNT) + ' ( $' + $.number(response.total) + ' total)');
                                $("#currentamout").val(response.total);
                                //                                $('#msg_dev3').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div>  ');
                            } else {
                                //                                $('#msg_dev3').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                                //                                loadBrnachlist();
                                window.scrollTo(0, 0);
                            }

                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            window.scrollTo(0, 0);
                        }
                    });
                    loadBranchActivitycount();
//                            var dataObject = new Object();
//                            dataObject.targetid = $("#gentargetid").val();
//                            var content = JSON.stringify(dataObject);
//                            $.ajax({type: "post",
//                                url: "${pageContext.servletContext.contextPath}/deletealltargetafterthirdtab",
//                                cache: false,
//                                async: false,
//                                data: {tab1_info: content},
//                                success: function (response) {
//                                    response = JSON.parse(response);
//                                    if (response.status === true) {
//                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');
//
//                                    } else {
//                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
//                                        window.scrollTo(0, 0);
//                                    }
//
//                                },
//                                error: function () {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                    window.scrollTo(0, 0);
//                                }
//                            });
//                            getBranchGroupRevenueDetails();
//                            getBranchGroupMemberDetails();
//                        }
//                        if (ButtonPressed === "No") {
//                            loadBrnachlist();
//                        }

//                    });
                }
                $("#tabthreeregionalid").prop('selectedIndex', 0);
            }

            //--------------------------------------------------------------------------------------------------------
            //-------------------------------Region List by Targetid-------------------------------------------------
            function RegionDropDownByTargetId() {
                var dataObject = new Object();
                dataObject.targetid = $("#tabthreetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/load/ractivitytargetlist",
                    data: {tab3_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.ractivitytargetlist.length; i++) {
                            $('#regionalactivitytargetid').append($("<option></option>").attr("value", response.ractivitytargetlist[i].regionalactivitytargetid).text(response.ractivitytargetlist[i].regionalactivitytargetdes));
                        }
                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }
            //--------------------------------------------------------------------------------------------------------
            //------------------------------------load branch activity count-----------------------------------------
            function loadBranchActivitycount() {
                $("#BAcount").val("");
                $("#tabthreebranchid").prop('selectedIndex', 0);
                var dataObject = new Object();
                dataObject.regionalid = $("#tabthreeregionalid").val();
                dataObject.regionalactivitytargetid = $("#barnchtargetactivitiy").val();
                var content = JSON.stringify(dataObject);
                //////console.log(content);
                $.ajax({
                    type: "POST",
                    async: true,
                    url: "${pageContext.servletContext.contextPath}/loadbranch/count",
                    data: {tab3_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response);
                        $("#barnchtotalcount").val(response.count);
                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
                //                getBranchActivityDetails();
            }

            function brachlistclick() {
                $("#BAcount").val("");
                var selectedItem = $('#barnchtargetactivitiy option:selected');
                var selectedItem2 = $('#tabthreebranchid option:selected');
                var regionactid = selectedItem.val();
                var regionactdes = selectedItem.text();
                var branchid = selectedItem2.val();
                var branhcdes = selectedItem2.text();
                var table5 = $('#dt_basic5').DataTable();
                var data1 = table5.row(0).data();
                $('#dt_basic5').find('tr').each(function () {
                    var table5 = $('#dt_basic5').DataTable();
                    var idx = table5.row(this).index();
                    if (idx >= 0) {
                        var tregionactid = table5.cell(this, 0).data();
                        var tbranchid = table5.cell(this, 2).data();
                        if (regionactid === tregionactid && branchid === tbranchid) {
                            $("#BAcount").val(table5.cell(this, 4).data());
                        }
                    }
                });
            }

            //            var table5 = $('#dt_basic5').DataTable({
            //                "bFilter": false,
            ////                async: false,
            //                "sPaginationType": "full_numbers",
            //                "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
            //                        "t" +
            //                        "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
            ////                "autoWidth": true,
            //            });
            function validationdatatbel5() {
                var sum = 0;
                var selectedItem = $('#barnchtargetactivitiy option:selected');
                var selectedItem2 = $('#tabthreebranchid option:selected');
                var rgionalactivityid = selectedItem.val();
                var branchid = selectedItem2.val();
                $('#dt_basic5').find('tr').each(function () {
                    var table5 = $('#dt_basic5').DataTable();
                    var idx = table5.row(this).index();
                    if (idx >= 0) {
                        var trgionalactivityid = table5.cell(this, 0).data();
                        var tbranchid = table5.cell(this, 2).data();
                        if (rgionalactivityid === trgionalactivityid && tbranchid === branchid) {
                        } else {
                            if (rgionalactivityid === trgionalactivityid) {
                                sum += parseInt(table5.cell(this, 4).data());
                            }

                        }
                    }
                });
                jQuery.validator.addMethod("activitycount", function () {
                    ////console.log(sum);
                    ////console.log(parseInt($("#BAcount").val()) + parseInt(sum));
                    ////console.log($("#BAcount").val());
                    if ($.isNumeric($("#BAcount").val()) && $.isNumeric(parseInt(sum))) {
                        if (parseInt($("#BAcount").val()) + parseInt(sum) <= $("#barnchtotalcount").val()) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }, jQuery.validator.format("Invalid amount."));
            }

            $('#addBranchActivityRow').on('click', function () {
                $('#msg_dev').empty();
                $("#tabthreeregionalid").rules("add", {
                    required: true
                });
                $("#tabthreebranchid").rules("add", {
                    required: true
                });
                $("#barnchtargetactivitiy").rules("add", {
                    required: true
                });
                validationdatatbel5();
                $("#BAcount").rules('add', {
                    activitycount: true,
                    number: true,
                    required: true
                });
                var $valid = $("#wizard-1").valid();
                if ($valid) {
                    var count = $("#BAcount").val();
                    var selectedItem = $('#barnchtargetactivitiy option:selected');
                    var selectedItem2 = $('#tabthreebranchid option:selected');
                    var regionacid = selectedItem.val();
                    var branchid = selectedItem2.val();
                    var status = false;
                    $('#dt_basic5').find('tr').each(function () {
                        var table5 = $('#dt_basic5').DataTable();
                        var idx = table5.row(this).index();
                        if (idx >= 0) {
                            var tregionacid = table5.cell(this, 0).data();
                            var tbranchid = table5.cell(this, 2).data();
                            if (regionacid === tregionacid && branchid === tbranchid) {
                                status = true;
                            }
                        }
                    });
                    if (status) {
                        $('#dt_basic5').find('tr').each(function () {
                            var table5 = $('#dt_basic5').DataTable();
                            var idx = table5.row(this).index();
                            if (idx >= 0) {
                                var tregionacid = table5.cell(this, 0).data();
                                var tbranchid = table5.cell(this, 2).data();
                                if (regionacid === tregionacid) {
                                    if (branchid === tbranchid) {
                                        var dataObject = new Object();
                                        dataObject.targetid = $("#targetid").val();
                                        dataObject.regionalactivitytargetid = $("#barnchtargetactivitiy").val();
                                        dataObject.branchid = $("#tabthreebranchid").val();
                                        dataObject.count = $("#BAcount").val();
                                        var content = JSON.stringify(dataObject);
                                        $.ajax({
                                            type: "post",
                                            url: "${pageContext.servletContext.contextPath}/updatedatatabletfiverow",
                                            cache: false,
                                            async: false,
                                            data: {tab3_info: content},
                                            success: function (response) {
                                                response = JSON.parse(response);
                                            },
                                            error: function () {
                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                                window.scrollTo(0, 0);
                                            }
                                        });
                                    }

                                }
                            }
                        });
                        //                
                    } else {
                        var dataObject = new Object();
                        dataObject.regionalactivitytargetid = $("#barnchtargetactivitiy").val();
                        dataObject.branchid = $("#tabthreebranchid").val();
                        dataObject.count = $("#BAcount").val();
                        var content = JSON.stringify(dataObject);
                        $.ajax({
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/savedatatblefive",
                            cache: false,
                            async: false,
                            data: {tab3_info: content},
                            success: function (response) {
                                response = JSON.parse(response);
                                //                                getBranchActivityDetails();
                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                window.scrollTo(0, 0);
                            }
                        });
                    }
                }
                //                getBranchActivityDetails();
                $("#BAcount").val("");
                $("#tabthreeregionalid").rules("remove");
                $("#tabthreebranchid").rules("remove");
                $("#barnchtargetactivitiy").rules("remove");
                $("#BAcount").rules("remove");
                $("#tabthreebranchid").prop('selectedIndex', 0);
                $("#BAcount").val("");
            });
            function getBranchActivityDetails() {
                $("#msg_dev5").empty();
                $('#dt_basic5').dataTable().fnClearTable();
                if ($("#tabthreeregionalid  option:selected").index() == 0) {
                    $('#tfiverowtwo').find('th').eq(1).html("Remaning activity count " + $.number(0));
                    $('#tfiverowtwo').find('th').eq(2).html("Remaning activity count " + $.number(0));
                    $('#tfiverowtwo').find('th').eq(3).html("Remaning activity count " + $.number(0));
                    $(tfoot).find('th').eq(1).html("Toatal activity count " + $.number(0));
                    $(tfoot).find('th').eq(2).html("Toatal activity count " + $.number(0));
                    $(tfoot).find('th').eq(3).html("Toatal activity count " + $.number(0));

                }
                if ($("#tabthreeregionalid  option:selected").index() > 0) {

                    $('#dt_basic5').dataTable().fnDestroy();
                    $('#dt_basic5').dataTable({
                        "bProcessing": false,
                        //                    async: false,
                        "bServerSide": true,
                        "bFilter": false,
                        cache: false,
                        "sPaginationType": "full_numbers",
                        "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                                "t" +
                                "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                        //                    "autoWidth": true,
                        "preDrawCallback": function () {
                            // Initialize the responsive datatables helper once.
                            if (!responsiveHelper_dt_basic) {
                                responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                            }
                        },
                        "rowCallback": function (nRow) {
                            responsiveHelper_dt_basic.createExpandIcon(nRow);
                        },
                        "sAjaxSource": "${pageContext.servletContext.contextPath}/load/branchactivity",
                        "fnServerData": function (sSource, aoData, fnCallback) {
                            aoData.push({"name": "targetid", "value": $('#tabthreetargetid').val()});
                            aoData.push({"name": "regionid", "value": $('#tabthreeregionalid').val()});
                            ////console.log(aoData);
                            $.ajax({
                                "dataType": 'json',
                                async: false,
                                "type": "POST",
                                "url": "${pageContext.servletContext.contextPath}/load/branchactivity",
                                "data": aoData,
                                "success": fnCallback

                            });
                        },
                        "aoColumns": [
                            //                        {"mDataProp": "regionid", "bSortable": false},
                            {"mDataProp": "branchdes", "bSortable": false},
                            {"mRender": function (data, type, full) {
                                    return '<input type="text" id="' + full["callactivityid"] + '"  name="' + full["callactivityid"] + '"  value="' + $.number(full["callcount"]) + '" class="bcallcount"  onfocus="numberformationtabel5()"/> <div id="errorContainer"></div>';
                                }},
                            {"mRender": function (data, type, full) {
                                    return '<input type="text" id="' + full["vpresentationsactivityid"] + '" name="' + full["vpresentationsactivityid"] + '"  value="' + $.number(full["visitspresentationscount"]) + '"  class="bvpcount" onfocus="numberformationtabel5() /> <div id="errorContainer"></div>';
                                }},
                            {"mRender": function (data, type, full) {
                                    return '<input type="text" id="' + full["proposalsactivityid"] + '" name="' + full["proposalsactivityid"] + '"  value="' + $.number(full["proposalscount"]) + '" class="bproposalcount" onfocus="numberformationtabel5() /> <div id="errorContainer"></div>';
                                }},
                            {"mRender": function (data, type, full) {
                                    return '<button type="button" id="" class="btn btn-primary btn btn-sm">Save</button>';
                                }}
                        ],
                        "headerCallback": function (thead, data, start, end, display) {
                            var call = 0;
                            var proposal = 0;
                            var vp = 0;
                            var dataObject = new Object();
                            dataObject.targetid = $("#tabthreetargetid").val();
                            dataObject.regionid = $("#tabthreeregionalid").val();
                            var content = JSON.stringify(dataObject);
                            if ($("#tabthreeregionalid  option:selected").index() > 0) {
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.servletContext.contextPath}/branchactivitycountbyregion",
                                    cache: false,
                                    async: false,
                                    dataType: 'json',
                                    data: {tab3_info: content},
                                    success: function (response) {
                                        ////console.log(response);
                                        call = response[0].callcount;
                                        proposal = response[2].proposal;
                                        vp = response[1].vp;
                                        $("#bccount").val(response[0].Tcallcount);
                                        $("#bpcount").val(response[2].Tproposal);
                                        $("#bvpcount").val(response[1].Tvp);
                                        $('#tfiverowtwo').find('th').eq(1).html("Remaning activity count " + $.number(call));
                                        $('#tfiverowtwo').find('th').eq(2).html("Remaning activity count " + $.number(vp));
                                        $('#tfiverowtwo').find('th').eq(3).html("Remaning activity count " + $.number(proposal));


                                    },
                                    error: function () {
                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                        window.scrollTo(0, 0);
                                    }
                                });

                            }
                        },
                        "footerCallback": function (tfoot, data, start, end, display) {
                            var call = 0;
                            var proposal = 0;
                            var vp = 0;
                            data.forEach(function (x) {
                                call += parseInt((x['callcount']));
                                proposal += parseInt((x['proposalscount']));
                                vp += parseInt((x['visitspresentationscount']));
                            });
                            $(tfoot).find('th').eq(1).html("Toatal activity count " + $.number(call));
                            $(tfoot).find('th').eq(2).html("Toatal activity count " + $.number(vp));
                            $(tfoot).find('th').eq(3).html("Toatal activity count " + $.number(proposal));

                        }

                    });

                    //                });
                }
            }

            function numberformationtabel5() {
                $('#dt_basic5').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).number(true, ',', ' ');
                    });
                });
            }

            //            jQuery.validator.addMethod("branchcallcount", function (value, element) {
            //
            //
            ////                if (COUNT <= $("#bccount").val()) {
            ////                    return true;
            ////                } else {
            ////                    return false;
            ////                }
            //            }, "Pleas enter less than activity count");
            //
            //            jQuery.validator.addMethod("proposalcount", function (value, element) {
            //                var i = parseInt(value.replace(/,/g, ""));
            //                if ($.isNumeric(i)) {
            //                    if (i <= $("#pcount").val().replace(/,/g, "")) {
            //                        return true;
            //                    }
            //                } else {
            //                    return true;
            //                }
            //            }, "Pleas enter less than activity count");
            //
            //            jQuery.validator.addMethod("vpcount", function (value, element) {
            //                var i = parseInt(value.replace(/,/g, ""));
            //                if ($.isNumeric(i)) {
            //                    if (i <= $("#vpcount").val().replace(/,/g, "")) {
            //                        return true;
            //                    }
            //                } else {
            //                    return true;
            //                }
            //            }, "Pleas enter less than activity count");


            $('#dt_basic5').on('click', 'tr button', function (e) {
                $('#msg_dev').empty();
                $('#msg_dev5').empty();
                var array = [];


                var $row = $(this).closest("tr");
                $row.find("input,text").eq(0).rules("add", {
                    required: true,
                });
                $row.find("input,text").eq(1).rules("add", {
                    required: true,
                });
                $row.find("input,text").eq(2).rules("add", {
                    required: true,
                });
                var $valid = $("#wizard-1").valid();
                $row.find("input,text").each(function (i) {
                    array.push({
                        ID: $(this).attr("id"),
                        VALUE: $(this).val().replace(/,/g, "")
                    });
                });
                if ($valid) {
//                    $.SmartMessageBox({
//                        title: "Confirmation!",
//                        content: "Are you sure you want to change the Activity count? This will clear  any already allocated activity counts in lower levels of the hierarchy.",
//                        buttons: '[No][Yes]'
//                    }, function (ButtonPressed) {
//                        if (ButtonPressed === "Yes") {
                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.datatabledata = $("#datatabledata").val();
                    dataObject.targetid = $("#tabthreetargetid").val();
                    dataObject.regionid = $("#tabthreeregionalid").val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/update/branchactivity",
                        cache: false,
                        async: false,
                        data: {tab3_info: content},
                        success: function (response) {
                            ////console.log(response);
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev5').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div>  ');
                                //                                window.scrollTo(0, 0);
                            } else {
                                $('#msg_dev5').html('<div class="alert alert-warning"><strong>Warning!</strong> Invalid Count Please Check your count</div>  ');
//                                        window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            window.scrollTo(0, 0);
                        }
                    });
                    getBranchActivityDetails();
//                            var dataObject = new Object();
//                            dataObject.targetid = $("#gentargetid").val();
//                            var content = JSON.stringify(dataObject);
//                            $.ajax({type: "post",
//                                url: "${pageContext.servletContext.contextPath}/deleteallactivitycountafterthirdtab",
//                                cache: false,
//                                async: false,
//                                data: {tab1_info: content},
//                                success: function (response) {
//                                    response = JSON.parse(response);
//                                    if (response.status === true) {
//                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');
//
//                                    } else {
//                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
//                                        window.scrollTo(0, 0);
//                                    }
//
//                                },
//                                error: function () {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                    window.scrollTo(0, 0);
//                                }
//                            });



//                        }
//                        if (ButtonPressed === "No") {
//                            getBranchActivityDetails();
//                        }
//
//                    });
                }

            });










            //            $('#dt_basic5').on('click', 'tr a', function (e) {
            //                e.preventDefault();
            //                var id = $(this).attr('id');
            //                var id2 = $(this).attr('style');
            //                $.SmartMessageBox({
            //                    title: "Smart Alert!",
            //                    content: "This is a confirmation box. Can be programmed for button callback",
            //                    buttons: '[No][Yes]'
            //                }, function (ButtonPressed) {
            //                    if (ButtonPressed === "Yes") {
            //
            //                        $.smallBox({
            //                            title: "Callback function",
            //                            content: "<i class='fa fa-clock-o'></i> <i>You pressed Yes...</i>",
            //                            color: "#659265",
            //                            iconSmall: "fa fa-check fa-2x fadeInRight animated",
            //                            timeout: 4000
            //                        });
            //                    }
            //                    if (ButtonPressed === "No") {
            //                        $.smallBox({
            //                            title: "Callback function",
            //                            content: "<i class='fa fa-clock-o'></i> <i>You pressed No...</i>",
            //                            color: "#C46A69",
            //                            iconSmall: "fa fa-times fa-2x fadeInRight animated",
            //                            timeout: 4000
            //                        });
            //                    }
            //
            //                });
            //                e.preventDefault();
            //            });
            function  deletetable5row(id1, id2) {
                $("#BAcount").val("");
                var dataObject = new Object();
                dataObject.regionalactivitytargetid = id1;
                dataObject.branchid = id2;
                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/deletetablefiverow",
                    cache: false,
                    async: false,
                    data: {tab3_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div>  ');
                            window.scrollTo(0, 0);
                        } else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                //                getBranchActivityDetails();
            }

            function BranchList() {
                $('#tabthreebranchid').empty();
                $('#tabthreebranchid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.regionalactivitytargetid = $("#tabthreeregionalid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/getbranchlist",
                    data: {tab3_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response);
                        for (var i = 0; i < response.branchlist.length; i++) {
                            $('#tabthreebranchid').append($("<option></option>").attr("value", response.branchlist[i].branchId).text(response.branchlist[i].branchDesc));
                        }
                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }
            //--------------------------------------------------------------------------------------------------------
            //----------------------------------------save tab three------------------------------------------------

            //            function  savetabthree() {
            ////                alert("save");
            //                $('#brachtargetlist').val(JSON.stringify($('#dt_basic5').tableToJSON()));
            //                var dataObject = new Object();
            //                dataObject.targetid = $("#targetid").val();
            //                dataObject.brachtargetlist = $("#brachtargetlist").val();
            //                ////console.log(dataObject);
            //                var content = JSON.stringify(dataObject);
            //                $.ajax({
            //                    type: "post",
            //                    url: "${pageContext.servletContext.contextPath}/target/savetabthree", cache: false,
            //                    data: {tabthree_info: content},
            //                    success: function (response) {
            //                        response = JSON.parse(response);
            //                        if (response.CODE === "SUCCESS") {
            //                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div>  ');
            //                        } else {
            //                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
            //                            window.scrollTo(0, 0);
            //                        }
            //                    },
            //                    error: function () {
            //                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
            //                        window.scrollTo(0, 0);
            //                    }
            //                });
            //            }
            //-----------------------------------------------------------------------------------------------------------

            //--------------------------------------Load barnch region list for tab 4---------------------------------------------

            function  loadbranchregionlist() {
                $("#msg_dev").empty();
                $("#msg_dev_tmr").empty();
                $("#branchtoatal").empty();
                $('#brnachtargetid').empty();
                $("#branchgroupid").prop('selectedIndex', 0);
                $("#barnchamount").val("");
                $('#brnachtargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.regionaltargetid = $("#tabfourregionalid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/loadbranchregionlist",
                    data: {tab4_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response);
                        for (var i = 0; i < response.regionbranchlist.length; i++) {
                            $('#brnachtargetid').append($("<option></option>").attr("value", response.regionbranchlist[i].brnachtargetid).text(response.regionbranchlist[i].brnachtargetdes));
                        }
                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
                ////console.log($('#dt_basic6').dataTable().fnClearTable());
                ////console.log($('#dt_basic7').dataTable().fnClearTable());
                $('#dt_basic7').dataTable().fnClearTable();
                $('#dt_basic7 tbody').empty();
                $("#branchmembertoatal").empty();
                $('#dt_basic6').dataTable().fnClearTable();

                //                 $("#branchmembertoatal").append("$0 ($0 total)");
            }

            //-----------------------------------------------------------------------------------------------------------

            //            function  loadgroupList() {
            ////                alert("ok");
            //                $('#branchgroupid').empty();
            //                $('#branchgroupid').append($("<option></option>").attr("value", '').text('-- Select --'));
            //                var dataObject = new Object();
            //                dataObject.targetid = $("#tabfourtargetid").val();
            //                var content = JSON.stringify(dataObject);
            //                $.ajax({
            //                    type: "POST",
            //                    async: false,
            //                    url: "${pageContext.servletContext.contextPath}/loadgrouplist",
            //                    data: {tab4_info: content},
            //                    cache: false, success: function (response) {
            //                        response = JSON.parse(response);
            //                        ////console.log(response);
            //                        for (var i = 0; i < response.branchgrouplist.length; i++) {
            //                            $('#branchgroupid').append($("<option></option>").attr("value", response.branchgrouplist[i].branchgroupid).text(response.branchgrouplist[i].branchgroupdes));
            //                        }
            //                    },
            //                    error: function () {
            //                        ////console.log('Error while request..');
            //                    }
            //                });
            //            }


            function  tabfiveloadgroupList() {
                //                alert("ok");
                $('#tabfivebranchgrouptargetid').empty();
                $('#tabfivebranchgrouptargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabfivetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/loadgrouplist",
                    data: {tab4_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response);
                        for (var i = 0; i < response.branchgrouplist.length; i++) {
                            $('#tabfivebranchgrouptargetid').append($("<option></option>").attr("value", response.branchgrouplist[i].branchgroupid).text(response.branchgrouplist[i].branchgroupdes));
                        }
                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }

            //----------------------------------------get target amout ------------------------------------------
            $("#brnachtargetid").on('change', function () {
                var sum = 0;
                var selectedItem = $('#brnachtargetid option:selected');
                var brnachtargetid = selectedItem.val();
                var table6 = $('#dt_basic6').DataTable();
                $('#dt_basic6').find('tr').each(function () {
                    var idx = table6.row(this).index();
                    if (idx >= 0) {
                        var tbrnachtargetid = table6.cell(this, 0).data();
                        //                        if (brnachtargetid === tbrnachtargetid) {
                        sum += parseInt(table6.cell(this, 4).data());
                        ////console.log("sum" + sum);
                        //                        }
                    }
                });
                if ($("#barnchamount").val().replace(/,/g, "") == sum) {

                } else {
                    $("#branchgroupamount").rules("add", {
                        //                        equalTo: "#barnchamount"
                    });
                    return false;
                }

            });
            function getBranchTarget() {
                //                $('#dt_basic6').dataTable().fnClearTable();
                //                $('#dt_basic6').dataTable().fnDestroy();
                var dataObject = new Object();
                dataObject.brnachtargetid = $("#brnachtargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/branchtarget/amount",
                    cache: false,
                    async: false,
                    data: {tab4_info: content},
                    success: function (response) {
                        console.log(response)
                        response = JSON.parse(response);
                        console.log(response)
                        var TOTAL = response.amount;
                        $("#barnchamount").val(TOTAL);
                    },
                    error: function () {
//                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        //                        window.scrollTo(0, 0);
                    }
                });
                $("#branchgroupamount").val("");
                $("#branchgroupid").prop('selectedIndex', 0);
                //                getBranchGroupRevenueDetails();
                getBranchGroupRevenue();
                $("#dt_basic6").dataTable().fnClearTable();
                $('#dt_basic7').dataTable().fnClearTable();
                $('#dt_basic7 tbody').empty();
                //                $("#branchmembertoatal").append("$0 ($0 total)");
                $("#branchmembertoatal").empty();
            }

            //-----------------------------------------------------------------------------------------------------------
            function branchgorupchange() {
                $("#branchgroupamount").val("");
                var selectedItem = $('#brnachtargetid option:selected');
                var selectedItem2 = $('#branchgroupid option:selected');
                var regionabranchid = selectedItem.val();
                var brangroupid = selectedItem2.val();
                var table6 = $('#dt_basic6').DataTable();
                $('#dt_basic6').find('tr').each(function () {
                    var table6 = $('#dt_basic6').DataTable();
                    var idx = table6.row(this).index();
                    if (idx >= 0) {
                        var tregionbranchid = table6.cell(this, 0).data();
                        var tbranchgroupid = table6.cell(this, 2).data();
                        if (regionabranchid === tregionbranchid && brangroupid === tbranchgroupid) {
                            $("#branchgroupamount").val(table6.cell(this, 4).data());
                        }
                    }
                });
            }
            //----------------------------------------------------------adad row to datatbel 6  and insert- ---------------------

            //$( document ).ready(function() {
            //    ////console.log( "ready!" );
            //});

            $('#addBrnachGroupTarget').on('click', function () {
                $('#msg_dev').empty();
                $("#tabfourtargetid").rules("add", {
                    required: true
                });
                $("#tabfourregionalid").rules("add", {
                    required: true
                });
                $("#brnachtargetid").rules("add", {
                    required: true
                            //                    branchgroupamount: true
                });
                $("#branchgroupid").rules("add", {
                    required: true
                });
                validationDatatbelSix();
                $("#branchgroupamount").rules('add', {
                    amountvalidation: true,
                    number: true
                });
                var $valid = $("#wizard-1").valid();
                if ($valid) {
                    var selectedItem = $('#brnachtargetid option:selected');
                    var selectedItem2 = $('#branchgroupid option:selected');
                    var id = selectedItem.val().replace(/,/g, "");
                    var groupid = selectedItem2.val();
                    var status = false;
                    $('#dt_basic6').find('tr').each(function () {
                        var table6 = $('#dt_basic6').DataTable();
                        var idx = table6.row(this).index();
                        if (idx >= 0) {
                            var tid = table6.cell(this, 0).data();
                            var tgroupid = table6.cell(this, 2).data();
                            if (id === tid && groupid === tgroupid) {
                                status = true;
                            }
                        }
                    });
                    if (status) {
                        $('#dt_basic6').find('tr').each(function () {
                            var table6 = $('#dt_basic6').DataTable();
                            var idx = table6.row(this).index();
                            if (idx >= 0) {
                                var tid = table6.cell(this, 0).data();
                                var tgroupid = table6.cell(this, 2).data();
                                if (id === tid) {
                                    if (groupid === tgroupid) {
                                        var dataObject = new Object();
                                        dataObject.branchgroupid = $("#branchgroupid").val();
                                        dataObject.brnachtargetid = $("#brnachtargetid").val();
                                        dataObject.revenue = $("#branchgroupamount").val().replace(/,/g, "");
                                        var content = JSON.stringify(dataObject);
                                        $.ajax({
                                            type: "post",
                                            url: "${pageContext.servletContext.contextPath}/updatebarnchgrouprevenetdata",
                                            cache: false,
                                            async: false,
                                            data: {tab4_info: content},
                                            success: function (response) {
                                                response = JSON.parse(response);
                                                if (response.CODE === "SUCCESS") {
                                                    if (response.remaning == '0') {
                                                        //                                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Sucsess!</strong>  Sucecessfully recorede added</div>  ');
                                                        //                                                        window.scrollTo(0, 0);
                                                    } else {
                                                        $('#msg_dev').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');
                                                        window.scrollTo(0, 0);
                                                    }

                                                } else {
                                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                                    window.scrollTo(0, 0);
                                                }

                                            },
                                            error: function () {
                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                                window.scrollTo(0, 0);
                                            }
                                        });
                                    }

                                }
                            }
                        });
                    } else {
                        var dataObject = new Object();
                        dataObject.branchgroupid = $("#branchgroupid").val();
                        dataObject.brnachtargetid = $("#brnachtargetid").val();
                        dataObject.revenue = $("#branchgroupamount").val().replace(/,/g, "");
                        var content = JSON.stringify(dataObject);
                        $.ajax({
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/savebarnchgrouprevenetdata",
                            cache: false,
                            async: false,
                            data: {tab4_info: content},
                            success: function (response) {
                                response = JSON.parse(response);
                                if (response.CODE === "SUCCESS") {
                                    if (response.remaning == '0') {
                                        //                                        $("#brnachtargetid").removeAttr("disabled");
                                        //                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Warning!</strong>  Sucecessfully recorede added</div>  ');
                                    } else {
                                        $('#msg_dev').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');
                                        window.scrollTo(0, 0);
                                    }

                                } else {
                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                    window.scrollTo(0, 0);
                                }

                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                window.scrollTo(0, 0);
                            }
                        });
                    }
                }
                getBranchGroupRevenueDetails();
                //                loadRegionBranchGroupDropdownList();
                $("#tabfourtargetid").rules("remove");
                $("#tabfourregionalid").rules("remove");
                $("#brnachtargetid").rules("remove");
                $("#tabfourtargetid").rules("remove");
                $("#branchgroupamount").rules("remove");
                $("#branchgroupid").rules("remove");
                $("#branchgroupid").prop('selectedIndex', 0);
                $("#branchgroupamount").val("");
            });
            //---------------------------------------------------------------------------------------
            //----------------------------table 6 DATA RETRIVE-------------------------------------------
            //            function getBranchGroupRevenueDetails() {
            //                $('#dt_basic6').dataTable().fnClearTable();
            //                $('#dt_basic6').dataTable().fnDestroy();
            //                $('#dt_basic6').dataTable({
            //                    "bProcessing": true,
            //                    "bServerSide": true,
            //                    "bFilter": false,
            ////                    async: false,
            //                    "cache": false,
            //                    "sPaginationType": "full_numbers",
            //                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
            //                            "t" +
            //                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
            ////                    "autoWidth": true,
            //                    "preDrawCallback": function () {
            //                        // Initialize the responsive datatables helper once.
            //                        if (!responsiveHelper_dt_basic) {
            //                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic6'), breakpointDefinition);
            //                        }
            //                    },
            //                    "rowCallback": function (nRow) {
            //                        responsiveHelper_dt_basic.createExpandIcon(nRow);
            //                    },
            //                    "sAjaxSource": "${pageContext.servletContext.contextPath}/load/branchgrouprevenue",
            //                    "fnServerData": function (sSource, aoData, fnCallback) {
            //                        aoData.push({"name": "targetid", "value": $('#tabfourtargetid').val()});
            //                        aoData.push({"name": "brnachtargetid", "value": $('#brnachtargetid').val()});
            //                        aoData.push({"name": "branchgroupid", "value": $('#branchgroupid').val()});
            //                        ////console.log(aoData);
            //                        $.ajax({
            //                            "dataType": 'json',
            //                            async: true,
            //                            "type": "POST",
            //                            "url": "${pageContext.servletContext.contextPath}/load/branchgrouprevenue",
            //                            "data": aoData,
            //                            "success": fnCallback
            //
            //                        });
            //                    },
            //                    //                "order": [[0, "asc"]],
            //                    "aoColumns": [
            //                        {"mDataProp": "brnachtargetid", "bSortable": false, "sClass": "columnhide"},
            //                        {"mDataProp": "brnachtargetdes", "bSortable": false},
            //                        {"mDataProp": "branchgroupid", "bSortable": false, "sClass": "columnhide"},
            //                        {"mDataProp": "branchgroupdes", "bSortable": false},
            //                        {"mDataProp": "revenue", "bSortable": false},
            //                        {"mRender": function (data, type, full) {
            //                                return '<a  href="#" id="' + full["brnachtargetid"] + '" style="' + full["branchgroupid"] + '"><i class="fa fa-trash-o" aria-hidden="true"></i></a>';
            //                            }}
            //                    ]
            //
            //                });
            //                loadRegionBranchGroupDropdownList();
            //                loadBrancgGroupMemberRegion();
            ////                getRegionBranchActivityGroupDropdown();
            //            }



            function getBranchGroupRevenue() {
                $('#dt_basic6').dataTable().fnDestroy();
                //                $('#dt_basic6').dataTable().fnClearTable();

                //                $('#dt_basic6').dataTable().fnDestroy();
                $("#branchtoatal").empty();
                var totalval = 0;
                var $valid = $("#wizard-1").valid();
                //                $('#dt_basic6').dataTable().fnDestroy();
                //                if ($valid) {
                //                    $('#dt_basic6').dataTable().fnClearTable();
                $('#dt_basic6').dataTable().fnDestroy();
                $('#dt_basic6').dataTable({
                    "bProcessing": true,
                    "bServerSide": true,
                    "bFilter": false,
                    "async": false,
                    "cache": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                    "autoWidth": true,

                    "sAjaxSource": "${pageContext.servletContext.contextPath}/load/teamrevenue",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "targetid", "value": $('#tabfourtargetid').val()});
                        aoData.push({"name": "brnachtargetid", "value": $('#brnachtargetid').val()});
                        aoData.push({"name": "branchgroupid", "value": $('#branchgroupid').val()});
                        ////console.log(aoData);
                        $.ajax({
                            "dataType": 'json',
                            async: false,
                            "type": "POST",
                            "url": "${pageContext.servletContext.contextPath}/load/teamrevenue",
                            "data": aoData,
                            "success": fnCallback

                        });
                    },
                    //                "order": [[0, "asc"]],

                    "aoColumns": [
                        {"mDataProp": "branchgrouptargetid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "brnachtargetid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "brnachtargetdes", "bSortable": false},
                        {"mDataProp": "branchgroupid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "branchgroupdes", "bSortable": false},
                        {"mRender": function (data, type, full) {
                                return '<input type="text" id2="' + full["branchgroupid"] + '"  id="' + full["branchgrouptargetid"] + '" name="' + full["branchgrouptargetid"] + '"   value="' + full["revenue"] + '" onfocusout="brachrevenuecalcuation();"  > <div id="errorContainer"></div>';
                            }},
                        {"mDataProp": "action", "bSortable": false},
                    ],
                    "footerCallback": function (tfoot, data, start, end, display) {

                        data.forEach(function (x) {
                            totalval += parseInt((x['revenue']).replace(/,/g, ""));
                        });

                    }

                });
//                $("#branchtoatal").append('$' + $.number(totalval) + ' ( $' + $.number($("#barnchamount").val()) + ' total)');
                brachrevenuecalcuation();
                //                    loadRegionBranchGroupDropdownList();
                //                    loadBrancgGroupMemberRegion();
                //                    getRegionBranchActivityGroupDropdown();
                //                }
            }

            jQuery.validator.addMethod("barnchamountlessthan", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                if ($.isNumeric(i)) {
                    if (i <= $("#barnchamount").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Pleas enter less than target amount");
            jQuery.validator.addMethod("barnchtotalamout", function (value, element) {
                var COUNT = 0;
                $('#dt_basic6').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                        }
                    });
                });
                if (COUNT <= $("#barnchamount").val().replace(/,/g, "")) {
                    return true;
                } else {
                    return false;
                }
            }, "Target amount exceeded");
            function brachrevenuecalcuation() {
                $("#branchtoatal").empty();
                $('#msg_dev').empty();
                $("#totalrevenue").rules("remove");
                var $valid = $("#wizard-1").valid();
                $('#dt_basic6').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).rules('add', {
                            //                            required: true,
//                            number: true,
                            barnchamountlessthan: true,
                            barnchtotalamout: true
                        });
                        $(this).number(true, ',', ' ');
                    });
                });
                var array = [];
                var COUNT = 0;
                //                var BRANCHGROUPID = 0;
                $('#dt_basic6').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric(($(this).val().replace(/,/g, "")))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                            array.push({
                                ID: $(this).attr("id"),
                                COUNT: $(this).val(),
                                BRANCHGROUPID: $(this).attr("id2")
                            });
                        }
                    });
                });
                if ($valid) {
//                    $.SmartMessageBox({
//                        title: "Confirmation!",
//                        content: "Are you sure you want to change the Target Amount? This will clear  any already created target amounts in lower levels of the hierarchy.",
//                        buttons: '[No][Yes]'
//                    }, function (ButtonPressed) {
//                        if (ButtonPressed === "Yes") {
                    $("#branchtoatal").empty();
                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.branchgroupid = $("#branchgroupid").val();
                    dataObject.brnachtargetid = $("#brnachtargetid").val();
                    dataObject.revenue = $("#branchgroupamount").val().replace(/,/g, "");
                    dataObject.datatabledata = $('#datatabledata').val();
                    //                    ////console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/updatebranchgrouprevenue",
                        cache: false,
                        async: false,
                        data: {datatable_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            //                            ////console.log(response);
                            if (response.CODE === "SUCCESS") {
                                if (response.remaning == '0') {
                                } else {
                                    $('#msg_dev').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');
                                    window.scrollTo(0, 0);
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                window.scrollTo(0, 0);
                            }
                            $("#branchtoatal").append('$' + $.number(COUNT) + ' ( $' + $.number(response.sum) + ' total)');
                        },
                        error: function () {
//                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            //                            window.scrollTo(0, 0);
                        }
                    });
//                            var dataObject = new Object();
//                            dataObject.targetid = $("#gentargetid").val();
//                            var content = JSON.stringify(dataObject);
//                            $.ajax({type: "post",
//                                url: "${pageContext.servletContext.contextPath}/deletealltargetafterfourrhtabfirstpart",
//                                cache: false,
//                                async: false,
//                                data: {tab1_info: content},
//                                success: function (response) {
//                                    response = JSON.parse(response);
//                                    if (response.status === true) {
//                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');
//
//                                    } else {
//                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
//                                        window.scrollTo(0, 0);
//                                    }
//
//                                },
//                                error: function () {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                    window.scrollTo(0, 0);
//                                }
//                            });
//                            getBranchGroupMemberDetails();
//                        }
//                        if (ButtonPressed === "No") {
//                            getBranchGroupRevenue();
//                        }
//
//                    });



                    //                    loadregionlistByTargetid();
                    //                    loadActivityList();
                    //                    getBranchGroupRevenue();
                }
            }


            $('#dt_basic6').on('click', 'a', function (e) {
                var id = $(this).attr('id');
                $("#totalbranchgroprevene").val($(this).closest('tr').find("input,text").val());
                $("#dt_basic7").dataTable().fnClearTable();
                $("#dt_basic7").dataTable().fnDestroy();
                getBranchGroupMemberDetails(id);

                ////console.log(table.row(this).data());
            });
            //---------------------------------------------------------------------------------------
            //----------------------------table row remove-------------------------------------------
            //            $('#dt_basic6').on('click', 'tr a', function (e) {
            //                e.preventDefault();
            //                var id = $(this).attr('id');
            //                var id2 = $(this).attr('style');
            //                deletetable2row(id, id2);
            //                getBranchGroupRevenueDetails();
            //            }
            //            );

            function  deletetable2row(id1, id2) {
                var dataObject = new Object();
                dataObject.brnachtargetid = id1;
                dataObject.branchgroupid = id2;
                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/deletetabelsixrow",
                    cache: false,
                    data: {tab4_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div>  ');
                            window.scrollTo(0, 0);
                            $("#branchgroupamount").val("");
                        } else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            //-----------------------------------------------------------------------------------------
            //
            function validationDatatbelSix() {
                var sum = 0;
                var selectedItem = $('#brnachtargetid option:selected');
                var brnachtargetid = selectedItem.val();
                $('#dt_basic6').find('tr').each(function () {
                    var table6 = $('#dt_basic6').DataTable();
                    var idx = table6.row(this).index();
                    if (idx >= 0) {
                        var tbrnachtargetid = table6.cell(this, 0).data();
                        if (brnachtargetid === tbrnachtargetid) {
                            //                            sum += parseInt($("#branchgroupamount").val());
                            ////console.log("sum" + sum);
                        } else {
                            sum += parseInt(table6.cell(this, 4).data().replace(/,/g, ""));
                        }
                    }

                });
                jQuery.validator.addMethod("amountvalidation", function () {
                    if ($("#barnchamount").val().replace(/,/g, "") >= parseInt($("#branchgroupamount").val().replace(/,/g, ""))) {
                        if (parseInt($("#barnchamount").val()) >= parseInt(sum) + parseInt($("#branchgroupamount").val().replace(/,/g, ""))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }

                }, jQuery.validator.format("Invalid Amout."));
            }
            //--------------------------load region barncg group dropdwon----------------------------
            //            function loadRegionBranchGroupDropdownList() {
            //                $('#branchgrouprevenuetargetid').empty();
            //                $('#branchgrouprevenuetargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
            //                var dataObject = new Object();
            //                dataObject.brnachtargetid = $("#brnachtargetidbmr").val();
            //                var content = JSON.stringify(dataObject);
            //                $.ajax({
            //                    type: "POST",
            //                    async: false,
            //                    url: "${pageContext.servletContext.contextPath}/loadbranchgroupmembergroup",
            //                    data: {tab4_info: content},
            //                    cache: false, success: function (response) {
            //                        response = JSON.parse(response);
            //                        ////console.log(response);
            //                        for (var i = 0; i < response.regionbranchgrouplist.length; i++) {
            //                            $('#branchgrouprevenuetargetid').append($("<option></option>").attr("value", response.regionbranchgrouplist[i].branchgrouprevenuetargetid).text(response.regionbranchgrouplist[i].branchgrouprevenuetargetdes));
            //                        }
            //
            //                    },
            //                    error: function () {
            //                        ////console.log('Error while request..');
            //                    }
            //                });
            //            }

            //            function loadBrancgGroupMemberRegion() {
            //                $('#tabfourregionalidbmr').empty();
            //                $('#tabfourregionalidbmr').append($("<option></option>").attr("value", '').text('-- Select --'));
            //                var dataObject = new Object();
            //                dataObject.targetid = $("#tabfourtargetid").val();
            //                var content = JSON.stringify(dataObject);
            //                $.ajax({
            //                    type: "POST",
            //                    async: false,
            //                    url: "${pageContext.servletContext.contextPath}/loadbranchgroupmemberregion",
            //                    data: {tab4_info: content},
            //                    cache: false, success: function (response) {
            //                        response = JSON.parse(response);
            //                        ////console.log(response);
            //                        for (var i = 0; i < response.loadbranchgroupmemberregion.length; i++) {
            //                            $('#tabfourregionalidbmr').append($("<option></option>").attr("value", response.loadbranchgroupmemberregion[i].regionid).text(response.loadbranchgroupmemberregion[i].regiondes));
            //                        }
            //
            //                    },
            //                    error: function () {
            //                        ////console.log('Error while request..');
            //                    }
            //                });
            //            }

            function loadBrancgGroupMemberBranch() {
                $("#dt_basic7").dataTable().fnClearTable();
                $("#dt_basic7").dataTable().fnDestroy();
                $("#branchgrouprevenuetargetid").prop("selectedIndex", 0);
                $("#branchgrouprevenuetargetid").trigger('change');
                $("#brnachtargetidbmr").trigger('change');
                $("#branchgrouprevenuetargetid").trigger('change');
                $('#brnachtargetidbmr').empty();
                $("#totalbranchgroprevene").val("");
                $('#brnachtargetidbmr').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.regionaltargetid = $("#tabfourregionalidbmr").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/loadbranchgroupmemberbranch",
                    data: {tab4_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response);
                        for (var i = 0; i < response.loadbranchgroupmemberbranch.length; i++) {
                            $('#brnachtargetidbmr').append($("<option></option>").attr("value", response.loadbranchgroupmemberbranch[i].branchid).text(response.loadbranchgroupmemberbranch[i].branchdes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }

            function loadBrancgGroupMemberGroup() {
                $('#brnachtargetidbmr').empty();
                $('#brnachtargetidbmr').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.brnachtargetid = $("#brnachtargetidbmr").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/loadbranchgroupmembergroup",
                    data: {tab4_info: content},
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response);
                        for (var i = 0; i < response.loadbranchgroupmemberbranch.length; i++) {
                            $('#branchgrouprevenuetargetid').append($("<option></option>").attr("value", response.loadbranchgroupmemberregion[i].regionid).text(response.loadbranchgroupmemberregion[i].regiondes));
                        }

                    },
                    error: function () {
                        ////console.log('Error while request..');
                    }
                });
            }

            //----------------------------------------------------------------------------------------
            //---------------------------------load barnacg revene group amount---------------------
            function getBranchGroupRevenueTarget() {
                $("#memberrevenue").val("");
                $("#branchgroupmembertargetid").prop('selectedIndex', 0);
                $("#totalbranchgroprevene").val("");
                var dataObject = new Object();
                dataObject.branchgrouprevenuetargetid = $("#branchgrouprevenuetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/branchgrouprevenuamount",
                    cache: false,
                    async: true,
                    data: {tab4_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        TOTAL = response.amount;
                        $("#totalbranchgroprevene").val(TOTAL);
                        $("#branchgroupmembertargetid").empty();
                        $('#branchgroupmembertargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                        for (var i = 0; i < response.emplist.length; i++) {
                            $('#branchgroupmembertargetid').append($("<option></option>").attr("value", response.emplist[i].empid).text(response.emplist[i].empname));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                //                getBranchGroupMemberDetails();
                //                getRegionBranchActivityGroupDropdown();
            }


            //----------------------------------------------------------------------------------------
            function membertargetonchange() {
                $("#memberrevenue").val("");
                $('#dt_basic7').find('tr').each(function () {
                    var selectedItem = $('#branchgroupmembertargetid option:selected');
                    var branchgroupmembertargetid = selectedItem.val();
                    var table7 = $('#dt_basic7').DataTable();
                    var idx = table7.row(this).index();
                    if (idx >= 0) {
                        var tbranchgroupmembertargetid = table7.cell(this, 2).data();
                        if (branchgroupmembertargetid === tbranchgroupmembertargetid) {
                            $("#memberrevenue").val(table7.cell(this, 4).data());
                        }
                    }
                });
            }
            //


            function validationDatatbelSeven() {
                var sum = 0;
                var selectedItem = $('#branchgrouprevenuetargetid option:selected');
                var selectedItem2 = $('#branchgroupmembertargetid option:selected');
                var branchgrouprevenuetargetid = selectedItem.val();
                var empid = selectedItem2.val();
                $('#dt_basic7').find('tr').each(function () {
                    var table7 = $('#dt_basic7').DataTable();
                    var idx = table7.row(this).index();
                    if (idx >= 0) {
                        var tbranchgrouprevenuetargetid = table7.cell(this, 0).data();
                        var tempid = table7.cell(this, 2).data();
                        if (branchgrouprevenuetargetid === tbranchgrouprevenuetargetid && empid === tempid) {
                            ////console.log("true validarion");
                        } else {
                            ////console.log("sum");
                            ////console.log(branchgrouprevenuetargetid);
                            ////console.log(tbranchgrouprevenuetargetid);
                            if (branchgrouprevenuetargetid === tbranchgrouprevenuetargetid) {
                                sum += parseInt(table7.cell(this, 4).data().replace(/,/g, ""));
                                ////console.log(sum);
                            }

                        }
                    }
                });
                jQuery.validator.addMethod("tbranchgrouprevenuetargetid", function () {
                    ////console.log(sum);
                    ////console.log($("#totalbranchgroprevene").val().replace(/,/g, ""));
                    ////console.log($("#memberrevenue").val().replace(/,/g, ""));
                    ////console.log($("#memberrevenue").val().replace(/,/g, "") + parseInt(sum));
                    if ($("#totalbranchgroprevene").val().replace(/,/g, "") >= parseInt($("#memberrevenue").val().replace(/,/g, "")) + parseInt(sum)) {
                        return true;
                    }
                    else {
                        return false;
                    }

                }, jQuery.validator.format("Invalid amout."));
            }

            //--------------------------------add branch memeber revebr table row-------------------------------------
            //


            $('#addbranchmemberrevenue').on('click', function () {
                $('#msg_dev').empty();
                validationDatatbelSeven();
                $("#memberrevenue").rules('add', {
                    tbranchgrouprevenuetargetid: true,
                    number: true
                });
                var $valid = $("#wizard-1").valid();
                if ($valid) {
                    var sum = table7.column(2).data().sum();
                    var amount = $("#branchgroupamount").val().replace(/,/g, "");
                    var total = sum + amount;
                    var selectedItem = $('#branchgrouprevenuetargetid option:selected');
                    var selectedItem2 = $('#branchgroupmembertargetid option:selected');
                    var rbgid = selectedItem.val();
                    var rbgtext = selectedItem.text();
                    var empname = selectedItem2.text();
                    var empid = selectedItem2.val();
                    var status = false;
                    $('#dt_basic7').find('tr').each(function () {
                        var table7 = $('#dt_basic7').DataTable();
                        var idx = table7.row(this).index();
                        if (idx >= 0) {
                            var trbgid = table7.cell(this, 0).data();
                            var tempid = table7.cell(this, 2).data();
                            if (empid === tempid) {
                                status = true;
                            }
                        }
                    });
                    if (status) {
                        ////console.log("status");
                        $('#dt_basic7').find('tr').each(function () {
                            var table7 = $('#dt_basic7').DataTable();
                            var idx = table7.row(this).index();
                            if (idx >= 0) {
                                var trgbid = table7.cell(this, 0).data();
                                var tempid = table7.cell(this, 2).data();
                                //                                if (rbgid === trgbid) {
                                if (empid === tempid) {
                                    var dataObject = new Object();
                                    dataObject.branchgrouprevenuetargetid = $("#branchgrouprevenuetargetid").val();
                                    dataObject.branchgroupmembertargetid = $("#branchgroupmembertargetid").val();
                                    dataObject.revenue = $("#memberrevenue").val();
                                    var content = JSON.stringify(dataObject);
                                    $.ajax({
                                        type: "post",
                                        url: "${pageContext.servletContext.contextPath}/updatebarnchgroupmemberdata",
                                        cache: false,
                                        async: false,
                                        data: {tab4_info: content},
                                        success: function (response) {
                                            response = JSON.parse(response);
                                            if (response.CODE === "SUCCESS") {
                                                if (response.remaning == '0') {
                                                    //                                                    $("#brnachtargetid").removeAttr("disabled");
                                                    //                                                    $('#msg_dev').html('<div class="alert alert-success"><strong>Sucsess!</strong>  Sucecessfully recorede added</div>  ');
                                                } else {
                                                    $('#msg_dev').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');
                                                    window.scrollTo(0, 0);
                                                }

                                            } else {
                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong>Error Please Re-Try!</div>  ');
                                                window.scrollTo(0, 0);
                                            }

                                        },
                                        error: function () {
                                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                            window.scrollTo(0, 0);
                                        }
                                    });
                                }

                                //                                }
                            }
                        });
                    } else {
                        var dataObject = new Object();
                        dataObject.branchgrouprevenuetargetid = $("#branchgrouprevenuetargetid").val();
                        dataObject.branchgroupmembertargetid = $("#branchgroupmembertargetid").val();
                        dataObject.revenue = $("#memberrevenue").val();
                        var content = JSON.stringify(dataObject);
                        $.ajax({
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/savebarnchgroupmemberdata",
                            cache: false,
                            async: false,
                            data: {tab4_info: content},
                            success: function (response) {
                                response = JSON.parse(response);
                                if (response.CODE === "SUCCESS") {
                                    if (response.remaning == '0') {
                                        $("#brnachtargetid").removeAttr("disabled");
                                        //                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Sucsess!</strong>  Sucecessfully recorede added</div>  ');
                                        //                                        window.scrollTo(0, 0);
                                    } else {
                                        $('#msg_dev').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');
                                        window.scrollTo(0, 0);
                                    }

                                } else {
                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                    window.scrollTo(0, 0);
                                }


                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                window.scrollTo(0, 0);
                            }
                        });
                        //                        getBranchGroupMemberDetails();
                    }
                }
                //                getBranchGroupMemberDetails();
            });
            //----------------------------------------------------------------------------------------
            //-----------------------------datatabel 7 data load--------------------------------------
            //            function getBranchGroupMemberDetails() {
            ////                alert("data seacrh");
            ////                ////console.log("get datat tabel data");
            //                $('#dt_basic7').dataTable().fnClearTable();
            //                $('#dt_basic7').dataTable().fnDestroy();
            //                $('#dt_basic7').dataTable({
            //                    "bProcessing": false,
            //                    "bServerSide": true,
            //                    "bFilter": false,
            ////                    cache: false,
            //                    "sPaginationType": "full_numbers",
            //                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
            //                            "t" +
            //                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
            ////                    "autoWidth": true,
            //                    "sAjaxSource": "${pageContext.servletContext.contextPath}/load/branchgroupmemberdetail",
            //                    "fnServerData": function (sSource, aoData, fnCallback) {
            //                        aoData.push({"name": "branchgrouprevenuetargetid", "value": $('#branchgrouprevenuetargetid').val()});
            //                        $.ajax({
            //                            "dataType": 'json',
            //                            async: true,
            //                            "type": "POST",
            //                            "url": "${pageContext.servletContext.contextPath}/load/branchgroupmemberdetail",
            //                            "data": aoData,
            //                            "success": fnCallback
            //
            //                        });
            //                    }, "aoColumns": [
            //                        {"mDataProp": "branchgrouprevenuetargetid", "bSortable": false, "sClass": "columnhide"},
            //                        {"mDataProp": "branchgrouprevenuetargetdes", "bSortable": false},
            //                        {"mDataProp": "branchgroupmemberid", "bSortable": false, "sClass": "columnhide"},
            //                        {"mDataProp": "empname", "bSortable": false},
            //                        {"mDataProp": "revenue", "bSortable": false},
            //                        {"mRender": function (data, type, full) {
            //                                return '<a  href="#" id="' + full["branchgrouprevenuetargetid"] + '" style="' + full["branchgroupmemberid"] + '"><i class="fa fa-trash-o" aria-hidden="true"></i></a>';
            ////                                return full['branchgroupmembertargetid'];
            //                            }
            //                        }
            //                    ]
            //
            //                });
            //                ////console.log("get datat tabel data");
            //            }
            //            

            function getBranchGroupMemberDetails(branchgrouprevenuetargetid) {
                //                alert("data seacrh");
                //                ////console.log("get datat tabel data");
                $('#dt_basic7').dataTable().fnClearTable();
                $('#dt_basic7').dataTable().fnDestroy();
                $('#dt_basic7').dataTable({
                    "bProcessing": false,
                    "bServerSide": true,
                    "bFilter": false,
                    //                    cache: false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                    "autoWidth": true,
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/load/branchgroupmemberdetail",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "branchgrouprevenuetargetid", "value": branchgrouprevenuetargetid});
                        $.ajax({
                            "dataType": 'json',
                            async: false,
                            "type": "POST",
                            "url": "${pageContext.servletContext.contextPath}/load/branchgroupmemberdetail",
                            "data": aoData,
                            "success": fnCallback

                        });
                    }, "aoColumns": [
                        {"mDataProp": "branchgroupmembertargetid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "branchgrouprevenuetargetid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "branchgrouprevenuetargetdes", "bSortable": false},
                        {"mDataProp": "branchgroupmemberid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "empname", "bSortable": false},
                        //                        {"mDataProp": "revenue", "bSortable": false},
                        {"mRender": function (data, type, full) {
                                return '<input type="text"  id="' + full["branchgroupmembertargetid"] + '" name="' + full["branchgroupmembertargetid"] + '" id2="' + full["branchgrouprevenuetargetid"] + '"   value="' + full["revenue"] + '" onfocusout="bracugroupmembercalcuation();"  > <div id="errorContainer"></div>';
                            }
                        }
                    ]

                });
                bracugroupmembercalcuation();
                ////console.log("get datat tabel data");
            }

            jQuery.validator.addMethod("barnchamountmemberlessthan", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                if ($.isNumeric(i)) {
                    if (i <= $("#totalbranchgroprevene").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Pleas enter less than target amount");
            jQuery.validator.addMethod("barnchmembertotalamout", function (value, element) {
                var COUNT = 0;
                $('#dt_basic7').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                        }
                    });
                });
                if (COUNT <= $("#totalbranchgroprevene").val().replace(/,/g, "")) {
                    return true;
                } else {
                    return false;
                }
            }, "Target amount exceeded");
            function bracugroupmembercalcuation() {
                //                alert("tets");
                $("#branchmembertoatal").empty();
                $('#msg_dev').empty();
                $('#msg_dev_tmr').empty();
                $("#totalrevenue").rules("remove");
                var $valid = $("#wizard-1").valid();
                $('#dt_basic7').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).rules('add', {
                            //                            required: true,
                            number: true,
                            barnchmembertotalamout: true,
                            barnchamountmemberlessthan: true
                        });
                        $(this).number(true, ',', ' ');
                    });
                });
                var array = [];
                var COUNT = 0;
                //                var BRANCHGROUPID = 0;
                $('#dt_basic7').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric(($(this).val().replace(/,/g, "")))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                            array.push({
                                ID: $(this).attr("id"),
                                COUNT: $(this).val(),
                                BGRTID: $(this).attr("id2")
                            });
                        }
                    });
                });
                if ($valid) {
                    $("#branchmembertoatal").empty();
                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.branchgrouprevenuetargetid = $("#branchgrouprevenuetargetid").val();
                    dataObject.branchgroupmembertargetid = $("#branchgroupmembertargetid").val();
                    dataObject.revenue = $("#memberrevenue").val();
                    dataObject.datatabledata = $('#datatabledata').val();
                    //                    ////console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/updatebranchgroupmemberrevenue",
                        cache: false,
                        async: false,
                        data: {datatable_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            //                            ////console.log(response);
                            if (response.CODE === "SUCCESS") {
                                if (response.remaning == '0') {
                                    //                                        $("#brnachtargetid").removeAttr("disabled");
                                    //                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Warning!</strong>  Sucecessfully recorede added</div>  ');
                                } else {
                                    $('#msg_dev_tmr').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');

                                }
                            } else {
                                $('#msg_dev_tmr').html('<div class="alert alert-warning"><strong>Warning!</strong> Invalid Amount</div>  ');

//                                window.scrollTo(0, 0);
                            }
                            $("#branchmembertoatal").append('$' + $.number(COUNT) + ' ( $' + $.number(response.sum) + ' total)');
                            //                            $("#currentamout").val(response.total);
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            window.scrollTo(0, 0);
                        }
                    });
                    //                    loadregionlistByTargetid();
                    //                    loadActivityList();
                }
            }




            //----------------------------------------------------------------------------------------------

            //----------------------------table7 row remove-------------------------------------------
            //            $('#dt_basic7').on('click', 'tr a', function (e) {
            //                e.preventDefault();
            //                var id = $(this).attr('id');
            //                var id2 = $(this).attr('style');
            //                deletetable7row(id, id2);
            //                getBranchGroupMemberDetails();
            //            });
            function  deletetable7row(id1, id2) {
                var dataObject = new Object();
                dataObject.branchgrouprevenuetargetid = id1;
                dataObject.branchgroupmemberid = id2;
                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/deletetablerow",
                    cache: false,
                    data: {tab4_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div>  ');
                            window.scrollTo(0, 0);
                        } else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }
            //branchgroupclick();
            //-----------------------------------------------------------------------------------------
            //---------------------tab5 branch grop activiity----------------------------------

            function getRegionList() {
                $("#tabfiveregionid").empty();
                $('#tabfiveregionid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabfivetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfiveregionlist",
                    cache: false,
                    async: false,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.regionlist.length; i++) {
                            $('#tabfiveregionid').append($("<option></option>").attr("value", response.regionlist[i].regionid).text(response.regionlist[i].regiondes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            function getRegionActivityList() {
                $("#msg_dev_tma").empty();
                $("#msg_dev").empty();
                $("#regionalactivitytargetid").empty();
                $('#regionalactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                $("#branchactivitytargetid").empty();
                $('#branchactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));

                var dataObject = new Object();
                dataObject.targetid = $("#tabfivetargetid").val();
                dataObject.regionid = $("#tabfiveregionid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfiveregionactivitylist",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.activitylist.length; i++) {
                            $('#regionalactivitytargetid').append($("<option></option>").attr("value", response.activitylist[i].activitytypeid).text(response.activitylist[i].activitytypedes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                $("#dt_basic8").dataTable().fnClearTable();
                $("#dt_basic9").dataTable().fnClearTable();
                $("#dt_basic8").dataTable().fnDestroy();
                $("#dt_basic9").dataTable().fnDestroy();
                $("#bmactivity").empty();
                $('#dt_basic8 tbody').empty();
                $('#dt_basic9 tbody').empty();
                $("#branchactivitytargetid").prop('selectedIndex', 0);
                $("#branchactivitycount").val("");
            }


            function getRegionActivityBranchList() {
                $('#msg_dev').empty();
                $("#branchactivitytargetid").empty();
                $('#branchactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.branchactivitytargetid = $("#regionalactivitytargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfiveregionactivitybranchlist",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.barnchlist.length; i++) {
                            $('#branchactivitytargetid').append($("<option></option>").attr("value", response.barnchlist[i].branchactivitytargetid).text(response.barnchlist[i].branchactivitytargetdes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                //                tabfiveloadgroupList();
                $("#dt_basic8").dataTable().fnClearTable();
                $("#dt_basic9").dataTable().fnClearTable();
                $("#dt_basic8").dataTable().fnDestroy();
                $("#dt_basic9").dataTable().fnDestroy();
                $("#bmactivity").empty();
                $("#bactivity").empty();
                $('#dt_basic8 tbody').empty();
                $('#dt_basic9 tbody').empty();
                $("#branchactivitytargetid").prop('selectedIndex', 0);
                //                $("#tabfiveregionid").prop('selectedIndex', 0);
                $("#branchactivitycount").val("");
            }


            function getRegionBranchActivity() {
                $("#branchactivitycount").val("0");
                $("#regionalactivitytargetid").prop('selectedIndex', 0);
                $("#branchactivitytargetid").prop('selectedIndex', 0);
                //                $("#").prop('selectedIndex', 0);
                //                $("#dt_basic8").dataTable().fnClearTable();
                $("regionalactivitytargetid").trigger('change');
                $("branchactivitytargetid").trigger('change');
                //                getBranchGroupActivityDetails();
                getRegionList();
                //                getBranchGroupMemberRegionList();


            }


            function getBranchGroupMemberRegionList() {
                $("#tabfivememberregionid").empty();
                $('#tabfivememberregionid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabfivetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfivememberregionalist",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.regionlist.length; i++) {
                            $('#tabfivememberregionid').append($("<option></option>").attr("value", response.regionlist[i].regionid).text(response.regionlist[i].regiondes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            function getMemberRegionActivityList() {
                $("#memberregionalactivitytargetid").empty();
                $('#memberregionalactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabfivetargetid").val();
                dataObject.regionid = $("#tabfivememberregionid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfiveregionactivitylist",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.activitylist.length; i++) {
                            $('#memberregionalactivitytargetid').append($("<option></option>").attr("value", response.activitylist[i].activitytypeid).text(response.activitylist[i].activitytypedes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                //                $("#dt_basic9").dataTable().fnClearTable();
                $("#memberbranchactivitytargetid").prop("selectedIndex", 0);
                $("#regionalactivitytargetid").prop("selectedIndex", 0);
                $("#tabfivetotalbranchgroprevene").val("");
                $("#memberbranchactivitytargetid").trigger("change");
                $("#branchgroupactivitytargeid").trigger("change");
            }



            function getMemberActivityList() {
                $("#memberregionalactivitytargetid").empty();
                $('#memberregionalactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabfivetargetid").val();
                dataObject.regionid = $("#tabfiveregionid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfivememeberregionactivitylist",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.activitylist.length; i++) {
                            $('#memberregionalactivitytargetid').append($("<option></option>").attr("value", response.activitylist[i].activitytypeid).text(response.activitylist[i].activitytypedes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            function getMemberBramchList() {
                $("#memberbranchactivitytargetid").empty();
                $('#memberbranchactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.regionalactivitytargetid = $("#memberregionalactivitytargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfivememeberregionabranchlist",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.barnchlist.length; i++) {
                            $('#memberbranchactivitytargetid').append($("<option></option>").attr("value", response.barnchlist[i].branchactivitytargetid).text(response.barnchlist[i].branchactivitytargetdes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            function getMemberBramchGroupList() {
                $("#branchgroupactivitytargeid").empty();
                $('#branchgroupactivitytargeid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.branchactivitytargetid = $("#memberbranchactivitytargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfivememeberregionabranchgrouplist",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        for (var i = 0; i < response.barnchgrouplist.length; i++) {
                            $('#branchgroupactivitytargeid').append($("<option></option>").attr("value", response.barnchgrouplist[i].branchgroupid).text(response.barnchgrouplist[i].branchgroupdes));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                getBranchGroupMember2();
            }

            //-----------------------------------------------------------------------------------------
            //----------------------------------getbarnchactivity count-----------------------------------

            function getBranchActivityCount() {
                $('#msg_dev').empty();
                $("#branchgrouptivitycount").val("");
                $("#branchactivitycount").val("");
                var dataObject = new Object();
                dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();
                var content = JSON.stringify(dataObject);
                ////console.log(content);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/branchactivitycount",
                    cache: false,
                    async: false,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        ////console.log(response.count);
                        $("#branchactivitycount").val(response.count);
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                getBranchGroupActivityDetails();
                $("#dt_basic9").dataTable().fnClearTable();
                $("#dt_basic9").dataTable().fnDestroy();
                $("#bmactivity").empty();
                $('#dt_basic9 tbody').empty();


            }

            //--------------------------------add  table8 row add------------------------------------
            //

            $('#addBrnachGroupActivityTarget').on('click', function () {
                $("#tabfiveregionid").rules("add", {
                    required: true
                });
                $("#regionalactivitytargetid").rules("add", {
                    required: true
                });
                $("#branchactivitytargetid").rules("add", {
                    required: true
                });
                $("#tabfivebranchgrouptargetid").rules("add", {
                    required: true
                });
                $('#msg_dev').empty();
                validationDatatbelEight();
                $("#branchgrouptivitycount").rules('add', {
                    tbranchgrouprevenuetargetid: true,
                    number: true
                });
                var $valid = $("#wizard-1").valid();
                ////console.log("validation" + $valid);
                if ($valid) {
                    var selectedItem = $('#branchactivitytargetid option:selected');
                    var selectedItem2 = $('#tabfivebranchgrouptargetid option:selected');
                    var branchactivityid = selectedItem.val();
                    var branchactivitydes = selectedItem.text();
                    var groupid = selectedItem2.val();
                    var groupdes = selectedItem2.text();
                    var status = false;
                    $('#dt_basic8').find('tr').each(function () {
                        var table8 = $('#dt_basic8').DataTable();
                        var idx = table8.row(this).index();
                        if (idx >= 0) {
                            var tbranchactivityid = table8.cell(this, 0).data();
                            var tgroupid = table8.cell(this, 2).data();
                            if (branchactivityid === tbranchactivityid && groupid === tgroupid) {
                                status = true;
                            }
                        }
                    });
                    if (status) {
                        $('#dt_basic8').find('tr').each(function () {
                            var table8 = $('#dt_basic8').DataTable();
                            var idx = table8.row(this).index();
                            if (idx >= 0) {
                                var tbranchactivityid = table8.cell(this, 0).data();
                                var tgroupid = table8.cell(this, 2).data();
                                if (branchactivityid === tbranchactivityid) {
                                    if (groupid === tgroupid) {
                                        var dataObject = new Object();
                                        dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();
                                        dataObject.count = $("#branchgrouptivitycount").val().replace(/,/g, "");
                                        dataObject.branchgroupid = $("#tabfivebranchgrouptargetid").val();
                                        var content = JSON.stringify(dataObject);
                                        $.ajax({type: "post",
                                            url: "${pageContext.servletContext.contextPath}/updatebrangroupactivity",
                                            cache: false,
                                            async: false,
                                            data: {tab5_info: content},
                                            success: function (response) {
                                                response = JSON.parse(response);
                                            },
                                            error: function () {
                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                                window.scrollTo(0, 0);
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    } else {
                        var dataObject = new Object();
                        dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();
                        dataObject.count = $("#branchgrouptivitycount").val().replace(/,/g, "");
                        dataObject.branchgroupid = $("#tabfivebranchgrouptargetid").val();
                        var content = JSON.stringify(dataObject);
                        $.ajax({type: "post",
                            url: "${pageContext.servletContext.contextPath}/savebrangroupactivity",
                            cache: false,
                            async: false,
                            data: {tab5_info: content},
                            success: function (response) {
                                response = JSON.parse(response);
                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                window.scrollTo(0, 0);
                            }});
                    }
                }
                getBranchGroupActivityDetails();
                //                 getBranchGroupMemberRegionList();


            });
            //----------------------------------------------------------------------------------------
            function branchgroupclick() {
                $("#branchgrouptivitycount").val("");
                $('#dt_basic8').find('tr').each(function () {
                    var selectedItem = $('#tabfivebranchgrouptargetid option:selected');
                    var branchactivitytargetid = selectedItem.val();
                    var table8 = $('#dt_basic8').DataTable();
                    var idx = table8.row(this).index();
                    if (idx >= 0) {

                        var tbranchactivitytargetid = table8.cell(this, 2).data();
                        if (branchactivitytargetid === tbranchactivitytargetid) {
                            $("#branchgrouptivitycount").val(table8.cell(this, 4).data().replace(/,/g, ""));
                        }
                    }
                });
            }

            function validationDatatbelEight() {
                var sum = 0;
                var selectedItem = $('#branchactivitytargetid option:selected');
                var selectedItem2 = $('#tabfivebranchgrouptargetid option:selected');
                var branchactivitytargetid = selectedItem.val();
                var branchgroupid = selectedItem2.val();
                $('#dt_basic8').find('tr').each(function () {
                    var table8 = $('#dt_basic8').DataTable();
                    var idx = table8.row(this).index();
                    if (idx >= 0) {
                        var tbranchactivitytargetid = table8.cell(this, 0).data();
                        var tbranchgroupid = table8.cell(this, 2).data();
                        if (branchactivitytargetid === tbranchactivitytargetid && tbranchgroupid === branchgroupid) {

                        } else {
                            if (branchactivitytargetid === tbranchactivitytargetid) {
                                sum += parseInt(table8.cell(this, 4).data());
                            }

                        }
                        ////console.log(sum);
                    }
                });
                jQuery.validator.addMethod("tbranchgrouprevenuetargetid", function () {
                    ////console.log(sum);
                    ////console.log($("#branchactivitycount").val());
                    ////console.log($("#branchgrouptivitycount").val());
                    ////console.log(parseInt(sum) + parseInt($("#branchgrouptivitycount").val().replace(/,/g, "")));
                    if (parseInt($("#branchactivitycount").val().replace(/,/g, "")) >= parseInt(sum) + parseInt($("#branchgrouptivitycount").val().replace(/,/g, ""))) {
                        return true;
                    } else {
                        return false;
                    }

                }, jQuery.validator.format("Invalid Count."));
            }

            //-----------------------------datatabel 8 data load--------------------------------------
            function getBranchGroupActivityDetails() {
                $("#bactivity").empty();
                var totalval = 0;
                $('#dt_basic8').dataTable().fnClearTable();
                $('#dt_basic8').dataTable().fnDestroy();
                $('#dt_basic8').dataTable({
                    "bProcessing": false,
                    "bServerSide": true,
                    "bFilter": false,
                    cache: false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    }, "sAjaxSource": "${pageContext.servletContext.contextPath}/load/branchgroupactivitydetail",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "branchactivitytargetid", "value": $('#branchactivitytargetid').val()});
                        aoData.push({"name": "branchgroupid", "value": $('#tabfivebranchgrouptargetid').val()});
                        aoData.push({"name": "targetid", "value": $('#tabfivetargetid').val()});

                        ////console.log(aoData);
                        $.ajax({
                            "dataType": 'json',
                            async: false,
                            "type": "POST",
                            "url": "${pageContext.servletContext.contextPath}/load/branchgroupactivitydetail",
                            "data": aoData,
                            "success": fnCallback

                        });
                    },
                    //                "order": [[0, "asc"]],
                    "aoColumns": [
                        {"mDataProp": "branchactivitytargetid", "bSortable": false, "sClass": "columnhide"}, {"mDataProp": "branchactivitytargetdes", "bSortable": false},
                        {"mDataProp": "branchgroupid", "bSortable": false, "sClass": "columnhide"}, {"mDataProp": "branchgroupdes", "bSortable": false},
                        //                        {"mDataProp": "count", "bSortable": false},
                        {"mRender": function (data, type, full) {
                                return '<input type="text"  id="' + full["branchgroupid"] + '"  id2="' + full["branchactivitytargetid"] + '"  id3="' + full["branchgroupactivitytargetid"] + '"  value="' + full["count"] + '" onfocusout="bracugroupactivitycalcuation();" name="' + full["branchgroupactivitytargetid"] + '"  > <div id="errorContainer"></div>';
                            }
                        },
                        {"mRender": function (data, type, full) {
                                return '<a  href="#" id="' + full["branchgroupactivitytargetid"] + '" style="' + full["branchgroupid"] + '"><i class="fa fa-fw fa-money" aria-hidden="true"></i></a>';
                            }}

                    ], "footerCallback": function (tfoot, data, start, end, display) {

                        data.forEach(function (x) {
                            totalval += parseInt((x['count']).replace(/,/g, ""));
                        });

                    }
                });
//                $("#bactivity").append('$' + $.number(totalval) + ' ( $' + $.number($("#branchactivitycount").val()) + ' total)');
                bracugroupactivitycalcuation();
//                $("#branchgrouptivitycount").val("");
//                $("#tabfiveregionid").rules("remove");
//                $("#regionalactivitytargetid").rules("remove");
//                $("#branchactivitytargetid").rules("remove");
////                $("#tabfivebranchgrouptargetid").rules("remove");
//                $("#branchgrouptivitycount").rules("remove");
                //                $("#tabfivebranchgrouptargetid").prop('selectedIndex', 0);
                getBranchGroupMemberRegionList();

                //                getRegionBranchActivityGroupDropdown();
            }
            //----------------------------------------------------------------------------------------------
            jQuery.validator.addMethod("bgactivitylessthan", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                if ($.isNumeric(i)) {
                    if (i <= $("#branchactivitycount").val().replace(/,/g, "")) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Pleas enter less than activit count");
            jQuery.validator.addMethod("bgactivitytotal", function (value, element) {
                var COUNT = 0;
                $('#dt_basic8').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                        }
                    });
                });
                if (COUNT <= $("#branchactivitycount").val().replace(/,/g, "")) {
                    return true;
                } else {
                    return false;
                }
            }, "Branch Group Activity Count exceeded");
            function bracugroupactivitycalcuation() {
                //console.log("worl");
                $("#bactivity").empty();
                $('#msg_dev').empty();
                //                $("#totalrevenue").rules("remove");
                var $valid = $("#wizard-1").valid();
                $('#dt_basic8').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).rules('add', {
                            number: true,
                            bgactivitylessthan: true,
                            bgactivitytotal: true
                        });
                        $(this).number(true, ',', ' ');
                    });
                });
                var array = [];
                var COUNT = 0;
                $('#dt_basic8').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric(($(this).val().replace(/,/g, "")))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                            array.push({
                                ID: $(this).attr("id"),
                                COUNT: $(this).val(),
                                BATID: $(this).attr("id2"),
                                BGATID: $(this).attr("id3")

                            });
                        }
                    });
                });
                if ($valid) {
//                    $.SmartMessageBox({
//                        title: "Confirmation!",
//                        content: "Are you sure you want to change the Activity count? This will clear  any already allocated activity counts in lower levels of the hierarchy.",
//                        buttons: '[No][Yes]'
//                    }, function (ButtonPressed) {
//                        if (ButtonPressed === "Yes") {
                    $("#bactivity").empty();
                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();
                    dataObject.regionalactivitytargetid = $("#regionalactivitytargetid").val();
                    dataObject.regionid = $("#tabfiveregionid").val();
                    dataObject.datatabledata = $('#datatabledata').val();
                    //                    ////console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/updatebranchgroupactivity",
                        cache: false,
                        async: false,
                        data: {datatable_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            //                            ////console.log(response);
                            if (response.CODE === "SUCCESS") {
                                if (response.remaning == '0') {
                                    //                                        $("#brnachtargetid").removeAttr("disabled");
                                    //                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Warning!</strong>  Sucecessfully recorede added</div>  ');
                                } else {
                                    $('#msg_dev').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');
                                    window.scrollTo(0, 0);
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Invalid Amount</div>  ');

                                window.scrollTo(0, 0);
                            }
                            $("#bactivity").append('$' + $.number(COUNT) + ' ( $' + $.number(response.sum) + ' total)');
                            //                            $("#currentamout").val(response.total);
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            window.scrollTo(0, 0);
                        }
                    });
//                            var dataObject = new Object();
//                            dataObject.targetid = $("#gentargetid").val();
//                            var content = JSON.stringify(dataObject);
//                            $.ajax({type: "post",
//                                url: "${pageContext.servletContext.contextPath}/deleteallactivitycountafterfourrhtabfirstpart",
//                                cache: false,
//                                async: false,
//                                data: {tab1_info: content},
//                                success: function (response) {
//                                    response = JSON.parse(response);
//                                    if (response.status === true) {
//                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');
//
//                                    } else {
//                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
//                                        window.scrollTo(0, 0);
//                                    }
//
//                                },
//                                error: function () {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                    window.scrollTo(0, 0);
//                                }
//                            });
//                        }
//                        if (ButtonPressed === "No") {
//                            getBranchGroupActivityDetails();
//                        }
//
//                    });

                    //                    loadregionlistByTargetid();
                    //                    loadActivityList();
                }
            }





            //----------------------------table8 row remove-------------------------------------------
//            $('#dt_basic8').on('click', 'tr a', function (e) {
//                e.preventDefault();
//                var id = $(this).attr('id');
//                var id2 = $(this).attr('style');
//                deletetable8row(id, id2);
//                getBranchGroupActivityDetails();
            //            });


            $('#dt_basic8').on('click', 'a', function (e) {
                var id = $(this).attr('id');
                $("#tabfivetotalbranchgroprevene").val($(this).closest("tr").find("input,text").val());
                $("#dt_basic9").dataTable().fnClearTable();
                $("#dt_basic9").dataTable().fnDestroy();
                getBranchGroupActivityMemberDetails(id)

                ////console.log(table.row(this).data());
            });


            function  deletetable8row(id1, id2) {
                $("#branchgrouptivitycount").val("");
                var dataObject = new Object();
                dataObject.branchactivitytargetid = id1;
                dataObject.branchgroupid = id2;
                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/deletetableeoghtrow",
                    cache: false,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            getBranchGroupActivityDetails();
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div>  ');
                            window.scrollTo(0, 0);
                        } else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            //-----------------------------------------------------------------------------------------

            //---------------------tab5 branch grop activiity TARGET DROP DOWN----------------------------------

            //            function getRegionBranchActivityGroupDropdown() {
            //                $('#branchgroupactivitytargeid').empty();
            //                $('#branchgroupactivitytargeid').append($("<option></option>").attr("value", '').text('-- Select --'));
            //                var dataObject = new Object();
            //                dataObject.targetid = $("#tabfivetargetid").val();
            //                var content = JSON.stringify(dataObject);
            //                $.ajax({
            //                    type: "post",
            //                    url: "${pageContext.servletContext.contextPath}/regionbranchactivitygroup", cache: false,
            //                    async: true,
            //                    data: {tab5_info: content},
            //                    success: function (response) {
            //                        response = JSON.parse(response);
            //                        for (var i = 0; i < response.regionbranchgroupactivity.length; i++) {
            //                            $('#branchgroupactivitytargeid').append($("<option></option>").attr("value", response.regionbranchgroupactivity[i].branchgroupactivitytargeid).text(response.regionbranchgroupactivity[i].branchgroupactivitytargetdes));
            //                        }
            //                    },
            //                    error: function () {
            //                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
            //                        window.scrollTo(0, 0);
            //                    }
            //                }); //                
            //getBranchGroupMemberDetails();
            //            }
            //-----------------------------------------------------------------------------------------
            function getBranchGroupMember2() {
                $("#branchgroupmemberid").empty();
                $('#branchgroupmemberid').append($("<option></option>").attr("value", '').text('-- Select --'));
                var dataObject = new Object();
                dataObject.targetid = $("#tabfivetargetid").val();
                dataObject.branchgroupactivitytargeid = $("#branchgroupactivitytargeid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/getbrnachgroupmemberbybgmatid",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        ////console.log(response);
                        response = JSON.parse(response);
                        ////console.log(response.count);
                        $("#tabfivetotalbranchgroprevene").val(response.count);
                        for (var i = 0; i < response.branchgroupmember.length; i++) {
                            $('#branchgroupmemberid').append($("<option></option>").attr("value", response.branchgroupmember[i].empid).text(response.branchgroupmember[i].empname));
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                //                getBranchGroupActivityMemberDetails();
            }
            //_------------------------------------------------------------------------------------------------

            //--------------------------------add  table8 row add------------------------------------
            //

//            $('#addbranchgroupmemberactivity').on('click', function () {
//                $("#tabfivememberregionid").rules("add", {
//                    required: true
//                });
//                $("#memberregionalactivitytargetid").rules("add", {
//                    required: true
//                });
//                $("#memberbranchactivitytargetid").rules("add", {
//                    required: true
//                });
//                $("#branchgroupactivitytargeid").rules("add", {
//                    required: true
//                });
//                $("#branchgroupmemberid").rules("add", {
//                    required: true
//                });
//                validationDatatbelNine();
//                $("#branchgroupmembercount").rules('add', {
//                    branchgropactivityvalidation: true,
//                    number: true
//                });
//                var $valid = $("#wizard-1").valid();
//                ////console.log("validation" + $valid);
//                if ($valid) {
//                    var selectedItem = $('#branchgroupactivitytargeid option:selected');
//                    var selectedItem2 = $('#branchgroupmemberid option:selected');
//                    var bgaid = selectedItem.val();
//                    var bgmid = selectedItem2.val();
//                    var status = false;
//                    $('#dt_basic9').find('tr').each(function () {
//                        var table9 = $('#dt_basic9').DataTable();
//                        var idx = table9.row(this).index();
//                        if (idx >= 0) {
//                            var tbgaid = table9.cell(this, 0).data();
//                            var tbgmid = table9.cell(this, 2).data();
//                            if (bgaid === tbgaid && bgmid === tbgmid) {
//                                status = true;
//                            }
//                        }
//                        ////console.log("status" + status);
//                    });
//                    if (status) {
//                        ////console.log("status1" + status);
//                        $('#dt_basic9').find('tr').each(function () {
//                            var table9 = $('#dt_basic9').DataTable();
//                            var idx = table9.row(this).index();
//                            if (idx >= 0) {
//                                var tbgaid = table9.cell(this, 0).data();
//                                var tbgmid = table9.cell(this, 2).data();
//                                if (bgaid === tbgaid) {
//                                    if (bgmid === tbgmid) {
//                                        var dataObject = new Object();
//                                        dataObject.branchgroupactivitytargeid = $("#branchgroupactivitytargeid").val();
//                                        dataObject.branchgroupmemberid = $("#branchgroupmemberid").val();
//                                        dataObject.count = $("#branchgroupmembercount").val();
//                                        var content = JSON.stringify(dataObject);
//                                        $.ajax({
//                                            type: "post",
//                                            url: "${pageContext.servletContext.contextPath}/updatebranchmemberactivity",
//                                            cache: false,
//                                            async: false,
//                                            data: {tab5_info: content},
//                                            success: function (response) {
//                                                response = JSON.parse(response);
//                                                ////console.log("response" + response);
////                                                getBranchGroupActivityMemberDetails();
//                                            },
//                                            error: function () {
//                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                                window.scrollTo(0, 0);
//                                            }
//                                        });
//                                    }
//                                }
//                            }
//                        });
//                    } else {
//                        ////console.log("status2" + status);
//                        var dataObject = new Object();
//                        dataObject.branchgroupactivitytargeid = $("#branchgroupactivitytargeid").val();
//                        dataObject.branchgroupmembertargetid = $("#branchgroupmemberid").val();
//                        dataObject.count = $("#branchgroupmembercount").val();
//                        var content = JSON.stringify(dataObject);
//                        $.ajax({
//                            type: "post",
//                            url: "${pageContext.servletContext.contextPath}/savebrangroupmemberactivity",
//                            cache: false,
//                            async: true,
//                            data: {tab5_info: content},
//                            success: function (response) {
//                                response = JSON.parse(response);
//                            },
//                            error: function () {
//                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
//                                window.scrollTo(0, 0);
//                            }
//                        });
////                        getBranchGroupActivityMemberDetails();
//                    }
//                }
//                $("#tabfivememberregionid").rules("remove");
//                $("#memberregionalactivitytargetid").rules("remove");
//                $("#memberbranchactivitytargetid").rules("remove");
//                $("#branchgroupactivitytargeid").rules("remove");
//                $("#branchgroupmemberid").rules("remove");
//                $("#branchgrouptivitycount").rules("remove");
//                $("#branchgroupmembercount").rules("remove");
//                $("#branchgroupmembercount").val("");
            //            });
            function getBranchGroupActivityMemberDetails(bgactivityid) {
                ////console.log("datata tabel loada");
                $('#dt_basic9').dataTable().fnClearTable();
                $('#dt_basic9').dataTable().fnDestroy();
                $('#dt_basic9').dataTable({
                    "bProcessing": false,
                    "bServerSide": true,
                    "bFilter": false,
                    cache: false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
                    //                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/load/branchgroupactivitydetail",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "branchgroupactivitytargeid", "value": bgactivityid});
                        ////console.log(aoData);
                        $.ajax({
                            "dataType": 'json',
                            async: false,
                            "type": "POST",
                            "url": "${pageContext.servletContext.contextPath}/load/branchgroupactivitymemberdetail",
                            "data": aoData,
                            "success": fnCallback

                        });
                    },
                    //                "order": [[0, "asc"]],
                    "aoColumns": [
                        {"mDataProp": "branchgroupactivitytarhetid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "branchgroupactivitydes", "bSortable": false},
                        {"mDataProp": "branchgroupmemberid", "bSortable": false, "sClass": "columnhide"},
                        {"mDataProp": "branchgroupmemberdes", "bSortable": false},
                        //                        {"mDataProp": "count", "bSortable": false},
                        {"mRender": function (data, type, full) {
                                return '<input type="text"  id="' + full["branchgroupactivitytarhetid"] + '"  id2="' + full["branchgroupmemberid"] + '"  id3="' + full["branchgroumemberactivityid"] + '"   value="' + full["count"] + '" onfocusout="branchgroupmemberactivitycalcuation();" name="' + full["branchgroumemberactivityid"] + '"  > <div id="errorContainer"></div>';
                            }
                        },
//                        {"mRender": function (data, type, full) {
//
//                                return '<a  href="#" id="' + full["branchgroupactivitytarhetid"] + '" style="' + full["branchgroupmemberid"] + '"><i class="fa fa-trash-o" aria-hidden="true"></i></a>';
                        //                            }}

                    ]
                });
                branchgroupmemberactivitycalcuation();
            }

            jQuery.validator.addMethod("bgmactivitylessthan", function (value, element) {
                var i = parseInt(value.replace(/,/g, ""));
                var branchgroupactivitytargeid = element.getAttribute("id");
                //console.log("Value" + branchgroupactivitytargeid)
                var totalvalue = 0;
//                var dataObject = new Object();
//                dataObject.branchgroupactivitytargeid = branchgroupactivitytargeid;
//                 var content = JSON.stringify(dataObject);
//                $.ajax({
//                    type: "post",
//                    url: "${pageContext.servletContext.contextPath}/getbrnachgroupmember",
//                    cache: false,
//                    async: false,
//                    data: {tab5_info: content},
//                    success: function (response) {
//                        response = JSON.parse(response);
//                        totalvalue = response.count;
//                        $("#tabfivetotalbranchgroprevene").val(totalvalue);
//                    },
//                    error: function () {
//
//                    }
                //                });


                if ($.isNumeric(i)) {
                    if (i <= $("#tabfivetotalbranchgroprevene").val()) {
                        return true;
                    }
                } else {
                    return true;
                }
            }, "Pleas enter less than activit count");
            jQuery.validator.addMethod("bgmactivitytotal", function (value, element) {

                var COUNT = 0;
                $('#dt_basic9').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                        }
                    });
                });
                if (COUNT <= $("#tabfivetotalbranchgroprevene").val().replace(/,/g, "")) {
                    return true;
                } else {
                    return false;
                }
            }, "Branch Group Member Activity Count exceeded");


            function branchgroupmemberactivitycalcuation() {
                $("#bmactivity").empty();
                $('#msg_dev').empty();
                $('#msg_dev_tma').empty();
                //                $("#totalrevenue").rules("remove");

                $('#dt_basic9').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        $(this).rules('add', {
                            number: true,
                            bgmactivitylessthan: true,
                            bgmactivitytotal: true
                        });
                        $(this).number(true, ',', ' ');
                    });
                });
                var $valid = $("#wizard-1").valid();
                var array = [];
                var COUNT = 0;
                $('#dt_basic9').find('tr').each(function () {
                    $(this).find("input,text").each(function (i) {
                        if ($.isNumeric(($(this).val().replace(/,/g, "")))) {
                            COUNT += parseInt($(this).val().replace(/,/g, ""));
                            array.push({
                                BGATID: $(this).attr("id"),
                                COUNT: $(this).val(),
                                MEMBERID: $(this).attr("id2"),
                                BMATID: $(this).attr("id3")


                            });
                        }
                    });
                });
                if ($valid) {
                    $("#bactivity").empty();
                    var jsonString = JSON.stringify(array);
                    $("#datatabledata").val(jsonString);
                    var dataObject = new Object();
                    dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();
                    dataObject.regionalactivitytargetid = $("#regionalactivitytargetid").val();
                    dataObject.regionid = $("#tabfiveregionid").val();
                    dataObject.datatabledata = $('#datatabledata').val();
                    //                    ////console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/updatebranchgroupmemberactivity",
                        cache: false,
                        async: false,
                        data: {datatable_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            //                            ////console.log(response);
                            if (response.CODE === "SUCCESS") {
                                if (response.remaning == '0') {
                                    //                                        $("#brnachtargetid").removeAttr("disabled");
                                    //                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Warning!</strong>  Sucecessfully recorede added</div>  ');
                                } else {
                                    $('#msg_dev_tma').html('<div class="alert alert-info"><strong>Info!</strong> ' + response.RMESSAGE + ' ' + $.number(response.remaning) + '</div>  ');

                                }
                            } else {
                                $('#msg_dev_tma').html('<div class="alert alert-warning"><strong>Warning!</strong> Invalid Amount</div>  ');

                                //                                window.scrollTo(0, 0);
                            }
                            $("#bmactivity").append('$' + $.number(COUNT) + ' ( $' + $.number(response.sum) + ' total)');
                            //                            $("#currentamout").val(response.total);
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                            window.scrollTo(0, 0);
                        }
                    });
                    //                    loadregionlistByTargetid();
                    //                    loadActivityList();
                }
            }



            $('#dt_basic9').on('click', 'tr a', function (e) {
                e.preventDefault();
                var id = $(this).attr('id');
                var id2 = $(this).attr('style');
                deletetable9row(id, id2);
                getBranchGroupActivityMemberDetails();
            });
            function  deletetable9row(id1, id2) {
                var dataObject = new Object();
                dataObject.branchgroumemberactivityid = id1;
                dataObject.branchgroupmemberid = id2;
                ////console.log(dataObject);
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/deletetableninerow",
                    cache: false,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            getBranchGroupActivityDetails();
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div>  ');
                            window.scrollTo(0, 0);
                        } else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                        }
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }

            function validationDatatbelNine() {
                var sum = 0;
                var selectedItem = $('#branchgroupactivitytargeid option:selected');
                var selectedItem2 = $('#branchgroupmemberid option:selected');
                var branchgroupactivityid = selectedItem.val();
                var memeberid = selectedItem2.val();
                $('#dt_basic9').find('tr').each(function () {
                    var table9 = $('#dt_basic9').DataTable();
                    var idx = table9.row(this).index();
                    ////console.log("idx" + idx);
                    if (idx >= 0) {
                        var tbranchgroupactivityid = table9.cell(this, 0).data();
                        var tmemeberid = table9.cell(this, 2).data();
                        ////console.log("A" + memeberid);
                        ////console.log("AA" + tmemeberid);
                        if (branchgroupactivityid === tbranchgroupactivityid && memeberid === tmemeberid) {
                        } else {
                            ////console.log("b" + branchgroupactivityid);
                            ////console.log("c" + tbranchgroupactivityid);
                            if (branchgroupactivityid === tbranchgroupactivityid) {
                                sum += parseInt(table9.cell(this, 4).data());
                            }

                        }
                        ////console.log(sum);
                    }
                });
                jQuery.validator.addMethod("branchgropactivityvalidation", function () {
                    ////console.log("sum" + sum);
                    ////console.log("tabfivetotalbranchgroprevene" + $("#tabfivetotalbranchgroprevene").val());
                    ////console.log("branchgroupmembercount" + $("#branchgroupmembercount").val());
                    ////console.log("sumtotal" + parseInt(sum) + parseInt($("#branchgroupmembercount").val()));
                    if ($.isNumeric($("#branchgroupmembercount").val()) && $.isNumeric(sum)) {
                        if (parseInt($("#tabfivetotalbranchgroprevene").val()) >= parseInt(sum) + parseInt($("#branchgroupmembercount").val())) {
                            return true;
                        } else {
                            return false;
                        }
                    }


                }, jQuery.validator.format("Invalid Count."));
            }

            function empnameclick() {
                $("#branchgroupmembercount").val("");
                $('#dt_basic9').find('tr').each(function () {
                    var selectedItem = $('#branchgroupmemberid option:selected');
                    var branchgroupmemberid = selectedItem.val();
                    var table9 = $('#dt_basic9').DataTable();
                    var idx = table9.row(this).index();
                    if (idx >= 0) {

                        var tbranchgroupmemberid = table9.cell(this, 2).data();
                        if (branchgroupmemberid === tbranchgroupmemberid) {
                            $("#branchgroupmembercount").val(table9.cell(this, 4).data().replace(/,/g, ""));
                        }
                    }
                });
            }

            function tabfourvalidation() {
                var returnValue;
                var dataObject = new Object();
                dataObject.targetid = $("#tabfourtargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfourvalidarion",
                    cache: false,
                    async: false,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        ////console.log('Error code :: ' + response.CODE);
                        ////console.log(response.CODE === 'SUCCESS');
                        if (response.CODE === true) {
                            //                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong></div>  ');
                            //                            window.scrollTo(0, 0);
                            returnValue = true;
                            //                            window.scrollTo(0, 0);
                            ////console.log("sucess retern" + returnValue);
                        } else {
                            ////console.log("ERROR");
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                            returnValue = false;
                            ////console.log("error retern" + returnValue);
                        }

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                ////console.log("final retern" + returnValue);
                return returnValue;
            }


            function tabthreevalidation() {
                var returnValue;
                var dataObject = new Object();
                dataObject.targetid = $("#tabthreetargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabthreevalidation",
                    cache: false,
                    async: false,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        console.log(response.ststus === true);
                        if (response.ststus === true) {
                            if (response.CODE === "SUCCESS") {
                                //                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> </div>  ');
                                //  
                                returnValue = true;
                                ////console.log("sucess retern" + returnValue);
                            } else {

                            }
                        } else {
                            ////console.log("ERROR");
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            returnValue = false;
                            window.scrollTo(0, 0);
                        }
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                ////console.log("final retern" + returnValue);
                return returnValue;
            }


            function tabfourvalidationMemberTarget() {
             $("#msg_dev_tmr").empty();
                var returnValue;
                var dataObject = new Object();
                dataObject.targetid = $("#tabfourtargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({type: "post",
                    url: "${pageContext.servletContext.contextPath}/tabfourvalidarionmembertarget",
                    cache: false,
                    async: false,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.ststus === true) {
                            if (response.CODE === "SUCCESS") {
                                //                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong></div>  ');
                                returnValue = true;
                                ////console.log("sucess retern" + returnValue);
                            } else {

                                ////console.log("error retern" + returnValue);
                            }
                        } else {
                            $('#msg_dev_tmr').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                            window.scrollTo(0, 0);
                            returnValue = false;
                        }
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
                ////console.log("final retern" + returnValue);
                return returnValue;
            }

            //----------------------------------------------------------------------------------------
            $('#wizard-1').validate({
                onkeyup: function (element) {
                    $(element).valid();
                },
                onfocusout: function (element) {
                    $(element).valid();
                },
                rules: {
                    productid: {
                        required: true
                    },
                    targergroupid: {
                        required: true
                    }, nuberofcontact: {
                        required: true,
                        number: true
                    },
                    targetperiodid: {
                        required: true,
                        number: true
                    },
                    tragetdes: {
                        required: true
                    },
                    targetstartdate: {
                        required: true
                    },
                    targetenddate: {
                        required: true
                    },
                    revenue: {
                        required: true
                    }
                    //                    regiontarget: {
                    //                        required: true
                    //                    }, oneregiontarget: {
                    //                        required: true
                    //                    },
                    //                    multiregionalvalidation: {
                    //                        required: true
                    //                    },
                    //                    targetid: {
                    //                        required: true
                    //                    },
                    //                    targettypeid: {
                    //                        required: true
                    //                    },
                    //                    regionaltargetid: {
                    //                        required: true
                    //                    },
                    //                    branchgroupid: {
                    //                        required: true
                    //                    },
                    //                    brnachtargetid: {
                    //                        required: true
                    //                    },
                    //                    branchgroupactivitytargeid: {
                    ////                        required: true
                    //                    }

                }
            });
            $('#revenue').focus(function () {
                $(this).number(true, ',', ' ');
            });
            $('#branchgroupamount').focus(function () {
                $(this).number(true, ',', ' ');
            });
            $('#memberrevenue').focus(function () {
                $(this).number(true, ',', ' ');
            });
            $('#branchgrouptivitycount').focus(function () {
                $(this).number(true, ',', ' ');
            });
            $('#branchgroupmembercount').focus(function () {
                $(this).number(true, ',', ' ');
            });
            $("#revenue").number(true, ',', ' ');
            $("#totalrevenue").number(true, ',', ' ');
            $("#barnchamount").number(true, ',', ' ');
            $("#tab3totalrevenue").number(true, ',', ' ');
            $("#totalbranchgroprevene").number(true, ',', ' ');
            $("#branchactivitycount").number(true, ',', ' ');
            $("#tabfivetotalbranchgroprevene").number(true, ',', ' ');


            //.replace(/,/g, "")
            function checkAmountChange() {
                var array = [];
                var activitycountchange = true;
                var dataObject = new Object();
                dataObject.targetid = $("#gentargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({type: "post",
                    url: "${pageContext.servletContext.contextPath}/getTargetAmount",
                    cache: false,
                    async: false,
                    data: {tab1_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        console.log(response.Amonut);
                        if ($("#revenue").val().replace(/,/g, "") !== response.Amonut) {
                            $.SmartMessageBox({
                                title: "Confirmation!",
                                content: "Are you sure you want to change the Target Amount? This will clear any already created target amounts in lower levels of the hierarchy.",
                                buttons: '[No][Yes]'
                            }, function (ButtonPressed) {
                                if (ButtonPressed === "Yes") {
                                    savetab1();
                                    var dataObject = new Object();
                                    dataObject.targetid = $("#gentargetid").val();
                                    var content = JSON.stringify(dataObject);
                                    $.ajax({type: "post",
                                        url: "${pageContext.servletContext.contextPath}/deletealltargetamount",
                                        cache: false,
                                        async: false,
                                        data: {tab1_info: content},
                                        success: function (response) {
                                            response = JSON.parse(response);
                                            if (response.status === true) {
                                                $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');

                                            } else {
                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                                                window.scrollTo(0, 0);
                                            }

                                        },
                                        error: function () {
                                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                            window.scrollTo(0, 0);
                                        }
                                    });
                                    loadregionlist();
                                    loadBrnachlist();
//                                    getBranchGroupRevenueDetails();
//                                    getBranchGroupMemberDetails();
                                }
                                if (ButtonPressed === "No") {
                                    $("#revenue").val(response.Amonut);
                                }

                            });
                            //                            e.preventDefault();
                        } else {
                            console.log(response.ActivityCount);
                            var activityarray = [];
                            activityarray = response.ActivityCount;
                            console.log("length" + activityarray.length);
                            for (i = 0; i < activityarray.length; i++) {
                                array = activityarray[i];
                                $('#dt_basic').find('tr').each(function () {
                                    $(this).find("input,text").each(function (i) {
                                        if ($.isNumeric($(this).val().replace(/,/g, ""))) {
                                            if ($(this).attr("id") === array[0]) {
                                                if ($(this).val().replace(/,/g, "") !== array[2]) {
//                                                    alert($(this).val().replace(/,/g, "") !== array[2]);
                                                    activitycountchange = false;
                                                }
                                            }

                                        }
                                    });
                                });
                            }

                            if (!activitycountchange) {
                                $.SmartMessageBox({
                                    title: "Confirmation!",
                                    content: "Are you sure you want to change the Activity count? This will clear  any already allocated activity counts in lower levels of the hierarchy.",
                                    buttons: '[No][Yes]'
                                }, function (ButtonPressed) {
                                    if (ButtonPressed === "Yes") {
                                        savetab1();
                                        var dataObject = new Object();
                                        dataObject.targetid = $("#gentargetid").val();
                                        var content = JSON.stringify(dataObject);
                                        $.ajax({type: "post",
                                            url: "${pageContext.servletContext.contextPath}/deleteallactivitycount",
                                            cache: false,
                                            async: false,
                                            data: {tab1_info: content},
                                            success: function (response) {
                                                response = JSON.parse(response);
                                                if (response.status === true) {
                                                    $('#msg_dev').html('<div class="alert alert-success"><strong>Delete Success!</strong></div>  ');

                                                } else {
                                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div>  ');
                                                    window.scrollTo(0, 0);
                                                }

                                            },
                                            error: function () {
                                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                                                window.scrollTo(0, 0);
                                            }
                                        });
                                        getRegionActivityDetails()
                                    }
                                    if (ButtonPressed === "No") {
                                        var count = 0;
                                        $('#dt_basic').dataTable().fnClearTable();
                                        $('#dt_basic').dataTable().fnDestroy();

                                        $('#dt_basic').DataTable({
                                            data: ${newactivitytype},
                                            'columnDefs': [
                                                {
                                                    'targets': 2,
                                                    'width': '1%',
                                                    'className': 'dt-body-center',
                                                    'render': function (data, type, full, meta) {
                                                        count++;
                                                        return '<input type="text" id="' + full[0] + '"  name="name' + count + '" value="' + data + '" onchange="validate()" > <div id="errorContainer"></div>';
                                                    }
                                                },
                                                {
                                                    "targets": [0],
                                                    "visible": false

                                                }
                                            ],
                                            'searchable': false,
                                            'orderable': false,
                                            "bPaginate": false,
                                            "bFilter": false,
                                            "bInfo": false

                                        });
                                    }
                                });
                            }
                        }
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div>  ');
                        window.scrollTo(0, 0);
                    }
                });
            }


        </script>





    </body>
</html>