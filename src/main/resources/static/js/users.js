// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers();
  $('#users').DataTable();
});


async function loadUsers()
{
      const request = await fetch('api/users', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.token
        }
      });
      const users = await request.json();


      let htmlList = ``;

      for (let user of users)
      {
         let phoneNumber = user.phoneNumber == null ? '---' : user.phoneNumber;
         let userHTML = `<tr><td>${user.id}</td><td>${user.name} ${user.surname}</td><td>${user.email}</td><td>${phoneNumber}</td><td><a href="#" onclick="deleteUser(${user.id})" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>`
         htmlList += userHTML;
      }


      document.querySelector('#users tbody').outerHTML = htmlList;
}


async function deleteUser(id)
{
    if (!confirm("Desea eliminar este usuario?"))
    {
        return;
    }

    const request = await fetch('api/users/' + id, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.token
        }
      });

    location.reload();

}