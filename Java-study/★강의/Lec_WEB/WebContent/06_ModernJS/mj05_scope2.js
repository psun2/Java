const value = "hello1"    // global scope

function myFunction() {
    console.log("myFunction: ");
    console.log(value); // "hello1"
}
myFunction();


function ohterFunction(){
    console.log("otherFunction: ")
    //console.log(value)

    //let value = "bye"


}
ohterFunction()

//------------------------------------------
function myFunction2(){
    const value = "bye!"
    const anotherValue = "world"

    function functionInside(){
        console.log("functionInside()")
        console.log(value)
        console.log(anotherValue)
    }
    functionInside()
}

myFunction2()
console.log(value)  // hello1
// console.log(anotherValue)

console.log()

function myFunction3(){
    const value = "bye!"
    if(true){
        const value = "world"
        console.log('block scope value =', value) // world
    }
    console.log('function scope value =', value) // bye
}
myFunction3()

console.log()

function myFunction4(){
    // var : function scope
    var value = "bye!"
    if(true){
        var value = "world"
        console.log('block scope value =', value) // world
    }
    console.log('function scope value =', value) // world ★★
}
myFunction4()

// 이러한 이유 때문에 var 보다는
// const, let 사용을 권장함.










