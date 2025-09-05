import { api } from "@/lib/axios";

// Você pode definir as interfaces em um arquivo separado, mas para este exemplo,
// vamos defini-las aqui para simplificar.
// interface IUserLogin {
//   username: string;
//   password: string;
// }

const login = async (user) => {
  let response;
  try {
    response = await api.post("/login", user);
    if (response.status === 200) {
      localStorage.setItem("authToken", response.data.token);
      api.defaults.headers.common["Authorization"] = `Bearer ${response.data.token}`;
    }
  } catch (error) {
    response = error.response;
  }
  return response;
};

const isAuthenticated = () => {
  const token = localStorage.getItem("authToken");
  if (token) {
    api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    return true;
  }
  return false;
};

const logout = () => {
  localStorage.removeItem("authToken");
  api.defaults.headers.common["Authorization"] = "";
};

const AuthService = {
  login,
  isAuthenticated,
  logout,
};

export default AuthService;
