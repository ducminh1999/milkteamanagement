document.addEventListener("load", load());
function load() {
	document.getElementById("loading").innerHTML = "Loading";
	setTimeout(function() {
		$("#loading").css("display", "none");
		$("#content1").css("display", "block");
	}, 50);
}

function resetEditProfile() {
	document.getElementById("editForm").reset();
	document.getElementById("firstName").style.borderColor = "";
	document.getElementById("lastName").style.borderColor = "";
	document.getElementById("phone").style.borderColor = "";
	document.getElementById("error").innerHTML = "";
}

function resetAddContent() {
	document.getElementById("addForm").reset();
	document.getElementById("title").style.borderColor = "";
	document.getElementById("brief").style.borderColor = "";
	document.getElementById("content2").style.borderColor = "";
	document.getElementById("error").innerHTML = "";
}

function setBorderColor(element) {
	if (element.value == "") {
		element.style.borderColor = "red";
	} else {
		element.style.borderColor = "green";
	}
}

function validateUserName(userName) {
	var len = { min: 3, max: 30 };
	if (userName.length < len.min || userName.length > len.max) return false;
	else return true;
}

function validatePassword(password) {
	var len = { min: 8, max: 30 };
	if (password.length < len.min || password.length > len.max) return false;
	else return true;
}

function validateTitle(title) {
	var len = { min: 10, max: 200 };
	if (title.length < len.min || title.length > len.max) return false;
	else return true;
}

function validateBrief(brief) {
	var len = { min: 30, max: 150 };
	if (brief.length < len.min || brief.length > len.max) return false;
	else return true;
}

function validateContent(content) {
	var len = { min: 50, max: 1000 };
	if (content.length < len.min || content.length > len.max) return false;
	else return true;
}

function validateFirstName(firstName) {
	var len = { min: 3, max: 30 };
	if (firstName.length < len.min || firstName.length > len.max) return false;
	else return true;
}

function validateLastName(lastName) {
	var len = { min: 3, max: 30 };
	if (lastName.length < len.min || lastName.length > len.max) return false;
	else return true;
}

function validatePhone(phone) {
	let re = /^[0-9]{9,13}$/;
	console.log(re.test(phone))
	return re.test(phone);
}

function validateDescription(description) {
	var max = 200;
	if (description.length > max) return false;
	else return true;
}

function validateEmail(email) {
	let re = /^[\w-_\.+]*[\w-_\.]\@([\w]+\.)+[\w]+[\w]$/;
	/*let re =
		/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	*/
	console.log(re.test(email));
	return re.test(email);
}
