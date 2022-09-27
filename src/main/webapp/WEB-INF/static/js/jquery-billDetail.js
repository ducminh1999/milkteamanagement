
var form =document.querySelector("#detailSubmit");
form.billId;
form.deliverId;
form.addEventListener("submit", function(event) {
	event.preventDefault();
	let deliverId = document.getElementById("deliverId").value;
	var status = false;
	var message = "Please fill Deliver code";

	if (deliverId != "") {
		status = true;
	}

	// if  status -> submit
	if (status) {
		//document.getElementById("detailSubmit").submit();
		HTMLFormElement.prototype.submit.call(form);
	} else {
		document.getElementById("error").innerHTML = message;
	}
});

/*document.forms.detailSubmit.addEventListener("submit", function(event) {
	event.preventDefault();
	let deliverId = document.getElementById("deliverId").value;
	var status = false;
	var message = "Please fill Deliver code";

	if (deliverId != "") {
		status = true;
	}

	// if  status -> submit
	if (status) {
		document.getElementById("detailSubmit").submit();
		//document.forms["billDetail"].submit();
	} else {
		document.getElementById("error").innerHTML = message;
	}
});*/