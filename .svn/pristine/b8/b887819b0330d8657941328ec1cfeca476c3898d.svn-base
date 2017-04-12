window.ui = {};
(function (A) {
    A.tip = function (info, outstate, refresh, href) {
        var poptip_bx = $("#processTip").find('.pro_wrap');
        if (outstate == "success") {
            poptip_bx.find(".text").text(info);
            if (refresh == true) {
                poptip_bx.attr("class", "pro_wrap pro_success").fadeIn(5000, function () {
                    window.location.reload();
                });
            } else if (href != undefined) {
                poptip_bx.attr("class", "pro_wrap pro_success").fadeIn(5000, function () {
                    window.location = href;
                });

            } else {
                poptip_bx.attr("class", "pro_wrap pro_success").fadeIn().delay(2000).fadeOut();
            }
        } else if (outstate == "fail") {
            poptip_bx.find(".text").text(info);
            if (refresh == true) {
                poptip_bx.attr("class", "pro_wrap pro_warning").fadeIn(5000, function () {
                    window.location.reload();
                });
            } else if (href != undefined) {
                poptip_bx.attr("class", "pro_wrap pro_warning").fadeIn(5000, function () {
                    window.location = href;
                });

            } else {
                poptip_bx.attr("class", "pro_wrap pro_warning").fadeIn().delay(2000).fadeOut();
            }
        } else if (outstate == "loading") {
            poptip_bx.find(".text").text(info);
            poptip_bx.attr("class", "pro_wrap pro_loading").fadeIn();
        }
    };
})(ui);