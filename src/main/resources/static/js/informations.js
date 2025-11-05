const apiUrl = "/informations";
const sectorApi = "/sectors";
const tableBody = document.querySelector("#infoTable tbody");
const modal = document.getElementById("infoFormModal");
const form = document.getElementById("infoForm");
const sectorSelect = document.getElementById("sectorId");

document.getElementById("newInfoBtn").addEventListener("click", () => openModal());
document.getElementById("cancelBtn").addEventListener("click", closeModal);

function openModal(info = null) {
  modal.classList.remove("hidden");
  form.reset();

  if (info) {
    document.getElementById("modalTitle").textContent = "Editar Informação";
    document.getElementById("infoId").value = info.id;
    document.getElementById("title").value = info.title;
    document.getElementById("description").value = info.description;
    document.getElementById("type").value = info.type;
    if (info.sectorId?.id) sectorSelect.value = info.sectorId.id;
  } else {
    document.getElementById("modalTitle").textContent = "Nova Informação";
  }
}

function closeModal() {
  modal.classList.add("hidden");
}

async function loadSectors() {
  const res = await fetch(sectorApi);
  const sectors = await res.json();
  sectorSelect.innerHTML = "<option value=''>Selecione um setor...</option>";
  sectors.forEach(s => {
    const opt = document.createElement("option");
    opt.value = s.id;
    opt.textContent = `${s.acronym || ""} - ${s.name}`;
    sectorSelect.appendChild(opt);
  });
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const id = document.getElementById("infoId").value;
  const data = {
    title: document.getElementById("title").value,
    description: document.getElementById("description").value,
    type: document.getElementById("type").value,
    sectorId: parseInt(sectorSelect.value)
  };

  const method = id ? "PUT" : "POST";
  const url = id ? `${apiUrl}/${id}` : apiUrl;

  await fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });

  closeModal();
  loadInformations();
});

async function loadInformations() {
  const res = await fetch(apiUrl);
  const infos = await res.json();
  tableBody.innerHTML = "";
  infos.forEach(i => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${i.id}</td>
      <td>${i.title}</td>
      <td>${i.description}</td>
      <td>${i.type || "-"}</td>
      <td>${i.sectorId?.name || "-"}</td>
      <td>
        <button class="btn secondary edit-btn">✏️</button>
        <button class="btn danger delete-btn">🗑️</button>
      </td>
    `;
    row.querySelector(".edit-btn").addEventListener("click", () => openModal(i));
    row.querySelector(".delete-btn").addEventListener("click", () => deleteInformation(i.id));
    tableBody.appendChild(row);
  });
}

async function deleteInformation(id) {
  if (!confirm("Deseja excluir esta informação?")) return;
  await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
  loadInformations();
}

// inicialização
loadSectors().then(loadInformations);
