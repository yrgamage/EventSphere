import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const EventForm = () => {
  const navigate = useNavigate();
  const [formSubmitted, setFormSubmitted] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const form = e.target;
    const eventName = form.name.value;
    const ticketCount = parseInt(form.number.value, 10);
    const eventDate = form.date.value;
    const eventTime = form.time.value;
    const eventLocation = form.location.value;
    const ticketPrice = form.price.value;

    try {
      const response = await fetch("http://localhost:8080/event/saveEvent", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          eventName,
          ticketCount,
          eventDate,
          eventTime,
          eventLocation,
          ticketPrice,
        }),
      });

      if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
      const data = await response.json();
      console.log("Event recorded", data);
      setFormSubmitted(true);
    } catch (error) {
      console.error("Error:", error.message);
    }
  };

  const SendingStart = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/frontend/startButton", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: "start" }),
      });

      if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
      const data = await response.json();
      console.log("Event started", data);
    } catch (error) {
      console.error("Error:", error.message);
    }
  };

  const SendingStop = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/frontend/stopButton", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: "stop" }),
      });

      if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
      const data = await response.json();
      console.log("Event stopped", data);

      
  
    } catch (error) {
      console.error("Error:", error.message);
    }
  };

  const handleBack = () => navigate("/");

  return (
    <>
      {!formSubmitted ? (
        <form onSubmit={handleSubmit} id="eventForm">
          <h3 className="h3">Event Details</h3>
          <div className="form-group">
            <label htmlFor="name">Event Name</label>
            <input className="pass" name="name" type="text" required />
            <label htmlFor="number">Number of Tickets</label>
            <input className="pass" name="number" type="number" required />
            <label htmlFor="date">Date</label>
            <input className="pass" name="date" type="date" required />
            <label htmlFor="time">Time</label>
            <input className="pass" name="time" type="time" required />
            <label htmlFor="location">Location</label>
            <input className="pass" name="location" type="text" required />
            <label htmlFor="price">Ticket Price</label>
            <input className="pass" name="price" type="number" required />
            <label htmlFor="image">Image</label>
            <input className="pass" name="image" type="file" />
          </div>
          <button className="cancel" type="button" onClick={handleBack}>
            Reset
          </button>
          <button className="submit" type="submit">
            Submit
          </button>
        </form>
      ) : (
        <div className="start-button">
          <button className="submit" onClick={SendingStart}>
            Start
          </button>
          <p className="p">Click to start ticket releasing</p>
          <button className="submit" onClick={SendingStop}>
            Stop
          </button>
          <p className="p" style={{ color: "white" }}>
            Click to stop ticket releasing
          </p>
          <button className="submit" onClick={handleBack}>
            Back
          </button>
        </div>
      )}
    </>
  );
};

export default EventForm;
