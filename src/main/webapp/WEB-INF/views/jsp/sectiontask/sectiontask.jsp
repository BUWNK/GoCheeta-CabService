<%-- 
    Document   : sectiontask
    Created on : Dec 2, 2015, 2:33:49 PM
    Author     : Isuru
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">Section Task Management</a></li><li>Assign Task</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Section Assign Task
                            <span>
                                
                            </span>
                        </h1>
                    </div>
                </div>
                <form:form class="smart-form" id="sectionsCreateForm" novalidate="novalidate" commandName="sectiontask" action="${pageContext.request.contextPath}/sections/create" method="post" >
                    <form:input type="hidden" id="multitaskarray" path="multitaskarray"/>
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
                                <label class="label">Section</label>
                                <label class="select">
                                    <form:select id="sectionid" path="sectionid" items="${sectionList}" onchange="TakList('${pageContext.servletContext.contextPath}')"/><i></i>
                                </label>
                            </section>
                        </div>

                    </div>
                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Task</label>
                                <label class="select">
                                    <form:select id="taskid" path="taskid" multiple="true" style="height:100px;"/>
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
                                        <button id="pull_right" type="button" class="btn btn-xs">>></button>
                                    </section>
                                    <section>
                                        <button id="pull_left" type="button" class="btn btn-xs"><<</button>
                                    </section>
                                </div>
                                <div class="col-xs-5"></div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label" style="height:19px;"></label>
                                <label class="select">
                                    <form:select id="multitask" path="multitask" multiple="true" style="height:100px;"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <form:button type="button" class="btn btn-primary" id ="pagesubmit" disabled="${avn_search}" onclick="pageSubmit();">Save</form:button>  
                            </footer>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>

                </form:form>
                <!-- END OF FORM -->
                <!-- START MODAL -->

                <!-- END MODAL -->                                        
                <script type="text/javascript">
                    function pageSubmit() {
                        $('#msg_dev').empty();
                        if ($("#sectionsCreateForm").valid()) {
                            var dataObject = new Object();
                            dataObject.sectionid = $('#sectionid').val();
                            dataObject.multitaskarray = $('#multitaskarray').val();
                            console.log(dataObject);
                            var content = JSON.stringify(dataObject);
                            console.log(content);
                            $.ajax({
                                type: "post",
                                url: "${pageContext.servletContext.contextPath}/sectiontask/addtasked",
                                cache: false,
                                data: {sectiontask_info: content},
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
            $(document).ready(function () {
                $('#sectionsCreateForm').validate({
                    rules: {
                        sectionid: {
                            required: true
                        },
                        multitask: {
//                            multitaskvalidate: true
                        }

                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }

                });
            });

            $('#pagesubmit').click(function () {
                $("#sectionsCreateForm").valid();
            });

            $('#pull_right').click(function () {
                var selectedItem = $("#taskid option:selected");
                $("#multitask").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
            });

            $('#pull_left').click(function () {
                var selectedItem = $("#multitask option:selected");
                $("#taskid").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
            });

            $('#taskid').dblclick(function () {
                var selectedItem = $("#taskid option:selected");
                $("#multitask").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
            });

            $('#multitask').dblclick(function () {
                var selectedItem = $("#multitask option:selected");
                $("#taskid").append(selectedItem);
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
                $("#multitask option").each(function () {
                    section.push($(this).val());
                });
                $('#multitaskarray').val(JSON.stringify(section));
            }
            jQuery.validator.addMethod("multitaskvalidate", function () {
                $('#multitask option').prop('selected', true);
                var selectedItem = $('#multitask option:selected');
                console.log(selectedItem.length);
                if (selectedItem.length > 0) {
                    return true;
                } else {
                    return false;
                }
            }, jQuery.validator.format("Please select atleast one task."));


            function TakList(context) {
                $('#taskid').empty();
                var sectionid = $('#sectionid').val();
                $('#multitask').empty();
                generateBranchArray();
                if (sectionid !== '') {
                    $.ajax({
                        type: "POST",
                        url: context + "/sectiontask/ajaxtasklist",
                        cache: false,
                        data: 'sectiontask=' + sectionid,
                        success: function (response) {
                            response = JSON.parse(response);
                            console.log(response);
                            for (var i = 0; i < response.notAssigntask.length; i++) {
                                $('#taskid').append($("<option></option>").attr("value", response.notAssigntask[i].taskid).text(response.notAssigntask[i].taskDes));
                            }

                            for (var i = 0; i < response.assigntask.length; i++) {
                                $('#multitask').append($("<option></option>").attr("value", response.assigntask[i].taskid).text(response.assigntask[i].taskDes));
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


