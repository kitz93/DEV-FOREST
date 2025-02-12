import "./App.css";
import BoardDetail from "./Board/BoardDetail";
import BoardList from "./Board/BoardList";
import Footer from "./Footer/Footer";
import Header from "./Header/Header";
import { Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Header />
      <h1>메인임</h1>

      <Routes>
        <Route path="/boards" element={<BoardList />} />
        <Route path="/boards/:id" element={<BoardDetail />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
