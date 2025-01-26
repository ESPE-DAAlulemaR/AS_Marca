import React, { useEffect, useState } from "react";
import { obtenerTransacciones } from "../api/transacciones";
import TransaccionesTable from "../components/TransaccionesTable";

const TransaccionesPage = () => {
    const [transacciones, setTransacciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await obtenerTransacciones();
                setTransacciones(data.content || []); // Verificar que la API devuelve `.content`
            } catch (err) {
                setError("No se pudieron cargar las transacciones");
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    return (
        <div>
            <h1>Lista de Transacciones</h1>
            {loading && <p>Cargando...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}
            {!loading && !error && <TransaccionesTable transacciones={transacciones} />}
        </div>
    );
};

export default TransaccionesPage;
