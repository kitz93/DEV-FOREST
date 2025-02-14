import { Route, Routes } from "react-router-dom";
import "./App.css";
import { AuthProvider } from "./Component/Context/AuthContext";
import Footer from "./Component/Footer/Footer";
import Header from "./Component/Header/Header";
import MyPage from "./Component/MyPage/MyPage";
import Main from "./Component/Main/Main";

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <Header />

        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/myPage" element={<MyPage />} />
        </Routes>

        <Footer />
      </AuthProvider>
    </div>
  );
}

export default App;
