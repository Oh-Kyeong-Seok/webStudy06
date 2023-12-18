<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<table class="table table_border">
		<thead>
			<tr>
				<th>NO</th>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>IP</th>
				<th>이메일</th>
				<th>내용</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
	  	<tbody>
	  		<c:set value="${paging.dataList }" var="boardList" />
	  		<c:if test="${empty boardList }">
	  			<tr>
	  				<td colspan="6">검색 조건에 맞는 리스트 없습니다 </td>
	  			</tr>
	  		</c:if>
	  		<c:if test="${not empty boardList }">
	  			<c:forEach items="${boardList }" var="board">
		  			<tr>
		  				<td>${board.rnum }</td>
		  				<td>${board.boNo }</td>
		  				<td>
		  				<a href="<c:url value='/board/${board.boNo}' />">${board.boTitle}</a>
		  				[${board.filecnt}]
		  				</td>
		  				<td>${board.boWriter }</td>
		  				<td>${board.boIp }</td>
		  				<td>${board.boMail }</td>
		  				<td>${board.boContent }</td>
		  				<td>${board.boDate }</td>
		  				<td>${board.boHit }</td>
		  			</tr>
	  			</c:forEach>
	  		</c:if>
	  	</tbody>
	  	<tfoot>
	  		<tr>
	  			<td colspan="9">
	  			${paging.pagingHTML }
	  			</td>
	  		</tr>
	  	</tfoot>
	</table>
	
<form:form modelAttribute="detailCondition" id = "searchForm" class="border" method="get">
<%-- 	<form:input path="prodLgu" readonly="readonly" placeholder="prodLgu"/> --%>
<%-- 	<form:input path="prodBuyer" readonly="readonly" placeholder="prodBuyer"/> --%>
<%-- 	<form:input path="prodName" readonly="readonly" placeholder="prodName"/> --%>
	
	<input type="text" name="page" readonly="readonly" placeholder="page" />
</form:form>
<script>
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
}
</script>	
