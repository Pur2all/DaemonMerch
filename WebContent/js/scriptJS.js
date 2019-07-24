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


addProductDynamic = function(){
	addProductForm();
	retrieveArtists();
	selectUpdate();
};

addProductForm = function(){
	$('#shirt-specific').hide();
};

selectUpdate = function() {
	
	$('div.right > select.product-type').on('change', function() {
		
		if(this.value == "shirt") {
			$('#shirt-specific').slideDown();
			$('#patch-specific').slideUp();
		};
		
		if(this.value == "other") {
			$('#shirt-specific').slideUp();
			$('#patch-specific').slideUp();
		};
		
		if(this.value == "patch") {
			$('#patch-specific').slideDown();
			$('#shirt-specific').slideUp();
		};

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