<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

        <aside id="left-panel">
            <jsp:include page="../template/menu.jsp"/>
        </aside>
        <div id="main" role="main">
            <div id="ribbon">
                <div class="row">
                    <div class="col-xs-6">
                        <ol class="breadcrumb">
                            <li>Sales Pipeline</li><li>Dashboard</li>
                        </ol>
                    </div>
                    <div class="col-xs-6">
                        <p style="color: #ffffff; padding-top: 11px" class="text-right"></p>
                    </div>
                </div>

            </div>
            <div id="content">
                <div class="row">
                    <div class="col-xs-9">
                        <label for="select-agent" style="font-size: 11.3px; padding-left: 4px; position: relative; bottom: -15px;">Agent</label>
                    </div>
                    <div class="col-xs-3">
                        <button type="button" id="addcontactmanuwal" class="btn  btn-block add-button" style="transition: 0.4s all; text-transform: uppercase; color: #FFF; border-radius: 5px; padding: 7px 0px; font-size: 11px; text-shadow: 0px 1px 0px rgba(0, 0, 0, 0.2);">Add Contact</button>
                    </div>
                </div>
                <div class="row row-margin-top">
                    <div class="col-xs-9">
                        <div class="col-xs-4 no-padding" style="opacity: 0.7;">
                            <c:choose>
                                <c:when test="${username == 'TRUE'}">
                                    <div style="position: relative">
                                        <select class="form-control sl-cuz" disabled id="select-agent" onchange="SerachFunction()">
                                            <c:forEach var="userDetailList" items="${userDetailList}">
                                                <option value="${userDetailList.key}">${userDetailList.value}</option>
                                            </c:forEach>
                                        </select>
                                        <i class="fa fa-angle-right d_arrow" aria-hidden="true"></i>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div style="position: relative">
                                        <select class="form-control sl-cuz" id="select-agent" onchange="SerachFunction()">
                                            <c:forEach var="userDetailList" items="${userDetailList}">
                                                <option value="${userDetailList.key}">${userDetailList.value}</option>
                                            </c:forEach>
                                        </select>
                                        <i class="fa fa-angle-right d_arrow" aria-hidden="true"></i>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <button type="button" id="add_lead" class="btn btn-block add-button" style="transition: 0.4s all; text-transform: uppercase; color: #FFF; background-color: #668CE0; border-radius: 5px; padding: 7px 0px; font-size: 11px; text-shadow: 0px 1px 0px rgba(0, 0, 0, 0.2);">Add Lead</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-9"></div>
                    <div class="col-xs-3" style="position: relative; opacity: 0.5;">
                        <label for="duration-select" style="font-size: 11.3px; padding-left: 4px;">Display Leads Won and Lost for</label>
                        <i class="fa fa-cog" aria-hidden="true" style="position: absolute; opacity: 0.4; bottom: 5px; left: 18px; font-size: 22px; z-index: 10;"></i>
                        <div style="position: relative">
                            <select class="form-control" id="duration-select" onchange="SerachFunction()" style="padding-left: 32px; -webkit-appearance: none; border: none;">
                                <option value="YEARS">Last Year</option>
                                <option value="QUARTER">Last 3 Months</option>
                                <option value="MONTH">Last Month</option>
                            </select>
                            <i class="fa fa-angle-right d_arrow" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
                <div id="add_lead_append">

                </div>
                <!--div class="row row-margin-top">
                    <div class="col-xs-3 col-delete-padding">
                        <div class="form-group">
                            <label for="sel1">Product</label>
                            <select class="form-control" id="productid-all" onchange="SerachFunction()">
                <%--<c:forEach var="product" items="${productList}">--%>
                    <option value="${product.key}">${product.value}</option>
                <%--</c:forEach>--%>
            </select>
        </div>
    </div>
    <div class="col-xs-3 col-delete-padding">
        <div class="form-group">
            <label for="sel1">Customer Type</label>
            <select class="form-control" id="customer-type" onchange="SerachFunction()">
                <option value="">-- Select --</option>
                <option value="NEW">New Customer</option>
                <option value="EXI">Existing Customer</option>
            </select>
        </div>
    </div>
    <div class="col-xs-3 col-delete-padding">
                <%--<c:choose>--%>
                <%--<c:when test="${username == 'TRUE'}">--%>
                    <div class="form-group">
                        <label for="sel1">Agent</label>
                        <select class="form-control" disabled id="select-agent" onchange="SerachFunction()">
                <%--<c:forEach var="userDetailList" items="${userDetailList}">--%>
                    <option value="${userDetailList.key}">${userDetailList.value}</option>
                <%--</c:forEach>--%>
            </select>
        </div>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <div class="form-group">
                        <label for="sel1">Select Agent</label>
                        <select class="form-control" id="select-agent" onchange="SerachFunction()">
                <%--<c:forEach var="userDetailList" items="${userDetailList}">--%>
                    <option value="${userDetailList.key}">${userDetailList.value}</option>
                <%--</c:forEach>--%>
            </select>
        </div>
                <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            </div>
            <div class="col-xs-3" >
                <div class="form-group">
                    <section>
                        <label for="sel1">Forecasted Date</label>
                        <div class="input-group" >
                            <span class="input-group-addon"><i class="icon-prepend fa fa-calendar"></i></span>
                            <input type="text" class="form-control input-small calender-background" id="for_cast_until_serach"  placeholder="Pick a Date"  onchange="SerachFunction()" readonly="readonly">
                        </div>
                    </section>
                </div>
            </div>
        </div-->
                <div class="row row-margin-top">
                    <div class="col-xs-3 col-delete-padding" >
                        <div class="panel panel-default contact-header" style="border-top-width: thick; border-top-color: #ABA9A9">
                            <div class="row">
                                <div class="col-xs-8">
                                    <div class="panel-heading panel-heading-contact" >Contacts</div>
                                </div>
                                <div class="col-xs-4" >
                                    <div class="panel-heading panel-heading-contact" id="contact_main_header" style="float: right">${CONTACTCOUNT}</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default panel-colour columncontact" style="border: 0px;" >
                            <div class="row" style="margin-left: -8px;margin-right: -8px;" id="load-more-contact-up">
                                <div class="form-group" style="margin-bottom: 0px;margin-top: 0px;margin-left: 8px;margin-right: 8px;">
                                    <input type="text" class="form-control" id="contact-serach" placeholder="Search Contacts">
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-footer panel-footer-text" style="height:39px"></div>
                        </div>
                        <div class="panel panel-default panel-colour columncontact" id="contact-main-div" style="border-width: 0px;">
                            <c:forEach var = "contactlist" items = "${contactlist}">
                                <div id="contact-id-${contactlist.contactid}">
                                    <div class="panel-body cartbody" >
                                        <div class="panel panel-default cart-panel-body-header no-margin" style="border-radius: 5px;">
                                            <div class="panel-body cart-panel-body mousetoHand" >
                                                <div class="row cart-body-row">
                                                    <i class="icon-append fa fa-user"></i>
                                                    <div class="col-xs-10 col-xs-offset-2">
                                                        <p class="cart-description card-name" id="contact-name-${contactlist.contactid}">${contactlist.title} ${contactlist.nameInFull}</p>
                                                        <p class="cart-description job-title" id="contact-jobtitle-${contactlist.contactid}">${contactlist.jobtitle}</p>
                                                        <p class="cart-description mobile-number contact-mobile" id="contact-mobile-${contactlist.contactid}">${contactlist.mobile}</p>

                                                        <div id="lead-collapse-${contactlist.contactid}" class="panel-collapse collapse">
                                                            <div class="row" style = "margin-bottom: 2px;">
                                                                <div class="col-xs-6" style = "margin-right: -16px;">
                                                                    <button type="button" id="${contactlist.contactid}" class="btn btn-block add-button-cart_new dropdown-button view-more-contact"><span class="icon-left"></span>Update</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="view-more-cart">
                                            <a data-toggle="collapse" href="#lead-collapse-${contactlist.contactid}" class="collapsed">
                                                <span id="view-more-less-more-${contactlist.contactid}" style="float: right">
                                                    <i class="fa fa-angle-down view-more-below"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-xs-3 col-delete-padding" >
                        <div class="panel panel-default contact-header" style="border-top-width: thick; border-top-color: #668CE0">

                            <div class="row">
                                <div class="col-xs-8">
                                    <div class="panel-heading panel-heading-contact" >Leads</div>
                                </div>
                                <div class="col-xs-4" >
                                    <div class="panel-heading panel-heading-contact" id="lead_main_header" style="float: right">${LEADCOUNT}</div>
                                </div>
                            </div>

                        </div>
                        <div class="panel panel-default panel-colour columncontact" style="border: 0px;" >
                            <div class="row" style="margin-left: -8px;margin-right: -8px;" id="load-more-contact-up">
                                <div class="form-group" style="margin-bottom: 0px; margin-top: 0px; margin-left: 8px; margin-right: 8px;">
                                    <input type="text" class="form-control" id="lead-serach" placeholder="Search Leads">
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-footer panel-footer-text">Total : LKR <span id="lead-sum"></span></div>
                        </div>
                        <div class="panel panel-default panel-colour columnlead panel-no-box-shadow" id="parent-collapse" style="border: 0px;">
                            <c:forEach var = "leadList" items = "${leadList}">
                                <div class="panel-body cartbody" id="${leadList.leadid}">
                                    <div class="panel panel-default cart-panel-body-header no-margin">
                                        <div class="panel-body cart-panel-body mousetoHand">
                                            <div class="row cart-body-row">
                                                <i class="icon-append fa fa-user"></i>
                                                <div class="col-xs-10 col-xs-offset-2">
                                                    <p class="cart-description card-name">${leadList.title} ${leadList.nameInFull}</p>
                                                    <p class="cart-description job-title">${leadList.jobtitle}</p>
                                                    <p class="cart-description mobile-number">${leadList.mobile}</p>
                                                    <p class="cart-description-price money-price" style="font-size: 13px; margin: 0px; margin-bottom: 10px; color: #888;">LKR ${leadList.leadamount}</p>
                                                    <div id="lead-collapse-${leadList.leadid}" class="panel-collapse collapse">
                                                        <div class="row" style = "margin-bottom: 2px;">
                                                            <div class="col-xs-6" style = "margin-right: -16px;">
                                                                <button type="button" class="btn  btn-block add-button-cart viewcontactinfo dropdown-button" id="${leadList.contactid}"><span class="icon-left"></span>View Contact</button>
                                                            </div>
                                                            <div class="col-xs-6" style = "padding-left: 1px;">
                                                                <button type="button" class="btn  btn-block add-button-cart leadInfoview dropdown-button" id="${leadList.leadid}"><span class="icon-left" ></span>View Lead</button>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-6" style = "margin-right: -16px;">
                                                                <form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >
                                                                    <form:input path="leadid" type="hidden" id="leadid" value="${leadList.leadid}"></form:input>
                                                                    <form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart dropdown-button">
                                                                        <span class="icon-left"></span>Activity History
                                                                    </form:button>
                                                                </form:form>
                                                            </div>
                                                            <div class="col-xs-6" style = "padding-left: 1px;">
                                                                <button type="submit" class="btn  btn-block add-button-cart_new addActivitySalespipline dropdown-button" id="${leadList.leadid}"><span class="icon-left"></span>Sync.</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>               
                                    <div class="view-more-cart">
                                        <a data-toggle="collapse" href="#lead-collapse-${leadList.leadid}" class="collapsed">
                                            <span id="view-more-less-more-${leadList.leadid}" style="float: right">
                                                <i class="fa fa-angle-down view-more-below"></i>
                                            </span>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-xs-3 col-delete-padding" >
                        <div class="panel panel-default contact-header" style="border-top-width: thick; border-top-color: #5CA54A">


                            <div class="row">
                                <div class="col-xs-8">
                                    <div class="panel-heading panel-heading-contact" >Sales Closed</div>
                                </div>
                                <div class="col-xs-4" >
                                    <div class="panel-heading panel-heading-contact" id="closed_main_header" style="float: right">${CLOSEDCOUNT}</div>
                                </div>
                            </div>

                        </div>
                        <div class="panel panel-default panel-colour columncontact" style="border: 0px;" >
                            <div class="row" style="margin-left: -8px; margin-right: -8px;">
                                <div class="form-group" style="margin-bottom: 0px; margin-top: 0px; margin-left: 8px; margin-right: 8px;">
                                    <input type="text" class="form-control" id="closed-serach" placeholder="Search Closed Leads">
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-footer panel-footer-text">Total : LKR <span id="lead-sum-closed"></span></div>
                        </div>
                        <div class="panel panel-default panel-colour column panel-no-box-shadow" id="saleclosed">
                            <c:forEach var = "leadClosedList" items = "${leadClosedList}">
                                <div class="panel-body cartbody" id="${leadClosedList.leadid}">
                                    <div class="panel panel-default cart-panel-body-header no-margin" style="border-radius: 5px;">
                                        <div class="panel-body cart-panel-body mousetoHand">
                                            <div class="row cart-body-row">
                                                <i class="icon-append fa fa-user"></i>
                                                <div class="col-xs-10 col-xs-offset-2">
                                                    <p class="cart-description card-name">${leadClosedList.title} ${leadClosedList.nameInFull}</p>
                                                    <p class="cart-description job-title">${leadClosedList.jobtitle}</p>
                                                    <p class="cart-description mobile-number">${leadClosedList.mobile}</p>
                                                    <p class="cart-description-price money-price">LKR ${leadClosedList.leadamount}</p>
                                                    <p class="cart-description-price days-left"><div class="sl-butz">${leadClosedList.lead_closed_date}</div></p>
                                                    <div id="lead-collapse-${leadClosedList.leadid}" class="panel-collapse collapse">
                                                        <div class="row" style = "margin-bottom: 2px; padding-right: 50px; padding-left: 12px;">
                                                            <div class="col-lg-6 col-md-12 no-padding">
                                                                <button type="button" class="btn  btn-block add-button-cart viewcontactinfo dropdown-button" id="${leadClosedList.contactid}"><span class="icon-left"></span>View Contact</button>
                                                            </div>
                                                            <div class="col-lg-6 col-md-12 no-padding">
                                                                <button type="button" class="btn  btn-block add-button-cart leadInfoview dropdown-button" id="${leadClosedList.leadid}"><span class="icon-left"></span>View Sales</button>
                                                            </div>
                                                            <div class="col-lg-6 col-md-12 no-padding">
                                                                <form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >
                                                                    <form:input path="leadid" type="hidden" id="leadid" value="${leadClosedList.leadid}"></form:input>
                                                                    <form:button id="createbutton" type="submit" class="btn btn-block add-button-cart dropdown-button" >
                                                                        <span class="icon-left"></span>Activity History
                                                                    </form:button>
                                                                </form:form>
                                                            </div>
                                                            <div class="col-lg-6 col-md-12 no-padding">
                                                                <form:form id="accountForm" commandName="accountForm" action="${pageContext.request.contextPath}/salespipline/customer/update" method="POST" >
                                                                    <input path="account_id" type="hidden" name="account_id" value="' + value.customer_account_id + '"></input>
                                                                    <div>
                                                                        <form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart_new addActivitySalespipline dropdown-button" value="Submit"><span class="icon-left"></span>Sync.
                                                                        </form:button>
                                                                    </div>
                                                                </form:form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="view-more-cart">
                                        <a data-toggle="collapse" data-parent="#parent-collapse" href="#lead-collapse-${leadClosedList.leadid}" class="collapsed">
                                            <span id="view-more-less-more-${leadClosedList.leadid}" style="float: right">
                                                <i class="fa fa-angle-down view-more-below"></i>
                                            </span>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>


                    </div>
                    <div class="col-xs-3" >
                        <div class="panel panel-default contact-header" style="border-top-width: thick; border-top-color: #CF3C43">


                            <div class="row">
                                <div class="col-xs-8">
                                    <div class="panel-heading panel-heading-contact" >Leads Lost</div>
                                </div>
                                <div class="col-xs-4" >
                                    <div class="panel-heading panel-heading-contact" id="lost_main_header" style="float: right">${LOSTCOUNT}</div>
                                </div>
                            </div>

                        </div>
                        <div class="panel panel-default panel-colour columncontact" style="border: 0px;" >
                            <div class="row" style="margin-left: -8px;margin-right: -8px;">
                                <div class="form-group" style="margin-bottom: 0px;margin-top: 0px;margin-left: 8px;margin-right: 8px;">
                                    <input type="text" class="form-control" id="lost-serach" placeholder="Search Lost Leads">
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-footer panel-footer-text">Total : LKR <span id="lead-sum-lost"></span></div>
                        </div>
                        <div class="panel panel-default panel-colour column_oppotunity_lost panel-no-box-shadow"  id="oppotunity_lost">
                            <c:forEach var = "leadLost" items = "${leadLost}">
                                <div class="panel-body cartbody" id="${leadLost.leadid}">
                                    <div class="panel panel-default cart-panel-body-header no-margin" style="border-radius: 5px;">
                                        <div class="panel-body cart-panel-body mousetoHand">
                                            <div class="row cart-body-row">
                                                <i class="icon-append fa fa-user"></i>
                                                <div class="col-xs-10 col-xs-offset-2">
                                                    <p class="cart-description card-name">${leadLost.title} ${leadLost.nameInFull}</p>
                                                    <p class="cart-description job-title">${leadLost.jobtitle}</p>
                                                    <p class="cart-description mobile-number">${leadLost.mobile}</p>
                                                    <p class="cart-description-price money-price">LKR ${leadLost.leadamount}</p>
                                                    <p class="cart-description-price days-left"><div class="sl-butz-leads">${leadLost.lead_lost_date}</div></p>
                                                    <div id="lead-collapse-${leadLost.leadid}" class="panel-collapse collapse">
                                                        <div class="row" style = "margin-bottom: 2px; padding-right: 50px; padding-left: 12px;">
                                                            <div class="col-lg-6 col-md-12 no-padding">
                                                                <button type="button" class="btn btn-block add-button-cart viewcontactinfo dropdown-button" id="${leadLost.contactid}"><span class="icon-left"></span>View Contact</button>
                                                            </div>
                                                            <div class="col-lg-6 col-md-12 no-padding">
                                                                <button type="button" class="btn btn-block add-button-cart leadInfoview dropdown-button" id="${leadLost.leadid}"><span class="icon-left"></span>View Lead</button>
                                                            </div>
                                                            <div class="col-lg-6 col-md-12 no-padding">
                                                                <form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >
                                                                    <form:input path="leadid" type="hidden" id="leadid" value="${leadLost.leadid}"></form:input>
                                                                    <form:button id="createbutton" type="submit" class="btn btn-block add-button-cart add-button-cart_new dropdown-button">
                                                                        <span class="icon-left"></span>Activity History
                                                                    </form:button>
                                                                </form:form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="view-more-cart">
                                        <a  data-toggle="collapse" data-parent="#parent-collapse" href="#lead-collapse-${leadLost.leadid}" class="collapsed">
                                            <span id="view-more-less-more-${leadLost.leadid}">
                                                <i class="fa fa-angle-down view-more-below"></i>
                                            </span>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </div>
            </div>


            <!-- Modal -->
            <div class="modal fade" id="contactInfo" role="dialog" >
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header modal-contact-header">

                            <h4 class="modal-title" id="contactHeder">View Contact Information</h4>

                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="contactEditForm">
                                        <input type="hidden" id="con_edit_contact_id">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <section>
                                                        <label >Name in Full<samp style="color: red">*</samp></label>
                                                        <input type="text"  class="form-control" id="con_edit_name_in_full" name="con_edit_name_in_full" placeholder="Name in Full" maxlength="100" disabled="true">
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <section>
                                                        <label >Job Title<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_edit_jobtitle" name="con_edit_jobtitle" placeholder="Job Title" disabled="true">
                                                    </section>
                                                </div>
                                            </div>
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <section>
                                                        <label >Employer<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_edit_employer" name="con_edit_employer" placeholder="Employer" maxlength="20" disabled="true">
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <section>
                                                        <label >Email<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_edit_email" name="con_edit_email" placeholder="Email" disabled="true">
                                                    </section>
                                                </div>
                                            </div>
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <section>
                                                        <label >Mobile<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_edit_mobile" name="con_edit_mobile" placeholder="Mobile" maxlength="20" disabled="true">
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" id="con_edit_footer">
                            <button type="button" class="btn btn-default hgjgj" onclick="ContactEditFunction()">Edit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal fade" id="modal_opptunity_lost_update" role="dialog">
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header modal-lead-lost-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>

                                </button>
                                <h4 class="modal-title">Update Lost Lead</h4>

                            </div>
                            <div class="modal-body">
                                <form role="form" id="lostFormUpdate">
                                    <input type="hidden" id="lost_edit_lead_id">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <div class="form-group">
                                                <label for="sel1">Select list:</label>
                                                <select class="form-control" id="opportunitylostresonsidupdate" name="opportunitylostresonsidupdate">
                                                    <c:forEach var="Opportunitylist" items="${Opportunitylist}">
                                                        <option value="${Opportunitylist.key}">${Opportunitylist.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-default" id="opportunity_lost_update">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="leadInfoLead" role="dialog" >
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header modal-lead-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title" id="leadInfoHeader_lead">View Lead Information</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="editLeadFrom_lead">
                                        <input type="hidden" id="edit_lead_id_lead">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <section>
                                                        <label for="productid">Product</label>
                                                        <select class="form-control" id="edit_productid_lead" name="edit_productid_lead" disabled="true">
                                                            <c:forEach var="product" items="${productList}">
                                                                <option value="${product.key}">${product.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label for="amount">Amount</label>
                                                        <input type="text" class="form-control" id="edit_amount_lead" name="edit_amount_lead" placeholder="Amount" disabled="true"/>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label>Account Count</label>
                                                        <input type="text" class="form-control" id="edit_account_count" name="edit_account_count" placeholder="Account Count" disabled="true"/>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label id="closed_date">Forecasted Date</label>
                                                        <div class="input-group">

                                                            <input type="text" class="form-control input-small calender-background" id="edit_for_cast_until_lead" name="edit_for_cast_until_lead" placeholder="Pick a Date"  disabled="true" readonly="readonly"/>
                                                            <span class="input-group-addon"><i class="icon-prepend fa fa-calendar"></i></span>
                                                        </div>
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" id="lead_edit_footer_lead">
                            <button type="button" class="btn btn-default" onclick="LeadEditFunction()">Edit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal fade" id="leadInfoSalesClosed" role="dialog" >
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header modal-lead-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title" id="leadInfoHeader_sales_closed">View Lead Information</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="editLeadFrom_sales_closed">
                                        <input type="hidden" id="edit_lead_id_sales_closed">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <section>
                                                        <label for="productid">Product</label>
                                                        <select class="form-control" id="edit_productid_sales_closed" name="edit_productid_sales_closed" disabled="true">
                                                            <c:forEach var="product" items="${productList}">
                                                                <option value="${product.key}">${product.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label for="amount" id="closed_amount">Lead Amount</label>
                                                        <input type="text" class="form-control" id="edit_amount_sales_closed" name="edit_amount_sales_closed" placeholder="Amount" disabled="true">
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label for="amount" id="closed_amount">Lead Closed Amount</label>
                                                        <input type="text" class="form-control" id="edit_confirm_amount_sales_closed" name="edit_confirm_amount_sales_closed" placeholder="Amount" disabled="true">
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label>Lead Account Count</label>
                                                        <input type="text" class="form-control" id="edit_account_count_closed" name="edit_account_count_closed" placeholder="Lead Account Count" disabled="true"/>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label>Lead Closed Account Count</label>
                                                        <input type="text" class="form-control" id="edit_account_count_sales_closed" name="edit_account_count_sales_closed" placeholder="Lead Closed Account Count" disabled="true"/>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label id="closed_date">Closed Date</label>
                                                        <div class="input-group">

                                                            <input type="text" class="form-control input-small calender-background" id="edit_for_cast_until_sales_closed" name="edit_for_cast_until_sales_closed" placeholder="Pick a Date"  disabled="true" readonly="readonly">
                                                            <span class="input-group-addon"><i class="icon-prepend fa fa-calendar"></i></span>
                                                        </div>
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" id="lead_edit_footer_sales_closed">
                            <button type="button" class="btn btn-default" onclick="LeadEditFunction()">Edit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal fade" id="leadInfoLost" role="dialog" >
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header modal-lead-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title" id="leadInfoHeader_lost">View Lead Information</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="editLeadFrom_lost">
                                        <input type="hidden" id="edit_lead_id_lost">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <section>
                                                        <label for="productid">Product</label>
                                                        <select class="form-control" id="edit_productid_lost" name="edit_productid_lost" disabled="true">
                                                            <c:forEach var="product" items="${productList}">
                                                                <option value="${product.key}">${product.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label for="amount" id="closed_amount">Lead Amount</label>
                                                        <input type="text" class="form-control" id="edit_amount_lost" name="edit_amount_lost" placeholder="Amount" disabled="true">
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label>Account Count</label>
                                                        <input type="text" class="form-control" id="edit_account_lost_count" name="edit_account_lost_count" placeholder="Account Count" disabled="true"/>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label >Lost Date</label>
                                                        <div class="input-group">

                                                            <input type="text" class="form-control input-small calender-background" id="edit_for_cast_until_lost" name="edit_for_cast_until_lost" placeholder="Pick a Date"  disabled="true" readonly="readonly">
                                                            <span class="input-group-addon"><i class="icon-prepend fa fa-calendar"></i></span>
                                                        </div>
                                                    </section>
                                                </div>
                                                <div class="form-group">
                                                    <section>
                                                        <label for="sel1">Lost Reason</label>
                                                        <select class="form-control" id="edit_opportunity_lost_resons_id" name="edit_opportunity_lost_resons_id">
                                                            <c:forEach var="Opportunitylist" items="${Opportunitylist}">
                                                                <option value="${Opportunitylist.key}">${Opportunitylist.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" id="lead_edit_footer_lost">
                            <button type="button" class="btn btn-default" onclick="LeadEditFunction()">Edit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal fade" id="addContactModal" role="dialog" >
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header modal-contact-header">
                                <button type="button" class="close" data-dismiss="modal" onclick="clearContactFields()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>

                                </button>
                                <h4 class="modal-title" id="myModalLabel">Create Contact</h4>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="row">
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-8 " id="errorMsgContact" hidden="" style="color: red">Contact number or email cannot be duplicated</div>
                                            <div class="col-xs-2"></div>
                                        </div>
                                        <form role="form" id="addContactForm">
                                            <div class="row">
                                                <div class="col-xs-5">
                                                    <div class="form-group">
                                                        <label for="sel1">Title<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="con_title" name="con_title">
                                                            <c:forEach var="titleList" items="${titleList}">
                                                                <option value="${titleList.key}">${titleList.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label >Name in Full<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_name_in_full" name="con_name_in_full" placeholder="Name in Full" maxlength="100">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-5">
                                                    <div class="form-group">
                                                        <label >Job Title<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_jobtitle" name="con_jobtitle" placeholder="Job Title">
                                                    </div>
                                                </div>
                                                <div class="col-xs-2"></div>
                                                <div class="col-xs-5">
                                                    <div class="form-group">
                                                        <label >Employer<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_employer" name="con_employer" placeholder="Employer" maxlength="100">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-5">
                                                    <div class="form-group">
                                                        <label >Email<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_email" name="con_email" placeholder="Email" maxlength="100">
                                                    </div>
                                                </div>
                                                <div class="col-xs-2"></div>
                                                <div class="col-xs-5">
                                                    <div class="form-group">
                                                        <label >Mobile<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="con_mobile" name="con_mobile" placeholder="Mobile" maxlength="10">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-5">
                                                    <div class="form-group">
                                                        <label for="sel1">Dealer<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="con_dealer" name="con_dealer">
                                                            <option value="">-- Select --</option>
                                                            <option value="YES">Yes</option>
                                                            <option value="NO">No</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearContactFields()">Close</button>
                                <button type="button" class="btn btn-default" id="addcontact">Add Contacts</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="myModal-contact">
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header modal-lead-header">
                                <button type="button" class="close" data-dismiss="modal" id="lead-modal-close1"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>

                                </button>
                                <h4 class="modal-title" id="myModalLabel">Create Lead</h4>

                            </div>
                            <div class="modal-body">
                                <form role="form" id="leadCreate">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="form-group">
                                                <section>
                                                    <label for="productid">Product<samp style="color: red">*</samp></label>
                                                    <select class="form-control" id="productid" name="productid">
                                                        <c:forEach var="product" items="${productList}">
                                                            <option value="${product.key}">${product.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </section>
                                            </div>
                                            <div class="form-group">
                                                <section>
                                                    <label for="amount">Amount<samp style="color: red">*</samp></label>
                                                    <input type="text" class="form-control" id="amount" name="amount" placeholder="Amount">
                                                </section>
                                            </div>
                                            <div class="form-group">
                                                <section>
                                                    <label for="amount">Account Count<samp style="color: red">*</samp></label>
                                                    <input type="text" class="form-control" id="account_count" name="account_count" value="1" placeholder="Account Count">
                                                </section>
                                            </div>
                                            <div class="form-group" >
                                                <section>
                                                    <label>Forecasted Date<samp style="color: red">*</samp></label>
                                                    <div class="input-group" id="for_cast_until_input_group">
                                                        <input type="text" class="form-control input-small calender-background" id="for_cast_until" name="for_cast_until" placeholder="Pick a Date" readonly="readonly">
                                                        <span class="input-group-addon"><i class="icon-prepend fa fa-calendar"></i></span>
                                                    </div>
                                                </section>
                                            </div>
                                            <div class="form-group">
                                                <section>
                                                    <label for="productid">Channel<samp style="color: red">*</samp></label>
                                                    <select class="form-control" id="chanelId" name="chanelId">
                                                        <option value="">-- Select --</option>
                                                        <c:forEach var="CHANELDRODOWNLIST" items="${CHANELDRODOWNLIST}">
                                                            <option value="${CHANELDRODOWNLIST.key}">${CHANELDRODOWNLIST.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </section>
                                            </div>
                                            <div id="selectCampaign">
                                                <div class="form-group">
                                                    <section>
                                                        <label for="productid">Campaign<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="champignId" name="champignId">
                                                            <option value="">-- Select --</option>
                                                            <c:forEach var="CAMPIGNDRODOWNLIST" items="${CAMPIGNDRODOWNLIST}">
                                                                <option value="${CAMPIGNDRODOWNLIST.key}">${CAMPIGNDRODOWNLIST.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </section>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label >Promotion Code</label>
                                                <input type="text" class="form-control" id="promationCode" name="promationCode" placeholder="Promotion Code">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" id="lead-modal-close2">Close</button>
                                <button type="button" class="btn btn-default" id="submit_lead">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="myModal-contactmanual">
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header modal-lead-header">
                                <button type="button" class="close" data-dismiss="modal" id="lead-modal-close1"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>

                                </button>
                                <h4 class="modal-title" id="myModalLabel">Create Lead</h4>

                            </div>
                            <div class="modal-body">
                                <form role="form" id="leadCreatemanual">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <c:choose>
                                                <c:when test="${username == 'TRUE'}">
                                                    <div class="form-group">
                                                        <label for="sel1">Agent<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="lead_create_agent" name="lead_create_agent" disabled="true">
                                                            <c:forEach var="userDetailList" items="${userDetailList}">
                                                                <option value="${userDetailList.key}">${userDetailList.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="sel1">Contact<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="selectcontact" name="selectcontact">
                                                            <c:forEach var="userDetailList" items="${CONTACTDRODOWNLIST}">
                                                                <option value="${userDetailList.key}">${userDetailList.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="form-group">
                                                        <label for="sel1">Agent<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="lead_create_agent" name="lead_create_agent">
                                                            <c:forEach var="userDetailList" items="${userDetailList}">
                                                                <option value="${userDetailList.key}">${userDetailList.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="sel1">Contact<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="selectcontact" name="selectcontact">
                                                            <option value="">-- Select --</option>
                                                            <c:forEach var="userDetailList" items="${CONTACTDRODOWNLIST}">
                                                                <option value="${userDetailList.key}">${userDetailList.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="form-group">
                                                <section>
                                                    <label for="productid">Product<samp style="color: red">*</samp></label>
                                                    <select class="form-control" id="productidmanual" name="productidmanual">
                                                        <c:forEach var="product" items="${productList}">
                                                            <option value="${product.key}">${product.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </section>
                                            </div>
                                            <div class="form-group">
                                                <section>
                                                    <label>Amount<samp style="color: red">*</samp></label>
                                                    <input type="text" class="form-control" id="amountmanual" name="amountmanual" placeholder="Amount">
                                                </section>
                                            </div>
                                            <div class="form-group">
                                                <section>

                                                    <label>Forecasted Date<samp style="color: red">*</samp></label>

                                                    <div class="input-group" id="for_cast_until_manual_input_group">
                                                        <input type="text" class="form-control input-small calender-background" id="for_cast_until_manual" name="for_cast_until_manual" placeholder="Pick a Date" readonly="readonly">
                                                        <span class="input-group-addon"><i class="icon-prepend fa fa-calendar"></i></span>
                                                    </div>
                                                </section>
                                            </div>
                                            <div class="form-group">
                                                <section>
                                                    <label for="productid">Channel<samp style="color: red">*</samp></label>
                                                    <select class="form-control" id="chanelIdmanual" name="chanelIdmanual">
                                                        <option value="">-- Select --</option>
                                                        <c:forEach var="CHANELDRODOWNLIST" items="${CHANELDRODOWNLIST}">
                                                            <option value="${CHANELDRODOWNLIST.key}">${CHANELDRODOWNLIST.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </section>
                                            </div>
                                            <div id="selectCampaignmanual">
                                                <div class="form-group">
                                                    <section>
                                                        <label for="productid">Campaign<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="champignIdmanual" name="champignIdmanual">
                                                            <option value="">-- Select --</option>
                                                            <c:forEach var="CAMPIGNDRODOWNLIST" items="${CAMPIGNDRODOWNLIST}">
                                                                <option value="${CAMPIGNDRODOWNLIST.key}">${CAMPIGNDRODOWNLIST.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </section>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>Promotion Code</label>
                                                <input type="text" class="form-control" id="promationCodemanual" name="promationCodemanual" placeholder="Promotion Code">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="createLeadClose()">Close</button>
                                <button type="button" class="btn btn-default" id="submit_lead_manual">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="myModal-opptunity" role="dialog">
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header modal-lead-lost-header">
                                <button type="button" class="close" data-dismiss="modal" id="lead-modal-close-opportunity-1"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>

                                </button>
                                <h4 class="modal-title" id="myModalLabel">Lost Lead</h4>

                            </div>
                            <div class="modal-body">
                                <form role="form" id="lostForm">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <div class="form-group">
                                                <label for="sel1">Select list:</label>
                                                <select class="form-control" id="opportunitylostresonsid" name="opportunitylostresonsid">
                                                    <c:forEach var="Opportunitylist" items="${Opportunitylist}">
                                                        <option value="${Opportunitylist.key}">${Opportunitylist.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <div class="form-group">
                                                <label>Remark:<samp style="color: red">*</samp></label>
                                                <input type="text" class="form-control" id="lead_lost_remark" name="lead_lost_remark"/>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" id="lead-modal-close-opportunity-2">Close</button>
                                <button type="button" class="btn btn-default" id="submit_opportunity-lost">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="myModal-closed" role="dialog">
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header modal-lead-close-header">
                                <button type="button" class="close" data-dismiss="modal" onclick="closedModal()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>

                                </button>
                                <h4 class="modal-title" id="myModalLabel">Create Customer Account</h4>
                            </div>
                            <div id="closed-lead-id-div"></div>
                            <div class="modal-body scroll-co">
                                <form role="form" id="leadCreate"></form>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <form role="form" id="customercategoryform">
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label for="sel1">Expected Lead Amount:</label>
                                                        <input type="hidden" id="leadamount"/>
                                                        <input type="text" class="form-control" value="" id="lead-closed-amount" disabled="true"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label >Actual Lead Amount:<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="actual_lead_amount" name="actual_lead_amount" placeholder="Actual Lead Amount"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label for="sel1">Expected Account Count:</label>
                                                        <input type="hidden" id="leadamount"/>
                                                        <input type="text" class="form-control" id="expected_account_count" disabled="true"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label >Actual Account Count:<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="actual_account_count" name="actual_account_count" value="1" placeholder="Actual Account Count"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label for="sel1">Customer Category:<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="customercategory" name="customercategory">
                                                            <c:forEach var="customerCategoryList" items="${customerCategoryList}">
                                                                <option value="${customerCategoryList.key}">${customerCategoryList.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label >Customer Category Type:<samp style="color: red">*</samp></label>
                                                        <select class="form-control" id="customer_category_type" name="customer_category_type">
                                                            <option value="">-- Select --</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label >Remark:</label>
                                                        <input type="text" class="form-control" id="leadremark" name="leadremark" placeholder="Remark"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <div id="individual" style="display: none">
                                            <form role="form" id="customerindividual">
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <div class="form-group">
                                                            <section>
                                                                <label for="sel1">Title<samp style="color: red">*</samp></label>
                                                                <select class="form-control" id="title" name="title">
                                                                    <c:forEach var="titleList" items="${titleList}">
                                                                        <option value="${titleList.key}">${titleList.value}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </section>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <section>
                                                                <label >Name in Full<samp style="color: red">*</samp></label>
                                                                <input type="text" class="form-control" id="name_in_full" name="name_in_full" placeholder="Name in Full" maxlength="100" style="text-transform:uppercase"/>
                                                            </section>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <div class="form-group">
                                                            <section>
                                                                <label >Initials<samp style="color: red">*</samp></label>
                                                                <input type="text" class="form-control" autocorrect="off" autocapitalize="none" id="initials" name="initials" placeholder="Initials" style="text-transform:uppercase"/>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <div class="form-group">
                                                            <section>
                                                                <label >Last Name<samp style="color: red">*</samp></label>
                                                                <input type="text" class="form-control" autocorrect="off" autocapitalize="none" id="last_name" name="last_name" placeholder="Last Name" maxlength="20" style="text-transform:uppercase"/>
                                                            </section>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <div class="form-group">
                                                            <section>
                                                                <label >Preferred Name<samp style="color: red">*</samp></label>
                                                                <input type="text" class="form-control" autocapitalize="on" id="preferred_name" name="preferred_name" placeholder="Preferred Name" maxlength="20" style="text-transform:uppercase"/>
                                                            </section> 
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <div class="form-group">
                                                            <section>
                                                                <label for="sel1">Date of Birth</label>
                                                                <div class="input-group" id="date_of_birth_input_group">
                                                                    <span class="input-group-addon"><i class="icon-prepend fa fa-calendar"></i></span>
                                                                    <input type="text" class="form-control input-small" id="date_of_birth" name="date_of_birth" placeholder="Date of Birth" readonly="true"/>
                                                                </div>
                                                            </section>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <div class="form-group">
                                                            <section>
                                                                <label for="sel1">Gender<samp style="color: red">*</samp></label>
                                                                <select class="form-control" id="gender" name="gender">
                                                                    <c:forEach var="genderList" items="${genderList}">
                                                                        <option value="${genderList.key}">${genderList.value}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <div class="form-group">
                                                            <section>
                                                                <label >Mother's Maiden Name<!--<samp style="color: red">*</samp>--></label>
                                                                <input type="text" class="form-control" id="mothers_maiden_name" name="mothers_maiden_name" placeholder="Mothers Maden Name" maxlength="100"/>
                                                            </section>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div id="Organization" style="display: none">
                                            <form role="form" id="customerorganization">
                                                <div class="form-group">
                                                    <label >Company Name<samp style="color: red">*</samp></label>
                                                    <input type="text" class="form-control" id="copemployer" name="copemployer" placeholder="Company Name" maxlength="150"/>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-5">
                                                        <div class="form-group">
                                                            <label for="sel1">Sector<samp style="color: red">*</samp></label>
                                                            <select class="form-control" id="copsector" name="copsector">
                                                                <c:forEach var="opsectorList" items="${opsectorList}">
                                                                    <option value="${opsectorList.key}">${opsectorList.value}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-5">
                                                        <div class="form-group">
                                                            <label for="sel1">Sub Sector<samp style="color: red">*</samp></label>
                                                            <select class="form-control" id="copsub_sector_not_assign" name="copsub_sector_not_assign" multiple="true" style="height:100px;">
                                                                <c:forEach var="subsectorNotAssignList" items="${subsectorNotAssignList}">


                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-2" style="margin-top: 40px;text-align: center">
                                                        <section>
                                                            <button id="cop_pull_right" type="button" class="btn btn" style="margin-bottom: 12px;">>></button>
                                                        </section>
                                                        <section>
                                                            <button id="cop_pull_left" type="button" class="btn btn"><<</button>
                                                        </section>
                                                    </div>
                                                    <div class="col-xs-5">
                                                        <div class="form-group">
                                                            <label for="sel1"></label>
                                                            <select class="form-control" id="copsub_sector_assign" multiple="true" style="height:100px;margin-top: 6px;">
                                                                <c:forEach var="subsectorAssignList" items="${subsectorAssignList}">
                                                                    <option value="${subsectorAssignList.key}">${subsectorAssignList.value}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closedModal()">Close</button>
                                <button type="button" class="btn btn-default" id="submit_Lead_closed">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="addActivityModal" role="dialog" >
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">Schedule Activity</h4>
                            </div>
                            <div id="salesActivity"></div>
                            <div class="modal-body">
                                <form role="form" id="leadCreate"></form>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <form role="form" id="addActivityForm">
                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <section>
                                                            <label >Activity Description<samp style="color: red">*</samp></label>
                                                            <input type="text" class="form-control" id="Adescription" name="Adescription" placeholder="Activity Description" maxlength="100">
                                                        </section>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-xs-5">
                                                            <div class="form-group">
                                                                <section>
                                                                    <label for="sel1">Activity Type<samp style="color: red">*</samp></label>
                                                                    <select class="form-control" id="atype" name="atype">
                                                                        <option value="">--Select--</option>
                                                                        <c:forEach var="typelist" items="${typelist}">
                                                                            <option value="${typelist.activitytypeid}">${typelist.activitytype}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </section>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-5">
                                                            <div class="form-group"> 
                                                                <section>
                                                                    <label>Activity Date Time<samp style="color: red">*</samp></label>
                                                                    <div class="input-group date" id="adate_input_group" name="datetimepicker2">
                                                                        <input id="adate" name="adate" type="text" class="form-control calender-background pickernexthide" placeholder="Pick a Date" readonly="readonly"/>
                                                                        <span class="input-group-addon">
                                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                                        </span>
                                                                    </div>
                                                                </section>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div> 
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-default" id="addactivitysales">Add Activity</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <style>
            .vertical-alignment-helper {
                display:table;
                height: 100%;
                width: 100%;
                pointer-events:none;
            }
            .vertical-align-center {
                /* To center vertically */
                display: table-cell;
                vertical-align: middle;
                pointer-events:none;
            }
            .modal-content {
                /* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
                width:inherit;
                height:inherit;
                /* To center horizontally */
                margin: 0 auto;
                pointer-events:all;
            }
        </style>
        <div class="page-footer">
            <jsp:include page="../template/footer.jsp"/>
        </div>

        <jsp:include page="../template/jsinclide.jsp"/>

        <script type="text/javascript">

            $('#adate').datetimepicker('setStartDate', new Date());
            $('.pickerhide').on('change', function () {
                $('.datetimepicker').hide();
            });
            $('#nadate').datetimepicker('setStartDate', new Date());
            $('.pickernexthide').on('change', function () {
                $('.datetimepicker').hide();
            });
            $(document).ready(function () {

                //set lead amou nt
                var lead_a =${SUM};
                $("#lead-sum").append((lead_a.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")) + ".00");

                var lead_c =${SUMCLOSED};
                $("#lead-sum-closed").append((lead_c.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")) + ".00");

                var lead_l =${SUMLOST};
                $("#lead-sum-lost").append((lead_l.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")) + ".00");

                $("#addcontact").click(function (event) {
                    event.preventDefault();
                    if ($('#addContactForm').valid()) {
                        var title = $("#con_title").val();
                        var nameInFull = $("#con_name_in_full").val();
                        var jobtitle = $("#con_jobtitle").val();
                        var employer = $("#con_employer").val();
                        var email = $("#con_email").val();
                        var mobile = $("#con_mobile").val();
                        var isDealer = $("#con_dealer").val();
                        jQuery.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/salespipline/addcontact",
                            data: {title: title, nameInFull: nameInFull, jobtitle: jobtitle, employer: employer, email: email, mobile: mobile, isDealer: isDealer},
                            success: function (res) {
                                res1 = JSON.parse(res);
                                if (res1.CODE == "SUCCESS") {
                                    SerachFunction();
                                    $('#addContactModal').modal('toggle');
                                    $("#errorMsgContact").hide();
                                } else if (res1.CODE == "FAIL") {
                                    $('#addContactModal').val()
                                    $("#errorMsgContact").show();
                                } else {

                                }
                                $("#con_title").val('');
                                $("#con_name_in_full").val('');
                                $("#con_jobtitle").val('');
                                $("#con_employer").val('');
                                $("#con_email").val('');
                                $("#con_mobile").val('');
                                $("#con_dealer").val('');
                            }
                        });
                    }

                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(function () {
                    $('#datetimepicker1').datetimepicker();
                });
                $(function () {
                    $('#datetimepicker2').datetimepicker();
                });
            });
        </script>
        <script>
            var dataObjectFilter = new Object();
            dataObjectFilter.productidAll = '';
            dataObjectFilter.customerType = '';
            dataObjectFilter.selectAgent = $("#select-agent").val();
            dataObjectFilter.forcastUntilSerach = '';
            dataObjectFilter.serachContact = '';
            dataObjectFilter.serachLead = '';
            dataObjectFilter.serachClosed = '';
            dataObjectFilter.serachLost = '';
            function SerachFunction() {

                dataObjectFilter.serachContact = $("#contact-serach").val();
                dataObjectFilter.serachLead = $("#lead-serach").val();
                dataObjectFilter.serachClosed = $("#closed-serach").val();
                dataObjectFilter.serachLost = $("#lost-serach").val();
                dataObjectFilter.selectAgent = $("#select-agent").val();
                dataObjectFilter.forcastUntilSerach = $("#for_cast_until_serach").val();
                dataObjectFilter.customerType = $("#customer-type").val();
                dataObjectFilter.productidAll = $("#productid-all").val();
                dataObjectFilter.durationSelect = $("#duration-select").val();
                var dataObjectFilterJson = JSON.stringify(dataObjectFilter);
                $.ajax({
                    async: true,
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/salespipline/contactserach",
                    cache: false, data: {filterObject: dataObjectFilterJson},
                    success: function (res) {
                        res1 = JSON.parse(res);

                        $("#contact-main-div").empty();
                        $.each(res1[0].CONTACTLIST, function (index, value) {
                            $("#contact-main-div").append(contactListAppend(value));
                        });
                        $("#parent-collapse").empty();
                        $.each(res1[1].LEADLIST, function (index, value) {
                            $('#parent-collapse').append(LeadAppend(value));
                        });
                        $("#saleclosed").empty();
                        $.each(res1[2].CLOSEDLIST, function (index, value) {
                            if ((value.customer_account_id != 0) && (value.contact_status != null) && (value.contact_status == 'INIT')) {
                                $('#saleclosed').append(LeadClosedTrueAppend(value));
                            } else {
                                $('#saleclosed').append(LeadClosedFalseAppend(value));
                            }
                        });
                        $("#oppotunity_lost").empty();
                        $.each(res1[3].LOSTLIST, function (index, value) {
                            $('#oppotunity_lost').append(LeadLostAppend(value));
                        });

                        var le_sum = res1[8].SUM.toString();
                        $('#lead-sum').html(le_sum.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '.00');
                        var le_sum_clo = res1[9].SUMCLOSED.toString();
                        $("#lead-sum-closed").html(le_sum_clo.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '.00');
                        var le_sum_lo = res1[10].SUMLOST.toString();
                        $("#lead-sum-lost").html(le_sum_lo.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '.00');

                        if ($("#contact-serach").val()) {
                            $('#contact_main_header').html(res1[4].CONTACTCOUNT + "/" + res1[11].CONTACTTOTALCOUNT);

                        } else {
                            $('#contact_main_header').html(res1[4].CONTACTCOUNT);
                        }

                        if ($("#lead-serach").val()) {
                            $('#lead_main_header').html(res1[5].LEADCOUNT + "/" + res1[12].LEADTOTALCOUNT);

                        } else {
                            $('#lead_main_header').html(res1[5].LEADCOUNT);
                        }

                        if ($("#closed-serach").val()) {
                            $('#closed_main_header').html(res1[6].CLOSEDCOUNT + "/" + res1[13].CLOSEDTOTALCOUNT);

                        } else {
                            $('#closed_main_header').html(res1[6].CLOSEDCOUNT);
                        }

                        if ($("#lost-serach").val()) {
                            $('#lost_main_header').html(res1[7].LOSTCOUNT + "/" + res1[14].LOSTTOTALCOUNT);
                        } else {
                            $('#lost_main_header').html(res1[7].LOSTCOUNT);
                        }
                        lead_collapse = '';
                        closed_collapse = '';
                        lost_collapse = '';
                    }});
            }

            //onchange select ajent, load select product dropdown
            $("#select-agent").change(function () {
                var ajentObjectFilter = new Object();
                ajentObjectFilter.selectAgent = $("#select-agent").val();
                var dataObjectFilterJson = JSON.stringify(ajentObjectFilter);
                $.ajax({
                    async: true,
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/salespipline/contactserachajent",
                    cache: false, data: {filterObject: dataObjectFilterJson},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $('#productid-all').empty();
                        var mySelect = $('#productid-all');
                        mySelect.append(
                                $('<option></option>').val("").html("--select--")
                                );
                        $.each(res1[0].AGENTPRODUCT, function (index, value) {
                            $('#productid-all').append(ProductAppend(value));
                        });
                    }

                });
            });
            var contact_drag_item_id = '';
            var leadid = '';
            $("#contact-main-div,#parent-collapse").sortable({
                connectWith: "#parent-collapse",
                start: function (event, ui) {
                    contact_drag_item_id = ui.item.attr("id");
                }


            }).disableSelection();
            $("#parent-collapse,#oppotunity_lost,#saleclosed").sortable({
                connectWith: "#oppotunity_lost,#saleclosed",
                start: function (event, ui) {
                    leadid = ui.item.attr("id");
                },
                receive: function (event, ui) {
                    if ($("#select-agent").prop('selectedIndex') == 0) {
                        if ((this.id === "saleclosed") && (ui.sender.attr("id") === "parent-collapse")) {
                            $('#closed-lead-id-div').html('<input type="hidden"  value="' + leadid + '" id="closed-lead-id" />');
                            var validator = $("#customercategoryform").validate();
                            validator.resetForm();
                            var validator = $("#customerorganization").validate();
                            validator.resetForm();
                            var validator = $("#customerindividual").validate();
                            validator.resetForm();
                            $("#actual_lead_amount").val('');
                            $("#customercategory").val('');
                            $('#customer_category_type').empty();
                            $('#customer_category_type').append($("<option></option>").attr("value", '').text('-- Select --'));
                            $("#customer_category_type").val('');
                            $("#title").val('');
                            $("#name_in_full").val('');
                            $("#initials").val('');
                            $("#last_name").val('');
                            $("#date_of_birth").val('');
                            $("#gender").val('');
                            $("#preferred_name").val('');
                            $("#mothers_maiden_name").val('');
                            $("#copemployer").val('');
                            $("#copsector").val('');
                            $("#copsub_sector_not_assign").val('');
                            $("#actual_lead_amount").removeClass("invalid");
                            $("#customercategory").removeClass("invalid");
                            $("#customer_category_type").removeClass("invalid");
                            $("#title").removeClass("invalid");
                            $("#name_in_full").removeClass("invalid");
                            $("#initials").removeClass("invalid");
                            $("#last_name").removeClass("invalid");
                            $("#date_of_birth").removeClass("invalid");
                            $("#gender").removeClass("invalid");
                            $("#preferred_name").removeClass("invalid");
                            $("#mothers_maiden_name").removeClass("invalid");
                            $("#copemployer").removeClass("invalid");
                            $("#copsector").removeClass("invalid");
                            $("#copsub_sector_not_assign").removeClass("invalid");
                            $("#date_of_birth_input_group").removeClass("state-error");
                            $('#Organization').hide();
                            $('#individual').hide();
                            $.ajax({
                                async: true,
                                type: "POST",
                                url: "${pageContext.servletContext.contextPath}/salespipline/getLeadDetailsById",
                                cache: false,
                                data: {leadid: parseInt(leadid)},
                                success: function (res) {
                                    res1 = JSON.parse(res);
                                    var stringamount = res1.amount.toString();
                                    $("#lead-closed-amount").val(stringamount.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                                    $('#leadamount').val(stringamount);
                                    $('#expected_account_count').val(res1.accouncount);
                                    $('#myModal-closed').modal({backdrop: 'static', keyboard: false});
                                    $('#actual_lead_amount').val('');
                                    $('#actual_account_count').val('1');
                                }
                            });
                        } else if ((this.id === "parent-collapse") && (ui.sender.attr("id") === "contact-main-div")) {
                            var validator = $("#leadCreate").validate();
                            validator.resetForm();
                            $("#productid").val('');
                            $("#amount").val('');
                            $("#for_cast_until").val('');
                            $("#account_count").val('');
                            $("#promationCode").val('');
                            $("#chanelId").val('');
                            $("#champignId").val('');
                            $("#productid").removeClass("invalid");
                            $("#amount").removeClass("invalid");
                            $("#chanelId").removeClass("invalid");
                            $("#champignId").removeClass("invalid");
                            $("#for_cast_until").removeClass("invalid");
                            $("#for_cast_until_input_group").removeClass("state-error");
                            $("#selectCampaign").hide();
                            $('#myModal-contact').modal({backdrop: 'static', keyboard: false});
                        } else if ((this.id === "oppotunity_lost") && (ui.sender.attr("id") === "parent-collapse")) {
                            var validator = $("#lostForm").validate();
                            validator.resetForm();
                            $("#opportunitylostresonsid").val('');
                            $("#opportunitylostresonsid").removeClass("invalid");
                            $('#myModal-opptunity').modal({backdrop: 'static', keyboard: false});
                        } else if ((this.id === "oppotunity_lost") && (ui.sender.attr("id") === "saleclosed")) {
                            $("#saleclosed,#oppotunity_lost").sortable('cancel');
                        } else {
                            $("#parent-collapse,#oppotunity_lost").sortable('cancel');
                            $("#contact-main-div,#parent-collapse").sortable('cancel');
                        }
                    } else {
                        $("#contact-main-div,#parent-collapse").sortable('cancel');
                        $("#contact-main-div,#saleclosed").sortable('cancel');
                        $("#contact-main-div,#oppotunity_lost").sortable('cancel');

                        $("#parent-collapse,#contact-main-div").sortable('cancel');
                        $("#parent-collapse,#saleclosed").sortable('cancel');
                        $("#parent-collapse,#oppotunity_lost").sortable('cancel');

                        $("#saleclosed,#contact-main-div").sortable('cancel');
                        $("#saleclosed,#parent-collapse").sortable('cancel');
                        $("#saleclosed,#oppotunity_lost").sortable('cancel');

                        $("#oppotunity_lost,#contact-main-div").sortable('cancel');
                        $("#oppotunity_lost,#parent-collapse").sortable('cancel');
                        $("#oppotunity_lost,#saleclosed").sortable('cancel');
                    }
                }

            }).disableSelection();
            $("#lead-modal-close1").click(function (event) {
                $("#contact-main-div,#parent-collapse").sortable('cancel');
            });
            $("#lead-modal-close2").click(function (event) {
                $("#contact-main-div,#parent-collapse").sortable('cancel');
            });
            $("#lead-modal-close-opportunity-1").click(function (event) {
                $("#parent-collapse,#oppotunity_lost").sortable('cancel');
            });
            $("#lead-modal-close-opportunity-2").click(function (event) {
                $("#parent-collapse,#oppotunity_lost").sortable('cancel');
            });
            $("#submit_lead").click(function (event) {
                event.preventDefault();
                if ($('#leadCreate').valid()) {
                    var dataObject = new Object();
                    dataObject.productid = $("#productid").val();
                    dataObject.date = $("#for_cast_until").val();
                    dataObject.contact_id = (contact_drag_item_id.substring(11));
                    var amount = $("#amount").val();
                    dataObject.amount = amount.replace(/,/g, "");
                    dataObject.accountcount = $("#account_count").val();
                    dataObject.channelId = $("#chanelId").val();
                    dataObject.campignId = $("#champignId").val();
                    dataObject.promationCode = $("#promationCode").val();
                    var content = JSON.stringify(dataObject);
                    jQuery.ajax({
                        async: true,
                        type: "POST",
                        url: "${pageContext.request.contextPath}/salespipline/lead/create",
                        cache: false,
                        data: {lead: content},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                $("#lead-serach").val('');
                                $("#lead-hidden-down").html('<input type="hidden"  value="3" id="lead-count-down" />');
                                $("#lead-hidden-up").html('<input type="hidden"  value="0" id="lead-count-up" />');
                                SerachFunction();
                            }

                            $("#contact-main-div,#parent-collapse").sortable('cancel');
                            $('#myModal-contact').modal('toggle');
                            $("#productid").val('');
                            $("#account_count").val('');
                            $("#for_cast_until").val('');
                            $("#amount").val('');
                        }
                    });
                }

            });
            $("#add_lead").click(function (event) {
                event.preventDefault();
                var validator = $("#leadCreatemanual").validate();
                validator.resetForm();
                $("#productidmanual").val('');
                $("#selectcontact").val('');
                $("#for_cast_until_manual").val('');
                $("#amountmanual").val('');
                $("#promationCodemanual").val('');
                $("#champignIdmanual").val('');
                $("#chanelIdmanual").val('');
                $("#productidmanual").removeClass("invalid");
                $("#amountmanual").removeClass("invalid");
                $("#chanelIdmanual").removeClass("invalid");
                $("#champignIdmanual").removeClass("invalid");
                $("#selectcontact").removeClass("invalid");
                $("#for_cast_until_manual").removeClass("invalid");
                $("#for_cast_until_manual_input_group").removeClass("state-error");
                $("#selectCampaignmanual").hide();
                $('#myModal-contactmanual').modal({backdrop: 'static', keyboard: false});
            });
            $("#addcontactmanuwal").click(function (event) {
                event.preventDefault();
                $('#addContactForm').valid();
                $("#con_title").rules("add", {
                    required: true
                });
                $("#con_name_in_full").rules("add", {
                    required: true,
                    lettersonly: true,
                    minlength: 2
                });
                $("#con_mobile").rules("add", {
                    required: true,
                    number: true,
                    maxlength: 10,
                    minlength: 10
                });
                $("#con_jobtitle").rules("add", {
                    required: true,
                    minlength: 2
                });
                $("#con_employer").rules("add", {
                    required: true, minlength: 2
                });
                $("#con_email").rules("add", {
                    required: true,
                    email: true
                });
                $("#con_dealer").rules("add", {
                    required: true
                });
                $('#addContactModal').modal({backdrop: 'static', keyboard: false});
            });

            $("#submit_Lead_closed").click(function (event) {
                event.preventDefault();
                var categoryid = $("#customercategory").val();
                var dataObject = new Object();
                dataObject.account_category = categoryid;
                dataObject.customer_category_type = $('#customer_category_type').val();
                var leadamount = $('#leadamount').val();
                var amount = $('#actual_lead_amount').val();
                var x = (amount.replace(/,/g, "") / leadamount) * 100;
                if (x < 50 || x > 150) {
                    $("#leadremark").rules("add", {
                        required: true,
                        messages: {
                            required: "Please enter a remark for the tolerance of 50% between Expected and Actual Lead amounts"
                        }
                    });
                } else {
                    $("#leadremark").rules("remove");
                }
                dataObject.actual_lead_amount = amount.replace(/,/g, "");
                dataObject.lead_remark = $("#leadremark").val();
                dataObject.lead_id = $('#closed-lead-id').val();
                dataObject.createUser = $("#select-agent").val();
                dataObject.actualaccountcount = $('#actual_account_count').val();
                if (categoryid == "0") {
                    dataObject.title = $('#title').val();
                    dataObject.name_in_full = $('#name_in_full').val();
                    dataObject.initials = $('#initials').val();
                    dataObject.last_name = $('#last_name').val();
                    dataObject.preferred_name = $('#preferred_name').val();
                    dataObject.date_of_birth = $('#date_of_birth').val();
                    dataObject.gender = $('#gender').val();
                    dataObject.mothers_maiden_name = $('#mothers_maiden_name').val();
                } else if (categoryid == "1" || categoryid == "2") {
                    dataObject.copemployer = $('#copemployer').val();
                    dataObject.copsector = $('#copsector').val();
                    var subsectors = [];
                    $("#copsub_sector_assign option").each(function () {
                        subsectors.push($(this).val());
                    });
                    var subsectorassign = $.parseJSON(JSON.stringify(subsectors));
                    dataObject.copsub_sector_assign = subsectorassign;
                } else {
                }
                var content = JSON.stringify(dataObject);
                //                if ($('#customercategoryform').valid()) {
                if (($('#customerindividual').valid() & $('#customercategoryform').valid()) && categoryid == "0") {
                    $.ajax({
                        async: true,
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/salespipline/create/account",
                        cache: false,
                        data: {corporate: content},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                dataObjectFilter.closedStart = 0;
                                dataObjectFilter.closedEnd = 3;
                                $("#closed-serach").val('');
                                $("#closed-hidden-down").html('<input type="hidden"  value="0" id="closed-count-down" />');
                                $("#closed-hidden-up").html('<input type="hidden"  value="0" id="closed-count-up" />');
                                SerachFunction();
                            }
                            $('#myModal-closed').modal('toggle');
                        }
                    });
                } else if (($('#customerorganization').valid() & $('#customercategoryform').valid()) && (categoryid == "1" || categoryid == "2")) {
                    $.ajax({
                        async: true,
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/salespipline/create/account",
                        cache: false,
                        data: {corporate: content},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                dataObjectFilter.closedStart = 0;
                                dataObjectFilter.closedEnd = 3;
                                $("#closed-serach").val('');
                                $("#closed-hidden-down").html('<input type="hidden"  value="0" id="closed-count-down" />');
                                $("#closed-hidden-up").html('<input type="hidden"  value="0" id="closed-count-up" />');
                                SerachFunction();
                            }
                            $('#myModal-closed').modal('toggle');
                        }
                    });
                }
                //                }

            });
            $("#submit_opportunity-lost").click(function (event) {
                event.preventDefault();
                var opportunityid = $("#opportunitylostresonsid").val();
                if ($('#lostForm').valid()) {
                    jQuery.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/salespipline/lead/update/opportunitylost",
                        data: {leadid: parseInt(leadid), opportunityid: parseInt(opportunityid), remark: $('#lead_lost_remark').val()},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                dataObjectFilter.lostStart = 0;
                                dataObjectFilter.lostEnd = 3;
                                $("#closed-serach").val('');
                                $("#lost-hidden-down").html('<input type="hidden"  value="3" id="lost-count-down" />');
                                $("#lost-hidden-up").html('<input type="hidden"  value="0" id="lost-count-up" />');
                                SerachFunction();
                            }
                            $('#myModal-opptunity').modal('toggle');
                        }
                    });
                }

            });
        </script>
        <script>
            $("#copsector").change(function () {
                var sectorid = $('#copsector').val();
                $('#copsub_sector_not_assign').empty();
                $('#copsub_sector_assign').empty();
                if (sectorid) {
                    $.ajax({
                        async: true,
                        type: "post",
                        url: "${pageContext.request.contextPath}/account/create/subsectorlist",
                        cache: false,
                        data: 'sectorid=' + sectorid,
                        success: function (response) {
                            for (var i = 0; i < response.length; i++) {
                                $('#copsub_sector_not_assign').append('<option value="' + response[i].id + '">' + response[i].description + '</option>');
                            }
                        },
                        error: function () {
                            console.log('Error while request..');
                        }
                    });
                }
            });

            $("#customercategory").change(function () {
                var categoryid = $("#customercategory").val();
                $('#customer_category_type').empty();
                $('#customer_category_type').append($("<option></option>").attr("value", '').text('-- Select --'));
                if (categoryid) {
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/account/create/categorytypelist",
                        cache: false,
                        data: 'category=' + categoryid,
                        success: function (response) {
                            for (var i = 0; i < response.length; i++) {
                                $('#customer_category_type').append($("<option></option>").attr("value", response[i].accountcategorytypeid).text(response[i].description));
                            }
                        },
                        error: function () {
                            console.log('Error while request..');
                        }
                    });
                }
                if (categoryid == "0") {
                    var leadId = $("#closed-lead-id").val();
                    jQuery.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/salespipline/getName",
                        data: {leadid: leadId},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            var name = res1.NAME;
                            var rese = name.split("/");
                            var strsplit = rese[1].split(" ");
                            var lengtharray = strsplit.length;
                            $("#title").val(rese[0]);
                            $("#name_in_full").val(rese[1]);
                            if (lengtharray > 1) {
                                $("#last_name").val(strsplit[lengtharray - 1]);
                            }
                            var addinitial = "";
                            for (i = 0; i < (lengtharray - 1); i++) {
                                var initial = strsplit[i].substring(0, 1);
                                if (i === (lengtharray - 2)) {
                                    addinitial = addinitial + "" + initial
                                } else {
                                    addinitial = addinitial + "" + initial + "."
                                }

                            }
                            $("#initials").val(addinitial);

                        }
                    });
                    $('#individual').show();
                    $('#Organization').hide();
                } else if (categoryid == "1" || categoryid == "2") {
                    $('#Organization').show();
                    $('#individual').hide();
                } else {
                    $('#individual').hide();
                    $('#Organization').hide();
                }

            });

            $('#date_of_birth').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            $('#date_of_birth').datetimepicker('setEndDate', new Date());
            $('#for_cast_until').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            $('#for_cast_until').datetimepicker('setStartDate', new Date());
            $('#edit_for_cast_until_lead').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            $('#edit_for_cast_until_lead').datetimepicker('setStartDate', new Date());
            $('#edit_for_cast_until_sales_closed').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            $('#edit_for_cast_until_sales_closed').datetimepicker('setStartDate', new Date());
            $('#edit_for_cast_until_lost').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            $('#edit_for_cast_until_lost').datetimepicker('setStartDate', new Date());
            $('#for_cast_until_manual').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            $('#for_cast_until_manual').datetimepicker('setStartDate', new Date());
            $('#for_cast_until_serach').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            $('#for_cast_until_serach').datetimepicker('setStartDate', new Date());
            $('#cop_pull_right').click(function () {
                var selectedItem = $("#copsub_sector_not_assign option:selected");
                $("#copsub_sector_assign").append(selectedItem);
                selectedItem.prop("selected", false);
                generateCopSubsectorArray();
            });
            $('#cop_pull_left').click(function () {
                var selectedItem = $("#copsub_sector_assign option:selected");
                $("#copsub_sector_not_assign").append(selectedItem);
                selectedItem.prop("selected", false);
                generateCopSubsectorArray();
            });
        </script>
        <script type="text/javascript">
            var lead_collapse = '';
            var closed_collapse = '';
            var lost_collapse = '';
            $(document).ready(function () {
                $("#parent-collapse").on("click", ".view-more-link", function () {
                    var id = $(this).attr("id");
                    var sub_id = id.substring(14);
                    var new_id = "#" + id;
                    var viewless = "#view-more-less-more-" + sub_id;
                    $(new_id).on("hide.bs.collapse", function () {
                        $(viewless).html('<i class="fa fa-plus"></i>  View More');
                    });
                    $(new_id).on("show.bs.collapse", function () {
                        $(viewless).html('<i class="fa fa-minus"></i>  View Less');
                        if (lead_collapse) {
                            $(lead_collapse).collapse('hide');
                        }
                        lead_collapse = new_id;
                    });
                });

                $("#oppotunity_lost").on("click", ".view-more-link", function () {
                    var id = $(this).attr("id");
                    var sub_id = id.substring(14);
                    var new_id = "#" + id;
                    var viewless = "#view-more-less-more-" + sub_id;
                    $(new_id).on("hide.bs.collapse", function () {
                        $(viewless).html('<i class="fa fa-plus"></i>  View More');
                    });
                    $(new_id).on("show.bs.collapse", function () {
                        $(viewless).html('<i class="fa fa-minus"></i>  View Less');
                        if (lost_collapse) {
                            $(lost_collapse).collapse('hide');
                        }
                        lost_collapse = new_id;
                    });
                });

                $("#saleclosed").on("click", ".view-more-link", function () {
                    var id = $(this).attr("id");
                    var sub_id = id.substring(14);
                    var new_id = "#" + id;
                    var viewless = "#view-more-less-more-" + sub_id;
                    $(new_id).on("hide.bs.collapse", function () {
                        $(viewless).html('<i class="fa fa-plus"></i>  View More');
                    });
                    $(new_id).on("show.bs.collapse", function () {
                        $(viewless).html('<i class="fa fa-minus"></i>  View Less');
                        if (closed_collapse) {
                            $(closed_collapse).collapse('hide');
                        }
                        closed_collapse = new_id;
                    });
                });
            });

            $("body").on("click", ".view-more-contact", function () {
                var id = $(this).attr("id");
                var validator = $("#contactEditForm").validate();
                validator.resetForm();
                $("#con_edit_name_in_full").removeClass("invalid");
                $("#con_edit_jobtitle").removeClass("invalid");
                $("#con_edit_employer").removeClass("invalid");
                $("#con_edit_email").removeClass("invalid");
                $("#con_edit_mobile").removeClass("invalid");
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getContactOne",
                    data: {contactId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $.each(res1.CONTACT, function (index, value) {
                            $("#con_edit_name_in_full").val(value.nameInFull);
                            $("#con_edit_jobtitle").val(value.jobtitle);
                            $("#con_edit_employer").val(value.employer);
                            $("#con_edit_email").val(value.email);
                            $("#con_edit_mobile").val(value.mobile);
                            $("#con_edit_contact_id").val(value.contactid);
                        });
                        document.getElementById("con_edit_name_in_full").disabled = true;
                        document.getElementById("con_edit_jobtitle").disabled = true;
                        document.getElementById("con_edit_employer").disabled = true;
                        document.getElementById("con_edit_email").disabled = true;
                        document.getElementById("con_edit_mobile").disabled = true;
                        document.getElementById("con_edit_contact_id").disabled = true;
                        $("#contactHeder").html('View Contact Information');
                        $('#con_edit_footer').html('<button type="button" class="btn btn-default" onclick="ContactEditFunction()">Edit</button>'
                                + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                        $('#contactInfo').modal({backdrop: 'static', keyboard: false});
                    }
                });
            });

            $("#parent-collapse").on("click", ".viewcontactinfo", function () {
                var id = $(this).attr("id");
                var validator = $("#contactEditForm").validate();
                validator.resetForm();
                $("#con_edit_name_in_full").removeClass("invalid");
                $("#con_edit_jobtitle").removeClass("invalid");
                $("#con_edit_employer").removeClass("invalid");
                $("#con_edit_email").removeClass("invalid");
                $("#con_edit_mobile").removeClass("invalid");
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getContactOne",
                    data: {contactId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $.each(res1.CONTACT, function (index, value) {
                            $("#con_edit_name_in_full").val(value.nameInFull);
                            $("#con_edit_jobtitle").val(value.jobtitle);
                            $("#con_edit_employer").val(value.employer);
                            $("#con_edit_email").val(value.email);
                            $("#con_edit_mobile").val(value.mobile);
                            $("#con_edit_contact_id").val(value.contactid);
                        });
                        $('#contactInfo').modal({backdrop: 'static', keyboard: false});
                        document.getElementById("con_edit_name_in_full").disabled = true;
                        document.getElementById("con_edit_jobtitle").disabled = true;
                        document.getElementById("con_edit_employer").disabled = true;
                        document.getElementById("con_edit_email").disabled = true;
                        document.getElementById("con_edit_mobile").disabled = true;
                        document.getElementById("con_edit_contact_id").disabled = true;
                        $("#contactHeder").html('View Contact Information');
                        $('#con_edit_footer').html('<button type="button" class="btn btn-default" onclick="ContactEditFunction()">Edit</button>'
                                + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                        $('#contactInfo').modal({backdrop: 'static', keyboard: false});
                    }
                });
            });

            $("#parent-collapse").on("click", ".leadInfoview", function () {
                var id = $(this).attr("id");
                var validator = $("#editLeadFrom_lead").validate();
                validator.resetForm();
                $("#edit_productid_lead").removeClass("invalid");
                $("#edit_amount_lead").removeClass("invalid");
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getleadOne",
                    data: {leadId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $.each(res1.CONTACT, function (index, value) {
                            $("#edit_productid_lead").val(value.productid);
                            var amount = value.leadamount;
                            var stringamount = amount.toString();
                            $("#edit_amount_lead").val(stringamount.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '.00');
                            $("#edit_for_cast_until_lead").val(value.forcastuntildate);
                            $("#edit_account_count").val(value.accouncount);
                            $("#edit_lead_id_lead").val(id);
                        });
                        document.getElementById("edit_for_cast_until_lead").disabled = true;
                        document.getElementById("edit_amount_lead").disabled = true;
                        document.getElementById("edit_productid_lead").disabled = true;
                        document.getElementById("edit_account_count").disabled = true;
                        $('#lead_edit_footer_lead').html('<button type="button" class="btn btn-default" onclick="LeadEditFunction()">Edit</button>'
                                + '<button type="button" class="btn btn-default" data-dismiss="modal">Close');
                        $('#leadInfoHeader_lead').html("View Lead Information");
                        $('#leadInfoLead').modal({backdrop: 'static', keyboard: false});
                    }
                });
            });

            $("#saleclosed").on("click", ".viewcontactinfo", function () {
                var id = $(this).attr("id");
                var validator = $("#contactEditForm").validate();
                validator.resetForm();
                $("#con_edit_name_in_full").removeClass("invalid");
                $("#con_edit_jobtitle").removeClass("invalid");
                $("#con_edit_employer").removeClass("invalid");
                $("#con_edit_email").removeClass("invalid");
                $("#con_edit_mobile").removeClass("invalid");
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getContactOne",
                    data: {contactId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $.each(res1.CONTACT, function (index, value) {
                            $("#con_edit_name_in_full").val(value.nameInFull);
                            $("#con_edit_jobtitle").val(value.jobtitle);
                            $("#con_edit_employer").val(value.employer);
                            $("#con_edit_email").val(value.email);
                            $("#con_edit_mobile").val(value.mobile);
                            $("#con_edit_contact_id").val(value.contactid);
                        });
                        document.getElementById("con_edit_name_in_full").disabled = true;
                        document.getElementById("con_edit_jobtitle").disabled = true;
                        document.getElementById("con_edit_employer").disabled = true;
                        document.getElementById("con_edit_email").disabled = true;
                        document.getElementById("con_edit_mobile").disabled = true;
                        document.getElementById("con_edit_contact_id").disabled = true;
                        $("#contactHeder").html('View Contact Information');
                        $('#con_edit_footer').html('<button type="button" class="btn btn-default" onclick="ContactEditFunction()">Edit</button>'
                                + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                        $('#contactInfo').modal({backdrop: 'static', keyboard: false});
                    }
                });
            });

            $("#saleclosed").on("click", ".leadInfoview", function () {
                var id = $(this).attr("id");
                var validator = $("#editLeadFrom_sales_closed").validate();
                validator.resetForm();
                $("#edit_productid_sales_closed").removeClass("invalid");
                $("#edit_amount_sales_closed").removeClass("invalid");
                $("#edit_confirm_amount_sales_closed").removeClass("invalid");
                $("#edit_account_count_closed").removeClass("invalid");
                $("#edit_account_count_sales_closed").removeClass("invalid");
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getleadOne",
                    data: {leadId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $.each(res1.CONTACT, function (index, value) {
                            $("#edit_productid_sales_closed").val(value.productid);
                            var amount = value.leadamount;
                            var stringamount = amount.toString();
                            $("#edit_amount_sales_closed").val(stringamount.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '.00');
                            var confirmamount = value.confirmedamount;
                            var stringconfirmamount = confirmamount.toString();
                            $("#edit_confirm_amount_sales_closed").val(stringconfirmamount.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '.00');
                            $("#edit_for_cast_until_sales_closed").val(value.salecloseddate);
                            $("#edit_account_count_closed").val(value.accouncount);
                            $("#edit_account_count_sales_closed").val(value.actualaccountcount);
                            $("#edit_lead_id_sales_closed").val(id);
                        });
                        document.getElementById("edit_for_cast_until_sales_closed").disabled = true;
                        document.getElementById("edit_amount_sales_closed").disabled = true;
                        document.getElementById("edit_confirm_amount_sales_closed").disabled = true;
                        document.getElementById("edit_productid_sales_closed").disabled = true;
                        document.getElementById("edit_account_count_closed").disabled = true;
                        document.getElementById("edit_account_count_sales_closed").disabled = true;
                        //'<button type="button" class="btn btn-default" onclick="LeadSalesClosedEditFunction()">Edit</button>'
                        $('#lead_edit_footer_sales_closed').html('<button type="button" class="btn btn-default" data-dismiss="modal">Close');
                        $('#leadInfoHeader_sales_closed').html("View Lead Information");
                        $('#leadInfoSalesClosed').modal({backdrop: 'static', keyboard: false});
                    }});
            });

            $("#oppotunity_lost").on("click", ".viewcontactinfo", function () {
                var id = $(this).attr("id");
                var validator = $("#contactEditForm").validate();
                validator.resetForm();
                $("#con_edit_name_in_full").removeClass("invalid");
                $("#con_edit_jobtitle").removeClass("invalid");
                $("#con_edit_employer").removeClass("invalid");
                $("#con_edit_email").removeClass("invalid");
                $("#con_edit_mobile").removeClass("invalid");
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getContactOne",
                    data: {contactId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $.each(res1.CONTACT, function (index, value) {
                            $("#con_edit_name_in_full").val(value.nameInFull);
                            $("#con_edit_jobtitle").val(value.jobtitle);
                            $("#con_edit_employer").val(value.employer);
                            $("#con_edit_email").val(value.email);
                            $("#con_edit_mobile").val(value.mobile);
                            $("#con_edit_contact_id").val(value.contactid);
                        });
                        document.getElementById("con_edit_name_in_full").disabled = true;
                        document.getElementById("con_edit_jobtitle").disabled = true;
                        document.getElementById("con_edit_employer").disabled = true;
                        document.getElementById("con_edit_email").disabled = true;
                        document.getElementById("con_edit_mobile").disabled = true;
                        document.getElementById("con_edit_contact_id").disabled = true;
                        $("#contactHeder").html('View Contact Information');
                        $('#con_edit_footer').html('<button type="button" class="btn btn-default" onclick="ContactEditFunction()">Edit</button>'
                                + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                        $('#contactInfo').modal({backdrop: 'static', keyboard: false});
                    }
                });
            });

            $("#oppotunity_lost").on("click", ".leadInfoview", function () {
                console.log('called');
                var id = $(this).attr("id");
                var validator = $("#editLeadFrom_lost").validate();
                validator.resetForm();
                $("#edit_productid_lost").removeClass("invalid");
                $("#edit_amount_lost").removeClass("invalid");
                $("#edit_opportunity_lost_resons_id").removeClass("invalid");
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getleadOne",
                    data: {leadId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $.each(res1.CONTACT, function (index, value) {
                            $("#edit_productid_lost").val(value.productid);
                            var amount = value.leadamount;
                            var stringamount = amount.toString();
                            $("#edit_amount_lost").val(stringamount.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '.00');
                            $("#edit_for_cast_until_lost").val(value.lostdate);
                            $("#edit_opportunity_lost_resons_id").val(value.lostresoncode);
                            $("#edit_account_lost_count").val(value.accouncount);
                            $("#edit_lead_id_lost").val(id);
                        });
                        document.getElementById("edit_for_cast_until_lost").disabled = true;
                        document.getElementById("edit_amount_lost").disabled = true;
                        document.getElementById("edit_productid_lost").disabled = true;
                        document.getElementById("edit_opportunity_lost_resons_id").disabled = true;
                        document.getElementById("edit_account_count").disabled = true;
                        //'<button type="button" class="btn btn-default" onclick="LeadLostEditFunction()">Edit</button>'
                        $('#lead_edit_footer_lost').html('<button type="button" class="btn btn-default" data-dismiss="modal">Close');
                        $('#leadInfoHeader_lost').html("View Lead Information");
                        $('#leadInfoLost').modal({backdrop: 'static', keyboard: false});
                    }
                });
            });

            $("#parent-collapse").on("click", ".addActivitySalespipline", function () {
                var id = $(this).attr("id");
                var validator = $("#addActivityForm").validate();
                validator.resetForm();
                $("#atype").val('');
                $("#adate").val('');
                $("#Adescription").val('');
                $("#atype").removeClass("invalid");
                $("#adate").removeClass("invalid");
                $("#Adescription").removeClass("invalid");
                $("#adate_input_group").removeClass("state-error");
                $('#salesActivity').html('<input type="hidden" value="' + id + '" id="addActivitySetLeadId">');
                $('#addActivityModal').modal({backdrop: 'static', keyboard: false});
            });</script>
        <script>
            function contactListAppend(value) {
//                var string = '<div id="contact-id-' + value.contactid + '">' + '<div class="panel-body cartbody" >'
//                        + '<div class="panel panel-default cart-panel-body-header" style="border-radius: 5px;">'
//                        + '<div class="panel-body cart-panel-body mousetoHand" >'
//                        + '<div class="row cart-body-row">'
//                        + '<div class="col-xs-2"><i class="icon-append fa fa-user user-icon-border" style="margin-top: 10px;font-size: 24px;color: #EEE"></i></div>' + '<div class="col-xs-10">'
//                        + '<p class="cart-description card-name" id="contact-name-' + value.contactid + '">' + value.title + value.nameInFull + '</p>' + '<p class="cart-description" id="contact-jobtitle-' + value.contactid + '"><i class="icon-append fa fa-suitcase" style="font-size: 15px;margin-top: 10px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + value.jobtitle + '</p><br/>'
//                        + '<p class="cart-description" style="margin-top: -19px;" id="contact-mobile-' + value.contactid + '"><i class="icon-append fa fa-phone" style="font-size: 18px; margin-left: 3px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;' + value.mobile + '</p><br/>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '<div class="view-more-cart">'
//                        + '<a class="view-more-link view-more-contact" data-toggle="modal" data-target="#myModal" id="' + value.contactid + '"><i class="fa fa-plus"></i>  View More</a>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>';

                var string = '<div id="contact-id-' + value.contactid + '">'
                        + ' <div class="panel-body cartbody" >'
                        + ' <div class="panel panel-default cart-panel-body-header no-margin" style="border-radius: 5px;">'
                        + ' <div class="panel-body cart-panel-body mousetoHand" >'
                        + ' <div class="row cart-body-row">'
                        + ' <i class="icon-append fa fa-user"></i>'
                        + ' <div class="col-xs-10 col-xs-offset-2">'
                        + ' <p class="cart-description card-name" id="contact-name-' + value.contactid + '">' + value.title + value.nameInFull + '</p>'
                        + ' <p class="cart-description job-title" id="contact-jobtitle-' + value.contactid + '">' + value.jobtitle + '</p>'
                        + ' <p class="cart-description mobile-number contact-mobile" id="contact-mobile-' + value.contactid + '">' + value.mobile + '</p>'
                        + ' <div id="lead-collapse-' + value.contactid + '" class="panel-collapse collapse">'
                        + ' <div class="row" style = "margin-bottom: 2px;">'
                        + ' <div class="col-xs-6" style = "margin-right: -16px;">'
                        + ' <button type="button" id="' + value.contactid + '" class="btn btn-block add-button-cart_new dropdown-button view-more-contact"><span class="icon-left"></span>Update</button>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' <div class="view-more-cart">'
                        + ' <a data-toggle="collapse" data-parent="#parent-collapse" href="#lead-collapse-' + value.contactid + '" class="collapsed">'
                        + ' <span id="view-more-less-more-' + value.contactid + '" style="float: right">'
                        + ' <i class="fa fa-angle-down view-more-below"></i>'
                        + ' </span>'
                        + ' </a>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>';

                return string;
            }
            function LeadAppend(value) {
//                var string = '<div class="panel-body cartbody" id="' + value.leadid + '">'
//                        + '<div class="panel panel-default cart-panel-body-header" style="border-radius: 5px;">'
//                        + '<div class="panel-body cart-panel-body mousetoHand">'
//                        + '<div class="row cart-body-row">'
//                        + '<div class="col-xs-2"><i class="icon-append fa fa-user user-icon-border" style="margin-top: 10px;font-size: 24px;color: #EEE"></i></div>' + '<div class="col-xs-10">'
//                        + '<p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
//                        + '<p class="cart-description"><i class="icon-append fa fa-suitcase" style="font-size: 15px;margin-top: 10px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + value.jobtitle + '</p>'
//                        + '<p class="cart-description"><i class="icon-append fa fa-phone" style="font-size: 18px;margin-left: 3px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;' + value.mobile + '</p>'
//                        + '<p class="cart-description-price"><i class="icon-append fa fa-money" style="font-size: 16px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp; LKR ' + value.leadamount + '</p>' + '<div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
//                        + '<button type="button" class="btn  btn-block add-button-cart viewcontactinfo" id="' + value.contactid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Contact Info</button>'
//                        + '<button type="button" class="btn  btn-block add-button-cart leadInfoview" id="' + value.leadid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Lead Info</button>'
//                        + '<form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
//                        + '<input path="leadid" type="hidden" name="leadid" value="' + value.leadid + '"></input>'
//                        + '<form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart">'
//                        + '<span class="icon-left"><i class="fa fa-plus"></i></span>View Activity History'
//                        + '</form:button>'
//                        + '</form:form>'
//                        + '<button type="submit" class="btn  btn-block add-button-cart_under addActivitySalespipline" id="' + value.leadid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>Schedule Activity</button>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '<div class="view-more-cart">'
//                        + '<a  class="view-more-link" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-' + value.leadid + '" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '"><span id="view-more-less-more-' + value.leadid + '"><i class="fa fa-plus"></i>  View More</span></a>'
//                        + '</div>'
//                        + '</div>';

                var string = '<div class="panel-body cartbody" id="' + value.leadid + '">'
                        + ' <div class="panel panel-default cart-panel-body-header no-margin">'
                        + ' <div class="panel-body cart-panel-body mousetoHand">'
                        + ' <div class="row cart-body-row">'
                        + ' <i class="icon-append fa fa-user"></i>'
                        + ' <div class="col-xs-10 col-xs-offset-2">'
                        + ' <p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
                        + ' <p class="cart-description job-title">' + value.jobtitle + '</p>'
                        + ' <p class="cart-description mobile-number">' + value.mobile + '</p>'
                        + ' <p class="cart-description-price money-price" style="font-size: 13px; margin: 0px; margin-bottom: 10px; color: #888;">LKR ' + value.leadamount + '</p>'
                        + ' <div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
                        + ' <div class="row" style = "margin-bottom: 2px;">'
                        + ' <div class="col-xs-6" style = "margin-right: -16px;">'
                        + ' <button type="button" class="btn  btn-block add-button-cart viewcontactinfo dropdown-button" id="' + value.contactid + '"><span class="icon-left"></span>View Contact</button>'
                        + ' </div>'
                        + ' <div class="col-xs-6" style = "padding-left: 1px;">'
                        + ' <button type="button" class="btn  btn-block add-button-cart leadInfoview dropdown-button" id="' + value.leadid + '"><span class="icon-left" ></span>View Lead</button>'
                        + ' </div>'
                        + ' </div>'
                        + ' <div class="row">'
                        + ' <div class="col-xs-6" style = "margin-right: -16px;">'
                        + ' <form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
                        + ' <input path="leadid" type="hidden" id="leadid" value="' + value.leadid + '"></input>'
                        + ' <form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart_new dropdown-button">'
                        + ' <span class="icon-left"></span>Activity History'
                        + ' </form:button>'
                        + ' </form:form>'
                        + ' </div>'
                        + ' <div class="col-xs-6" style = "padding-left: 1px;">'
                        + ' <button type="submit" class="btn  btn-block add-button-cart_new addActivitySalespipline dropdown-button" id="' + value.leadid + '"><span class="icon-left"></span>Sync.</button>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>               '
                        + ' <div class="view-more-cart">'
                        + ' <a data-toggle="collapse" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '" class="collapsed">'
                        + ' <span id="view-more-less-more-' + value.leadid + '" style="float: right">'
                        + ' <i class="fa fa-angle-down view-more-below"></i>'
                        + ' </span>'
                        + ' </a>'
                        + ' </div>'
                        + ' </div>';

                return string;
            }
            function LeadClosedTrueAppend(value) {
//                var string = '<div class="panel-body cartbody" id="' + value.leadid + '">'
//                        + '<div class="panel panel-default cart-panel-body-header" style="border-radius: 5px;">'
//                        + '<div class="panel-body cart-panel-body mousetoHand">'
//                        + '<div class="row cart-body-row">'
//                        + '<div class="col-xs-2"><i class="icon-append fa fa-user user-icon-border" style="margin-top: 10px;font-size: 24px;color: #EEE"></i></div>' + '<div class="col-xs-10">'
//                        + '<p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
//                        + '<p class="cart-description"><i class="icon-append fa fa-suitcase" style="font-size: 15px;margin-top: 10px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + value.jobtitle + '</p>'
//                        + '<p class="cart-description"><i class="icon-append fa fa-phone" style="font-size: 18px;margin-left: 3px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;' + value.mobile + '</p>'
//                        + '<p class="cart-description-price"><i class="icon-append fa fa-money" style="font-size: 16px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp; LKR ' + value.leadamount + '</p>' + '<div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
//                        + '<button type="button" class="btn  btn-block add-button-cart viewcontactinfo" id="' + value.contactid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Contact Info</button>'
//                        + '<button type="button" class="btn  btn-block add-button-cart leadInfoview" id="' + value.leadid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Lead Info</button>'
//                        + '<form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
//                        + '<input path="leadid" type="hidden" name="leadid" value="' + value.leadid + '"></input>'
//                        + '<form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart">'
//                        + '<span class="icon-left"><i class="fa fa-plus"></i></span>View Activity History'
//                        + '</form:button>'
//                        + '</form:form>'
//                        + '<form:form id="accountForm" commandName="accountForm" action="${pageContext.request.contextPath}/salespipline/customer/update" method="POST" >'
//                        + '<input path="account_id" type="hidden" name="account_id" value="' + value.customer_account_id + '"></input>'
//                        + '<div>'
//                        + '<form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart_under addActivitySalespipline" value="Submit"><span class="icon-left"><i class="fa fa-plus"></i></span>Update'
//                        + '</form:button>'
//                        + '</div>'
//                        + '</form:form>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '<div class="row" style="margin-top: -15px;">'
//                        + '<div class="col-xs-2"></div>' + '<div class="col-xs-5" style="margin-top: -4px;">'
//                        + '</div>'
//                        + '<div class="col-xs-5 pull-right">' + '<a  class="view-more-link" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-' + value.leadid + '" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '"><span id="view-more-less-more-' + value.leadid + '" style="float: right"><i class="fa fa-plus"></i>  View More</span></a>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>';

                var string = '<div class = "panel-body cartbody" id = "' + value.leadid + '">'
                        + ' <div class="panel panel-default cart-panel-body-header no-margin" style="border-radius: 5px;">'
                        + ' <div class="panel-body cart-panel-body mousetoHand">'
                        + ' <div class="row cart-body-row">'
                        + ' <i class="icon-append fa fa-user"></i>'
                        + ' <div class="col-xs-10 col-xs-offset-2">'
                        + ' <p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
                        + ' <p class="cart-description job-title">' + value.jobtitle + '</p>'
                        + ' <p class="cart-description mobile-number">' + value.mobile + '</p>'
                        + ' <p class="cart-description-price money-price">LKR ' + value.leadamount + '</p>'
                        + ' <p class="cart-description-price days-left"><span class="sl-butz">' + value.lead_closed_date + '</span></p>' //no of days
                        + ' <div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
                        + ' <div class="row" style = "margin-bottom: 2px; padding-right: 50px; padding-left: 12px;">'
                        + ' <div class="col-lg-6 col-md-12 no-padding">'
                        + ' <button type="button" class="btn  btn-block add-button-cart viewcontactinfo dropdown-button" id="' + value.contactid + '"><span class="icon-left"></span>View Contact</button>'
                        + ' </div>'
                        + ' <div class="col-lg-6 col-md-12 no-padding">'
                        + ' <button type="button" class="btn  btn-block add-button-cart leadInfoview dropdown-button" id="' + value.leadid + '"><span class="icon-left"></span>View Sales</button>'
                        + ' </div>'
                        + ' <div class="col-lg-6 col-md-12 no-padding">'
                        + ' <form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
                        + ' <form path="leadid" type="hidden" id="leadid" value="' + value.leadid + '"></input>'
                        + ' <form:button id="createbutton" type="submit" class="btn btn-block add-button-cart dropdown-button" >'
                        + ' <span class="icon-left"></span>Activity History'
                        + ' </form:button>'
                        + ' </form:form>'
                        + ' </div>'
                        + ' <div class="col-lg-6 col-md-12 no-padding">'
                        + ' <form:form id="accountForm" commandName="accountForm" action="${pageContext.request.contextPath}/salespipline/customer/update" method="POST" >'
                        + ' <input path="account_id" type="hidden" name="account_id" value="' + value.customer_account_id + '"></input>'
                        + ' <div>'
                        + ' <form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart_new addActivitySalespipline dropdown-button" value="Submit"><span class="icon-left"></span>Sync.'
                        + ' </form:button>'
                        + ' </div>'
                        + ' </form:form>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' <div class="view-more-cart">'
                        + ' <a data-toggle="collapse" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '" class="collapsed">'
                        + ' <span id="view-more-less-more-' + value.leadid + '" style="float: right">'
                        + ' <i class="fa fa-angle-down view-more-below"></i>'
                        + ' </span>'
                        + ' </a>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>';

                return string;
            }

            function LeadClosedFalseAppend(value) {
                var string = '<div class="panel-body cartbody" id="' + value.leadid + '">'
                        + '<div class="panel panel-default cart-panel-body-header" style="border-radius: 5px;">'
                        + '<div class="panel-body cart-panel-body mousetoHand">'
                        + '<div class="row cart-body-row">'
                        + '<div class="col-xs-2"><i class="icon-append fa fa-user user-icon-border" style="margin-top: 10px;font-size: 24px;color: #EEE"></i></div>' + '<div class="col-xs-10">'
                        + '<p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
                        + '<p class="cart-description"><i class="icon-append fa fa-suitcase" style="font-size: 15px;margin-top: 10px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + value.jobtitle + '</p>'
                        + '<p class="cart-description"><i class="icon-append fa fa-phone" style="font-size: 18px;margin-left: 3px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;' + value.mobile + '</p>'
                        + '<p class="cart-description-price"><i class="icon-append fa fa-money" style="font-size: 16px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp; LKR ' + value.leadamount + '</p>' + '<div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
                        + '<button type="button" class="btn  btn-block add-button-cart viewcontactinfo" id="' + value.contactid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Contact Info</button>'
                        + '<button type="button" class="btn  btn-block add-button-cart leadInfoview" id="' + value.leadid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Lead Info</button>'
                        + '<form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
                        + '<input path="leadid" type="hidden" name="leadid" value="' + value.leadid + '"></input>'
                        + '<form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart">'
                        + '<span class="icon-left"><i class="fa fa-plus"></i></span>View Activity History'
                        + '</form:button>'
                        + '</form:form>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '<div class="view-more-cart">'
                        + '<a  class="view-more-link" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-' + value.leadid + '" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '"><span id="view-more-less-more-' + value.leadid + '"><i class="fa fa-plus"></i>  View More</span></a>'
                        + '</div>'
                        + '</div>';

//                var string = '<div class="panel-body cartbody" id="' + value.leadid + '">'
//                        + ' <div class="panel panel-default cart-panel-body-header no-margin" style="border-radius: 5px;">'
//                        + ' <div class="panel-body cart-panel-body mousetoHand">'
//                        + ' <div class="row cart-body-row">'
//                        + ' <i class="icon-append fa fa-user"></i>'
//                        + ' <div class="col-xs-10 col-xs-offset-2">'
//                        + ' <p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
//                        + ' <p class="cart-description job-title">' + value.jobtitle + '</p>'
//                        + ' <p class="cart-description mobile-number">' + value.mobile + '</p>'
//                        + ' <p class="cart-description-price money-price">LKR ' + value.leadamount + '</p>'
//                        + ' <p class="cart-description-price days-left"><span class="sl-butz-leads">' + value.lead_closed_date + '</span></p>'
//                        + ' <div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
//                        + ' <div class="row" style = "margin-bottom: 2px; padding-right: 50px; padding-left: 12px;">'
//                        + ' <div class="col-lg-6 col-md-12 no-padding">'
//                        + ' <button type="button" class="btn btn-block add-button-cart viewcontactinfo dropdown-button" id="' + value.contactid + '"><span class="icon-left"></span>View Contact</button>'
//                        + ' </div>'
//                        + ' <div class="col-lg-6 col-md-12 no-padding">'
//                        + ' <button type="button" class="btn btn-block add-button-cart leadInfoview dropdown-button" id="' + value.leadid + '"><span class="icon-left"></span>View Lead</button>'
//                        + ' </div>'
//                        + ' <div class="col-lg-6 col-md-12 no-padding">'
//                        + ' <form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
//                        + ' <form path="leadid" type="hidden" id="leadid" value="' + value.leadid + '"></form>'
//                        + ' <form:button id="createbutton" type="submit" class="btn btn-block add-button-cart add-button-cart_new dropdown-button">'
//                        + ' <span class="icon-left"></span>Activity History'
//                        + ' </form:button>'
//                        + ' </form:form>'
//                        + ' </div>'
//                        + ' </div>'
//                        + ' </div>'
//                        + ' </div>'
//                        + ' </div>'
//                        + ' </div>'
//                        + ' </div>'
//                        + ' <div class="view-more-cart">'
//                        + ' <a  data-toggle="collapse" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '" class="collapsed">'
//                        + ' <span id="view-more-less-more-' + value.leadid + '">'
//                        + ' <i class="fa fa-angle-down view-more-below"></i>'
//                        + ' </span>'
//                        + ' </a>'
//                        + ' </div>'
//                        + ' </div>'

                return string;
            }
            function LeadLostAppend(value) {
//                var string = '<div class="panel-body cartbody" id="' + value.leadid + '">'
//                        + '<div class="panel panel-default cart-panel-body-header" style="border-radius: 5px;">'
//                        + '<div class="panel-body cart-panel-body mousetoHand">'
//                        + '<div class="row cart-body-row">'
//                        + '<div class="col-xs-2"><i class="icon-append fa fa-user user-icon-border" style="margin-top: 10px;font-size: 24px;color: #EEE"></i></div>' + '<div class="col-xs-10">'
//                        + '<p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
//                        + '<p class="cart-description"><i class="icon-append fa fa-suitcase" style="font-size: 15px;margin-top: 10px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + value.jobtitle + '</p>'
//                        + '<p class="cart-description"><i class="icon-append fa fa-phone" style="font-size: 18px;margin-left: 3px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp;&nbsp;' + value.mobile + '</p>'
//                        + '<p class="cart-description-price"><i class="icon-append fa fa-money" style="font-size: 16px;color: #B7B7B7;"></i>&nbsp;&nbsp;&nbsp; LKR ' + value.leadamount + '</p>' + '<div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
//                        + '<button type="button" class="btn  btn-block add-button-cart viewcontactinfo" id="' + value.contactid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Contact Info</button>'
//                        + '<button type="button" class="btn  btn-block add-button-cart leadInfoview" id="' + value.leadid + '"><span class="icon-left"><i class="fa fa-plus"></i></span>View Lead Info</button>'
//                        + '<form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
//                        + '<input path="leadid" type="hidden" name="leadid" value="' + value.leadid + '"></input>'
//                        + '<form:button id="createbutton" type="submit" class="btn  btn-block add-button-cart bottom-set">'
//                        + '<span class="icon-left"><i class="fa fa-plus"></i></span>View Activity History'
//                        + '</form:button>'
//                        + '</form:form>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '</div>'
//                        + '<div class="view-more-cart">'
//                        + '<a  class="view-more-link" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-' + value.leadid + '" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '"><span id="view-more-less-more-' + value.leadid + '"><i class="fa fa-plus"></i>  View More</span></a>'
//                        + '</div>'
//                        + '</div>';

                var string = '<div class="panel-body cartbody" id="' + value.leadid + '">'
                        + ' <div class="panel panel-default cart-panel-body-header no-margin" style="border-radius: 5px;">'
                        + ' <div class="panel-body cart-panel-body mousetoHand">'
                        + ' <div class="row cart-body-row">'
                        + ' <i class="icon-append fa fa-user"></i>'
                        + ' <div class="col-xs-10 col-xs-offset-2">'
                        + ' <p class="cart-description card-name">' + value.title + value.nameInFull + '</p>'
                        + ' <p class="cart-description job-title">' + value.jobtitle + '</p>'
                        + ' <p class="cart-description mobile-number">' + value.mobile + '</p>'
                        + ' <p class="cart-description-price money-price">LKR ' + value.leadamount + '</p>'
                        + ' <p class="cart-description-price days-left"><span class="sl-butz-leads">' + value.lead_lost_date + '</span></p>'
                        + ' <div id="lead-collapse-' + value.leadid + '" class="panel-collapse collapse">'
                        + ' <div class="row" style = "margin-bottom: 2px; padding-right: 50px; padding-left: 12px;">'
                        + ' <div class="col-lg-6 col-md-12 no-padding">'
                        + ' <button type="button" class="btn btn-block add-button-cart viewcontactinfo dropdown-button" id="' + value.contactid + '"><span class="icon-left"></span>View Contact</button>'
                        + ' </div>'
                        + ' <div class="col-lg-6 col-md-12 no-padding">'
                        + ' <button type="button" class="btn btn-block add-button-cart leadInfoview dropdown-button" id="' + value.leadid + '"><span class="icon-left"></span>View Lead</button>'
                        + ' </div>'
                        + ' <div class="col-lg-6 col-md-12 no-padding">'
                        + ' <form:form id="addactivity" commandName="activityForm" action="${pageContext.request.contextPath}/lead" method="POST" >'
                        + ' <input path="leadid" type="hidden" id="leadid" value="' + value.leadid + '"></input>'
                        + ' <form:button id="createbutton" type="submit" class="btn btn-block add-button-cart add-button-cart_new dropdown-button">'
                        + ' <span class="icon-left"></span>Activity History'
                        + ' </form:button>'
                        + ' </form:form>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' </div>'
                        + ' <div class="view-more-cart">'
                        + ' <a  data-toggle="collapse" data-parent="#parent-collapse" href="#lead-collapse-' + value.leadid + '" class="collapsed">'
                        + ' <span id="view-more-less-more-' + value.leadid + '">'
                        + ' <i class="fa fa-angle-down view-more-below"></i>'
                        + ' </span>'
                        + ' </a>'
                        + ' </div>'
                        + ' </div>';
                return string;
            }
            $('#leadCreate').validate({
                rules: {
                    amount: {
                        required: true,
                        number: true
                    },
                    account_count: {
                        required: true,
                        number: true
                    },
                    productid: {
                        required: true
                    },
                    chanelId: {
                        required: true
                    },
                    champignId: {
                        required: true
                    },
                    for_cast_until: {
                        required: true
                    }
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            $('#customercategoryform').validate({
                rules: {
                    customercategory: {
                        required: true
                    },
                    customer_category_type: {
                        required: true
                    },
                    actual_lead_amount: {
                        required: true
                    },
                    actual_account_count: {
                        required: true,
                        number: true
                    }
                }
            });
            jQuery.validator.addMethod("lettersonly", function (value, element) {
                return this.optional(element) || /^[a-z\s]+$/i.test(value);
            }, "Letters only please");
            $('#customerindividual').validate({
                rules: {
                    title: {
                        required: true
                    },
                    name_in_full: {
                        required: true,
                        lettersonly: true,
                        minlength: 2
                    },
                    initials: {
                        required: true
                    },
                    last_name: {
                        required: true,
                        lettersonly: true,
                        minlength: 2
                    },
                    preferred_name: {
                        required: true,
                        lettersonly: true
                    },
                    date_of_birth: {
                        required: true
                    },
                    gender: {
                        required: true
                    }/*,
                     mothers_maiden_name: {
                     required: true,
                     lettersonly: true,
                     minlength: 2
                     }*/
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            $('#customerorganization').validate({
                rules: {
                    copemployer: {
                        required: true
                    },
                    copsector: {
                        required: true
                    }
                }
            });
            $('#lostForm').validate({
                rules: {
                    opportunitylostresonsid: {
                        required: true
                    },
                    lead_lost_remark: {
                        required: true
                    }
                }
            });
            $('#leadCreatemanual').validate({
                rules: {
                    selectcontact: {
                        required: true
                    },
                    lead_create_agent: {
                        required: true
                    },
                    amountmanual: {
                        maxlength: 9,
                        required: true,
                        number: true
                    },
                    productidmanual: {
                        required: true
                    },
                    for_cast_until_manual: {
                        required: true
                    },
                    chanelIdmanual: {
                        required: true
                    },
                    champignIdmanual: {
                        required: true
                    }
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            $("#lead_create_agent").change(function () {
                var createUser = $("#lead_create_agent").val();
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getContactCreateLead",
                    data: {createUser: createUser},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $("#selectcontact").empty();
                        $.each(res1.CONTACT, function (index, value) {
                            $("#selectcontact").append('<option value="' + index + '">' + value + '</option>');
                        });
                    }
                });
            });
            $("#submit_lead_manual").click(function () {
                if ($('#leadCreatemanual').valid()) {
//                    if(){}
                    var dataObject = new Object();
                    dataObject.productid = $("#productidmanual").val();
                    dataObject.date = $("#for_cast_until_manual").val();
                    dataObject.contact_id = $("#selectcontact").val();
                    var amount = $("#amountmanual").val();
                    dataObject.amount = amount.replace(/,/g, "");
                    dataObject.channelId = $("#chanelIdmanual").val();
                    dataObject.campignId = $("#champignIdmanual").val();
                    dataObject.promationCode = $("#promationCodemanual").val();
                    var content = JSON.stringify(dataObject);
                    jQuery.ajax({
                        async: true,
                        type: "POST", url: "${pageContext.request.contextPath}/salespipline/lead/create",
                        cache: false,
                        data: {lead: content},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                dataObjectFilter.leadStart = 0;
                                dataObjectFilter.leadEnd = 3;
                                $("#lead-serach").val('');
                                $("#lead-hidden-down").html('<input type="hidden"  value="3" id="lead-count-down" />');
                                $("#lead-hidden-up").html('<input type="hidden"  value="0" id="lead-count-up" />');
                                SerachFunction();
                            }
                            $('#myModal-contactmanual').modal('toggle');
                            $("#productidmanual").val('');
                            $("#for_cast_until_manual").val('');
                            $("#selectcontact").val('');
                        }
                    });
                }
            });
            function closedModal() {
                $("#parent-collapse,#saleclosed").sortable('cancel');
            }

            function ContactEditFunction() {
                document.getElementById("con_edit_name_in_full").disabled = false;
                document.getElementById("con_edit_jobtitle").disabled = false;
                document.getElementById("con_edit_employer").disabled = false;
                document.getElementById("con_edit_email").disabled = false;
                document.getElementById("con_edit_mobile").disabled = false;
                document.getElementById("con_edit_contact_id").disabled = false;
                $('#con_edit_footer').html('<button type="button" class="btn btn-default" onclick="ContactEditedFunction()">Save Changes</button>'
                        + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                $('#contactHeder').html("Update Contact Information");
            }
            function ContactEditedFunction() {
                if ($('#contactEditForm').valid()) {
                    var nameInFull = $("#con_edit_name_in_full").val();
                    var jobtitle = $("#con_edit_jobtitle").val();
                    var employer = $("#con_edit_employer").val();
                    var email = $("#con_edit_email").val();
                    var mobile = $("#con_edit_mobile").val();
                    var contactId = $("#con_edit_contact_id").val();
                    jQuery.ajax({
                        async: true,
                        type: "POST", url: "${pageContext.request.contextPath}/salespipline/updatecontact",
                        cache: false,
                        data: {nameInFull: nameInFull, jobtitle: jobtitle, employer: employer, email: email, mobile: mobile, contactId: contactId},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                $('#contactInfo').modal('toggle');
                                SerachFunction();
                            }
                        }
                    });
                }
            }
            function LeadEditFunction() {
                document.getElementById("edit_for_cast_until_lead").disabled = false;
                document.getElementById("edit_amount_lead").disabled = false;
                document.getElementById("edit_productid_lead").disabled = false;
                document.getElementById("edit_account_count").disabled = false;
                $('#lead_edit_footer_lead').html('<button type="button" class="btn btn-default" onclick="LeadEditedFunction()">Save Changes</button>'
                        + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                $('#leadInfoHeader_lead').html("Update Lead Information");
            }
            function LeadEditedFunction() {
                if ($('#editLeadFrom_lead').valid()) {
                    var forcastuntildate = $("#edit_for_cast_until_lead").val();
                    var amountNew = $("#edit_amount_lead").val();
                    var amount = amountNew.replace(/,/g, "");
                    var productId = $("#edit_productid_lead").val();
                    var leadId = $("#edit_lead_id_lead").val();
                    var accountcount = $('#edit_account_count').val();
                    jQuery.ajax({
                        async: true,
                        type: "POST",
                        url: "${pageContext.request.contextPath}/salespipline/lead/update",
                        cache: false,
                        data: {forcastuntildate: forcastuntildate, amount: amount, productId: productId, leadId: leadId, accountcount: accountcount},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                $('#leadInfoLead').modal('toggle');
                            }
                            SerachFunction();
                        }
                    });
                }
            }
            $('#editLeadFrom_lead').validate({
                rules: {
                    edit_amount_lead: {
                        required: true,
                        number: true
                    },
                    edit_productid_lead: {
                        required: true
                    },
                    edit_for_cast_until_lead: {
                        required: true
                    },
                    edit_account_count: {
                        required: true,
                        number: true
                    }
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            function LeadSalesClosedEditFunction() {
                document.getElementById("edit_for_cast_until_sales_closed").disabled = false;
                document.getElementById("edit_amount_sales_closed").disabled = false;
                document.getElementById("edit_confirm_amount_sales_closed").disabled = false;
                document.getElementById("edit_productid_sales_closed").disabled = false;
                $('#lead_edit_footer_sales_closed').html('<button type="button" class="btn btn-default" onclick="LeadSalesClosedEditedFunction()">Save Changes</button>'
                        + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                $('#leadInfoHeader_sales_closed').html("Update Lead Information");
            }
            function LeadSalesClosedEditedFunction() {
                if ($('#editLeadFrom_sales_closed').valid()) {
                    var closeddate = $("#edit_for_cast_until_sales_closed").val();
                    var amountNew = $("#edit_amount_sales_closed").val();
                    var amount = amountNew.replace(/,/g, "");
                    var confirmamountNew = $("#edit_confirm_amount_sales_closed").val();
                    var confirmamount = confirmamountNew.replace(/,/g, "");
                    var productId = $("#edit_productid_sales_closed").val();
                    var leadId = $("#edit_lead_id_sales_closed").val();
                    jQuery.ajax({
                        async: true,
                        type: "POST",
                        url: "${pageContext.request.contextPath}/salespipline/lead/update/salesclosed",
                        cache: false,
                        data: {closeddate: closeddate, amount: amount, productId: productId, leadId: leadId, confirmamount: confirmamount},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                $('#leadInfoSalesClosed').modal('toggle');
                            }
                            SerachFunction();
                        }
                    });
                }
            }
            $('#editLeadFrom_sales_closed').validate({
                rules: {
                    edit_amount_sales_closed: {
                        required: true,
                        number: true
                    },
                    edit_confirm_amount_sales_closed: {
                        required: true,
                        number: true
                    },
                    edit_productid_sales_closed: {
                        required: true
                    },
                    edit_for_cast_until_lead: {
                        required: true
                    }
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            function LeadLostEditFunction() {
                document.getElementById("edit_for_cast_until_lost").disabled = false;
                document.getElementById("edit_amount_lost").disabled = false;
                document.getElementById("edit_productid_lost").disabled = false;
                document.getElementById("edit_opportunity_lost_resons_id").disabled = false;
                $('#lead_edit_footer_lost').html('<button type="button" class="btn btn-default" onclick="LeadLostEditedFunction()">Save Changes</button>'
                        + '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
                $('#leadInfoHeader_lost').html("Update Lead Information");
            }
            function LeadLostEditedFunction() {
                if ($('#editLeadFrom_lost').valid()) {
                    var lostdate = $("#edit_for_cast_until_lost").val();
                    var amountNew = $("#edit_amount_lost").val();
                    var amount = amountNew.replace(/,/g, "");
                    var productId = $("#edit_productid_lost").val();
                    var leadId = $("#edit_lead_id_lost").val();
                    var resoncode = $("#edit_opportunity_lost_resons_id").val();
                    jQuery.ajax({
                        async: true,
                        type: "POST",
                        url: "${pageContext.request.contextPath}/salespipline/lead/update/lost",
                        cache: false,
                        data: {lostdate: lostdate, amount: amount, productId: productId, leadId: leadId, resoncode: resoncode},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                $('#leadInfoLost').modal('toggle');
                            }
                            SerachFunction();
                        }
                    });
                }
            }
            $('#editLeadFrom_lost').validate({
                rules: {
                    edit_amount_lost: {
                        required: true,
                        number: true
                    },
                    edit_productid_lost: {
                        required: true
                    },
                    edit_for_cast_until_lost: {
                        required: true
                    },
                    edit_opportunity_lost_resons_id: {
                        required: true
                    }
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            $("#addactivitysales").click(function (event) {
                event.preventDefault();
                if ($('#addActivityForm').valid()) {
                    var description = $("#Adescription").val();
                    var leadid = parseInt($("#addActivitySetLeadId").val());
                    var atype = $("#atype").val();
                    var adate = $("#adate").val();
                    jQuery.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/activity/addactivity",
                        data: {description: description, atype: atype, adate: adate, leadid: leadid},
                        success: function (res) {
                            $('#addActivityModal').modal('toggle');
                        }
                    });
                }
            });
            $('#addActivityForm').validate({
                rules: {
                    Adescription: {
                        required: true
                    },
                    atype: {
                        required: true
                    },
                    adate: {
                        required: true
                    }
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            $('#contactEditForm').validate({
                rules: {
                    con_edit_name_in_full: {
                        required: true
                    },
                    con_edit_mobile: {
                        required: true,
                        number: true,
                        maxlength: 10,
                        minlength: 10
                    },
                    con_edit_jobtitle: {
                        required: true
                    },
                    con_edit_employer: {
                        required: true
                    },
                    con_edit_email: {
                        required: true,
                        email: true
                    }
                }, errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
            $("#oppotunity_lost").on("click", ".view-update-lost", function () {
                var id = $(this).attr("id");
                $("#lost_edit_lead_id").val(id);
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/salespipline/getoptunitylostresonscode",
                    data: {leadId: id},
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $("#opportunitylostresonsidupdate").val(res1.RESONSCODE);
                        $('#modal_opptunity_lost_update').modal({backdrop: 'static', keyboard: false});
                    }
                });
            });
            $('#lostFormUpdate').validate({
                rules: {
                    opportunitylostresonsidupdate: {
                        required: true
                    }
                }
            });
            $("#opportunity_lost_update").click(function (event) {
                event.preventDefault();
                var opportunityid = $("#opportunitylostresonsidupdate").val();
                var leadId = $("#lost_edit_lead_id").val();
                if ($('#lostFormUpdate').valid()) {
                    jQuery.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/salespipline/lead/update/opportunitylost", data: {leadid: leadId, opportunityid: parseInt(opportunityid)},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            if (res1.CODE == "SUCCESS") {
                                $('#modal_opptunity_lost_update').modal('toggle');
                            }

                        }
                    });
                }

            });
            $("#chanelId").change(function () {
                var chanelId = $(this).val();
                $("#champignId").val('');
                if (chanelId === "1") {
                    $("#selectCampaign").show();
                } else {
                    $("#selectCampaign").hide();
                }
            });
            $("#chanelIdmanual").change(function () {
                $("#champignIdmanual").val('');
                var chanelIdmanual = $(this).val();
                if (chanelIdmanual === "1") {
                    $("#selectCampaignmanual").show();
                } else {
                    $("#selectCampaignmanual").hide();
                }
            });

            $('#').change(function () {

            });

            function clearContactFields() {
                $("#con_title").rules("remove");
                $("#con_name_in_full").rules("remove");
                $("#con_mobile").rules("remove");
                $("#con_jobtitle").rules("remove");
                $("#con_employer").rules("remove");
                $("#con_email").rules("remove");
                $("#con_title").removeClass("invalid");
                $('#addContactForm').valid();
                document.getElementById("con_title").value = '';
                document.getElementById("con_name_in_full").value = '';
                document.getElementById("con_jobtitle").value = '';
                document.getElementById("con_employer").value = '';
                document.getElementById("con_email").value = '';
                document.getElementById("con_mobile").value = '';
                $("#errorMsgContact").hide();
            }



            function ProductAppend(value) {
                var string =
                        '<option value="' + value.productID + '">'
                        + value.productDesc +
                        '</option>';
                return string;
            }

            $("#contact-serach").keyup(function (event) {
                if (event.keyCode == 13) {
                    SerachFunction();
                }
            });
            $("#lead-serach").keyup(function (event) {
                if (event.keyCode == 13) {
                    SerachFunction();
                }
            });
            $("#closed-serach").keyup(function (event) {
                if (event.keyCode == 13) {
                    SerachFunction();
                }
            });
            $("#lost-serach").keyup(function (event) {
                if (event.keyCode == 13) {
                    SerachFunction();
                }
            });
            var fnfamount = document.getElementById("amount");
            fnfamount.addEventListener('keyup', function (evt) {

                if (fnfamount.value) {
                    var n = parseInt(this.value.replace(/\D/g, ''), 10);
                    fnfamount.value = n.toLocaleString();
                }

            }, false);
            var fnfamountmanual = document.getElementById("amountmanual");
            fnfamountmanual.addEventListener('keyup', function (evt) {
                if (fnfamountmanual.value) {
                    var n = parseInt(this.value.replace(/\D/g, ''), 10);
                    fnfamountmanual.value = n.toLocaleString();
                }

            }, false);
            var fnfeditamountlead = document.getElementById("edit_amount_lead");
            fnfeditamountlead.addEventListener('keyup', function (evt) {
                if (fnfeditamountlead.value) {
                    var n = parseInt(this.value.replace(/\D/g, ''), 10);
                    fnfeditamountlead.value = n.toLocaleString();
                }

            }, false);
            var fnfeditamountleadclosed = document.getElementById("edit_amount_sales_closed");
            fnfeditamountleadclosed.addEventListener('keyup', function (evt) {
                if (fnfeditamountleadclosed.value) {
                    var n = parseInt(this.value.replace(/\D/g, ''), 10);
                    fnfeditamountleadclosed.value = n.toLocaleString();
                }

            }, false);
            var fnfeditamountleadconfirm = document.getElementById("edit_confirm_amount_sales_closed");
            fnfeditamountleadconfirm.addEventListener('keyup', function (evt) {
                if (fnfeditamountleadconfirm.value) {
                    var n = parseInt(this.value.replace(/\D/g, ''), 10);
                    fnfeditamountleadconfirm.value = n.toLocaleString();
                }
            }, false);
            var fnfeditamountleadlost = document.getElementById("edit_amount_lost");
            fnfeditamountleadlost.addEventListener('keyup', function (evt) {
                if (fnfeditamountleadlost.value) {
                    var n = parseInt(this.value.replace(/\D/g, ''), 10);
                    fnfeditamountleadlost.value = n.toLocaleString();
                }

            }, false);
            var fnfactualamountleadlost = document.getElementById("actual_lead_amount");
            fnfactualamountleadlost.addEventListener('keyup', function (evt) {
                if (fnfactualamountleadlost.value) {
                    var n = parseInt(this.value.replace(/\D/g, ''), 10);
                    fnfactualamountleadlost.value = n.toLocaleString();
                }

            }, false);
            $(window).load(function () {
                var h = $("#contact-main-div").height()
                $('#parent-collapse').css('height', h);
                $('#parent-collapse').css('border', '0px');
                $('#saleclosed').css('height', h);
                $('#saleclosed').css('border', '0px');
                $('#oppotunity_lost').css('height', h);
                $('#oppotunity_lost').css('border', '0px');
            });

            $('#con_name_in_full').keyup(function () {
                $('#con_name_in_full').val($('#con_name_in_full').val().toUpperCase());

            });

            $("#contact-main-div").sortable('cancel');


        </script>
    </body>

</html>
