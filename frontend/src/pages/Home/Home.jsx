import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="min-h-screen bg-slate-950 text-white">

      {/* Navbar */}
      <nav className="flex justify-between items-center px-10 py-6 border-b border-slate-800">

        <h1 className="text-3xl font-bold text-cyan-400">
          AI Mock Interview
        </h1>

        <div className="space-x-4">
          <Link
            to="/login"
            className="px-5 py-2 rounded-lg hover:bg-slate-800"
          >
            Login
          </Link>

          <Link
            to="/register"
            className="bg-cyan-500 hover:bg-cyan-600 px-5 py-2 rounded-lg"
          >
            Register
          </Link>
        </div>

      </nav>

      {/* Hero */}

      <section className="text-center py-24 px-6">

        <h1 className="text-6xl font-extrabold mb-6">

          Land Your Dream Job

          <br />

          <span className="text-cyan-400">
            with AI
          </span>

        </h1>

        <p className="text-xl text-slate-300 max-w-3xl mx-auto">

          Practice Java, HR and Technical Interviews powered by Google Gemini AI.
          Receive real-time evaluation, feedback and improvement suggestions.

        </p>

        <div className="mt-10 space-x-4">

          <Link
            to="/login"
            className="bg-cyan-500 hover:bg-cyan-600 px-8 py-4 rounded-xl font-semibold"
          >
            Start Interview
          </Link>

          <Link
            to="/register"
            className="border border-cyan-400 px-8 py-4 rounded-xl hover:bg-slate-800"
          >
            Create Account
          </Link>

        </div>

      </section>

      {/* Features */}

      <section className="grid md:grid-cols-3 gap-8 px-10 pb-20">

        <div className="bg-slate-900 rounded-2xl p-8">
          <h2 className="text-2xl font-bold mb-3">
            🤖 AI Evaluation
          </h2>

          <p>
            Detailed technical, communication and confidence analysis.
          </p>
        </div>

        <div className="bg-slate-900 rounded-2xl p-8">
          <h2 className="text-2xl font-bold mb-3">
            🎤 Voice Interview
          </h2>

          <p>
            Answer interview questions using voice recognition.
          </p>
        </div>

        <div className="bg-slate-900 rounded-2xl p-8">
          <h2 className="text-2xl font-bold mb-3">
            📊 Progress Dashboard
          </h2>

          <p>
            Track scores, strengths and weaknesses over multiple interviews.
          </p>
        </div>

      </section>

    </div>
  );
}

export default Home;