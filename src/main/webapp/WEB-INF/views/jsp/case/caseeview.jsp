
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li><a href="${pageContext.servletContext.contextPath}/case/search">Ticket Management</a></li><li>View Ticket</li>
                    <!-- end breadcrumb -->

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
                            <i class="fa fa-fw fa-eye"></i> 
                            View Ticket 
                            <span>
                                Ticket Id : ${caseDetails.caseId}
                            </span>
                        </h1>
                    </div>
                </div>

                <div class="row" id="msg_dev">
                    <div class="col-xs-1"></div>
                    <div class="col-xs-10">
                        <c:if test="${not empty successMsg}">
                            <div id="success-alert" class="alert alert-success">
                                <strong>Success!</strong> ${successMsg}
                            </div>
                        </c:if> 

                        <c:if test="${not empty errorMsg}">
                            <div id="alert-danger"  class="alert alert-warning">
                                <strong>Warning!</strong> ${errorMsg}
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
                                <h2></h2>
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
                                    <div class="panel-group smart-accordion-default" id="accordion">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Ticket Details </a></h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <form:form class="smart-form" id="caseAttachment" commandName="caseAttachment" action="${pageContext.servletContext.contextPath}/case/downloadattachment">
                                                        <form:input type="hidden" path="file_location" name="file_location" readonly="true" value="${caseDetails.file_location}"/>
                                                        <form:input type="hidden" path="download_token_value_id" name="download_token_value_id" id="download_token_value_id" />
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
                                                                    <label>Start Date </label>
                                                                    <label class="input">
                                                                        <input type="text" readonly="true" value="${caseDetails.caseDate}">
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
                                                                <section>
                                                                    <label name="title" class="label">Title</label>
                                                                    <label class="input">
                                                                        <input  type="text" readonly="true"  value="${caseDetails.title}" /><i></i>
                                                                    </label>
                                                                </section>
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
                                                                        <label name="phonenumber" class="label"> Telephone</label>
                                                                        <label class="input">
                                                                            <input  type="text" readonly="true" value="${caseDetails.phonenumber}" /><i></i>
                                                                        </label>
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
                                                            <c:if test="${caseDetails.filename != null}">
                                                                <div class="col-xs-2"></div>
                                                                <div class="col-xs-3">
                                                                    <section>
                                                                        <label class="label">Attachment</label>
                                                                        <label class="input">
                                                                            <input type="text" name="filename" readonly="true" value="${caseDetails.filename}">
                                                                        </label>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-2"></div>
                                                            </c:if>
                                                        </div>
                                                        <c:if test="${urllist != null && urllist.size() > 0}">  
                                                            <form id ="Data"  method="post" action="${pageContext.servletContext.contextPath}/" commandName="postData">
                                                                <table id="file_table" class="table table-bordered table-condensed">
                                                                    <thead>			                
                                                                        <tr>
                                                                            <th>File Name</th>
                                                                            <th>Download</th>
                                                                            <th>Remove</th>
                                                                        </tr>
                                                                    <tbody>
                                                                        <c:forEach var = "lsit" items = "${urllist}">
                                                                            <tr>
                                                                                <td><c:out value="${lsit.filename}"/></td>
                                                                                <td><a class="" href="${pageContext.servletContext.contextPath}/case/download?caseid=<c:out value="${lsit.caseid}"/>&fileid=<c:out value="${lsit.docid}"/>&downloadall=false" role="button">Download <i class="fa fa-fw fa-download"></i></a></td>
                                                                                <td><a  href="${pageContext.servletContext.contextPath}/case/removeattchment?caseid=<c:out value="${lsit.caseid}"/>&fileid=<c:out value="${lsit.docid}"/>&downloadall=false" role="button" class="fileDownloadPromise"  id="rmattach">Remove <i class="fa fa-fw fa-trash-o"></i></a></td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </form>
                                                        </c:if>
                                                        <c:if test="${urllist != null && urllist.size() > 1}">
                                                            <div id="download_button" class="row" style="padding-top: 10px;">
                                                                <div class="col-xs-offset-9 col-xs-3"> 
                                                                    <footer style="background-color: #ffffff">
                                                                        <a href="${pageContext.servletContext.contextPath}/case/download?caseid=${caseDetails.caseId}&downloadall=true" class="form-control btn btn-default pull-right" role="button">Download ALL Attachment</a>
                                                                    </footer>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <!--<div id="calllog">-->
                                        <c:if test="${caseDetails.caseCallLogId != null}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Call Log </a></h4>
                                                </div>
                                                <div id="collapseTwo" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <form class="smart-form" >
                                                            <div class="row">
                                                                <div class="col-xs-1"></div>
                                                                <div id="msg_dev" class="col-xs-10">
                                                                    <c:if test="${not empty successMsg}">
                                                                        <div class="alert alert-success">
                                                                            <strong>Success!</strong> ${successMsg} ${caseIdList}
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
                                                                        <label class="label">Preferred Language<samp style="color: red">*</samp></label>
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
                                                                            <label class="label">Callback Date<samp style="color: red">*</samp></label>
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
                                                                            <input type="text" id="telephone" disabled="true" value="${objcallcenter.telephone}"/>
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
                                            <br/>
                                        </c:if>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Ticket History </a></h4>
                                            </div>
                                            <div id="collapseThree" class="panel-collapse collapse">
                                                <div class="panel-body">

                                                    <table class="table table-bordered table-condensed case-update-table">
                                                        <thead>
                                                            <tr>
                                                                <th>History id</th>
                                                                <th>Date</th>
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
                <!-- WIDGET END -->
            </div>
            <!-- end widget -->
            <div class="row">
                <div class="col-xs-12">
                    <footer style="background-color: #ffffff; padding-right: 15px; padding-bottom: 50px;">
                        <a id="back_btn" type="button" class="btn btn-primary pull-right" href="${pageContext.servletContext.contextPath}/case/search">Back to Search</a>
                    </footer>
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

            $(document).on("click", "a.fileDownloadPromise", function () {
                var element = $(this);
                var url = element.attr('path');
                $.ajax({
                    type: "GET",
                    url: url,
                    contentType: "application/json",
                    cache: false,
                    dataType: 'json',
                    success: function (response) {
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="col-xs-1"></div><div class="col-xs-10"><div class="alert alert-success"><strong>Success!</strong> File removed</div><br/></div><div class="col-xs-1"></div>');
                            element.closest('tr').remove();
                            if ($('#file_table > tbody > tr').length === 1) {
                                $('#download_button').remove();
                            }
                        } else {
                            $('#msg_dev').html('<div class="col-xs-1"></div><div class="col-xs-10"><div class="alert alert-warning"><strong>Warning!</strong> Error while removing file</div><br/></div><div class="col-xs-1"></div>');
                        }
                    },
                    error: function () {
                        console.log('Error while request..');
                    }
                });
                return false; //this is critical to stop the click event which will trigger a normal file download
            });

        </script>
    </body>

</html>
