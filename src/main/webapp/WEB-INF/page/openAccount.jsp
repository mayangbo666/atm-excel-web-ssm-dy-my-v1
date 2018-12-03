<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
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
                    
                    <div class="row">

                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">银行卡开户</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form">
                                    <div class="am-form-group">
                                        <label for="cardNum" class="am-u-sm-3 am-form-label">添加卡号 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="cardNum" placeholder="添加卡号">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="pwd" class="am-u-sm-3 am-form-label">添加密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="pwd" placeholder="添加密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="openAccount()">开户</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

        

                    
                    
                </div>

               

 
                </div>



            </div>
        </div>
    </div>
    </div>
    <script src="/static/js/amazeui.min.js"></script>
    <script src="/static/js/amazeui.datatables.min.js"></script>
    <script src="/static/js/dataTables.responsive.min.js"></script>
    <script src="/static/js/app.js"></script>

</body>
<script>
    // $(function () {
    //
    // })

    function openAccount() {
        $.ajax({
            async: 'true',
            type: 'post',
            dataType: 'json',
            url: '/openAccount',
            data: {
                cardNum: $("#cardNum").val(),
                password: $("#pwd").val()
            },
            success: function (dataResult) {
                if (!dataResult.success){
                    alert(dataResult.message)
                    return false
                }
                alert('开户成功')
                window.location.href = '/toDeposit'
            }
            // ,
            // error : function(XMLHttpResponse) {
            // }
        })
    }
</script>
</html>