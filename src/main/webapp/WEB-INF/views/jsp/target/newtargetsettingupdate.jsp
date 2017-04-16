/* global ids */

<%-- 
    Document   : targetview
    Created on : May 9, 2016, 3:32:41 PM
    Author     : ISURU
--%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
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
                                View Target
                            </span>
                        </h1>
                    </div>
                </div>
                <div class="jarviswidget" id="wid-id-8" data-widget-editbutton="false" data-widget-custombutton="false">
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
                        <span class="widget-icon"> <i class="fa fa-edit"></i> </span>
                        <h2>Target View</h2>				

                    </header>

                    <!-- widget div-->
                    <div>

                        <!-- widget edit box -->
                        <div class="jarviswidget-editbox">
                            <!-- This area used as dropdown edit box -->

                        </div>
                        <!-- end widget edit box -->

                        <!-- widget content -->
                        <div class="widget-body no-padding">

                            <form:form class="smart-form" id="targetUpdate" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="targetUpdate" method="post" >
                                <form:hidden  id="multiregionalarray" path="multiregionalarray"/>
                                <form:hidden  id="targetnotassignregion" path="targetnotassignregion"/>
                                <form:hidden  id="multibrancharray" path="multibrancharray"/>
                                <form:hidden   id="targetnotassignbranch" path="targetnotassignbranch"/>
                                <form:hidden  id="regionaltargetlist" path="regionaltargetlist"/>
                                <form:hidden  id="brachtargetlist" path="brachtargetlist"/>
                                <form:hidden  id="targetid" path="targetid" value=""/>
                                <form:hidden  id="oldtaget" path="oldtaget"/>
                                <form:hidden  id="oneregiontarget"  path="regiontarget"  />
                                <form:hidden  id="regiontarget" path="regiontarget"/>


                                <header>Organization Target</header>

                                <fieldset>					

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
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Target Amount<samp style="color: red">*</samp></label>
                                                    <label class="input">
                                                        <form:input path="revenue" type="text" name="revenue" placeholder="Customer Name" onchange="targetchange()"  /><i></i>
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
                                                        <form:input path="tragetdes" type="text" name="tragetdes" placeholder="Customer Name" value="${fullname}" /><i></i>
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
                                            <div class="col-xs-2"></div>
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

                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Target Period<samp style="color: red">*</samp></label>
                                                    <label class="select">
                                                        <form:select id="targetperiodid" path="targetperiodid" items="${sectionList}"/>
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
                                                    <form:input path="targetstartdate" type="text" id="datetimepickerfromdate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                    </div>
                                                </div>
                                                <div class="col-xs-2"></div>
                                                <div class="col-xs-3">
                                                    <div class="form-group">
                                                    <form:label path="">Target End Date:<samp style="color: red">*</samp></form:label>
                                                    <form:input path="targetenddate" disabled="true" type="text" id="datetimepickertodate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                    </div>
                                                </div>
                                            </div>


                                        </div>


                                        <header>Regional Target</header>
                                        <%--<c:forEach var = "current" items = "${regionaltarget}">--%>  


                                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3" id="sectionids">
                                            <section>
                                                <label class="label">Region</label>
                                                <label class="select">
                                                    <form:select id="regionalid" path="regionalid" multiple="true" items="${notassignreagion}" style="height:100px;" />
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
                                                    <form:select id="multiregional" path="multiregional" items="${assignreagion}"  multiple="true" style="height:100px;"/>
                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>

                                    </div>
                                    <div id="appendsection">
                                        <%
                                            JSONArray array = (JSONArray) session.getAttribute("regionaltarget");
                                            JSONObject jaray = new JSONObject();
                                            //                                            List<Section> sections = (List<Section>) session.getAttribute("pagehierarchy");
                                            for (int i = 0; i < array.length(); i++) {
                                                jaray = array.getJSONObject(i);


                                        %>
                                        <div class="row" id="regionarea">
                                            <% if (i % 2 == 0) {%>

                                            <!--<div class="row">-->
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section id="<%=jaray.getString("region")%>">
                                                    <label class="label"><%=jaray.getString("region")%><samp style="color: red">*</samp></label>
                                                    <label class="input">
                                                        <input path="tragetdes" id="<%=jaray.getString("regionid")%>" type="text" name="name" placeholder="Customer Name" onchange="calcuation();" value="<%=jaray.getString("target")%>" /><i></i>
                                                    </label>
                                                </section>  
                                            </div> 
                                            <% }%>

                                            <% if (i % 2 == 1) {%>

                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section id="<%=jaray.getString("region")%>">
                                                    <label class="label"><%=jaray.getString("region")%><samp style="color: red">*</samp></label>
                                                    <label class="input">
                                                        <input path="tragetdes" id="<%=jaray.getString("regionid")%>" type="text" name="name" placeholder="Customer Name" onchange="calcuation();" value="<%=jaray.getString("target")%>" /><i></i>
                                                    </label>
                                                </section>  
                                            </div> 
                                            <!--</div>-->
                                            <% } %>
                                        </div>
                                        <% }%>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-2"></div>
                                        <div id="appendId" class="col-xs-6">   
                                        </div> 

                                    </div> 

                                    <div  class="row">
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
                                                    <form:input  id="totalregionvalue"  path="totalvalue"  /><i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>

                                    <br><br><br><br>
                                    <header>Branch Target</header> 
                                    <div class="row">
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Region <samp style="color: red">*</samp></label>

                                                <label class="select">
                                                    <form:select id="reigondropdown" path="regionalid"  onchange="loadBranchList()">
                                                        <form:option value="" label="--- Select ---"/>
                                                        <form:options items="${assignreagion}" />
                                                    </form:select><i></i>

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
                                                    <form:select id="branchid" path="branchid" multiple="true" items="${notassignbranc}" style="height:100px;"/>
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
                                                    <form:select id="multibranch" path="multibranch"  items="${assignbranc}" multiple="true" style="height:100px;"/>

                                                </label>
                                            </section>
                                        </div>
                                        <div class="col-xs-2"></div>

                                    </div>
                                    <%
                                        JSONArray Barray = (JSONArray) session.getAttribute("branchtarget");
                                        JSONObject bobj = new JSONObject();
                                        //                                            List<Section> sections = (List<Section>) session.getAttribute("pagehierarchy");
                                        for (int i = 0; i < Barray.length(); i++) {
                                            bobj = Barray.getJSONObject(i);
                                    %>
                                    <div class="row" id="selectregion">
                                        <% if (i % 2 == 0) {%>
                                        <!--<div class="row">-->
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section id="D<%=bobj.getString("brnachid")%>">
                                                <label class="label"><%=bobj.getString("branch")%><samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <input id="<%=bobj.getString("brnachid")%>" rid="<%=bobj.getString("regionid")%>" type="text" name="feild<%=bobj.getString("regionid")%>" placeholder="Customer Name" value="<%=bobj.getString("target")%>"  onchange="BranchTargetcalcuation();"/><i></i>
                                                </label>
                                            </section>  
                                        </div> 




                                        <% }%>

                                        <% if (i % 2 == 1) {%>

                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section id="D<%=bobj.getString("brnachid")%>">
                                                <label class="label"><%=bobj.getString("branch")%><samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <input id="<%=bobj.getString("brnachid")%>" rid="<%=bobj.getString("regionid")%>" type="text" name="feild<%=bobj.getString("branch")%>" placeholder="Customer Name" value="<%=bobj.getString("target")%>" onchange="BranchTargetcalcuation();" /><i></i>
                                                </label>
                                            </section>  
                                        </div> 
                                        <!--</div>-->
                                        <% } %>
                                    </div>
                                    <% }%>
                                    <div class="row">
                                        <div class="col-xs-2"></div>
                                        <div id="appendId2" class="col-xs-6">   

                                        </div>     
                                    </div> 
                                    <div  class="row">
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
                                                    <form:input  id="totalbranchvalue"  path="totalvalue"  /><i></i>
                                                </label>
                                            </section>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-1"></div>
                                        <div class="col-xs-10">
                                            <footer style="background-color: #ffffff">

                                                <form:button id="Save" type="button"  disabled="${avn_update}" class="btn btn-primary" onclick="pageSubmit() ">
                                                    Update
                                                </form:button>


                                                <%--<form:button type="button" class="btn btn-primary" disabled="${avn_create}" id ="pagesubmit" onclick="pageSubmit();">Save</form:button>--%>  
                                                </footer>
                                            </div>
                                            <div class="col-xs-1"></div>
                                        </div>
                                </form:form>

                            </fieldset>







                        </div>
                        <!-- end widget content -->

                    </div>
                    <!-- end widget div -->

                </div>
                <!-- end widget -->								


                </article>
                <!-- END COL -->		

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

        $(document).ready(function () {
            calcuation();
            BranchTargetcalcuation();
            generateRegionArray();
            generateBranchArray();
            generateTargetNotAssignRegionArray();
            generateTargetNotAssignBranchArray();
        });
        var date = new Date(), y = date.getFullYear(), m = date.getMonth();
        console.log(y);
        console.log(m);
        //            if ($('#targetperiodid').val() == "1") {
        $('#datetimepickerfromdate').datetimepicker({
            weekStart: 1,
            todayBtn: 2,
            autoclose: 1,
            todayHighlight: 0,
            startView: 3,
            minView: 2,
            forceParse: 0,
            startDate: new Date(y, m, 1),
            endDate: new Date(y, m, 1),
            format: "yyyy-mm-dd"
        });
        //            }


        $('#datetimepickertodate').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 1,
            minView: 2,
            forceParse: 0,
            format: "yyyy-mm-dd"
        });

        $('#datetimepickerfromdate').datetimepicker().on('changeDate', function (ev) {
            alert("test");
        });


        //            console.log($('#datetimepickerfromdate').val());
        //            var currentDate = new Date();
        //            currentDate.setMonth(currentDate.getMonth() + 1);
        //            currentDate.setDate(currentDate.getDate() + 1);
        //            $('#datetimepickerfromdate').datetimepicker('setDate', currentDate);
        //            alert($('#datetimepickerfromdate').val());
        //            console.log(currentDate);
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
            generateTargetNotAssignRegionArray();
            addTextBox(selectedItem.text(), selectedItem.val());
            regiondropdowncreate();
        });
        $('#Regionpull_left').click(function () {
            var selectedItem = $("#multiregional option:selected");
            $("#regionalid").append(selectedItem);
            selectedItem.prop("selected", false);
            removeTextBox(selectedItem.text());
            generateRegionArray();
            generateTargetNotAssignRegionArray();
            regiondropdowncreate();
        });
        $('#regionalid').dblclick(function () {
            var selectedItem = $("#regionalid option:selected");
            $("#multiregional").append(selectedItem);
            selectedItem.prop("selected", false);
            generateRegionArray();
            generateTargetNotAssignRegionArray();
            addTextBox(selectedItem.text(), selectedItem.val());
            regiondropdowncreate();
        });
        $('#multiregional').dblclick(function () {
            var selectedItem = $("#multiregional option:selected");
            $("#regionalid").append(selectedItem);
            selectedItem.prop("selected", false);
            removeTextBox(selectedItem.text());
            generateRegionArray();
            generateTargetNotAssignRegionArray();
            regiondropdowncreate();
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
            if ($("#targetUpdate").valid()) {
                generateBranchTargetArray();
                generateTargetArray();
                var dataObject = new Object();

                dataObject.tragetdes = $('#tragetdes').val();
                dataObject.productid = $('#productid').val();
                dataObject.targergroupid = $('#targergroupid').val();
                dataObject.revenue = $('#revenue').val();
                dataObject.targetperiodid = $('#targetperiodid').val();
                dataObject.targetstartdate = $('#datetimepickerfromdate').val();
                dataObject.targetenddate = $('#datetimepickerfromdate').val();
                dataObject.targetnotassignregion = $("#targetnotassignregion").val();
                dataObject.targetnotassignbranch = $("#targetnotassignbranch").val();
                dataObject.multiregionalarray = $("#multiregionalarray").val();
                dataObject.multibrancharray = $("#multibrancharray").val();
                dataObject.regionaltargetlist = $("#regionaltargetlist").val();
                dataObject.brachtargetlist = $("#brachtargetlist").val();
                dataObject.targetid = $("#targetid").val();
                console.log(dataObject);
                var content = JSON.stringify(dataObject);
                console.log(content);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/updatetarget",
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



        }

        function SectionList(context) {
            var roleid = $('#userroleid').val();
            $('#sectionid').empty();
            $('#multisection').empty();
            generateBranchArray();
            if (roleid !== '') {
                $.ajax({
                    type: "POST",
                    url: context + "/userrolesection/ajaxsectionList",
                    cache: false,
                    data: 'userroleid=' + roleid,
                    success: function (response) {
                        response = JSON.parse(response);
                        console.log(response);
                        for (var i = 0; i < response.notassignsections.length; i++) {
                            $('#sectionid').append($("<option></option>").attr("value", response.notassignsections[i].sectionid).text(response.notassignsections[i].sectionDes));
                        }
                        for (var i = 0; i < response.assignsections.length; i++) {
                            $('#multisection').append($("<option></option>").attr("value", response.assignsections[i].sectionid).text(response.assignsections[i].sectionDes));
                        }
                    },
                    error: function () {
                        console.log('Error while request..');
                    }
                });
            }

        }


        $('#Regionpull_right').click(function () {
            var selectedItem = $("#regionalid option:selected");
            $("#multiregional").append(selectedItem);
            selectedItem.prop("selected", false);
            generateRegionArray();
            generateTargetNotAssignRegionArray();
            addTextBox(selectedItem.text(), selectedItem.val());
            clearfeild();
            regiondropdowncreate();
        });
        $('#Regionpull_left').click(function () {
            var selectedItem = $("#multiregional option:selected");
            $("#regionalid").append(selectedItem);
            selectedItem.prop("selected", false);
            removeTextBox(selectedItem.text());
            generateRegionArray();
            generateTargetNotAssignRegionArray();
            clearfeild();
            regiondropdowncreate();
        });
        $('#regionalid').dblclick(function () {
            var selectedItem = $("#regionalid option:selected");
            $("#multiregional").append(selectedItem);
            selectedItem.prop("selected", false);
            generateRegionArray();
            generateTargetNotAssignRegionArray();
            addTextBox(selectedItem.text(), selectedItem.val());
            clearfeild();
            regiondropdowncreate();
        });
        $('#multiregional').dblclick(function () {
            var selectedItem = $("#multiregional option:selected");
            $("#regionalid").append(selectedItem);
            selectedItem.prop("selected", false);
            removeTextBox(selectedItem.text());
            generateRegionArray();
            generateTargetNotAssignRegionArray();
            clearfeild();
            regiondropdowncreate();
        });
        function resetMultiRegionSelect() {
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

        function addTextBox(name, id) {
            //                alert(id);
            if (id !== undefined) {

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
                        '<input type="text"  classfn="regionclass"  required="true" name="name' + id + '" id="' + id + '"  onchange="calcuation();" style="width:258px" value="0" class="form-control" >' +
                        '</div>' +
                        '</div>');


            }
        }


        function removeTextBox(name) {
            alert(name);
            $("#" + name).remove();

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

        $('#Bpull_right').click(function () {
            $("#multibranch").rules('add', {
                checkallreadyin: true
            });
            if ($("#multibranch").valid()) {
                var selectedItem = $("#branchid option:selected");
                $("#multibranch").append(selectedItem);
                selectedItem.prop("selected", false);
                addBranchTextBoxAdd(selectedItem.text(), selectedItem.val());
                generateBranchArray();
                generateTargetNotAssignBranchArray();
                $("#targetUpdate").valid();
                validater(selectedItem.val());
            }

        });

        $('#Bpull_left').click(function () {
            var selectedItem = $("#multibranch option:selected");
            $("#branchid").append(selectedItem);
            selectedItem.prop("selected", false);
            generateBranchArray();
            generateTargetNotAssignBranchArray();
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
            generateTargetNotAssignBranchArray();
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

        function  validater(ids) {


        }


        jQuery.validator.addMethod("checkallreadyin", function () {
            alert("validation");
            var selectedItem = $("#branchid option:selected");
            alert("sletcdbrnaid" + selectedItem.val());
            var retern = false;
            $("#multibranch option").each(function () {
                alert("this" + $(this).val());
                if (selectedItem.val() !== $(this).val()) {
                    retern = true;
                } else {
                    retern = false;
                }
            });
            alert("retern" + retern);
            return retern;

        }, jQuery.validator.format("Allready Select this item.."));






        jQuery.validator.addMethod("allradyin", function () {
            $('#multibranch option').prop('selected', true);
            var selectedItem = $('#multibranch option:selected');
            console.log(selectedItem.length);
            if (selectedItem.length > 0) {
                return true;
            } else {
                return false;
            }
        }, jQuery.validator.format("Please select ."));


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

        function addBranchTextBoxAdd(name, id) {
            //                counter++
            if (id !== undefined) {
                var rid = $("#reigondropdown option:selected").val();
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
                        '<input type="text"  classfn="regionclass" rid="' + rid + '" required="true" name="feild' + id + '" id="' + id + '"  onchange="BranchTargetcalcuation();" style="width:258px" class="form-control" value="0"   >' +
                        '</div>' +
                        '</div>');


            }
            //            }
        }

        function calcuation() {
            $("#totalregionvalue").val();
            var sum = 0;
            $('[name*="name"]').each(function () {
                sum += parseInt($(this).val());
            });
            $("#totalregionvalue").val(sum);
        }


        function BranchTargetcalcuation() {
            var sum = 0;
            $('[name*="feild"]').each(function () {
                sum += parseInt($(this).val());
            });
            $("#totalbranchvalue").val(sum.toString());
        }
        function removeTextBoxTAB3(id) {
            $("#D" + id).remove();

        }

        function clearfeild() {
            $("#totalregionvalue").val("0");
            $("#totalbranchvalue").val("0");
            $("#selectregion").remove();
            $("#branchid").empty();
            $("#multibranch").empty();
            $("#reigondropdown").empty();

            $("#reigondropdown").append("<option value='sas'>--Select--</option>");

        }
        function regiondropdowncreate() {
            $("#multiregional option").each(function () {
                alert("tets");
                alert($(this).val());
                $("#reigondropdown").append($("<option value='" + $(this).val() + "'>" + $(this).text() + "</option>"));

            });
        }

        function loadBranchList() {
            $('#branchid').empty();
            var dataObject = new Object();
            dataObject.regionalid = $("#reigondropdown").val();
            console.log(dataObject);
            var content = JSON.stringify(dataObject);
            console.log(content);
            $.ajax({type: "POST",
                url: "${pageContext.servletContext.contextPath}/loadbranchlist",
                cache: false,
                data: {regional_info: content}, 
                success: function (response) {
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

        function targetchange() {
            alert("work");
            var oldval = $("#oldtaget").val();
            var newval = $("#totalregionvalue").val();
            if (oldval !== newval && $("#revenue").valid()) {
                clearallfeild();
            }

        }

        function clearallfeild() {

            $("#totalregionvalue").val("0");
            $("#totalbranchvalue").val("0");
            $("#selectregion").remove();
            $('#appendsection').empty();
            $("#branchid").empty();
            $("#multibranch").empty();
            $("#reigondropdown").empty();
            $("#reigondropdown").append("<option value='sas'>--Select--</option>");
            resetMultiRegionSelect();
            $("#appendId").empty();





//            $("#multiregional").empty();

        }

        $('#targetUpdate').validate({
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
//                regiontarget: {
//                    required: true
//                },
                totalvalue: {
                    equalTo: "#revenue"
                },
                revenue: {
                    required: true,
                    number: true
                },
//                oneregiontarget: {
//                    required: true
//                },
                totalregion: {
                    equalTo: "#revenue"
                },
                multiregionalvalidation: {
                    required: true
                },
                targetid: {
                    required: true
                },
                multiregional: {
                    checkallreadyin: true
                }


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

