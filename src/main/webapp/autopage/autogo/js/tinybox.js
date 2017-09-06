var TINY = {};


function T$(i) {
    return document.getElementById(i)
}

TINY.box = function () {
    var p, m, b, fn, ic, iu, iw, ih, ia, f = 0;
    return {
        show: function (c, u, w, h, a, t) {
            p = document.createElement('div');
            p.id = 'tinybox';
            m = document.createElement('div');
            m.id = 'tinymask';
            b = document.createElement('div');
            b.id = 'tinycontent';
            document.body.appendChild(m);
            document.body.appendChild(p);
            p.appendChild(b);
            m.onclick = TINY.box.hide;
            window.onresize = TINY.box.resize;
            f = 1
            p.style.width = 'auto';
            p.style.height = 'auto';
            p.style.backgroundImage = 'none';
            b.innerHTML = c;
            // if(!a&&!u){

            // }else{
            // 	b.style.display='none'; p.style.width=p.style.height='100px'
            // }
            this.mask();
            ic = c;
            iu = u;
            iw = w;
            ih = h;
            ia = a;
            // this.alpha(m, 1, 80, 3);
            // if (t) {
            //     setTimeout(function () {
            //         TINY.box.hide()
            //     }, 1000 * t)
            // }
        }, mask: function () {
            m.style.height = TINY.page.theight() + 'px';
            m.style.width = '';
            m.style.width = TINY.page.twidth() + 'px'
        }
    }
}();

TINY.page = function () {
    return {
        top: function () {
            return document.body.scrollTop || document.documentElement.scrollTop
        },
        width: function () {
            return self.innerWidth || document.documentElement.clientWidth
        },
        height: function () {
            return self.innerHeight || document.documentElement.clientHeight
        },
        theight: function () {
            var d = document, b = d.body, e = d.documentElement;
            return Math.max(Math.max(b.scrollHeight, e.scrollHeight), Math.max(b.clientHeight, e.clientHeight))
        },
        twidth: function () {
            var d = document, b = d.body, e = d.documentElement;
            return Math.max(Math.max(b.scrollWidth, e.scrollWidth), Math.max(b.clientWidth, e.clientWidth))
        }
    }
}();