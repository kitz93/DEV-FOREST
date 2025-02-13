import { useState, useEffect, createContext } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState({
    username: null,
    accessToken: null,
    refreshToken: null,
    isAuthenticated: false,
  });

  useEffect(() => {
    const accessToken = sessionStorage.getItem("accessToken");
    const refreshToken = sessionStorage.getItem("refreshToken");
    const username = sessionStorage.getItem("username");
    if (accessToken && refreshToken && username) {
      setAuth({
        username,
        accessToken,
        refreshToken,
        isAuthenticated: true,
      });
    }
  }, []);

  const login = (username, accessToken, refreshToken) => {
    setAuth({
      username,
      accessToken,
      refreshToken,
      isAuthenticated: true,
    });
    sessionStorage.setItem("username", username);
    sessionStorage.setItem("accessToken", accessToken);
    sessionStorage.setItem("refreshToken", refreshToken);
  };

  const unlinkKakao = async () => {
    try {
      await fetch("https://kapi.kakao.com/v1/user/unlink", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${auth.accessToken}`,
        },
      });
    } catch (error) {
      console.error("카카오 계정 해제 오류:", error);
    } finally {
      setAuth({
        username: null,
        accessToken: null,
        refreshToken: null,
        isAuthenticated: false,
      });
      sessionStorage.removeItem("username");
      sessionStorage.removeItem("accessToken");
      sessionStorage.removeItem("refreshToken");
    }
  };

  const logout = async () => {
    try {
      await fetch("https://kapi.kakao.com/v1/user/logout", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${auth.accessToken}`,
        },
      });
    } catch (error) {
      console.error("카카오 로그아웃 오류:", error);
    } finally {
      setAuth({
        username: null,
        accessToken: null,
        refreshToken: null,
        isAuthenticated: false,
      });
      sessionStorage.removeItem("username");
      sessionStorage.removeItem("accessToken");
      sessionStorage.removeItem("refreshToken");
    }
  };

  return (
    <AuthContext.Provider value={{ auth, login, logout, unlinkKakao }}>
      {children}
    </AuthContext.Provider>
  );
};
