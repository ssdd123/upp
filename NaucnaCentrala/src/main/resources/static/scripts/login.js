function buttonLoginClick() {
	email = $('#formLogin-email').val();
	password = $('#formLogin-password').val();
	
	if(!email || !password) {
		toastr["error"]('Fill in all required entry fields!');
		return;
	}
	
	var data = JSON.stringify({
		"email": email,
		"password": password
	});
	$.ajax({
		async: false,
		url: "http://localhost:2345/user/login",
        type: "POST",
        contentType: "application/json",
        data: data,
        success: function () {
        	top.location.href = "home.html";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            toastr["error"](jqXHR.responseText);
        }
    });
}

function logout() {
	$.ajax({
		async: false,
		url: "http://localhost:2345/user/logout",
        type: "GET",
        success: function () {
        	top.location.href = "login.html";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            toastr["error"](jqXHR.responseText);
        }
	})
}