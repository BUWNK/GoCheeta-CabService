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

                            <form:form class="smart-form" id="useRoleSection" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="targetview" method="post" >
                                <header>Organization Target</header>

                                <fieldset>					

                                    <div class="row">



                                        <div class="row">
                                            <div class="col-xs-2"></div>

                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Product<samp style="color: red">*</samp></label>
                                                    <label class="select">
                                                        <form:select disabled="true"  id="productid" path="productid" items="${productList}"/>
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Target Amount<samp style="color: red">*</samp></label>
                                                    <label class="input">
                                                        <form:input readonly="true" path="revenue" type="text" name="revenue" placeholder="Customer Name" onchange="targetchange()"  /><i></i>
                                                    </label>
                                                </section>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-8">
                                                <section>
                                                    <label class="label">Description<samp style="color: red">*</samp></label>
                                                    <label class="input">
                                                        <form:input readonly="true" path="tragetdes" type="text" name="tragetdes" placeholder="Customer Name" value="${fullname}" /><i></i>
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
                                                        <form:select disabled="true" id="targettypeid" path="targettypeid" items="${targettype}"/>
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Activity Type<samp style="color: red">*</samp></label>
                                                    <label class="select">
                                                        <form:select disabled="true" id="activitytypeid" path="activitytypeid" items="${activitytype}"/>
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
                                                        <form:select disabled="true" id="targergroupid" path="targergroupid" items="${userRoleList}"/>
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>

                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Target Period<samp style="color: red">*</samp></label>
                                                    <label class="select">
                                                        <form:select readonly="true" id="targetperiodid" path="targetperiodid" items="${sectionList}"/>
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
                                                    <form:input path="targetstartdate" readonly="true" type="text" id="datetimepickerfromdate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                    </div>
                                                </div>
                                                <div class="col-xs-2"></div>
                                                <div class="col-xs-3">
                                                    <div class="form-group">
                                                    <form:label path="">Target End Date:<samp style="color: red">*</samp></form:label>
                                                    <form:input path="targetenddate" readonly="true" disabled="true" type="text" id="datetimepickertodate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                    </div>
                                                </div>
                                            </div>


                                        </div>


                                        <header>Regional Target</header>
                                        <%--<c:forEach var = "current" items = "${regionaltarget}">--%>  

                                    <%
                                        JSONArray array = (JSONArray) session.getAttribute("regionaltarget");
                                        JSONObject jaray = new JSONObject();
//                                            List<Section> sections = (List<Section>) session.getAttribute("pagehierarchy");
                                        for (int i = 0; i < array.length(); i++) {
                                            jaray = array.getJSONObject(i);


                                    %>

                                    <% if (i % 2 == 0) {%>
                                    <div class="row">
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label"><%=jaray.getString("region")%><samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <input path="tragetdes" readonly="true" type="text" name="tragetdes" placeholder="Customer Name" value="<%=jaray.getString("target")%>" /><i></i>
                                                </label>
                                            </section>  
                                        </div> 
                                        <% }%>

                                        <% if (i % 2 == 1) {%>

                                        <div class="col-xs-1"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label"><%=jaray.getString("region")%><samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <input path="tragetdes" readonly="true" type="text" name="tragetdes" placeholder="Customer Name" value="<%=jaray.getString("target")%>" /><i></i>
                                                </label>
                                            </section>  
                                        </div> 
                                    </div>
                                    <% } %>

                                    <% }%>
                                    
                                    <br><br><br><br>
                                    <header>Branch Target</header> 
                                    <%
                                        JSONArray Barray = (JSONArray) session.getAttribute("branchtarget");
                                        JSONObject bobj = new JSONObject();
//                                            List<Section> sections = (List<Section>) session.getAttribute("pagehierarchy");
                                        for (int i = 0; i < Barray.length(); i++) {
                                            bobj = Barray.getJSONObject(i);


                                    %>

                                    <% if (i % 2 == 0) {%>
                                    <div class="row">
                                        <div class="col-xs-2"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label"><%=bobj.getString("branch")%><samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <input id="<%=bobj.getString("brnachid")%>" readonly="true" rid="<%=bobj.getString("regionid")%>" type="text" name="feild<%=bobj.getString("branch")%>" placeholder="Customer Name" value="<%=bobj.getString("target")%>" /><i></i>
                                                </label>
                                            </section>  
                                        </div> 
                                        <% }%>

                                        <% if (i % 2 == 1) {%>

                                        <div class="col-xs-1"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label"><%=bobj.getString("branch")%><samp style="color: red">*</samp></label>
                                                <label class="input">
                                                    <input id="<%=bobj.getString("brnachid")%>" readonly="true" rid="<%=bobj.getString("regionid")%>" type="text" name="feild<%=bobj.getString("branch")%>" placeholder="Customer Name" value="<%=bobj.getString("target")%>" /><i></i>
                                                </label>
                                            </section>  
                                        </div> 
                                    </div>
                                    <% } %>

                                    <% }%>

                                    

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

    $('#pull_right').click(function () {
        var selectedItem = $("#sectionid option:selected");
        $("#multisection").append(selectedItem);
        selectedItem.prop("selected", false);
        generateBranchArray();
    });

    $('#pull_left').click(function () {
        var selectedItem = $("#multisection option:selected");
        $("#sectionid").append(selectedItem);
        selectedItem.prop("selected", false);
        generateBranchArray();
    });

    $('#sectionid').dblclick(function () {
        var selectedItem = $("#sectionid option:selected");
        $("#multisection").append(selectedItem);
        selectedItem.prop("selected", false);
        generateBranchArray();
    });

    $('#multisection').dblclick(function () {
        var selectedItem = $("#multisection option:selected");
        $("#sectionid").append(selectedItem);
        selectedItem.prop("selected", false);
        generateBranchArray();
    });

    function resetMultiBranchSelect() {
        $('#multisectionarray').val();
        $('#multisection option').prop('selected', true);
        var selectedItem = $('#multisection option:selected');
        $('#sectionid').append(selectedItem);
        selectedItem.prop('selected', false);
        sortSelect('#sectionid', 'text', 'asc');
    }

    function generateBranchArray() {
        var section = [];
        $("#multisection option").each(function () {
            section.push($(this).val());
        });
        $('#multisectionarray').val(JSON.stringify(section));
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
    }, jQuery.validator.format("Please select atleast one section."));

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






</script>  
</body>

</html>
