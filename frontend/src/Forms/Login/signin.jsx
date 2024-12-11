import Footer from "../../Components/Footer/Footer";
import Header from "../../Components/Header/Header";
import "./signin.css";
import { useNavigate } from "react-router";  // Make sure you import from 'react-router-dom'

const handleBack = () => {
  // Reset the form fields
  document.getElementById("loginForm").reset();
};

function Signin() {  // Renamed to Signin (capitalized)
  const navigate = useNavigate();  // Call useNavigate at the top level of the component

  const handleSubmit = async (e) => {
    e.preventDefault();

    const form = e.target;  // Access the form element
    const name = form.name.value;  // Access field values
    const password = form.password.value;
    const role = form.role.value;  // Get selected role

    console.log({ name, password, role });

    try {
      if (role === "customer") {
        console.log("Customer");
        const response = await fetch("http://localhost:8080/customer/saveCustomer", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ name, password }),
        });
        const data = await response.json();
        console.log("Customer recorded", data);
        navigate("/");  // Navigate after successful submission
      } else {
        console.log("Vendor");
        const response1 = await fetch("http://localhost:8080/vendor/saveVendor",{
          method : "POST",
          headers:{"Content-Type":"application/json"},
          body:JSON.stringify({name,password}),
        });
        const data1 = await response1.json();
        console.log("saved vendor",data1)
        navigate("/")
      }
    } catch (error) {
      console.error("Error:", error.message);
    }
  };

  return (
    <>
    <Header/>
      <div className="cover">
        <form id="loginForm" onSubmit={handleSubmit}>
          <h3 className="h3">Register</h3>
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input
              className="pass"
              id="name"
              name="name"
              type="text"
              placeholder="Name"
              autoComplete="name"
              required
            />
            <label htmlFor="password">Password</label>
            <input
              className="pass"
              id="password"
              name="password"
              type="password"
              placeholder="Password"
              required
              autoComplete="current-password"
            />
          </div>

          {/* Role Selection */}
          <div className="role-selection">
            <label>
              <input type="radio" name="role" value="customer" required />
              Customer
            </label>
            <label>
              <input type="radio" name="role" value="vendor" />
              Vendor
            </label>
          </div>

          {/* Buttons */}
          <button className="cancel" type="button" onClick={handleBack}>
            Reset
          </button>
          <button className="submit" type="submit">
            Login
          </button>
        </form>
      </div>
      <Footer/>
    </>
  );
}

export default Signin;  // Changed to 'Signin' (capitalized)
