<%-- 
    Document   : userroletaskupdate
    Created on : Dec 7, 2015, 3:15:52 PM
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
                    <li> <a href="${pageContext.servletContext.contextPath}/userroletask/search">Task Management</a></li><li>Update</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Task Update
                            <span>
                                
                            </span>
                        </h1>
                    </div>
                </div>
                <form:form class="smart-form" id="useRoleSection" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userroletaskupdate" method="post" >
                    <form:input type="hidden" id="multitaskarray" path="multitaskarray"/>
                    <form:input type="hidden" id="userrolesubsectionid" path="userrolesubsectionid"/>
                    <form:input type="hidden" id="oldmultitaskarray" items="${assigneTask}" path="oldmultitaskarray"/>
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
                                    <form:select id="userroleid" path="userroleid"  disabled="true" items="${userRoleList}" onchange="RoleSectionList('${pageContext.servletContext.contextPath}')"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>

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
                    </div>
                    <div class="row">             
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3" id="sectionids">
                            <section>
                                <label class="label">Sub Section</label>
                                <label class="select">
                                    <form:select id="subsectionid" path="subsectionid"  items="${subsectionList}"/> <i></i>
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
                                    <form:select id="taskid" path="taskid" items="${notAssigneTask}" multiple="true" style="height:100px;"/>
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
                                    <form:select id="multitask" items="${assigneTask}" path="multitask" multiple="true" style="height:100px;"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <form:button type="button" disabled="${avn_update}" class="btn btn-primary" id ="pagesubmit" onclick="pageSubmit();">Save</form:button>  
                                <a id="back_btn" type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/userroletask/search">Back to Search</a>
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
                        if ($("#useRoleSection").valid()) {
                            var dataObject = new Object();
                            dataObject.multitaskarray = $('#multitaskarray').val();
                            dataObject.userroleid = $('#userroleid').val();
                            dataObject.userrolesubsectionid = $('#userrolesubsectionid').val();
                            dataObject.oldmultitaskarray = $('#oldmultitaskarray').val();
                            console.log(dataObject);
                            var content = JSON.stringify(dataObject);
                            console.log(content);
                            $.ajax({
                                type: "post",
                                url: "${pageContext.servletContext.contextPath}/userroletask/updated",
                                cache: false,
                                data: {userroletask_update: content},
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


                    function RoleSectionList(context) {
                        var roleid = $('#userroleid').val();
                        if (roleid !== '') {
                            $.ajax({
                                type: "POST",
                                url: context + "/userroletask/userrolesubsection",
                                cache: false,
                                data: 'userroleid=' + roleid,
                                success: function (response) {
                                    for (var i = 0; i < response.length; i++) {
                                        console.log(response);
                                        $('#userrolesubsectionid').append($("<option></option>").attr("value", response[i].userrolesubsectionid).text(response[i].sectionDes + "|" + response[i].subsectionDes));

                                    }
                                },
                                error: function () {
                                    console.log('Error while request..');
                                }
                            });
                        }
                    }

                    function TaskList(context) {
                        var dataObject = new Object();
                        dataObject.sectionid = $('#sectionid').val();
                        dataObject.userroleid = $('#userroleid').val();
                        dataObject.subsectionid = $('#subsectionid').val();
                        var content = JSON.stringify(dataObject);
                        if (content !== '') {
                            $.ajax({
                                type: "POST",
                                url: context + "/userroletask/tasklist",
                                cache: false,
                                data: 'userroleid=' + content,
                                success: function (response) {
                                    for (var i = 0; i < response.length; i++) {
                                        console.log(response);
                                        $('#taskid').append($("<option></option>").attr("value", response[i].taskid).text(response[i].taskDes));
                                    }
                                },
                                error: function () {
                                    console.log('Error while request..');
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
                generateBranchArray();
                generateBranchArrayOld();
                var roleid = $('#userroleid').val();
                console.log(roleid);
                if (roleid !== '') {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/userroletask/userrolesubsection",
                        cache: false,
                        data: 'userroleid=' + roleid,
                        success: function (response) {
                            for (var i = 0; i < response.length; i++) {
                                console.log(response);
                                $('#userrolesubsectionid').append($("<option></option>").attr("value", response[i].userrolesubsectionid).text(response[i].sectionDes + "|" + response[i].subsectionDes));
                            }
                            $("#userrolesubsectionid").val("${userroletaskupdate.userrolesubsectionid}");
                        },
                        error: function () {
                            console.log('Error while request..');
                        }
                    });
                }

                $('#useRoleSection').validate({rules: {
                        userroleid: {
                            required: true
                        },
                        multitask: {
//                            multitaskvalidate: true
                        },
                        userrolesubsectionid: {
                            required: true
                        }
                    },
                    errorPlacement: function (error, element) {
                        error.insertAfter(element.parent());
                    }

                });
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
            function generateBranchArrayOld() {
                var section = [];
                $("#multitask option").each(function () {
                    section.push($(this).val());
                });
                $('#oldmultitaskarray').val(JSON.stringify(section));
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
        </script>  
    </body>

</html>



