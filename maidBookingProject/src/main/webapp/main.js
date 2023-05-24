// header bg reveal

const headerBg = ()=> {
    var header=document.querySelector(".js-header");
    alert("It is okay...Styling ")
    window.addEventListener("scroll",function(){
        if(this.scrollY>0){
            header.classList.add("bg-reveal");
        }
        else{
            header.classList.remove("bg-reveal")
        }
    });
}
headerBg();

//featureslider
var swiper = new Swiper(".FeatureSlider", {
    grabCursor: true,
    spaceBetween: 20,
    centeredSlides: true,
    loop: true,
    autoplay: {
      delay: 2000,
      disableOnInteraction: false,
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    //This is For Responsive
    breakpoints: {
      0: {
        slidesPerView: 1,
      },
      768: {
        slidesPerView: 2,
      },
      1024: {
        slidesPerView: 3,
      },
    },
  });
  
  
  
  
   // One
  
 
  function postOne(){

console.log("in function parameter  ")


 var temp1 = localStorage.getItem("recentCredntial")
 var name = localStorage.getItem("nameCredntial")

   var lname= name.split("_")[0];
   var fname= name.split("_")[1];
   var data= { "email": `${temp1}`  , "FirstName": `${fname}` , "LastName": `${lname}` , "Ordered_Service": "Floor Cleaning" }  

  
  params = {
                     method : 'post',
                     headers : { 'Content-Type': 'application/json'  } ,
                     body : JSON.stringify(data)
  }

const url= "http://localhost:8080/serviceBooking"

  fetch(url, params).then(res=>res.text() ).then(data=>{ console.log(data)  }  );



}


    function func(){
		
		console.log("It is okay...")
	}
  
  
  
  
  //  Two 
  
  
  
  
  
  /*
  
function postTwo(){


localStorage.getItem("lastname")


 var temp1 = localStorage.getItem("recentCredntial")
 var name = localStorage.getItem("nameCredntial")

   var lname= text.split("_");
   var fname= text.split("_");


const body = {
  userMail: `${temp1}` ,
  service_ordered : "Floor Cleaning" ,
  Client_Name: `${fname}`+" "+`${lname}`
};

const url= "http://localhost:8080/maidBookingProject/"
$.post( url , body, (data, status) => {
  console.log(data);
});



}

*/
  
  
  
  
  
  














  
  
