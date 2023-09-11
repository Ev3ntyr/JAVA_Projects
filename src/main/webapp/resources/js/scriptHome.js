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

function handleFilter(radioBtn) {
	
	if (radioBtn.getAttribute('id') === 'sell') {
		document.getElementById('openBids').setAttribute('disabled', 'disabled');
		document.getElementById('openBids').checked=false;
		document.getElementById('myBids').setAttribute('disabled', 'disabled');
		document.getElementById('myBids').checked=false;
		document.getElementById('myWonBids').setAttribute('disabled', 'disabled');
		document.getElementById('myWonBids').checked=false;
		
		document.getElementById('currentSell').removeAttribute('disabled');
		document.getElementById('currentSell').checked=true;
		document.getElementById('pendingSell').removeAttribute('disabled');
		document.getElementById('concludedSell').removeAttribute('disabled');
	} else {
		document.getElementById('openBids').removeAttribute('disabled');
		document.getElementById('openBids').checked=true;
		document.getElementById('myBids').removeAttribute('disabled');
		document.getElementById('myWonBids').removeAttribute('disabled');
		
		document.getElementById('currentSell').setAttribute('disabled', 'disabled');
		document.getElementById('currentSell').checked=false;
		document.getElementById('pendingSell').setAttribute('disabled', 'disabled');
		document.getElementById('pendingSell').checked=false;
		document.getElementById('concludedSell').setAttribute('disabled', 'disabled');
		document.getElementById('concludedSell').checked=false;
	}
	
	
}