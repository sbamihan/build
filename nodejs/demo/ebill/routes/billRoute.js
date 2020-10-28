const router = require('express').Router();
const db = require('../database');

router.get('/accounts/:accountId', function (req, res) {
  var accountId = req.params.accountId;

  async function getCustomerInfo() {
    async function getAccount() {
      return new Promise((resolve, reject) => {
        db.column('account_id', 'name')
          .select()
          .from('account')
          .where('account_id', accountId)
          .then(data => {
            resolve(data);
          })
          .catch(error => {
            console.log("ERROR => " + error.toString())
            resolve([]);
          });
      });
    };

    async function getContacts() {
      return new Promise((resolve, reject) => {
        db.column('contact_type', 'stat_flg', 'prim_sw', 'value')
          .select()
          .from('contact')
          .where('account_id', accountId)
          .then(data => {
            resolve(data);
          })
          .catch(error => {
            console.log("ERROR => " + error.toString())
            resolve([]);
          });
      });
    };

    async function getSubscriptions(acct_id) {
      return new Promise((resolve, reject) => {
        db.column('subscription_type', 'stat_flg', 'subscribe')
          .select()
          .from('subscription')
          .where('account_id', acct_id)
          .then(data => {
            resolve(data);
          })
          .catch(error => {
            console.log("ERROR => " + error.toString())
            resolve([]);
          });
      });
    };

    var customerInfo = {};
    var account = await getAccount();
    var contacts = await getContacts();

    if (account.length > 0) {
      var subscriptions = await getSubscriptions(account[0].account_id);

      customerInfo = {
        account_id: account[0].account_id,
        account_name: account[0].name,
        contacts: contacts,
        subscriptions: subscriptions
      }
    }

    res.set('Content-Type', 'application/json');
    res.status(200);
    res.json(customerInfo);
  }

  getCustomerInfo();

});

module.exports = router;