

$(document).ready(function(){
	createCode();
});


var code ; //在全局 定义验证码
/**
 * 生成验证码事件
 * @return
 */
function createCode()
{ 
	code = "";
	var codeLength = 4;//��֤��ĳ���
	var checkCode = document.getElementById("checkCode");
	var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');// ���к�ѡ�����֤����ַ�ȻҲ���������ĵ�

	for(var i = 0;i < codeLength; i++ )
	{
		var charIndex = Math.floor(Math.random()*36);
		code +=selectChar[charIndex];
	}
		if(checkCode)
		{
			checkCode.className="code";
			checkCode.innerHTML = code;
		}
}



//$(document).ready(function(){
//	createCode();
//	txtName();
//	txtEmail();
//	txtPwd();
//	txtPass();
//	txtCode();
//});


$(document).ready(function(){
	var q = document.getElementById("txtUserName");
	$("#userName").focus(function(){
		q.innerHTML = "请输入4到20为字符，首字母不能为数字！ ";
	});
});

$(document).ready(function(){
	var q = document.getElementById("txtUserPwd");
	$("#UserPwd").focus(function(){
		q.innerHTML = "请输入您的登录密码！";
	});
});

/*$(document).ready(function(){
	var q = document.getElementById("txtUserEmail");
	$("#email").focus(function(){
		q.innerHTML = "���������õ����䣬����Ϊ�ܱ����䣡";
	});
});
*/

$(document).ready(function(){
	var q = document.getElementById("txtUserPass");
	$("#UserPass").focus(function(){
		q.innerHTML = "请输入您的登陆密码";
	});
});

/*$(document).ready(function(){
	var q = document.getElementById("txtCode");
	$("#inputCode").focus(function(){
		q.innerHTML = "��������֤�룡";
	});
});*/



/**
 * �û�����֤
 */
function txtName(){
	//alert(222);
	var bool=true;
	var regCode = /^[A-Z,a-z]+\w{3,19}$/;
	var q = document.getElementById("txtUserName");
	//var name = document.getElementById("UserName").value;
	var name = $("#userName").val();
	 if(!name) {
		 q.innerHTML = "<p class='code1'>用户名不能为空!</p>";
			bool=false;
		 } else if(regCode.test(name) == false){
			q.innerHTML = "<p class='code1'>请输入4到20位字符，首位不能为数字!</p>";
			bool=false;
		}else{
			
			
			
			$.ajax({
				cache: false,
				async: true,
				type: "POST",
			dataType: "json",
				data: {userName: name},
				url: "tbuser_existUserWithUserName.action",
			success: function(flage) {
				//alert(333);
					if(flage) {
						q.innerHTML = "<p class='code2'>ͨ通过</p>";
						bool = false;				
					}else{
						
						q.innerHTML = "<p class='code1'>账户不存在!</p>";
					}
				}
	      });
			
			
		
		}
		return bool;
}

/**
 * �û�������֤
 *//*
function txtEmail(){
	    var bool=true;
		var regCode =  /^([a-zA-Z0-9]+.*[a-zA-Z0-9]*)+@{1}(163|126|sina|google|qq)\.com$/;
		var q = document.getElementById("txtUserEmail");
		//var name = document.getElementById("UserEmail").value;
		var name = $("#email").val();
		if(!name) {
			 q.innerHTML = "<p class='code1'>���䲻��Ϊ��!</p>";
				bool=false;
			 } else if(regCode.test(name) == false){
			q.innerHTML = "<p class='code1'>��������ȷ�����䣡</p>";
			bool=false;
		}else{
			
			
			$.ajax({
				cache: false,
				async: true,
				type: "POST",
			dataType: "json",
				data: {email: name},
				url: "user!CheckEmail.action",
			success: function(flag) {
					if(flag) {
						q.innerHTML = "<p class='code1'>Email�ѱ�ע�ᣡ!</p>";
						bool = false;				
					}else{
						
						q.innerHTML = "<p class='code2'>ͨ��</p>";
					}
				}
	      });
			
		}
		
		return bool;
}

*//**
 * �û�������֤
 *//*
function txtPwd(){
		var regCode =  /^(\w|\W){6,16}$/;
		var q = document.getElementById("txtUserPwd");
		var name = document.getElementById("UserPwd").value;
		if(!name) {
			 q.innerHTML = "<p class='code1'>���벻��Ϊ��!</p>";
				bool=false;
			 } else if(regCode.test(name) == false){
			q.innerHTML = "<p class='code1'>������6-16λ���ַ����ֻ��ţ�</p>";
			return false;
		}else{
			q.innerHTML = "<p class='code2'>ͨ��</p>";
			return true;
		}
}

*//**
 * �û������ٴ���֤
 *//*
function txtPass(){
		var q = document.getElementById("txtUserPass");
		var pass = document.getElementById("UserPass").value;
		var pwd = document.getElementById("UserPwd").value;
		
		 if(pass == "" || pass == null){
			q.innerHTML = "<p class='code1'>���ٴ�����6-16λ���룡</p>";
			return false;
		}else{
			if(pass == pwd){
				q.innerHTML = "<p class='code2'>ͨ��</p>";
				return true;
			}else{
				q.innerHTML = "<p class='code1'>�����������һ�£�</p>";
				return false;
			}
		}
}*/

/**
 * ��֤����֤�¼�
 */
function txtCode(){
	var q = document.getElementById("txtCode");
	var q1 = document.getElementById("inputCode").value;
		if(q1 != code){
			q.innerHTML = "<p class='code1'>请输入正确的验证码！</p>";
			return false;
		}else{
			q.innerHTML = "<p class='code2'>ͨ通过</p>";
			return true;
		}
}

function xyj()
{
	var q = document.getElementById("txtCheck");
	var box = document.getElementById("check");
	if(box.checked == true){
		if(txtName() == false | txtEmail() == false | txtPwd() == false | txtPass() == false | txtCode() == false){
			return false;
		}else{
			return true;
		}
	}else{
		q.innerHTML = "<p class='code3'>请仔细阅读XX协议！</p>";
		return false;
	}
 }


/**
 * A标签不跳转事件
 */
$(function(){
	$("#qq").click(function(){
		//return false;
	});
});

