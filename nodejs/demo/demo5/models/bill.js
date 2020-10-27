const db = require('../database');

let model = {
  getBill: function(tranNo = null) {
    return new Promise((resolve, reject) => {
      db.from("bp_header").where("tran_no", tranNo)
        .then(data => {
          resolve(data);
        })
        .catch(error => {
          resolve([]);
        });
    });
  },

  getMeterDetails: function(tranNo = null) {
    return new Promise((resolve, reject) => {
      db.from("bp_meter_details").where("tran_no", tranNo)
        .then(data => {
          resolve(data);
        })
        .catch(error => {
          resolve([]);
        });
    });
  },
};

module.exports = model;