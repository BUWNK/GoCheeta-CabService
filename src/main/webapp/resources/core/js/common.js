$(document).ready(function () {
    $(window).keydown(function (event) {
        if ((event.target.nodeName !== "TEXTAREA") && event.keyCode === 13) {
            event.preventDefault();
            return false;
        }
    });

    $(function () {
        var currenturl = window.location.href;
        var currentappurl = getAppURL(currenturl);
        $('nav a').each(function () {
            var pageurl = getAppURL(this.href);
            if (currentappurl === pageurl) {
                $(this).closest("li").addClass("active");
                $(this).closest("li").parents('li').addClass("open");
                $(this).parents('ul').attr('style', 'display: block');
            }
        });
    });

    $(function () {
        // We can attach the `fileselect` event to all file inputs on the page
        $(document).on('change', ':file', function () {
            var input = $(this),
                    numFiles = input.get(0).files ? input.get(0).files.length : 1,
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [numFiles, label]);
        });

        // We can watch for our custom `fileselect` event like this
        $(':file').on('fileselect', function (event, numFiles, label) {
            var input = $(this).parents('.input-group').find(':text'),
                    log = numFiles > 1 ? numFiles + ' files selected' : label;
            if (input.length) {
                input.val(log);
            } else {
                if (log)
                    alert(log);
            }
        });
    });
});

function getAppURL(url) {
    var urlarray = url.split('/');
    var appurl = "";
    for (var i = 3; i < urlarray.length; i++) {
        if (i === 3) {
            appurl += "/" + urlarray[i] + "/";
        } else if ((i + 1) === urlarray.length) {
            appurl += urlarray[i].split('?')[0];
        } else {
            appurl += urlarray[i] + "/";
        }
    }
    return appurl;
}
