### Day 2 Exercise

---

#### Upload

1. Install multer as local dependency `npm install multer`

2. Create Upload directory `mkdir upload`

3. Create a storage folder `mkdir storage`

4. Add new file named `DiskStorage.js` inside storage folder.

5. Update our `app.js` to handle `json` **POST** request.
```javascript
...
...
...
const app = express();

// Setup middleware for parsing application/json
app.use(express.json());
// Setup middleware for parsing application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));
...
...
...
```

6. Add upload routes, create `upload.js` inside **routes** folder. Visit [https://www.npmjs.com/package/multer](https://www.npmjs.com/package/multer) for more configs.
```javascript
const router = require('express').Router();
const multer = require('multer');

let upload = multer({ dest: 'upload/' })
router.post('/', upload.single('avatar'), function(req, res){
  res.json(req.file);
});

module.exports = router;
```

7. Update the `app.js` to include upload routes.
```javascript
....
const public = require('./routes/public');
const private = require('./routes/private');

const upload = require('./routes/upload1'); //<-- add

app.use('/upload', upload);
....
....
```

8. You can now test the upload with `avatar` as `file name`.
```bash
curl -X POST \
  http://localhost:3001/upload \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'avatar=@/path/to/file.png'
```

9. Let's start creating our own `DiskStorage`. Open/Create `storage/DiskStorage.js`
```javascript
const fs = require('fs'); //filesystem (no need to npm install)
const path = require('path'); //path library (no need to npm install)

// Create Destination Path for upload
function getDestination(req, file, cb) {
  cb(null, path.join(__dirname, `../upload/${file.originalname}`));
}

// Main Function
function DiskStorage(opts) {
  this.getDestination = opts.destination || getDestination;
}

// Save file after upload
DiskStorage.prototype._handleFile = function _handleFile(req, file, cb) {
  this.getDestination(req, file, function(err, path) {
    if (err) return cb(err);

    let outStream = fs.createWriteStream(path);

    file.stream.pipe(outStream);
    outStream.on('error', cb);
    outStream.on('finish', function() {
        //success
        cb(null, {
          path: path,
          size: outStream.bytesWritten,
        });
    });
  });
};

// remove file if there is problem with request
DiskStorage.prototype._removeFile = function _removeFile(req, file, cb) {
  // unlink the file if there is problem
  fs.unlink(file.path, cb);
};
module.exports = function(opts) {
  return new DiskStorage(opts);
};
```

10. Update `routes/upload.js` to add new route which use `DiskStorage`. Visit [https://www.npmjs.com/package/multer](https://www.npmjs.com/package/multer) for more configs.
```javascript
const router = require('express').Router();
const multer = require('multer');
const DiskStorage = require('../storage/DiskStorage');

let upload = multer({ dest: 'upload/' })
router.post('/', upload.single('avatar'), function(req, res){
  res.json(req.file);
});

let filesUpload = multer({ storage: DiskStorage({})});
router.post('/files', filesUpload.single('avatar'), function(req, res){
  res.json(req.file);
});
module.exports = router;
```

11. You can now test the upload with `avatar` as `file name`.
```bash
curl -X POST \
  http://localhost:3001/upload/files \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'avatar=@/path/to/file.png'
```

---

#### Database

---

#### Public
- Create `/public` route
- All `/public` routes should only display `plant` records which are `private=false`
- Create GET `/public/plants` which displays all plant information
- Create GET `/public/plants/:pid` which only display information of specific plant
- Create GET `/public/plants/:pid/meters` which only display information of specific plant list of meters
- Create GET `/public/plants/:pid/meters/:mid` which only display information of specific plant meter of the plant
- Create **Plant** POST `/public/plants` to insert new record
- Create **Meter** POST `/public/plants/:pid/meters` to insert new record

#### Private
- Create `/private` route
- All `/private` routes should display `plant` records which are `private=true`
- All `/private` routes should be secured by a middleware that checks the `Basic Authentication`
  - **USER** and **PASS** is inside the `.env`
- Create GET `/private/plants` which displays all plant information
- Create GET `/private/plants/:pid` which only display information of specific plant
- Create GET `/private/plants/:pid/meters` which only display information of specific plant list of meters
- Create GET `/private/plants/:pid/meters/:mid` which only display information of specific plant meter of the plant
- Create **Plant** POST `/private/plants` to insert new record
- Create **Meter** POST `/private/plants/:pid/meters` to insert new record

---

#### Knexfile Configuration

---

### Prerequisite

1. Create `users` table.

2. Create `plants` table.

3. Create `meters` table.

4. Import the `sql` for all tables.
```sql
INSERT INTO users(username, password) VALUES('aboitiz', 'power');

INSERT INTO plants(id, name, private) VALUES(1,'PLANT A',false);
INSERT INTO plants(id, name, private) VALUES(2,'PLANT B',true);
INSERT INTO plants(id, name, private) VALUES(3,'PLANT C',false);
INSERT INTO plants(id, name, private) VALUES(4,'PLANT D',true);
INSERT INTO plants(id, name, private) VALUES(5,'PLANT E',false);
INSERT INTO plants(id, name, private) VALUES(6,'PLANT F',true);
INSERT INTO plants(id, name, private) VALUES(7,'PLANT G',false);
INSERT INTO plants(id, name, private) VALUES(8,'PLANT H',true);
INSERT INTO plants(id, name, private) VALUES(9,'PLANT I',false);
INSERT INTO plants(id, name, private) VALUES(10,'PLANT J',true);
INSERT INTO plants(id, name, private) VALUES(11,'PLANT K',false);
INSERT INTO plants(id, name, private) VALUES(12,'PLANT L',true);
INSERT INTO plants(id, name, private) VALUES(13,'PLANT M',false);
INSERT INTO plants(id, name, private) VALUES(14,'PLANT N',true);
INSERT INTO plants(id, name, private) VALUES(15,'PLANT O',false);
INSERT INTO plants(id, name, private) VALUES(16,'PLANT P',true);
INSERT INTO plants(id, name, private) VALUES(17,'PLANT Q',false);
INSERT INTO plants(id, name, private) VALUES(18,'PLANT R',true);
INSERT INTO plants(id, name, private) VALUES(19,'PLANT S',false);
INSERT INTO plants(id, name, private) VALUES(20,'PLANT T',true);
INSERT INTO plants(id, name, private) VALUES(21,'PLANT U',false);
INSERT INTO plants(id, name, private) VALUES(22,'PLANT V',true);
INSERT INTO plants(id, name, private) VALUES(23,'PLANT W',false);
INSERT INTO plants(id, name, private) VALUES(24,'PLANT X',true);
INSERT INTO plants(id, name, private) VALUES(25,'PLANT Y',false);
INSERT INTO plants(id, name, private) VALUES(26,'PLANT Z',true);
INSERT INTO plants(id, name, private) VALUES(27,'PLANT AA',false);
INSERT INTO plants(id, name, private) VALUES(28,'PLANT BB',true);
INSERT INTO plants(id, name, private) VALUES(29,'PLANT CC',false);
INSERT INTO plants(id, name, private) VALUES(30,'PLANT DD',true);

INSERT INTO meters(id, plant_id, value) VALUES(1, 22, 456.94653163628936);
INSERT INTO meters(id, plant_id, value) VALUES(2, 33, 523.676353610039);
INSERT INTO meters(id, plant_id, value) VALUES(3, 31, 61.70984945822669);
INSERT INTO meters(id, plant_id, value) VALUES(4, 27, 87.87176737010685);
INSERT INTO meters(id, plant_id, value) VALUES(5, 21, 111.08177773449798);
INSERT INTO meters(id, plant_id, value) VALUES(6, 19, 101.04773699148276);
INSERT INTO meters(id, plant_id, value) VALUES(7, 16, 853.4345747254354);
INSERT INTO meters(id, plant_id, value) VALUES(8, 28, 184.47005850255894);
INSERT INTO meters(id, plant_id, value) VALUES(9, 27, 65.98440917827912);
INSERT INTO meters(id, plant_id, value) VALUES(10, 31, 385.67241634098195);
INSERT INTO meters(id, plant_id, value) VALUES(11, 17, 26.32426037944178);
INSERT INTO meters(id, plant_id, value) VALUES(12, 27, 342.0723994539562);
INSERT INTO meters(id, plant_id, value) VALUES(13, 9, 130.46759233787927);
INSERT INTO meters(id, plant_id, value) VALUES(14, 32, 663.2556560045356);
INSERT INTO meters(id, plant_id, value) VALUES(15, 12, 418.8658421918776);
INSERT INTO meters(id, plant_id, value) VALUES(16, 4, 106.00013559972642);
INSERT INTO meters(id, plant_id, value) VALUES(17, 26, 587.5472107742305);
INSERT INTO meters(id, plant_id, value) VALUES(18, 31, 323.56669438077677);
INSERT INTO meters(id, plant_id, value) VALUES(19, 33, 481.0469041278116);
INSERT INTO meters(id, plant_id, value) VALUES(20, 21, 242.54230934447423);
INSERT INTO meters(id, plant_id, value) VALUES(21, 3, 0);
INSERT INTO meters(id, plant_id, value) VALUES(22, 10, 103.4313836885535);
INSERT INTO meters(id, plant_id, value) VALUES(23, 27, 169.2764371781896);
INSERT INTO meters(id, plant_id, value) VALUES(24, 23, 39.96657230229264);
INSERT INTO meters(id, plant_id, value) VALUES(25, 23, 59.05839220056856);
INSERT INTO meters(id, plant_id, value) VALUES(26, 7, 27.149222198729476);
INSERT INTO meters(id, plant_id, value) VALUES(27, 8, 62.67208733343469);
INSERT INTO meters(id, plant_id, value) VALUES(28, 13, 464.1950351022773);
INSERT INTO meters(id, plant_id, value) VALUES(29, 11, 334.08113446736286);
INSERT INTO meters(id, plant_id, value) VALUES(30, 23, 63.500090245234745);
INSERT INTO meters(id, plant_id, value) VALUES(31, 23, 91.81644014627858);
INSERT INTO meters(id, plant_id, value) VALUES(32, 21, 544.8185763877447);
INSERT INTO meters(id, plant_id, value) VALUES(33, 20, 649.2018201179361);
INSERT INTO meters(id, plant_id, value) VALUES(34, 6, 86.9418211254383);
INSERT INTO meters(id, plant_id, value) VALUES(35, 5, 367.63391908346824);
INSERT INTO meters(id, plant_id, value) VALUES(36, 25, 482.8340380430755);
INSERT INTO meters(id, plant_id, value) VALUES(37, 4, 160.344057545454);
INSERT INTO meters(id, plant_id, value) VALUES(38, 8, 207.47743801314857);
INSERT INTO meters(id, plant_id, value) VALUES(39, 12, 29.56431956309254);
INSERT INTO meters(id, plant_id, value) VALUES(40, 3, 302.428213583853);
INSERT INTO meters(id, plant_id, value) VALUES(41, 6, 61.738921395656405);
INSERT INTO meters(id, plant_id, value) VALUES(42, 21, 35.960332443790364);
INSERT INTO meters(id, plant_id, value) VALUES(43, 5, 324.7131528582329);
INSERT INTO meters(id, plant_id, value) VALUES(44, 3, 546.6623643219906);
INSERT INTO meters(id, plant_id, value) VALUES(45, 29, 218.13272745619918);
INSERT INTO meters(id, plant_id, value) VALUES(46, 20, 382.72027401736165);
INSERT INTO meters(id, plant_id, value) VALUES(47, 24, 345.4524088539429);
INSERT INTO meters(id, plant_id, value) VALUES(48, 21, 408.7152842342369);
INSERT INTO meters(id, plant_id, value) VALUES(49, 18, 623.1399336439962);
INSERT INTO meters(id, plant_id, value) VALUES(50, 15, 710.3818460615223);
INSERT INTO meters(id, plant_id, value) VALUES(51, 4, 56.51079550979069);
INSERT INTO meters(id, plant_id, value) VALUES(52, 31, 0);
INSERT INTO meters(id, plant_id, value) VALUES(53, 9, 13.48352410189753);
INSERT INTO meters(id, plant_id, value) VALUES(54, 30, 208.47105558893955);
INSERT INTO meters(id, plant_id, value) VALUES(55, 8, 119.08524679706426);
INSERT INTO meters(id, plant_id, value) VALUES(56, 12, 452.9982447595709);
INSERT INTO meters(id, plant_id, value) VALUES(57, 21, 130.1236003782924);
INSERT INTO meters(id, plant_id, value) VALUES(58, 9, 460.9611382410919);
INSERT INTO meters(id, plant_id, value) VALUES(59, 15, 228.14513952864553);
INSERT INTO meters(id, plant_id, value) VALUES(60, 27, 573.2607144343382);
INSERT INTO meters(id, plant_id, value) VALUES(61, 24, 68.86637581360016);
INSERT INTO meters(id, plant_id, value) VALUES(62, 12, 454.07187184389704);
INSERT INTO meters(id, plant_id, value) VALUES(63, 33, 516.7102336347115);
INSERT INTO meters(id, plant_id, value) VALUES(64, 12, 86.04164437702808);
INSERT INTO meters(id, plant_id, value) VALUES(65, 4, 611.665665112131);
INSERT INTO meters(id, plant_id, value) VALUES(66, 6, 207.4973565935591);
INSERT INTO meters(id, plant_id, value) VALUES(67, 27, 70.82555366966545);
INSERT INTO meters(id, plant_id, value) VALUES(68, 30, 93.32475953726984);
INSERT INTO meters(id, plant_id, value) VALUES(69, 27, 69.62886278256286);
INSERT INTO meters(id, plant_id, value) VALUES(70, 20, 69.04522079967823);
INSERT INTO meters(id, plant_id, value) VALUES(71, 11, 117.38198103820831);
INSERT INTO meters(id, plant_id, value) VALUES(72, 16, 52.22230404754444);
INSERT INTO meters(id, plant_id, value) VALUES(73, 24, 84.02513380225673);
INSERT INTO meters(id, plant_id, value) VALUES(74, 5, 26.74191744662531);
INSERT INTO meters(id, plant_id, value) VALUES(75, 22, 171.3409910299845);
INSERT INTO meters(id, plant_id, value) VALUES(76, 17, 249.10835925753315);
INSERT INTO meters(id, plant_id, value) VALUES(77, 33, 231.48143017483355);
INSERT INTO meters(id, plant_id, value) VALUES(78, 19, 0);
INSERT INTO meters(id, plant_id, value) VALUES(79, 15, 0);
INSERT INTO meters(id, plant_id, value) VALUES(80, 15, 0);
INSERT INTO meters(id, plant_id, value) VALUES(81, 30, 140.7584466152546);
INSERT INTO meters(id, plant_id, value) VALUES(82, 30, 843.7675763343789);
INSERT INTO meters(id, plant_id, value) VALUES(83, 25, 446.3969441253228);
INSERT INTO meters(id, plant_id, value) VALUES(84, 31, 131.7777269336869);
INSERT INTO meters(id, plant_id, value) VALUES(85, 4, 127.55257647057933);
INSERT INTO meters(id, plant_id, value) VALUES(86, 12, 34.0701435340313);
INSERT INTO meters(id, plant_id, value) VALUES(87, 24, 434.1879379561112);
INSERT INTO meters(id, plant_id, value) VALUES(88, 31, 88.37633952045664);
INSERT INTO meters(id, plant_id, value) VALUES(89, 6, 128.61797594363793);
INSERT INTO meters(id, plant_id, value) VALUES(90, 33, 15.487778004105731);
INSERT INTO meters(id, plant_id, value) VALUES(91, 22, 786.5125980618918);
INSERT INTO meters(id, plant_id, value) VALUES(92, 19, 58.641320468841386);
INSERT INTO meters(id, plant_id, value) VALUES(93, 8, 380.62485233577735);
INSERT INTO meters(id, plant_id, value) VALUES(94, 27, 129.52465334750377);
INSERT INTO meters(id, plant_id, value) VALUES(95, 11, 394.99988967920046);
INSERT INTO meters(id, plant_id, value) VALUES(96, 12, 20.851185152967624);
INSERT INTO meters(id, plant_id, value) VALUES(97, 25, 109.23212731740841);
INSERT INTO meters(id, plant_id, value) VALUES(98, 22, 541.253625690512);
INSERT INTO meters(id, plant_id, value) VALUES(99, 30, 176.72237054287427);
```

---

### Setup Model using Knex

Refer to this url [http://knexjs.org](http://knexjs.org/#Installation-node) for more info.

1. Install **knex** module. `npm install knex`

2. Install the **database** that you will be using.
```bash
# Choose 1 only
$ npm install mysql
$ npm install mysql2
$ npm install pg
$ npm install oracledb
$ npm install mssql
$ npm install sqlite3
```

3. Update `.env` for the **DB** settings.
```bash
ENV=development

# DB Credentials
DB_HOST=localhost
DB_USER=root
DB_PASS=password
DB_NAME=training
```

3. Create `knexfile.js` to configure your environment settings.
```javascript
module.exports = {
  development: {
    client: "mysql",
    connection: {
      host: process.env.DB_HOST,
      database: process.env.DB_NAME,
      user: process.env.DB_USER,
      password: process.env.DB_PASS
    },
    pool: {
      min: 2,
      max: 10
    }
  }
};
```

4. Create `database.js` to setup the **DB** config.
```javascript
const config = require('./knexfile')[process.env.ENV];
const connection = require('knex')(config);

connection.on('query', function(data) {
  console.log('debug', `[QUERY]`, data.sql);
});

connection.on('query-error', function(error) {
  console.log('error', `[ERROR]`, error);
});

module.exports = connection;
```

5. Update `meter.js` under **models** folder.
```javascript
// fetch data through DB
const db = require('../database');

let model = {
  getPublicPlantMeters: function(pid, mid=null){
    let query = db
    .select("mtr.*").from("meters as mtr")
    .leftJoin("plants as plt", "plt.id", "mtr.plant_id")
    .where("plt.private", 0);
    if(mid){
      query = query.where("mtr.id", mid)
    }
    if(pid){
      query = query.where("plt.id", pid)
    }
    return new Promise((resolve, reject)=>{
      query
      .then(data=>{
        resolve(data);
      }).catch(error=>{
        resolve([]);
      });
    });
  },
  getPrivatePlantMeters: function(pid, mid=null){
    let query = db
    .select("mtr.*").from("meters as mtr")
    .leftJoin("plants as plt", "plt.id", "mtr.plant_id")
    .where("plt.private", 1);
    if(mid){
      query = query.where("mtr.id", mid)
    }
    if(pid){
      query = query.where("plt.id", pid)
    }
    return new Promise((resolve, reject)=>{
      query.then(data=>{
        resolve(data);
      }).catch(error=>{
        resolve([]);
      });
    });
  },
};
module.exports = model;
```

6. Update `plant.js` inside **models** folder.
```javascript
const db = require("../database");

const model = {
  getPublicPlants: function(id = null) {
    return new Promise((resolve, reject) => {
      let query = db.from("plants").where("private", 0);
      if (id) {
        query = query.where("id", id);
      }
      query
        .then(data => {
          resolve(data);
        })
        .catch(error => {
          resolve([]);
        });
      });
  },
  getPrivatePlants: function(id = null) {
    let query = db.from("plants").where("private", 1);
    if (id) {
      query = query.where("id", id);
    }
    return new Promise((resolve, reject)=>{
      query
      .then(data => {
        resolve(data);
      })
      .catch(error => {
        resolve([]);
      });
    });
  }
};

module.exports = model;
```

7. Update `public.js` in **routes** folder. Since our model now return a **Promise**
```javascript
const router = require('express').Router();
const plants = require('../models/plant');
const meters = require('../models/meter');

router.get('/plants', function(req, res){
  plants.getPublicPlants().then(data=>{
    res.json(data);
  });
});

router.get('/plants/:pid', function(req, res){
  plants.getPublicPlants(req.params.pid).then(data=>{
    res.json(data);
  });
});

router.get('/plants/:pid/meters', function(req, res){
  meters.getPublicPlantMeters(req.params.pid).then(data=>{
    res.json(data);
  });
});

router.get('/plants/:pid/meters/:mid', function(req, res){
  meters.getPublicPlantMeters(req.params.pid, req.params.mid).then(data=>{
    res.json(data);
  });
});


module.exports = router;
```

8. Update `private.js` in **routes** folder. Since our model now return a **Promise**
```javascript
const router = require('express').Router();
const plants = require('../models/plant');
const meters = require('../models/meter');

router.get('/plants', function(req, res){
  plants.getPrivatePlants().then(data=>{
    res.json(data);
  });
});

router.get('/plants/:pid', function(req, res){
  plants.getPrivatePlants(req.params.pid).then(data=>{
    res.json(data);
  });
});

router.get('/plants/:pid/meters', function(req, res){
  meters.getPrivatePlantMeters(req.params.pid).then(data=>{
    res.json(data);
  });
});

router.get('/plants/:pid/meters/:mid', function(req, res){
  meters.getPrivatePlantMeters(req.params.pid, req.params.mid).then(data=>{
    res.json(data);
  });
});


module.exports = router;
```

9. Update our `auth.js` inside **middleware** folder to use the cross check data inside **DB**.
```javascript
const db = require('../database');

module.exports = {

  /**
 * Authorization: Basic base64(username:password) 
 */

  auth: function (req, res, next){
    let authorization = req.headers.authorization;
    let base64Auth = authorization.replace('Basic ', '');
    let rawUserPass = Buffer.from(base64Auth, "base64").toString();
    let splitUserPass = rawUserPass.split(":"); // result will be ["username","password"]
    db.from("users")
    .where("username", splitUserPass[0])
    .where("password", splitUserPass[1])
    .then(data=>{
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
```

10. Start testing your route.

11. To create **POST** record let's modify the models to have create a `plant` and `meter` methods. In `plant.js` add this method in model. 
```javascript
let model = {
  ...
  ...
  createPlant: function(payload, private=0){
    return new Promise(function(resolve, reject){
      payload = {...payload, private };
      db('plants').insert(payload).then(data=>{
        resolve({
          message: "Success",
          data: data
        });
      }).catch(error=>{
        reject({
          message: 'There is problem with your request.',
          error: error.toString()
        });
      });
    });
  },
  ...
  ...
};
...
...
...
```
And in `meter.js` add this method.
```javascript
let model = {
  ...
  ...
  createMeter: function(payload, private=0){
    return new Promise(function(resolve, reject){
      db.from("plants").where("id", payload.plant_id)
      .where("private", private)
      .then(data=>{
        if(data.length > 0){
          db('meters').insert(payload).then(data=>{
            resolve({
              message: "Success",
              data: data
            });
          }).catch(error=>{
            reject({
              message: 'There is problem with your request.',
              error: error.toString()
            });
          });
        }else{
          reject({
            message: 'Plant doesn\'t exist.'
          });
        }
      }).catch(error=>{
        reject({
          message: 'There is problem with your request.',
          error: error.toString()
        });
      });
    });
  },
  ...
  ...
  ...
};
...
...
```

12. Now we can modify our `routes` to add `POST` request in `public.js` and `private.js` routes.
In `routes/public.js`, add these routes.
```javascript
...
...
const meters = require('../models/meter');

router.post('/plants', function(req, res){
  plants.createPlant(req.body).then(data=>{
    res.json(data);
  }).catch(error=>{
    res.status(400).json(error)
  });
});

router.post('/plants/:pid/meters', function(req, res){
  meters.createMeter({...req.body, plant_id: req.params.pid}).then(data=>{
    res.json(data);
  }).catch(error=>{
    res.status(400).json(error)
  });
});
...
...
```

In `routes/private.js` add these routes.
```javascript
...
...
const meters = require('../models/meter');

router.post('/plants', function(req, res){
  plants.createPlant(req.body, 1).then(data=>{
    res.json(data);
  }).catch(error=>{
    res.status(400).json(error)
  });
});

router.post('/plants/:pid/meters', function(req, res){
  meters.createMeter({...req.body, plant_id: req.params.pid}, 1).then(data=>{
    res.json(data);
  }).catch(error=>{
    res.status(400).json(error)
  });
});
...
...
```

13. We can test our routes.