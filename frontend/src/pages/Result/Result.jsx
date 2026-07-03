import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getResult } from "../../services/InterviewService";

function Result() {

  const { id } = useParams();

  const [result, setResult] = useState(null);

  useEffect(() => {

    const fetchResult = async () => {

      try {

        const data = await getResult(id);

        setResult(data);

      } catch (error) {

        console.error(error);

      }

    };

    fetchResult();

  }, [id]);

  if (!result) {

    return (
      <div className="min-h-screen bg-slate-950 text-white flex justify-center items-center">
        Loading Result...
      </div>
    );

  }

  return (

    <div className="min-h-screen bg-slate-950 text-white p-10">

      <div className="max-w-5xl mx-auto">

        <h1 className="text-5xl font-bold text-cyan-400 text-center">

          Interview Result

        </h1>

        <div className="grid md:grid-cols-4 gap-6 mt-12">

          <ScoreCard
            title="Technical"
            score={result.technicalScore}
          />

          <ScoreCard
            title="Communication"
            score={result.communicationScore}
          />

          <ScoreCard
            title="Confidence"
            score={result.confidenceScore}
          />

          <ScoreCard
            title="Overall"
            score={result.overallScore}
          />

        </div>

        <Section
          title="Feedback"
          content={result.feedback}
        />

        <Section
          title="Strengths"
          content={result.strengths}
        />

        <Section
          title="Weaknesses"
          content={result.weaknesses}
        />

        <Section
          title="Suggestions"
          content={result.suggestions}
        />

      </div>

    </div>

  );

}

function ScoreCard({ title, score }) {

  return (

    <div className="bg-slate-900 rounded-2xl p-6 text-center">

      <h2 className="text-xl font-bold">

        {title}

      </h2>

      <p className="text-5xl mt-6 font-bold text-cyan-400">

        {score}

      </p>

    </div>

  );

}

function Section({ title, content }) {

  return (

    <div className="bg-slate-900 rounded-2xl p-8 mt-8">

      <h2 className="text-2xl font-bold text-cyan-400">

        {title}

      </h2>

      <p className="mt-4 text-lg leading-8">

        {content}

      </p>

    </div>

  );

}

export default Result;