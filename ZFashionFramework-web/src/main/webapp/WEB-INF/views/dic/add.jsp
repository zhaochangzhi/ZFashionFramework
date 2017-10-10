<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>H+ 后台主题UI框架 - FooTable</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link href="../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet">
	<link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="../css/animate.min.css" rel="stylesheet">
    <link href="../css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
           	<div class="ibox col-sm-12">
           		<div class="ibox-title"></div>
           		<div class="ibox-content">
	                <form id="dicForm" method="get" class="form-horizontal">
		                <div class="form-group">
		                    <label class="col-sm-2 control-label">类&nbsp;&nbsp;型</label>
			
		                    <div class="col-sm-10">
		                        <select id="dicType" name="type" class="form-control m-b" name="account" onchange="javascript:change();">
		                        <c:forEach items="${list}" var="item">
		                             <option value="${item}">${item}</option>
		                         </c:forEach>
		                        </select>
		                    </div>
		                </div>
	                	<div class="hr-line-dashed"></div>
	                	<div class="form-group">
                            <label class="col-sm-2 control-label">字典码</label>
                            <div class="col-sm-10">
                                <p id="dicCode" class="form-control-static"></p>
                                <input type="hidden" id="code" name="code" />
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
		                 <div class="form-group">
		                     <label class="col-sm-2 control-label">字典值</label>
		                     <div class="col-sm-10">
		                         <input id="value" name="value" type="text" class="form-control" />
		                     </div>
		                 </div>
		                 <div class="hr-line-dashed"></div>
	                     <div class="form-group">
	                         <div class="col-sm-4 col-sm-offset-2">
	                             <button class="btn btn-primary save" type="button">保存</button>
	                             <button class="btn btn-white" type="button" onclick="javascript:window.location.href='list'">取消</button>
	                         </div>
	                     </div>
	                 </form>
                 </div>
            </div>
        </div>
    </div>
    <script src="../js/jquery.min.js?v=2.1.4"></script>
    <script src="../js/bootstrap.min.js?v=3.3.6"></script>
    <script src="../js/content.min.js?v=1.0.0"></script>
    <script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
        	
        	getDicCode("usertype");
        	
        	$(".save").click(function() { save(); });
        });
        
        function getDicCode(type) {
        	
        	$.ajax({ 
        		
        		url: "ajaxDicCode", 
        		type : "post",
        		data : {"type" : type},
        		success: function(data){
        			
        			$("#dicCode").html(data);
        			$("#code").val(data);
        	  	}
        	});
        }
        
        function change() {
        	
        	getDicCode($("#dicType").val());
        }
        
        function save() {
        	
			$.ajax({ 
        		
        		url: "save", 
        		async: false,
        		type : "post",
        		data : $("#dicForm").serialize(),
        		success: function(data){
        			
        			if(data == 'success') {
							
						swal({
								title:"保存成功！", 
								type: "success"
							}, 
							function(){
								
								window.location.href="info?type=" + $("#dicType").val();
							}
						);
					} else {
							
						swal("出错了！", "有可能是字典码重复了，请关闭，重新创建", "error");
					}
        	  	}
        	});
        }
       
	</script>
</body>

</html>

