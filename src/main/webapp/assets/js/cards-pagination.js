'use strict';
var cardsPagination = (function () {
    
    //<![CDATA[
    function cardsPagination(id, parameters) {

        let _card = document.getElementById(id);

        let _description;
        let _linkDetails;
        let _title;
        let _urlData;
        let _sort;
        let _size = 'size';
        let _page = 'page';
        let _limit = 6;
        
        
        
        if(parameters) {
            _description = parameters.description;
            _linkDetails = parameters.linkDetails;
            _title = parameters.title;
            _urlData = parameters.urlData;
            _sort = paremeters.sort ? paremeters.sort : _sort;
            _size = parameters.size ? parameters.size : _size;
            _page = parameters.page ? parameters.page : _page;
            _limit = parameters.limit ? parameters.limit : _limit;
        }else{
            _description = _card.getAttribute("data-description");
            _linkDetails = _card.getAttribute("data-url-detail") ? JSON.parse(_card.getAttribute("data-url-detail").replace(/['"]/g, '"')) : _card.getAttribute("data-url-detail");
            _title = _card.getAttribute("data-title");
            _urlData = _card.getAttribute("data-url");
            _sort = _card.getAttribute("data-sort");
            _size = _card.getAttribute("data-size") ? _card.getAttribute("data-size") : _size;
            _page = _card.getAttribute("data-page") ? _card.getAttribute("data-page") : _page;
            _limit = _card.getAttribute("data-limit") ? _card.getAttribute("data-limit") : _limit;
        }

        function buscar(pagina) {

            fetch(_urlData+'?'+_size+'='+_limit+'&'+_page+'='+pagina+(_sort ? '&sort='+_sort : ''), 
            		{ method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json =>  createTemplateCard(json));
        }
        
        function createTemplateCard(object) {
        	
        	if(object.totalElements > 0) {
        		
        	
        		_card.innerHTML = `
	                <div class="card-columns">
	                    ${object.content.map((item, index) => ` 
	                        <div class="card  ">
	                            <div class="card-body ">
	                                <h4 class="card-title text-uppercase">${item[_title]}</h4>
	                                ${_description.split(',').map(descr => `
	                                    <p class="card-text">${ executeFn(descr, item) }</p>
	                                `).join('')}
	                            </div>
	                            
	                            ${createFooterCard(item)}
	                            
	                            
	                        </div>
	                    `).join('')}
	                </div>
	               
	            `;
	            _card.appendChild(criarPaginacao(object));
        	}else{
        		
        		_card.innerHTML = `
        			<div class="alert alert-primary text-center" role="alert">
        				<h3>Não há itens para serem exibidos!</h3>
        			</div>
    			`;
        		
        	}

        }
        
        function createFooterCard(item) {
        	if(_linkDetails) {
        		let footer =`
	        		<div class="card-footer ">
	                	<a href="${gerarLinkDetail(item)}" class="card-link ">${_linkDetails.label}</a>
	                </div>
        		`;
        		return footer;
        	}
        }

        function criarLi(className) {
            let li = document.createElement('li');
            li.className = `page-item ${className}`;
            return li;
        }

        function criarLink(label, page) {
            let link = document.createElement('a');
            link.innerText = label;
            link.className = 'page-link';
            link.href = '#'
            link.onclick = function () {buscar(page);};
            return link;
        }

        function criarPaginacao(object) {
            let nav = document.createElement('nav');

            let ul = document.createElement('ul');
            ul.className = 'pagination justify-content-center';
            
            let li, link;

            //create first page
            li = criarLi(object.first ?'disabled' : '');
            link = criarLink('Primeiro', 0);
            li.appendChild(link);
            ul.appendChild(li);

            //create previous page
            li = criarLi(object.first ? 'disabled' : '');
            link = criarLink('Anterior', object.number-2);
            li.appendChild(link);
            ul.appendChild(li);

            let qtyPages = criarSequenciaPaginacao(object);
            qtyPages.forEach(page => {
                li = criarLi(page-1 === object.number ? 'active':'');
                link = criarLink(page, page-1);
                li.appendChild(link);
                ul.appendChild(li);
            });
            
            //create next page
            li = criarLi(object.last ? 'disabled' : '');
            link = criarLink('Próximo', object.number+1);
            li.appendChild(link);
            ul.appendChild(li);

            //create last page
            li = criarLi(object.last ? 'disabled' : '' );
            link = criarLink('Último', object.totalPages-1);
            li.appendChild(link);
            ul.appendChild(li);

            nav.appendChild(ul);

            return nav;

        }


        function executeFn(param, obj) {
            
            if(param.indexOf(':') > 0) {
                let map = param.split(":");
                let func = map[0].trim();
                let value = obj[map[1].trim()];
                return window[func].apply(null, [value]);
            }
            return obj[param.trim()];
        }
        
        function gerarLinkDetail(objeto) {
        	
            let regExp = /\{([^)]+)\}/;
            let matches = regExp.exec(_linkDetails.url);
            
            
            return _linkDetails.url.replace(matches[0], objeto[matches[1]]) ;
        }
        
        function criarSequenciaPaginacao (lista) {
            let pages = [];
        
            let visiblePage = lista.totalPages < 5 ? lista.totalPages : 5;
        
            let half = Math.floor(visiblePage / 2);
            let start = lista.number - half + 1 - visiblePage % 2;
            let end = lista.number + half;
        
            // handle boundary case
            if (start <= 0) {
                start = 1;
                end = visiblePage;
            }
            if (end > lista.totalPages) {
                start = lista.totalPages - visiblePage + 1;
                end = lista.totalPages;
            }
        
            let itPage = start;
            while (itPage <= end) {
                pages.push(itPage);
                itPage++;
            }
            return pages;
        }
     
        
        buscar(0);
       
    }
    
    return cardsPagination;
        //]]>
}

)();