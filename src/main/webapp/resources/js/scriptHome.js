document.addEventListener('DOMContentLoaded', init);

function init() {
	console.log('DOM Content successfully loaded');	
	
}

const select = document.getElementById('category');
select.addEventListener('change', filterItemList);

function filterItemList() {
	let idCategory = document.getElementById('category').value;
	let cards = document.getElementsByClassName('card');
	for (let i = 0; i < cards.length; i++) {
		if (cards[i].getAttribute('name') === idCategory) {
			cards[i].setAttribute('style', 'width: 18rem;display:block');
		} else {
			cards[i].setAttribute('style', 'width: 18rem;display:none');
		}
	}
			
}