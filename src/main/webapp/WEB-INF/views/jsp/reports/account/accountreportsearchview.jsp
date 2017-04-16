<%-- 
    Author     : Roshen Dilshan
    Document   : accountsearchview
    Created on : Aug 10, 2015, 4:23:38 PM
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-us">
    <head>
        <jsp:include page="/WEB-INF/views/jsp/template/cssinclude.jsp"/>
    </head>
    <body class="">

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
                <ol id="view_bc" class="breadcrumb">
                    <li>Reports</li><li>Customer Report</li>
                </ol>
            </div>
            <!-- END RIBBON -->


            <!-- MAIN CONTENT -->
            <div id="content">
                <!-- SEARCH CONTENT -->

                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-eye fa-fw "></i> 
                            Customer View - CCID : ${accountForm.searhDataBean.stakeholder_reference_no}
                            <span>
                            </span>
                        </h1>
                    </div>
                </div>
                <form:form id="accountReport" class="smart-form" commandName="accountForm" action="${pageContext.servletContext.contextPath}/report/account/pdf">
                    <form:hidden id="customer_code" path="customer_code"/>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Customer Category</label>
                                <label class="select">
                                    <form:select id="customer_category" path="searhDataBean.account_category" disabled="true" items="${categoryList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Customer Category Type</label>
                                <label class="select">
                                    <form:select id="account_type" path="searhDataBean.account_category_type" disabled="true" items="${categoryTypeList}"/>
                                    <i></i>
                                </label>
                            </section></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Code Type</label>
                                <label class="select">
                                    <form:select disabled="true" id="customer_code_type" path="searhDataBean.customer_code_type" items="${customerCodeTypeList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Customer Code</label>
                                <label class="input">
                                    <form:input type="text" disabled="true" id="customer_code" path="searhDataBean.customer_code" placeholder="Customer Code"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Branch / Location</label>
                                <label class="input">
                                    <form:input type="text" disabled="true" id="location" path="searhDataBean.location" placeholder="Branch / Location"/>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <!--<h2 class="row-seperator-header"><i class="fa fa-th-list"></i> Accordions </h2>-->
                        <!-- NEW WIDGET START -->
                        <article class="col-xs-10">
                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
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
                                    <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                                    <h2>Accordions </h2>
                                </header>
                                <!-- widget div-->
                                <div></div>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div class="panel-group smart-accordion-default" id="accordion">
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 0}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Personal Details </a></h4>
                                                </div>
                                                <div id="collapseOne" class="panel-collapse collapse in">
                                                    <div class="panel-body">
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Title</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="title" path="searhDataBean.title" items="${titleList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Name in Full</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="last_name" path="searhDataBean.name_in_full" placeholder="Name in Full"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Initials</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="initials" path="searhDataBean.initials" placeholder="Initials"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Preferred Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="preferrd_name" path="searhDataBean.first_name" placeholder="Preferred Name"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Last Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="last_name" path="searhDataBean.last_name" placeholder="Last Name"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Date of Birth</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="date_of_birth" path="searhDataBean.date_of_birth" placeholder="Date of Birth"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Gender</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="gender" path="searhDataBean.gender" items="${genderList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Nationality</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="nationality" path="" items="${nationalityList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Religion</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="reagon" path="searhDataBean.religion" items="${religonList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Marital Status</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="marital_status" path="searhDataBean.marital_status" items="${maritalStatusList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Preferred Language</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="prefferd_language" path="" placeholder="Preferred Language"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Mobile No 01</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="mobile_no_1" path="searhDataBean.mobile_no_1" placeholder="Mobile No 01"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Mobile No 02</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="mobile_no_2" path="searhDataBean.mobile_no_2" placeholder="Mobile No 02"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Email</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="email" path="searhDataBean.email" placeholder="Email"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <br/>
                                                        <br/>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 1 || accountForm.searhDataBean.account_category == 2}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseEight" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Corporate Customer Details </a></h4>
                                                </div>
                                                <div id="collapseEight" class="panel-collapse collapse in">
                                                    <div class="panel-body">
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Company Name <samp style="color: red">*</samp></label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="company_name" path="searhDataBean.company_name" placeholder="Company Name" maxlength="150"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Sector</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="sector_description" path="searhDataBean.sector_description" placeholder="Sector"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-6"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Sub Sector</label>
                                                                    <label class="select">
                                                                        <form:select id="subSectorList" multiple="true" path="" style="height:100px;" items="${subSectorList}"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-6"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <br/>
                                        </c:if>
                                        <c:if test="${accountForm.searhDataBean != null && accountForm.searhDataBean.account_category == 1 || accountForm.searhDataBean.account_category == 2}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="background-color: #F2F1F1">
                                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseNine" class="collapsed"> <i class="fa fa-lg fa-angle-down pull-right"></i> <i class="fa fa-lg fa-angle-up pull-right"></i> Contact Person Details </a></h4>
                                                </div>
                                                <div id="collapseNine" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">NIC</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_nic" path="searhDataBean.cp_nic" placeholder="NIC" maxlength="10"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Title</label>
                                                                    <label class="select">
                                                                        <form:select disabled="true" id="cp_title" path="searhDataBean.cp_title" items="${titleList}"/>
                                                                        <i></i>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-10">
                                                                <section>
                                                                    <label class="label">Name in Full</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_name_in_full" path="searhDataBean.cp_name_in_full" placeholder="Name in Full" maxlength="100"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Initials</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_initial" path="searhDataBean.cp_initial" placeholder="Initials"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Last Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_last_name" path="searhDataBean.cp_last_name" placeholder="Last Name" maxlength="20"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Preferred Name</label>
                                                                    <label class="input">
                                                                        <form:input type="text" disabled="true" id="cp_preferred_name" path="searhDataBean.cp_preferred_name" placeholder="Preferred Name" maxlength="20"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4"></div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-1"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Mobile No</label>
                                                                    <label class="input">
                                                                        <i class="icon-prepend fa fa-phone"></i>
                                                                        <form:input type="text" disabled="true" id="cp_mobile_no" path="searhDataBean.cp_mobile_no" placeholder="Mobile No" maxlength="9"/>
                                                                        <b class="tooltip tooltip-bottom-left">Ex: 777101010</b>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-2"></div>
                                                            <div class="col-xs-4">
                                                                <section>
                                                                    <label class="label">Email</label>
                                                                    <label class="input">
                                                                        <i class="icon-prepend fa fa-envelope-o"></i>
                                                                        <form:input type="text" disabled="true" id="cp_email" path="searhDataBean.cp_email" placeholder="Email" maxlength="100"/>
                                                                    </label>
                                                                </section>
                                                            </div>
                                                            <div class="col-xs-1"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <br/>
                                        </c:if>
                                        <!-- end widget content -->
                                    </div>
                                    <!-- end widget div -->
                                </div>
                                <!-- end widget -->
                            </div>
                        </article>
                        <!-- WIDGET END -->

                        <!-- NEW WIDGET START -->
                        <article class="col-sm-12 col-md-12 col-lg-6">

                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-10" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
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
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->
                        </article>
                        <!-- WIDGET END -->
                        <div class="col-xs-2"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <form:hidden id="download_token_value_id" path="download_token_value_id"/>
                                <form:button  type="submit"  id="download_pdf" disabled="${avn_download}" class="btn btn-primary">Download PDF</form:button>
                                <a id="back_btn" href="${pageContext.servletContext.contextPath}/report/account" class="btn btn-default">Back to Search</a>
                            </footer>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                </form:form>
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
                        console.log(nRow);
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

            });
        </script>

        <script type="text/javascript">

            $(document).ready(function () {
                $('#back_btn').click(function () {
                });
            });

            function setAddressValues(id) {
                var addressObject = $('#' + id).text();
                var obj = jQuery.parseJSON(addressObject);
                clearAddressElements();
                $('#address_type_description').val(obj.address_type_description);
                $('#address_line_1').val(obj.address_line_1);
                $('#address_line_2').val(obj.address_line_2);
                $('#address_line_3').val(obj.address_line_3);
                $('#country_name').val(obj.country_name);
                $('#province_name').val(obj.province_name);
                $('#district_name').val(obj.district_name);
                $('#city_name').val(obj.city_name);
                $('#gs_division_name').val(obj.gs_division_name);
                $('#gps').val(obj.gps);
                $('#land_phone_no').val(obj.land_phone_no);
            }

            //Clear all address related elements
            function clearAddressElements() {
                $('#mf_customer_reference').val();
                $('#stakeholder_reference_no').val();
                $('#address_line_1').val();
                $('#address_line_2').val();
                $('#address_line_3').val();
                $('#address_type_description').val();
                $('#city_name').val();
                $('#gs_division_name').val();
                $('#district_name').val();
                $('#province_name').val();
                $('#country_name').val();
                $('#gps').val();
                $('#land_phone_no').val();
                $('#fax').val();
                $('#version').val();
            }

            function setSavingValues(id) {
                var savingObject = $('#' + id).text();
                var obj = jQuery.parseJSON(savingObject);
                clearSavingElements();
                $('#savings_id_view').val(obj.savings_id_view);
                $('#account_number').val(obj.account_number);
                $('#product_code').val(obj.product_code);
                //                $('#stake_holder_reference').val(obj.stake_holder_reference);
                $('#current_balance').val(obj.current_balance);
                $('#freeze_amount').val(obj.freeze_amount);
                $('#available_balance').val(obj.available_balance);
                $('#savings_status').val(obj.status);
                $('#last_deposit_date').val(obj.last_deposit_date);
                $('#last_withdrawal_date').val(obj.last_withdrawal_date);
                $('#effective_start_date').val(obj.effective_start_date);
                $('#effective_end_date').val(obj.effective_end_date);
            }

            function clearSavingElements() {
                $('#savings_id_view').val();
                $('#account_number').val();
                $('#product_code').val();
                //                $('#stake_holder_reference').val();
                $('#current_balance').val();
                $('#freeze_amount').val();
                $('#available_balance').val();
                $('#status').val('');
                $('#last_deposit_date').val();
                $('#last_withdrawal_date').val();
                $('#effective_start_date').val();
                $('#effective_end_date').val();
            }

            function setMicroFinanceValues(id) {
                var loanObject = $('#' + id).text();
                var obj = jQuery.parseJSON(loanObject);
                clearMicroFinanceElements();
                $('#mf_cs').val(obj.cs);
                $('#mf_module_code').val(obj.module_code);
                $('#mf_customer_code').val(obj.customer_code);
                $('#mf_contract_no').val(obj.contract_no);
                $('#mf_alias_name').val(obj.alias_name);
                $('#mf_contract_date').val(obj.contract_date);
                $('#mf_total_offered_amount').val(obj.total_offered_amount);
                $('#mf_contract_status').val(obj.contract_status);
                $('#mf_total_arrears_amount').val(obj.total_arrears_amount);
                $('#mf_outstanding_amount').val(obj.outstanding_amount);
            }

            function clearMicroFinanceElements() {
                $('#mf_cs').val();
                $('#mf_module_code').val();
                $('#mf_customer_code').val();
                $('#mf_contract_no').val();
                $('#mf_alias_name').val();
                $('#mf_contract_date').val();
                $('#mf_total_offered_amount').val();
                $('#mf_contract_status').val();
                $('#mf_total_arrears_amount').val();
                $('#mf_outstanding_amount').val();
            }

            function setCreditValues(id) {
                var loanObject = $('#' + id).text();
                var obj = jQuery.parseJSON(loanObject);
                clearCreditElements();
                $('#cr_cs').val(obj.cs);
                $('#cr_module_code').val(obj.module_code);
                $('#cr_customer_code').val(obj.customer_code);
                $('#cr_contract_no').val(obj.contract_no);
                $('#cr_alias_name').val(obj.alias_name);
                $('#cr_contract_date').val(obj.contract_date);
                $('#cr_total_offered_amount').val(obj.total_offered_amount);
                $('#cr_contract_status').val(obj.contract_status);
                $('#cr_total_arrears_amount').val(obj.total_arrears_amount);
                $('#cr_outstanding_amount').val(obj.outstanding_amount);
            }

            function clearCreditElements() {
                $('#cr_cs').val();
                $('#cr_module_code').val();
                $('#cr_customer_code').val();
                $('#cr_contract_no').val();
                $('#cr_alias_name').val();
                $('#cr_contract_date').val();
                $('#cr_total_offered_amount').val();
                $('#cr_contract_status').val();
                $('#cr_total_arrears_amount').val();
                $('#cr_outstanding_amount').val();
            }

            function setPawningValues(id) {
                var loanObject = $('#' + id).text();
                var obj = jQuery.parseJSON(loanObject);
                clearPawningElements();
                $('#pw_cs').val(obj.cs);
                $('#pw_module_code').val(obj.module_code);
                $('#pw_customer_code').val(obj.customer_code);
                $('#pw_contract_no').val(obj.contract_no);
                $('#pw_alias_name').val(obj.alias_name);
                $('#pw_contract_date').val(obj.contract_date);
                $('#pw_total_offered_amount').val(obj.total_offered_amount);
                $('#pw_contract_status').val(obj.contract_status);
                $('#pw_total_arrears_amount').val(obj.total_arrears_amount);
                $('#pw_outstanding_amount').val(obj.outstanding_amount);
            }

            function clearPawningElements() {
                $('#pw_cs').val();
                $('#pw_module_code').val();
                $('#pw_customer_code').val();
                $('#pw_contract_no').val();
                $('#pw_alias_name').val();
                $('#pw_contract_date').val();
                $('#pw_total_offered_amount').val();
                $('#pw_contract_status').val();
                $('#pw_total_arrears_amount').val();
                $('#pw_outstanding_amount').val();
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
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>

    </body>

</html>
