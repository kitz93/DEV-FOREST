import "./App.css";
import RankingChart from "./Ranking/RankingChart";
import Quiz from "./Quiz/Quiz";
import { Route, Routes } from "react-router-dom";
import BackendTheory from "./Theory/BackendTheory.";
import { AuthProvider } from "./Component/Context/AuthContext";
import styled from "styled-components";
import Footer from "./Component/Footer/Footer";
import Header from "./Component/Header/Header";
import BoardList from "./Board/BoardList";
import BoardDetail from "./Board/BoardDetail";
import InsertBoard from "./Board/InsertBoard";
import EditBoard from "./Board/EditBoard";
import ReservationList from "./Reservation/ReservationList";
import StudyingList from "./Studying/StudyingList";
import ReservationDetail from "./Reservation/ReservationDetail";
import MyPage from "./Component/MyPage/MyPage";
import Main from "./Component/Main/Main";
import FrontendTheory from "./Theory/FrontendTheory";
import InsertReservation from "./Reservation/InsertReservation";
import KakaoMap from "./Component/Map/KakaoMap";

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
          <Route path="boards" element={<BoardList />} />
          <Route path="insert" element={<InsertBoard />} />
          <Route path="boards/:id" element={<BoardDetail />} />
          <Route path="boards/:id/edit" element={<EditBoard />} />
          <Route path="reservations" element={<ReservationList />} />
          <Route path="reservations/:id" element={<ReservationDetail />} />
          <Route path="studyings" element={<StudyingList />} />
          <Route path="/" element={<Main />} />
          <Route path="/myPage" element={<MyPage />} />
          <Route path="/quizs" element={<Quiz />} />
          <Route path="/rankings" element={<RankingChart />} />
          <Route path="/backend" element={<BackendTheory />} />
          <Route path="/frontend" element={<FrontendTheory />} />
          <Route path="create" element={<InsertReservation />} />

        </Routes>

        <Footer />
      </AuthProvider>
    </div>
  );
}

export default App;
