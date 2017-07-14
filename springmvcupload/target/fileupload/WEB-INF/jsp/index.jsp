<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>上传图片</title>
</head>
<body>
<form action="upload.do" method="post" enctype="multipart/form-data">
<input type="file" name="file" /> <input type="submit" value="Submit" /></form>
<a href="test.do">test</a>
<br> list all files: <a href="list.do"><b>list</b></a>
</body>
</html>
