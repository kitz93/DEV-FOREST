import { useEffect, useState } from "react";

const Ranking = () => {
  const [ranking, setranking] = useState([]);

  useEffect(() => {
    const socket = SockJS("http://loacalhost/rankings");
  });
};
