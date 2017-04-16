
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
                    <li> <a href="${pageContext.servletContext.contextPath}/case/search">Ticket Management</a></li><li>Create Ticket</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i>
                            Ticket Management Create Ticket        
                        </h1>
                        <span>

                        </span>
                    </div>
                </div>        
                <form:form class="smart-form" id="caseCreateForm" novalidate="novalidate" commandName="userCase" action="${pageContext.request.contextPath}/case/insert" method="post"  enctype="multipart/form-data">
                    <form:input type="hidden" id="caseCallLogId" path="caseCallLogId" value="${callLogId}"/>
                    <form:input type="hidden" id="customervisitId" path="customervisitId" value="${customervisitId}"/>
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
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Module <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="productId" path="productId" items="${productList}"/><i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <!--<section>-->
                                <label class="label">Date <samp style="color: red">*</samp></label> 
                                <label class="input">
                                    <form:input id='datetimepicker' path="caseDate" data-date-format="yyyy-mm-dd  HH:ii:ss P" cssClass="form-control input-group date"/>
                                    <i class="icon-append fa fa-calendar"></i>
                                </label>
                                <!--                                    <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                <%--<form:input type="text" id="date_of_birth" data-dateformat="yy-mm-dd" path="caseDate" placeholder="Date"/>--%>
                            </label>-->
                            </section>
                        </div>    
                        <div class="col-xs-2"></div>
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
                                <label class="label">Priority <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="priorityId" path="priorityId" items="${casePriorityList}"/><i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Department <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="departmentId" path="departmentId" items="${departmentList}"/><i></i>
                                </label>  
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <form:label path="accountId" class="label">
                                    Customer Account 
                                </form:label>
                                <div class="input-group">                  
                                    <form:input  class="form-control" path="accountId" name="customerAccount" id="customerAccount" placeholder="Customer Account" readonly="true" style="z-index: 0;"/>
                                    <span class="input-group-btn">
                                        <a class="btn btn-default submit-button-link" data-toggle="modal" data-target="#customerModal">
                                            &nbsp;&nbsp;&nbsp;<i class="fa fa-fw fa-search fa-lg"></i>&nbsp;&nbsp;&nbsp;
                                        </a>                                  
                                    </span>
                                </div>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Title <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="title" path="title" items="${titleList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <div class="input">
                                <section>
                                    <label class="label">Customer Name <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <form:input path="customername" type="text" name="customername" placeholder="Customer Name" value="${fullname}" /><i></i>
                                    </label>
                                </section>                                   
                            </div>

                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
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
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <div class="input">
                                <section>
                                    <label class="label"> Telephone <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <form:input path="phonenumber" type="text" name="phonenumber" placeholder="Telephone" /><i></i>
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
                        <div class="col-xs-3">
                            <div class="input">
                                <section>
                                    <label class="label">NIC </label>
                                    <label class="input">
                                        <form:input path="nic" type="text" name="nic" placeholder="NIC" maxlength="10" /><i></i>
                                    </label>
                                </section>                                   
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <div class="input">
                                <section>
                                    <label class="label">Subject <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <form:input path="subject" type="text" name="subject"  placeholder="Subject" />
                                    </label>
                                    <div class="note">
                                        <strong>Hint</strong> 32 characters only
                                    </div>
                                </section>                                   
                            </div>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <section>
                                <label class="label">Ticket Description <samp style="color: red">*</samp></label>
                                <label class="textarea"> 										
                                    <form:textarea path="description" rows="3" placeholder="Description" class="custom-scroll" name="caseDesc"/> 
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
                                <label class="label">Manual Assign</label>
                                <label class="afinity_checkbox">
                                    <form:checkbox path="autoassign" id="checkbox"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Assignee<samp style="color: red">*</samp></label>
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
                                <label class="label">Branch</label>
                                <label class="select" >
                                    <form:select id="branchcode" path="branchcode" onchange="assigneeList()" items="${branchList}"/>
                                    <i></i>
                                </label>
                            </section>

                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">BR/MF<samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="caseCategory" path="caseCategory" onchange="assigneeList()" items="${caseCategoryList}"/>
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
                                    <label class="label"> Assignee <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <form:input path="assigneeId1" type="text" readonly="true" name="assigneeId1" placeholder="Assignee" id="assigneeId1" /><i></i>
                                    </label>
                                </section>                                   
                            </div>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <div class="input">
                                <section>
                                    <label name="employeeId" class="label"> </label>
                                    <label class="input">
                                        <input path="employeeId" type="hidden" name="employeeId"  id="employeeId" /><i></i>
                                    </label>
                                </section>                                   
                            </div>
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
                                <form:button id="createbutton" disabled="${avn_create}" type="submit" class="btn btn-primary">
                                    Save
                                </form:button>
                            </footer>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                </form:form>
                <!-- END OF FORM -->
                <jsp:include page="customermodal.jsp" />
                <!-- START MODAL -->

                <!-- END MODAL -->                                        
                <script type="text/javascript">
                    function callSearch() {
                        $('#msg_dev').empty();
                        var isValid = false;
                        if (!$('#parameter_value').val()) {
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Please enter valid customer code.</div>');
                            isValid = false;
                        } else {
                            isValid = true;
                        }

                        if (isValid) {
                            var cus_id = document.getElementById('parameter_value').value;
                            console.log(cus_id);
                            $.ajax({
                                type: "POST",
                                url: "${pageContext.servletContext.contextPath}/case/getaccounts",
                                data: 'cus_id=' + cus_id,
//                            contentType: "application/text",
                                async: false,
                                success: function (sdata) {
                                    // $("#response").html(data.d);
                                    if (sdata) {
                                        document.getElementById('case-datatable').className = 'case-datatable-show';
                                        var data = JSON.parse(sdata);
                                        //                                        console.log(data.STAKEHOLDER_ID_PK);
                                        document.getElementById('cus-ccid').innerHTML = data.STAKEHOLDER_ID_PK;
                                        document.getElementById('cus-code').innerHTML = data.CUSTOMER_CODE;
                                        document.getElementById('cus-title').innerHTML = data.TITLE;
                                        document.getElementById('cus-initials').innerHTML = data.INITIALS;
                                        document.getElementById('cus-name').innerHTML = data.NAME_IN_FULL;
                                        document.getElementById('cus-dob').innerHTML = data.DATE_OF_BIRTH;
                                        document.getElementById('cus-gender').innerHTML = data.GENDER;
                                        document.getElementById('cus-marital').innerHTML = data.MARITAL_STATUS;
                                    } else {
                                        document.getElementById('case-datatable').className = 'case-datatable-hide';
                                    }
                                }

                            });
                        }

                    }//end call search
                    function submitcode() {
                        document.getElementById('customerAccount').value = document.getElementById('cus-ccid').innerHTML;
                        document.getElementById('case-datatable').className = 'case-datatable-hide';
                    }

//                  Formreset
                    function resetForm() {
                        document.getElementById("caseCreateForm").reset();
                    }
                </script>
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

            jQuery.validator.addMethod("nic", function (value) {
                return (/^[0-9]{9}[V]$/.test(value) || /^[0-9]{9}[v]$/.test(value) || /^[0-9]{9}[X]$/.test(value) || /^[0-9]{9}[x]$/.test(value) || '' === value);
            }, jQuery.validator.format("Please input valid nic number"));

            $(document).ready(function () {
                $('#caseCreateForm').validate({
                    onkeyup: function (element) {
                        $(element).valid();
                    },
                    onfocusout: function (element) {
                        $(element).valid();
                    },
                    rules: {
                        subject: {
                            required: true,
                            maxlength: 32
                        },
                        description: {
                            required: true,
                            maxlength: 1024
                        },
                        caseDate: {
                            required: true
                        },
                        caseTypeId: {
                            required: true
                        },
                        priorityId: {
                            required: true
                        },
                        departmentId: {
                            required: true
                        },
                        productId: {
                            required: true
                        },
                        assigneeId1: {
                            required: true
                        },
                        phonenumber: {
                            number: true,
                            minlength: 9,
                            maxlength: 9,
                            required: true
                        },
                        customername: {
                            required: true
                        },
                        branchcode: {
                            required: true
                        },
                        caseCategory: {
                            required: true
                        },
                        title: {
                            required: true
                        },
                        parameter_value: {
                            nic: true,
                            required: true
                        },
                        nic: {
                            nic: true
                        }

                    }, errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }
                });

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

                $('#datetimepicker').datetimepicker('setDate', new Date());
                $('#datetimepicker').datetimepicker('setStartDate', new Date());
                var phonelength = '${userCase.phonenumber}';
                $("#phonenumber").rules("remove");
                if (phonelength.length > 9) {
                    $("#isIdd").attr('checked', true);
                    $("#phonenumber").rules("add", {
                        required: true, minlength: 9,
                        maxlength: 20,
                        number: true
                    });
                } else {
                    $("#phonenumber").attr('maxlength', '9');
                    $("#phonenumber").rules("add", {
                        required: true,
                        minlength: 9,
                        maxlength: 9,
                        number: true
                    });
                }

                $('#isIdd').change(function () {
                    $("#phonenumber").rules("remove");
                    if ($('#isIdd').is(':checked')) {
                        $("#phonenumber").attr('maxlength', '20');
                        $("#phonenumber").rules("add", {required: true,
                            minlength: 9,
                            maxlength: 20,
                            number: true
                        });
                    } else {
                        $("#phonenumber").attr('maxlength', '9');
                        $("#phonenumber").rules("add", {
                            required: true,
                            minlength: 9, maxlength: 9,
                            number: true
                        });
                    }
                    $('#phonenumber').valid();
                });
            });

            function assigneeList() {
                var branchcode = $('#branchcode').val();
                var caseCategory = $('#caseCategory').val();
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
                                $('#employeeId').val(response);
                            },
                            error: function () {
                                console.log('Error while request..');
                            }
                        });
                    }
                }
            }

            $('#manuslassigneeId1').attr('disabled', 'disabled');
            $('#checkbox').change(function () {
                $("#manuslassigneeId1").rules("remove");
                $("#manuslassigneeId1").get(0).selectedIndex = 0;
                $("#caseCategory").get(0).selectedIndex = 0;
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

            $('#manuslassigneeId1').change(function () {
                $('#employeeId').val($('#manuslassigneeId1').val());
                var selectitem = $("#manuslassigneeId1 :selected").text();
                var array = selectitem.split('-');
                console.log(array);
                console.log(array[0]);
                $('#assigneeId1').val(array[0]);
            });

            function caseAttachFileSelect() {
                $('#upload_file').parent().siblings().val($('#upload_file').val());
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
    </body>
</html>