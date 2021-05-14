$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ------------------------------------------------------------------------------------------------------

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts----------------------------------------------------------------------------------------
	
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-----------------------------------------------------------------------------------------
	
	var status = validatePaymentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//If valid--------------------------------------------------------------------------------------------------
	
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url: "PaymentAPI",
		type: type,
		data: $("#formPayment").serialize(),
		dataType: "text",
		complete: function(response, status) {
			onPaymentSaveComplete(response.responseText, status);
		}
	});
});


// UPDATE----------------------------------------------------------------------------------------------------------

$(document).on("click", ".btnUpdate", function(event) {
	$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
	$("#pName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#quantity").val($(this).closest("tr").find('td:eq(1)').text());
	$("#cName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#date").val($(this).closest("tr").find('td:eq(3)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(4)').text());
});

//Delete------------------------------------------------------------------------------------------------------------

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url: "PaymentAPI",
		type: "DELETE",
		data: "pID=" + $(this).data("paymentid"),
		dataType: "text",
		complete: function(response, status) {
			onPaymentDeleteComplete(response.responseText, status);
		}
	});
});

// CLIENT-MODEL-------------------------------------------------------------------------------------------------------

function validatePaymentForm() {

	// Product Name
	if ($("#pName").val().trim() == "") {
		return "!!! Insert Product Name !!!";
	}
	// Quantity
	if ($("#quantity").val().trim() == "") {
		return "!!! Insert Quantity !!! ";
	}
	var quantity = $("#quantity").val().trim();
	if(!$.isNumeric(quantity)){
	   return "Please Enter Numbers for Quantity";
	}

	// Customer Name
	if ($("#cName").val().trim() == "") {
		return "!!! Insert Customer Name !!! ";
	}

	// Date
	if ($("#date").val().trim() == "") {
		return "!!! Insert Date !!! ";
	}
	// Amount
	if ($("#amount").val().trim() == "") {
		return "!!! Insert Amount !!! ";
	}
	var amount = $("#amount").val().trim();
	if(!$.isNumeric(amount)){
	   return "Please Enter Numbers for Amount";
	}
	return true;

}

function onPaymentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Payment Saved !!! ");
			$("#alertSuccess").show();
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while Saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while Saving..");
		$("#alertError").show();
	}

	$("#hidPaymentIDSave").val("");
	$("#formPayment")[0].reset();
}

function onPaymentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Payment Deleted !!! ");
			$("#alertSuccess").show();
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while Deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while Deleting..");
		$("#alertError").show();
	}
}