window.onload=function(){
	
	rechercherLivres();
	
	(document.getElementById("btnRechercher")).addEventListener("click",rechercherLivres);
	(document.getElementById("btnAjout")).addEventListener("click", ajouterLivre);
	(document.getElementById("btnDelete")).addEventListener("click", deleteLivre);
	(document.getElementById("btnUpdateDispo")).addEventListener("click", updateLivreDispo);
	(document.getElementById("btnUpdateEtat")).addEventListener("click",updateLivreEtat);
	(document.getElementById("btnRechercheParLecteur")).addEventListener("click",rechercherLivresParLecteurs);
	(document.getElementById("btnRechercheParLecteurActuel")).addEventListener("click",rechercherLivresParLecteursActuel);
	
	
}

function errCallbackJson(responseErrCallbackJson) {
	document.getElementById("messageException").classList.remove("d-none");
	let repJs = JSON.parse(responseErrCallbackJson);
	document.getElementById("messageException").innerHTML = repJs.message;
}

function rechercherLivres(){
	
	let wsUrl = "./api-bibli/livre"; //l'appel du controller qui fournit le fichier JSON
	makeAjaxGetRequest(wsUrl,function(responseJson){
		document.getElementById("messageException").classList.add("d-none");
		let livresJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le reremplir
		for(let livre of livresJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = livre.id;
			(row.insertCell(1)).innerHTML = livre.titre;
			(row.insertCell(2)).innerHTML = livre.auteur;
			(row.insertCell(3)).innerHTML = livre.dispo;
			(row.insertCell(4)).innerHTML = livre.etat;
			(row.insertCell(5)).innerHTML = livre.domaine;
			
		}
	},errCallbackJson);
	/*document.getElementById('idMessage').innerHTML="ok";*/
}

function ajouterLivre(){
	  let labelTitre = (document.getElementById("inputLabelTitre")).value;
	  let labelAuteur = (document.getElementById("inputLabelAuteur")).value;
	  let livreJs = {titre : labelTitre,
	  				 auteur: labelAuteur};
	  let livreJson= JSON.stringify(livreJs);
	   let wsUrl= "./api-bibli/livre";
	    makeAjaxPostRequest(wsUrl, livreJson,function(responseJson){
			document.getElementById("messageException").classList.add("d-none");
			rechercherLivres();
		});
   
 }
 
function deleteLivre(){
	 
	 let labelLivreDelete = (document.getElementById("inputLabelLivreDelete")).value;
	 	 
	 let wsUrl= "./api-bibli/livre/"+labelLivreDelete; 
	 makeAjaxDeleteRequest(wsUrl,function(responseJson){
		 document.getElementById("messageException").classList.add("d-none");
		 rechercherLivres();
	 },errCallbackJson)
	 
 }
 
 function updateLivreDispo(){
	  let labelLivreUpdateDispo = (document.getElementById("inputLabelLivreUpdateDispo")).value;
	  let labelIdLivre = (document.getElementById("inputLabelIdLivre")).value;
	  let livreUpdateDispoJs = {id : labelIdLivre,
	  							dispo: labelLivreUpdateDispo};
		  						
	  let livreUpdateDispoJson = JSON.stringify(livreUpdateDispoJs);
	  
	  let wsUrl= "./api-bibli/livre/";
	  console.log("TEST UPDATE ", labelIdLivre+"  "+ livreUpdateDispoJs.dispo);
	  makeAjaxPutRequest(wsUrl,livreUpdateDispoJson, function(responseJson){
		  document.getElementById("messageException").classList.add("d-none");
		  rechercherLivres();
	  },errCallbackJson)
	  
 }
 
 function updateLivreEtat(){
	  let labelLivreUpdateEtat = (document.getElementById("inputLabelLivreUpdateEtat")).value;
	  let labelIdLivre = (document.getElementById("inputLabelIdLivreEtat")).value;
	  var  dispoVar= new Boolean(true);
	  
	 
	  let livreUpdateEtatJs = {id : labelIdLivre,
	  							dispo: dispoVar,
	  							etat: labelLivreUpdateEtat.toUpperCase()};
	  
	  if(labelLivreUpdateEtat.toUpperCase() == "HORS_SERVICE") dispoVar = false;
			
	  livreUpdateEtatJs = {id : labelIdLivre,
	  						 dispo: dispoVar,
	  						 etat: labelLivreUpdateEtat.toUpperCase()};
	
	  let livreUpdateEtatJson = JSON.stringify(livreUpdateEtatJs);
	  
	  let wsUrl= "./api-bibli/livre/";
	  makeAjaxPutRequest(wsUrl,livreUpdateEtatJson, function(responseJson){
		  document.getElementById("messageException").classList.add("d-none");
		  rechercherLivres();
	  },errCallbackJson)
	  
 }
     


 function rechercherLivresParLecteurs(){
	let idLecteur = (document.getElementById("inputRechercheParLecteur")).value;
	let wsUrl = "./api-bibli/livre/livresEmpruntes?idLecteur="+idLecteur; //l'appel du controller qui fournit le fichier JSON
	makeAjaxGetRequest(wsUrl,function(responseJson){
		document.getElementById("messageException").classList.add("d-none");
		
		let livresJs = JSON.parse(responseJson);
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le reremplir
		
		for(let livre of livresJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = livre.id;
			(row.insertCell(1)).innerHTML = livre.titre;
			(row.insertCell(2)).innerHTML = livre.auteur;
			(row.insertCell(3)).innerHTML = livre.dispo;
			(row.insertCell(4)).innerHTML = livre.etat;
		}
	},errCallbackJson);
}

 function rechercherLivresParLecteursActuel(){
	let idLecteur = (document.getElementById("inputRechercheParLecteur")).value;
	let wsUrl = "./api-bibli/livre/livresEmpruntesActuel?idLecteur="+idLecteur; //l'appel du controller qui fournit le fichier JSON
	makeAjaxGetRequest(wsUrl,function(responseJson){
		document.getElementById("messageException").classList.add("d-none");
		
		let livresJs = JSON.parse(responseJson);
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le reremplir
		
		for(let livre of livresJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = livre.id;
			(row.insertCell(1)).innerHTML = livre.titre;
			(row.insertCell(2)).innerHTML = livre.auteur;
			(row.insertCell(3)).innerHTML = livre.dispo;
			(row.insertCell(4)).innerHTML = livre.etat;
		}
	},errCallbackJson);
}
