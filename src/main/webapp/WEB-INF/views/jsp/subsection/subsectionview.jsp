<%-- 
    Document   : subsectionview
    Created on : Nov 26, 2015, 10:15:20 AM
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
                    <li> <a href="${pageContext.servletContext.contextPath}/">Section Management</a></li><li>View SubSections</li>
                    <!-- end breadcrumb -->
            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">

                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            SubSection View 
                            <span>
                                 Subsection Id: ${subsectionsview.subsectionId} 
                            </span>
                        </h1>
                    </div>
                </div>

<!--                <div class="row">
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <i class="fa fa-edit fa-fw "></i> 
                            Sub Section ID: ${subsectionsview.subsectionId} 
                            <span>

                            </span>
                        </h1>
                    </div>
                </div>-->
                <form:form class="smart-form" id="sectionsCreateForm" novalidate="novalidate" commandName="subsectionsview" action="${pageContext.request.contextPath}/sections/create" method="post" >
                    <form:input type="hidden" id="subsectionId" path="subsectionId" value="${subsectionId}"/>
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
                                <label class="label">Section <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input id="sectionDes" path="sectionDes" readonly="true"/>
                                    <i></i>
                                </label>

                        </div>

                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Sub Section <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="subsectionDes" id="subsectionDes" path="subsectionDes" placeholder="Sub Section" readonly="true"/>
                                    <i></i>
                                </label>
                            </section>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Status <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input id="statusDes" path="statusDes" readonly="true"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Icon <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="icon" id="icon" path="icon" placeholder="Section" readonly="true"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div id="parent">
                        <div class="row">

                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">URL <samp style="color: red">*</samp></label>
                                    <label class="input">
                                        <form:input type="text" id="url" path="url" placeholder="URL" readonly="true"/>
                                        <i></i>
                                    </label>
                                </section> 
                            </div>
                            <div class="col-xs-2"></div>
                            <div class="col-xs-3">
                                <section>
                                    <label class="label">Clickable  URL</label>
                                    <label class="afinity_checkbox">
                                        <form:checkbox path="clickableurl" checked="checked" id="checkbox" disabled="true"/>
                                    </label>
                                </section>

                            </div>

                            <div class="col-xs-2"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-3">
                            <section>
                                <label class="label">Sort Id <samp style="color: red">*</samp></label>
                                <label class="input">
                                    <form:input type="sortid" id="sortid" path="sortid" placeholder="Sort Id" readonly="true"/>
                                    <i></i>
                                </label>
                            </section>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <footer style="background-color: #ffffff">
                                <a id="back_btn" type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/subsection/search">Back to Search</a>
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

