<div class="modal fade" id="customerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Customer Account</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-1"></div>
                    <div id="msg_dev" class="col-xs-10"></div>
                    <div class="col-xs-1"></div>
                </div>
                <div class="row">
                    <article class="col-sm-12 col-md-12 col-lg-6">
                        <div class="input-group input-group-lg ">
                            <div class="input-group-btn">
                                <select id="search_type" name="search_type" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                    <option value="1">Customer Code</option>

                                </select>
                            </div>
                            <input style="width: 350px;" class="form-control input-lg" type="text" placeholder="Search"  name="parameter_value" id="parameter_value" maxlength="10" />
                            <div class="input-group-btn">
                                <button onclick="callSearch()" type="submit" id="cus_search_btn" class="btn btn-default">
                                    &nbsp;&nbsp;&nbsp;<i class="fa fa-fw fa-search fa-lg"></i>&nbsp;&nbsp;&nbsp;
                                </button>
                            </div>
                        </div>
                    </article>
                </div>
                <br/>
                <section id="case-datatable" class="case-datatable-hide">

                    <!-- row -->
                    <div class="row">

                        <!-- NEW WIDGET START -->
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>Account search results</h2>

                                </header>
                                <!-- widget div-->
                                <div>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">

                                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                                            <thead>			                
                                                <tr>
                                                    <th>CCID</th>
                                                    <th>Customer Code</th>
                                                    <th data-hide="phone,tablet">Title</th>
                                                    <th data-class="phone"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i> Initials</th>
                                                    <th data-hide="expand"><i class=""></i> Name in Full</th>
                                                    <th>Date of Birth</th>
                                                    <th data-hide="phone,tablet">Gender</th>
                                                    <th data-hide="phone,tablet"> Marital Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td id="cus-ccid"></td>
                                                    <td id="cus-code"></td>
                                                    <td id="cus-title"></td>
                                                    <td id="cus-initials"></td>
                                                    <td id="cus-name"></td>
                                                    <td id="cus-dob"></td>
                                                    <td id="cus-gender"></td>
                                                    <td id="cus-marital"></td>
                                                    <td><a href="#" id="hide_search" data-dismiss="modal" onclick="submitcode()">Select</a></td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                    <!-- end widget content -->

                                </div>
                                <!-- end widget div -->

                            </div>
                            <!-- end widget -->											
                        </article>
                        <!-- WIDGET END -->

                    </div>
                </section>
            </div>
            <!--            <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>-->
        </div>
    </div>
</div>