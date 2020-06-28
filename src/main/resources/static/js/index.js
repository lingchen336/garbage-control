$(function() {
    // 模块 cardShow
    (function() {
        var ul_image = $('#body div.exhibition ul.box')[0],
            // 末尾重复li.image数量、
            mun = 4,
            i = 1,
            lock = null;
        var width = $('#body div.exhibition ul.box>li.image').outerWidth(true);
        var lun = $('#body div.exhibition ul.box>li.image').length - mun;
        console.log(ul_image, width, lun);


        // 支持多个属性动态变化,缓冲运动  bufferMove(元素，属性对象，速度，回调函数)
        // bufferMove(this, {top: '150px',left: '300px',width: '600px',height: '600px',opacity: '0.05'}, 17, function() {console.log('Hello World~');});
        function bufferMove(event, attrObj, speed, callback) {
            // 兼容写法：取style样式属性、
            function getStyle(dom, attr) {
                if (window.getComputedStyle) {
                    return window.getComputedStyle(dom, null)[attr];
                } else {
                    return dom.currentStyle[attr];
                }
            }

            // 每次执行时结束上一次的定时器、
            clearInterval(event.timer);
            var peed = null, // 每次运动的速度、
                current = null, // 当前位置、
                target = null; // 终点位置、
            event.timer = setInterval(function() {
                var trigger = true;
                for (var key in attrObj) {
                    if (key == 'opacity') {
                        target = parseFloat(attrObj[key]) * 100;
                        current = parseFloat(getStyle(event, key)) * 100;
                    } else {
                        target = parseFloat(attrObj[key]);
                        current = parseInt(getStyle(event, key));
                    }
                    peed = (target - current) / speed;
                    peed = peed > 0 ? Math.ceil(peed) : Math.floor(peed);
                    if (key == 'opacity') {
                        event.style[key] = (current + peed) / 100;
                    } else {
                        event.style[key] = current + peed + 'px';
                    }
                    if (target != current) {
                        trigger = false;
                    }
                }
                if (trigger) {
                    clearInterval(event.timer);
                    typeof(callback) === 'function' ? callback(): null; // 回调函数、
                }
            }, 20)
        }


        $('#body > div.Button > div.left').click(function() {
            if (!lock) {
                lock = true;
                i--;
                if (ul_image.offsetLeft >= 0) {
                    ul_image.style.left = -(lun) * width + 'px';
                    i = lun;
                }
                bufferMove(ul_image, {
                    left: ul_image.offsetLeft + width + 'px'
                }, 6, function() {
                    console.log(i);
                    lock = false;
                });
            }
        });

        $('#body > div.Button > div.right').click(function() {
            if (!lock) {
                lock = true;
                i++;
                bufferMove(ul_image, {
                    left: ul_image.offsetLeft - width + 'px'
                }, 6, function() {
                    if (ul_image.offsetLeft <= -(lun) * width) {
                        ul_image.style.left = '0px';
                        i = 1;
                    }
                    console.log(i);
                    lock = false;
                });
            }
        });
    }());
});