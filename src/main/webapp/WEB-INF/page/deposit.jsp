<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

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
                                <div class="widget-title am-fl">存款操作</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form">


                                <div class="am-form-group">
                                        <label for="user-phone" class="am-u-sm-3 am-form-label">银行卡 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected="{searchBox: 1}" style="display: none;" id="cardDiv">

                                            </select>

                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="pwd" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="pwd" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="amount" class="am-u-sm-3 am-form-label">金额 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="amount" placeholder="请输入存款金额">
                                            <small></small>
                                        </div>
                                    </div>


                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="deposit();">存款</button>
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



                    <div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    <div class="am-modal-bd">
      操作成功
    </div>
  </div>
</div>


    <script src="/static/js/amazeui.min.js"></script>
    <script src="/static/js/amazeui.datatables.min.js"></script>
    <script src="/static/js/dataTables.responsive.min.js"></script>
    <script src="/static/js/app.js"></script>

</body>

<script>
  $(document).ready(function() {

      loadBankCard();
  });

  function loadBankCard() {
      $.ajax({
          type : "POST",
          dataType : "json",
          url : '/listBankCard',
          data : {
          },
          success : function(dataResult) {
              if (!dataResult.success) {
                alert(dataResult.message);
                return false;
              }

              var cards = dataResult.data;
              var msg = '<option value="-1">请选择银行卡</option>';
              for (var i=0; i<cards.length;i++) {
                  msg += '<option value="'+cards[i].cardNum+'">'+cards[i].cardNum+'</option>';
              }

              $('#cardDiv').html(msg);
          },
          error : function(XMLHttpResponse) {
          }
      });
  }

  function deposit() {
      $.ajax({
          type : "POST",
          dataType : "json",
          url : '/deposit',
          data : {
              cardNum : $('#cardDiv').val(),
              amount : $('#amount').val(),
              password : $('#pwd').val()
          },
          success : function(dataResult) {
              if (!dataResult.success) {
                  alert(dataResult.message);
                  return false;
              }

              alert('存款成功!');
          },
          error : function(XMLHttpResponse) {
          }
      });
  }
</script>

</html>