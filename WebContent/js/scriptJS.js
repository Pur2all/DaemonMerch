$(document).ready(function(){
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
});