// src/components/TransaccionItem.jsx
import React from 'react';
import { Badge } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const estadoColor = (estado) => {
  switch (estado) {
    case 'PEN':
      return 'warning';
    case 'APR':
      return 'success';
    case 'REC':
      return 'danger';
    case 'REV':
      return 'secondary';
    default:
      return 'primary';
  }
};

const estadoTexto = (estado) => {
  switch (estado) {
    case 'PEN':
      return 'Pendiente';
    case 'APR':
      return 'Aprobada';
    case 'REC':
      return 'Rechazada';
    case 'REV':
      return 'Reversada';
    default:
      return estado;
  }
};

const TransaccionItem = ({ transaccion }) => {
  const tarjetaNumero = transaccion.tarjeta?.numero || '************';
  const ultimo4Digitos = tarjetaNumero.slice(-4);

  return (
    <tr>
      <td>{transaccion.id}</td>
      <td>**** **** **** {ultimo4Digitos}</td>
      <td>
        <Badge bg={estadoColor(transaccion.estado)}>
          {estadoTexto(transaccion.estado)}
        </Badge>
      </td>
      <td>{new Date(transaccion.fechaHora).toLocaleString()}</td>
      <td>${transaccion.valor.toFixed(2)}</td>
      <td>${transaccion.comision.toFixed(2)}</td>
      <td>{transaccion.esDiferido ? 'Sí' : 'No'}</td>
      <td>{new Date(transaccion.fechaActualizacion).toLocaleString()}</td>
    </tr>
  );
};

export default TransaccionItem;
