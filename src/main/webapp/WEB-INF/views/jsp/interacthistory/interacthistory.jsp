<%-- 
    Document   : interacthistory
    Created on : Jan 12, 2016, 9:59:30 AM
    Author     : ISURU
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../template/cssinclude.jsp"/>
    </head>
    <body>

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
                    <li>Interact History</li><li>Management</li>
                </ol>
                <!-- end breadcrumb -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->

            <!-- MAIN CONTENT -->
            <div id="content">	
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Customer 360 View
                            <span>

                            </span>
                        </h1>
                    </div>
                </div>


                <div class="row">

                    <!-- NEW COL START -->



                    <form:form id="" novalidate="novalidate" class="smart-form" commandName="interacthistory" action="${pageContext.servletContext.contextPath}/report/case/report" autocomplete="off">
                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Customer Name </label>
                                    <label class="input">
                                        <form:input id="firstName" path="firstName" placeholder="First Name" onchange="clearTable()"/>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Telephone </label>
                                    <label class="input">
                                        <form:input id="telephone" path="telephone" placeholder="Telephone" onchange="clearTable()"/>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">From Date </label>
                                    <label class="input"> <i class="icon-prepend fa fa-calendar"></i>
                                        <form:input class="form-control" id="from_date" path="from_date" data-mask="2099-99-99" placeholder="From Date" onchange="clearTable()"/>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">To Date </label>
                                    <label class="input"> <i class="icon-prepend fa fa-calendar"></i>
                                        <form:input class="form-control" id="to_date" path="to_date" data-mask="2099-99-99" placeholder="To Date" onchange="clearTable()"/>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-xs-1"></div>
                            <div class="col-xs-10">
                                <footer style="background-color: #ffffff">     
                                    <form:button id="search_btn" type="button" disabled="${avn_search}" class="btn btn-default">Search</form:button>
                                    </footer>
                                </div>
                                <div class="col-xs-1"></div>
                            </div>
                    </form:form>
                    <!--                        <div class="row">
                                                <div class="col-sm-5">
                                                    <div class="input-group input-group-sm">
                                                        <span class="input-group-addon" id="sizing-addon3">FROM</span>
                                                        <input id="from_date" class="form-control" path="from_date" data-mask="2099-99-99" placeholder="From Date" onchange="clearTable()"  From Date"  style="height: 30px; width: 270px"/>
                    
                                                    </div><br/>
                    
                                                </div>
                                                <div class="col-sm-5">
                                                    <div class="input-group input-group-sm">
                                                        <span class="input-group-addon" id="sizing-addon3">&nbsp;&nbsp;&nbsp;TO&nbsp;&nbsp;&nbsp;</span>
                                                        <input id="to_date" class="form-control" path="to_date" data-mask="2099-99-99" placeholder=" To Date"  onchange="clearTable()" style="height: 30px; width: 270px"/>
                    
                                                    </div><br/>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="input-group input-group-sm">
                                                        <button id="search_btn" type="button" class="btn btn-default">search</button>
                                                    </div>
                                                </div>
                    
                                            </div>-->

                </div>


                <!-- widget grid -->
                <!--                <section id="widget-grid" class="case-datatable">
                                     row 
                                    <div class="row">
                                         NEW WIDGET START 
                                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                             Widget ID (each widget will need unique ID)
                                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" role="widget">
                                                 widget options:
                                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
                                                data-widget-colorbutton="false"
                                                data-widget-editbutton="false"
                                                data-widget-togglebutton="false"
                                                data-widget-deletebutton="false"
                                                data-widget-fullscreenbutton="false"
                                                data-widget-custombutton="false"
                                                data-widget-collapsed="true"
                                                data-widget-sortable="false"
                                                
                                                <header>
                                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                                    <h2>Case</h2>
                                                </header>
                                                 widget div
                                                <div>
                                                     widget content 
                                                    <div class="widget-body no-padding">
                                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                                            <thead>			                
                                                                <tr>
                                                                    <th data-hide="phone">ID</th>
                                                                    <th data-hide="phone,tablet">Module</th>
                                                                    <th data-hide="phone,tablet">Case Type</th>
                                                                    <th data-hide="phone,tablet">Customer Name</th>
                                                                    <th data-hide="phone,tablet">Telephone</th>
                                                                    <th data-class="expand">Description</th>
                                                                    <th data-class="expand">Status</th>
                                                                    <th data-hide="phone">Created Date Time</th>
                                                                                                                        <th data-class="expand">Last Update Datetime</th>
                                                                                                                        <th data-hide="phone">Created User</th>
                                                                    <th data-hide="phone,tablet">Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody></tbody>
                                                        </table>
                                                    </div>
                                                     end widget content 
                                                </div>
                                                 end widget div 
                                            </div>
                                             end widget 											
                                        </article>
                                         WIDGET END 
                                    </div>								
                                </section>-->
                <!-- end widget grid -->

                <!-- widget grid -->
                <section id="widget-grid" class="case-datatable">
                    <!-- row -->
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" role="widget">
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
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>Case results</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">

                                        <table id="case_table" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th data-hide="phone">Case ID</th>   
                                                    <!--                                                    <th data-hide="phone">Branch</th>
                                                                                                        <th data-hide="phone">Module</th>-->
                                                    <th data-class="expand"><i class=""></i> Case Type</th>
                                                    <!--<th data-hide="phone">Department</th>-->
                                                    <!--<th data-hide="phone"><i class=""></i> Priority</th>-->
                                                    <!--<th data-hide="phone,tablet"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"> </i>Assignee</th>-->
                                                    <!--<th data-hide="phone,tablet">Created User</th>-->
                                                    <th data-class="expand"><i class=""></i> Customer Name</th>
                                                    <th data-class="expand"><i class=""></i> Phone</th>
                                                    <th data-class="expand"><i class=""></i> Description</th>
                                                    <th data-hide="phone,tablet">Status</th>
                                                    <th data-class="expand"><i class=""></i> Created Date Time</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i> Action</th>

                                                </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->											
                        </article>
                        <!-- WIDGET END -->
                    </div>								
                </section>
                <!-- end widget grid -->

                <section id="widget-grid" class="case-datatable">
                    <!-- row -->
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" role="widget">
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>Call results</h2>

                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <table id="call_table" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th data-hide="phone">Call Log Id</th>
                                                    <th data-hide="phone">Agent Name</th>
                                                    <th data-hide="phone">Customer Name</th>
                                                    <th data-class="expand"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"> </i> Mobile No</th>
                                                    <th data-hide="expand"> <i class="glyphicon glyphicon-sort">  </i>  Call Direction</th>
                                                    <th data-hide="phone,tablet">Follow up Action</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i> Action</th>
                                                </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->											
                        </article>
                        <!-- WIDGET END -->
                    </div>								
                </section>

                <form:form class="smart-form" commandName="interacthistory">
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <!--<h2 class="row-seperator-header"><i class="fa fa-th-list"></i> Accordions </h2>-->
                        <!-- NEW WIDGET START -->
                        <article class="col-xs-10">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
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
                                    <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                                    <h2>Accordions </h2>
                                </header>
                                <!-- widget div-->
                                <div></div>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div class="panel-group smart-accordion-default" id="accordion">

                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a id="pdcol" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Personal Details </a></h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Title</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="title" path="title" items="${titleList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label">Name in Full</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="last_name" placeholder="Name in Full"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Initials</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="initials" placeholder="Initials"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Preferred Name</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="preferrd_name" placeholder="Preferred Name"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Last Name</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="last_name" placeholder="Last Name"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Date of Birth</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="date_of_birth" placeholder="Date of Birth"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Gender</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="gender" path="gender" items="${genderList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Nationality</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="nationality" path="nationality" items="${nationalityList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Religion</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="reagon" path="religion" items="${religonList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Marital Status</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="marital_status" path="marital_status" items="${maritalStatusList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Preferred Language</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="preferred_language" path="preferred_language" items="${languageList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Mobile No 01</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="mobile_no_1" placeholder="Mobile No 01"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Mobile No 02</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="mobile_no_2" placeholder="Mobile No 02"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Email</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="true" id="email" placeholder="Email"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>

                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseFifteen"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Fix Deposits </a></h4>
                                            </div>
                                            <div id="collapseFifteen" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Fix Deposit Number</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="fd_no" placeholder="Fix Deposit Number"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Deposit Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="depamt" placeholder="Deposit Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Fix Deposit Start Date</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="fd_date" placeholder="Fix Deposit Start Date"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Fix Deposit Period</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="fd_prd" placeholder="Fix Deposit Period" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Fix Deposit Interest Rate</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="fd_inrate" placeholder="Fix Deposit Interest Rate" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Location</label>
                                                                <label class="select">
                                                                    <form:select id="center" path="center" disabled="true" items="${branchOldCodeList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Fix Deposit Type</label>
                                                                <label class="select">
                                                                    <form:select id="fd_type" path="fd_type" disabled="true" items="${fdTypeList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Interest</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="fd_monint" placeholder="Interest"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Fix Deposit Maturity Date</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="fd_next" placeholder="Fix Deposit Maturity Date"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table class="table table-bordered table-condensed table-striped table-hover">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Product</th>
                                                                        <th>Fix Deposit No</th>
                                                                        <th>Deposit Amount</th>
                                                                        <th>Fix Deposit Start Date</th>
                                                                        <th>Fix Deposit Period</th>
                                                                        <th>Actions</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%--<c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.stakeHolderFDBeans.size() > 0}">
                                                                        <c:forEach var="fd" items="${accountForm.searhDataBean.stakeHolderFDBeans}">
                                                                            <tr>
                                                                                <td>FD</td>
                                                                                <td>${fd.fd_no}</td>
                                                                                <td align="right">${fd.depamt}</td>
                                                                                <td align="right">${fd.fd_date}</td>
                                                                                <td align="right">${fd.fd_prd}</td>
                                                                                <td>
                                                                                    <a href="javascript:void(0);" onclick="javascript:setFdValues('${fd.fd_id_view}fd')" class="fa fa-eye" style="text-decoration:none;"/>
                                                                                    <div id="${fd.fd_id_view}fd" style="display: none;">${fd.toString()}</div>
                                                                                </td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </c:if>--%>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseSeven"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Products - Savings </a></h4>
                                            </div>
                                            <div id="collapseSeven" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Account Number</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="account_number" placeholder="Account Number"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Product Code</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="product_code" placeholder="Product Code"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Status</label>
                                                                <label class="select">
                                                                    <form:select id="savings_status" path="savings_status" disabled="true" items="${savingsStatusList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Current Balance</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="current_balance" placeholder="Current Balance" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Available Balance</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="available_balance" placeholder="Available Balance" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Available Balance</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="hold_amount" placeholder="Available Balance" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table class="table table-bordered table-condensed table-striped table-hover">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Product</th>
                                                                        <th>Account No</th>
                                                                        <th>Savings Current Balance</th>
                                                                        <th>Status</th>
                                                                        <th>Actions</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%--<c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.stakeHolderSavingsBeans.size() > 0}">
                                                                        <c:forEach var="savings" items="${accountForm.searhDataBean.stakeHolderSavingsBeans}">
                                                                            <tr>
                                                                                <td>Saving</td>
                                                                                <td>${savings.account_number}</td>
                                                                                <td align="right">${savings.current_balance}</td>
                                                                                <td>${savings.status_description}</td>
                                                                                <td>
                                                                                    <a href="javascript:void(0);" onclick="javascript:setSavingValues('${savings.savings_id_view}saving')" class="fa fa-eye" style="text-decoration:none;"/>
                                                                                    <div id="${savings.savings_id_view}saving" style="display: none;">${savings.toString()}</div>
                                                                                </td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </c:if>--%>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEight"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Products - Micro Finance </a></h4>
                                            </div>
                                            <div id="collapseEight" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Module Code</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_module_code" placeholder="Module Code"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Customer Code</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_customer_code" placeholder="Customer Code"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Contract No</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_contract_no" placeholder="Contract No"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Alias Name</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_alias_name" placeholder="Alias Name"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Contract Date</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_contract_date" placeholder="Contract Date"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Total Offered Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_total_offered_amount"  placeholder="Total Offered Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Status</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="mf_cs" path="mf_cs" items="${contractStatusList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Total Arrears Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_total_arrears_amount" placeholder="Total Arrears Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Outstanding Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="mf_outstanding_amount" placeholder="Outstanding Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table class="table table-bordered table-condensed table-striped table-hover">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Product</th>
                                                                        <th>Account No</th>
                                                                        <th>Loan Arrears Amount</th>
                                                                        <th>Status</th>
                                                                        <th>Actions</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%--<c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerLoanBeans.size() > 0}">
                                                                        <c:forEach var="microfinace" items="${accountForm.searhDataBean.customerLoanBeans}">
                                                                            <c:if test="${microfinace.module_code == 'MF'}">
                                                                                <tr>
                                                                                    <td>Loan</td>
                                                                                    <td>${microfinace.contract_no}</td>
                                                                                    <td align="right">${microfinace.total_arrears_amount}</td>
                                                                                    <td>${microfinace.contract_status}</td>
                                                                                    <td>
                                                                                        <a href="javascript:void(0);" onclick="javascript:setMicroFinanceValues('${microfinace.loan_id_view}microfinance')" class="fa fa-eye" style="text-decoration:none;"/>
                                                                                        <div id="${microfinace.loan_id_view}microfinance" style="display: none;">${microfinace.toString()}</div>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>--%>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseNine"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Products - Credit </a></h4>
                                            </div>
                                            <div id="collapseNine" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Module Code</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_module_code" placeholder="Module Code"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Customer Code</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_customer_code" placeholder="Customer Code"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Contract No</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_contract_no" placeholder="Contract No"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Alias Name</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_alias_name" placeholder="Alias Name"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Contract Date</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_contract_date" placeholder="Contract Date"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Total Offered Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_total_offered_amount"  placeholder="Total Offered Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Status</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="cr_cs" path="cr_cs" items="${contractStatusList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Total Arrears Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_total_arrears_amount" placeholder="Total Arrears Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Outstanding Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="cr_outstanding_amount" placeholder="Outstanding Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table class="table table-bordered table-condensed table-striped table-hover">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Product</th>
                                                                        <th>Account No</th>
                                                                        <th>Loan Arrears Amount</th>
                                                                        <th>Status</th>
                                                                        <th>Actions</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%--<c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerLoanBeans.size() > 0}">
                                                                        <c:forEach var="credit" items="${accountForm.searhDataBean.customerLoanBeans}">
                                                                            <c:if test="${credit.module_code == 'CR'}">
                                                                                <tr>
                                                                                    <td>Loan</td>
                                                                                    <td>${credit.contract_no}</td>
                                                                                    <td align="right">${credit.total_arrears_amount}</td>
                                                                                    <td>${credit.contract_status}</td>
                                                                                    <td>
                                                                                        <a href="javascript:void(0);" onclick="javascript:setCreditValues('${credit.loan_id_view}credit')" class="fa fa-eye" style="text-decoration:none;"/>
                                                                                        <div id="${credit.loan_id_view}credit" style="display: none;">${credit.toString()}</div>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>--%>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTen"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Products - Pawning </a></h4>
                                            </div>
                                            <div id="collapseTen" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Module Code</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_module_code" placeholder="Module Code"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Customer Code</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_customer_code" placeholder="Customer Code"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Contract No</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_contract_no" placeholder="Contract No"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Alias Name</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_alias_name" placeholder="Alias Name"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Contract Date</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_contract_date" placeholder="Contract Date"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Total Offered Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_total_offered_amount"  placeholder="Total Offered Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Status</label>
                                                                <label class="select">
                                                                    <form:select disabled="true" id="pw_cs" path="pw_cs" items="${contractStatusList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Total Arrears Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_total_arrears_amount" placeholder="Total Arrears Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Outstanding Amount</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="pw_outstanding_amount" placeholder="Outstanding Amount" style="text-align: right"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table class="table table-bordered table-condensed table-striped table-hover">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Product</th>
                                                                        <th>Account No</th>
                                                                        <th>Loan Arrears Amount</th>
                                                                        <th>Status</th>
                                                                        <th>Actions</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%--<c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerLoanBeans.size() > 0}">
                                                                        <c:forEach var="pawning" items="${accountForm.searhDataBean.customerLoanBeans}">
                                                                            <c:if test="${pawning.module_code == 'PW'}">
                                                                                <tr>
                                                                                    <td>Loan</td>
                                                                                    <td>${pawning.contract_no}</td>
                                                                                    <td align="right">${pawning.total_arrears_amount}</td>
                                                                                    <td>${pawning.contract_status}</td>
                                                                                    <td>
                                                                                        <a href="javascript:void(0);" onclick="javascript:setPawningValues('${pawning.loan_id_view}pawning')" class="fa fa-eye" style="text-decoration:none;"/>
                                                                                        <div id="${pawning.loan_id_view}pawning" style="display: none;">${pawning.toString()}</div>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>--%>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                </div>
                                <!-- end widget -->
                            </div>
                        </article>
                        <!-- WIDGET END -->
                    </div>
                </form:form>
            </div>
            <!-- END MAIN CONTENT -->
        </div>  

        <!-- END MAIN PANEL -->

        <!-- PAGE FOOTER -->
        <div class="page-footer">
            <jsp:include page="/WEB-INF/views/jsp/template/footer.jsp"/>
        </div>
        <!-- END PAGE FOOTER -->


        <!-- PAGE RELATED PLUGIN(S) -->

        <jsp:include page="../template/jsinclide.jsp"/>

        <script type="text/javascript">

        </script>

        <script type="text/javascript">


            // DO NOT REMOVE : GLOBAL FUNCTIONS!
            var search = false;

            $(document).ready(function () {
                pageSetUp();
                /* // DOM Position key index //
                 
                 l - Length changing (dropdown)
                 f - Filtering input (search)
                 t - The Table! (datatable)
                 i - Information (records)
                 p - Pagination (paging)
                 r - pRocessing 
                 < and > - div elements
                 <"#id" and > - div with an id
                 <"class" and > - div with a class
                 <"#id.class" and > - div with an id and class
                 
                 Also see: http://legacy.datatables.net/usage/features
                 */

                /* BASIC ;*/
                var responsiveHelper_dt_basic = undefined;
                var responsiveHelper_case_table = undefined;
                var responsiveHelper_call_table = undefined;

                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };

                var search_table = $('#dt_basic').dataTable({
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
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_dt_basic.respond();
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/interacthistory/searched",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "from_date", "value": $('#from_date').val()});
                        aoData.push({"name": "to_date", "value": $('#to_date').val()});
                        aoData.push({"name": "firstName", "value": $('#firstName').val()});
                        aoData.push({"name": "telephone", "value": $('#telephone').val()});
                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/interacthistory/searched",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "aoColumns": [
                        {"mDataProp": "id", "bSortable": false},
                        {"mDataProp": "module", "bSortable": false},
                        {"mDataProp": "caseTypeDes", "bSortable": false},
                        {"mDataProp": "firstName", "bSortable": false},
                        {"mDataProp": "telephone", "bSortable": false},
                        {"mDataProp": "discription", "bSortable": false},
                        {"mDataProp": "status", "bSortable": false},
                        {"mDataProp": "createdDateTime", "bSortable": false},
                        /*{"mDataProp": "lastUpdateDatetime", "bSortable": false},
                         {"mDataProp": "createduser", "bSortable": false},*/
                        {"mDataProp": "action", "bSortable": false}

                    ]
                });

                var case_table = $('#case_table').dataTable({
                    "bProcessing": true,
                    "bServerSide": true,
                    "bFilter": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
//                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_case_table) {
                            responsiveHelper_case_table = new ResponsiveDatatablesHelper($('#case_table'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_case_table.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_case_table.respond();
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/case/searched",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "from_date", "value": $('#from_date').val()});
                        aoData.push({"name": "to_date", "value": $('#to_date').val()});
                        aoData.push({"name": "firstName", "value": $('#firstName').val()});
                        aoData.push({"name": "telephone", "value": $('#telephone').val()});
                        aoData.push({"name": "searchoption", "value": $('#searchoption').val()});
                        aoData.push({"name": "input", "value": $('#input').val()});

                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/case/searched",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "order": [[0, "desc"]],
                    "aoColumns": [
                        {"mDataProp": "caseId", "bSortable": false},
//                        {"mDataProp": "branchDesc", "bSortable": false},
//                        {"mDataProp": "productDes", "bSortable": false},
                        {"mDataProp": "caseTypeDes", "bSortable": false},
//                        {"mDataProp": "departmentDes", "bSortable": false},
//                        {"mDataProp": "priorityDes", "bSortable": false},
//                        {"mDataProp": "assigneeId1", "bSortable": false},
//                        {"mDataProp": "casecreateduser", "bSortable": false},
                        {"mDataProp": "customername", "bSortable": false},
                        {"mDataProp": "phone", "bSortable": false},
                        {"mDataProp": "description", "bSortable": false},
                        {"mDataProp": "statusDes", "bSortable": false},
                        {"mDataProp": "createddatetime", "bSortable": false},
                        {"mDataProp": "action", "bSortable": false}

                    ],
                    "columnDefs": [{"width": "35%", "targets": 4}]

                });


                var call_table = $('#call_table').dataTable({
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
                        if (!responsiveHelper_call_table) {
                            responsiveHelper_call_table = new ResponsiveDatatablesHelper($('#call_table'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_call_table.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_call_table.respond();
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/calllog/searched",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "from_date", "value": $('#from_date').val()});
                        aoData.push({"name": "to_date", "value": $('#to_date').val()});
                        aoData.push({"name": "firstName", "value": $('#firstName').val()});
                        aoData.push({"name": "telephone", "value": $('#telephone').val()});
                        aoData.push({"name": "searchoption", "value": $('#searchoption').val()});
                        aoData.push({"name": "input", "value": $('#input').val()});
//                        aoData.push({"name": "nameInFull", "value": $('#nameInFull').val()});
                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/calllog/searched",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "order": [[0, "desc"]],
                    "aoColumns": [
                        {"mDataProp": "callLogId", "bSortable": false},
                        {"mDataProp": "username", "bSortable": false},
                        {"mDataProp": "nameInFull", "bSortable": false},
                        {"mDataProp": "telephone", "bSortable": false},
                        {"mDataProp": "callDirectionDes", "bSortable": false},
                        {"mDataProp": "followUpActionDes", "bSortable": false},
                        {"mDataProp": "action", "bSortable": false}

                    ]
                });


                $('#search_btn').click(function () {
                    search = true;
                    search_table.fnDraw();
                    case_table.fnDraw();
                    call_table.fnDraw();
                });
            });
//            $(document).ajaxSuccess(function () {
//                var rowCount = 0;
//                $('#dt_basic >tbody >tr').each(function () {
//                    if (!$(this).hasClass('odd')) {
//                        rowCount++;
//                    }
//                });
//                if (rowCount > 0) {
//                    $('#download').removeAttr('disabled');
//                } else {
//                    $('#download').attr('disabled', 'disabled');
//                }
//            });

            function clearTable() {
                search = false;
//                $('#dt_basic').dataTable().fnClearTable();
                $('#case_table').dataTable().fnClearTable();
                $('#call_table').dataTable().fnClearTable();
                $('#download').attr('disabled', 'disabled');
            }

        </script>

        <script type="text/javascript">
//            $(document).ready(function () {

            $('#from_date').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });


            $('#to_date').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });

//                $('#from_date').datepicker({
//                    changeMonth: true,
//                    changeYear: true,
//                    yearRange: '1900:' + (new Date).getFullYear(),
//                    dateFormat: 'yy-mm-dd',
//                    prevText: '<i class="fa fa-chevron-left"></i>',
//                    nextText: '<i class="fa fa-chevron-right"></i>'
//                });
//
//                $('#to_date').datepicker({
//                    changeMonth: true,
//                    changeYear: true,
//                    yearRange: '1900:' + (new Date).getFullYear(),
//                    dateFormat: 'yy-mm-dd',
//                    prevText: '<i class="fa fa-chevron-left"></i>',
//                    nextText: '<i class="fa fa-chevron-right"></i>'
//                });
//            });
        </script>

        <!-- Your GOOGLE ANALYTICS CODE Below -->
        <script type="text/javascript">
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
            _gaq.push(['_trackPageview']);
            (function () {
                var ga = document.createElement('script');
                ga.type = 'text/javascript';
                ga.async = true;
                ga.src = ('https:' === document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
    </body>
</html>

