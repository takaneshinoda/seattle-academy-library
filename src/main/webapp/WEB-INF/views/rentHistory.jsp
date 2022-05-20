<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>書籍の追加｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="resources/js/thumbnail.js"></script>
</head>
<body class="wrapper">
    <header>
        <div class="left">
            <img class="mark" src="resources/img/logo.png" />       
            <div class="logo">Seattle Library</div>
        </div>
        <div class="right">
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class="menu">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/">ログアウト</a></li>
            </ul>
        </div>
    </header>
    <main>
        <div class="container">
            <table style="width: 60%" class="table table-bordered mx-auto">
                <thead>
                    <tr class="table-info">
                        <th style="width: 30%" scope="col">書籍名</th>
                        <th style="width: 15%" scope="col">貸出日</th>
                        <th style="width: 15%" scope="col">返却日</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="rentalbookInfo" items="${rentalbookList}">
                        <input type="hidden" name="bookId" value="${rentalbookInfo.bookId}">
                        <tr>
                            <td><form method="get" action="<%=request.getContextPath()%>/rentdate">
                                    <input type="hidden" name="bookId" value="${rentalbookInfo.bookId}"><a href="javascript:void(0)" onclick="this.parentNode.submit();"> ${rentalbookInfo.title}</a>
                                </form></td>
                            <td>${rentalbookInfo.lendDate}</td>
                            <td>${rentalbookInfo.returnDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>