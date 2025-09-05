import { useState, useEffect } from 'react';
import AuthenticatedRoutes from '@/routes/AuthenticatedRoutes/AuthenticatedRoutes';
import BaseRoutes from '@/routes/BaseRoutes/BaseRoutes';
import AuthService from '@/service/AuthService';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    setIsAuthenticated(AuthService.isAuthenticated());
  }, []);

  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
  };

  return isAuthenticated ? <AuthenticatedRoutes /> : <BaseRoutes />;
}

export default App;