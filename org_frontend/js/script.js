//Colocar esse DOMContentLoaded, porque primeiro vai carregar o script e depois a pagina html e da B.O, com esse DOM não acontece isso...
document.addEventListener("DOMContentLoaded", function() {
    const emailBorder = document.getElementById("email");
    const senhaBorder = document.getElementById("pass");

    //const divMail = document.getElementById("invalidMail");

    emailBorder.addEventListener("focus", function() {
        emailBorder.style.borderColor = "blue";
        //divMail.style.display="none";
    });

    emailBorder.addEventListener("blur", function(){
        emailBorder.style.borderColor="#ccc";
        //console.log("entrou")
    });


    senhaBorder.addEventListener("focus", function() {
        senhaBorder.style.borderColor = "blue";
        //divMail.style.display="none";
    });

    senhaBorder.addEventListener("blur", function(){
        senhaBorder.style.borderColor="#ccc";
        //console.log("entrou")
    });
});



// -------------------  CADASTRAR NO CONTROLE DE ACESSO

function JWT(event){
    event.preventDefault(); // Evita o envio padrão do formulário

    const URL = "http://localhost:8080/apis/adm/get-email";
    const email = document.getElementById("email").value;
    const senha = document.getElementById("pass").value;
    const token = localStorage.getItem('jwtToken');

    var data = {
        login: email,
        senha: senha,
        nome: "nometeste"
    };
    
    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(data)
    })
    .then(response => {

        return response.json(); // Processar os dados recebidos (assumindo que seja JSON)
    })
    .then(json =>{
        console.log(json);
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
}


async function testeJWT(event) 
{
    event.preventDefault();

    const URL = "http://localhost:8080/apis/security/logar/";
    var formLogin = document.getElementById("formLogin");
    const email = document.getElementById("email");
    const divMail = document.getElementById("invalidMail");
    const senha = document.getElementById("pass");
    const divPass = document.getElementById("invalidPass");
    const divStatus = document.getElementById("inativeStatus");
    const divParametrizacao = document.getElementById("semParametrizacao");

    try {

        const response = await fetch(URL, {
            method: 'POST', 
            body: new FormData(formLogin)
        });

        const json = await response.json();

        if(json.success === true) 
        {
            localStorage.setItem('jwtToken', json.token);


            if(json.controleAcesso.nivel === '0') 
            {
                const testeParametrizacao2 = await verificaParametrizacao();

                if(testeParametrizacao2===0)
                {
                    divParametrizacao.style.display = "block";
                    setTimeout(() => {
                        divParametrizacao.style.display = "none";
                        window.location.href="index.html";
                    }, 2200);
                }
                else
                    window.location.href="paginaUser.html";
                console.log("Parabéns, você logou como usuário");
            } 
            else 
            {
                const testeParametrizacao = await verificaParametrizacao();

                console.log("Resultado da parametrização: ", testeParametrizacao);

                if(testeParametrizacao === 0 || testeParametrizacao===null)//tabela vazia nada 
                {   
                    window.location.href = "cadastroParametrizacao.html";
                    console.log("Tabela vazia");
                }
                else
                    window.location.href = "paginaAdmin.html";

                console.log("Parabéns, você logou como admin");
            }
            console.log(json.token + " funciona");
        } 
        else 
        {
            if(json.message === "Email não encontrado") {
                email.style.borderColor = "red";
                divMail.style.display = "block";
                setTimeout(() => {
                    divMail.style.display = "none";
                }, 2200);
                console.log("Email não encontrado");
            } else if (json.message === "Senha incorreta") {
                senha.style.borderColor = "red";
                divPass.style.display = "block";
                setTimeout(() => {
                    divPass.style.display = "none";
                }, 2200);
                console.log("Senha incorreta");
            } else if (json.message === "Conta inativa") {
                divStatus.style.display = "block";
                setTimeout(() => {
                    divStatus.style.display = "none";
                }, 3500);
                console.log("Conta inativa");
            }
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

async function verificaParametrizacao() {
    const URL = "http://localhost:8080/apis/adm/get-parametrizacao";
    const token = localStorage.getItem('jwtToken');

    if (!token) {
        console.log('Token JWT não encontrado no localStorage');
        return null;
    }

    try {
        const response = await fetch(URL, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });
        const json = await response.json();
        console.log("ID recebido: ", json.id);
        return json.id;
    } catch (error) {
        console.error('Erro:', error);
        return null;
    }
}



function validarCampos()
{
    event.preventDefault();
    //console.log("teste");
    const email = document.getElementById("email");
    const divMail = document.getElementById("invalidMail");

    if(validarEmail(email.value))
    {
        //fazer a fetch com o backend para ver se a senha está correta
    }
    else
        {
            email.style.borderColor = "red";

            divMail.style.display="block";

            setTimeout(()=>{
                divMail.style.display="none";
            },2200);//esconde a div apos 2segundos e pouco
        }
}


function validarEmail(email)
{
    return /^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$/.test(email);
}



function testeToken()
{
    const token = localStorage.getItem('jwtToken');
    const decoded = jwt_decode(token);

    console.log("ESSE TOKEN É FODA MANE               "+token);

    console.log(decoded);
}