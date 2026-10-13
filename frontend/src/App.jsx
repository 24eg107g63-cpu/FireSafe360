import { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

const API_URL = "http://localhost:8081";

function App() {
  const [incident, setIncident] = useState({
    location: "",
    fireType: "",
    buildingType: "",
    floors: "",
    peopleAtRisk: "",
    description: ""
  });

  const [incidents, setIncidents] = useState([]);
  const [message, setMessage] = useState("");

  // Load incidents from backend
  const loadIncidents = async () => {
    try {
      const response = await axios.get(`${API_URL}/api/incidents`);
      setIncidents(response.data);
    } catch (error) {
      console.error("Error loading incidents:", error);
    }
  };

  useEffect(() => {
    loadIncidents();
  }, []);

  const handleChange = (e) => {
    setIncident({
      ...incident,
      [e.target.name]: e.target.value
    });
  };

  const submitIncident = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        `${API_URL}/api/incidents`,
        {
          location: incident.location,
          fireType: incident.fireType,
          buildingType: incident.buildingType,
          floors: Number(incident.floors),
          peopleAtRisk: Number(incident.peopleAtRisk),
          description: incident.description,
          status: "REPORTED"
        }
      );

      setMessage(
        `Incident #${response.data.id} reported successfully. Severity: ${response.data.severity}`
      );

      setIncident({
        location: "",
        fireType: "",
        buildingType: "",
        floors: "",
        peopleAtRisk: "",
        description: ""
      });

      // Refresh incident list
      loadIncidents();

    } catch (error) {
      console.error(error);
      setMessage("Error: Unable to report incident.");
    }
  };

  return (
    <div className="app">

      {/* Header */}
      <header className="header">
        <h1>🔥 FireSafe 360</h1>
        <p>Smart Fire Detection & Response Platform</p>
      </header>

      <main className="dashboard">

        {/* Report Incident */}
        <div className="welcome">
          <h2>🚨 Report Fire Incident</h2>
          <p>Enter the incident details below.</p>
        </div>

        <form className="incident-form" onSubmit={submitIncident}>

          <label>Location</label>
          <input
            type="text"
            name="location"
            value={incident.location}
            onChange={handleChange}
            placeholder="Enter incident location"
            required
          />

          <label>Fire Type</label>
          <select
            name="fireType"
            value={incident.fireType}
            onChange={handleChange}
            required
          >
            <option value="">Select fire type</option>
            <option value="Electrical">Electrical</option>
            <option value="Gas">Gas</option>
            <option value="Chemical">Chemical</option>
            <option value="Normal">Normal</option>
          </select>

          <label>Building Type</label>
          <select
            name="buildingType"
            value={incident.buildingType}
            onChange={handleChange}
            required
          >
            <option value="">Select building type</option>
            <option value="Residential">Residential</option>
            <option value="Commercial">Commercial</option>
            <option value="Industrial">Industrial</option>
            <option value="Hospital">Hospital</option>
          </select>

          <label>Number of Floors</label>
          <input
            type="number"
            name="floors"
            min="1"
            value={incident.floors}
            onChange={handleChange}
            required
          />

          <label>People at Risk</label>
          <input
            type="number"
            name="peopleAtRisk"
            min="0"
            value={incident.peopleAtRisk}
            onChange={handleChange}
            required
          />

          <label>Description</label>
          <textarea
            name="description"
            value={incident.description}
            onChange={handleChange}
            placeholder="Describe the fire incident"
            rows="4"
            required
          />

          <button type="submit" className="submit-button">
            🚨 Report Incident
          </button>

        </form>

        {/* Message */}
        {message && (
          <div className="message">
            {message}
          </div>
        )}

        {/* Incident List */}
        <section className="incident-section">

          <div className="section-header">
            <h2>📋 Reported Incidents</h2>

            <button
              className="refresh-button"
              onClick={loadIncidents}
            >
              🔄 Refresh
            </button>
          </div>

          {incidents.length === 0 ? (
            <p className="no-data">No incidents reported yet.</p>
          ) : (
            <div className="incident-table-container">

              <table className="incident-table">

                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Location</th>
                    <th>Fire Type</th>
                    <th>Building</th>
                    <th>People at Risk</th>
                    <th>Severity</th>
                    <th>Status</th>
                  </tr>
                </thead>

                <tbody>
                  {incidents.map((item) => (
                    <tr key={item.id}>

                      <td>{item.id}</td>
                      <td>{item.location}</td>
                      <td>{item.fireType}</td>
                      <td>{item.buildingType}</td>
                      <td>{item.peopleAtRisk}</td>

                      <td>
                        <span
                          className={`severity ${item.severity?.toLowerCase()}`}
                        >
                          {item.severity}
                        </span>
                      </td>

                      <td>{item.status}</td>

                    </tr>
                  ))}
                </tbody>

              </table>

            </div>
          )}

        </section>

      </main>

    </div>
  );
}

export default App;