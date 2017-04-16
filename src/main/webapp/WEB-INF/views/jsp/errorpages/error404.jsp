
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html lang="en-us">
    <head>

    </head>
    <jsp:include page="../template/cssinclude.jsp"/>

    <body>

        <!-- HEADER -->
        <header id="header">			
            <jsp:include page="../template/header.jsp"/>
        </header>
        <!-- END HEADER -->

        <aside id="left-panel">
            <%--<jsp:include page="../template/menu.jsp"/>--%>
        </aside>
        <!-- END NAVIGATION -->

        <!-- MAIN PANEL -->
        <div id="main" role="main">

            <!-- RIBBON -->
            <!--            <div id="ribbon">
            
                            <ol class="breadcrumb">
                                <li>Error</li><li>404</li>
            
                        </div>-->
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->

            <div id="content"  >

                <div class="row">
                    <div class="col-sm-1" ></div>
                    <div class="col-sm-10" >
                        <div class="panel_old">
                            <div class="panel_new panel-default_new">
                                <div class="panel-body_new" style="background-color:lavender;">

                                    <div class="row">
                                        <div class="col-sm-6" style="background-color:lavender;">
                                            <h1 style="font-size: 150px;">404</h1>
                                            <h1 style="font-size: 53px;"> File not found,sorry.</h1>
                                        </div>
                                        <div class="col-sm-6" style="background-color:lavender;">

                                            <h1 style="font-size: 20px;"> The Linked you followed may be broken, or the page may have been removed
                                                , either contact your <b> Administrator </b> or try again. Use your browsers <b>Back</b> button to navigate to the page you have previously come from </h1> <br> <br> <br>

                                            <div class="row">

                                                <div class="col-sm-12">
                                                    <ul class="list-inline">
                                                        <li>
                                                            &nbsp;<a href="/AVN-CCL/login"><h3>Home</h3></a>&nbsp &nbsp;
                                                        </li>

                                                        <li>
                                                            &nbsp;<a href="/AVN-CCL"><h3>Login</h3></a>&nbsp &nbsp;
                                                        </li>

                                                        <li>
                                                            &nbsp;<a href="" onclick="goBack()"><h3>Go Back </h3> </a>&nbsp &nbsp;
                                                            <script>
                                                                function goBack() {
                                                                    window.history.back();
                                                                }
                                                            </script>
                                                        </li>


                                                    </ul>
                                                </div>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-1" ></div>
                    </div>


                </div>

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

    </body>

</html>