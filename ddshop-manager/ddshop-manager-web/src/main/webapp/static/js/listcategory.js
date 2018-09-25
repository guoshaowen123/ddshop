//引入admin.js(webapp/static/js/admin.js)
//itemlist.js(webapp/static/js/itemlist.js)
layui.extend({
  admin: '{/}../../static/js/admin'
});
//按需加载admin.js
layui.use(['admin', 'jquery','form', 'table','layer'], function () {
  //初始化变量
  var admin = layui.admin,
      $ = layui.jquery,
      table = layui.table,
      form = layui.form,
      layer = layui.layer;
  //对表格进行渲染
  table.render({
    //什么是表格属性：page,elem,url,cols
    //什么是列属性：type,field,title
    //开启分页
    page: true,
    //渲染的容器是谁
    elem: '#articleList',
    //异步提交请求给后台返回JSON
    url: '../../items',
    //要显示的表头是什么
    cols:[[

      {type:'checkbox'},
      {field:'id',title:'类别id'},
      {field:'parentId',title:'父类别id'},
{field:'name',title:'类别名称'},
{field:'status',title:'类别状态',templet: '#myTpl2'},
{field:'sort',title:'排序'},
{field:'createdate',title:'创建时间'},
{field:'modifytime',title:'更新时间'}
]]

 //当执行完渲染之后的回调（table.render）
 //,done:function(res,curr,count){
 //    //val() 相当于js中的value，一般用于文本框、单选按钮、复选按钮取值
 //    //text() 相当于js中的innerText,一般用于设置文本和取值
 //    //html() 相当于js中的innerHTML,一般用于设置HTML和取值
 //
 //    $("[data-field='status']").children().each(function(){
 //        if($(this).text() == '1'){
 //            $(this).text('正常');
 //        }else if ($(this).text() == '2'){
 //            $(this).text('下架');
 //        }
 //    });
 //}

});
  //监听行工具事件
  form.on('switch(update)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = $(obj.elem);//获得当前行数据
    var id = data.parents('tr').first().find('td').eq(1).text();
    var status = data.val();

    var check = obj.elem.checked;


    $.post({
      url: "../../update",
      type: 'post',
      data:{ id:id, status:status},
      dataType: 'JSON',
      success:function (data){
      if (data > 0) {
        //停留在原来页面刷新
        $('.layui-laypage-btn').click();
      //  layer.msg("修改成功！", {icon: 6});
      }
    }
  })

/*    $.ajax({
      url: "../../update",
      type: 'post',
      data:{ id:id,
        status:status
      },
      dataType: 'JSON',
    })*/

  });

  //form.on('switch(update)', function(obj){
  //  //当前元素
  //  var data = $(obj.elem);
  //  //遍历父级tr，取第一个，然后查找第二个td，取值
  //  var id = data.parents('tr').first().find('td').eq(1).text();
  //  var check = obj.elem.checked;
  //  $.ajax({
  //    url: "../../update",
  //    type: 'post',
  //    data:{ id:id,
  //      status:status
  //    },
  //    dataType: 'JSON',
  //    success: function(data){
  //      layer.alert("成功");
  //    }
  //  })
  //});


//定义active
var active = {
  reload:function(){
    //val text html
    var title = $.trim($("#title").val());
    if(title.length > 0){
      table.reload('articleList',{
        page:{curr:1},
        where:{name:title}
      });
    }

  },
  getCheckData:function(){
    //获取选中行的数据
    var data = table.checkStatus("articleList").data;
    //判断是否有选中行
    if(data.length > 0){
      //有选中行
      //创建一个空数组
      var ids = [];
      //遍历data
      for(var i=0;i<data.length;i++){
        ids.push(data[i].id);
      }
      console.log(ids);
      //异步提交到后台 ids
      $.post(
          '../../iteam/update',
          {'ids[]': ids},
          function (data) {
            //至少删除一条记录
            if (data > 0) {
              //停留在原来页面刷新
              $('.layui-laypage-btn').click();
              layer.msg("恭喜，删除成功！", {icon: 1});
            }
          },
          'json'
      );
    }else{
      //没有选中行
      layer.msg("请至少选中一行再批量删除！", {icon: 2});
    }
  }
};


//点击"批量删除"按钮触发的事件
$(".demoTable .layui-btn-danger").on('click', function () {
  // //获取按钮的data-type属性
  var type = $(this).data("type");//getCheckData
  // //判断active这个变量中是否具有getCheckData属性，若有，则调用，若无，则什么都不做
  active[type] ? active[type].call(this) : '';
});

//点击"模糊查询"按钮触发的事件
$(".layui-row .layui-btn").on('click',function(){
  //获取相关属性
  var type = $(this).data('type');//reload
  //判断active对象有没有这个属性
  active[type] ? active[type].call(this) : '';

});


});