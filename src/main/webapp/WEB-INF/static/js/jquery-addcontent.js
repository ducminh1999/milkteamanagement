document.forms.addForm.addEventListener("submit", function(event) {
	event.preventDefault();
	var titleElement = document.getElementById("title");
	var briefElement = document.getElementById("brief");
	var contentElement = document.getElementById("content2");
	var status = false;
	var message = "Please fill all mandatory fields";
	setBorderColor(titleElement);
	setBorderColor(briefElement);
	setBorderColor(contentElement);

	// if all input are valid, set ok = true
	if (
		titleElement.style.borderColor == "green" &&
		briefElement.style.borderColor == "green" &&
		contentElement.style.borderColor == "green"
	) {
		if (!validateTitle(titleElement.value)) {
			message =
				"Title must be at least 10 characters and maximum 200 characters";
			titleElement.style.borderColor = "red";
		} else if (!validateBrief(briefElement.value)) {
			message =
				"Brief must be at least 30 characters and maximum 150 characters";
			briefElement.style.borderColor = "red";
			titleElement.style.borderColor = "green";
		} else if (!validateContent(contentElement.value)) {
			message =
				"Content must be at least 50 characters and maximum 1000 characters";
			contentElement.style.borderColor = "red";
			titleElement.style.borderColor = "green";
			briefElement.style.borderColor = "green";
		} else {
			status = true;
		}
	}

	// if  status -> submit
	if (status) {
		document.getElementById("addForm").submit();
	} else {
		document.getElementById("error").innerHTML = message;
	}
});
