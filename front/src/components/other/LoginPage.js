import { Link } from "react-router-dom";

const LoginPage = () => {
    return (
        <div>
            <h1>Login</h1>
            <Link to="/patient"><button>Patient</button></Link>
            <Link to="/dermatologist"><button>Dermatologist</button></Link>
            <Link to="/pharmacist"><button>Pharmacist</button></Link>
            <Link to="/supplier"><button>Supplier</button></Link>
            <Link to="/system-admin"><button>System admin</button></Link>
        </div>
    )
}

export default LoginPage
