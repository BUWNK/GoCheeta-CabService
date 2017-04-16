
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li><a href="${pageContext.servletContext.contextPath}/case/search">Ticket Management</a></li><li>Update Ticket</li>
                    <!-- end breadcrumb -->
                </ol>
                <!-- You can also add more buttons to the
                ribbon for further usability

                Example below:

                <span class="ribbon-button-alignment pull-right">
                <span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa-grid"></i> Change Grid</span>
                <span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa-plus"></i> Add</span>
                <span id="search" class="btn btn-ribbon" data-title="search"><i class="fa-search"></i> <span class="hidden-mobile">Search</span></span>
                </span> -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Edit Ticket 
                            <span>
                                Ticket ID : ${caseDetails.caseId}
                            </span>
                        </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-1"></div>
                    <div class="col-xs-10">
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success">
                                <strong>Success!</strong> ${successMessage}
                            </div>
                        </c:if> 
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-warning">
                                <strong>Warning!</strong> ${errorMessage}
                            </div>
                            <br/>
                        </c:if> 
                    </div>
                    <div class="col-xs-1"></div>
                </div>
                <div class="row">
                    <!--<h2 class="row-seperator-header"><i class="fa fa-th-list"></i> Case ID > ${caseDetails.caseId} </h2>-->
                    <!-- NEW WIDGET START -->
                    <article class="col-sm-12 col-md-12 col-lg-12">

                        <!-- Widget ID (each widget will need unique ID)-->
                        <!--<div class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">-->
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
                        <!--                            <header>
                                                        <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                                                        <h2></h2>
                        
                                                    </header>-->
                        <!-- widget div-->
                        <div>
                            <!-- widget edit box -->
                            <div class="jarviswidget-editbox">
                                <!-- This area used as dropdown edit box -->
                            </div>
                            <!-- end widget edit box -->
                            <!-- widget content -->
                            <div class="widget-body">
                                <div class="panel-group smart-accordion-default" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Ticket Details </a></h4>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse">
                                            <div class="panel-body no-padding">
                                                <form class="smart-form">
                                                    <input type="hidden" path="file_location" name="file_location" id="file_location" readonly="true" value="${caseDetails.file_location}"/>
                                                    <input type="hidden" path="caseId" name="caseId" id="file_location" readonly="true" value="${caseDetails.caseId}"/>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Ticket Type</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.caseTypeDes}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="form-group">
                                                                <label class="lable">Start Date </label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.caseDate}">
                                                                    <i class="icon-append fa fa-calendar"></i>
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Priority</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.priorityDes}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Department</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.departmentDes}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Product</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.productDes}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Customer Account</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.accountId}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="input">
                                                                <section>
                                                                    <label name="title" class="label">Title</label>
                                                                    <label class="input">
                                                                        <input  type="text" readonly="true"  value="${caseDetails.title}" /><i></i>
                                                                    </label>
                                                                </section>                                   
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="input">
                                                                <section>
                                                                    <label name="customername" class="label">Customer Name </label>
                                                                    <label class="input">
                                                                        <input  type="text" readonly="true"  value="${caseDetails.customername}" /><i></i>
                                                                    </label>
                                                                </section>                                   
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="input">
                                                                <section>
                                                                    <label name="nic" class="label"> NIC </label>
                                                                    <label class="input">
                                                                        <input  type="text"  readonly="true"  value="${caseDetails.nic}" /><i></i>
                                                                    </label>
                                                                </section>                                   
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="input">
                                                                <section>
                                                                    <label name="phonenumber" class="label"> Telephone </label>
                                                                    <label class="input">
                                                                        <input  type="text" readonly="true" value="${caseDetails.phonenumber}" /><i></i>
                                                                    </label>
                                                                    <div class="note">
                                                                        <strong>Hint</strong> e.g. 777101010
                                                                    </div>
                                                                </section>                                   
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <label class="label">Subject</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.subject}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <label class="label">Ticket Description</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.description}">
                                                                </label>
                                                            </section> 
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="input">
                                                                <section>
                                                                    <label name="branchcode" class="label"> Branch </label>
                                                                    <label class="input">
                                                                        <input  type="text"  readonly="true"  value="${caseDetails.branchDesc}" /><i></i>
                                                                    </label>
                                                                </section>                                   
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="input">
                                                                <section>
                                                                    <label name="caseCategory" class="label"> BR/MF </label>
                                                                    <label class="input">
                                                                        <input  type="text" readonly="true" value="${caseDetails.caseCategory}" /><i></i>
                                                                    </label>
                                                                </section>                                   
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <label class="label"></label>
                                                                <label class="input">
                                                                    <input type="hidden" readonly="true" value="${caseDetails.branchcode}">
                                                                </label>
                                                            </section> 
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <label class="label">Assignee</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.assignee1Name}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Status</label>
                                                                <label class="input">
                                                                    <input type="text" readonly="true" value="${caseDetails.statusDes}">
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div id="attachment">
                                                            <c:if test="${caseDetails.filename != null}">

                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Attachment</label>
                                                                        <label class="input">
                                                                            <input type="text" id="filename" name="filename" readonly="true" value="${caseDetails.filename}">
                                                                        </label>
                                                                    </section>
                                                                </div>

                                                            </c:if>
                                                        </div>
                                                        <div id ="removeAttachmentbutton">
                                                            <c:if test="${caseDetails.filename != null}">
                                                                <div class="row">
                                                                    <div class="col-xs-1"></div>
                                                                    <div class="col-xs-10">
                                                                        <footer style="background-color: #ffffff">
                                                                            <button id="button" type="button" onclick="removeAttachment()" class="btn btn-default">Remove Attachment</button>
                                                                        </footer>
                                                                    </div>
                                                                    <div class="col-xs-1"></div>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                    <div id="calllog">
                                        <c:if test="${caseDetails.caseCallLogId != null}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Call log </a></h4>
                                                </div>
                                                <div id="collapseTwo" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <form class="smart-form" >
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Title</label>
                                                                        <label class="input">
                                                                            <input type="text" id="title" disabled="true" path="title" value="${objcallcenter.title}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-8"></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-8">
                                                                    <section>
                                                                        <label class="label">Name in Full <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <input type="text" id="name_in_full"  path="name_in_full" disabled="true" value="${objcallcenter.name_in_full}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>     
                                                                <div class="col-xs-2"></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Company Name</label>
                                                                        <label class="input">
                                                                            <input type="text" id="companyname" path="companyname" disabled="true"  placeholder="companyname name" value="${objcallcenter.companyname}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>    
                                                            </div>  
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Last Name</label>
                                                                        <label class="input">
                                                                            <input type="text" id="last_name" path="last_name"  value="${objcallcenter.last_name}" disabled="true"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3"></div>  
                                                                <div class="col-xs-3"></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Preferred Language <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <input type="text" id="preferred_language"  path="preferred_language"  disabled="true" value="${objcallcenter.languageID}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Call Direction <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <input type="text" id="callDirection"  path="callDirection"  disabled="true" value="${objcallcenter.callDirectionDes}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-7"></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label path="productId" class="label">Module <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <input type="text" id="productId"  path="productId"  disabled="true" value="${objcallcenter.productIdDes}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label" path="caseTypeId">Ticket Type <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <input type="text" id="caseTypeId"  path="caseTypeId"  disabled="true" value="${objcallcenter.caseTypeDes}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Start date <samp style="color: red">*</samp></label>
                                                                        <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                                            <fmt:formatDate value="${objcallcenter.startDate}" pattern="yyyy-MM-dd" var="startdate"/>
                                                                            <input type="text" path="startDate" name="startDate" disabled="true" data-dateformat="yy-mm-dd" value="${startdate}" /></label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Start Time <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <fmt:formatDate value="${objcallcenter.startTime}" pattern="hh:mm a" var="startTime"/>
                                                                            <input type="text" id="startTime" disabled="true" value="${startTime}"/>
                                                                            <i class="icon-append fa fa-clock-o"></i>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Follow-up-Action <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <input type="text" id="followUpAction"  path="followUpAction"  disabled="true"  value="${objcallcenter.followUpActionDes}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label" >Status <samp style="color: red">*</samp></label>
                                                                        <label class="input">
                                                                            <input type="text" id="statusDes"  path="statusDes"  disabled="true" value="${objcallcenter.statusDes}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                            </div>
                                                            <c:if test="${objcallcenter.callbackDate != null}">
                                                                <div class="row">
                                                                    <div class="col-xs-2"></div>
                                                                    <div class="col-xs-3">
                                                                        <section>
                                                                            <label class="label">Callback Date <samp style="color: red">*</samp></label>
                                                                            <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                                                <input type="text" id="startTime" disabled="true" value="${objcallcenter.callbackDate}"/>
                                                                            </label>
                                                                        </section>
                                                                    </div>
                                                                    <div class="col-xs-2"></div>
                                                                    <div class="col-xs-3">
                                                                        <section>
                                                                            <label class="label">Callback Time <samp style="color: red">*</samp></label>
                                                                            <label class="input">
                                                                                <input type="time" id="callbackTime" disabled="true" value="${objcallcenter.callbackTime}"/>
                                                                                <i class="icon-append fa fa-clock-o"></i>
                                                                            </label>
                                                                        </section>
                                                                    </div>
                                                                    <div class="col-xs-1"></div>
                                                                </div>
                                                            </c:if>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Contact 1</label>
                                                                        <label class="input">
                                                                            <i class="icon-prepend fa fa-phone"></i>
                                                                            <input type="text" id="telephone" disabled="true" value="${objcallcenter.telephone}" />
                                                                        </label>
                                                                        <div class="note">
                                                                            <strong>Hint</strong> e.g. 777101010
                                                                        </div>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Contact 2</label>
                                                                        <label class="input">
                                                                            <i class="icon-prepend fa fa-phone"></i>
                                                                            <input type="text" id="telephone" disabled="true" value="${objcallcenter.telephone2}" />
                                                                        </label>
                                                                        <div class="note">
                                                                            <strong>Hint</strong> e.g. 777101010
                                                                        </div>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-1">
                                                                    <section>
                                                                        <label class="label"></label>
                                                                        <label class="input">
                                                                            <input type="hidden" id="accountId" disabled="true" value=""/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-8">
                                                                    <section>
                                                                        <label class="label"></label>
                                                                        <label class="input">
                                                                            <input type="hidden" id="callLogId" disabled="true" path="callLogId" value="${objcallcenter.callLogId}"/>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Branch</label>
                                                                        <label class="input">
                                                                            <i class="icon-prepend fa fa-phone"></i>
                                                                            <input type="text" id="branchDes" disabled="true" value="${objcallcenter.branchDes}" />
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-8">
                                                                    <section>
                                                                        <label path="comments" class="label">Comments <samp style="color: red">*</samp></label>
                                                                        <label class="textarea">
                                                                            <textarea path="comments" rows="3" class="custom-scroll" disabled="true"><c:out value="${objcallcenter.comments}"/></textarea>
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                            </div>

                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </c:if>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Ticket History </a></h4>
                                        </div>
                                        <div id="collapseThree" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <form:form class="smart-form" id="caseupdated" novalidate="novalidate" method="POST" action="${pageContext.request.contextPath}/case/updated" commandName="caseupdated" enctype="multipart/form-data">
                                                    <form:input type="hidden" path="caseId" name="caseId" value="${caseDetails.caseId}"/>
                                                    <form:input  path="hempid" type="hidden" name="employeeId" placeholder="Assignee" id="employeeId"/><i></i>
                                                    <form:input  path="oldemployeeID" type="hidden" name="oldemployeeID" placeholder="Assignee" id="oldemployeeID" value="${caseupdated.hempid}"/>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Date</label>
                                                                <label class="input">
                                                                    <form:input id="datetimepicker" data-date-format="yyyy-mm-dd  HH:ii:ss P" path="hcasedate" cssClass="input-group date"/>
                                                                    <i class="icon-append fa fa-calendar"></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Status Update<samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="statusId" path="hstatusid" items="${statusList}" value=""/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <div class="input">
                                                                <section>
                                                                    <label class="label">Re-Assign Reason <samp style="color: red">*</samp></label>
                                                                    <label class="input">
                                                                        <form:input path="reassignreason" type="text" name="reassignreason" placeholder="Re-Assign Reason" id="reassignreason"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Manual Assign</label>
                                                                <label class="afinity_checkbox">
                                                                    <form:checkbox path="checkbox" value="true" id="checkbox"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Assignee <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="manuslassigneeId1" path="employeeID" items="${assigneeList}"/>
                                                                    <i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Branch <samp style="color: red">*</samp></label>
                                                                <label class="select" >
                                                                    <form:select id="hbranchid" path="hbranchid" onchange="assigneeList()" items="${branchList}" /> 
                                                                    <i></i> 
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">BR/MF  <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="hbrmf" path="hbrmf" onchange="assigneeList()" items="${caseCategoryList}"  /> 
                                                                    <i></i> 
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <div class="input">
                                                                <section>
                                                                    <label class="label">Assignee <samp style="color: red">*</samp></label>
                                                                    <label class="input">
                                                                        <form:input path="hempname"  type="text" name="assigneeId1" placeholder="Assignee" id="assigneeId1" readonly="true"/><i></i>
                                                                    </label>
                                                                </section>                                  
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <label class="label">Description / Resolution</label>
                                                                <label class="textarea"> 
                                                                    <form:textarea path="hcomment" rows="3" class="custom-scroll"  id="resolutionDescription" placeholder="Description" name="resolutionDescription"></form:textarea> 
                                                                    <%--<c:out value="${caseDetails.hcomment}"/>--%>
                                                                </label>
                                                            </section> 
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <label class="label">File Upload</label>
                                                                <div id="file_continer">
                                                                    <div class="row no-margin file_row" style="padding-bottom: 2px;">
                                                                        <div class="col-xs-5">
                                                                            <div class="input-group">
                                                                                <label class="input-group-btn" >
                                                                                    <span class="btn btn-primary upload_button">
                                                                                        <p class="upload_button_text">Browse&hellip;</p> <input type="file" name="files[0]" class="file_clear file_size" style="display: none;"/>
                                                                                    </span>
                                                                                </label>
                                                                                <input type="text" class="form-control file_clear_text" readonly>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-xs-1"></div>
                                                                        <div class="col-xs-1">
                                                                            <a href="javascript:void(0);" class="btn btn-sm btn-default pull-right file_clear_btn"><i class="fa fa-fw fa-refresh"></i></a>
                                                                        </div>
                                                                        <div class="col-xs-3" style="text-align: center; height: 36px;">
                                                                            <span class="file_error_message" style="color: #ff1700; display: inline-block; vertical-align: middle; line-height: normal; padding-top: 6px;"></span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <section>
                                                                <div class="row no-margin">
                                                                    <div class="col-xs-5">
                                                                        <input id="addFile" class="form-control btn-default" type="button" value="Add Multiple Files" />
                                                                        <div class="note">
                                                                            <strong>Hint</strong>  Max upload file 5MB
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-8">
                                                            <footer>
                                                                <form:button type="submit" disabled="${avn_create}"  class="btn btn-primary">
                                                                    Save
                                                                </form:button>
                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                </form:form>
                                                <br/><br/>
                                                <div class="jarviswidget jarviswidget-color-darken" id="wid-id-990" data-widget-editbutton="false">
                                                    <header>
                                                        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                                        <h2>Ticket History</h2>
                                                    </header>
                                                    <!-- widget div-->
                                                    <div>
                                                        <!-- widget content -->
                                                        <div class="widget-body no-padding">
                                                            <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                                                <thead>			                
                                                                    <tr>
                                                                        <th>History id</th>
                                                                        <th><i class="icon-append fa fa-calendar"> </i> Date</th>
                                                                        <th>Branch</th>
                                                                        <th>BR/MF</th>
                                                                        <th>Assignee</th>
                                                                        <th>Status</th>
                                                                        <th>Resolution</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var = "caseHistory" items = "${caseHistoryList}">
                                                                        <tr>
                                                                            <td>${caseHistory.caseHistoryId}</td>
                                                                            <td>${caseHistory.affectedDate}</td>
                                                                            <td>${caseHistory.hbranchDes}</td>
                                                                            <td>${caseHistory.hbrmf}</td>
                                                                            <td>${caseHistory.hempname}</td>
                                                                            <td>${caseHistory.statusDes}</td>
                                                                            <td>${caseHistory.resolutionDescription}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <!-- end widget content -->
                                                    </div>
                                                    <!-- end widget div -->
                                                </div>
                                                <!-- end widget -->											
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end widget content -->
                        </div>
                        <!-- end widget div -->
                    </article>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <footer style="background-color: #ffffff; padding-right: 15px; padding-bottom: 50px; padding-top: 15px;">
                            <a id="back_btn" type="button" class="btn btn-primary pull-right" href="${pageContext.servletContext.contextPath}/case/search">Back to Search</a>
                        </footer>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT -->

        <!-- PAGE FOOTER -->
        <div class="page-footer">
            <jsp:include page="../template/footer.jsp"/>
        </div>
        <!-- END PAGE FOOTER -->

        <jsp:include page="../template/jsinclide.jsp"/>

        <script type="text/javascript">

            // DO NOT REMOVE : GLOBAL FUNCTIONS!

            $(document).ready(function () {
                $('#datetimepicker').datetimepicker('setDate', new Date());
                $('#datetimepicker').datetimepicker('setStartDate', new Date());
                $('#manuslassigneeId1').attr('disabled', 'disabled');
                $('#reassignreason').attr('disabled', 'disabled');
                $('#checkbox').change(function () {
                    $("#manuslassigneeId1").rules("remove");
                    $("#manuslassigneeId1").get(0).selectedIndex = 0;
                    //                $("#caseCategory").get(0).selectedIndex = 0;
                    $('#branchcode').removeAttr('disabled');
                    $('#caseCategory').removeAttr('disabled');
                    if ($('#checkbox').is(':checked')) {
                        $('#manuslassigneeId1').removeAttr('disabled');
                        $('#employeeId').val('');
                        $('#assigneeId1').val('');
                        $("#manuslassigneeId1").rules("add", {
                            required: true
                        });
                    } else {
                        $('#branchcode').removeAttr('disabled');
                        $('#manuslassigneeId1').attr('disabled', 'disabled');
                        $('#employeeId').val('');
                        $("#manuslassigneeId1").rules("remove");
                    }
                    $('#manuslassigneeId1').valid();
                });

                $('#statusId').change(function () {
                    var value = $('#statusId').val();
                    var oldempid = $('#oldemployeeID').val();
                    var newempid = $('#employeeId').val();
                    console.log("value" + value);
                    console.log("oldempid" + oldempid);
                    console.log("newempid" + newempid);
                    reassignassigne();


                });

                $('#manuslassigneeId1').change(function () {
                    $('#employeeId').val($('#manuslassigneeId1').val());
                    var selectitem = $("#manuslassigneeId1 :selected").text();
                    var array = selectitem.split('-');
                    console.log(array);
                    console.log(array[0]);
                    $('#assigneeId1').val(array[0]);
                    reassignassigne();
                });
                var value = $('#statusId').val();
                if (value === '6' || value === '5') {
                    $("#reassignreason").removeAttr("disabled");
                } else {
                    //                $("#reassignreason").rules("remove");
                    $('#reassignreason').attr('disabled', 'disabled');
                    $("#reassignreason").val("");
                }


                $('#caseupdated').validate({
                    rules: {
                        affectedDate: {
                            required: true
                        },
                        resolutionDescription: {
                            maxlength: 1024
                        },
                        hbrmf: {
                            required: true
                        },
                        reassignreason: {
                            required: true
                        },
                        hbranchid: {
                            required: true
                        },
                        hstatusid: {
                            required: true
                        }

                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }
                });

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

                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };

                $('#dt_basic').dataTable({
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
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
                    }
                });

                /* END BASIC */

            });
            function caseAttachFileSelect() {
                $('#upload_file').parent().siblings().val($('#upload_file').val());
            }

        </script>
        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>


        <script>

            function assigneeList() {
                var branchcode = $('#hbranchid').val();
                var caseCategory = $('#hbrmf').val();
                console.log("Casecategory" + caseCategory);
                console.log("branchcode" + branchcode);
                if (!$('#checkbox').is(':checked')) {
                    if (branchcode && caseCategory) {

                        var dataObject = new Object();
                        dataObject.branchcode = branchcode;
                        dataObject.caseCategory = caseCategory;
                        var content = JSON.stringify(dataObject);

                        $.ajax({
                            async: false,
                            type: "GET",
                            url: "${pageContext.servletContext.contextPath}/case/assignee1",
                            cache: false,
                            data: {case: content},
                            success: function (response) {
                                console.log(response);
                                $('#assigneeId1').val(response);
                            },
                            error: function () {
                                console.log('Error while request..');
                            }

                        });

                        $.ajax({
                            async: false,
                            type: "GET",
                            url: "${pageContext.servletContext.contextPath}/case/employeeid",
                            cache: false,
                            data: {case: content},
                            success: function (response) {
                                console.log(response);
                                $('#employeeId').val(response);
                            },
                            error: function () {
                                console.log('Error while request..');
                            }

                        });
                        reassignassigne();
                    }
                }
            }
            function reassignassigne() {
                var value = $('#statusId').val();
                var oldempid = $('#oldemployeeID').val();
                var newempid = $('#employeeId').val();
                if (value === '6' || value === '5') {
                    $("#reassignreason").removeAttr("disabled");
                    $('#reassignreason').rules("add", {
                        required: true
                    });

                } else {
                    $("#reassignreason").rules("remove");
                    $('#reassignreason').attr('disabled', 'disabled');
                    $("#reassignreason").val("");
                }

            }


            function removeAttachment() {
                var file_location = $('#file_location').val();
                var caseId = $('#caseId').val();
                console.log(file_location);
                console.log(caseId);
                var dataObject = new Object();

                dataObject.file_location = file_location;
                dataObject.caseId = caseId;
                var content = JSON.stringify(dataObject);
                console.log(content);
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/case/removeattachment",
                    cache: false,
                    data: {case: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        console.log(response);
                        if (response.CODE === "SUCCESS") {
                            $('#filename').val("");
                            $('#attachment').hide();
                            $('#removeAttachmentbutton').hide();
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Attachment Sucessfully Deleted</div> <br/>');
                        }
                        else {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong>Error Attachment Deleting</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    },
                    error: function () {
                        console.log('Error while request..');
                    }

                });

            }

            $('#addFile').click(function () {
                var fileIndex = $('#file_continer .col-xs-5').children().length;
                $('#file_continer').append('<div class = "row no-margin file_row" style="padding-bottom: 2px;">'
                        + '<div class="col-xs-5">'
                        + '<div class="input-group">'
                        + '<label class="input-group-btn" >'
                        + '<span class="btn btn-primary upload_button">'
                        + '<p class="upload_button_text">Browse&hellip;</p> <input type="file" name="files[' + fileIndex + ']" class="file_size" style="display: none;"/>'
                        + '</span>'
                        + '</label>'
                        + '<input type="text" class="form-control" readonly>'
                        + '</div>'
                        + '</div>'
                        + '<div class="col-xs-1"></div>'
                        + '<div class="col-xs-1">'
                        + '<a href="javascript:void(0);" class="btn btn-sm btn-default pull-right file_remove_btn"><i class="fa fa-fw fa-remove"></i></a>'
                        + '</div>'
                        + '<div class="col-xs-3" style="text-align: center; height: 36px;">'
                        + '<span class="file_error_message" style="color: #ff1700; display: inline-block; vertical-align: middle; line-height: normal; padding-top: 6px;">'
                        + '</span>'
                        + '</div>'
                        + '</div>');
                $('.file_remove_btn').click(function () {
                    $(this).closest('.file_row').remove();
                });

                $(':file').on('fileselect', function (event, numFiles, label) {
                    var input = $(this).parents('.input-group').find(':text'),
                            log = numFiles > 1 ? numFiles + ' files selected' : label;
                    if (input.length) {
                        input.val(log);
                    } else {
                        if (log)
                            alert(log);
                    }
                });

                $('.file_size').change(function () {
                    var element = $(this);
                    if (element.context.files.length > 0) {
                        element.parents().closest('.file_row').find('.file_error_message').empty();
                        console.log('inner');
                        var filesize = element.context.files[0].size;
                        if (!(filesize > 0 && (filesize / 1048576) < 5)) {
                            element.val('');
                            element.closest('.file_clear_text').val('');
                            element.parents().closest('.file_row').find('.file_error_message').text('File size exceeded');
                        }
                    }
                });
            });

            $('.file_size').change(function () {
                var element = $(this);
                element.parents().closest('.file_row').find('.file_error_message').empty();
                console.log('outer');
                var filesize = element.context.files[0].size;
                if (!(filesize > 0 && (filesize / 1048576) < 5)) {
                    element.val('');
                    element.closest('.file_clear_text').val('');
                    element.parents().closest('.file_row').find('.file_error_message').text('File size exceeded');
                }
            });

            $('.file_clear_btn').click(function () {
                $(this).parents().closest('.file_row').find('.file_error_message').text('');
                $('.file_clear_text').val('');
                $('.file_clear').val('');
            });
        </script>

        <script type="text/javascript">
            $('#datetimepicker').datetimepicker({
                //language:  'fr',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1
            });

            //                                                                            $('#datetimepicker').datetimepicker('setDate', new Date());
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
