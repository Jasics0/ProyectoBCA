var botonPassword= document.getElementById("btn-password");
 botonPassword.onclick= function(){
     
      var tipo = document.getElementById("password");
      if(tipo.type === "password"){
          tipo.type = "text";
      }else{
          tipo.type = "password";
      }
  }
  