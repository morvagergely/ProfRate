import { Input, Component, Output, EventEmitter, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CoursesService } from '../courses.service';
import { CourseDto } from '../course.dto';

@Component({
  selector: 'app-rate-course',
  templateUrl: './rate-course.component.html',
  styleUrls: ['./rate-course.component.scss']
})
export class RateCourseComponent implements OnInit {

  constructor(
    private service: CoursesService,
    private router: Router,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute,
  ) { }

  public course: CourseDto;
  public selected = '5';
  
  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.service.getCourse(params.get('id')).subscribe(res =>
        this.course = res);
    });
  }

  form: FormGroup = new FormGroup({
    rating: new FormControl(''),
  });

  submit() {
    this.error = null;
    
    this.course.averageRating = (this.course.averageRating * 100 + parseInt(this.selected, 10)) / 101;
    this.service.updateCourse(this.course).subscribe(res => {
      if (res) {
        this.snackBar.open("Course successfully rated!", "OK", { duration: 5000, verticalPosition: 'top' });
        this.router.navigate(['courses']);
      }
      else {
        this.error = 'Something went wrong!';
      }
    })
  }

  @Input() error: string | null;

  @Output() submitEM = new EventEmitter();

}
