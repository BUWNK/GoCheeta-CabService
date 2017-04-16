
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html lang="en-us">
    <head>
        <jsp:include page="../template/cssinclude.jsp"/>
    </head>

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
                                            <h1 style="font-size: 150px;">500</h1>
                                            <h1 style="font-size: 50px;">Oooops, Something went wrong!</h1>

                                        </div>
                                        <div class="col-sm-6" style="background-color:lavender;">

                                            <h1 style="font-size: 20px;"> 

                                                You have experienced a technical error. We apologize.
                                                We are working hard to correct this issue. Please wait a few moments and try your search again. </h1> <br> <br> <br>

                                            <div class="row">

                                                <div class="col-sm-12">
                                                    <ul class="error-search text-left font-md">
                                                        <li><a href="/AVN-CCL/login"><small>Go to My Home <i class="fa fa-arrow-right"></i></small></a></li>
                                                        <!--							            <li><a href=""><small>Contact IT Staff <i class="fa fa-mail-forward"></i></small></a></li>
                                                                                                                            <li><a href=""><small>Report error!</small></a></li>-->
                                                        <li><a href=" " onclick="goBack()"><small>Go back</small></a></li>
                                                        <script>
                                                            function goBack() {
                                                                window.history.back();
                                                            }
                                                        </script>
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