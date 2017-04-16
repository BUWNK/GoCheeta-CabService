<%-- 
    Document   : customervisit
    Created on : Jan 6, 2016, 8:38:03 AM
    Author     : Isuru
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

        <aside id="left-panel">
            <jsp:include page="../template/menu.jsp"/>
        </aside>

        <div id="main" role="main">
            <!-- RIBBON -->
            <div id="ribbon">
                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li>Customer visit Management</li><li>Customer Visit</li>
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
                            Create New Customer Visit
                            <span>
                                CCID : <c:out value="${accountId}"/>
                            </span>   
                        </h1>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <ul id="sparks" class="">
                            <a href="${pageContext.servletContext.contextPath}/customervisit/searchprofile" class="btn btn-primary">View Full Profile</a>
                        </ul>
                    </div>
                </div>
                <form:form class="smart-form" id="customerVisitForm" commandName="customerVistForm" action="${pageContext.servletContext.contextPath}/customervisit/created" method="POST">
                    <form:input type="hidden" id="customervisitId" path="customervisitId" value="${callLogId}"/>
                    <form:input type="hidden" id="callcreattype" class="callcreattypew" path="callcreattype" value="CL"/>
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
                        <div class="col-xs-2">
                            <div class="col-xs-10">
                                <section>
                                    <label class="label">Title </label>
                                    <label class="select">
                                        <form:select path="title" items="${titleList}"/>
                                        <i></i>
                                    </label>
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
                                <label class="label">Module <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="productId" path="productId" items="${productList}"/><i></i>
                                </label>
                            </section>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Case Type <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="caseTypeId" path="caseTypeId" items="${caseTypeList}"/><i></i>
                                </label>
                            </section>
                        </div>
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
                            <div class="input-group bootstrap-timepicker timepicker">
                                <input id="timepicker2" id="callbackTime" name="callbackTime" type="text" class="form-control input-small">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                            </div>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="row">
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
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">IDD NUMBER</label>
                                <label class="afinity_checkbox">
                                    <form:checkbox path="iddnum" id="isIdd"/>
                                </label>
                                <div class="note">
                                    <strong>Hint</strong> Please check if contact is IDD
                                </div>
                            </section>
                        </div>
                    </div>
                    <div class="row">
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
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Contact #2 </label>
                                <label class="input">
                                    <i class="icon-prepend fa fa-phone"></i>
                                    <form:input type="text" id="telephone2" path="telephone2" placeholder="Telephone" />
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
                                <label class="label">Branch <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="branchcode" path="branchcode" items="${branchList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>        
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <section>
                                <label class="label">Comments </label>
                                <label class="textarea" id="textarea">
                                    <form:textarea path="comments" rows="3" class="custom-scroll" id="comments" name="comments"/>
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
                                    <form:input  class="form-control" id="startDate" path="startDate" placeholder="Select Date"  data-date-format="yyyy-mm-dd"  value="${Date}"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Start Time <samp style="color: red">*</samp></label>
                                <div class="input-group bootstrap-timepicker timepicker">
                                    <input id="timepicker1"  name="startTime" type="text" class="form-control input-small"  value="${time}">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                </div>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <footer> 
                                <div id="calllogwithcase">
                                    <form:button id="customevisitwithcase" disabled="${avn_create}" type="button" class="btn btn-primary">
                                        Create Log With Case
                                    </form:button>
                                </div>
                                <div id="calllog">
                                    <form:button id="customervisit" disabled="${avn_create}" type="button" class="btn btn-primary">
                                        Create Customer Visit 
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
                document.getElementById("customerVisitForm").reset();
            }

//            function createCasewithCalllog() {
//                $('#callcreattype').val('CLWC');
//            }
            $('#customervisit').click(function () {
                pageSubmit();
            });

            $('#customevisitwithcase').click(function () {
                $('#callcreattype').val('CLWC');
                pageSubmit();
            });

            $(function () {
                $('#startDate').datetimepicker({
                    //language: 'fr',
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0

                });

                $('#callbackDate').datetimepicker({
                    //language: 'fr',
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0

                });
                if (!"${Date}") {
                    $('#startDate').datetimepicker('setDate', new Date());
                }

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
                        $('#last_name').val(last_name.toUpperCase())
                    }
                });
                $('#last_name').keyup(function () {
                    $('#last_name').val($('#last_name').val().toUpperCase());

                });

                $('#customerVisitForm').validate({
                    onkeyup: function (element) {
                        $(element).valid();
                    },
                    onfocusout: function (element) {
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
                        }
                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }
                });

                $('#callbackdiv').hide();
                $('#calllog').show();
                $('#calllogwithcase').hide();
                $('#callbackDate').attr('disabled', 'disabled');
                $('#timepicker2').attr('disabled', 'disabled');
                $('#followUpAction').on('change', function () {
                    $("#timepicker2").rules("remove");
                    $("#callbackDate").rules("remove");
                    var selcvalue = $('#followUpAction').val();
                    var selcvaluecasetype = $('#caseTypeId').val();
                    console.log(selcvalue);
                    if (selcvalue === '1') {
                        $('#calllog').show();
                        $('#calllogwithcase').hide();
                        $('#timepicker2').removeAttr('disabled');
                        $('#callbackDate').removeAttr('disabled');
                        $('#callbackdiv').show();
                        console.log("add rule");
                        $("#callbackDate").rules("add", {
                            required: true
                        });
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
                    var selcvalue = $('#followUpAction').val();
                    var selcvaluecasetype = $('#caseTypeId').val();
                    console.log(selcvaluecasetype);
                    if (selcvalue === '6') {
                        $('#calllogwithcase').show();
                        $('#calllog').hide();
                    }
                    else if (selcvaluecasetype === '9') {
                        $('#calllogwithcase').show();
                        $('#calllog').hide();
                    } else {
                        $('#calllogwithcase').hide();
                        $('#calllog').show();
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
//                        $("#telephone2").rules("add", {
//                            required: true,
//                            minlength: 9,
//                            maxlength: 20,
//                            number: true
//                        });
                    } else {
                        $("#telephone").attr('maxlength', '9');
                        $("#telephone2").attr('maxlength', '9');
                        $("#telephone").rules("add", {
                            required: true,
                            minlength: 9,
                            maxlength: 9,
                            number: true
                        });
//                        $("#telephone2").rules("add", {
//                            required: true,
//                            minlength: 9,
//                            maxlength: 9,
//                            number: true
//                        });
                    }
                    $('#telephone').valid();
//                    $('#telephone2').valid();
                });
                $("#telephone").attr('maxlength', '9');
                $("#telephone2").attr('maxlength', '9');
                $("#telephone").rules("add", {
                    required: true,
                    minlength: 9,
                    maxlength: 9,
                    number: true
                });
                $("#telephone2").rules("add", {
                    minlength: 9,
                    maxlength: 9,
                    number: true
                });
//                $('#telephone').valid();
//                $('#telephone2').valid();
            });
        </script>

        <script type="text/javascript">
            function pageSubmit() {
                var outputcallbacktime = convert12to24($('#timepicker2').val());
                var outputstarttime = convert12to24($('#timepicker1').val());
                var callbackTime = new Date();
                var startTime = new Date();
                startTime.setHours(outputstarttime[0], outputstarttime[1], outputstarttime[2]);
                callbackTime.setHours(outputcallbacktime[0], outputcallbacktime[1], outputcallbacktime[2]);
                console.log(outputstarttime);
                console.log(outputcallbacktime);
                console.log(startTime);
                console.log(callbackTime);
                $('#msg_dev').empty();
                if ($("#customerVisitForm").valid()) {
                    var dataObject = new Object();
                    dataObject.title = $('#title').val();
                    dataObject.name_in_full = $('#name_in_full').val();
                    dataObject.last_name = $('#last_name').val();
                    dataObject.companyname = $('#companyname').val();
                    dataObject.preferred_language = $('#preferred_language').val();
                    dataObject.productId = $('#productId').val();
                    dataObject.caseTypeId = $('#caseTypeId').val();
                    dataObject.followUpAction = $('#followUpAction').val();
                    dataObject.status = $('#status').val();
                    dataObject.callbackDate = $('#callbackDate').val();
                    dataObject.callbackTime = callbackTime;
                    dataObject.telephone = $('#telephone').val();
                    dataObject.branchcode = $('#branchcode').val();
                    dataObject.telephone2 = $('#telephone2').val();
                    dataObject.comments = $('#comments').val();
                    dataObject.startDate = $('#startDate').val();
                    dataObject.startTime = startTime;
                    dataObject.callcreattype = $('#callcreattype').val();
                    console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    console.log(content);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/customervisit/create",
                        cache: false,
                        data: {customervisit: content},
                        success: function (response) {
                            response = JSON.parse(response);
                            console.log(response);
                            if (response.CODE === "SUCCESS" && response.TYPE === "CLWC") {
                                $('#customervisitId').val(response.CUSVISITID);
                                clearFields();
                            } else if (response.CODE === "SUCCESS") {

                                $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> ' + response.MESSAGE + '</div> <br/>');
                                clearFields();
                                window.scrollTo(0, 0);

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

            function clearFields() {
                $("#title").get(0).selectedIndex = 1;
                $('#name_in_full').val('');
                $('#last_name').val('');
                $('#companyname').val('');
                $("#preferred_language").get(0).selectedIndex = 1;
                $("#productId").get(0).selectedIndex = 0;
                $('#caseTypeId').val('');
                $("#followUpAction").get(0).selectedIndex = 4;
                $("#status").get(0).selectedIndex = 0;
                $('#callbackDate').val('');
                $('#telephone').val('');
                $("#branchcode").get(0).selectedIndex = 0;
                $('#telephone2').val('');
                $('#comments').val('');
                $('#startDate').empty();
                $('#callcreattype').empty();
            }
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



            function convert12to24(timeStr) {
                var meridian = timeStr.substr(timeStr.length - 2).toLowerCase();
                var hours = timeStr.substr(0, timeStr.indexOf(':'));
                var minutes = timeStr.substring(timeStr.indexOf(':') + 1, timeStr.indexOf(' '));
                if (meridian === 'pm') {
                    if (hours !== 12) {
                        hours = hours * 1 + 12;
                    } else {
                        hours = 0;
                    }
                }
                return [parseInt(hours), parseInt(minutes), 0];
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



