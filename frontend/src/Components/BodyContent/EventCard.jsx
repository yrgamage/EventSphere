import './EventCard.css';
import PropTypes from 'prop-types';

function EventCard(props) {
  const { eventName, eventLocation, eventDate, eventTime, ticketCount, imageUrl } = props;

  // Check if props are undefined or null before rendering
  if (!props) {
    return <div>Event data not available</div>;
  }

  return (
    <div className="card">
      <div className="card-content">
        <div className="event-field">
          <span className="value">{eventName || "Not Provided"}</span>
        </div>
        {imageUrl && (
          <img
            className="card-image"
            src={imageUrl}
            alt="Event"
            height={300}
          />
        )}
        <div className="event-field">
          <span className="label">Event Location:</span>
          <span className="value">{eventLocation || "Not Provided"}</span>
        </div>
        <div className="event-field">
          <span className="label">Event Date:</span>
          <span className="value">{eventDate || "Not Provided"}</span>
        </div>
        <div className="event-field">
          <span className="label">Event Time:</span>
          <span className="value">{eventTime || "Not Provided"}</span>
        </div>
        <div className="event-field">
          <span className="label">Available Tickets:</span>
          <span className="value">{ticketCount || 0}</span>
        </div>
      </div>
    </div>
  );
}

// Prop validation with PropTypes
EventCard.propTypes = {
  eventName: PropTypes.string,
  eventLocation: PropTypes.string,
  eventDate: PropTypes.string,
  eventTime: PropTypes.string,
  ticketCount: PropTypes.number,
  imageUrl: PropTypes.string,
};

export default EventCard;
