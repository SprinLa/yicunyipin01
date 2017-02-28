function Tab(tabId, tabTag, contentId, contentTag, option) {
    this.setOption(option);
    var tabEl = Kg.$(tabId),
    tabChild = tabEl.childNodes,
    contentEl = Kg.$(contentId),
    contentChild = contentEl.childNodes;
    this.tabsFather = tabEl;
    this.contentFather = contentEl;
    this.tabs = [];
    this.contents = [];
    this.timer = null;
    for (var i = 0,
    l = tabChild.length; i < l; i++) {
        if (tabChild[i].nodeType == 1 && tabChild[i].tagName.toLowerCase() == tabTag) {
            this.tabs.push(tabChild[i])
        }
    }
    for (var i = 0,
    l = contentChild.length; i < l; i++) {
        if (contentChild[i].nodeType == 1 && contentChild[i].tagName.toLowerCase() == contentTag) {
            this.contents.push(contentChild[i])
        }
    }
    this.currentIndex = this.option.index === "random" ? parseInt(Math.random() * this.tabs.length) : this.option.index;
    for (var i = 0,
    l = this.contents.length; i < l; i++) {
        this.contents[i].style.display = "none"
    }
    this.tabs[this.currentIndex].className += " " + this.option.current;
    this.contents[this.currentIndex].style.display = "block";
    this.run()
};
Tab.prototype.setOption = function(option) {
    this.option = {
        event: "click",
        effect: null,
        fadeStep: 1,
        current: "current",
        loop: true,
        index: 0,
        autoPlay: false,
        timeout: 1500,
        delay: 0,
        callback: null
    };
    Kg.extend(this.option, option || {},
    true)
};
Tab.prototype.run = function() {
    var timer = null;
    for (var i = 0,
    l = this.tabs.length; i < l; i++) {
        Kg.addEvent(this.tabs[i], this.option.event, Kg.bind(function(index) {
            if (this.option.event == "mouseover" && this.option.delay > 0) {
                var _this = this;
                timer = setTimeout(function() {
                    _this.change(index)
                },
                this.option.delay)
            } else {
                this.change(index)
            }
        },
        this, i));
        if (this.option.event == "mouseover" && this.option.delay > 0) {
            Kg.addEvent(this.tabs[i], "mouseout",
            function() {
                clearTimeout(timer)
            })
        }
    }
    if (this.option.autoPlay) {
        this.auto();
        Kg.addEvent(this.tabsFather, "mouseover", Kg.bind(function() {
            clearInterval(this.timer)
        },
        this));
        Kg.addEvent(this.contentFather, "mouseover", Kg.bind(function() {
            clearInterval(this.timer)
        },
        this));
        Kg.addEvent(this.tabsFather, "mouseout", Kg.bind(function() {
            this.auto()
        },
        this));
        Kg.addEvent(this.contentFather, "mouseout", Kg.bind(function() {
            this.auto()
        },
        this))
    }
};
Tab.prototype.change = function(index) {
    var current = this.option.current;
    var curIndex = this.currentIndex;
    var lastCon = this.contents[curIndex];
    var curCon = this.contents[index];
    this.tabs[curIndex].className = this.tabs[curIndex].className.replace(new RegExp("\\s*" + current, "g"), "");
    this.tabs[index].className += " " + current;
    lastCon.style.display = "none";
    curCon.style.display = "block";
    if (this.option.effect === "fade") {
        clearInterval(lastCon.timer);
        Kg.setOpacity(this.contents[index], 0);
        curCon.timer = Kg.fadein(curCon, 1, this.option.fadeStep)
    }
    this.currentIndex = index;
    typeof(this.option.callback) == "function" && this.option.callback()
};
Tab.prototype.auto = function() {
    clearInterval(this.timer);
    this.timer = setInterval(Kg.bind(function() {
        this.next()
    },
    this), this.option.timeout)
};
Tab.prototype.next = function() {
    var index = (this.currentIndex + 1) > (this.tabs.length - 1) ? 0 : (this.currentIndex + 1);
    if (this.option.loop === false && index === 0) {
        return
    }
    this.change(index)
};
Tab.prototype.prev = function() {
    var index = (this.currentIndex - 1) >= 0 ? (this.currentIndex - 1) : (this.tabs.length - 1);
    if (this.option.loop === false && index === this.tabs.length - 1) {
        return
    }
    this.change(index)
};
Tab.rotation = function(contentId, contentTag, timeout) {
    var timer = null;
    var el = Kg.$(contentId);
    var els = Kg.$T(contentTag, el);
    var index = 0;
    Kg.addEvent(el, "mouseover",
    function() {
        clearInterval(timer)
    });
    Kg.addEvent(el, "mouseout",
    function() {
        go()
    });
    function go() {
        timer = window.setInterval(function() {
            for (var i = 0; i < els.length; i++) {
                if (els[i].offsetHeight > 0) index = i;
                els[i].style.display = "none"
            }
            var num = (index >= els.length - 1) ? 0 : index + 1;
            els[num].style.display = "block"
        },
        timeout)
    }
    go()
}