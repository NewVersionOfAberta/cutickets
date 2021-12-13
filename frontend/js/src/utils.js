const PATH_TO_SERVER = "http://localhost:8080";
const $ = require("jquery");

export const createPath = (relativePath) => {
  return PATH_TO_SERVER + relativePath;
};

export const moveToPage = (pageName) => {
  window.location = pageName;
};

export const storeToken = ({ user, token }) => {
  localStorage.setItem("currentUser", JSON.stringify(user));
  localStorage.setItem("token", "Bearer " + token);
  console.log("after login");
};

export const createRequest = (url, protocol, callback) => {
  const jwtToken = localStorage.getItem("token");
  $.ajax({
    url: createPath(url),
    success: (data, status) => callback(data, status),
    type: protocol,
    headers: {
      Authorization: jwtToken,
    },
    dataType: "json",
  });
};

export const createRequestWithBody = (url, protocol, data, callback) => {
  const jwtToken = localStorage.getItem("token");
  $.ajax({
    url: createPath(url),
    dataType: "json",
    success: (data, status) => callback(data, status),
    error: (e) => {
      console.log(e);
    },
    type: protocol,
    headers: {
      "Content-Type": "application/json",
      Authorization: jwtToken,
    },
    data: JSON.stringify(data),
  });
};
