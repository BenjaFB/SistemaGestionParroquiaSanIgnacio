// ============================================================
// api.js — Conexión Frontend <-> Backend (MONOLITO, mismo origen)
// Parroquia San Ignacio — todo corre en http://localhost:8080
// ============================================================

const API = {
    USUARIOS:       "/api/usuarios",
    DONACIONES:     "/api/donaciones",
    INVENTARIO:     "/api/inventario",
    EVENTOS:        "/api/eventos",
    INSTITUCION:    "/api/institucion",
    NOTIFICACIONES: "/api/notificaciones",
    REPORTES:       "/api/reportes"
};

// Helper genérico: apiFetch(url, metodo, bodyOpcional)
async function apiFetch(url, method = "GET", body = null) {
    const options = {
        method,
        headers: { "Content-Type": "application/json" }
    };
    if (body) options.body = JSON.stringify(body);

    const response = await fetch(url, options);
    const text = await response.text();

    if (!response.ok) {
        let msg = text;
        try { msg = JSON.parse(text).error || JSON.parse(text).message || text; } catch (e) {}
        throw new Error(msg || `Error ${response.status}`);
    }

    try { return JSON.parse(text); } catch (e) { return text; }
}

// --- Sesión ---
function guardarSesion(usuario) {
    sessionStorage.setItem("usuario", JSON.stringify(usuario));
}
function obtenerSesion() {
    const data = sessionStorage.getItem("usuario");
    return data ? JSON.parse(data) : null;
}
function cerrarSesion() {
    sessionStorage.removeItem("usuario");
    window.location.href = "/index.html";
}
