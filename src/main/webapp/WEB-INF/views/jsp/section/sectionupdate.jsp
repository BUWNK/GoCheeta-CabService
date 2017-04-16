<%-- 
    Document   : sectionupdate
    Created on : Nov 25, 2015, 10:46:33 AM
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">Section Management</a></li><li>Update Sections</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Section ID: ${sectionsUpdate.sectionid}
                            <span>

                            </span>
                        </h1>
                    </div>
                </div>
                <form:form class="smart-form" id="sectionsUpdateForm" novalidate="novalidate" commandName="sectionsUpdate" action="${pageContext.request.contextPath}/sections/create" method="post" >
                    <form:input type="hidden" id="sectionid" path="sectionid" value="${sectionid}"/>
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
                                <label class="label">Section Description<samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="sectionDes" id="sectionDes" path="sectionDes" placeholder="Section"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>

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

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Level <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="sectionlevel" path="sectionlevel" items="${SectionLevel}" onchange="ParentList('${pageContext.servletContext.contextPath}')"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>

                        <div id="parent">
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Parent<samp style="color: red">*</samp></label>
                                    <label class="select">
                                        <form:select id="parentsection" path="parentsection"   items="${ParantSection}"/>
                                        <i></i>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Only Parent Section</label>
                                <label class="afinity_checkbox">
                                    <form:checkbox path="onlyparentsection" name="checkbox" id="checkbox"/>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row" id="urldiv">
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
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Icon </label>
                                <label class="input">
                                    <form:input type="icon" id="icon" path="icon" placeholder="Section"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Sort Id <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="sortid" id="sortid" path="sortid" placeholder="Sort Id"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <form:button type="button" class="btn btn-primary" id ="pagesubmit" disabled="${avn_update}" onclick="pageSubmit();">Update</form:button>  
                                <a id="back_btn" type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/section/search">Back to Search</a>
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
                        if ($("#sectionsUpdateForm").valid()) {
                            var dataObject = new Object();
                            dataObject.sectionDes = $('#sectionDes').val();
                            dataObject.sectionid = $('#sectionid').val();
                            dataObject.statusid = $('#statusid').val();
                            dataObject.sectionlevel = $('#sectionlevel').val();
                            dataObject.parentsection = $('#parentsection').val();
                            dataObject.onlyparentsection = $("#checkbox").is(':checked') ? true : false;
                            dataObject.url = $('#url').val();
                            dataObject.icon = $('#icon').val();
                            dataObject.sortid = $('#sortid').val();
                            console.log(dataObject);
                            var content = JSON.stringify(dataObject);
                            console.log(content);
                            $.ajax({
                                type: "post",
                                url: "${pageContext.servletContext.contextPath}/section/updated",
                                cache: false,
                                data: {section_info: content},
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
//               
                if (${sectionsUpdate.onlyparentsection}) {
                    $("#urldiv").show();
                } else {
                    $("#urldiv").hide();
                }
                $('#sectionsUpdateForm').validate({
                    rules: {
                        sectionDes: {
                            required: true
                        },
                        statusid: {
                            required: true
                        },
                        sectionlevel: {
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
                ParentList('${pageContext.servletContext.contextPath}');
                var selcvalue = $('#sectionlevel').val();
                if (selcvalue >= '1') {
                    $('#parentsection').removeAttr('disabled');
//                    $("#parentsection").rules("add", {
//                        required: true
//                    });

                } else {
                    $('#parentsection').attr('disabled', 'disabled');
                    $("#parentsection").rules("remove");
                }

            });
            $('#pagesubmit').click(function () {
                $("#sectionsUpdateForm").valid();
            });
            $('#sectionlevel').on('change', function () {
                var selcvalue = $('#sectionlevel').val();
                console.log(selcvalue);
                if (selcvalue >= '1') {
                    $('#parentsection').removeAttr('disabled');
//                    $("#parentsection").rules("add", {
//                        required: true
//                    });

                } else {
                    $('#parentsection').attr('disabled', 'disabled');
                    $("#parentsection").rules("remove");
                }
            });
            function ParentList(context) {
                var sectionlevel = $('#sectionlevel').val();
                console.log(sectionlevel);
                $('#parentsection').empty();
                $('#parentsection').append($("<option></option>").attr("value", '').text('-- Select --'));
                if (sectionlevel !== '') {
                    $.ajax({
                        type: "POST",
                        url: context + "/section/parentsection",
                        cache: false,
                        data: 'sectionlevel=' + sectionlevel,
                        success: function (response) {
                            for (var i = 0; i < response.length; i++) {
                                console.log(response[i].sectionid);
                                $('#parentsection').append($("<option></option>").attr("value", response[i].sectionid).text(response[i].sectionDes));
                                if ('${sectionsUpdate.parentsection}' !== '' && response[i].sectionid ==${sectionsUpdate.parentsection}) {
                                    console.log("true");
                                    $("#parentsection").val("${sectionsUpdate.parentsection}");
                                }

                            }
//                            
                        },
                        error: function () {
                            console.log('Error while request..');
                        }
                    });
                }
            }
            function myFunction(element) {
                if (element.checked) {
                    $("#urldiv").show();
                }
                else {
                    $("#urldiv").hide();
                    $("#url").val('');
                }
            }

        </script>  
    </body>

</html>

