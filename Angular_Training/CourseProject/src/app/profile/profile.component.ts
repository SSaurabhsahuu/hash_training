import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user= {displayname:'',firstname:'',lastname:'',about:'',
          Designer:false,Developer:false,ProductManager:false,Sales:false,
          Occupation:'',Experience:'', Expertise:'',note:''};

  Interests = [
    {  Name: "Designer",
      Checked: this.user.Designer
    },
    {  Name: "Developer",
      Checked: this.user.Developer
    },
    { Name: "ProductManager",
      Checked: this.user.ProductManager
    },
    { Name: "Sales",
      Checked: this.user.Sales
    }
  ]
  Occupation = [
    {
      Name: "Student",
      Checked: false
    },
    {
      Name: "Professional",
      Checked: false
    }
  ]
  Experience = [
    { Name: "0-5",
      Checked: false
    },
    { Name: "5-10",
      Checked: false
    },
    { Name: "10&above",
      Checked: false
    }
  ]
  Expertise = [
    { Name: "Java",
      Checked: false
    },
    { Name: "Testing",
      Checked: false
    },
    { Name: "React",
      Checked: false
    },
    { Name: "Angular",
      Checked: false
    },
    { Name: "Backend",
      Checked: false
    }
  ]
  cService: CourseService;

  constructor(cService: CourseService,private router:Router) {
    this.cService = cService;
  }

  ngOnInit(): void {
    this.user=this.cService.getProfileDetails();

     this.Interests[0].Checked= this.user.Designer;
     this.Interests[1].Checked= this.user.Developer;
     this.Interests[2].Checked= this.user.ProductManager;
     this.Interests[3].Checked= this.user.Sales;

    console.log("User "+this.user.Designer+" Interest "+this.Interests[0].Checked);
  }
  onCheckout(){
    alert("Courses succesfully Purchased !! ");
    this.cService.deleteAllCart();
    this.router.navigate([`/courses`]);
  }
  onSubmit(submittedForm) {
    if (submittedForm.invalid) {
      return;
    }
    console.log(submittedForm.value);
    this.cService.addProfileDetails(submittedForm.value);

    alert("User Details saved Successfully !! ");
    this.router.navigate([`/courses`]);

  }
}
