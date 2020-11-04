const fs = require('fs');
const path = require('path');

function getDestination(req, file, cb) {
  cb(null, path.join(__dirname, `../upload/${file.originalname}`));
}

function DiskStorage(opts) {
  this.getDestination = opts.destination || getDestination;
}

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
          height: 1000,
          width: 1000
        });
    });
  });
};

DiskStorage.prototype._removeFile = function _removeFile(req, file, cb) {
  // unlink the file if there is problem
  fs.unlink(file.path, cb);
};

module.exports = function(opts) {
  return new DiskStorage(opts);
};

