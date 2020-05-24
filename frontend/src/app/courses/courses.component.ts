import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { CoursesService } from '../courses.service';
import { tap, mapTo } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { CourseDto } from '../course.dto';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit, OnDestroy {
  public readonly pageSize = 20;
  public currentPage = 0;

  filterSubscription?: Subscription;

  constructor(
    private service: CoursesService,
    public auth: AuthService,
    private router: Router,
  ) { }

  selected = '5';
  courses: CourseDto[];

  ngOnInit(): void {
    this.updateCourses().subscribe();
  }

  ngOnDestroy(): void {
    this.filterSubscription?.unsubscribe();
  }

  goto(path: string, additional: string) {
    console.log(`${path}/${additional}`);
    this.router.navigate([`${path}/${additional}`]);
  }

  private updateCourses(): Observable<null> {
    return this.service.getCourses().pipe(
      tap(courses => {
        this.courses = courses;
      }),
      mapTo(null),
    );
  }
}
