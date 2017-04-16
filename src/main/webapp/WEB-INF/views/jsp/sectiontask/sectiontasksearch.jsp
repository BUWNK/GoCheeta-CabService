<%-- 
    Document   : sectiontasksearch
    Created on : Dec 3, 2015, 10:46:05 AM
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
                    <li><a href="${pageContext.servletContext.contextPath}/case/search"></a>Section Task Management</li><li>Search</li>
                    <!-- end breadcrumb -->
                </ol>   
            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">	
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                           Section Task Search
                            <span>
                                
                            </span>
                        </h1>
                    </div>
                </div>


                <div class="row">

                    <!-- NEW COL START -->
                    <form:form class="smart-form"  novalidate="novalidate" method="get"  commandName="sectiontasksearch">

                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Section</label>
                                    <label class="select">
                                        <form:select id="sectionid" path="sectionid" items="${sectionList}" data-toggle="dropdown" onchange="clearTable()"/>
                                        <i></i>
                                    </label>
                                </section>
                            </div>  
                        </div>

                        <div class="row">
                            <div class="col-xs-1"></div>
                            <div class="col-xs-10">
                                <footer style="background-color: #ffffff">
                                    <form:button type="button" class="btn btn-primary"   disabled="${avn_search}" id="search_btn">Search</form:button>  
                                </footer>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                    </form:form>
                </div>
                <!-- widget grid -->
                <section id="widget-grid" class="case-datatable">

                    <!-- row -->
                    <div class="row">

                        <!-- NEW WIDGET START -->
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" role="widget">

                                <header>
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>User Role Section results</h2>

                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th data-hide="phone">Section</th>
                                                    <th data-class="expand"><i class=""></i>Task</th> 
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-clock-o txt-color-blue hidden-md hidden-sm hidden-xs"></i> Last Updated Time</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-clock-o txt-color-blue hidden-md hidden-sm hidden-xs"></i> Created Time</th>
                                                    <th data-class="expand"><i class=""></i>Created User</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i> Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>

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
            <jsp:include page="/WEB-INF/views/jsp/template/footer.jsp"/>
        </div>
        <!-- END PAGE FOOTER -->
        <jsp:include page="../template/jsinclide.jsp"/>


        <script type="text/javascript">
            // DO NOT REMOVE : GLOBAL FUNCTIONS!
            var search = false;

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

                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };

                var search_table = $('#dt_basic').dataTable({
                    "bProcessing": true,
                    "bServerSide": true,
                    "bFilter": false,
                    "sPaginationType": "full_numbers",
                    "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
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
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/userrolesection/searched",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "sectionid", "value": $('#sectionid').val()});
                        aoData.push({"name": "userroleid", "value": $('#userroleid').val()});

                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/sectiontask/searched",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "order": [[0, "desc"]],
                    "aoColumns": [
                        {"mDataProp": "sectionDes", "bSortable": false},
                        {"mDataProp": "taskDes", "bSortable": false},
                        {"mDataProp": "lastupdateddatetime", "bSortable": false},
                        {"mDataProp": "createddatetime", "bSortable": false},
                        {"mDataProp": "createduser", "bSortable": false},
                        {"mDataProp": "action", "bSortable": false}
                    ]
                });
                /* END BASIC */

                $('#search_btn').click(function () {
                    search = true;
                    search_table.fnDraw();
                });
            });

            function clearTable() {
                search = false;
                $('#dt_basic').dataTable().fnClearTable();
                $('#download').attr('disabled', 'disabled');
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
                ga.src = ('https:' === document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
    </body>
</html>


