var $ = layui.jquery;
var jQuery = layui.jquery;

// ajax设置
$.ajaxSetup({
    error: function (r) {
        layer.closeAll();
        if (!r.error) {
            r.error = '未知错误';
        }
        if (r.responseText) {
            r.error = r.responseText;
        }
        console.warn("请求返回 " + r.status, r);
        layer.msg('失败 ' + r.error, {time: r.error.length / 10.0 * 4000});
    }
});

// vue 自定义过滤器
moment.locale('zh-cn');
Vue.filter("moment", function (value, reverse) {
    return ptFormat(value, reverse);
});
Vue.filter("format", function (value, withFromNow) {
    return format(value, withFromNow);
});
//侧栏高亮
var leftNavItems = $(".layui-nav-tree .layui-nav-item");
for (var i = 0; i < leftNavItems.length; i++) {
    var item = $(leftNavItems[i]);
    if (0 === location.pathname.indexOf(item.children('a').attr('href'))) {
        //当前访问的页面
        item.addClass('layui-this');
        break;
    }
}
function format(value, withFromNow) {
    var m = moment(value);
    var format = m.format("lll");
    if (withFromNow) {
        format += '(';
        format += m.fromNow();
        format += ')'
    }
    return format;
}
function ptFormat(value, reverse) {
    var now = new Date().getTime();

    //3天+
    if (now - value > 1000 * 60 * 60 * 24 * 3) {
        return !reverse ? moment(value).format("lll") : moment(value).fromNow();
    }
    return !reverse ? moment(value).fromNow() : moment(value).format("lll");
}
