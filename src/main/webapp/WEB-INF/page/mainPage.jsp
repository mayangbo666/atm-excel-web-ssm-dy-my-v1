<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >

<head>
</head>

<body data-type="index">

    <div class="am-g tpl-g">
        <!-- 头部 -->
        <jsp:include page="common/header.jsp"></jsp:include>
        <!-- 风格切换 -->
        
        <!-- 侧边导航栏 -->
        <jsp:include page="common/left.jsp"></jsp:include>


        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">

            <div class="row-content am-cf">
                <div class="row  am-cf">
                    
                    <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                        <div class="widget widget-primary am-cf">
                            <div class="widget-statistic-header">
                                6222*****196
                            </div>
                            <div class="widget-statistic-body">
                                <div class="widget-statistic-value">
                                    ￥27,294
                                </div>
                                <div class="widget-statistic-description">
                                    
                                </div>
                                <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                            </div>
                        </div>
                    </div>

                    <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                        <div class="widget widget-primary am-cf">
                            <div class="widget-statistic-header">
                                6222*****904
                            </div>
                            <div class="widget-statistic-body">
                                <div class="widget-statistic-value">
                                    ￥27,294
                                </div>
                                <div class="widget-statistic-description">
                                    
                                </div>
                                <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                        <div class="widget widget-primary am-cf">
                            <div class="widget-statistic-header">
                                6222*****369
                            </div>
                            <div class="widget-statistic-body">
                                <div class="widget-statistic-value">
                                    ￥27,294
                                </div>
                                <div class="widget-statistic-description">
                                    
                                </div>
                                <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                            </div>
                        </div>
                    </div>

        

                    
                    
                </div>

               

 <div class="am-u-sm-12 am-u-md-12 am-u-lg-6">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">最近十笔流水</div>
                                <div class="widget-function am-fr">
                                    
                                </div>
                            </div>
                            <div class="widget-body  widget-body-lg am-fr">

                                <table width="100%" class="am-table am-table-compact am-table-bordered am-table-radius am-table-striped tpl-table-black " id="example-r">
                                    <thead>
                                        <tr>
                                            <th>卡号</th>
                                            <th>金额</th>
                                            <th>备注</th>
                                            <th>时间</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="gradeX">
                                            <td>6222*****196</td>
                                            <td>200.00</td>
                                            <td>存款</td>
                                            <td>2017-11-26</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****904</td>
                                            <td>-500.60</td>
                                            <td>取款</td>
                                            <td>2017-11-30</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****369</td>
                                            <td>-12.00</td>
                                            <td>取款</td>
                                            <td>2017-11-30</td>
                                        </tr>

                                        <tr class="gradeX">
                                            <td>6222*****196</td>
                                            <td>100.00</td>
                                            <td>存款</td>
                                            <td>2017-11-30</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****369</td>
                                            <td>200.00</td>
                                            <td>转账收入</td>
                                            <td>2017-11-30</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****369</td>
                                            <td>200.00</td>
                                            <td>转账收入</td>
                                            <td>2017-12-03</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****904</td>
                                            <td>200.00</td>
                                            <td>存款</td>
                                            <td>2017-12-03</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****196</td>
                                            <td>-100.00</td>
                                            <td>转账支出</td>
                                            <td>2017-12-04</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****904</td>
                                            <td>600.00</td>
                                            <td>存款</td>
                                            <td>2017-12-09</td>
                                        </tr>
                                        <tr class="gradeX">
                                            <td>6222*****196</td>
                                            <td>800.00</td>
                                            <td>存款</td>
                                            <td>2017-12-19</td>
                                        </tr>
                                        
                                        <!-- more data -->
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>



            </div>
        </div>
    </div>
    </div>
    <script src="static/js/amazeui.min.js"></script>
    <script src="static/js/amazeui.datatables.min.js"></script>
    <script src="static/js/dataTables.responsive.min.js"></script>
    <script src="static/js/app.js"></script>

</body>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            async: true,           //true异步，false同步(默认异步)
            type: 'post',           //请求类型（get post）
            dataType: 'json',       //预期的服务器响应的数据类型。
            url: '/getUser',  //请求地址
            timeout: '10000',      //超时时间（ms）
            success: function (result) {
                if (result.success) {
                    // alert(result.data.userName);
                    // console.log(result.data.userName);
                    $(".tpl-header-list-user-nick").html(result.data.userName);
                } else {
                    alert("数据异常");
                }
            }
        });
    });
</script>
</html>