const apiUrl = "/sectors";
const tableBody = document.querySelector("#sectorTable tbody");
const modal = document.getElementById("sectorFormModal");
const form = document.getElementById("sectorForm");
const addDayBtn = document.getElementById("addDayBtn");
const hoursContainer = document.getElementById("hoursContainer");

document.getElementById("newSectorBtn").addEventListener("click", () => openModal());
document.getElementById("cancelBtn").addEventListener("click", closeModal);

function openModal(sector = null) {
  modal.classList.remove("hidden");
  form.reset();
  hoursContainer.innerHTML = "";

  if (sector) {
    document.getElementById("modalTitle").textContent = "Editar Setor";
    document.getElementById("sectorId").value = sector.id;
    document.getElementById("acronym").value = sector.acronym;
    document.getElementById("name").value = sector.name;
    document.getElementById("desc").value = sector.description || "";
    document.getElementById("lat").value = sector.lat || "";
    document.getElementById("longi").value = sector.longi || "";
    document.getElementById("build").value = sector.build || "";
    document.getElementById("room").value = sector.room || "";
    document.getElementById("responsibleId").value = sector.responsible?.id || "";

    if (sector.operatingHours?.days) {
      Object.entries(sector.operatingHours.days).forEach(([day, ranges]) => {
        addDayBlock(day, ranges);
      });
    }
  } else {
    document.getElementById("modalTitle").textContent = "Novo Setor";
  }
}

function closeModal() {
  modal.classList.add("hidden");
}

function addDayBlock(day = "", ranges = [{ open: "", close: "" }]) {
  const block = document.createElement("div");
  block.className = "day-block";

  const dayLabel = document.createElement("input");
  dayLabel.placeholder = "Dia da Semana (ex: segunda)";
  dayLabel.value = day;

  const removeDay = document.createElement("button");
  removeDay.textContent = "❌";
  removeDay.className = "btn danger";
  removeDay.addEventListener("click", () => block.remove());

  block.append(dayLabel, removeDay);

  const rangeContainer = document.createElement("div");
  ranges.forEach(r => addTimeRange(rangeContainer, r.open, r.close));
  block.appendChild(rangeContainer);

  const addRangeBtn = document.createElement("button");
  addRangeBtn.textContent = "+ Adicionar Horário";
  addRangeBtn.className = "btn secondary";
  addRangeBtn.addEventListener("click", () => addTimeRange(rangeContainer));
  block.appendChild(addRangeBtn);

  hoursContainer.appendChild(block);
}

function addTimeRange(container, open = "", close = "") {
  const div = document.createElement("div");
  div.className = "time-range";
  div.innerHTML = `
    <input type="time" class="open" value="${open}">
    <input type="time" class="close" value="${close}">
    <button class="btn danger remove-range">🗑️</button>
  `;
  div.querySelector(".remove-range").addEventListener("click", () => div.remove());
  container.appendChild(div);
}

addDayBtn.addEventListener("click", () => addDayBlock());

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const id = document.getElementById("sectorId").value;

  const operatingHours = { days: {} };
  document.querySelectorAll(".day-block").forEach(block => {
    const day = block.querySelector("input").value.trim();
    if (!day) return;
    const ranges = [...block.querySelectorAll(".time-range")].map(div => ({
      open: div.querySelector(".open").value,
      close: div.querySelector(".close").value
    }));
    operatingHours.days[day] = ranges;
  });

  const data = {
    acromyn: document.getElementById("acronym").value,
    name: document.getElementById("name").value,
    desc: document.getElementById("desc").value,
    lat: document.getElementById("lat").value,
    longi: document.getElementById("longi").value,
    build: document.getElementById("build").value,
    room: document.getElementById("room").value,
    responsibleId: document.getElementById("responsibleId").value,
    operatingHours
  };

  const method = id ? "PUT" : "POST";
  const url = id ? `${apiUrl}/${id}` : apiUrl;

  await fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });

  closeModal();
  loadSectors();
});

async function loadSectors() {
  const res = await fetch(apiUrl);
  const sectors = await res.json();
  tableBody.innerHTML = "";
  sectors.forEach(s => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${s.id}</td>
      <td>${s.acronym}</td>
      <td>${s.name}</td>
      <td>${s.responsible?.name || "-"}</td>
      <td>${s.build || "-"}</td>
      <td>${s.room || "-"}</td>
      <td>
        <button class="btn secondary edit-btn">✏️</button>
        <button class="btn danger delete-btn">🗑️</button>
      </td>
    `;
    row.querySelector(".edit-btn").addEventListener("click", () => openModal(s));
    row.querySelector(".delete-btn").addEventListener("click", () => deleteSector(s.id));
    tableBody.appendChild(row);
  });
}

async function deleteSector(id) {
  if (!confirm("Tem certeza que deseja deletar este setor?")) return;
  await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
  loadSectors();
}

loadSectors();
