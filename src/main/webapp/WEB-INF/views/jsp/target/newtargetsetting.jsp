<%--
    Document   : newtargetsetting
    Created on : Jun 23, 2016, 8:59:27 AM
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">Target Management</a></li><li>Add Target</li>
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
                                Add Target
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
                                                    <a href="#tab1" data-toggle="tab"> <span class="step">1</span> <span class="title">Target Setting</span> </a>
                                                </li>
                                                <li data-target="#step2">
                                                    <a href="#tab2" data-toggle="tab"> <span class="step">2</span> <span class="title">Organization Target</span> </a>
                                                </li>
                                                <li data-target="#step3">
                                                    <a href="#tab3" data-toggle="tab"> <span class="step">3</span> <span class="title">Branch Target</span> </a>
                                                </li>
                                                <li data-target="#step4" >
                                                    <a href="#tab4" data-toggle="tab"> <span class="step">4</span> <span class="title">Save Form</span> </a>
                                                </li>
                                                <li >
                                                    <a href="#tab5" data-toggle="tab"></a>
                                                </li>

                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                        <form:form class="smart-form" id="wizard-1" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userolesection" method="post" >
                                            <form:input  type="hidden" id="multiregionalarray" path="multiregionalarray"/>
                                            <form:input  type="hidden" id="targetnotassignregion" path="targetnotassignregion"/>
                                            <form:input  type="hidden" id="multibrancharray" path="multibrancharray"/>
                                            <form:input  type="hidden" id="targetnotassignbranch" path="targetnotassignbranch"/>
                                            <form:input  type="hidden" id="regionaltargetlist" path="regionaltargetlist"/>
                                            <form:input  type="hidden" id="brachtargetlist" path="brachtargetlist"/>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab1">
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
                                                    <br><br><br>
                                                    <h3><strong>Step 1 </strong> - Target Setting</h3>
                                                    <br> <br> <br>
                                                    <label id="tab1"  ></label>
                                                    <div class="row">



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
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-3">
                                                                <section>
                                                                    <label class="label">Target Amount<samp style="color: red">*</samp></label>
                                                                    <label class="input">
                                                                        <form:input path="revenue" type="text" name="revenue"   /><i></i>
                                                                    </label>
                                                                </section>
                                                            </div>

                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-7">
                                                                <section>
                                                                    <label class="label">Description<samp style="color: red">*</samp></label>
                                                                    <label class="input">
                                                                        <form:input path="tragetdes" type="text" name="tragetdes" value="${fullname}" /><i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-3">
                                                                <section>
                                                                    <label class="label">Target Type<samp style="color: red">*</samp></label>
                                                                    <label class="select">
                                                                        <form:select id="targettypeid" path="targettypeid" items="${targettype}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-3">
                                                                <section>
                                                                    <label class="label">Activity Type<samp style="color: red">*</samp></label>
                                                                    <label class="select">
                                                                        <form:select id="activitytypeid" path="activitytypeid" items="${activitytype}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-3">
                                                                <section>
                                                                    <label class="label">Target Group<samp style="color: red">*</samp></label>
                                                                    <label class="select">
                                                                        <form:select id="targergroupid" path="targergroupid" items="${userRoleList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>

                                                            <div class="col-xs-1"></div>
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
                                                                    <form:label path="">Target Start Date:<samp style="color: red">*</samp></form:label>
                                                                    <form:input disabled="true" path="targetstartdate" type="text" id="datetimepickerfromdate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-1"></div>
                                                                <div class="col-xs-3">
                                                                    <div class="form-group">
                                                                    <form:label path="">Target End Date:<samp style="color: red">*</samp></form:label>
                                                                    <form:input path="targetenddate" disabled="true" type="text" id="datetimepickertodate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                                    </div>
                                                                </div>
                                                            </div>


                                                        </div>



                                                    </div>
                                                    <div class="tab-pane" id="tab2">
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
                                                    <br> <br> <br>
                                                    <h3><strong>Step 2</strong> - Organization Target: <label id="organizattionWStarget"></label></h3>
                                                    <br> <br> <br>
                                                    <div class="row">
                                                        <!--                                                        <div class="col-xs-2"></div>
                                                                                                                <div class="col-xs-3">
                                                                                                                    <section>
                                                        <%--<form:input class="form-control" path="tragetdes" placeholder="Target Description..." type="text" id="TDes"  ondblclick="loadRegionList();" cssStyle="padding-left:10px;"/>--%>
                                                        <div id="log" class="font-xs margin-top-10 text-danger"></div>
                                                    </section>
                                                </div>-->
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Target <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="targetiddropdown" path="targetid"  onchange="loadRegionList()"  items="${targetdropdown}"/><i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">

                                                            <form:hidden  id="regiontarget" path="regiontarget"/><i></i>

                                                            <section>
                                                                <label class="label"> Toatal Target <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input readonly="false" id="totalvalue" path="totalvalue"/><i></i>
                                                                </label>

                                                            </section>

                                                            <section>


                                                            </section>
                                                        </div>
                                                    </div>

                                                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3" id="sectionids">
                                                            <section>
                                                                <label class="label">Region</label>
                                                                <label class="select">
                                                                    <form:select id="regionalid" path="regionalid" multiple="true"  style="height:100px;" />
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
                                                                        <button id="Regionpull_right" type="button" class="btn btn-xs">>></button>
                                                                    </section>
                                                                    <section>
                                                                        <button id="Regionpull_left" type="button" class="btn btn-xs"><<</button>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-5"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label" style="height:19px;"></label>
                                                                <label class="select">
                                                                    <form:select id="multiregional" path="multiregional"  multiple="true" style="height:100px;"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>

                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <!--                                                        <div class="row" id="TextBoxesGroup">-->
                                                        <!--                                                        <table id="fileTable">
                                                           
                                                        
                                                                                                                </table>-->
                                                        <div id="appendId" class="col-xs-6">   

                                                        </div>     
                                                    </div>

                                                </div>



                                                <div class="tab-pane" id="tab3">
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
                                                    <br> <br> <br>
                                                    <h3><strong>Step 3</strong> - Branch Target: <label id="branchtarget"></label></h3>
                                                    <br> <br> <br>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Region <samp style="color: red">*</samp></label>
                                                                <label class="select">
                                                                    <form:select id="selectedregion" path="targetid"   onchange="appenTarget();"/><i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>

                                                                <label class="input">
                                                                    <form:hidden  id="oneregiontarget"  path="regiontarget"  /><i></i>
                                                                </label>
                                                            </section>

                                                            <section>
                                                                <label class="label">Toatal Target <samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input  id="totalregion"  path="regiontarget"  /><i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3" id="sectionids">
                                                            <section>
                                                                <label class="label">Branch</label>
                                                                <label class="select">
                                                                    <form:select id="branchid" path="branchid" multiple="true" style="height:100px;"/>
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
                                                                        <button id="Bpull_right" type="button" class="btn btn-xs">>></button>
                                                                    </section>
                                                                    <section>
                                                                        <button id="Bpull_left" type="button" class="btn btn-xs"><<</button>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-5"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label" style="height:19px;"></label>
                                                                <label class="select">
                                                                    <form:select id="multibranch" path="multibranch"  multiple="true" style="height:100px;"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>

                                                    </div>
                                                    <!--<div class="row" id="container">-->
                                                    <!--                                                    <div class="col-xs-2"></div>
                                                                                                        <table id="fileTable2">
                                                                                                        </table>-->
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <!--                                                        <div class="row" id="TextBoxesGroup">-->
                                                        <!--                                                        <table id="fileTable">
                                                           
                                                        
                                                                                                                </table>-->
                                                        <div id="appendId2" class="col-xs-6">   

                                                        </div>     
                                                    </div>
                                                </div>
                                                <div class="tab-pane" id="tab4">
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
                                                    <br>
                                                    <h3><strong>Step 4</strong> - Save Form</h3>
                                                    <br>
                                                    <h1 class="text-center text-success"><strong><i class="fa fa-check fa-lg"></i> Complete</strong></h1>
                                                    <h4 class="text-center">Click next to finish</h4>
                                                    <br>
                                                    <br>
                                                </div>
                                                <!--<form id="wizard-1" novalidate="novalidate">-->

                                                <div class="form-actions">
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <ul class="pager wizard no-margin">
                                                                <!--<li class="previous first disabled">
                                                                <a href="javascript:void(0);" class="btn btn-lg btn-default"> First </a>
                                                                </li>-->
                                                                <li class="previous disabled">
                                                                    <a href="javascript:void(0);" class="btn btn-lg btn-default"> Previous </a>
                                                                </li>
                                                                <!--<li class="next last">
                                                                <a href="javascript:void(0);" class="btn btn-lg btn-primary"> Last </a>
                                                                </li>-->
                                                                <li class="next">
                                                                    <a href="javascript:void(0);" class="btn btn-lg txt-color-darken"> Next </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                    </form>
                                </div>

                            </div>
                            <!-- end widget content -->

                        </div>
                        <!-- end widget div -->

                    </div>
                    <!-- end widget -->

                    </arti

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
            var counter = 0;
            var date = new Date(), y = date.getFullYear(), m = date.getMonth();
            d = date.getDate();
            $('#datetimepickerfromdate').datetimepicker({
                weekStart: 1,
                todayHighlight: false,
                todayBtn: 2,
                autoclose: 1,
                startView: 3,
                minView: 2,
                forceParse: 0,
                startDate: new Date(y, m, 1),
                endDate: new Date(y, m, 1),
                format: "yyyy-mm-dd"
            });
            $(document).ready(function () {
                $("#targetiddropdown").append("<option value='sas'>--Select--</option>");
                $("#selectedregion").append("<option value='sas'>--Select--</option>");

            });
            $('#Regionpull_right').click(function () {
                var selectedItem = $("#regionalid option:selected");
                $("#multiregional").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateTargetNotAssignRegionArray();
                addTextBox(selectedItem.text(), selectedItem.val());
            });
            $('#Regionpull_left').click(function () {
                var selectedItem = $("#multiregional option:selected");
                $("#regionalid").append(selectedItem);
                selectedItem.prop("selected", false);
                removeTextBox(selectedItem.text());
                generateRegionArray();
                generateTargetNotAssignRegionArray();
            });
            $('#regionalid').dblclick(function () {
                var selectedItem = $("#regionalid option:selected");
                $("#multiregional").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateTargetNotAssignRegionArray();
                addTextBox(selectedItem.text(), selectedItem.val());
            });
            $('#multiregional').dblclick(function () {
                var selectedItem = $("#multiregional option:selected");
                $("#regionalid").append(selectedItem);
                selectedItem.prop("selected", false);
                removeTextBox(selectedItem.text());
                generateRegionArray();
                generateTargetNotAssignRegionArray();
                //                generateZeroBranchArray();
            });
            function resetMultiBranchSelect() {
                $('#multiregionalarray').val();
                $('#multiregional option').prop('selected', true);
                var selectedItem = $('#multiregional option:selected');
                $('#regionalid').append(selectedItem);
                selectedItem.prop('selected', false);
                sortSelect('#regionalid', 'text', 'asc');
            }

            function generateRegionArray() {
                var section = [];
                $("#multiregional option").each(function () {
                    section.push($(this).val());
                });
                $('#multiregionalarray').val(JSON.stringify(section));
            }


            function generateTargetNotAssignRegionArray() {
                var section = [];
                $("#regionalid option").each(function () {
                    section.push($(this).val());
                });
                $('#targetnotassignregion').val(JSON.stringify(section));
            }


            jQuery.validator.addMethod("multiregionalvalidation", function () {
                $('#multiregional option').prop('selected', true);
                var selectedItem = $('#multiregional option:selected');
                console.log(selectedItem.length);
                if (selectedItem.length < 0) {
                    return true;
                } else {
                    return false;
                }
            }, jQuery.validator.format("Please select atleast one region."));


            function pageSubmit() {
                $('#msg_dev').empty();
                var dataObject = new Object();
                dataObject.tragetdes = $('#tragetdes').val();
                dataObject.productid = $('#productid').val();
                dataObject.targergroupid = $('#targergroupid').val();
                dataObject.revenue = $('#revenue').val();
                dataObject.targetperiodid = $('#targetperiodid').val();
                dataObject.targetstartdate = $('#datetimepickerfromdate').val();
                dataObject.targetenddate = $('#datetimepickerfromdate').val();
                dataObject.targettypeid = $('#targettypeid').val();
                dataObject.activitytypeid = $('#activitytypeid').val();
                console.log(dataObject);
                var content = JSON.stringify(dataObject);
                console.log(content);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/target/created",
                    cache: false,
                    async: false,
                    data: {target_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
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
                loadTargetDroupDown();

            }


            $('#datetimepickerfromdate').datetimepicker().on('changeDate', function (ev) {
                var date = new Date(), yy = date.getFullYear(), mm = date.getMonth();
                dd = date.getDate();
                if ($('#targetperiodid').val() == 1) { //                    alert("1");
                    mm = mm + 1;
                    dd = 1;
                }
                if ($('#targetperiodid').val() == 2) {
                    mm = m + 3;
                    dd = 1;
                }
                if ($('#targetperiodid').val() == 3) {
                    //                    alert("3");                     yy = y + 1;
                    mm = m + 2;
                    d = 1;
                }
                $('#datetimepickertodate').datetimepicker('setDate', new Date(yy, mm, 1));
            });


//            $("#TDes").autocomplete({
//                source: function (request, response) {
//                    $.ajax({
//                        url: "${pageContext.servletContext.contextPath}/target/targetdropdown",
//                        dataType: "json", data: {
//                            name_startsWith: request.term
//                        },
//                        success: function (data) {
//
//                            response($.map(data, function (item) {
//                                console.log(data);
//                                return {
//                                    label: item.tragetdes,
//                                    value: item.tragetdes
//                                }
//                            }));
//                        }
//                    });
//                },
//                minLength: 2,
//                select: function (event, ui) {
//                    console.log(this.value);
//                    //
//                }
//            });


//            function selectedRegionlTargeRow(row) {
//                var $row = jQuery(row).closest('tr');
//                var $columns = $row.find('td');
//                jQuery.each($columns, function (i, item) {
//                    if (i === 4) {
//                        $('#education_level').val(item.innerHTML);
//                    } else if (i === 5) {
//                        $('#institute').val(item.innerHTML);
//                    }
//                });
//            }
//

//            function deleteRegionlTargetRow(row) {
//                var tableRow = row.closest('tr');
//                var tableDataList = tableRow.find('td');
//                $.SmartMessageBox({
//                    title: "Alert!",
//                    content: "<i class='fa fa-trash-o fa-3x'></i> Are you sure you want to delete the selected Education Qualification ?",
//                    buttons: '[No][Yes]'
//                }, function (ButtonPressed) {
//                    if (ButtonPressed === "Yes") {
//                        $.ajax({
//                            async: true,
//                            type: "post",
//                            url: "${pageContext.servletContext.contextPath}/account/create/deleteeducation",
//                            cache: false,
//                            data: {target_id: tableDataList[3].innerText, account_id: $('#account_id').val()},
//                            success: function (response) {
//                                response = JSON.parse(response);
//                                if (response.CODE === "SUCCESS") {
//                                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Deleted</div> <br/>');
//                                    tableRow.remove();
//                                    clearEducationFields();
//                                } else {
//                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
//                                    window.scrollTo(0, 0);
//                                }
//                            },
//                            error: function () {
//                                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
//                                window.scrollTo(0, 0);
//                            }
//                        });
//                    }
//                });
//            }
//
//            function clearEducationFields() {
//                $('#education_level').val('');
//                $('#institute').val('');
//            }

//            function zeroRegionalTargetSubmit() {
//
//                $('#msg_dev').empty();
//                var dataObject = new Object();
//                dataObject.targetid = $('#targetid').val();
//                dataObject.multizeroregional = $('#multizeroregional').val();
//                console.log(dataObject);
//                var content = JSON.stringify(dataObject);
//                console.log(content);
//                $.ajax({
//                    type: "post",
//                    url: "${pageContext.servletContext.contextPath}/target/regionaltarget",
//                    cache: false,
//                    data: {regionaltarget_info: content},
//                    success: function (response) {
//                        response = JSON.parse(response);
//                        if (response.CODE === "SUCCESS") {
//                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
//                            $('#collapseOne').collapse('hide');
//                            $('#collapseTwo').collapse('show');
//                        } else {
//                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
//                            window.scrollTo(0, 0);
//                        }
//
//                    },
//                    error: function () {
            //                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
            //                        window.scrollTo(0, 0);
//                    }
//                });
//            }



            function loadRegionList() {
                $("#appendId").empty();
                $("#regionalid").empty();
                $("#multiregional").empty();

                $('#msg_dev').empty();
                var dataObject = new Object();
                dataObject.targetid = $('#targetiddropdown').val();
                console.log(dataObject);
                var content = JSON.stringify(dataObject);
                console.log(content);
                $.ajax({type: "POST",
                    url: "${pageContext.servletContext.contextPath}/loadregionlist",
                    cache: false,
                    async: false,
                    data: {regional_info: content}, success: function (response) {
                        response = JSON.parse(response);
                        console.log(response);
                        for (var i = 0; i < response.notasssignregion.length; i++) {
                            $('#regionalid').append($("<option></option>").attr("value", response.notasssignregion[i].regionid).text(response.notasssignregion[i].regiondes));
                        }
                        console.log(response.target);
                        generateTargetNotAssignRegionArray();
                        $("#regiontarget").val(response.target);
                        $("#organizattionWStarget").val("");
                        $("#organizattionWStarget").empty();
                        $("#organizattionWStarget").append(response.target);
                    },
                    error: function () {
                        console.log('Error while request..');
                    }
                });
            }


            function loadAssignRegionList() {

                $('#msg_dev').empty();
                var dataObject = new Object();
                dataObject.targetid = $('#targetid').val();
                console.log(dataObject);
                var content = JSON.stringify(dataObject);
                console.log(content);
                $.ajax({
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/assignreagion",
                    cache: false,
                    data: {regional_info: content}, success: function (response) {
                        response = JSON.parse(response);
                        console.log(response);
                        for (var i = 0; i < response.asssignregion.length; i++) {
                            $('#assignreagion').append($("<option></option>").attr("value", response.asssignregion[i].regionid).text(response.asssignregion[i].regiondes));
                        }
                    },
                    error: function () {
                        console.log('Error while request..');
                    }
                });
//                }
            }

//            function TargetSubmit() {
////                alert("page submit");
//                $('#msg_dev').empty();
//                var dataObject = new Object();
//                dataObject.targetid = $('#targetid').val();
//                dataObject.regionalid = $('#selectregion').val();
//                dataObject.regiontarget = $('#regiontarget').val();
//                console.log(dataObject);
//                var content = JSON.stringify(dataObject);
//                console.log(content);
//                $.ajax({
//                    type: "post",
//                    url: "${pageContext.servletContext.contextPath}/regiontarget",
//                    cache: false,
//                    data: {target_info: content},
//                    success: function (response) {
//                        response = JSON.parse(response);
//                        if (response.CODE === "SUCCESS") {
//                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
//                        } else {
//                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
//                            window.scrollTo(0, 0);
//                        }
//                    },
//                    error: function () {
//                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
//                        window.scrollTo(0, 0);
//                    }
//                });
//
//            }


//            jQuery.validator.addMethod("amount", function () {
//                var amount = 0;
//                $("input[class=regionclass]").each(function () {
//                    amount += parseInt($(this).val());
////                    alert(amount);
//                    console.log("total amount" + amount);
//                });
//                console.log("regional target" + $("#regiontarget").val().toString());
//                if (amount === $("#regiontarget").val().toString()) {
            //                    return true;
//                } else {
            //                    return false;
            //                }
            //            }, jQuery.validator.format("Please select atleast one branch."));
// 

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
                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 0 && state !== "complete active") {
                            pageSubmit();


                        }
//
                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 1) {
                            regiondropdowncreate();
                            generateTargetArray();


                            $("#targetiddropdown").rules("add", {
                                required: true
                            });
                            $('[name*="name"]').each(function () {
                                $(this).rules('add', {
                                    number: true
                                });
//
                            });
//                          
                            var $valid = $("#wizard-1").valid();
                            if (!$valid) {
                                return false;
                            }



                        }
                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 2) {
                            $("#selectedregion").rules("add", {
                                required: true
                            });

                            var $valid = $("#wizard-1").valid();
                            if (!$valid) {
                                return false;
                            }
                            generateBranchTargetArray();

                        }

                        if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 3) {
                            //                            alert("last steo");
                            var $valid = $("#wizard-1").valid();
                            if (!$valid) {
                                return false;
                            }
                            tets();
                        }

//                        alert($('#bootstrap-wizard-1').bootstrapWizard('currentIndex'));
                        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).addClass(
                                'complete');
                        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).find('.step')
                                .html('<i class="fa fa-check"></i>');




                    }
                }
            });


            function addTextBox(name, id) {
//                alert(id);
                if (id !== undefined) {
                    counter++;
//                    $("#fileTable").append('<tr id="' + name + '"><td style="width: 1550px;">' +
//                            '<div class="col-xs-7">' +
//                            '<section>' +
//                            '<div class="form-group">' +
//                            '<label class="label col-sm-4">' + name + '</label>' +
//                            '<label class = "input col-sm-4" style="margin-bottom:10px">' +
//                            '<input type="text"  required="required" style="margin-left:30px" class="regionclass" onchange="calcuation();"  id="' + id + '" name="name" "></br>' +
//                            '</label>' +
//                            '</div>' +
//                            '</section>' +
//                            '</div>' +
//                            '<br>' +
//                            '</td></tr>');

//                    $("#fileTable").append(
//                            '<div class="row">'+
//                            '<div class="col-xs-4">' +
//                            '<section>' +
//                            '<div class="form-group">' +
//                            '<label class="label col-sm-4">' + name + '</label>' +
//                            '<label class = "input col-sm-6" style="margin-bottom:10px">' +
//                            '<input type="text"  required="required" style="margin-left:30px" class="regionclass" onchange="calcuation();"  id="' + id + '" name="name" "></br>' +
//                            '</label>' +
//                            '</div>' +
//                            '</section>' +
//                            '</div>' +
//                            '<br>');

//                        $("#appendId").append('<div class="row"><div class="form-control">' +
//                        '<label class="control-label col-sm-2" for="pwd">Password:</label>'+
//                        '<div class="col-sm-5">'+
//                        '<input type="password" required="required" class="form-control" id="pwd" placeholder="Enter password">'+
//                        '</div>'+
//                        '</div></div>');


                    $("#appendId").append(
                            '<div class="row" Id="' + name + '" style="margin-bottom:10px">' +
                            '<div class="col-xs-2">' +
                            '<label class="control-label col-sm-2">' + name + '</label>' +
                            '</div>' +
                            '<div class="col-xs-5">' +
                            '<input type="text"  classfn="regionclass"  required="true" name="name' + id + '" id="' + counter + '"  onchange="calcuation();" style="width:258px" class="form-control" >' +
                            '</div>' +
                            '</div>');


                }
            }


            function removeTextBox(name) {
                alert(name);
                $("#" + name).remove();
                counter--;
            }

            function generateTargetArray() {
                //                alert("targete array");
                var array = [];
                $('[name*="name"]').each(function () {
                    array.push({
                        ID: $(this).attr("id"),
                        TARGET: $(this).val()
                    });
                });
                var jsonString = JSON.stringify(array);
                $("#regionaltargetlist").val(jsonString);
                console.log("Targetr Array" + jsonString);

            }
            function regiondropdowncreate() {
                $("#multiregional option").each(function () {

                    $("#selectedregion").append($("<option value='" + $(this).val() + "'>" + $(this).text() + "</option>"));
                    var newTextBoxDiv = $(document.createElement('table')).attr("id", '' + $(this).text() + '');
                    $("#container").append(newTextBoxDiv);
                });
            }
            function appenTarget() {


                var name = $("#selectedregion option:selected").val();
                $("#oneregiontarget").val($("input[id=" + name + "]").val());
                $("#branchtarget").empty();
                $("#branchtarget").append($("input[id=" + name + "]").val());

//                var value = false;
//                $("#multibranch option").each(function () {
//                    alert($(this).val().length);
//                    if ($(this).val().length === 0) {
//                        alert("true");
//                        value = true;
//                    } else {
//                        value = false;
//                    }
//                });
//                alert("value" + value);
//                if (!value) {
//                    var $valid = $("#wizard-1").valid();
//                    if ($valid) {
//                        loadBranchList();
//                    }
//
//                } else {
//                    loadBranchList();
//                }
loadBranchList();
            }

            function loadBranchList() {
                $('#branchid').empty();
                var dataObject = new Object();
                dataObject.regionalid = $("#selectedregion option:selected").val();
                console.log(dataObject);
                var content = JSON.stringify(dataObject);
                console.log(content);
                $.ajax({type: "POST",
                    url: "${pageContext.servletContext.contextPath}/loadbranchlist",
                    cache: false,
                    data: {regional_info: content}, success: function (response) {
                        response = JSON.parse(response);
                        console.log(response);
                        for (var i = 0; i < response.branchlist.length; i++) {
                            $('#branchid').append($("<option></option>").attr("value", response.branchlist[i].branchId).text(response.branchlist[i].aliasName));
                        }
                        generateTargetNotAssignBranchArray();
                    },
                    error: function () {
                        console.log('Error while request..');
                    }
                });
                //                }
            }

            $('#Bpull_right').click(function () {

                $("#multibranch").rules('add', {
                    checkallreadyin: true
                });
                if ($("#multibranch").valid()) {
                    var selectedItem = $("#branchid option:selected");
                    $("#multibranch").append(selectedItem);
                    selectedItem.prop("selected", false);
                    var region = $("#selectedregion option:selected").text();
                    addBranchTextBoxAdd(selectedItem.text(), selectedItem.val());
                    generateBranchArray();
                }

            });
            $('#Bpull_left').click(function () {
                var selectedItem = $("#multibranch option:selected");
                $("#branchid").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
                removeTextBoxTAB3(selectedItem.val());

            });
            $('#branchid').dblclick(function () {
                $("#multibranch").rules('add', {
                    checkallreadyin: true
                });
                if ($("#multibranch").valid()) {
                    var selectedItem = $("#branchid option:selected");
                    $("#multibranch").append(selectedItem);
                    selectedItem.prop("selected", false);
                    var region = $("#selectedregion option:selected").text();
                    addBranchTextBoxAdd(selectedItem.text(), selectedItem.val());
                    generateBranchArray();
                    generateTargetNotAssignBranchArray();
                }
            });
            $('#multibranch').dblclick(function () {
                var selectedItem = $("#multibranch option:selected");
                $("#branchid").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();

                removeTextBoxTAB3(selectedItem.val());
            });
            function resetMultiBranchSelect() {
                $('#multiregionalarray').val();
                $('#selectregion option').prop('selected', true);
                var selectedItem = $('#selectregion option:selected');
                $('#assignreagion').append(selectedItem);
                selectedItem.prop('selected', false);
                sortSelect('#assignreagion', 'text', 'asc');
            }

            function generateBranchArray() {
                var region = [];
                $("#branchid option").each(function () {
                    region.push($(this).val());
                });
                $('#multibrancharray').val(JSON.stringify(region));
            }


            function generateTargetNotAssignBranchArray() {
                var region = [];
                $("#multibranch option").each(function () {
                    region.push($(this).val());
                });
                $('#targetnotassignbranch').val(JSON.stringify(region));
            }


            jQuery.validator.addMethod("multibranch", function () {
                $('#multisection option').prop('selected', true);
                var selectedItem = $('#multisection option:selected');
                console.log(selectedItem.length);
                if (selectedItem.length > 0) {
                    return true;
                } else {
                    return false;
                }
            }, jQuery.validator.format("Please select atleast one subsection."));


            function generateBranchTargetArray() {

                var Barray = [];
                $('[name*="feild"]').each(function () {
                    Barray.push({
                        ID: $(this).attr("id"),
                        RID: $(this).attr("rid"),
                        TARGET: $(this).val()
                    });
                });
                var jsonString2 = JSON.stringify(Barray);
                $("#brachtargetlist").val(jsonString2);
                console.log("Targetr Array" + jsonString2);


            }


            function tets() {
                var dataObject = new Object();
                dataObject.targetnotassignregion = $("#targetnotassignregion").val();
                dataObject.targetnotassignbranch = $("#targetnotassignbranch").val();
                dataObject.multiregionalarray = $("#multiregionalarray").val();
                dataObject.multibrancharray = $("#multibrancharray").val();
                dataObject.regionaltargetlist = $("#regionaltargetlist").val();
                dataObject.brachtargetlist = $("#brachtargetlist").val();
                dataObject.targetid = $("#targetiddropdown").val();
                console.log(dataObject);
                var content = JSON.stringify(dataObject);
                console.log(content);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/tets",
                    cache: false,
                    data: {regional_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        if (response.CODE === "SUCCESS") {
                            $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
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

            function addBranchTextBoxAdd(name, id) {
//                counter++
                if (id !== undefined) {
                    var rid = $("#selectedregion option:selected").val();
//                    $("#fileTable2").append('<tr id="' + name + '"><td style="width: 1550px; ">' +
//                            '<div class="col-xs-3">' +
//                            '<section>' +
//                            '<label class="label">' + name + '</label>' +
//                            '<label class = "input">' +
//                            '<input type="text" class="regionclass2"   style="width:374px;" required="true" onchange="BranchTargetcalcuation();" name="name1" cls="branches" rid="' + rid + '" id="' + id + '">' +
//                            '</label>' +
//                            '</section>' +
//                            '</div>' +
//                            '<br>' +
//                            '</td></tr>');

//                    $("#fileTable2").append('<tr id="' + name + '">' +
//                            '<td style="width: 1550px; ">' +
//                            '<div class="col-xs-3">' +
//                            '<label class="label">' + name + '</label>' +
//                            '</div>' +
//                            '</td>' +
//                            '<td>' +
//                            '<div class="col-xs-3">' +
//                            '<section>' +
//                            '<label class = "input">' +
//                            '<input type="text" class="regionclass2"   style="width:374px;" required="true" onchange="BranchTargetcalcuation();" name="name1" cls="branches" rid="' + rid + '" id="' + id + '">' +
                    //                            '</label>' +
                    //                            '</section>' + //                            '</div>' +
                    //                            '<br>' +
                    //                            '</td></tr>');


//                    $("input[class=regionclass2]").each(function () {
//                        $("#" + this.id).rules('add', {
//                            required: true,
//                            number: true
//                        });
//                    });

                    $("#appendId2").append(
                            '<div class="row" Id="D' + id + '" style="margin-bottom:10px">' +
                            '<div class="col-xs-2">' +
                            '<label class="control-label col-sm-2">' + name + '</label>' +
                            '</div>' +
                            '<div class="col-xs-5">' +
                            '<input type="text"  classfn="regionclass" rid="' + rid + '" required="true" name="feild' + id + '" id="' + id + '"  onchange="BranchTargetcalcuation();" style="width:258px" class="form-control" >' +
                            '</div>' +
                            '</div>');


                }
//            }
            }

            function calcuation() {
                $("#totalvalue").val();
                var sum = 0;
                $('[name*="name"]').each(function () {
                    sum += parseInt($(this).val());
                });
                $("#totalvalue").val(sum);
            }


            function BranchTargetcalcuation() {
                var sum = 0;
                $('[name*="feild"]').each(function () {
                    sum += parseInt($(this).val());
                });
                $("#totalregion").val(sum.toString());
            }

            function loadTargetDroupDown() {
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${pageContext.servletContext.contextPath}/target/targetdropdown",
                    cache: false, success: function (response) {
                        response = JSON.parse(response);
                        console.log(response);
                        for (var i = 0; i < response.targetdropdown.length; i++) {
                            $('#targetiddropdown').append($("<option></option>").attr("value", response.targetdropdown[i].targetid).text(response.targetdropdown[i].tragetdes));
                        }

                    },
                    error: function () {
                        console.log('Error while request..');
                    }
                });
            }

            jQuery.validator.addMethod("checkallreadyin", function () {
                var selectedItem = $("#branchid option:selected");
                alert("sletcdbrnaid" + selectedItem.val());
                var retern = false;
                $("#multibranch option").each(function () {
                    if (selectedItem.val() !== $(this).val()) {
                        retern = true;
                    } else {
                        retern = false;
                    }
                });
                
                return retern;

            }, jQuery.validator.format("Allready Select this item.."));


            function removeTextBoxTAB3(id) {
                $("#D" + id).remove();

            }
            
            function activedatime(){
                 $('#datetimepickerfromdate').removeAttr('disabled');
            }

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
                    },
                    nuberofcontact: {
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
                    regiontarget: {
                        required: true
                    },
                    totalvalue: {
                        equalTo: "#regiontarget"
                    },
                    revenue: {
                        required: true,
                        number: true
                    },
                    oneregiontarget: {
                        required: true
                    },
                    totalregion: {
                        equalTo: "#oneregiontarget"
                    },
                    multiregionalvalidation: {
                        required: true
                    },
                    targetid: {
                        required: true
                    },
                    targettypeid: {
                        required: true
                    },
                    
//                    activitytypeid: {
//                        required: true
//                    }

                }, messages: {
                    totalvalue: {
                        equalTo: "Total Value must equal to organization target"
                    },
                    totalregion: {
                        equalTo: "Total Value must equal to branch target"
                    }
                },
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });

        </script>
    </body>

</html>
