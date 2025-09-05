import { Routes, Route, Navigate } from 'react-router-dom';
import Login from '../../pages/LoginPage';

function AuthenticatedRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      {/* Redireciona para a página inicial se a rota não for encontrada */}
      <Route path="*" element={<Navigate to="/home" />} />
    </Routes>
  );
}

export default AuthenticatedRoutes;