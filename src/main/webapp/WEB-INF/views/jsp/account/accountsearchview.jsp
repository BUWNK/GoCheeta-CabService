<%-- 
    Author     : Roshen Dilshan
    Document   : accountsearchview
    Created on : Aug 10, 2015, 4:23:38 PM
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
                <!-- breadcrumb -->
                <ol id="view_bc" class="breadcrumb">
                    <li><a href="${pageContext.servletContext.contextPath}/account">Customer Management</a></li><li>View Customer</li>
                </ol>
            </div>
            <!-- END RIBBON -->


            <!-- MAIN CONTENT -->
            <div id="content">
                <!-- SEARCH CONTENT -->
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Customer Management View
                            <span>
                                CCID : ${accountForm.searhDataBean.stakeholder_reference_no}
                            </span>
                        </h1>
                    </div>
                </div>        
                <form:form class="smart-form" commandName="accountForm">
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
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Customer Category</label>
                                <label class="select">
                                    <form:select id="customer_category" path="searhDataBean.account_category" disabled="true" items="${categoryList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Customer Category Type</label>
                                <label class="select">
                                    <form:select id="account_type" path="searhDataBean.account_category_type" disabled="true" items="${categoryTypeList}"/>
                                    <i></i>
                                </label>
                            </section></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Code Type</label>
                                <label class="select">
                                    <form:select disabled="true" id="customer_code_type" path="searhDataBean.customer_code_type" items="${customerCodeTypeList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Customer Code</label>
                                <label class="input">
                                    <form:input type="text" disabled="true" id="customer_code" path="searhDataBean.customer_code" placeholder="Customer Code"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch / Location</label>
                                <label class="input">
                                    <form:input type="text" disabled="true" id="location" path="searhDataBean.location" placeholder="Branch / Location"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>

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
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 0}">
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
                                                                        <form:select disabled="true" id="title" path="searhDataBean.title" items="${titleList}"/>
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
                                                                        <form:input type="text" disabled="true" id="last_name" path="searhDataBean.name_in_full" placeholder="Name in Full"/>
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
                                                                        <form:input type="text" disabled="true" id="initials" path="searhDataBean.initials" placeholder="Initials"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Preferred Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="preferrd_name" path="searhDataBean.first_name" placeholder="Preferred Name"/>
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
                                                                        <form:input type="text" disabled="true" id="last_name" path="searhDataBean.last_name" placeholder="Last Name"/>
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
                                                                        <form:input type="text" disabled="true" id="date_of_birth" path="searhDataBean.date_of_birth" placeholder="Date of Birth"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Gender</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="gender" path="searhDataBean.gender" items="${genderList}"/>
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
                                                                        <form:select disabled="true" id="reagon" path="searhDataBean.religion" items="${religonList}"/>
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
                                                                        <form:select disabled="true" id="marital_status" path="searhDataBean.marital_status" items="${maritalStatusList}"/>
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
                                                                        <%--<form:input type="text" disabled="true" id="prefferd_language" path="preferred_language" placeholder="Preferred Language"/>--%>
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
                                                                        <form:input type="text" disabled="true" id="mobile_no_1" path="searhDataBean.mobile_no_1" placeholder="Mobile No 01"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Mobile No 02</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="mobile_no_2" path="searhDataBean.mobile_no_2" placeholder="Mobile No 02"/>
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
                                                                        <form:input type="text" disabled="true" id="email" path="searhDataBean.email" placeholder="Email"/>
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
                                        </c:if>
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 1 || accountForm.searhDataBean.account_category == 2}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTwelve" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Corporate Customer Details </a></h4>
                                                </div>
                                                <div id="collapseTwelve" class="panel-collapse collapse in">
                                                    <div class="panel-body">
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Company Name <samp style="color: red">*</samp></label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="company_name" path="searhDataBean.company_name" placeholder="Company Name" maxlength="150"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Sector</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sector_description" path="searhDataBean.sector_description" placeholder="Sector"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-6"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Sub Sector</label>
                                                                    <label class="select">
                                                                        <form:select id="subSectorList" multiple="true" path="" style="height:100px;" items="${subSectorList}"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-6"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <br/>
                                        </c:if>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Contact Addresses </a></h4>
                                            </div>
                                            <div id="collapseTwo" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Address Type</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="address_type_description" placeholder="Address Type"/>
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
                                                                <label class="label">Address Line 01</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="address_line_1" placeholder="Address Line 01"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label">Address Line 02</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="address_line_2" placeholder="Address Line 02"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label">Address Line 03</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="address_line_3" placeholder="Address Line 03"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Country</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="country_name" placeholder="Country"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Province</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="province_name" placeholder="Province"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">District</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="district_name" placeholder="District"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">City</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="city_name" placeholder="City"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">GS Division</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="gs_division_name" placeholder="GS Division"/>
                                                                </label>
                                                            </section></div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">GPS</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="gps" placeholder="GPS"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Land Phone No</label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="land_phone_no" placeholder="Land Phone No"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Billing Proof </label>
                                                                <label class="input">
                                                                    <input type="text" disabled="disabled" id="billing_proof" placeholder="Billing Proof"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table class="table table-bordered table-condensed table-hover">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th> Address Type </th>
                                                                        <th data-hide="phone,tablet">Address Line 01</th>
                                                                        <th data-class="phone"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i> Telephone </th>
                                                                        <th> Action </th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerAddressBeans.size() > 0}">
                                                                        <c:forEach var="address" items="${accountForm.searhDataBean.customerAddressBeans}">
                                                                            <tr>
                                                                                <td>${address.address_type_description}</td>
                                                                                <td>${address.address_line_1}</td>
                                                                                <td>${address.land_phone_no}</td>
                                                                                <td>
                                                                                    <a href="javascript:void(0);" onclick="javascript:setAddressValues('${address.address_id_view}')" class="fa fa-eye" style="text-decoration:none;"/>
                                                                                    <div id="${address.address_id_view}" style="display: none;">${address.toString()}</div>
                                                                                </td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 0}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Education Info </a></h4>
                                                </div>
                                                <div id="collapseThree" class="panel-collapse collapse">
                                                    <div class="panel-body no-padding">
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <table class="table table-bordered table-condensed">
                                                                    <thead>			                
                                                                        <tr>
                                                                            <th> Education Level </th>
                                                                            <th> Institution </th>
                                                                            <!--<th> Action </th>-->
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerEducationBeans.size() > 0}">
                                                                            <c:forEach var="education" items="${accountForm.searhDataBean.customerEducationBeans}">
                                                                                <tr>
                                                                                    <td>${education.educationLevelDescription}</td>
                                                                                    <td>${education.institute}</td>
                                                                                    <!--<td><a>View</a></td>-->
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <br/>
                                                    </div>
                                                </div>
                                            </div>
                                            <br/>
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseFour" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Occupation </a></h4>
                                                </div>
                                                <div id="collapseFour" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Designation</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="designation_description" path="searhDataBean.designation_description" placeholder="Designation"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <!--                                                                <section>
                                                                                                                                    <label class="label">Level</label>
                                                                                                                                    <label class="input">
                                                                                                                                        <form :input type="text" disabled="true" id="level_description" path="searhDataBean.level_description" placeholder="Level"/>
                                                                                                                                    </label>
                                                                                                                                </section>-->
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Profession</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="profession_description" path="searhDataBean.profession_description" placeholder="Profession"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Employer</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="employeer" path="searhDataBean.employeer" placeholder="Employer"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Sector</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sector_description" path="searhDataBean.sector_description" placeholder="Sector"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-6"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Sub Sector</label>
                                                                    <label class="select">
                                                                        <form:select id="subSectorList" multiple="true" path="" style="height:100px;" items="${subSectorList}"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-6"></div>
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
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseFive" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Spouse / Guardian / Care Taker </a></h4>
                                                </div>
                                                <div id="collapseFive" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Title</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_title" path="sgc_title" placeholder="Title"/>
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
                                                                        <form:input type="text" disabled="true" id="sgc_name_in_full" path="sgc_name_in_full" placeholder="Name in Full"/>
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
                                                                        <form:input type="text" disabled="true" id="sgc_initials" path="sgc_initials" placeholder="Initials"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">First Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_firstname" path="sgc_firstname" placeholder="First Name"/>
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
                                                                        <form:input type="text" disabled="true" id="sgc_lastname" path="sgc_lastname" placeholder="Last Name"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Address - Residence</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_address_residence" path="sgc_address_residence" placeholder="Address - Residence"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-6">
                                                                <section>
                                                                    <label class="label">Occupation</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_occupation" path="sgc_occupation" placeholder="Occupation"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-5"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-6">
                                                                <section>
                                                                    <label class="label">Employer / Business Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_employer_business_name" path="sgc_employer_business_name" placeholder="Employer / Business Name"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-5"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Employer / Business Address</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_employer_business_address" path="sgc_employer_business_address" placeholder="Employer / Business Address"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Telephone - Work</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_employer_telephone" path="sgc_employer_telephone" placeholder="Telephone - Work"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Years of Employment / Business</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sgc_years_of_employeement_business" path="sgc_years_of_employeement_business" placeholder="Years of Employment / Business"/>
                                                                    </label>
                                                                </section>
                                                            </div>
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
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseSix" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Dependents </a></h4>
                                                </div>
                                                <div id="collapseSix" class="panel-collapse collapse">
                                                    <div class="panel-body no-padding">
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <table class="table table-bordered table-condensed">
                                                                    <thead>			                
                                                                        <tr>
                                                                            <th>Relationship</th>
                                                                            <th>Date of Birth</th>
                                                                            <th>Name in Full </th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:if test="${dependetList != null && dependetList.size() > 0}">
                                                                            <c:forEach var="dependent" items="${dependetList}">
                                                                                <tr>
                                                                                    <td>${dependent.dependent_relationship_description}</td>
                                                                                    <td>${dependent.dependent_date_of_birth}</td>
                                                                                    <td>${dependent.dependent_name_in_full}</td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <br/>
                                                    </div>
                                                </div>
                                            </div>
                                            <br/>
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseFourteen" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Customer Hobbies / Interests </a></h4>
                                                </div>
                                                <div id="collapseFourteen" class="panel-collapse collapse">
                                                    <div class="panel-body no-padding">
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <table class="table table-bordered table-condensed">
                                                                    <thead>			                
                                                                        <tr>
                                                                            <th>Hobby / Interest</th>
                                                                            <th>Comment</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:if test="${customerHobbiesInterestsList != null && customerHobbiesInterestsList.size() > 0}">
                                                                            <c:forEach var="hobbiesinterest" items="${customerHobbiesInterestsList}">
                                                                                <tr>
                                                                                    <td>${hobbiesinterest.hobby_description}</td>
                                                                                    <td>${hobbiesinterest.hobby_comment}</td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <br/>
                                                    </div>
                                                </div>
                                            </div>
                                            <br/>
                                        </c:if>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseFifteen"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-down pull-right"></i> Fix Deposits </a></h4>
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
                                                                    <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.stakeHolderFDBeans.size() > 0}">
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
                                                                    </c:if>
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
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 0}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseSeven"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-down pull-right"></i> Products - Savings </a></h4>
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
                                                                    <label class="label">Hold Amount</label>
                                                                    <label class="input">
                                                                        <input type="text" disabled="disabled" id="hold_amount" placeholder="Hold Amount" style="text-align: right"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <!--                                                        <div class="row">
                                                                                                                    <div class="col-xs-1"></div>
                                                                                                                    <div class="col-xs-4">
                                                                                                                        <section>
                                                                                                                            <label class="label">Freeze Amount</label>
                                                                                                                            <label class="input">
                                                                                                                                <input type="text" disabled="disabled" id="freeze_amount" placeholder="Freeze Amount"/>
                                                                                                                            </label>
                                                                                                                        </section>
                                                                                                                    </div>
                                                                                                                    <div class="col-xs-2"></div>
                                                                                                                    <div class="col-xs-4">
                                                                                                                        <section>
                                                                                                                            <label class="label">Available Balance</label>
                                                                                                                            <label class="input">
                                                                                                                                <input type="text" disabled="disabled" id="available_balance" placeholder="Available Balance"/>
                                                                                                                            </label>
                                                                                                                        </section>
                                                                                                                    </div>
                                                                                                                    <div class="col-xs-1"></div>
                                                                                                                </div>
                                                                                                                <div class="row">
                                                                                                                    <div class="col-xs-1"></div>
                                                                                                                    <div class="col-xs-4">
                                                                                                                        <section>
                                                                                                                            <label class="label">Last Deposit Date</label>
                                                                                                                            <label class="input">
                                                                                                                                <input type="text" disabled="disabled" id="last_deposit_date" placeholder="Last Deposit Date"/>
                                                                                                                            </label>
                                                                                                                        </section>
                                                                                                                    </div>
                                                                                                                    <div class="col-xs-2"></div>
                                                                                                                    <div class="col-xs-4">
                                                                                                                        <section>
                                                                                                                            <label class="label">Last Withdrawal Date</label>
                                                                                                                            <label class="input">
                                                                                                                                <input type="text" disabled="disabled" id="last_withdrawal_date" placeholder="Last Withdrawal Date"/>
                                                                                                                            </label>
                                                                                                                        </section>
                                                                                                                    </div>
                                                                                                                    <div class="col-xs-1"></div>
                                                                                                                </div>
                                                                                                                <div class="row">
                                                                                                                    <div class="col-xs-1"></div>
                                                                                                                    <div class="col-xs-4">
                                                                                                                        <section>
                                                                                                                            <label class="label">Effective Start Date</label>
                                                                                                                            <label class="input">
                                                                                                                                <input type="text" disabled="disabled" id="effective_start_date" placeholder="Effective Start Date"/>
                                                                                                                            </label>
                                                                                                                        </section>
                                                                                                                    </div>
                                                                                                                    <div class="col-xs-2"></div>
                                                                                                                    <div class="col-xs-4">
                                                                                                                        <section>
                                                                                                                            <label class="lable">Effective End Date</label>
                                                                                                                            <label class="input">
                                                                                                                                <input type="text" disabled="disabled" id="effective_end_date" placeholder="Effective End Date"/>
                                                                                                                            </label>
                                                                                                                        </section>
                                                                                                                    </div>
                                                                                                                    <div class="col-xs-1"></div>
                                                                                                                </div>-->
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
                                                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.stakeHolderSavingsBeans.size() > 0}">
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
                                                                        </c:if>
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
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEight"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-down pull-right"></i> Products - Micro Finance </a></h4>
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
                                                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerLoanBeans.size() > 0}">
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
                                                                        </c:if>
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
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseNine"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-down pull-right"></i> Products - Credit </a></h4>
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
                                                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerLoanBeans.size() > 0}">
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
                                                                        </c:if>
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
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTen"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-down pull-right"></i> Products - Pawning </a></h4>
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
                                                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.customerLoanBeans.size() > 0}">
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
                                                                        </c:if>
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
                                        </c:if>
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 1 || accountForm.searhDataBean.account_category == 2}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseThirteen" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Contact Person Details </a></h4>
                                                </div>
                                                <div id="collapseThirteen" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">NIC</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_nic" path="searhDataBean.cp_nic" placeholder="NIC" maxlength="10"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Title</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="cp_title" path="searhDataBean.cp_title" items="${titleList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Name in Full</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_name_in_full" path="searhDataBean.cp_name_in_full" placeholder="Name in Full" maxlength="100"/>
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
                                                                        <form:input type="text" disabled="true" id="cp_initial" path="searhDataBean.cp_initial" placeholder="Initials"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Last Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_last_name" path="searhDataBean.cp_last_name" placeholder="Last Name" maxlength="20"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Preferred Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_preferred_name" path="searhDataBean.cp_preferred_name" placeholder="Preferred Name" maxlength="20"/>
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
                                                                    <label class="label">Mobile No</label>
                                                                    <label class="input">
                                                                        <i class="icon-prepend fa fa-phone"></i>
                                                                        <form:input type="text" disabled="true" id="cp_mobile_no" path="searhDataBean.cp_mobile_no" placeholder="Mobile No" maxlength="9"/>
                                                                        <b class="tooltip tooltip-bottom-left">Ex: 777101010</b>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Email</label>
                                                                    <label class="input">
                                                                        <i class="icon-prepend fa fa-envelope-o"></i>
                                                                        <form:input type="text" disabled="true" id="cp_email" path="searhDataBean.cp_email" placeholder="Email" maxlength="100"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <br/>
                                        </c:if>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEleven" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Other </a></h4>
                                            </div>
                                            <div id="collapseEleven" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Tax Payee</label>
                                                                <label class="select">
                                                                    <form:select id="is_tax_payee" path="searhDataBean.is_tax_payee" items="${taxPayeeList}" disabled="true"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Tax Number <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="tax_number" path="searhDataBean.tax_number" placeholder="Tax Number" disabled="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-6">
                                                            <section>
                                                                <label class="label">Secret Question</label>
                                                                <label class="select">
                                                                    <form:select id="secret_question" path="secret_question" items="${secretQuestionList}" disabled="true"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-5"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-6">
                                                            <section>
                                                                <label class="label">Secret Response</label>
                                                                <label class="input">
                                                                    <form:input type="text" disabled="true" id="secret_response" path="secret_response" placeholder="Secret Response"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-5"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- end widget content -->
                                    </div>
                                    <!-- end widget div -->
                                </div>
                                <!-- end widget -->
                            </div>
                        </article>
                        <!-- WIDGET END -->

                        <!-- NEW WIDGET START -->
                        <article class="col-sm-12 col-md-12 col-lg-6">

                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-10" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
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
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->
                        </article>
                        <!-- WIDGET END -->
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <a id="back_btn" type="button" class="btn btn-default" href="${pageContext.servletContext.contextPath}/account">Back to Search</a>
                            </footer>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                </form:form>
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

        <script type="text/javascript">
            $(document).ready(function () {
                $('#pdcol').click();
            });

            function setAddressValues(id) {
                var addressObject = $('#' + id).text();
                var obj = jQuery.parseJSON(addressObject);
                clearAddressElements();
                $('#address_type_description').val(obj.address_type_description);
                $('#address_line_1').val(obj.address_line_1);
                $('#address_line_2').val(obj.address_line_2);
                $('#address_line_3').val(obj.address_line_3);
                $('#country_name').val(obj.country_name);
                $('#province_name').val(obj.province_name);
                $('#district_name').val(obj.district_name);
                $('#city_name').val(obj.city_name);
                $('#gs_division_name').val(obj.gs_division_name);
                $('#gps').val(obj.gps);
                $('#land_phone_no').val(obj.land_phone_no);
            }

            //Clear all address related elements
            function clearAddressElements() {
                $('#mf_customer_reference').val();
                $('#stakeholder_reference_no').val();
                $('#address_line_1').val();
                $('#address_line_2').val();
                $('#address_line_3').val();
                $('#address_type_description').val();
                $('#city_name').val();
                $('#gs_division_name').val();
                $('#district_name').val();
                $('#province_name').val();
                $('#country_name').val();
                $('#gps').val();
                $('#land_phone_no').val();
                $('#fax').val();
                $('#version').val();
            }

            function setSavingValues(id) {
                var savingObject = $('#' + id).text();
                var obj = jQuery.parseJSON(savingObject);
                clearSavingElements();
                $('#savings_id_view').val(obj.savings_id_view);
                $('#account_number').val(obj.account_number);
                $('#product_code').val(obj.product_code);
//                $('#stake_holder_reference').val(obj.stake_holder_reference);
                $('#current_balance').val(obj.current_balance);
                $('#freeze_amount').val(obj.freeze_amount);
                $('#available_balance').val(obj.available_balance);
                $('#savings_status').val(obj.status);
                $('#last_deposit_date').val(obj.last_deposit_date);
                $('#last_withdrawal_date').val(obj.last_withdrawal_date);
                $('#effective_start_date').val(obj.effective_start_date);
                $('#effective_end_date').val(obj.effective_end_date);
                $('#hold_amount').val(obj.hold_amount);
            }

            function clearSavingElements() {
                $('#savings_id_view').val();
                $('#account_number').val();
                $('#product_code').val();
//                $('#stake_holder_reference').val();
                $('#current_balance').val();
                $('#freeze_amount').val();
                $('#available_balance').val();
                $('#status').val('');
                $('#last_deposit_date').val();
                $('#last_withdrawal_date').val();
                $('#effective_start_date').val();
                $('#effective_end_date').val();
            }

            function setMicroFinanceValues(id) {
                var loanObject = $('#' + id).text();
                var obj = jQuery.parseJSON(loanObject);
                clearMicroFinanceElements();
                $('#mf_cs').val(obj.cs);
                $('#mf_module_code').val(obj.module_code);
                $('#mf_customer_code').val(obj.customer_code);
                $('#mf_contract_no').val(obj.contract_no);
                $('#mf_alias_name').val(obj.alias_name);
                $('#mf_contract_date').val(obj.contract_date);
                $('#mf_total_offered_amount').val(obj.total_offered_amount);
                $('#mf_contract_status').val(obj.contract_status);
                $('#mf_total_arrears_amount').val(obj.total_arrears_amount);
                $('#mf_outstanding_amount').val(obj.outstanding_amount);
            }

            function clearMicroFinanceElements() {
                $('#mf_cs').val();
                $('#mf_module_code').val();
                $('#mf_customer_code').val();
                $('#mf_contract_no').val();
                $('#mf_alias_name').val();
                $('#mf_contract_date').val();
                $('#mf_total_offered_amount').val();
                $('#mf_contract_status').val();
                $('#mf_total_arrears_amount').val();
                $('#mf_outstanding_amount').val();
            }

            function setCreditValues(id) {
                var loanObject = $('#' + id).text();
                var obj = jQuery.parseJSON(loanObject);
                clearCreditElements();
                $('#cr_cs').val(obj.cs);
                $('#cr_module_code').val(obj.module_code);
                $('#cr_customer_code').val(obj.customer_code);
                $('#cr_contract_no').val(obj.contract_no);
                $('#cr_alias_name').val(obj.alias_name);
                $('#cr_contract_date').val(obj.contract_date);
                $('#cr_total_offered_amount').val(obj.total_offered_amount);
                $('#cr_contract_status').val(obj.contract_status);
                $('#cr_total_arrears_amount').val(obj.total_arrears_amount);
                $('#cr_outstanding_amount').val(obj.outstanding_amount);
            }

            function clearCreditElements() {
                $('#cr_cs').val();
                $('#cr_module_code').val();
                $('#cr_customer_code').val();
                $('#cr_contract_no').val();
                $('#cr_alias_name').val();
                $('#cr_contract_date').val();
                $('#cr_total_offered_amount').val();
                $('#cr_contract_status').val();
                $('#cr_total_arrears_amount').val();
                $('#cr_outstanding_amount').val();
            }

            function setPawningValues(id) {
                var loanObject = $('#' + id).text();
                var obj = jQuery.parseJSON(loanObject);
                clearPawningElements();
                $('#pw_cs').val(obj.cs);
                $('#pw_module_code').val(obj.module_code);
                $('#pw_customer_code').val(obj.customer_code);
                $('#pw_contract_no').val(obj.contract_no);
                $('#pw_alias_name').val(obj.alias_name);
                $('#pw_contract_date').val(obj.contract_date);
                $('#pw_total_offered_amount').val(obj.total_offered_amount);
                $('#pw_contract_status').val(obj.contract_status);
                $('#pw_total_arrears_amount').val(obj.total_arrears_amount);
                $('#pw_outstanding_amount').val(obj.outstanding_amount);
            }

            function clearPawningElements() {
                $('#pw_cs').val();
                $('#pw_module_code').val();
                $('#pw_customer_code').val();
                $('#pw_contract_no').val();
                $('#pw_alias_name').val();
                $('#pw_contract_date').val();
                $('#pw_total_offered_amount').val();
                $('#pw_contract_status').val();
                $('#pw_total_arrears_amount').val();
                $('#pw_outstanding_amount').val();
            }

            function setFdValues(id) {
                var loanObject = $('#' + id).text();
                var obj = jQuery.parseJSON(loanObject);
                clearFdElements();
                $('#fd_no').val(obj.fd_no);
                $('#depamt').val(obj.depamt);
                $('#fd_date').val(obj.fd_date);
                $('#fd_prd').val(obj.fd_prd);
                $('#fd_inrate').val(obj.fd_inrate);
                $('#center').val(obj.center);
                $('#fd_type').val(obj.fd_type);
                $('#fd_monint').val(obj.fd_monint);
                $('#fd_next').val(obj.fd_next);
            }

            function clearFdElements() {
                $('#fd_no').val();
                $('#depamt').val();
                $('#fd_date').val();
                $('#fd_prd').val();
                $('#fd_inrate').val();
                $('#center').val();
                $('#fd_type').val();
                $('#fd_monint').val();
                $('#fd_next').val();
            }
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
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
    </body>
</html>
