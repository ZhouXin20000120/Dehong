<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
<li id="save"><a href="#" onclick="formSubmit('addcontract.action','_self');">确定</a></li>
<li id="back"><a href="/findtract.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		新增生产厂家信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">客户名称：</td>
	            <td class="tableContent"><input type="text" name="custom_Name"/></td>
	            <td class="columnTitle_mustbe">收购方：</td>
	            <td class="tableContent"><input type="text" name="offeror"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">合同号：</td>
	            <td class="tableContent"><input type="text" name="contract_No"/></td>
	            <td class="columnTitle_mustbe">打印版式：</td>
	            <td class="tableContent"><input type="radio" name="print_Style" value="2" style="width: 10px"/>两个
					<input type="radio" name="print_Style"  value="1" style="width: 10px"/>一个
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">签单日期：</td>
	            <td class="tableContent"><input type="text" name="signing_Date"/></td>
	            <td class="columnTitle_mustbe">重要程度：</td>
	            <td class="tableContent"><input type="radio" name="import_Num" value="3" style="width: 10px"/>⭐⭐⭐
					<input type="radio" name="import_Num" value="2" style="width: 10px"/>⭐⭐
					<input type="radio" name="import_Num" value="1" style="width: 10px"/>⭐
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">交货日期：</td>
	            <td class="tableContent"><input type="text" name="delivery_Period"/></td>
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent"><input type="text" name="ship_Time"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">总金额：</td>
	            <td class="tableContent"><input type="text" name="total_Amount"/></td>
				<td class="columnTitle_mustbe">验货员：</td>
				<td class="tableContent"><input type="text" name="inspector"/></td>
	        </tr>

			<tr>
				<td class="columnTitle_mustbe">制单人：</td>
				<td class="tableContent"><input type="text" name="input_By"/></td>
				<td class="columnTitle_mustbe">审单人：</td>
				<td class="tableContent"><input type="text" name="check_By"/></td>
			</tr>

			<tr>
				<td class="columnTitle_mustbe">要求：</td>
				<td class="tableContent"><textarea name="request" style="height:120px;"></textarea></td>

				<td class="columnTitle_mustbe">说明：</td>
				<td class="tableContent"><textarea name="remark" style="height:120px;"></textarea></td>
			</tr>


		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

