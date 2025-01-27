import axios from 'axios';
import { API_USERNAME, API_PASSWORD, API_BASE_URL } from '../config.js'; 

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
    auth: {
        username: API_USERNAME,
        password: API_PASSWORD,
    },
});
/*
api.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => Promise.reject(error)
);
*/
// Agregamos mejor manejo de errores y logging
export const getTransacciones = async (params) => {
    try {
        console.log('Solicitando transacciones con params:', params);
        const response = await api.get('/transacciones', { params });
        console.log('Respuesta recibida:', response.data);
        return response;
    } catch (error) {
        console.log('Error al obtener transacciones:', error);
        throw error;
    }
};