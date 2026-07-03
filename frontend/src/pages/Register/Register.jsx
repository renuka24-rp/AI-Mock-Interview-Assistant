import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { registerUser } from "../../services/AuthService";

function Register() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        fullName: "",
        email: "",
        password: ""
    });

    const [message, setMessage] = useState("");

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const response = await registerUser(formData);

            setMessage(response.message);

            setTimeout(() => {
                navigate("/login");
            }, 1500);

        } catch (error) {

           console.error(error);

setMessage(
    JSON.stringify(error.response?.data) ||
    error.message
);

        }

    };

    return (

        <div className="min-h-screen bg-slate-950 flex justify-center items-center">

            <form
                onSubmit={handleSubmit}
                className="bg-slate-900 p-10 rounded-2xl w-[420px] space-y-5 shadow-2xl"
            >

                <h1 className="text-3xl font-bold text-cyan-400 text-center">

                    Create Account

                </h1>

                <input
                    className="w-full p-3 rounded bg-slate-800 text-white"
                    placeholder="Full Name"
                    name="fullName"
                    onChange={handleChange}
                />

                <input
                    className="w-full p-3 rounded bg-slate-800 text-white"
                    placeholder="Email"
                    name="email"
                    type="email"
                    onChange={handleChange}
                />

                <input
                    className="w-full p-3 rounded bg-slate-800 text-white"
                    placeholder="Password"
                    name="password"
                    type="password"
                    onChange={handleChange}
                />

                <button
                    className="w-full bg-cyan-500 hover:bg-cyan-600 p-3 rounded font-semibold"
                >
                    Register
                </button>

                <p className="text-center text-green-400">
                    {message}
                </p>

                <p className="text-center text-slate-400">

                    Already have an account?

                    {" "}

                    <Link
                        to="/login"
                        className="text-cyan-400"
                    >
                        Login
                    </Link>

                </p>

            </form>

        </div>

    );

}

export default Register;