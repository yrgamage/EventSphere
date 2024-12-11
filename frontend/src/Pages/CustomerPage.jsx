import Footer from '../Components/Footer/Footer';
import Header from '../Components/Header/Header';
import './page.css';
import { useNavigate } from 'react-router';

const handleBack = () => {
  document.getElementById('eventForm').reset();
};

function CustomerPage() {
  const navigate = useNavigate();
  
  const handleSubmit = async (e) => {
    e.preventDefault();

    const form = e.target;
    const ticketCount = parseInt(form.number.value, 10);
    const eventName = form.event.value;
    const name = form.name.value;

    console.log({ ticketCount, eventName, name });
    
    try {
      const response = await fetch("http://localhost:8080/ticket/saveTicket", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ ticketCount, eventName, name }),
      });

      const data = await response.json();
      console.log("Ticket recorded", data);
      navigate("/");
    } catch (error) {
      console.error("Error:", error.message);
    }
  };

  return (
    <>
    <Header/>
      <form id="eventForm" onSubmit={handleSubmit}>
        <h4 className="h3">Customer Details</h4>
        <div className="form-group"></div>

        <label htmlFor="name">Customer Name</label>
        <input className="pass" name="name" type="text" required />

        <label htmlFor="number">Number of Tickets</label>
        <input className="pass" name="number" type="number" required />

        <label htmlFor="event">Event Name</label>
        <input className="pass" name="event" type="text" required />

        <label htmlFor="cardNumber">Card Number</label>
        <input
          className="pass"
          name="cardNumber"
          type="text"
          placeholder="XXXX XXXX XXXX XXXX"
          required
          pattern="\d{4} \d{4} \d{4} \d{4}"
        />

        <label htmlFor="expiryDate">Expiration Date</label>
        <input
          className="pass"
          name="expiryDate"
          type="month"
          required
        />

        <label htmlFor="cvv">CVV</label>
        <input
          className="pass"
          name="cvv"
          type="text"
          placeholder="XXX"
          required
          pattern="\d{3}"
        />

        <label htmlFor="cardholderName">Cardholder Name</label>
        <input className="pass" name="cardholderName" type="text" required />

        <div />
        <div />
        <button className="cancel" type="button" onClick={handleBack}>
          Reset
        </button>
        <button className="submit" type="submit">Submit</button>
      </form>
      <Footer />
    </>  
  );
}

export default CustomerPage;
