function filtrarEmail()
{
    const divEmail = document.getElementById("filtrarE");
    const divCPF = document.getElementById("filtrarC");

    divCPF.style.display="none";
    divEmail.style.display="block";
}

function filtrarCpf()
{
    const divEmail = document.getElementById("filtrarE");
    const divCPF = document.getElementById("filtrarC");

    divEmail.style.display="none";
    divCPF.style.display="block";

}



function consultarEmail()
{
    const URL = "http://localhost:8080/apis/user/get-Email";

    const token = localStorage.getItem('jwtToken');
    const nome = document.getElementById("nome");
    const senha = document.getElementById("passU");
    const button = document.getElementById("buttonU");
    const status = document.getElementById("status");
    const divAll = document.getElementById("divAll");
    var formU = document.getElementById("formU");
    var texto = document.getElementById("emailU").value;


    if(texto!="" && texto.includes("@") && texto.includes(".com"))
    {
        fetch(URL, {
            method: 'POST', 
            headers: {
                'Authorization': `Bearer ${token}`
            },
            body: new FormData(formU)
        })
        .then(resp => {

            if(resp.status===200)
            {
                resp.json().then(data =>{

                    console.log(data)

                    nome.value=data.nome;
                    passU.value=data.senha;
                    if(data.status=="A")
                        status.value="Ativo";
                    else
                        status.value="Inativo";
                   
                    divAll.style.display="block"
                    button.style.display="block";
                })
            }
            else
                if(resp.status===404)
                {
                    divEmail.style.display = "block";
                        setTimeout(() => {
                            divEmail.style.display = "none";
                        }, 3000);
                }
            
            //alert("Denuncia \u2022"+formDenuncia.elements["denName"].value+"\u2022 Concluida!");
            //window.location.href="cadastroParametrizacao.html";
            return console.log(resp);
        })
        .catch(error => {
            console.error('Erro na requisição:', error);
        });
    }
}