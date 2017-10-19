var teste1 = (function () {
    // console.log(this);
    //<![CDATA[
    function teste1(id) {

        let alert1 = function(valor) {
            
            console.log(valor);
            
        }

        let div = document.getElementById(id);
        // let btn = document.createElement("BUTTON");
        // btn.addEventListener('click', function() { alert1("clicktrack")});
        // var texto = document.createTextNode("CLICK ME");       // Create a text node
        // btn.appendChild(texto);                                // Append the text to <button>
        // div.appendChild(btn);


        let divCardColunms = document.createElement("div");
        divCardColunms.className = "card-columns";

        let divCard;
        let divCardBody;

        divCard = document.createElement("div");
        divCard.className = "card";

        divCardBody = document.createElement("div");
        divCardBody.className = "card-body";

        divCardBody.innerHTML = '<h4 class="card-title">Titulo</h4>';
        divCardBody.innerHTML += '<p class="card-text">Conta de Luz</p>';
        divCardBody.innerHTML += '<p class="card-text">11/10/2017</p>';
        divCardBody.innerHTML += '<p class="card-text">R$ 56,89</p>';

        

        
        divCard.appendChild(divCardBody);

        divCard.innerHTML += '<div class="card-footer"><a href="#" class="card-link ">Visualizar</a></div>';

        divCardColunms.appendChild(divCard);

        divCard = document.createElement("div");
        divCard.className = "card";

        divCardBody = document.createElement("div");
        divCardBody.className = "card-body";

        divCardBody.innerHTML = '<h4 class="card-title">Titulo</h4>';
        divCardBody.innerHTML += '<p class="card-text">Conta de Luz</p>';
        divCardBody.innerHTML += '<p class="card-text">11/10/2017</p>';
        divCardBody.innerHTML += '<p class="card-text">R$ 56,89</p>';

        

        
        divCard.appendChild(divCardBody);

        divCard.innerHTML += '<div class="card-footer"><a href="#" class="card-link ">Visualizar</a></div>';

        divCardColunms.appendChild(divCard);

        divCard = document.createElement("div");
        divCard.className = "card";

        divCardBody = document.createElement("div");
        divCardBody.className = "card-body";

        divCardBody.innerHTML = '<h4 class="card-title">Titulo</h4>';
        divCardBody.innerHTML += '<p class="card-text">Conta de Luz</p>';
        divCardBody.innerHTML += '<p class="card-text">11/10/2017</p>';
        divCardBody.innerHTML += '<p class="card-text">R$ 56,89</p>';

        

        
        divCard.appendChild(divCardBody);

        divCard.innerHTML += '<div class="card-footer"><a href="#" class="card-link ">Visualizar</a></div>';

        divCardColunms.appendChild(divCard);

        divCard = document.createElement("div");
        divCard.className = "card";

        divCardBody = document.createElement("div");
        divCardBody.className = "card-body";

        divCardBody.innerHTML = '<h4 class="card-title">Titulo</h4>';
        divCardBody.innerHTML += '<p class="card-text">Conta de Luz</p>';
        divCardBody.innerHTML += '<p class="card-text">11/10/2017</p>';
        divCardBody.innerHTML += '<p class="card-text">R$ 56,89</p>';

        

        
        divCard.appendChild(divCardBody);

        divCard.innerHTML += '<div class="card-footer"><a href="#" class="card-link ">Visualizar</a></div>';

        divCardColunms.appendChild(divCard);



        div.appendChild(divCardColunms);
        

    }

    return teste1;

})();