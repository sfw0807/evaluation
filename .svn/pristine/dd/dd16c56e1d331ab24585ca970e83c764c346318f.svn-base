/*imgpreview.min.0.22.jquery.js*/
/*(function (c) { c.expr[':'].linkingToImage = function (a, g, e) { return !!(c(a).attr(e[3]) && c(a).attr(e[3]).match(/\.(gif|jpe?g|png|bmp)$/i)) }; c.fn.imgPreview = function (j) { var b = c.extend({ imgCSS: {}, distanceFromCursor: { top: 10, left: 10 }, preloadImages: true, onShow: function () { }, onHide: function () { }, onLoad: function () { }, containerID: 'imgPreviewContainer', containerLoadingClass: 'loading', thumbPrefix: '', srcAttr: 'href' }, j), d = c('<div/>').attr('id', b.containerID).append('<img/>').hide().css('position', 'absolute').appendTo('body'), f = c('img', d).css(b.imgCSS), h = this.filter(':linkingToImage(' + b.srcAttr + ')'); function i(a) { return a.replace(/(\/?)([^\/]+)$/, '$1' + b.thumbPrefix + '$2') } if (b.preloadImages) { (function (a) { var g = new Image(), e = arguments.callee; g.src = i(c(h[a]).attr(b.srcAttr)); g.onload = function () { h[a + 1] && e(a + 1) } })(0) } h.mousemove(function (a) { d.css({ top: a.pageY + b.distanceFromCursor.top + 'px', left: a.pageX + b.distanceFromCursor.left + 'px' }) }).hover(function () { var a = this; d.addClass(b.containerLoadingClass).show(); f.load(function () { d.removeClass(b.containerLoadingClass); f.show(); b.onLoad.call(f[0], a) }).attr('src', i(c(a).attr(b.srcAttr))); b.onShow.call(d[0], a) }, function () { d.hide(); f.unbind('load').attr('src', '').hide(); b.onHide.call(d[0], this) }); return this } })(jQuery);*/
(function (c) {
    c.expr[':'].linkingToImage = function (a, g, e) {
        return !!(c(a).attr(e[3]) && c(a).attr(e[3]).match(/\.(gif|jpe?g|png|bmp)$/i))
    };
    c.fn.imgPreview = function (j) {
        var b = c.extend({
            imgCSS: {},
            distanceFromCursor: {
                top: 10,
                left: 10
            },
            preloadImages: true,
            onShow: function () { },
            onHide: function () { },
            onLoad: function () { },
            containerID: 'imgPreviewContainer',
            containerLoadingClass: 'loading',
            thumbPrefix: '',
            srcAttr: 'href'
        }, j),
			d = c('<div/>').attr('id', b.containerID).attr('class', b.containerID).append('<img/>').hide().css('position', 'absolute').appendTo('body'),
			f = c('img', d).css(b.imgCSS),
			h = this.filter(':linkingToImage(' + b.srcAttr + ')');

        function i(a) {
            return a.replace(/(\/?)([^\/]+)$/, '$1' + b.thumbPrefix + '$2')
        }
        if (b.preloadImages) {
            (function (a) {
                var g = new Image(),
					e = arguments.callee;
                g.src = i(c(h[a]).attr(b.srcAttr));
                g.onload = function () {
                    h[a + 1] && e(a + 1)
                }
            })(0)
        }
        h.mousemove(function (a) {
            var mt = a.pageY + b.distanceFromCursor.top - $("#imgPreviewContainer img").height();
            var ml = a.pageX + b.distanceFromCursor.left - $("#imgPreviewContainer img").width();
            if (mt < 0) { mt = 0; }
            if (ml < 0) { ml = 0; }
            if (b.imgCSS.width != undefined) {
                /*mw = b.imgCSS.width;*/
            }
            d.css({
                /*top: a.pageY + b.distanceFromCursor.top + 'px',
                left: a.pageX + b.distanceFromCursor.left + 'px'*/
                top: mt + 'px',
                left: ml + 'px'
            })
        }).hover(function () {
            var a = this;
            d.addClass(b.containerLoadingClass).show();
            f.load(function () {
                d.removeClass(b.containerLoadingClass);
                f.show();
                b.onLoad.call(f[0], a)
            }).attr('src', i(c(a).attr(b.srcAttr)));
            b.onShow.call(d[0], a)
        }, null);
        $("#imgPreviewContainer img").hover(null,function () {
            d.hide();
            f.unbind('load').attr('src', '').hide();
            b.onHide.call(d[0], this)
        })
        return this
    }
})(jQuery);