function errorMessage(msg) {
	$("#error").html(msg);
	$("#error").show();
}

function insertionOk() {
	$("#insertionOk").html("Insertion réussie !");
	$("#insertionOk").show();
}

function checkComputerName(name) {
	if (name.trim() == "") {
		errorMessage("Veuillez entrer un nom de PC svp");
		return 0;
	}
	return 1;
}

function checkDate(introduced, discontinued) {
	var dateLimit = new Date("1970-01-01");
	if(introduced.trim() != "" && new Date(introduced).getTime()<dateLimit.getTime()){
		errorMessage("Impossible d'avoir une date avant 1970");
		return 0;
	}
	if(discontinued.trim() != "" && new Date(discontinued).getTime()<dateLimit.getTime()){
		errorMessage("Impossible d'avoir une date avant 1970");
		return 0;
	}
	if (introduced.trim() != "" && discontinued.trim() != "") {
		if ((new Date(introduced).getTime() > new Date(discontinued).getTime())) {
			errorMessage("Impossible d'avoir une date d'introduction postérieur à celle de destitution");
			return 0;
		}
	}
	return 1;
}

(function($) {
	$("#submit").click(function() {
		var name = $("#computerName").val();
		var introduced = $("#introduced").val();
		var discontinued = $("#discontinued").val();

		if (!checkComputerName(name) || !checkDate(introduced, discontinued))
			return false;
		else 
			insertionOk()
	});
}(jQuery));