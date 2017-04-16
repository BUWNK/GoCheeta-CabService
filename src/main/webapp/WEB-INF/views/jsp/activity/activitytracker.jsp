<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <aside id="left-panel">
            <jsp:include page="../template/menu.jsp"/>
        </aside>

        <div id="main" role="main">
            <div id="ribbon">
                <div class="row">
                    <div class="col-xs-6">
                        <ol class="breadcrumb">
                            <li>Sales Pipeline</li><li>Activity History</li>
                        </ol>
                    </div>

                    <div class="col-xs-6">
                        <p style="color: #ffffff; padding-top: 11px" class="text-right"></p>
                    </div>
                </div>
            </div>
            <div id="content" style="margin-right: 2%">
                <br>
                <br>
                <div class="row row-margin-top">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-3">
                                <h1> <span id="card-title"></span><span id="card-name"></span></h1>
                                <!--<h5><span id="card-jobtitle"></span></h5>-->
                            </div>
                            <div class="col-xs-3" style="margin-top: 17px">
                                <label for="sel1" style="float: left">Contact</label>
                            </div>
                            <div class="col-xs-3" style="margin-top: 17px">
                                <label for="sel1" style="float: left">Lead</label>
                            </div>
                            <div class="view-calender col-xs-3" align="right">
                                <div  align="right" id="calender_icon_name" style="display: inline-block">View Calendar</div>
                                <div  id="calender_icon" style="display: inline-block">
                                    <form:form id="calenderForm" commandName="calenderForm" action="${pageContext.request.contextPath}/calender/view" method="POST" >
                                        <form:input path="leadid" type="hidden" id="leadid" value="${leadid}"></form:input>
                                        <form:input path="createUser" type="hidden" id="createUser" value="${createUser}"></form:input>
                                        <form:button id="createbutton" type="submit" class="btn-link  view-more-link" style="border:none;">
                                            <span> <i class="fa fa-calendar fa-2x"></i></span>
                                            </form:button>
                                        </form:form>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-3 col-delete-padding tracker-head" align="left">

                            </div>  
                            <div class=" col-xs-3 form-group" id="leaddropdown-div">
                                <select class="form-control" id="selectcontact" onchange="SelectContactFunction()">
                                    <c:forEach var="contactDroplist" items="${contactDroplist}">
                                        <option value="${contactDroplist.contactid}">${contactDroplist.nameinFull}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class=" col-xs-3 form-group" id="leaddropdown-div">
                                <select class="form-control" id="selectlead" onchange="SelectleadFunction()">
                                    <c:forEach var="leaddropList" items="${leaddropList}">
                                        <option value="${leaddropList.leadid}">${leaddropList.description} | LKR <fmt:formatNumber type="number" maxFractionDigits="3" value="${leaddropList.leadamount}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-xs-3" style="padding-right: 0px">
                                <button id="scheduleActivity" type="button"  class="btn  btn-block add-button"><span class="icon-left"><i class="fa fa-plus"></i></span>Schedule Activity</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!--all previous activities-->
                <div class="row row-margin-top">
                    <div class="col-xs-3 col-delete-padding tracker-head" id="parent-collapse-basic">
                        <div class="panel panel-default contact-header">
                            <div class="panel-heading panel-heading-contact" >Activity History</div>
                            <div id="activityHistory">
                                <c:forEach var = "activitylist" items = "${activitylist}">
                                    <div class="panel-heading" style="padding: 4px">
                                        <div class="panel-heading" style="padding:4px;background-color: #ddd; border-radius: 5px">
                                            <div class="panel-heading-type">
                                                <p id="type-description" align="center">${activitylist.activitytypedescription}</p>
                                            </div>
                                            <div id="activity-description-${activitylist.activityid}" class="collapse" style="padding: 4px" align="center">
                                                <div class="row">
                                                    <div class="col-xs-5 activity-history-label">Description</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.description}</div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-5 activity-history-label">Activity Date</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.activitytime}</div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-5 activity-history-label">Call Duration</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.spenttime}</div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-5 activity-history-label">Initiated By</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.createduser}</div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="view-more-cart" style="padding:4px">
                                        <a  class="view-more-link" style="text-decoration: none;" id="activity-description-${activitylist.activityid}" data-target="#activity-description-${activitylist.activityid}" data-toggle="collapse"><span id="view-more-less-more1-${activitylist.activityid}"><i class="fa fa-plus"></i>  View More </span></a>
                                    </div>
                                    <br>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <!--all previous call activities-->
                    <div class="col-xs-3 col-delete-padding" id="parent-collapse" >
                        <div class="panel panel-default contact-header">
                            <div class="panel-heading panel-heading-lead" >Calls</div>
                        </div>
                        <div class="col-delete-padding" id="parent-collapse-basic">
                            <div class="panel-heading" style="padding: 0px">
                                <div class="panel-heading" style="padding:5px;background-color: lightgray; border-radius: 5px;margin-top: 10px" >
                                    Time : <span id="callTime"><c:out value="${time1}"/></span> 
                                </div>
                            </div>
                        </div>

                        <div id="call_panel">
                            <c:forEach var = "activitylist" items = "${activitylist}">
                                <c:if test="${activitylist.activitytypeid == 1}">
                                    <div id="activity-more">
                                        <div>
                                            <div id="lead-collapse-${activitylist.activityid}" data-toggle = "collapse" class="panel-collapse collapse activityinfo" align="left">
                                                <p style="margin-left: 11px;">${activitylist.description}</p>
                                                <div class="row activity-calls">
                                                    <div class="col-xs-5 activity-history-label">Call Duration</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.spenttime}</div>
                                                </div>
                                                <div class="row activity-calls">
                                                    <div class="col-xs-5 activity-history-label">Initiated By</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.createduser}</div>
                                                </div>
                                                <br/>
                                            </div>
                                        </div>
                                        <button class="view-more-link btn  btn-block add-button" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-${activitylist.activityid}" data-parent="#parent-collapse" href="#lead-collapse-${activitylist.activityid}"><span id="view-more-less-more-${activitylist.activityid}"><i class="fa fa-plus"></i> </span>&nbsp ${activitylist.activitytime}</button>
                                    </div>
                                    <div class="view-more-activity" id="update_activity">
                                        <a  class="view-more-link-activity" style="text-decoration: none;" id="${activitylist.activityid}"><span><i class="fa fa-plus"></i>  Update</span></a>
                                    </div>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>

                    <!--all previous presentation and visit activities-->
                    <div class="col-xs-3 col-delete-padding" id="parent-collapse-v">
                        <div class="panel panel-default contact-header">
                            <div class="panel-heading panel-heading-sale-closed" >Visits/ Presentations</div>
                        </div>
                        <div class="col-delete-padding" id="parent-collapse-basic">
                            <div class="panel-heading" style="padding: 0px">
                                <div class="panel-heading" style="padding:5px;background-color: lightgray; border-radius: 5px; margin-top: 10px" >
                                    Time : <span id="visitTime"><c:out value="${time2}"/></span>
                                </div>
                            </div>
                        </div> 
                        <div id="visit_panel" id="update_activity">
                            <c:forEach var = "activitylist" items = "${activitylist}">
                                <c:if test="${activitylist.activitytypeid == 2}">
                                    <div id="activity-more">
                                        <div>
                                            <div id="lead-collapse-${activitylist.activityid}" data-toggle = "collapse" class="panel-collapse collapse activityinfo" align="left">
                                                <p style="margin-left: 11px;">${activitylist.description}</p>
                                                <div class="row activity-calls">
                                                    <div class="col-xs-5 activity-history-label">Call Duration</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.spenttime}</div>
                                                </div>
                                                <div class="row activity-calls">
                                                    <div class="col-xs-5 activity-history-label">Initiated By</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.createduser}</div>
                                                </div>
                                                <br/>
                                            </div>
                                        </div>
                                        <button class="view-more-link btn  btn-block add-button" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-${activitylist.activityid}" data-parent="#parent-collapse" href="#lead-collapse-${activitylist.activityid}"><span id="view-more-less-more-${activitylist.activityid}"><i class="fa fa-plus"></i> </span>&nbsp ${activitylist.activitytime}</button>
                                    </div>
                                    <div class="view-more-activity">
                                        <a  class="view-more-link-activity" style="text-decoration: none;" id="${activitylist.activityid}"><span><i class="fa fa-plus"></i>  Update</span></a>
                                    </div>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>

                    <!--all previous proposal activities-->
                    <div class="col-xs-3 col-delete-padding" id="parent-collapse-p">
                        <div class="panel panel-default contact-header">
                            <div class="panel-heading panel-heading-sale-lost" >Proposals</div>
                        </div>
                        <div class="col-delete-padding" id="parent-collapse-basic">
                            <div class="panel-heading" style="padding: 0px">
                                <div class="panel-heading" style="padding:5px;background-color: lightgray; border-radius: 5px; margin-top: 10px" >
                                    Time : <span id="propasalTime"><c:out value="${time3}"/></span>                                  
                                </div>
                            </div>
                        </div>
                        <div id="propasal_panel">
                            <c:forEach var = "activitylist" items = "${activitylist}">
                                <c:if test="${activitylist.activitytypeid == 3}">
                                    <div id="activity-more">
                                        <div>
                                            <div id="lead-collapse-${activitylist.activityid}" data-toggle = "collapse" class="panel-collapse collapse activityinfo" align="left">
                                                <p style="margin-left: 11px;">${activitylist.description}</p>
                                                <div class="row activity-calls">
                                                    <div class="col-xs-5 activity-history-label">Call Duration</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.spenttime}</div>
                                                </div>
                                                <div class="row activity-calls">
                                                    <div class="col-xs-5 activity-history-label">Initiated By</div>
                                                    <div class="col-xs-7 activity-history-value">${activitylist.createduser}</div>
                                                </div>
                                                <br/>
                                            </div>
                                        </div>
                                        <button class="view-more-link btn  btn-block add-button" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-${activitylist.activityid}" data-parent="#parent-collapse" href="#lead-collapse-${activitylist.activityid}"><span id="view-more-less-more-${activitylist.activityid}"><i class="fa fa-plus"></i> </span>&nbsp ${activitylist.activitytime}</button>
                                    </div>
                                    <div class="view-more-activity" id="update_activity">
                                        <a  class="view-more-link-activity" style="text-decoration: none;" id="${activitylist.activityid}"><i class="fa fa-plus"></i>  Update</a>
                                    </div>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <!--                <div class="row row-margin-top">
                                    <div class="col-xs-3 col-delete-padding" id="parent-collapse-basic"></div>
                
                                    time for calls
                                    <div class="col-xs-3 col-delete-padding" id="parent-collapse-basic">
                                        <div class="panel-heading" style="padding: 4px">
                                            <div class="panel-heading" style="padding:4px;background-color: lightgray; border-radius: 5px" >
                                                Time : <span id="callTime"><c:out value="${time1}"/></span> 
                                            </div>
                                        </div>
                                    </div>
                
                                    time for presentation and visit
                                    <div class="col-xs-3 col-delete-padding" id="parent-collapse-basic">
                                        <div class="panel-heading" style="padding: 4px">
                                            <div class="panel-heading" style="padding:4px;background-color: lightgray; border-radius: 5px" >
                                                Time : <span id="visitTime"><c:out value="${time2}"/></span>
                                            </div>
                                        </div>
                                    </div>
                
                                    time for proposal
                                    <div class="col-xs-3 col-delete-padding" id="parent-collapse-basic">
                                        <div class="panel-heading" style="padding: 4px">
                                            <div class="panel-heading" style="padding:4px;background-color: lightgray; border-radius: 5px" >
                                                Time : <span id="propasalTime"><c:out value="${time3}"/></span>                                  
                                            </div>
                                        </div>
                                    </div>
                                </div>-->
            </div>
        </div>

        <!--add activity model start-->
        <div class="modal fade" id="addActivityModal" role="dialog" >
            <div class="vertical-alignment-helper">
                <div class="modal-dialog vertical-align-center">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Schedule Activity</h4>
                        </div>
                        <div id="salesActivity"></div>
                        <div class="modal-body">
                            <form role="form" id="leadCreate"></form>
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="addActivityForm">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <section>
                                                        <label >Activity Description<samp style="color: red">*</samp></label>
                                                        <input type="text" class="form-control" id="Adescription" name="Adescription" placeholder="Activity Description" maxlength="100">
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-xs-5">
                                                        <div class="form-group">
                                                            <section>
                                                                <label for="sel1">Activity Type<samp style="color: red">*</samp></label>
                                                                <select class="form-control" id="atype" name="atype">
                                                                    <option value="">--Select--</option>
                                                                    <c:forEach var="typelist" items="${typelist}">
                                                                        <option value="${typelist.activitytypeid}">${typelist.activitytype}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </section>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-2"></div>
                                                    <div class="col-xs-5">
                                                        <div class="form-group">                                                    
                                                            <label>Activity Date Time<samp style="color: red">*</samp></label>
                                                            <div class="input-group date" id="input_group_delete_validation" name="datetimepicker2">
                                                                <input id="adate" name="adate" type="text" class="form-control calender-background activity_datepickerhide" placeholder="Pick a Date" readonly="readonly"/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> 
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-default" id="addactivity">Schedule Activity</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--add activity model end-->

        <!--update activity model start-->
        <div class="modal fade" id="updateActivityModal" role="dialog" >
            <div class="vertical-alignment-helper">
                <div class="modal-dialog vertical-align-center">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">Update Activity</h4>
                        </div>
                        <div id="closed-lead-id-div"></div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="updateActivityForm">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <label >Activity Description<samp style="color: red">*</samp></label>
                                                    <input type="text" class="form-control" id="activity_description" name="activity_description" placeholder="Activity Description" maxlength="100">
                                                    <input type="hidden" class="form-control" id="edit_activity_id" name="edit_activity_id">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <label for="sel1">Activity Type<samp style="color: red">*</samp></label>
                                                    <select class="form-control" id="activity_type" name="activity_type">
                                                        <option value="">--Select--</option>
                                                        <c:forEach var="typelist" items="${typelist}">
                                                            <option value="${typelist.activitytypeid}">${typelist.activitytype}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xs-2"></div>
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <label >Spent time<samp style="color: red">*</samp></label>
                                                    <div class="row">
                                                        <div class="col-xs-3" style="padding-right: 0px">
                                                            <input type="number" id="activity_spenthour" class="form-control" style="padding-right: 0px;" min="0" onchange="SpentTimeZeroFunction()">
                                                        </div>
                                                        <div class="col-xs-2" style="margin-top: 4px;">hh</div>
                                                        <div class="col-xs-3"style="padding-left: 0px;padding-right: 14px;">
                                                            <input type="number" id="activity_spentminite" class="form-control" style="padding-right: 0px;" min="0" onchange="SpentTimeZeroFunction()">
                                                        </div>
                                                        <div class="col-xs-2" style="margin-top: 4px; padding-left: 0px;">mm</div>
                                                    </div>
                                                    <!--<input type="text" class="form-control" id="activity_spenttime" name="activity_spenttime" placeholder="Spent time" maxlength="20">-->
                                                </div>
                                                <div id="spent_time_zero" style="display: none">
                                                    <p style="color: red">Spent time cannot be 0</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <label >Activity Date<samp style="color: red">*</samp></label>
                                                    <div class="input-group date" id='activity_date_picker'>
                                                        <input id="datetimepicker_update" name="datetimepicker_update" type="text" class="form-control calender-background hidepicker" placeholder="Pick a Date" readonly="readonly" onchange="calanderRemove()"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-7"></div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-default" id="updateactivity">Update Activity</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--update activity model end-->
        <div class="page-footer">
            <jsp:include page="../template/footer.jsp"/>
        </div>
        <jsp:include page="../template/jsinclide.jsp"/>

        <script>
                                    function calanderRemove() {
                                        $('.datetimepicker').hide();
                                    }
                                    //         append the lead id details(lead name,job title,...)       
                                    $(document).ready(function() {
                                        $('#selectlead option[value="' + ${leadid} + '"]').attr("selected", true);
                                        var contactuser = '${contactuser}';
                                        var jobtitle = '${jobtitle}';
                                        var nametitle = '${nametitle}';
                                        agentData(contactuser, jobtitle, nametitle);
                                    });

                                    //          append agent details
                                    function agentData(contactuser, jobtitle, nametitle) {
                                        $('#card-title').append(nametitle);
                                        $('#card-name').append(contactuser);
                                        $('#card-jobtitle').append(jobtitle);
                                    }

                                    //selected value set
                                    $(document).ready(function() {
                                        $('#selectcontact option[value="' + ${contactid} + '"]').attr("selected", true);
                                    });

                                    //date picker for add activity model
                                    $(function() {
                                        $('#adate').datetimepicker('setStartDate', new Date());
                                        $('.activity_datepickerhide').on('change', function() {
                                            $('.datetimepicker').hide();
                                        });
                                    });

                                    //          date picker for update activity model
                                    $(function() {
                                        $('#datetimepicker2').datetimepicker('setStartDate', new Date());
                                        $('#activity_date_picker').datetimepicker('setStartDate', new Date());
                                        $('.update_activity_datepickerhide').on('change', function() {
                                            $('.datetimepicker').hide();
                                        });
                                    });

                                    //          add new activity for selected lead
                                    $("#addactivity").click(function(event) {
                                        event.preventDefault();
                                        if ($('#addActivityForm').valid()) {
                                            var description = $("#Adescription").val();
                                            var leadid = parseInt($("#selectlead").val());
                                            var atype = $("#atype").val();
                                            var adate = $("#adate").val();
                                            jQuery.ajax({
                                                type: "POST",
                                                url: "${pageContext.request.contextPath}/activity/addactivity",
                                                data: {description: description, atype: atype, adate: adate, leadid: leadid},
                                                success: function(res) {
                                                    $('#addActivityModal').modal('toggle');
                                                    $("#Adescription").val('');
                                                    $("#atype").val(2);
                                                    $("#adate").val('');
                                                }
                                            });
                                        }
                                    });

                                    //          set data in update model form field 
                                    $("#call_panel").on("click", ".view-more-link-activity", function() {
                                        var id = $(this).attr("id");
                                        var validator = $("#updateActivityForm").validate();
                                        validator.resetForm();
                                        $("#activity_description").removeClass("invalid");
                                        $("#activity_type").removeClass("invalid");
                                        $('#spent_time_zero').hide();
                                        jQuery.ajax({
                                            type: "POST",
                                            url: "${pageContext.request.contextPath}/activity/getdataactivityupdate",
                                            data: {activityId: parseInt(id)},
                                            success: function(res) {
                                                res1 = JSON.parse(res);
                                                $.each(res1.activityList, function(index, value) {
                                                    $("#edit_activity_id").val(value.activityid);
                                                    $("#activity_description").val(value.description);
                                                    $("#activity_type").val(value.activitytypeid);
                                                    var arr = value.spenttime.split('/');
                                                    $("#activity_spenthour").val(arr[0]);
                                                    $("#activity_spentminite").val(arr[1]);
                                                    $("#datetimepicker_update").val(value.activitytime);
                                                    $('#updateActivityModal').modal({backdrop: 'static', keyboard: false});
                                                });
                                            }
                                        });
                                    });
                                    function SpentTimeZeroFunction() {
                                        var spent_minite = $("#activity_spentminite").val();
                                        var spent_hour = $("#activity_spenthour").val();
                                        if (parseInt(spent_minite) === 0 && parseInt(spent_hour) === 0 || spent_minite == "" || spent_hour == "") {
                                            $('#spent_time_zero').show()();
                                        } else {
                                            $('#spent_time_zero').hide();
                                        }
                                        if (parseInt(spent_hour) > 24) {
                                            $('#activity_spenthour').val('24');
                                        }
                                        if (parseInt(spent_minite) > 59) {
                                            $('#activity_spentminite').val('59');
                                        }

                                    }

                                    $("#visit_panel").on("click", ".view-more-link-activity", function() {
                                        var id = $(this).attr("id");
                                        var validator = $("#updateActivityForm").validate();
                                        validator.resetForm();
                                        $("#activity_description").removeClass("invalid");
                                        $("#activity_type").removeClass("invalid");
                                        $('#spent_time_zero').hide();
                                        jQuery.ajax({
                                            type: "POST",
                                            url: "${pageContext.request.contextPath}/activity/getdataactivityupdate",
                                            data: {activityId: id},
                                            success: function(res) {
                                                res1 = JSON.parse(res);
                                                $.each(res1.activityList, function(index, value) {
                                                    $("#edit_activity_id").val(value.activityid);
                                                    $("#activity_description").val(value.description);
                                                    $("#activity_type").val(value.activitytypeid);
                                                    var arr = value.spenttime.split('/');
                                                    $("#activity_spenthour").val(arr[0]);
                                                    $("#activity_spentminite").val(arr[1]);
                                                    $("#datetimepicker_update").val(value.activitytime);
                                                    $('#updateActivityModal').modal({backdrop: 'static', keyboard: false});
                                                });
                                            }
                                        });
                                    });

                                    $("#propasal_panel").on("click", ".view-more-link-activity", function() {
                                        var validator = $("#updateActivityForm").validate();
                                        validator.resetForm();
                                        $("#activity_description").removeClass("invalid");
                                        $("#activity_type").removeClass("invalid");
                                        $('#spent_time_zero').hide();
                                        var id = $(this).attr("id");
                                        jQuery.ajax({
                                            type: "POST",
                                            url: "${pageContext.request.contextPath}/activity/getdataactivityupdate",
                                            data: {activityId: id},
                                            success: function(res) {
                                                res1 = JSON.parse(res);
                                                $.each(res1.activityList, function(index, value) {
                                                    $("#edit_activity_id").val(value.activityid);
                                                    $("#activity_description").val(value.description);
                                                    $("#activity_type").val(value.activitytypeid);
                                                    var arr = value.spenttime.split('/');
                                                    $("#activity_spenthour").val(arr[0]);
                                                    $("#activity_spentminite").val(arr[1]);
                                                    $("#datetimepicker_update").val(value.activitytime);

                                                    $('#updateActivityModal').modal({backdrop: 'static', keyboard: false});
                                                });
                                            }
                                        });
                                    });

                                    //          update the shown previous type activity
                                    $("#updateactivity").click(function(event) {
                                        event.preventDefault();
                                        if ($("#updateActivityForm").valid()) {
                                            var description = $("#activity_description").val();
                                            var aid = $("#edit_activity_id").val();
                                            var atype = $("#activity_type").val();
                                            var spenthour = $("#activity_spenthour").val();
                                            var spentminite = $("#activity_spentminite").val();
                                            var adate = $("#datetimepicker_update").val();
                                            var leadId = $("#selectlead").val();
                                            var contactid = $("#selectcontact").val();
                                            if (parseInt(spenthour) === 0 && parseInt(spentminite) === 0) {
                                                $('#spent_time_zero').show();
                                            } else {
                                                $('#spent_time_zero').hide();
                                                jQuery.ajax({
                                                    async: true,
                                                    type: "POST",
                                                    url: "${pageContext.request.contextPath}/activity/updateactivity",
                                                    data: {aid: aid, description: description, atype: atype, spenthour: spenthour, spentminite: spentminite, adate: adate, leadId: leadId, contactid: contactid},
                                                    success: function(res) {
                                                        $('#updateActivityModal').modal('toggle');
                                                    }
                                                });
                                            }

                                        }
                                    });
        </script>

        <script type="text/javascript" >
            //      collapse event
            $(document).ready(function() {
                $("#parent-collapse").on("click", ".view-more-link", function() {
                    var id = $(this).attr("id");
                    var sub_id = id.substring(14);
                    var new_id = "#" + id;
                    var viewless = "#view-more-less-more-" + sub_id;
                    $(new_id).on("hide.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-plus"></i>');
                    });
                    $(new_id).on("show.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-minus"></i>');
                    });
                });
                //      collapse event
                $("#parent-collapse-v").on("click", ".view-more-link", function() {
                    var id = $(this).attr("id");
                    var sub_id = id.substring(14);
                    var new_id = "#" + id;
                    var viewless = "#view-more-less-more-" + sub_id;
                    $(new_id).on("hide.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-plus"></i>');
                    });
                    $(new_id).on("show.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-minus"></i>');
                    });
                });
                //      collapse event
                $("#parent-collapse-p").on("click", ".view-more-link", function() {
                    var id = $(this).attr("id");
                    var sub_id = id.substring(14);
                    var new_id = "#" + id;
                    var viewless = "#view-more-less-more-" + sub_id;
                    $(new_id).on("hide.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-plus"></i>');
                    });
                    $(new_id).on("show.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-minus"></i>');
                    });
                });
                //      collapse event
                $("#parent-collapse-basic").on("click", ".view-more-link", function() {
                    var id = $(this).attr("id");
                    var sub_id = id.substring(21);
                    var new_id = "#" + id;
                    var viewless = "#view-more-less-more1-" + sub_id;
                    $(new_id).on("hide.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-plus"></i>  View More');
                    });
                    $(new_id).on("show.bs.collapse", function() {
                        $(viewless).html('<i class="fa fa-minus"></i>  View Less');
                    });
                });
            });

        </script>

        <script>
            //          update history when change the lead id
            function SelectleadFunction() {
                var leadId = $("#selectlead").val();
                var contactId = $("#selectcontact").val();
                $("#createbutton").prop('disabled', false);
                //when change lead link calender to sutable lead
                $("#leadid").attr("value", leadId);
                var ajent = '${createUser}';
                $.ajax({
                    async: true,
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/activity/getLeadIdDetail",
                    cache: false,
                    data: {leadId: leadId, contactId: contactId},
                    success: function(res) {
                        res1 = JSON.parse(res);
                        $('#call_panel').empty();
                        $('#visit_panel').empty();
                        $('#propasal_panel').empty();
                        $('#activityHistory').empty();

                        //                      append activities as activity type vise
                        $.each(res1[0].activityList, function(index, value) {
                            var descriptionid = value.activitytypeid;
                            if (descriptionid === "1") {
                                $('#call_panel').append(callVisitPropasalAppend(value));
                            } else if (descriptionid === "2") {
                                $('#visit_panel').append(callVisitPropasalAppend(value));
                            } else if (descriptionid === "3") {
                                $('#propasal_panel').append(callVisitPropasalAppend(value));
                            }
                        });

                        //                      append all history activities 
                        $.each(res1[0].activityList, function(index, value) {
                            $('#activityHistory').append(activityHistory(value));
                        });

                        //                      sum of due activity type time
                        $("#callTime").html(res1[1].time01);
                        $("#visitTime").html(res1[2].time02);
                        $("#propasalTime").html(res1[3].time03);

                        //                      hide add activity when lead 'all'
                        if (leadId === "0") {
                            $("#scheduleActivity").prop("disabled", true);
                        } else {
                            $("#scheduleActivity").prop("disabled", false);
                        }
                    }
                });
            }

            //when select contactdropdown load lead dropdown
            function SelectContactFunction() {
                $('#createbutton').attr('disabled', 'disabled');
                var contactId = $("#selectcontact").val();
                var ajent = '${createUser}';
                var contactname = $("#selectcontact option:selected").text();
                $("#card-name").html(contactname);
                $.ajax({
                    async: true,
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/activity/getLeadDropdownList",
                    cache: false,
                    data: {contactId: contactId, ajent: ajent},
                    success: function(res) {
                        res1 = JSON.parse(res);
                        $('#selectlead').empty();
                        $('#call_panel').empty();
                        $('#visit_panel').empty();
                        $('#propasal_panel').empty();
                        $('#activityHistory').empty();

                        //                      append activities as activity type vise
                        $('#selectlead').append(
                                $('<option></option>').val(0).html("--Select--")
                                );

                        //                      append activities as activity type vise
                        $.each(res1[0].activityList, function(index, value) {
                            var descriptionid = value.activitytypeid;
                            if (descriptionid === "1") {
                                $('#call_panel').append(callVisitPropasalAppend(value));
                            } else if (descriptionid === "2") {
                                $('#visit_panel').append(callVisitPropasalAppend(value));
                            } else if (descriptionid === "3") {
                                $('#propasal_panel').append(callVisitPropasalAppend(value));
                            }
                        });
                        //                      append all history activities 
                        $.each(res1[0].activityList, function(index, value) {
                            $('#activityHistory').append(activityHistory(value));
                        });

                        $.each(res1[4].leadList, function(index, value) {
                            $('#selectlead').append(
                                    $('<option></option>').val(value.leadid).html(value.description + " | " + value.leadamount)
                                    );
                        });
                        $("#callTime").html(res1[1].time01);
                        $("#visitTime").html(res1[2].time02);
                        $("#propasalTime").html(res1[3].time03);

                    }
                });
            }

            //          function for append all previous acticities 
            function activityHistory(value) {
                var string = '<div class="panel-heading" style="padding: 4px">'
                        + '<div class="panel-heading" style="padding:4px;background-color: #ddd; border-radius: 5px">'
                        + '<div class=panel-heading-type>'
                        + '<p id="type-description" align="center" >' + value.activitytypedescription + '</p>'
                        + '</div>'
                        + '<div id="activity-description-' + value.activityid + '" class="collapse" align="center">'
                        + '<p>' + value.description + '</p>'
                        + '<p>' + value.activitytime + '</p>'
                        + '<p><span>Call duration : ' + value.spenttime + '</span></p>'
                        + '<p>Call initiated by : ' + value.createduser + '</p>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '<br>'
                        + '<div class="view-more-cart" style="padding:4px">'
                        + '<a  class="view-more-link" style="text-decoration: none;" id="activity-description-' + value.activityid + '" data-target="#activity-description-' + value.activityid + '" data-toggle="collapse"><span id="view-more-less-more1-' + value.activityid + '"><i class="fa fa-plus"></i>  View More </span></a>'
                        + '</div>'
                        + '<br>';
                return string;
            }

            //          function for append due type vise activities
            function callVisitPropasalAppend(value) {
                var string = '<div id="activity-more">'
                        + '<div>'
                        + '<div id="lead-collapse-' + value.activityid + '" data-toggle = "collapse" class="panel-collapse collapse">'
                        + '<p style="margin-left: 11px;">' + value.description + '</p>'
                        + '<p style="margin-left: 11px;"><span>Call duration : ' + value.spenttime + '</span></p>'
                        + '<p style="margin-left: 11px;">Call initiated by : ' + value.createduser + '</p>'
                        + '</div>'
                        + '</div>'
                        + '<button class="view-more-link btn  btn-block add-button" style="text-decoration: none;" data-toggle="collapse" id="lead-collapse-' + value.activityid + '" data-parent="#parent-collapse" href="#lead-collapse-' + value.activityid + '"><span id="view-more-less-more-' + value.activityid + '"><i class="fa fa-plus"></i> </span>&nbsp' + value.activitytime + '</button>'
                        + '</div>'
                        + '<br>';
                return string;
            }

            //          add activity model form validations  
            $('#addActivityForm').validate({
                rules: {
                    Adescription: {
                        required: true
                    },
                    atype: {
                        required: true
                    },
                    adate: {
                        required: true
                    }
                }, errorPlacement: function(error, element) {
                    error.insertAfter(element.parent());
                }
            });

            $('#updateActivityForm').validate({
                rules: {
                    activity_description: {
                        required: true
                    },
                    activity_type: {
                        required: true
                    },
                    datetimepicker_update: {
                        required: true
                    }
                }, errorPlacement: function(error, element) {
                    error.insertAfter(element.parent());
                }
            });


            $("#scheduleActivity").click(function(event) {
                event.preventDefault();
                var validator = $("#addActivityForm").validate();
                validator.resetForm();
                $("#atype").val('');
                $("#adate").val('');
                $("#Adescription").val('');
                $("#atype").removeClass("invalid");
                $("#adate").removeClass("invalid");
                $("#Adescription").removeClass("invalid");
                $("#input_group_delete_validation").removeClass("state-error");
                $('#addActivityModal').modal({backdrop: 'static', keyboard: false});
            });
        </script>
    </body>
</html>
