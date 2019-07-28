loginForm = function(){
	validateRegistrationForm();
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

	$('.product-type').on('change', function() {

		if(this.value == "shirt") {
			$('#insertForm').attr('action', 'admin/InsertTop');
			$('#shirt-specific').slideDown();
			$('#patch-specific').hide();
		};

		if(this.value == "other") {
			$('#insertForm').attr('action', 'admin/InsertProduct');
			$('#shirt-specific').slideUp();
			$('#patch-specific').slideUp();
		};

		if(this.value == "patch") {
			$('#insertForm').attr('action', 'admin/InsertPatch');
			$('#patch-specific').slideDown();
			$('#shirt-specific').hide();
		};

	});
};


userProfileScript = function() {
	editProfile();
	dynamicForm();
	dynamicFormAdmin();
	addProductForm();
	retrieveArtists();
	selectUpdate();
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

	$('input#cancel-button1').click(function(){
		$('div#productForm').slideUp();
		$('div#artistForm').slideUp();
	});

	$('input#cancel-button').click(function(){
		$('div#productForm').slideUp();
		$('div#artistForm').slideUp();
	});

}


//Function to update user info
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


//Function to update the user info in UserProfile
function updateUserInfo(user)
{
	$("#username").html(user.username);
	$("#firstname").html(user.name);
	$("#surname").html(user.surname);
	$("birthday").html(user.birthday);
}


//Function to update product
function updateProduct()
{
	var product={
		name:$("#newName").val(),
		description:$("#newDescription").val(),
		remaining:$("#newRemaining").val(),
		price:$("#newPrice").val(),
		tag:$("#newTag").val(),
		id:$("#id").val()
	};

	return updateProductAsync(JSON.stringify(product));
}


//Function to update the product info in ProductPage
function updateProductInfo(product)
{
	$("#name").html(product.name);
	$("#description").html(product.description);
	$("#remaining").html(product.remaining);
	$("#price").html(product.price);
	$("#tag").html(product.tag);
}


//Function to update patch
function updatePatch()
{
	var patch={
		name:$("#newName").val(),
		description:$("#newDescription").val(),
		remaining:$("#newRemaining").val(),
		price:$("#newPrice").val(),
		tag:$("#newTag").val(),
		id:$("#id").val(),
		measures:$("#newMeasures").val(),
		type:$("#newPatchType").val(),
		material:$("#newMaterial").val()
	};

	return updatePatchAsync(JSON.stringify(patch));
}


//Function to update the patch info in ProductPage
function updatePatchInfo(patch)
{
	$("#name").html(patch.name);
	$("#description").html(patch.description);
	$("#remaining").html(patch.remaining);
	$("#price").html(patch.price);
	$("#tag").html(patch.tag);
	$("#measures").html(patch.measures);
	$("#patchType").html(patch.type);
	$("#material").html(patch.material);
}


//Function to update top
function updateTop()
{
	var top={
		name:$("#newName").val(),
		description:$("#newDescription").val(),
		remaining:$("#newRemaining").val(),
		price:$("#newPrice").val(),
		tag:$("#newTag").val(),
		id:$("#id").val(),
		size:$("#newSize").val(),
		type:$("#newPrintType").val(),
		category:$("#newCategory").val()
	};

	return updateTopAsync(JSON.stringify(top));
}


//Function to update the top info in ProductPage
function updateTopInfo(top)
{
	$("#name").html(top.name);
	$("#description").html(top.description);
	$("#remaining").html(top.remaining);
	$("#price").html(top.price);
	$("#tag").html(top.tag);
	$("#size").html(top.size);
	$("#printType").html(top.type);
	$("#category").html(top.category);
}


//Function to validate the registration form
function validateRegistrationForm()
{
	$("#regName").on('input', function()
	{
		if(!$("#regName").val().match(/^[A-Za-z]{3,10}$/))
			$("#regName").css('border', '1px solid red');
		else
			$("#regName").css('border', '1px solid green');
	});

	$("#regUsername").on('input', function()
	{
		if(!$("#regUsername").val().match(/^[^_][A-Za-z0-9_]{5,10}$/))
			$("#regUsername").css('border', '1px solid red');
		else
			{
				$("#regUsername").css('border', '1px solid green');
				checkUsername($("#regUsername").val());
			}
	});

	$("#regPassword").on('input', function()
	{
		if(!$("#regPassword").val().match(/^[A-Za-z0-9_@!?]{5,10}$/))
			$("#regPassword").css('border', '1px solid red');
		else
			$("#regPassword").css('border', '1px solid green');
	});
}

//Function for check if some input is null
function checkForm()
{
	var value=true;

	$("#registrationForm").children().each(function(index, el)
	{
		if((el.nodeName=="INPUT" && el.value=="") || el.getAttribute('style')=='border: 1px solid red;')
		{
			value=false;
			return;
		}
	});

	return value;
}
