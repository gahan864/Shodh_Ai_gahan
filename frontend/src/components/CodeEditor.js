import Editor from "@monaco-editor/react";

export default function CodeEditor({ code, setCode }) {
  return (
    <div className="bg-white rounded-lg shadow p-2">
      <Editor
        height="300px"
        defaultLanguage="java"
        value={code}
        onChange={(v) => setCode(v)}
        theme="vs-dark"
      />
    </div>
  );
}
