<%-- 
    Document   : calllog
    Created on : Jul 19, 2015, 4:19:34 PM
    Author     : Lahiru
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

                <!--                <span class="ribbon-button-alignment"> 
                                    <span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true">
                                        <i class="fa fa-refresh"></i>
                                    </span> 
                                </span>-->

                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li><a href="${pageContext.servletContext.contextPath}/calllogsearch">Call Center Management</a></li><li>Search</li>
                </ol>
                <!-- end breadcrumb -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-search fa-fw "></i>
                            Search Call Log
                        </h1>
                        <span>
                           
                        </span>
                    </div>
                </div>        

                <div class="row">

                    <!-- NEW COL START -->
                    <form:form novalidate="novalidate" method="get" action="${pageContext.request.contextPath}/calllog/searched" commandName="callsearch">
                        <article class="col-sm-12 col-md-12 col-lg-6">

                            <div class="input-group input-group-sm ">
                                <div class="input-group-btn">
                                    <form:select name = "searchoption" path="searchoption" class="btn btn-default dropdown-toggle" data-toggle="dropdown" onchange="clearTable()">
                                        <form:option value="callLogID" >Call Log ID</form:option>
                                        <form:option value="telephone" >Contact No</form:option>
                                        <form:option value="nameInFull" >Name</form:option>
                                    </form:select>
                                </div>
                                <form:input class="form-control input-lg" placeholder="Search" name="input"  path="input"/>
                                <div class="input-group-btn">
                                    <form:button  id="search_btn" type="button" disabled="${avn_search}" class="btn btn-default">Search</form:button>
                                    </div>
                                </div>
                            </article>
                    </form:form>
                </div>

                <section id="widget-grid" class="case-datatable">

                    <!-- row -->
                    <div class="row">

                        <!-- NEW WIDGET START -->
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" role="widget">

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
                                                    <th data-hide="phone">Call Log Id</th>
                                                    <th data-hide="phone">Agent Name</th>
                                                    <th data-hide="phone">Customer Name</th>
                                                    <th data-class="expand"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"> </i> Mobile No</th>
                                                    <th data-hide="expand"> <i class="glyphicon glyphicon-sort">  </i>  Call Direction</th>
                                                    <th data-hide="phone,tablet">Follow up Action</th>
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
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/calllog/searched",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name": "search", "value": search});
                        aoData.push({"name": "searchoption", "value": $('#searchoption').val()});
                        aoData.push({"name": "input", "value": $('#input').val()});
//                        aoData.push({"name": "nameInFull", "value": $('#nameInFull').val()});
                        $.ajax({
                            "dataType": 'json',
                            "type": "GET",
                            "url": "${pageContext.servletContext.contextPath}/calllog/searched",
                            "data": aoData,
                            "success": fnCallback
                        });
                    },
                    "order": [[0, "desc"]],
                    "aoColumns": [
                        {"mDataProp": "callLogId", "bSortable": false},
                        {"mDataProp": "username", "bSortable": false},
                        {"mDataProp": "nameInFull", "bSortable": false},
                        {"mDataProp": "telephone", "bSortable": false},
                        {"mDataProp": "callDirectionDes", "bSortable": false},
                        {"mDataProp": "followUpActionDes", "bSortable": false},
                        {"mDataProp": "action", "bSortable": false}

                    ]
                });

                /* END BASIC */

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
                $('#download').val('');
                $('#dt_basic').dataTable().fnClearTable();
            }

            $('#searchoption').on('change', function () {
                $('#input').val('');
            });


            $('#input').keyup(function () {
                var searchoption = $('#searchoption').val();
                if (searchoption === "nameInFull") {
                    $('#input').val($('#input').val().toUpperCase());
                }
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
