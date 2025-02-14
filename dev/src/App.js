import "./App.css";
import RankingChart from "./Ranking/RankingChart";
import Footer from "./Footer/Footer";
import Header from "./Header/Header";
import Quiz from "./Quiz/Quiz";
import { Route, Routes } from "react-router-dom";
import BackendTheory from "./Theory/BackendTheory.";

function App() {
  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/quizs" element={<Quiz />} />
        <Route path="/rankings" element={<RankingChart />} />
        <Route path="/backend" element={<BackendTheory />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
