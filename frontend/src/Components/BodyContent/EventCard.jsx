// EventCard.jsx
import './EventCard.css';
import PropTypes from 'prop-types';
function EventCard(props) {
  // Check if event is undefined or null before rendering
  if (!props) {
    return <div>Event data not available</div>;
  }

  return (
    <div className="card">
      <div className="card-content">
        <label className="card-label">Event Name:</label>
        <input
          className="card-input"
          value={props.eventName || "N/A"} // Default to "N/A" if eventName is not available
          readOnly
          style={{ backgroundColor: "inherit" }}
        />
        {props.imageUrl && (
          <img
            className="card-image"
            src={props.imageUrl}
            alt="Event"
            height={300}
          />
        )}
        <label className="card-label">Event Location:</label>
        <input
          className="card-input"
          value={props.eventLocation || "N/A"} // Default to "N/A" if eventLocation is not available
          readOnly
          style={{ backgroundColor: "inherit" }}
        />
        <label className="card-label">Event Date:</label>
        <input
          className="card-input"
          value={props.eventDate || "N/A"} // Default to "N/A" if eventDate is not available
          readOnly
          style={{ backgroundColor: "inherit" }}
        />
        <label className="card-label">Event Time:</label>
        <input
          className="card-input"
          value={props.eventTime || "N/A"} // Default to "N/A" if eventTime is not available
          readOnly
          style={{ backgroundColor: "inherit" }}
        />
        <label className="card-label">Total Tickets:</label>
        <input
          className="card-input"
          value={props.ticketCount || 0} // Default to 0 if ticketCount is not available
          readOnly
          style={{ backgroundColor: "inherit" }}
        />
      </div>
    </div>
  );
}
// Prop validation with PropTypes
EventCard.propTypes = {
  eventName: PropTypes.string,         // Should be a string
  eventLocation: PropTypes.string,     // Should be a string
  eventDate: PropTypes.string,         // Should be a string
  eventTime: PropTypes.string,         // Should be a string
  ticketCount: PropTypes.number,       // Should be a number
  imageUrl: PropTypes.string,          // Should be a string (URL of the image)
};
export default EventCard;
