// Call the dataTables jQuery plugin
$(document).ready(function() {

});


async function loginUser()
{
      let data = {};
      data.email = document.getElementById('exampleInputEmail').value;
      data.password = document.getElementById('exampleInputPassword').value;

      const request = await fetch("api/login", {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

        const response = await request.text();

        if (response == "FAILED")
        {
            alert("Your credentials are incorrect. Try again!");
            return;
        }

        localStorage.token = response;
        localStorage.email = data.email;
        window.location.href = "users.html";

}
