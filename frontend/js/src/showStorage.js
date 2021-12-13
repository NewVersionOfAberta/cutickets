import { createRequest } from "./utils";

export const showStorage = {
  showsList: [],
  theaters: [],
  showTypes: [],

  currentShowType: 0,
  currentTheater: 0,

  loadFilters(callback) {
    createRequest("/shows/filters", "get", callback);
  },

  loadShows(callback) {
    const filteredShowsUrl =
      "/shows/theater/" +
      showStorage.currentTheater +
      "/type/" +
      showStorage.currentShowType;

    createRequest(filteredShowsUrl, "get", callback);
  },

  applyFilters(data) {
    showStorage.showTypes = data.showTypes;
    showStorage.theaters = data.theaters;
  },

  applyShows(data) {
    showStorage.showsList = data.shows;
  },

  findShowById(id) {
    if (!this || !this.showsList) {
      console.log(this);
      return;
    }
    let currentItem;
    this.showsList.forEach((e) => {
      console.log(e);
      if (e.id == id) {
        currentItem = e;
      }
    });
    return currentItem;
  },
};
