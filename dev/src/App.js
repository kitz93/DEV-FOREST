import "./App.css";
import RankingChart from "./Ranking/RankingChart";
import Header from "./testComponent/Header";

function App() {
  return (
    <div className="App">
      <Header />
      <h1>랭킹 시스템</h1>
      <RankingChart />
    </div>
  );
}

export default App;
