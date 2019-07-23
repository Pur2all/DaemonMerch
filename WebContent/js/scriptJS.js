loginForm = function(){
	$('.registrationForm').hide();
	$('.toggle-button').click(function(){

		$('.errorMessage').hide();

		if($(".registrationForm").is(':hidden'))
			$('.loginForm').slideToggle(250, function(){
			$('.registrationForm').slideToggle(250);
		});

		if($(".loginForm").is(':hidden'))
			$('.registrationForm').slideToggle(250, function(){
			$('.loginForm').slideToggle(250);
		});
	});
};


editProfile = function(){
	$('.edit-button').click(function(){
		$('div.edit').slideToggle(250);
		$('div.profile').slideToggle(250);
	});
}


function updateUser()
{
	var user={
		name:$("#newName").val(),
		surname:$("#newSurname").val(),
		birthday:$("#newBirthday").val(),
		username:$("#newUsername").val(),
		password:$("#newPassword").val()
	};

	return updateUserAsync(JSON.stringify(user));
}
