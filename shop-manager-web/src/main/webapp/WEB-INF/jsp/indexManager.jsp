<%--
  Created by IntelliJ IDEA.
  User: baoli
  Date: 2019/1/2
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="importAll()">一键导入搜索商品索引库</a>
</div>
<script>
    function importAll() {
        $.ajax({
            type:"POST",
            url:"/index/importAll",
            dataType:"json",
            success:function (data) {
                if(data.state==200){
                    alert("成功");
                }
            }


        })
    }
</script>