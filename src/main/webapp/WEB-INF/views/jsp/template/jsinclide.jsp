<%-- 
    Document   : jsinclide
    Created on : Oct 16, 2015, 9:28:22 AM
    Author     : Isuru
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/resources/core/js/jquery-2.0.2.min.js" var="jquery2" />
<script src="${jquery2}"></script>

<spring:url value="/resources/core/js/jquery-ui-1.10.3.min.js" var="jqueryui" />
<script src="${jqueryui}"></script>

<spring:url value="/resources/core/js/app.config.js" var="appconfig" />
<script src="${appconfig}"></script>

<spring:url value="/resources/core/js/app.min.js" var="appmin" />
<script src="${appmin}"></script>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrap" />
<script src="${bootstrap}"></script>

<spring:url value="/resources/core/js/bootstrap-datetimepicker.js" var="datetimepicker" />
<script src="${datetimepicker}"></script>

<!-- PAGE RELATED PLUGIN(S) -->

<c:choose>
    <c:when test="${MAP=='HP'}">
        <spring:url value="/resources/core/js/datatables/jquery.dataTables.min_Dashboard.js" var="dataTables" />
        <script src="${dataTables}"></script>
    </c:when>
    <c:otherwise>
        <spring:url value="/resources/core/js/datatables/jquery.dataTables.min.js" var="dataTables" />
        <script src="${dataTables}"></script>
    </c:otherwise>
</c:choose>

<spring:url value="/resources/core/js/datatables/dataTables.colVis.min.js" var="colVis" />
<script src="${colVis}"></script>

<spring:url value="/resources/core/js/datatables/dataTables.tableTools.min.js" var="tableTools" />
<script src="${tableTools}"></script>

<spring:url value="/resources/core/js/datatables/dataTables.bootstrap.min.js" var="dataTablesbootstrap" />
<script src="${dataTablesbootstrap}"></script>

<spring:url value="/resources/core/js/datatable-responsive/datatables.responsive.min.js" var="datatablesresponsive" />
<script src="${datatablesresponsive}"></script>

<spring:url value="/resources/core/js/jquery.validate.min.js" var="jqueryvalidation" />
<script src="${jqueryvalidation}"></script>

<spring:url value="/resources/core/js/jquery.tabletojson.min.js" var="tableToJson" />
<script src="${tableToJson}"></script>

<spring:url value="/resources/core/js/jquery.blockUI.js" var="blockingui" />
<script src="${blockingui}"></script>



<c:choose>
    <c:when test="${MAP=='CNAP'}">
        <spring:url value="/resources/core/js/account.js" var="account" />
        <script src="${account}"></script>
    </c:when>
</c:choose>

<spring:url value="/resources/core/js/ajaxcall.loading.js" var="ajaxcallloading" />
<script src="${ajaxcallloading}"></script>

<spring:url value="/resources/core/js/jquery.cookie.js" var="cookiejs" />
<script src="${cookiejs}"></script>

<spring:url value="/resources/core/js/SmartNotification.min.js" var="smartnotification" />
<script src="${smartnotification}"></script>

<spring:url value="/resources/core/js/logout.js" var="logout" />
<script src="${logout}"></script> 

<spring:url value="/resources/core/js/RemindNotification.js" var="RemindNotification" />
<script src="${RemindNotification}"></script> 


<!-- Morris Chart Dependencies -->

<spring:url value="/resources/core/js/raphael.min.js" var="raphael" />
<script src="${raphael}"></script>

<spring:url value="/resources/core/js/morris.min.js" var="morris" />
<script src="${morris}"></script>

<spring:url value="/resources/core/js/Chart.js" var="Chart" />
<script src="${Chart}"></script>

<spring:url value="/resources/core/js/jquery.easy-pie-chart.min.js" var="piechart" />
<script src="${piechart}"></script>

<spring:url value="/resources/core/js/flot/jquery.flot.cust.min.js" var="cust" />
<script src="${cust}"></script>

<spring:url value="/resources/core/js/flot/jquery.flot.fillbetween.min.js" var="fillbetween" />
<script src="${fillbetween}"></script>

<spring:url value="/resources/core/js/flot/jquery.flot.orderBar.min.js" var="orderBar" />
<script src="${orderBar}"></script>

<spring:url value="/resources/core/js/flot/jquery.flot.pie.min.js" var="pie" />
<script src="${pie}"></script>

<spring:url value="/resources/core/js/flot/jquery.flot.resize.min.js" var="resize" />
<script src="${resize}"></script>

<spring:url value="/resources/core/js/flot/jquery.flot.tooltip.min.js" var="tooltip" />
<script src="${tooltip}"></script>

<spring:url value="/resources/core/js/bootstrap-timepicker.min.js" var="timepic" />
<script src="${timepic}"></script>

<spring:url value="/resources/core/js/common.js" var="common" />
<script src="${common}"></script>

<spring:url value="/resources/core/js/jarvis.widget.min.js" var="jarvis" />
<script src="${jarvis}"></script>

<spring:url value="/resources/core/js/chartist.min.js" var="chartistjs" />
<script src="${chartistjs}"></script>

<spring:url value="/resources/core/js/jquery.fileDownload.js" var="filedownload" />
<script src="${filedownload}"></script>




<spring:url value="/resources/core/js/jquery.bootstrap.wizard.min.js" var="bootstrapwizard" />
<script src="${bootstrapwizard}"></script>

<spring:url value="/resources/core/js/jquery.number.min.js" var="numberformater" />
<script src="${numberformater}"></script>

<spring:url value="/resources/core/js/moment.min.js" var="moment" />
<script src="${moment}"></script>

<spring:url value="/resources/core/js/fullcalendar.min.js" var="calenderjs" />
<script src="${calenderjs}"></script>

<spring:url value="/resources/core/js/dateFormat.js" var="dateformatjs" />
<script src="${dateformatjs}"></script>

<spring:url value="/resources/core/js/jquery.dateFormat.js" var="jquerydateformatjs" />
<script src="${jquerydateformatjs}"></script>

<spring:url value="/resources/core/js/sum().js" var="sum" />
<script src="${sum}"></script>


<spring:url value="/resources/core/js/jquery.bootstrap.wizard.min.js" var="bootstrapwizard" />
<script src="${bootstrapwizard}"></script>