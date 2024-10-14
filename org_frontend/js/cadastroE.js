


//Parte de autocomplete e replace
document.addEventListener("DOMContentLoaded", function () {

    const campoCPF = document.querySelector('input[name="cpf"]');

    // Adicionar eventos de escuta aos campos
    campoCPF.addEventListener('input', function (event) {

        const cpfFormatado = formatarCPF(event.target.value);
        this.value = cpfFormatado;


        if(cpfFormatado.length===14)
            funcionarioE();
    });
});

document.addEventListener("DOMContentLoaded", function () {

    const campoCPF2 = document.querySelector('input[name="cpf2"]');

    // Adicionar eventos de escuta aos campos
    campoCPF2.addEventListener('input', function (event) {

        const cpfFormatado2 = formatarCPF(event.target.value);
        this.value = cpfFormatado2;

        if(cpfFormatado2.length===14)
            donatarioE();
    });
});



// Função para formatar o CPF
function formatarCPF(cpf) {
    cpf = cpf.replace(/\D/g, '');
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
}

function funcionarioE()
{
    const URL = "http://localhost:8080/apis/adm/get-funcionario-cpf";

    const token = localStorage.getItem('jwtToken');
    const nome = document.getElementById("nome");
    const email = document.getElementById("email");
    const cidade = document.getElementById("cidade");
    const idE = document.getElementById("idFunc");
    const divAll = document.getElementById("divAll");
    const divCPF = document.getElementById("divCPF");
    const divGeralD = document.getElementById("divGeralD");
    const divGeralF = document.getElementById("divGeralF");
    var formU = document.getElementById("formU");
    const idD = document.getElementById("idDonat");

   
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

                    nome.value=data.nome;
                    email.value=data.email;
                    cidade.value=data.cidade;
                    idE.value=data.id;


                    divGeralD.style.display="block"
                    divAll.style.display="block"
                    
                    if(idD.value>0)
                        divGeralF.style.display="block"
                    
                    console.log(idE.value);
                    mostrarDados()
                })
            }
            else
                if(resp.status===404)
                {
                    idE.value=0;
                    divCPF.style.display = "block";
                    divGeralF.style.display="none"

                    limparDados();

                    setTimeout(() => {
                        divCPF.style.display = "none";
                        document.getElementById("cpf").value=""; 
                        esconderDados();   
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

function donatarioE()
{
    const URL = "http://localhost:8080/apis/adm/get-donatario-cpf";

    const token = localStorage.getItem('jwtToken');
    const nome = document.getElementById("nome2");
    const telefone = document.getElementById("telefone2");
    const cidade = document.getElementById("cidade2");
    const idD = document.getElementById("idDonat");
    const idF = document.getElementById("idFunc");
    const divGeralF = document.getElementById("divGeralF");
    const divAll = document.getElementById("divAll2");
    const divCPF = document.getElementById("divCPF2");
    var formU = document.getElementById("formU2");

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
                    telefone.value=data.telefone;
                    cidade.value=data.cidade;
                    idD.value=data.id;
                    divAll.style.display="block"
                    mostrarDados('buttonM2')
                    console.log(idD.value);

                    if(idF.value>0)
                        divGeralF.style.display="block";
                })
            }
            else
                if(resp.status===404)
                {
                    divCPF.style.display = "block";
                    idD.value=0;
                    if(idD.value==0)
                        divGeralF.style.display="none";

                    limparDados("donatario");
                        setTimeout(() => {
                            divCPF.style.display = "none";
                            esconderDados('buttonE2');      
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


function mostrarDados(text)
{
    if(text==="buttonM2")
    {
        const divAll = document.getElementById("divAll2");
        divAll.style.display="block";
    
        document.getElementById("buttonM2").style.display="none";
        document.getElementById("buttonE2").style.display="block"
    }
    else
        {
            const divAll = document.getElementById("divAll");
            divAll.style.display="block";
        
            document.getElementById("buttonM").style.display="none";
            document.getElementById("buttonE").style.display="block";
        }
}

function esconderDados(text)
{
    if(text=="buttonE2")
    {
        const divAll = document.getElementById("divAll2");
        divAll.style.display="none";
    
        document.getElementById("buttonM2").style.display="block";
        document.getElementById("buttonE2").style.display="none";
    }
    else
    {
        const divAll = document.getElementById("divAll");
        divAll.style.display="none";
    
        document.getElementById("buttonM").style.display="block";
        document.getElementById("buttonE").style.display="none";
    }
   
}

function limparDados(text)
{
    if(text=="donatario")
    {
        document.getElementById("nome2").value="";
        document.getElementById("telefone2").value="";
        document.getElementById("cidade2").value="";
    }
    else
    {
        document.getElementById("nome").value="";
        document.getElementById("email").value="";
        document.getElementById("cidade").value="";
    }
}


function cadastrarE()
{

    const URL = 'http://localhost:8080/apis/adm/add-entrega';

    const divSucesso3 = document.getElementById("divSucesso3");
    const data = {
        idD: document.getElementById("idDonat").value,
        idF: document.getElementById("idFunc").value,
        tipo: document.getElementById("tipo").value,
        data: document.getElementById("data").value
    };

    var inputData = new Date(document.getElementById("data").value);
    var dataAtual = new Date();

    const formData = new FormData();
    formData.append('idD', data.idD);
    formData.append('idF', data.idF);
    formData.append('tipo', data.tipo);
    formData.append('data', data.data);

    
    if(inputData>=dataAtual && document.getElementById("tipo").value!="")
    {
        fetch(URL, {
            method: 'POST',
            body: formData
        })
        .then(response => {
        if(response.ok) 
        {
            console.log("Entrega adicionada com sucesso");
            divSucesso3.style.display = "block";

            setTimeout(() => {
                divSucesso3.style.display = "none";
                window.location.href="cadastroEntrega.html"
            }, 3000);
        }
        else 
        {
            console.error("Erro ao adicionar entrega");
        }
        })
        .catch(error => {
            console.error("Erro na requisição:", error);
        });
    }
    else
    {
        divErro3.style.display = "block";
        setTimeout(() => {
            divErro3.style.display = "none";
        }, 3000);
    }
}