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