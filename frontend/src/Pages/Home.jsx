
import Event from '../Components/BodyContent/EventCard';
import { useState, useEffect } from 'react';

function Home() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    // Fetch the data from your backend
    fetch("http://localhost:8080/event/eventsWithImages")
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log("API Response:", data);
        if (data && Array.isArray(data.event)) {
          // Assuming the backend returns an array of events
          const eventsData = data.event.map((event,index) => ({
            eventName: event.eventName,
            eventLocation: event.eventLocation,
            eventDate: event.eventDate,
            eventTime: event.eventTime,
            ticketCount: event.ticketCount,
            imageUrl: data.image[index] ? `data:image/jpeg;base64,${data.image[index]}` : null,
          }));
          setEvents(eventsData);
        }
      })
      .catch((error) => console.error("Fetch Error:", error));
  }, []);

  if (events.length === 0) {
    return <div>Loading events...</div>;
  }
  return (
    <div>
      <h1>Upcoming Events</h1>
      <div className="event-list">
        {events.map((event, index) => (
          <Event key={index} event={event} />
        ))}
      </div>
    </div>
  )
}

export default Home