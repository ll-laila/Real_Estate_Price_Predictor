import { useState } from "react";
import "./navbar.scss";
import logo from '../../assets/images/logo.png';
import iconmenu from '../../assets/images/menu.png';

function Navbar() {
    const [open, setOpen] = useState(false);

    const user = true;
    return (
        <nav>
            <div className="left">
                <a href="/" className="logo">
                    <img src={logo} alt="RealEstatePrediction Logo" />
                    <span>RealEstatePrediction</span>
                </a>
                <a href="/">Home</a>
                <a href="/">About</a>
                <a href="/">Contact</a>
                <a href="/">Agents</a>
            </div>
            <div className="right">
                <a href="/">Sign in</a>
                <a href="/" className="register">
                    Sign up
                </a>

                <div className="menuIcon">
                    <img
                        src={iconmenu}
                        alt=""
                        onClick={() => setOpen((prev) => !prev)}
                    />
                </div>
                <div className={open ? "menu active" : "menu"}>
                    <a href="/">Home</a>
                    <a href="/">About</a>
                    <a href="/">Contact</a>
                    <a href="/">Agents</a>
                    <a href="/">Sign in</a>
                    <a href="/">Sign up</a>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;