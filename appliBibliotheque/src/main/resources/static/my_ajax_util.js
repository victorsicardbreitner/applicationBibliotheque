//subfunction with errCallback as optional callback :
function registerCallbacks(xhr, callback, errCallback) {
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) { //la requête est terminée
			if ((xhr.status == 200 || xhr.status == 0)) {
				callback(xhr.responseText);
			}
			else {
				if (errCallback)
					errCallback(xhr.responseText);
			}
		}
	};
}




//ajout de fonctions pour les jetons

function setTokenInRequestHeader(xhr){ 
	var token = sessionStorage.getItem("token");//inverse de sessionStorage.setItem
	xhr.setRequestHeader("Authorization", "Bearer " + token);
	
}


function makeAjaxGetRequest(url, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("GET", url, true); 
	
	setTokenInRequestHeader(xhr);//transmission du token
	
	xhr.send(null);
}

function makeAjaxDeleteRequest(url, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("DELETE", url, true);
	
	setTokenInRequestHeader(xhr);//transmission du token
	
	xhr.send(null);
}

/* version originale de Didier Defrance*/
/*
function makeAjaxPostRequest(url, jsonData, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	
	setTokenInRequestHeader(xhr);//transmission du token
	
	xhr.send(jsonData);
}
*/



function makeAjaxPutRequest(url, jsonData, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("PUT", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	
	setTokenInRequestHeader(xhr);//transmission du token
	
	xhr.send(jsonData);
}



//---------------------------------------------------------------------





//subfunction with errCallback as optional callback :
function traitementChangementDEtat(xhr, callback, errCallback) {
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) { // requête terminée
			if ((xhr.status == 200 || xhr.status == 0)) {
				callback(xhr.responseText);
			}
			else {
				if (errCallback)
					errCallback(xhr.responseText);
			}
		}
	};
}


/* version modifiee*/
function makeAjaxPostRequest(url, jsonData, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	traitementChangementDEtat(xhr, callback, errCallback);
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	setTokenInRequestHeader(xhr);//transmission du token
	
	xhr.send(jsonData);
	
	
	
}


