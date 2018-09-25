<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>添加订单-后台管理系统-Admin 1.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weadmin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/treeselect.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/treeselect.js" charset="utf-8"></script>--%>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="weadmin-body">
    <form class="layui-form">
       <%-- <div class="layui-form-item">
            <label for="id" class="layui-form-label">类别id</label>
            <div class="layui-input-inline">
                <input type="text" id="id" name="id" required="" lay-verify="" autocomplete="off"
                       class="layui-input">
            </div>
        </div>--%>

        <div class="layui-form-item">
            <label for="parentId" class="layui-form-label">输入框</label>
            <div class="layui-input-inline">
                <input type="text" id="parentId" name="parentId" required="" lay-verify="number" placeholder="父类别id" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="name" class="layui-form-label">类别名称</label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" required="" lay-verify="required" autocomplete="off" placeholder="父类别id"
                       class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="status" class="layui-form-label">类别状态</label>
            <div class="layui-input-inline">
                <input type="text" id="status" name="status" required="" lay-verify="required" placeholder="类别状态"  autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="sort" class="layui-form-label">排序</label>
            <div class="layui-input-inline">
                <input type="text" id="sort" name="sort" required="" lay-verify="number" autocomplete="off" placeholder="排序"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="addcategory" class="layui-form-label">
            </label>
            <button id="addcategory" class="layui-btn" lay-filter="add" lay-submit="">增加</button>
        </div>
    </form>
</div>
<script>
    layui.extend({
        admin: '{/}../../static/js/admin',
        treeselect: '{/}../../static/js/treeselect'
    });
    layui.use(['form', 'admin','layer', 'layedit', 'treeselect','laydate'], function () {
        var form = layui.form,
            admin = layui.admin,
            layer = layui.layer,
            layedit = layui.layedit,
            treeselect = layui.treeselect,
                laydate = layui.laydate;
            $=layui.$;
            form.on('submit(add)', function (data) {
                console.log(data);
                //发异步，把数据提交给php
                $.ajax({
                    url: "${pageContext.request.contextPath}/add",
                    type: 'post',
                    data:{ name:data.field.name
                     ,parentId:data.field.parentId,
                        status:data.field.status,
                        sort:data.field.sort

                    },
                    dataType: 'JSON',
                    success: function(data){
                        if(data=='1'){
                            layer.alert("增加成功", {
                                icon: 6
                            }, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                            });
                        }else if(data=='error'){
                            layer.msg('添加失败');
                        }
                    }
                })

                return false;
            });

            });

        //监听提交
        /*form.on('submit(add)', function (data) {
            console.log(data);
            //发异步，把数据提交给php
            $.ajax({
                url: "{:url('../../add')}",
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (data) {
                    layer.msg(data.msg, {icon: 6});
                    location.href = location.href;//刷新页面

                    return false;
                }
            });
         layer.alert("增加成功", {
                icon: 6
            }, function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
            return false;
        });*/


</script>
</body>

</html>