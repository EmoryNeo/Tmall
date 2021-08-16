<%--
  Created by IntelliJ IDEA.
  User: iceorangeduxiaocheng@aliyun.com
  Date: 2021/8/15
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script  type="text/javascript">
    $(function(){
        // 将函数绑定到submit事件
        $("#addForm").submit(function(){
            /**
             * 对分类名称和分类图片做了为空判断，当为空的时候，不能提交
             */
           if(!checkEmpty("name", "分类图片")){
               return false;
           }
           if(!checkEmpty("categoryPic", "分类图片")){
               return false;
           }
           return true;
        });
    });

</script>

<title>分类管理</title>

<div class="workingArea">
    <h1 class="label label-info" >分类管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>分类名称</th>
                <th>属性管理</th>
                <th>产品管理</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">

                <tr>
                    <td>${category.id}</td>
                    <td><img height="40px" src="img/category/${category.id}.jpg"></td>
                    <td>${category.name}</td>

                    <td><a href="admin_property_list?cid=${category.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a href="admin_product_list?cid=${category.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
                    <td><a href="admin_category_edit.do?id=${category.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <!--
                        用于删除的超链接，只想地址admin_category_delete.do，传递当前分类对象的id
                    -->
                    <td><a deleteLink="true" href="admin_category_delete.do?id=${category.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增分类</div>
        <div class="panel-body">
            <!--
                ps：
                    1、method=post 用于保证中文的正确提交
                    2、必须有enctype=multipart/form-data：才能做文件上传
                    3、accept=image/*    ：这样就可以把上传的文件类型限制为图片
            -->
            <form method="post" id="addForm" action="admin_category_add.do" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <td>
                            <input id="categoryPic" accept="image/*" type="file" name="image" />
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp"%>
