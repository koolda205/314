    const url = 'http://localhost:8080/api/users/';
    const container = document.querySelector('tbody');
    let result = '';

    //////////////////////////////////////////////


    // const show = (users) => {
    //     users.forEach(user => {
    //         results +=  <tr>
    //                         <td>${user.id}</td>
    //                         <td>${user.name}</td>
    //                         <td>${user.surname}</td>
    //                         <td>${user.age}</td>
    //                         <td>${user.email}</td>
    //                         <td>${user.roles}</td>
    //                         <td class="text-center"><a class="btnEdit btn btn-primary">Edit</a></td>
    //                         <td className="text-center"><a className="btnDelete btn btn-danger">Delete</a></td>
    //                     </tr>
    // })
    // container.innerHTML = results;
    // }

///////////////////////////////////////////////////////////////////
      // fetch(url)
      //   .then( response => response.json() )
      //   .then( data => show(data) )
      //   .catch( error => console.log(error) );
/////////////////////////////////////////////////////////////////
// fetch(url)
//     .then(res => {if(res.ok) {
//         return res.json();
//     } else {
//         console.log("ERROR");
//         throw Error;
//     }
//     })
//     .then(data =>
//         document.getElementById('root').innerHTML =
//         JSON.stringify(data)
//     ).catch(error => {
//         console.log(error);
//     })

    async function getIndexPage() {
        let page = await fetch(url);
        if (page.ok) {
            let listAllUser = await page.json();
            loadTableData(listAllUser);
        } else {
            alert(`Error, ${page.status}`)
        }
    }

    function loadTableData(listAllUser) {
        let tableBody = document.getElementById('tbody');
        let dataHtml = '';
        for (let user of listAllUser) {
            let roles = [];
            for (let role of user.roles) {
                roles.push(" " + role.name)
            }
            dataHtml +=
                `<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>${roles}</td>
                    <td>
                        <button class="btn blue-background" data-bs-toogle="modal"
                           data-bs-target="#editModal"
                           onclick="editModalData(${user.id})">Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" data-bs-toogle="modal"
                        data-bs-target="#deleteModal"
                        onclick="deleteModalData(${user.id})">Delete</button>
                    </td>
                </tr>`
        }
        tableBody.innerHTML = dataHtml;
    }

    getIndexPage();