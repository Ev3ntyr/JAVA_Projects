document.addEventListener('DOMContentLoaded', init);

function init() {
	console.log('DOM Content successfully loaded.');
}

const UPDATE_BTN = document.getElementById('delete');


function updateCategory(element) {
	let currentLabel = element.value;
	let newLabel = window.prompt('Quel est le nouveau nom de la catégorie ?', currentLabel);
	
	if (newLabel != null) {
		document.getElementById('oldCategoryLabel').value = currentLabel;
		document.getElementById('newCategoryLabel').value = newLabel;
		console.log("let's submit");
		document.getElementById('updateCategory').submit();
	} 
	
}