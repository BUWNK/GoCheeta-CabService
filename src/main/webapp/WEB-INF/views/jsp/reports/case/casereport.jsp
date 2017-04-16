<%-- 
    Author     : Roshen Dilshan
    Document   : casereport
    Created on : Jul 28, 2015, 2:36:10 PM
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/views/jsp/template/cssinclude.jsp"/> 
    </head>
    <body>

        <!-- HEADER -->
        <header id="header">
            <jsp:include page="/WEB-INF/views/jsp/template/header.jsp"/>
        </header>
        <!-- END HEADER -->

        <aside id="left-panel">
            <jsp:include page="/WEB-INF/views/jsp/template/menu.jsp"/>
        </aside>
        <!-- END NAVIGATION -->

        <!-- MAIN PANEL -->
        <div id="main" role="main">
            <!-- RIBBON -->
            <div id="ribbon">

                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li>Reports</li><li>Ticket Report</li>
                </ol>

            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-files-o fa-fw"></i> 
                            Ticket Reports 
                            <span>
                            </span>
                        </h1>
                    </div>
                </div>

                <form:form id="caseReport" novalidate="novalidate" class="smart-form" commandName="caseReportForm" action="${pageContext.servletContext.contextPath}/report/case/report" autocomplete="off">
                    <form:hidden id="action" path="action"/>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">From Date </label>
                                <label class="input"> <i class="icon-prepend fa fa-calendar"></i>
                                    <form:input class="form-control" id="from_date" path="from_date" data-mask="2099-99-99" placeholder="From Date" onchange="clearTable()"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">To Date </label>
                                <label class="input"> <i class="icon-prepend fa fa-calendar"></i>
                                    <form:input class="form-control" id="to_date" path="to_date" data-mask="2099-99-99" placeholder="To Date" onchange="clearTable()"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Ticket Type </label>
                                <label class="select">
                                    <form:select id="case_type" path="case_type" items="${caseTypeList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Status </label>
                                <label class="select">
                                    <form:select id="status" path="status" items="${statusList}" onchange="clearTable()"/>
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
                                <label class="label">Priority </label>
                                <label class="select">
                                    <form:select id="priority" path="priority" items="${casePriorityList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Ticket Date </label>
                                <label class="input"> <i class="icon-prepend fa fa-calendar"></i>
                                    <form:input class="form-control" id="case_date" path="case_date" data-mask="2099-99-99" placeholder="Ticket Date" onchange="clearTable()"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Module </label>
                                <label class="select">
                                    <form:select id="product" path="product" items="${productList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Department </label>
                                <label class="select">
                                    <form:select id="department" path="department" items="${departmentList}" onchange="clearTable()"/>
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
                                <label class="label">Subject </label>
                                <label class="input">
                                    <form:input id="subject" path="subject" placeholder="Subject" onchange="clearTable()"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Assignee </label>
                                <label class="select">
                                    <form:select id="assignee" path="assignee" items="${userList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <form:hidden id="download_token_value_id" path="download_token_value_id"/>
                                <!--<button id="download_pdf" type="submit" class="btn btn-primary" onclick="pdfButton()" disabled="disabled">Download PDF</button>-->
                                <form:button id="download_excel" type="submit" disabled="${avn_download}" class="btn btn-primary" onclick="excelButton()">Download Excel</form:button>
                                <form:button id="search_btn" type="button" disabled="${avn_search}" class="btn btn-default">Search</form:button>
                                </footer>
                            </div>
                            <div class="col-xs-1"></div>
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
                                    <h2>Ticket search results</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th data-hide="phone"> Ticket ID</th>
                                                    <th data-class="expand"> Ticket Type</th>
                                                    <th data-class="expand"><i class="icon-append fa fa-calendar"></i> Ticket Date</th>
                                                    <th data-hide="phone"><i class="fa fa-fw fa-info text-muted hidden-md hidden-sm hidden-xs"></i> Priority</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i> Assignee</th>
                                                    <th data-hide="phone,tablet"> Status</th>                   
                                                    <th data-hide="phone,tablet">Created User</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-clock-o txt-color-blue hidden-md hidden-sm hidden-xs"></i> Last Updated Time</th>
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-clock-o txt-color-blue hidden-md hidden-sm hidden-xs"></i> Created Time</th>
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

        <jsp:include page="/WEB-INF/views/jsp/template/jsinclide.jsp"/>

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
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/report/case/tabledata",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "from_date", "value": $('#from_date').val()});
                        aoData.push({"name": "to_date", "value": $('#to_date').val()});
                        aoData.push({"name": "case_type", "value": $('#case_type').val()});
                        aoData.push({"name": "status", "value": $('#status').val()});
                        aoData.push({"name": "priority", "value": $('#priority').val()});
                        aoData.push({"name": "case_date", "value": $('#case_date').val()});
                        aoData.push({"name": "department", "value": $('#department').val()});
                        aoData.push({"name": "product", "value": $('#product').val()});
                        aoData.push({"name": "subject", "value": $('#subject').val()});
                        aoData.push({"name": "assignee", "value": $('#assignee').val()});

                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/report/case/tabledata",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "order": [[7, "desc"]],
                    "aoColumns": [
                        {"mDataProp": "case_id", "bSortable": false},
                        {"mDataProp": "case_type", "bSortable": false},
                        {"mDataProp": "case_date", "bSortable": false},
                        {"mDataProp": "priority", "bSortable": false},
                        {"mDataProp": "assignee_01", "bSortable": false},
                        {"mDataProp": "status", "bSortable": false},
                        {"mDataProp": "createduser", "bSortable": false},
                        {"mDataProp": "lastupdated_date_time", "bSortable": false},
                        {"mDataProp": "created_date_time", "bSortable": false}
                    ]
                });

                /* END BASIC */

                $('#search_btn').click(function () {
                    search = true;
                    search_table.fnDraw();
                });
            });

            $(document).ajaxSuccess(function () {
                var numberOfColumns = $('#dt_basic >tbody >tr:first').children('td').length;
                if (numberOfColumns > 1) {
                    $('#download_pdf').removeAttr('disabled');
                    $('#download_excel').removeAttr('disabled');
                } else {
                    $('#download_pdf').attr('disabled', 'disabled');
                    $('#download_excel').attr('disabled', 'disabled');
                }
            });

            function clearTable() {
                search = false;
                $('#dt_basic').dataTable().fnClearTable();
                $('#download_pdf').attr('disabled', 'disabled');
                $('#download_excel').attr('disabled', 'disabled');
            }

            function pdfButton() {
                $('#action').val('PDF');
                return true;
            }

            function excelButton() {
                $('#action').val('EXCEL');
                return true;
            }

        </script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#from_date').datetimepicker({
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0,
                    format: "yyyy-mm-dd"
                });

                $('#to_date').datetimepicker({
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0,
                    format: "yyyy-mm-dd"
                });

                $('#case_date').datetimepicker({
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0,
                    format: "yyyy-mm-dd"
                });

                $("#from_date").datetimepicker("setDate", new Date());
                $("#to_date").datetimepicker("setDate", new Date());
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
                ga.src = ('https:' === document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
    </body>
</html>
