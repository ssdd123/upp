$(document).ready(function() {
	$.ajax({
		async: false,
		url: "http://localhost:2345/user/getLoggedIn",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	if(data) {
        		render(data.role);
        	} else {
        		top.location.href = "login.html";
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	})
});