import { useState, useEffect, createContext } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState({
    nickname: null,
    accessToken: null,
    refreshToken: null,
    isAuthenticated: false,
  });

  useEffect(() => {
    const accessToken = sessionStorage.getItem("accessToken");
    const refreshToken = sessionStorage.getItem("refreshToken");
    const nickname = sessionStorage.getItem("nickname");
    if (accessToken && refreshToken && nickname) {
      setAuth({
        nickname,
        accessToken,
        refreshToken,
        isAuthenticated: true,
      });
    }
  }, []);

  const login = (
    nickname,
    accessToken,
    refreshToken,
    snsAccessToken,
    snsRefreshToken
  ) => {
    setAuth({
      nickname,
      accessToken,
      refreshToken,
      isAuthenticated: true,
    });
    sessionStorage.setItem("nickname", nickname);
    sessionStorage.setItem("accessToken", accessToken);
    sessionStorage.setItem("snsAccessToken", snsAccessToken);
    sessionStorage.setItem("refreshToken", refreshToken);
    sessionStorage.setItem("snsRefreshToken", snsRefreshToken);
  };

  const unlinkKakao = async () => {
    try {
      await fetch("https://kapi.kakao.com/v1/user/unlink", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("snsAccessToken")}`,
        },
      });
    } catch (error) {
      console.error("카카오 계정 해제 오류:", error);
    } finally {
      setAuth({
        nickname: null,
        accessToken: null,
        refreshToken: null,
        isAuthenticated: false,
      });
      sessionStorage.removeItem("nickname");
      sessionStorage.removeItem("accessToken");
      sessionStorage.removeItem("snsAccessToken");
      sessionStorage.removeItem("refreshToken");
      sessionStorage.removeItem("snsRefreshToken");
    }
  };

  const logout = async () => {
    try {
      await fetch("https://kapi.kakao.com/v1/user/logout", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("snsAccessToken")}`,
        },
      });
    } catch (error) {
      console.error("카카오 로그아웃 오류:", error);
    } finally {
      setAuth({
        nickname: null,
        accessToken: null,
        refreshToken: null,
        isAuthenticated: false,
      });
      sessionStorage.removeItem("nickname");
      sessionStorage.removeItem("accessToken");
      sessionStorage.removeItem("snsAccessToken");
      sessionStorage.removeItem("refreshToken");
      sessionStorage.removeItem("snsRefreshToken");
    }
  };

  return (
    <AuthContext.Provider value={{ auth, login, logout, unlinkKakao }}>
      {children}
    </AuthContext.Provider>
  );
};
