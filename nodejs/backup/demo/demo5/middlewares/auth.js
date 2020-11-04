const db = require('../database');

module.exports = {

  /**
 * Authorization: Basic base64(username:password) 
 */

  auth: function (req, res, next){
    let authorization = req.headers.authorization;
    let base64Auth = authorization.replace('Basic ', '');
    let rawUserPass = Buffer.from(base64Auth, "base64").toString();
    let splitUserPass = rawUserPass.split(":");
    db.from("users")
    .where("username", splitUserPass[0])
    .where("password", splitUserPass[1]).then(data=>{
      if(data.length > 0){
        next();
      }else{
        res.status(401).json({
          message: "Invalid username or password."
        });
      }
    }).catch(error=>{
      res.status(401).json({
        message: "Invalid username or password."
      });
    });
  }
};