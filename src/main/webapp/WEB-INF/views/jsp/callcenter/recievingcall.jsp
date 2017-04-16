<%-- 
    Document   : callview
    Created on : Jul 16, 2015, 1:47:26 PM
    Author     : ISURU
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <%--<sec:authorize access="hasRole('ROLE_USER')">--%>
        <aside id="left-panel">
            <jsp:include page="../template/menu.jsp"/>
        </aside>
        <!-- END NAVIGATION -->
        <%--</sec:authorize>--%>
        <!-- MAIN PANEL -->
        <div id="main" role="main">
            <!-- RIBBON -->
            <div id="ribbon">
                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li>Call Center Management</li><li>Receiving Calls</li>
                </ol>
                <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Create New Call Log
                            <span>
                                CCID : <c:out value="${accountId}"/>
                            </span>
                        </h1>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <ul id="sparks" class="">
                            <a href="${pageContext.servletContext.contextPath}/callcenter/searchprofile?cli=${telephone}&datetime=${clidatetime}" class="btn btn-primary">View Full Profile</a>
                        </ul>
                    </div>
                </div>
                <form:form class="smart-form" id="callCenterForm" commandName="callCenterForm" action="${pageContext.servletContext.contextPath}/callcenter/calllogview/insert" method="post">
                    <form:input type="hidden" id="callLogId" path="callLogId" value="${callLogId}"/>
                    <form:input type="hidden" id="callcreattype" class="callcreattypew" path="callcreattype" value="CL"/>
                    <form:input type="hidden" id="accountId" path="accountId" value="${accounid}"/>
                    <form:input type="hidden" id="topipeline" path="topipeline" value="false"/>
                    <form:hidden id="amount" path="amount"/>
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
                    <div class="row" style="padding-bottom: 30px;">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <header><h4>Contact Info</h4></header>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-2">
                            <div class="col-xs-10">
                                <section>
                                    <label class="label">Title </label>
                                    <label class="select">
                                        <form:select path="title">
                                            <c:forEach var="ttl" items="${titleList}">
                                                <c:if test="${ttl.key==title}">
                                                    <form:option value="${ttl.key}" selected="true">${ttl.value}</form:option>
                                                </c:if>
                                                <c:if test="${ttl!=title}">
                                                    <form:option value="${ttl.key}">${ttl.value}</form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select> <i></i> </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                        </div>
                        <div class="col-xs-4">
                            <div class="col-xs-11">
                                <section>
                                    <label class="label">Name in Full </label>
                                    <label class="input">
                                        <form:input type="text" id="name_in_full" path="name_in_full" placeholder="Name in full" value="${name_in_full} "/>
                                    </label>
                                </section>
                            </div> 
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="col-xs-2">
                            <section>
                                <label class="label">Last Name </label>
                                <label class="input">
                                    <form:input type="text" id="last_name" path="last_name" placeholder="Last name" value="${l_name}"/>
                                </label>
                            </section>
                        </div>
                    </div>                 
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <section>
                                <label class="label">Company Name</label>
                                <label class="input">
                                    <form:input type="text" id="companyname" path="companyname" placeholder="Company name" value="${companyname}"/>
                                </label>
                            </section>
                        </div>    
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">IDD NUMBER</label>
                                <label class="afinity_checkbox">
                                    <form:checkbox path="iddnum"  id="isIdd"/>
                                </label>
                                <div class="note">
                                    <strong>Hint</strong> Please check if contact is IDD
                                </div>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Contact #1 <samp style="color: red">*</samp></label>
                                <label class="input"> <i class="icon-prepend fa fa-phone"></i>
                                    <form:input type="text" id="telephone" path="telephone" placeholder="Telephone"  value="${telephone}"/>
                                </label>
                                <div class="note">
                                    <strong>Hint</strong> e.g. 777101010
                                </div>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Contact #2 </label>
                                <label class="input">
                                    <i class="icon-prepend fa fa-phone"></i>
                                    <form:input id="telephone2" path="telephone2" placeholder="Telephone" />
                                </label>
                                <div class="note">
                                    <strong>Hint</strong> e.g. 777101010
                                </div>
                            </section>
                        </div>  
                        <div class="col-xs-7"></div>
                    </div>
                    <div class="row" style="padding-bottom: 30px;">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <header><h4>Call Info</h4></header>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Preferred Language </label>
                                <label class="select">
                                    <form:select value="${p_language.value}" id="preferred_language" path="preferred_language" items="${p_language}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Call Direction <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select value="${callDirectoryList.value}" id="callDirection" path="callDirection"  items="${callDirectoryList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Follow-up-Action <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="followUpAction" value="${fuaList.value}" path="followUpAction" items="${fuaList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Status <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="status" value="${statusList.value}" path="status" items="${statusList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row" id="callbackdiv">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Callback Date </label>
                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                    <form:input  name="callbackDate" id="callbackDate" path="callbackDate" data-date-format="yyyy-mm-dd"  placeholder="Select Date" class="form-control"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <label class="label">Callback Time </label>
                            <label class="input">
                                <input id="timepicker2" id="callbackTime" name="callbackTime" type="text" class="form-control input-small input-group bootstrap-timepicker timepicker">
                                <i class="icon-append fa fa-clock-o"></i>
                            </label>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <section>
                                <label class="label">Comments </label>
                                <label class="textarea" id="textarea">
                                    <form:textarea path="comments" rows="3" class="custom-scroll" name="comments"/>
                                </label>
                                <div class="note">
                                    <strong>Hint</strong> 1024 characters only
                                </div>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Start date <samp style="color: red">*</samp></label>
                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                    <form:input class="form-control" id="startDate" path="startDate" placeholder="Select Date" data-date-format="yyyy-mm-dd" value="${Date}" readonly="true"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Start Time <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <input id="timepicker1" name="startTime" type="text" class="form-control input-small input-group bootstrap-timepicker timepicker" value="${time}"/>
                                    <i class="icon-append fa fa-clock-o"></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row" style="padding-bottom: 30px;">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <header><h4>Ticket Info</h4></header>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Ticket Type <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="caseTypeId" path="caseTypeId" items="${caseTypeList}"/><i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Product <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="productId" path="productId" items="${productList}"/><i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="branchcode" path="branchcode" items="${branchList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>       
                        <div class="col-xs-7"></div>
                    </div>
                    <div id="lead-info" style="display: none;">
                        <div class="row" style="padding-bottom: 30px;">
                            <div class="col-xs-1"></div>
                            <div class="col-xs-10">
                                <header><h4>Lead Info</h4></header>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Amount <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <input id="formatamount" placeholder="Amount"/>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Forecasted Date <samp style="color: red">*</samp></label>
                                    <label class="input"><i class="icon-append fa fa-calendar"></i>
                                        <form:input class="form-control" id="forecastedDate" path="forecastedDate" placeholder="Forecasted Date" data-date-format="yyyy-mm-dd" readonly="true"/>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Select Channel <samp style="color: red">*</samp></label>
                                    <label class="select">
                                        <form:select id="channel" path="channel" items="${channelList}"/>
                                        <i></i>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section id="campaign_section" style="display: none;">
                                    <label class="label">Select Campaign <samp style="color: red">*</samp></label>
                                    <label class="select">
                                        <form:select id="camping" path="camping" items="${campaingList}"/>
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
                                    <label class="label">Promotion Code </label>
                                    <label class="input">
                                        <form:input id="promotionCode" path="promotionCode" placeholder="Promotion Code" maxlength="32"/>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-7"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <footer> 
                                <div id="tosalespipeline">
                                    <form:button id="topipelinebtn" type="submit" class="btn btn-primary" disabled="${avn_create}">
                                        Transfer To Sales Pipeline
                                    </form:button>
                                </div>
                                <div id="calllogwithcase">
                                    <form:button id="" type="submit" class="btn btn-primary" disabled="${avn_create}" onclick="createCasewithCalllog()">
                                        Create Call Log With Ticket
                                    </form:button>
                                </div>
                                <div id="calllog">
                                    <form:button id="" type="submit" disabled="${avn_create}" class="btn btn-primary">
                                        Create Call Log 
                                    </form:button>
                                </div>
                            </footer>
                        </div>
                        <div class="col-xs-2"></div>
                    </div> 
                    <div class="row">
                        <div class="col-xs-12"></div>      
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

        <script type="text/javascript"></script>

        <script type="text/javascript">
            function resetForm() {
                document.getElementById("callCenterForm").reset();
            }

            function createCasewithCalllog() {
                $('#callcreattype').val('CLWC');
            }

            $(function () {
                $('#startDate').datetimepicker({
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0
                });

                var date = new Date();
                if (!"${Date}") {
                    $('#startDate').datetimepicker('setDate', date);
                }
                $('#startDate').datetimepicker('setStartDate', date);
                $('#startDate').datetimepicker('setEndDate', date);

                $('#callbackDate').datetimepicker({
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0
                });

                $('#callbackDate').datetimepicker('setStartDate', date);

                $('#forecastedDate').datetimepicker({
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0
                });
                $('#forecastedDate').datetimepicker('setStartDate', date);

            });

            $(function () {
                $('#timepicker1').timepicker({
                    useCurrent: true,
                    minuteStep: 1
                });

                $('#timepicker2').timepicker({
                    useCurrent: true
                });
            });

            $(document).ready(function () {

                $('#topipelinebtn').click(function () {
                    $('#topipeline').val('true');
                    $("#formatamount").rules("add", {
                        required: true,
                        amountvalidation: true
                    });

                    $("#forecastedDate").rules("add", {
                        required: true
                    });

                    $("#channel").rules("add", {
                        required: true
                    });
                    
                    $('#amount').val($('#formatamount').val().replace(/,/g, ""));
                });

                $('#name_in_full').keyup(function () {
                    var fullname = $('#name_in_full').val().split(' ');
                    $('#name_in_full').val($('#name_in_full').val().toUpperCase());
                    $('#initials').val('');
                    $('#last_name').val('');
                    if (fullname.length > 1) {
                        var initials = "";
                        for (var i = 0; i < fullname.length - 1; i++) {
                            initials += fullname[i].substring(0, 1) + ".";
                        }
                        $('#initials').val(initials.toUpperCase());
                        $('#last_name').val(fullname[fullname.length - 1].toUpperCase());
                    }
                });
                $('#last_name').keyup(function () {
                    $('#last_name').val($('#last_name').val().toUpperCase());

                });

                $('#callCenterForm').validate({
                    onkeyup: function (element) {
                        $(element).valid();
                    },
                    rules: {
                        startDate: {
                            required: true
                        },
                        startTime: {
                            required: true
                        },
                        callDirection: {
                            required: true
                        },
                        followUpAction: {
                            required: true
                        },
                        status: {
                            required: true
                        },
                        caseTypeId: {
                            required: true
                        },
                        productId: {
                            required: true
                        },
                        comments: {
                            maxlength: 1024
                        },
                        branchcode: {
                            required: true
                        },
                        telephone2: {
                            number: true,
                            minlength: 9,
                            maxlength: 9
                        }
                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }
                });

                $('#callbackdiv').hide();
                $('#calllog').show();
                $('#calllogwithcase').hide();
                $('#tosalespipeline').hide();
                $('#callbackDate').attr('disabled', 'disabled');
                $('#timepicker2').attr('disabled', 'disabled');
                $('#followUpAction').on('change', function () {
                    $("#timepicker2").rules("remove");
                    $("#callbackDate").rules("remove");
                    var selcvalue = $('#followUpAction').val();
                    var selcvaluecasetype = $('#caseTypeId').val();
                    if (selcvalue === '1') {
                        $("#callbackDate").rules("add", {
                            required: true
                        });
                        $('#calllog').show();
                        $('#calllogwithcase').hide();
                        $('#timepicker2').removeAttr('disabled');
                        $('#callbackDate').removeAttr('disabled');
                        $('#callbackdiv').show();
                        console.log("add rule");
                        $("#timepicker2").rules("add", {
                            required: true
                        });
                    } else {
                        $('#callbackdiv').hide();
                        $('#calllogwithcase').hide();
                        $('#callbackDate').attr('disabled', 'disabled');
                        $('#timepicker2').attr('disabled', 'disabled');
                        $("#callbackDate").rules("remove");
                        $("#timepicker2").rules("remove");
                    }
                    if (selcvaluecasetype === '9') {
                        $('#calllogwithcase').show();
                        $('#calllog').hide();
                    }
                    else if (selcvaluecasetype !== '9' && selcvalue === '6') {
                        $('#calllogwithcase').show();
                        $('#calllog').hide();
                    } else {
                        $('#calllogwithcase').hide();
                        $('#calllog').show();
                    }

                });

                $('#caseTypeId').on('change', function () {
                    var selcvalue = parseInt($('#followUpAction').val());
                    var selcvaluecasetype = parseInt($('#caseTypeId').val());
                    if (selcvalue === 6) {
                        $('#calllogwithcase').show();
                        $('#calllog').hide();
                    } else if (selcvaluecasetype === 3) {
                        $('#calllogwithcase').show();
                        $('#calllog').hide();
                    } else {
                        $('#calllogwithcase').hide();
                        $('#calllog').show();
                    }

                    if (selcvaluecasetype === 3) {
                        $('#tosalespipeline').show();
                        $('#lead-info').show();
                    } else {
                        $('#tosalespipeline').hide();
                        $('#lead-info').hide();
                    }
                });

                $('#isIdd').change(function () {
                    $("#telephone").rules("remove");
                    $("#telephone2").rules("remove");
                    if ($('#isIdd').is(':checked')) {
                        $("#telephone").attr('maxlength', '20');
                        $("#telephone2").attr('maxlength', '20');
                        $("#telephone").rules("add", {
                            required: true,
                            minlength: 9,
                            maxlength: 20,
                            number: true
                        });
                    } else {
                        $("#telephone").attr('maxlength', '9');
                        $("#telephone2").attr('maxlength', '9');
                        $("#telephone").rules("add", {
                            required: true,
                            minlength: 9,
                            maxlength: 9,
                            number: true
                        });
                    }
                    $('#telephone').valid();
                });

                $("#telephone").attr('maxlength', '9');
                $("#telephone2").attr('maxlength', '9');
                $("#telephone").rules("add", {
                    required: true,
                    minlength: 9,
                    maxlength: 9,
                    number: true
                });

                $('#channel').change(function () {
                    if (parseInt($('#channel').val()) === 1) {
                        $('#campaign_section').show();
                        $("#camping").rules("add", {
                            required: true
                        });
                    } else {
                        $('#campaign_section').hide();
                        $("#camping").rules("remove");
                    }
                });

                $('#formatamount').focus(function () {
                    $(this).number(true, ',', ' ');
                });

                jQuery.validator.addMethod("amountvalidation", function (value, element) {
                    if ($.isNumeric(parseInt(value.replace(/,/g, "")))) {
                        return true;
                    } else {
                        return false;
                    }
                }, "Pleas enter correct amount");
            });
        </script>

        <script type="text/javascript">
            // DO NOT REMOVE : GLOBAL FUNCTIONS!
            $(document).ready(function () {
                //$("#casecreate").hide();
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
        </script>
    </body>
</html>



