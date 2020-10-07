<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('addtract.action','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('findbycid.action','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('deletebycid.action','_self');this.blur();">删除N</a></li>
<li id="new"><a href="#" onclick="formSubmit('updateconstatee.action','_self');this.blur();">上报</a></li>
<li id="new"><a href="#" onclick="formSubmit('updateconstate.action','_self');this.blur();">取消</a></li>
	<li id="new"><a href="#" onclick="formSubmit('print.action','_self');this.blur();">打印</a></li>
	<li id="new"><a href="#" onclick="formSubmit('printmoban.action','_self');this.blur();">模板打印</a></li>
    <li id="new"><a href="#" onclick="formSubmit('','_self');this.blur();">归档</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    生产厂家列表
  </div> 
  </div>
  </div>
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">客户全称</td>
		<td class="tableHeader">合同号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">制单人</td>
		<td class="tableHeader">审单人</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">签单日期</td>
		<td class="tableHeader">交货日期</td>
		<td class="tableHeader">船期</td>
		<td class="tableHeader">总金额</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${contractlist}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="contract_Id" value="${o.contract_Id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.custom_Name}</td>
		<td>${o.contract_No}</td>
		<td>${o.cpnum}/${o.extnum}</td>
		<td>${o.input_By}</td>
		<td>${o.check_By}</td>
		<td>${o.inspector}</td>
		<td><fmt:formatDate value="${o.signing_Date}" pattern="yyyy-MM-dd"/></td>

		<td><fmt:formatDate value="${o.delivery_Period}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.ship_Time}" pattern="yyyy-MM-dd"/></td>
		<td>${o.total_Amount}</td>
		<td>
			<c:if test="${o.state==0}"><font color="green">已上报</font></c:if>
			<c:if test="${o.state==1}">草稿</c:if>
			<c:if test="${o.state==2}">待报运</c:if>
		</td>
		<td><a href="/findpro.action?contract_Id=${o.contract_Id}">[货物]</a></td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

