<%-- 
    Document   : userrolewedgetview
    Created on : May 6, 2016, 9:27:51 AM
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">User Role Wedget Management</a></li><li>View Wedget Details</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            User Role Wedget Management
                            <span>
                                View Wedget Details
                            </span>
                        </h1>
                    </div>
                </div>
                <form:form class="smart-form" id="userroleWedgetCreateForm" novalidate="novalidate" commandName="wedgetView" action="${pageContext.request.contextPath}/wedget/add" method="post" >  
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
                                <label class="label">User Role <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="userroleid" path="userroleid" items="${userRoleList}" onchange="SortIdList('${pageContext.servletContext.contextPath}')"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>

                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Wedget<samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="wedgetid" path="wedgetid" items="${wedgetList}"/>
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
                                <label class="label">Sort Id<samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input id="sortid" path="sortid" />
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>


                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Wedget width <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="wedgetwidthid" path="wedgetwidthid" items="${wedgetWidthList}"/>
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
                                <label class="label">Status <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="statusid" path="statusid" items="${statusList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                </form:form>
                <!-- END OF FORM -->
                <!-- START MODAL -->

                <!-- END MODAL -->                                        
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
                $('#sortid').append($("<option></option>").attr("value", '').text('-- Select --'));
                $('#wedgetCreateForm').validate({
                    rules: {
                        description: {
                            required: true
                        },
                        statusid: {
                            required: true
                        }
                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }

                });
            });

            $('#pagesubmit').click(function () {
                $("#userroleWedgetCreateForm").valid();
            });

            function SortIdList(context) {
                var roleid = $('#userroleid').val();
                if (roleid !== '') {
                    $.ajax({
                        type: "POST",
                        url: context + "/asd/asd",
                        cache: false,
                        data: 'userrole=' + roleid,
                        success: function (response) {
                            for (var i = 0; i < response.length; i++) {
                                $('#sortid').append($("<option></option>").attr("value", response[i].sortid).text(response[i].sortid));
                            }
                        },
                        error: function () {
                            console.log('Error while request..');
                        }
                    });
                }

            }


            function pageSubmit() {
                $('#msg_dev').empty();
                if ($("#userroleWedgetCreateForm").valid()) {
                    var dataObject = new Object();
                    dataObject.description = $('#description').val();
                    dataObject.statusid = $('#statusid').val();
                    console.log(dataObject);
                    var content = JSON.stringify(dataObject);
                    console.log(content);
                    $.ajax({
                        type: "post",
                        url: "${pageContext.servletContext.contextPath}/wedget/created",
                        cache: false,
                        data: {wedget_info: content},
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
    </body>

