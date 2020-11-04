/**
 * Callback functions
 * A callback function is a function passed into another function as an argument, 
 * which is then invoked inside the outer function to complete some kind of routine or action.
 * 
 * Ref: https://developer.mozilla.org/en-US/docs/Glossary/Callback_function
 */


// function processUserInput(name, callback){
//   callback(name);
// }

// let greetings = function(cb_name){
//   console.log('This is from callback : ', cb_name);
// };

// function greetings(cb_name){
//   console.log('This is from callback : ', cb_name);
// }

// processUserInput('Ken', function(cb_name){
//   console.log('This is from callback : ', cb_name);
// });

// processUserInput('Ken', greetings);



function timeout(interval, callback){
  console.log('Called `timeout` function');
  callback(interval);
}

timeout(2000, function(skip){
  console.log('Inside `callback` function');
  setTimeout(function(){
    console.log(`Runs after ${skip} second/s`)
  }, skip);
  console.log('This output before the timeout.');
});