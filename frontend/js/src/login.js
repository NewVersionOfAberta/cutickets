import { moveToPage, storeToken, createRequestWithBody } from "./utils";

const $ = require("jquery");

function afterLogin(data, status) {
  try {
    storeToken(data);
    moveToPage("profile.html");
  } catch (e) {
    console.log(e);
  }
}

function login() {
  const email = $("#email").val();
  const password = $("#password").val();
  const formData = { email, password };
  console.log("Here", formData);
  try {
    createRequestWithBody("/login", "POST", formData, afterLogin);
  } catch (e) {
    console.log(e);
  }
}

$(document).on("click", "#btn-login", login);
