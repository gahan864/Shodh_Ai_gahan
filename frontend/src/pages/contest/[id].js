import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import api from "../../utils/api";
import ProblemView from "../../components/ProblemView";
import CodeEditor from "../../components/CodeEditor";
import Leaderboard from "../../components/Leaderboard";

export default function ContestPage() {
  const router = useRouter();
  const { id } = router.query;
  const [contest, setContest] = useState(null);
  const [problem, setProblem] = useState(null);
  const [code, setCode] = useState("public class Main { public static void main(String[] args) { } }");
  const [submissionStatus, setSubmissionStatus] = useState("");
  const username = typeof window !== "undefined" ? localStorage.getItem("username") : null;

  useEffect(() => {
    if (!id) return;
    api.get(`/contests/${id}`).then((res) => {
      setContest(res.data);
      if (res.data.problems?.length) setProblem(res.data.problems[0]);
    });
  }, [id]);

  const handleSubmit = async () => {
    if (!problem || !username) return;
    setSubmissionStatus("Submitting...");
    const userId = username === "alice" ? 1 : 2; // simplified user mapping

    const res = await api.post("/submissions", {
      userId,
      problemId: problem.id,
      language: "java",
      code,
    });

    const subId = res.data.submissionId;
    pollSubmission(subId);
  };

  const pollSubmission = (id) => {
    const interval = setInterval(async () => {
      const res = await api.get(`/submissions/${id}`);
      setSubmissionStatus(res.data.status);
      if (res.data.status !== "Running" && res.data.status !== "Pending") {
        clearInterval(interval);
      }
    }, 3000);
  };

  if (!contest) return <p className="text-center mt-10">Loading contest...</p>;

  return (
    <div className="p-6 bg-gray-50 min-h-screen">
      <h1 className="text-3xl font-bold mb-2">{contest.title}</h1>
      <p className="text-gray-600 mb-6">{contest.description}</p>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="md:col-span-2 space-y-4">
          {problem && <ProblemView problem={problem} />}
          <CodeEditor code={code} setCode={setCode} />
          <button
            onClick={handleSubmit}
            className="bg-green-600 text-white px-6 py-2 rounded hover:bg-green-700 transition"
          >
            Submit Code
          </button>
          <p className="text-sm mt-2">Status: {submissionStatus}</p>
        </div>

        <div>
          <Leaderboard contestId={id} />
        </div>
      </div>
    </div>
  );
}
