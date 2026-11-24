const ENTITIES = {
    herois: {
        label: "Heróis",
        endpoint: "/api/herois",
        fields: [
            { name: "nome", label: "Nome do Herói", type: "text", placeholder: "Ex: Ramattra", required: true },
            { name: "funcao", label: "Função", type: "select", options: ["Tanque", "Dano", "Suporte"], required: true },
            { name: "vidaBase", label: "Vida Base (HP)", type: "number", placeholder: "Ex: 450", required: true },
            { name: "habilidadeSuprema", label: "Habilidade Suprema (Ultimate)", type: "text", placeholder: "Ex: Aniquilação", required: true }
        ]
    },
    jogadores: {
        label: "Jogadores",
        endpoint: "/api/jogadores",
        fields: [
            { name: "battleTag", label: "BattleTag", type: "text", placeholder: "Ex: KaiMoralez#1234", required: true },
            { name: "nomeReal", label: "Nome Real", type: "text", placeholder: "Ex: Kaique", required: true },
            { name: "funcaoPrincipal", label: "Função Principal", type: "select", options: ["Tanque", "Dano", "Suporte", "Flex"], required: true },
            { name: "elo", label: "Elo Competitivo", type: "select", options: ["Bronze", "Prata", "Ouro", "Platina", "Diamante", "Mestre", "Grão-Mestre", "Campeão"], required: true }
        ]
    },
    equipes: {
        label: "Equipes",
        endpoint: "/api/equipes",
        fields: [
            { name: "nome", label: "Nome da Equipe", type: "text", placeholder: "Ex: Overwatch Brasil", required: true },
            { name: "sigla", label: "Sigla (Tag)", type: "text", placeholder: "Ex: OWBR", required: true },
            { name: "regiao", label: "Região", type: "select", options: ["América do Sul", "América do Norte", "Europa", "Ásia"], required: true },
            { name: "treinador", label: "Treinador (Coach)", type: "text", placeholder: "Nome do Coach", required: true }
        ]
    },
    mapas: {
        label: "Mapas",
        endpoint: "/api/mapas",
        fields: [
            { name: "nome", label: "Nome do Mapa", type: "text", placeholder: "Ex: King's Row", required: true },
            { name: "modoJogo", label: "Modo de Jogo", type: "select", options: ["Escolta", "Híbrido", "Controle", "Avanço", "Ponto de Flash"], required: true },
            { name: "pais", label: "País / Localização", type: "text", placeholder: "Ex: Reino Unido", required: true }
        ]
    },
    partidas: {
        label: "Partidas",
        endpoint: "/api/partidas",
        fields: [
            { name: "equipeA", label: "Equipe A", type: "text", placeholder: "Nome da Equipe A", required: true },
            { name: "equipeB", label: "Equipe B", type: "text", placeholder: "Nome da Equipe B", required: true },
            { name: "mapaJogado", label: "Mapa Jogado", type: "text", placeholder: "Ex: Ilios", required: true },
            { name: "placar", label: "Placar Final", type: "text", placeholder: "Ex: 3 x 1", required: true },
            { name: "dataPartida", label: "Data da Partida", type: "date", required: true }
        ]
    }
};

let currentEntityKey = "herois";
let currentRecords = [];

const statusBanner = document.getElementById("status-banner");
const formTitle = document.getElementById("form-title");
const listTitle = document.getElementById("list-title");
const entityForm = document.getElementById("entity-form");
const dynamicFields = document.getElementById("dynamic-fields");
const recordIdInput = document.getElementById("record-id");
const btnSubmit = document.getElementById("btn-submit");
const btnCancel = document.getElementById("btn-cancel");
const loadingState = document.getElementById("loading-state");
const emptyState = document.getElementById("empty-state");
const dataTable = document.getElementById("data-table");
const tableHead = document.getElementById("table-head");
const tableBody = document.getElementById("table-body");

function showStatus(message, type = "success") {
    statusBanner.textContent = message;
    statusBanner.className = `status-banner ${type}`;
    setTimeout(() => {
        statusBanner.classList.add("hidden");
    }, 4500);
}

function renderForm() {
    const config = ENTITIES[currentEntityKey];
    formTitle.textContent = `Cadastrar ${config.label}`;
    listTitle.textContent = `Lista de ${config.label}`;
    recordIdInput.value = "";
    btnSubmit.textContent = "Salvar";
    btnCancel.classList.add("hidden");

    dynamicFields.innerHTML = "";
    config.fields.forEach(field => {
        const group = document.createElement("div");
        group.className = "form-group";

        const label = document.createElement("label");
        label.setAttribute("for", field.name);
        label.textContent = field.label;

        let input;
        if (field.type === "select") {
            input = document.createElement("select");
            field.options.forEach(opt => {
                const option = document.createElement("option");
                option.value = opt;
                option.textContent = opt;
                input.appendChild(option);
            });
        } else {
            input = document.createElement("input");
            input.type = field.type;
            if (field.placeholder) input.placeholder = field.placeholder;
        }

        input.id = field.name;
        input.name = field.name;
        if (field.required) input.required = true;

        group.appendChild(label);
        group.appendChild(input);
        dynamicFields.appendChild(group);
    });
}

async function fetchRecords() {
    const config = ENTITIES[currentEntityKey];
    loadingState.classList.remove("hidden");
    emptyState.classList.add("hidden");
    dataTable.classList.add("hidden");

    try {
        const response = await fetch(config.endpoint);
        if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`);
        currentRecords = await response.json();
        renderTable();
    } catch (error) {
        showStatus(`Falha de conexão com a API: ${error.message}`, "error");
    } finally {
        loadingState.classList.add("hidden");
    }
}

function renderTable() {
    const config = ENTITIES[currentEntityKey];
    if (!currentRecords || currentRecords.length === 0) {
        emptyState.classList.remove("hidden");
        dataTable.classList.add("hidden");
        return;
    }

    emptyState.classList.add("hidden");
    dataTable.classList.remove("hidden");

    let headHtml = "<tr><th>ID</th>";
    config.fields.forEach(f => {
        headHtml += `<th>${f.label}</th>`;
    });
    headHtml += "<th>Ações</th></tr>";
    tableHead.innerHTML = headHtml;

    tableBody.innerHTML = "";
    currentRecords.forEach(item => {
        const tr = document.createElement("tr");
        let rowHtml = `<td>#${item.id}</td>`;
        config.fields.forEach(f => {
            rowHtml += `<td>${item[f.name] ?? "-"}</td>`;
        });
        rowHtml += `
            <td class="actions-cell">
                <button type="button" class="btn-edit" onclick="editRecord(${item.id})">Editar</button>
                <button type="button" class="btn-delete" onclick="deleteRecord(${item.id})">Excluir</button>
            </td>
        `;
        tr.innerHTML = rowHtml;
        tableBody.appendChild(tr);
    });
}

entityForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const config = ENTITIES[currentEntityKey];
    const id = recordIdInput.value;
    const payload = {};

    config.fields.forEach(f => {
        const val = document.getElementById(f.name).value;
        payload[f.name] = f.type === "number" ? Number(val) : val.trim();
    });

    const method = id ? "PUT" : "POST";
    const url = id ? `${config.endpoint}/${id}` : config.endpoint;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error("Verifique se todos os campos obrigatórios foram preenchidos corretamente.");
        }

        showStatus(id ? "Registro atualizado com sucesso!" : "Registro cadastrado com sucesso!", "success");
        renderForm();
        fetchRecords();
    } catch (error) {
        showStatus(`Erro na operação: ${error.message}`, "error");
    }
});

window.editRecord = function(id) {
    const record = currentRecords.find(r => r.id === id);
    if (!record) return;

    const config = ENTITIES[currentEntityKey];
    recordIdInput.value = record.id;
    formTitle.textContent = `Editando ${config.label} #${record.id}`;
    btnSubmit.textContent = "Atualizar";
    btnCancel.classList.remove("hidden");

    config.fields.forEach(f => {
        document.getElementById(f.name).value = record[f.name];
    });
};

window.deleteRecord = async function(id) {
    if (!confirm(`Deseja realmente excluir o registro #${id}?`)) return;
    const config = ENTITIES[currentEntityKey];

    try {
        const response = await fetch(`${config.endpoint}/${id}`, { method: "DELETE" });
        if (!response.ok) throw new Error(`Erro ao excluir (${response.status})`);
        showStatus("Registro excluído com sucesso!", "success");
        fetchRecords();
    } catch (error) {
        showStatus(error.message, "error");
    }
};

btnCancel.addEventListener("click", renderForm);
document.getElementById("btn-refresh").addEventListener("click", fetchRecords);

document.querySelectorAll(".tab-btn").forEach(btn => {
    btn.addEventListener("click", () => {
        document.querySelectorAll(".tab-btn").forEach(b => b.classList.remove("active"));
        btn.classList.add("active");
        currentEntityKey = btn.dataset.entity;
        renderForm();
        fetchRecords();
    });
});

renderForm();
fetchRecords();
