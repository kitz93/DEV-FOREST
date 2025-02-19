import "./App.css";
import RankingChart from "./Ranking/RankingChart";
import Quiz from "./Quiz/Quiz";
import { Route, Routes } from "react-router-dom";
import BackendTheory from "./Theory/BackendTheory.";
import { AuthProvider } from "./Component/Context/AuthContext";
import Footer from "./Component/Footer/Footer";
import Header from "./Component/Header/Header";
import MyPage from "./Component/MyPage/MyPage";
import Main from "./Component/Main/Main";
import FrontendTheory from "./Theory/FrontendTheory";

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <Header />

        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/myPage" element={<MyPage />} />
          <Route path="/quizs" element={<Quiz />} />
          <Route path="/rankings" element={<RankingChart />} />
          <Route path="/backend" element={<BackendTheory />} />
          <Route path="/frontend" element={<FrontendTheory />} />
        </Routes>

        <Footer />
      </AuthProvider>
    </div>
  );
}

export default App;
