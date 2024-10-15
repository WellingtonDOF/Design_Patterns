//Parte de autocomplete e replace
document.addEventListener("DOMContentLoaded", function () {

    const campoCNPJ = document.querySelector('input[name="cnpjP"]');
    const campoCEP = document.querySelector('input[name="cepP"]');

    // Adicionar eventos de escuta aos campos
    campoCNPJ.addEventListener('input', function (event) {
        this.value = formatarCNPJ(event.target.value);
    });

    // Adicionar evento de input ao campo de CEP
    campoCEP.addEventListener('input', function (event) {
        let cep = event.target.value.replace(/\D/g, ''); // Remove caracteres não numéricos
        if (cep.length > 5) { // Adicionar traço após o 5º dígito
            cep = cep.replace(/^(\d{5})(\d)/, '$1-$2');
        }

        // Atualizar valor do campo com o CEP formatado
        this.value = cep;

        if (cep.length === 9) { // Quando o CEP atingir 8 dígitos, chamar a função buscarCEP
            cep = event.target.value.replace(/\D/g, '');
            buscarCEP(cep);
        }
    });
});


function formatarCNPJ(cnpj) {
    cnpj = cnpj.replace(/\D/g, '');
    return cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');
}



// Função para buscar dados do CEP via API
async function buscarCEP(cep) {
    const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
    const data = await response.json();

    if (data.erro) {
        console.log("CEP não encontrado.");
    } else {
        document.querySelector('input[name="enderecoP"]').value = data.logradouro;
        document.querySelector('input[name="bairroP"]').value = data.bairro;
        document.querySelector('input[name="cidadeP"]').value = data.localidade;
        document.querySelector('input[name="estadoP"]').value = data.uf;
    }
}

// Configurar evento de input para o campo de CPF
document.querySelector('input[name="cpfP"]').addEventListener('input', function (event) {
    this.value = formatarCPF(event.target.value);
});


// Configurar evento de blur para o campo de CEP
document.querySelector('input[name="cepP"]').addEventListener('blur', function (event) {
    buscarCEP(this.value);
});


document.addEventListener("DOMContentLoaded", function () {
    const razaoBorder = document.getElementById("razaoP");
    const nomeBorder = document.getElementById("nomeP");  
    const cnpjBorder = document.getElementById("cnpjP");
    const emailBorder = document.getElementById("emailP");
    const siteBorder = document.getElementById("siteP");
    const cepBorder =document.getElementById("cepP");
    const cidadeBorder = document.getElementById("cidadeP");
    const estadoBorder = document.getElementById("estadoP");
    const enderecoBorder = document.getElementById("enderecoP");
    const bairroBorder =document.getElementById("bairroP");

    adicionarEventos(razaoBorder);
    adicionarEventos(nomeBorder);
    adicionarEventos(cnpjBorder);
    adicionarEventos(emailBorder);
    adicionarEventos(siteBorder);
    adicionarEventos(cepBorder);
    adicionarEventos(cidadeBorder);
    adicionarEventos(estadoBorder);
    adicionarEventos(enderecoBorder);
    adicionarEventos(bairroBorder);
});


function cadastrarParametrizacao(event) {

    event.preventDefault();

    const razaoP = document.getElementById("razaoP");
    const nomeP = document.getElementById("nomeP");  
    const cnpjP = document.getElementById("cnpjP");
    const emailP = document.getElementById("emailP");
    const siteP = document.getElementById("siteP");
    const cepP =document.getElementById("cepP");
    const cidadeP = document.getElementById("cidadeP");
    const estadoP = document.getElementById("estadoP");
    const enderecoP = document.getElementById("enderecoP");
    const bairroP =document.getElementById("bairroP");
    const arquivo = document.getElementById("arquivo1");
    const arquivo2 = document.getElementById("arquivo2");


    if(!arquivo.files[0] || arquivo.files[0]===0)
        console.log("vazio")

    const divRazao = document.getElementById("divRazao");
    const divNome = document.getElementById("divNome");
    const divEmail = document.getElementById("divEmail");
    const divCNPJ = document.getElementById("divCNPJ");
    const divSite = document.getElementById("divSite");
    const divCEP = document.getElementById("divCEP");
    const divCidade = document.getElementById("divCidade");
    const divEndereco = document.getElementById("divEndereco");
    const divBairro = document.getElementById("divBairro");
    const divEstado = document.getElementById("divEstado");
    const divSucesso = document.getElementById("divSucesso");
    const divArq1 = document.getElementById("divArq1");
    const divArq2 = document.getElementById("divArq2");


    if (razaoP.value.trim() === '') {
        mostrarAviso(razaoP, divRazao);
        adicionarEventos(razaoP);
    } else if (nomeP.value.trim() === '') {
        mostrarAviso(nomeP, divNome);
        adicionarEventos(nomeP);
    } else if (cnpjP.value.trim() === '' || !validarCNPJ(cnpjP.value)) {
        mostrarAviso(cnpjP, divCNPJ);
        adicionarEventos(cnpjP);
    } else if (emailP.value.trim() === '' || !validarEmail(emailP.value)) {
        mostrarAviso(emailP, divEmail);
        adicionarEventos(emailP);
    } else if (siteP.value.trim() === '') {
        mostrarAviso(siteP, divSite);
        adicionarEventos(siteP);
    } else if (cepP.value.trim() === '' || cepP.value.length<9) {
        mostrarAviso(cepP, divCEP);
        adicionarEventos(cepP);
    } else if (cidadeP.value.trim() === '') {
        mostrarAviso(cidadeP, divCidade);
        adicionarEventos(cidadeP);
    } else if (estadoP.value.trim() === '') {
        mostrarAviso(estadoP, divEstado);
        adicionarEventos(estadoP);
    } else if (enderecoP.value.trim() === '') {
        mostrarAviso(enderecoP, divEndereco);
        adicionarEventos(enderecoP);
    } else if (bairroP.value.trim() === '') {
        mostrarAviso(bairroP, divBairro);
        adicionarEventos(bairroP);
    } 
    else if(!arquivo.files[0] || arquivo.files[0]===0){
        mostrarAviso(arquivo,divArq1);
        adicionarEventos(arquivo);
    }
    else if(!arquivo2.files[0] || arquivo2.files[0]===0){
        mostrarAviso(arquivo2,divArq2);
        adicionarEventos(arquivo2);
    }
    else
    {
        // Todos os campos estão preenchidos e certos!
        setParametrizacao();
        divSucesso.style.display = "block";
        setTimeout(() => {
            divSucesso.style.display = "none";
            //window.location.href = "index.html";
        }, 3000); // esconde a div após 3 segundos
        console.log('Todos os campos estão preenchidos corretamente');
    }
}


function deletarAcessoU()
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



function produtoPesquisar()
{
    const URL = "http://localhost:8080/apis/adm/get-produto-estoque";

    const token = localStorage.getItem('jwtToken');
    const nome = document.getElementById("nome");
    const tipo = document.getElementById("tipo");
    const button = document.getElementById("buttonU");
    const valor = document.getElementById("valor");
    const peso = document.getElementById("peso");
    const estoque = document.getElementById("estoque");
    const estoqueId = document.getElementById("estoqueId");

    const divAll = document.getElementById("divAll");
    var formPD = document.getElementById("formPD");

        fetch(URL, 
        {
            method: 'POST', 
            headers: {
            },
            body: new FormData(formPD)
        })
        .then(resp => 
        {
            if(resp.status===200)
            {
                resp.json().then(data =>{

                    console.log(data)
                    nome.value=data.nome;
                    tipo.value=data.tipo;
                    valor.value=data.valor;
                    peso.value=data.peso;
                    estoque.value=data.id_estoque.quantidade;
                    estoqueId.value=data.id_estoque.id;
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

function aumentarEstoque() {
    const estoqueInput = document.getElementById('estoque');
    estoqueInput.value = parseInt(estoqueInput.value) + 1;
}

function diminuirEstoque() {
    
    const estoqueInput = document.getElementById('estoque');
    if (parseInt(estoqueInput.value) > 0) {
        estoqueInput.value = parseInt(estoqueInput.value) - 1;
    } else {
        alert("O estoque não pode ser negativo!");
    }
}
function confirmarEstoque(event) {
    event.preventDefault(); // Previne o comportamento padrão do botão
    const URL = "http://localhost:8080/apis/adm/alterar-estoque";

    const estoqueInput = document.getElementById('estoque');
    const estoqueAtual = parseInt(estoqueInput.value);
    const estoqueId = document.getElementById('estoqueId').value; // Obtém o ID do produto

    const requestUrl = `${URL}?estoque=${estoqueAtual}&id=${estoqueId}`; // Monta a URL com os parâmetros

    fetch(requestUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        document.getElementById('divSucesso').style.display = 'block';
    })
    .catch(error => {
        console.error('Erro:', error);
        document.getElementById('divErro').style.display = 'block';
    });
}


function inativarUsuario()
{
    const URL = "http://localhost:8080/apis/user/deletar-User";

    const token = localStorage.getItem('jwtToken');
    const divErro = document.getElementById("divErro");
    const divSucesso = document.getElementById("divSucesso");

    var formU = document.getElementById("formU");
    
    var resposta = confirm("Tem certeza que deseja excluir?");
    if(resposta) 
    {
        fetch(URL, {
            method: 'POST', 
            headers: {
                'Authorization': `Bearer ${token}`
            },
            body: new FormData(formU)
        })
        .then(response => {
            if(response.ok) 
            {
                divSucesso.style.display = "block";
                    setTimeout(() => {
                        divSucesso.style.display = "none";
                        //window.location.href = "index.html";
                    }, 3000); // esconde a div após 3 segundos
            }
            else 
                if(response.status === 404) 
                {
                    divErro.style.display = "block";
                    setTimeout(() => {
                        divErro.style.display = "none";
                    }, 3000); 
                } 
                else 
                {
                    console.error("Erro ao excluir usuário:", response.status);
                    divErro.style.display = "block";
                    setTimeout(() => {
                        divErro.style.display = "none";
                    }, 3000);
                }
        })
        .catch(error => {
            console.error('Erro na requisição:', error);
        });
    }
    else
        alert("Exclusão cancelada!");

}


function desativarCaixas()
{
    const nome = document.getElementById("nome");
    const senha = document.getElementById("passU");
    const button = document.getElementById("buttonU");
    const divAll = document.getElementById("divAll");
    
    divAll.style.display="none";
    button.style.display="none";
    document.getElementById("emailU").value="";
}


function setParametrizacao() {
    //event.preventDefault();
    const URL = "http://localhost:8080/apis/adm/add-parametrizacao";
    var formP = document.getElementById("formP");
    const token = localStorage.getItem('jwtToken');

    console.log("entrou aqui");
    
    fetch(URL, {
        method: 'POST', 
        headers: {
            'Authorization': `Bearer ${token}`
        },
        body: new FormData(formP)
    })
    .then(resp => {
        //alert("Denuncia \u2022"+formDenuncia.elements["denName"].value+"\u2022 Concluida!");
        window.location.href="cadastroParametrizacao.html";
        return console.log(resp);
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
}

function mostrarAviso(elemento, div)
{
    elemento.style.borderColor = "red";
    div.style.display="block";
    setTimeout(()=>{
        div.style.display="none";
    },2200);//esconde a div apos 2segundos e pouco
}

function adicionarEventos(elemento) {
    elemento.addEventListener("focus", function () {
        elemento.style.borderColor = "blue";
        // Código adicional que você pode querer executar quando o elemento recebe foco
    });

    elemento.addEventListener("blur", function () {
        elemento.style.borderColor = "#ccc";
        // Código adicional que você pode querer executar quando o elemento perde foco
    });
}


function validarEmail(email)
{
    return /^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$/.test(email);
}

function validarCNPJ(cnpj) {
    // Remove caracteres não numéricos
    cnpj = cnpj.replace(/\D/g, '');

    // Verifica se o CNPJ possui 14 dígitos após limpar não numéricos
    if (cnpj.length !== 14) {
        return false;
    }

    // Verifica se todos os dígitos são iguais (caso especial de CNPJ inválido)
    if (/^(\d)\1{13}$/.test(cnpj)) {
        return false;
    }

    // Calcula o primeiro dígito verificador
    let tamanho = cnpj.length - 2;
    let numeros = cnpj.substring(0, tamanho);
    let digitos = cnpj.substring(tamanho);
    let soma = 0;
    let pos = tamanho - 7;
    for (let i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2) {
            pos = 9;
        }
    }
    let resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0)) {
        return false;
    }

    // Calcula o segundo dígito verificador
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (let i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2) {
            pos = 9;
        }
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1)) {
        return false;
    }

    // Se passou por todas as verificações, o CNPJ é válido
    return true;
}



// -------------------------- ALTERAÇÃO DA TABELA AQUI !! --------------------------------------- //

function getParametrizacao(event)
{
    
    event.preventDefault();

    const URL = "http://localhost:8080/apis/adm/get-parametrizacao";
    //const cnpj = document.getElementById("cnpjP");
    var formDenuncia = document.getElementById("formDenuncia");
    const token = localStorage.getItem('jwtToken');

    fetch(URL, {
        method: 'GET', 
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
            }
    })
    .then(resp => {
        //alert("Denuncia \u2022"+formDenuncia.elements["denName"].value+"\u2022 Concluida!");
        return resp.json();
    })
    .then(json =>{

        document.getElementById("razaoP").value=json.razaoSocial;
        document.getElementById("nomeP").value=json.nomeFantasia;  
        document.getElementById("cnpjP").value=json.cnpj;
        document.getElementById("emailP").value=json.email;
        document.getElementById("siteP").value=json.site;
        document.getElementById("cepP").value=json.cep;
        document.getElementById("cidadeP").value=json.cidade;
        document.getElementById("estadoP").value=json.estado;
        document.getElementById("enderecoP").value=json.rua;
        document.getElementById("bairroP").value=json.bairro;
        //document.getElementById("arquivo1").value=json.logotipoG;
        //document.getElementById("arquivo2").value=json.logotipoP;
        console.log(json);
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
}


