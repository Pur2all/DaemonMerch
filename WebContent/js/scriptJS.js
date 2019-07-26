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
			$('#insertForm').attr('action', 'admin/InsertTop');
			$('#shirt-specific').slideDown();
			$('#patch-specific').slideUp();
		};

		if(this.value == "other") {
			$('#insertForm').attr('action', 'admin/InsertProduct');
			$('#shirt-specific').slideUp();
			$('#patch-specific').slideUp();
		};

		if(this.value == "patch") {
			$('#insertForm').attr('action', 'admin/InsertPatch');
			$('#patch-specific').slideDown();
			$('#shirt-specific').slideUp();
		};

	});
};


userProfileScript = function() {
	editProfile();
	dynamicForm();
	dynamicFormAdmin();
}

editProfile = function(){
	$('.edit-button').click(function(){
		$('div.edit').slideToggle(250);
		$('div.profile').slideToggle(250);
	});
}

dynamicForm = function() {
	$('input#creditCards').click(function(){
		$('.billingAddressForm').slideUp();
		$('.creditCardForm').slideDown();
	});

	$('input#billingAddress').click(function(){
		$('.billingAddressForm').slideDown();
		$('.creditCardForm').slideUp();
	});

	$('input.confirmButton').click(function(){
		$('.billingAddressForm').slideUp();
		$('.creditCardForm').slideUp();
	});

	$('input.cancelButton').click(function(){
		$('.billingAddressForm').slideUp();
		$('.creditCardForm').slideUp();
	});

}


dynamicFormAdmin = function() {
	$('input#artistAdd').click(function(){
		$('div#productForm').slideUp();
		$('div#artistForm').slideDown();
	});

	$('input#productAdd').click(function(){
		$('div#productForm').slideDown();
		$('div#artistForm').slideUp();
	});

	$('input#submit-button').click(function(){
		$('div#productForm').slideUp();
		$('div#artistForm').slideUp();
	});

	$('input#cancel-button').click(function(){
		$('div#productForm').slideUp();
		$('div#artistForm').slideUp();
	});

}


function updateUser()
{
	var user={
		name:$("#newName").val(),
		surname:$("#newSurname").val(),
		birthday:$("#newBirthday").val(),
		username:$("#newUsername").val(),
		password:$("#newPassword").val(),
		type:"REGISTERED_USER",
		id:$("#id").val()
	};

	return updateUserAsync(JSON.stringify(user));
}
