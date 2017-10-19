'use strict';
var cardsPagination = (function () {
    
    //<![CDATA[
    function cardsPagination(id, parameters) {

        let _card = document.querySelector('#'+id);

        let _description;
        let _linkDetails;
        let _title;
        let _urlData;
        let _max = 'size';
        let _page = 'page';
        let _limit = 6;
        
        
        if(parameters) {
            _description = parameters.description;
            _linkDetails = parameters.linkDetails;
            _title = parameters.title;
            _urlData = parameters.urlData;
        }else{
            _description = _card.getAttribute("data-description");
            _linkDetails = _card.getAttribute("data-url-detail");
            _title = _card.getAttribute("data-title");
            _urlData = _card.getAttribute("data-url");
        }

        function buscar(pagina) {

            fetch(_urlData+'?'+_max+'='+_limit+'&'+_page+'='+pagina)
                .then(response => response.json())
                .then(json =>  createTemplateCard(json));
        }
        
        function createTemplateCard(object) {

            _card.innerHTML = `
                <div class="card-columns">
                    ${object.currentList.map((item, index) => ` 
                        <div class="card  ">
                            <div class="card-body ">
                                <h4 class="card-title">${item[_title]} - ${index+1}</h4>
                                ${_description.split(',').map(descr => `
                                    <p class="card-text">${ executeFn(descr, item) }</p>
                                `).join('')}
                            </div>
                            <div class="card-footer ">
                                <a href="${gerarLinkDetail(item)}" class="card-link ">Visualizar</a>
                            </div>
                        </div>
                    `).join('')}
                </div>
               
            `;
            _card.appendChild(criarPaginacao(object));

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
            li = criarLi(object.currentPage === 1 ?'disabled' : '');
            link = criarLink('Primeiro', 0);
            li.appendChild(link);
            ul.appendChild(li);

            //create previous page
            li = criarLi(object.currentPage === 1 ? 'disabled' : '');
            link = criarLink('Anterior', object.currentPage-2);
            li.appendChild(link);
            ul.appendChild(li);

            let qtyPages = criarSequenciaPaginacao(object);
            qtyPages.forEach(page => {
                li = criarLi(page === object.currentPage ? 'active':'');
                link = criarLink(page, page-1);
                li.appendChild(link);
                ul.appendChild(li);
            });
            
            //create next page
            li = criarLi(object.numberPages === object.currentPage ? 'disabled' : '');
            link = criarLink('Próximo', object.currentPage);
            li.appendChild(link);
            ul.appendChild(li);

            //create last page
            li = criarLi(object.numberPages === object.currentPage ? 'disabled' : '' );
            link = criarLink('Último', object.numberPages-1);
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
            let matches = regExp.exec(_linkDetails);
            return _linkDetails.replace(matches[0], objeto[matches[1]]) ;
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
            return pages;
        }
        
        // function criarItensPaginacao(lista) {
        //     let mapP = [];
        //     mapP.push(`
        //         <li class="page-item ${lista.currentPage === 1 ? 'disabled':''}">
        //             <a class="page-link" href="#" onclick="cardsPagination.buscar(1)">Primeira</a>
        //           </li>
        //     `);
        //     mapP.push(`
        //         <li class="page-item ${lista.currentPage === 1 ? 'disabled':''}">
        //             <a class="page-link" href="#" onclick="cardsPagination.buscar(${lista.currentPage-2})">Anterior</a>
        //           </li>
        //     `);
        
        //     let paginas = criarSequenciaPaginacao(lista);
        
        //     paginas.forEach(pagina => {
        //         mapP.push(` 
        //             <li class="first-page page-item ${pagina === lista.currentPage ? 'active':''}">
        //                 <a class="page-link" onclick="cardsPagination.buscar(${pagina-1})" href="#">${pagina}</a>
        //             </li> `
        //         );
        //     });
            
        //     mapP.push(`
        //         <li class="page-item ${lista.numberPages === lista.currentPage ? 'disabled':''}">
        //             <a class="page-link" href="#" onclick="cardsPagination.buscar(${lista.currentPage})">Próximo</a>
        //         </li>
        //     `);
        //     mapP.push(`
        //         <li class="page-item ${lista.numberPages === lista.currentPage ? 'disabled':''}">
        //             <a class="page-link" href="#" onclick="cardsPagination.buscar(${lista.numberPages-1})">Última</a>
        //         </li>
        //     `);
        //     return mapP.join("");
        // }
        
        buscar(0);
       
    }
    
    return cardsPagination;
        //]]>
}

)();