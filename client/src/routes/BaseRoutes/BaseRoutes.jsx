import { Routes, Route } from 'react-router-dom';
import LoginPage from '@/pages/LoginPage';

function BaseRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
    </Routes>
  );
}

export default BaseRoutes;