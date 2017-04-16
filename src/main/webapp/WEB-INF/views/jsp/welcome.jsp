<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-us">
    <head>
        <jsp:include page="template/cssinclude.jsp"/>
    </head>
    <body class="">
        <!-- HEADER -->
        <header id="header">			
            <jsp:include page="template/header.jsp"/>
        </header>
        <!-- END HEADER -->

        <aside id="left-panel">
            <jsp:include page="template/menu.jsp"/>
        </aside>
        <!-- END NAVIGATION -->

        <!-- MAIN PANEL -->
        <div id="main" role="main">
            <!-- RIBBON -->
            <div id="ribbon">
                <div class="row">
                    <div class="col-xs-6">
                        <ol class="breadcrumb">
                            <li>Home</li><li>CRM DASHBOARD</li>
                        </ol>
                    </div>
                    <div class="col-xs-6">
                        <p style="color: #ffffff; padding-top: 11px" class="text-right"><c:out value="${sessionScope.lastlogin}" /></p>
                    </div>
                </div>
                <!-- breadcrumb -->

                <!-- end breadcrumb -->

                <!-- You can also add more buttons to the
                ribbon for further usability

                Example below:

                <span class="ribbon-button-alignment pull-right">
                <span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa-grid"></i> Change Grid</span>
                <span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa-plus"></i> Add</span>
                <span id="search" class="btn btn-ribbon" data-title="search"><i class="fa-search"></i> <span class="hidden-mobile">Search</span></span>
                </span> -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">

                <c:if test="${MyCase}">
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>My Tickets</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <!--<div class="widget-body no-padding">-->

                                    <!--<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">-->
                                    <div>
                                        <!-- widget content -->
                                        <!--<div class="widget-body no-padding">-->
                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>
                                                <tr>
                                                    <th data-hide="phone">Ticket ID</th>
                                                    <th data-hide="phone,tablet">Assignee</th>
                                                    <th data-class="expand">Ticket Type</th>
                                                    <th data-hide="phone,tablet">Module</th>
                                                    <th data-hide="phone,tablet">NIC</th>
                                                    <th data-hide="phone,tablet">Telephone</th>
                                                    <th data-hide="phone">Priority</th>
                                                    <th data-hide="phone,tablet">Status</th>
                                                    <th data-hide="phone,tablet">Created</th>
                                                    <th data-hide="phone,tablet">Assigned</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i>Action</th>
                                                    <!--<th data-hide="phone,tablet">Created Date</th>-->
                                                </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                        <!--</div>-->
                                        <!-- end widget content -->
                                    </div>
                                    <!--</article>-->
                                </div>
                                <!-- end widget content -->
                            </div>
                            <!-- end widget div -->
                        </article>
                        <!-- WIDGET END -->
                    </div>
                </c:if>
                <div class="row">
                    <c:if test="${CallVsCaseChart}">
                        <!-- NEW WIDGET START -->
                        <article class="${CallVsCaseChartCSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${CallVsCaseChartID}" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
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
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>Calls Vs Tickets</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="row">
                                            <div class="col-xs-8"></div>
                                            <div class="col-xs-2"></div>
                                        </div>
                                        <div id="nogrid-graph" class="chart no-padding"></div>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->
                        </article>
                        <!-- WIDGET END -->
                    </c:if>
                    <c:if test="${AssignCase}">
                        <!-- NEW WIDGET START -->
                        <article class="${AssignCaseCSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${AssignCaseID}" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
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
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>Assigned Tickets (<%= session.getAttribute("username") %>)</h2>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="row">
                                            <div class="col-xs-8"></div>
                                            <div class="col-xs-2"></div>
                                        </div>
                                        <div id="nogrid-graph2" class="chart no-padding"></div>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->
                        </article>
                        <!-- WIDGET END -->
                    </c:if>
                </div>
                <c:if test="${ProductWise}">
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <article class="${ProductWiseCSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${ProductWiseID}" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
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
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2><span class="wtitel"> Product Wise Inquiry</span></h2>
                                    <div class="widget-toolbar">
                                        <div class="btn-group">
                                            <button class="btn dropdown-toggle btn-xs btn-warning" data-toggle="dropdown" id="Select_drpdwn">
                                                <span class="ctitle">
                                                    User wise(<%= session.getAttribute("username") %>)
                                                </span>  <i class="fa fa-caret-down"></i>
                                            </button>
                                            <ul class="dropdown-menu pull-right">
                                                <li>
                                                    <a href="javascript:void(0);" onclick="myFunction(1)">User wise(<%= session.getAttribute("username") %>)</a>
                                                </li>
                                                <li>
                                                    <a href="javascript:void(0);" onclick="myFunction(2)">Organization wise</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <!--<input type="button" value="chart" id="chart" />-->
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div id="normal-bar-graph" class="chart no-padding"></div>
                                                <div id="normal-bar-graph2" class="chart no-padding"></div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div id="pie-chart" class="chart"></div>
                                                <div id="pie-chart2" class="chart"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->
                        </article>
                        <!-- WIDGET END -->
                    </div>
                </c:if>
                <!-- end row -->

                <div class="row">
                    <!-- NEW WIDGET START -->
                    <c:if test="${MyCalls}">
                        <article class="${MyCallsCSS}">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget" id="${MyCallsID}" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" role="widget">
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                    <h2>My Calls </h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->

                                    <!-- widget content -->
                                    <!--<div class="widget-body no-padding">-->
                                    <div>
                                        <table id="dt_basic1" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>
                                                <tr>
                                                    <th data-hide="phone">Call log Id</th>
                                                    <th data-class="phone, expand">Name in full</th>
                                                    <th data-class="phone, expand">Module</th>
                                                    <th data-class="phone, tablet"> Telephone</th>
                                                    <th data-class="phone,tablet"> <i class="glyphicon glyphicon-sort">  </i>   Call direction</th>
                                                    <th data-class="phone,tablet">Follow up Action</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i> Action</th>
                                                </tr>
                                            </thead>
                                            <tbody></tbody> 
                                        </table>
                                        <!--<div id="bar-graph" class="chart no-padding"></div>-->
                                    </div>
                                </div>
                                <!-- end widget content -->
                            </div>
                            <!-- end widget div -->
                        </article>
                    </c:if>
                    <!-- end widget content -->
                </div>

            </div>
            <!-- end widget div -->
        </div>
        <!-- end widget -->

        <!-- PAGE FOOTER -->
        <div class="page-footer">
            <jsp:include page="template/footer.jsp"/>
        </div>

        <!-- END PAGE FOOTER -->

        <jsp:include page="template/jsinclide.jsp"/>

    </body>
    
    <script type="text/javascript">
        // DO NOT REMOVE : GLOBAL FUNCTIONS!
        $(document).ready(function () {
            pageSetUp();
            loadUseWiseBarChart();
            loadUseWisePieChart();
            loadCaseVsCaseChart();
            loadAssigneCaseChart();
            //                            setInterval("ajaxd('${pageContext.servletContext.contextPath}')", 30000);
            setInterval(function () {

            }, 3000);
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
 
            $('#dt_basic1').dataTable({
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
                        responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic1'), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    responsiveHelper_dt_basic.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    responsiveHelper_dt_basic.respond();
                },
                "sAjaxSource": "${pageContext.servletContext.contextPath}/calltable",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    $.ajax({
                        "dataType": 'json',
                        "type": "GET",
                        "url": "${pageContext.servletContext.contextPath}/calltable",
                        "data": aoData,
                        "global": false,
                        "success": fnCallback
                    });
                },
                "order": [[0, "desc"]],
                "aoColumns": [
                    {"mDataProp": "callLogId", "bSortable": true},
                    {"mDataProp": "name_in_full", "bSortable": true},
                    {"mDataProp": "productIdDes", "bSortable": true},
                    {"mDataProp": "telephone", "bSortable": true},
                    {"mDataProp": "callDirectionDes", "bSortable": true},
                    {"mDataProp": "followUpActionDes", "bSortable": true},
                    {"mDataProp": "action", "bSortable": false}

                ]
            });
            
            $('#dt_basic').dataTable({
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
                "sAjaxSource": "${pageContext.servletContext.contextPath}/casetable",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    $.ajax({
                        "dataType": 'json',
                        "type": "GET",
                        "url": "${pageContext.servletContext.contextPath}/casetable",
                        "data": aoData,
                        "global": false,
                        "success": fnCallback
                    });
                },
                "order": [[0, "desc"]],
                "aoColumns": [
                    {"mDataProp": "caseid", "bSortable": true},
                    {"mDataProp": "assignee", "bSortable": true},
                    {"mDataProp": "casetype", "bSortable": true},
                    {"mDataProp": "module", "bSortable": true},
                    {"mDataProp": "nic", "bSortable": true},
                    {"mDataProp": "phone", "bSortable": true},
                    {"mDataProp": "priority", "bSortable": true},
                    {"mDataProp": "status", "bSortable": true},
                    {"mDataProp": "created", "bSortable": false},
                    {"mDataProp": "assigned", "bSortable": false},
                    {"mDataProp": "action", "bSortable": false}
                ]
            });

            function loadCaseVsCaseChart() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/loadCaseVsCaseChart",
                    cache: false,
                    global: false,
                    dataType: 'json',
                    success: function (response) {
                        if ($('#nogrid-graph').length) {
                            var day_data = response;
                            Morris.Line({
                                element: 'nogrid-graph',
                                grid: true,
                                data: day_data,
                                xkey: 'x',
                                ykeys: ['y', 'z'],
                                labels: ['Ticket Count', 'Call Count'],
                                hideHover: 'auto',
                                resize: false,
                                parseTime: false

                            });
                        }
                    },
                    error: function () {
                        console.log("Error loading case vs call graph.");
                    }
                });
            }

            function loadAssigneCaseChart() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/loadAssigneCaseChart",
                    cache: false,
                    global: false,
                    dataType: 'json',
                    success: function (response) {
                        if ($('#nogrid-graph2').length) {
                            var day_data2 = response;
                            Morris.Line({
                                element: 'nogrid-graph2',
                                grid: true,
                                data: day_data2,
                                xkey: 'x',
                                ykeys: ['y'],
                                labels: ['Ticket count'],
                                hideHover: 'auto',
                                resize: false,
                                parseTime: false
                            });
                        }
                    },
                    error: function () {
                        console.log("Error loading assignee case chart.");
                    }
                });
            }

            /* ASSIGNEE CASE CHART */
        });

        //add the jQuery click/show/hide behaviours (or native JS if you prefer):
        $(document).ready(function () {
            if ($("#normal-bar-graph").is(":visible")) {
                $("#pie-chart").show();
                $("#pie-chart2").hide();
                $("#normal-bar-graph2").hide();
                //                                 document.getElementsByClassName("wtitel")[0].innerHTML = "Reset";
            }
            return false;
        });
        function myFunction(x) {
            //                            var x = document.getElementById("mySelect").value;
            //                                alert(x);
            if (x === 2) {
                $("#pie-chart").hide();
                $("#normal-bar-graph").hide();
                $("#pie-chart2").show();
                loadOrganizationWisePieChart();
                $("#normal-bar-graph2").show();
                loadOrganizationWiseBarChart()
                document.getElementsByClassName("ctitle")[0].innerHTML = "Organizatin wise";
            } else {
                $("#pie-chart2").hide();
                $("#normal-bar-graph2").hide();
                $("#pie-chart").show();
                loadUseWisePieChart();
                $("#normal-bar-graph").show();
                loadUseWiseBarChart();
                document.getElementsByClassName("ctitle")[0].innerHTML = "User wise";
            }
        }

        function loadOrganizationWiseBarChart() {
            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/loadOrganizationWiseBarchart",
                cache: false,
                global: false,
                dataType: 'json',
                success: function (response) {
                    if ($('#normal-bar-graph2').length) {
                        Morris.Bar({
                            element: 'normal-bar-graph2',
                            data: response.DpcontentOrg,
                            xkey: 'x',
                            ykeys: response.DepartmentOrg,
                            labels: response.DepartmentOrg,
                            hideHover: 'auto'
                        });
                    }
                },
                error: function () {
                    console.log("Error loading organization wise barchart.");
                }
            });
        }

        function loadOrganizationWisePieChart() {
            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/loadOrganizationWisePieChart",
                cache: false,
                global: false,
                dataType: 'json',
                success: function (response) {
                    if ($('#nogrid-graph').length) {
                        if ($('#pie-chart2').length) {
                            var data_pie = response;
                            $.plot($("#pie-chart2"), data_pie, {
                                series: {
                                    pie: {
                                        show: true,
                                        innerRadius: 0.5,
                                        radius: 1,
                                        label: {
                                            show: true,
                                            radius: 3 / 3,
                                            formatter: function (label, series) {
                                                return '<div style="font-size:13px;text-align:center;font-weight: bold; padding:4px;color:black;">' + label + '<br/>' + series.data[0][1] + '%</div>';
                                            },
                                            threshold: 0.1
                                        }
                                    }
                                },
                                legend: {
                                    show: true,
                                    noColumns: 1, // number of colums in legend table
                                    labelFormatter: null, // fn: string -> string
                                    labelBoxBorderColor: "#000", // border color for the little label boxes
                                    container: null, // container (as jQuery object) to put legend in, null means default on top of graph
                                    position: "ne", // position of default legend container within plot
                                    margin: [5, 10], // distance from grid edge to default legend container within plot
                                    backgroundColor: "#efefef", // null means auto-detect
                                    backgroundOpacity: 1 // set to 0 to avoid background
                                },
                                grid: {
                                    hoverable: true
                                }
                            });
                        }
                    }
                },
                error: function () {
                    console.log("Error loading organization pie chart.");
                }

            });
        }

        function loadUseWiseBarChart() {
            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/loadUserWiseBarchart",
                cache: false,
                global: false,
//                dataType: 'json',
                success: function (response) {
                     response = JSON.parse(response);
                    console.log("departme"+response.Department);
                    if ($('#normal-bar-graph').length) {
                        Morris.Bar({
                            element: 'normal-bar-graph',
                            data: response.Dcontent,
                            xkey: 'x',
                            ykeys: response.Department,
                            labels: response.Department,
                            hideHover: 'auto'
                        });
                    }
                },
                error: function () {
                    console.log("Error loading user wise barchart.");
                }
            });
        }

        function loadUseWisePieChart() {
            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/loadUserWisePieChart",
                cache: false,
                global: false,
                dataType: 'json',
                success: function (response) {
                    if ($('#nogrid-graph').length) {
                        if ($('#pie-chart').length) {
                            var data_pie = response;
                            $.plot($("#pie-chart"), data_pie, {
                                series: {
                                    pie: {
                                        show: true,
                                        innerRadius: 0.5,
                                        radius: 1,
                                        label: {
                                            show: true,
                                            radius: 2 / 3,
                                            formatter: function (label, series) {
                                                return '<div style="font-size:13px;text-align:center;font-weight: bold; padding:4px;color:black;">' + label + '<br/>' + series.data[0][1] + '%</div>';
                                            },
                                            threshold: 0.1
                                        }
                                    }
                                },
                                legend: {
                                    show: true,
                                    noColumns: 1, // number of colums in legend table
                                    labelFormatter: null, // fn: string -> string
                                    labelBoxBorderColor: "#000", // border color for the little label boxes
                                    container: null, // container (as jQuery object) to put legend in, null means default on top of graph
                                    position: "ne", // position of default legend container within plot
                                    margin: [5, 10], // distance from grid edge to default legend container within plot
                                    backgroundColor: "#efefef", // null means auto-detect
                                    backgroundOpacity: 1 // set to 0 to avoid background
                                },
                                grid: {
                                    hoverable: true
                                }
                            });
                        }
                    }
                },
                error: function () {
                    console.log("Error loading user piechart.");
                }
            });
        }
    </script>
</html>
