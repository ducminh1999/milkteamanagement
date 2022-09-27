document.forms.editForm.addEventListener("submit", function(event) {
	event.preventDefault();
	var firstNameElement = document.getElementById("firstName");
	var lastNameElment = document.getElementById("lastName");
	var phoneElement = document.getElementById("phone");
	var descriptionElement = document.getElementById("description");

	var status = false;
	var message = "Please fill all mandatory fields";
	setBorderColor(firstNameElement);
	setBorderColor(lastNameElment);
	setBorderColor(phoneElement);

	// count number of input tags
	var numberOfInput = document.getElementsByTagName("input").length;
	var countNumberValidInput = 0;
	for (var j = 0; j < numberOfInput; j++) {
		// check all input are valid
		if (
			document.getElementsByTagName("input")[j].style.borderColor == "green"
		) {
			countNumberValidInput++;
		}
	}

	// if all input are valid, set ok = true
	if (countNumberValidInput == numberOfInput - 2) {
		if (!validateFirstName(firstNameElement.value)) {
			message =
				"First nam must be at least 3 characters and maximum 30 characters";
			firstNameElement.style.borderColor = "red";
		} else if (!validateLastName(lastNameElment.value)) {
			message =
				"Last name must be at least 3 characters and maximum 30 characters";
			lastNameElment.style.borderColor = "red";
			firstNameElement.style.borderColor = "green";
			phone.length < len.min || phone.length > len.max
		} else if (!validatePhone(phoneElement.value)) {
			message = "Phone invalid";
			phoneElement.style.borderColor = "red";
			firstNameElement.style.borderColor = "green";
			lastNameElment.style.borderColor = "green";
		} else if (!validateDescription(descriptionElement.value)) {
			message = "Description must have a maximum of 200 characters";
			descriptionElement.style.borderColor = "red";
			firstNameElement.style.borderColor = "green";
			lastNameElment.style.borderColor = "green";
			phoneElement.style.borderColor = "green";
		} else {
			status = true;
		}
	}

	// if  status -> submit
	if (status) {
		document.getElementById("editForm").submit();
		alert("Update profile successful");
	} else {
		document.getElementById("error").innerHTML = message;
	}
})