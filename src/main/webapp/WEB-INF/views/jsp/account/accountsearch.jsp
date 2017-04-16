<%-- 
    Author     : Roshen Dilshan
    Document   : accountsearch
    Created on : Jun 26, 2015, 11:14:35 AM
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
                <ol id="search_bc" class="breadcrumb">
                    <li><a href="${pageContext.servletContext.contextPath}/account">Customer Management</a></li><li>Search Customer</li>
                </ol>
            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i>
                             Search Customer
                        </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-5 col-md-12 col-lg-12"  style="height: 25px;">
                        <!--                        <ul id="sparks" class="">
                                                    <a href="${pageContext.servletContext.contextPath}/account/create/view" class="btn btn-primary">Create New Customer</a>
                                                </ul>-->
                    </div>
                </div>
                <form:form novalidate="novalidate" method="post" action="${pageContext.servletContext.contextPath}/account/search" commandName="accountForm" autocomplete="off">
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
                        <!-- NEW COL START -->
                        <article class="col-sm-12 col-md-12 col-lg-6">
                            <div class="input-group input-group-sm">
                                <!--<div class="input-group-btn">
                                    <form :select id="search_type" path="search_type" items="${customerCodeTypeList}" class="btn btn-default dropdown-toggle" data-toggle="dropdown"/>
                                </div>-->
                                <form:input class="form-control input-lg" type="text" placeholder="Customer Code" id="parameter_value" path="parameter_value"/>
                                <div class="input-group-btn">
                                    <form:button type="submit" disabled="${avn_search}" id="search_btn" class="btn btn-default" >
                                        &nbsp;&nbsp;&nbsp;<i class="fa fa-fw fa-search fa-lg"></i>&nbsp;&nbsp;&nbsp;
                                    </form:button>
                                </div>
                            </div>
                        </article>
                    </div>
                </form:form>
                <!-- widget grid -->
                <section id="widget-grid" class="case-datatable">
                    <!-- row -->
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" role="widget">
                                <!-- widget options:
                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                                data-widget-colorbutton="false"
                                data-widget-editbutton="false"
                                data-widget-togglebutton="false"
                                data-widget-deletebutton="false"
                                data-widget-fullscreenbutton="false"
                                data-widget-custombutton="false"
                                data-widget-collapsed="true"
                                data-widget-sortable="false"

                                -->
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>Customer search results</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th> CCID </th>
                                                    <th data-hide="phone,tablet"> Customer Category </th>
                                                    <th data-class="phone"> Customer Category Type </th>
                                                    <th data-hide="expand"> Customer Code Type </th>
                                                    <th data-hide="phone,tablet"> Customer Code </th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-map-marker txt-color-blue hidden-md hidden-sm hidden-xs"> </i> Branch / Location </th>
                                                    <th> <i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i> Action </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${accountForm.searchData != null}">
                                                    <tr>
                                                        <td>${accountForm.searchData.ccid}</td>
                                                        <td>${accountForm.searchData.account_category}</td>
                                                        <td>${accountForm.searchData.account_category_type}</td>
                                                        <td>${accountForm.searchData.customer_code_type}</td>
                                                        <td>${accountForm.searchData.customer_code}</td>
                                                        <td>${accountForm.searchData.branch}</td>
                                                        <td>
                                                            <form:form novalidate="novalidate" method="post" action="${pageContext.servletContext.contextPath}/account/search/view" commandName="accountForm">
                                                                <form:hidden id="parameter_value" path="parameter_value" value="${accountForm.searchData.customer_code}"/>
                                                                <a id="form_submit" href="#" class="${avn_view}"> View </a>
                                                            </form:form>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->											
                        </article>
                        <!-- WIDGET END -->
                    </div>
                </section>
                <!-- end widget grid -->
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
            // DO NOT REMOVE : GLOBAL FUNCTIONS!
            $(document).ready(function () {
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
                var responsiveHelper_datatable_fixed_column = undefined;
                var responsiveHelper_datatable_col_reorder = undefined;
                var responsiveHelper_datatable_tabletools = undefined;

                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };

                var oTable = $('#dt_basic').dataTable({
                    "sDom": "" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
//                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
//                            "t" +
//                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        console.log(nRow);
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_dt_basic.respond();
                    }
                });

                /* END BASIC */

            });
        </script>

        <script type="text/javascript">
            $('#form_submit').click(function () {
                $('#form_submit').parent().submit();
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
