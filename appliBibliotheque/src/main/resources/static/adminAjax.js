window.onload=function(){
	
	afficherConnexion();
	rechercherAdminsSelonId();
	
	(document.getElementById("btnRechercherParId")).addEventListener("click",rechercherAdminsSelonId);
	(document.getElementById("btnRechercher")).addEventListener("click",rechercherAdminsSelonId);
	(document.getElementById("btnAjout")).addEventListener("click", ajouterAdmin);
	
}


function afficherConnexion(){	
	let token = sessionStorage.getItem('token');
	let jsonPayload =JSON.parse(parseJwt(token));
	document.getElementById("connexionActuelle").innerHTML=jsonPayload.sub+" "+jsonPayload.authorities+" "+jsonPayload.exp;
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




function errCallbackJson(responseErrCallbackJson) {
	document.getElementById("messageException").classList.remove("d-none");
	let repJs = JSON.parse(responseErrCallbackJson);
	document.getElementById("messageException").innerHTML = repJs.message;
}

function rechercherAdminsSelonId(){
	let id = (document.getElementById("inputId")).value;
		
	let wsUrl = "./api-bibli/administrateur/"+id; //l'appel du controller qui fournit le fichier JSON
	makeAjaxGetRequest(wsUrl,function(responseJson){
		document.getElementById("messageException").classList.add("d-none");
		let adminsJs = JSON.parse(responseJson);
		//console.log("comptesJs="+livresJs);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le reremplir
		
		if(id >= 1){
			let admin = adminsJs;
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = admin.id;
			(row.insertCell(1)).innerHTML = admin.prenom;
			(row.insertCell(2)).innerHTML = admin.nom;
			/*
			(row.insertCell(3)).innerHTML = admin.codepostal;
			(row.insertCell(4)).innerHTML = admin.email;
			(row.insertCell(5)).innerHTML = admin.numallee;
			(row.insertCell(6)).innerHTML = admin.numtel;
			(row.insertCell(7)).innerHTML = admin.pays;
			(row.insertCell(8)).innerHTML = admin.typevoie;
			(row.insertCell(9)).innerHTML = admin.ville;
			(row.insertCell(10)).innerHTML = admin.password;
			(row.insertCell(11)).innerHTML = admin.username;
			*/
		}
		else{
			for(let admin of adminsJs){
				let row = bodyElt.insertRow(-1);
				(row.insertCell(0)).innerHTML = admin.id;
				(row.insertCell(1)).innerHTML = admin.prenom;
				(row.insertCell(2)).innerHTML = admin.nom;
				console.log(admin)
				/*
				(row.insertCell(3)).innerHTML = admin.codepostal;
				(row.insertCell(4)).innerHTML = admin.email;
				(row.insertCell(5)).innerHTML = admin.numallee;
				(row.insertCell(6)).innerHTML = admin.numtel;
				(row.insertCell(7)).innerHTML = admin.pays;
				(row.insertCell(8)).innerHTML = admin.typevoie;
				(row.insertCell(9)).innerHTML = admin.ville;
				(row.insertCell(10)).innerHTML = admin.password;
				(row.insertCell(11)).innerHTML = admin.username;
				*/
			}
		}
	},errCallbackJson);
	
}

 function ajouterAdmin(){
	  let labelPrenom = (document.getElementById("inputLabelPrenom")).value;
	  console.log("LABEL "+ labelPrenom);
	  let labelNom = (document.getElementById("inputLabelNom")).value;
	  let labelUsername = (document.getElementById("inputLabelUsername")).value;
	  let labelEmail = (document.getElementById("inputLabelEmail")).value;
	  let labelNumtel = (document.getElementById("inputLabelNumtel")).value;
	  let labelPassword = (document.getElementById("inputLabelPassword")).value;
	  let labelNumallee = (document.getElementById("inputLabelNumallee")).value;
	  let labelTypevoie = (document.getElementById("inputLabelTypevoie")).value;
	  let labelCodepostal = (document.getElementById("inputLabelCodepostal")).value;
	  let labelVille = (document.getElementById("inputLabelVille")).value;
	  let labelPays = (document.getElementById("inputLabelPays")).value;
	  let adminJs = {prenom : labelPrenom,
	  				 nom : labelNom,
	  				 username : labelUsername,
	  				 email : labelEmail,
	  				 numtel : labelNumtel,
	  				 password : labelPassword,
	  				 numallee : labelNumallee,
	  				 typevoie : labelTypevoie,
	  				 codepostal : labelCodepostal,
	  				 ville : labelVille,
	  				 pays : labelPays};
	  let adminJson= JSON.stringify(adminJs);
	   let wsUrl= "./api-bibli/lecteur";
	    makeAjaxPostRequest(wsUrl, adminJson,function(responseJson){
			document.getElementById("messageException").classList.add("d-none");
			console.log("responseJson=", responseJson);
		    rechercherLecteursSelonId();
		});
   
 }
 
