window.onload = function() {
	
	afficherConnexion();  //date avant expiration du token
	
	
	donneesLivres();
	donneesLecteurs();
	rechercherEmprunts();

	(document.getElementById("btnRechercher")).addEventListener("click", rechercherEmprunts);

	(document.getElementById("btnAjout")).addEventListener("click", ajouterEmprunt);

	(document.getElementById("btnDelete")).addEventListener("click", supprimerEmprunt);
	
	(document.getElementById("btnUpdate")).addEventListener("click",miseAJourEmprunt);

	/*
	(document.getElementById("btnUpdateDispo")).addEventListener("click", updateLivreDispo);
	
	*/
}


//connecté en tant qu'admin : statut modifié par la fonction "afficher connexion" executée à l'ouverture de la page ou en cas de MAJ
var admin=false;

//Pour afficher le reste de secondes
var endDate = new Date();
var interval = setInterval(updateCounter, 1000);

function afficherConnexion(){	
	let token = sessionStorage.getItem('token');
	let jsonPayload =JSON.parse(parseJwt(token));
	
	//document.getElementById("connexionActuelle").innerHTML=jsonPayload.sub+" "+jsonPayload.authorities+" "+jsonPayload.exp;
	endDate = new Date(jsonPayload.exp * 1000);
	updateCounter();
	
	if(jsonPayload.authorities=="[ROLE_ADMIN]"){
		admin=true;
		document.getElementById("colonneAdmin").classList.remove("d-none");
		
		document.getElementById("connexionActuelle").innerHTML=jsonPayload.sub+" <i class='fa-solid fa-user-gear'></i> - <i class='fa-regular fa-clock'></i> <i id='counter' ></i>";
	}
	else if(jsonPayload.authorities=="[ROLE_USER]"){
		document.getElementById("connexionActuelle").innerHTML=jsonPayload.sub+" <i class='fa-solid fa-user'></i> - <i class='fa-regular fa-clock'></i> <i id='counter' ></i>";
	}
	else{
		document.getElementById("connexionActuelle").innerHTML="Aucune connexion <i class='fa-solid fa-circle-question'></i>";
	}
}

function parseJwt (token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    let jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return jsonPayload;
    //return JSON.parse(jsonPayload);
};

function updateCounter() {
    var now = new Date();
    var secondsRemaining = (endDate.getTime() - now.getTime()) / 1000;
    
    // Arrondir au nombre de secondes le plus proche
    secondsRemaining = Math.round(secondsRemaining);
    
    var minutes = Math.floor(secondsRemaining / 60);
    var seconds = secondsRemaining % 60;

    
    // Si le temps est écoulé, on arrête le compteur
    if (secondsRemaining <= 0) {
        secondsRemaining = 0;
        clearInterval(interval);
    }
    
    function formatNumber(number) {
    	return number < 10 ? '0' + number : number;
	}
    
    document.getElementById("counter").textContent = "Session terminée dans "+`${formatNumber(minutes)}:${formatNumber(seconds)}`+" (minutes)";
}



function errCallbackJson(responseErrCallbackJson) {
	document.getElementById("messageException").classList.remove("d-none");
	let repJs = JSON.parse(responseErrCallbackJson);
	document.getElementById("messageException").innerHTML = repJs.message;
}


function rechercherEmprunts() {

	let wsUrl = "./api-bibli/emprunt"; //l'appel du controller qui fournit le fichier JSON
	makeAjaxGetRequest(wsUrl, function(responseJson) {
		document.getElementById("messageException").classList.add("d-none");
		let empruntsJs = JSON.parse(responseJson);

		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML = "";//vider le tableau avant de le reremplir
		for (let emprunt of empruntsJs) {
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = emprunt.nomLecteur; //emprunt.livre.titre
			(row.insertCell(1)).innerHTML = emprunt.nomLivre; //emprunt.lecteur.nom
			(row.insertCell(2)).innerHTML = emprunt.dateDebut; //emprunt.date_debut
			(row.insertCell(3)).innerHTML = emprunt.dateFin; //emprunt.date_fin
			(row.insertCell(4)).innerHTML = emprunt.incident; //emprunt.incident
			if(admin){
				(row.insertCell(5)).innerHTML = "<button class='btn btn-danger ' onClick='supprimerEmpruntBouton("+emprunt.idLecteur+","+emprunt.idLivre+")'>Supprimer <i class='fa-solid fa-xmark'></i></button>";
			}
		}
	});
}

function ajouterEmprunt() {
	let idLivre = (document.getElementById("inputIdLivre")).value;
	let idLecteur = (document.getElementById("inputIdLecteur")).value;
	let motifIncident = (document.getElementById("inputMotifIncident")).value;
	let empruntJs = {
		idLivre: idLivre,
		idLecteur: idLecteur
	};
	let empruntJson = JSON.stringify(empruntJs);
	let wsUrl = "./api-bibli/emprunt?idLivre=" + idLivre + "&idLecteur=" + idLecteur+ "&motifIncident=" + motifIncident;
	makeAjaxPostRequest(wsUrl, empruntJson, function(responseJson) {
		document.getElementById("messageException").classList.add("d-none");
		rechercherEmprunts();
	}, errCallbackJson);

}


function supprimerEmprunt() {
	let idLivre = (document.getElementById("inputIdLivre")).value;
	let idLecteur = (document.getElementById("inputIdLecteur")).value;
	let empruntJs = {
		idLivre: idLivre,
		idLecteur: idLecteur
	};
	let empruntJson = JSON.stringify(empruntJs);
	let wsUrl = "./api-bibli/emprunt?idLivre=" + idLivre + "&idLecteur=" + idLecteur;
	makeAjaxDeleteRequest(wsUrl, function(responseJson) {
		document.getElementById("messageException").classList.add("d-none");
		rechercherEmprunts();
	}, errCallbackJson)

}


function miseAJourEmprunt(){
	let idLivre = (document.getElementById("inputIdLivre")).value;
	let idLecteur = (document.getElementById("inputIdLecteur")).value;
	let motifIncident = (document.getElementById("inputMotifIncident")).value;
	let empruntJs = {
		idLivre: idLivre,
		idLecteur: idLecteur
	};
	let empruntJson = JSON.stringify(empruntJs); //motifIncident
	let wsUrl = "./api-bibli/emprunt?idLivre=" + idLivre + "&idLecteur=" + idLecteur+ "&motifIncident=" + motifIncident;
	makeAjaxPutRequest(wsUrl, empruntJson, function(responseJson) {
		document.getElementById("messageException").classList.add("d-none");
		rechercherEmprunts();
	}, errCallbackJson);
}

function supprimerEmpruntBouton(idLecteur,idLivre) {
	let empruntJs = {
		idLivre: idLivre,
		idLecteur: idLecteur
	};
	let empruntJson = JSON.stringify(empruntJs);
	let wsUrl = "./api-bibli/emprunt?idLivre=" + idLivre + "&idLecteur=" + idLecteur;
	makeAjaxDeleteRequest(wsUrl, function(responseJson) {
		document.getElementById("messageException").classList.add("d-none");
		rechercherEmprunts();
	}, errCallbackJson)

}

//pour remplir la liste de choix de livres
function donneesLivres(){
	
	let wsUrl = "./api-bibli/livre"; //l'appel du controller qui fournit le fichier JSON
	
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		document.getElementById("messageException").classList.add("d-none");
		let livresJs = JSON.parse(responseJson);
		
		
		for(let livre of livresJs){
			document.getElementById("inputIdLivre").innerHTML += "<option value='"+livre.id+"'>"+livre.titre+"</option>";
		}
	},errCallbackJson);
}

//pour remplir la liste de choix de livres
function donneesLecteurs(){
	
	let wsUrl = "./api-bibli/lecteur";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		document.getElementById("messageException").classList.add("d-none");
		let lecteursJs = JSON.parse(responseJson);
		
		for(let lecteur of lecteursJs){
			document.getElementById("inputIdLecteur").innerHTML += "<option value='"+lecteur.id+"'>"+lecteur.prenom+" "+lecteur.nom+"</option>";
		}
	},errCallbackJson);
}



    


