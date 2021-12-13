import { moveToPage } from "./utils";
import { showStorage } from "./showStorage";

const $ = require("jquery");

const showFilters = (data, status) => {
  showStorage.applyFilters(data);

  const dropdownTheaters = $("#theaters-list");
  const dropdownShowTypes = $("#show-types-list");
  fillDropdownWithItems("theater", showStorage.theaters, dropdownTheaters);
  fillDropdownWithItems("show-type", showStorage.showTypes, dropdownShowTypes);
};

const fillDropdownWithItems = (prefixItemId, items, dropdown) => {
  dropdown.empty();
  items.forEach((e) =>
    dropdown.append(
      `<li class="dropdown-item" id="${prefixItemId}-${e.id}" value="${e.id}">${e.name}</li>`
    )
  );
};

const displayShows = (shows, nameFilter = "") => {
  const showList = $("#show-list");
  showList.empty();
  const filter = nameFilter.toLowerCase();
  shows.forEach((e) => {
    if (!nameFilter || e.name.toLowerCase().startsWith(filter)) {
      showList.append(`
      <div class="card">
      <div class="card-header">
      <div class="container">
        <div class="row">
          <span class="col">
          ${e.name}
          </span>
          <span class="col text-secondary">
          ${e.theater.name}
          </span>
          </div>
        </div>
        </div>
      </div>
      <div class="card-body">
        <p class="card-text">${e.description}</p>
        <ul>
          <li>Type: ${e.showType.name}</li>
          <li>Date: ${e.datetime}</li>
        </ul>
        <button value="${e.id}" class="btn btn-primary book">Book</button>
      </div>
    </div>`);
    }
  });
};

const processShows = (data, status) => {
  showStorage.applyShows(data);
  displayShows(showStorage.showsList);
};

showStorage.loadFilters(showFilters);
showStorage.loadShows(processShows);

$("#btn-apply-filters").on("click", () => showStorage.loadShows(processShows));

$("#btn-clear-filters").on("click", () => {
  showStorage.currentShowType = 0;
  showStorage.currentTheater = 0;
  $("#btn-dropdown-types").text("Show types");
  $("#btn-dropdown-theater").text("Theaters");
  showStorage.loadShows(processShows);
});

$("#btn-search-for-name").on("click", () => {
  const value = $("#input-name-filter").val();
  console.log(value);
  displayShows(showStorage.showsList, value);
});

$("btn-cart").on("click", () => moveToPage("cart.html"));

$(document).on("click", "li.dropdown-item", function () {
  const applyedFilter = $(this);
  const id = this.id;
  if (id.startsWith("show-type")) {
    $("#btn-dropdown-types").text(applyedFilter.text());
    showStorage.currentShowType = applyedFilter.val();
  } else if (id.startsWith("theater")) {
    $("#btn-dropdown-theater").text(applyedFilter.text());
    showStorage.currentTheater = applyedFilter.val();
  }
});

$(document).on("click", "button.btn.btn-primary.book", function () {
  const id = $(this).val();
  const currentShow = showStorage.findShowById(id);
  localStorage.setItem("currentShow", JSON.stringify(currentShow));
  console.log("Save", currentShow);
  moveToPage("book.html");
});

const btnSign = $("#nav-login");
if (localStorage.getItem("token")) {
  btnSign.text("Profile");
  btnSign.on("click", () => moveToPage("profile.html"));
} else {
  btnSign.text("Sign up");
  btnSign.on("click", () => moveToPage("login.html"));
}
