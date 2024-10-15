


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

let produtosSelecionados = [];

function adicionarProduto(event) {
    event.preventDefault();

    const nomeProduto = document.getElementById('produto').value.trim();
    const quantidade = parseInt(document.getElementById('quantidade').value);

    if (nomeProduto && quantidade > 0) {
        produtosSelecionados.push({ nome: nomeProduto, quantidade });

        document.getElementById('produto').value = '';
        document.getElementById('quantidade').value = '';

        atualizarListaProdutos();
    } else {
        alert('Por favor, informe um nome de produto e uma quantidade válida.');
    }
}

function atualizarListaProdutos() {
    const produtosListDiv = document.getElementById('produtosList');
    produtosListDiv.innerHTML = '';

    produtosSelecionados.forEach((produto, index) => {
        const produtoDiv = document.createElement('div');
        produtoDiv.textContent = `Produto: ${produto.nome}, Quantidade: ${produto.quantidade}`;
        produtoDiv.style.marginBottom = '5px';

        const removerButton = document.createElement('button');
        removerButton.textContent = 'Remover';
        removerButton.className = 'btn btn-small';
        removerButton.style.marginLeft = '10px';
        removerButton.onclick = () => removerProduto(index);

        produtoDiv.appendChild(removerButton);
        produtosListDiv.appendChild(produtoDiv);
    });
}

function removerProduto(index) {
    produtosSelecionados.splice(index, 1);
    atualizarListaProdutos();
}

/*
function cadastrarEntrega() {
    const tipo = document.getElementById('tipo').value.trim();
    const data = document.getElementById('data').value.trim();

    if (!tipo || !data) {
        alert('Por favor, preencha todos os campos da entrega.');
        return;
    }

    if (produtosSelecionados.length === 0) {
        alert('Adicione ao menos um produto à entrega.');
        return;
    }

    const entregaData = {
        tipo,
        data,
        produtos: produtosSelecionados
    };

    fetch('http://localhost:8080/apis/adm/add-entrega', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entregaData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            document.getElementById('divSucesso3').style.display = 'block';
            document.getElementById('divErro3').style.display = 'none';
            produtosSelecionados = [];
            atualizarListaProdutos();
        } else {
            throw new Error(data.message || 'Erro ao cadastrar a entrega.');
        }
    })
    .catch(error => {
        document.getElementById('divErro3').style.display = 'block';
        document.getElementById('divSucesso3').style.display = 'none';
        console.error('Erro:', error);
    });
}*/

function cadastrarE() {
    const URL = 'http://localhost:8080/apis/adm/add-entrega';
    const divSucesso3 = document.getElementById("divSucesso3");

    const idD = document.getElementById("idDonat").value;
    const idF = document.getElementById("idFunc").value;
    const tipo = document.getElementById("tipo").value;
    const data = document.getElementById("data").value;

    const produtos = produtosSelecionados.map(produto => ({
        idProduto: produto.idProduto, // Certifique-se de que isso está correto
        quantidade: produto.quantidade // Certifique-se de que isso está correto
    }));

    var inputData = new Date(data);
    var dataAtual = new Date();

    if (produtosSelecionados.length === 0) {
        alert('Adicione ao menos um produto à entrega.');
        return;
    }

    if (inputData >= dataAtual && tipo != "") {
        fetch(`${URL}?idF=${idF}&idD=${idD}&tipo=${tipo}&data=${data}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Define o tipo de conteúdo como JSON
            },
            body: JSON.stringify(produtos) // Envia apenas a lista de produtos no corpo
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(err => {
                    throw new Error(err);
                });
            }
            return response.json(); // Parse como JSON se a resposta for OK
        })
        .then(data => {
            console.log("Entrega adicionada com sucesso:", data);
            divSucesso3.style.display = "block";

            setTimeout(() => {
                divSucesso3.style.display = "none";
                window.location.href = "cadastroEntrega.html";
            }, 3000);
        })
        .catch(error => {
            console.error("Erro na requisição:", error);
        });
    } else {
        divErro3.style.display = "block";
        setTimeout(() => {
            divErro3.style.display = "none";
        }, 3000);
    }
}

