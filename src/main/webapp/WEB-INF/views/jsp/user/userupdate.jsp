<%-- 
    Author     : Roshen Dilshan
    Document   : userupdate
    Created on : Sep 17, 2015, 12:05:34 PM
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
                    <li><a href="${pageContext.servletContext.contextPath}/user">User Management</a></li><li>Update User</li>
                </ol>

            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            User Management 
                            <span>
                            </span>
                        </h1>
                    </div>
                </div>

                <form:form id="userCreate" novalidate="novalidate" class="smart-form" commandName="userForm" action="${pageContext.servletContext.contextPath}/user/update" autocomplete="off">
                    <form:hidden id="multibranch" path="multibranch"/>
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
                                <label class="label">Username <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input id="username" path="username" placeholder="Username" readonly="true"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">User Role <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="user_role" path="user_role" items="${userRoleList}"/>
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
                                <label class="label">Email <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input id="email" path="email" placeholder="Email"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Status <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="status" path="status" items="${statusList}"/>
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
                                <label class="label">Employee Category </samp></label>
                                <label class="select">
                                    <form:select id="employeecategory" path="employeecategory" items="${employeeCategoryList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch </label>
                                <label class="select">
                                    <form:select id="branchsb" path="branchsb" items="${branchList}" disabled="${branchsbed}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row" id="radiooption" style="display: ${radiooptiondisplay};">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Only Branch Manager</label>
                                <label class="radio">
                                    <form:radiobutton id="bm" path="brorrm" value="BM" checked="checked"/>
                                    <i></i>
                                </label>
                            </section>
                            <section>
                                <label class="label">Regional Manager as Well</label>
                                <label class="radio">
                                    <form:radiobutton id="rm" path="brorrm" value="RM"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row" id="multibranchselect" style="display: ${multibranchdisplay};">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch</label>
                                <label class="select">
                                    <form:select id="branchmb_not_assign" path="branchmb_not_assign" multiple="true" style="height:100px;" items="${branchMultiselect}"/>
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
                                    <form:select id="branchmb_assign" path="branchmb_assign" multiple="true" style="height:100px;" items="${multiBranchList}"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row" id="isnotify" style="display: ${isnotifydisplay};">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Is Notify</label>
                                <label class="afinity_checkbox">
                                    <form:checkbox id="notify" path="notify"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <button type="submit" class="btn btn-primary">Save</button>
                                <a id="back_btn" type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user">Back to Search</a>
                            </footer>
                        </div>
                        <div class="col-xs-1"></div>
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


        <script type="text/javascript">
            $(document).ready(function () {
                jQuery.validator.addMethod("lettersonly", function (value, element) {
                    return this.optional(element) || /^[a-z0-9]+$/i.test(value);
                }, "Only alphabetical characters");

                $('#userCreate').validate({
                    rules: {
                        username: {
                            required: true,
                            lettersonly: true
                        },
                        user_role: {
                            required: true
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        status: {
                            required: true
                        }
                    }
                });

                $('#employeecategory').on('change', function () {
                    if ($('#employeecategory').val()) {
                        $("#branchsb").rules("remove");
                        $("#branchmb_assign").rules("remove");
                        $('#branchsb').removeAttr('disabled');
                        if ($('#employeecategory').val() === "BM") {
                            $('#radiooption').show();
                            resetMultiBranchSelect();
                            $('#multibranchselect').hide();
                            $("#branchsb").rules("add", {
                                required: true
                            });
                        } else if ($('#employeecategory').val() === "RM" || $('#employeecategory').val() === "ZM") {
                            $('#branchsb').val('');
                            $('#bm').prop('checked', true);
                            $('#rm').prop('checked', false);
                            $('#radiooption').hide();
                            resetMultiBranchSelect();
                            $('#multibranchselect').show();
                            $('#branchsb').attr('disabled', 'disabled');
                            $('#branchmb_assign').valid();
                            $("#branchmb_assign").rules("add", {
                                multibranchvalidate: true
                            });
                        } else {
                            $('#branchsb').val('');
                            $('#bm').prop('checked', true);
                            $('#rm').prop('checked', false);
                            $('#radiooption').hide();
                            resetMultiBranchSelect();
                            $('#multibranchselect').hide();
                        }
                        if ($('#employeecategory').val() === "IA" || $('#employeecategory').val() === "CC") {
                            $('#isnotify').show();
                        } else {
                            $('#isnotify').hide();
                        }
                    } else {
                        $('#branchsb').val('');
                        $('#branchsb').attr('disabled', 'disabled');
                        $('#bm').prop('checked', true);
                        $('#rm').prop('checked', false);
                        $('#radiooption').hide();
                        resetMultiBranchSelect();
                        $('#multibranchselect').hide();
                    }
                });

                $('#pull_right').click(function () {
                    var selectedItem = $("#branchmb_not_assign option:selected");
                    $("#branchmb_assign").append(selectedItem);
                    selectedItem.prop("selected", false);
                    generateBranchArray();
                    $('#branchmb_assign').valid();
                });

                $('#pull_left').click(function () {
                    var selectedItem = $("#branchmb_assign option:selected");
                    $("#branchmb_not_assign").append(selectedItem);
                    selectedItem.prop("selected", false);
                    generateBranchArray();
                    $('#branchmb_assign').valid();
                });

                $('#branchmb_not_assign').dblclick(function () {
                    var selectedItem = $("#branchmb_not_assign option:selected");
                    $("#branchmb_assign").append(selectedItem);
                    selectedItem.prop("selected", false);
                    generateBranchArray();
                    $('#branchmb_assign').valid();
                });

                $('#branchmb_assign').dblclick(function () {
                    var selectedItem = $("#branchmb_assign option:selected");
                    $("#branchmb_not_assign").append(selectedItem);
                    selectedItem.prop("selected", false);
                    generateBranchArray();
                    $('#branchmb_assign').valid();
                });

                $("input:radio[name=brorrm]").click(function () {
                    var value = $(this).val();
                    if (value === "RM") {
                        $('#multibranchselect').show();
                        $("#branchmb_assign").rules("add", {
                            multibranchvalidate: true
                        });
                    } else if (value === "BM") {
                        resetMultiBranchSelect();
                        $('#multibranchselect').hide();
                    }
                });
            });

            function resetMultiBranchSelect() {
                $('#multibranch').val();
                $('#branchmb_assign option').prop('selected', true);
                var selectedItem = $('#branchmb_assign option:selected');
                $('#branchmb_not_assign').append(selectedItem);
                selectedItem.prop('selected', false);
                sortSelect('#branchmb_not_assign', 'text', 'asc');
            }

            var sortSelect = function (select, attr, order) {
                if (attr === 'text') {
                    if (order === 'asc') {
                        $(select).html($(select).children('option').sort(function (x, y) {
                            return $(x).text().toUpperCase() < $(y).text().toUpperCase() ? -1 : 1;
                        }));
                        $(select).get(0).selectedIndex = 0;
                    }// end asc
                    if (order === 'desc') {
                        $(select).html($(select).children('option').sort(function (y, x) {
                            return $(x).text().toUpperCase() < $(y).text().toUpperCase() ? -1 : 1;
                        }));
                        $(select).get(0).selectedIndex = 0;
                    }// end desc
                }

            };

            function generateBranchArray() {
                var branch = [];
                $("#branchmb_assign option").each(function () {
                    branch.push($(this).val());
                });
                console.log(branch);
                $('#multibranch').val(JSON.stringify(branch));
            }

            jQuery.validator.addMethod("multibranchvalidate", function () {
                $('#branchmb_assign option').prop('selected', true);
                var selectedItem = $('#branchmb_assign option:selected');
                console.log(selectedItem.length);
                if (selectedItem.length > 0) {
                    return true;
                } else {
                    return false;
                }
            }, jQuery.validator.format("Please select atleast one branch."));
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
            })();</script>
    </body>
</html>
