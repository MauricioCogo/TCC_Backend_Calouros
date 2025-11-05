const API_URL = "http://localhost:8080/responsibles";

async function loadResponsibles() {
    const response = await fetch(API_URL);
    const data = await response.json();

    const tableBody = document.getElementById("responsibleTableBody");
    tableBody.innerHTML = "";

    data.forEach(r => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${r.id}</td>
            <td><input type="text" value="${r.name}" class="editable" data-id="${r.id}" data-field="name"></td>
            <td><input type="email" value="${r.email}" class="editable" data-id="${r.id}" data-field="email"></td>
            <td><input type="text" value="${r.role}" class="editable" data-id="${r.id}" data-field="role"></td>
            <td>
                <button class="delete-btn" onclick="deleteResponsible(${r.id})">🗑️</button>
                <button class="update-btn" onclick="updateResponsible(${r.id})">💾</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function addResponsible(event) {
    event.preventDefault();
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const role = document.getElementById("role").value;

    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, email, role })
    });

    document.getElementById("addForm").reset();
    loadResponsibles();
}

async function updateResponsible(id) {
    const inputs = document.querySelectorAll(`.editable[data-id='${id}']`);
    const updated = {};
    inputs.forEach(input => updated[input.dataset.field] = input.value);

    await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updated)
    });

    loadResponsibles();
}

async function deleteResponsible(id) {
    if (confirm("Tem certeza que deseja excluir este responsável?")) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        loadResponsibles();
    }
}

document.getElementById("addForm").addEventListener("submit", addResponsible);
loadResponsibles();
