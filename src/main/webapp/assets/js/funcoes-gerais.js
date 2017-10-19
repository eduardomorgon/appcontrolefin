function formatDate (mile) {
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