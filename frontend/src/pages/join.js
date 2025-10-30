import { useState } from "react";
import { useRouter } from "next/router";

export default function JoinPage() {
  const [contestId, setContestId] = useState("");
  const [username, setUsername] = useState("");
  const router = useRouter();

  const handleJoin = () => {
    if (!contestId || !username) return alert("Please fill both fields!");
    localStorage.setItem("username", username);
    router.push(`/contest/${contestId}`);
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-100">
      <h1 className="text-3xl font-bold mb-6">Join Contest</h1>
      <div className="bg-white p-8 rounded-xl shadow-md w-80 space-y-4">
        <input
          type="text"
          placeholder="Contest ID"
          className="w-full p-2 border rounded"
          value={contestId}
          onChange={(e) => setContestId(e.target.value)}
        />
        <input
          type="text"
          placeholder="Username"
          className="w-full p-2 border rounded"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <button
          onClick={handleJoin}
          className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
        >
          Join
        </button>
      </div>
    </div>
  );
}
