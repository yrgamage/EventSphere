import EventCard from '../Components/BodyContent/EventCard'; // Fixed import to align with the actual file
import { useState, useEffect } from 'react';
import Header from '../Components/Header/Header';
import Footer from '../Components/Footer/Footer';

function Home() {
  console.log("Home component rendered");
  const [events, setEvents] = useState([]);

  useEffect(() => {
    console.log("useEffect executed");
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

        if (data && Array.isArray(data)) {
          // Map the array where each item contains both an event and an image
          const eventsData = data.map((item) => ({
            eventId: item.event.eventID,
            eventName: item.event.eventName,
            eventLocation: item.event.eventLocation,
            eventDate: item.event.eventDate,
            eventTime: item.event.eventTime,
            ticketCount: item.event.ticketCount,
            imageUrl: item.image ? `data:image/jpeg;base64,${item.image}` : null,
          }));
          console.log("Mapped Events Data:", eventsData); // Verify mapped data
          setEvents(eventsData);
        }
      })
      .catch((error) => console.error("Fetch Error:", error));
  }, []); // Runs once on component mount

  if (events.length === 0) {
    return <div>Loading events...</div>;
  }

  return (
    <>
    <Header/>
    <div>
      <div className="event-list">
        {events.map((event, index) => {
          console.log(`Rendering EventCard - Index: ${index}`, event);
          return <EventCard key={event.eventId || index} {...event} />;
        })}
      </div>
    </div>
    <Footer/>
    </>
  );
}

export default Home;
