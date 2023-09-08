document.addEventListener('DOMContentLoaded', init);

function init() {
	console.log('DOM Content successfully loaded.');
}

const formSubmitBtn = document.getElementById('formSubmitBtn');

formSubmitBtn.addEventListener('click', checkPasswordConfirmation);
	
function checkPasswordConfirmation() {
	let newPasswordUser = document.getElementById('newPasswordUser').value;
	if (newPasswordUser != null) {
		let newPasswordUserCheck = document.getElementById('newPasswordUserCheck').value;
		console.log('pwd:' + newPasswordUser);
		console.log('pwd:' + newPasswordUserCheck);
		if (newPasswordUser === newPasswordUserCheck) {
			document.getElementById('formUpdate').submit();
		} else {
			alert('Les mots de passe fournis ne correspondent pas.')
		}
	} else {
		document.getElementById('formUpdate').submit();
	}
}

const deleteAccountBtn = document.getElementById('deleteAccountBtn');
deleteAccountBtn.addEventListener('click', confirmDeletion);

function confirmDeletion() {
	if(confirm('Cette action est irréversible, continuer ?')) {
		console.log('confirmed');
		window.location.replace(deleteAccountBtn.getAttribute('href'));
	} else {
		console.log('canceled');
		window.location.reload();
	}
}