
import './page.css';
function Parameteres() {
  // const response2 = await fetch("http://localhost:8080/config/getPara", {
  //   method: "GET",
  //   headers: { "Content-Type": "application/json" },
  // });

  // if (!response2.ok) throw new Error(`HTTP error: ${response2.status}`);
  // const data2 = await response2.json();
  // console.log("Another action completed", data2);npm
  return (
    <>
        
        <form>
        <h3 className="h3">Parameteres</h3>
        <p>View only</p>
          <label>Maximiun Tickets:</label>
          <input type="text" name="parameter1" />
          <label>Total Tickets:</label>
          <input type="text" name="parameter2" />
          <label>Ticket Release Rate:</label>
          <input type="text" name="parameter3" />
          <label>Customer Retrival Rate:</label>
          <input type="text" name="parameter3" />
        </form>  
    
    </>
  )
}

export default Parameteres