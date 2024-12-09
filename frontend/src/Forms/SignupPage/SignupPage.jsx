const handleBack = () => {
  // Reset the form fields
  document.getElementById('eventForm').reset();
};

function SignupPage() {
  return (
    <>
    <div className="cover">
      
      <form>
      <h3 className="h3">Login</h3>
        <div className="form-group">
        <label htmlFor="username">Username</label>
        <input className= "pass"name="username" type="text" placeholder="  Usename"  required/>
        <label htmlFor="password">Password</label>
        <input className= "pass" type="password" placeholder="Password" required />
        </div>
        {/* Add a section for selecting Customer or Vendor */}
        <div className="role-selection">
          <label>
            <input type="radio" name="role" value="customer" required/>
            Customer
          </label>
          <label>
            <input type="radio" name="role" value="vendor" />
            Vendor
          </label>
        </div>
        <button className="cancel" type="button" onClick={handleBack}>
            Reset
          </button>
        
        <button className="submit">Submit</button>
      </form>

    </div>
    </>
  )
}

export default SignupPage