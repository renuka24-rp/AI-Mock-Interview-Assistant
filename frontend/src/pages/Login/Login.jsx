import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { loginUser } from "../../services/AuthService";

function Login() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
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

            const response = await loginUser(formData);

            localStorage.setItem("token", response.token);

            navigate("/dashboard");

        } catch (error) {

            setMessage(
                error.response?.data?.message ||
                "Invalid Credentials"
            );

        }

    };

    return (

        <div className="min-h-screen bg-slate-950 flex justify-center items-center">

            <form
                onSubmit={handleSubmit}
                className="bg-slate-900 w-[420px] p-10 rounded-2xl shadow-xl space-y-5"
            >

                <h1 className="text-3xl font-bold text-cyan-400 text-center">

                    Welcome Back

                </h1>

                <input
                    className="w-full p-3 rounded bg-slate-800 text-white"
                    placeholder="Email"
                    type="email"
                    name="email"
                    onChange={handleChange}
                />

                <input
                    className="w-full p-3 rounded bg-slate-800 text-white"
                    placeholder="Password"
                    type="password"
                    name="password"
                    onChange={handleChange}
                />

                <button
                    className="w-full bg-cyan-500 hover:bg-cyan-600 p-3 rounded font-semibold"
                >
                    Login
                </button>

                <p className="text-center text-red-400">

                    {message}

                </p>

                <p className="text-center text-slate-400">

                    Don't have an account?

                    {" "}

                    <Link
                        to="/register"
                        className="text-cyan-400"
                    >
                        Register
                    </Link>

                </p>

            </form>

        </div>

    );

}

export default Login;