//console.log('passei');
let descricao = '';
let titulo = '';
let linkDetalhes = '';

function get(url) {
    return new Promise((resolve, reject) => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', url);
        xhr.onreadystatechange = () => {
            if(xhr.readyState === 4 ) {
                if(xhr.status === 200) {
                    resolve(JSON.parse(xhr.responseText));
                }else{
                    reject(xhr.statusText);
                }
            }
        }
        xhr.send();
    });
}

function criarCards() {
	
//	criarEstruturaCard();
	
//	let card = document.querySelector('cards');
//	let contas = {};
	// get("http://localhost:8080/conta/json?&max=6")
	// 	.then(result => criarEstruturaCard(result))
//		.then(c => c.currentList.forEach(contas => criarEstruturaCard(contas.currentList)));
//		
//	
	
	buscar(0);
	
}

function buscar(pagina) {
	return get("http://localhost:8080/conta/json?&max=6&page="+pagina)
	.then(result => criarEstruturaCard(result))
}

function criarEstruturaCard(lista) {

	let card = document.querySelector('cards');
	
	titulo = card.getAttribute("data-title");
	descricao = card.getAttribute("data-description");
	linkDetalhes = card.getAttribute("data-url-detail");

	card.innerHTML = `
		<div class="card-columns">
			${lista.currentList.map((conta, index) => ` 
				<div class="card  ">
					<div class="card-body ">
						<h4 class="card-title">${conta[titulo]} - ${index+1}</h4>
						${descricao.split(',').map(descr => `
							<p class="card-text">${executeFn(descr ,conta)}</p>
						`).join('')}
					</div>
					<div class="card-footer ">
						<a href="${gerarLinkDetail(conta)}" class="card-link ">Visualizar</a>
					</div>
				</div>
			`).join('')}
		</div>
		<nav aria-label="...">
			<ul class="pagination justify-content-center">
			  ${criarItensPaginacao(lista)}
			</ul>
		</nav>
	`;
	
}

function executeFn(param, obj) {
	
	if(param.indexOf(':') > 0) {
		let map = param.split(":");
		return window[map[0].trim()].apply(null, [obj[map[1].trim()]]);
	}
	return obj[param.trim()];
}

function gerarLinkDetail(objeto) {
	let regExp = /\{([^)]+)\}/;
	let matches = regExp.exec(linkDetalhes);
	return linkDetalhes.replace(matches[0], objeto[matches[1]]) ;
}

function criarSequenciaPaginacao (lista) {
	let pages = [];

	let visiblePage = lista.numberPages < 5 ? lista.numberPages : 5;

	let half = Math.floor(visiblePage / 2);
	let start = lista.currentPage - half + 1 - visiblePage % 2;
	let end = lista.currentPage + half;

	// handle boundary case
	if (start <= 0) {
		start = 1;
		end = visiblePage;
	}
	if (end > lista.numberPages) {
		start = lista.numberPages - visiblePage + 1;
		end = lista.numberPages;
	}

	let itPage = start;
	while (itPage <= end) {
		pages.push(itPage);
		itPage++;
	}
//	console.log(pages);
	return pages;
}

function criarItensPaginacao(lista) {
	let mapP = [];
	mapP.push(`
		<li class="page-item ${lista.currentPage === 1 ? 'disabled':''}">
			<a class="page-link" href="#" onclick="buscar(0)">Primeira</a>
		  </li>
	`);
	mapP.push(`
		<li class="page-item ${lista.currentPage === 1 ? 'disabled':''}">
			<a class="page-link" href="#" onclick="buscar(${lista.currentPage-2})">Anterior</a>
		  </li>
	`);

	let paginas = criarSequenciaPaginacao(lista);

	paginas.forEach(pagina => {
		mapP.push(` 
			<li class="page-item ${pagina === lista.currentPage ? 'active':''}">
				<a class="page-link" onclick="buscar(${pagina-1})" href="#">${pagina}</a>
			</li> `
		);
	});
	
	mapP.push(`
		<li class="page-item ${lista.numberPages === lista.currentPage ? 'disabled':''}">
			<a class="page-link" href="#" onclick="buscar(${lista.currentPage})">Próximo</a>
		</li>
	`);
	mapP.push(`
		<li class="page-item ${lista.numberPages === lista.currentPage ? 'disabled':''}">
			<a class="page-link" href="#" onclick="buscar(${lista.numberPages-1})">Última</a>
		</li>
	`);
	return mapP.join("");
}

function toDate(mile) {
	let data = new Date(parseInt(mile));
	return `${data.getDate()<10 ? '0' : ''}${data.getDate()}/${data.getMonth()+1<10 ? '0' : ''}${data.getMonth()+1}/${data.getFullYear()}`;
}

function formatNumero(valor) {
//	console.log()
//	return valor.toFixed(2).replace(/./g, function(c, i, a) {
////		console.log(c);
////		console.log(i);
////		console.log(a.replace('.', ','));
//	    return i && c !== "," && ((a.length - i) % 3 === 0) ? ',' + c : c;
//	});
	return 'R$ ' +valor.toFixed(2).replace(".", ",");
}

criarCards();