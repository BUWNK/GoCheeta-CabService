<%-- 
    Document   : subsectionupdate
    Created on : Nov 26, 2015, 11:44:10 AM
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">Sub Section Management</a></li><li>Update Sub Sections</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Subsection Update/View 
                            <span>
                                ID: ${subsectionsupdate.subsectionId} 
                            </span>
                        </h1>
                    </div>
                </div>

                <!--                <div class="row">
                                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                                        <h1 class="page-title txt-color-blueDark">
                                            <i class="fa fa-edit fa-fw "></i> 
                                            Sub Sections ID: ${subsectionsupdate.subsectionId} 
                                            <span>
                
                                            </span>
                                        </h1>
                                    </div>
                                </div>-->
                <form:form class="smart-form" id="subSectionsUpdateForm" novalidate="novalidate" commandName="subsectionsupdate" action="${pageContext.request.contextPath}/sections/create" method="post" >
                    <form:input type="hidden" id="subsectionId" path="subsectionId" value="${subsectionId}"/>
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
                                <label class="label">Section <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="sectionid" path="sectionid" items="${sectionList}"/>
                                    <i></i>
                                </label>
                            </section>

                        </div>

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Sub Section <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="subsectionDes" path="subsectionDes" placeholder="Sub Section"/>
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
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Icon <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="icon" path="icon" placeholder="Section"/>
                                    <i></i>
                                </label>
                            </section>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">URL <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="url" path="url" placeholder="URL"/>
                                    <i></i>
                                </label>
                            </section> 
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Clickable  URL</label>
                                <label class="afinity_checkbox">
                                    <form:checkbox path="clickableurl" checked="checked" id="checkbox" />
                                </label>
                            </section>

                        </div>
                    </div>
                    <div class="row">            
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Sort Id <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="sortid" path="sortid" placeholder="Sort Id"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <form:button type="button" class="btn btn-primary"  disabled="${avn_update}" id ="pagesubmit" onclick="pageSubmit();">Save</form:button>  
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
                        if ($("#subSectionsUpdateForm").valid()) {
                            var dataObject = new Object();
                            dataObject.subsectionId = $('#subsectionId').val();
                            dataObject.subsectionDes = $('#subsectionDes').val();
                            dataObject.sectionid = $('#sectionid').val();
                            dataObject.statusid = $('#statusid').val();
                            dataObject.url = $('#url').val();
                            dataObject.icon = $('#icon').val();
                            dataObject.sortid = $('#sortid').val();
                            dataObject.clickableurl = $("#checkbox").is(':checked') ? true : false;

                            console.log(dataObject);
                            var content = JSON.stringify(dataObject);
                            console.log(content);
                            $.ajax({
                                type: "post",
                                url: "${pageContext.servletContext.contextPath}/subsection/updated",
                                cache: false,
                                data: {subsection_info: content},
                                success: function (response) {
                                    response = JSON.parse(response);
                                    if (response.CODE === "SUCCESS") {
                                        $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Updated</div> <br/>');
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
                $('#subSectionsUpdateForm').validate({
                    rules: {
                        subsectionDes: {
                            required: true
                        },
                        sectionid: {
                            required: true
                        },
                        statusid: {
                            required: true
                        },
                        url: {
                            required: true
                        },
                        sortid: {
                            required: true
                        }
                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }

                });
            });

            $('#pagesubmit').click(function () {
                $("#subSectionsUpdateForm").valid();
            });

        </script>  
    </body>

</html>
