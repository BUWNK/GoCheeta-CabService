<%-- 
    Author     : Lahiru
    Document   : audittrace
    Created on : 
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
       <jsp:include page="../template/cssinclude.jsp"/>
    </head>
    <body>

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
                    <li>Audit Trace</li><li>Management</li>
                </ol>
                <!-- end breadcrumb -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->

            <!-- MAIN CONTENT -->
            <div id="content">	
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Audit Trace
                            <span>

                            </span>
                        </h1>
                    </div>
                </div>


                <div class="row">

                    <!-- NEW COL START -->

                    <article class="col-sm-10 col-md-10 col-lg-9">
                        <div class="row">
                            <div class="col-sm-5">
                                <div class="input-group input-group-sm">
                                    <span class="input-group-addon" id="sizing-addon3">FROM</span>
                                    <input id="from_date" class="form-control" path="from_date" data-mask="2099-99-99" placeholder="  From Date" onchange="clearTable()" style="height: 30px; width: 270px"/>

                                </div><br/>

                            </div>
                            <div class="col-sm-5">
                                <div class="input-group input-group-sm">
                                    <span class="input-group-addon" id="sizing-addon3">&nbsp;&nbsp;&nbsp;TO&nbsp;&nbsp;&nbsp;</span>
                                    <input id="to_date" class="form-control" path="to_date" data-mask="2099-99-99" placeholder="  To Date" onchange="clearTable()" style="height: 30px; width: 270px"/>

                                </div><br/>
                            </div>
                            <div class="col-sm-2">
                                <div class="input-group input-group-sm">
                                    <button id="search_btn" type="button" class="btn btn-default">search</button>
                                </div>
                            </div>

                        </div>
                    </article>
                </div>


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
                                    <h2>Case search results</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th data-hide="phone"> Audit Trace Id</th>
                                                    <th data-class="expand">   Affected Id</th>
                                                    <th data-class="expand">   Page</th>
                                                    <th data-hide="phone"> Task </th>
                                                    <th data-class="expand"> Description  </th>
                                                    <th data-hide="phone">   </i> Responsible user</th>
                                                    <th data-hide="phone,tablet"> Created DateTime</th>
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


        <!-- PAGE RELATED PLUGIN(S) -->

       <jsp:include page="../template/jsinclide.jsp"/>

        <script type="text/javascript">

        </script>

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
                var responsiveHelper_datatable_fixed_column = undefined;
                var responsiveHelper_datatable_col_reorder = undefined;
                var responsiveHelper_datatable_tabletools = undefined;

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
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/audittrace/searched",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "from_date", "value": $('#from_date').val()});
                        aoData.push({"name": "to_date", "value": $('#to_date').val()});
                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/audittrace/searched",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "aoColumns": [
                        {"mDataProp": "audittraceid", "bSortable": false},
                        {"mDataProp": "afectedid", "bSortable": false},
                        {"mDataProp": "afectedpage", "bSortable": false},
                        {"mDataProp": "task", "bSortable": false},
                        {"mDataProp": "description", "bSortable": false},
                        {"mDataProp": "responsibleuser", "bSortable": false},
                        {"mDataProp": "createddatetime", "bSortable": false}
                    ]
                });

                /* END BASIC */

                /* COLUMN FILTER  */
                var otable = $('#datatable_fixed_column').DataTable({
                    //"bFilter": false,
                    //"bInfo": false,
                    //"bLengthChange": false
                    //"bAutoWidth": false,
                    //"bPaginate": false,
                    //"bStateSave": true // saves sort state using localStorage
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_datatable_fixed_column) {
                            responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_datatable_fixed_column.respond();
                    }

                });

                // custom toolbar
                $("div.toolbar").html('<div class="text-right"><img src="img/logo.png" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>');

                // Apply the filter
                $("#datatable_fixed_column thead th input[type=text]").on('keyup change', function () {

                    otable
                            .column($(this).parent().index() + ':visible')
                            .search(this.value)
                            .draw();

                });
                /* END COLUMN FILTER */

                /* COLUMN SHOW - HIDE */
                $('#datatable_col_reorder').dataTable({
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_datatable_col_reorder) {
                            responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_col_reorder'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_datatable_col_reorder.respond();
                    }
                });
                /* END COLUMN SHOW - HIDE */

                /* TABLETOOLS */
                $('#datatable_tabletools').dataTable({
                    // Tabletools options: 
                    //   https://datatables.net/extensions/tabletools/button_options
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
                    "oTableTools": {
                        "aButtons": [
                            "copy",
                            "csv",
                            "xls",
                            {
                                "sExtends": "pdf",
                                "sTitle": "SmartAdmin_PDF",
                                "sPdfMessage": "SmartAdmin PDF Export",
                                "sPdfSize": "letter"
                            },
                            {
                                "sExtends": "print",
                                "sMessage": "Generated by SmartAdmin <i>(press Esc to close)</i>"
                            }
                        ],
                        "sSwfPath": "js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
                    },
                    "autoWidth": true,
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_datatable_tabletools) {
                            responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_tabletools'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_datatable_tabletools.respond();
                    }
                });
                /* END TABLETOOLS */

                $('#search_btn').click(function () {
                    search = true;
                    search_table.fnDraw();
                });
            });
//            $(document).ajaxSuccess(function () {
//                var rowCount = 0;
//                $('#dt_basic >tbody >tr').each(function () {
//                    if (!$(this).hasClass('odd')) {
//                        rowCount++;
//                    }
//                });
//                if (rowCount > 0) {
//                    $('#download').removeAttr('disabled');
//                } else {
//                    $('#download').attr('disabled', 'disabled');
//                }
//            });

            function clearTable() {
                search = false;
                $('#dt_basic').dataTable().fnClearTable();
                $('#download').attr('disabled', 'disabled');
            }

        </script>

        <script type="text/javascript">
//            $(document).ready(function () {

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

//                $('#from_date').datepicker({
//                    changeMonth: true,
//                    changeYear: true,
//                    yearRange: '1900:' + (new Date).getFullYear(),
//                    dateFormat: 'yy-mm-dd',
//                    prevText: '<i class="fa fa-chevron-left"></i>',
//                    nextText: '<i class="fa fa-chevron-right"></i>'
//                });
//
//                $('#to_date').datepicker({
//                    changeMonth: true,
//                    changeYear: true,
//                    yearRange: '1900:' + (new Date).getFullYear(),
//                    dateFormat: 'yy-mm-dd',
//                    prevText: '<i class="fa fa-chevron-left"></i>',
//                    nextText: '<i class="fa fa-chevron-right"></i>'
//                });
//            });
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
