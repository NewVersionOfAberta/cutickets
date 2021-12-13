const path = require("path");

module.exports = {
  mode: "development",
  entry: {
    show: "./src/show.js",
    book: "./src/book.js",
    login: "./src/login.js",
    profil: "./src/profil.js",
  },
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "[name].js",
    library: "[name]",
  },
};
