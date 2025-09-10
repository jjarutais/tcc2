import { Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from '@/pages/LoginPage';
import AuthService from '@/service/AuthService';

function BaseRoutes() {
  if (AuthService.isAuthenticated()) {
    return <Navigate to="/login" />;
  }
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
    </Routes>
  );
}

export default BaseRoutes;