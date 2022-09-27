document.forms.registerForm.addEventListener("submit", function(event) {
	event.preventDefault();
	var userNameElement = document.getElementById("userName");
	var emailElement = document.getElementById("email");
	var passwordElement = document.getElementById("password");
	var repasswordElement = document.getElementById("rePassword");

	var status = false;
	var message = "Please fill all mandatory fields";
	setBorderColor(userNameElement);
	setBorderColor(emailElement);
	setBorderColor(passwordElement);
	setBorderColor(repasswordElement);

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
	if (countNumberValidInput == numberOfInput) {
		var email = emailElement.value;
		var minLen = 5;
		if (!validateUserName(userNameElement.value)) {
			message =
				"User name must be at least 3 characters and maximum 30 characters";
			userNameElement.style.borderColor = "red";
		} else if (email.length < minLen) {
			message = "Email must be at least 5 characters";
			emailElement.style.borderColor = "red";
			userNameElement.style.borderColor = "green";
		} else if (!validateEmail(email)) {
			message = "Email invalidate";
			emailElement.style.borderColor = "red";
			userNameElement.style.borderColor = "green";
		} else if (!validatePassword(passwordElement.value)) {
			message =
				"Password must be at least 8 characters and maximum 30 characters";
			passwordElement.style.borderColor = "red";
			userNameElement.style.borderColor = "green";
			emailElement.style.borderColor = "green";
		} else if (passwordElement.value != repasswordElement.value) {
			message = "Confirm password is not match with password";
			repasswordElement.style.borderColor = "red";
			userNameElement.style.borderColor = "green";
			passwordElement.style.borderColor = "green";
			emailElement.style.borderColor = "green";
		} else {
			message = "";
			status = true;
		}
	}

	if (status) {
		document.getElementById("registerForm").submit();
	} else {
		document.getElementById("error").innerHTML = message;
	}
})