import './Header.css'
import { Link } from "react-router-dom";




function Header () {
  return (
    <>
      <nav className="headerContent">


       <Link className="header" to="/home">Home</Link>
        <Link className="header" to="/customer">Buy Ticket</Link>
        <Link className="header" to="/vendor">Add Event</Link>
        <Link className="header" to="/parameteres">Parameteres</Link>
        <Link className="header" to="/signin">Sign Up</Link>
      </nav>
    </>
  )
}


export default Header;