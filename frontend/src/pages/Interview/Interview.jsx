import { useState } from "react";
import { submitAnswer } from "../../services/InterviewService";

function Interview() {

    const [question, setQuestion] =
        useState(localStorage.getItem("question"));

    const [answer, setAnswer] = useState("");

    const interviewId =
        localStorage.getItem("interviewId");

    const handleSubmit = async () => {

        const response =
            await submitAnswer(
                interviewId,
                answer
            );

        if (response.completed) {

            window.location.href =
                "/result/" + interviewId;

            return;
        }

        setQuestion(response.question);

        setAnswer("");

    };

    return (

        <div className="min-h-screen bg-slate-950 text-white flex justify-center items-center">

            <div className="bg-slate-900 w-[700px] p-10 rounded-2xl">

                <h2 className="text-3xl font-bold text-cyan-400">

                    Interview

                </h2>

                <p className="mt-8 text-xl">

                    {question}

                </p>

                <textarea
                    value={answer}
                    onChange={(e)=>setAnswer(e.target.value)}
                    rows={8}
                    className="mt-8 w-full p-4 rounded bg-slate-800"
                    placeholder="Type your answer..."
                />

                <button
                    onClick={handleSubmit}
                    className="mt-6 w-full bg-cyan-500 p-3 rounded-xl"
                >
                    Submit Answer
                </button>

            </div>

        </div>

    );

}

export default Interview;