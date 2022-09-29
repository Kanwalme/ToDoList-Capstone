
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("todo-form")
const todoContainer = document.getElementById("todo-container")

//Modal Elements
let todoBody = document.getElementById(`todo-edit-item`)
let todoDate = document.getElementById(`todo-edit-date`)
let updateTodoBtn = document.getElementById('update-todo-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/toDo/"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        item: document.getElementById("todo-input").value,
        date: document.getElementById("todo-date").value

    }
    await addToDoItem(bodyObj);
    document.getElementById("todo-input").value = ''
        document.getElementById("todo-date").value= ''
}

async function addToDoItem(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getTodo(userId);
    }
}

async function getTodo(userId) {
    console.log("getToDo func")
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createTodoCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(toDoId){
    await fetch(baseUrl + toDoId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getTodo(userId);
}

async function getToDoItemById(toDoId){
    console.log("getToDoItemById func" + toDoId)
    await fetch(baseUrl + toDoId, {
        method: "GET",
        headers: headers

    })

        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleTodoEdit(toDoId){
    let bodyObj = {
        id: toDoId,
        item: todoBody.value,
        date:todoDate.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getTodo(userId);
}

const createTodoCards = (array) => {
    console.log("createTodoCards func")
    todoContainer.innerHTML = ''
    array.forEach(obj => {
        console.log(obj)
        let toDoCard = document.createElement("div")
        toDoCard.classList.add("m-2")
        toDoCard.innerHTML = `
            <div class="card d-flex" style="width: 50rem; height: 15rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.item}</p>
                    <p class="card-text">${obj.date}</p>
                    <div class="d-flex justify-content-flex-start">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Complete</button>
                        <button onclick="getToDoItemById(${obj.id})" type="button" class="btn btn-primary" 
                        data-bs-toggle="modal" data-bs-target="#todo-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        todoContainer.prepend(toDoCard);
    })
}
// function handleLogout(){
//     let c = document.cookie.split(";");
//     for(let i in c){
//         document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
//     }
// }

const populateModal = (obj) =>{
    console.log("populateModal func")
    console.log(obj.item)
    todoBody.value= ''
    todoBody.value = obj.item
    todoDate.value = obj.date
    console.log(obj)
    updateTodoBtn.setAttribute('data-todo-id', obj.id)
}

getTodo(userId);

submitForm.addEventListener("submit", handleSubmit)

updateTodoBtn.addEventListener("click", (e)=>{
    let toDoId = e.target.getAttribute('data-todo-id')
    handleTodoEdit(toDoId);
})