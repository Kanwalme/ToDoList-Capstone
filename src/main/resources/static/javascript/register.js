
const registerForm = document.getElementById('register-form')
const registerUsername = document.getElementById('register-username')
const registerPassword = document.getElementById('register-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/v1/users'


const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }

    const response = await fetch(`http://localhost:8080/api/v1/users/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers

    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()


        if (response.status === 200){
        console.log(responseArr[0])
        window.location.replace(responseArr[0])
    }
}

registerForm.addEventListener("submit", handleSubmit)

//quote generator
const text=document.getElementById("quote");
const author=document.getElementById("author");

const getNewQuote = async () =>
{
    var url="https://type.fit/api/quotes";

    const response=await fetch(url);
    console.log(typeof response);

    const allQuotes = await response.json();


    const indx = Math.floor(Math.random()*allQuotes.length);


    const quote=allQuotes[indx].text;


    const auth=allQuotes[indx].author;

    if(auth==null)
    {
        author = "Anonymous";
    }

    text.innerHTML=quote;
    author.innerHTML="~ "+auth;

}

getNewQuote();