window.onload=function(){
	(document.getElementById("btnLogin")).addEventListener("click",doLogin);
	
	afficherConnexion();
	
	
}

function errCallbackJson(responseErrCallbackJson){
		document.getElementById("messageException").classList.remove("d-none");
		let repJs = JSON.parse(responseErrCallbackJson);
		document.getElementById("messageException").innerHTML=repJs.message;
}



function ajouterCompte(){
	let user = (document.getElementById("inputUser")).value;
	let password = (document.getElementById("inputPassword")).value;
	
	//ici on crée un JSON
	let compteJs = {username : user, password : password}; //presque pareil que le JSON mais Javascript litteral	
	let compteJson = JSON.stringify(compteJs);
	
	let wsUrl = "./api-login/public/login";
	makeAjaxPostRequest(wsUrl,compteJson, function(responseJson){
		console.log("responseJson="+responseJson);
	},errCallbackJson)
	
}


function afficherConnexion(){	
	let token = sessionStorage.getItem('token');
	let jsonPayload =JSON.parse(parseJwt(token));
	document.getElementById("connexionActuelle").innerHTML=jsonPayload.sub+" "+jsonPayload.authorities+" "+jsonPayload.exp;
}


//sessionStorage.setItem("token",null);


function parseJwt (token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    let jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return jsonPayload;
    //return JSON.parse(jsonPayload);
};

function doLogin(){
	sessionStorage.setItem("token",null);

	var username = document.getElementById("inputUser").value;
	var password = document.getElementById("inputPassword").value;

	
	var url = "./api-login/public/login"

    var callback = function(data){
	   //console.log("success data=" + data);
       var jwtToken = (JSON.parse(data)).token;
       //tokenGlobal=jwtToken;
       sessionStorage.setItem("token",jwtToken);
       /*
       var tokenSession = sessionStorage.getItem('token');
		document.getElementById("connexionActuelle").innerHTML=tokenSession;
		*/
       
       //mise en page du JSON pour data
       data=data.replace(/,/g, ',<br />').replace(/{/, '{<br>').replace(/}/, '<br>}');
       //mise en page du JSON pour parsejwt
       parsejwt=parseJwt(jwtToken).replace(/,/g, ',<br />').replace(/{/, '{<br>').replace(/}/, '<br>}');
       
       var message ="<b>Reponse login :</b> <br/> " + data + " <br /><br /> <b>Payload token :</b><br/> " + parsejwt;
       document.getElementById("spanMessageLogin").innerHTML=" "+message+" ";
       afficherConnexion();
    }

    var errCallback = function(data){
	   console.log("erreur=" + data);
       var message = (JSON.parse(data)).message;
       sessionStorage.setItem("token",null);
       document.getElementById("spanMessageLogin").innerHTML=" "+message+" ";
    }

    var jsLoginRequestObject = {username , password};
    var jsonData = JSON.stringify(jsLoginRequestObject);

	makeAjaxPostRequest(url,jsonData,callback,errCallback) ;
	
	
}

