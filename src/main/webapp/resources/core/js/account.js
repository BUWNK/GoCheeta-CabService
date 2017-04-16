/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

individualSections();

function categoryOnChange(context) {
    loadCategoryType(context);
    loadCodeType(context);
}

function loadCategoryType(context) {
    var category = $('#customer_category').val();
    $('#customer_category_type').empty();
    $('#customer_category_type').append($("<option></option>").attr("value", '').text('-- Select --'));
    if (category) {
        $.ajax({
            type: "post",
            url: context + "/account/create/categorytypelist",
            cache: false,
            data: 'category=' + category,
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#customer_category_type').append($("<option></option>").attr("value", response[i].accountcategorytypeid).text(response[i].description));
                }
            },
            error: function () {
                console.log('Error while request..');
            }
        });
    }
    if (category === '0') { // INDIVIDUAL
        individualSections();
    } else if (category === '1') { // CORPORATE
        corporateSections();
    } else if (category === '2') { // MISCELLANEOUS
        corporateSections();
    } else {
        individualSections();
    }
}

function loadCodeType(context) {
    var category = $('#customer_category').val();
    $('#customer_code_type').empty();
    $('#customer_code_type').append($("<option></option>").attr("value", '').text('-- Select --'));
    if (category) {
        $.ajax({
            type: "post",
            url: context + "/account/create/customercodetypelist",
            cache: false,
            data: 'category=' + category,
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#customer_code_type').append($("<option></option>").attr("value", response[i].codetypeid).text(response[i].description));
                }
            },
            error: function () {
                console.log('Error while request..');
            }
        });
    }
}

function individualSections() {
    $('#collapseOne').parent().show();
    $('.collapseOne').show();
    $('#collapseThree').parent().show();
    $('.collapseThree').show();
    $('#collapseFour').parent().show();
    $('.collapseFour').show();
//    $('#collapseFive').parent().show();
//    $('.collapseFive').show();
    $('#collapseSix').parent().show();
    $('.collapseSix').show();
    $('#collapseEight').parent().hide();
    $('.collapseEight').hide();
    $('#collapseNine').parent().hide();
    $('.collapseNine').hide();
    $('#collapseTwelve').parent().show();
    $('.collapseTwelve').show();
    $('#collapseThirteen').parent().show();
    $('.collapseThirteen').show();

    $('#collapseEleven').parent().show();
    $('.collapseEleven').show();
}

function corporateSections() {
    $('#collapseEight').parent().show();
    $('.collapseEight').show();
    $('#collapseNine').parent().show();
    $('.collapseNine').show();
    $('#collapseOne').parent().hide();
    $('.collapseOne').hide();
    $('#collapseThree').parent().hide();
    $('.collapseThree').hide();
    $('#collapseFour').parent().hide();
    $('.collapseFour').hide();
//    $('#collapseFive').parent().hide();
//    $('.collapseFive').hide();
    $('#collapseSix').parent().hide();
    $('.collapseSix').hide();

    $('#collapseEleven').parent().hide();
    $('.collapseEleven').hide();
}

function loadProvinceList(context) {
    var countryid = $('#country').val();
    $('#city').empty();
    $('#city').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#district').empty();
    $('#district').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#province').empty();
    $('#province').append($("<option></option>").attr("value", '').text('-- Select --'));
    if (countryid) {
        $.ajax({
            async: false,
            type: "post",
            url: context + "/account/create/provincelist",
            cache: false,
            data: 'countryid=' + countryid,
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#province').append($("<option></option>").attr("value", response[i].provinceid).text(response[i].provincename));
                }
            },
            error: function () {
                console.log('Error while request..');
            }
        });
    }
}

function loadDistrictList(context) {
    var provinceid = $('#province').val();
    $('#city').empty();
    $('#city').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#district').empty();
    $('#district').append($("<option></option>").attr("value", '').text('-- Select --'));
    if (provinceid) {
        $.ajax({
            async: false,
            type: "post",
            url: context + "/account/create/districtlist",
            cache: false,
            data: 'provinceid=' + provinceid,
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#district').append($("<option></option>").attr("value", response[i].districtid).text(response[i].districtname));
                }
            },
            error: function () {
                console.log('Error while request..');
            }
        });
    }
}

function loadCityList(context) {
    var districtid = $('#district').val();
    $('#city').empty();
    $('#city').append($("<option></option>").attr("value", '').text('-- Select --'));
    if (districtid) {
        $.ajax({
            async: false,
            type: "post",
            url: context + "/account/create/citylist",
            cache: false,
            data: 'districtid=' + districtid,
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#city').append($("<option></option>").attr("value", response[i].cityid).text(response[i].cityname));
                }
            },
            error: function () {
                console.log('Error while request..');
            }
        });
    }
}

function loadSubsectorList(context) {
    var sectorid = $('#sector').val();
    $('#sub_sector_not_assign').empty();
    $('#sub_sector_assign').empty();
    if (sectorid) {
        $.ajax({
            async: true,
            type: "post",
            url: context + "/account/create/subsectorlist",
            cache: false,
            data: 'sectorid=' + sectorid,
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#sub_sector_not_assign').append($("<option></option>").attr("value", response[i].id).text(response[i].description));
                }
            },
            error: function () {
                console.log('Error while request..');
            }
        });
    }
}

function loadCopSubsectorList(context) {
    var sectorid = $('#copsector').val();
    $('#copsub_sector_not_assign').empty();
    $('#copsub_sector_assign').empty();
    if (sectorid) {
        $.ajax({
            async: true,
            type: "post",
            url: context + "/account/create/subsectorlist",
            cache: false,
            data: 'sectorid=' + sectorid,
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#copsub_sector_not_assign').append($("<option></option>").attr("value", response[i].id).text(response[i].description));
                }
            },
            error: function () {
                console.log('Error while request..');
            }
        });
    }
}

function pad(numb) {
    return (numb < 10 ? '0' : '') + numb;
}

function selectedAddressRow(row, context) {
    var $row = jQuery(row).closest('tr');
    var $columns = $row.find('td');
    jQuery.each($columns, function (i, item) {
        if (i === 5) {
            $('#address_type').val(item.innerHTML);
        } else if (i === 6) {
            $('#address_line_01').val(item.innerHTML);
        } else if (i === 7) {
            $('#address_line_02').val(item.innerHTML);
        } else if (i === 8) {
            $('#address_line_03').val(item.innerHTML);
        } else if (i === 9) {
            $('#country').val(item.innerHTML);
            loadProvinceList(context);
        } else if (i === 10) {
            $('#province').val(item.innerHTML);
            loadDistrictList(context);
        } else if (i === 11) {
            $('#district').val(item.innerHTML);
            loadCityList(context);
        } else if (i === 12) {
            $('#city').val(item.innerHTML);
        } else if (i === 13) {
            $('#gs').val(item.innerHTML);
        } else if (i === 14) {
            $('#gps').val(item.innerHTML);
        } else if (i === 15) {
            $('#land_phone_no').val(item.innerHTML);
        } else if (i === 16) {
            $('#billing_proof').val(item.innerHTML);
        }
    });
}

function selectedEducationRow(row) {
    var $row = jQuery(row).closest('tr');
    var $columns = $row.find('td');
    jQuery.each($columns, function (i, item) {
        if (i === 4) {
            $('#education_level').val(item.innerHTML);
        } else if (i === 5) {
            $('#institute').val(item.innerHTML);
        }
    });
}

function selectedDependentRow(row) {
    var $row = jQuery(row).closest('tr');
    var $columns = $row.find('td');
    jQuery.each($columns, function (i, item) {
        if (i === 5) {
            $('#dependent_relationship').val(item.innerHTML);
        } else if (i === 6) {
            $('#dependent_date_of_birth').val(item.innerHTML);
        } else if (i === 7) {
            $('#dependent_name_in_full').val(item.innerHTML);
        }
    });
}

function selectedHobbyRow(row) {
    var $row = jQuery(row).closest('tr');
    var $columns = $row.find('td');
    jQuery.each($columns, function (i, item) {
        if (i === 4) {
            $('#hobby_id').val(item.innerHTML);
        } else if (i === 5) {
            $('#hobby_comment').val(item.innerHTML);
        }
    });
}

function clearAddressFields() {
    $('#address_type').val('');
    $('#address_line_01').val('');
    $('#address_line_02').val('');
    $('#address_line_03').val('');
    $('#country').val('');
    $('#province').val('');
    $('#district').val('');
    $('#city').val('');
    $('#gs').val('');
    $('#gps').val('');
    $('#land_phone_no').val('');
    $('#billing_proof').val('');
    $('#city').empty();
    $('#city').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#district').empty();
    $('#district').append($("<option></option>").attr("value", '').text('-- Select --'));
    $('#province').empty();
    $('#province').append($("<option></option>").attr("value", '').text('-- Select --'));
}

function clearEducationFields() {
    $('#education_level').val('');
    $('#institute').val('');
}

function clearDependentFields() {
    $('#dependent_relationship').val('');
    $('#dependent_date_of_birth').val('');
    $('#dependent_name_in_full').val('');
}

function clearHobbyFields() {
    $('#hobby_id').val('');
    $('#hobby_comment').val('');
}

function submitFinlizer() {
    return true;
}

function generateSubsectorArray() {
    var subsectors = [];
    $("#sub_sector_assign option").each(function () {
        subsectors.push($(this).val());
    });
    $('#sub_sector_list').val(JSON.stringify(subsectors));
}

function generateCopSubsectorArray() {
    var subsectors = [];
    $("#copsub_sector_assign option").each(function () {
        subsectors.push($(this).val());
    });
    $('#copsub_sector_list').val(JSON.stringify(subsectors));
}

function signatureFileSelect() {
    $('#signature_file').parent().siblings().val($('#signature_file').val());
}

function nicFileSelect() {
    $('#nic_file').parent().siblings().val($('#nic_file').val());
}

function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
    return pattern.test(emailAddress);
}

$(document).ready(function () {

    $('#customer_code').bind('copy paste', function (e) {
        e.preventDefault();
    });

    $('#customer_code_confirm').bind('copy paste', function (e) {
        e.preventDefault();
    });

    $('#date_of_birth').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: "yyyy-mm-dd"
    });

    $('#date_of_birth').datetimepicker('setEndDate', new Date());

    $('#dependent_date_of_birth').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: "yyyy-mm-dd"
    });

    $('#dependent_date_of_birth').datetimepicker('setEndDate', new Date());


//    $('#dependent_date_of_birth').datepicker({
//        maxDate: '+0D',
//        changeMonth: true,
//        changeYear: true,
//        yearRange: '1900:' + (new Date).getFullYear(),
//        dateFormat: 'yy-mm-dd',
//        prevText: '<i class="fa fa-chevron-left"></i>',
//        nextText: '<i class="fa fa-chevron-right"></i>'
//    });


//    $('#dependent_date_of_birth').datepicker({
//        maxDate: '+0D',
//        changeMonth: true,
//        changeYear: true,
//        yearRange: '1900:' + (new Date).getFullYear(),
//        dateFormat: 'yy-mm-dd',
//        prevText: '<i class="fa fa-chevron-left"></i>',
//        nextText: '<i class="fa fa-chevron-right"></i>'
//    });

    $('#customer_code').keyup(function () {
        $('#date_of_birth').val('');
        $('#gender').val('');
        if ($('#customer_code_type').val() === '1') {
            if ($('#customer_code').val().length > 5) {
                var id = $('#customer_code').val();
                var year = id.substring(0, 2);
                if (year > 25) {
                    year = 1900 + parseInt(year);
                } else if (year < 25) {
                    year = 2000 + parseInt(year);
                }
                var date = new Date(year, 0, 1);
                var numberOfDays = parseInt(id.substring(2, 5));
                if (numberOfDays > 500) {
                    numberOfDays = numberOfDays - 500;
                    $('#gender').val('F');
                } else {
                    numberOfDays = numberOfDays - 1;
                    $('#gender').val('M');
                }
                date.setDate(parseInt(numberOfDays));
                var _year = date.getFullYear();
                var _month = (date.getMonth() + 1);
                var _date = date.getDate();
                $('#date_of_birth').val(_year + '-' + pad(_month) + '-' + pad(_date));
            }
        }
    });

    $('#name_in_full').keyup(function () {
        var fullname = $('#name_in_full').val().split(' ');
        $('#name_in_full').val($('#name_in_full').val().toUpperCase());
        $('#initials').val('');
        $('#last_name').val('');
        if (fullname.length > 1) {
            var initials = "";
            for (var i = 0; i < fullname.length - 1; i++) {
                initials += fullname[i].substring(0, 1) + ".";
            }
            $('#initials').val(initials.toUpperCase());
            $('#last_name').val(fullname[fullname.length - 1].toUpperCase());
        }
    });

    $('#sgc_name_in_full').keyup(function () {
        var fullname = $('#sgc_name_in_full').val().split(' ');
        $('#sgc_name_in_full').val($('#sgc_name_in_full').val().toUpperCase());
        $('#sgc_initials').val('');
        $('#sgc_lastname').val('');
        if (fullname.length > 1) {
            var initials = "";
            for (var i = 0; i < fullname.length - 1; i++) {
                initials += fullname[i].substring(0, 1) + ".";
            }
            $('#sgc_initials').val(initials.toUpperCase());
            $('#sgc_lastname').val(fullname[fullname.length - 1].toUpperCase());
        }
    });

    $('#cp_name_in_full').keyup(function () {
        var fullname = $('#cp_name_in_full').val().split(' ');
        $('#cp_name_in_full').val($('#cp_name_in_full').val().toUpperCase());
        $('#cp_initials').val('');
        $('#cp_last_name').val('');
        if (fullname.length > 1) {
            var initials = "";
            for (var i = 0; i < fullname.length - 1; i++) {
                initials += fullname[i].substring(0, 1) + ".";
            }
            $('#cp_initials').val(initials.toUpperCase());
            $('#cp_last_name').val(fullname[fullname.length - 1].toUpperCase());
        }
    });

    $('#pull_right').click(function () {
        var selectedItem = $("#sub_sector_not_assign option:selected");
        $("#sub_sector_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateSubsectorArray();
    });

    $('#pull_left').click(function () {
        var selectedItem = $("#sub_sector_assign option:selected");
        $("#sub_sector_not_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateSubsectorArray();
    });

    $('#sub_sector_not_assign').dblclick(function () {
        var selectedItem = $("#sub_sector_not_assign option:selected");
        $("#sub_sector_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateSubsectorArray();
    });

    $('#sub_sector_assign').dblclick(function () {
        var selectedItem = $("#sub_sector_assign option:selected");
        $("#sub_sector_not_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateSubsectorArray();
    });

    $('#cop_pull_right').click(function () {
        var selectedItem = $("#copsub_sector_not_assign option:selected");
        $("#copsub_sector_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateCopSubsectorArray();
    });

    $('#cop_pull_left').click(function () {
        var selectedItem = $("#copsub_sector_assign option:selected");
        $("#copsub_sector_not_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateCopSubsectorArray();
    });

    $('#copsub_sector_not_assign').dblclick(function () {
        var selectedItem = $("#copsub_sector_not_assign option:selected");
        $("#copsub_sector_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateCopSubsectorArray();
    });

    $('#copsub_sector_assign').dblclick(function () {
        var selectedItem = $("#copsub_sector_assign option:selected");
        $("#copsub_sector_not_assign").append(selectedItem);
        selectedItem.prop("selected", false);
        generateCopSubsectorArray();
    });

    jQuery.validator.addMethod("nic", function (value) {
        return (/^[0-9]{9}[V]$/.test(value) || /^[0-9]{9}[v]$/.test(value) || /^[0-9]{9}[X]$/.test(value) || /^[0-9]{9}[x]$/.test(value) || '' === value);
    }, jQuery.validator.format("Please input valid nic number"));

    jQuery.validator.addMethod("lettersonly", function (value, element) {
        return this.optional(element) || /^[a-z\s]+$/i.test(value);
    }, "Only alphabetical characters");

    jQuery.validator.addMethod("alphanumaric", function (value, element) {
        return this.optional(element) || /^[a-z0-9]+$/i.test(value);
    }, "Only alphabetical characters and numbers.");

    $("#customer_code_type").change(function () {
        var option = $("#customer_code_type").val();
        $("#customer_code").removeAttr('disabled');
        $("#customer_code").val('');
        if (option === "1") { //NIC
            $("#customer_code").rules("remove");
            $("#customer_code").rules("add", {
                required: true,
                maxlength: 10,
                nic: true
            });
        } else if (option === "6") { //BRN
            $("#customer_code").rules("remove");
            $("#customer_code").rules("add", {
                required: true,
                maxlength: 6,
                alphanumaric: true
            });
        } else if (option === "4") { //CCID
            $("#customer_code").rules("remove");
            $("#customer_code").attr('disabled', 'disabled');
        }
    });

    $("#customer_category_type").change(function () {
        if ($('#customer_category_type').val() === "1") {
            $('#collapseFive').parent().show();
            $('.collapseFive').show();
        } else {
            $('#collapseFive').parent().hide();
            $('.collapseFive').hide();
        }
    });

    $("#marital_status").change(function () {
        if ($('#marital_status').val() === "M") {
            $('#collapseFive').parent().show();
            $('.collapseFive').show();
        } else {
            $('#collapseFive').parent().hide();
            $('.collapseFive').hide();
        }
    });

    $('#accountCreate').validate({
        onkeyup: function (element) {
            if (element.id !== 'address_type' && element.id !== 'address_line_01' && element.id !== 'address_line_02' && element.id !== 'country' && element.id !== 'province' && element.id !== 'district' && element.id !== 'land_phone_no' && element.id !== 'billing_proof') {
                $(element).valid();
            }
        },
        onfocusout: function (element) {
            if (element.id !== 'address_type' && element.id !== 'address_line_01' && element.id !== 'address_line_02' && element.id !== 'country' && element.id !== 'province' && element.id !== 'district' && element.id !== 'land_phone_no' && element.id !== 'billing_proof') {
                $(element).valid();
            }
        },
        rules: {
            customer_category: {
                required: true
            },
            customer_category_type: {
                required: true
            },
            customer_code_type: {
                required: true
            },
            customer_code_confirm: {
                equalTo: '#customer_code'
            },
            branch_location: {
                required: true
            },
            title: {
                required: true
            },
            initials: {
                required: true
            },
            preferred_name: {
                lettersonly: true,
                required: true
            },
            last_name: {
                required: true
            },
            name_in_full: {
                lettersonly: true,
                required: true
            },
            date_of_birth: {
                required: true
            },
            gender: {
                required: true
            },
            mothers_maiden_name: {
                lettersonly: true,
                required: true
            },
            mobile_01: {
                number: true,
                minlength: 9
            },
            mobile_02: {
                number: true,
                minlength: 9
            },
            email: {
                email: true
            },
            copemployer: {
                required: true
            },
            copsector: {
                required: true
            },
            employer: {
                lettersonly: true
            },
            sgc_name_in_full: {
                lettersonly: true
            },
            sgc_firstname: {
                lettersonly: true
            },
            sgc_occupation: {
                lettersonly: true
            },
            sgc_employer_business_name: {
                lettersonly: true
            },
            sgc_employer_telephone: {
                number: true,
                minlength: 9
            },
            sgc_years_of_employeement_business: {
                number: true
            },
            cp_nic: {
                nic: true
            },
            cp_name_in_full: {
                lettersonly: true
            },
            cp_preferred_name: {
                lettersonly: true
            },
            cp_mobile: {
                number: true,
                minlength: 9
            },
            cp_email: {
                email: true
            }
        },
        messages: {
            customer_code_confirm: {
                equalTo: "Customer Code Mismatch."
            }
        }
    });
});
