<%-- 
    Author     : Roshen Dilshan
    Document   : callreport
    Created on : Aug 13, 2015, 10:59:59 AM
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
                    <li>Reports</li><li>Call Log Report</li>
                </ol>

            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-files-o fa-fw"></i> 
                            Call Log Reports
                            <span>
                            </span>
                        </h1>
                    </div>
                </div>
                <form:form id="callReport" novalidate="novalidate" class="smart-form" commandName="callReportForm" action="${pageContext.servletContext.contextPath}/report/call/report" autocomplete="off">
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
                                <label class="label">Preferred Language </label>
                                <label class="select">
                                    <form:select id="preferred_language" path="preferred_language" items="${languageList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Call Direction </label>
                                <label class="select">
                                    <form:select id="call_direction" path="call_direction" items="${callDirectionsList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
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
                                <label class="label" >Case Type </label>
                                <label class="select">
                                    <form:select id="case_type" path="case_type" items="${caseTypeList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Follow-up-Action </label>
                                <label class="select">
                                    <form:select id="follow_up_action" path="follow_up_action" items="${followUpActionList}" onchange="clearTable()"/>
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
                                <label class="label">Name </label>
                                <label class="input"> <i class="icon-prepend fa fa-user"></i>
                                    <form:input id="name" path="name" placeholder="Name" onchange="clearTable()"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Contact #1 </label>
                                <label class="input"> <i class="icon-prepend fa fa-phone"></i>
                                    <form:input id="telephone" path="telephone" placeholder="Telephone" onchange="clearTable()"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch </label>
                                <label class="select">
                                    <form:select id="branch" path="branch" items="${branchList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>

                        <div class="col-xs-3">
                            <section>
                                <label class="label">User</label>
                                <label class="select">
                                    <form:select id="user" path="user" items="${userList}" onchange="clearTable()"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-7"></div>
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
                                    <h2>Call search results</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th data-hide="phone"><i class="glyphicon glyphicon-sort"></i> Call Log Id</th>
                                                    <th data-hide="phone"><i class="fa fa-fw fa-user txt-color-blue hidden-md hidden-sm hidden-xs"></i> Name</th>
                                                    <th data-class="expand"><i class="icon-append fa fa-calendar"></i> Call Start Date</th>
                                                    <th data-hide="phone"><i class="glyphicon glyphicon-sort"></i> Call Direction</th>
                                                    <th data-hide="phone,tablet"> Follow Up Action</th>
                                                    <!--<th data-hide="phone,tablet"> <i class="icon-append fa fa-calendar">  </i>  Call Back Date</th>-->
                                                    <th data-hide="phone,tablet"><i class="fa fa-fw fa-phone txt-color-blue hidden-md hidden-sm hidden-xs"></i> Phone Number</th>
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
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/report/call/tabledata",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "from_date", "value": $('#from_date').val()});
                        aoData.push({"name": "to_date", "value": $('#to_date').val()});
                        aoData.push({"name": "preferred_language", "value": $('#preferred_language').val()});
                        aoData.push({"name": "call_direction", "value": $('#call_direction').val()});
                        aoData.push({"name": "product", "value": $('#product').val()});
                        aoData.push({"name": "case_type", "value": $('#case_type').val()});
                        aoData.push({"name": "follow_up_action", "value": $('#follow_up_action').val()});
                        aoData.push({"name": "status", "value": $('#status').val()});
                        aoData.push({"name": "name", "value": $('#name').val()});
                        aoData.push({"name": "telephone", "value": $('#telephone').val()});
                        aoData.push({"name": "branch", "value": $('#branch').val()});
                        aoData.push({"name": "user", "value": $('#user').val()});
                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/report/call/tabledata",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "order": [[6, "desc"]],
                    "aoColumns": [
                        {"mDataProp": "calllogid", "bSortable": false},
                        {"mDataProp": "name", "bSortable": false},
                        {"mDataProp": "call_start_date", "bSortable": false},
                        {"mDataProp": "call_direction", "bSortable": false},
                        {"mDataProp": "follow_up_action", "bSortable": false},
//                        {"mDataProp": "call_back_date", "bSortable": false},
                        {"mDataProp": "phone_number", "bSortable": false},
                        {"mDataProp": "lastupdated_time", "bSortable": false},
                        {"mDataProp": "created_time", "bSortable": false}
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

                $('#callback_date').datetimepicker({
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
