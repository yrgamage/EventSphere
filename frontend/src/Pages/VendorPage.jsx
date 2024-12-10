import { useState } from 'react';
import { useNavigate } from 'react-router-dom';


const EventForm = () => {
  const navigate = useNavigate();
  const [formSubmitted, setFormSubmitted] = useState(false);

  const [formData, setFormData] = useState({
    eventName: "",
    ticketCount: 0,
    eventDate: "",
    eventTime: "",
    eventLocation: "",
    ticketPrice: 0,
    image: null,
  });

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formDataToSend = new FormData();
    formDataToSend.append("eventName", formData.eventName);
    formDataToSend.append("ticketCount", formData.ticketCount);
    formDataToSend.append("eventDate", formData.eventDate);
    formDataToSend.append("eventTime", formData.eventTime);
    formDataToSend.append("eventLocation", formData.eventLocation);
    formDataToSend.append("ticketPrice", formData.ticketPrice);
    if (formData.image) formDataToSend.append("image", formData.image);

    try {
      const response = await fetch("http://localhost:8080/event/saveEvent", {
        method: "POST",
        body: formDataToSend,
      });

      if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
      const data = await response.json();
      console.log("Event recorded", data);
      setFormSubmitted(true);
    } catch (error) {
      console.error("Error:", error.message);
    }
  };

  const handleInputChange = (e) => {
    const { name, value, type, files } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: type === "file" ? files[0] : value,
    }));
  };

  const handleBack = () => navigate("/");

  return (
    <>
      {!formSubmitted ? (
        <form onSubmit={handleSubmit} id="eventForm" encType="multipart/form-data">
          <h3 className="h3">Event Details</h3>
          <div className="form-group">
            <label htmlFor="eventName">Event Name</label>
            <input
              className="pass"
              name="eventName"
              type="text"
              value={formData.eventName}
              onChange={handleInputChange}
              required
            />
            <label htmlFor="ticketCount">Number of Tickets</label>
            <input
              className="pass"
              name="ticketCount"
              type="number"
              value={formData.ticketCount}
              onChange={handleInputChange}
              required
            />
            <label htmlFor="eventDate">Date</label>
            <input
              className="pass"
              name="eventDate"
              type="date"
              value={formData.eventDate}
              onChange={handleInputChange}
            />
            <label htmlFor="eventTime">Time</label>
            <input
              className="pass"
              name="eventTime"
              type="time"
              value={formData.eventTime}
              onChange={handleInputChange}
            />
            <label htmlFor="eventLocation">Location</label>
            <input
              className="pass"
              name="eventLocation"
              type="text"
              value={formData.eventLocation}
              onChange={handleInputChange}
            />
            <label htmlFor="ticketPrice">Ticket Price</label>
            <input
              className="pass"
              name="ticketPrice"
              type="number"
              value={formData.ticketPrice}
              onChange={handleInputChange}
            />
            <label htmlFor="image">Image</label>
            <input
              className="pass"
              name="image"
              type="file"
              onChange={handleInputChange}
            />
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
          <button className="submit" onClick={() => console.log("Start triggered")}>
            Start
          </button>
          <p className="p">Click to start ticket releasing</p>
          <button className="submit" onClick={() => console.log("Stop triggered")}>
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
