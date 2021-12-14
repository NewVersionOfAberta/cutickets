import { useState, useCallback, useEffect } from "react";

const storageName = "userData";

export const useAuth = () => {
  const [token, setToken] = useState(null);
  const [userId, setUserId] = useState(null);
  const [userName, setUserName] = useState(null);
  const [roles, setRoles] = useState(null);

  const login = useCallback((jwtToken, id, name, role) => {
    console.log("In log in auth hook", role);
    console.log("Token: ", name);
    setUserId(id);
    setUserName(name);
    console.log("Name: ", name);
    setRoles(role);
    console.log("Roles: ", roles);
    setToken(jwtToken);
    localStorage.setItem(
      storageName,
      JSON.stringify({
        userId: id,
        token: jwtToken,
        userName: name,
        roles: role,
      })
    );
  }, []);

  const logout = useCallback(() => {
    setToken(null);
    setUserId(null);
    setUserName(null);
    setRoles(null);
    localStorage.removeItem(storageName);
  }, []);

  useEffect(() => {
    const data = JSON.parse(localStorage.getItem(storageName));
    console.log("Data", data);
    if (data && data.token) {
      login(data.token, data.userId, data.userName, data.roles);
    }
  }, [login]);
  console.log("Before return ", token, roles);
  return { login, logout, token, userId, userName, roles };
};
