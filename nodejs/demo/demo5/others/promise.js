/**
 * Promise
 * The Promise object represents the eventual completion 
 * (or failure) of an asynchronous operation, and its resulting value.
 * 
 * Ref: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise
 */


//  let timeout = new Promise((resolve, reject)=>{
//    setTimeout(function(){
//      reject(500);
//    }, 1000);
//  });

//  timeout.then(data=>{
//    console.log('Success : ', data);
//  }).catch(error=>{
//    console.log('Error : ', error);
//  });

 //fnTimeout is a function that returns a Promise Object
 function fnTimeout(interval){
   return new Promise((resolve, reject)=>{
     if(interval % 100 == 0){
      reject(
        {
          interval: interval,
          message: 'error',
          error: 'Interval should not be divisible by 100'
        }
      );
     }else{
      setTimeout(function(){
        console.log(`This runs after ${interval}`);
        resolve(
          {
            interval : interval,
            message: 'success',
            data: [1,2,3,4,5,6,7]
          }
        )
      }, interval);
     }
   });
 }

 fnTimeout(1001).then(data=>{
  console.log('Success ', data);
 }).catch(error=>{
   console.log('Error ', error);
 });