import React, { useState, useContext } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { SoldTicket } from "../components/SoldTicket";
import { useParams } from "react-router-dom";

export const Profile = () => {
  const showId = useParams().id;
  const [profileInfo, setProfileInfo] = useState();
  const { token } = useContext(AuthContext);
  const { request } = useHttp();

  //   const fetchShows = useCallback(async () => {
  //     try {
  //       const fetched = await request(`/shows/showId=${showId}`, "GET", null, {
  //         Authorization: `user ${token}`,
  //       });
  //       console.log("Show schedule", fetched);

  //       setScheduledShows(fetched);
  //     } catch (e) {}
  //   }, [showId, setScheduledShows]);

  //   useEffect(() => {
  //     fetchShows();
  //   }, [fetchShows]);
  //   if (scheduledShows) {
  //     console.log("Show", scheduledShows.show);
  //     const dates = scheduledShows.scheduled_shows.map((e) => {
  //       return (
  //         <>
  //           <NavLink to={`/tickets/${e.id}`}>Date: {e.datetime}</NavLink>
  //           <br />
  //         </>
  //       );
  //     });
  const tickets = [
    {
      showName: "Durochka",
      datetime: "2021-09-01",
      status: "inactive",
      place: 1,
      row: 4,
      price: 1000,
    },
  ];
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
              <div class="card-footer text-muted">2 days ago</div>
              <h5>Active orders:</h5>
              {soldTickets}
            </div>
          </div>
        </section>
      </div>
    </>
  );
  //   return <Loader></Loader>;
  //   return showList;
};
