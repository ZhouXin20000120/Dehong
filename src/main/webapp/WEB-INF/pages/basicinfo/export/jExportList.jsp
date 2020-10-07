<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form method="post">

	<div id="menubar">
		<div id="middleMenubar">
			<div id="innerMenubar">
				<div id="navMenubar">
					<ul>
						<li id="save"><a href="#" onclick="formSubmit('addexport.action','_self');">确定</a></li>
						<li id="back"><a href="/findexport.action">返回</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="textbox" id="centerTextbox">

		<div class="textbox-header">
			<div class="textbox-inner-header">
				<div class="textbox-title">
					新增货物信息
				</div>
			</div>
		</div>
		<div>
			<div>
				<table class="commonTable" cellspacing="1">
					<tr>
						<td class="columnTitle_mustbe">厂家名称：</td>
						<td class="tableContent"><select name="factory_id">
							<c:forEach items="${factoryList}" var="a">
							<option value="${a.factory_id}">${a.full_name}</option>
							</c:forEach>
								</select></td>
						<td class="columnTitle_mustbe">货号：</td>
						<td class="tableContent"><input type="text" name="product_No"/></td>
					</tr>
					<tr>
						<td class="columnTitle_mustbe">货物照片：</td>
						<td class="tableContent"><input type="text" name="product_Image"/></td>

					</tr>
					<tr>
						<td class="columnTitle_mustbe">数量：</td>
						<td class="tableContent"><input type="text" name="cnumber"/></td>
						<td class="columnTitle_mustbe">包装单位：</td>
						<td class="tableContent"><input type="text" name="packing_Unit"/>

						</td>
					</tr>
					<tr>
						<td class="columnTitle_mustbe">装率：</td>
						<td class="tableContent"><input type="text" name="loading_Rate"/></td>
						<td class="columnTitle_mustbe">箱数：</td>
						<td class="tableContent"><input type="text" name="box_Num"/></td>
					</tr>
					<tr>
						<td class="columnTitle_mustbe">单价：</td>
						<td class="tableContent"><input type="text" name="price"/></td>
						<td class="columnTitle_mustbe">排序号：</td>
						<td class="tableContent"><input type="text" name="order_No"/></td>
					</tr>

					<tr>
						<td class="columnTitle_mustbe">货物描述：</td>
						<td class="tableContent"><input type="text" name="product_Desc"/></td>

					</tr>




				</table>
			</div>
		</div>


</form>

<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    货物列表
  </div>
  </div>
  </div>

<div>

	<div class="eXtremeTable">
		<table id="ec_table" class="tableRegion" width="98%">
			<thead>
			<tr>
				<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
				<td class="tableHeader">序号</td>
				<td class="tableHeader">厂家名称</td>
				<td class="tableHeader">货号</td>
				<td class="tableHeader">数量</td>
				<td class="tableHeader">包装单位</td>
				<td class="tableHeader">装率</td>
				<td class="tableHeader">箱数</td>
				<td class="tableHeader">单价</td>
				<td class="tableHeader">总金额</td>
				<td class="tableHeader">操作</td>
			</tr>
			</thead>
			<tbody class="tableBody">
			<c:forEach items="${exportList}" var="o" varStatus="status">
				<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
					<td><input type="checkbox" name="EXPORT_PRODUCT_ID" value="${o.EXPORT_PRODUCT_ID}"/></td>
					<td>${status.index+1}</td>
					<td>${o.factory_c.full_name}</td>
					<td>${o.product_No}</td>
					<td>${o.cnumber}</td>
					<td>${o.packing_Unit}</td>
					<td>${o.loading_Rate}</td>

					<td>${o.box_Num}</td>
					<td>${o.price}</td>
					<td>${o.amount}</td>
					<td>
						<a href="/findbyexport.action?EXPORT_PRODUCT_ID=${o.EXPORT_PRODUCT_ID}">{修改}</a>
						<a href="/deleteexport.action?EXPORT_PRODUCT_ID=${o.EXPORT_PRODUCT_ID}">{删除}</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
 
</div>
 
 

</body>
</html>

