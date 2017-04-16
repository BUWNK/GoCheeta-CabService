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
    <body >
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
                            <li>Sales Pipeline</li><li>Activity Scheduler</li>
                        </ol>
                    </div>
                    <div class="col-xs-6">
                        <p style="color: #ffffff; padding-top: 11px" class="text-right"></p>
                    </div>
                </div>

            </div>
            <div class="row" style="margin-left: 5%; margin-right: 5%; margin-top: 40px;">
                <div class="col-xs-5">
                    <div class="form-group">
                        <label>Select Agent</label>
                        <select class="form-control" id="select-agent" onchange="changeAjentFunction()">
                            <c:forEach var="userDetailList" items="${userDetailList}">
                                <option value="${userDetailList.key}">${userDetailList.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-xs-2"></div>
                <div class="col-xs-5"></div>
            </div>
            <div class="row" style="margin-left: 5%; margin-right: 5%; margin-bottom: 40px;">
                <div class="col-xs-5">
                    <div class="form-group">
                        <label for="sel1">Contact</label>
                        <select class="form-control" id="selectcontact" onchange="changeAjentFunction()">
                            <option value="0">ALL</option>
                            <c:forEach var="contactDroplist" items="${contactList}">
                                <option value="${contactDroplist.contactid}">${contactDroplist.nameinFull}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-xs-2"></div>
                <div class="col-xs-5">
                    <%--     <c:choose>
                            <c:when test="${username == 'TRUE'}">
                                <div class="form-group">
                                    <label for="sel1">Select Agent</label>
                                    <select class="form-control" disabled id="selectagent" onchange="changeAjentFunction()">
                                        <c:forEach var="userDetailList" items="${userDetailList}">
                                            <option value="${userDetailList.key}">${userDetailList.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-group">
                                    <label for="sel1">Select Agent</label>
                                    <select class="form-control" id="selectagent" onchange="changeAjentFunction()">
                                        <c:forEach var="userDetailList" items="${userDetailList}">
                                            <option value="${userDetailList.key}">${userDetailList.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:otherwise>
                        </c:choose> --%>
                    <div class="form-group">
                        <label for="sel1" style="float: left">Lead</label>
                        <select class="form-control" id="selectlead" onchange="changeLeadFunction()">
                            <option value="0">ALL</option>
                            <%--<c:forEach var="leadDetailList" items="${leadDetailList}">
                                <option value="${leadDetailList.leadid}">${leadDetailList.nameinFull} | ${leadDetailList.description} | LKR <fmt:formatNumber type="number" maxFractionDigits="3" value="${leadDetailList.leadamount}" /></option>
                            </c:forEach>--%>
                            <c:forEach var="leaddropList" items="${leaddropList}">
                                <option value="${leaddropList.leadid}">${leaddropList.description} | LKR <fmt:formatNumber type="number" maxFractionDigits="3" value="${leaddropList.leadamount}" /></option>
                            </c:forEach>



                        </select>
                    </div>
                </div>
            </div>
            <div class="row" id="calender-nav">

                <div class="col-xs-3">
                    <div class="col-xs-1"><p class="circle-calendar success-event"></p></div><div class="col-xs-10">Completed Activities</div>
                </div>
                <div class="col-xs-3 ">
                    <div class="col-xs-1"><p class="circle-calendar pending-event"></p></div><div class="col-xs-10">New Activities</div>
                </div>
                <div class="col-xs-3 ">
                    <div class="col-xs-1"><p class="circle-calendar expired-event"></p></div><div class="col-xs-10">Expired Activities</div>
                </div>
                <div class="col-xs-3 "></div>
            </div>

            <div class="row" style="margin-left: 2%;margin-right: 2%">
                <div class="col-xs-9">
                    <div id='calendar'></div>
                </div>
                <div class="col-xs-3">
                    <button id="addactivity" type="button"  class="btn  btn-block add-button"><span class="icon-left"><i class="fa fa-plus"></i></span>Schedule Activity</button>
                    <div class="panel panel-default" style="margin-top: 10px;background: #f5f5f5" id="eventDetail">
                        <c:if test="${not empty eventDetailList}">
                            <c:forEach var = "eventDetailList" items = "${eventDetailList}">
                                <div class="panel-body">
                                    <div style="text-align: center">
                                        <p class="activity-summery" style="margin-bottom: 0px">Activity Summary</p><br/>
                                        <p style="margin-bottom: 0px"><b>Customer Information</b></p>
                                        <p style="margin-bottom: 0px">${eventDetailList.title} ${eventDetailList.nameInFull}</p>
                                        <p style="margin-bottom: 0px">${eventDetailList.jobTitle}</p>
                                        <p style="margin-bottom: 0px">${eventDetailList.mobile}</p>
                                    </div>
                                    <br>
                                    <div style="text-align: center">
                                        <p style="margin-bottom: 0px"><b>Activity Information</b></p>
                                        <p style="margin-bottom: 0px">Activity Type: ${eventDetailList.activityTypeDecription}</p>
                                        <p style="margin-bottom: 0px">Date time: ${eventDetailList.activityTime}</p>
                                        <p style="margin-bottom: 0px">Description: ${eventDetailList.activityDecription}</p>
                                    </div>
                                    <br>
                                    <div style="text-align: center">
                                        <p style="margin-bottom: 0px"><b>Lead Information</b></p>
                                        <p style="margin-bottom: 0px">Product: ${eventDetailList.productdescription}</p>
                                        <p style="margin-bottom: 0px">Lead Amount: ${eventDetailList.leadAmount}</p>
                                    </div>
                                    <br>
                                    <button id="update_activity" type="button"  value="${activityId}" class="btn  btn-block add-button"><span class="icon-left"><i class="fa fa-plus"></i></span><b>Update Activity</b></button>
                                    <br>
                                </div>
                            </c:forEach>
                        </c:if> 
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="addActivityModal" role="dialog" >
            <div class="vertical-alignment-helper">
                <div class="modal-dialog vertical-align-center">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title" id="mySubModalLabel">Schedule New Activity</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="addActivityForm">
                                        <div class="row">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <label >Activity Description<samp style="color: red">*</samp></label>
                                                            <input type="text" class="form-control" id="Adescription" name="Adescription" placeholder="Activity Description" maxlength="100">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-5">
                                                        <div class="form-group">
                                                            <label for="sel1">Activity Type<samp style="color: red">*</samp></label>
                                                            <select class="form-control" id="atype" name="atype">
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
                                                            <label >Activity Date Time<samp style="color: red">*</samp></label>
                                                            <div class="input-group date" id="datetimepicker" name="datetimepicker">
                                                                <input id="adate" name="adate" type="text" class="form-control calender-background" placeholder="Pick a Date" readonly="readonly"/>
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
                            <button type="button" class="btn btn-default" id="addactivitysales">Add Activity</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="updateActivityModal" role="dialog" >
            <div class="vertical-alignment-helper">
                <div class="modal-dialog vertical-align-center">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>

                            </button>
                            <h4 class="modal-title" id="myModalLabel">Update Activity</h4>
                        </div>
                        <div id="closed-lead-id-div"></div>
                        <div class="modal-body">
                            <form role="form" id="leadCreate"></form>
                            <div class="row">
                                <div class="col-xs-12">
                                    <form role="form" id="updateActivityForm">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="form-group">
                                                    <label >Activity Description<samp style="color: red">*</samp></label>
                                                    <input type="text" class="form-control" id="activity_description" name="activity_description" placeholder="Activity Description" maxlength="100" readonly="">
                                                    <input type="hidden" class="form-control" id="edit_activity_id" name="edit_activity_id">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <label for="sel1">Activity Type<samp style="color: red">*</samp></label>
                                                    <select class="form-control" id="activity_type" name="activity_type" disabled="">
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
                                                            <input type="number" id="activity_spenthour" class="form-control" min="0" required="" style="padding-right: 0px;" onchange="SpentTimeZeroFunction()">
                                                        </div>
                                                        <div class="col-xs-2" style="margin-top: 4px;">hh</div>
                                                        <div class="col-xs-3"style="padding-left: 0px;padding-right: 14px;">
                                                            <input type="number" id="activity_spentminite" class="form-control" min="0" required="" style="padding-right: 0px;" onchange="SpentTimeZeroFunction()">
                                                        </div>
                                                        <div class="col-xs-2" style="margin-top: 4px; padding-left: 0px;">mm</div>
                                                    </div>
                                                    <!--<input type="text" class="form-control" id="activity_spenttime" name="activity_spenttime" placeholder="Spent time" maxlength="20">-->
                                                </div>
                                                <div id="spent_time_zero" style="display: none">
                                                    <p style="color: red">Spent time cannot be 0 or empty</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <div class="form-group">
                                                    <label >Activity Date<samp style="color: red">*</samp></label>
                                                    <div class="input-group date" id='activity_date_picker'>
                                                        <input id="datetimepicker_update" name="datetimepicker1" type="text" class="form-control calender-background hidepicker" placeholder="Pick a Date" readonly="readonly"/>
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

        <div id="messageModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Info</h4>
                    </div>
                    <div class="modal-body" style="font-weight: 400; font-size: 14px;">
                        <p>You are too late to update this Activity</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="page-footer">
            <jsp:include page="../template/footer.jsp"/>
        </div>
    </body>
    <jsp:include page="../template/jsinclide.jsp"/>

    <script>
        $('#adate').datetimepicker('setStartDate', new Date());
    </script>

    <script>
        var eventsr = '${events}';
        var eventsjson = JSON.parse(eventsr);
        $('#calendar').fullCalendar({
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            events: eventsjson.events,
            eventClick: function (event) {
                if (event.id) {
                    var activityId = event.id;
                    $.ajax({
                        async: true,
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/calender/eventClick",
                        cache: false,
                        data: {activityId: activityId},
                        success: function (res) {
                            res1 = JSON.parse(res);
                            $.each(res1.eventsListDetail, function (index, value) {
                                $("#eventDetail").html(eventClickDetailAppend(value));
                            });
                        }
                    });
                    return false;
                }
            }
        });
        //this dropdown removed so old requrement
        $(document).ready(function () {
            var leadid = '${leadid}';
            var creteUser = '${createUser}';
            $('#selectlead option[value="' + leadid + '"]').attr("selected", true);
            $('#selectagent option[value="' + creteUser + '"]').attr("selected", true);
            if (leadid == 0) {
                $("#addactivity").prop("disabled", true);
            }
        });

        //for set value to contact dropdown
        $(document).ready(function () {
            var leadid = '${leadid}';
            var contactid = '${contactid}';
            var creteUser = '${createUser}';
            $('#selectcontact option[value="' + contactid + '"]').attr("selected", true);
            $('#selectlead option[value="' + leadid + '"]').attr("selected", true);
            if (leadid == 0) {
                $("#addactivity").prop("disabled", true);
            }
        });

        $('body').on('click', '#update_activity', function (event) {
            event.preventDefault();
            var id = $(update_activity).attr("value");
            $('#spent_time_zero').hide();
            jQuery.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/activity/getdataactivityupdate",
                data: {activityId: parseInt(id)},
                success: function (res) {
                    res1 = JSON.parse(res);
                    if (res1.EXP[0]) {
                        $('#messageModal').modal('show');
                    } else {
                        $.each(res1.activityList, function (index, value) {
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
                }
            });
        });

        function changeAjentFunction() {
            //format currency
            var formatter = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'LKR',
                minimumFractionDigits: 2,
            });

//                    var createUser = $("#selectagent").val();
            var contactId = $("#selectcontact").val();
            var username = $("#select-agent").val();
            if ($("#select-agent").prop('selectedIndex') > 0) {
                document.getElementById("selectcontact").disabled = true;
                document.getElementById("selectlead").disabled = true;
            } else {
                document.getElementById("selectcontact").disabled = false;
                document.getElementById("selectlead").disabled = false;
                if (contactId === "0") {
                    document.getElementById("selectlead").disabled = true;
                } else {
                    document.getElementById("selectlead").disabled = false;
                }
            }
            $.ajax({
                async: true,
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/calender/changecontact",
                cache: false,
                data: {contactId: contactId, username: username},
                success: function (res) {
                    res1 = JSON.parse(res);
                    $('#selectlead').empty();
                    $("#selectlead").append('<option value="' + 0 + '">' + "ALL" + '</option>');
                    $.each(res1[0].LeadDropDown, function (index, value) {
                        $("#selectlead").append('<option value="' + value.leadid + '">' + value.description + " | " + formatter.format(value.leadamount) + '</option>');
                    });
                    $('#calendar').fullCalendar('removeEvents');
                    $('#calendar').fullCalendar('addEventSource', {
                        editable: true,
                        eventLimit: true,
                        events: res1[1].eventsList
                    });
                    if (res1[3].eventsDetailListSize === 1) {
                        $.each(res1[2].eventDetailList, function (index, value) {
                            $("#eventDetail").html(eventClickDetailAppend(value));
                        });
                    } else {
                        $("#eventDetail").html('');
                    }
                }
            });
        }
        function changeLeadFunction() {
            var leadId = $("#selectlead").val();
            var contactid = $("#selectcontact").val();
            $.ajax({
                async: true,
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/calender/changeleaddrop",
                cache: false,
                data: {leadId: leadId, contactid: contactid},
                success: function (res) {
                    res1 = JSON.parse(res);
                    $('#calendar').fullCalendar('removeEvents');
                    $('#calendar').fullCalendar('addEventSource', {
                        editable: true,
                        eventLimit: true, // allow "more" link when too many events
                        events: res1[0].eventsList
                    });
                    if (res1[2].eventsDetailListSize === 1) {
                        $.each(res1[1].eventDetailList, function (index, value) {
                            $("#eventDetail").html(eventClickDetailAppend(value));
                        });
                    } else {
                        $("#eventDetail").html('');
                    }
                    if (leadId === "0") {
                        $("#addactivity").prop("disabled", true);

                    } else {
                        $("#addactivity").prop("disabled", false);
                    }
                }
            });
        }
        function SpentTimeZeroFunction() {
            var spent_minite = $("#activity_spentminite").val();
            var spent_hour = $("#activity_spenthour").val();
            if (parseInt(spent_minite) === 0 && parseInt(spent_hour) === 0 || spent_minite == "" || spent_hour == "") {
                $('#spent_time_zero').show();
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
        function eventClickDetailAppend(value) {
            var string = '<div class="panel-body">'
                    + '<div style="text-align: center">'
                    + '<p class="activity-summery" style="margin-bottom: 0px">Activity Summary</p><br/>'
                    + '<p style="margin-bottom: 0px"><b>Customer Information</b></p>'
                    + '<p style="margin-bottom: 0px">' + value.title + '.' + value.nameInFull + '</p>'
                    + '<p style="margin-bottom: 0px">' + value.jobTitle + '</p>'
                    + '<p style="margin-bottom: 0px">' + value.mobile + '</p>'
                    + '</div>'
                    + '<br>'
                    + '<div style="text-align: center">'
                    + '<p style="margin-bottom: 0px"><b>Activity Information</b></p>'
                    + '<p style="margin-bottom: 0px">Activity Type:' + value.activityTypeDecription + '</p>'
                    + '<p style="margin-bottom: 0px">Date time:' + value.activityTime + '</p>'
                    + '<p style="margin-bottom: 0px">Description:' + value.activityDecription + '</p>'
                    + '</div>'
                    + '<br>'
                    + '<div style="text-align: center">'
                    + '<p style="margin-bottom: 0px"><b>Lead Information</b></p>'
                    + '<p style="margin-bottom: 0px">Product:' + value.productdescription + '</p>'
                    + '<p style="margin-bottom: 0px">Lead Amount:' + value.leadAmount + '</p>'
                    + '</div>'
                    + '<br>'
                    + '<div style="align:center">'
                    + '<button id="update_activity" type="button"  value="' + value.activityId + '" class="btn  btn-block add-button"><span class="icon-left"><i class="fa fa-plus"></i></span><b>Update Activity</b></button>'
                    + '</div>'
                    + '</div>';
            return string;
        }

        $("#addactivitysales").click(function (event) {
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
                    success: function (res) {
                        res1 = JSON.parse(res);
                        $('#addActivityModal').modal('toggle');
                        $('#calendar').fullCalendar('removeEvents');
                        $('#calendar').fullCalendar('addEventSource', {
                            editable: true,
                            eventLimit: true, // allow "more" link when too many events
                            events: res1[1].eventinf
                        });
                        $.each(res1[2].eventDetailList, function (index, value) {
                            $("#eventDetail").html(eventClickDetailAppend(value));
                        });
                        $("#Adescription").val('');
                        $("#atype").val('');
                        $("#adate").val('');
                    }
                });
            }
        });
        $('#adate').on('change', function () {
            $('.datetimepicker').hide();
        });
        $("#addactivity").click(function (event) {
            event.preventDefault();
            var validator = $("#addActivityForm").validate();
            validator.resetForm();
            $("#atype").val('');
            $("#adate").val('');
            $("#Adescription").val('');
            $("#atype").removeClass("invalid");
            $("#adate").removeClass("invalid");
            $("#Adescription").removeClass("invalid");
            $("#datetimepicker").removeClass("state-error");
            $('#addActivityModal').modal({backdrop: 'static', keyboard: false});
        });
        $("#updateactivity").click(function (event) {
            event.preventDefault();
            if ($('#updateActivityForm').valid()) {

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
                        type: "POST",
                        url: "${pageContext.request.contextPath}/activity/updateactivity",
                        data: {aid: aid, description: description, atype: atype, spenthour: spenthour, spentminite: spentminite, adate: adate, leadId: leadId, contactid: contactid},
                        success: function (res) {
                            $('#updateActivityModal').modal('toggle');
                            res1 = JSON.parse(res);
                            $('#calendar').fullCalendar('removeEvents');
                            $('#calendar').fullCalendar('addEventSource', {
                                editable: true,
                                eventLimit: true, // allow "more" link when too many events
                                events: res1[1].eventinf
                            });
                            $.each(res1[2].eventDetailList, function (index, value) {
                                $("#eventDetail").html(eventClickDetailAppend(value));
                            });
                        }
                    });
                }

            }
        });

//        $(function () {
//            $("#activity_spenthour").keydown(function () {
//                // Save old value.
//                $(this).data("old", $(this).val());
//            });
//            $("#activity_spenthour").keyup(function () {
//                // Check correct, else revert back to old value.
//                if (parseInt($(this).val()) <= 23 && parseInt($(this).val()) >= 0)
//                    ;
//                else
//                    $(this).val($(this).data("old"));
//            });
//        });

//              add activity form validation
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
            }, errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            }
        });

        //update model form validation
        $('#updateActivityForm').validate({
            rules: {
                activity_description: {
                    required: true
                },
                activity_type: {
                    required: true
                },
                activity_spenttime: {
                    required: true,
                    number: true,
                    min: 1
                },
                activity_date: {
                    required: true
                }
            }
        });

    </script>




</html>
