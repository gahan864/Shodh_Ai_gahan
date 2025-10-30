export default function ProblemView({ problem }) {
  return (
    <div className="bg-white p-4 rounded-lg shadow">
      <h2 className="text-xl font-semibold mb-2">{problem.title}</h2>
      <p className="mb-4 text-gray-700">{problem.description}</p>
      <div className="text-sm bg-gray-100 p-2 rounded">
        <strong>Input:</strong> {problem.inputSample}
        <br />
        <strong>Expected Output:</strong> {problem.expectedOutput}
      </div>
    </div>
  );
}
