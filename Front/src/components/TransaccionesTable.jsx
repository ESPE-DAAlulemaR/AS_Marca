// src/components/TransaccionList.jsx
import React, { useEffect, useState } from 'react';
import { getTransacciones } from '../api/transacciones';
import TransaccionItem from './TransaccionItem';
import { Table, Pagination, Form, Button, Row, Col, Alert, Spinner } from 'react-bootstrap';

const TransaccionList = () => {
  const [transacciones, setTransacciones] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [filters, setFilters] = useState({
    estado: '',
    fechaDesde: '',
    fechaHasta: '',
    numeroTarjeta: '',
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchTransacciones();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentPage, filters]);

  const fetchTransacciones = async () => {
    setLoading(true);
    setError('');
    try {
      const params = {
        page: currentPage - 1, // Spring Boot usa páginas base 0
        size: 20,
        sort: 'fechaHora,desc',
        ...filters,
      };
      const response = await getTransacciones(params);
      console.log('Transacciones obtenidas:', response.data.content); // Nuevo log
      setTransacciones(response.data.content);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.error('Error al obtener transacciones:', error);
      if (error.response) {
        setError(`Error ${error.response.status}: ${error.response.data.message || 'No se pudo cargar las transacciones.'}`);
      } else if (error.request) {
        setError('No se recibió respuesta del servidor. Verifique su conexión.');
      } else {
        setError('Error al configurar la solicitud.');
      }
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleFilterChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value,
    });
  };

  const handleFilterSubmit = (e) => {
    e.preventDefault();
    setCurrentPage(1);
    fetchTransacciones();
  };

  return (
    <div className="container mt-4">
      <h2>Listado de Transacciones</h2>
      
      {/* Filtros */}
      <Form onSubmit={handleFilterSubmit} className="mb-4">
        <Row>
          <Col md={3}>
            <Form.Group controlId="estado">
              <Form.Label>Estado</Form.Label>
              <Form.Control
                as="select"
                name="estado"
                value={filters.estado}
                onChange={handleFilterChange}
              >
                <option value="">Todos</option>
                <option value="PEN">Pendiente</option>
                <option value="APR">Aprobada</option>
                <option value="REC">Rechazada</option>
                <option value="REV">Reversada</option>
              </Form.Control>
            </Form.Group>
          </Col>
          <Col md={3}>
            <Form.Group controlId="fechaDesde">
              <Form.Label>Fecha Desde</Form.Label>
              <Form.Control
                type="datetime-local"
                name="fechaDesde"
                value={filters.fechaDesde}
                onChange={handleFilterChange}
              />
            </Form.Group>
          </Col>
          <Col md={3}>
            <Form.Group controlId="fechaHasta">
              <Form.Label>Fecha Hasta</Form.Label>
              <Form.Control
                type="datetime-local"
                name="fechaHasta"
                value={filters.fechaHasta}
                onChange={handleFilterChange}
              />
            </Form.Group>
          </Col>
          <Col md={3}>
            <Form.Group controlId="numeroTarjeta">
              <Form.Label>Número de Tarjeta</Form.Label>
              <Form.Control
                type="text"
                name="numeroTarjeta"
                value={filters.numeroTarjeta}
                onChange={handleFilterChange}
                placeholder="Ej: 4111111111111111"
              />
            </Form.Group>
          </Col>
        </Row>
        <Button variant="primary" type="submit" className="mt-3">
          Filtrar
        </Button>
      </Form>

      {/* Mostrar Spinner de Carga */}
      {loading && (
        <div className="text-center my-4">
          <Spinner animation="border" role="status">
            <span className="visually-hidden">Cargando...</span>
          </Spinner>
        </div>
      )}

      {/* Mostrar Mensaje de Error */}
      {error && <Alert variant="danger">{error}</Alert>}

      {/* Tabla de Transacciones */}
      {!loading && !error && (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>ID</th>
              <th>Tarjeta</th>
              <th>Estado</th>
              <th>Fecha y Hora</th>
              <th>Valor</th>
              <th>Comisión</th>
              <th>Diferido</th>
              <th>Última Actualización</th>
            </tr>
          </thead>
          <tbody>
            {transacciones.length > 0 ? (
              transacciones.map((transaccion) => (
                <TransaccionItem key={transaccion.id} transaccion={transaccion} />
              ))
            ) : (
              <tr>
                <td colSpan="8" className="text-center">
                  No hay transacciones para mostrar.
                </td>
              </tr>
            )}
          </tbody>
        </Table>
      )}

      {/* Paginación */}
      {!loading && totalPages > 1 && (
        <Pagination>
          {[...Array(totalPages).keys()].map((page) => (
            <Pagination.Item
              key={page + 1}
              active={page + 1 === currentPage}
              onClick={() => handlePageChange(page + 1)}
            >
              {page + 1}
            </Pagination.Item>
          ))}
        </Pagination>
      )}
    </div>
  );
};

export default TransaccionList;
