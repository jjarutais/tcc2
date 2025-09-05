import { useState, useEffect } from 'react';
import AuthenticatedRoutes from './routes/AuthenticatedRoutes/AuthenticatedRoutes.jsx';
import BaseRoutes from './routes/BaseRoutes/BaseRoutes.jsx';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    // Verifica se existe um token de autenticação no localStorage
    const token = localStorage.getItem('authToken');
    if (token) {
      // Em uma aplicação real, você faria uma validação do token
      // na API para confirmar se ele ainda é válido.
      setIsAuthenticated(true);
    }
  }, []);

  // Simulação de como o estado de autenticação é atualizado
  // após o login bem-sucedido.
  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
  };

  // Renderiza as rotas apropriadas com base no estado de autenticação
  return isAuthenticated ? <AuthenticatedRoutes /> : <BaseRoutes />;
}

export default App;