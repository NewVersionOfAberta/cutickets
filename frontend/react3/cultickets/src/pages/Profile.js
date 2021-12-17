import React, { useState, useContext, useCallback, useEffect } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { SoldTicket } from "../components/SoldTicket";
import { NavLink, useParams } from "react-router-dom";

export const Profile = () => {
  const [profileInfo, setProfileInfo] = useState();
  const [tickets, setTickets] = useState();
  const [change, setChange] = useState(false);
  const { token, userId } = useContext(AuthContext);
  const { request } = useHttp();

  const fetchProfileInfo = useCallback(async () => {
    try {
      const fetched = await request(`/user/${userId}`, "GET", null, {
        Authorization: `user ${token}`,
      });
      setChange(false);
      setProfileInfo(fetched.info);
    } catch (e) {}
  }, [userId, setProfileInfo, token]);

  const fetchTickets = useCallback(async () => {
    try {
      const fetched = await request(`/user/tickets/${userId}`, "GET", null, {
        Authorization: `user ${token}`,
      });

      setTickets(fetched.tickets);
    } catch (e) {}
  }, [userId, setTickets, token]);

  useEffect(() => {
    fetchTickets();
    fetchProfileInfo();
  }, [fetchTickets, fetchProfileInfo, change]);
  if (tickets && profileInfo) {
    const soldTickets = tickets.map((e) => {
      return <SoldTicket>{{ ticket: e, setChange }}</SoldTicket>;
    });

    return (
      <>
        <div className="container">
          <section className="container">
            <div className="card">
              <div className="card-body">
                <h5 id="user-name" className="card-title">
                  {`${profileInfo.userName}`}
                </h5>
                <div id="user-name">{`Name: ${profileInfo.name} ${profileInfo.patronymic} ${profileInfo.surname}`}</div>
                <div id="user-bday">{`Date of birth: ${profileInfo.birthDate}`}</div>
                <div id="user-city">{`${profileInfo.city}`}</div>
                <div id="user-email">{`${profileInfo.email}`}</div>
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
  } else {
    return <></>;
  }
};
