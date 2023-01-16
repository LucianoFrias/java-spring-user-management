// Call the dataTables jQuery plugin
$(document).ready(function() {

});


async function registerUser()
{
      let data = {};
      data.name = document.getElementById('exampleFirstName').value;
      data.surname = document.getElementById('exampleLastName').value;
      data.email = document.getElementById('exampleInputEmail').value;
      data.password = document.getElementById('exampleInputPassword').value;
      data.repeatPassword = document.getElementById('exampleRepeatPassword').value;

      if (data.repeatPassword != data.password)
      {
        alert("La contraseña escrita difiere de la repetida.");
        return;
      }

      const request = await fetch('api/users', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

    alert("La cuenta fue creada con exito!");
    window.location.href = "login.html";
}
