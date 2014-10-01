<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dungeons &amp; Dragons 5th Edtion Character Sheet</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap-theme.min.css">
<script src="/resources/js/bootstrap.min.js"></script>
</head>
<body>
<c:forEach var="character" items="${characters }">
<h3><a href="/character/${character.characterId}">${character.name }</a></h3> <h4> ${character.characterClass }, ${character.level } Lv.</h4>
</c:forEach>

</body>
</html>