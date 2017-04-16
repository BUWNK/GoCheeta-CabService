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
                    <li>Call Log</li><li>Management</li>
                </ol>
                <!-- end breadcrumb -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Call Center Management View
                            <span>
                                Call Log Id : ${objcallcenter.callLogId}
                            </span>
                        </h1>
                    </div>
                </div>         
      
                <form:form class="smart-form" commandName="callCenterForm" action="${pageContext.servletContext.contextPath}/callcenter/calllogview/insert" method="post">



                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <c:if test="${not empty successMsg}">
                                <div class="successMsg">${successMsg}</div> ${callLogId}
                                <br/>
                            </c:if>
                            <c:if test="${not empty errorMsg}">
                                <div class="errorMsg">${errorMsg}</div> 
                                <br/>
                            </c:if>
                        </div>
                    </div>



                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <section>
                                <label class="label">Full Name</label>
                                <label class="input">
                                    <form:input type="text" id="name_in_full" path="name_in_full"  value="${fullname}"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Company Name</label>
                                <label class="input">
                                    <form:input type="text" id="companyname" path="companyname" placeholder="companyname name" value="${companyname}"/>
                                </label>
                            </section>
                        </div>    
                    </div>  
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Start date <samp style="color: red">*</samp></label>
                                <label class="input"> <i class="icon-append fa fa-calendar"></i>

                                    <input type="text" name="startDate" id="startDate" path="startDate" placeholder=" Select Date"
                                           class="form-control datepicker"  data-dateformat="yy-mm-dd">

                                    </section>
                                    </div>

                                    <div class="col-xs-1"></div>
                                    <div class="col-xs-2">
                                        <section>
                                            <label class="label">Start Time <samp style="color: red">*</samp></label>
                                            <label class="input">
                                                <form:input type="text" id="startTime" path="startTime" placeholder="Time"/>
                                            </label>
                                        </section>
                                    </div>

                                    <div class="col-xs-1"></div>
                                    <div class="col-xs-3">
                                        <section>
                                            <label class="label">Call Direction <samp style="color: red">*</samp></label>
                                            <label class="select">
                                                <form:select value="${callDirectoryList.value}" id="callDirection" path="callDirection"  items="${callDirectoryList}"/>
                                                <i></i>
                                            </label>
                                        </section>
                                    </div>
                                    <div class="col-xs-1"></div>

                                    </div>




                                    <div class="row">
                                        <div class="col-xs-1"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Follow-up-Action <samp style="color: red">*</samp></label>
                                                <label class="select">
                                                    <form:select id="followUpAction" value="${fuaList.value}" path="followUpAction" items="${fuaList}"/>
                                                    <i></i>
                                                </label>
                                            </section>
                                        </div>

                                        <div class="col-xs-1"></div>
                                        <div class="col-xs-3">
                                            <section>
                                                <label class="label">Callback Date<samp style="color: red">*</samp></label>
                                                <label class="input"> <i class="icon-append fa fa-calendar"></i>

                                                    <input type="text" name="callbackDate" id="callbackDate" path="callbackDate"  placeholder=" Select Date"
                                                           class="form-control datepicker"  data-dateformat="yy-mm-dd" >

                                                    </section>
                                                    </div>

                                                    <div class="col-xs-1"></div>
                                                    <div class="col-xs-2">

                                                        <section>
                                                            <label class="label">Callback Time <samp style="color: red">*</samp></label>
                                                            <label class="input">
                                                                <form:input type="Time" id="callbackTime" path="callbackTime" placeholder="Callback Time" />
                                                            </label>
                                                        </section>
                                                    </div>
                                                    <div class="col-xs-1"></div>
                                                    </div>



                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-2">
                                                            <section>
                                                                <label class="label"></label>
                                                                <label class="input">
                                                                    <form:input type="hidden" id="accountId" path="accountId" value="${accounid}"/>
                                                                </label>
                                                            </section>

                                                        </div>

                                                        <div class="col-xs-2"></div>
                                                        <div class="col-xs-3">
                                                            <section>
                                                                <label class="label">Telephone</label>
                                                                <label class="input">
                                                                    <i class="icon-prepend fa fa-phone"></i>
                                                                    <form:input type="text" id="telephone" path="telephone" placeholder="Telephone" maxlength="9" value="${cli}"/>
                                                                </label>
                                                                <div class="note">
                                                                    <strong>Hint</strong> Eg :  777937099
                                                                </div>
                                                            </section>

                                                        </div>
                                                        <div class="col-xs-4"></div>
                                                    </div>  


                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <label class="label"></label>
                                                                <label class="input">
                                                                    <form:input type="hidden" id="callLogId" path="callLogId" value="${callLogId}"/>
                                                                </label>
                                                            </section>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>


                                                    <div class="row">
                                                        <div class="col-xs-1"></div>
                                                        <div class="col-xs-10">
                                                            <section>
                                                                <form:label path="comments" class="label">Comments <samp style="color: red">*</samp></form:label>
                                                                    <label class="textarea">
                                                                    <form:textarea path="comments" rows="3" class="custom-scroll" name="comments"></form:textarea>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>


                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <footer>

                                                                    <a href="/AVN-CCL/case/createcalllog?callLogId=${callLogId}" type="submit" class="btn btn-default" role="button">Case Create</a>
                                                                <form:button id="Save" type="submit" class="btn btn-primary" >
                                                                    Save  

                                                                </form:button>



                                                            </footer>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>

                                                </form:form>

                                                <section id="widget-grid" class="case-datatable">

                                                    <!-- row -->
                                                    <div class="row">

                                                        <!-- NEW WIDGET START -->
                                                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                                                            <!-- Widget ID (each widget will need unique ID)-->
                                                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">

                                                                <header>
                                                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                                                    <h2>Call log</h2>

                                                                </header>

                                                                <!-- widget div-->
                                                                <div>

                                                                    <form:form novalidate="novalidate" method="post" action="${pageContext.request.contextPath}/callcenter/callogview/a" commandName="callForm">
                                                                        <!-- widget content -->
                                                                        <div class="widget-body no-padding">

                                                                            <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th data-class="expand">ACCOUNTID</th>
                                                                                        <th data-class="expand"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"> </i> PHONE NUMBER</th>
                                                                                        <th data-class="expand">NAMEINFULL</th>
                                                                                        <th data-class="expand"><i class="glyphicon glyphicon-comment"> </i> COMMENTS</th>

                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach var = "call" items = "${callList}">
                                                                                        <tr>
                                                                                            <td>${call.accountId}</td>
                                                                                            <td>${call.telephone}</td>
                                                                                            <td>${call.nameInFull}</td>
                                                                                            <td>${call.comments}</td>




                                                                                        </tr>
                                                                                    </c:forEach>
                                                                                </tbody>
                                                                            </table>
                                                                        </form:form>


                                                                    </div>
                                                                </div>

                                                                <!-- end widget content -->

                                                            </div>
                                                            <!-- end widget div -->
                                                        </article>
                                                    </div>
                                                    <!-- end widget -->

                                                    <!-- WIDGET END -->


                                                </section>

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
                                                            $('#dt_basic').dataTable({
                                                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
                                                            "t" +
                                                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
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
                                                            }
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
                                                    // https://datatables.net/extensions/tabletools/button_options
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

                                                    })

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

                                                <script type="text/javascript">
                                                            $(document).ready(function () {
                                                    $('#callCenterForm').validate({
                                                    rules: {
                                                    nameInFull: {
                                                    required: true
                                                    },
                                                            startDate: {
                                                            required: true
                                                            },
                                                            startTime: {
                                                            required: true
                                                            },
                                                            callbackDate: {
                                                            required: true
                                                            },
                                                            comments: {
                                                            required: true
                                                            },
                                                            telephone: {
                                                            required: true
                                                            },
                                                            callbackTime: {
                                                            required: true
                                                            },
                                                            followUpAction: {
                                                            required: true
                                                            },
                                                            callDirection: {
                                                            required: true
                                                            }
                                                    }

                                                    });
                                                    });
                                                </script>
                                                </body>

                                                </html>