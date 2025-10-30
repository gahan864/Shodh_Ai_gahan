import { useEffect, useState } from "react";
import api from "../utils/api";

export default function Leaderboard({ contestId }) {
  const [data, setData] = useState([]);

  const fetchLeaderboard = async () => {
    const res = await api.get(`/contests/${contestId}/leaderboard`);
    setData(res.data);
  };

  useEffect(() => {
    fetchLeaderboard();
    const interval = setInterval(fetchLeaderboard, 15000);
    return () => clearInterval(interval);
  }, [contestId]);

  return (
    <div className="bg-white p-4 rounded-lg shadow">
      <h3 className="text-lg font-semibold mb-2">Leaderboard</h3>
      <ul>
        {data.map((item, i) => (
          <li key={i} className="flex justify-between py-1 border-b text-sm">
            <span>{item.username}</span>
            <span className="font-bold">{item.score}</span>
          </li>
        ))}
        {data.length === 0 && <p>No submissions yet</p>}
      </ul>
    </div>
  );
}
