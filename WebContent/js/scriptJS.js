removeFromCart = function(i, total, price) {
	$('#product' + i).slideToggle(250, function(){
		$('#product' + i).remove();
	});
	
	$('#totalPrice').text("Total: " + (total - price).toFixed() + "0 EUR");
}

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

removeFromCart = function(i, total, price) {
	$('#product' + i).slideToggle(250, function(){
		$('#product' + i).remove();
	});
	
	$('#totalPrice').text("Total: " + (total - price).toFixed() + "0 EUR");
}

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


carouselSlick = function() {
	$('.slideshow-container').slick({
		infinite: true,
		slidesToShow: 3,
		slidesToScroll: 3
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

//Function to update the user info in UserProfile
function updateInfo(user)
{
	$("#username").html(user.username);
	$("#firstname").html(user.name);
	$("#surname").html(user.surname);
	$("birthday").html(user.birthday)
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

//Function to change currency
function changeCurrency(price)
{
	if($("#selectCurrency").val()=="USD")
		$(price).html($(price).val()*euroToDollar() + " USD");
	else
		$(price).html($(price).val()*dollarToEuro() + " EUR");
}


//Function to change currency in cart
function changeCurrencyInCart()
{
	$(".price").each(function(index, el)
	{
		$(el).html(changeCurrency(el));
	});
	$("#total").html(changeCurrency("#total"));
}