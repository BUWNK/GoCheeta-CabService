<%--
    Document   : callview
    Created on : Jul 19, 2015, 4:19:34 PM
    Author     : Isuru
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <%--<sec:authorize access="hasRole('ROLE_USER')">--%>
        <aside id="left-panel">
            <jsp:include page="../template/menu.jsp"/>
        </aside>
        <!-- END NAVIGATION -->
        <%--</sec:authorize>--%>
        <!-- MAIN PANEL -->
        <div id="main" role="main">

            <!-- RIBBON -->
            <div id="ribbon">



                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li><a href="${pageContext.servletContext.contextPath}/calllogsearch">Call Center Management</a></li><li>View Call</li>
                </ol>
                <!-- end breadcrumb -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-eye fa-fw "></i> 
                            View Call Log
                            <span>
                                ID : ${objcallcenter.callLogId}
                            </span>
                        </h1>
                    </div>
                </div>       
                <!--                    <div class="row">
                                        <div class="col-xs-12 col-sm-5 col-md-12 col-lg-12">
                                            <ul id="sparks" class="">
                                                <a href="${pageContext.servletContext.contextPath}/callcenter/searchprofile?cli=${telephone}&datetime=${clidatetime}" class="btn btn-primary">View Full Profile</a>
                                        </ul>
                                    </div>
                                </div>-->
                <form:form class="smart-form" id="callview" commandName="callview" action="${pageContext.servletContext.contextPath}/call/view" method="get">
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div id="msg_dev" class="col-xs-10">
                            <c:if test="${not empty successMsg}">
                                <div class="alert alert-success">
                                    <strong>Success!</strong> ${successMsg} ${caseIdList}
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
                                <label class="label">Title</label>
                                <label class="input">
                                    <form:input type="text" id="title" disabled="true" path="title" value="${objcallcenter.title}"/>
                                </label>
                            </section>
                        </div>     



                        <div class="col-xs-8"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <section>
                                <label class="label">Name in Full <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="name_in_full"  path="name_in_full" disabled="true" value="${objcallcenter.name_in_full}"/>
                                </label>
                            </section>

                        </div>     
                        <div class="col-xs-2"></div>

                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Last Name</label>
                                <label class="input">
                                    <form:input type="text" id="last_name" path="last_name"  value="${objcallcenter.last_name}" disabled="true"/>
                                </label>
                            </section>
                        </div>     

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Company Name</label>
                                <label class="input">
                                    <form:input type="text" id="companyname" path="companyname" disabled="true"  placeholder="companyname name" value="${objcallcenter.companyname}"/>
                                </label>
                            </section>
                        </div>    
                        <div class="col-xs-2"></div>
                    </div>



                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Preferred Language<samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="preferred_language"  path="preferred_language"  disabled="true" value="${objcallcenter.languageID}"/>
                                </label>
                            </section>
                        </div>     

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Call Direction <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="callDirection"  path="callDirection"  disabled="true" value="${objcallcenter.callDirectionDes}"/>
                                </label>
                            </section>
                        </div>     
                        <div class="col-xs-7"></div>

                    </div> 
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <form:label path="productId" class="label">Module <samp style="color: red">*</samp></form:label>
                                    <label class="input">                      
                                    <form:input type="text" id="productId"  path="productId"  disabled="true" value="${objcallcenter.productIdDes}"/>
                                </label>
                            </section>

                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <form:label class="label" path="caseTypeId">Ticket Type <samp style="color: red">*</samp></form:label>
                                    <label class="input">
                                    <form:input type="text" id="caseTypeId"  path="caseTypeId"  disabled="true" value="${objcallcenter.caseTypeDes}"/>
                                </label>
                            </section>

                        </div>

                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Start date <samp style="color: red">*</samp></label>
                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                    <fmt:formatDate value="${objcallcenter.startDate}" pattern="yyyy-MM-dd" var="startdate"/>
                                    <form:input type="text" path="startDate" name="startDate" disabled="true" data-dateformat="yy-mm-dd" value="${startdate}" /></label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Start Time <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <fmt:formatDate value="${objcallcenter.startTime}" pattern="hh:mm a" var="startTime"/>
                                    <input type="text" id="startTime" disabled="true" value="${startTime}"/>
                                    <i class="icon-append fa fa-clock-o"></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Follow-up-Action <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="text" id="followUpAction"  path="followUpAction"  disabled="true"  value="${objcallcenter.followUpActionDes}"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <form:label class="label" path="statusDes">Status <samp style="color: red">*</samp></form:label>
                                    <label class="input">
                                    <form:input type="text" id="statusDes"  path="statusDes"  disabled="true" value="${objcallcenter.statusDes}"/>
                                </label>
                            </section>

                        </div>
                    </div>

                    <c:if test="${objcallcenter.callbackDate != null}">
                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Callback Date<samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <fmt:formatDate value="${objcallcenter.callbackDate}" pattern="yyyy-MM-dd" var="callbackDate"/>
                                        <form:input type="text" path="callbackDate" name="callbackDate" disabled="true" data-dateformat="yy-mm-dd" value="${callbackDate}" />
                                        <i class="icon-append fa fa-calendar"></i>
                                    </label>
                                </section>
                            </div>

                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">

                                <section>
                                    <label class="label">Callback Time <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <fmt:formatDate value="${objcallcenter.callbackTime}" pattern="hh:mm a" var="callbackTime"/>
                                        <input type="text" id="callbackTime" disabled="true" value="${callbackTime}"/>
                                        <i class="icon-append fa fa-clock-o"></i>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>


                    </c:if>         




                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Contact 1</label>
                                <label class="input">
                                    <i class="icon-prepend fa fa-phone"></i>
                                    <input type="text" id="telephone" disabled="true" value="${objcallcenter.telephone}" />
                                </label>
                                <div class="note">
                                    <strong>Hint</strong> e.g. 777101010
                                </div>
                            </section>
                        </div>

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Contact 2</label>
                                <label class="input">
                                    <i class="icon-prepend fa fa-phone"></i>
                                    <input type="text" id="telephone" disabled="true" value="${objcallcenter.telephone2}" />
                                </label>
                                <div class="note">
                                    <strong>Hint</strong> e.g. 777101010
                                </div>
                            </section>
                        </div>
                        <div class="col-xs-1">


                            <section>
                                <label class="label"></label>
                                <label class="input">
                                    <input type="hidden" id="accountId" disabled="true" value=""/>
                                </label>
                            </section>

                        </div>
                        <div class="col-xs-2"></div>
                    </div>  


                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <section>
                                <label class="label"></label>
                                <label class="input">
                                    <form:input type="hidden" id="callLogId" disabled="true" path="callLogId" value="${objcallcenter.callLogId}"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch</label>
                                <label class="input">
                                    <i class="icon-prepend fa fa-phone"></i>
                                    <input type="text" id="branchDes" disabled="true" value="${objcallcenter.branchDes}" />
                                </label>
                            </section>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-8">
                            <section>
                                <form:label path="comments" class="label">Comments <samp style="color: red">*</samp></form:label>
                                    <label class="textarea">
                                        <textarea path="comments" rows="3" class="custom-scroll" disabled="true"><c:out value="${objcallcenter.comments}"/></textarea>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>


                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <a id="back_btn" type="button" class="btn btn-default" href="${pageContext.servletContext.contextPath}/calllogsearch">Back to Search</a>
                            </footer>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                </form:form>
                <!-- END MAIN CONTENT -->





            </div>
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
                //$("#casecreate").hide();
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


    </body>
</html>



