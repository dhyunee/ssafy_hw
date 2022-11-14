export default {
  makeDateStr(year, month, day, type) {
    return year + type + (month < 10 ? "0" + month : month) + type + (day < 10 ? "0" + day : day);
  },
};
