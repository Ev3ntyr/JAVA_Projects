document.addEventListener('DOMContentLoaded', init);

function init() {
	console.log('DOM Content successfully loaded.');
}

const formSubmitBtn = document.getElementById('updatePwd');

formSubmitBtn.addEventListener('click', checkPasswordConfirmation);
	
function checkPasswordConfirmation() {
	let password = document.getElementById('passwordUser').value;
	let confPassword = document.getElementById('passwordUserCheck').value;
	console.log('pwd:' + password);
	console.log('pwd:' + confPassword);
	if (password === confPassword) {
		document.getElementById('formResetPwd').submit();
	} else {
		alert('Les mots de passe fournis ne correspondent pas.')
	}
}