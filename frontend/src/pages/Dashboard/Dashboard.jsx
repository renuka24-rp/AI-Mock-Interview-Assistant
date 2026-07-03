import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { startInterview } from "../../services/InterviewService";
import {
  Brain,
  PlayCircle,
  History,
  User,
  FileText,
} from "lucide-react";

function Dashboard() {
  const navigate = useNavigate();

  const [interviewType, setInterviewType] = useState("JAVA");

  const handleStartInterview = async () => {
    try {
      const interview = await startInterview(interviewType);

      localStorage.setItem(
        "interviewId",
        interview.interviewId
      );

      localStorage.setItem(
        "question",
        interview.question
      );

      navigate("/interview");
    } catch (error) {

  console.error("Start Interview Error:", error);

  if (error.response) {

    console.log("Status:", error.response.status);
    console.log("Response:", error.response.data);

    alert(
      "Status: " +
      error.response.status +
      "\n\n" +
      JSON.stringify(error.response.data)
    );

  } else {

    alert(error.message);

  }

}
  };

  return (
    <div className="min-h-screen bg-slate-950 text-white">

      {/* Navbar */}
      <nav className="flex justify-between items-center px-10 py-6 border-b border-slate-800">

        <h1 className="text-3xl font-bold text-cyan-400">
          AI Mock Interview
        </h1>

        <div className="flex items-center gap-3">
          <User size={22} />
          <span>Renuka</span>
        </div>

      </nav>

      <div className="max-w-7xl mx-auto p-10">

        {/* Welcome */}
        <h1 className="text-5xl font-bold">
          Welcome 👋
        </h1>

        <p className="text-slate-400 mt-2">
          Ready for today's interview?
        </p>

        {/* Overall Progress */}
        <div className="mt-10 bg-slate-900 rounded-2xl p-8">

          <h2 className="text-2xl font-bold mb-5">
            Overall Progress
          </h2>

          <div className="w-full bg-slate-800 rounded-full h-6">
            <div
              className="bg-cyan-400 h-6 rounded-full"
              style={{ width: "87%" }}
            />
          </div>

          <p className="mt-3 text-cyan-400 font-bold">
            87%
          </p>

        </div>

        {/* Main Grid */}
        <div className="grid md:grid-cols-2 gap-8 mt-10">

          {/* Start Interview */}
          <div className="bg-slate-900 p-8 rounded-2xl">

            <Brain size={40} className="text-cyan-400" />

            <h2 className="text-2xl font-bold mt-4">
              Start Interview
            </h2>

            <select
              value={interviewType}
              onChange={(e) =>
                setInterviewType(e.target.value)
              }
              className="w-full mt-6 p-3 rounded bg-slate-800"
            >
              <option value="JAVA">Java</option>
              <option value="HR">HR</option>
            </select>

            <button
              onClick={handleStartInterview}
              className="mt-6 w-full bg-cyan-500 hover:bg-cyan-600 p-3 rounded-xl flex justify-center items-center gap-2"
            >
              <PlayCircle size={20} />
              Start Interview
            </button>

          </div>

          {/* Previous Interviews */}
          <div className="bg-slate-900 p-8 rounded-2xl">

            <History
              className="text-cyan-400"
              size={40}
            />

            <h2 className="text-2xl font-bold mt-4">
              Previous Interviews
            </h2>

            <div className="space-y-4 mt-6">

              <div className="flex justify-between">
                <span>Java Backend</span>
                <span className="text-cyan-400">
                  91%
                </span>
              </div>

              <div className="flex justify-between">
                <span>HR Round</span>
                <span className="text-cyan-400">
                  86%
                </span>
              </div>

              <div className="flex justify-between">
                <span>Spring Boot</span>
                <span className="text-cyan-400">
                  95%
                </span>
              </div>

            </div>

          </div>

        </div>

        {/* Quick Actions */}
        <div className="grid md:grid-cols-3 gap-6 mt-10">

          <div className="bg-slate-900 p-8 rounded-xl">

            <FileText className="text-cyan-400" />

            <h3 className="mt-4 text-xl font-bold">
              Resume Analyzer
            </h3>

          </div>

          <div className="bg-slate-900 p-8 rounded-xl">

            <History className="text-cyan-400" />

            <h3 className="mt-4 text-xl font-bold">
              Interview History
            </h3>

          </div>

          <div className="bg-slate-900 p-8 rounded-xl">

            <User className="text-cyan-400" />

            <h3 className="mt-4 text-xl font-bold">
              Profile
            </h3>

          </div>

        </div>

      </div>

    </div>
  );
}

export default Dashboard;