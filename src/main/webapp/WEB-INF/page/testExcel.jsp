<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>导入Excel数据</title>
</head>
<body>
    <h1>这是用户信息页</h1>

    <form action="/upload" method="post" enctype="multipart/form-data">
        <input name="file" type="file"/>
        <input type="submit"/>
    </form>

    <a id="downExcel" href="" onclick="down()">下载excel数据</a>

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"> </script>

<script type="application/javascript">
    function down() {
        $("#downExcel").attr('href', '/downUserExcel');
    }
</script>
</body>
</html>