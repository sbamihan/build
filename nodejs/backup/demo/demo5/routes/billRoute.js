const router = require('express').Router();

const db = require('../database');

router.get('/bills/:tranNo', function (req, res) {
  var tranNo = req.params.tranNo;

  async function getBill() { 
    async function getBpHeader() {
      return new Promise((resolve, reject) => {
        db.from("bp_headers").where("tran_no", tranNo)
          .then(data => {
            resolve(data);
          })
          .catch(error => {
            resolve([{"col1": "value1", "col2": "value2"}]);
          });
      });
    };

    async function getMeterDetails() {
      return new Promise((resolve, reject) => {
        db.from("bp_meter_details").where("tran_no", tranNo)
          .then(data => {
            resolve(data);
          })
          .catch(error => {
            resolve([{"col1": "value1", "col2": "value2"}]);
          });
      });
    };

    const header = await getBpHeader();
    const meterDetails = await getMeterDetails();

    bill = {
      header: header,
      details: meterDetails
    }

    res.json(bill);
  }

  getBill();
  
});

module.exports = router;