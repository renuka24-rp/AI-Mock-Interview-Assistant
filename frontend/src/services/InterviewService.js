import api from "../api/axiosConfig";

export const startInterview = async (interviewType) => {
    const response = await api.post("/interview/start", {
        interviewType
    });

    return response.data;
};

export const submitAnswer = async (interviewId, answer) => {
    const response = await api.post("/interview/answer", {
        interviewId,
        answer
    });

    return response.data;
};

export const getResult = async (interviewId) => {
    const response = await api.get(`/interview/result/${interviewId}`);
    return response.data;
};