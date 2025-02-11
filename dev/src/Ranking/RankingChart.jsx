import React, { useEffect, useState } from "react";
import { Bar } from "react-chartjs-2";
import axios from "axios";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

// Chart.js에 필요한 요소 등록
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const RankingChart = () => {
  const [chartData, setChartData] = useState({
    labels: [],
    datasets: [],
  });

  useEffect(() => {
    axios
      .get("http://localhost/rankings")
      .then((data) => {
        // console.log(data.data);
        const names = data.data.map((item) => item.nickName);
        const scores = data.data.map((item) => item.correctCount);

        setChartData({
          labels: names,
          datasets: [
            {
              label: "정답 개수",
              data: scores,
              backgroundColor: "rgba(75, 192, 192, 0.5)",
              borderColor: "rgba(75, 192, 192, 1)",
              borderWidth: 1,
            },
          ],
        });
      })
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  return (
    <div>
      <h2>🏆 랭킹 차트</h2>
      <Bar
        data={chartData}
        options={{ responsive: true, scales: { y: { beginAtZero: true } } }}
      />
    </div>
  );
};

export default RankingChart;
