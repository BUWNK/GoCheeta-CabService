<%-- 
    Document   : hierarchytarget
    Created on : Feb 23, 2017, 9:21:04 AM
    Author     : Roshen Dilshan
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
                    <li><a href="${pageContext.servletContext.contextPath}/hierarchytarget/sethierarchytarget">Hierarchy Target</a></li><li></li>
                </ol>

            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Hierarchy Target Management 
                            <span>
                            </span>
                        </h1>
                    </div>
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
                                    <h2>Hierarchy Target</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <table id="hierarchytargettable" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th data-hide="phone">Hierarchy ID</th>   
                                                    <th data-hide="phone">Hierarchy</th>
                                                    <th data-hide="phone">Amount</th>
                                                    <th data-hide="phone"><i class="fa fa-fw fa-hand-o-up hidden-md hidden-sm hidden-xs"></i> Action</th>
                                                </tr>
                                            </thead>
                                            <tbody></tbody>
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
                    <!-- end row -->
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

            $(document).ready(function () {

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

                var search_table = $('#hierarchytargettable').dataTable({
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
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#hierarchytargettable'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_dt_basic.respond();
                    },
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/hierarchytarget/tabledata",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": sSource,
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "order": [[0, "desc"]],
                    "aoColumns": [
                        {"mDataProp": "hierarchyid", "bSortable": false, "sClass": "hidetablecolumn"},
                        {"mDataProp": "hierarchy", "bSortable": false},
                        {"mDataProp": "amount", "bSortable": false},
                        {"mDataProp": "action", "bSortable": false}
                    ]
                });
                /* END BASIC */



                $(document).on('click', '#hierarchytargettable tbody tr .action', function () {
                    var sel = $(this).closest('tr');
                    var dataObject = new Object();
                    dataObject.hierarchyid = sel.find('td:eq(0)').text();
                    dataObject.targetamount = sel.find('td:eq(2) input').val().replace(/,/g, '');
                    ;
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "${pageContext.servletContext.contextPath}/hierarchytarget/save",
                        data: JSON.stringify(dataObject),
                        dataType: 'json',
                        success: function (data) {
                            search_table.fnDraw();
                        },
                        error: function (e) {
                            console.log(e);
                        }
                    });
                });

                $('#hierarchytargettable tbody tr input').number(true, 2);

                /********** Add seperators when typing numbers **********/

                $(document).on('focusout', '#hierarchytargettable tbody tr input', function () {
                    $(this).number(true, 2);
                });

                $(document).on('keyup', '#hierarchytargettable tbody tr input', function (event) {
                    console.log("entred");
                    // skip for arrow keys
                    if (event.which >= 37 && event.which <= 40) {
                        event.preventDefault();
                    }
                    $(this).val(function (index, value) {
                        value = value.replace(/,/g, ''); // remove commas from existing input
                        return numberWithCommas(value); // add commas back in
                    });
                });

                function numberWithCommas(x) {
                    var parts = x.toString().split(".");
                    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                    return parts.join(".");
                }

                /************************* End *****************************/
            });
        </script>
    </body>
</html>