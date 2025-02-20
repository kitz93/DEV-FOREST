import "./App.css";
import { AuthProvider } from "./Component/Context/AuthContext";
import Footer from "./Component/Footer/Footer";
import Header from "./Component/Header/Header";
import styled from "styled-components";
import { Route, Routes } from "react-router-dom";
import BoardDetail from "./Board/BoardDetail";
import BoardList from "./Board/BoardList";
import EditBoard from "./Board/EditBoard";
import InsertBoard from "./Board/InsertBoard";

const TestDiv = styled.div`
  width: 1000px;
  background-color: skyblue;
  margin: auto;
`;

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <Header />

        <Routes>
          <Route path="/boards" element={<BoardList />} />
          <Route path="/insert" element={<InsertBoard />} />
          <Route path="/boards/:id" element={<BoardDetail />} />
          <Route path="/boards/:id/edit" element={<EditBoard />} />
        </Routes>

        <TestDiv>
          <h1>
            메인임
            <br />
            <br />
            카카오 회원가입 및 로그인 가능
            <br />
            <br />
            기능은 아직
            <br />
            <br />
          </h1>
        </TestDiv>

        <Footer />
      </AuthProvider>
    </div>
  );
}

export default App;
