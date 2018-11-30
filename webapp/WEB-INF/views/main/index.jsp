<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" 
	  href="${pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" 
	  href="${pageContext.servletContext.contextPath }/resources/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
<script>
$( document ).ready(function() {
    var counts = ${counts};
    var myRadio = $("input[name=size]");
    
    var checkedValue = myRadio.filter(":checked").val();
	var pageButton;
    
    pageButton = Math.ceil( counts / checkedValue ); 
    for( i = 1; i <= pageButton; i++){
    	$('ul.pagination').append('<li class="page-item"><a class="page-link" href="#">' + i + '</a></li>');
    }
	//alert(pageButton);
	
	// 이벤트 등록 
    $('input[name=size]').on('change', function() {
    	myRadio = $("input[name=size]");
    	checkedValue = myRadio.filter(":checked").val();
    	alert(checkedValue);
    	pageButton = Math.ceil( counts / checkedValue );
    	//alert(pageButton);
	});
	
});


</script>
<title>${pageContext.servletContext.contextPath }</title>
</head>
<body>
<h3 class="pageTitle" align="center" > employee list</h3>

<div class="searchWrap">
 <form action="${pageContext.request.contextPath }/employee" id="searchForm">
	<input name="key" id="keyword" type="text">
	<input name="size" value="10" type="radio"> 10개씩
	<input name="size" value="20" type="radio"> 20개씩
	<input name="size" value="30" type="radio"> 30개씩
	
	<input type="submit" value="검색">
 </form>
</div>

<hr>
<div class="tableArea">
 <table border="1" class="myTable">
 	<tr class="tb_header" >
 	 <td>ID</td>
 	 <td>성</td>
 	 <td>이름</td>
 	 <td>이메일</td>
 	 <td>전화번호</td>
 	 <td>입사일</td>
 	 <td>잡ID</td>
 	 <td>연봉</td>
 	 <td>커미션%</td>
 	 <td>manager_id</td>
 	 <td>부서ID</td>
 	</tr>
 	<c:forEach items="${data}" var="vo" varStatus="status">
	 <tr> 		
 		<td><div style="text-align:center; ">${vo.EMPLOYEE_ID}</div></td>
 		<td>${vo.FIRST_NAME}    </td>
 		<td>${vo.LAST_NAME}     </td>
 		<td>${vo.EMAIL}         </td>
 		<td>${vo.PHONE_NUMBER}  </td>
 		<td>${vo.HIRE_DATE}     </td>
 		<td>${vo.JOB_ID}        </td>
 		<td>${vo.SALARY}        </td>
 		<td>${vo.COMMISSION_PCT}</td>
 		<td>${vo.MANAGER_ID}       </td>
 		<td>${vo.DEPARTMENT_ID}    </td>    
 	 </tr>
 	</c:forEach>
 
 </table>
 
 <div>
  <ul class="pagination" style="margin: 10px;">
  	<!--  
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
    -->
  </ul>
 </div>
 
</div>
</body>
</html>