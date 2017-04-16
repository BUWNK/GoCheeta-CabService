<%-- 
    Document   : targetsetting
    Created on : May 6, 2016, 2:55:21 PM
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
                <form:form class="smart-form" id="useRoleSection" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userolesection" method="post" >
                    <form:input  type="text" id="setrevenue" path="revenue"/>
                    <form:input  type="text" id="targetid" path="targetid"/>
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
                                <label class="label">Product Wise<samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="productid" path="productid" items="${productList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>

                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Target Group<samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="targergroupid" path="targergroupid" items="${userRoleList}" onchange="SectionList('${pageContext.servletContext.contextPath}')"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>



                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Contact<samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input path="nuberofcontact" type="text" name="nuberofcontact" placeholder="Customer Name" value="${fullname}" /><i></i>
                                </label>
                            </section>  
                        </div>

                        <div class="col-xs-1"></div>

                        <div class="col-xs-3">
                            <section>
                                <label class="label">Revenue<samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input path="revenue" type="text" name="revenue" placeholder="Customer Name"  /><i></i>
                                </label>
                            </section>  
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Target Period<samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="targetperiodid" path="targetperiodid" items="${sectionList}" onchange="SectionList('${pageContext.servletContext.contextPath}')"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Description<samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input path="tragetdes" type="text" name="tragetdes" placeholder="Customer Name" value="${fullname}" /><i></i>
                                </label>
                            </section>  
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <div class="form-group">
                                <form:label path="">Target Start Date:<samp style="color: red">*</samp></form:label>
                                <form:input path="targetstartdate" type="text" id="datetimepickerfromdate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
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
                        <br><br>
                        <div class="row">
                            <div class="col-xs-1"></div>
                            <div class="col-xs-10">
                                <footer style="background-color: #ffffff">
                                <form:button type="button" class="btn btn-primary" disabled="${avn_create}" id ="pagesubmit" onclick="pageSubmit();">Save</form:button>  
                                </footer>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>


















                </form:form>
                <!-- END OF FORM -->
                <!-- START MODAL -->

                <!-- END MODAL -->                                        
                <div class="row">


                    <div class="col-xs-1"></div>
                    <!--<h2 class="row-seperator-header"><i class="fa fa-th-list"></i> Accordions </h2>-->
                    <!--NEW WIDGET START--> 
                    <article class="col-xs-10">
                        <!--Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
                            <!--widget options:-->
                            <!--usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">-->
                            <!--                                data-widget-colorbutton="false"
                                                            data-widget-editbutton="false"
                                                            data-widget-togglebutton="false"
                                                            data-widget-deletebutton="false"
                                                            data-widget-fullscreenbutton="false"
                                                            data-widget-custombutton="false"
                                                            data-widget-collapsed="true"
                                                            data-widget-sortable="false"-->

                            <header>
                                <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                                <h2>Accordions </h2>
                            </header>
                            <!--widget div-->
                            <div></div>
                            <!--widget edit box--> 
                            <div class="jarviswidget-editbox">
                                <!--This area used as dropdown edit box--> 
                            </div>
                            <!--end widget edit box--> 
                            <!--widget content--> 
                            <div class="widget-body">
                                <div class="panel-group smart-accordion-default" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color: #F2F1F1">
                                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i>Set Regional Wise Target </a></h4>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse">
                                            <div class="panel-body">

                                                <form:form class="smart-form" id="regionTargetSetting" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userolesection" method="post" >
                                                    <form:input  type="text" id="multiregionalarray" path="multiregionalarray"/>
                                                    <form:input  type="text" id="multizeroregional" path="multizeroregional"/>
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
                                                                    <form:select id="targetid" path="targetid" items="${targetdropdown}" onchange="loadRegionList()"/><i></i>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </div>

                                                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3" id="sectionids">
                                                            <section>
                                                                <label class="label">Region</label>
                                                                <label class="select">
                                                                    <form:select id="regionalid" path="regionalid" multiple="true" style="height:100px;"/>
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




                                                    <footer style="background-color: #ffffff">
                                                        <form:button id="checkOne" type="button" onClick="zeroRegionalTargetSubmit()" class="btn btn-primary">Save & Move Next</form:button>
                                                        </footer>
                                                </form:form>

                                            </div>
                                        </div>

                                    </div>
                                    <br>
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color: #F2F1F1">
                                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEight" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i>Set  Branch Wise Target</a></h4>
                                        </div>
                                        <div id="collapseEight" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <form:form class="smart-form" id="regionTargetSetting" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userolesection" method="post" >
                                                    <form:input  type="text" id="multiregionalarray" path="multiregionalarray"/>
                                                    <form:input  type="text" id="multizeroregional" path="multizeroregional"/>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <form:input class="form-control" path="tragetdes" placeholder="Target Description..." type="text" id="TDes"  ondblclick="loadRegionList();" cssStyle="padding-left:10px;"/>
                                                                <div id="log" class="font-xs margin-top-10 text-danger"></div>
                                                            </section>  
                                                        </div>
                                                    </div>

                                                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3" id="sectionids">
                                                            <section>
                                                                <label class="label">Region</label>
                                                                <label class="select">
                                                                    <form:select id="assignreagion" path="regionalid" multiple="true" style="height:100px;"/>
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
                                                                        <button id="ARegionpull_right" type="button" class="btn btn-xs">>></button>
                                                                    </section>
                                                                    <section>
                                                                        <button id="ARegionpull_left" type="button" class="btn btn-xs"><<</button>
                                                                    </section>
                                                                </div>
                                                                <div class="col-xs-5"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label" style="height:19px;"></label>
                                                                <label class="select">
                                                                    <form:select id="selectregion" path="multiregional"  multiple="true" style="height:100px;"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Revenue<samp style="color: red">*</samp></label>
                                                                <label class="input">
                                                                    <form:input path="revenue" type="text" name="revenue" placeholder="Customer Name"  /><i></i>
                                                                </label>
                                                            </section>  
                                                        </div>

                                                    </div>     
                                                    <footer style="background-color: #ffffff">
                                                        <form:button id="checkOne" type="button" onClick="zeroRegionalTargetSubmit()" class="btn btn-primary">Save & Move Next</form:button>
                                                        </footer>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color: #F2F1F1">
                                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEight" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i>Set Individual Wise Target</a></h4>
                                        </div>
                                        <div id="collapseEight" class="panel-collapse collapse">
                                            <div class="panel-body">

                                            </div>
                                        </div>
                                    </div>
                                    <br>

                                </div>
                            </div>
                        </div>
                    </article>
                </div>

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

                $('#useRoleSection').validate({
                    rules: {
                        userroleid: {
                            required: true
                        },
                        multisection: {
                            //                            multisectionvalidate: true
                        }
                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }

                });
            });

            $('#Regionpull_right').click(function () {
                var selectedItem = $("#regionalid option:selected");
                $("#multiregional").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
            });
            $('#Regionpull_left').click(function () {
                var selectedItem = $("#multiregional option:selected");
                $("#regionalid").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
            });
            $('#regionalid').dblclick(function () {
                var selectedItem = $("#regionalid option:selected");
                $("#multiregional").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
            });
            $('#multiregional').dblclick(function () {
                var selectedItem = $("#multiregional option:selected");
                $("#regionalid").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
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

            jQuery.validator.addMethod("multiregional", function () {
                $('#multiregional option').prop('selected', true);
                var selectedItem = $('#multiregional option:selected');
                console.log(selectedItem.length);
                if (selectedItem.length > 0) {
                    return true;
                } else {
                    return false;
                }
            }, jQuery.validator.format("Please select atleast one region."));

            function pageSubmit() {
                $('#msg_dev').empty();
                if ($("#useRoleSection").valid()) {
                    var dataObject = new Object();
                    dataObject.tragetdes = $('#tragetdes').val();
                    dataObject.productid = $('#productid').val();
                    dataObject.targergroupid = $('#targergroupid').val();
                    dataObject.revenue = $('#revenue').val();
                    dataObject.targetperiodid = $('#targetperiodid').val();
                    dataObject.targetstartdate = $('#datetimepickerfromdate').val();
                    dataObject.targetenddate = $('#datetimepickerfromdate').val();
                    console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    console.log(content);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/target/created",
                        cache: false,
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
                }
            }


            $('#datetimepickerfromdate').datetimepicker().on('changeDate', function (ev) {
                var date = new Date(), yy = date.getFullYear(), mm = date.getMonth();
                dd = date.getDate();
                if ($('#targetperiodid').val() == 1) {
                    alert("1");
                    mm = mm + 1;
                    dd = 1;
                }
                if ($('#targetperiodid').val() == 2) {
                    mm = m + 3;
                    dd = 1;
                }
                if ($('#targetperiodid').val() == 3) {
                    alert("3");
                    yy = y + 1;
                    mm = m + 2;
                    d = 1;
                }
                $('#datetimepickertodate').datetimepicker('setDate', new Date(yy, mm, 1));
            });

            $("#TDes").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: "${pageContext.servletContext.contextPath}/target/targetdropdown",
                        dataType: "json", data: {
                            name_startsWith: request.term
                        },
                        success: function (data) {

                            response($.map(data, function (item) {
                                console.log(data);
                                return {
                                    label: item.tragetdes,
                                    value: item.tragetdes
                                }
                            }));
                        }
                    });
                },
                minLength: 2,
                select: function (event, ui) {
                    console.log(this.value);
                    //                   
                }
            });

            function selectedRegionlTargeRow(row) {
                var $row = jQuery(row).closest('tr');
                var $columns = $row.find('td');
                jQuery.each($columns, function (i, item) {
                    if (i === 4) {
                        $('#education_level').val(item.innerHTML);
                    } else if (i === 5) {
                        $('#institute').val(item.innerHTML);
                    }
                });
            }


            function deleteRegionlTargetRow(row) {
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
                            data: {target_id: tableDataList[3].innerText, account_id: $('#account_id').val()},
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

            function clearEducationFields() {
                $('#education_level').val('');
                $('#institute').val('');
            }


            $('#ARegionpull_right').click(function () {
                var selectedItem = $("#assignreagion option:selected");
                $("#selectregion").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
            });
            $('#ARegionpull_left').click(function () {
                var selectedItem = $("#selectregion option:selected");
                $("#assignreagion").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
            });
            $('#regionalid').dblclick(function () {
                var selectedItem = $("#assignreagion option:selected");
                $("#selectregion").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
            });
            $('#selectregion').dblclick(function () {
                var selectedItem = $("#selectregion option:selected");
                $("#assignreagion").append(selectedItem);
                selectedItem.prop("selected", false);
                generateRegionArray();
                generateZeroBranchArray();
            });

            function resetMultiBranchSelect() {
                $('#multiregionalarray').val();
                $('#selectregion option').prop('selected', true);
                var selectedItem = $('#selectregion option:selected');
                $('#assignreagion').append(selectedItem);
                selectedItem.prop('selected', false);
                sortSelect('#assignreagion', 'text', 'asc');
            }

            function generateRegionArray() {
                var section = [];
                $("#multiregional option").each(function () {
                    section.push($(this).val());
                });
                $('#multiregionalarray').val(JSON.stringify(section));
            }


            function generateZeroBranchArray() {
                var region = [];
                $("#regionalid option").each(function () {
                    region.push($(this).val());
                });
                $('#multizeroregional').val(JSON.stringify(region));
            }


            jQuery.validator.addMethod("multisectionvalidate", function () {
                $('#multisection option').prop('selected', true);
                var selectedItem = $('#multisection option:selected');
                console.log(selectedItem.length);
                if (selectedItem.length > 0) {
                    return true;
                } else {
                    return false;
                }
            }, jQuery.validator.format("Please select atleast one subsection."));



            function zeroRegionalTargetSubmit() {
                $('#msg_dev').empty();
                if ($("#useRoleSection").valid()) {
                    var dataObject = new Object();
                    dataObject.tragetdes = $('#TDes').val();
                    dataObject.multizeroregional = $('#multizeroregional').val();
                    console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    console.log(content);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/target/regionaltarget",
                        cache: false,
                        data: {regionaltarget_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');

//                                $('#collapseOne').collapse('hide');
//                                $('#collapseTwo').collapse('show');

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



            function loadRegionList() {
                $('#msg_dev').empty();
                if ($("#useRoleSection").valid()) {
                    var dataObject = new Object();
                    dataObject.targetid = $('#targetid').val();
                    console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    console.log(content);
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/loadregionlist",
                        cache: false,
                        data: {regional_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            console.log(response);
                            for (var i = 0; i < response.notasssignregion.length; i++) {
                                $('#regionalid').append($("<option></option>").attr("value", response.notasssignregion[i].regionid).text(response.notasssignregion[i].regiondes));
                            }
                            //                            for (var i = 0; i < response.assignsubsections.length; i++) {
                            //                                $('#multiregional').append($("<option></option>").attr("value", response.assignsubsections[i].regionid).text(response.assignsubsections[i].regiondes));
                            //                            }
                            //        
                            //                                                generateBranchArrayOld();
                            generateZeroBranchArray();
                            $("#setrevenue").val("2000");
                            loadAssignRegionList();

                        },
                        error: function () {
                            console.log('Error while request..');
                        }
                    });
                }
            }



            function loadAssignRegionList() {
                $('#msg_dev').empty();
                if ($("#useRoleSection").valid()) {
                    var dataObject = new Object();
                    dataObject.targetid = $('#targetid').val();
                    console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    console.log(content);
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/assignreagion",
                        cache: false,
                        data: {regional_info: content},
                        success: function (response) {
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
                }
            }




            $('#bootstrap-wizard-1').bootstrapWizard({
                'tabClass': 'form-wizard',
                'onNext': function (tab, navigation, index) {
                    var $valid = $("#wizard-1").valid();
                    if (!$valid) {
                        $validator.focusInvalid();
                        return false;
                    } else {
                        alert($('#bootstrap-wizard-1').bootstrapWizard('currentIndex'));
                        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).addClass(
                                'complete');
                        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).find('.step')
                                .html('<i class="fa fa-check"></i>');



                    }
                }
            });



            function RegionalTargetSubmit() {
                $('#msg_dev').empty();
                if ($("#useRoleSection").valid()) {
                    var dataObject = new Object();
                    dataObject.tragetdes = $('#TDes').val();
                    dataObject.multizeroregional = $('#multizeroregional').val();
                    console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    console.log(content);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/target/regionaltarget",
                        cache: false,
                        data: {regionaltarget_info: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');

//                                $('#collapseOne').collapse('hide');
//                                $('#collapseTwo').collapse('show');

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


        </script>  

        <!----><!--       
        <!---->        </body>

</html>
