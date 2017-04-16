<%-- 
    Author     : Roshen Dilshan
    Document   : accountcreate
    Created on : Jul 3, 2015, 9:17:17 AM
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
                <ol class="breadcrumb">
                    <li><a href="${pageContext.servletContext.contextPath}/account">Customer Management</a></li><li>Create Customer</li>
                </ol>

            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                       <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Create New Customer
                            <span>
                            </span>
                        </h1>
                    </div>
                </div>

                <form:form id="accountCreate" novalidate="novalidate" class="smart-form" commandName="accountForm" action="${pageContext.servletContext.contextPath}/account/create" autocomplete="off" enctype="multipart/form-data">
                    <form:hidden id="address_list" path="address_list"/>
                    <form:hidden id="education_list" path="education_list"/>
                    <form:hidden id="dependent_list" path="dependent_list"/>
                    <form:hidden id="sub_sector_not_assign_list" path="sub_sector_not_assign_list"/>
                    <form:hidden id="sub_sector_list" path="sub_sector_list"/>
                    <form:hidden id="copsub_sector_list" path="copsub_sector_list"/>
                    <form:hidden id="copsub_sector_not_assign_list" path="copsub_sector_not_assign_list"/>
                    <form:hidden id="customer_hobby_list" path="customer_hobby_list"/>
                    <form:hidden id="account_id" path="account_id"/>
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
                                <label class="label">Customer Category <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="customer_category" path="customer_category" onchange="categoryOnChange('${pageContext.servletContext.contextPath}')" items="${customerCategoryList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Customer Category Type <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="customer_category_type" path="customer_category_type" items="${customerCategoryTypeList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Code Type <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="customer_code_type" path="customer_code_type" items="${customerCodeTypeList}"/>
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
                                <label class="label">Code <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="password" id="customer_code" path="customer_code" placeholder="Code"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Confirm Code <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="customer_code_confirm" path="customer_code_confirm" name="customer_code_confirm" placeholder="Confirm Code" maxlength="10"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch / Location <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="branch_location" path="branch_location" items="${branchList}"/>
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
                        <div class="col-xs-8">
                            <footer style="background-color: #ffffff">
                                <!--<button type="button" id="test" class="btn btn-primary">Test</button>-->
                                <form:button  type="button" id="checkPointOneSaveAndMove" disabled="${avn_create}" class="btn btn-primary">Create & Move Next</form:button>
                                <form:button type="button" id="checkPointOneSave" disabled="${avn_create}" class="btn btn-primary">Create</form:button>
                            </footer>
                        </div>
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
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Personal Details </a></h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Title <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="title" path="title" items="${titleList}"/>
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
                                                                <label class="label">Name in Full <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="name_in_full" path="name_in_full" placeholder="Name in Full" maxlength="100"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Initials <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="initials" path="initials" placeholder="Initials" readonly="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Last Name <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="last_name" path="last_name" placeholder="Last Name" maxlength="20" readonly="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Preferred Name <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="preferred_name" path="preferred_name" placeholder="Preferred Name" maxlength="20"/>
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
                                                                <label class="label">Date of Birth <samp style="color: red">*</samp></label>
                                                                <label class="input"> <i class="icon-prepend fa fa-calendar"></i>
                                                                    <form:input type="text" class="form-control" id="date_of_birth" path="date_of_birth" placeholder="Date of Birth" readonly="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Gender <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="gender" path="gender" items="${genderList}"/>
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
                                                                <label class="label">Mothers Maiden Name <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input id="mothers_maiden_name" path="mothers_maiden_name" placeholder="Mothers Maden Name" maxlength="100"/>
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
                                                                <label class="label">Nationality</label>
                                                                <label class="select">
                                                                    <form:select id="nationality" path="nationality" items="${nationalityList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Religion</label>
                                                                <label class="select">
                                                                    <form:select id="religion" path="religion" items="${religonList}"/>
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
                                                                    <form:select id="marital_status" path="marital_status" items="${maritalStatusList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Preferred Language</label>
                                                                <label class="select">
                                                                    <form:select id="preferred_language" path="preferred_language" items="${languageList}"/>
                                                                    <i></i>
                                                                    <%--<form:input type="text" id="preferred_language" path="preferred_language" placeholder="Preferred Language" maxlength="20"/>--%>
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
                                                                    <i class="icon-prepend fa fa-phone"></i>
                                                                    <form:input type="text" id="mobile_01" path="mobile_01" placeholder="Mobile No 01" maxlength="9"/>
                                                                    <b class="tooltip tooltip-bottom-left">e.g. : 777101010</b>
                                                                </label>
                                                                <div class="note">
                                                                    <strong>Hint</strong> e.g. :  777101010
                                                                </div>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Mobile No 02</label>
                                                                <label class="input">
                                                                    <i class="icon-prepend fa fa-phone"></i>
                                                                    <form:input type="text" id="mobile_02" path="mobile_02" placeholder="Mobile No 02" maxlength="9"/>
                                                                    <b class="tooltip tooltip-bottom-left">e.g. : 777101010</b>
                                                                </label>
                                                                <div class="note">
                                                                    <strong>Hint</strong> e.g. :  777101010
                                                                </div>
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
                                                                    <i class="icon-prepend fa fa-envelope-o"></i>
                                                                    <form:input type="text" id="email" path="email" placeholder="Email" maxlength="100"/>
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
                                                            <footer style="background-color: #ffffff">
                                                                <form:button type="button" id="checkPointTwoSaveAndMove" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button type="button" id="checkPointTwoSave" disabled="${avn_create}" class="btn btn-primary">Save</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseOne"/>
                                        <!-- corporate customer details -->
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEight" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Corporate Customer Details </a></h4>
                                            </div>
                                            <div id="collapseEight" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label">Company Name <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="copemployer" path="copemployer" placeholder="Company Name" maxlength="150"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Sector <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="copsector" path="copsector" items="${opsectorList}" onchange="loadCopSubsectorList('${pageContext.servletContext.contextPath}')"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-7"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Sub Sector <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="copsub_sector_not_assign" path="copsub_sector_not_assign" multiple="true" style="height:100px;" items="${subsectorNotAssignList}"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2">
                                                            <section>
                                                                <label class="label" style="height:19px;"></label>
                                                            </section>
                                                            <div class="row">
                                                                <div class="col-xs-5"></div>
                                                                <div class="col-xs-2">
                                                                    <section>
                                                                        <button id="cop_pull_right" type="button" class="btn btn-xs">>></button>
                                                                    </section>
                                                                    <section>
                                                                        <button id="cop_pull_left" type="button" class="btn btn-xs"><<</button>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-5"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label" style="height:19px;"></label>
                                                                <label class="select">
                                                                    <form:select id="copsub_sector_assign" path="copsub_sector_assign" multiple="true" style="height:100px;" items="${subsectorAssignList}"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button type="button" id="checkPointNineSaveAndMove" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button type="button" id="checkPointNineSave" disabled="${avn_create}" class="btn btn-primary">Save</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseEight"/>
                                        <!-- corporate customer details -->
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
                                                                <label class="label">Address Type <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="address_type" path="address_type" items="${addressTypeList}"/>
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
                                                                <label class="label">Address Line 01 <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="address_line_01" path="address_line_01" placeholder="Address Line 01" maxlength="100"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label">Address Line 02 <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="address_line_02" path="address_line_02" placeholder="Address Line 02" maxlength="100"/>
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
                                                                    <form:input type="text" id="address_line_03" path="address_line_03" placeholder="Address Line 03" maxlength="100"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Country <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="country" path="country" items="${countryList}" onchange="loadProvinceList('${pageContext.servletContext.contextPath}')"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Province <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="province" path="province" items="${provinceList}" onchange="loadDistrictList('${pageContext.servletContext.contextPath}')"/>
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
                                                                <label class="label">District <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="district" path="district" items="${districtList}" onchange="loadCityList('${pageContext.servletContext.contextPath}')"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">City</label>
                                                                <label class="select">
                                                                    <form:select id="city" path="city" items="${cityList}"/>
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
                                                                <label class="label">GS Division</label>
                                                                <label class="input">
                                                                    <form:input type="text" id="gs" path="gs" placeholder="GS Division" maxlength="20"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">GPS</label>
                                                                <label class="input">
                                                                    <form:input type="text" id="gps" path="gps" placeholder="GPS" maxlength="20"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Land Phone No <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="land_phone_no" path="land_phone_no" placeholder="Land Phone No" maxlength="9"/>
                                                                    <b class="tooltip tooltip-bottom-left">e.g. : 333101010</b>
                                                                </label>
                                                                <div class="note">
                                                                    <strong>Hint</strong> e.g. :  333101010
                                                                </div>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Billing Proof <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="billing_proof" path="billing_proof" items="${billingProofList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-7"></div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-sm btn-default pull-right" onclick="clearAddressFields()">Clear</button>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table id="address_table" class="table table-bordered table-condensed table-hover">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Address Type</th>
                                                                        <th data-hide="phone,tablet">Address Line 01</th>
                                                                        <th data-class="phone"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i> Telephone</th>
                                                                        <th>Action</th>
                                                                        <th style="display: none">address_id</th>
                                                                        <th style="display: none">address_type</th>
                                                                        <th style="display: none">address_line_01</th>
                                                                        <th style="display: none">address_line_02</th>
                                                                        <th style="display: none">address_line_03</th>
                                                                        <th style="display: none">country</th>
                                                                        <th style="display: none">province</th>
                                                                        <th style="display: none">district</th>
                                                                        <th style="display: none">city</th>
                                                                        <th style="display: none">gs</th>
                                                                        <th style="display: none">gps</th>
                                                                        <th style="display: none">land_phone_no</th>
                                                                        <th style="display: none">billing_proof</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var = "addressrow" items = "${addressRowList}">
                                                                        <tr>
                                                                            <td>${addressrow.address_type_descriptin == null ? "" : addressrow.address_type_descriptin}</td>
                                                                            <td>${addressrow.address_line_01 == null ? "" : addressrow.address_line_01}</td>
                                                                            <td>${addressrow.land_phone_no == null ? "" : addressrow.land_phone_no}</td>
                                                                            <td>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="selectedAddressRow($(this), '${pageContext.servletContext.contextPath}')">
                                                                                        <i class="fa fa-eye" title="View"></i>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="deleteAddressRow($(this))">
                                                                                        <i class="fa fa-times-circle" title="Delete"></i>
                                                                                    </a>
                                                                                </div>
                                                                            </td>
                                                                            <td style="display: none">${addressrow.address_id == null ? "" : addressrow.address_id}</td>
                                                                            <td style="display: none">${addressrow.address_type == null ? "" : addressrow.address_type}</td>
                                                                            <td style="display: none">${addressrow.address_line_01 == null ? "" : addressrow.address_line_01}</td>
                                                                            <td style="display: none">${addressrow.address_line_02 == null ? "" : addressrow.address_line_02}</td>
                                                                            <td style="display: none">${addressrow.address_line_03 == null ? "" : addressrow.address_line_03}</td>
                                                                            <td style="display: none">${addressrow.country == null ? "" : addressrow.country}</td>
                                                                            <td style="display: none">${addressrow.province == null ? "" : addressrow.province}</td>
                                                                            <td style="display: none">${addressrow.district == null ? "" : addressrow.district}</td>
                                                                            <td style="display: none">${addressrow.city == null ? "" : addressrow.city}</td>
                                                                            <td style="display: none">${addressrow.gs == null ? "" : addressrow.gs}</td>
                                                                            <td style="display: none">${addressrow.gps == null ? "" : addressrow.gps}</td>
                                                                            <td style="display: none">${addressrow.land_phone_no == null ? "" : addressrow.land_phone_no}</td>
                                                                            <td style="display: none">${addressrow.billing_proof == null ? "" : addressrow.billing_proof}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button id="checkPointThree" type="button" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button id="address_add_btn" type="button" disabled="${avn_create}" class="btn btn-primary">Save & Add Another</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <!-- Contact Person Details -->
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseNine" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Contact Person Details </a></h4>
                                            </div>
                                            <div id="collapseNine" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">NIC</label>
                                                                <label class="input">
                                                                    <form:input id="cp_nic" path="cp_nic" placeholder="NIC" maxlength="10"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Title</label>
                                                                <label class="select">
                                                                    <form:select id="cp_title" path="cp_title" items="${titleList}"/>
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
                                                                    <form:input type="text" id="cp_name_in_full" path="cp_name_in_full" placeholder="Name in Full" maxlength="100"/>
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
                                                                    <form:input type="text" id="cp_initials" path="cp_initials" placeholder="Initials" readonly="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Last Name</label>
                                                                <label class="input">
                                                                    <form:input type="text" id="cp_last_name" path="cp_last_name" placeholder="Last Name" maxlength="20" readonly="true"/>
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
                                                                    <form:input type="text" id="cp_preferred_name" path="cp_preferred_name" placeholder="Preferred Name" maxlength="20"/>
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
                                                                    <form:input type="text" id="cp_mobile" path="cp_mobile" placeholder="Mobile No" maxlength="9"/>
                                                                    <b class="tooltip tooltip-bottom-left">e.g. : 777101010</b>
                                                                </label>
                                                                <div class="note">
                                                                    <strong>Hint</strong> e.g. :  333101010
                                                                </div>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Email</label>
                                                                <label class="input">
                                                                    <i class="icon-prepend fa fa-envelope-o"></i>
                                                                    <form:input type="text" id="cp_email" path="cp_email" placeholder="Email" maxlength="100"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button type="button" id="checkPointTenSaveAndMove" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button type="button" id="checkPointTenSave" disabled="${avn_create}" class="btn btn-primary">Save</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseNine"/>
                                        <!-- Contact Person Details -->
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Education Info </a></h4>
                                            </div>
                                            <div id="collapseThree" class="panel-collapse collapse">
                                                <div class="panel-body no-padding">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Education Level</label>
                                                                <label class="select">
                                                                    <form:select id="education_level" path="education_level" items="${educationLevelList}"/>
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
                                                        <div class="col-xs-6">
                                                            <section>
                                                                <label class="label">Institute</label>
                                                                <label class="input">
                                                                    <form:input id="institute" path="institute" placeholder="Institute"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-4"></div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-7"></div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-sm btn-default pull-right" onclick="clearEducationFields()">Clear</button>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table id="education_table" class="table table-bordered table-condensed">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Education Level</th>
                                                                        <th>Institution</th>
                                                                        <th>Action</th>
                                                                        <th style="display: none">education_id</th>
                                                                        <th style="display: none">education_level</th>
                                                                        <th style="display: none">institute</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var = "educationrow" items = "${educationRowList}">
                                                                        <tr>
                                                                            <td>${educationrow.education_level_description == null ? "" : educationrow.education_level_description}</td>
                                                                            <td>${educationrow.institute == null ? "" : educationrow.institute}</td>
                                                                            <td>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="selectedEducationRow($(this))">
                                                                                        <i class="fa fa-eye" title="View"></i>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="deleteEducationRow($(this))">
                                                                                        <i class="fa fa-times-circle" title="Delete"></i>
                                                                                    </a>
                                                                                </div>
                                                                            </td>
                                                                            <td style="display: none">${educationrow.education_id == null ? "" : educationrow.education_id}</td>
                                                                            <td style="display: none">${educationrow.education_level == null ? "" : educationrow.education_level}</td>
                                                                            <td style="display: none">${educationrow.institute == null ? "" : educationrow.institute}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button id="checkPointFour" type="button" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button id="education_add_btn" type="button" disabled="${avn_create}" class="btn btn-primary">Save & Add Another</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseThree"/>
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
                                                                <label class="select">
                                                                    <form:select id="designation" path="designation" items="${opdesignationList}"/>
                                                                    <i></i>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <!--                                                            <section>
                                                                                                                            <label class="label">Level</label>
                                                                                                                            <label class="select">
                                                                                                                                <form :select id="level" path="level" items="${oplevelList}"/>
                                                                                                                                <i></i>
                                                                                                                            </label>
                                                                                                                        </section>-->
                                                            <section>
                                                                <label class="label">Profession</label>
                                                                <label class="select">
                                                                    <form:select id="profession" path="profession" items="${opprofessionList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <!--                                                    <div class="row">
                                                                                                            <div class="col-xs-1"></div>
                                                                                                            <div class="col-xs-4"></div>
                                                                                                            <div class="col-xs-7"></div>
                                                                                                        </div>-->
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label">Employer</label>
                                                                <label class="input">
                                                                    <form:input type="text" id="employer" path="employer" placeholder="Employer" maxlength="150"/>
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
                                                                <label class="select">
                                                                    <form:select id="sector" path="sector" items="${opsectorList}" onchange="loadSubsectorList('${pageContext.servletContext.contextPath}')"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-7"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Sub Sector</label>
                                                                <label class="select">
                                                                    <form:select id="sub_sector_not_assign" path="sub_sector_not_assign" multiple="true" style="height:100px;" items="${subsectorNotAssignList}"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2">
                                                            <section>
                                                                <label class="label" style="height:19px;"></label>
                                                            </section>
                                                            <div class="row">
                                                                <div class="col-xs-5"></div>
                                                                <div class="col-xs-2">
                                                                    <section>
                                                                        <button id="pull_right" type="button" class="btn btn-xs">>></button>
                                                                    </section>
                                                                    <section>
                                                                        <button id="pull_left" type="button" class="btn btn-xs"><<</button>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-5"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label" style="height:19px;"></label>
                                                                <label class="select">
                                                                    <form:select id="sub_sector_assign" path="sub_sector_assign" multiple="true" style="height:100px;" items="${subsectorAssignList}"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button type="button" id="checkPointFiveSaveAndMove" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button type="button" id="checkPointFiveSave" disabled="${avn_create}" class="btn btn-primary">Save</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseFour"/>
                                        <div class="panel panel-default" style="display: none;">
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
                                                                <label class="select">
                                                                    <form:select id="sgc_title" path="sgc_title" items="${titleList}"/>
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
                                                                    <form:input type="text" id="sgc_name_in_full" path="sgc_name_in_full" placeholder="Name in Full"/>
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
                                                                    <form:input type="text" id="sgc_initials" path="sgc_initials" placeholder="Initials" readonly="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">First Name</label>
                                                                <label class="input">
                                                                    <form:input type="text" id="sgc_firstname" path="sgc_firstname" placeholder="First Name"/>
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
                                                                    <form:input type="text" id="sgc_lastname" path="sgc_lastname" placeholder="Last Name" readonly="true"/>
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
                                                                <label class="label">Address - Residence</label>
                                                                <label class="input">
                                                                    <form:input type="text" id="sgc_address_residence" path="sgc_address_residence" placeholder="Address - Residence"/>
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
                                                                    <form:input type="text" id="sgc_occupation" path="sgc_occupation" placeholder="Occupation"/>
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
                                                                    <form:input type="text" id="sgc_employer_business_name" path="sgc_employer_business_name" placeholder="Employer / Business Name"/>
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
                                                                    <form:input type="text" id="sgc_employer_business_address" path="sgc_employer_business_address" placeholder="Employer / Business Address"/>
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
                                                                    <form:input type="text" id="sgc_employer_telephone" path="sgc_employer_telephone" placeholder="Telephone - Work" maxlength="9"/>
                                                                    <b class="tooltip tooltip-bottom-left">e.g. : 777101010</b>
                                                                </label>
                                                                <div class="note">
                                                                    <strong>Hint</strong> e.g. :  333101010
                                                                </div>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Years of Employment/ Business</label>
                                                                <label class="input">
                                                                    <form:input type="text" id="sgc_years_of_employeement_business" path="sgc_years_of_employeement_business" placeholder="Years of Employment/ Business"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button id="checkPointSixSaveAndMove" type="button" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button type="button" id="checkPointSixSave" disabled="${avn_create}" class="btn btn-primary">Save</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseFive" style="display: none;"/>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseSix" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Dependents </a></h4>
                                            </div>
                                            <div id="collapseSix" class="panel-collapse collapse">
                                                <div class="panel-body no-padding">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Dependent Relationship </label>
                                                                <label class="select">
                                                                    <form:select id="dependent_relationship" path="dependent_relationship" items="${dependentRelationshipList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Date of Birth </label>
                                                                <label class="input"><i class="icon-prepend fa fa-calendar"></i>
                                                                    <form:input id="dependent_date_of_birth" class="form-control"  path="dependent_date_of_birth" placeholder="Date of Birth" readonly="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label">Name in Full </label>
                                                                <label class="input">
                                                                    <form:input id="dependent_name_in_full" path="dependent_name_in_full" placeholder="Name in Full"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-7"></div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-sm btn-default pull-right" onclick="clearDependentFields()">Clear</button>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table id="dependent_table" class="table table-bordered table-condensed">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Relationship</th>
                                                                        <th>Date of Birth</th>
                                                                        <th>Name in Full </th>
                                                                        <th>Action</th>
                                                                        <th style="display: none">dependent_id</th>
                                                                        <th style="display: none">dependent_relationship</th>
                                                                        <th style="display: none">dependent_date_of_birth</th>
                                                                        <th style="display: none">dependent_name_in_full</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var = "dependentrow" items = "${dependentRowList}">
                                                                        <tr>
                                                                            <td>${dependentrow.dependent_relationship_description == null ? "" : dependentrow.dependent_relationship_description}</td>
                                                                            <td>${dependentrow.dependent_date_of_birth == null ? "" : dependentrow.dependent_date_of_birth}</td>
                                                                            <td>${dependentrow.dependent_name_in_full == null ? "" : dependentrow.dependent_name_in_full}</td>
                                                                            <td>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="selectedDependentRow($(this))">
                                                                                        <i class="fa fa-eye" title="View"></i>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="deleteDependentRow($(this))">
                                                                                        <i class="fa fa-times-circle" title="Delete"></i>
                                                                                    </a>
                                                                                </div>
                                                                            </td>
                                                                            <td style="display: none">${dependentrow.dependent_id == null ? "" : dependentrow.dependent_id}</td>
                                                                            <td style="display: none">${dependentrow.dependent_relationship == null ? "" : dependentrow.dependent_relationship}</td>
                                                                            <td style="display: none">${dependentrow.dependent_date_of_birth == null ? "" : dependentrow.dependent_date_of_birth}</td>
                                                                            <td style="display: none">${dependentrow.dependent_name_in_full == null ? "" : dependentrow.dependent_name_in_full}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button id="checkPointSeven" type="button" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button id="dependent_add_btn" type="button" disabled="${avn_create}" class="btn btn-primary">Save & Add Another</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseSix"/>

                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEleven" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Hobbies And Interests </a></h4>
                                            </div>
                                            <div id="collapseEleven" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Hobbies And Interests <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="hobby_id" path="hobby_id" items="${hobbuesAndInterestList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Description </label>
                                                                <label class="textarea textarea-expandable">
                                                                    <form:textarea id="hobby_comment" path="hobby_comment" style="custom-scroll"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-7"></div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-sm btn-default pull-right" onclick="">Clear</button>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <table id="hobby_table" class="table table-bordered table-condensed">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>Hobby / Interest</th>
                                                                        <th>Description</th>
                                                                        <th>Action</th>
                                                                        <th style="display: none">hobby_id</th>
                                                                        <th style="display: none">description</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var = "hobbiesandintersrow" items = "${hobbiesAndIntersetList}">
                                                                        <tr>
                                                                            <td>${hobbiesandintersrow.hobby_description == null ? "" : hobbiesandintersrow.hobby_description}</td>
                                                                            <td>${hobbiesandintersrow.hobby_comment == null ? "" : hobbiesandintersrow.hobby_comment}</td>
                                                                            <td>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="selectedHobbyRow($(this))">
                                                                                        <i class="fa fa-eye" title="View"></i>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="col-xs-6">
                                                                                    <a href="javascript:void(0);" onclick ="deleteHobbyRow($(this))">
                                                                                        <i class="fa fa-times-circle" title="Delete"></i>
                                                                                    </a>
                                                                                </div>
                                                                            </td>
                                                                            <td style="display: none">${hobbiesandintersrow.hobby_id == null ? "" : hobbiesandintersrow.hobby_id}</td>
                                                                            <td style="display: none">${hobbiesandintersrow.hobby_description == null ? "" : hobbiesandintersrow.hobby_description}</td>
                                                                            <td style="display: none">${hobbiesandintersrow.hobby_comment == null ? "" : hobbiesandintersrow.hobby_comment}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button type="button"  disabled="${avn_create}" id="checkPointElevenSaveAndMove" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button id="checkPointEleven" disabled="${avn_create}" type="button" class="btn btn-primary">Save & Add Another</form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseEleven"/>

                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Other Details </a></h4>
                                            </div>
                                            <div id="collapseSeven" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Tax Payee</label>
                                                                <label class="select">
                                                                    <form:select id="is_tax_payee" path="is_tax_payee" items="${taxPayeeList}" onchange="taxOnChange()"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Tax Number <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="tax_no" path="tax_no" placeholder="Tax Number" disabled="true"/>
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
                                                                    <form:select id="secret_question" path="secret_question" items="${secretQuestionList}" onchange="secreteOnChange()"/>
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
                                                                <label class="label">Secret Response <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input type="text" id="secret_response" path="secret_response" placeholder="Secret Response" disabled="true"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-5"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <footer style="background-color: #ffffff">
                                                                <form:button type="button" id="checkPointEightSaveAndMove" disabled="${avn_create}" class="btn btn-primary">Save & Move Next</form:button>
                                                                <form:button id="checkPointEight" type="button" disabled="${avn_create}" class="btn btn-primary">Save</form:button>

                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br class="collapseSeven"/>                
                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="background-color: #F2F1F1">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTen" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Attachments </a></h4>
                                            </div>
                                            <div id="collapseTen" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <br/>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">NIC</label>
                                                                <div class="input input-file">
                                                                    <span class="button" style="background-color: #CF3C43;"><form:input type="file" id="nic_file" path="nic_file" onchange="nicFileSelect()"/>Browse</span><input type="text" placeholder="NIC" readonly=""/>
                                                                </div>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-4">
                                                            <section>
                                                                <label class="label">Signature</label>
                                                                <div class="input input-file">
                                                                    <span class="button" style="background-color: #CF3C43;"><form:input type="file" id="signature_file" path="signature_file" onchange="signatureFileSelect()"/>Browse</span><input type="text" placeholder="Signature" readonly="readonly"/>
                                                                </div>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
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
                                <form:button type="submit" disabled="${avn_create}" class="btn btn-primary">Save</form:button>
                                <a id="back_btn" type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/account">Back to Search</a>
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
                $('#customer_code').focus(function () {
                    $('#customer_code').attr('type', 'text');
                });

                $('#customer_code').focusout(function () {
                    $('#customer_code').attr('type', 'password');
                });

                $('#test').click(function () {
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/testcall",
                        cache: false,
                        success: function (response) {
                            console.log('success : ' + response);
                        },
                        error: function () {
                            console.log('error');
                        }
                    });
                });

                $('#checkPointOneSave').click(function () {
                    createRecord(false);
                });

                $('#checkPointOneSaveAndMove').click(function () {
                    createRecord(true);
                });

                $('#checkPointTwoSave').click(function () {
                    createPersonalDetails(false);
                });

                $('#checkPointTwoSaveAndMove').click(function () {
                    createPersonalDetails(true);
                });

                $('#address_type').focusout(function () {
                    if ($('#address_type').val() === '') {
                        var parent = $('#address_type').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#address_type').addClass('invalid');
                        parent.append('<em for="address_type" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#address_type').parent();
                        parent.removeClass('state-error');
                        $('#address_type').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#address_line_01').focusout(function () {
                    if ($('#address_line_01').val() === '') {
                        var parent = $('#address_line_01').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#address_line_01').addClass('invalid');
                        parent.append('<em for="address_line_01" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#address_line_01').parent();
                        parent.find('em').remove();
                        parent.removeClass('state-error');
                        $('#address_line_01').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#address_line_02').focusout(function () {
                    if ($('#address_line_02').val() === '') {
                        var parent = $('#address_line_02').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#address_line_02').addClass('invalid');
                        parent.append('<em for="address_line_02" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#address_line_02').parent();
                        parent.find('em').remove();
                        parent.removeClass('state-error');
                        $('#address_line_02').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                //                $('#address_line_03').focusout(function () {
                //                    if ($('#address_line_03').val() === '') {
                //                        var parent = $('#address_line_03').parent();
                //                        parent.find('em').remove();
                //                        parent.addClass('state-error');
                //                        $('#address_line_03').addClass('invalid');
                //                        parent.append('<em for="customer_category" class="invalid">This field is required</em>');
                //                        isValid = false;
                //                    } else {
                //                        var parent = $('#address_line_03').parent();
                //                        parent.removeClass('state-error');
                //                        $('#address_line_03').removeClass('invalid');
                //                        parent.find('em').remove();
                //                    }
                //                });

                $('#country').focusout(function () {
                    if ($('#country').val() === '') {
                        var parent = $('#country').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#country').addClass('invalid');
                        parent.append('<em for="country" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#country').parent();
                        parent.removeClass('state-error');
                        $('#country').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#province').focusout(function () {
                    if ($('#province').val() === '') {
                        var parent = $('#province').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#province').addClass('invalid');
                        parent.append('<em for="province" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#province').parent();
                        parent.removeClass('state-error');
                        $('#province').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#district').focusout(function () {
                    if ($('#district').val() === '') {
                        var parent = $('#district').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#district').addClass('invalid');
                        parent.append('<em for="district" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#district').parent();
                        parent.removeClass('state-error');
                        $('#district').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#land_phone_no').focusout(function () {
                    if ($('#land_phone_no').val() === '' || $('#land_phone_no').val().length !== 9) {
                        var parent = $('#land_phone_no').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#land_phone_no').addClass('invalid');
                        parent.append('<em for="land_phone_no" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#land_phone_no').parent();
                        parent.removeClass('state-error');
                        $('#land_phone_no').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#billing_proof').focusout(function () {
                    if ($('#billing_proof').val() === '') {
                        var parent = $('#billing_proof').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#billing_proof').addClass('invalid');
                        parent.append('<em for="billing_proof" class="invalid">This field is required</em>');
                    } else {
                        var parent = $('#billing_proof').parent();
                        parent.removeClass('state-error');
                        $('#billing_proof').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#tax_no').focusout(function () {
                    if ($('#is_tax_payee').val() === '1') {
                        if ($('#tax_no').val() === '') {
                            var parent = $('#tax_no').parent();
                            parent.find('em').remove();
                            parent.addClass('state-error');
                            $('#tax_no').addClass('invalid');
                            parent.append('<em for="tax_no" class="invalid">This field is required</em>');
                            isValid = false;
                        } else {
                            var parent = $('#tax_no').parent();
                            parent.removeClass('state-error');
                            $('#tax_no').removeClass('invalid');
                            parent.find('em').remove();
                        }
                    } else {
                        var parent = $('#tax_no').parent();
                        parent.removeClass('state-error');
                        $('#tax_no').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#secret_response').focusout(function () {
                    if ($('#secret_question').val() !== '') {
                        if ($('#secret_response').val() === '') {
                            var parent = $('#secret_response').parent();
                            parent.find('em').remove();
                            parent.addClass('state-error');
                            $('#secret_response').addClass('invalid');
                            parent.append('<em for="secret_response" class="invalid">This field is required</em>');
                            isValid = false;
                        } else {
                            var parent = $('#secret_response').parent();
                            parent.removeClass('state-error');
                            $('#secret_response').removeClass('invalid');
                            parent.find('em').remove();
                        }
                    } else {
                        var parent = $('#secret_response').parent();
                        parent.removeClass('state-error');
                        $('#secret_response').removeClass('invalid');
                        parent.find('em').remove();
                    }
                });

                $('#address_add_btn').click(function () {
                    crateAddress(false);
                });

                $('#checkPointThree').click(function () {
                    crateAddress(true);
                });

                $('#education_add_btn').click(function () {
                    createEducation(false);
                });

                $('#checkPointFour').click(function () {
                    createEducation(true);
                });

                $('#checkPointFiveSave').click(function () {
                    createOccupation(false);
                });

                $('#checkPointFiveSaveAndMove').click(function () {
                    createOccupation(true);
                });

                $('#checkPointSixSave').click(function () {
                    createSGC(false);
                });

                $('#checkPointSixSaveAndMove').click(function () {
                    createSGC(true);
                });

                $('#dependent_add_btn').click(function () {
                    createDependent(false);
                });

                $('#checkPointSeven').click(function () {
                    createDependent(true);
                });

                $('#checkPointEight').click(function () {
                    createTaxDetails(false);
                });

                $('#checkPointEightSaveAndMove').click(function () {
                    createTaxDetails(true);
                });

                $('#checkPointEleven').click(function () {
                    createHobby(false);
                });

                $('#checkPointElevenSaveAndMove').click(function () {
                    createHobby(true);
                });

//                $('#checkPointEight').click(function () {
//                    $('#msg_dev').empty();
//                    var isValid = true;
//                    if ($('#is_tax_payee').val() !== '') {
//                        if ($('#tax_no').val() === '') {
//                            var parent = $('#tax_no').parent();
//                            parent.find('em').remove();
//                            parent.addClass('state-error');
//                            $('#tax_no').addClass('invalid');
//                            parent.append('<em for="customer_category" class="invalid">This field is required</em>');
//                            isValid = false;
//                        } else {
//                            var parent = $('#tax_no').parent();
//                            parent.removeClass('state-error');
//                            $('#tax_no').removeClass('invalid');
//                            parent.find('em').remove();
//                        }
//                    } else {
//                        var parent = $('#tax_no').parent();
//                        parent.removeClass('state-error');
//                        $('#tax_no').removeClass('invalid');
//                        parent.find('em').remove();
//                    }
//
//                    if ($('#secret_question').val() !== '') {
//                        if ($('#secret_response').val() === '') {
//                            var parent = $('#secret_response').parent();
//                            parent.find('em').remove();
//                            parent.addClass('state-error');
//                            $('#secret_response').addClass('invalid');
//                            parent.append('<em for="customer_category" class="invalid">This field is required</em>');
//                            isValid = false;
//                        } else {
//                            var parent = $('#secret_response').parent();
//                            parent.removeClass('state-error');
//                            $('#secret_response').removeClass('invalid');
//                            parent.find('em').remove();
//                        }
//                    } else {
//                        var parent = $('#secret_response').parent();
//                        parent.removeClass('state-error');
//                        $('#secret_response').removeClass('invalid');
//                        parent.find('em').remove();
//                    }
//
//                    if (isValid) {
//                        var dataObject = new Object();
//                        dataObject.account_id = $('#account_id').val();
//                        dataObject.is_tax_payee = $('#is_tax_payee').val();
//                        dataObject.tax_no = $('#tax_no').val();
//                        dataObject.secret_question = $('#secret_question').val();
//                        dataObject.secret_response = $('#secret_response').val();
//                        var content = JSON.stringify(dataObject);
//                        $.ajax({
//                            async: true,
//                            type: "post",
//                            url: "${pageContext.servletContext.contextPath}/account/create/savepointeight",
//                            cache: false,
//                            data: {other: content},
//                            success: function (response) {
//                                response = JSON.parse(response);
//                                if (response.CODE !== "SUCCESS") {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
//                                    window.scrollTo(0, 0);
//                                }
//                            },
//                            error: function () {
//                                console.log('Error while request..');
//                            }
//                        });
//                    }
//                });

                $('#checkPointNineSave').click(function () {
                    createCorporateDetails(false);
                });

                $('#checkPointNineSaveAndMove').click(function () {
                    createCorporateDetails(true);
                });

                $('#checkPointTenSave').click(function () {
                    createContacrPerson(false);
                });

                $('#checkPointTenSaveAndMove').click(function () {
                    createContacrPerson(true);
                });
            });

            function createRecord(move_next) {
                $('#msg_dev').empty();
                var isValid = 1;
                if ($('#customer_category').valid() === 0) {
                    isValid = 0;
                }
                if ($('#customer_category_type').valid() === 0) {
                    isValid = 0;
                }
                if ($('#customer_code_type').valid() === 0) {
                    isValid = 0;
                }
                if ($('#customer_code').valid() === 0) {
                    isValid = 0;
                }
                if ($('#customer_code_confirm').valid() === 0) {
                    isValid = 0;
                }
                if ($('#branch_location').valid() === 0) {
                    isValid = 0;
                }
                if (isValid !== 0) {
                    var dataObject = new Object();
                    dataObject.customer_category = $('#customer_category').val();
                    dataObject.customer_category_type = $('#customer_category_type').val();
                    dataObject.customer_code_type = $('#customer_code_type').val();
                    dataObject.customer_code = $('#customer_code').val();
                    dataObject.branch_location = $('#branch_location').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/createrecord",
                        cache: false,
                        data: {account_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                                $('#account_id').val(response.account_id);
                                if (move_next) {
                                    if ($('#customer_category').val() === '0') {
                                        $('#collapseOne').collapse('show');
                                    } else {
                                        $('#collapseEight').collapse('show');
                                    }
                                }
                                $('#checkPointOneSave').attr('disabled', 'disabled');
                                $('#checkPointOneSaveAndMove').attr('disabled', 'disabled');
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createPersonalDetails(move_next) {
                $('#msg_dev').empty();
                var isValid = 1;
                if ($('#title').valid() === 0) {
                    isValid = 0;
                }
                if ($('#name_in_full').valid() === 0) {
                    isValid = 0;
                }
                if ($('#initials').valid() === 0) {
                    isValid = 0;
                }
                if ($('#last_name').valid() === 0) {
                    isValid = 0;
                }
                if ($('#preferred_name').valid() === 0) {
                    isValid = 0;
                }
                if ($('#date_of_birth').valid() === 0) {
                    isValid = 0;
                }
                if ($('#gender').valid() === 0) {
                    isValid = 0;
                }
                if ($('#mothers_maiden_name').valid() === 0) {
                    isValid = 0;
                }
                if ($('#mobile_01').valid() === 0) {
                    isValid = 0;
                }
                if ($('#mobile_02').valid() === 0) {
                    isValid = 0;
                }
                if ($('#email').valid() === 0) {
                    isValid = 0;
                }
                if (isValid !== 0) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.title = $("#title").val();
                    dataObject.name_in_full = $("#name_in_full").val();
                    dataObject.initials = $("#initials").val();
                    dataObject.last_name = $("#last_name").val();
                    dataObject.preferred_name = $("#preferred_name").val();
                    dataObject.date_of_birth = $("#date_of_birth").val();
                    dataObject.gender = $("#gender").val();
                    dataObject.mothers_maiden_name = $("#mothers_maiden_name").val();
                    dataObject.nationality = $("#nationality").val();
                    dataObject.religion = $("#religion").val();
                    dataObject.marital_status = $("#marital_status").val();
                    dataObject.preferred_language = $("#preferred_language").val();
                    dataObject.mobile_01 = $("#mobile_01").val();
                    dataObject.mobile_02 = $("#mobile_02").val();
                    dataObject.email = $("#email").val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointone",
                        cache: false,
                        data: {account: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                if (move_next) {
                                    $('#collapseOne').collapse('hide');
                                    $('#collapseTwo').collapse('show');
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createCorporateDetails(move_next) {
                $('#msg_dev').empty();
                var isValid = 1;
                if ($('#copemployer').valid() === 0) {
                    isValid = 0;
                }
                if ($('#copsector').valid() === 0) {
                    isValid = 0;
                }
                if (isValid !== 0) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.copemployer = $('#copemployer').val();
                    dataObject.copsector = $('#copsector').val();

                    var subsectorsnotassign = [];
                    $("#copsub_sector_not_assign option").each(function () {
                        subsectorsnotassign.push($(this).val());
                    });
                    var subsectornotassign = $.parseJSON(JSON.stringify(subsectorsnotassign));
                    $('#copsub_sector_not_assign_list').val('[' + subsectornotassign + ']');

                    var subsectors = [];
                    $("#copsub_sector_assign option").each(function () {
                        subsectors.push($(this).val());
                    });
                    var subsectorassign = $.parseJSON(JSON.stringify(subsectors));
                    $('#copsub_sector_list').val('[' + subsectorassign + ']');

                    dataObject.copsub_sector_assign = subsectorassign;
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointnine",
                        cache: false,
                        data: {corporate: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                if (move_next) {
                                    $('#collapseEight').collapse('hide');
                                    $('#collapseTwo').collapse('show');
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function crateAddress(move_next) {
                $('#msg_dev').empty();
                var isValid = true;
                if ($('#address_type').val() === '') {
                    var parent = $('#address_type').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#address_type').addClass('invalid');
                    parent.append('<em for="address_type" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#address_type').parent();
                    parent.removeClass('state-error');
                    $('#address_type').removeClass('invalid');
                    parent.find('em').remove();
                }

                if ($('#address_line_01').val() === '') {
                    var parent = $('#address_line_01').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#address_line_01').addClass('invalid');
                    parent.append('<em for="address_line_01" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#address_line_01').parent();
                    parent.find('em').remove();
                    parent.removeClass('state-error');
                    $('#address_line_01').removeClass('invalid');
                    parent.find('em').remove();
                }

                if ($('#address_line_02').val() === '') {
                    var parent = $('#address_line_02').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#address_line_02').addClass('invalid');
                    parent.append('<em for="address_line_02" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#address_line_02').parent();
                    parent.find('em').remove();
                    parent.removeClass('state-error');
                    $('#address_line_02').removeClass('invalid');
                    parent.find('em').remove();
                }

                //                    if ($('#address_line_03').val() === '') {
                //                        var parent = $('#address_line_03').parent();
                //                        parent.find('em').remove();
                //                        parent.addClass('state-error');
                //                        $('#address_line_03').addClass('invalid');
                //                        parent.append('<em for="customer_category" class="invalid">This field is required</em>');
                //                        isValid = false;
                //                    } else {
                //                        var parent = $('#address_line_03').parent();
                //                        parent.removeClass('state-error');
                //                        $('#address_line_03').removeClass('invalid');
                //                        parent.find('em').remove();
                //                    }

                if ($('#country').val() === '') {
                    var parent = $('#country').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#country').addClass('invalid');
                    parent.append('<em for="country" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#country').parent();
                    parent.removeClass('state-error');
                    $('#country').removeClass('invalid');
                    parent.find('em').remove();
                }

                if ($('#province').val() === '') {
                    var parent = $('#province').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#province').addClass('invalid');
                    parent.append('<em for="province" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#province').parent();
                    parent.removeClass('state-error');
                    $('#province').removeClass('invalid');
                    parent.find('em').remove();
                }

                if ($('#district').val() === '') {
                    var parent = $('#district').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#district').addClass('invalid');
                    parent.append('<em for="district" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#district').parent();
                    parent.removeClass('state-error');
                    $('#district').removeClass('invalid');
                    parent.find('em').remove();
                }

                if ($('#land_phone_no').val() === '' || $('#land_phone_no').val().length !== 9) {
                    var parent = $('#land_phone_no').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#land_phone_no').addClass('invalid');
                    parent.append('<em for="land_phone_no" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#land_phone_no').parent();
                    parent.removeClass('state-error');
                    $('#land_phone_no').removeClass('invalid');
                    parent.find('em').remove();
                }

                if ($('#billing_proof').val() === '') {
                    var parent = $('#billing_proof').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#billing_proof').addClass('invalid');
                    parent.append('<em for="billing_proof" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#billing_proof').parent();
                    parent.removeClass('state-error');
                    $('#billing_proof').removeClass('invalid');
                    parent.find('em').remove();
                }

                if (isValid) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.address_type = $('#address_type').val();
                    dataObject.address_line_01 = $('#address_line_01').val();
                    dataObject.address_line_02 = $('#address_line_02').val();
                    dataObject.address_line_03 = $('#address_line_03').val();
                    dataObject.country = $('#country').val();
                    dataObject.province = $('#province').val();
                    dataObject.district = $('#district').val();
                    dataObject.city = $('#city').val();
                    dataObject.gs = $('#gs').val();
                    dataObject.gps = $('#gps').val();
                    dataObject.land_phone_no = $('#land_phone_no').val();
                    dataObject.billing_proof = $('#billing_proof').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointtwo",
                        cache: false,
                        data: {address: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                $('#address_table tbody').append('<tr>'
                                        + '<td>' + $("#address_type option:selected").text() + '</td>'
                                        + '<td>' + $('#address_line_01').val() + '</td>'
                                        + '<td>' + $('#land_phone_no').val() + '</td><td>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="selectedAddressRow($(this), &quot;${pageContext.servletContext.contextPath}&quot;)">'
                                        + '<i class="fa fa-eye" title="View"></i>'
                                        + '</a>'
                                        + '</div>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="deleteAddressRow($(this))">'
                                        + '<i class="fa fa-times-circle" title="Delete"></i>'
                                        + '</a>'
                                        + '</div></td>'
                                        + '<td style="display: none">' + response.address_id + '</td>'
                                        + '<td style="display: none">' + dataObject.address_type + '</td>'
                                        + '<td style="display: none">' + dataObject.address_line_01 + '</td>'
                                        + '<td style="display: none">' + dataObject.address_line_02 + '</td>'
                                        + '<td style="display: none">' + dataObject.address_line_03 + '</td>'
                                        + '<td style="display: none">' + dataObject.country + '</td>'
                                        + '<td style="display: none">' + dataObject.province + '</td>'
                                        + '<td style="display: none">' + dataObject.district + '</td>'
                                        + '<td style="display: none">' + dataObject.city + '</td>'
                                        + '<td style="display: none">' + dataObject.gs + '</td>'
                                        + '<td style="display: none">' + dataObject.gps + '</td>'
                                        + '<td style="display: none">' + dataObject.land_phone_no + '</td>'
                                        + '<td style="display: none">' + dataObject.billing_proof + '</td>'
                                        + '</tr>');
                                $('#address_list').val(JSON.stringify($('#address_table').tableToJSON({
                                    ignoreColumns: [0, 1, 2, 3]
                                })));
                                clearAddressFields();
                                if (move_next) {
                                    $('#collapseTwo').collapse('hide');
                                    if ($('#customer_category').val() === '0') {
                                        $('#collapseThree').collapse('show');
                                    } else {
                                        $('#collapseNine').collapse('show');
                                    }
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createContacrPerson(move_next) {
                $('#msg_dev').empty();
                var isValid = 1;
                if ($('#cp_nic').valid() === 0) {
                    isValid = 0;
                }
                if ($('#cp_name_in_full').valid() === 0) {
                    isValid = 0;
                }
                if ($('#cp_preferred_name').valid() === 0) {
                    isValid = 0;
                }
                if ($('#cp_mobile').valid() === 0) {
                    isValid = 0;
                }
                if ($('#cp_email').valid() === 0) {
                    isValid = 0;
                }
                if (isValid !== 0) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.cp_nic = $('#cp_nic').val();
                    dataObject.cp_title = $('#cp_title').val();
                    dataObject.cp_name_in_full = $('#cp_name_in_full').val();
                    dataObject.cp_initials = $('#cp_initials').val();
                    dataObject.cp_last_name = $('#cp_last_name').val();
                    dataObject.cp_preferred_name = $('#cp_preferred_name').val();
                    dataObject.cp_mobile = $('#cp_mobile').val();
                    dataObject.cp_email = $('#cp_email').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointten",
                        cache: false,
                        data: {contactperson: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                if (move_next) {
                                    $('#collapseNine').collapse('hide');
                                    $('#collapseSeven').collapse('show');
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createEducation(move_next) {
                $('#msg_dev').empty();
                var isValid = true;
                if ($('#education_level').val() === '') {
                    var parent = $('#education_level').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#education_level').addClass('invalid');
                    parent.append('<em for="education_level" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#education_level').parent();
                    parent.find('em').remove();
                    parent.removeClass('state-error');
                    $('#education_level').removeClass('invalid');
                    parent.find('em').remove();
                }

                if (isValid) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.education_level = $('#education_level').val();
                    dataObject.institute = $('#institute').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointthree",
                        cache: false,
                        data: {education: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                $('#education_table tbody').append('<tr>'
                                        + '<td>' + $("#education_level option:selected").text() + '</td>'
                                        + '<td>' + $('#institute').val() + '</td>'
                                        + '<td>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="selectedEducationRow($(this))">'
                                        + '<i class="fa fa-eye" title="View"></i>'
                                        + '</a>'
                                        + '</div>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="deleteEducationRow($(this))">'
                                        + '<i class="fa fa-times-circle" title="Delete"></i>'
                                        + '</a>'
                                        + '</div></td>'
                                        + '<td style="display: none">' + response.education_id + '</td>'
                                        + '<td style="display: none">' + dataObject.education_level + '</td>'
                                        + '<td style="display: none">' + dataObject.institute + '</td>'
                                        + '</tr>');
                                $('#education_list').val(JSON.stringify($('#education_table').tableToJSON({
                                    ignoreColumns: [0, 1, 2]
                                })));
                                clearEducationFields();
                                if (move_next) {
                                    $('#collapseThree').collapse('hide');
                                    $('#collapseFour').collapse('show');
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createOccupation(move_next) {
                $('#msg_dev').empty();
                var isValid = 1;
                if ($('#employer').valid() === 0) {
                    isValid = 0;
                }
                if (isValid !== 0) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.designation = $('#designation').val();
                    dataObject.level = $('#level').val();
                    dataObject.profession = $('#profession').val();
                    dataObject.employer = $('#employer').val();
                    dataObject.sector = $('#sector').val();

                    var subsectorsnotassign = [];
                    $("#sub_sector_not_assign option").each(function () {
                        subsectorsnotassign.push($(this).val());
                    });
                    var subsectornotassign = $.parseJSON(JSON.stringify(subsectorsnotassign));
                    $('#sub_sector_not_assign_list').val('[' + subsectornotassign + ']');

                    var subsectors = [];
                    $("#sub_sector_assign option").each(function () {
                        subsectors.push($(this).val());
                    });
                    var subsectorassign = $.parseJSON(JSON.stringify(subsectors));
                    $('#sub_sector_list').val('[' + subsectorassign + ']');

                    dataObject.subsectors = subsectorassign;
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointfive",
                        cache: false,
                        data: {occupation: content, account_id: $('#account_id').val()},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                if (move_next) {
                                    $('#collapseFour').collapse('hide');
                                    if ($('#collapseFive').parent().is(':visible')) {
                                        $('#collapseFive').collapse('show');
                                    } else {
                                        $('#collapseSix').collapse('show');
                                    }
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createSGC(move_next) {
                $('#msg_dev').empty();
//                var isValid = true;
//                if (!$('#sgc_years_of_employeement_business').isNumeric()) {
//                    var parent = $('#sgc_years_of_employeement_business').parent();
//                    parent.find('em').remove();
//                    parent.addClass('state-error');
//                    $('#sgc_years_of_employeement_business').addClass('invalid');
//                    parent.append('<em for="sgc_years_of_employeement_business" class="invalid">Should be a Number</em>');
//                    isValid = false;
//                } else {
//                    var parent = $('#sgc_years_of_employeement_business').parent();
//                    parent.find('em').remove();
//                    parent.removeClass('state-error');
//                    $('#sgc_years_of_employeement_business').removeClass('invalid');
//                    parent.find('em').remove();
//                }

                var isValid = 1;
                if ($('#sgc_name_in_full').valid() === 0) {
                    isValid = 0;
                }
                if ($('#sgc_firstname').valid() === 0) {
                    isValid = 0;
                }
                if ($('#sgc_occupation').valid() === 0) {
                    isValid = 0;
                }
                if ($('#sgc_employer_business_name').valid() === 0) {
                    isValid = 0;
                }
                if ($('#sgc_employer_telephone').valid() === 0) {
                    isValid = 0;
                }
                if ($('#sgc_years_of_employeement_business').valid() === 0) {
                    isValid = 0;
                }
                if (isValid !== 0) {
//                    if (isValid) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.sgc_title = $('#sgc_title').val();
                    dataObject.sgc_name_in_full = $('#sgc_name_in_full').val();
                    dataObject.sgc_initials = $('#sgc_initials').val();
                    dataObject.sgc_firstname = $('#sgc_firstname').val();
                    dataObject.sgc_lastname = $('#sgc_lastname').val();
                    dataObject.sgc_address_residence = $('#sgc_address_residence').val();
                    dataObject.sgc_occupation = $('#sgc_occupation').val();
                    dataObject.sgc_employer_business_name = $('#sgc_employer_business_name').val();
                    dataObject.sgc_employer_business_address = $('#sgc_employer_business_address').val();
                    dataObject.sgc_employer_telephone = $('#sgc_employer_telephone').val();
                    dataObject.sgc_years_of_employeement_business = $('#sgc_years_of_employeement_business').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointsix",
                        cache: false,
                        data: {sgc: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                if (move_next) {
                                    $('#collapseFive').collapse('hide');
                                    $('#collapseSix').collapse('show');
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createDependent(move_next) {
                $('#msg_dev').empty();
                var isValid = true;
                if ($('#dependent_relationship').val() === '') {
                    var parent = $('#dependent_relationship').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#dependent_relationship').addClass('invalid');
                    parent.append('<em for="dependent_relationship" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#dependent_relationship').parent();
                    parent.find('em').remove();
                    parent.removeClass('state-error');
                    $('#dependent_relationship').removeClass('invalid');
                    parent.find('em').remove();
                }

                if (isValid) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.dependent_relationship = $('#dependent_relationship').val();
                    dataObject.dependent_date_of_birth = $('#dependent_date_of_birth').val();
                    dataObject.dependent_name_in_full = $('#dependent_name_in_full').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointseven",
                        cache: false,
                        data: {dependent: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                $('#dependent_table tbody').append('<tr>'
                                        + '<td>' + $("#dependent_relationship option:selected").text() + '</td>'
                                        + '<td>' + dataObject.dependent_date_of_birth + '</td>'
                                        + '<td>' + dataObject.dependent_name_in_full + '</td>'
                                        + '<td>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="selectedDependentRow($(this))">'
                                        + '<i class="fa fa-eye" title="View"></i>'
                                        + '</a>'
                                        + '</div>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="deleteDependentRow($(this))">'
                                        + '<i class="fa fa-times-circle" title="Delete"></i>'
                                        + '</a>'
                                        + '</div></td>'
                                        + '<td style="display: none">' + response.dependent_id + '</td>'
                                        + '<td style="display: none">' + dataObject.dependent_relationship + '</td>'
                                        + '<td style="display: none">' + dataObject.dependent_date_of_birth + '</td>'
                                        + '<td style="display: none">' + dataObject.dependent_name_in_full + '</td>'
                                        + '</tr>');
                                $('#dependent_list').val(JSON.stringify($('#dependent_table').tableToJSON({
                                    ignoreColumns: [0, 1, 2, 3]
                                })));
                                clearDependentFields();
                                if (move_next) {
                                    $('#collapseSix').collapse('hide');
                                    $('#collapseEleven').collapse('show');
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function createHobby(move_next) {
                $('#msg_dev').empty();
                var isValid = true;
                if ($('#hobby_id').val() === '') {
                    var parent = $('#hobby_id').parent();
                    parent.find('em').remove();
                    parent.addClass('state-error');
                    $('#hobby_id').addClass('invalid');
                    parent.append('<em for="hobby_id" class="invalid">This field is required</em>');
                    isValid = false;
                } else {
                    var parent = $('#hobby_id').parent();
                    parent.find('em').remove();
                    parent.removeClass('state-error');
                    $('#hobby_id').removeClass('invalid');
                    parent.find('em').remove();
                }

                if (isValid) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.hobby_id = $('#hobby_id').val();
                    dataObject.hobby_comment = $('#hobby_comment').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointeleven",
                        cache: false,
                        data: {hobbies: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                $('#hobby_table tbody').append('<tr>'
                                        + '<td>' + $("#hobby_id option:selected").text() + '</td>'
                                        + '<td>' + dataObject.hobby_comment + '</td>'
                                        + '<td>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="selectedHobbyRow($(this))">'
                                        + '<i class="fa fa-eye" title="View"></i>'
                                        + '</a>'
                                        + '</div>'
                                        + '<div class="col-xs-6">'
                                        + '<a href="javascript:void(0);" onclick ="deleteHobbyRow($(this))">'
                                        + '<i class="fa fa-times-circle" title="Delete"></i>'
                                        + '</a>'
                                        + '</div></td>'
                                        + '<td style="display: none">' + response.customer_hobby_id + '</td>'
                                        + '<td style="display: none">' + dataObject.hobby_id + '</td>'
                                        + '<td style="display: none">' + dataObject.hobby_comment + '</td>'
                                        + '</tr>');
                                $('#customer_hobby_list').val(JSON.stringify($('#hobby_table').tableToJSON({
                                    ignoreColumns: [0, 1, 2, 3]
                                })));
                                clearHobbyFields();
                                if (move_next) {
                                    $('#collapseEleven').collapse('hide');
                                    $('#collapseSeven').collapse('show');
                                }
                            } else {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
            }

            function deleteAddressRow(row) {
                var tableRow = row.closest('tr');
                var tableDataList = tableRow.find('td');
                $.SmartMessageBox({
                    title: "Alert!",
                    content: "<i class='fa fa-trash-o fa-3x'></i> Are you sure you want to delete the selected Address ?",
                    buttons: '[No][Yes]'
                }, function (ButtonPressed) {
                    if (ButtonPressed === "Yes") {
                        $.ajax({
                            async: true,
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/account/create/deleteaddress",
                            cache: false,
                            data: {address_id: tableDataList[4].innerText, account_id: $('#account_id').val()},
                            success: function (response) {
                                response = JSON.parse(response);
                                if (response.CODE === "SUCCESS") {
                                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div> <br/>');
                                    tableRow.remove();
                                    clearAddressFields();
                                } else {
                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                    window.scrollTo(0, 0);
                                }
                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        });
                    }
                });
            }

            function deleteEducationRow(row) {
                var tableRow = row.closest('tr');
                var tableDataList = tableRow.find('td');
                $.SmartMessageBox({
                    title: "Alert!",
                    content: "<i class='fa fa-trash-o fa-3x'></i> Are you sure you want to delete the selected Education Qualification ?",
                    buttons: '[No][Yes]'
                }, function (ButtonPressed) {
                    if (ButtonPressed === "Yes") {
                        $.ajax({
                            async: true,
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/account/create/deleteeducation",
                            cache: false,
                            data: {education_id: tableDataList[3].innerText, account_id: $('#account_id').val()},
                            success: function (response) {
                                response = JSON.parse(response);
                                if (response.CODE === "SUCCESS") {
                                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div> <br/>');
                                    tableRow.remove();
                                    clearEducationFields();
                                } else {
                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                    window.scrollTo(0, 0);
                                }
                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        });
                    }
                });
            }

            function deleteDependentRow(row) {
                var tableRow = row.closest('tr');
                var tableDataList = tableRow.find('td');
                $.SmartMessageBox({
                    title: "Alert!",
                    content: "<i class='fa fa-trash-o fa-3x'></i> Are you sure you want to delete the selected Dependent ?",
                    buttons: '[No][Yes]'
                }, function (ButtonPressed) {
                    if (ButtonPressed === "Yes") {
                        $.ajax({
                            async: true,
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/account/create/deletedependent",
                            cache: false,
                            data: {dependent_id: tableDataList[4].innerText, account_id: $('#account_id').val()},
                            success: function (response) {
                                response = JSON.parse(response);
                                if (response.CODE === "SUCCESS") {
                                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div> <br/>');
                                    tableRow.remove();
                                    clearDependentFields();
                                } else {
                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                    window.scrollTo(0, 0);
                                }
                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        });
                    }
                });
            }

            function deleteHobbyRow(row) {
                var tableRow = row.closest('tr');
                var tableDataList = tableRow.find('td');
                $.SmartMessageBox({
                    title: "Alert!",
                    content: "<i class='fa fa-trash-o fa-3x'></i> Are you sure you want to delete the selected Hobby/Interest ?",
                    buttons: '[No][Yes]'
                }, function (ButtonPressed) {
                    if (ButtonPressed === "Yes") {
                        $.ajax({
                            async: true,
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/account/create/deletehobby",
                            cache: false,
                            data: {hobby_id: tableDataList[3].innerText, account_id: $('#account_id').val()},
                            success: function (response) {
                                response = JSON.parse(response);
                                if (response.CODE === "SUCCESS") {
                                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div> <br/>');
                                    tableRow.remove();
                                    clearDependentFields();
                                } else {
                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                    window.scrollTo(0, 0);
                                }
                            },
                            error: function () {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                window.scrollTo(0, 0);
                            }
                        });
                    }
                });
            }

            function taxOnChange() {
                if ($('#is_tax_payee').val() === '1') {
                    $('#tax_no').removeAttr('disabled');
                    var parent = $('#tax_no').parent();
                    parent.removeClass('state-error');
                    $('#tax_no').removeClass('invalid');
                    parent.find('em').remove();
                } else {
                    $('#tax_no').val('');
                    $('#tax_no').attr('disabled', 'disabled');
                }
            }

            function secreteOnChange() {
                if ($('#secret_question').val() === '') {
                    $('#secret_response').val('');
                    $('#secret_response').attr('disabled', 'disabled');
                } else {
                    $('#secret_response').removeAttr('disabled');
                    var parent = $('#secret_response').parent();
                    parent.removeClass('state-error');
                    $('#secret_response').removeClass('invalid');
                    parent.find('em').remove();
                }
            }

            function createTaxDetails(move_next) {
                $('#msg_dev').empty();
                var isValid = true;
                if ($('#is_tax_payee').val() === '1') {
                    if ($('#tax_no').val() === '') {
                        var parent = $('#tax_no').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#tax_no').addClass('invalid');
                        parent.append('<em for="tax_no" class="invalid">This field is required</em>');
                        isValid = false;
                    } else {
                        var parent = $('#tax_no').parent();
                        parent.removeClass('state-error');
                        $('#tax_no').removeClass('invalid');
                        parent.find('em').remove();
                    }
                } else {
                    var parent = $('#tax_no').parent();
                    parent.removeClass('state-error');
                    $('#tax_no').removeClass('invalid');
                    parent.find('em').remove();
                }

                if ($('#secret_question').val() !== '') {
                    if ($('#secret_response').val() === '') {
                        var parent = $('#secret_response').parent();
                        parent.find('em').remove();
                        parent.addClass('state-error');
                        $('#secret_response').addClass('invalid');
                        parent.append('<em for="secret_response" class="invalid">This field is required</em>');
                        isValid = false;
                    } else {
                        var parent = $('#secret_response').parent();
                        parent.removeClass('state-error');
                        $('#secret_response').removeClass('invalid');
                        parent.find('em').remove();
                    }
                } else {
                    var parent = $('#secret_response').parent();
                    parent.removeClass('state-error');
                    $('#secret_response').removeClass('invalid');
                    parent.find('em').remove();
                }

                if (isValid) {
                    var dataObject = new Object();
                    dataObject.account_id = $('#account_id').val();
                    dataObject.is_tax_payee = $('#is_tax_payee').val();
                    dataObject.tax_no = $('#tax_no').val();
                    dataObject.secret_question = $('#secret_question').val();
                    dataObject.secret_response = $('#secret_response').val();
                    var content = JSON.stringify(dataObject);
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/account/create/savepointeight",
                        cache: false,
                        data: {other: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE !== "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                                window.scrollTo(0, 0);
                            } else {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
                                if (move_next) {
                                    $('#collapseSeven').collapse('hide');
                                    $('#collapseTen').collapse('show');
                                }
                            }
                        },
                        error: function () {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }
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
            })();</script>
    </body>
</html>