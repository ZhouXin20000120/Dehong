<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
    	//设置冗余的生产厂家名称
    	function setFactoryName(val){
    		var ele = document.getElementById("factory_Name");
    		ele.value = val;
    	}
    </script>
</head>
<body>
<form method="post">
	<input type="text" name="EXT_CPRODUCT_ID" value="${obj.EXT_CPRODUCT_ID}"/>
	<input type="text" name="CONTRACT_PRODUCT_ID" value="${obj.CONTRACT_PRODUCT_ID}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('updateextpro.action','_self');">确定</a></li>
<li id="back"><a href="${ctx}/findextpro.action?CONTRACT_PRODUCT_ID=${obj.CONTRACT_PRODUCT_ID}">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改附件信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">厂家名称：</td>
	            <td class="tableContent">
	            	<select name="factory_Id" onchange="setFactoryName(this.options[this.selectedIndex].text);">
		            	<option value="">--请选择--</option>
	            		<c:forEach items="${factoryListt}" var="f">
	            			<option value="${f.factory_id}" <c:if test="${obj.factory_Id==f.factory_id}">selected</c:if>>${f.full_name}</option>
	            		</c:forEach>
	            	</select>
	            	<input type="hidden" id="factory_Name" name="factory_Name" value="${obj.factory_Name}"/>
	            </td>
	            <td class="columnTitle_mustbe">货号：</td>
	            <td class="tableContent"><input type="text" name="product_No" value="${obj.product_No}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">分类：</td>
	            <td class="tableContent">
	            	<select name="ctype">
		            	<option value="">--请选择--</option>
	            		<c:forEach items="${ctypeListt}" var="cl">
	            			<option value="${cl.sys_Code_Id}" <c:if test="${obj.ctype==cl.sys_Code_Id}">selected</c:if>>${cl.name}</option>
	            		</c:forEach>
	            	</select>
	            </td>
	            <td class="columnTitle_mustbe">货物照片：</td>
	            <td class="tableContent"><input type="text" name="product_Image" value="${obj.product_Image}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent"><input type="text" name="cnumber" value="${obj.cnumber}"/></td>
	            <td class="columnTitle_mustbe">包装单位：</td>
	            <td class="tableContent"><input type="text" name="packing_Unit" value="${obj.packing_Unit}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">单价：</td>
	            <td class="tableContent"><input type="text" name="price" value="${obj.price}"/></td>
	            <td class="columnTitle_mustbe">排序号：</td>
	            <td class="tableContent"><input type="text" name="order_No" value="${obj.order_No}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货物描述：</td>
	            <td class="tableContent"><textarea name="product_Desc" style="height:120px;">${obj.product_Desc}</textarea></td>
	            <td class="columnTitle_mustbe">要求：</td>
	            <td class="tableContent"><textarea name="product_Request" style="height:120px;">${obj.product_Request}</textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

