<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Hello World!</h1>
<hr>
<h5>test1: 스프링에서 단일 오브젝트로 그냥받게 하기</h5>
<form action='test1'method='post'>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<button type='submit'>submit</button>
</form>
<hr>
<h4>test2 List를 이용해서 메소드 시그니쳐 이용. 스프링에서 리스트로 받아주는지 실험</h4>
<form action='test2'method='post'>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<button type='submit'>submit</button>
</form>
<h4>test3 서블릿 스펙을 이용(작동)</h4>
<form action='test3'method='post'>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<button type='submit'>submit</button>
</form>
<h4>test4 스프링이 어레이로 받도록 해보기</h4>
<form action='test4'method='post'>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<label for='aa'><input id='aa'type='text'name='attr'></label><br>
	<button type='submit'>submit</button>
</form>
</body>
</html>