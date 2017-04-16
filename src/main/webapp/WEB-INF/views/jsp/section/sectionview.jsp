<%-- 
    Document   : sectionview
    Created on : Nov 25, 2015, 10:07:06 AM
    Author     : Isuru
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
                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li> <a href="${pageContext.servletContext.contextPath}/">Section Management</a></li><li>View Sections</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">
                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Sections ID: ${sectionsview.sectionid} 
                            <span>

                            </span>
                        </h1>
                    </div>
                </div>
                <form:form class="smart-form" id="sectionsCreateForm" novalidate="novalidate" commandName="sectionsview" action="${pageContext.request.contextPath}/sections/create" method="post" >
                    <form:input type="hidden" id="sectionid" path="sectionid" value="${sectionid}"/>
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

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Section Description<samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="sectionDes" id="sectionDes" disabled="true" path="sectionDes" placeholder="Section"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Status <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="statusid" path="statusid" disabled="true" items="${statusList}"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Level <samp style="color: red">*</samp></label>
                                <label class="select">
                                    <form:select id="sectionlevel" path="sectionlevel" disabled="true" items="${SectionLevel}" onchange="ParentList('${pageContext.servletContext.contextPath}')"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>

                        <div id="parent">
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Parent<samp style="color: red">*</samp></label>
                                    <label class="select">
                                        <form:select id="parentsection" path="parentsection"  disabled="true" items="${ParantSection}"/>
                                        <i></i>
                                    </label>
                                </section>
                            </div>
                            <div class="col-xs-2"></div>
                        </div>
                    </div>
                    <c:if test="${sectionsview.url != null}">
                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Only Parent Section</label>
                                    <label class="afinity_checkbox">
                                        <form:checkbox path="onlyparentsection" name="checkbox" disabled="true" id="checkbox" onchange="myFunction(this)"/>
                                    </label>
                                </section>
                            </div>
                        </div>
                        <div class="row" id="urldiv">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">URL <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <form:input type="text" id="url" readonly="true" path="url" placeholder="URL"/>
                                        <i></i>
                                    </label>
                                </section>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Icon <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="icon" id="icon" path="icon" disabled="true" placeholder="Section"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Sort Id <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="sortid" id="sortid" disabled="true" path="sortid" placeholder="Sort Id"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <a id="back_btn" type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/section/search">Back to Search</a>
                            </footer>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                </form:form>
                <!-- END OF FORM -->
                <!-- START MODAL -->
                <!-- END MODAL -->                                        
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
    </body>

</html>


