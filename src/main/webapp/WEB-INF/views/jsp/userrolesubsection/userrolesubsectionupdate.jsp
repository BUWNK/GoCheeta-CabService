<%-- 
    Document   : userrolesubsectionupdate
    Created on : Nov 30, 2015, 11:08:59 AM
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">Role Subsection Management</a></li><li>Update</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                 <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-12">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            User Role Sub Section Update 
                            <span>
                                 
                            </span>
                        </h1>
                    </div>
                </div>
                <form:form class="smart-form" id="useRoleSection" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userolesubsectionupdate" method="post" >
                    <form:hidden id="multisubsectionarray" path="multisubsectionarray"/>
                    <form:input type="hidden" id="oldmultisubsectionarray" path="oldmultisubsectionarray"/>
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
                                    <form:select id="userroleid" path="userroleid"  disabled="true" items="${userRoleList}" onchange="SectionList('${pageContext.servletContext.contextPath}')"/>
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
                                <label class="label">Section<samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="sectionid" path="sectionid" disabled="true" items="${sectionList}" onchange="SubSectionList('${pageContext.servletContext.contextPath}')"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3" id="sectionids">
                            <section>
                                <label class="label">Sub Section</label>
                                <label class="select">
                                    <form:select id="subsectionid" items="${notAssigneSubSection}" path="subsectionid" multiple="true" style="height:100px;"/>
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
                                    <form:select id="multisection" items="${assigneSubSection}" path="multisection" multiple="true" style="height:100px;"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <form:button type="button" class="btn btn-primary" disabled="${avn_update}" id ="pagesubmit" onclick="pageSubmit();">Save</form:button> 
                                <a id="back_btn" type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/rolesubsection/search">Back to Search</a>
                            </footer>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>

                </form:form>
                <!-- END OF FORM -->
                <!-- START MODAL -->

                <!-- END MODAL -->   
                <script type="text/javascript">

                    function SubSectionList(context) {
                        var roleid = $('#sectionid').val();
                        $('#subsectionid').empty();
                        $('#multisection').empty();
                        generateBranchArray();
                        if (roleid !== '') {
                            $.ajax({
                                type: "POST",
                                url: context + "/userrolesubsection/subsections",
                                cache: false,
                                data: 'sectionid=' + roleid,
                                success: function (response) {
                                    for (var i = 0; i < response.length; i++) {
                                        $('#subsectionid').append($("<option></option>").attr("value", response[i].subsectionid).text(response[i].subsectionDes));
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
                        if ($("#useRoleSection").valid()) {
                            var dataObject = new Object();
                            dataObject.multisubsectionarray = $('#multisubsectionarray').val();
                            dataObject.userroleid = $('#userroleid').val();
                            dataObject.sectionid = $('#sectionid').val();
                            dataObject.oldmultisubsectionarray = $('#oldmultisubsectionarray').val();
                            
                            console.log(dataObject);
                            var content = JSON.stringify(dataObject);
                            console.log(content);
                            $.ajax({
                                type: "post",
                                url: "${pageContext.servletContext.contextPath}/userrolesubsection/updated",
                                cache: false,
                                data: {userrolesubsection_info: content},
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
            generateBranchArrayOld();
            $(document).ready(function () {
                generateBranchArrayOld();
                $('#useRoleSection').validate({
                    rules: {
                        userroleid: {
                            required: true
                        },
                        sectionid: {
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
                var selectedItem = $("#subsectionid option:selected");
                $("#multisection").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
            });

            $('#pull_left').click(function () {
                var selectedItem = $("#multisection option:selected");
                $("#subsectionid").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
            });


            $('#multisection').dblclick(function () {
                var selectedItem = $("#multisection option:selected");
                $("#subsectionid").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
            });

            $('#subsectionid').dblclick(function () {
                var selectedItem = $("#subsectionid option:selected");
                $("#multisection").append(selectedItem);
                selectedItem.prop("selected", false);
                generateBranchArray();
            });

            function resetMultiBranchSelect() {
                $('#multisectionarray').val();
                $('#multisection option').prop('selected', true);
                var selectedItem = $('#multisection option:selected');
                $('#subsectionid').append(selectedItem);
                selectedItem.prop('selected', false);
                sortSelect('#subsectionid', 'text', 'asc');
            }

            function generateBranchArray() {
                var section = [];
                $("#multisection option").each(function () {
                    section.push($(this).val());
                });
                $('#multisubsectionarray').val(JSON.stringify(section));
            }
            
            function generateBranchArrayOld() {
                var section = [];
                $("#multisection option").each(function () {
                    section.push($(this).val());
                });
                $('#oldmultisubsectionarray').val(JSON.stringify(section));
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
        </script>  
    </body>

</html>

