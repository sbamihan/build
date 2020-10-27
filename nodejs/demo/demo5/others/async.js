const async = require('async');
const fs = require('fs');


function add(cb){
    setTimeout(function(){
        cb(1 + 1);
    }, 1000);
}

function sub(value, cb){
    setTimeout(function(){
        cb(value - 3);
    }, 3000);
}

function mult(value, cb){
    setTimeout(function(){
        cb(value * 2);
    }, 2000);
}

function divide(value, cb){
    setTimeout(function(){
        cb(value / 5);
    }, 1500)
}

// add(function(sum){
//     console.log('Sum: ', sum);
//     if(err){
//         console.log('Error');
//     }else{
//         sub(sum, function(diff){
//             if(err){
//                 console.log('Error');
//             }else{
//                 console.log('Diff: ' , diff);
//                 mult(diff, function(prod){
//                     console.log('Prod: ', prod);
//                     divide(prod, function(quo){
//                         console.log('Quo: ', quo);
//                     });
//                 });
//             }
//         });
//     }
// });

async.auto({
    sum : function(callback){
        add(function(sum){
            console.log('SUM', sum);
            callback(null, sum);
        });
    },
    diff: ['sum', function(result, callback){
        sub(result.sum, function(diff){
            console.log('DIFF', diff);
            callback(null, diff);
        });
    }],
    prod: ['diff', function(result, callback){
        mult(result.diff, function(prod){
            console.log('PROD', prod);
            callback(null, prod)
        })
    }],
    quo: ['prod', function(result, callback){
        divide(result.prod, function(quo){
            console.log('QUO', quo);
            callback(null, quo)
        })
    }]
}, function(err, results){
  console.log(err);
  console.log(results);
});
















// // async.auto({
// //   // this function will just be passed a callback
// //   readData: async.apply(fs.readFile, 'data.txt', 'utf-8'),
// //   showData: ['readData', function(results, cb) {
// //       // results.readData is the file's contents
// //       // ...
// //   }]
// // }, function(results){
// //   console.log(results);
// // });

// async.auto({
//   get_data: function(callback) {
//       console.log('in get_data');
//       // async code to get some data
//       callback(null, 'data', 'converted to array');
//   },
//   make_folder: function(callback) {
//       console.log('in make_folder');
//       // async code to create a directory to store a file in
//       // this is run at the same time as getting the data
//       callback(null, 'folder');
//   },
//   write_file: ['get_data', 'make_folder', function(results, callback) {
//       console.log('in write_file', JSON.stringify(results));
//       // once there is some data and the directory exists,
//       // write the data to a file in the directory
//       callback(null, 'filename');
//   }],
//   email_link: ['write_file', function(results, callback) {
//       console.log('in email_link', JSON.stringify(results));
//       // once the file is written let's email a link to it...
//       // results.write_file contains the filename returned by write_file.
//       callback(null, {'file':results.write_file, 'email':'user@example.com'});
//   }]
// }, function(err, results) {
//   console.log('err = ', err);
//   console.log('results = ', results);
// });