<%-- 
    Document   : revenuetarget
    Created on : Jul 1, 2016, 3:08:03 PM
    Author     : ISURU
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
               <style>
            .columnhide{
                display: none
            }
        </style>
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">Target Management</a></li><li>View Target</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->
            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-12">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i>
                            Target Management
                            <span>
                                View Target
                            </span>
                        </h1>
                    </div>
                </div>

                <article class="col-sm-12 col-md-12 col-lg-12">

                    <!-- Widget ID (each widget will need unique ID)-->
                    <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" data-widget-deletebutton="false">
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
                            <span class="widget-icon"> <i class="fa fa-check"></i> </span>
                            <h2>Target Setting </h2>

                        </header>

                        <!-- widget div-->
                        <div>

                            <!-- widget edit box -->
                            <div class="jarviswidget-editbox">
                                <!-- This area used as dropdown edit box -->

                            </div>
                            <!-- end widget edit box -->

                            <!-- widget content -->
                            <div class="widget-body">



                                <!--                                    <div id="bootstrap-wizard-1" class="col-sm-12">
                                
                                                                        <div class="form-bootstrapWizard">
                                                                            <ul class="bootstrapWizard form-wizard">
                                                                                <li class="active" data-target="#step1">
                                                                                    <a href="#tab1" data-toggle="tab"> <span class="step">1</span> <span class="title">Target Setting</span> </a>
                                                                                </li>
                                                                                <li data-target="#step2">
                                                                                    <a href="#tab2" data-toggle="tab"> <span class="step">2</span> <span class="title">Organization Target</span> </a>
                                                                                </li>
                                                                                <li data-target="#step3">
                                                                                    <a href="#tab3" data-toggle="tab"> <span class="step">3</span> <span class="title">Branch Target</span> </a>
                                                                                </li>
                                                                                <li data-target="#step4" >
                                                                                    <a href="#tab4" data-toggle="tab"> <span class="step">4</span> <span class="title">Save Form</span> </a>
                                                                                </li>
                                                                                <li data-target="#step5" >
                                                                                    <a href="#tab5" data-toggle="tab"> <span class="step">5</span> <span class="title">Save Form</span> </a>
                                                                                </li>
                                                                            </ul>
                                                                            <div class="clearfix"></div>
                                                                        </div>-->
                                <form:form class="smart-form" id="wizard-1" novalidate="novalidate" action="${pageContext.request.contextPath}/subsections/searched" commandName="userolesection" method="post" >
                                    <form:input  type="hidden" id="multiregionalarray" path="multiregionalarray"/>
                                    <form:input  type="hidden" id="targetnotassignregion" path="targetnotassignregion"/>
                                    <form:input  type="hidden" id="multibrancharray" path="multibrancharray"/>
                                    <form:input  type="hidden" id="targetnotassignbranch" path="targetnotassignbranch"/>
                                    <form:input  type="hidden" id="regionaltargetlist" path="regionaltargetlist"/>
                                    <form:input  type="hidden" id="brachtargetlist" path="brachtargetlist"/>
                                    <form:input  type="hidden" id="activitytargettbl" path="activitytargettbl"/>
                                    <form:input  type="hidden" id="datatabledata" path="datatabledata"/>
                                    <form:input  type="hidden" id="regionactivity" path="regionactivity"/>

                                    <form:input  type="hidden" id="totalamount" path="totalvalue"/>
                                    <form:input  type="hidden" id="currentamout" path="oldtaget"/>
                                    <div class="tab-content">
                                        <!--<div class="tab-pane active" id="tab1">-->
                                        <div class="row">
                                            <div class="col-xs-1"></div>
                                            <div id="msg_dev" class="col-xs-10">
                                                <c:if test="${not empty successMsg}">
                                                    <div class="alert alert-success">
                                                        <strong>Success!</strong> ${successMsg}
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

                                        <label id="tab1"  ></label>
                                        <div class="row">

                                            <header>Organization Revenue</header>
                                            <br><br><br>
                                            <div class="row">
                                                <div class="col-xs-2"></div>

                                                <div class="col-xs-3">
                                                    <section>
                                                        <label class="label">Product<samp style="color: red">*</samp></label>
                                                        <label class="select">
                                                            <form:select id="productid" disabled="true" path="productid" items="${productList}"/>
                                                            <i></i>
                                                        </label>
                                                    </section>
                                                </div>
                                                <div class="col-xs-1"></div>
                                                <div class="col-xs-3">
                                                    <section>
                                                        <label class="label">Target Period<samp style="color: red">*</samp></label>
                                                        <label class="select">
                                                            <form:select  id="targetperiodid" disabled="true" path="targetperiodid" items="${sectionList}" onchange="activedatime();"/>
                                                            <i></i>
                                                        </label>
                                                    </section>
                                                </div>


                                            </div>


                                            <div class="row">
                                                <div class="col-xs-2"></div>
                                                <div class="col-xs-3">
                                                    <div class="form-group">
                                                        <form:label path="">Target Start Date:<samp style="color: red">*</samp></form:label>
                                                        <form:input disabled="true" path="targetstartdate" type="text" id="datetimepickerfromdate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-1"></div>
                                                    <div class="col-xs-3">
                                                        <div class="form-group">
                                                        <form:label path="">Target End Date:<samp style="color: red">*</samp></form:label>
                                                        <form:input path="targetenddate" disabled="true" type="text" id="datetimepickertodate" class="form-control"  data-date-format="yyyy-mm-dd" placeholder="From Date"></form:input>
                                                        </div>
                                                    </div>

                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-xs-2"></div>
                                                    <div class="col-xs-7">
                                                        <section>
                                                            <label class="label">Description<samp style="color: red">*</samp></label>
                                                            <label class="input">
                                                            <form:input path="tragetdes" disabled="true" type="text" name="tragetdes" value="${fullname}" /><i></i>
                                                        </label>
                                                    </section>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-2"></div>
                                                <div class="col-xs-3">
                                                    <section>
                                                        <label class="label">Target Amount<samp style="color: red">*</samp></label>
                                                        <label class="input">
                                                            <form:input path="targetrevenue" disabled="true" type="text" name="targetrevenue" id="targetrevenue"  /><i></i>
                                                        </label>
                                                    </section>
                                                </div>
                                            </div>

                                        </div>
                                        <header>Organization Activity</header>
                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">Activity ID</th>
                                                            <th data-hide="phone">Activity Type</th>
                                                            <th data-class="expand"><i class=""></i>Count</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>
                                        </div>
                                        <header>Region Revenue</header>
                                        <br> <br>
                                        <div class="row">
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Target<samp style="color: red">*</samp></label>
                                                    <label class="select">
                                                        <form:select id="targetid" path="targetid" items="${taget}" onchange="loadregionlist();" />
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>


                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-3">
                                                <section>
                                                    <label class="label">Target Amount<samp style="color: red">*</samp></label>
                                                    <label class="input">
                                                        <form:input id="totalrevenue" path="revenue" readonly="true" value="0" />
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>
                                        </div>


                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic2" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone"  style="display: none">Region ID</th>
                                                            <th data-hide="phone">Region Name</th>
                                                            <th data-hide="phone" style="width:145px" class="hasinput">Amount</th>

                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr>
                                                            <th style="display: none"></th>
                                                            <th style="text-align:right">Total:</th>
                                                            <th id="toatal"></th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>
                                            <br>

                                        </div>

                                        <header>Region Activity</header>
                                        <br> <br> 
                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic3" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">Region Id</th>
                                                            <th data-hide="phone">Region Name</th>
                                                            <th data-hide="phone" style="display: none">Activity Id</th>
                                                            <th data-hide="phone">Activity Name</th>
                                                            <th data-hide="phone" style="width:201px" class="hasinput">Count</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>

                                        </div>

                                        <!--</div>-->



                                        <!--<div class="tab-pane" id="tab3">-->
                                        <div class="row">
                                            <div class="col-xs-1"></div>
                                            <div id="msg_dev3" class="col-xs-10">
                                                <c:if test="${not empty successMsg}">
                                                    <div class="alert alert-success">
                                                        <strong>Success!</strong> ${successMsg}
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
                                        <br> <br> <br>             
                                        <header>Branch Revenue</header>
                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic4" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">Branch ID</th>
                                                            <th data-hide="phone">Branch Name</th>
                                                            <th data-hide="phone" style="width:201px" class="hasinput">Amount</th>

                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr>
                                                            <th style="display: none"></th>
                                                            <th style="text-align:right">Total:</th>
                                                            <th id="brnachtoatal"></th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>
                                            <br>

                                        </div>

                                        <header>Branch Activity</header>
                                        <br> <br> 
                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic5" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">Region Activity Id</th>
                                                            <th data-hide="phone">Region Activity Name</th>  
                                                            <th data-hide="phone" style="display: none">Branch Id</th>     
                                                            <th data-hide="phone">Branch Name</th>     
                                                            <th data-hide="phone" style="width:201px" class="hasinput">Count</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>

                                        </div>
                                        <br>                                           
                                        <br>
                                        <br>
                                        <header>Team Revenue</header>
                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic6" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">Region Brach Id</th>
                                                            <th data-hide="phone">Region Brach Name</th>
                                                            <th data-hide="phone" style="width:201px" class="hasinput" style="display: none">Group Id</th>
                                                            <th data-hide="phone" style="width:201px" class="hasinput">Group Name</th>
                                                            <th data-hide="phone" style="width:201px" class="hasinput">Amount</th>


                                                        </tr>
                                                    </thead>
                                                    <tfoot>

                                                    </tfoot>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>
                                            <br>
                                            <br>
                                        </div>

                                        <header>Team Member Revenue</header>
                                        <br> <br> 

                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic7" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">RBG Id</th>
                                                            <th data-hide="phone" style="width:301px">RBG Name</th>  
                                                            <th data-hide="phone" style="display: none">Branch Group Member Id</th>     
                                                            <th data-hide="phone">Branch Group Member</th>     
                                                            <th data-hide="phone" style="width:201px" class="hasinput">Count</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>

                                        </div>
                                        <br>
                                        <br>            
                                        <br>

                                        <header>Team Activity</header>
                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic8" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">RBA Id</th>
                                                            <th data-hide="phone" style=" width: 363px;">Region Branch Activity Name</th>
                                                            <th data-hide="phone" style="display: none" >Group Id</th>
                                                            <th data-hide="phone" style="width:201px">Group Name</th>
                                                            <th data-hide="phone" style="width:201px" >Amount</th>


                                                        </tr>
                                                    </thead>
                                                    <tfoot>

                                                    </tfoot>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>
                                            <br>

                                        </div>

                                        <header>Team Member Activity</header>
                                        <br> <br>

                                        <div class="row" style="margin-left:220px ; margin-top:50px">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table id="dt_basic9" class="table table-striped table-bordered table-hover" width="100%">
                                                    <thead>			                
                                                        <tr>
                                                            <th data-hide="phone" style="display: none">Id</th>
                                                            <th data-hide="phone">Activity Name</th>  
                                                            <th data-hide="phone" style="display: none">Branch Id</th>     
                                                            <th data-hide="phone">Branch Name</th>     
                                                            <th data-hide="phone" style="width:201px" class="hasinput">Count</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table> 
                                            </div>

                                        </div>
                                        <br>
                                        <br>
                                        <!--</div>-->



                                        <!--<form id="wizard-1" novalidate="novalidate">-->

                                        <!--                                                    <div class="form-actions">
                                                                                                <div class="row">
                                                                                                    <div class="col-sm-12">
                                                                                                        <ul class="pager wizard no-margin">
                                                                                                            <li class="previous first disabled">
                                                                                                            <a href="javascript:void(0);" class="btn btn-lg btn-default"> First </a>
                                                                                                            </li>
                                                                                                            <li class="previous disabled">
                                                                                                                <a href="javascript:void(0);" class="btn btn-lg btn-default"> Previous </a>
                                                                                                            </li>
                                                                                                            <li class="next last">
                                                                                                            <a href="javascript:void(0);" class="btn btn-lg btn-primary"> Last </a>
                                                                                                            </li>
                                                                                                            <li class="next">
                                                                                                                <a href="javascript:void(0);" class="btn btn-lg txt-color-darken"> Save and Move </a>
                                                                                                            </li>
                                                                                                        </ul>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>-->
                                    </form:form>
                                </div>


                            </div>

                        </div>
                        <!-- end widget content -->

                    </div>
                    <!-- end widget div -->

            </div>
            <!-- end widget -->

        </arti

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
<script>
    //tab navigetion------------------

    $(document).ready(function () {
        $("#targetrevenue").number(true, ',', ' ');
        $("#totalrevenue").number(true, ',', ' ');
        $("#barnchamount").number(true, ',', ' ');
        $("#tab3totalrevenue").number(true, ',', ' ');
        $("#totalbranchgroprevene").number(true, ',', ' ');
        $("#branchactivitycount").number(true, ',', ' ');

    });

    $('#regionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#targetactivityid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#regionaltargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#regionalactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#tabthreebranchid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#brnachtargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#branchgrouprevenuetargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#branchgroupmembertargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#branchactivitytargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#branchgroupmemberid').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#branchgroupactivitytargeid').append($("<option></option>").attr("value", '').text('-- Select --'));
    loadregionlist();
    loadRegionDropdownList();
    loadbranchregionlist();
    getRegionBranchActivity();
    getRegionActivityDetails();
    loadBrnachlist();
    getBranchActivityDetails();
    getBranchGroupRevenueDetails();
    getBranchGroupActivityDetails();
    getBranchGroupActivityMemberDetails();

//            });


    $('#bootstrap-wizard-1').bootstrapWizard({'tabClass': 'form-wizard',
        'onNext': function (tab, navigation, index) {

            var $valid = $("#wizard-1").valid();
            if (!$valid) {
                return false;
            } else {
                var state = $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).attr("class");

                var $valid = $("#wizard-1").valid();
                if (!$valid) {
                    return false;
                }
                if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 0 && state !== "complete active") {
                    var $valid = $("#wizard-1").valid();
                    if (!$valid) {
                        return false;
                    }
                    savetab1();
                }
                //
                if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 1) {
                    savetabtwo();
                    $("#targetiddropdown").rules("add", {
                        required: true
                    });
                    $('[name*="name"]').each(function () {
                        $(this).rules('add', {
                            number: true
                        });
                        //
                    });
                    //                          
                    var $valid = $("#wizard-1").valid();
                    if (!$valid) {
                        return false;
                    }



                }
                if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 2) {

                    $("#selectedregion").rules("add", {
                        required: true
                    });
                    var $valid = $("#wizard-1").valid();
                    if (!$valid) {
                        return false;
                    }
                    savetabthree();
                }

                if ($('#bootstrap-wizard-1').bootstrapWizard('currentIndex') === 3) {
                    //                            alert("last steo");
                    var $valid = $("#wizard-1").valid();
                    if (!$valid) {
                        return false;
                    }

                }

                //                        alert($('#bootstrap-wizard-1').bootstrapWizard('currentIndex'));
                $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).addClass(
                        'complete');
                $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).find('.step')
                        .html('<i class="fa fa-check"></i>');
            }
        }
    });
    //-------------tabnavigation-------------------------------


    var data = [
        [
            "Tigsasasaaser Nixon",
            "Tiger Nixon",
            "3"


        ],
        [
            "Tiger Nixon",
            "Garrett Winters",
            "4"
        ]
    ];

    $('#dt_basic').dataTable().fnClearTable();
    $('#dt_basic').dataTable().fnDestroy();
    var count = 0;
    var table = $('#dt_basic').DataTable({
        data: ${activitytype},
        'columnDefs': [
            {
                "targets": [0],
                "visible": false
            },
            {
                'targets': 2,
                'width': '1%',
                'className': 'dt-body-center',
                'render': function (data, type, full, meta) {
                    //console.log("data" + data);
                    count++;
                    return '<input type="text" readonly="readonly"  name="name' + count + '" value="' + $.number(data) + '" onchange="validate()" > <div id="errorContainer"></div>';
                }
            }],
        'searchable': false,
        'orderable': false,
        "bPaginate": false,
        "bFilter": false,
        "bInfo": false

    });


    function validate() {
        //            datatbel text box validation
        $('#dt_basic').find('tr').each(function () {
            $(this).find("input,text").each(function (i) {
                $(this).rules('add', {
                    required: true,
                    number: true
                });
            });
        });
    }




    function activedatime() {
        $('#datetimepickerfromdate').removeAttr('disabled');
    }

//    var myTable = $('#dt_basic').DataTable();
//    $('#myTable').on('click', 'tbody td', function () {
////        alert("ok");
//        myTable.cell(this).edit();
//    });
    var counter = 0;
    var date = new Date(), y = date.getFullYear(), m = date.getMonth();
    d = date.getDate();
    $('#datetimepickerfromdate').datetimepicker({
        weekStart: 1,
        todayHighlight: false,
        todayBtn: 2,
        autoclose: 1,
        startView: 3,
        minView: 2,
        forceParse: 0,
        startDate: new Date(y, m, 1),
        endDate: new Date(y, m, 1),
        format: "yyyy-mm-dd"
    });
    $('#datetimepickerfromdate').datetimepicker().on('changeDate', function (ev) {
        var date = new Date(), yy = date.getFullYear(), mm = date.getMonth();
        dd = date.getDate();
        if ($('#targetperiodid').val() == 1) {
            mm = mm + 1;
            dd = 1;
        }
        if ($('#targetperiodid').val() == 2) {
            mm = m + 3;
            dd = 1;
        }
        if ($('#targetperiodid').val() == 3) {
            mm = m + 2;
            d = 1;
        }
        $('#datetimepickertodate').datetimepicker('setDate', new Date(yy, mm, 1));
    });
    function savetab1() {
        var array = [];
        $('#dt_basic').find('tr').each(function () {
            var idx = table
                    .row(this)
                    .index();
            //console.log(idx);
            if (idx >= 0) {
                var ID = table.cell(this, 0).data();
                var COUNT = 0;
                $(this).find("input,text").each(function (i) {
                    COUNT = $(this).val();
                });
                array.push({
                    ID: ID,
                    COUNT: COUNT
                });
            }

        });
        //console.log("array" + array);
        var jsonString = JSON.stringify(array);
        $("#activitytargettbl").val(jsonString);
        $('#msg_dev').empty();
        var dataObject = new Object();
        dataObject.productid = $('#productid').val();
        dataObject.targetperiodid = $('#targetperiodid').val();
        dataObject.targetstartdate = $('#datetimepickerfromdate').val();
        dataObject.targetenddate = $('#datetimepickertodate').val();
        dataObject.tragetdes = $('#tragetdes').val();
        dataObject.revenue = $('#revenue').val();
        dataObject.activitytargettbl = $('#activitytargettbl').val();
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        //console.log(content);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/target/savetabone",
            cache: false,
            data: {tab1_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
    }
    //-------------------------------------search ----------------------------------
    var responsiveHelper_dt_basic = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone: 480
    };
    ;
    /* END BASIC */
    var search_table;
    var TOTAL = 0;
    function loadregionlist() {
        countnew = 0
        search = true;
        $('#dt_basic2').dataTable().fnClearTable();
        $('#dt_basic2').dataTable().fnDestroy();
        search_table = $('#dt_basic2').dataTable({
            "bProcessing": false,
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
            //                    "drawCallback": function (oSettings) {
            //                        responsiveHelper_dt_basic.respond();
            //                    },
            //                    "drawCallback": function () {
            //                        var api = this.api();
            //                        $(api.table().footer()).html(
            //                                api.column(2, {page: 'current'}).data().sum()
            //                                );
            //                    },
            "sAjaxSource": "${pageContext.servletContext.contextPath}/target/regionlist",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: false,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/target/regionlist",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "branchid", "bSortable": false ,"sClass": "columnhide"},
                {"mDataProp": "branchdes", "bSortable": false},
                {"mRender": function (data, type, full) {
                        countnew++;
                        return '<input type="text" readonly="readonly" id="' + full["branchid"] + '"  name="feild' + countnew + '" value="' + $.number(full["revenue"]) + '"> <div id="errorContainer"></div>';
                    }}


            ]

        });
        var dataObject = new Object();
        dataObject.targetid = $('#targetid').val();
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        //console.log(content);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/target/sum",
            cache: false,
            async: true,
            data: {tab1_info: content},
            success: function (response) {
                response = JSON.parse(response);
                TOTAL = response.sum;
                TOTALAMOUNT = response.totalamount;
                $("#totalrevenue").val(TOTALAMOUNT);
                $("#currentamout").val(TOTAL);
                calcuation();
            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
        //                loadregionlistByTargetid();
        //                loadActivityList();
    }

    function clearTable() {
        search = false;
        $('#dt_basic2').dataTable().fnClearTable();
    }

    //-------------------------------------search ----------------------------------
    //-----------------------------calculation and save onchaneg------------------------------
    function calcuation() {
        $('#dt_basic2').find('tr').each(function () {
            $(this).find("input,text").each(function (i) {
                $(this).rules('add', {
                    required: true,
                    number: true
                });
            });
        });
        var $valid = $("#wizard-1").valid();
        var array = [];
        //                alert("this"+$(this).val());
        var COUNT = 0;
        $('#dt_basic2').find('tr').each(function () {
            $(this).find("input,text").each(function (i) {
                COUNT += parseInt($(this).val().replace(/,/g, ""));
                array.push({
                    ID: $(this).attr("id"),
                    COUNT: $(this).val()
                });
            });
        });

        $("#currentamout").val(COUNT);

        //                if ($("#totalrevenue").val() !== TOTAL && $valid) {
        $("#toatal").empty();
        $("#toatal").append('$' + $.number(COUNT) + ' ( $' + $.number(TOTAL) + ' total)');
        var jsonString = JSON.stringify(array);
        $("#datatabledata").val(jsonString);
        var dataObject = new Object();
        dataObject.targetid = $("#tabthreetargetid").val();
        dataObject.regionaltargetid = $("#regionaltargetid").val();
        dataObject.datatabledata = $('#datatabledata').val();
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        loadregionlistByTargetid();
        loadActivityList();
        //                }
    }


    //-----------------------------calculation and save onchaneg------------------------------

    //-----------------------------loadRegionlist and activity list to tabe 1------------------------------

    function loadregionlistByTargetid() {
        $("#regionalid").empty();
        $('#regionalid').append($("<option></option>").attr("value", '').text('-- Select --'));
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.servletContext.contextPath}/load/regionlist",
            data: {tab2_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                for (var i = 0; i < response.regionlist.length; i++) {
                    $('#regionalid').append($("<option></option>").attr("value", response.regionlist[i].regionid).text(response.regionlist[i].regiondes));
                }

            },
            error: function () {
                //console.log('Error while request..');
            }
        });
    }


    function loadActivityList() {
        $("#targetactivityid").empty();
        $('#targetactivityid').append($("<option></option>").attr("value", '').text('-- Select --'));
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.servletContext.contextPath}/load/activitylist",
            data: {tab2_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                for (var i = 0; i < response.activitylist.length; i++) {
                    $('#targetactivityid').append($("<option></option>").attr("value", response.activitylist[i].targetactivityid).text(response.activitylist[i].targetactivitydes));
                }

            },
            error: function () {
                //console.log('Error while request..');
            }
        });
    }


    //-----------------------------loadRegionlist and activity list to tabe 1------------------------------
    //---------------------------------load region activity count-------------------------------------------
    function targetactivitycount() {
        var dataObject = new Object();
        dataObject.targetactivityid = $("#targetactivityid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/gettargetactivitycount",
            cache: false,
            async: true,
            data: {tab2_info: content},
            success: function (response) {
                response = JSON.parse(response);
                //console.log("count" + response.count);
                $("#tab2totalcount").val(response.count);
            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });

        var selectedRegion = $('#regionalid option:selected');
        var selectedActivity = $('#targetactivityid option:selected');
        var region = selectedRegion.text();
        var regionid = selectedRegion.val();
//                alert("regionid" + regionid);
        var activity = selectedActivity.text();
        var activityid = selectedActivity.val();
//                alert("activityid" + activityid);
        var table3 = $('#dt_basic3').DataTable();

        $('#dt_basic3').find('tr').each(function () {
//                    alert("chek table data");
            var table3 = $('#dt_basic3').DataTable();
            var idx = table3.row(this).index();
//                    alert("idx" + idx);
            if (idx >= 0) {
                var tregionid = table3.cell(this, 0).data();
//                        alert("tregionid" + tregionid);
                var tactivityid = table3.cell(this, 2).data();
//                        alert("tactivityid" + tactivityid);
                if (regionid === tregionid && activityid === tactivityid) {
                    $("#tab2count").val(table3.cell(this, 4).data());
                }
            }
        });

    }
    //--------------------------------------------------------------------------------------------------
    //-----------------------------load activity count and check allready data in datatabel ------------------------------
    function loadActivitycount() {
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        dataObject.targetactivityid = $("#targetactivityid").val();
        var content = JSON.stringify(dataObject);
        //console.log(content);
        $.ajax({
            type: "POST",
            async: true,
            url: "${pageContext.servletContext.contextPath}/load/count",
            data: {tab2_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                $("#totalcount").val(response.count);
            },
            error: function () {
                //console.log('Error while request..');
            }
        });

    }

//    var table3 = $('#dt_basic3').DataTable({
//        "bFilter": false,
//        "sPaginationType": "full_numbers",
//        "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
//                "t" +
//                "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
//        "autoWidth": true
//    });

    function validation() {
        var sum = 0;
        var selectedItem = $('#targetactivityid option:selected');
        var tactivityid = selectedItem.val();
        $('#dt_basic3').find('tr').each(function () {
            var table3 = $('#dt_basic3').DataTable();
            var idx = table3.row(this).index();

            if (idx >= 0) {
                var tgroupid = table3.cell(this, 2).data();
                if (tactivityid === tgroupid) {
                    sum += parseInt(table3.cell(this, 4).data());
                }
//                        alert("sum" + sum);
            }
        });


        jQuery.validator.addMethod("sum", function () {
            if ($("#tab2totalcount").val() >= sum + $("#tab2totalcount").val()) {
                return true;
            } else {
                return false;
            }
        }, jQuery.validator.format("Please select atleast one subsection."));

    }

    //-----------------------------load  adta tabel33333 activity count------------------------------

    function getRegionActivityDetails() {
//                alert("ok woek");
        $('#dt_basic3').dataTable().fnClearTable();
        $('#dt_basic3').dataTable().fnDestroy();
        $('#dt_basic3').dataTable({
            "bProcessing": false,
            "bServerSide": true,
            "bFilter": false,
            cache: false,
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
            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/searchregionactivity",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: true,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/load/searchregionactivity",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "regionid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "regiondes", "bSortable": false},
                {"mDataProp": "targetactivityid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "targetactivitydes", "bSortable": false},
                {"mDataProp": "count", "bSortable": false}


            ]
        });

    }
    //----------------------------table row remove-------------------------------------------



    //-----------------------------------------------------------------------------------------
    //-----------------------------tab tow savae------------------------------

    function  savetabtwo() {
//                alert("save");
        $('#regionactivity').val(JSON.stringify($('#dt_basic3').tableToJSON()));
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        dataObject.regionactivity = $("#regionactivity").val();
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/target/savetabtwo",
            cache: false,
            data: {tabtwo_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
    }

    //-----------------------------tab tow savae------------------------------  
    //-----------------------------tab there load Regions------------------------------  

    function loadRegionDropdownList() {
//                alert("work");
        $('#regionaltargetid').empty();
        $('#regionaltargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.servletContext.contextPath}/load/regiondropdown",
            data: {tab3_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                for (var i = 0; i < response.regionlisttabthree.length; i++) {
                    $('#regionaltargetid').append($("<option></option>").attr("value", response.regionlisttabthree[i].regionaltargetid).text(response.regionlisttabthree[i].regiondes));
                }

            },
            error: function () {
                //console.log('Error while request..');
            }
        });
        RegionDropDownByTargetId();
    }

    //-----------------------------tab there load Regions-------------------------------------------  
    //-------------------------------load brnalist----------------------------------------------------------

    function loadRegionAmout() {
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        dataObject.regionaltargetid = $("#regionaltargetid").val();
        var content = JSON.stringify(dataObject);
        //console.log(content);
        $.ajax({
            type: "POST",
            async: true,
            url: "${pageContext.servletContext.contextPath}/loadregiontargetamount",
            data: {tab3_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                $("#tab3totalcount").val(response.amount);
            },
            error: function () {
                //console.log('Error while request..');
            }
        });

    }






    var responsiveHelper_dt_basic = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone: 480
    };
    ;
    /* END BASIC */
    var search_table2;
    var TOTAL = 0;
    function loadBrnachlist() {
        $('#dt_basic4').dataTable().fnClearTable();
        $('#dt_basic4').dataTable().fnDestroy();
        countnew = 0
        search = true;
        search_table2 = $('#dt_basic4').dataTable({
            "bProcessing": false,
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
            //                    "drawCallback": function (oSettings) {
            //                        responsiveHelper_dt_basic.respond();
            //                    },
            //                    "drawCallback": function () {
            //                        var api = this.api();
            //                        $(api.table().footer()).html(
            //                                api.column(2, {page: 'current'}).data().sum()
            //                                );
            //                    },
            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/searchbranchlist",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: false,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/load/searchbranchlist",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "branchid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "branchdes", "bSortable": false},
                {"mRender": function (data, type, full) {
                        countnew++;
                        return '<input type="text" readonly="readonly" id="' + full["branchid"] + '"  name="feild' + countnew + '" value="' + $.number(full["revenue"]) + '"  > <div id="errorContainer"></div>';
                    }}


            ]

        });
        var dataObject = new Object();
        dataObject.regionaltargetid = $('#regionaltargetid').val();
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        //console.log(content);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/branchtarget/sum",
            cache: false,
            async: true,
            data: {tab3_info: content},
            success: function (response) {
                response = JSON.parse(response);
                TOTAL = response.sum;
                $("#brnachtoatal").val(TOTAL);
                calcuationBrnachtarget();
            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });

        loadRegionAmout();
    }

    function clearTable() {
        search = false;
        $('#dt_basic2').dataTable().fnClearTable();
    }
    //--------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------Brancgt target list calculation-----------------

    function calcuationBrnachtarget() {
        $('#dt_basic4').find('tr').each(function () {
            $(this).find("input,text").each(function (i) {
                $(this).rules('add', {
                    required: true,
                    number: true
                });
            });
        });
        var $valid = $("#wizard-1").valid();
        var array = [];
        //                alert("this"+$(this).val());
        var COUNT = 0;
        if ($valid) {
            $('#dt_basic4').find('tr').each(function () {
                $(this).find("input,text").each(function (i) {
                    COUNT += parseInt($(this).val().replace(/,/g, ""));
                    array.push({
                        ID: $(this).attr("id"),
                        COUNT: $(this).val().replace(/,/g, "")
                    });
                });
            });
            $("#brnachtoatal").empty();
            $("#brnachtoatal").append('$' + $.number(COUNT) + ' ( $' + $.number(COUNT) + ' total)');
            var jsonString = JSON.stringify(array);
            $("#datatabledata").val(jsonString);
            var dataObject = new Object();
            dataObject.targetid = $("#targetid").val();
            dataObject.datatabledata = $('#datatabledata').val();
            dataObject.regionaltargetid = $("#regionaltargetid").val();
            dataObject.datatabledata = $('#datatabledata').val();
            //console.log(dataObject);
            var content = JSON.stringify(dataObject);

//            loadBrnachlist();

            loadBranchActivitycount();
            //                loadActivityList();
        }
    }

    //--------------------------------------------------------------------------------------------------------
    //-------------------------------Region List by Targetid-------------------------------------------------
    function RegionDropDownByTargetId() {
        //                $('#regionaltargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.servletContext.contextPath}/load/ractivitytargetlist",
            data: {tab3_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                for (var i = 0; i < response.ractivitytargetlist.length; i++) {
                    $('#regionalactivitytargetid').append($("<option></option>").attr("value", response.ractivitytargetlist[i].regionalactivitytargetid).text(response.ractivitytargetlist[i].regionalactivitytargetdes));
                }

            },
            error: function () {
                //console.log('Error while request..');
            }
        });
    }
    //--------------------------------------------------------------------------------------------------------
    //------------------------------------load branch activity count-----------------------------------------
    function loadBranchActivitycount() {
        var dataObject = new Object();
        dataObject.regionalactivitytargetid = $("#regionalactivitytargetid").val();
        var content = JSON.stringify(dataObject);
        //console.log(content);
        $.ajax({
            type: "POST",
            async: true,
            url: "${pageContext.servletContext.contextPath}/loadbranch/count",
            data: {tab3_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                $("#barnchtotalcount").val(response.count);
            },
            error: function () {
                //console.log('Error while request..');
            }
        });

        BranchList();
        getBranchActivityDetails();
    }

    function brachlistclick() {

        var selectedItem = $('#regionalactivitytargetid option:selected');
        var selectedItem2 = $('#tabthreebranchid option:selected');
        var regionactid = selectedItem.val();
        var regionactdes = selectedItem.text();
        var branchid = selectedItem2.val();
        var branhcdes = selectedItem2.text();

        var table5 = $('#dt_basic5').DataTable();
        var data1 = table5.row(0).data();
//                alert("data" + data1);
        $('#dt_basic5').find('tr').each(function () {
            var table5 = $('#dt_basic5').DataTable();
            var idx = table5.row(this).index();
//                    alert(idx);
            if (idx >= 0) {
                var tregionactid = table5.cell(this, 0).data();
                var tbranchid = table5.cell(this, 2).data();
                if (regionactid === tregionactid && branchid === tbranchid) {
                    $("#BAcount").val(table5.cell(this, 4).data());
                }
            }
        });

    }


    function validationdatatbel5() {
        var sum = 0;
        var selectedItem = $('#regionalactivitytargetid option:selected');
        var rgionalactivityid = selectedItem.val();
        $('#dt_basic5').find('tr').each(function () {
            var table5 = $('#dt_basic5').DataTable();
            var idx = table5.row(this).index();

            if (idx >= 0) {
                var trgionalactivityid = table5.cell(this, 0).data();
                if (rgionalactivityid === trgionalactivityid) {
                    sum += parseInt(table5.cell(this, 4).data());
                }
//                        alert("sum" + sum);
            }
        });


        jQuery.validator.addMethod("activitycount", function () {
//                    alert($("#barnchtotalcount").val());
//                    alert(sum + $("#BAcount").val());
            if ($("#barnchtotalcount").val() >= (parseInt(sum) + parseInt($("#BAcount").val()))) {
                return true;
            } else {
                return false;
            }
        }, jQuery.validator.format("Please select atleast one subsection."));

    }



    $('#addBranchActivityRow').on('click', function () {

        validationdatatbel5();
        $("#BAcount").rules('add', {
            activitycount: true,
            number: true
        });
        var $valid = $("#wizard-1").valid();
        if ($valid) {

            var count = $("#BAcount").val();

            var selectedItem = $('#regionalactivitytargetid option:selected');
            var selectedItem2 = $('#tabthreebranchid option:selected');
            var regionacid = selectedItem.val();
//                    alert("regionacid" + regionacid);
            var regionactdes = selectedItem.text();
            var branchid = selectedItem2.val();
//                    alert("branchid" + branchid);
            var branchdes = selectedItem2.text();
            var status = false;
            $('#dt_basic5').find('tr').each(function () {
//                        alert("table row cehk");
                var table5 = $('#dt_basic5').DataTable();
                var idx = table5.row(this).index();
//                        alert(idx);
                if (idx >= 0) {
                    var tregionacid = table5.cell(this, 0).data();
//                            alert("tregionacid" + tregionacid);
                    var tbranchid = table5.cell(this, 2).data();
//                            alert("tregionacid" + tregionacid);
                    if (regionacid === tregionacid && branchid === tbranchid) {
                        status = true;
//                                alert(status);
                    }
                }
            });
            if (status) {
                $('#dt_basic5').find('tr').each(function () {
                    var table5 = $('#dt_basic5').DataTable();
                    var idx = table5.row(this).index();
                    if (idx >= 0) {
                        var tregionacid = table5.cell(this, 0).data();
                        var tbranchid = table5.cell(this, 2).data();
                        if (regionacid === tregionacid) {
//                                    alert(" eqwal region");
                            if (branchid === tbranchid) {
//                                        alert(" eqwal barnch");
                                //                                          var dataObject = new Object();
                                var dataObject = new Object();
                                dataObject.targetid = $("#targetid").val();
                                dataObject.regionalactivitytargetid = $("#regionalactivitytargetid").val();
                                dataObject.branchid = $("#tabthreebranchid").val();
                                dataObject.count = $("#BAcount").val();
                                var content = JSON.stringify(dataObject);
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.servletContext.contextPath}/updatedatatabletfiverow",
                                    cache: false,
                                    async: true,
                                    data: {tab3_info: content},
                                    success: function (response) {
                                        response = JSON.parse(response);

                                        getBranchActivityDetails();
                                    },
                                    error: function () {
                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                        window.scrollTo(0, 0);
                                    }
                                });

                                getBranchActivityDetails();
                            }

                        }
                    }
                });
                //                
            } else {
                var dataObject = new Object();
                dataObject.regionalactivitytargetid = $("#regionalactivitytargetid").val();
                dataObject.branchid = $("#tabthreebranchid").val();
                dataObject.count = $("#BAcount").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/savedatatblefive",
                    cache: false,
                    async: true,
                    data: {tab3_info: content},
                    success: function (response) {
                        response = JSON.parse(response);
                        getBranchActivityDetails();
                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                        window.scrollTo(0, 0);
                    }
                });

            }
        }


    });

    function getBranchActivityDetails() {
//                alert("ok woek");
        $('#dt_basic5').dataTable().fnClearTable();
        $('#dt_basic5').dataTable().fnDestroy();
        $('#dt_basic5').dataTable({
            "bProcessing": false,
            "bServerSide": true,
            "bFilter": false,
            cache: false,
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
            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/searchbranchactivity",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: true,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/load/searchbranchactivity",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "regionalactivitytargetid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "regionalactivitytargetdes", "bSortable": false},
                {"mDataProp": "branchid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "branchdes", "bSortable": false},
                {"mDataProp": "count", "bSortable": false}
//                {"mRender": function (data, type, full) {
//                        //                                return '<a  href="${pageContext.servletContext.contextPath}/delete/recorde?branchgrouptargetid="'+full["branchgrouptargetid"]+'"&branchgroupid="'+full["branchgroupid"]+'">del</a>';
//                        return '<a  href="#" id="' + full["regionalactivitytargetid"] + '" style="' + full["branchid"] + '">del</a>';
//                    }}

            ]
        });

    }



    function  deletetable5row(id1, id2) {
//                alert("delete");
        var dataObject = new Object();
//                dataObject.regionalactivitytargetid = $("#targetid").val();
        dataObject.regionalactivitytargetid = id1;
        dataObject.branchid = id2;
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/deletetablefiverow",
            cache: false,
            async: false,
            data: {tab3_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });

        getBranchActivityDetails();
    }






    function BranchList() {
//                alert("ok");
        $('#tabthreebranchid').empty();
        $('#tabthreebranchid').append($("<option></option>").attr("value", '').text('-- Select --'));
        var dataObject = new Object();
        dataObject.regionalactivitytargetid = $("#regionalactivitytargetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.servletContext.contextPath}/getbranchlist",
            data: {tab3_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                for (var i = 0; i < response.branchlist.length; i++) {
                    $('#tabthreebranchid').append($("<option></option>").attr("value", response.branchlist[i].branchId).text(response.branchlist[i].branchDesc));
                }
            },
            error: function () {
                //console.log('Error while request..');
            }
        });
    }

    //--------------------------------------------------------------------------------------------------------
    //----------------------------------------save tab three------------------------------------------------

    function  savetabthree() {
//                alert("save");
        $('#brachtargetlist').val(JSON.stringify($('#dt_basic5').tableToJSON()));
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        dataObject.brachtargetlist = $("#brachtargetlist").val();
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/target/savetabthree", cache: false,
            data: {tabthree_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
    }


    //-----------------------------------------------------------------------------------------------------------

    //--------------------------------------Load barnch region list for tab 4---------------------------------------------

    function  loadbranchregionlist() {
//                alert("ok");
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.servletContext.contextPath}/loadbranchregionlist",
            data: {tab4_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                for (var i = 0; i < response.regionbranchlist.length; i++) {
                    $('#brnachtargetid').append($("<option></option>").attr("value", response.regionbranchlist[i].brnachtargetid).text(response.regionbranchlist[i].brnachtargetdes));
                }

            },
            error: function () {
                //console.log('Error while request..');
            }
        });
    }

    //-----------------------------------------------------------------------------------------------------------

    //----------------------------------------get target amout ------------------------------------------

    function getBranchTarget() {
//                alert("ok");
        var dataObject = new Object();
        dataObject.brnachtargetid = $("#brnachtargetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/branchtarget/amount",
            cache: false,
            async: true,
            data: {tab4_info: content},
            success: function (response) {
                response = JSON.parse(response);
                TOTAL = response.amount;
                $("#barnchamount").val(TOTAL);
            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
        getBranchGroupRevenueDetails();
    }

    //-----------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------adad row to datatbel 6  and insert- ---------------------
//    var table6 = $('#dt_basic6').DataTable({
//        "bFilter": false,
//        "sPaginationType": "full_numbers",
//        "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
//                "t" +
//                "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
//        "autoWidth": true,
//        "columnDefs": [
//            //                    {
//            //                        "targets": [0],
//            //                        "visible": false
//            //
//            //                    },
//            //                    {
//            //                        "targets": [2],
//            //                        "visible": false
//            //                    }
//        ]
//
//    });
    $('#addBrnachGroupTarget').on('click', function () {

        validationDatatbelSix();
        $("#branchgroupamount").rules('add', {
            brnachtargetid: true,
            number: true
        });
        var $valid = $("#wizard-1").valid();

        if ($valid) {

            var sum = table6.column(2).data().sum();
            var amount = $("#branchgroupamount").val();
            var total = sum + amount;
            var selectedItem = $('#brnachtargetid option:selected');
            var selectedItem2 = $('#branchgroupid option:selected');
            var id = selectedItem.val();
            var brnachregion = selectedItem.text();
            var groupdes = selectedItem2.text();
            var groupid = selectedItem2.val();
            var status = false;
            $('#dt_basic6').find('tr').each(function () {
//                        alert("table row cehk");
                var table6 = $('#dt_basic6').DataTable();
                var idx = table6.row(this).index();
//                        alert(idx);
                if (idx >= 0) {

                    var tid = table6.cell(this, 0).data();
                    var tgroupid = table6.cell(this, 2).data();
                    if (id === tid && groupid === tgroupid) {
                        status = true;
//                                alert(status);
                    }
                }
            });
            if (status) {
                $('#dt_basic6').find('tr').each(function () {
                    var table6 = $('#dt_basic6').DataTable();
                    var idx = table6.row(this).index();
                    if (idx >= 0) {
                        var tid = table6.cell(this, 0).data();
                        var tgroupid = table6.cell(this, 2).data();
                        if (id === tid) {
//                                    alert(" eqwal region");
                            if (groupid === tgroupid) {
//                                        alert(" eqwal barnch");
                                var dataObject = new Object();
                                dataObject.branchgroupid = $("#branchgroupid").val();
                                dataObject.brnachtargetid = $("#brnachtargetid").val();
                                dataObject.revenue = $("#branchgroupamount").val();
                                var content = JSON.stringify(dataObject);
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.servletContext.contextPath}/updatebarnchgrouprevenetdata",
                                    cache: false,
                                    async: true,
                                    data: {tab4_info: content},
                                    success: function (response) {
                                        response = JSON.parse(response);
                                        getBranchGroupRevenueDetails();
                                    },
                                    error: function () {
                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                        window.scrollTo(0, 0);
                                    }
                                });
                            }

                        }
                    }
                });

            } else {
                var dataObject = new Object();
                dataObject.branchgroupid = $("#branchgroupid").val();
                dataObject.brnachtargetid = $("#brnachtargetid").val();
                dataObject.revenue = $("#branchgroupamount").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/savebarnchgrouprevenetdata",
                    cache: false,
                    async: true,
                    data: {tab4_info: content},
                    success: function (response) {
                        response = JSON.parse(response);

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                        window.scrollTo(0, 0);
                    }
                });
                getBranchGroupRevenueDetails();

                loadRegionBranchGroupDropdownList();
            }
        }
    });
    //---------------------------------------------------------------------------------------
    //----------------------------table 6 DATA RETRIVE-------------------------------------------
    function getBranchGroupRevenueDetails() {
//                alert("ok woek");
        //                $('#dt_basic6').dataTable().fnClearTable();
        $('#dt_basic6').dataTable().fnDestroy();
        $('#dt_basic6').dataTable({
            "bProcessing": false,
            "bServerSide": true,
            "bFilter": false,
            cache: false,
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
            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/searchbranchgrouprevenue",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                aoData.push({"name": "brnachtargetid", "value": $('#brnachtargetid').val()});
                aoData.push({"name": "branchgroupid", "value": $('#branchgroupid').val()});

                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: true,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/load/searchbranchgrouprevenue",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "brnachtargetid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "brnachtargetdes", "bSortable": false},
                {"mDataProp": "branchgroupid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "branchgroupdes", "bSortable": false},
                {"mDataProp": "revenue", "bSortable": false}
//                {"mRender": function (data, type, full) {
//                        //                                return '<a  href="${pageContext.servletContext.contextPath}/delete/recorde?branchgrouptargetid="'+full["branchgrouptargetid"]+'"&branchgroupid="'+full["branchgroupid"]+'">del</a>';
//                        return '<a  href="#" id="' + full["brnachtargetid"] + '" style="' + full["branchgroupid"] + '">del</a>';
//                    }}

            ]
        });

        loadRegionBranchGroupDropdownList();
        getRegionBranchActivityGroupDropdown();
    }
    //---------------------------------------------------------------------------------------
    //----------------------------table row remove-------------------------------------------
    $('#dt_basic6').on('click', 'tr a', function (e) {
        e.preventDefault();
        var id = $(this).attr('id');
        var id2 = $(this).attr('style');
//                alert(id);
//                alert(id2);
        //                $(this).parents('tr').remove();
        deletetable2row(id, id2);
        getBranchGroupRevenueDetails();
    });

    function  deletetable2row(id1, id2) {
//                alert("delete");
        var dataObject = new Object();
        dataObject.brnachtargetid = id1;
        dataObject.branchgroupid = id2;
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/deletetabelsixrow",
            cache: false,
            data: {tab4_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
    }

    //-----------------------------------------------------------------------------------------
    //
    function validationDatatbelSix() {
        var sum = 0;
        var selectedItem = $('#brnachtargetid option:selected');
        var brnachtargetid = selectedItem.val();
        $('#dt_basic6').find('tr').each(function () {
            var table6 = $('#dt_basic6').DataTable();
            var idx = table6.row(this).index();

            if (idx >= 0) {
                var tbrnachtargetid = table6.cell(this, 0).data();
                if (brnachtargetid === tbrnachtargetid) {
                    sum += parseInt(table6.cell(this, 4).data());
                }
//                        alert("sum" + sum);
            }
        });


        jQuery.validator.addMethod("brnachtargetid", function () {
            if (parseInt($("#barnchamount").val()) >= parseInt(sum) + parseInt($("#branchgroupamount").val())) {
                return true;
            } else {
                return false;
            }
        }, jQuery.validator.format("Please select atleast one subsection."));

    }
    //--------------------------load region barncg group dropdwon----------------------------
    function loadRegionBranchGroupDropdownList() {
//                alert("work");
        //  
        //                              $('#regionaltargetid').append($("<option></option>").attr("value", '').text('-- Select --'));
        $('#branchgrouprevenuetargetid').empty();
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.servletContext.contextPath}/loadbranchregiongroup",
            data: {tab4_info: content},
            cache: false, success: function (response) {
                response = JSON.parse(response);
                //console.log(response);
                for (var i = 0; i < response.regionbranchgrouplist.length; i++) {
                    $('#branchgrouprevenuetargetid').append($("<option></option>").attr("value", response.regionbranchgrouplist[i].branchgrouprevenuetargetid).text(response.regionbranchgrouplist[i].branchgrouprevenuetargetdes));
                }

            },
            error: function () {
                //console.log('Error while request..');
            }
        });
        RegionDropDownByTargetId();
    }

    //----------------------------------------------------------------------------------------
    //---------------------------------load barnacg revene group amount---------------------
    function getBranchGroupRevenueTarget() {

        var dataObject = new Object();
        dataObject.branchgrouprevenuetargetid = $("#branchgrouprevenuetargetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/branchgrouprevenuamount",
            cache: false,
            async: true,
            data: {tab4_info: content},
            success: function (response) {
                response = JSON.parse(response);
                TOTAL = response.amount;
                $("#totalbranchgroprevene").val(TOTAL);
                for (var i = 0; i < response.emplist.length; i++) {
                    $('#branchgroupmembertargetid').append($("<option></option>").attr("value", response.emplist[i].empid).text(response.emplist[i].empname));
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
        getBranchGroupMemberDetails();
        getRegionBranchActivityGroupDropdown();
    }


    //----------------------------------------------------------------------------------------
    function membertargetonchange() {
        $('#dt_basic7').find('tr').each(function () {
            var selectedItem = $('#branchgroupmembertargetid option:selected');
            var branchgroupmembertargetid = selectedItem.val();
//                    alert("table row cehk");
            var table7 = $('#dt_basic7').DataTable();
            var idx = table7.row(this).index();
//                    alert(id/x);
            if (idx >= 0) {

                var tbranchgroupmembertargetid = table7.cell(this, 2).data();
                if (branchgroupmembertargetid === tbranchgroupmembertargetid) {
                    $("#memberrevenue").val(table7.cell(this, 4).data());
//                            alert(status);
                }
            }
        });

    }
    //


    function validationDatatbelSeven() {
        var sum = 0;
        var selectedItem = $('#branchgrouprevenuetargetid option:selected');
        var branchgrouprevenuetargetid = selectedItem.text();
        $('#dt_basic7').find('tr').each(function () {
            var table7 = $('#dt_basic7').DataTable();
            var idx = table7.row(this).index();

            if (idx >= 0) {
                var tbranchgrouprevenuetargetid = table7.cell(this, 1).data();
                if (branchgrouprevenuetargetid === tbranchgrouprevenuetargetid) {
                    sum += parseInt(table7.cell(this, 4).data());
                }
//                        alert("sum" + sum);
            }
        });


        jQuery.validator.addMethod("tbranchgrouprevenuetargetid", function () {
            if (parseInt($("#totalbranchgroprevene").val()) >= parseInt(sum) + parseInt($("#memberrevenue").val())) {
                return true;
            } else {
                return false;
            }
        }, jQuery.validator.format("Please select atleast one subsection."));

    }



    //--------------------------------add branch memeber revebr table row-------------------------------------
    //
    $('#dt_basic7').dataTable().fnDestroy();
    var table7 = $('#dt_basic7').DataTable({
        "bFilter": false,
        "sPaginationType": "full_numbers",
        "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
                "t" +
                "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
        "autoWidth": true,
        "columnDefs": [
            //                    {
            //                        "targets": [0],
            //                        "visible": false
            //
            //                    },
            //                    {
            //                        "targets": [2],
            //                        "visible": false
            //                    }
        ]

    });

    $('#addbranchmemberrevenue').on('click', function () {
        validationDatatbelSeven();
        $("#memberrevenue").rules('add', {
            tbranchgrouprevenuetargetid: true,
            number: true
        });
        var $valid = $("#wizard-1").valid();
        if ($valid) {
            var sum = table7.column(2).data().sum();
            var amount = $("#branchgroupamount").val();
            var total = sum + amount;
            var selectedItem = $('#branchgrouprevenuetargetid option:selected');
            var selectedItem2 = $('#empid option:selected');
            var rbgid = selectedItem.val();
            var rbgtext = selectedItem.text();
            var empname = selectedItem2.text();
            var empid = selectedItem2.val();
            var status = false;
            $('#dt_basic7').find('tr').each(function () {
//                        alert("table row cehk");
                var table7 = $('#dt_basic7').DataTable();
                var idx = table7.row(this).index();
//                        alert(idx);
                if (idx >= 0) {
                    var trbgid = table7.cell(this, 0).data();
                    var tempid = table7.cell(this, 2).data();
                    if (rbgid === trbgid && empid === tempid) {
                        status = true;
//                                alert(status);
                    }
                }
            });
            if (status) {
                $('#dt_basic7').find('tr').each(function () {
                    var table7 = $('#dt_basic7').DataTable();
                    var idx = table7.row(this).index();
                    if (idx >= 0) {
                        var trgbid = table7.cell(this, 0).data();
                        var tempid = table7.cell(this, 2).data();
                        if (rbgid === trgbid) {
//                                    alert(" eqwal region");
                            if (empid === tempid) {
//                                        alert(" eqwal barnch");
                                var dataObject = new Object();
                                dataObject.branchgrouprevenuetargetid = $("#branchgrouprevenuetargetid").val();
                                dataObject.branchgroupmembertargetid = $("#branchgroupmembertargetid").val();
                                dataObject.revenue = $("#memberrevenue").val();
                                var content = JSON.stringify(dataObject);
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.servletContext.contextPath}/updatebarnchgroupmemberdata",
                                    cache: false,
                                    async: true,
                                    data: {tab4_info: content},
                                    success: function (response) {
                                        response = JSON.parse(response);
                                        getBranchGroupMemberDetails();
                                    },
                                    error: function () {
                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                        window.scrollTo(0, 0);
                                    }
                                });
                            }

                        }
                    }
                });
                //                    getBranchGroupRevenueDetails();
            } else {
                var dataObject = new Object();
                dataObject.branchgrouprevenuetargetid = $("#branchgrouprevenuetargetid").val();
                dataObject.branchgroupmembertargetid = $("#branchgroupmembertargetid").val();
                dataObject.revenue = $("#memberrevenue").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/savebarnchgroupmemberdata",
                    cache: false,
                    async: true,
                    data: {tab4_info: content},
                    success: function (response) {
                        response = JSON.parse(response);

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                        window.scrollTo(0, 0);
                    }
                });
                getBranchGroupMemberDetails();
            }
        }
    });


    //----------------------------------------------------------------------------------------
    //-----------------------------datatabel 7 data load--------------------------------------
    function getBranchGroupMemberDetails() {
//                alert("data seacrh");
        $('#dt_basic7').dataTable().fnClearTable();
        $('#dt_basic7').dataTable().fnDestroy();
        $('#dt_basic7').dataTable({
            "bProcessing": false,
            "bServerSide": true,
            "bFilter": false,
            cache: false,
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
            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/searchbranchgroupmemberdetail",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: true,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/load/searchbranchgroupmemberdetail",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "branchgroupmembertargetid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "branchgroupmembertargetdes", "bSortable": false},
                {"mDataProp": "branchgroupid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "empname", "bSortable": false},
                {"mDataProp": "revenue", "bSortable": false}
//                {"mRender": function (data, type, full) {
//                        //                                return '<a  href="${pageContext.servletContext.contextPath}/delete/recorde?branchgrouptargetid="'+full["branchgrouptargetid"]+'"&branchgroupid="'+full["branchgroupid"]+'">del</a>';
//                        return '<a  href="#" id="' + full["branchgroupmembertargetid"] + '" style="' + full["branchgroupid"] + '">del</a>';
//                    }}

            ]
        });


    }
    //----------------------------------------------------------------------------------------------

    //----------------------------table7 row remove-------------------------------------------
    $('#dt_basic7').on('click', 'tr a', function (e) {
//                alert("tabel2");
        e.preventDefault();
        var id = $(this).attr('id');
        var id2 = $(this).attr('style');
//                alert(id);
//                alert(id2);
        //                $(this).parents('tr').remove();
        deletetable7row(id, id2);
        getBranchGroupMemberDetails();
    });

    function  deletetable7row(id1, id2) {
//        alert("delete");
        var dataObject = new Object();
        dataObject.branchgroupmembertargetid = id1;
        dataObject.branchgroupid = id2;
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/deletetablerow",
            cache: false,
            data: {tab4_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
    }

    //-----------------------------------------------------------------------------------------
    //---------------------tab5 branch grop activiity----------------------------------

    function getRegionBranchActivity() {

        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/regionbranchactivity",
            cache: false,
            async: true,
            data: {tab5_info: content},
            success: function (response) {
                response = JSON.parse(response);
                for (var i = 0; i < response.regionbrachactivity.length; i++) {
                    $('#branchactivitytargetid').append($("<option></option>").attr("value", response.regionbrachactivity[i].branchactivitytargetid).text(response.regionbrachactivity[i].branchactivitytargetdes));
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
        getBranchGroupMemberDetails();
    }




    //-----------------------------------------------------------------------------------------
    //----------------------------------getbarnchactivity count-----------------------------------

    function getBranchActivityCount() {

        var dataObject = new Object();
        dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();

        var content = JSON.stringify(dataObject);
        //console.log(content);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/branchactivitycount",
            cache: false,
            async: true,
            data: {tab5_info: content},
            success: function (response) {
                response = JSON.parse(response);
                //console.log(response.count);
                $("#branchactivitycount").val(response.count);

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
        getBranchGroupActivityDetails();
    }



    //--------------------------------add  table8 row add------------------------------------
    //
//    var table8 = $('#dt_basic8').DataTable({
//        "bFilter": false,
//        "sPaginationType": "full_numbers",
//        "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
//                "t" +
//                "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
//        "autoWidth": true,
//        "columnDefs": [
//            //                    {
//            //                        "targets": [0],
//            //                        "visible": false
//            //
//            //                    },
//            //                    {
//            //                        "targets": [2],
//            //                        "visible": false
//            //                    }
//        ]
//
//    });
    $('#addBrnachGroupActivityTarget').on('click', function () {
        validationDatatbelEight();
        $("#branchgrouptivitycount").rules('add', {
//                    regionalid: true,
            number: true
        });
        var $valid = $("#wizard-1").valid();

        if ($valid) {
            var sum = table8.column(2).data().sum();
            var amount = $("#branchgroupamount").val();
            var total = sum + amount;
            var selectedItem = $('#branchactivitytargetid option:selected');
            var selectedItem2 = $('#tabfivebranchgrouptargetid option:selected');

            var branchactivityid = selectedItem.val();
            var branchactivitydes = selectedItem.text();
            var groupid = selectedItem2.val();
            var groupdes = selectedItem2.text();

            var status = false;
            $('#dt_basic8').find('tr').each(function () {
//                        alert("table row cehk");
                var table8 = $('#dt_basic8').DataTable();
                var idx = table8.row(this).index();
//                        alert(idx);
                if (idx >= 0) {
                    var tbranchactivityid = table8.cell(this, 0).data();
                    var tgroupid = table8.cell(this, 2).data();

                    if (branchactivityid === tbranchactivityid && groupid === tgroupid) {
                        status = true;
//                                alert(status);
                    }
                }
            });
            if (status) {
                $('#dt_basic8').find('tr').each(function () {
                    var table8 = $('#dt_basic8').DataTable();
                    var idx = table8.row(this).index();
                    if (idx >= 0) {
                        var tbranchactivityid = table8.cell(this, 0).data();
                        var tgroupid = table8.cell(this, 2).data();
                        if (branchactivityid === tbranchactivityid) {
                            if (groupid === tgroupid) {
                                var dataObject = new Object();
                                dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();
                                dataObject.count = $("#branchgrouptivitycount").val();
                                dataObject.branchgroupid = $("#tabfivebranchgrouptargetid").val();
                                var content = JSON.stringify(dataObject);
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.servletContext.contextPath}/updatebrangroupactivity",
                                    cache: false,
                                    async: true,
                                    data: {tab5_info: content},
                                    success: function (response) {
                                        response = JSON.parse(response);
                                        getBranchGroupActivityDetails();
                                    },
                                    error: function () {
                                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                        window.scrollTo(0, 0);
                                    }
                                });
                            }



                        }
                    }
                });
                //                    getBranchGroupRevenueDetails();
            } else {
                var dataObject = new Object();
                dataObject.branchactivitytargetid = $("#branchactivitytargetid").val();
                dataObject.count = $("#branchgrouptivitycount").val();
                dataObject.branchgroupid = $("#tabfivebranchgrouptargetid").val();
                var content = JSON.stringify(dataObject);
                $.ajax({
                    type: "post",
                    url: "${pageContext.servletContext.contextPath}/savebrangroupactivity",
                    cache: false,
                    async: true,
                    data: {tab5_info: content},
                    success: function (response) {
                        response = JSON.parse(response);

                    },
                    error: function () {
                        $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                        window.scrollTo(0, 0);
                    }
                });
                getBranchGroupActivityDetails();
            }
        }
    });


    //----------------------------------------------------------------------------------------



    function branchgroupclick() {
        $('#dt_basic8').find('tr').each(function () {
            var selectedItem = $('#branchactivitytargetid option:selected');
            var branchactivitytargetid = selectedItem.text();
//                    alert("table row cehk");
            var table8 = $('#dt_basic8').DataTable();
//                    alert("branchactivitytargetid" + branchactivitytargetid);
            var idx = table8.row(this).index();
//                    alert(idx);
            if (idx >= 0) {

                var tbranchactivitytargetid = table8.cell(this, 1).data();
                if (branchactivitytargetid === tbranchactivitytargetid) {
                    $("#branchgrouptivitycount").val(table8.cell(this, 4).data());
                    alert(status);
                }
            }
        });


    }

    function validationDatatbelEight() {
        var sum = 0;
        var selectedItem = $('#regionalid option:selected');
        var branchactivitytargetid = selectedItem.text();
        $('#dt_basic8').find('tr').each(function () {
            var table8 = $('#dt_basic8').DataTable();
            var idx = table8.row(this).index();

            if (idx >= 0) {
                var tbranchactivitytargetid = table8.cell(this, 1).data();
                if (branchactivitytargetid === tbranchactivitytargetid) {
                    sum += parseInt(table8.cell(this, 4).data());
                }
//                        alert("sum" + sum);
            }
        });


        jQuery.validator.addMethod("tbranchgrouprevenuetargetid", function () {
            if (parseInt($("#branchactivitycount").val()) >= parseInt(sum) + parseInt($("#branchgrouptivitycount").val())) {
                return true;
            } else {
                return false;
            }
        }, jQuery.validator.format("Please select atleast one subsection."));

    }

    //-----------------------------datatabel 8 data load--------------------------------------
    function getBranchGroupActivityDetails() {
//                alert("data seacrh");
        $('#dt_basic8').dataTable().fnClearTable();
        $('#dt_basic8').dataTable().fnDestroy();
        $('#dt_basic8').dataTable({
            "bProcessing": false,
            "bServerSide": true,
            "bFilter": false,
            cache: false,
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
            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/searchbranchgroupactivitydetail",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                aoData.push({"name": "branchgroupid", "value": $('#tabfivebranchgrouptargetid').val()});
                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: true,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/load/searchbranchgroupactivitydetail",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "branchactivitytargetid", "bSortable": false,"sClass": "columnhide"},
                {"mDataProp": "branchactivitytargetdes", "bSortable": false},
                {"mDataProp": "branchgroupid", "bSortable": false,"sClass": "columnhide"},
                {"mDataProp": "branchgroupdes", "bSortable": false},
                {"mDataProp": "count", "bSortable": false}
//                {"mRender": function (data, type, full) {
//                        //                                return '<a  href="${pageContext.servletContext.contextPath}/delete/recorde?branchgrouptargetid="'+full["branchgrouptargetid"]+'"&branchgroupid="'+full["branchgroupid"]+'">del</a>';
//                        return '<a  href="#" id="' + full["branchactivitytargetid"] + '" style="' + full["branchgroupid"] + '">del</a>';
//                    }}

            ]
        });

        getRegionBranchActivityGroupDropdown();
    }
    //----------------------------------------------------------------------------------------------

    //----------------------------table8 row remove-------------------------------------------
    $('#dt_basic8').on('click', 'tr a', function (e) {
//                alert("tabel8");
        e.preventDefault();
        var id = $(this).attr('id');
        var id2 = $(this).attr('style');
//                alert(id);
//                alert(id2);
        //                $(this).parents('tr').remove();
        deletetable8row(id, id2);
        getBranchGroupActivityDetails();
    });

    function  deletetable8row(id1, id2) {
//                alert("delete");
        var dataObject = new Object();
        dataObject.branchactivitytargetid = id1;
        dataObject.branchgroupid = id2;
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/deletetableeoghtrow",
            cache: false,
            data: {tab5_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    getBranchGroupActivityDetails();
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
    }

    //-----------------------------------------------------------------------------------------

    //---------------------tab5 branch grop activiity TARGET DROP DOWN----------------------------------

    function getRegionBranchActivityGroupDropdown() {

        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/regionbranchactivitygroup",
            cache: false,
            async: true,
            data: {tab5_info: content},
            success: function (response) {
                response = JSON.parse(response);
                for (var i = 0; i < response.regionbranchgroupactivity.length; i++) {
                    $('#branchgroupactivitytargeid').append($("<option></option>").attr("value", response.regionbranchgroupactivity[i].branchgroupactivitytargeid).text(response.regionbranchgroupactivity[i].branchgroupactivitytargetdes));
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
        getBranchGroupMemberDetails();
    }




    //-----------------------------------------------------------------------------------------
    function getBranchGroupMember2() {
        var dataObject = new Object();
        dataObject.targetid = $("#targetid").val();
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/getbrnachgroupmember",
            cache: false,
            async: true,
            data: {tab5_info: content},
            success: function (response) {
                response = JSON.parse(response);
                for (var i = 0; i < response.branchgroupmember.length; i++) {
                    $('#branchgroupmemberid').append($("<option></option>").attr("value", response.branchgroupmember[i].empid).text(response.branchgroupmember[i].empname));
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
        //                getBranchGroupMemberDetails();
    }
    //_------------------------------------------------------------------------------------------------

    //--------------------------------add  table8 row add------------------------------------
    //
//    var table9 = $('#dt_basic9').DataTable({
//        "bFilter": false,
//        "sPaginationType": "full_numbers",
//        "sDom": "<'dt-toolbar'<'col-sm-4 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-6'r>>" +
//                "t" +
//                "<'dt-toolbar-footer'<'col-sm-4 col-xs-6 hidden-xs'l><'col-xs-12 col-sm-8'p>>",
//        "autoWidth": true,
//        "columnDefs": [
//            //                    {
//            //                        "targets": [0],
//            //                        "visible": false
//            //
//            //                    },
//            //                    {
//            //                        "targets": [2],
//            //                        "visible": false
//            //                    }
//        ]
//
//    });
    $('#addbranchgroupmemberactivity').on('click', function () {







        var sum = table8.column(2).data().sum();
        var amount = $("#branchgroupamount").val();
        var total = sum + amount;
        var selectedItem = $('#branchgroupactivitytargeid option:selected');
        var selectedItem2 = $('#branchgroupmemberid option:selected');

        var bgaid = selectedItem.val();
        var bgades = selectedItem.text();
        var bgmid = selectedItem2.val();
        var bgmdes = selectedItem2.text();

        var status = false;
        $('#dt_basic9').find('tr').each(function () {
//                    alert("table row cehk");
            var table9 = $('#dt_basic9').DataTable();
            var idx = table9.row(this).index();
//                    alert(idx);
            if (idx >= 0) {
                var tbgaid = table9.cell(this, 0).data();
                var tbgmid = table8.cell(this, 2).data();

                if (bgaid === tbgaid && bgmid === tbgmid) {
                    status = true;
//                            alert(status);
                }
            }
        });
        if (status) {
            $('#dt_basic9').find('tr').each(function () {
                var table8 = $('#dt_basic9').DataTable();
                var idx = table8.row(this).index();
                if (idx >= 0) {
                    var tbgaid = table9.cell(this, 0).data();
                    var tbgmid = table9.cell(this, 2).data();
                    if (bgaid === tbgaid) {
                        if (bgmid === tbgmid) {
                            var dataObject = new Object();
                            dataObject.branchgroupactivitytargeid = $("#branchgroupactivitytargeid").val();
                            dataObject.branchgroupmembertargetid = $("#branchgroupmemberid").val();
                            dataObject.count = $("#branchgroupmembercount").val();
                            var content = JSON.stringify(dataObject);
                            $.ajax({
                                type: "post",
                                url: "${pageContext.servletContext.contextPath}/savebrangroupmemberactivity",
                                cache: false,
                                async: true,
                                data: {tab5_info: content},
                                success: function (response) {
                                    response = JSON.parse(response);

                                },
                                error: function () {
                                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                                    window.scrollTo(0, 0);
                                }
                            });
                            getBranchGroupActivityDetails();
                        }



                    }
                }
            });
            //                    getBranchGroupRevenueDetails();
        } else {
            var dataObject = new Object();
            dataObject.branchgroupactivitytargeid = $("#branchgroupactivitytargeid").val();
            dataObject.branchgroupmembertargetid = $("#branchgroupmemberid").val();
            dataObject.count = $("#branchgroupmembercount").val();
            var content = JSON.stringify(dataObject);
            $.ajax({
                type: "post",
                url: "${pageContext.servletContext.contextPath}/savebrangroupmemberactivity",
                cache: false,
                async: true,
                data: {tab5_info: content},
                success: function (response) {
                    response = JSON.parse(response);

                },
                error: function () {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                    window.scrollTo(0, 0);
                }
            });
            getBranchGroupActivityDetails();
        }

    });


    function getBranchGroupActivityMemberDetails() {
//                alert("data seacrh");
        $('#dt_basic9').dataTable().fnClearTable();
        $('#dt_basic9').dataTable().fnDestroy();
        $('#dt_basic9').dataTable({
            "bProcessing": false,
            "bServerSide": true,
            "bFilter": false,
            cache: false,
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
            "sAjaxSource": "${pageContext.servletContext.contextPath}/load/searchbranchgroupactivitymemberdetail",
            "fnServerData": function (sSource, aoData, fnCallback) {
                aoData.push({"name": "targetid", "value": $('#targetid').val()});
                //console.log(aoData);
                $.ajax({
                    "dataType": 'json',
                    async: true,
                    "type": "POST",
                    "url": "${pageContext.servletContext.contextPath}/load/searchbranchgroupactivitymemberdetail",
                    "data": aoData,
                    "success": fnCallback

                });
            },
            //                "order": [[0, "asc"]],
            "aoColumns": [
                {"mDataProp": "branchgroumemberactivityid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "branchgroumemberactivitydes", "bSortable": false},
                {"mDataProp": "branchgroupmemberid", "bSortable": false, "sClass": "columnhide"},
                {"mDataProp": "branchgroupmemberdes", "bSortable": false},
                {"mDataProp": "count", "bSortable": false}
//                {"mRender": function (data, type, full) {
//                        //                                return '<a  href="${pageContext.servletContext.contextPath}/delete/recorde?branchgrouptargetid="'+full["branchgrouptargetid"]+'"&branchgroupid="'+full["branchgroupid"]+'">del</a>';
//                        return '<a  href="#" id="' + full["branchgroumemberactivityid"] + '" style="' + full["branchgroupmembertargetid"] + '">del</a>';
//                    }}

            ]
        });

    }


    $('#dt_basic9').on('click', 'tr a', function (e) {
//                alert("tabel9");
        e.preventDefault();
        var id = $(this).attr('id');
        var id2 = $(this).attr('style');
//                alert(id);
//                alert(id2);
        //                $(this).parents('tr').remove();
        deletetable9row(id, id2);
        getBranchGroupActivityMemberDetails();
    });

    function  deletetable9row(id1, id2) {
//                alert("delete");
        var dataObject = new Object();
        dataObject.branchgroumemberactivityid = id1;
        dataObject.branchgroupmemberid = id2;
        //console.log(dataObject);
        var content = JSON.stringify(dataObject);
        $.ajax({
            type: "post",
            url: "${pageContext.servletContext.contextPath}/deletetableninerow",
            cache: false,
            data: {tab5_info: content},
            success: function (response) {
                response = JSON.parse(response);
                if (response.CODE === "SUCCESS") {
                    getBranchGroupActivityDetails();
                    $('#msg_dev').html('<div class="alert alert-success"><strong>Success!</strong> Record Sucessfully Created</div> <br/>');
                } else {
                    $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> ' + response.MESSAGE + '</div> <br/>');
                    window.scrollTo(0, 0);
                }

            },
            error: function () {
                $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                window.scrollTo(0, 0);
            }
        });
    }



    //----------------------------------------------------------------------------------------
    $('#wizard-1').validate({
        onkeyup: function (element) {
            $(element).valid();
        },
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            productid: {
                required: true
            },
            targergroupid: {
                required: true
            }, nuberofcontact: {
                required: true,
                number: true
            },
            targetperiodid: {
                required: true,
                number: true
            },
            tragetdes: {
                required: true
            },
            targetstartdate: {
                required: true
            },
            targetenddate: {
                required: true
            },
            regiontarget: {
                required: true
            },
            //                    totalvalue: {
            //                        equalTo: "#regiontarget"
            //                    },
            revenue: {
                required: true,
                number: true
            },
            oneregiontarget: {
                required: true
            },
            totalregion: {
                equalTo: "#oneregiontarget"
            },
            multiregionalvalidation: {
                required: true
            },
            targetid: {
                required: true
            },
            targettypeid: {
                required: true
            },
            //                    regionalid:{
            //                          required: true
            //                    }
            //                    targetactivityid:{
            //                         required: true
            //                    }


            //                    activitytypeid: {
            //                        required: true
            //                    }

        }, messages: {
            totalvalue: {
                equalTo: "Total Value must equal to organization target"
            },
            totalregion: {
                equalTo: "Total Value must equal to branch target"
            }
        }
        //                errorPlacement: function (error, element) {
        ////                    error.insertAfter(element.parent());
        ////                    error.appendTo($('#errorContainer'));
        //                }
    });

</script>




