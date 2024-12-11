import { useState, useEffect } from "react";
import "./page.css";
import Header from "../Components/Header/Header";
import Footer from "../Components/Footer/Footer";

function Parameters() {
  const [records, setRecords] = useState({
    parameter1: "",
    parameter2: "",
    parameter3: "",
    parameter4: "",
  });

  useEffect(() => {
    fetch("http://localhost:8080/config/getPara")
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log("API Response:", data); // Debugging step
        setRecords({
          parameter1: data.maxCapacityTickets || "", 
          parameter2: data.totalTickets || "", 
          parameter3: data.ticketReleaseRate || "", 
          parameter4: data.customerRetrievalRate || "", 
        });
      })
      .catch((error) => console.error("Fetch Error:", error));
  }, []);
  
  

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setRecords({
      ...records,
      [name]: value,
    });
  };

  return (
    <>
    <Header/>
      <form>
        <h3 className="h3">Parameters</h3>
        <p>View only</p>
        <label>Maximum Tickets:</label>
        
        <input
          type="text"
          name="parameter1"
          value={records.parameter1 ||""}
          onChange={handleInputChange}
          readOnly
        />
        <br />
        <label>Total Tickets:</label>
        <input
          type="text"
          name="parameter2"
          value={records.parameter2 ||""}
          onChange={handleInputChange}
          readOnly
        />
        <br />
        <label>Ticket Release Rate:</label>
        <input
          type="text"
          name="parameter3"
          value={records.parameter3 ||""}
          onChange={handleInputChange}
          readOnly
        />
        <br />
        <label>Customer Retrieval Rate:</label>
        <input
          type="text"
          name="parameter4"
          value={records.parameter4 }
          onChange={handleInputChange}
          readOnly
        />
      </form>
    <Footer/>  
    </>
  );
}

export default Parameters;
