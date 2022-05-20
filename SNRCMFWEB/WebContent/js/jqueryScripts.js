$(document).ready(function()
{
	$('#sidebarCollapse').on('click', function()
	{
		$('#sidebar').toggleClass('active');
		$(this).toggleClass('active');
	});
	
	$('#idFormAntSistema').on('keyup keypress', function(e) 
	{
		var keyCode = e.keyCode || e.which;
		
	  	if (keyCode === 13) 
	  	{ 
	    	e.preventDefault();
	    	return false;
	  	}
	});
});