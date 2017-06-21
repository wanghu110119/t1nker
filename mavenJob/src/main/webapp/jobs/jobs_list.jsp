<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<base href="${base}" />
<link rel="shortcut icon" href="//favicon.ico" />
<link href="styles/common.css" rel="stylesheet" type="text/css" />
<link href="styles/jobs.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<script type="text/javascript" src="jquery-3.2.1.js"></script>
	<script type="text/javascript">
		function goPage(p) {
			if (p<1 || p>${pageBean.pages}) {
				return;
			}
			$("#page").val(p);
			location.href = "${pageContext.request.contextPath}/joblist.do?pageCode1="
					+ p;
		}
	</script>
	<jsp:include page="../header.jsp" />
	<!--导航 -->
	<div class="floatnav">
		<jsp:include page="../nav.jsp" />
	</div>
	<!--导航end -->
	<div class="page_location link_bk">
		当前位置：<a href="//">首页</a>&nbsp;>>&nbsp;<a href="">招聘信息</a>
	</div>
	<div class="jobs-nav">
		<a href="jobs.jsp" class="select">全能搜索</a> <a href="jobtag-search.jsp">按标签搜索</a>
		<div class="clear"></div>
	</div>
	<div class="jobsearchbox" id="jobsearchbox">
		<div class="loading"></div>
	</div>
	<div class="jobsbox">
		<div class="tit">
			<div class="left">
				<h2>推荐职位</h2>
				<ul class="link_bk">
					<c:forEach var="list" items="${joblist }">
						<li><a href="" target="_blank">${list.comId }</a> <a href=""
							target="_blank" class="jobsname">${list.name }</a></li>
					</c:forEach>
				</ul>
				<br> <br>
			</div>
			<div class='pages_bar'>
				<a href='javascript:goPage(1)' id="first">首页</a> <a
					href='javascript:goPage(${pageBean.pageNum-1})' id="first">上一页</a>
				<%-- <c:set var="begin" value="1" />
				<c:set var="end" value="10" />
				<c:if test="${page.pageCode > 6 }">
					<c:set var="begin" value="${pageBean.pageCode-5}" />
					<c:set var="end" value="${pageBean.pageCode+4 }" />
				</c:if>
				<c:choose>
					<c:when test="${pageBean.totalPage <=10 }">
						<c:set var="begin" value="1" />
						<c:set var="end" value="${pageBean.totalPage}" />
					</c:when>
					<c:otherwise>
						<c:if test="${pageBean.pageCode >= pageBean.totalPage -4 }">
							<c:set var="begin" value="${pageBean.totalPage -9 }" />
							<c:set var="end" value="${pageBean.totalPage }" />
						</c:if>
					</c:otherwise>
				</c:choose> --%>
				<c:forEach begin="${pageBean.navigateFirstPage }" end="${pageBean.navigateLastPage }" var="p">
					<a href="javascript:goPage('${p}')">${p}</a>
				</c:forEach>
				<input type="hidden" name="pageCode" id="page" value="1" />
				 <a href='javascript:goPage(${pageBean.pageNum+1})' id="first">下一页</a>
				<a href='javascript:goPage(${pageBean.pages})' id="last">尾页</a>
				<span>当前第${pageBean.pageNum}页/共${pageBean.pages}页</span>
			</div>
		</div>
		<div class="right link_bk">
			<a href="" target="_blank">我也要出现在这里！</a>
		</div>
		<div class="clear"></div>
	</div>
	<ul class="link_bk">
	</ul>
	<div class="clear"></div>
	</div>
	<div class="jobsbox">
		<div class="tit">
			<div class="left">
				<h2>最新职位</h2>
			</div>
			<div class="right link_bk">
				<a href="" target="_blank">更多</a>
			</div>
			<div class="clear"></div>
		</div>
		<ul class="link_bk">
			<c:forEach var="list" items="${joblist2 }">
				<li><a href="" target="_blank">${list.comId }</a> <a href=""
					target="_blank" class="jobsname">${list.name }</a></li>
			</c:forEach>
		</ul>
		<div class="clear"></div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>
