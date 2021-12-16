import React, { useState, useContext } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { SoldTicket } from "../components/SoldTicket";
import { NavLink, useParams } from "react-router-dom";

export const Profile = () => {
  const [profileInfo, setProfileInfo] = useState();
  const [tickets, setTickets] = useState();
  const { token, userId } = useContext(AuthContext);
  const { request } = useHttp();

  const fetchProfileInfo = useCallback(async () => {
    try {
      const fetched = await request(``, "GET", null, {
        Authorization: `user ${token}`,
      });
      console.log("", fetched);

      setProfileInfo(fetched);
    } catch (e) {}
  }, [userId, setProfileInfo]);

  const fetchTickets = useCallback(async () => {
    try {
      const fetched = await request(`/user/tickets/${userId}`, "GET", null, {
        Authorization: `user ${token}`,
      });

      setTickets(fetched.tickets);
    } catch (e) {}
  }, [userId, setTickets]);

  useEffect(() => {
    fetchTickets();
  }, [fetchTickets]);

    const soldTickets = tickets.map((e) => {
      return <SoldTicket>{e}</SoldTicket>;
    });
    return (
      <>
        <div className="container">
          <section className="container">
            <div className="card">
              <div className="card-body">
                <h5 id="user-name" className="card-title">
                  AndryKiller
                </h5>
                <div id="user-name">Name: Sergey Gavrilovitch Petrov</div>
                <div id="user-bday">Date of birth: 20.10.1999</div>
                <div id="user-city">Vitebsk, Belarus</div>
                <div id="user-email">some@mail.ru</div>
                <div class="card-footer text-muted">
                  <NavLink to={`/statistics`}>Statistics</NavLink>
                </div>
                <h5>Active orders:</h5>
                {soldTickets}
              </div>
            </div>
          </section>
        </div>
      </>
    );
  }
};
