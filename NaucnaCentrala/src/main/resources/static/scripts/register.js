function buttonRegisterClick() {
	email = $('#formRegister-email').val();
	city = $('#formRegister-city').val();
	country = $('#formRegister-country').val();
	name = $('#formRegister-name').val();
	password = $('#formRegister-password').val();
	
	var data = JSON.stringify({
		"email":email,
		"city":city,
		"country":country,
		"name":name,
		"password":password
	});
	
	$.ajax({
		async: false,
		url: "http://localhost:2345/user/register",
        type: "POST",
        contentType:"application/json",
        data : data,
        success: function (data1) {
        	top.location.href = "login.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Incorrect data.');
        } 
    });
}