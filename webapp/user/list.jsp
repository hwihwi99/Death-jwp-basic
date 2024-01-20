<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"/>

<!DOCTYPE html>
<html lang="kr">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>SLiPP Java Web Programming</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link href="../css/styles.css" rel="stylesheet">
    </head>
    <body>
        <div class="container" id="main">
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>#</th> <th>사용자 아이디</th> <th>이름</th> <th>이메일</th><th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="u" varStatus="status">
                            <tr>
                                <th scope="row">${status.count}</th>
                                <td>${u.userId}</td>
                                <td>${u.name}</td>
                                <td>${u.email}</td>
                                <c:choose>
                                    <c:when test="${user.getUserId() == u.userId}">
                                        <td><a href="/user/update?userId=${user.userId}" class="btn btn-success" role="button">수정</a></td>
                                    </c:when>
                                </c:choose>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- script references -->
        <script src="../js/jquery-2.2.0.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/scripts.js"></script>
    </body>
</html>
